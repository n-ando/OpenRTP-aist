/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Corba Connector Profile</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.CorbaConnectorProfile#getRtcConnectorProfile <em>Rtc Connector Profile</em>}</li>
 * </ul>
 *
 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getCorbaConnectorProfile()
 * @model
 * @generated
 */
public interface CorbaConnectorProfile extends ConnectorProfile {
	/**
	 * Returns the value of the '<em><b>Rtc Connector Profile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rtc Connector Profile</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rtc Connector Profile</em>' attribute.
	 * @see #setRtcConnectorProfile(RTC.ConnectorProfile)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getCorbaConnectorProfile_RtcConnectorProfile()
	 * @model dataType="jp.go.aist.rtm.toolscommon.model.component.RTCConnectorProfile" transient="true"
	 * @generated
	 */
	RTC.ConnectorProfile getRtcConnectorProfile();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaConnectorProfile#getRtcConnectorProfile <em>Rtc Connector Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rtc Connector Profile</em>' attribute.
	 * @see #getRtcConnectorProfile()
	 * @generated
	 */
	void setRtcConnectorProfile(RTC.ConnectorProfile value);

} // CorbaConnectorProfile
