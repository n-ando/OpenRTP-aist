/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.IPropertyMap;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.util.PropertyMap;
import jp.go.aist.rtm.toolscommon.synchronizationframework.LocalObject;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.AttributeMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ClassMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ConstructorParamMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ManyReferenceMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.MappingRule;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ReferenceMapping;
import jp.go.aist.rtm.toolscommon.ui.propertysource.ComponentSpecificationPropertySource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.views.properties.IPropertySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentSpecificationImpl#getAliasName <em>Alias Name</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentSpecificationImpl#isSpecUnLoad <em>Spec Un Load</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentSpecificationImpl#getRtcType <em>Rtc Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ComponentSpecificationImpl extends ComponentImpl implements ComponentSpecification {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ComponentSpecificationImpl.class);

	/**
	 * The default value of the '{@link #getAliasName() <em>Alias Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAliasName()
	 * @generated
	 * @ordered
	 */
	protected static final String ALIAS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAliasName() <em>Alias Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAliasName()
	 * @generated
	 * @ordered
	 */
	protected String aliasName = ALIAS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isSpecUnLoad() <em>Spec Un Load</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSpecUnLoad()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SPEC_UN_LOAD_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSpecUnLoad() <em>Spec Un Load</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSpecUnLoad()
	 * @generated
	 * @ordered
	 */
	protected boolean specUnLoad = SPEC_UN_LOAD_EDEFAULT;

	/**
	 * The default value of the '{@link #getRtcType() <em>Rtc Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRtcType()
	 * @generated
	 * @ordered
	 */
	protected static final String RTC_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRtcType() <em>Rtc Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRtcType()
	 * @generated
	 * @ordered
	 */
	protected String rtcType = RTC_TYPE_EDEFAULT;

	IPropertyMap properties;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ComponentSpecificationImpl() {
		super();
		this.properties = new PropertyMap();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentPackage.Literals.COMPONENT_SPECIFICATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAliasName() {
		return aliasName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAliasName(String newAliasName) {
		String oldAliasName = aliasName;
		aliasName = newAliasName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT_SPECIFICATION__ALIAS_NAME, oldAliasName, aliasName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSpecUnLoad() {
		return specUnLoad;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpecUnLoad(boolean newSpecUnLoad) {
		boolean oldSpecUnLoad = specUnLoad;
		specUnLoad = newSpecUnLoad;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT_SPECIFICATION__SPEC_UN_LOAD, oldSpecUnLoad, specUnLoad));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRtcType() {
		return rtcType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRtcType(String newRtcType) {
		String oldRtcType = rtcType;
		rtcType = newRtcType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT_SPECIFICATION__RTC_TYPE, oldRtcType, rtcType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ComponentPackage.COMPONENT_SPECIFICATION__ALIAS_NAME:
				return getAliasName();
			case ComponentPackage.COMPONENT_SPECIFICATION__SPEC_UN_LOAD:
				return isSpecUnLoad();
			case ComponentPackage.COMPONENT_SPECIFICATION__RTC_TYPE:
				return getRtcType();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ComponentPackage.COMPONENT_SPECIFICATION__ALIAS_NAME:
				setAliasName((String)newValue);
				return;
			case ComponentPackage.COMPONENT_SPECIFICATION__SPEC_UN_LOAD:
				setSpecUnLoad((Boolean)newValue);
				return;
			case ComponentPackage.COMPONENT_SPECIFICATION__RTC_TYPE:
				setRtcType((String)newValue);
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
			case ComponentPackage.COMPONENT_SPECIFICATION__ALIAS_NAME:
				setAliasName(ALIAS_NAME_EDEFAULT);
				return;
			case ComponentPackage.COMPONENT_SPECIFICATION__SPEC_UN_LOAD:
				setSpecUnLoad(SPEC_UN_LOAD_EDEFAULT);
				return;
			case ComponentPackage.COMPONENT_SPECIFICATION__RTC_TYPE:
				setRtcType(RTC_TYPE_EDEFAULT);
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
			case ComponentPackage.COMPONENT_SPECIFICATION__ALIAS_NAME:
				return ALIAS_NAME_EDEFAULT == null ? aliasName != null : !ALIAS_NAME_EDEFAULT.equals(aliasName);
			case ComponentPackage.COMPONENT_SPECIFICATION__SPEC_UN_LOAD:
				return specUnLoad != SPEC_UN_LOAD_EDEFAULT;
			case ComponentPackage.COMPONENT_SPECIFICATION__RTC_TYPE:
				return RTC_TYPE_EDEFAULT == null ? rtcType != null : !RTC_TYPE_EDEFAULT.equals(rtcType);
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
		result.append(" (aliasName: ");
		result.append(aliasName);
		result.append(", specUnLoad: ");
		result.append(specUnLoad);
		result.append(", rtcType: ");
		result.append(rtcType);
		result.append(')');
		return result.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateConfigurationSetListR(List list, ConfigurationSet activeConfigurationSet, List originallist) {
		clearConfigurationSet();
		getConfigurationSets().addAll(list);
		if (activeConfigurationSet != null) {
			setActiveConfigurationSet(activeConfigurationSet);
		}
		return true;
	}

	private void clearConfigurationSet() {
		getConfigurationSets().clear();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateConfigurationSetR(ConfigurationSet configSet, boolean isActive) {
		EList list = this.getConfigurationSets();
		int index = -1;
		for (int i = 0; i < list.size(); i++) {
			ConfigurationSet cs = (ConfigurationSet) list.get(i);
			if (cs.getId().equals(configSet.getId())) {
				index = i;
				break;
			}
		}
		if (index == -1) {
			list.add(configSet);
		} else {
			ConfigurationSet acs = (ConfigurationSet) this
					.getActiveConfigurationSet();
			ConfigurationSet cs = (ConfigurationSet) list.get(index);
			if (acs != null && acs.getId().equals(cs.getId())) {
				this.setActiveConfigurationSet(configSet);
			}
			Object o = list.get(index);
			list.set(index, configSet);
			if (!o.equals(configSet)) {
				list.remove(o);
			}
		}
		return true;
	}

	@Override
	public boolean addComponentsR(List<Component> componentList) {
		return doAddComponents(componentList);			
	}

	@Override
	public boolean setComponentsR(List<Component> componentList) {
		getComponents().clear();
		return doAddComponents(componentList);			
	}

	private boolean doAddComponents(List<Component> componentList) {
		getComponents().addAll(componentList);
		for (Component c : componentList) {
			if (c.inOnlineSystemDiagram()) {
				// オンラインの場合は同期処理でポート設定
				return true;
			}
		}
		// オフラインの場合はここでポート設定
		_setWrappingPorts(_getWrappedPorts());
		// 複合RTCのECを、子RTCのparticipateに追加
		// EListでリスト走査時の排他チェックに問題があり、複合RTC作成時のFuture内で例外となるのでArrayListに詰め替え
		List<ExecutionContext> ecList = new ArrayList<>(getExecutionContexts());
		for (Component c : componentList) {
			for (ExecutionContext ec : ecList) {
				ec.addComponentR(c);
			}
			c.getParticipationContextHandler().sync();
		}
		return true;
	}

	@Override
	public boolean removeComponentR(Component component) {
		removeByEqual(getComponents(), component);
		if (component.inOnlineSystemDiagram()) {
			// オンラインの場合は同期処理でポート設定
			return true;
		}
		// オフラインの場合は手動で設定
		List<Port> ports = this._getWrappedPorts();
		this._setWrappingPorts(ports);
		// 子RTCから複合RTCのECを削除
		List<ExecutionContext> deletes = new ArrayList<ExecutionContext>();
		for (ExecutionContext pc : component.getParticipationContexts()) {
			if (getExecutionContexts().contains(pc)) {
				deletes.add(pc);
			}
		}
		for (ExecutionContext pc : deletes) {
			pc.removeComponentR(component);
		}
		component.getParticipationContextHandler().sync();
		return true;
	}

	@SuppressWarnings("unchecked")
	private void removeByEqual(EList components, Component component) {
		for (Iterator iterate = components.iterator(); iterate.hasNext();) {
			if (iterate.next().equals(component)) iterate.remove();
		}
	}

	void _setWrappingPorts(List<Port> ports) {
		// 子RTCのポートのコピーでポートリストを更新
		getPorts().clear();
		for (Port port : ports) {
			getPorts().add(port.proxy());
		}
	}

	List<Port> _getWrappedPorts() {
		// プロパティから表示するポート名一覧を作成
		List<String> names = new ArrayList<String>(getExportedPorts());

		// プロキシ対象の子コンポーネントのポート一覧を生成
		List<Port> ports = new ArrayList<Port>();
		for (Object obj : getComponents()) {
			Component cp = (Component) obj;
			for (Object o : cp.getPorts()) {
				Port port = (Port) o;
				String name = port.getNameL();
				if (names.contains(name)) {
					ports.add(port);
				}
			}
		}
		return ports;
	}

	@Override
	public boolean isGroupingCompositeComponent() {
		return (getCompositeTypeL().equals(
				Component.COMPOSITETYPE_GROUPING)) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public java.lang.Object getAdapter(Class adapter) {
		java.lang.Object result = null;
		if (IPropertySource.class.equals(adapter)) {
			result = new ComponentSpecificationPropertySource(this);
		}

		if (result == null) {
			result = super.getAdapter(adapter);
		}
		return result;
	}

	@Override
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	@Override
	public void setProperty(String key, String value) {
		properties.setProperty(key, value);
	}

	@Override
	public String removeProperty(String key) {
		return properties.removeProperty(key);
	}

	@Override
	public EList<String> getPropertyKeys() {
		return properties.getPropertyKeys();
	}

	@Override
	public IPropertyMap getPropertyMap() {
		return properties;
	}

	/** Grouping複合RTC用のマッピングルール */
	public static final MappingRule MAPPING_RULE = new MappingRule(null,
			new ClassMapping(ComponentSpecificationImpl.class,
					new ConstructorParamMapping[] {}, true) {
				@Override
				public boolean isTarget(LocalObject parent,
						Object[] remoteObjects, java.lang.Object link) {
					return false;
				}
			}, new AttributeMapping[] {}, new ReferenceMapping[] {});


	private static ReferenceMapping[] getReferenceMappings() {
		return new ReferenceMapping[] { new ManyReferenceMapping(
				ComponentPackage.eINSTANCE.getComponent_Ports()) {
			@SuppressWarnings("unchecked")
			@Override
			public void syncronizeLocal(LocalObject localObject) {
				if (!(localObject instanceof ComponentSpecificationImpl)) {
					return;
				}
				ComponentSpecificationImpl comp = (ComponentSpecificationImpl) localObject;
				if (!comp.inOnlineSystemDiagram() || !comp.isGroupingCompositeComponent()) {
					return;
				}
				
				// 子コンポーネントの生存確認を行う
				comp.removeDeadChild();
				
				// ポートの更新を行う
				// 新しいポートのリンクリスト
				List<Port> newPorts = comp._getWrappedPorts();
				// 現在のポートを更新する
				for (Iterator iterate = comp.getPorts().iterator();iterate.hasNext();){
					Port port = (Port) iterate.next();
					if (!isExist(port, newPorts)) {
						iterate.remove();
					}
				}
				// 追加対象のポートを追加
				for (Port port : newPorts) {
					comp.getPorts().add(port.proxy());
				}
			}

			private boolean isExist(Port port, List<Port> newPorts) {
				String target = port.getOriginalPortString();
				for (Iterator<Port> iterate = newPorts.iterator();iterate.hasNext();){
					Port temp = iterate.next();
					if (temp.getOriginalPortString().equals(target)) {
						iterate.remove();
						return true;
					}
				}
				return false;
			}
		}
		};
	}

	@Override
	public void synchronizeLocalReference() {
		if (!inOnlineSystemDiagram()) return;
		if (!isGroupingCompositeComponent()) return;
		
		for (ReferenceMapping refMap : getReferenceMappings()) {
			try {
				refMap.syncronizeLocal(this);
			} catch (Exception e) {
				LOGGER.error("Fail to synchronize local", e);
			}
		}
	}

	@Override
	public void synchronizeRemoteChildComponents() {
		if (!inOnlineSystemDiagram() || !isGroupingCompositeComponent()) {
			super.synchronizeRemoteChildComponents();
			return;
		}
		// Grouping複合RTCで、子RTCがCorbaComponent、かつオブザーバありの場合は子の同期を再帰しない
		if (getComponents() == null) {
			return;
		}
		for (Component comp : getComponents()) {
			if (hasStatusObserver(comp)) {
				continue;
			}
			comp.synchronizeRemoteAttribute(null);
			comp.synchronizeRemoteChildComponents();
		}
	}

	@Override
	public void synchronizeChildComponents() {
		if (!inOnlineSystemDiagram() || !isGroupingCompositeComponent()) {
			super.synchronizeRemoteChildComponents();
			return;
		}
		// Grouping複合RTCで、子RTCがCorbaComponent、かつオブザーバありの場合は子の同期を再帰しない
		if (getComponents() == null) {
			return;
		}
		for (Component comp : getComponents()) {
			if (hasStatusObserver(comp)) {
				continue;
			}
			comp.synchronizeLocalAttribute(null);
			comp.synchronizeLocalReference();
			comp.synchronizeChildComponents();
		}
	}

	private boolean hasStatusObserver(Component comp) {
		if (comp instanceof CorbaComponent) {
			CorbaComponent c = (CorbaComponent) comp;
			if (c.getStatusObserver() != null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String getPath() {
		if (getPathId() == null) return null;
		int index = getPathId().lastIndexOf(File.separator);
		if (index < 0) return getPathId();
		return getPathId().substring(0, index);
	}

	@Override
	public Component copy() {
		Component copy = (Component) EcoreUtil.copy(this);
		// ExecutionContextとIDの関連付けを複製
		for (int i = 0; i < this.getExecutionContexts().size(); i++) {
			ExecutionContext orgEc = (ExecutionContext) this
					.getExecutionContexts().get(i);
			ExecutionContext newEc = (ExecutionContext) copy
					.getExecutionContexts().get(i);
			if (newEc == null) {
				continue;
			}
			String ecid = this.getExecutionContextHandler().getId(orgEc);
			if (ecid != null) {
				copy.getExecutionContextHandler().setContext(ecid, newEc);
			}
		}
		adjustPathId(copy.getAllComponents());
		// プロパティの複製
		for (String key : this.getPropertyKeys()) {
			copy.setProperty(key, this.getProperty(key));
		}
		for (int i = 0; i < this.getPorts().size(); i++) {
			Port origPort = this.getPorts().get(i);
			Port copyPort = copy.getPorts().get(i);
			for (String key : origPort.getSynchronizer().getPropertyKeys()) {
				String value = origPort.getSynchronizer().getProperty(key);
				copyPort.getSynchronizer().setProperty(key, value);
			}
		}
		return copy;
	}

	@Override
	public boolean isDead() {
		return true;
	}

} // ComponentSpecificationImpl
