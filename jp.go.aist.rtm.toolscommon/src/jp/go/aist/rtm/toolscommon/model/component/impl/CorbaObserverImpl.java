/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import java.util.UUID;

import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaObserver;
import jp.go.aist.rtm.toolscommon.model.component.IPropertyMap;
import jp.go.aist.rtm.toolscommon.model.component.util.CorbaPropertyMap;
import jp.go.aist.rtm.toolscommon.ui.propertysource.CorbaObserverPropertySource;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.ui.views.properties.IPropertySource;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.Servant;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import _SDOPackage.ServiceProfile;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Corba Observer</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaObserverImpl#getServiceProfile <em>Service Profile</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaObserverImpl#getServant <em>Servant</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CorbaObserverImpl extends EObjectImpl implements CorbaObserver {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CorbaObserverImpl.class);

	/**
	 * The default value of the '{@link #getServiceProfile() <em>Service Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceProfile()
	 * @generated
	 * @ordered
	 */
	protected static final ServiceProfile SERVICE_PROFILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServiceProfile() <em>Service Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceProfile()
	 * @generated
	 * @ordered
	 */
	protected ServiceProfile serviceProfile = SERVICE_PROFILE_EDEFAULT;

	/**
	 * The default value of the '{@link #getServant() <em>Servant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServant()
	 * @generated
	 * @ordered
	 */
	protected static final Servant SERVANT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServant() <em>Servant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServant()
	 * @generated
	 * @ordered
	 */
	protected Servant servant = SERVANT_EDEFAULT;

	protected IPropertyMap properties;

	static ORB orb = null;
	static POA rootpoa = null;

	public static void initialization() {
		if (rootpoa != null) {
			return;
		}
		try {
			// ORBの生成と初期化を行います
			orb = ORB.init(new String[] {}, null);
			// RootPOAの参照を取得しPOAManagerを使用可能にします
			rootpoa = POAHelper.narrow(orb
					.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();
		} catch (InvalidName e) {
			LOGGER.error("Fail to resolve reference RootPOA", e);
		} catch (AdapterInactive e) {
			LOGGER.error("Fail to activate POAManager", e);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public CorbaObserverImpl() {
		super();
		initialization();
		this.properties = new CorbaPropertyMap() {
			@Override
			public _SDOPackage.NameValue[] getNameValues() {
				if (serviceProfile == null || serviceProfile.properties == null) {
					return new _SDOPackage.NameValue[0];
				}
				return serviceProfile.properties;
			}

			@Override
			public void setNameValues(_SDOPackage.NameValue[] nvs) {
				serviceProfile.properties = nvs;
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
		return ComponentPackage.Literals.CORBA_OBSERVER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ServiceProfile getServiceProfile() {
		return serviceProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Servant getServant() {
		return servant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void activate() {
		if (serviceProfile.service == null) {
			// サーバントからオブジェクトの参照を取得します
			try {
				org.omg.CORBA.Object obj = rootpoa
						.servant_to_reference(getServant());
				serviceProfile.service = _SDOPackage.SDOServiceHelper
						.narrow(obj);
			} catch (Exception e) {
				throw new RuntimeException("Servant error", e);
			}
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void deactivate() {
		try {
			if (serviceProfile.service != null) {
				byte[] oid = rootpoa.reference_to_id(serviceProfile.service);
				rootpoa.deactivate_object(oid);
				serviceProfile.service = null;
			}
		} catch (Exception e) {
			// void
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean attachComponent(CorbaComponent component) {
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
	public boolean detachComponent() {
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
	public boolean finish() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	protected boolean addServiceProfile(RTC.RTObject ro) {
		boolean result;
		try {
			serviceProfile.id = UUID.randomUUID().toString();
			if (serviceProfile.properties == null) {
				serviceProfile.properties = new _SDOPackage.NameValue[0];
			}
			result = ro.get_configuration().add_service_profile(serviceProfile);
			//
			if (result) {
				LOGGER.info("add_service_profile:    id={} type={} ior={} obs={} rtc=<{}>", serviceProfile.id,
						serviceProfile.interface_type, serviceProfile.service, this.getClass().getName(), ro);
			}
		} catch (Exception e) {
			LOGGER.error("Fail to add service profile: id={} type={} ior={} obs={} rtc=<{}>", serviceProfile.id,
					serviceProfile.interface_type, serviceProfile.service, this.getClass().getName(), ro);
			LOGGER.error("ERROR:", e);
			result = false;
		}
		return result;
	}

	protected boolean removeServiceProfile(RTC.RTObject ro) {
		boolean result;
		try {
			result = ro.get_configuration().remove_service_profile(serviceProfile.id);
			//
			LOGGER.info("remove_service_profile: id={} type={} ior={} obs={} rtc=<{}>", serviceProfile.id,
					serviceProfile.interface_type, serviceProfile.service, this.getClass().getName(), ro);
		} catch (Exception e) {
			LOGGER.warn("Fail to remove service profile: id={} type={} ior={} obs={} rtc=<{}> exp=<{}:{}>",
					serviceProfile.id, serviceProfile.interface_type, serviceProfile.service, this.getClass().getName(),
					ro, e.getClass().getSimpleName(), e.getMessage());
			result = false;
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void setProperty(String key, String value) {
		properties.setProperty(key, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String removeProperty(String key) {
		return properties.removeProperty(key);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<String> getPropertyKeys() {
		return properties.getPropertyKeys();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
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
			case ComponentPackage.CORBA_OBSERVER__SERVICE_PROFILE:
				return getServiceProfile();
			case ComponentPackage.CORBA_OBSERVER__SERVANT:
				return getServant();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ComponentPackage.CORBA_OBSERVER__SERVICE_PROFILE:
				return SERVICE_PROFILE_EDEFAULT == null ? serviceProfile != null : !SERVICE_PROFILE_EDEFAULT.equals(serviceProfile);
			case ComponentPackage.CORBA_OBSERVER__SERVANT:
				return SERVANT_EDEFAULT == null ? servant != null : !SERVANT_EDEFAULT.equals(servant);
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
		result.append(" (serviceProfile: ");
		result.append(serviceProfile);
		result.append(", servant: ");
		result.append(servant);
		result.append(')');
		return result.toString();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class adapter) {
		java.lang.Object result = null;
		if (IPropertySource.class.equals(adapter)) {
			result = new CorbaObserverPropertySource(this);
		}
		return result;
	}

} //CorbaObserverImpl
