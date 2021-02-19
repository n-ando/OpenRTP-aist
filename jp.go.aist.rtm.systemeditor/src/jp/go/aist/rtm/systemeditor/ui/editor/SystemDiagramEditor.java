package jp.go.aist.rtm.systemeditor.ui.editor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.FileEditorInput;
import org.openrtp.namespaces.rts.version02.DataportConnector;
import org.openrtp.namespaces.rts.version02.Property;
import org.openrtp.namespaces.rts.version02.RtsProfileExt;
import org.openrtp.namespaces.rts.version02.ServiceportConnector;
import org.openrtp.namespaces.rts.version02.TargetPortExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.go.aist.rtm.systemeditor.extension.LoadProfileExtension;
import jp.go.aist.rtm.systemeditor.factory.ProfileLoader;
import jp.go.aist.rtm.systemeditor.factory.Rehabilitation;
import jp.go.aist.rtm.systemeditor.factory.SystemEditorWrapperFactory;
import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.restoration.Restoration;
import jp.go.aist.rtm.systemeditor.restoration.Result;
import jp.go.aist.rtm.systemeditor.ui.dialog.RestoreComponentDialog;
import jp.go.aist.rtm.systemeditor.ui.dialog.param.ComponentInfo;
import jp.go.aist.rtm.systemeditor.ui.dialog.param.ConnectorInfo;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind;
import jp.go.aist.rtm.toolscommon.util.RtsProfileHandler;

/**
 * SystemDiagramEditorクラス
 */
public class SystemDiagramEditor extends AbstractSystemDiagramEditor {

	private static final Logger LOGGER = LoggerFactory.getLogger(SystemDiagramEditor.class);

	/**
	 * システムダイアグラムエディタのID
	 */
	public static final String SYSTEM_DIAGRAM_EDITOR_ID = "jp.go.aist.rtm.systemeditor.ui.editor.SystemDiagramEditor"; //$NON-NLS-1$

