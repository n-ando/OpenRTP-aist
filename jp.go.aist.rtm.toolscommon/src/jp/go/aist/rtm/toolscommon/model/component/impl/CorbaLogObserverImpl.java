/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaLogObserver;
import jp.go.aist.rtm.toolscommon.model.component.util.CorbaObserverStore;
import jp.go.aist.rtm.toolscommon.model.component.util.RTCLogStore;

import org.eclipse.emf.ecore.EClass;
import org.omg.PortableServer.Servant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static jp.go.aist.rtm.toolscommon.util.RTMixin.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Corba Log Observer</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class CorbaLogObserverImpl extends CorbaObserverImpl implements CorbaLogObserver {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CorbaLogObserverImpl.class);

	public static final String[] LEVEL_NAMES = new String[] { "SILENT", //
			"ERROR", //
			"WARN", //
			"INFO", //
			"NORMAL", //
			"DEBUG", //
			"TRACE", //
			"VERBOSE", //
			"PARANOID", //
	};

	protected LoggerPOAImpl servant;

	RTC.RTObject rtc;
	String rtcName;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected CorbaLogObserverImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentPackage.Literals.CORBA_LOG_OBSERVER;
	}

	@Override
	public Servant getServant() {
		if (servant == null) {
			servant = new LoggerPOAImpl(this);
		}
		return servant;
	}

	@Override
	public boolean attachComponent(CorbaComponent component) {
		RTC.RTObject ro = component.getCorbaObjectInterface();
		if (rtc == null) {
			rtc = ro;
			rtcName = component.getInstanceNameL();
		}
		if (!eql(rtc, ro)) {
			return false;
		}
		CorbaLogObserver obs = CorbaObserverStore.eINSTANCE.findLogObserver(ro);
		if (obs != null) {
			return true;
		} else {
			serviceProfile = new _SDOPackage.ServiceProfile();
			serviceProfile.interface_type = OpenRTM.LoggerHelper.id();
			setProperty("logger.filter", "ALL");
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
			CorbaObserverStore.eINSTANCE.registLogObserver(ro, this);
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
		}
		deactivate();
		//
		RTCLogStore.eINSTANCE.remove(serviceProfile.id);
		CorbaObserverStore.eINSTANCE.removeLogObserver(rtc);
		//
		return result;
	}

	public void save(OpenRTM.LogRecord record) {
		LOGGER.info("publish({}, {}, {})", LEVEL_NAMES[record.level.value()],
				record.loggername, record.message);
		RTCLogStore.eINSTANCE.save(serviceProfile.id, rtcName, record);
	}

	static class LoggerPOAImpl extends OpenRTM.LoggerPOA {
		CorbaLogObserverImpl parent;

		public LoggerPOAImpl(CorbaLogObserverImpl parent) {
			this.parent = parent;
		}

		@Override
		public void publish(OpenRTM.LogRecord record) {
			parent.save(record);
		}

		@Override
		public void close() {
			// TODO Auto-generated method stub

		}
	}

} //CorbaLogObserverImpl
