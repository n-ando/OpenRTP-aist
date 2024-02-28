/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component;

import _SDOPackage.ServiceProfile;

import org.eclipse.core.runtime.IAdaptable;
import org.omg.PortableServer.Servant;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Corba Observer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.CorbaObserver#getServiceProfile <em>Service Profile</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.CorbaObserver#getServant <em>Servant</em>}</li>
 * </ul>
 *
 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getCorbaObserver()
 * @model superTypes="jp.go.aist.rtm.toolscommon.model.component.IPropertyMap jp.go.aist.rtm.toolscommon.model.core.IAdaptable"
 * @generated
 */
public interface CorbaObserver extends IPropertyMap, IAdaptable {
	/**
	 * Returns the value of the '<em><b>Service Profile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service Profile</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Service Profile</em>' attribute.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getCorbaObserver_ServiceProfile()
	 * @model dataType="jp.go.aist.rtm.toolscommon.model.component.SDOServiceProfile" changeable="false"
	 * @generated
	 */
	ServiceProfile getServiceProfile();

	/**
	 * Returns the value of the '<em><b>Servant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Servant</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Servant</em>' attribute.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getCorbaObserver_Servant()
	 * @model dataType="jp.go.aist.rtm.toolscommon.model.component.Servant" changeable="false"
	 * @generated
	 */
	Servant getServant();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void activate();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void deactivate();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean attachComponent(CorbaComponent component);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean detachComponent();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean finish();

} // CorbaObserver