	/**
	 * 設定の変更に対するリスナ
	 */
	PropertyChangeListener preferenceListener = new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent evt) {
			getSystemDiagram()
					.setSynchronizeInterval(
							SystemEditorPreferenceManager
									.getInstance()
									.getInterval(
											SystemEditorPreferenceManager.SYNC_SYSTEMEDITOR_INTERVAL));
		}
	};

	@Override
	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();

		SystemEditorPreferenceManager.getInstance().addPropertyChangeListener(
				preferenceListener);
	}

	protected IEditorInput load(IEditorInput input, final IEditorSite site)
			throws PartInitException {

		IEditorInput targetInput = getTargetInput(input, "System Editor");

		if (getSystemDiagram() != null) {
			getSystemDiagram().setSynchronizeInterval(0);
		}

		if (targetInput instanceof FileEditorInput) {
			doLoadWithMapping(site, (FileEditorInput) targetInput);
		}

		// システムダイアグラムの同期スレッド開始
		getSystemDiagram()
				.setSynchronizeInterval(
						SystemEditorPreferenceManager
								.getInstance()
								.getInterval(
										SystemEditorPreferenceManager.SYNC_SYSTEMEDITOR_INTERVAL));

		postLoad();

		return targetInput;
	}

	private void doLoadWithMapping(final IEditorSite site,
			FileEditorInput editorInput) throws PartInitException {
		try {
			final String strPath = editorInput.getPath().toOSString();
			
			List<ComponentInfo> componentList = new ArrayList<ComponentInfo>();
			 Map<String, Component> compIdMap = new HashMap<String, Component>();
			 String errMsg = "";

			try {
				RtsProfileHandler handler = new RtsProfileHandler();
				RtsProfileExt profile = handler.load(strPath);

				// STEP2: 拡張ポイント (ダイアグラム生成前)
				ProfileLoader creator = new ProfileLoader();
				for (LoadProfileExtension.ErrorInfo info : creator
						.preLoad(profile, strPath)) {
					if (info.isError()) {
						openError(DIALOG_TITLE_ERROR, info.getMessage());
						return;
					} else {
						if (!openConfirm(DIALOG_TITLE_CONFIRM,
								info.getMessage())) {
							return;
						}
					}
				}
				
				// プロファイル読込
				SystemDiagram diagram = handler.load(profile,
						SystemDiagramKind.ONLINE_LITERAL);

				// CORBAコンポーネント抽出
				List<CorbaComponent> corbaComponents = new ArrayList<>();
				for (Component c : diagram.getRegisteredComponents()) {
					if (c instanceof CorbaComponent) {
						corbaComponents.add((CorbaComponent) c);
					}
				}

				// マッピング設定ダイアログを開始
				RestoreComponentDialog dialog = new RestoreComponentDialog(
						getSite().getShell());
				dialog.setSystemInfo(corbaComponents, profile);
				dialog.setSystemDiagram(diagram);
				if (dialog.open() != IDialogConstants.OK_ID) {
					return;
				}
				
				componentList = dialog.getComponentList();

				// 同期サポート割当
				SystemEditorWrapperFactory.getInstance()
						.getSynchronizationManager()
						.assignSynchonizationSupportToDiagram(diagram);

				// 読み込み時に明示的に状態の同期を実行
				List<Component> eComps = new ArrayList<>(diagram.getComponents());
				diagram.getComponents().clear();
				for (Component c : eComps) {
					Component targetComp = c;
					boolean isSkip = false;
					for(ComponentInfo info :componentList) {
						if(info.getCorbaComponent() == c) {
							if(info.isRestore()==false) {
								isSkip = true;
								break;
							}
							compIdMap.put(c.getComponentId(), targetComp);
							targetComp = info.getSelectedRTC();
							if(targetComp == null) {
								errMsg = info.getStatus();
							}
							targetComp.setConstraint(c.getConstraint());
							
							c.setPathId(targetComp.getPathId());
							info.getProfile().setPathUri(targetComp.getPathId());
							c.setComponentId(targetComp.getComponentId());
							c.setInstanceNameL(targetComp.getInstanceNameL());
						}
					}
					if(isSkip) continue;
					
					Rehabilitation.rehabilitateComponent(targetComp, diagram, false);
					targetComp.synchronizeManually();
					diagram.addComponent(targetComp);
				}

				handler.restoreCompositeComponentPort(diagram);

				SystemDiagram oldDiagram = getSystemDiagram();
				setSystemDiagram(diagram);
				
				// STEP4: 拡張ポイント (ダイアグラム生成後)
				for (LoadProfileExtension.ErrorInfo info : creator
						.postLoad(diagram, profile, oldDiagram)) {
					if (info.isError()) {
						openError(DIALOG_TITLE_ERROR, info.getMessage());
						return;
					} else {
						if (!openConfirm(DIALOG_TITLE_CONFIRM,
								info.getMessage())) {
							return;
						}
					}
				}
				/////
				adjustPortProfile(componentList, compIdMap, profile);

			} catch (Exception e) {
				StringBuilder errbuilder = new StringBuilder();
				errbuilder.append(Messages.getString("SystemDiagramEditor.5"));
				if(e.getMessage() != null && 0 < e.getMessage().length() ) {
					errbuilder.append("\r\n").append(e.getMessage());
				}
				if(errMsg != null && 0 < errMsg.length()) {
					errbuilder.append("\r\n").append(errMsg);
				}
				throw new InvocationTargetException(e, errbuilder.toString());
			}

			try {
				RtsProfileHandler handler = new RtsProfileHandler();
				handler.restoreConnection(getSystemDiagram());
				handler.restoreConfigSet(getSystemDiagram());
				handler.restoreExecutionContext(getSystemDiagram());
				doReplace(getSystemDiagram(), site);
			} catch (Exception e) {
				LOGGER.error("Fail to replace diagram", e);
				throw new InvocationTargetException(e,
						Messages.getString("SystemDiagramEditor.8"));
			}
			//////////
			for(ComponentInfo comp : componentList) {
				if(comp.isRestore() == false) continue;
				CorbaComponent target = comp.getSelectedRTC();
				int state = target.getComponentState();
				if(comp.isActivate()) {
					if(state == ExecutionContext.RTC_ACTIVE) continue;
					target.activateR();
				} else {
					if(state == ExecutionContext.RTC_INACTIVE) continue;
					target.deactivateR();
				}
			}

		} catch (Exception e) {
			throw new PartInitException(
					Messages.getString("SystemDiagramEditor.9"), e);
		}
	}

	private void adjustPortProfile(List<ComponentInfo> componentList, Map<String, Component> compIdMap,
			RtsProfileExt profile) {
		List<DataportConnector> removeDataList = new ArrayList<DataportConnector>();
		List<ServiceportConnector> removeServiceList = new ArrayList<ServiceportConnector>();
		for(ComponentInfo info :componentList) {
			for(ConnectorInfo conn : info.getConnectorList()) {
				if(conn.isConnect() == false ) {
					for(DataportConnector d_conn : profile.getDataPortConnectors() ) {
						if(d_conn == conn.getDataConnector()) {
							removeDataList.add(d_conn);
							break;
						}
					}
					for(ServiceportConnector s_conn : profile.getServicePortConnectors() ) {
						if(s_conn == conn.getServiceConnector()) {
							removeServiceList.add(s_conn);
							break;
						}
					}
				}
			}
		}
		if( 0<removeDataList.size() ) {
			for(DataportConnector d_conn : removeDataList ) {
				profile.getDataPortConnectors().remove(d_conn);
			}
		}
		if( 0<removeServiceList.size() ) {
			for(ServiceportConnector s_conn : removeServiceList ) {
				profile.getServicePortConnectors().remove(s_conn);
			}
		}
		
		if(profile.getDataPortConnectors()!=null) {
			for(DataportConnector each : profile.getDataPortConnectors()) {
				replacePortInfo(compIdMap, (TargetPortExt)each.getSourceDataPort());
				replacePortInfo(compIdMap, (TargetPortExt)each.getTargetDataPort());
			}
		}
		if(profile.getServicePortConnectors()!=null) {
			for(ServiceportConnector each : profile.getServicePortConnectors()) {
				replacePortInfo(compIdMap, (TargetPortExt)each.getSourceServicePort());
				replacePortInfo(compIdMap, (TargetPortExt)each.getTargetServicePort());
			}
		}
	}

	private void replacePortInfo(Map<String, Component> compIdMap, TargetPortExt port) {
		if(compIdMap.containsKey(port.getComponentId())) {
			Component comp = compIdMap.get(port.getComponentId());
			port.setComponentId(comp.getComponentId());
			String portName = port.getPortName();
			portName = portName.replace(port.getInstanceName(), comp.getInstanceNameL());
			port.setPortName(portName);
			port.setInstanceName(comp.getInstanceNameL());
			for(Property prop : port.getProperties()) {
				if(prop.getName().equals("COMPONENT_PATH_ID")) {
					prop.setValue(comp.getPathId());
				}
			}
		}
	}

	public void updateWithMapping(IEditorSite site, SystemDiagram diagram, RtsProfileHandler handler) {
		// 同期サポート割当
		SystemEditorWrapperFactory.getInstance()
				.getSynchronizationManager()
				.assignSynchonizationSupportToDiagram(diagram);

		// 読み込み時に明示的に状態の同期を実行
		List<Component> eComps = new ArrayList<>(
				diagram.getComponents());
		diagram.getComponents().clear();
		for (Component c : eComps) {
			c.synchronizeManually();
			diagram.addComponent(c);
		}

		handler.restoreCompositeComponentPort(diagram);

		setSystemDiagram(diagram);

		try {
			RtsProfileHandler handlerProf = new RtsProfileHandler();
			handlerProf.restoreConnection(getSystemDiagram());
			handlerProf.restoreConfigSet(getSystemDiagram());
			handlerProf.restoreExecutionContext(getSystemDiagram());
			doReplace(getSystemDiagram(), site);
		} catch (Exception e) {
			LOGGER.error("Fail to replace diagram", e);
		}
	}

	/**
	 * ロード時の復元を行います。
	 */
	public void doReplace(SystemDiagram systemDiagram, IEditorSite site) {
		final StringBuffer buffer = new StringBuffer();
		Result resultHolder = new Result() {
			private boolean success;

			public boolean isSuccess() {
				return success;
			}

			public void setSuccess(boolean success) {
				this.success = success;
			}

			public void putResult(String resultPart) {
				buffer.append(resultPart + "\r\n"); //$NON-NLS-1$
			}
		};
		resultHolder.setSuccess(true);
		Restoration.processAllRestoreConfigurationSet(resultHolder, systemDiagram );
		if (resultHolder.isSuccess() == false) {
			Dialog dialog = new jp.go.aist.rtm.toolscommon.ui.dialog.ErrorDialog(
					site.getShell(), Messages.getString("Common.dialog.error_title"), null, Messages.getString("SystemDiagramEditor.13"), buffer //$NON-NLS-1$ //$NON-NLS-2$
							.toString(), MessageDialog.ERROR);
			dialog.open();
		}
	}

	@Override
	public void dispose() {
		getSystemDiagram().setSynchronizeInterval(-1);

		super.dispose();

		SystemEditorPreferenceManager.getInstance()
				.removePropertyChangeListener(preferenceListener);
	}

	@Override
	public boolean isOnline() {
		return true;
	}

	@Override
	public String getEditorId() {
		return SYSTEM_DIAGRAM_EDITOR_ID;
	}

}
