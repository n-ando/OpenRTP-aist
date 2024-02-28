/**
 * <copyright>
 * </copyright>
 *
 * $Id: ExecutionContextImpl.java,v 1.1 2008/01/29 05:52:23 yamashita Exp $
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import java.util.Collection;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.IPropertyMap;
import jp.go.aist.rtm.toolscommon.model.component.util.PropertyMap;
import jp.go.aist.rtm.toolscommon.model.core.impl.WrapperObjectImpl;
import jp.go.aist.rtm.toolscommon.ui.propertysource.ExecutionContextPropertySource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Execution Context</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ExecutionContextImpl#getKindL <em>Kind L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ExecutionContextImpl#getRateL <em>Rate L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ExecutionContextImpl#getStateL <em>State L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ExecutionContextImpl#getOwner <em>Owner</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ExecutionContextImpl#getParticipants <em>Participants</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExecutionContextImpl extends WrapperObjectImpl implements
		ExecutionContext {

	/** RTC.LifeCycleStateを内部値へ変換します。 */
	public static int RTC_STATUS(RTC.LifeCycleState state) {
		if (state == null) {
			return RTC_UNKNOWN;
		}
		if (state == RTC.LifeCycleState.ACTIVE_STATE
				|| state == RTC.LifeCycleState.INACTIVE_STATE
				|| state == RTC.LifeCycleState.ERROR_STATE) {
			return state.value();
		}
		return RTC_UNKNOWN;
	}

	/** EC種別をラベルへ変換します。 */
	public static String EC_KIND_LABEL(int kind) {
		if (kind == KIND_PERIODIC) {
			return "PERIODIC";
		} else if (kind == KIND_EVENT_DRIVEN) {
			return "EVENT_DRIVEN";
		} else if (kind == KIND_OTHER) {
			return "OTHER";
		}
		return "UNKNOWN";
	}

	/** EC状態をラベルへ変換します。 */
	public static String EC_STATUS_LABEL(int state) {
		if (state == STATE_STOPPED) {
			return "STOPPED";
		} else if (state == STATE_RUNNING) {
			return "RUNNING";
		}
		return "UNKNOWN";
	}

	/** コンポーネント状態をラベルへ変換します。 */
	public static String RTC_STATUS_LABEL(int state) {
		if (state == RTC_ACTIVE) {
			return "ACTIVE";
		} else if (state == RTC_INACTIVE) {
			return "INACTIVE";
		} else if (state == RTC_ERROR) {
			return "ERROR";
		}
		return "UNKNOWN";
	}

	/**
	 * The default value of the '{@link #getKindL() <em>Kind L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKindL()
	 * @generated
	 * @ordered
	 */
	protected static final int KIND_L_EDEFAULT = -1;

	/**
	 * The cached value of the '{@link #getKindL() <em>Kind L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKindL()
	 * @generated
	 * @ordered
	 */
	protected int kindL = KIND_L_EDEFAULT;

	/**
	 * The default value of the '{@link #getRateL() <em>Rate L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRateL()
	 * @generated
	 * @ordered
	 */
	protected static final Double RATE_L_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRateL() <em>Rate L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRateL()
	 * @generated
	 * @ordered
	 */
	protected Double rateL = RATE_L_EDEFAULT;

	/**
	 * The default value of the '{@link #getStateL() <em>State L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStateL()
	 * @generated
	 * @ordered
	 */
	protected static final int STATE_L_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getStateL() <em>State L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStateL()
	 * @generated
	 * @ordered
	 */
	protected int stateL = STATE_L_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOwner() <em>Owner</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwner()
	 * @generated
	 * @ordered
	 */
	protected Component owner;

	/**
	 * The cached value of the '{@link #getParticipants() <em>Participants</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParticipants()
	 * @generated
	 * @ordered
	 */
	protected EList<Component> participants;

	protected IPropertyMap properties;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	public ExecutionContextImpl() {
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
		return ComponentPackage.Literals.EXECUTION_CONTEXT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getKindL() {
		return kindL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKindL(int newKindL) {
		int oldKindL = kindL;
		kindL = newKindL;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.EXECUTION_CONTEXT__KIND_L, oldKindL, kindL));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double getRateL() {
		return rateL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRateL(Double newRateL) {
		Double oldRateL = rateL;
		rateL = newRateL;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.EXECUTION_CONTEXT__RATE_L, oldRateL, rateL));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getStateL() {
		return stateL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStateL(int newStateL) {
		int oldStateL = stateL;
		stateL = newStateL;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.EXECUTION_CONTEXT__STATE_L, oldStateL, stateL));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Component getOwner() {
		if (owner != null && owner.eIsProxy()) {
			InternalEObject oldOwner = (InternalEObject)owner;
			owner = (Component)eResolveProxy(oldOwner);
			if (owner != oldOwner) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ComponentPackage.EXECUTION_CONTEXT__OWNER, oldOwner, owner));
			}
		}
		return owner;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Component basicGetOwner() {
		return owner;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwner(Component newOwner) {
		Component oldOwner = owner;
		owner = newOwner;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.EXECUTION_CONTEXT__OWNER, oldOwner, owner));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Component> getParticipants() {
		if (participants == null) {
			participants = new EObjectEList<Component>(Component.class, this, ComponentPackage.EXECUTION_CONTEXT__PARTICIPANTS);
		}
		return participants;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getId() {
		Component comp = (Component) eContainer();
		if (comp != null) {
			String id = comp.getExecutionContextHandler().getId(this);
			if (id == null) {
				id = comp.getParticipationContextHandler().getId(this);
			}
			if (id != null) {
				return id;
			}
		}
		return "";
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getKindName() {
		return EC_KIND_LABEL(this.kindL);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getStateName() {
		return EC_STATUS_LABEL(this.stateL);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean setRateR(Double rate) {
		setRateL(rate);
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean addComponentR(Component comp) {
		// 同一RTCのアタッチを非許容
		if (containsComponent(comp)) {
			return false;
		}
		getParticipants().add(comp);
		comp.getParticipationContexts().add(this);
		comp.getParticipationContextHandler().sync();
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean removeComponentR(Component comp) {
		if (containsComponent(comp)) {
			getParticipants().remove(comp);
		}
		if (comp.getParticipationContexts().contains(this)) {
			comp.getParticipationContexts().remove(this);
		}
		comp.getParticipationContextHandler().sync();
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean containsComponent(Component comp) {
		return getParticipants().contains(comp);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isOwner(Component comp) {
		if (this.getOwner() != null && this.getOwner().equals(comp)) {
			return true;
		}
		return false;
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
			case ComponentPackage.EXECUTION_CONTEXT__KIND_L:
				return getKindL();
			case ComponentPackage.EXECUTION_CONTEXT__RATE_L:
				return getRateL();
			case ComponentPackage.EXECUTION_CONTEXT__STATE_L:
				return getStateL();
			case ComponentPackage.EXECUTION_CONTEXT__OWNER:
				if (resolve) return getOwner();
				return basicGetOwner();
			case ComponentPackage.EXECUTION_CONTEXT__PARTICIPANTS:
				return getParticipants();
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
			case ComponentPackage.EXECUTION_CONTEXT__KIND_L:
				setKindL((Integer)newValue);
				return;
			case ComponentPackage.EXECUTION_CONTEXT__RATE_L:
				setRateL((Double)newValue);
				return;
			case ComponentPackage.EXECUTION_CONTEXT__STATE_L:
				setStateL((Integer)newValue);
				return;
			case ComponentPackage.EXECUTION_CONTEXT__OWNER:
				setOwner((Component)newValue);
				return;
			case ComponentPackage.EXECUTION_CONTEXT__PARTICIPANTS:
				getParticipants().clear();
				getParticipants().addAll((Collection<? extends Component>)newValue);
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
			case ComponentPackage.EXECUTION_CONTEXT__KIND_L:
				setKindL(KIND_L_EDEFAULT);
				return;
			case ComponentPackage.EXECUTION_CONTEXT__RATE_L:
				setRateL(RATE_L_EDEFAULT);
				return;
			case ComponentPackage.EXECUTION_CONTEXT__STATE_L:
				setStateL(STATE_L_EDEFAULT);
				return;
			case ComponentPackage.EXECUTION_CONTEXT__OWNER:
				setOwner((Component)null);
				return;
			case ComponentPackage.EXECUTION_CONTEXT__PARTICIPANTS:
				getParticipants().clear();
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
			case ComponentPackage.EXECUTION_CONTEXT__KIND_L:
				return kindL != KIND_L_EDEFAULT;
			case ComponentPackage.EXECUTION_CONTEXT__RATE_L:
				return RATE_L_EDEFAULT == null ? rateL != null : !RATE_L_EDEFAULT.equals(rateL);
			case ComponentPackage.EXECUTION_CONTEXT__STATE_L:
				return stateL != STATE_L_EDEFAULT;
			case ComponentPackage.EXECUTION_CONTEXT__OWNER:
				return owner != null;
			case ComponentPackage.EXECUTION_CONTEXT__PARTICIPANTS:
				return participants != null && !participants.isEmpty();
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
		result.append(" (kindL: ");
		result.append(kindL);
		result.append(", rateL: ");
		result.append(rateL);
		result.append(", stateL: ");
		result.append(stateL);
		result.append(')');
		return result.toString();
	}

	@Override
	public java.lang.Object getAdapter(Class adapter) {
		java.lang.Object result = null;
		if (IPropertySource.class.equals(adapter)) {
			result = new ExecutionContextPropertySource(this);
		}
		if (result == null) {
			result = super.getAdapter(adapter);
		}
		return result;
	}

} // ExecutionContextImpl
