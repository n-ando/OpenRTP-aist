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
import jp.go.aist.rtm.toolscommon.model.component.CorbaConfigurationSet;
import jp.go.aist.rtm.toolscommon.synchronizationframework.LocalObject;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.AttributeMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ClassMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ConstructorParamMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.MappingRule;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ReferenceMapping;
import jp.go.aist.rtm.toolscommon.util.SDOUtil;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import _SDOPackage.ConfigurationSet;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Corba Configuration Set</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaConfigurationSetImpl#getSDOConfigurationSet <em>SDO Configuration Set</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CorbaConfigurationSetImpl extends ConfigurationSetImpl implements CorbaConfigurationSet {
	/**
	 * The default value of the '{@link #getSDOConfigurationSet() <em>SDO Configuration Set</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSDOConfigurationSet()
	 * @generated
	 * @ordered
	 */
	protected static final ConfigurationSet SDO_CONFIGURATION_SET_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSDOConfigurationSet() <em>SDO Configuration Set</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSDOConfigurationSet()
	 * @generated
	 * @ordered
	 */
	protected ConfigurationSet sDOConfigurationSet = SDO_CONFIGURATION_SET_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public CorbaConfigurationSetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentPackage.Literals.CORBA_CONFIGURATION_SET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConfigurationSet getSDOConfigurationSet() {
		return sDOConfigurationSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSDOConfigurationSet(ConfigurationSet newSDOConfigurationSet) {
		ConfigurationSet oldSDOConfigurationSet = sDOConfigurationSet;
		sDOConfigurationSet = newSDOConfigurationSet;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CORBA_CONFIGURATION_SET__SDO_CONFIGURATION_SET, oldSDOConfigurationSet, sDOConfigurationSet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ComponentPackage.CORBA_CONFIGURATION_SET__SDO_CONFIGURATION_SET:
				return getSDOConfigurationSet();
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
			case ComponentPackage.CORBA_CONFIGURATION_SET__SDO_CONFIGURATION_SET:
				setSDOConfigurationSet((ConfigurationSet)newValue);
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
			case ComponentPackage.CORBA_CONFIGURATION_SET__SDO_CONFIGURATION_SET:
				setSDOConfigurationSet(SDO_CONFIGURATION_SET_EDEFAULT);
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
			case ComponentPackage.CORBA_CONFIGURATION_SET__SDO_CONFIGURATION_SET:
				return SDO_CONFIGURATION_SET_EDEFAULT == null ? sDOConfigurationSet != null : !SDO_CONFIGURATION_SET_EDEFAULT.equals(sDOConfigurationSet);
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
		result.append(" (sDOConfigurationSet: ");
		result.append(sDOConfigurationSet);
		result.append(')');
		return result.toString();
	}

	@Override
	public String getId() {
		return getSDOConfigurationSet() == null ? null : getSDOConfigurationSet().id;
	}

	@Override
	public void setId(String newId) {
		if (getSDOConfigurationSet() == null) setSDOConfigurationSet(new ConfigurationSet());
		getSDOConfigurationSet().id = newId;
	}

	// Mapping Rule
    public static final MappingRule MAPPING_RULE = new MappingRule(
            null,
            new ClassMapping(
                    CorbaConfigurationSetImpl.class,
                    new ConstructorParamMapping[] { new ConstructorParamMapping(
                            ComponentPackage.eINSTANCE
                                    .getCorbaConfigurationSet_SDOConfigurationSet()) }) {
            }, new AttributeMapping[] {new AttributeMapping(ComponentPackage.eINSTANCE
                    .getConfigurationSet_ConfigurationData(), true) {
				@Override
                public Object getRemoteAttributeValue(
                        LocalObject localObject, Object[] remoteObjects) {
                    List result = new ArrayList();
                    try {
                        _SDOPackage.ConfigurationSet configurationSet = ((CorbaConfigurationSet) localObject)
                                .getSDOConfigurationSet();
                        if (configurationSet != null) {
                            result = SDOUtil
                                    .createNameValueList(configurationSet.configuration_data);
                        }
                    } catch (Exception e) {
                        // void
                    }

                    return result;
                }
            }, }, new ReferenceMapping[] {});


} //CorbaConfigurationSetImpl
