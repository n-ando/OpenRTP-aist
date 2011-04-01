/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import jp.go.aist.rtm.toolscommon.ToolsCommonPlugin;
import jp.go.aist.rtm.toolscommon.manager.ToolsCommonPreferenceManager;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaStatusObserver;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.util.CorbaObjectStore;
import jp.go.aist.rtm.toolscommon.ui.propertysource.CorbaStatusObserverPropertySource;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.ui.views.properties.IPropertySource;
import org.omg.PortableServer.Servant;

import static jp.go.aist.rtm.toolscommon.util.RTMixin.*;
import static jp.go.aist.rtm.toolscommon.manager.ToolsCommonPreferenceManager.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Corba Status Observer</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class CorbaStatusObserverImpl extends CorbaObserverImpl implements CorbaStatusObserver {

	static Logger log = ToolsCommonPlugin.getLogger();

	public static final String[] TYPE_NAMES = new String[] {
			"COMPONENT_PROFILE", //
			"RTC_STATUS", //
			"EC_STATUS", //
			"PORT_PROFILE", //
			"CONFIGURATION", //
			"HEART_BEAT", //
	};

	protected ComponentObserverPOAImpl servant;

	RTC.RTObject rtc;

	static Map<RTC.RTObject, _SDOPackage.ServiceProfile> profileMap;
	static Map<RTC.RTObject, HeartBeat> hbMap;
	static Map<RTC.RTObject, ComponentList> componentListMap;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected CorbaStatusObserverImpl() {
		super();
		if (profileMap == null) {
			profileMap = new HashMap<RTC.RTObject, _SDOPackage.ServiceProfile>();
		}
		if (hbMap == null) {
			hbMap = new HashMap<RTC.RTObject, HeartBeat>();
		}
		if (componentListMap == null) {
			componentListMap = new HashMap<RTC.RTObject, ComponentList>();
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentPackage.Literals.CORBA_STATUS_OBSERVER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean isTimeOut() {
		if (rtc == null) {
			return false;
		}
		HeartBeat hb = hbMap.get(rtc);
		if (hb == null) {
			return false;
		}
		if (!hb.isEnable()) {
			return false;
		}
		return hb.isTimeOut();
	}

	@Override
	public Servant getServant() {
		if (servant == null) {
			servant = new ComponentObserverPOAImpl(this);
		}
		return servant;
	}

	PropertyChangeListener listener;

	@Override
	public boolean attachComponent(CorbaComponent component) {
		RTC.RTObject ro = component.getCorbaObjectInterface();
		if (rtc == null) {
			rtc = ro;
		}
		if (!eql(rtc, ro)) {
			return false;
		}
		_SDOPackage.ServiceProfile prof = profileMap.get(rtc);
		if (prof != null) {
			serviceProfile = prof;
		} else {
			HeartBeat hb = new HeartBeat();
			hbMap.put(rtc, hb);
			//
			serviceProfile = new _SDOPackage.ServiceProfile();
			serviceProfile.interface_type = OpenRTM.ComponentObserverHelper
					.id();
			//
			setProperty("observed_status", "ALL");
			setProperty("heartbeat.enable", hb.getPropEnable());
			setProperty("heartbeat.interval", hb.getPropInterval());
			//
			listener = new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					propertyChanged(evt.getPropertyName());
				}
			};
			ToolsCommonPreferenceManager.getInstance()
					.addPropertyChangeListener(listener);
			//
			activate();
			boolean result = addServiceProfile(component);
			if (!result) {
				deactivate();
				return false;
			}
			profileMap.put(rtc, serviceProfile);
		}
		ComponentList components = getComponentList();
		if (!components.contain(component)) {
			components.add(component);
			component.setStatusObserver(this);
		}
		return true;
	}

	@Override
	public boolean detachComponent(CorbaComponent component) {
		RTC.RTObject ro = component.getCorbaObjectInterface();
		if (!eql(rtc, ro)) {
			return false;
		}
		ComponentList components = getComponentList();
		if (components.contain(component)) {
			if (!isCompositeMember(component)) {
				components.remove(component);
				component.setStatusObserver(null);
			}
		}
		if (!components.isEmpty()) {
			return true;
		}
		boolean result = removeServiceProfile(component);
		deactivate();
		profileMap.remove(rtc);
		hbMap.remove(rtc);
		ToolsCommonPreferenceManager.getInstance()
				.removePropertyChangeListener(listener);
		return result;
	}

	ComponentList getComponentList() {
		if (rtc == null) {
			return new ComponentList();
		}
		ComponentList result = componentListMap.get(rtc);
		if (result == null) {
			result = new ComponentList();
			componentListMap.put(rtc, result);
		}
		return result;
	}

	public void notifyStatus(OpenRTM.StatusKind status_kind, String hint) {
		if (OpenRTM.StatusKind.HEART_BEAT.equals(status_kind)) {
			// H.B受信
			HeartBeat hb = hbMap.get(rtc);
			if (hb != null) {
				hb.recv();
				hb.setForceTimeOut(false);
			}
			return;
		}

		log.info("update_status(" + TYPE_NAMES[status_kind.value()] + ", "
				+ hint + ")");

		ComponentList components = getComponentList();
		if (components.isEmpty()) {
			return;
		}
		CorbaComponentImpl ccImpl = (CorbaComponentImpl) components.get(0);
		if (OpenRTM.StatusKind.COMPONENT_PROFILE.equals(status_kind)) {
			// RTC.ComponentProfileの変更通知
			ccImpl.synchronizeRemote_RTCComponentProfile();
		}
		if (OpenRTM.StatusKind.RTC_STATUS.equals(status_kind)) {
			// RTC状態の変更通知
			if (hint == null) {
				return;
			}
			String[] ss = hint.split(":");
			if (ss.length != 2) {
				return;
			}
			String state = ss[0];
			String id = ss[1];
			//
			int stateValue = ExecutionContext.RTC_UNKNOWN;
			if ("ACTIVE".equals(state)) {
				stateValue = RTC.LifeCycleState._ACTIVE_STATE;
			} else if ("INACTIVE".equals(state)) {
				stateValue = RTC.LifeCycleState._INACTIVE_STATE;
			} else if ("ERROR".equals(state)) {
				stateValue = RTC.LifeCycleState._ERROR_STATE;
			} else if ("FINALIZE".equals(state)) {
				// H.Bタイムアウトを設定
				HeartBeat hb = hbMap.get(rtc);
				hb.setForceTimeOut(true);
				return;
			}
			//
			RTC.ExecutionContext ec = CorbaObjectStore.eINSTANCE.findContext(
					ccImpl.getCorbaObjectInterface(), id);
			CorbaObjectStore.eINSTANCE.registComponentState(ec, ccImpl
					.getCorbaObjectInterface(), stateValue);
		}
		if (OpenRTM.StatusKind.EC_STATUS.equals(status_kind)) {
			// EC状態の変更通知
			if (hint == null) {
				return;
			}
			String[] ss = hint.split(":");
			if (ss.length != 2) {
				return;
			}
			String action = ss[0];
			String id = ss[1];
			//
			if ("ATTACHED".equals(action) || "DETACHED".equals(action)) {
				ccImpl.synchronizeRemote_RTCExecutionContexts();
				RTC.ExecutionContext ec = CorbaObjectStore.eINSTANCE
						.findContext(ccImpl.getCorbaObjectInterface(), id);
				if (ec != null) {
					ccImpl.synchronizeRemote_EC_ECProfile(ec);
					ccImpl.synchronizeRemote_EC_ComponentState(ec);
				}
			} else if ("RATE_CHANGED".equals(action)) {
				RTC.ExecutionContext ec = CorbaObjectStore.eINSTANCE
						.findContext(ccImpl.getCorbaObjectInterface(), id);
				if (ec != null) {
					ccImpl.synchronizeRemote_EC_ECProfile(ec);
				}
			} else if ("STARTUP".equals(action) || "SHUTDOWN".equals(action)) {
				RTC.ExecutionContext ec = CorbaObjectStore.eINSTANCE
						.findContext(ccImpl.getCorbaObjectInterface(), id);
				if (ec != null) {
					ccImpl.synchronizeRemote_EC_ECState(ec);
				}
			}
		}
		if (OpenRTM.StatusKind.PORT_PROFILE.equals(status_kind)) {
			// RTC.PortProfileの変更通知
			if (hint == null) {
				return;
			}
			String[] ss = hint.split(":");
			if (ss.length != 2) {
				return;
			}
			String action = ss[0];
			String port_name = ss[1];
			//
			if ("CONNECT".equals(action) || "DISCONNECT".equals(action)) {
				ccImpl.synchronizeRemote_RTCPortProfile(port_name);
			} else if ("ADD".equals(action) || "REMOVE".equals(action)) {
				ccImpl.synchronizeRemote_RTCComponentProfile();
			}
		}
		if (OpenRTM.StatusKind.CONFIGURATION.equals(status_kind)) {
			// ConfigurationSetの変更通知
			if (hint == null) {
				return;
			}
			if ("ACTIVATE_CONFIG_SET".equals(hint)) {
				ccImpl.synchronizeRemote_ActiveConfigurationSet();
			} else {
				ccImpl.synchronizeRemote_ConfigurationSets();
			}
		}
	}

	public void propertyChanged(String name) {
		if (name == null) {
			return;
		}
		String p = name.substring(name.lastIndexOf(".") + 1);

		log.info("property changed: " + p);

		if (!KEY_STATUS_OBSERVER_HB_ENABLE.equals(name)
				&& !KEY_STATUS_OBSERVER_HB_INTERVAL.equals(name)
				&& !KEY_STATUS_OBSERVER_HB_TRYCOUNT.equals(name)) {
			return;
		}
		//
		if (rtc == null) {
			return;
		}
		HeartBeat hb = hbMap.get(rtc);
		serviceProfile = profileMap.get(rtc);
		if (hb == null || serviceProfile == null) {
			return;
		}
		ComponentList components = getComponentList();
		if (components.isEmpty()) {
			return;
		}
		CorbaComponent component = components.get(0);
		//
		hb.updatePreference();
		setProperty("heartbeat.enable", hb.getPropEnable());
		setProperty("heartbeat.interval", hb.getPropInterval());
		//
		removeServiceProfile(component);
		addServiceProfile(component);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class adapter) {
		java.lang.Object result = null;
		if (IPropertySource.class.equals(adapter)) {
			result = new CorbaStatusObserverPropertySource(this);
		}
		return result;
	}

	static class ComponentObserverPOAImpl extends OpenRTM.ComponentObserverPOA {
		CorbaStatusObserverImpl parent;

		public ComponentObserverPOAImpl(CorbaStatusObserverImpl parent) {
			this.parent = parent;
		}

		@Override
		public void update_status(OpenRTM.StatusKind status_kind, String hint) {
			parent.notifyStatus(status_kind, hint);
		}
	}

	public static class HeartBeat {
		Boolean enable;
		Double interval;
		Integer tryCount;
		Integer count;
		Long nextTime;
		boolean forceTimeOut;

		ToolsCommonPreferenceManager pref;

		HeartBeat() {
			this.pref = ToolsCommonPreferenceManager.getInstance();
			//
			this.enable = pref.isSTATUS_OBSERVER_HB_ENABLE();
			this.interval = pref.getSTATUS_OBSERVER_HB_INTERVAL();
			this.tryCount = pref.getSTATUS_OBSERVER_HB_TRYCOUNT();
			this.count = 0;
			this.nextTime = new Date().getTime();
			this.forceTimeOut = false;
		}

		void updatePreference() {
			this.enable = pref.isSTATUS_OBSERVER_HB_ENABLE();
			this.interval = pref.getSTATUS_OBSERVER_HB_INTERVAL();
			this.tryCount = pref.getSTATUS_OBSERVER_HB_TRYCOUNT();
		}

		String getPropEnable() {
			return isEnable() ? "YES" : "NO";
		}

		String getPropInterval() {
			return Double.toString(interval);
		}

		boolean isEnable() {
			return this.enable;
		}

		Double getInterval() {
			return interval;
		}

		void setInterval(Double interval) {
			this.interval = interval;
		}

		Integer getTryCount() {
			return tryCount;
		}

		void setTryCount(Integer tryCount) {
			this.tryCount = tryCount;
		}

		void recv() {
			nextTime = getNextTime();
			count = 0;
		}

		boolean isTimeOut() {
			if (forceTimeOut) {
				return true;
			}
			Date now = new Date();
			if (now.getTime() > nextTime) {
				nextTime = getNextTime();
				count++;
			}
			if (count > tryCount) {
				return true;
			}
			return false;
		}

		long getNextTime() {
			Date now = new Date();
			return now.getTime() + new Double(interval * 1000.0).longValue();
		}

		void setForceTimeOut(boolean b) {
			this.forceTimeOut = b;
		}
	}

} //CorbaStatusObserverImpl