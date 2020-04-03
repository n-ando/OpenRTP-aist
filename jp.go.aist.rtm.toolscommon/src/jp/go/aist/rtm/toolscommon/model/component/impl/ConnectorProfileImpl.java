/**
 * <copyright>
 * </copyright>
 *
 * $Id: ConnectorProfileImpl.java,v 1.1 2008/01/29 05:52:23 yamashita Exp $
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.IPropertyMap;
import jp.go.aist.rtm.toolscommon.model.component.util.PropertyMap;
import jp.go.aist.rtm.toolscommon.model.core.impl.WrapperObjectImpl;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Connector Profile</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getDataflowType <em>Dataflow Type</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getSubscriptionType <em>Subscription Type</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#isSubscriptionTypeAvailable <em>Subscription Type Available</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#isPushIntervalAvailable <em>Push Interval Available</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getName <em>Name</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getConnectorId <em>Connector Id</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getDataType <em>Data Type</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getInterfaceType <em>Interface Type</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getPushRate <em>Push Rate</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getPushPolicy <em>Push Policy</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getSkipCount <em>Skip Count</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#isPushPolicyAvailable <em>Push Policy Available</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#isSkipCountAvailable <em>Skip Count Available</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getSourceString <em>Source String</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getTargetString <em>Target String</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getOutportBufferLength <em>Outport Buffer Length</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getOutportBufferFullPolicy <em>Outport Buffer Full Policy</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getOutportBufferWriteTimeout <em>Outport Buffer Write Timeout</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getOutportBufferEmptyPolicy <em>Outport Buffer Empty Policy</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getOutportBufferReadTimeout <em>Outport Buffer Read Timeout</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getInportBufferLength <em>Inport Buffer Length</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getInportBufferFullPolicy <em>Inport Buffer Full Policy</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getInportBufferWriteTimeout <em>Inport Buffer Write Timeout</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getInportBufferEmptyPolicy <em>Inport Buffer Empty Policy</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getInportBufferReadTimeout <em>Inport Buffer Read Timeout</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getTimestampPolicy <em>Timestamp Policy</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#isIsReverse <em>Is Reverse</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getOutportSerializerType <em>Outport Serializer Type</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getInportSerializerType <em>Inport Serializer Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConnectorProfileImpl extends WrapperObjectImpl implements
		ConnectorProfile {
	/**
	 * The default value of the '{@link #getDataflowType() <em>Dataflow Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	 * The default value of the '{@link #isSubscriptionTypeAvailable() <em>Subscription Type Available</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isSubscriptionTypeAvailable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SUBSCRIPTION_TYPE_AVAILABLE_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isPushIntervalAvailable() <em>Push Interval Available</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isPushIntervalAvailable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PUSH_INTERVAL_AVAILABLE_EDEFAULT = false;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getConnectorId() <em>Connector Id</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getConnectorId()
	 * @generated
	 * @ordered
	 */
	protected static final String CONNECTOR_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConnectorId() <em>Connector Id</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getConnectorId()
	 * @generated
	 * @ordered
	 */
	protected String connectorId = CONNECTOR_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getDataType() <em>Data Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	 * The default value of the '{@link #getPushRate() <em>Push Rate</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPushRate()
	 * @generated
	 * @ordered
	 */
	protected static final Double PUSH_RATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPushRate() <em>Push Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPushRate()
	 * @generated
	 * @ordered
	 */
	protected Double pushRate = PUSH_RATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPushPolicy() <em>Push Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPushPolicy()
	 * @generated
	 * @ordered
	 */
	protected static final String PUSH_POLICY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPushPolicy() <em>Push Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPushPolicy()
	 * @generated
	 * @ordered
	 */
	protected String pushPolicy = PUSH_POLICY_EDEFAULT;

	/**
	 * The default value of the '{@link #getSkipCount() <em>Skip Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSkipCount()
	 * @generated
	 * @ordered
	 */
	protected static final Integer SKIP_COUNT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSkipCount() <em>Skip Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSkipCount()
	 * @generated
	 * @ordered
	 */
	protected Integer skipCount = SKIP_COUNT_EDEFAULT;

	/**
	 * The default value of the '{@link #isPushPolicyAvailable() <em>Push Policy Available</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPushPolicyAvailable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PUSH_POLICY_AVAILABLE_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isSkipCountAvailable() <em>Skip Count Available</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSkipCountAvailable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SKIP_COUNT_AVAILABLE_EDEFAULT = false;

	/**
	 * The default value of the '{@link #getSourceString() <em>Source String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceString()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_STRING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSourceString() <em>Source String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceString()
	 * @generated
	 * @ordered
	 */
	protected String sourceString = SOURCE_STRING_EDEFAULT;

	/**
	 * The default value of the '{@link #getTargetString() <em>Target String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetString()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_STRING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTargetString() <em>Target String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetString()
	 * @generated
	 * @ordered
	 */
	protected String targetString = TARGET_STRING_EDEFAULT;

	/**
	 * The default value of the '{@link #getOutportBufferLength() <em>Outport Buffer Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutportBufferLength()
	 * @generated
	 * @ordered
	 */
	protected static final Integer OUTPORT_BUFFER_LENGTH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOutportBufferLength() <em>Outport Buffer Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutportBufferLength()
	 * @generated
	 * @ordered
	 */
	protected Integer outportBufferLength = OUTPORT_BUFFER_LENGTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getOutportBufferFullPolicy() <em>Outport Buffer Full Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutportBufferFullPolicy()
	 * @generated
	 * @ordered
	 */
	protected static final String OUTPORT_BUFFER_FULL_POLICY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOutportBufferFullPolicy() <em>Outport Buffer Full Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutportBufferFullPolicy()
	 * @generated
	 * @ordered
	 */
	protected String outportBufferFullPolicy = OUTPORT_BUFFER_FULL_POLICY_EDEFAULT;

	/**
	 * The default value of the '{@link #getOutportBufferWriteTimeout() <em>Outport Buffer Write Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutportBufferWriteTimeout()
	 * @generated
	 * @ordered
	 */
	protected static final Double OUTPORT_BUFFER_WRITE_TIMEOUT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOutportBufferWriteTimeout() <em>Outport Buffer Write Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutportBufferWriteTimeout()
	 * @generated
	 * @ordered
	 */
	protected Double outportBufferWriteTimeout = OUTPORT_BUFFER_WRITE_TIMEOUT_EDEFAULT;

	/**
	 * The default value of the '{@link #getOutportBufferEmptyPolicy() <em>Outport Buffer Empty Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutportBufferEmptyPolicy()
	 * @generated
	 * @ordered
	 */
	protected static final String OUTPORT_BUFFER_EMPTY_POLICY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOutportBufferEmptyPolicy() <em>Outport Buffer Empty Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutportBufferEmptyPolicy()
	 * @generated
	 * @ordered
	 */
	protected String outportBufferEmptyPolicy = OUTPORT_BUFFER_EMPTY_POLICY_EDEFAULT;

	/**
	 * The default value of the '{@link #getOutportBufferReadTimeout() <em>Outport Buffer Read Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutportBufferReadTimeout()
	 * @generated
	 * @ordered
	 */
	protected static final Double OUTPORT_BUFFER_READ_TIMEOUT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOutportBufferReadTimeout() <em>Outport Buffer Read Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutportBufferReadTimeout()
	 * @generated
	 * @ordered
	 */
	protected Double outportBufferReadTimeout = OUTPORT_BUFFER_READ_TIMEOUT_EDEFAULT;

	/**
	 * The default value of the '{@link #getInportBufferLength() <em>Inport Buffer Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInportBufferLength()
	 * @generated
	 * @ordered
	 */
	protected static final Integer INPORT_BUFFER_LENGTH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInportBufferLength() <em>Inport Buffer Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInportBufferLength()
	 * @generated
	 * @ordered
	 */
	protected Integer inportBufferLength = INPORT_BUFFER_LENGTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getInportBufferFullPolicy() <em>Inport Buffer Full Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInportBufferFullPolicy()
	 * @generated
	 * @ordered
	 */
	protected static final String INPORT_BUFFER_FULL_POLICY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInportBufferFullPolicy() <em>Inport Buffer Full Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInportBufferFullPolicy()
	 * @generated
	 * @ordered
	 */
	protected String inportBufferFullPolicy = INPORT_BUFFER_FULL_POLICY_EDEFAULT;

	/**
	 * The default value of the '{@link #getInportBufferWriteTimeout() <em>Inport Buffer Write Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInportBufferWriteTimeout()
	 * @generated
	 * @ordered
	 */
	protected static final Double INPORT_BUFFER_WRITE_TIMEOUT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInportBufferWriteTimeout() <em>Inport Buffer Write Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInportBufferWriteTimeout()
	 * @generated
	 * @ordered
	 */
	protected Double inportBufferWriteTimeout = INPORT_BUFFER_WRITE_TIMEOUT_EDEFAULT;

	/**
	 * The default value of the '{@link #getInportBufferEmptyPolicy() <em>Inport Buffer Empty Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInportBufferEmptyPolicy()
	 * @generated
	 * @ordered
	 */
	protected static final String INPORT_BUFFER_EMPTY_POLICY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInportBufferEmptyPolicy() <em>Inport Buffer Empty Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInportBufferEmptyPolicy()
	 * @generated
	 * @ordered
	 */
	protected String inportBufferEmptyPolicy = INPORT_BUFFER_EMPTY_POLICY_EDEFAULT;

	/**
	 * The default value of the '{@link #getInportBufferReadTimeout() <em>Inport Buffer Read Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInportBufferReadTimeout()
	 * @generated
	 * @ordered
	 */
	protected static final Double INPORT_BUFFER_READ_TIMEOUT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInportBufferReadTimeout() <em>Inport Buffer Read Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInportBufferReadTimeout()
	 * @generated
	 * @ordered
	 */
	protected Double inportBufferReadTimeout = INPORT_BUFFER_READ_TIMEOUT_EDEFAULT;

	/**
	 * The default value of the '{@link #getTimestampPolicy() <em>Timestamp Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimestampPolicy()
	 * @generated
	 * @ordered
	 */
	protected static final String TIMESTAMP_POLICY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTimestampPolicy() <em>Timestamp Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimestampPolicy()
	 * @generated
	 * @ordered
	 */
	protected String timestampPolicy = TIMESTAMP_POLICY_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsReverse() <em>Is Reverse</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsReverse()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_REVERSE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsReverse() <em>Is Reverse</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsReverse()
	 * @generated
	 * @ordered
	 */
	protected boolean isReverse = IS_REVERSE_EDEFAULT;

	/**
	 * The default value of the '{@link #getOutportSerializerType() <em>Outport Serializer Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutportSerializerType()
	 * @generated
	 * @ordered
	 */
	protected static final String OUTPORT_SERIALIZER_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOutportSerializerType() <em>Outport Serializer Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutportSerializerType()
	 * @generated
	 * @ordered
	 */
	protected String outportSerializerType = OUTPORT_SERIALIZER_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getInportSerializerType() <em>Inport Serializer Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInportSerializerType()
	 * @generated
	 * @ordered
	 */
	protected static final String INPORT_SERIALIZER_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInportSerializerType() <em>Inport Serializer Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInportSerializerType()
	 * @generated
	 * @ordered
	 */
	protected String inportSerializerType = INPORT_SERIALIZER_TYPE_EDEFAULT;

	IPropertyMap properties;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public ConnectorProfileImpl() {
		super();
		this.properties = new PropertyMap();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentPackage.Literals.CONNECTOR_PROFILE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDataflowType() {
		return dataflowType;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__DATAFLOW_TYPE, oldDataflowType, dataflowType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSubscriptionType() {
		return subscriptionType;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__SUBSCRIPTION_TYPE, oldSubscriptionType, subscriptionType));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isSubscriptionTypeAvailable() {
		return ConnectorProfile.PUSH.equalsIgnoreCase(getDataflowType());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isPushIntervalAvailable() {
		return isSubscriptionTypeAvailable() && ConnectorProfile.PERIODIC
				.equalsIgnoreCase(getSubscriptionType());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getConnectorId() {
		return connectorId;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnectorId(String newConnectorId) {
		String oldConnectorId = connectorId;
		connectorId = newConnectorId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__CONNECTOR_ID, oldConnectorId, connectorId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDataType() {
		return dataType;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__DATA_TYPE, oldDataType, dataType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInterfaceType() {
		return interfaceType;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__INTERFACE_TYPE, oldInterfaceType, interfaceType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double getPushRate() {
		return pushRate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPushRate(Double newPushRate) {
		Double oldPushRate = pushRate;
		pushRate = newPushRate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__PUSH_RATE, oldPushRate, pushRate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPushPolicy() {
		return pushPolicy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPushPolicy(String newPushPolicy) {
		String oldPushPolicy = pushPolicy;
		pushPolicy = newPushPolicy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__PUSH_POLICY, oldPushPolicy, pushPolicy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getSkipCount() {
		return skipCount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSkipCount(Integer newSkipCount) {
		Integer oldSkipCount = skipCount;
		skipCount = newSkipCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__SKIP_COUNT, oldSkipCount, skipCount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isPushPolicyAvailable() {
		if (!isSubscriptionTypeAvailable()) {
			return false;
		}
		if (ConnectorProfile.NEW.equalsIgnoreCase(getSubscriptionType())
				|| ConnectorProfile.PERIODIC
						.equalsIgnoreCase(getSubscriptionType())) {
			return true;
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isSkipCountAvailable() {
		if (!isPushPolicyAvailable()) {
			return false;
		}
		if (ConnectorProfile.SKIP.equalsIgnoreCase(getPushPolicy())) {
			return true;
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSourceString() {
		return sourceString;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceString(String newSourceString) {
		String oldSourceString = sourceString;
		sourceString = newSourceString;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__SOURCE_STRING, oldSourceString, sourceString));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTargetString() {
		return targetString;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetString(String newTargetString) {
		String oldTargetString = targetString;
		targetString = newTargetString;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__TARGET_STRING, oldTargetString, targetString));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOutportSerializerType() {
		return outportSerializerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutportSerializerType(String newOutportSerializerType) {
		String oldOutportSerializerType = outportSerializerType;
		outportSerializerType = newOutportSerializerType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__OUTPORT_SERIALIZER_TYPE, oldOutportSerializerType, outportSerializerType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getOutportBufferLength() {
		return outportBufferLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutportBufferLength(Integer newOutportBufferLength) {
		Integer oldOutportBufferLength = outportBufferLength;
		outportBufferLength = newOutportBufferLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__OUTPORT_BUFFER_LENGTH, oldOutportBufferLength, outportBufferLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOutportBufferFullPolicy() {
		return outportBufferFullPolicy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutportBufferFullPolicy(String newOutportBufferFullPolicy) {
		String oldOutportBufferFullPolicy = outportBufferFullPolicy;
		outportBufferFullPolicy = newOutportBufferFullPolicy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__OUTPORT_BUFFER_FULL_POLICY, oldOutportBufferFullPolicy, outportBufferFullPolicy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double getOutportBufferWriteTimeout() {
		return outportBufferWriteTimeout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutportBufferWriteTimeout(Double newOutportBufferWriteTimeout) {
		Double oldOutportBufferWriteTimeout = outportBufferWriteTimeout;
		outportBufferWriteTimeout = newOutportBufferWriteTimeout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__OUTPORT_BUFFER_WRITE_TIMEOUT, oldOutportBufferWriteTimeout, outportBufferWriteTimeout));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOutportBufferEmptyPolicy() {
		return outportBufferEmptyPolicy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutportBufferEmptyPolicy(String newOutportBufferEmptyPolicy) {
		String oldOutportBufferEmptyPolicy = outportBufferEmptyPolicy;
		outportBufferEmptyPolicy = newOutportBufferEmptyPolicy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__OUTPORT_BUFFER_EMPTY_POLICY, oldOutportBufferEmptyPolicy, outportBufferEmptyPolicy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double getOutportBufferReadTimeout() {
		return outportBufferReadTimeout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutportBufferReadTimeout(Double newOutportBufferReadTimeout) {
		Double oldOutportBufferReadTimeout = outportBufferReadTimeout;
		outportBufferReadTimeout = newOutportBufferReadTimeout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__OUTPORT_BUFFER_READ_TIMEOUT, oldOutportBufferReadTimeout, outportBufferReadTimeout));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getInportBufferLength() {
		return inportBufferLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInportBufferLength(Integer newInportBufferLength) {
		Integer oldInportBufferLength = inportBufferLength;
		inportBufferLength = newInportBufferLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__INPORT_BUFFER_LENGTH, oldInportBufferLength, inportBufferLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInportBufferFullPolicy() {
		return inportBufferFullPolicy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInportBufferFullPolicy(String newInportBufferFullPolicy) {
		String oldInportBufferFullPolicy = inportBufferFullPolicy;
		inportBufferFullPolicy = newInportBufferFullPolicy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__INPORT_BUFFER_FULL_POLICY, oldInportBufferFullPolicy, inportBufferFullPolicy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double getInportBufferWriteTimeout() {
		return inportBufferWriteTimeout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInportBufferWriteTimeout(Double newInportBufferWriteTimeout) {
		Double oldInportBufferWriteTimeout = inportBufferWriteTimeout;
		inportBufferWriteTimeout = newInportBufferWriteTimeout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__INPORT_BUFFER_WRITE_TIMEOUT, oldInportBufferWriteTimeout, inportBufferWriteTimeout));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInportBufferEmptyPolicy() {
		return inportBufferEmptyPolicy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInportBufferEmptyPolicy(String newInportBufferEmptyPolicy) {
		String oldInportBufferEmptyPolicy = inportBufferEmptyPolicy;
		inportBufferEmptyPolicy = newInportBufferEmptyPolicy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__INPORT_BUFFER_EMPTY_POLICY, oldInportBufferEmptyPolicy, inportBufferEmptyPolicy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double getInportBufferReadTimeout() {
		return inportBufferReadTimeout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInportBufferReadTimeout(Double newInportBufferReadTimeout) {
		Double oldInportBufferReadTimeout = inportBufferReadTimeout;
		inportBufferReadTimeout = newInportBufferReadTimeout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__INPORT_BUFFER_READ_TIMEOUT, oldInportBufferReadTimeout, inportBufferReadTimeout));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInportSerializerType() {
		return inportSerializerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInportSerializerType(String newInportSerializerType) {
		String oldInportSerializerType = inportSerializerType;
		inportSerializerType = newInportSerializerType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__INPORT_SERIALIZER_TYPE, oldInportSerializerType, inportSerializerType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTimestampPolicy() {
		return timestampPolicy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimestampPolicy(String newTimestampPolicy) {
		String oldTimestampPolicy = timestampPolicy;
		timestampPolicy = newTimestampPolicy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__TIMESTAMP_POLICY, oldTimestampPolicy, timestampPolicy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsReverse() {
		return isReverse;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsReverse(boolean newIsReverse) {
		boolean oldIsReverse = isReverse;
		isReverse = newIsReverse;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__IS_REVERSE, oldIsReverse, isReverse));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setProperty(String key, String value) {
		properties.setProperty(key, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String removeProperty(String key) {
		return properties.removeProperty(key);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<String> getPropertyKeys() {
		return properties.getPropertyKeys();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public IPropertyMap getPropertyMap() {
		return properties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ComponentPackage.CONNECTOR_PROFILE__DATAFLOW_TYPE:
				return getDataflowType();
			case ComponentPackage.CONNECTOR_PROFILE__SUBSCRIPTION_TYPE:
				return getSubscriptionType();
			case ComponentPackage.CONNECTOR_PROFILE__SUBSCRIPTION_TYPE_AVAILABLE:
				return isSubscriptionTypeAvailable();
			case ComponentPackage.CONNECTOR_PROFILE__PUSH_INTERVAL_AVAILABLE:
				return isPushIntervalAvailable();
			case ComponentPackage.CONNECTOR_PROFILE__NAME:
				return getName();
			case ComponentPackage.CONNECTOR_PROFILE__CONNECTOR_ID:
				return getConnectorId();
			case ComponentPackage.CONNECTOR_PROFILE__DATA_TYPE:
				return getDataType();
			case ComponentPackage.CONNECTOR_PROFILE__INTERFACE_TYPE:
				return getInterfaceType();
			case ComponentPackage.CONNECTOR_PROFILE__PUSH_RATE:
				return getPushRate();
			case ComponentPackage.CONNECTOR_PROFILE__PUSH_POLICY:
				return getPushPolicy();
			case ComponentPackage.CONNECTOR_PROFILE__SKIP_COUNT:
				return getSkipCount();
			case ComponentPackage.CONNECTOR_PROFILE__PUSH_POLICY_AVAILABLE:
				return isPushPolicyAvailable();
			case ComponentPackage.CONNECTOR_PROFILE__SKIP_COUNT_AVAILABLE:
				return isSkipCountAvailable();
			case ComponentPackage.CONNECTOR_PROFILE__SOURCE_STRING:
				return getSourceString();
			case ComponentPackage.CONNECTOR_PROFILE__TARGET_STRING:
				return getTargetString();
			case ComponentPackage.CONNECTOR_PROFILE__OUTPORT_BUFFER_LENGTH:
				return getOutportBufferLength();
			case ComponentPackage.CONNECTOR_PROFILE__OUTPORT_BUFFER_FULL_POLICY:
				return getOutportBufferFullPolicy();
			case ComponentPackage.CONNECTOR_PROFILE__OUTPORT_BUFFER_WRITE_TIMEOUT:
				return getOutportBufferWriteTimeout();
			case ComponentPackage.CONNECTOR_PROFILE__OUTPORT_BUFFER_EMPTY_POLICY:
				return getOutportBufferEmptyPolicy();
			case ComponentPackage.CONNECTOR_PROFILE__OUTPORT_BUFFER_READ_TIMEOUT:
				return getOutportBufferReadTimeout();
			case ComponentPackage.CONNECTOR_PROFILE__INPORT_BUFFER_LENGTH:
				return getInportBufferLength();
			case ComponentPackage.CONNECTOR_PROFILE__INPORT_BUFFER_FULL_POLICY:
				return getInportBufferFullPolicy();
			case ComponentPackage.CONNECTOR_PROFILE__INPORT_BUFFER_WRITE_TIMEOUT:
				return getInportBufferWriteTimeout();
			case ComponentPackage.CONNECTOR_PROFILE__INPORT_BUFFER_EMPTY_POLICY:
				return getInportBufferEmptyPolicy();
			case ComponentPackage.CONNECTOR_PROFILE__INPORT_BUFFER_READ_TIMEOUT:
				return getInportBufferReadTimeout();
			case ComponentPackage.CONNECTOR_PROFILE__TIMESTAMP_POLICY:
				return getTimestampPolicy();
			case ComponentPackage.CONNECTOR_PROFILE__IS_REVERSE:
				return isIsReverse();
			case ComponentPackage.CONNECTOR_PROFILE__OUTPORT_SERIALIZER_TYPE:
				return getOutportSerializerType();
			case ComponentPackage.CONNECTOR_PROFILE__INPORT_SERIALIZER_TYPE:
				return getInportSerializerType();
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
			case ComponentPackage.CONNECTOR_PROFILE__DATAFLOW_TYPE:
				setDataflowType((String)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__SUBSCRIPTION_TYPE:
				setSubscriptionType((String)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__NAME:
				setName((String)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__CONNECTOR_ID:
				setConnectorId((String)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__DATA_TYPE:
				setDataType((String)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__INTERFACE_TYPE:
				setInterfaceType((String)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__PUSH_RATE:
				setPushRate((Double)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__PUSH_POLICY:
				setPushPolicy((String)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__SKIP_COUNT:
				setSkipCount((Integer)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__SOURCE_STRING:
				setSourceString((String)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__TARGET_STRING:
				setTargetString((String)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__OUTPORT_BUFFER_LENGTH:
				setOutportBufferLength((Integer)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__OUTPORT_BUFFER_FULL_POLICY:
				setOutportBufferFullPolicy((String)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__OUTPORT_BUFFER_WRITE_TIMEOUT:
				setOutportBufferWriteTimeout((Double)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__OUTPORT_BUFFER_EMPTY_POLICY:
				setOutportBufferEmptyPolicy((String)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__OUTPORT_BUFFER_READ_TIMEOUT:
				setOutportBufferReadTimeout((Double)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__INPORT_BUFFER_LENGTH:
				setInportBufferLength((Integer)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__INPORT_BUFFER_FULL_POLICY:
				setInportBufferFullPolicy((String)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__INPORT_BUFFER_WRITE_TIMEOUT:
				setInportBufferWriteTimeout((Double)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__INPORT_BUFFER_EMPTY_POLICY:
				setInportBufferEmptyPolicy((String)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__INPORT_BUFFER_READ_TIMEOUT:
				setInportBufferReadTimeout((Double)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__TIMESTAMP_POLICY:
				setTimestampPolicy((String)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__IS_REVERSE:
				setIsReverse((Boolean)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__OUTPORT_SERIALIZER_TYPE:
				setOutportSerializerType((String)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__INPORT_SERIALIZER_TYPE:
				setInportSerializerType((String)newValue);
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
			case ComponentPackage.CONNECTOR_PROFILE__DATAFLOW_TYPE:
				setDataflowType(DATAFLOW_TYPE_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__SUBSCRIPTION_TYPE:
				setSubscriptionType(SUBSCRIPTION_TYPE_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__CONNECTOR_ID:
				setConnectorId(CONNECTOR_ID_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__DATA_TYPE:
				setDataType(DATA_TYPE_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__INTERFACE_TYPE:
				setInterfaceType(INTERFACE_TYPE_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__PUSH_RATE:
				setPushRate(PUSH_RATE_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__PUSH_POLICY:
				setPushPolicy(PUSH_POLICY_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__SKIP_COUNT:
				setSkipCount(SKIP_COUNT_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__SOURCE_STRING:
				setSourceString(SOURCE_STRING_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__TARGET_STRING:
				setTargetString(TARGET_STRING_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__OUTPORT_BUFFER_LENGTH:
				setOutportBufferLength(OUTPORT_BUFFER_LENGTH_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__OUTPORT_BUFFER_FULL_POLICY:
				setOutportBufferFullPolicy(OUTPORT_BUFFER_FULL_POLICY_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__OUTPORT_BUFFER_WRITE_TIMEOUT:
				setOutportBufferWriteTimeout(OUTPORT_BUFFER_WRITE_TIMEOUT_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__OUTPORT_BUFFER_EMPTY_POLICY:
				setOutportBufferEmptyPolicy(OUTPORT_BUFFER_EMPTY_POLICY_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__OUTPORT_BUFFER_READ_TIMEOUT:
				setOutportBufferReadTimeout(OUTPORT_BUFFER_READ_TIMEOUT_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__INPORT_BUFFER_LENGTH:
				setInportBufferLength(INPORT_BUFFER_LENGTH_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__INPORT_BUFFER_FULL_POLICY:
				setInportBufferFullPolicy(INPORT_BUFFER_FULL_POLICY_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__INPORT_BUFFER_WRITE_TIMEOUT:
				setInportBufferWriteTimeout(INPORT_BUFFER_WRITE_TIMEOUT_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__INPORT_BUFFER_EMPTY_POLICY:
				setInportBufferEmptyPolicy(INPORT_BUFFER_EMPTY_POLICY_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__INPORT_BUFFER_READ_TIMEOUT:
				setInportBufferReadTimeout(INPORT_BUFFER_READ_TIMEOUT_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__TIMESTAMP_POLICY:
				setTimestampPolicy(TIMESTAMP_POLICY_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__IS_REVERSE:
				setIsReverse(IS_REVERSE_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__OUTPORT_SERIALIZER_TYPE:
				setOutportSerializerType(OUTPORT_SERIALIZER_TYPE_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__INPORT_SERIALIZER_TYPE:
				setInportSerializerType(INPORT_SERIALIZER_TYPE_EDEFAULT);
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
			case ComponentPackage.CONNECTOR_PROFILE__DATAFLOW_TYPE:
				return DATAFLOW_TYPE_EDEFAULT == null ? dataflowType != null : !DATAFLOW_TYPE_EDEFAULT.equals(dataflowType);
			case ComponentPackage.CONNECTOR_PROFILE__SUBSCRIPTION_TYPE:
				return SUBSCRIPTION_TYPE_EDEFAULT == null ? subscriptionType != null : !SUBSCRIPTION_TYPE_EDEFAULT.equals(subscriptionType);
			case ComponentPackage.CONNECTOR_PROFILE__SUBSCRIPTION_TYPE_AVAILABLE:
				return isSubscriptionTypeAvailable() != SUBSCRIPTION_TYPE_AVAILABLE_EDEFAULT;
			case ComponentPackage.CONNECTOR_PROFILE__PUSH_INTERVAL_AVAILABLE:
				return isPushIntervalAvailable() != PUSH_INTERVAL_AVAILABLE_EDEFAULT;
			case ComponentPackage.CONNECTOR_PROFILE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ComponentPackage.CONNECTOR_PROFILE__CONNECTOR_ID:
				return CONNECTOR_ID_EDEFAULT == null ? connectorId != null : !CONNECTOR_ID_EDEFAULT.equals(connectorId);
			case ComponentPackage.CONNECTOR_PROFILE__DATA_TYPE:
				return DATA_TYPE_EDEFAULT == null ? dataType != null : !DATA_TYPE_EDEFAULT.equals(dataType);
			case ComponentPackage.CONNECTOR_PROFILE__INTERFACE_TYPE:
				return INTERFACE_TYPE_EDEFAULT == null ? interfaceType != null : !INTERFACE_TYPE_EDEFAULT.equals(interfaceType);
			case ComponentPackage.CONNECTOR_PROFILE__PUSH_RATE:
				return PUSH_RATE_EDEFAULT == null ? pushRate != null : !PUSH_RATE_EDEFAULT.equals(pushRate);
			case ComponentPackage.CONNECTOR_PROFILE__PUSH_POLICY:
				return PUSH_POLICY_EDEFAULT == null ? pushPolicy != null : !PUSH_POLICY_EDEFAULT.equals(pushPolicy);
			case ComponentPackage.CONNECTOR_PROFILE__SKIP_COUNT:
				return SKIP_COUNT_EDEFAULT == null ? skipCount != null : !SKIP_COUNT_EDEFAULT.equals(skipCount);
			case ComponentPackage.CONNECTOR_PROFILE__PUSH_POLICY_AVAILABLE:
				return isPushPolicyAvailable() != PUSH_POLICY_AVAILABLE_EDEFAULT;
			case ComponentPackage.CONNECTOR_PROFILE__SKIP_COUNT_AVAILABLE:
				return isSkipCountAvailable() != SKIP_COUNT_AVAILABLE_EDEFAULT;
			case ComponentPackage.CONNECTOR_PROFILE__SOURCE_STRING:
				return SOURCE_STRING_EDEFAULT == null ? sourceString != null : !SOURCE_STRING_EDEFAULT.equals(sourceString);
			case ComponentPackage.CONNECTOR_PROFILE__TARGET_STRING:
				return TARGET_STRING_EDEFAULT == null ? targetString != null : !TARGET_STRING_EDEFAULT.equals(targetString);
			case ComponentPackage.CONNECTOR_PROFILE__OUTPORT_BUFFER_LENGTH:
				return OUTPORT_BUFFER_LENGTH_EDEFAULT == null ? outportBufferLength != null : !OUTPORT_BUFFER_LENGTH_EDEFAULT.equals(outportBufferLength);
			case ComponentPackage.CONNECTOR_PROFILE__OUTPORT_BUFFER_FULL_POLICY:
				return OUTPORT_BUFFER_FULL_POLICY_EDEFAULT == null ? outportBufferFullPolicy != null : !OUTPORT_BUFFER_FULL_POLICY_EDEFAULT.equals(outportBufferFullPolicy);
			case ComponentPackage.CONNECTOR_PROFILE__OUTPORT_BUFFER_WRITE_TIMEOUT:
				return OUTPORT_BUFFER_WRITE_TIMEOUT_EDEFAULT == null ? outportBufferWriteTimeout != null : !OUTPORT_BUFFER_WRITE_TIMEOUT_EDEFAULT.equals(outportBufferWriteTimeout);
			case ComponentPackage.CONNECTOR_PROFILE__OUTPORT_BUFFER_EMPTY_POLICY:
				return OUTPORT_BUFFER_EMPTY_POLICY_EDEFAULT == null ? outportBufferEmptyPolicy != null : !OUTPORT_BUFFER_EMPTY_POLICY_EDEFAULT.equals(outportBufferEmptyPolicy);
			case ComponentPackage.CONNECTOR_PROFILE__OUTPORT_BUFFER_READ_TIMEOUT:
				return OUTPORT_BUFFER_READ_TIMEOUT_EDEFAULT == null ? outportBufferReadTimeout != null : !OUTPORT_BUFFER_READ_TIMEOUT_EDEFAULT.equals(outportBufferReadTimeout);
			case ComponentPackage.CONNECTOR_PROFILE__INPORT_BUFFER_LENGTH:
				return INPORT_BUFFER_LENGTH_EDEFAULT == null ? inportBufferLength != null : !INPORT_BUFFER_LENGTH_EDEFAULT.equals(inportBufferLength);
			case ComponentPackage.CONNECTOR_PROFILE__INPORT_BUFFER_FULL_POLICY:
				return INPORT_BUFFER_FULL_POLICY_EDEFAULT == null ? inportBufferFullPolicy != null : !INPORT_BUFFER_FULL_POLICY_EDEFAULT.equals(inportBufferFullPolicy);
			case ComponentPackage.CONNECTOR_PROFILE__INPORT_BUFFER_WRITE_TIMEOUT:
				return INPORT_BUFFER_WRITE_TIMEOUT_EDEFAULT == null ? inportBufferWriteTimeout != null : !INPORT_BUFFER_WRITE_TIMEOUT_EDEFAULT.equals(inportBufferWriteTimeout);
			case ComponentPackage.CONNECTOR_PROFILE__INPORT_BUFFER_EMPTY_POLICY:
				return INPORT_BUFFER_EMPTY_POLICY_EDEFAULT == null ? inportBufferEmptyPolicy != null : !INPORT_BUFFER_EMPTY_POLICY_EDEFAULT.equals(inportBufferEmptyPolicy);
			case ComponentPackage.CONNECTOR_PROFILE__INPORT_BUFFER_READ_TIMEOUT:
				return INPORT_BUFFER_READ_TIMEOUT_EDEFAULT == null ? inportBufferReadTimeout != null : !INPORT_BUFFER_READ_TIMEOUT_EDEFAULT.equals(inportBufferReadTimeout);
			case ComponentPackage.CONNECTOR_PROFILE__TIMESTAMP_POLICY:
				return TIMESTAMP_POLICY_EDEFAULT == null ? timestampPolicy != null : !TIMESTAMP_POLICY_EDEFAULT.equals(timestampPolicy);
			case ComponentPackage.CONNECTOR_PROFILE__IS_REVERSE:
				return isReverse != IS_REVERSE_EDEFAULT;
			case ComponentPackage.CONNECTOR_PROFILE__OUTPORT_SERIALIZER_TYPE:
				return OUTPORT_SERIALIZER_TYPE_EDEFAULT == null ? outportSerializerType != null : !OUTPORT_SERIALIZER_TYPE_EDEFAULT.equals(outportSerializerType);
			case ComponentPackage.CONNECTOR_PROFILE__INPORT_SERIALIZER_TYPE:
				return INPORT_SERIALIZER_TYPE_EDEFAULT == null ? inportSerializerType != null : !INPORT_SERIALIZER_TYPE_EDEFAULT.equals(inportSerializerType);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (dataflowType: ");
		result.append(dataflowType);
		result.append(", subscriptionType: ");
		result.append(subscriptionType);
		result.append(", name: ");
		result.append(name);
		result.append(", connectorId: ");
		result.append(connectorId);
		result.append(", dataType: ");
		result.append(dataType);
		result.append(", interfaceType: ");
		result.append(interfaceType);
		result.append(", pushRate: ");
		result.append(pushRate);
		result.append(", pushPolicy: ");
		result.append(pushPolicy);
		result.append(", skipCount: ");
		result.append(skipCount);
		result.append(", sourceString: ");
		result.append(sourceString);
		result.append(", targetString: ");
		result.append(targetString);
		result.append(", outportBufferLength: ");
		result.append(outportBufferLength);
		result.append(", outportBufferFullPolicy: ");
		result.append(outportBufferFullPolicy);
		result.append(", outportBufferWriteTimeout: ");
		result.append(outportBufferWriteTimeout);
		result.append(", outportBufferEmptyPolicy: ");
		result.append(outportBufferEmptyPolicy);
		result.append(", outportBufferReadTimeout: ");
		result.append(outportBufferReadTimeout);
		result.append(", inportBufferLength: ");
		result.append(inportBufferLength);
		result.append(", inportBufferFullPolicy: ");
		result.append(inportBufferFullPolicy);
		result.append(", inportBufferWriteTimeout: ");
		result.append(inportBufferWriteTimeout);
		result.append(", inportBufferEmptyPolicy: ");
		result.append(inportBufferEmptyPolicy);
		result.append(", inportBufferReadTimeout: ");
		result.append(inportBufferReadTimeout);
		result.append(", timestampPolicy: ");
		result.append(timestampPolicy);
		result.append(", isReverse: ");
		result.append(isReverse);
		result.append(", outportSerializerType: ");
		result.append(outportSerializerType);
		result.append(", inportSerializerType: ");
		result.append(inportSerializerType);
		result.append(')');
		return result.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ConnectorProfile == false) {
			return false;
		}

		ConnectorProfile p = (ConnectorProfile) obj;

		return new EqualsBuilder().append(getConnectorId(), p.getConnectorId()).isEquals();
	}

} // ConnectorProfileImpl
