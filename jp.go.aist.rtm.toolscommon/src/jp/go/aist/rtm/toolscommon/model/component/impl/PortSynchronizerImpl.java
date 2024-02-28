/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.IPropertyMap;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;
import jp.go.aist.rtm.toolscommon.model.component.PortSynchronizer;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.util.PortConnectorFactory;
import jp.go.aist.rtm.toolscommon.model.component.util.PropertyMap;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Port Synchronizer</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.PortSynchronizerImpl#getOriginalPortString <em>Original Port String</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PortSynchronizerImpl extends EObjectImpl implements PortSynchronizer {
	/**
	 * The default value of the '{@link #getOriginalPortString() <em>Original Port String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginalPortString()
	 * @generated
	 * @ordered
	 */
	protected static final String ORIGINAL_PORT_STRING_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getOriginalPortString() <em>Original Port String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginalPortString()
	 * @generated
	 * @ordered
	 */
	protected String originalPortString = ORIGINAL_PORT_STRING_EDEFAULT;
	private SystemDiagram currentDiagram;

	IPropertyMap properties;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected PortSynchronizerImpl() {
		super();
		this.properties = new PropertyMap();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentPackage.Literals.PORT_SYNCHRONIZER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOriginalPortString() {
		return originalPortString;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOriginalPortString(String newOriginalPortString) {
		String oldOriginalPortString = originalPortString;
		originalPortString = newOriginalPortString;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.PORT_SYNCHRONIZER__ORIGINAL_PORT_STRING, oldOriginalPortString, originalPortString));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean disconnect(String conn_id) {
		Port port = (Port) eContainer();
		ConnectorProfile profile = findConnProfile(conn_id, port);
		if (profile == null) {
			return false;
		}
		SystemDiagram diagram = (SystemDiagram) port.eContainer().eContainer();
		if (diagram == null) {
			diagram = currentDiagram.getRootDiagram();
		} else {
			diagram = diagram.getRootDiagram();
		}
		PortConnector connector = PortConnectorFactory
				.createPortConnectorSpecification();
		connector.setConnectorProfile(profile);
		connector.setSource(port.findPort(diagram, profile.getSourceString()));
		connector.setTarget(port.findPort(diagram, profile.getTargetString()));
		return connector.deleteConnectorR();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean disconnect(ConnectorProfile conn_prof) {
		if (conn_prof == null) {
			return false;
		}
		return disconnect(conn_prof.getConnectorId());
	}

	ConnectorProfile findConnProfile(String conn_id, Port port) {
		for (ConnectorProfile profile : port.getConnectorProfiles()) {
			if (conn_id != null && conn_id.equals(profile.getConnectorId())) {
				return profile;
			}
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean disconnectAll() {
		// ここではオフラインのポート全切断を行う
		Port port = (Port) eContainer();
		SystemDiagram diagram = (SystemDiagram) port.eContainer().eContainer();
		diagram = diagram != null ? diagram.getRootDiagram() : currentDiagram.getRootDiagram();

		List<ConnectorProfile> profiles = new ArrayList<ConnectorProfile>(port.getConnectorProfiles());
		for (ConnectorProfile profile : profiles) {
			PortConnector connector = PortConnectorFactory.createPortConnectorSpecification();
			connector.setConnectorProfile(profile);
			connector.setSource(port.findPort(diagram, profile.getSourceString()));
			connector.setTarget(port.findPort(diagram, profile.getTargetString()));
			connector.deleteConnectorR();
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ComponentPackage.PORT_SYNCHRONIZER__ORIGINAL_PORT_STRING:
				return getOriginalPortString();
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
			case ComponentPackage.PORT_SYNCHRONIZER__ORIGINAL_PORT_STRING:
				setOriginalPortString((String)newValue);
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
			case ComponentPackage.PORT_SYNCHRONIZER__ORIGINAL_PORT_STRING:
				setOriginalPortString(ORIGINAL_PORT_STRING_EDEFAULT);
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
			case ComponentPackage.PORT_SYNCHRONIZER__ORIGINAL_PORT_STRING:
				return ORIGINAL_PORT_STRING_EDEFAULT == null ? originalPortString != null : !ORIGINAL_PORT_STRING_EDEFAULT.equals(originalPortString);
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
		result.append(" (originalPortString: ");
		result.append(originalPortString);
		result.append(')');
		return result.toString();
	}

	@Override
	public String getDataflowType() {
		return null;
	}

	@Override
	public String getDataType() {
		return null;
	}

	@Override
	public String getInterfaceType() {
		return null;
	}

	@Override
	public String getSubscriptionType() {
		return null;
	}

	@Override
	public List<NameValue> getProperties() {
		List<jp.go.aist.rtm.toolscommon.model.component.NameValue> result = new ArrayList<jp.go.aist.rtm.toolscommon.model.component.NameValue>();
		for (String key : getPropertyKeys()) {
			String value = getProperty(key);
			jp.go.aist.rtm.toolscommon.model.component.NameValue entry = ComponentFactory.eINSTANCE
					.createNameValue();
			entry.setName(key);
			entry.setValue(value);
			result.add(entry);
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

	@Override
	public void setCurrentDiagram(SystemDiagram currentDiagram) {
		this.currentDiagram = currentDiagram;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

} //PortSynchronizerImpl
