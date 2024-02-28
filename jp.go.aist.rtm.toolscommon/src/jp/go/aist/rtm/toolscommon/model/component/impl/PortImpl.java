/**
 * <copyright>
 * </copyright>
 *
 * $Id: PortImpl.java,v 1.1 2008/01/29 05:52:23 yamashita Exp $
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortInterfaceProfile;
import jp.go.aist.rtm.toolscommon.model.component.PortSynchronizer;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.core.impl.WrapperObjectImpl;
import jp.go.aist.rtm.toolscommon.util.SDOUtil;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Port</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.PortImpl#getOriginalPortString <em>Original Port String</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.PortImpl#getSynchronizer <em>Synchronizer</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.PortImpl#getNameL <em>Name L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.PortImpl#isAllowAnyDataType <em>Allow Any Data Type</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.PortImpl#isAllowAnyInterfaceType <em>Allow Any Interface Type</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.PortImpl#isAllowAnyDataflowType <em>Allow Any Dataflow Type</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.PortImpl#isAllowAnySubscriptionType <em>Allow Any Subscription Type</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.PortImpl#getConnectorProfiles <em>Connector Profiles</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.PortImpl#getInterfaces <em>Interfaces</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.PortImpl#getDataflowType <em>Dataflow Type</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.PortImpl#getSubscriptionType <em>Subscription Type</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.PortImpl#getDataType <em>Data Type</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.PortImpl#getInterfaceType <em>Interface Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PortImpl extends WrapperObjectImpl implements Port {
	/**
	 * The default value of the '{@link #getOriginalPortString() <em>Original Port String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginalPortString()
	 * @generated
	 * @ordered
	 */
	protected static final String ORIGINAL_PORT_STRING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOriginalPortString() <em>Original Port String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginalPortString()
	 * @generated
	 * @ordered
	 */
	protected String originalPortString = ORIGINAL_PORT_STRING_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSynchronizer() <em>Synchronizer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSynchronizer()
	 * @generated
	 * @ordered
	 */
	protected PortSynchronizer synchronizer;

	/**
	 * The default value of the '{@link #getNameL() <em>Name L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNameL()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_L_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNameL() <em>Name L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNameL()
	 * @generated
	 * @ordered
	 */
	protected String nameL = NAME_L_EDEFAULT;

	/**
	 * The default value of the '{@link #isAllowAnyDataType() <em>Allow Any Data Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllowAnyDataType()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ALLOW_ANY_DATA_TYPE_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isAllowAnyInterfaceType() <em>Allow Any Interface Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllowAnyInterfaceType()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ALLOW_ANY_INTERFACE_TYPE_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isAllowAnyDataflowType() <em>Allow Any Dataflow Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllowAnyDataflowType()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ALLOW_ANY_DATAFLOW_TYPE_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isAllowAnySubscriptionType() <em>Allow Any Subscription Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllowAnySubscriptionType()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ALLOW_ANY_SUBSCRIPTION_TYPE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #getConnectorProfiles() <em>Connector Profiles</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectorProfiles()
	 * @generated
	 * @ordered
	 */
	protected EList<ConnectorProfile> connectorProfiles;

	/**
	 * The cached value of the '{@link #getInterfaces() <em>Interfaces</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterfaces()
	 * @generated
	 * @ordered
	 */
	protected EList<PortInterfaceProfile> interfaces;

	/**
	 * The default value of the '{@link #getDataflowType() <em>Dataflow Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataflowType()
	 * @generated
	 * @ordered
	 */
	protected static final String DATAFLOW_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDataflowType() <em>Dataflow Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataflowType()
	 * @generated
	 * @ordered
	 */
	protected String dataflowType = DATAFLOW_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSubscriptionType() <em>Subscription Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubscriptionType()
	 * @generated
	 * @ordered
	 */
	protected static final String SUBSCRIPTION_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSubscriptionType() <em>Subscription Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubscriptionType()
	 * @generated
	 * @ordered
	 */
	protected String subscriptionType = SUBSCRIPTION_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDataType() <em>Data Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataType()
	 * @generated
	 * @ordered
	 */
	protected static final String DATA_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDataType() <em>Data Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataType()
	 * @generated
	 * @ordered
	 */
	protected String dataType = DATA_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getInterfaceType() <em>Interface Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterfaceType()
	 * @generated
	 * @ordered
	 */
	protected static final String INTERFACE_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInterfaceType() <em>Interface Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterfaceType()
	 * @generated
	 * @ordered
	 */
	protected String interfaceType = INTERFACE_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected PortImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentPackage.Literals.PORT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getOriginalPortString() {
		if (getSynchronizer() != null) {
			String result = getSynchronizer().getOriginalPortString();
			if (result != null) return result;
		}
		Component component = (Component) eContainer();
		if (component == null) return getOriginalPortString(null, null, null, getNameL());
		//TODO 09.09.30 instanceName 追加(pathURI対応)
		return  getOriginalPortString(
				component.getComponentId(), component.getPathId(),
				component.getInstanceNameL(), getNameL());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setOriginalPortString(String newOriginalPortString) {
		if (getSynchronizer() != null) {
			getSynchronizer().setOriginalPortString(newOriginalPortString);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortSynchronizer getSynchronizer() {
		return synchronizer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSynchronizer(PortSynchronizer newSynchronizer, NotificationChain msgs) {
		PortSynchronizer oldSynchronizer = synchronizer;
		synchronizer = newSynchronizer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ComponentPackage.PORT__SYNCHRONIZER, oldSynchronizer, newSynchronizer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSynchronizer(PortSynchronizer newSynchronizer) {
		if (newSynchronizer != synchronizer) {
			NotificationChain msgs = null;
			if (synchronizer != null)
				msgs = ((InternalEObject)synchronizer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ComponentPackage.PORT__SYNCHRONIZER, null, msgs);
			if (newSynchronizer != null)
				msgs = ((InternalEObject)newSynchronizer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ComponentPackage.PORT__SYNCHRONIZER, null, msgs);
			msgs = basicSetSynchronizer(newSynchronizer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.PORT__SYNCHRONIZER, newSynchronizer, newSynchronizer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNameL() {
		return nameL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNameL(String newNameL) {
		String oldNameL = nameL;
		nameL = newNameL;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.PORT__NAME_L, oldNameL, nameL));
	}

	public List<String> getDataflowTypes() {
		return SDOUtil.getValueList(getDataflowType());
	}
	

	public List<String> getDataTypes() {
		return SDOUtil.getValueList(getDataType());
	}

	public List<String> getInterfaceTypes() {
		return SDOUtil.getValueList(getInterfaceType());
	}
	
	public List<String> getSubscriptionTypes() {
		return SDOUtil.getValueList(getSubscriptionType());
	}

	public List<String> getSerializerTypes() {
		return SDOUtil.getValueList(getSubscriptionType());
	}
	
	/**
	 * nameValueから値を取得する
	 * 
	 * @param nameValues
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getValueList(List nameValues, String key) {
		NameValue findByName = NameValueImpl.findByName(nameValues, key);
		if (findByName != null) {
			return findByName.getValueAsStringList();
		}

		return Collections.emptyList();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isAllowAnyDataType() {
		return isExistAny(getDataTypes());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isAllowAnyInterfaceType() {
		return isExistAny(getInterfaceTypes());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isAllowAnyDataflowType() {
		return isExistAny(getDataflowTypes());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isAllowAnySubscriptionType() {
		return isExistAny(getSubscriptionTypes());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConnectorProfile> getConnectorProfiles() {
		if (connectorProfiles == null) {
			connectorProfiles = new EObjectResolvingEList<ConnectorProfile>(ConnectorProfile.class, this, ComponentPackage.PORT__CONNECTOR_PROFILES);
		}
		return connectorProfiles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PortInterfaceProfile> getInterfaces() {
		if (interfaces == null) {
			interfaces = new EDataTypeUniqueEList<PortInterfaceProfile>(PortInterfaceProfile.class, this, ComponentPackage.PORT__INTERFACES);
		}
		return interfaces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getDataflowType() {
		if (dataflowType != null) return dataflowType;
		return getSynchronizer().getDataflowType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataflowType(String newDataflowType) {
		String oldDataflowType = dataflowType;
		dataflowType = newDataflowType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.PORT__DATAFLOW_TYPE, oldDataflowType, dataflowType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getSubscriptionType() {
		if (subscriptionType != null) return subscriptionType;
		return getSynchronizer().getSubscriptionType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubscriptionType(String newSubscriptionType) {
		String oldSubscriptionType = subscriptionType;
		subscriptionType = newSubscriptionType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.PORT__SUBSCRIPTION_TYPE, oldSubscriptionType, subscriptionType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getDataType() {
		if (dataType != null) return dataType;
		return getSynchronizer().getDataType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataType(String newDataType) {
		String oldDataType = dataType;
		dataType = newDataType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.PORT__DATA_TYPE, oldDataType, dataType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getInterfaceType() {
		if (interfaceType != null) return interfaceType;
		return getSynchronizer().getInterfaceType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInterfaceType(String newInterfaceType) {
		String oldInterfaceType = interfaceType;
		interfaceType = newInterfaceType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.PORT__INTERFACE_TYPE, oldInterfaceType, interfaceType));
	}

	public static boolean isExistAny(List<String> targetList) {
		for (String target : targetList) {
			if (isAnyString(target)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 対象がAnyであるか確認する
	 * 
	 * @param target
	 * @return
	 */
	public static boolean isAnyString(String target) {
		return ConnectorProfile.ANY.equalsIgnoreCase(target);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void disconnectAll() {
		getSynchronizer().disconnectAll();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Port findPort(SystemDiagram diagram, String originalPortString) {
		if (diagram == null) return getOriginalPortString().equals(originalPortString) ? this : null;
		for (Object element : diagram.getComponents()) {
			Component component = (Component) element;
			Port temp = findPort(component, originalPortString);
			if (temp != null) return temp;
		}
		return null;
	}

	private Port findPort(Component component, String originalPortString) {
		if (component.isCompositeComponent()) {
			for (Object element : component.getComponents()) {
				Component child = (Component)element;
				Port temp = findPort(child, originalPortString);
				if (temp != null) return temp;
			}
			return null;
		}
		for (Object obj: component.getPorts()) {
			Port temp = (Port) obj;
			if (temp.getOriginalPortString().equals(originalPortString)) {
				return temp;
			}
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTargetConnector(Port target) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSourceConnector(Port source) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ComponentPackage.PORT__SYNCHRONIZER:
				return basicSetSynchronizer(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ComponentPackage.PORT__ORIGINAL_PORT_STRING:
				return getOriginalPortString();
			case ComponentPackage.PORT__SYNCHRONIZER:
				return getSynchronizer();
			case ComponentPackage.PORT__NAME_L:
				return getNameL();
			case ComponentPackage.PORT__ALLOW_ANY_DATA_TYPE:
				return isAllowAnyDataType();
			case ComponentPackage.PORT__ALLOW_ANY_INTERFACE_TYPE:
				return isAllowAnyInterfaceType();
			case ComponentPackage.PORT__ALLOW_ANY_DATAFLOW_TYPE:
				return isAllowAnyDataflowType();
			case ComponentPackage.PORT__ALLOW_ANY_SUBSCRIPTION_TYPE:
				return isAllowAnySubscriptionType();
			case ComponentPackage.PORT__CONNECTOR_PROFILES:
				return getConnectorProfiles();
			case ComponentPackage.PORT__INTERFACES:
				return getInterfaces();
			case ComponentPackage.PORT__DATAFLOW_TYPE:
				return getDataflowType();
			case ComponentPackage.PORT__SUBSCRIPTION_TYPE:
				return getSubscriptionType();
			case ComponentPackage.PORT__DATA_TYPE:
				return getDataType();
			case ComponentPackage.PORT__INTERFACE_TYPE:
				return getInterfaceType();
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
			case ComponentPackage.PORT__ORIGINAL_PORT_STRING:
				setOriginalPortString((String)newValue);
				return;
			case ComponentPackage.PORT__SYNCHRONIZER:
				setSynchronizer((PortSynchronizer)newValue);
				return;
			case ComponentPackage.PORT__NAME_L:
				setNameL((String)newValue);
				return;
			case ComponentPackage.PORT__CONNECTOR_PROFILES:
				getConnectorProfiles().clear();
				getConnectorProfiles().addAll((Collection<? extends ConnectorProfile>)newValue);
				return;
			case ComponentPackage.PORT__INTERFACES:
				getInterfaces().clear();
				getInterfaces().addAll((Collection<? extends PortInterfaceProfile>)newValue);
				return;
			case ComponentPackage.PORT__DATAFLOW_TYPE:
				setDataflowType((String)newValue);
				return;
			case ComponentPackage.PORT__SUBSCRIPTION_TYPE:
				setSubscriptionType((String)newValue);
				return;
			case ComponentPackage.PORT__DATA_TYPE:
				setDataType((String)newValue);
				return;
			case ComponentPackage.PORT__INTERFACE_TYPE:
				setInterfaceType((String)newValue);
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
			case ComponentPackage.PORT__ORIGINAL_PORT_STRING:
				setOriginalPortString(ORIGINAL_PORT_STRING_EDEFAULT);
				return;
			case ComponentPackage.PORT__SYNCHRONIZER:
				setSynchronizer((PortSynchronizer)null);
				return;
			case ComponentPackage.PORT__NAME_L:
				setNameL(NAME_L_EDEFAULT);
				return;
			case ComponentPackage.PORT__CONNECTOR_PROFILES:
				getConnectorProfiles().clear();
				return;
			case ComponentPackage.PORT__INTERFACES:
				getInterfaces().clear();
				return;
			case ComponentPackage.PORT__DATAFLOW_TYPE:
				setDataflowType(DATAFLOW_TYPE_EDEFAULT);
				return;
			case ComponentPackage.PORT__SUBSCRIPTION_TYPE:
				setSubscriptionType(SUBSCRIPTION_TYPE_EDEFAULT);
				return;
			case ComponentPackage.PORT__DATA_TYPE:
				setDataType(DATA_TYPE_EDEFAULT);
				return;
			case ComponentPackage.PORT__INTERFACE_TYPE:
				setInterfaceType(INTERFACE_TYPE_EDEFAULT);
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
			case ComponentPackage.PORT__ORIGINAL_PORT_STRING:
				return ORIGINAL_PORT_STRING_EDEFAULT == null ? originalPortString != null : !ORIGINAL_PORT_STRING_EDEFAULT.equals(originalPortString);
			case ComponentPackage.PORT__SYNCHRONIZER:
				return synchronizer != null;
			case ComponentPackage.PORT__NAME_L:
				return NAME_L_EDEFAULT == null ? nameL != null : !NAME_L_EDEFAULT.equals(nameL);
			case ComponentPackage.PORT__ALLOW_ANY_DATA_TYPE:
				return isAllowAnyDataType() != ALLOW_ANY_DATA_TYPE_EDEFAULT;
			case ComponentPackage.PORT__ALLOW_ANY_INTERFACE_TYPE:
				return isAllowAnyInterfaceType() != ALLOW_ANY_INTERFACE_TYPE_EDEFAULT;
			case ComponentPackage.PORT__ALLOW_ANY_DATAFLOW_TYPE:
				return isAllowAnyDataflowType() != ALLOW_ANY_DATAFLOW_TYPE_EDEFAULT;
			case ComponentPackage.PORT__ALLOW_ANY_SUBSCRIPTION_TYPE:
				return isAllowAnySubscriptionType() != ALLOW_ANY_SUBSCRIPTION_TYPE_EDEFAULT;
			case ComponentPackage.PORT__CONNECTOR_PROFILES:
				return connectorProfiles != null && !connectorProfiles.isEmpty();
			case ComponentPackage.PORT__INTERFACES:
				return interfaces != null && !interfaces.isEmpty();
			case ComponentPackage.PORT__DATAFLOW_TYPE:
				return DATAFLOW_TYPE_EDEFAULT == null ? dataflowType != null : !DATAFLOW_TYPE_EDEFAULT.equals(dataflowType);
			case ComponentPackage.PORT__SUBSCRIPTION_TYPE:
				return SUBSCRIPTION_TYPE_EDEFAULT == null ? subscriptionType != null : !SUBSCRIPTION_TYPE_EDEFAULT.equals(subscriptionType);
			case ComponentPackage.PORT__DATA_TYPE:
				return DATA_TYPE_EDEFAULT == null ? dataType != null : !DATA_TYPE_EDEFAULT.equals(dataType);
			case ComponentPackage.PORT__INTERFACE_TYPE:
				return INTERFACE_TYPE_EDEFAULT == null ? interfaceType != null : !INTERFACE_TYPE_EDEFAULT.equals(interfaceType);
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
		result.append(" (originalPortString: ");
		result.append(originalPortString);
		result.append(", nameL: ");
		result.append(nameL);
		result.append(", interfaces: ");
		result.append(interfaces);
		result.append(", dataflowType: ");
		result.append(dataflowType);
		result.append(", subscriptionType: ");
		result.append(subscriptionType);
		result.append(", dataType: ");
		result.append(dataType);
		result.append(", interfaceType: ");
		result.append(interfaceType);
		result.append(')');
		return result.toString();
	}

	//TODO 09.09.30 instanceName 追加(pathURI対応)
	private String getOriginalPortString(String componentId,
			String pathId, String instanceName, String portName) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{componentId:").append(componentId);
		buffer.append(",pathId:").append(pathId);
		buffer.append(",instanceName:").append(instanceName);
		buffer.append(",portName:").append(portName).append("}");
		return buffer.toString();
	}

//	@Override
	public Port proxy() {
		return PortProxy.proxy(this);
	}
	/**
	 * @return DataType/InterfaceType/DataflowType/SUBSCRIPTION_TYPE以外のプロパティ
	 */
	public List<NameValue> getProperties() {
		return getSynchronizer().getProperties();
	}

	/**
	 * @param name	プロパティ名
	 * @return		プロパティの値
	 */
	public String getProperty(String name) {
		return getSynchronizer().getProperty(name);		
	}

} // PortImpl
