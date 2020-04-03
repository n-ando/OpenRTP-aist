/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.manager.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import RTC.ComponentProfile;
import RTC.RTObject;
import RTM.ManagerHelper;
import RTM.ManagerProfile;
import RTM.ModuleProfile;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl;
import jp.go.aist.rtm.toolscommon.model.core.CorePackage;
import jp.go.aist.rtm.toolscommon.model.core.impl.CorbaWrapperObjectImpl;
import jp.go.aist.rtm.toolscommon.model.manager.ManagerFactory;
import jp.go.aist.rtm.toolscommon.model.manager.ManagerPackage;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;
import jp.go.aist.rtm.toolscommon.synchronizationframework.LocalObject;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationManager;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.AttributeMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ClassMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ConstructorParamMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.MappingRule;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ReferenceMapping;
import jp.go.aist.rtm.toolscommon.util.SDOUtil;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>RTC Manager</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.manager.impl.RTCManagerImpl#getManagerProfile <em>Manager Profile</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.manager.impl.RTCManagerImpl#getInstanceNameL <em>Instance Name L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.manager.impl.RTCManagerImpl#getPathId <em>Path Id</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.manager.impl.RTCManagerImpl#getComponentProfiles <em>Component Profiles</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.manager.impl.RTCManagerImpl#getLoadableModuleProfiles <em>Loadable Module Profiles</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.manager.impl.RTCManagerImpl#getLoadedModuleProfiles <em>Loaded Module Profiles</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.manager.impl.RTCManagerImpl#getFactoryModuleProfiles <em>Factory Module Profiles</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.manager.impl.RTCManagerImpl#isMaster <em>Master</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.manager.impl.RTCManagerImpl#getMasterManagers <em>Master Managers</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.manager.impl.RTCManagerImpl#getSlaveManagers <em>Slave Managers</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.manager.impl.RTCManagerImpl#getConfiguratoins <em>Configuratoins</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RTCManagerImpl extends CorbaWrapperObjectImpl implements
		RTCManager {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RTCManagerImpl.class);

	/**
	 * The default value of the '{@link #getManagerProfile() <em>Manager Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getManagerProfile()
	 * @generated
	 * @ordered
	 */
	protected static final ManagerProfile MANAGER_PROFILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getManagerProfile() <em>Manager Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getManagerProfile()
	 * @generated
	 * @ordered
	 */
	protected ManagerProfile managerProfile = MANAGER_PROFILE_EDEFAULT;

	/**
	 * The default value of the '{@link #getInstanceNameL() <em>Instance Name L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstanceNameL()
	 * @generated
	 * @ordered
	 */
	protected static final String INSTANCE_NAME_L_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInstanceNameL() <em>Instance Name L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstanceNameL()
	 * @generated
	 * @ordered
	 */
	protected String instanceNameL = INSTANCE_NAME_L_EDEFAULT;

	/**
	 * The default value of the '{@link #getPathId() <em>Path Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPathId()
	 * @generated
	 * @ordered
	 */
	protected static final String PATH_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPathId() <em>Path Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPathId()
	 * @generated
	 * @ordered
	 */
	protected String pathId = PATH_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getComponentProfiles() <em>Component Profiles</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentProfiles()
	 * @generated
	 * @ordered
	 */
	protected EList<ComponentProfile> componentProfiles;

	/**
	 * The cached value of the '{@link #getLoadableModuleProfiles() <em>Loadable Module Profiles</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoadableModuleProfiles()
	 * @generated
	 * @ordered
	 */
	protected EList<ModuleProfile> loadableModuleProfiles;

	/**
	 * The cached value of the '{@link #getLoadedModuleProfiles() <em>Loaded Module Profiles</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoadedModuleProfiles()
	 * @generated
	 * @ordered
	 */
	protected EList<ModuleProfile> loadedModuleProfiles;

	/**
	 * The cached value of the '{@link #getFactoryModuleProfiles() <em>Factory Module Profiles</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFactoryModuleProfiles()
	 * @generated
	 * @ordered
	 */
	protected EList<ModuleProfile> factoryModuleProfiles;

	/**
	 * The default value of the '{@link #isMaster() <em>Master</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMaster()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MASTER_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMaster() <em>Master</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMaster()
	 * @generated
	 * @ordered
	 */
	protected boolean master = MASTER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMasterManagers() <em>Master Managers</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMasterManagers()
	 * @generated
	 * @ordered
	 */
	protected EList<RTCManager> masterManagers;

	/**
	 * The cached value of the '{@link #getSlaveManagers() <em>Slave Managers</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSlaveManagers()
	 * @generated
	 * @ordered
	 */
	protected EList<RTCManager> slaveManagers;

	/**
	 * The cached value of the '{@link #getConfiguratoins() <em>Configuratoins</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfiguratoins()
	 * @generated
	 * @ordered
	 */
	protected EList<NameValue> configuratoins;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public RTCManagerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ManagerPackage.Literals.RTC_MANAGER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ManagerProfile getManagerProfile() {
		return managerProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setManagerProfile(ManagerProfile newManagerProfile) {
		ManagerProfile oldManagerProfile = managerProfile;
		managerProfile = newManagerProfile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ManagerPackage.RTC_MANAGER__MANAGER_PROFILE, oldManagerProfile, managerProfile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getInstanceNameL() {
		String name = null;
		if (this.managerProfile != null) {
			name = SDOUtil.findValueAsString("instance_name",
					this.managerProfile.properties);
		}
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPathId() {
		return pathId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPathId(String newPathId) {
		String oldPathId = pathId;
		pathId = newPathId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ManagerPackage.RTC_MANAGER__PATH_ID, oldPathId, pathId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<ComponentProfile> getComponentProfiles() {
		if (componentProfiles == null) {
			getComponentProfilesR();
		}
		return componentProfiles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<ModuleProfile> getLoadableModuleProfiles() {
		getLoadableModuleProfilesR();
		return loadableModuleProfiles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<ModuleProfile> getLoadedModuleProfiles() {
		if (loadedModuleProfiles == null) {
			getLoadedModuleProfilesR();
		}
		return loadedModuleProfiles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<ModuleProfile> getFactoryModuleProfiles() {
		if (factoryModuleProfiles == null) {
			getFactoryModuleProfilesR();
		}
		return factoryModuleProfiles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMaster() {
		return master;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaster(boolean newMaster) {
		boolean oldMaster = master;
		master = newMaster;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ManagerPackage.RTC_MANAGER__MASTER, oldMaster, master));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<RTCManager> getMasterManagers() {
		if (masterManagers == null) {
			getMasterManagersR();
		}
		return masterManagers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<RTCManager> getSlaveManagers() {
		if (slaveManagers == null) {
			getSlaveManagersR();
		}
		return slaveManagers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<NameValue> getConfiguratoins() {
		if (configuratoins == null) {
			getConfigurationR();
		}
		return configuratoins;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Component createComponentR(String compName) {
		RTC.RTObject remote = this.getCorbaObjectInterface().create_component(
				compName);
		SynchronizationManager manager = new SynchronizationManager(
				new MappingRule[] { CorbaComponentImpl.MAPPING_RULE });
		LocalObject local = manager.createLocalObject(new Object[] { remote });
		if (local == null || !(local instanceof Component)) {
			return null;
		}
		// キャッシュ更新
		getComponentProfilesR();
		// プロパティ更新通知
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ManagerPackage.RTM_MODULE_PROFILE, false, true));
		return (Component) local;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int deleteComponentR(String instanceName) {
		RTC.ReturnCode_t rc = this.getCorbaObjectInterface().delete_component(
				instanceName);
		// キャッシュ更新
		getComponentProfilesR();
		// プロパティ更新通知
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ManagerPackage.RTM_MODULE_PROFILE, false, true));
		return rc.value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<RTObject> getComponentsR() {
		BasicEList<RTObject> result = new BasicEList<RTObject>();
		RTObject[] rtobjs = this.getCorbaObjectInterface().get_components();
		for (int i = 0; i < rtobjs.length; i++) {
			result.add(rtobjs[i]);
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<RTC.ComponentProfile> getComponentProfilesR() {
		if (componentProfiles == null) {
			componentProfiles = new EDataTypeUniqueEList<ComponentProfile>(
					ComponentProfile.class, this,
					ManagerPackage.RTC_MANAGER__COMPONENT_PROFILES);
		} else {
			componentProfiles.clear();
		}
		RTC.ComponentProfile[] profs = this.getCorbaObjectInterface()
				.get_component_profiles();
		for (int i = 0; i < profs.length; i++) {
			componentProfiles.add(profs[i]);
		}
		return componentProfiles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int loadModuleR(String pathname, String initfunc) {
		RTC.ReturnCode_t rc = this.getCorbaObjectInterface().load_module(
				pathname, initfunc);
		// キャッシュ更新
		getLoadableModuleProfilesR();
		getLoadedModuleProfilesR();
		// プロパティ更新通知
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ManagerPackage.RTM_MODULE_PROFILE, false, true));
		return rc.value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int unloadModuleR(String pathname) {
		RTC.ReturnCode_t rc = this.getCorbaObjectInterface().unload_module(
				pathname);
		// キャッシュ更新
		getLoadableModuleProfilesR();
		getLoadedModuleProfilesR();
		// プロパティ更新通知
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ManagerPackage.RTM_MODULE_PROFILE, false, true));
		return rc.value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<ModuleProfile> getLoadableModuleProfilesR() {
		if (loadableModuleProfiles == null) {
			loadableModuleProfiles = new EDataTypeUniqueEList<ModuleProfile>(
					ModuleProfile.class, this,
					ManagerPackage.RTC_MANAGER__LOADED_MODULE_PROFILES);
		} else {
			loadableModuleProfiles.clear();
		}
		ModuleProfile[] profs = this.getCorbaObjectInterface()
				.get_loadable_modules();
		for (int i = 0; i < profs.length; i++) {
			loadableModuleProfiles.add(profs[i]);
		}
		return loadableModuleProfiles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<ModuleProfile> getLoadedModuleProfilesR() {
		if (loadedModuleProfiles == null) {
			loadedModuleProfiles = new EDataTypeUniqueEList<ModuleProfile>(
					ModuleProfile.class, this,
					ManagerPackage.RTC_MANAGER__LOADED_MODULE_PROFILES);
		} else {
			loadedModuleProfiles.clear();
		}

		RTM.Manager[] managers = this.getCorbaObjectInterface().get_slave_managers();
		for(RTM.Manager targetMng : managers) {
			String managerName = SDOUtil.findValueAsString("instance_name", targetMng.get_profile().properties);
			ModuleProfile[] profs = targetMng.get_loaded_modules();
			ModuleProfile[] loadableProfs = this.getCorbaObjectInterface().get_loadable_modules();
			for (int i = 0; i < profs.length; i++) {
				String file_path = SDOUtil.findValueAsString("file_path", profs[i].properties);
				ModuleProfile targetProf = null;
				for(ModuleProfile lprop : loadableProfs) {
					String path = SDOUtil.findValueAsString("module_file_path", lprop.properties);
					if(file_path.equals(path)) {
						targetProf = lprop;
						break;
					}
				}
				if(targetProf==null) continue;
				//
				List<NameValue> propList = SDOUtil.createNameValueList(targetProf.properties);
				propList.add(SDOUtil.createNameValue("manager.instance_name", managerName));
				targetProf.properties = SDOUtil.createNameValueArray(propList);
				loadedModuleProfiles.add(targetProf);
			}
		}
		return loadedModuleProfiles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<ModuleProfile> getFactoryModuleProfilesR() {
		if (factoryModuleProfiles == null) {
			factoryModuleProfiles = new EDataTypeUniqueEList<ModuleProfile>(
					ModuleProfile.class, this,
					ManagerPackage.RTC_MANAGER__FACTORY_MODULE_PROFILES);
		} else {
			factoryModuleProfiles.clear();
		}
		ModuleProfile[] profs = this.getCorbaObjectInterface()
				.get_factory_profiles();
		for (int i = 0; i < profs.length; i++) {
			this.factoryModuleProfiles.add(profs[i]);
		}
		// ミドルウェアでファクトリ取得が修正されるまで、loadable moduleから取得する
		// for (ModuleProfile prof : getLoadableModuleProfiles()) {
		// String file = SDOUtil.findValueAsString("implementation_id",
		// prof.properties);
		// if (file != null) {
		// factoryModuleProfiles.add(prof);
		// }
		// }
		return this.factoryModuleProfiles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int forkR() {
		RTC.ReturnCode_t rc = this.getCorbaObjectInterface().fork();
		return rc.value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int shutdownR() {
		RTC.ReturnCode_t rc = this.getCorbaObjectInterface().shutdown();
		return rc.value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int restartR() {
		RTC.ReturnCode_t rc = this.getCorbaObjectInterface().restart();
		return rc.value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<String> getComponentInstanceNamesR() {
		// キャッシュ更新
		getComponentProfilesR();
		return getComponentInstanceNames();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<String> getLoadableModuleFileNamesR() {
		// キャッシュ更新
		getLoadableModuleProfilesR();
		return getLoadableModuleFileNames();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<String> getLoadedModuleFileNamesR() {
		// キャッシュ更新
		getLoadedModuleProfilesR();
		return getLoadedModuleFileNames();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<String> getFactoryProfileTypeNamesR() {
		// キャッシュ更新
		getFactoryModuleProfilesR();
		return getFactoryTypeNames();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<String> getComponentInstanceNames() {
		BasicEList<String> result = new BasicEList<String>();
		for (RTC.ComponentProfile prof : getComponentProfiles()) {
			result.add(prof.instance_name);
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<String> getLoadableModuleFileNames() {
		BasicEList<String> result = new BasicEList<String>();
		for (ModuleProfile prof : getLoadableModuleProfiles()) {
			String file = SDOUtil.findValueAsString("module_file_path",
					prof.properties);
			if (file != null) {
				result.add(file);
			}
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<String> getLoadedModuleFileNames() {
		BasicEList<String> result = new BasicEList<String>();
		for (ModuleProfile prof : getLoadedModuleProfiles()) {
			String file = SDOUtil.findValueAsString("file_path",
					prof.properties);
			if (file != null) {
				result.add(file);
			}
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<String> getFactoryTypeNames() {
		BasicEList<String> result = new BasicEList<String>();
		for (ModuleProfile prof : getFactoryModuleProfiles()) {
			String type = SDOUtil.findValueAsString("implementation_id",
					prof.properties);
			if (type != null) {
				result.add(type);
			}
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean isMasterR() {
		boolean ret = this.getCorbaObjectInterface().is_master();
		return ret;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<RTCManager> getMasterManagersR() {
		if (masterManagers == null) {
			masterManagers = new EDataTypeUniqueEList<RTCManager>(
					RTCManager.class, this,
					ManagerPackage.RTC_MANAGER__MASTER_MANAGERS);
		} else {
			masterManagers.clear();
		}
		for (RTM.Manager m : this.getCorbaObjectInterface()
				.get_master_managers()) {
			RTCManager mgr = ManagerFactory.eINSTANCE.createRTCManager();
			mgr.setCorbaObject(m);
			this.masterManagers.add(mgr);
		}
		return this.masterManagers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int addMasterManagerR(RTCManager mgr) {
		RTC.ReturnCode_t ret = RTC.ReturnCode_t.RTC_ERROR;
		if (mgr == null) {
			return ret.value();
		}
		RTM.Manager m = ManagerHelper.narrow(mgr.getCorbaObject());
		ret = this.getCorbaObjectInterface().add_master_manager(m);
		// Master Managerリストを同期
		this.getMasterManagersR();
		return ret.value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int removeMasterManagerR(RTCManager mgr) {
		RTC.ReturnCode_t ret = RTC.ReturnCode_t.RTC_ERROR;
		if (mgr == null) {
			return ret.value();
		}
		RTM.Manager m = ManagerHelper.narrow(mgr.getCorbaObject());
		ret = this.getCorbaObjectInterface().remove_master_manager(m);
		// Master Managerリストを同期
		this.getMasterManagersR();
		return ret.value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<RTCManager> getSlaveManagersR() {
		if (slaveManagers == null) {
			slaveManagers = new EDataTypeUniqueEList<RTCManager>(
					RTCManager.class, this,
					ManagerPackage.RTC_MANAGER__SLAVE_MANAGERS);
		} else {
			slaveManagers.clear();
		}
		for (RTM.Manager m : this.getCorbaObjectInterface()
				.get_slave_managers()) {
			RTCManager mgr = ManagerFactory.eINSTANCE.createRTCManager();
			mgr.setCorbaObject(m);
			this.slaveManagers.add(mgr);
		}
		return this.slaveManagers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int addSlaveManagerR(RTCManager mgr) {
		RTC.ReturnCode_t ret = RTC.ReturnCode_t.RTC_ERROR;
		if (mgr == null) {
			return ret.value();
		}
		RTM.Manager m = ManagerHelper.narrow(mgr.getCorbaObject());
		ret = this.getCorbaObjectInterface().add_slave_manager(m);
		// Slave Managerリストを同期
		this.getSlaveManagersR();
		return ret.value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int removeSlaveManagerR(RTCManager mgr) {
		RTC.ReturnCode_t ret = RTC.ReturnCode_t.RTC_ERROR;
		if (mgr == null) {
			return ret.value();
		}
		RTM.Manager m = ManagerHelper.narrow(mgr.getCorbaObject());
		ret = this.getCorbaObjectInterface().remove_slave_manager(m);
		// Slave Managerリストを同期
		this.getSlaveManagersR();
		return ret.value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<String> getSlaveManagerNames() {
		this.getSlaveManagersR();
		BasicEList<String> result = new BasicEList<String>();
		for (RTCManager m : getSlaveManagers()) {
			String name = SDOUtil.findValueAsString("manager.name",
					m.getProfileR().properties);
			if (name != null) {
				result.add(name);
			}
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<NameValue> getConfigurationR() {
		if (configuratoins == null) {
			configuratoins = new EDataTypeUniqueEList<NameValue>(
					NameValue.class, this,
					ManagerPackage.RTC_MANAGER__CONFIGURATOINS);
		} else {
			configuratoins.clear();
		}
		for (_SDOPackage.NameValue n : this.getCorbaObjectInterface()
				.get_configuration()) {
			NameValue nv = ComponentFactory.eINSTANCE.createNameValue();
			nv.setName(n.name);
//			nv.setValue(n.value.toString());
			nv.setValue(SDOUtil.toAnyString(n.value));
			this.configuratoins.add(nv);
		}
		return this.configuratoins;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int setConfigurationR(String name, String value) {
		RTC.ReturnCode_t ret = this.getCorbaObjectInterface()
				.set_configuration(name, value);
		// Configurationリストを同期
		this.getConfigurationR();
		return ret.value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ManagerPackage.RTC_MANAGER__MANAGER_PROFILE:
				return getManagerProfile();
			case ManagerPackage.RTC_MANAGER__INSTANCE_NAME_L:
				return getInstanceNameL();
			case ManagerPackage.RTC_MANAGER__PATH_ID:
				return getPathId();
			case ManagerPackage.RTC_MANAGER__COMPONENT_PROFILES:
				return getComponentProfiles();
			case ManagerPackage.RTC_MANAGER__LOADABLE_MODULE_PROFILES:
				return getLoadableModuleProfiles();
			case ManagerPackage.RTC_MANAGER__LOADED_MODULE_PROFILES:
				return getLoadedModuleProfiles();
			case ManagerPackage.RTC_MANAGER__FACTORY_MODULE_PROFILES:
				return getFactoryModuleProfiles();
			case ManagerPackage.RTC_MANAGER__MASTER:
				return isMaster();
			case ManagerPackage.RTC_MANAGER__MASTER_MANAGERS:
				return getMasterManagers();
			case ManagerPackage.RTC_MANAGER__SLAVE_MANAGERS:
				return getSlaveManagers();
			case ManagerPackage.RTC_MANAGER__CONFIGURATOINS:
				return getConfiguratoins();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
		@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ManagerPackage.RTC_MANAGER__MANAGER_PROFILE:
				setManagerProfile((ManagerProfile)newValue);
				return;
			case ManagerPackage.RTC_MANAGER__PATH_ID:
				setPathId((String)newValue);
				return;
			case ManagerPackage.RTC_MANAGER__COMPONENT_PROFILES:
				getComponentProfiles().clear();
				getComponentProfiles().addAll((Collection<? extends ComponentProfile>)newValue);
				return;
			case ManagerPackage.RTC_MANAGER__LOADABLE_MODULE_PROFILES:
				getLoadableModuleProfiles().clear();
				getLoadableModuleProfiles().addAll((Collection<? extends ModuleProfile>)newValue);
				return;
			case ManagerPackage.RTC_MANAGER__LOADED_MODULE_PROFILES:
				getLoadedModuleProfiles().clear();
				getLoadedModuleProfiles().addAll((Collection<? extends ModuleProfile>)newValue);
				return;
			case ManagerPackage.RTC_MANAGER__FACTORY_MODULE_PROFILES:
				getFactoryModuleProfiles().clear();
				getFactoryModuleProfiles().addAll((Collection<? extends ModuleProfile>)newValue);
				return;
			case ManagerPackage.RTC_MANAGER__MASTER:
				setMaster((Boolean)newValue);
				return;
			case ManagerPackage.RTC_MANAGER__MASTER_MANAGERS:
				getMasterManagers().clear();
				getMasterManagers().addAll((Collection<? extends RTCManager>)newValue);
				return;
			case ManagerPackage.RTC_MANAGER__SLAVE_MANAGERS:
				getSlaveManagers().clear();
				getSlaveManagers().addAll((Collection<? extends RTCManager>)newValue);
				return;
			case ManagerPackage.RTC_MANAGER__CONFIGURATOINS:
				getConfiguratoins().clear();
				getConfiguratoins().addAll((Collection<? extends NameValue>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ManagerPackage.RTC_MANAGER__MANAGER_PROFILE:
				setManagerProfile(MANAGER_PROFILE_EDEFAULT);
				return;
			case ManagerPackage.RTC_MANAGER__PATH_ID:
				setPathId(PATH_ID_EDEFAULT);
				return;
			case ManagerPackage.RTC_MANAGER__COMPONENT_PROFILES:
				getComponentProfiles().clear();
				return;
			case ManagerPackage.RTC_MANAGER__LOADABLE_MODULE_PROFILES:
				getLoadableModuleProfiles().clear();
				return;
			case ManagerPackage.RTC_MANAGER__LOADED_MODULE_PROFILES:
				getLoadedModuleProfiles().clear();
				return;
			case ManagerPackage.RTC_MANAGER__FACTORY_MODULE_PROFILES:
				getFactoryModuleProfiles().clear();
				return;
			case ManagerPackage.RTC_MANAGER__MASTER:
				setMaster(MASTER_EDEFAULT);
				return;
			case ManagerPackage.RTC_MANAGER__MASTER_MANAGERS:
				getMasterManagers().clear();
				return;
			case ManagerPackage.RTC_MANAGER__SLAVE_MANAGERS:
				getSlaveManagers().clear();
				return;
			case ManagerPackage.RTC_MANAGER__CONFIGURATOINS:
				getConfiguratoins().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ManagerPackage.RTC_MANAGER__MANAGER_PROFILE:
				return MANAGER_PROFILE_EDEFAULT == null ? managerProfile != null : !MANAGER_PROFILE_EDEFAULT.equals(managerProfile);
			case ManagerPackage.RTC_MANAGER__INSTANCE_NAME_L:
				return INSTANCE_NAME_L_EDEFAULT == null ? instanceNameL != null : !INSTANCE_NAME_L_EDEFAULT.equals(instanceNameL);
			case ManagerPackage.RTC_MANAGER__PATH_ID:
				return PATH_ID_EDEFAULT == null ? pathId != null : !PATH_ID_EDEFAULT.equals(pathId);
			case ManagerPackage.RTC_MANAGER__COMPONENT_PROFILES:
				return componentProfiles != null && !componentProfiles.isEmpty();
			case ManagerPackage.RTC_MANAGER__LOADABLE_MODULE_PROFILES:
				return loadableModuleProfiles != null && !loadableModuleProfiles.isEmpty();
			case ManagerPackage.RTC_MANAGER__LOADED_MODULE_PROFILES:
				return loadedModuleProfiles != null && !loadedModuleProfiles.isEmpty();
			case ManagerPackage.RTC_MANAGER__FACTORY_MODULE_PROFILES:
				return factoryModuleProfiles != null && !factoryModuleProfiles.isEmpty();
			case ManagerPackage.RTC_MANAGER__MASTER:
				return master != MASTER_EDEFAULT;
			case ManagerPackage.RTC_MANAGER__MASTER_MANAGERS:
				return masterManagers != null && !masterManagers.isEmpty();
			case ManagerPackage.RTC_MANAGER__SLAVE_MANAGERS:
				return slaveManagers != null && !slaveManagers.isEmpty();
			case ManagerPackage.RTC_MANAGER__CONFIGURATOINS:
				return configuratoins != null && !configuratoins.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (managerProfile: ");
		result.append(managerProfile);
		result.append(", instanceNameL: ");
		result.append(instanceNameL);
		result.append(", pathId: ");
		result.append(pathId);
		result.append(", componentProfiles: ");
		result.append(componentProfiles);
		result.append(", loadableModuleProfiles: ");
		result.append(loadableModuleProfiles);
		result.append(", loadedModuleProfiles: ");
		result.append(loadedModuleProfiles);
		result.append(", factoryModuleProfiles: ");
		result.append(factoryModuleProfiles);
		result.append(", master: ");
		result.append(master);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public ManagerProfile getProfileR() {
		this.managerProfile = this.getCorbaObjectInterface().get_profile();
		return this.managerProfile;
	}


	@Override
	public RTM.Manager getCorbaObjectInterface() {
		return ManagerHelper.narrow(super.getCorbaObject());
	}

	public static final MappingRule MAPPING_RULE = new MappingRule(
			null,
			new ClassMapping(
					RTCManagerImpl.class,
					new ConstructorParamMapping[] { new ConstructorParamMapping(
							CorePackage.eINSTANCE
									.getCorbaWrapperObject_CorbaObject()) }) {
				@Override
				public boolean isTarget(LocalObject parent,
						Object[] remoteObjects, java.lang.Object link) {
					boolean result = false;
					if (remoteObjects[0] instanceof org.omg.CORBA.Object) {
						result = ((org.omg.CORBA.Object) remoteObjects[0])
								._is_a(ManagerHelper.id());
					}
					return result;
				}

				@Override
				public boolean needsPing() {
					return true;
				}

				@Override
				public Object[] narrow(Object[] remoteObjects) {
					return new Object[] { ManagerHelper
							.narrow(((org.omg.CORBA.Object) remoteObjects[0])) };
				}
			}, new AttributeMapping[] {}, new ReferenceMapping[] {});

	private static AttributeMapping[] getAttributeMappings() {
		return new AttributeMapping[] { new AttributeMapping(
				ManagerPackage.eINSTANCE.getRTCManager_ManagerProfile(), true) {
			@Override
			public Object getRemoteAttributeValue(LocalObject localObject,
					Object[] remoteObjects) {
				Object result = null;
				result = ((RTCManager) localObject).getProfileR();
				return result;
			}
		} };

		// new AttributeMapping(ManagerPackage.eINSTANCE
		// .getRTCManager_LoadableModuleList(), true) {
		// @Override
		// public Object getRemoteAttributeValue(
		// LocalObject localObject, Object[] remoteObjects) {
		// List result = new ArrayList();
		// try {
		// String[] modules = RTCManagerHelper.narrow(
		// (org.omg.CORBA.Object) remoteObjects[0])
		// .get_loadable_modules();
		// result = java.util.Arrays.asList(modules);
		// // for (int i = 0; i < modules.length; i++) {
		// // result.add(modules[i]);
		// // }
		// } catch (Exception e) {
		// // void
		// }
		// return (Collection) result;
		// }
		// },
		// new AttributeMapping(ManagerPackage.eINSTANCE
		// .getRTCManager_LoadedModuleList(), true) {
		// @Override
		// public Object getRemoteAttributeValue(
		// LocalObject localObject, Object[] remoteObjects) {
		// List result = new ArrayList();
		// // try {
		// // result = ((Component) localObject)
		// // .getCorbaObjectInterface().get_component_profile();
		// // } catch (Exception e) {
		// // // void
		// // }
		// //
		// return (Collection) result;
		// }
		// },
		// new AttributeMapping(ManagerPackage.eINSTANCE
		// .getRTCManager_ComponentProfileList(), true) {
		// @Override
		// public Object getRemoteAttributeValue(
		// LocalObject localObject, Object[] remoteObjects) {
		// List result = new ArrayList();
		// // try {
		// // result = ((Component) localObject)
		// // .getCorbaObjectInterface().get_component_profile();
		// // } catch (Exception e) {
		// // // void
		// // }
		// //
		// return result;
		// }
		// } };
	}

	private static ReferenceMapping[] getReferenceMappings() {
		return new ReferenceMapping[] {};
	}

	private void synchronizeLocalAttribute(EReference reference) {
		for (AttributeMapping attibuteMapping : getAttributeMappings()) {
			if (reference != null) {
				if (reference.equals(attibuteMapping.getLocalFeature())) {
					try {
						attibuteMapping.syncronizeLocal(this);
					} catch (Exception e) {
						LOGGER.error("Fail to synchronize local", e);
					}
					return;
				}
			} else {
				try {
					attibuteMapping.syncronizeLocal(this);
				} catch (Exception e) {
					LOGGER.error("Fail to synchronize local", e);
					return;
				}
			}
		}
	}

	private void synchronizeLocalReference() {
		for (ReferenceMapping referenceMapping : RTCManagerImpl
				.getReferenceMappings()) {
			try {
				referenceMapping.syncronizeLocal(this);
			} catch (Exception e) {
				LOGGER.error("Fail to synchronize local", e);
			}
		}
	}

	private long lastExecutedTime;
	private static long SYNC_MANUAL_INTERVAL = 1000L;

	@Override
	public void synchronizeManually() {
		if (System.currentTimeMillis() - this.lastExecutedTime < SYNC_MANUAL_INTERVAL) {
			return;
		}
		synchronizeLocalAttribute(null);
		synchronizeLocalReference();
		this.lastExecutedTime = System.currentTimeMillis();
	}

} // RTCManagerImpl
