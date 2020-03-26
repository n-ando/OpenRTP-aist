/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.manager;

import RTC.ComponentProfile;
import RTC.RTObject;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.core.CorbaWrapperObject;

import org.eclipse.emf.common.util.EList;

import RTM.ManagerProfile;
import RTM.ModuleProfile;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RTC Manager</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getManagerProfile <em>Manager Profile</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getInstanceNameL <em>Instance Name L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getPathId <em>Path Id</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getComponentProfiles <em>Component Profiles</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getLoadableModuleProfiles <em>Loadable Module Profiles</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getLoadedModuleProfiles <em>Loaded Module Profiles</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getFactoryModuleProfiles <em>Factory Module Profiles</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager#isMaster <em>Master</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getMasterManagers <em>Master Managers</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getSlaveManagers <em>Slave Managers</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getConfiguratoins <em>Configuratoins</em>}</li>
 * </ul>
 *
 * @see jp.go.aist.rtm.toolscommon.model.manager.ManagerPackage#getRTCManager()
 * @model
 * @generated
 */
public interface RTCManager extends CorbaWrapperObject {
	/**
	 * Returns the value of the '<em><b>Manager Profile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Manager Profile</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Manager Profile</em>' attribute.
	 * @see #setManagerProfile(ManagerProfile)
	 * @see jp.go.aist.rtm.toolscommon.model.manager.ManagerPackage#getRTCManager_ManagerProfile()
	 * @model dataType="jp.go.aist.rtm.toolscommon.model.manager.RTMManagerProfile" transient="true"
	 * @generated
	 */
	ManagerProfile getManagerProfile();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getManagerProfile <em>Manager Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Manager Profile</em>' attribute.
	 * @see #getManagerProfile()
	 * @generated
	 */
	void setManagerProfile(ManagerProfile value);

	/**
	 * Returns the value of the '<em><b>Instance Name L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instance Name L</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instance Name L</em>' attribute.
	 * @see jp.go.aist.rtm.toolscommon.model.manager.ManagerPackage#getRTCManager_InstanceNameL()
	 * @model changeable="false"
	 * @generated
	 */
	String getInstanceNameL();

	/**
	 * Returns the value of the '<em><b>Path Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path Id</em>' attribute.
	 * @see #setPathId(String)
	 * @see jp.go.aist.rtm.toolscommon.model.manager.ManagerPackage#getRTCManager_PathId()
	 * @model
	 * @generated
	 */
	String getPathId();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getPathId <em>Path Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path Id</em>' attribute.
	 * @see #getPathId()
	 * @generated
	 */
	void setPathId(String value);

	/**
	 * Returns the value of the '<em><b>Component Profiles</b></em>' attribute list.
	 * The list contents are of type {@link RTC.ComponentProfile}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Profiles</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Profiles</em>' attribute list.
	 * @see jp.go.aist.rtm.toolscommon.model.manager.ManagerPackage#getRTCManager_ComponentProfiles()
	 * @model dataType="jp.go.aist.rtm.toolscommon.model.component.RTCComponentProfile" transient="true"
	 * @generated
	 */
	EList<ComponentProfile> getComponentProfiles();

	/**
	 * Returns the value of the '<em><b>Loadable Module Profiles</b></em>' attribute list.
	 * The list contents are of type {@link RTM.ModuleProfile}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loadable Module Profiles</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Loadable Module Profiles</em>' attribute list.
	 * @see jp.go.aist.rtm.toolscommon.model.manager.ManagerPackage#getRTCManager_LoadableModuleProfiles()
	 * @model dataType="jp.go.aist.rtm.toolscommon.model.manager.RTMModuleProfile" transient="true"
	 * @generated
	 */
	EList<ModuleProfile> getLoadableModuleProfiles();

	/**
	 * Returns the value of the '<em><b>Loaded Module Profiles</b></em>' attribute list.
	 * The list contents are of type {@link RTM.ModuleProfile}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loaded Module Profiles</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Loaded Module Profiles</em>' attribute list.
	 * @see jp.go.aist.rtm.toolscommon.model.manager.ManagerPackage#getRTCManager_LoadedModuleProfiles()
	 * @model dataType="jp.go.aist.rtm.toolscommon.model.manager.RTMModuleProfile" transient="true"
	 * @generated
	 */
	EList<ModuleProfile> getLoadedModuleProfiles();

	/**
	 * Returns the value of the '<em><b>Factory Module Profiles</b></em>' attribute list.
	 * The list contents are of type {@link RTM.ModuleProfile}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Factory Module Profiles</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Factory Module Profiles</em>' attribute list.
	 * @see jp.go.aist.rtm.toolscommon.model.manager.ManagerPackage#getRTCManager_FactoryModuleProfiles()
	 * @model dataType="jp.go.aist.rtm.toolscommon.model.manager.RTMModuleProfile" transient="true"
	 * @generated
	 */
	EList<ModuleProfile> getFactoryModuleProfiles();

