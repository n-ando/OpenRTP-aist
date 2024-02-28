package jp.go.aist.rtm.systemeditor.ui.views.managercontrolview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.go.aist.rtm.systemeditor.ui.dialog.CreateComponentDialog;
import jp.go.aist.rtm.systemeditor.ui.dialog.ManagerConfigurationDialog;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;
import jp.go.aist.rtm.toolscommon.util.SDOUtil;

/**
 * マネージャ管理ビュー
 */
public class ManagerControlView extends ViewPart {
	private enum DispMode {
		None,
		Managers,
		LoadableModules,
		LoadedModules,
		RTCInstances
	}

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ManagerControlView.class);

	private static final int MENU_BUTTON_WIDTH = 120;
	private static final int EXEC_BUTTON_WIDTH = 110;

	private Composite composite = null;
	private Button loadableModuleButton;
	private Button loadedeModuleButton;
	private Button rtcInstanceButton;
	private Button managersButton;

	private Table modulesTable;
	private TableViewer modulesTableViewer;
	private TableColumn moduleColumn1;
	private TableColumn moduleColumn2;

	private Button createButton;
	private Button configureButton;
	private Button restartButton;
	private Button shutdownButton;

	private DispMode mode;

	private RTCManager targetManager;
	private List<Profile> profileList;
	private List<RTCManager> managerList;

	public ManagerControlView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		GridLayout gl;
		GridData gd;

		gl = new GridLayout();
		gl.numColumns = 3;
		this.composite = new Composite(parent, SWT.FILL);
		this.composite.setLayout(gl);

		final Composite menuButtonComposite = new Composite(this.composite,
				SWT.NONE);
		gl = new GridLayout();
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.verticalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		menuButtonComposite.setLayout(gl);
		menuButtonComposite.setLayoutData(gd);
		/////
		this.managersButton = new Button(menuButtonComposite, SWT.TOP);
		this.managersButton.setText("Managers");
		gd = new GridData();
		gd.widthHint = MENU_BUTTON_WIDTH;
		this.managersButton.setLayoutData(gd);
		this.managersButton.setEnabled(false);
		this.managersButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mode = DispMode.None;
				if (targetManager != null) {
					moduleColumn1.setText("manager name");
					moduleColumn2.setText("manager language");
					mode = DispMode.Managers;
				}
				refreshModuleListData();
			}
		});
		//
		this.loadableModuleButton = new Button(menuButtonComposite, SWT.TOP);
		this.loadableModuleButton.setText("Loadable Mod.");
		gd = new GridData();
		gd.widthHint = MENU_BUTTON_WIDTH;
		this.loadableModuleButton.setLayoutData(gd);
		this.loadableModuleButton.setEnabled(false);
		this.loadableModuleButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (targetManager != null) {
					moduleColumn1.setText("module");
					moduleColumn2.setText("");
					mode = DispMode.LoadableModules;
				}
				refreshModuleListData();
			}
		});
		//
		this.loadedeModuleButton = new Button(menuButtonComposite, SWT.TOP);
		this.loadedeModuleButton.setText("Loaded Mod.");
		gd = new GridData();
		gd.widthHint = MENU_BUTTON_WIDTH;
		this.loadedeModuleButton.setLayoutData(gd);
		this.loadedeModuleButton.setEnabled(false);
		this.loadedeModuleButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (targetManager != null) {
					moduleColumn1.setText("module");
					moduleColumn2.setText("manager");
					mode = DispMode.LoadedModules;
				}
				refreshModuleListData();
			}
		});
		//
		this.rtcInstanceButton = new Button(menuButtonComposite, SWT.TOP);
		this.rtcInstanceButton.setText("RTC Instances");
		gd = new GridData();
		gd.widthHint = MENU_BUTTON_WIDTH;
		this.rtcInstanceButton.setLayoutData(gd);
		this.rtcInstanceButton.setEnabled(false);
		this.rtcInstanceButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (targetManager != null) {
					moduleColumn1.setText("component");
					moduleColumn2.setText("manager name");
					mode = DispMode.RTCInstances;
				}
				refreshModuleListData();
			}
		});

		final Composite listComposite = new Composite(this.composite, SWT.FILL);
		gl = new GridLayout();
		gl.marginWidth = 0;
		gl.marginHeight = 0;
		gl.numColumns = 2;
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.verticalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.grabExcessVerticalSpace = true;
		listComposite.setLayout(gl);
		listComposite.setLayoutData(gd);

		this.modulesTableViewer = new TableViewer(listComposite,
				SWT.FULL_SELECTION | SWT.SINGLE | SWT.BORDER);
		this.modulesTableViewer.setContentProvider(new ArrayContentProvider());

		this.modulesTable = this.modulesTableViewer.getTable();
		gl = new GridLayout(1, false);
		gl.numColumns = 1;
		gd = new GridData();
		gd.verticalAlignment = SWT.FILL;
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 2;
		this.modulesTable.setLayout(gl);
		this.modulesTable.setLayoutData(gd);
		this.modulesTable.setLinesVisible(true);
		this.modulesTable.setHeaderVisible(true);
		this.modulesTable.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				updateEnableButton();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				updateEnableButton();
			}
		});

		this.moduleColumn1 = new TableColumn(this.modulesTable, SWT.NONE);
		this.moduleColumn1.setText("module");
		this.moduleColumn1.setWidth(300);
		this.moduleColumn2 = new TableColumn(this.modulesTable, SWT.NONE);
		this.moduleColumn2.setText("");
		this.moduleColumn2.setWidth(300);

		this.modulesTableViewer.setLabelProvider(new ProfileLabelProvider());

		final Composite execButtonComposite = new Composite(this.composite,
				SWT.NONE);
		gl = new GridLayout();
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.verticalAlignment = SWT.FILL;
		execButtonComposite.setLayout(gl);
		execButtonComposite.setLayoutData(gd);

		this.createButton = new Button(execButtonComposite, SWT.TOP);
		this.createButton.setText("Create");
		gd = new GridData();
		gd.widthHint = EXEC_BUTTON_WIDTH;
		this.createButton.setLayoutData(gd);
		this.createButton.setEnabled(false);
		this.createButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (targetManager == null) {
					return;
				}
				CreateComponentDialog dialog = new CreateComponentDialog(
						getSite().getShell());
				dialog.setModuleProfileList(targetManager
						.getLoadableModuleProfilesR());
				//
				buildManagerData();
				List<String> managerList = new ArrayList<String>();
				for (Profile manager : profileList) {
					managerList.add(manager.getManager_name());
				}
				dialog.setManagerNameList(managerList);
				int selectedIndex = modulesTable.getSelectionIndex();
				if (selectedIndex != -1 && mode == DispMode.Managers) {
					String initManager = profileList.get(selectedIndex).getManager_name();
					dialog.setInitManager(initManager);
				}
				if (dialog.open() == IDialogConstants.OK_ID) {
					String cmd = dialog.getParameter();
					LOGGER.info("create command: <{}>", cmd);
					Component result = targetManager.createComponentR(cmd);
					if(result==null) {
						MessageDialog.openError(PlatformUI.getWorkbench()
								.getDisplay().getActiveShell(), "Error",
								"FAILED to create of target RTC.");
					}
				}
			}
		});

		this.configureButton = new Button(execButtonComposite, SWT.TOP);
		this.configureButton.setText("Configure");
		gd = new GridData();
		gd.widthHint = EXEC_BUTTON_WIDTH;
		this.configureButton.setLayoutData(gd);
		this.configureButton.setEnabled(false);
		this.configureButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				RTCManager target = targetManager;
				int selectedIndex = modulesTable.getSelectionIndex();
				if(selectedIndex < 0 || selectedIndex < managerList.size()) {
					target = managerList.get(selectedIndex);
				}
				if (target == null) {
					return;
				}
				ManagerConfigurationDialog dialog = new ManagerConfigurationDialog(
						getSite().getShell());
				dialog.setManager(target);
				dialog.open();
			}
		});
		//
		Label dummy01 = new Label(execButtonComposite, SWT.NONE);
		gd = new GridData();
		gd.grabExcessVerticalSpace = true;
		dummy01.setLayoutData(gd);
		//
		this.restartButton = new Button(execButtonComposite, SWT.TOP);
		this.restartButton.setText("Restart");
		gd = new GridData();
		gd.widthHint = EXEC_BUTTON_WIDTH;
		this.restartButton.setLayoutData(gd);
		this.restartButton.setEnabled(false);
		this.restartButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				RTCManager target = targetManager;
				int selectedIndex = modulesTable.getSelectionIndex();
				if(selectedIndex < 0 || selectedIndex < managerList.size()) {
					target = managerList.get(selectedIndex);
				}
				if (target == null) {
					return;
				}
				target.restartR();
				buildData();
			}
		});

		this.shutdownButton = new Button(execButtonComposite, SWT.TOP);
		this.shutdownButton.setText("Shutdown");
		gd = new GridData();
		gd.widthHint = EXEC_BUTTON_WIDTH;
		this.shutdownButton.setLayoutData(gd);
		this.shutdownButton.setEnabled(false);
		this.shutdownButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				RTCManager target = targetManager;
				int selectedIndex = modulesTable.getSelectionIndex();
				if(selectedIndex < 0 || selectedIndex < managerList.size()) {
					target = managerList.get(selectedIndex);
				}
				if (target == null) {
					return;
				}

				String managerName = SDOUtil.findValueAsString("instance_name", target.getProfileR().properties);
				target.shutdownR();

				if(target==targetManager) {
					targetManager = null;
				} else {
					try {
						Thread.sleep(500);
						for(int index=0; index<5; index++) {
							boolean isHit = false;
							for (RTCManager manager : targetManager.getSlaveManagersR()) {
								try {
									String mngName = SDOUtil.findValueAsString("instance_name", manager.getProfileR().properties);
									if(mngName.equals(managerName)) {
										isHit = true;
										break;
									}
								} catch (Exception ex) {
								}
							}
							if(isHit) {
								Thread.sleep(200);
							} else {
								break;
							}
						}
					} catch (Exception ex) {
					}
				}
				mode = DispMode.Managers;
				refreshData();
			}
		});

		setSiteSelection();
	}

	@Override
	public void setFocus() {
	}

	private void buildData() {
		this.mode = DispMode.None;
		refreshData();
	}

	private void refreshData() {
		this.loadableModuleButton.setEnabled(false);
		this.loadedeModuleButton.setEnabled(false);
		this.rtcInstanceButton.setEnabled(false);
		this.managersButton.setEnabled(false);
		this.createButton.setEnabled(false);
		this.configureButton.setEnabled(false);
		this.restartButton.setEnabled(false);
		this.shutdownButton.setEnabled(false);

		if (this.targetManager != null) {
			this.loadableModuleButton.setEnabled(true);
			this.loadedeModuleButton.setEnabled(true);
			this.rtcInstanceButton.setEnabled(true);
			this.managersButton.setEnabled(true);
			this.createButton.setEnabled(true);
			this.configureButton.setEnabled(true);
			this.restartButton.setEnabled(true);
			this.shutdownButton.setEnabled(true);
		}
		refreshModuleListData();
	}

	private void refreshModuleListData() {
		this.modulesTableViewer.setInput(Collections.EMPTY_LIST);
		if (this.profileList == null) {
			this.profileList = new ArrayList<Profile>();
		}
		this.profileList.clear();
		if (this.targetManager != null) {
			if (this.mode==DispMode.LoadableModules) {
				for (RTM.ModuleProfile module : this.targetManager
						.getLoadableModuleProfilesR()) {
					this.profileList.add(new Profile(module, false));
				}
				this.modulesTableViewer.setInput(this.profileList);

			} else if (this.mode==DispMode.LoadedModules) {
				for (RTM.ModuleProfile module : this.targetManager
						.getLoadedModuleProfilesR()) {
					this.profileList.add(new Profile(module, true));
				}
				this.modulesTableViewer.setInput(this.profileList);

			} else if (this.mode == DispMode.RTCInstances) {
				for (RTC.ComponentProfile component : this.targetManager
						.getComponentProfilesR()) {
					this.profileList.add(new Profile(component));
				}
				this.modulesTableViewer.setInput(this.profileList);

			} else if (this.mode == DispMode.Managers) {
				buildManagerData();
			}
		}
		updateEnableButton();
	}

	private void buildManagerData() {
		if(managerList == null) {
			managerList = new ArrayList<RTCManager>();
		}
		managerList.clear();
		//
		if (this.profileList == null) {
			this.profileList = new ArrayList<Profile>();
		}
		this.profileList.clear();

		this.profileList.add(new Profile(this.targetManager.getProfileR()));
		managerList.add(targetManager);
		for (RTCManager manager : this.targetManager
				.getSlaveManagersR()) {
			try {
				this.profileList.add(new Profile(manager.getProfileR()));
				managerList.add(manager);
			} catch (Exception ex) {
			}
		}
		this.modulesTableViewer.setInput(this.profileList);
	}

	private void updateEnableButton() {
		this.configureButton.setEnabled(false);
		this.restartButton.setEnabled(false);
		this.shutdownButton.setEnabled(false);
		if (this.modulesTable.getSelectionIndex() != -1) {
			if (this.mode==DispMode.Managers) {
				this.configureButton.setEnabled(true);
				this.restartButton.setEnabled(true);
				this.shutdownButton.setEnabled(true);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class adapter) {
		if (adapter.equals(IPropertySheetPage.class)) {
			return new PropertySheetPage();
		}
		return super.getAdapter(adapter);
	}

	/** 各種プロファイルを格納するラッパ */
	public static class Profile implements IAdaptable {

		private RTM.ModuleProfile module = null;
		private RTC.ComponentProfile component = null;
		private RTM.ManagerProfile manager = null;
		private boolean isLoaded = false;

		Profile(RTM.ModuleProfile module, boolean value) {
			this.module = module;
			this.isLoaded = value;
		}

		Profile(RTC.ComponentProfile component) {
			this.component = component;
		}

		Profile(RTM.ManagerProfile manager) {
			this.manager = manager;
		}

		public boolean hasModuleProfile() {
			return this.module != null;
		}

		public boolean hasComponentProfile() {
			return this.component != null;
		}

		public boolean hasManagerProfile() {
			return this.manager != null;
		}

		public boolean isLoaded() {
			return isLoaded;
		}

		public RTM.ModuleProfile getModuleProfile() {
			return this.module;
		}

		public RTC.ComponentProfile getComponentProfile() {
			return this.component;
		}

		public RTM.ManagerProfile getManagerProfile() {
			return this.manager;
		}

		/** モジュールプロファイル: ファイルパスを取得します */
		public String getModule_file_path() {
			return SDOUtil.findValueAsString("module_file_path",
					this.module.properties);
		}
		public String getModule_type_name() {
			return SDOUtil.findValueAsString("type_name",
					this.module.properties);
		}
		public String getModule_manager_name() {
			return SDOUtil.findValueAsString("manager.instance_name",
					this.module.properties);
		}

		/** コンポーネントプロファイル: インスタンス名を取得します */
		public String getComponent_instance_name() {
			return this.component.instance_name;
		}

		/** コンポーネントプロファイル: マネージャ名を取得します */
		public String getComponent_manager_name() {
			return SDOUtil.findValueAsString("manager.instance_name",
					this.component.properties);
		}

		/** マネージャプロファイル: マネージャ名を取得します */
		public String getManager_name() {
			return SDOUtil.findValueAsString("instance_name",
					this.manager.properties);
		}

		/** マネージャプロファイル: 言語を取得します */
		public String getManager_language() {
			return SDOUtil.findValueAsString("language",
					this.manager.properties);
		}

		@SuppressWarnings("rawtypes")
		@Override
		public Object getAdapter(Class adapter) {
			if (adapter.equals(IPropertySource.class)) {
				return new ProfilePropertySource(this);
			}
			return null;
		}

	}

	/** 各種プロファイルのプロパティ表示のためのPropertySource */
	public static class ProfilePropertySource implements IPropertySource {

		private Profile profile;

		ProfilePropertySource(Profile profile) {
			this.profile = profile;
		}

		@Override
		public Object getEditableValue() {
			return null;
		}

		@Override
		public IPropertyDescriptor[] getPropertyDescriptors() {
			if (this.profile == null) {
				return new IPropertyDescriptor[0];
			}
			_SDOPackage.NameValue[] props = new _SDOPackage.NameValue[0];
			if (this.profile.hasModuleProfile()) {
				props = this.profile.getModuleProfile().properties;
			} else if (this.profile.hasComponentProfile()) {
				props = this.profile.getComponentProfile().properties;
			} else if (this.profile.hasManagerProfile()) {
				props = this.profile.getManagerProfile().properties;
			}
			List<IPropertyDescriptor> descs = new ArrayList<>();
			for (_SDOPackage.NameValue nv : props) {
				descs.add(new TextPropertyDescriptor(nv.name, nv.name));
			}
			return descs.toArray(new IPropertyDescriptor[0]);
		}

		@Override
		public Object getPropertyValue(Object id) {
			_SDOPackage.NameValue[] props = new _SDOPackage.NameValue[0];
			if (this.profile.hasModuleProfile()) {
				props = this.profile.getModuleProfile().properties;
			} else if (this.profile.hasComponentProfile()) {
				props = this.profile.getComponentProfile().properties;
			} else if (this.profile.hasManagerProfile()) {
				props = this.profile.getManagerProfile().properties;
			}
			return SDOUtil.findValueAsString((String) id, props);
		}

		@Override
		public boolean isPropertySet(Object id) {
			return false;
		}

		@Override
		public void resetPropertyValue(Object id) {
		}

		@Override
		public void setPropertyValue(Object id, Object value) {
		}

	}

	/** 各種プロファイル一覧表示のLabelProvider */
	public class ProfileLabelProvider extends LabelProvider implements
			ITableLabelProvider, ITableColorProvider {

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			Profile profile = (Profile) element;
			if (profile.hasModuleProfile()) {
				if(profile.isLoaded()) {
					if (columnIndex == 0) {
						return profile.getModule_type_name();
					} else if (columnIndex == 1) {
						return profile.getModule_manager_name();
					} else {
						return "";
					}
				} else {
					if (columnIndex == 0) {
						return profile.getModule_file_path();
					} else {
						return "";
					}
				}
			} else if (profile.hasComponentProfile()) {
				if (columnIndex == 0) {
					return profile.getComponent_instance_name();
				} else if (columnIndex == 1) {
					return profile.getComponent_manager_name();
				} else {
					return "";
				}
			} else if (profile.hasManagerProfile()) {
				if (columnIndex == 0) {
					return profile.getManager_name();
				} else if (columnIndex == 1) {
					return profile.getManager_language();
				} else {
					return "";
				}
			} else {
				return "";
			}
		}

		@Override
		public Color getBackground(Object element, int columnIndex) {
			return null;
		}

		@Override
		public Color getForeground(Object element, int columnIndex) {
			return null;
		}

	}

	private ISelectionListener selectionListener = new ISelectionListener() {
		@Override
		public void selectionChanged(IWorkbenchPart part, ISelection selection) {
			if (selection instanceof IStructuredSelection) {
				IStructuredSelection ss = (IStructuredSelection) selection;
				Object firstElement = ss.getFirstElement();
				Object adapter = AdapterUtil.getAdapter(firstElement,
						RTCManager.class);
				if (adapter != null) {
					targetManager = (RTCManager) adapter;
					targetManager.synchronizeManually();
					buildData();
				}
			}
		}
	};

	private void setSiteSelection() {
		if (getSite() == null) {
			return;
		}

		this.selectionListener.selectionChanged(null, getSite()
				.getWorkbenchWindow().getSelectionService().getSelection());

		// NameServiceViewの選択監視リスナを登録
		getSite().getWorkbenchWindow().getSelectionService()
				.addSelectionListener(this.selectionListener);

		// SelectionProviderを登録(プロパティ・ビュー連携)
		getSite().setSelectionProvider(this.modulesTableViewer);
	}

}
