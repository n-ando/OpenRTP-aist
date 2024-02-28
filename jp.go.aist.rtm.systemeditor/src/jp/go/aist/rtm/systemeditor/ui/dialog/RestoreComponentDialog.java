package jp.go.aist.rtm.systemeditor.ui.dialog;

import static jp.go.aist.rtm.systemeditor.ui.util.UIUtil.COLOR_UNEDITABLE;
import static jp.go.aist.rtm.systemeditor.ui.util.UIUtil.getColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.openrtp.namespaces.rts.version02.Component;
import org.openrtp.namespaces.rts.version02.DataportConnector;
import org.openrtp.namespaces.rts.version02.RtsProfileExt;
import org.openrtp.namespaces.rts.version02.ServiceportConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import RTC.RTObjectHelper;
import RTM.ManagerHelper;
import jp.go.aist.rtm.nameserviceview.model.manager.NameServerManager;
import jp.go.aist.rtm.nameserviceview.model.manager.impl.NameServerManagerImpl;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingContextNode;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingObjectNode;
import jp.go.aist.rtm.systemeditor.corba.CORBAHelper;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.dialog.param.ComponentInfo;
import jp.go.aist.rtm.systemeditor.ui.dialog.param.ConnectorInfo;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.manager.ManagerFactory;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;

/**
 * RTシステム復元設定を行うダイアログ
 */