	/**
	 * Returns the value of the '<em><b>Master</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Master</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Master</em>' attribute.
	 * @see #setMaster(boolean)
	 * @see jp.go.aist.rtm.toolscommon.model.manager.ManagerPackage#getRTCManager_Master()
	 * @model
	 * @generated
	 */
	boolean isMaster();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager#isMaster <em>Master</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Master</em>' attribute.
	 * @see #isMaster()
	 * @generated
	 */
	void setMaster(boolean value);

	/**
	 * Returns the value of the '<em><b>Master Managers</b></em>' reference list.
	 * The list contents are of type {@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Master Managers</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Master Managers</em>' reference list.
	 * @see jp.go.aist.rtm.toolscommon.model.manager.ManagerPackage#getRTCManager_MasterManagers()
	 * @model
	 * @generated
	 */
	EList<RTCManager> getMasterManagers();

	/**
	 * Returns the value of the '<em><b>Slave Managers</b></em>' reference list.
	 * The list contents are of type {@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Slave Managers</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Slave Managers</em>' reference list.
	 * @see jp.go.aist.rtm.toolscommon.model.manager.ManagerPackage#getRTCManager_SlaveManagers()
	 * @model
	 * @generated
	 */
	EList<RTCManager> getSlaveManagers();

	/**
	 * Returns the value of the '<em><b>Configuratoins</b></em>' reference list.
	 * The list contents are of type {@link jp.go.aist.rtm.toolscommon.model.component.NameValue}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Configuratoins</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Configuratoins</em>' reference list.
	 * @see jp.go.aist.rtm.toolscommon.model.manager.ManagerPackage#getRTCManager_Configuratoins()
	 * @model
	 * @generated
	 */
	EList<NameValue> getConfiguratoins();

	/**
	 * <!-- begin-user-doc -->
	 * RTコンポーネントを新規に作成する
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	Component createComponentR(String compName);

	/**
	 * <!-- begin-user-doc -->
	 * 現在は未使用
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int deleteComponentR(String instanceName);

	/**
	 * <!-- begin-user-doc -->
	 * 現在は未使用
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="jp.go.aist.rtm.toolscommon.model.component.RTCRTObject"
	 * @generated
	 */
	EList<RTObject> getComponentsR();

	/**
	 * <!-- begin-user-doc -->
	 * 現在は未使用
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="jp.go.aist.rtm.toolscommon.model.component.RTCComponentProfile"
	 * @generated
	 */
	EList<ComponentProfile> getComponentProfilesR();

	/**
	 * <!-- begin-user-doc -->
	 * モジュールをロードする
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int loadModuleR(String pathname, String initfunc);

	/**
	 * <!-- begin-user-doc -->
	 * モジュールをアンロードする
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int unloadModuleR(String pathname);

	/**
	 * <!-- begin-user-doc -->
	 * LoadableModuleのプロファイル一覧を返す
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="jp.go.aist.rtm.toolscommon.model.manager.RTMModuleProfile"
	 * @generated
	 */
	EList<ModuleProfile> getLoadableModuleProfilesR();

	/**
	 * <!-- begin-user-doc -->
	 * LoadedModuleのプロファイル一覧を返す
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="jp.go.aist.rtm.toolscommon.model.manager.RTMModuleProfile"
	 * @generated
	 */
	EList<ModuleProfile> getLoadedModuleProfilesR();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="jp.go.aist.rtm.toolscommon.model.manager.RTMModuleProfile"
	 * @generated
	 */
	EList<ModuleProfile> getFactoryModuleProfilesR();

	/**
	 * <!-- begin-user-doc -->
	 * RTコンポーネントをフォークする
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int forkR();

	/**
	 * <!-- begin-user-doc -->
	 * マネージャをシャットダウンする
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int shutdownR();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int restartR();

	/**
	 * <!-- begin-user-doc -->
	 * 管理下のRTCの一覧を返す(IDL)
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<String> getComponentInstanceNamesR();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<String> getLoadableModuleFileNamesR();

	/**
	 * <!-- begin-user-doc -->
	 * LoadedModuleのファイル名一覧を返す(IDL)
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<String> getLoadedModuleFileNamesR();

	/**
	 * <!-- begin-user-doc -->
	 * ファクトリの一覧を返す(IDL)
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<String> getFactoryProfileTypeNamesR();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<String> getComponentInstanceNames();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<String> getLoadableModuleFileNames();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<String> getLoadedModuleFileNames();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<String> getFactoryTypeNames();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isMasterR();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<RTCManager> getMasterManagersR();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int addMasterManagerR(RTCManager mgr);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int removeMasterManagerR(RTCManager mgr);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<RTCManager> getSlaveManagersR();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int addSlaveManagerR(RTCManager mgr);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int removeSlaveManagerR(RTCManager mgr);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<String> getSlaveManagerNames();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<NameValue> getConfigurationR();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int setConfigurationR(String name, String value);

	/**
	 * <!-- begin-user-doc -->
	 * ManagerProfileを返す
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="jp.go.aist.rtm.toolscommon.model.manager.RTMManagerProfile"
	 * @generated
	 */
	ManagerProfile getProfileR();

	/**
	 * 同期を手動で実行する
	 */
	void synchronizeManually();

} // RTCManager