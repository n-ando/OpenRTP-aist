/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Corba Configuration Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.CorbaConfigurationSet#getSDOConfigurationSet <em>SDO Configuration Set</em>}</li>
 * </ul>
 *
 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getCorbaConfigurationSet()
 * @model
 * @generated
 */
public interface CorbaConfigurationSet extends ConfigurationSet {
	/**
	 * Returns the value of the '<em><b>SDO Configuration Set</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SDO Configuration Set</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>SDO Configuration Set</em>' attribute.
	 * @see #setSDOConfigurationSet(_SDOPackage.ConfigurationSet)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getCorbaConfigurationSet_SDOConfigurationSet()
	 * @model dataType="jp.go.aist.rtm.toolscommon.model.component.SDOConfigurationSet" transient="true"
	 * @generated
	 */
	_SDOPackage.ConfigurationSet getSDOConfigurationSet();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaConfigurationSet#getSDOConfigurationSet <em>SDO Configuration Set</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>SDO Configuration Set</em>' attribute.
	 * @see #getSDOConfigurationSet()
	 * @generated
	 */
	void setSDOConfigurationSet(_SDOPackage.ConfigurationSet value);

} // CorbaConfigurationSet
