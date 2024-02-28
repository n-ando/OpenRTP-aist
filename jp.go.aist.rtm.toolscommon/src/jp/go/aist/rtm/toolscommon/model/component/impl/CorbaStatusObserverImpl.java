/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import static jp.go.aist.rtm.toolscommon.manager.ToolsCommonPreferenceManager.KEY_STATUS_OBSERVER_HB_ENABLE;
import static jp.go.aist.rtm.toolscommon.manager.ToolsCommonPreferenceManager.KEY_STATUS_OBSERVER_HB_INTERVAL;
import static jp.go.aist.rtm.toolscommon.manager.ToolsCommonPreferenceManager.KEY_STATUS_OBSERVER_HB_TRYCOUNT;
import static jp.go.aist.rtm.toolscommon.manager.ToolsCommonPreferenceManager.KEY_STATUS_OBSERVER_PORT_EVENT_RECV_MIN_INTERVAL;
import static jp.go.aist.rtm.toolscommon.manager.ToolsCommonPreferenceManager.KEY_STATUS_OBSERVER_PORT_EVENT_SEND_MIN_INTERVAL;
import static jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl.removeRemote_FullObject;
import static jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl.synchronizeRemote_ActiveConfigurationSet;
import static jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl.synchronizeRemote_ConfigurationSets;
import static jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl.synchronizeRemote_EC_ComponentState;
import static jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl.synchronizeRemote_EC_ECProfile;
import static jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl.synchronizeRemote_EC_ECState;
import static jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl.synchronizeRemote_RTCComponentProfile;
import static jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl.synchronizeRemote_RTCExecutionContexts;
import static jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl.synchronizeRemote_RTCPortProfile;
import static jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl.synchronizeRemote_RTCRTObjects;
import static jp.go.aist.rtm.toolscommon.util.RTMixin.eql;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.omg.PortableServer.Servant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.go.aist.rtm.toolscommon.manager.ToolsCommonPreferenceManager;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaStatusObserver;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.util.CorbaObjectStore;
import jp.go.aist.rtm.toolscommon.model.component.util.CorbaObserverStore;
import jp.go.aist.rtm.toolscommon.model.component.util.ICorbaPortEventObserver;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Corba Status Observer</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class CorbaStatusObserverImpl extends CorbaObserverImpl implements CorbaStatusObserver {

	private static final Logger LOGGER = LoggerFactory.getLogger(CorbaStatusObserverImpl.class);

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

	PropertyChangeListener listener;

	static Map<RTC.RTObject, HeartBeat> hbMap;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected CorbaStatusObserverImpl() {
		super();
		if (hbMap == null) {
			hbMap = new HashMap<RTC.RTObject, HeartBeat>();
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

	@Override
	public boolean attachComponent(CorbaComponent component) {
		RTC.RTObject ro = component.getCorbaObjectInterface();
		if (rtc == null) {
			rtc = ro;
		}
		if (!eql(rtc, ro)) {
			return false;
		}
		CorbaStatusObserver obs = CorbaObserverStore.eINSTANCE.findStatusObserver(ro);
		if (obs != null) {
			return true;
		} else {
			HeartBeat hb = new HeartBeat();
			hbMap.put(rtc, hb);
			//
			serviceProfile = new _SDOPackage.ServiceProfile();
			serviceProfile.interface_type = OpenRTM.ComponentObserverHelper.id();
			//
			setProperty("observed_status", "ALL");
			setProperty("heartbeat.enable", hb.getPropEnable());
			setProperty("heartbeat.interval", hb.getPropInterval());
			//
			// port_profile.send_event.min_interval x [s] (最低送信イベント間隔)
			setProperty("port_profile.send_event.min_interval", getPropPortSendMinInterval());
			// port_profile.receive_event.min_interval: x [s] (最低受信イベント間隔))
			setProperty("port_profile.receive_event.min_interval", getPropPortRecvMinInterval());
			//
			listener = new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					propertyChanged(evt.getPropertyName());
				}
			};
			ToolsCommonPreferenceManager.getInstance().addPropertyChangeListener(listener);
			//
			activate();
			try {
				boolean result = addServiceProfile(rtc);
				if (!result) {
					deactivate();
					return false;
				}
			} catch (Exception e) {
				deactivate();
				return false;
			}
			CorbaObserverStore.eINSTANCE.registStatusObserver(ro, this);
		}
		return true;
	}

	@Override
	public boolean detachComponent() {
		if (rtc == null) {
			return true;
		}
		if (!CorbaObserverStore.eINSTANCE.isEmptyComponentReference(rtc)) {
			return true;
		}
		//
		return finish();
	}

	@Override
	public boolean finish() {
		if (rtc == null) {
			return true;
		}
		//
		boolean result = false;
		try {
			result = removeServiceProfile(rtc);
		} catch (Exception e) {
			LOGGER.warn("Fail to remove service profile. rtc={} exp=<{}:{}>", rtc, e.getClass().getSimpleName(),
					e.getMessage());
		}
		deactivate();
		//
		CorbaObserverStore.eINSTANCE.removeStatusObserver(rtc);
		hbMap.remove(rtc);
		ToolsCommonPreferenceManager.getInstance().removePropertyChangeListener(listener);
		//
		return result;
	}

	public void notifyStatus(OpenRTM.StatusKind status_kind, String hint) {
		if (OpenRTM.StatusKind.HEARTBEAT.equals(status_kind)) {
			// H.B受信
			HeartBeat hb = hbMap.get(rtc);
			if (hb != null) {
				hb.recv();
				hb.setForceTimeOut(false);
			}
			return;
		}

		String profId = (serviceProfile == null) ? "" : serviceProfile.id;
		LOGGER.info("update_status({}, {}): id={}", TYPE_NAMES[status_kind.value()], hint, profId);

		if (CorbaObserverStore.eINSTANCE.isEmptyComponentReference(rtc)) {
			return;
		}

		try {
			//
			if (OpenRTM.StatusKind.COMPONENT_PROFILE.equals(status_kind)) {
				// RTC.ComponentProfileの変更通知
				synchronizeRemote_RTCComponentProfile(rtc);
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
				RTC.ExecutionContext ec = CorbaObjectStore.eINSTANCE.findContext(rtc, id);
				CorbaObjectStore.eINSTANCE.registComponentState(ec, rtc, stateValue);
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
					RTC.ExecutionContext oldEc = CorbaObjectStore.eINSTANCE.findContext(rtc, id);
					//
					LOGGER.trace("  sync RTC rtc=<{}>", rtc);
					synchronizeRemote_RTCExecutionContexts(rtc);
					//
					RTC.ExecutionContext newEc = CorbaObjectStore.eINSTANCE.findContext(rtc, id);
					//
					RTC.ExecutionContext ec = null;
					if ("ATTACHED".equals(action)) {
						ec = newEc;
						//
						if (ec == null) {
							// 自RTCが所有する ECに対して自RTCをアタッチした場合、RTC-ECの関連のコンテキストIDが 2つになる
							// 1. RTC--<0>----->EC
							// 2. RTC--<1000>-->EC
							// このとき、ECから逆引きで 1つしかコンテキストIDが取得できないため、ローカルですべてのコンテキストIDを取得できない
							// そのため、オブザーバ通知で受け取ったID値によるECの検索で、暫定的に補完しておく
							try {
								ec = rtc.get_context(Integer.parseInt(id));
								CorbaObjectStore.eINSTANCE.registContext(rtc, id, ec);
							} catch (Exception e) {
								LOGGER.error("Fail to get RTC.ExecutionContext: rtc={} id={}", rtc, id);
								LOGGER.error("ERROR:", e);
							}
						}
					} else if ("DETACHED".equals(action)) {
						ec = oldEc;
					}
					if (ec != null) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							LOGGER.error("Fail to intarvel.", e);
						}
						LOGGER.trace("  sync EC: ec=<{}>", ec);
						synchronizeRemote_EC_ECProfile(ec);
						synchronizeRemote_EC_ComponentState(rtc, ec);
						// 複合RTCの子情報の変更通知がないため、ECのアタッチ/デタッチ時にECオーナーを更新
						RTC.ExecutionContextProfile ecprof = CorbaObjectStore.eINSTANCE.findECProfile(ec);
						if (ecprof != null && ecprof.owner != null) {
							synchronizeRemote_RTCRTObjects(ecprof.owner);
						}
					}
				} else if ("RATE_CHANGED".equals(action)) {
					RTC.ExecutionContext ec = CorbaObjectStore.eINSTANCE.findContext(rtc, id);
					if (ec != null) {
						synchronizeRemote_EC_ECProfile(ec);
					}
				} else if ("STARTUP".equals(action) || "SHUTDOWN".equals(action)) {
					RTC.ExecutionContext ec = CorbaObjectStore.eINSTANCE.findContext(rtc, id);
					if (ec != null) {
						synchronizeRemote_EC_ECState(ec);
					}
				}
			}
			if (OpenRTM.StatusKind.PORT_PROFILE.equals(status_kind)) {
				// RTC.PortProfileの変更通知
				if (hint == null) {
					return;
				}
				int p = hint.indexOf(":");
				if (p == -1) {
					return;
				}
				String action = hint.substring(0, p);
				String port_name = hint.substring(p + 1);
				//
				if ("CONNECT".equals(action) || "DISCONNECT".equals(action)) {
					synchronizeRemote_RTCPortProfile(rtc, port_name);
				} else if ("ADD".equals(action) || "REMOVE".equals(action)) {
					synchronizeRemote_RTCComponentProfile(rtc);
				} else if ("SEND".equals(action) || "RECEIVE".equals(action)) {
					for (ICorbaPortEventObserver obs : CorbaObserverStore.eINSTANCE.findPortEventObserver(this.rtc)) {
						obs.notifyEvent(action, port_name);
					}
				}
			}
			if (OpenRTM.StatusKind.CONFIGURATION.equals(status_kind)) {
				// ConfigurationSetの変更通知
				if (hint == null) {
					return;
				}
				int p = hint.indexOf(":");
				if (p == -1) {
					return;
				}
				String action = hint.substring(0, p);
				if ("ACTIVATE_CONFIG_SET".equals(action)) {
					synchronizeRemote_ActiveConfigurationSet(rtc);
				} else {
					synchronizeRemote_ConfigurationSets(rtc);
					// 複合RTCの公開ポート変更の通知がないので、ConfigurationSetの通知時にプロファイルを更新
					RTC.ComponentProfile prof = CorbaObjectStore.eINSTANCE.findRTCProfile(rtc);
					if (prof != null && prof.category != null && prof.category.startsWith("composite.")) {
						synchronizeRemote_RTCComponentProfile(rtc);
					}
				}
			}
		} catch (RuntimeException e) {
			LOGGER.error("Fail to sync: rtc={}", rtc);
			LOGGER.error("ERROR:", e);
			// 同期エラー時に強制的にタイムアウトにする
			HeartBeat hb = hbMap.get(rtc);
			if (hb != null) {
				hb.setForceTimeOut(true);
			}
		}
	}

	public void propertyChanged(String name) {
		if (name == null) {
			return;
		}
		String p = name.substring(name.lastIndexOf(".") + 1);

		LOGGER.info("property changed: {}", p);

		if (!KEY_STATUS_OBSERVER_HB_ENABLE.equals(name) //
				&& !KEY_STATUS_OBSERVER_HB_INTERVAL.equals(name) //
				&& !KEY_STATUS_OBSERVER_HB_TRYCOUNT.equals(name) //
				&& !KEY_STATUS_OBSERVER_PORT_EVENT_SEND_MIN_INTERVAL.equals(name) //
				&& !KEY_STATUS_OBSERVER_PORT_EVENT_RECV_MIN_INTERVAL.equals(name) //
		) {
			return;
		}
		//
		if (rtc == null) {
			return;
		}
		HeartBeat hb = hbMap.get(rtc);
		if (hb == null || serviceProfile == null) {
			return;
		}
		//
		hb.updatePreference();
		setProperty("heartbeat.enable", hb.getPropEnable());
		setProperty("heartbeat.interval", hb.getPropInterval());
		//
		setProperty("port_profile.send_event.min_interval", getPropPortSendMinInterval());
		setProperty("port_profile.receive_event.min_interval", getPropPortRecvMinInterval());
		//
		try {
			removeServiceProfile(rtc);
			addServiceProfile(rtc);
		} catch (Exception e) {
			LOGGER.error("Fail to reset service profile.", e);
		}
	}

	String getPropPortSendMinInterval() {
		return Double.toString(ToolsCommonPreferenceManager.getInstance().getSTATUS_OBSERVER_PORT_EVENT_SEND_MIN_INTERVAL());
	}

	String getPropPortRecvMinInterval() {
		return Double.toString(ToolsCommonPreferenceManager.getInstance().getSTATUS_OBSERVER_PORT_EVENT_RECV_MIN_INTERVAL());
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

} // CorbaStatusObserverImpl
