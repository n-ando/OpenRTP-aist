/**
 * <copyright>
 * </copyright>
 *
 * $Id: ComponentSpecification.java,v 1.2 2008/03/05 12:01:47 terui Exp $
 */
package jp.go.aist.rtm.toolscommon.model.component;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification#getAliasName <em>Alias Name</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification#isSpecUnLoad <em>Spec Un Load</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification#getRtcType <em>Rtc Type</em>}</li>
 * </ul>
 *
 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponentSpecification()
 * @model
 * @generated
 */
public interface ComponentSpecification extends Component {
	/**
	 * Returns the value of the '<em><b>Alias Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Alias Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Alias Name</em>' attribute.
	 * @see #setAliasName(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponentSpecification_AliasName()
	 * @model
	 * @generated
	 */
	String getAliasName();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification#getAliasName <em>Alias Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Alias Name</em>' attribute.
	 * @see #getAliasName()
	 * @generated
	 */
	void setAliasName(String value);

	/**
	 * Returns the value of the '<em><b>Spec Un Load</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Spec Un Load</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Spec Un Load</em>' attribute.
	 * @see #setSpecUnLoad(boolean)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponentSpecification_SpecUnLoad()
	 * @model default="false"
	 * @generated
	 */
	boolean isSpecUnLoad();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification#isSpecUnLoad <em>Spec Un Load</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Spec Un Load</em>' attribute.
	 * @see #isSpecUnLoad()
	 * @generated
	 */
	void setSpecUnLoad(boolean value);

	/**
	 * Returns the value of the '<em><b>Rtc Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rtc Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rtc Type</em>' attribute.
	 * @see #setRtcType(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponentSpecification_RtcType()
	 * @model
	 * @generated
	 */
	String getRtcType();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification#getRtcType <em>Rtc Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rtc Type</em>' attribute.
	 * @see #getRtcType()
	 * @generated
	 */
	void setRtcType(String value);

} // ComponentSpecification