public class RestoreComponentDialog extends Dialog {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RestoreComponentDialog.class);

	static final String LABEL_MAPPING_MESSAGE = Messages
			.getString("RestoreComponentDialog.head_message");

	static final String MSG_STATUS_NO_EP_NAME = Messages
			.getString("RestoreComponentDialog.msg_no_ep_name");
	static final String MSG_STATUS_NEED_CREATE = Messages
			.getString("RestoreComponentDialog.msg_need_create");

	private static final String LABEL_COMPONENT_NAME = "Component Name:";
	private static final String LABEL_COMPONENT_ID = "Component Id:";
	private static final String LABEL_NODE = "Node:";
	private static final String LABEL_CONTAINER = "Container:";
	private static final String LABEL_CONFIG = "   Active Config. Set:";

	private static final String LABEL_SOURCE_PORT_NAME = "Source Port:";
	private static final String LABEL_DATA_TYPE = "Data Type:";
	private static final String LABEL_TARGET_PORT_NAME = "Target Port:";
	private static final String LABEL_TARGET_COMP_NAME = "Target RTC:";

	static final int APPLY_ID = 998;
	
	private TableViewer tableViewer;
	private TableViewer connectorTableViewer;

	private Label componentNameLabel;
	private Label componentIdLabel;
	
	private Button includeBtn;
	private Button createBtn;
	private Label rtcLabel;
	private Combo rtcCombo;
	private Label nodeLabel;
	private Text nodeText;
	private Label mgrLabel;
	private Text containerText;
	private Button activateBtn;
	
	private Button configurationBtn;
	private Label configLabel;
	private Label configText;
	private Button configDetailBtn;

	private Button connectBtn;
	private Label sourcePortLabel;
	private Label targetPortLabel;
	private Label targetComponentLabel;

	private SystemDiagram diagram;
	
	private List<ComponentInfo> componentList = new ArrayList<>();
	private List<ConnectorInfo> connectorList = new ArrayList<>();
	private List<RTCInfo> rtcList = new ArrayList<>();
	private ComponentInfo selectedTarget;
	private ConnectorInfo selectedConnector;

	private EndpointCache endpoints = new EndpointCache();
	
	private ConnectorLabelProvider connectorProvider = new ConnectorLabelProvider();

	public List<ComponentInfo> getComponentList() {
		return componentList;
	}

	public RestoreComponentDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(getShellStyle() | SWT.CENTER | SWT.RESIZE);
	}

	public void setSystemDiagram(SystemDiagram source) {
		this.diagram = source;
	}
	
	public void setSystemInfo(List<CorbaComponent> list, RtsProfileExt profile) {
		NameServerManager ns = NameServerManagerImpl.getInstance();
		EList<?> nscomps = ns.getNodes();
		List<CorbaComponent> componentCandidates = new ArrayList<CorbaComponent>(); 
		componentCandidates = searchComponentList(nscomps, componentCandidates);
		for(CorbaComponent comp : componentCandidates) {
			RTCInfo rtc = new RTCInfo(comp);
			this.rtcList.add(rtc);
		}
		/////
		if (list != null) {
			for (CorbaComponent comp : list) {
				Component targetComp = null;
				for(Component proComp : profile.getComponents()) {
					if(comp.getComponentId().equals(proComp.getId())
							&& comp.getInstanceNameL().equals(proComp.getInstanceName())
							&& comp.getPathId().equals(proComp.getPathUri())) {
						targetComp = proComp;
						break;
					}
				}
				ComponentInfo target = new ComponentInfo(comp, targetComp);
				if(target.getCompRawId() != null && 0 < target.getCompRawId().length()) {
					for(RTCInfo rtc : rtcList) {
						if(rtc.compId.equals(target.getCompRawId()) && rtc.instanceName.equals(target.getCompName())) {
							target.setSelectedRTC(rtc.component);
							break;
						}
					}
				}
				this.componentList.add(target);
				String epName = target.getNode();
				if (epName == null) {
					continue;
				}
				String[] epList = epName.split(",");
				Endpoint ep = null;
				for(String each : epList) {
					ep = this.endpoints.get(each);
					if(ep!=EndpointCache.NULL_ENDPOINT) break;
				}
				RTC.RTObject rtc = ep.getComponent(target.getCompName());
				if (rtc != null) {
					target.setTarget(rtc);
				}	
			}
		}
		if(profile != null) {
			for(DataportConnector conn : profile.getDataPortConnectors()) {
				ConnectorInfo target = new ConnectorInfo(conn); 
				this.connectorList.add(target);
				//
				for(ComponentInfo comp : this.componentList) {
					if(comp.getCompRawId().equals(target.getSourceRTC()) || 
							comp.getCompRawId().equals(target.getTargetRTC())) {
						comp.addConnector(target);
					}
				}
			}
			for(ServiceportConnector conn : profile.getServicePortConnectors()) {
				ConnectorInfo target = new ConnectorInfo(conn); 
				this.connectorList.add(target);
				//
				for(ComponentInfo comp : this.componentList) {
					if(comp.getCompRawId().equals(target.getSourceRTC()) || 
							comp.getCompRawId().equals(target.getTargetRTC())) {
						comp.addConnector(target);
					}
				}
			}
		}
	}

	private List<CorbaComponent> searchComponentList(EList target, List<CorbaComponent> result) {
		for(int index=0;index<target.size();index++) {
			if( target.get(index) instanceof NamingObjectNode ) {
				NamingObjectNode obj = ((NamingObjectNode)target.get(index));
				try {
					if( obj.getCorbaObject()._is_a(RTObjectHelper.id()) ) {
						CorbaComponent component = (CorbaComponent)(jp.go.aist.rtm.toolscommon.model.component.Component) AdapterUtil.getAdapter(obj,jp.go.aist.rtm.toolscommon.model.component.Component.class);
						obj.getSynchronizationSupport().getSynchronizationManager().assignSynchonizationSupport(component);					
						component.synchronizeManually();
						component.setIor(obj.getCorbaObject().toString());
						result.add(component);
					}
				} catch (Exception e) {
					LOGGER.error("Fail to search component", e);
				}
			} else {
				EList nscomps = ((NamingContextNode)target.get(index)).getNodes();
				searchComponentList(nscomps, result);
			}
		}
		return result;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite dialogArea = (Composite) super.createDialogArea(parent);

		createRTCTableArea(dialogArea);
		createDetailArea(dialogArea);

		buildData();

		return dialogArea;
	}

	private void createRTCTableArea(Composite dialogArea) {
		GridLayout gl;
		GridData gd;
		Composite mainComposite = new Composite(dialogArea, SWT.NONE);
		gl = new GridLayout(1, false);
		gd = new GridData(GridData.FILL_BOTH);
		mainComposite.setLayout(gl);
		mainComposite.setLayoutData(gd);
		mainComposite.setFont(dialogArea.getFont());

		this.tableViewer = new TableViewer(mainComposite,
				SWT.FULL_SELECTION | SWT.SINGLE | SWT.BORDER);
		this.tableViewer.setContentProvider(new ArrayContentProvider());
		this.tableViewer.setLabelProvider(new TargetLabelProvider());
		this.tableViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						StructuredSelection selection = (StructuredSelection) event
								.getSelection();
						LOGGER.trace(
								"Restore: tableViewer.selectionChanged: selectoin=<{}>",
								selection);
						notifyModified();
						selectedTarget = (ComponentInfo) selection
								.getFirstElement();
						refreshData();
						setWidgetStatus();
					}
				});

		Table table = this.tableViewer.getTable();
		gl = new GridLayout(1, false);
		gd = new GridData();
		gd.verticalAlignment = SWT.FILL;
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 2;
		gd.heightHint = 200;
		table.setLayout(gl);
		table.setLayoutData(gd);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		TableColumn col = new TableColumn(table, SWT.NONE);
		col.setText("");
		col.setWidth(20);
		col.setAlignment(SWT.CENTER);
		
		col = new TableColumn(table, SWT.NONE);
		col.setText("Component Name");
		col.setWidth(150);
		
		col = new TableColumn(table, SWT.NONE);
		col.setText("Component Id");
		col.setWidth(300);
		///
		col = new TableColumn(table, SWT.NONE);
		col.setText("Create");
		col.setWidth(50);
		col.setAlignment(SWT.CENTER);
		
		col = new TableColumn(table, SWT.NONE);
		col.setText("Target RTC");
		col.setWidth(120);
		
		col = new TableColumn(table, SWT.NONE);
		col.setText("Node");
		col.setWidth(100);
		
		col = new TableColumn(table, SWT.NONE);
		col.setText("Container");
		col.setWidth(120);
		///
		col = new TableColumn(table, SWT.NONE);
		col.setText("Activate");
		col.setWidth(60);
		col.setAlignment(SWT.CENTER);
		
		col = new TableColumn(table, SWT.NONE);
		col.setText("Config. Set");
		col.setWidth(70);
		col.setAlignment(SWT.CENTER);
	}

	private void createDetailArea(Composite dialogArea) {
		GridLayout gl;
		GridData gd;
		Composite detailComposite = new Composite(dialogArea, SWT.NONE);
		gl = new GridLayout(1, false);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		detailComposite.setLayout(gl);
		detailComposite.setLayoutData(gd);
		detailComposite.setFont(dialogArea.getFont());

		gl = new GridLayout();
		gl.numColumns = 5;
		detailComposite.setLayout(gl);

		this.includeBtn = new Button(detailComposite, SWT.CHECK);
		this.includeBtn.setText("Restore");
		this.includeBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				setWidgetStatus();
			}
		});
			
		Label compLabel = new Label(detailComposite, SWT.NONE);
		compLabel.setText(LABEL_COMPONENT_NAME);

		this.componentNameLabel = new Label(detailComposite, SWT.BORDER);
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		this.componentNameLabel.setLayoutData(gd);
		this.componentNameLabel.setBackground(getColor(COLOR_UNEDITABLE));
		///
		Label compIdLabel = new Label(detailComposite, SWT.NONE);
		compIdLabel.setText(LABEL_COMPONENT_ID);
		
		this.componentIdLabel = new Label(detailComposite, SWT.BORDER);
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		this.componentIdLabel.setLayoutData(gd);
		this.componentIdLabel.setBackground(getColor(COLOR_UNEDITABLE));
		/////
		Group detailGroup = new Group(detailComposite, SWT.SHADOW_IN);
		gl = new GridLayout(5, false);
		detailGroup.setLayout(gl);
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.horizontalSpan = 5;
		gd.grabExcessHorizontalSpace = true;
		detailGroup.setLayoutData(gd);
			
		this.createBtn = new Button(detailGroup, SWT.CHECK);
		this.createBtn.setText("Create RTC");
		this.createBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				setWidgetStatus();
			}
		});
		
		this.rtcLabel = new Label(detailGroup, SWT.NONE);
		this.rtcLabel.setText("Target RTC:");
		this.rtcCombo = new  Combo(detailGroup, SWT.DROP_DOWN | SWT.READ_ONLY);
		gd = new GridData();
		gd.horizontalSpan = 3;
		this.rtcCombo.setLayoutData(gd);
		this.rtcCombo.add("");
		for(RTCInfo rtc : rtcList) {
			this.rtcCombo.add(rtc.compId);
		}
		/////
		Label dummyLabel = new Label(detailGroup, SWT.NONE);
		dummyLabel.setText("");
			
		this.nodeLabel = new Label(detailGroup, SWT.NONE);
		this.nodeLabel.setText(LABEL_NODE);

		this.nodeText = new Text(detailGroup, SWT.SINGLE
				| SWT.BORDER);
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		this.nodeText.setLayoutData(gd);
		/////
		this.mgrLabel = new Label(detailGroup, SWT.NONE);
		this.mgrLabel.setText(LABEL_CONTAINER);

		this.containerText = new Text(detailGroup, SWT.SINGLE
				| SWT.BORDER);
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		this.containerText.setLayoutData(gd);
		/////
		this.activateBtn = new Button(detailGroup, SWT.CHECK);
		this.activateBtn.setText("Activate RTC");
		/////
		Label dummyLabe2 = new Label(detailGroup, SWT.NONE);
		dummyLabe2.setText("");
		
		Composite configComposite = new Composite(detailGroup, SWT.NONE);
		gl = new GridLayout(1, false);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		configComposite.setLayout(gl);
		configComposite.setLayoutData(gd);
		configComposite.setFont(dialogArea.getFont());
		gl = new GridLayout();
		gl.numColumns = 4;
		configComposite.setLayout(gl);
		
		this.configurationBtn = new Button(configComposite, SWT.CHECK);
		this.configurationBtn.setText("Load Configuration");
		this.configurationBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setWidgetStatus();
			}
		});
		
		this.configLabel = new Label(configComposite, SWT.NONE);
		this.configLabel.setText(LABEL_CONFIG);
		
		this.configText = new Label(configComposite, SWT.BORDER);
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		this.configText.setLayoutData(gd);
		this.configText.setBackground(getColor(COLOR_UNEDITABLE));
		
		this.configDetailBtn = new Button(configComposite, SWT.PUSH);
		this.configDetailBtn.setText("Detail Setting");
		this.configDetailBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(selectedTarget == null) return;
				RestoreConfigurationDialog dialog = new RestoreConfigurationDialog(getShell(), selectedTarget.getProfile());
				dialog.open();
				configText.setText(selectedTarget.getProfile().getActiveConfigurationSet());
			}
		});
		//////////
		this.connectorTableViewer = new TableViewer(detailGroup,
				SWT.FULL_SELECTION | SWT.SINGLE | SWT.BORDER);
		this.connectorTableViewer.setContentProvider(new ArrayContentProvider());
		this.connectorTableViewer.setLabelProvider(connectorProvider);
		this.connectorTableViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
					StructuredSelection selection = (StructuredSelection) event
							.getSelection();
					notifyModifiedConnector();
					selectedConnector = (ConnectorInfo) selection
							.getFirstElement();
					refreshDataConnector();
			}
		});
		createPortTableArea(detailGroup);
	}

	private void createPortTableArea(Group detailGroup) {
		GridLayout gl;
		GridData gd;
		Table table = this.connectorTableViewer.getTable();
		gl = new GridLayout(1, false);
		gd = new GridData();
		gd.verticalAlignment = SWT.FILL;
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 5;
		gd.heightHint = 100;
		table.setLayout(gl);
		table.setLayoutData(gd);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		TableColumn col = new TableColumn(table, SWT.NONE);
		col.setText("");
		col.setWidth(20);
		col.setAlignment(SWT.CENTER);
		
		col = new TableColumn(table, SWT.NONE);
		col.setText(LABEL_SOURCE_PORT_NAME);
		col.setWidth(150);
		
		col = new TableColumn(table, SWT.NONE);
		col.setText(LABEL_DATA_TYPE);
		col.setWidth(200);
		
		col = new TableColumn(table, SWT.NONE);
		col.setText(LABEL_TARGET_PORT_NAME);
		col.setWidth(150);
		///
		col = new TableColumn(table, SWT.NONE);
		col.setText(LABEL_TARGET_COMP_NAME);
		col.setWidth(400);
		/////
		Composite detailComposite = new Composite(detailGroup, SWT.NONE);
		gl = new GridLayout(1, false);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 5;
		detailComposite.setLayout(gl);
		detailComposite.setLayoutData(gd);
		detailComposite.setFont(detailGroup.getFont());

		gl = new GridLayout(7, false);
		detailComposite.setLayout(gl);

		this.connectBtn = new Button(detailComposite, SWT.CHECK);
		this.connectBtn.setText("Connect");
		
		Label sourcePortLabel = new Label(detailComposite, SWT.NONE);
		sourcePortLabel.setText(LABEL_SOURCE_PORT_NAME);

		this.sourcePortLabel = new Label(detailComposite, SWT.BORDER);
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		this.sourcePortLabel.setLayoutData(gd);
		this.sourcePortLabel.setBackground(getColor(COLOR_UNEDITABLE));
		
		Label targetPortLabel = new Label(detailComposite, SWT.NONE);
		targetPortLabel.setText(LABEL_TARGET_PORT_NAME);

		this.targetPortLabel = new Label(detailComposite, SWT.BORDER);
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		this.targetPortLabel.setLayoutData(gd);
		this.targetPortLabel.setBackground(getColor(COLOR_UNEDITABLE));
		
		Label targetCompLabel = new Label(detailComposite, SWT.NONE);
		targetCompLabel.setText(LABEL_TARGET_COMP_NAME);

		this.targetComponentLabel = new Label(detailComposite, SWT.BORDER);
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		this.targetComponentLabel.setLayoutData(gd);
		this.targetComponentLabel.setBackground(getColor(COLOR_UNEDITABLE));
	}

	@Override
	protected Control createButtonBar(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout(4, false);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		composite.setLayout(gl);
		composite.setLayoutData(gd);
		
		Label lblDummy = new Label(composite, SWT.NONE);
		gd = new GridData();
		gd.grabExcessHorizontalSpace = true;
		lblDummy.setLayoutData(gd);
		
		Button btnClose = createButton(composite, APPLY_ID, "OK", false);
		btnClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				notifyModified();
				restoreComponent();
				close();
			}
		});
		Button btnCancel = createButton(composite, CANCEL, "Cancel", true);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				close();
			}
		});
		return composite;
	}

	/** テーブルの表示内容を構築 */
	void buildData() {
		this.tableViewer.setInput(this.componentList);
	}

	/** 詳細項目の変更を通知します */
	void notifyModified() {
		if (this.selectedTarget == null) {
			return;
		}

		this.selectedTarget.setRestore(includeBtn.getSelection());
		this.selectedTarget.setCreate(createBtn.getSelection());
		if(createBtn.getSelection()) {
			this.selectedTarget.setTargetRTC("");
			this.selectedTarget.setSelectedRTC(null);
			this.selectedTarget.setContainerName(this.containerText.getText());
			this.selectedTarget.setNode(this.nodeText.getText());
		} else {
			this.selectedTarget.setTargetRTC(rtcCombo.getText());
			if(rtcCombo.getText() != null && 0 < rtcCombo.getText().length()) {
				for(RTCInfo rtc : rtcList) {
					if(rtc.compId.equals(rtcCombo.getText())) {
						this.selectedTarget.setSelectedRTC(rtc.component);
						break;
					}
				}
			}
			this.selectedTarget.setContainerName("");
			this.selectedTarget.setNode("");
		}
		this.selectedTarget.setActivate(activateBtn.getSelection());
		this.selectedTarget.setConfig(configurationBtn.getSelection());

		this.tableViewer.refresh();
		
		notifyModifiedConnector();
	}

	void notifyModifiedConnector() {
		if (this.selectedConnector == null) {
			return;
		}

		this.selectedConnector.setConnect(connectBtn.getSelection());

		this.connectorTableViewer.refresh();
	}
	/** テーブル上の行選択を詳細項目へ反映します */
	void refreshData() {
		if (this.selectedTarget == null) {
			this.includeBtn.setSelection(false);
			this.createBtn.setSelection(false);
			this.activateBtn.setSelection(false);
			this.configurationBtn.setSelection(false);
			this.configDetailBtn.setSelection(false);
			
			this.componentNameLabel.setText("");
			this.componentIdLabel.setText("");
			this.containerText.setText("");
			this.nodeText.setText("");
		} else {
			this.includeBtn.setSelection(this.selectedTarget.isRestore());
			this.createBtn.setSelection(this.selectedTarget.isCreate());
			this.activateBtn.setSelection(this.selectedTarget.isActivate());
			
			this.configurationBtn.setEnabled(this.selectedTarget.isHasConfig());
			this.configDetailBtn.setEnabled(this.selectedTarget.isHasConfig());
			if(this.selectedTarget.isHasConfig()) {
				this.configurationBtn.setSelection(this.selectedTarget.isConfig());
				this.configText.setText(this.selectedTarget.getProfile().getActiveConfigurationSet());
				this.configDetailBtn.setSelection(this.selectedTarget.isConfig());
			}
			
			if (this.selectedTarget.getTargetRTC() != null) {
				this.rtcCombo.setText(this.selectedTarget.getTargetRTC());
			}
			
			if (this.selectedTarget.getCompName() != null) {
				this.componentNameLabel.setText(this.selectedTarget.getCompName());
			}
			if (this.selectedTarget.getCompId() != null) {
				this.componentIdLabel.setText(this.selectedTarget.getCompId());
			}
			if (this.selectedTarget.getContainerName() != null) {
				this.containerText.setText(this.selectedTarget.getContainerName());
			}
			if (this.selectedTarget.getNode() != null) {
				this.nodeText.setText(this.selectedTarget.getNode());
			}
			
//			this.createBtn.setSelection(true);
			for(String item : this.rtcCombo.getItems()) {
				if(item.equals(this.selectedTarget.getCompRawId())) {
					this.rtcCombo.setText(item);
					this.createBtn.setSelection(false);
					break;
				}
			}
			this.connectorProvider.setBaseComponent(this.selectedTarget.getCompRawId());
			this.connectorTableViewer.setInput(this.selectedTarget.getConnectorList());
		}
	}
	void refreshDataConnector() {
		if (this.selectedConnector == null) {
			this.connectBtn.setSelection(false);
			
			this.sourcePortLabel.setText("");
			this.targetPortLabel.setText("");
			this.targetComponentLabel.setText("");
		} else {
			this.connectBtn.setSelection(this.selectedConnector.isConnect());
			if (this.selectedConnector.getSourcePort() != null) {
				this.sourcePortLabel.setText(this.selectedConnector.getSourcePort());
			}
			if (this.selectedConnector.getTargetPort() != null) {
				this.targetPortLabel.setText(this.selectedConnector.getTargetPort());
			}
			if (this.selectedConnector.getTargetRTC() != null) {
				this.targetComponentLabel.setText(this.selectedConnector.getTargetRTC());
			}
		}
	}
	
	private void setWidgetStatus() {
		createBtn.setEnabled(includeBtn.getSelection());
		rtcLabel.setEnabled(includeBtn.getSelection() && !createBtn.getSelection());
		rtcCombo.setEnabled(includeBtn.getSelection() && !createBtn.getSelection());
		nodeLabel.setEnabled(includeBtn.getSelection() && createBtn.getSelection());
		nodeText.setEnabled(includeBtn.getSelection() && createBtn.getSelection());
		mgrLabel.setEnabled(includeBtn.getSelection() && createBtn.getSelection());
		containerText.setEnabled(includeBtn.getSelection() && createBtn.getSelection());
		activateBtn.setEnabled(includeBtn.getSelection());
		
		configurationBtn.setEnabled(includeBtn.getSelection() && this.selectedTarget.isHasConfig());
		configLabel.setEnabled(includeBtn.getSelection() && this.selectedTarget.isHasConfig() && this.configurationBtn.getSelection());
		configText.setEnabled(includeBtn.getSelection() && this.selectedTarget.isHasConfig() && this.configurationBtn.getSelection());
		configDetailBtn.setEnabled(includeBtn.getSelection() && this.selectedTarget.isHasConfig() && this.configurationBtn.getSelection());
		
		connectorTableViewer.getTable().setEnabled(includeBtn.getSelection());
		connectBtn.setEnabled(includeBtn.getSelection());
	}

	private void restoreComponent() {
		for(ComponentInfo target : componentList) {
			target.restoreComponent(endpoints, diagram);
		}
	}
	//
	public static class RTCInfo {
		private String compName;
		private String compId;
		private String instanceName;

		private CorbaComponent component;
		
		public RTCInfo(CorbaComponent comp) {
			this.component = comp;
			
			this.instanceName = comp.getInstanceNameL();
			this.compId = comp.getComponentId();
			this.compName = comp.getInstanceNameL();
		}
	}

	/**
	 * エンドポイントの検索・キャッシュを表します。
	 */
	public static class EndpointCache {

		/** エンドポイントの NullObject */
		public static Endpoint NULL_ENDPOINT = new Endpoint(null);

		private Map<String, Endpoint> cache = new HashMap<>();

		public Endpoint get(String endpoint) {
			Endpoint ret = this.cache.get(endpoint);
			if (ret != null) {
				return ret;
			}
			RTM.Manager remote = null;
			if (endpoint != null) {
				try {
					org.omg.CORBA.Object managerObj = CORBAHelper.ORBUtil.getOrb()
							.string_to_object("corbaloc::" + endpoint + ":" + CORBAHelper.MANAGER_PORT + "/manager");
					remote = ManagerHelper.narrow(managerObj);
				} catch (RuntimeException e) {
					remote = null;
				}
			}
			if (remote == null) {
				this.cache.put(endpoint, NULL_ENDPOINT);
				return NULL_ENDPOINT;
			}
			RTCManager manager = ManagerFactory.eINSTANCE.createRTCManager();
			manager.setCorbaObject(remote);
			ret = new Endpoint(manager);
			this.cache.put(endpoint, ret);
			return ret;
		}
	}

	/**
	 * エンドポイント(マネージャ)を表します。
	 */
	public static class Endpoint {

		private RTCManager remote;
		private Map<String, RTC.RTObject> objects = null;

		Endpoint(RTCManager remote) {
			this.remote = remote;
		}

		public RTCManager getManager() {
			return this.remote;
		}

		/**
		 * コンポーネント名に対する CORBAオブジェクトを取得します。
		 */
		public RTC.RTObject getComponent(String name) {
			if (this.objects != null) {
				return this.objects.get(name);
			}
			return null;
		}
	}

	private class TargetLabelProvider extends LabelProvider implements
			ITableLabelProvider, ITableColorProvider {

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			ComponentInfo entry = (ComponentInfo) element;
			if (columnIndex == 0) {
				if(entry.isRestore()) {
					return "O";
				} else {
					return "";
				}
			} else if (columnIndex == 1) {
				return entry.getCompName();
			} else if (columnIndex == 2) {
				return entry.getCompId();
			} else if (columnIndex == 3) {
				if(entry.isCreate()) {
					return "O";
				} else {
					return "";
				}
			} else if (columnIndex == 4) {
				if(entry.isCreate() == false) {
					return entry.getTargetRTC();
				} else {
					return "";
				}
			} else if (columnIndex == 5) {
				if(entry.isCreate()) {
					return entry.getNode();
				} else {
					return "";
				}
			} else if (columnIndex == 6) {
				if(entry.isCreate()) {
					return entry.getContainerName();
				} else {
					return "";
				}
			} else if (columnIndex == 7) {
				if(entry.isActivate()) {
					return "O";
				} else {
					return "";
				}
			} else if (columnIndex == 8) {
				if(entry.isConfig()) {
					return "O";
				} else {
					return "";
				}
			}
			return "";
		}

		@Override
		public Color getForeground(Object element, int columnIndex) {
			return null;
		}
		
		@Override
		public Color getBackground(Object element, int columnIndex) {
//			ComponentInfo entry = (ComponentInfo) element;
//			
//			if (columnIndex == 4) {
//				if(entry.isError) {
//					return getColor(COLOR_MODIFY);
//				} else {
//					return getColor(COLOR_WHITE);
//				}
//			}

			return null;
		}
	}
	
	private class ConnectorLabelProvider extends LabelProvider implements
			ITableLabelProvider {
		private String baseComponent;
		
		public void setBaseComponent(String comp) {
			this.baseComponent = comp;
		}

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		
		@Override
		public String getColumnText(Object element, int columnIndex) {
			ConnectorInfo entry = (ConnectorInfo) element;
			if (columnIndex == 0) {
				if(entry.isConnect()) {
					return "O";
				} else {
					return "";
				}
			} else if (columnIndex == 1) {
				if(baseComponent.equals(entry.getSourceRTC())) {
					return entry.getSourcePort();
				} else if(baseComponent.equals(entry.getTargetRTC())) {
					return entry.getTargetPort();
				}
				
			} else if (columnIndex == 2) {
				return entry.getDataType();
				
			} else if (columnIndex == 3) {
				if(baseComponent.equals(entry.getSourceRTC())) {
					return entry.getTargetPort();
				} else if(baseComponent.equals(entry.getTargetRTC())) {
					return entry.getSourcePort();
				}
				
			} else if (columnIndex == 4) {
				if(baseComponent.equals(entry.getSourceRTC())) {
					return entry.getTargetRTC();
				} else if(baseComponent.equals(entry.getTargetRTC())) {
					return entry.getSourceRTC();
				}
			}
			return "";
		}
	}
}

