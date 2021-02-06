package jp.go.aist.rtm.systemeditor.ui.editor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.FileEditorInput;
import org.openrtp.namespaces.rts.version02.RtsProfileExt;
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
import jp.go.aist.rtm.systemeditor.ui.editor.action.OpenAndCreateRestoreAction;
import jp.go.aist.rtm.systemeditor.ui.editor.action.OpenAndQuickRestoreAction;
import jp.go.aist.rtm.systemeditor.ui.editor.action.OpenAndRestoreAction;
import jp.go.aist.rtm.systemeditor.ui.editor.action.OpenWithMappingRestoreAction;
import jp.go.aist.rtm.systemeditor.ui.editor.action.RestoreOption;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
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

	@Override
	protected void createActions() {
		super.createActions();
		addAction(new OpenAndRestoreAction(this));
		addAction(new OpenAndQuickRestoreAction(this));
		addAction(new OpenAndCreateRestoreAction(this));
		addAction(new OpenWithMappingRestoreAction(this));
	}

	@SuppressWarnings("unchecked")
	private void addAction(IAction action) {
		getActionRegistry().registerAction(action);
		getPropertyActions().add(action.getId());
	}

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

		GraphicalViewer viewer = getGraphicalViewer();

		ContextMenuProvider provider = new SystemDiagramContextMenuProvider(
				viewer, getActionRegistry());
		viewer.setContextMenu(provider);
		((IEditorSite) getSite()).registerContextMenu(provider, viewer, false);

		SystemEditorPreferenceManager.getInstance().addPropertyChangeListener(
				preferenceListener);
	}

	protected IEditorInput load(IEditorInput input, final IEditorSite site,
			final RestoreOption restore) throws PartInitException {

		IEditorInput targetInput = getTargetInput(input, "System Editor");

		if (getSystemDiagram() != null) {
			getSystemDiagram().setSynchronizeInterval(0);
		}

		if (targetInput instanceof FileEditorInput) {
			// RTSプロファイルをファイルからロードする
			if (restore.doMapping()) {
				doLoadWithMapping(site, (FileEditorInput) targetInput);
			} else {
				doLoad(site, restore, (FileEditorInput) targetInput);
			}
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

	private void doLoad(final IEditorSite site, final RestoreOption restore,
			FileEditorInput editorInput) throws PartInitException {
		try {
			final String strPath = editorInput.getPath().toOSString();

			ProgressMonitorDialog dialog = new ProgressMonitorDialog(
					site.getShell());
			IRunnableWithProgress runable = new IRunnableWithProgress() {
				@Override
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {
					monitor.beginTask(
							Messages.getString("SystemDiagramEditor.3"), 100);
					monitor.subTask(Messages.getString("SystemDiagramEditor.4"));

					try {
						RtsProfileHandler handler = new RtsProfileHandler();

						// STEP1: ファイルからRTSプロファイルオブジェクトを作成
						monitor.internalWorked(20);

						RtsProfileExt profile = handler.load(strPath);

						// STEP2: 拡張ポイント (ダイアグラム生成前)
						monitor.internalWorked(20);

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

						// STEP3: RTSプロファイルオブジェクトからダイアグラムを作成
						monitor.internalWorked(20);

						SystemDiagram diagram = handler.load(profile,
								SystemDiagramKind.ONLINE_LITERAL);

						if (restore.doQuick()) {
							handler.populateCorbaBaseObject(diagram);
						}
						SystemEditorWrapperFactory.getInstance()
								.getSynchronizationManager()
								.assignSynchonizationSupportToDiagram(diagram);
						// リモートコンのポーネントが未起動時に、コンポーネントを生成するか指定
						Rehabilitation.rehabilitation(diagram,
								restore.doCreate());

						// 読み込み時に明示的に状態の同期を実行
						List<Component> eComps = new ArrayList<>(
								diagram.getComponents());
						diagram.getComponents().clear();
						for (Component c : eComps) {
							c.synchronizeManually();
							diagram.addComponent(c);
						}
						handler.restoreCompositeComponentPort(diagram);

						SystemDiagram oldDiagram = getSystemDiagram();
						setSystemDiagram(diagram);

						// STEP4: 拡張ポイント (ダイアグラム生成後)
						monitor.internalWorked(20);

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
					} catch (Exception e) {
						monitor.done();
						throw new InvocationTargetException(
								e,
								Messages.getString("SystemDiagramEditor.5") + "\r\n" + e.getMessage()); //$NON-NLS-1$
					}

					monitor.internalWorked(35);

					if (restore.doReplace()) {
						monitor.subTask(Messages
								.getString("SystemDiagramEditor.7")); //$NON-NLS-1$
						try {
							RtsProfileHandler handler = new RtsProfileHandler();
							handler.restoreConnection(getSystemDiagram());
							handler.restoreConfigSet(getSystemDiagram());
							handler.restoreExecutionContext(getSystemDiagram());
							doReplace(getSystemDiagram(), site);
						} catch (Exception e) {
							LOGGER.error("Fail to replace diagram", e);
							throw new InvocationTargetException(e,
									Messages.getString("SystemDiagramEditor.8")); //$NON-NLS-1$
						}
					}

					monitor.done();
				}
			};
			dialog.run(false, false, runable);
		} catch (Exception e) {
			throw new PartInitException(
					Messages.getString("SystemDiagramEditor.9"), e); //$NON-NLS-1$
		}
	}

	private void doLoadWithMapping(final IEditorSite site,
			FileEditorInput editorInput) throws PartInitException {
		try {
			final String strPath = editorInput.getPath().toOSString();

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
				
				//TODO 復元処理

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

			} catch (Exception e) {
				throw new InvocationTargetException(e,
						Messages.getString("SystemDiagramEditor.5") + "\r\n"
								+ e.getMessage());
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

		} catch (Exception e) {
			throw new PartInitException(
					Messages.getString("SystemDiagramEditor.9"), e);
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
