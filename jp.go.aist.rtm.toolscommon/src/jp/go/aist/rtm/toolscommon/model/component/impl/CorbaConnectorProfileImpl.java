/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.CorbaConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.IPropertyMap;
import jp.go.aist.rtm.toolscommon.model.component.util.CorbaPropertyMap;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.AttributeMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ClassMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ConstructorParamMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.MappingRule;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ReferenceMapping;
import jp.go.aist.rtm.toolscommon.util.SDOUtil;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import RTC.ConnectorProfile;
import _SDOPackage.NameValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Corba Connector Profile</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaConnectorProfileImpl#getRtcConnectorProfile <em>Rtc Connector Profile</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CorbaConnectorProfileImpl extends ConnectorProfileImpl implements CorbaConnectorProfile {

	/**
	 * The default value of the '{@link #getRtcConnectorProfile() <em>Rtc Connector Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRtcConnectorProfile()
	 * @generated
	 * @ordered
	 */
	protected static final ConnectorProfile RTC_CONNECTOR_PROFILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRtcConnectorProfile() <em>Rtc Connector Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRtcConnectorProfile()
	 * @generated
	 * @ordered
	 */
	protected ConnectorProfile rtcConnectorProfile = RTC_CONNECTOR_PROFILE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public CorbaConnectorProfileImpl() {
		super();
		this.properties = new CorbaPropertyMap() {
			@Override
			public NameValue[] getNameValues() {
				return getRtcConnectorProfile().properties;
			}

			@Override
			public void setNameValues(NameValue[] nvs) {
				getRtcConnectorProfile().properties = nvs;
			}
		};
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentPackage.Literals.CORBA_CONNECTOR_PROFILE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectorProfile getRtcConnectorProfile() {
		return rtcConnectorProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRtcConnectorProfile(ConnectorProfile newRtcConnectorProfile) {
		ConnectorProfile oldRtcConnectorProfile = rtcConnectorProfile;
		rtcConnectorProfile = newRtcConnectorProfile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CORBA_CONNECTOR_PROFILE__RTC_CONNECTOR_PROFILE, oldRtcConnectorProfile, rtcConnectorProfile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ComponentPackage.CORBA_CONNECTOR_PROFILE__RTC_CONNECTOR_PROFILE:
				return getRtcConnectorProfile();
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
			case ComponentPackage.CORBA_CONNECTOR_PROFILE__RTC_CONNECTOR_PROFILE:
				setRtcConnectorProfile((ConnectorProfile)newValue);
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
			case ComponentPackage.CORBA_CONNECTOR_PROFILE__RTC_CONNECTOR_PROFILE:
				setRtcConnectorProfile(RTC_CONNECTOR_PROFILE_EDEFAULT);
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
			case ComponentPackage.CORBA_CONNECTOR_PROFILE__RTC_CONNECTOR_PROFILE:
				return RTC_CONNECTOR_PROFILE_EDEFAULT == null ? rtcConnectorProfile != null : !RTC_CONNECTOR_PROFILE_EDEFAULT.equals(rtcConnectorProfile);
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
		result.append(" (rtcConnectorProfile: ");
		result.append(rtcConnectorProfile);
		result.append(')');
		return result.toString();
	}

	@Override
	public String getConnectorId() {
		return getRtcConnectorProfile() == null ? null
				: getRtcConnectorProfile().connector_id;
	}

	@Override
	public String getName() {
		return getRtcConnectorProfile() == null ? null
				: getRtcConnectorProfile().name;
	}

	@Override
	public void setConnectorId(String newConnectorId) {
		if (getRtcConnectorProfile() == null) setRtcConnectorProfile(new ConnectorProfile());
		getRtcConnectorProfile().connector_id = newConnectorId;
	}

	@Override
	public void setName(String newName) {
		if (getRtcConnectorProfile() == null) setRtcConnectorProfile(new ConnectorProfile());
		getRtcConnectorProfile().name = newName;
	}

	@Override
	public String getSourceString() {
		if (getRtcConnectorProfile() == null) return null;
		if (getRtcConnectorProfile().ports == null) return null;
		if (getRtcConnectorProfile().ports.length < 1) return null;
		if (getRtcConnectorProfile().ports[0] == null) return null;
		return getRtcConnectorProfile().ports[0].toString();
	}

	@Override
	public String getTargetString() {
		if (getRtcConnectorProfile() == null) return null;
		if (getRtcConnectorProfile().ports == null) return null;
		if (getRtcConnectorProfile().ports.length < 2) return null;
		if (getRtcConnectorProfile().ports[1] == null) return null;
		return getRtcConnectorProfile().ports[1].toString();
	}

	public static NameValue[] createProperties(
			jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile profile) {
		List<NameValue> result = new ArrayList<NameValue>();

		addProperty(result, profile.getDataType(), PROP.DATA_TYPE);
		addProperty(result, profile.getInterfaceType(), PROP.INTERFACE_TYPE);
		addProperty(result, profile.getDataflowType(), PROP.DATAFLOW_TYPE);
		addProperty(result, profile.getSubscriptionType(),
				PROP.SUBSCRIPTION_TYPE);
		addProperty(result, profile.getPushRate(), PROP.PUSH_RATE);
		addProperty(result, profile.getPushPolicy(), PROP.PUSH_POLICY);
		addProperty(result, profile.getSkipCount(), PROP.SKIP_COUNT);
		if(profile.getTimestampPolicy()!=null && 0<profile.getTimestampPolicy().length()) {
			addProperty(result, profile.getTimestampPolicy(), PROP.TIMESTAMP_POLICY);
		}
		//
		addProperty(result, profile.getOutportBufferLength(),
				PROP.OUTPORT_BUFF_LENGTH);
		addProperty(result, profile.getOutportBufferFullPolicy(),
				PROP.OUTPORT_FULL_POLICY);
		addProperty(result, profile.getOutportBufferWriteTimeout(),
				PROP.OUTPORT_WRITE_TIMEOUT);
		addProperty(result, profile.getOutportBufferEmptyPolicy(),
				PROP.OUTPORT_EMPTY_POLICY);
		addProperty(result, profile.getOutportBufferReadTimeout(),
				PROP.OUTPORT_READ_TIMEOUT);
		addProperty(result, profile.getOutportSerializerType(),
				PROP.OUTPORT_SERIALIZER_TYPE);
		//
		addProperty(result, profile.getInportBufferLength(),
				PROP.INPORT_BUFF_LENGTH);
		addProperty(result, profile.getInportBufferFullPolicy(),
				PROP.INPORT_FULL_POLICY);
		addProperty(result, profile.getInportBufferWriteTimeout(),
				PROP.INPORT_WRITE_TIMEOUT);
		addProperty(result, profile.getInportBufferEmptyPolicy(),
				PROP.INPORT_EMPTY_POLICY);
		addProperty(result, profile.getInportBufferReadTimeout(),
				PROP.INPORT_READ_TIMEOUT);
		addProperty(result, profile.getInportSerializerType(),
				PROP.INPORT_SERIALIZER_TYPE);

		for (String key : profile.getPropertyKeys()) {
			addProperty(result, profile.getProperty(key), key);
		}

		return result.toArray(new NameValue[0]);
	}

	private static void addProperty(List<NameValue> result, Double value,
			String name) {
		if (value == null)
			return;
		addProperty(result, value.toString(), name);
	}

	private static void addProperty(List<NameValue> result, Integer value,
			String name) {
		if (value == null)
			return;
		addProperty(result, value.toString(), name);
	}

	private static void addProperty(List<NameValue> result, String value,
			String name) {
		if (StringUtils.isBlank(value))
			return;
		result.add(SDOUtil.newNV(name, value));
	}

	@Override
	public String getDataflowType() {
		return getProperty(PROP.DATAFLOW_TYPE);
	}

	@Override
	public void setDataflowType(String newDataflowType) {
		setProperty(PROP.DATAFLOW_TYPE, newDataflowType);
	}

	@Override
	public String getSubscriptionType() {
		return getProperty(PROP.SUBSCRIPTION_TYPE);
	}

	@Override
	public void setSubscriptionType(String newSubscriptionType) {
		setProperty(PROP.SUBSCRIPTION_TYPE, newSubscriptionType);
	}

	@Override
	public String getDataType() {
		return getProperty(PROP.DATA_TYPE);
	}

	@Override
	public void setDataType(String newDataType) {
		setProperty(PROP.DATA_TYPE, newDataType);
	}

	@Override
	public String getInterfaceType() {
		return getProperty(PROP.INTERFACE_TYPE);
	}

	@Override
	public void setInterfaceType(String newInterfaceType) {
		setProperty(PROP.INTERFACE_TYPE, newInterfaceType);
	}

	@Override
	public Double getPushRate() {
		String value = getProperty(PROP.PUSH_RATE);
		try {
			return Double.parseDouble(value);
		} catch (RuntimeException e) {
			return null;
		}
	}

	@Override
	public void setPushRate(Double newPushRate) {
		setProperty(PROP.PUSH_RATE, newPushRate.toString());
	}

	@Override
	public String getPushPolicy() {
		return getProperty(PROP.PUSH_POLICY);
	}

	@Override
	public void setPushPolicy(String newPushPolicy) {
		setProperty(PROP.PUSH_POLICY, newPushPolicy);
	}

	@Override
	public Integer getSkipCount() {
		String value = getProperty(PROP.SKIP_COUNT);
		try {
			return Integer.parseInt(value);
		} catch (RuntimeException e) {
			return null;
		}
	}

	@Override
	public void setSkipCount(Integer newSkipCount) {
		setProperty(PROP.SKIP_COUNT, newSkipCount.toString());
	}
	
	@Override
	public void setTimestampPolicy(String value) {
		setProperty(PROP.TIMESTAMP_POLICY, value);
	}

	@Override
	public String getTimestampPolicy() {
		return getProperty(PROP.TIMESTAMP_POLICY);
	}

	@Override
	public Integer getOutportBufferLength() {
		String value = getProperty(PROP.OUTPORT_BUFF_LENGTH);
		try {
			return Integer.parseInt(value);
		} catch (RuntimeException e) {
			return null;
		}
	}

	@Override
	public void setOutportBufferLength(Integer newOutportBufferLength) {
		setProperty(PROP.OUTPORT_BUFF_LENGTH, newOutportBufferLength.toString());
	}

	@Override
	public String getOutportBufferFullPolicy() {
		return getProperty(PROP.OUTPORT_FULL_POLICY);
	}

	@Override
	public void setOutportBufferFullPolicy(String newOutportBufferFullPolicy) {
		setProperty(PROP.OUTPORT_FULL_POLICY, newOutportBufferFullPolicy);
	}

	@Override
	public Double getOutportBufferWriteTimeout() {
		String value = getProperty(PROP.OUTPORT_WRITE_TIMEOUT);
		try {
			return Double.parseDouble(value);
		} catch (RuntimeException e) {
			return null;
		}
	}

	@Override
	public void setOutportBufferWriteTimeout(Double newOutportBufferWriteTimeout) {
		setProperty(PROP.OUTPORT_WRITE_TIMEOUT, newOutportBufferWriteTimeout
				.toString());
	}

	@Override
	public String getOutportBufferEmptyPolicy() {
		return getProperty(PROP.OUTPORT_EMPTY_POLICY);
	}

	@Override
	public void setOutportBufferEmptyPolicy(String newOutportBufferEmptyPolicy) {
		setProperty(PROP.OUTPORT_EMPTY_POLICY, newOutportBufferEmptyPolicy);
	}

	@Override
	public Double getOutportBufferReadTimeout() {
		String value = getProperty(PROP.OUTPORT_READ_TIMEOUT);
		try {
			return Double.parseDouble(value);
		} catch (RuntimeException e) {
			return null;
		}
	}

	@Override
	public void setOutportBufferReadTimeout(Double newOutportBufferReadTimeout) {
		setProperty(PROP.OUTPORT_READ_TIMEOUT, newOutportBufferReadTimeout
				.toString());
	}

	@Override
	public Integer getInportBufferLength() {
		String value = getProperty(PROP.INPORT_BUFF_LENGTH);
		try {
			return Integer.parseInt(value);
		} catch (RuntimeException e) {
			return null;
		}
	}

	@Override
	public void setInportBufferLength(Integer newInportBufferLength) {
		setProperty(PROP.INPORT_BUFF_LENGTH, newInportBufferLength.toString());
	}

	@Override
	public String getInportBufferFullPolicy() {
		return getProperty(PROP.INPORT_FULL_POLICY);
	}

	@Override
	public void setInportBufferFullPolicy(String newInportBufferFullPolicy) {
		setProperty(PROP.INPORT_FULL_POLICY, newInportBufferFullPolicy);
	}

	@Override
	public Double getInportBufferWriteTimeout() {
		String value = getProperty(PROP.INPORT_WRITE_TIMEOUT);
		try {
			return Double.parseDouble(value);
		} catch (RuntimeException e) {
			return null;
		}
	}

	@Override
	public void setInportBufferWriteTimeout(Double newInportBufferWriteTimeout) {
		setProperty(PROP.INPORT_WRITE_TIMEOUT, newInportBufferWriteTimeout
				.toString());
	}

	@Override
	public String getInportBufferEmptyPolicy() {
		return getProperty(PROP.INPORT_EMPTY_POLICY);
	}

	@Override
	public void setInportBufferEmptyPolicy(String newInportBufferEmptyPolicy) {
		setProperty(PROP.INPORT_EMPTY_POLICY, newInportBufferEmptyPolicy);
	}

	@Override
	public Double getInportBufferReadTimeout() {
		String value = getProperty(PROP.INPORT_READ_TIMEOUT);
		try {
			return Double.parseDouble(value);
		} catch (RuntimeException e) {
			return null;
		}
	}

	@Override
	public void setInportBufferReadTimeout(Double newInportBufferReadTimeout) {
		setProperty(PROP.INPORT_READ_TIMEOUT, newInportBufferReadTimeout
				.toString());
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

	// Mapping Rule
	public static final MappingRule MAPPING_RULE = new MappingRule(
			null,
			new ClassMapping(
					CorbaConnectorProfileImpl.class,
					new ConstructorParamMapping[] { new ConstructorParamMapping(
							ComponentPackage.eINSTANCE
									.getCorbaConnectorProfile_RtcConnectorProfile()) }) {
			}, new AttributeMapping[] {}, new ReferenceMapping[] {});



} //CorbaConnectorProfileImpl
