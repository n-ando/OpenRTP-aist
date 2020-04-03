/**
 * <copyright>
 * </copyright>
 *
 * $Id: ConfigurationSetImpl.java,v 1.1 2008/01/29 05:52:23 yamashita Exp $
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import java.util.Collection;
import java.util.Iterator;

import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.core.impl.WrapperObjectImpl;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Configuration Set</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConfigurationSetImpl#getId <em>Id</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConfigurationSetImpl#getConfigurationData <em>Configuration Data</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConfigurationSetImpl extends WrapperObjectImpl implements
        ConfigurationSet {
    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final String ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getId()
     * @generated
     * @ordered
     */
    protected String id = ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getConfigurationData() <em>Configuration Data</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getConfigurationData()
	 * @generated
	 * @ordered
	 */
    protected EList<NameValue> configurationData;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    public ConfigurationSetImpl() {
        super();
    }

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				protected EClass eStaticClass() {
		return ComponentPackage.Literals.CONFIGURATION_SET;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONFIGURATION_SET__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public EList<NameValue> getConfigurationData() {
		if (configurationData == null) {
			configurationData = new EObjectContainmentEList<NameValue>(NameValue.class, this, ComponentPackage.CONFIGURATION_SET__CONFIGURATION_DATA);
		}
		return configurationData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ComponentPackage.CONFIGURATION_SET__CONFIGURATION_DATA:
				return ((InternalEList<?>)getConfigurationData()).basicRemove(otherEnd, msgs);
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
			case ComponentPackage.CONFIGURATION_SET__ID:
				return getId();
			case ComponentPackage.CONFIGURATION_SET__CONFIGURATION_DATA:
				return getConfigurationData();
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
			case ComponentPackage.CONFIGURATION_SET__ID:
				setId((String)newValue);
				return;
			case ComponentPackage.CONFIGURATION_SET__CONFIGURATION_DATA:
				getConfigurationData().clear();
				getConfigurationData().addAll((Collection<? extends NameValue>)newValue);
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
			case ComponentPackage.CONFIGURATION_SET__ID:
				setId(ID_EDEFAULT);
				return;
			case ComponentPackage.CONFIGURATION_SET__CONFIGURATION_DATA:
				getConfigurationData().clear();
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
			case ComponentPackage.CONFIGURATION_SET__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case ComponentPackage.CONFIGURATION_SET__CONFIGURATION_DATA:
				return configurationData != null && !configurationData.isEmpty();
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
		result.append(" (id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

    @Override
    public boolean equals(Object obj) {
    	if (obj == this) return true;

    	if (obj instanceof ConfigurationSet == false) {
            return false;
        }

        ConfigurationSet p = (ConfigurationSet) obj;

        return new EqualsBuilder().append(getId(), p.getId()).append(
                getConfigurationData().toArray(),
                p.getConfigurationData().toArray()).isEquals();
    }

	public static ConfigurationSet findConfigurationSetByID(String id,
            EList configurationSets) {
        ConfigurationSet result = null;
        for (Iterator iter = configurationSets.iterator(); iter.hasNext();) {
            ConfigurationSet tmp = (ConfigurationSet) iter.next();
            if (id.equals(tmp.getId())) {
                result = tmp;
                break;
            }
        }

        return result;
    }
} // ConfigurationSetImpl
