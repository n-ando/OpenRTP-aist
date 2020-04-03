/**
 * <copyright>
 * </copyright>
 *
 * $Id: CoreSwitch.java,v 1.4 2008/03/06 04:01:49 yamashita Exp $
 */
package jp.go.aist.rtm.toolscommon.model.core.util;

import java.util.List;

import jp.go.aist.rtm.toolscommon.model.core.*;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import jp.go.aist.rtm.toolscommon.model.core.CorbaWrapperObject;
import jp.go.aist.rtm.toolscommon.model.core.CorePackage;
import jp.go.aist.rtm.toolscommon.model.core.ModelElement;
import jp.go.aist.rtm.toolscommon.model.core.WrapperObject;
import jp.go.aist.rtm.toolscommon.synchronizationframework.LocalObject;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 * @see jp.go.aist.rtm.toolscommon.model.core.CorePackage
 * @generated
 */
public class CoreSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected static CorePackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public CoreSwitch() {
		if (modelPackage == null) {
			modelPackage = CorePackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case CorePackage.CORBA_WRAPPER_OBJECT: {
				CorbaWrapperObject corbaWrapperObject = (CorbaWrapperObject)theEObject;
				T result = caseCorbaWrapperObject(corbaWrapperObject);
				if (result == null) result = caseWrapperObject(corbaWrapperObject);
				if (result == null) result = caseModelElement(corbaWrapperObject);
				if (result == null) result = caseLocalObject(corbaWrapperObject);
				if (result == null) result = caseIAdaptable(corbaWrapperObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.MODEL_ELEMENT: {
				ModelElement modelElement = (ModelElement)theEObject;
				T result = caseModelElement(modelElement);
				if (result == null) result = caseIAdaptable(modelElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.IADAPTABLE: {
				IAdaptable iAdaptable = (IAdaptable)theEObject;
				T result = caseIAdaptable(iAdaptable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.LOCAL_OBJECT: {
				LocalObject localObject = (LocalObject)theEObject;
				T result = caseLocalObject(localObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.WRAPPER_OBJECT: {
				WrapperObject wrapperObject = (WrapperObject)theEObject;
				T result = caseWrapperObject(wrapperObject);
				if (result == null) result = caseModelElement(wrapperObject);
				if (result == null) result = caseLocalObject(wrapperObject);
				if (result == null) result = caseIAdaptable(wrapperObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Corba Wrapper Object</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a
	 * non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Corba Wrapper Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCorbaWrapperObject(CorbaWrapperObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a
	 * non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelElement(ModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IAdaptable</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a
	 * non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IAdaptable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIAdaptable(IAdaptable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Local Object</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a
	 * non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Local Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLocalObject(LocalObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wrapper Object</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a
	 * non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wrapper Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWrapperObject(WrapperObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a
	 * non-null result will terminate the switch, but this is the last case
	 * anyway. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} // CoreSwitch
