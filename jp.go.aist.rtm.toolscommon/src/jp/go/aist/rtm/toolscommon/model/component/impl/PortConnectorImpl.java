/**
 * <copyright>
 * </copyright>
 *
 * $Id: PortConnectorImpl.java,v 1.4 2008/03/06 04:01:49 yamashita Exp $
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;

import jp.go.aist.rtm.toolscommon.model.core.Point;
import jp.go.aist.rtm.toolscommon.model.core.impl.WrapperObjectImpl;
import jp.go.aist.rtm.toolscommon.ui.propertysource.PortConnectorPropertySource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Port Connector</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.PortConnectorImpl#getConnectorProfile <em>Connector Profile</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.PortConnectorImpl#getRoutingConstraint <em>Routing Constraint</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.PortConnectorImpl#getSource <em>Source</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.PortConnectorImpl#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class PortConnectorImpl extends WrapperObjectImpl implements PortConnector {
	/**
	 * The cached value of the '{@link #getConnectorProfile() <em>Connector Profile</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectorProfile()
	 * @generated
	 * @ordered
	 */
	protected ConnectorProfile connectorProfile;

	/**
	 * The cached value of the '{@link #getRoutingConstraint() <em>Routing Constraint</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoutingConstraint()
	 * @generated
	 * @ordered
	 */
	protected EMap<Integer, Point> routingConstraint;
	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected Port source;
	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected Port target;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public PortConnectorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentPackage.Literals.PORT_CONNECTOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectorProfile getConnectorProfile() {
		return connectorProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConnectorProfile(ConnectorProfile newConnectorProfile, NotificationChain msgs) {
		ConnectorProfile oldConnectorProfile = connectorProfile;
		connectorProfile = newConnectorProfile;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ComponentPackage.PORT_CONNECTOR__CONNECTOR_PROFILE, oldConnectorProfile, newConnectorProfile);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnectorProfile(ConnectorProfile newConnectorProfile) {
		if (newConnectorProfile != connectorProfile) {
			NotificationChain msgs = null;
			if (connectorProfile != null)
				msgs = ((InternalEObject)connectorProfile).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ComponentPackage.PORT_CONNECTOR__CONNECTOR_PROFILE, null, msgs);
			if (newConnectorProfile != null)
				msgs = ((InternalEObject)newConnectorProfile).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ComponentPackage.PORT_CONNECTOR__CONNECTOR_PROFILE, null, msgs);
			msgs = basicSetConnectorProfile(newConnectorProfile, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.PORT_CONNECTOR__CONNECTOR_PROFILE, newConnectorProfile, newConnectorProfile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<Integer, Point> getRoutingConstraint() {
		if (routingConstraint == null) {
			routingConstraint = new EcoreEMap<Integer,Point>(ComponentPackage.Literals.EINTEGER_OBJECT_TO_POINT_MAP_ENTRY, EIntegerObjectToPointMapEntryImpl.class, this, ComponentPackage.PORT_CONNECTOR__ROUTING_CONSTRAINT);
		}
		return routingConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Port getSource() {
		if (source != null && source.eIsProxy()) {
			InternalEObject oldSource = (InternalEObject)source;
			source = (Port)eResolveProxy(oldSource);
			if (source != oldSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ComponentPackage.PORT_CONNECTOR__SOURCE, oldSource, source));
			}
		}
		return source;
	}

	/**
	 * <!-- begin-user-doc --> TODO:コネクタが接続元（先）から削除された場合には、接続先（元）からも削除を行う<!--
	 * end-user-doc -->
	 *
	 * @generated NOT
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, Class baseClass, NotificationChain msgs) {
		switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
		case ComponentPackage.PORT_CONNECTOR__SOURCE:
		case ComponentPackage.PORT_CONNECTOR__TARGET:
			setTarget(null);
			setSource(null);
		}

		return super.eInverseRemove(otherEnd, featureID, baseClass, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ComponentPackage.PORT_CONNECTOR__CONNECTOR_PROFILE:
				return basicSetConnectorProfile(null, msgs);
			case ComponentPackage.PORT_CONNECTOR__ROUTING_CONSTRAINT:
				return ((InternalEList<?>)getRoutingConstraint()).basicRemove(otherEnd, msgs);
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
			case ComponentPackage.PORT_CONNECTOR__CONNECTOR_PROFILE:
				return getConnectorProfile();
			case ComponentPackage.PORT_CONNECTOR__ROUTING_CONSTRAINT:
				if (coreType) return getRoutingConstraint();
				else return getRoutingConstraint().map();
			case ComponentPackage.PORT_CONNECTOR__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
			case ComponentPackage.PORT_CONNECTOR__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
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
			case ComponentPackage.PORT_CONNECTOR__CONNECTOR_PROFILE:
				setConnectorProfile((ConnectorProfile)newValue);
				return;
			case ComponentPackage.PORT_CONNECTOR__ROUTING_CONSTRAINT:
				((EStructuralFeature.Setting)getRoutingConstraint()).set(newValue);
				return;
			case ComponentPackage.PORT_CONNECTOR__SOURCE:
				setSource((Port)newValue);
				return;
			case ComponentPackage.PORT_CONNECTOR__TARGET:
				setTarget((Port)newValue);
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
			case ComponentPackage.PORT_CONNECTOR__CONNECTOR_PROFILE:
				setConnectorProfile((ConnectorProfile)null);
				return;
			case ComponentPackage.PORT_CONNECTOR__ROUTING_CONSTRAINT:
				getRoutingConstraint().clear();
				return;
			case ComponentPackage.PORT_CONNECTOR__SOURCE:
				setSource((Port)null);
				return;
			case ComponentPackage.PORT_CONNECTOR__TARGET:
				setTarget((Port)null);
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
			case ComponentPackage.PORT_CONNECTOR__CONNECTOR_PROFILE:
				return connectorProfile != null;
			case ComponentPackage.PORT_CONNECTOR__ROUTING_CONSTRAINT:
				return routingConstraint != null && !routingConstraint.isEmpty();
			case ComponentPackage.PORT_CONNECTOR__SOURCE:
				return source != null;
			case ComponentPackage.PORT_CONNECTOR__TARGET:
				return target != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Port basicGetSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(Port newSource) {
		Port oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.PORT_CONNECTOR__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Port getTarget() {
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject)target;
			target = (Port)eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ComponentPackage.PORT_CONNECTOR__TARGET, oldTarget, target));
			}
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Port basicGetTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(Port newTarget) {
		Port oldTarget = target;
		target = newTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.PORT_CONNECTOR__TARGET, oldTarget, target));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean createConnectorR() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean deleteConnectorR() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	public java.lang.Object getAdapter(Class adapter) {
		java.lang.Object result = null;
		if (IPropertySource.class.equals(adapter)) {
			result = new PortConnectorPropertySource(this);
		}

		if (result == null) {
			result = super.getAdapter(adapter);
		}

		return result;
	}

} // PortConnectorImpl
