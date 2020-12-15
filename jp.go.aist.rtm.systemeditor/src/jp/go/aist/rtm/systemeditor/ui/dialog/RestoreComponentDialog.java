package jp.go.aist.rtm.systemeditor.ui.dialog;

import static jp.go.aist.rtm.systemeditor.ui.util.UIUtil.COLOR_MODIFY;
import static jp.go.aist.rtm.systemeditor.ui.util.UIUtil.COLOR_UNEDITABLE;
import static jp.go.aist.rtm.systemeditor.ui.util.UIUtil.COLOR_WHITE;
import static jp.go.aist.rtm.systemeditor.ui.util.UIUtil.getColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import RTM.ManagerHelper;
import jp.go.aist.rtm.systemeditor.corba.CORBAHelper;
import jp.go.aist.rtm.systemeditor.corba.CORBAHelper.CreateComponentParameter;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.manager.ManagerFactory;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;

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
	static final String MSG_STATUS_EP_UNREACHABLE = Messages
			.getString("RestoreComponentDialog.msg_ep_unreach");
	static final String MSG_STATUS_NEED_CREATE = Messages
			.getString("RestoreComponentDialog.msg_need_create");

	private static final String LABEL_COMPONENT_NAME = "Component Name:";
	private static final String LABEL_COMPONENT_ID = "Component Id:";
	private static final String LABEL_NODE = "Node:";
	private static final String LABEL_CONTAINER = "Container:";

	private static final String COL_COMPONENT_NAME = "Component Name";
	private static final String COL_COMPONENT_ID = "Component Id";
	private static final String COL_NODE = "Node";
	private static final String COL_CONTAINER = "Container";
	private static final String COL_STATUS = "Status";

	static final String PROP_IMPLEMENTATION_ID = "implementation_id";
	static final String PROP_INSTANCE_NAME = "instance_name";
	static final String PROP_LANGUAGE = "language";
	static final String PROP_VENDOR = "vendor";
	static final String PROP_CATEGORY = "category";
	static final String PROP_VERSION = "version";
	static final String PROP_CORBA_ENDPOINTS = "corba.endpoints";
	static final String PROP_MANAGER_NAME = "manager.instance_name";

	static final int APPLY_ID = 998;
	
	private TableViewer tableViewer;
	private Table table;

	private Label componentNameLabel;
	private Label componentIdLabel;
	private Text nodeText;
	private Text containerText;

	private SystemDiagram diagram;
	
	private List<TargetInfo> targetList = new ArrayList<>();
	private TargetInfo selectedTarget;

	private EndpointCache endpoints = new EndpointCache();

	public RestoreComponentDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(getShellStyle() | SWT.CENTER | SWT.RESIZE);
	}

	public void setSystemDiagram(SystemDiagram source) {
		this.diagram = source;
	}
	
	/**
	 * マッピング対象となる　CORBAコンポーネントの一覧を設定します。
	 */
	public void setCorbaComponents(List<CorbaComponent> list) {
		if (list == null) {
			return;
		}
		for (CorbaComponent comp : list) {
			TargetInfo target = new TargetInfo(comp);
			this.targetList.add(target);
			String epName = target.node;
			if (epName == null) {
				continue;
			}
			String[] epList = epName.split(",");
			Endpoint ep = null;
			for(String each : epList) {
				ep = this.endpoints.get(each);
				if(ep!=EndpointCache.NULL_ENDPOINT) break;
			}
			RTC.RTObject rtc = ep.getComponent(target.compName);
			if (rtc != null) {
				target.setTarget(rtc);
			}
		}
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite dialogArea = (Composite) super.createDialogArea(parent);

		GridLayout gl;
		GridData gd;

		Composite mainComposite = new Composite(dialogArea, SWT.NONE);
		gl = new GridLayout(1, false);
		gd = new GridData(GridData.FILL_BOTH);
		mainComposite.setLayout(gl);
		mainComposite.setLayoutData(gd);
		mainComposite.setFont(parent.getFont());

		Composite detailComposite = new Composite(dialogArea, SWT.NONE);
		gl = new GridLayout(1, false);
		gd = new GridData(GridData.FILL_BOTH);
		detailComposite.setLayout(gl);
		detailComposite.setLayoutData(gd);
		detailComposite.setFont(parent.getFont());

		{
			Label label = new Label(mainComposite, SWT.NONE);
			label.setText(LABEL_MAPPING_MESSAGE);

			this.tableViewer = new TableViewer(mainComposite,
					SWT.FULL_SELECTION | SWT.SINGLE | SWT.BORDER);
			this.tableViewer.setContentProvider(new ArrayContentProvider());
			this.tableViewer
					.setColumnProperties(new String[] { COL_COMPONENT_NAME,
							COL_COMPONENT_ID, COL_NODE, COL_CONTAINER, COL_STATUS });
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
							selectedTarget = (TargetInfo) selection
									.getFirstElement();
							refreshData();
						}
					});

			this.table = this.tableViewer.getTable();
			gl = new GridLayout(1, false);
			gd = new GridData();
			gd.verticalAlignment = SWT.FILL;
			gd.horizontalAlignment = SWT.FILL;
			gd.grabExcessVerticalSpace = true;
			gd.grabExcessHorizontalSpace = true;
			gd.horizontalSpan = 2;
			gd.heightHint = 250;
			this.table.setLayout(gl);
			this.table.setLayoutData(gd);
			this.table.setLinesVisible(true);
			this.table.setHeaderVisible(true);

			TableColumn col = new TableColumn(this.table, SWT.NONE);
			col.setText(COL_COMPONENT_NAME);
			col.setWidth(150);
			col = new TableColumn(this.table, SWT.NONE);
			col.setText(COL_COMPONENT_ID);
			col.setWidth(300);
			col = new TableColumn(this.table, SWT.NONE);
			col.setText(COL_NODE);
			col.setWidth(100);
			col = new TableColumn(this.table, SWT.NONE);
			col.setText(COL_CONTAINER);
			col.setWidth(120);
			col = new TableColumn(this.table, SWT.NONE);
			col.setText(COL_STATUS);
			col.setWidth(400);
		}
		{
			gl = new GridLayout();
			gl.numColumns = 4;
			detailComposite.setLayout(gl);

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
			Label nodeLabel = new Label(detailComposite, SWT.NONE);
			nodeLabel.setText(LABEL_NODE);

			this.nodeText = new Text(detailComposite, SWT.SINGLE
					| SWT.BORDER);
			gd = new GridData();
			gd.horizontalAlignment = SWT.FILL;
			gd.grabExcessHorizontalSpace = true;
			this.nodeText.setLayoutData(gd);
			this.nodeText.addFocusListener(new FocusListener() {
				@Override
				public void focusLost(FocusEvent e) {
					notifyModified();
				}

				@Override
				public void focusGained(FocusEvent e) {
				}
			});
			/////
			Label mgrLabel = new Label(detailComposite, SWT.NONE);
			mgrLabel.setText(LABEL_CONTAINER);

			this.containerText = new Text(detailComposite, SWT.SINGLE
					| SWT.BORDER);
			gd = new GridData();
			gd.horizontalAlignment = SWT.FILL;
			gd.grabExcessHorizontalSpace = true;
			this.containerText.setLayoutData(gd);
			this.containerText.addFocusListener(new FocusListener() {
				@Override
				public void focusLost(FocusEvent e) {
					LOGGER.trace("Restore: managerName.focusLost: event=<{}>",
							e);
					notifyModified();
				}

				@Override
				public void focusGained(FocusEvent e) {
				}
			});
		}

		buildData();

		return dialogArea;
	}

	@Override
	protected Control createButtonBar(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout(2, false);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		composite.setLayout(gl);
		composite.setLayoutData(gd);
		
		Button btnApply = createButton(composite, APPLY_ID, "Restore", false);
		btnApply.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				refreshData();
				for(TargetInfo target : targetList) {
					if(target.node==null || target.node.length()==0) {
						target.status = MSG_STATUS_EP_UNREACHABLE;
						target.isError = true;
						continue;
					}
					Endpoint ep = null;
					String[] nodes = target.node.split(",");
					if(nodes.length==0) {
						target.status = MSG_STATUS_EP_UNREACHABLE;
						target.isError = true;
						continue;
					}
					for(String node : nodes) {
						ep = endpoints.get(node);
						if(ep!=null) break;
					}
					if(ep==null) {
						target.status = MSG_STATUS_EP_UNREACHABLE;
						target.isError = true;
						continue;
					}
					//
					RTCManager manager = ep.getManager();
					if(manager==null) {
						target.status = String.format("No manager, it can not create component: comp=<%s>", target.compId);
						target.isError = true;
						continue;
					}
					try {
						RTC.RTObject rtobj = null;
						if (target.component.isCompositeComponent()) {
							rtobj = CORBAHelper.factory().createCompositeRTObject(
									manager, target.component, diagram);
						} else {
							rtobj = CORBAHelper.factory().createRTObject(
									manager, target.component, diagram);
						}
						if (rtobj == null) {
							target.status = String.format("Fail to create rtobject: comp=<%s>", target.compId);
							target.isError = true;
							continue;
						}
						target.component.setCorbaObject(rtobj);
					} catch (Exception e1) {
						target.status = String.format("Fail to create rtobject: comp=<%s>", target.compId);
						target.isError = true;
						continue;
					}
					target.status = String.format("Created: comp=<%s>", target.compId);
					target.isError = false;
				}
				tableViewer.refresh();
			}
		});
		
		Label lblDummy = new Label(composite, SWT.NONE);
		gd = new GridData();
		gd.grabExcessHorizontalSpace = true;
		lblDummy.setLayoutData(gd);
		
		Button btnClose = createButton(composite, OK, "OK", false);
		btnClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
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
		this.tableViewer.setInput(this.targetList);
	}

	/** 詳細項目の変更を通知します */
	void notifyModified() {
		if (this.selectedTarget == null) {
			return;
		}

		String managerName = this.containerText.getText();
		String epName = this.nodeText.getText();

		this.selectedTarget.containerName = managerName;
		this.selectedTarget.node = epName;

		this.tableViewer.refresh();
	}

	/** テーブル上の行選択を詳細項目へ反映します */
	void refreshData() {
		if (this.selectedTarget == null) {
			this.componentNameLabel.setText("");
			this.componentIdLabel.setText("");
			this.containerText.setText("");
			this.nodeText.setText("");
		} else {
			if (this.selectedTarget.compName != null) {
				this.componentNameLabel.setText(this.selectedTarget.compName);
			}
			if (this.selectedTarget.compId != null) {
				this.componentIdLabel.setText(this.selectedTarget.compId);
			}
			if (this.selectedTarget.containerName != null) {
				this.containerText.setText(this.selectedTarget.containerName);
			}
			if (this.selectedTarget.node != null) {
				this.nodeText.setText(this.selectedTarget.node);
			}
		}
	}

	//

	/**
	 * リストア対象情報
	 */
	public static class TargetInfo {

		private String compName;
		private String compId;
		private String node;
		private String containerName;
		private String status;
		private boolean isError;

		private CorbaComponent component;

		public TargetInfo(CorbaComponent component) {
			this.component = component;
			this.compName = component.getInstanceNameL();

			String lang = "";
			String vendor = "";
			String category = "";
			String version = "";

			String type = component.getProperty(PROP_IMPLEMENTATION_ID);
			if(type == null) {
				component.setProperty(CreateComponentParameter.KEY_INSTANCE_NAME, this.compName);
				String componentId = component.getComponentId();
				String elems[] = componentId.split(":");
				if(elems.length < 5) return;
				vendor = elems[1];
				category = elems[2];
				type = elems[3];
				component.setProperty(CreateComponentParameter.KEY_IMPLEMENTATION_ID, type);
				version = elems[4];
//				lang = "C++";
				
			} else {
				lang = component.getProperty(PROP_LANGUAGE);
				vendor = component.getProperty(PROP_VENDOR);
				category = component.getProperty(PROP_CATEGORY);
				version = component.getProperty(PROP_VERSION);
			}
			
			this.compId = "RTC:" + vendor + ":" + category + ":" + type + ":" + lang + ":" + version;
			
			String endPoints = component.getProperty(PROP_CORBA_ENDPOINTS);
			if(endPoints != null) {
				String[] epList = endPoints.split(",");
				StringBuilder builder = new StringBuilder();
				for(String each : epList) {
					if(0<builder.length()) {
						builder.append(",");
					}
					builder.append(getHostName(each));
				}
				this.node = builder.toString();
			}
			
			this.containerName = component.getProperty(PROP_MANAGER_NAME);
			//
			if (this.node == null) {
				// エンドポイントのプロパティ設定がない場合は、パスURIから取得(既存互換)
				String path = component.getPathId();
				if (path != null && !path.isEmpty()) {
					path = path.substring(0, path.indexOf("/"));
					this.node = path;
				}
			}
		}

		public void setTarget(RTC.RTObject rtc) {
			if (this.component != null) {
				this.component.setCorbaObject(rtc);
			}
		}

		@Override
		public String toString() {
			return getClass().getSimpleName() + "<" + this.compName + "|"
					+ this.compId + "|" + this.containerName + "|"
					+ this.node + "|"  + this.status + ">";
		}
		
		private String getHostName(String source) {
			String hostName = source;
			if(hostName.contains(":")) {
				String[] elems = source.split(":");
				hostName = elems[0];
			}
			return hostName;
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

	public class TargetLabelProvider extends LabelProvider implements
			ITableLabelProvider, ITableColorProvider {

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			TargetInfo entry = (TargetInfo) element;
			if (columnIndex == 0) {
				return entry.compName;
			} else if (columnIndex == 1) {
				return entry.compId;
			} else if (columnIndex == 2) {
				return entry.node;
			} else if (columnIndex == 3) {
				return entry.containerName;
			} else if (columnIndex == 4) {
				return entry.status;
			}
			return "";
		}

		@Override
		public Color getForeground(Object element, int columnIndex) {
			return null;
		}
		
		@Override
		public Color getBackground(Object element, int columnIndex) {
			TargetInfo entry = (TargetInfo) element;
			
			if (columnIndex == 4) {
				if(entry.isError) {
					return getColor(COLOR_MODIFY);
				} else {
					return getColor(COLOR_WHITE);
				}
			}

			return null;
		}
	}

}
