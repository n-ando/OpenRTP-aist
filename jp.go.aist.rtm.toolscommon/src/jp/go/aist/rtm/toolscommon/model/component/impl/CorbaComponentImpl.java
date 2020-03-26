/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import static jp.go.aist.rtm.toolscommon.model.component.impl.ExecutionContextImpl.EC_STATUS_LABEL;
import static jp.go.aist.rtm.toolscommon.model.component.impl.ExecutionContextImpl.RTC_STATUS;
import static jp.go.aist.rtm.toolscommon.model.component.impl.ExecutionContextImpl.RTC_STATUS_LABEL;
import static jp.go.aist.rtm.toolscommon.util.RTMixin.eql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.views.properties.IPropertySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import RTC.ComponentProfile;
import RTC.RTObject;
import RTC.ReturnCode_t;
import _SDOPackage.Configuration;
import _SDOPackage.InternalError;
import _SDOPackage.InvalidParameter;
import _SDOPackage.NotAvailable;
import _SDOPackage.Organization;
import _SDOPackage.SDO;
import jp.go.aist.rtm.toolscommon.factory.CorbaWrapperFactory;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.ContextHandler;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.CorbaLogObserver;
import jp.go.aist.rtm.toolscommon.model.component.CorbaPortSynchronizer;
import jp.go.aist.rtm.toolscommon.model.component.CorbaStatusObserver;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.IPropertyMap;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.util.CorbaObjectStore;
import jp.go.aist.rtm.toolscommon.model.component.util.CorbaObserverStore;
import jp.go.aist.rtm.toolscommon.model.component.util.CorbaPropertyMap;
import jp.go.aist.rtm.toolscommon.model.component.util.ICorbaPortEventObserver;
import jp.go.aist.rtm.toolscommon.model.core.CorbaWrapperObject;
import jp.go.aist.rtm.toolscommon.model.core.CorePackage;
import jp.go.aist.rtm.toolscommon.synchronizationframework.LocalObject;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationManager;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationSupport;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.AttributeMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ClassMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ConstructorParamMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ManyReferenceMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.MappingRule;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ReferenceMapping;
import jp.go.aist.rtm.toolscommon.ui.propertysource.ComponentPropertySource;
import jp.go.aist.rtm.toolscommon.util.SDOUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Corba Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl#getCorbaObject <em>Corba Object</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl#getRTCComponentProfile <em>RTC Component Profile</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl#getRTCExecutionContexts <em>RTC Execution Contexts</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl#getRTCParticipationContexts <em>RTC Participation Contexts</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl#getSDOConfiguration <em>SDO Configuration</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl#getSDOOrganization <em>SDO Organization</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl#getRTCRTObjects <em>RTCRT Objects</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl#getComponentState <em>Component State</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl#getIor <em>Ior</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl#getStatusObserver <em>Status Observer</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl#getLogObserver <em>Log Observer</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CorbaComponentImpl extends ComponentImpl implements CorbaComponent {

	private static final Logger LOGGER = LoggerFactory.getLogger(CorbaComponentImpl.class);

	/** ReturnCode値を内部値へ変換します。 */
	public static int RTC_RETURN_CODE(ReturnCode_t ret) {
		if (ret == null) {
			return RETURNCODE_ERROR;
		}
		return ret.value();
	}

	/** ReturnCodeをラベルへ変換します。 */
	public static String RTC_RETURN_CODE_NAME(ReturnCode_t ret) {
		return RTC_RETURN_CODE_NAME(ret == null ? -128 : ret.value());
	}

	/** ReturnCodeの内部値をラベルへ変換します。 */
	public static String RTC_RETURN_CODE_NAME(int ret) {
		if (ret == RETURNCODE_OK) {
			return "OK";
		} else if (ret == RETURNCODE_ERROR) {
			return "ERROR";
		} else if (ret == RETURNCODE_BAD_PARAMETER) {
			return "BAD_PARAMETER";
		} else if (ret == RETURNCODE_UNSUPPORTED) {
			return "UNSUPPORTED";
		} else if (ret == RETURNCODE_OUT_OF_RESOURCES) {
			return "OUT_OF_RESOURCES";
		} else if (ret == RETURNCODE_PRECONDITION_NOT_MET) {
			return "PRECONDITION_NOT_MET";
		} else {
			return "UNKNOWN";
		}
	}

	/**
	 * The default value of the '{@link #getCorbaObject() <em>Corba Object</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCorbaObject()
	 * @generated
	 * @ordered
	 */
	protected static final org.omg.CORBA.Object CORBA_OBJECT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCorbaObject() <em>Corba Object</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCorbaObject()
	 * @generated
	 * @ordered
	 */
	protected org.omg.CORBA.Object corbaObject = CORBA_OBJECT_EDEFAULT;

	/**
	 * The default value of the '{@link #getRTCComponentProfile() <em>RTC Component Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRTCComponentProfile()
	 * @generated
	 * @ordered
	 */
	protected static final ComponentProfile RTC_COMPONENT_PROFILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRTCComponentProfile() <em>RTC Component Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRTCComponentProfile()
	 * @generated
	 * @ordered
	 */
	protected ComponentProfile rTCComponentProfile = RTC_COMPONENT_PROFILE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRTCExecutionContexts() <em>RTC Execution Contexts</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRTCExecutionContexts()
	 * @generated NOT
	 * @ordered
	 */
	protected EList<RTC.ExecutionContext> rTCExecutionContexts;

	/**
	 * The cached value of the '{@link #getRTCParticipationContexts() <em>RTC Participation Contexts</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRTCParticipationContexts()
	 * @generated NOT
	 * @ordered
	 */
	protected EList<RTC.ExecutionContext> rTCParticipationContexts;

	/**
	 * The default value of the '{@link #getSDOConfiguration() <em>SDO Configuration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSDOConfiguration()
	 * @generated
	 * @ordered
	 */
	protected static final Configuration SDO_CONFIGURATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSDOConfiguration() <em>SDO Configuration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSDOConfiguration()
	 * @generated
	 * @ordered
	 */
	protected Configuration sDOConfiguration = SDO_CONFIGURATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getSDOOrganization() <em>SDO Organization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSDOOrganization()
	 * @generated NOT
	 * @ordered
	 */
	protected static final Organization SDO_ORGANIZATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSDOOrganization() <em>SDO Organization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSDOOrganization()
	 * @generated
	 * @ordered
	 */
	protected Organization sDOOrganization = SDO_ORGANIZATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRTCRTObjects() <em>RTCRT Objects</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRTCRTObjects()
	 * @generated
	 * @ordered
	 */
	protected EList<RTObject> rTCRTObjects;

	/**
	 * The default value of the '{@link #getComponentState() <em>Component State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentState()
	 * @generated
	 * @ordered
	 */
	protected static final int COMPONENT_STATE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getComponentState() <em>Component State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentState()
	 * @generated
	 * @ordered
	 */
	protected int componentState = COMPONENT_STATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIor() <em>Ior</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIor()
	 * @generated
	 * @ordered
	 */
	protected static final String IOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIor() <em>Ior</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIor()
	 * @generated
	 * @ordered
	 */
	protected String ior = IOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getStatusObserver() <em>Status Observer</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatusObserver()
	 * @generated
	 * @ordered
	 */
	protected CorbaStatusObserver statusObserver;

	/**
	 * The cached value of the '{@link #getLogObserver() <em>Log Observer</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLogObserver()
	 * @generated
	 * @ordered
	 */
	protected CorbaLogObserver logObserver;

	protected IPropertyMap properties;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public CorbaComponentImpl() {
		super();
		this.properties = new CorbaPropertyMap() {
			@Override
			public _SDOPackage.NameValue[] getNameValues() {
				if (getRTCComponentProfile() == null || getRTCComponentProfile().properties == null) {
					return new _SDOPackage.NameValue[0];
				}
				return getRTCComponentProfile().properties;
			}

			@Override
			public void setNameValues(_SDOPackage.NameValue[] nvs) {
				getRTCComponentProfile().properties = nvs;
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
		return ComponentPackage.Literals.CORBA_COMPONENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.omg.CORBA.Object getCorbaObject() {
		return corbaObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCorbaObject(org.omg.CORBA.Object newCorbaObject) {
		org.omg.CORBA.Object oldCorbaObject = corbaObject;
		corbaObject = newCorbaObject;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CORBA_COMPONENT__CORBA_OBJECT, oldCorbaObject, corbaObject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Configuration getSDOConfiguration() {
		try {
			RTC.RTObject ro = getCorbaObjectInterface();
			_SDOPackage.Configuration conf = ro.get_configuration();
			setSDOConfiguration(conf);
		} catch (Exception e) {
			// void
		}
		return sDOConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSDOConfiguration(Configuration newSDOConfiguration) {
		Configuration oldSDOConfiguration = sDOConfiguration;
		sDOConfiguration = newSDOConfiguration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CORBA_COMPONENT__SDO_CONFIGURATION, oldSDOConfiguration, sDOConfiguration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ComponentProfile getRTCComponentProfile() {
		return rTCComponentProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRTCComponentProfile(ComponentProfile newRTCComponentProfile) {
		ComponentProfile oldRTCComponentProfile = rTCComponentProfile;
		rTCComponentProfile = newRTCComponentProfile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CORBA_COMPONENT__RTC_COMPONENT_PROFILE, oldRTCComponentProfile, rTCComponentProfile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<RTC.ExecutionContext> getRTCExecutionContexts() {
		if (rTCExecutionContexts == null) {
			rTCExecutionContexts = new EDataTypeUniqueEList<RTC.ExecutionContext>(RTC.ExecutionContext.class, this, ComponentPackage.CORBA_COMPONENT__RTC_EXECUTION_CONTEXTS);
		}
		return rTCExecutionContexts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<RTC.ExecutionContext> getRTCParticipationContexts() {
		if (rTCParticipationContexts == null) {
			rTCParticipationContexts = new EDataTypeEList<RTC.ExecutionContext>(RTC.ExecutionContext.class, this, ComponentPackage.CORBA_COMPONENT__RTC_PARTICIPATION_CONTEXTS);
		}
		return rTCParticipationContexts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Organization getSDOOrganization() {
		try {
			RTC.RTObject ro = getCorbaObjectInterface();
			Organization[] orgs = ro.get_owned_organizations();
			if (orgs == null || orgs.length == 0) {
				setSDOOrganization(new NullSDOOrganization());
			} else {
				setSDOOrganization(orgs[0]); // １つ目固定
			}
		} catch (Exception e) {
			setSDOOrganization(new NullSDOOrganization());
		}
		return sDOOrganization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSDOOrganization(Organization newSDOOrganization) {
		Organization oldSDOOrganization = sDOOrganization;
		sDOOrganization = newSDOOrganization;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CORBA_COMPONENT__SDO_ORGANIZATION, oldSDOOrganization, sDOOrganization));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<RTObject> getRTCRTObjects() {
		if (rTCRTObjects == null) {
			rTCRTObjects = new EDataTypeEList<RTObject>(RTObject.class, this, ComponentPackage.CORBA_COMPONENT__RTCRT_OBJECTS);
		}
		return rTCRTObjects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getIor() {
		return ior;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIor(String newIor) {
		String oldIor = ior;
		ior = newIor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CORBA_COMPONENT__IOR, oldIor, ior));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public CorbaStatusObserver getStatusObserver() {
		return CorbaObserverStore.eINSTANCE
				.findStatusObserver(getCorbaObjectInterface());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CorbaStatusObserver basicGetStatusObserver() {
		return statusObserver;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void setStatusObserver(CorbaStatusObserver newStatusObserver) {
		CorbaObserverStore.eINSTANCE.registStatusObserver(
				getCorbaObjectInterface(), newStatusObserver);
		//
		CorbaStatusObserver oldStatusObserver = statusObserver;
		statusObserver = newStatusObserver;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CORBA_COMPONENT__STATUS_OBSERVER, oldStatusObserver, statusObserver));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public CorbaLogObserver getLogObserver() {
		return CorbaObserverStore.eINSTANCE
				.findLogObserver(getCorbaObjectInterface());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CorbaLogObserver basicGetLogObserver() {
		return logObserver;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setLogObserver(CorbaLogObserver newLogObserver) {
		CorbaObserverStore.eINSTANCE.registLogObserver(
				getCorbaObjectInterface(), newLogObserver);
		//
		CorbaLogObserver oldLogObserver = logObserver;
		logObserver = newLogObserver;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CORBA_COMPONENT__LOG_OBSERVER, oldLogObserver, logObserver));
	}

	@Override
	public RTC.RTObject getCorbaObjectInterface() {
		return RTC.RTObjectHelper.narrow(getCorbaObject());
	}

	@Override
	public boolean hasComponentAction() {
		return true;
	}

	@Override
	public ContextHandler getExecutionContextHandler() {
		if (executionContextHandler == null) {
			setExecutionContextHandler(new CorbaContextHandlerImpl());
		}
		return executionContextHandler;
	}

	@Override
	public ContextHandler getParticipationContextHandler() {
		if (participationContextHandler == null) {
			setParticipationContextHandler(new CorbaContextHandlerImpl());
		}
		return participationContextHandler;
	}

	// アクションのロギング (結果によってログレベルを切り替え)
	static void LOG_ACTION(String name, ReturnCode_t ret, RTC.ExecutionContext ec) {
		ReturnCode_t r = (ret == null) ? ReturnCode_t.RTC_ERROR : ret;
		if (r == ReturnCode_t.RTC_OK) {
			LOGGER.debug("{}: {} ec={}", name, RTC_RETURN_CODE_NAME(r), ec);
		} else {
			LOGGER.error("{}: {} ec={}", name, RTC_RETURN_CODE_NAME(r), ec);
		}
	}

	// アクションのロギング (結果によってログレベルを切り替え)
	static void LOG_ACTION(String name, ReturnCode_t ret, RTC.ExecutionContext ec, RTC.RTObject ro) {
		ReturnCode_t r = (ret == null) ? ReturnCode_t.RTC_ERROR : ret;
		if (r == ReturnCode_t.RTC_OK) {
			LOGGER.debug("{}: {} ec={} ro={}", name, RTC_RETURN_CODE_NAME(r), ec, ro);
		} else {
			LOGGER.error("{}: {} ec={} ro={}", name, RTC_RETURN_CODE_NAME(r), ec, ro);
		}
	}

	// アクションのロギング (結果によってログレベルを切り替え)
	static void LOG_ACTION(String name, ReturnCode_t ret, RTC.RTObject ro) {
		ReturnCode_t r = (ret == null) ? ReturnCode_t.RTC_ERROR : ret;
		if (r == ReturnCode_t.RTC_OK) {
			LOGGER.debug("{}: {} ro={}", name, RTC_RETURN_CODE_NAME(r), ro);
		} else {
			LOGGER.error("{}: {} ro={}", name, RTC_RETURN_CODE_NAME(r), ro);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int startR() {
		ExecutionContext pec = getPrimaryExecutionContext();
		ReturnCode_t ret = ReturnCode_t.RTC_ERROR;
		RTC.ExecutionContext ec = null;
		if (pec != null && pec instanceof CorbaExecutionContext) {
			ec = ((CorbaExecutionContext) pec).getCorbaObjectInterface();
			ret = ec.start();
		}
		LOG_ACTION("startR", ret, ec);
		return ret.value();
	}

	@Override
	public void startAll() {
		for (RTC.ExecutionContext ec : getRTCExecutionContexts()) {
			ReturnCode_t ret = ec.start();
			LOG_ACTION("startAll", ret, ec);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int stopR() {
		ExecutionContext pec = getPrimaryExecutionContext();
		ReturnCode_t ret = ReturnCode_t.RTC_ERROR;
		RTC.ExecutionContext ec = null;
		if (pec != null && pec instanceof CorbaExecutionContext) {
			ec = ((CorbaExecutionContext) pec).getCorbaObjectInterface();
			ret = ec.stop();
		}
		LOG_ACTION("stopR", ret, ec);
		return ret.value();
	}

	@Override
	public void stopAll() {
		for (RTC.ExecutionContext ec : getRTCExecutionContexts()) {
			ReturnCode_t ret = ec.stop();
			LOG_ACTION("stopAll", ret, ec);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void attachPortEventObserver(ICorbaPortEventObserver observer) {
		List<ICorbaPortEventObserver> list = CorbaObserverStore.eINSTANCE.findPortEventObserver(this.getCorbaObjectInterface());
		if (!list.contains(observer)) {
			LOGGER.info("attachPortEventObserver: regist obs=<{}> rtc=<{}>", observer, this.getCorbaObject());
			CorbaObserverStore.eINSTANCE.registPortEventObserver(this.getCorbaObjectInterface(), observer);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void detatchPortEventObserver(ICorbaPortEventObserver observer) {
		List<ICorbaPortEventObserver> list = CorbaObserverStore.eINSTANCE.findPortEventObserver(this.getCorbaObjectInterface());
		if (list.contains(observer)) {
			LOGGER.info("detatchPortEventObserver: regist obs=<{}> rtc=<{}>", observer, this.getCorbaObject());
			CorbaObserverStore.eINSTANCE.removePortEventObserver(this.getCorbaObjectInterface(), observer);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int activateR() {
		ExecutionContext pec = getPrimaryExecutionContext();
		ReturnCode_t ret = ReturnCode_t.RTC_ERROR;
		RTC.ExecutionContext ec = null;
		RTC.RTObject ro = null;
		if (pec != null && pec instanceof CorbaExecutionContext) {
			ec = ((CorbaExecutionContext) pec).getCorbaObjectInterface();
			ro = getCorbaObjectInterface();
			ret = ec.activate_component(ro);
		}
		LOG_ACTION("activateR", ret, ec, ro);
		return ret.value();
	}

	@Override
	public void activateAll() {
		for (RTC.ExecutionContext ec : getRTCExecutionContexts()) {
			RTC.RTObject ro = getCorbaObjectInterface();
			ReturnCode_t ret = ec.activate_component(ro);
			LOG_ACTION("activateAll", ret, ec, ro);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int deactivateR() {
		ExecutionContext pec = getPrimaryExecutionContext();
		ReturnCode_t ret = ReturnCode_t.RTC_ERROR;
		RTC.ExecutionContext ec = null;
		RTC.RTObject ro = null;
		if (pec != null && pec instanceof CorbaExecutionContext) {
			ec = ((CorbaExecutionContext) pec).getCorbaObjectInterface();
			ro = getCorbaObjectInterface();
			ret = ec.deactivate_component(ro);
		}
		LOG_ACTION("deactivateR", ret, ec, ro);
		return ret.value();
	}

	@Override
	public void deactivateAll() {
		for (RTC.ExecutionContext ec : getRTCExecutionContexts()) {
			RTC.RTObject ro = getCorbaObjectInterface();
			ReturnCode_t ret = ec.deactivate_component(ro);
			LOG_ACTION("deactivateAll", ret, ec, ro);
			if (ret == ReturnCode_t.RTC_ERROR) {
				ec.reset_component(ro);
				LOG_ACTION("deactivateAll - reset_component", ret, ec, ro);
			}
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int resetR() {
		ExecutionContext pec = getPrimaryExecutionContext();
		ReturnCode_t ret = ReturnCode_t.RTC_ERROR;
		RTC.ExecutionContext ec = null;
		RTC.RTObject ro = null;
		if (pec != null && pec instanceof CorbaExecutionContext) {
			ec = ((CorbaExecutionContext) pec).getCorbaObjectInterface();
			ro = getCorbaObjectInterface();
			ret = ec.reset_component(ro);
		}
		LOG_ACTION("resetR", ret, ec, ro);
		return ret.value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int finalizeR() {
		ReturnCode_t ret = ReturnCode_t.RTC_ERROR;
		RTC.RTObject ro = getCorbaObjectInterface();
		ret = ro._finalize();
		LOG_ACTION("finalizeR", ret, ro);
		return ret.value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int exitR() {
		ReturnCode_t ret = ReturnCode_t.RTC_ERROR;
		RTC.RTObject ro = getCorbaObjectInterface();
		ret = ro.exit();
		LOG_ACTION("exitR", ret, ro);
		return ret.value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int getExecutionContextState() {
		return getExecutionContextState(getPrimaryExecutionContext());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int getExecutionContextState(ExecutionContext ec) {
		CorbaExecutionContext cec = (CorbaExecutionContext) ec;
		if (cec != null) {
			return cec.getStateL();
		}
		return STATE_UNKNOWN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getExecutionContextStateName() {
		return EC_STATUS_LABEL(getExecutionContextState());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getExecutionContextStateName(ExecutionContext ec) {
		return EC_STATUS_LABEL(getExecutionContextState(ec));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getComponentState() {
		return componentState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponentState(int newComponentState) {
		int oldComponentState = componentState;
		componentState = newComponentState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CORBA_COMPONENT__COMPONENT_STATE, oldComponentState, componentState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getComponentStateName() {
		return RTC_STATUS_LABEL(getComponentState());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ComponentPackage.CORBA_COMPONENT__CORBA_OBJECT:
				return getCorbaObject();
			case ComponentPackage.CORBA_COMPONENT__RTC_COMPONENT_PROFILE:
				return getRTCComponentProfile();
			case ComponentPackage.CORBA_COMPONENT__RTC_EXECUTION_CONTEXTS:
				return getRTCExecutionContexts();
			case ComponentPackage.CORBA_COMPONENT__RTC_PARTICIPATION_CONTEXTS:
				return getRTCParticipationContexts();
			case ComponentPackage.CORBA_COMPONENT__SDO_CONFIGURATION:
				return getSDOConfiguration();
			case ComponentPackage.CORBA_COMPONENT__SDO_ORGANIZATION:
				return getSDOOrganization();
			case ComponentPackage.CORBA_COMPONENT__RTCRT_OBJECTS:
				return getRTCRTObjects();
			case ComponentPackage.CORBA_COMPONENT__COMPONENT_STATE:
				return getComponentState();
			case ComponentPackage.CORBA_COMPONENT__IOR:
				return getIor();
			case ComponentPackage.CORBA_COMPONENT__STATUS_OBSERVER:
				if (resolve) return getStatusObserver();
				return basicGetStatusObserver();
			case ComponentPackage.CORBA_COMPONENT__LOG_OBSERVER:
				if (resolve) return getLogObserver();
				return basicGetLogObserver();
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
			case ComponentPackage.CORBA_COMPONENT__CORBA_OBJECT:
				setCorbaObject((org.omg.CORBA.Object)newValue);
				return;
			case ComponentPackage.CORBA_COMPONENT__RTC_COMPONENT_PROFILE:
				setRTCComponentProfile((ComponentProfile)newValue);
				return;
			case ComponentPackage.CORBA_COMPONENT__RTC_EXECUTION_CONTEXTS:
				getRTCExecutionContexts().clear();
				getRTCExecutionContexts().addAll((Collection<? extends RTC.ExecutionContext>)newValue);
				return;
			case ComponentPackage.CORBA_COMPONENT__RTC_PARTICIPATION_CONTEXTS:
				getRTCParticipationContexts().clear();
				getRTCParticipationContexts().addAll((Collection<? extends RTC.ExecutionContext>)newValue);
				return;
			case ComponentPackage.CORBA_COMPONENT__SDO_CONFIGURATION:
				setSDOConfiguration((Configuration)newValue);
				return;
			case ComponentPackage.CORBA_COMPONENT__SDO_ORGANIZATION:
				setSDOOrganization((Organization)newValue);
				return;
			case ComponentPackage.CORBA_COMPONENT__RTCRT_OBJECTS:
				getRTCRTObjects().clear();
				getRTCRTObjects().addAll((Collection<? extends RTObject>)newValue);
				return;
			case ComponentPackage.CORBA_COMPONENT__COMPONENT_STATE:
				setComponentState((Integer)newValue);
				return;
			case ComponentPackage.CORBA_COMPONENT__IOR:
				setIor((String)newValue);
				return;
			case ComponentPackage.CORBA_COMPONENT__STATUS_OBSERVER:
				setStatusObserver((CorbaStatusObserver)newValue);
				return;
			case ComponentPackage.CORBA_COMPONENT__LOG_OBSERVER:
				setLogObserver((CorbaLogObserver)newValue);
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
			case ComponentPackage.CORBA_COMPONENT__CORBA_OBJECT:
				setCorbaObject(CORBA_OBJECT_EDEFAULT);
				return;
			case ComponentPackage.CORBA_COMPONENT__RTC_COMPONENT_PROFILE:
				setRTCComponentProfile(RTC_COMPONENT_PROFILE_EDEFAULT);
				return;
			case ComponentPackage.CORBA_COMPONENT__RTC_EXECUTION_CONTEXTS:
				getRTCExecutionContexts().clear();
				return;
			case ComponentPackage.CORBA_COMPONENT__RTC_PARTICIPATION_CONTEXTS:
				getRTCParticipationContexts().clear();
				return;
			case ComponentPackage.CORBA_COMPONENT__SDO_CONFIGURATION:
				setSDOConfiguration(SDO_CONFIGURATION_EDEFAULT);
				return;
			case ComponentPackage.CORBA_COMPONENT__SDO_ORGANIZATION:
				setSDOOrganization(SDO_ORGANIZATION_EDEFAULT);
				return;
			case ComponentPackage.CORBA_COMPONENT__RTCRT_OBJECTS:
				getRTCRTObjects().clear();
				return;
			case ComponentPackage.CORBA_COMPONENT__COMPONENT_STATE:
				setComponentState(COMPONENT_STATE_EDEFAULT);
				return;
			case ComponentPackage.CORBA_COMPONENT__IOR:
				setIor(IOR_EDEFAULT);
				return;
			case ComponentPackage.CORBA_COMPONENT__STATUS_OBSERVER:
				setStatusObserver((CorbaStatusObserver)null);
				return;
			case ComponentPackage.CORBA_COMPONENT__LOG_OBSERVER:
				setLogObserver((CorbaLogObserver)null);
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
			case ComponentPackage.CORBA_COMPONENT__CORBA_OBJECT:
				return CORBA_OBJECT_EDEFAULT == null ? corbaObject != null : !CORBA_OBJECT_EDEFAULT.equals(corbaObject);
			case ComponentPackage.CORBA_COMPONENT__RTC_COMPONENT_PROFILE:
				return RTC_COMPONENT_PROFILE_EDEFAULT == null ? rTCComponentProfile != null : !RTC_COMPONENT_PROFILE_EDEFAULT.equals(rTCComponentProfile);
			case ComponentPackage.CORBA_COMPONENT__RTC_EXECUTION_CONTEXTS:
				return rTCExecutionContexts != null && !rTCExecutionContexts.isEmpty();
			case ComponentPackage.CORBA_COMPONENT__RTC_PARTICIPATION_CONTEXTS:
				return rTCParticipationContexts != null && !rTCParticipationContexts.isEmpty();
			case ComponentPackage.CORBA_COMPONENT__SDO_CONFIGURATION:
				return SDO_CONFIGURATION_EDEFAULT == null ? sDOConfiguration != null : !SDO_CONFIGURATION_EDEFAULT.equals(sDOConfiguration);
			case ComponentPackage.CORBA_COMPONENT__SDO_ORGANIZATION:
				return SDO_ORGANIZATION_EDEFAULT == null ? sDOOrganization != null : !SDO_ORGANIZATION_EDEFAULT.equals(sDOOrganization);
			case ComponentPackage.CORBA_COMPONENT__RTCRT_OBJECTS:
				return rTCRTObjects != null && !rTCRTObjects.isEmpty();
			case ComponentPackage.CORBA_COMPONENT__COMPONENT_STATE:
				return componentState != COMPONENT_STATE_EDEFAULT;
			case ComponentPackage.CORBA_COMPONENT__IOR:
				return IOR_EDEFAULT == null ? ior != null : !IOR_EDEFAULT.equals(ior);
			case ComponentPackage.CORBA_COMPONENT__STATUS_OBSERVER:
				return statusObserver != null;
			case ComponentPackage.CORBA_COMPONENT__LOG_OBSERVER:
				return logObserver != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == CorbaWrapperObject.class) {
			switch (derivedFeatureID) {
				case ComponentPackage.CORBA_COMPONENT__CORBA_OBJECT: return CorePackage.CORBA_WRAPPER_OBJECT__CORBA_OBJECT;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == CorbaWrapperObject.class) {
			switch (baseFeatureID) {
				case CorePackage.CORBA_WRAPPER_OBJECT__CORBA_OBJECT: return ComponentPackage.CORBA_COMPONENT__CORBA_OBJECT;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (corbaObject: ");
		result.append(corbaObject);
		result.append(", rTCComponentProfile: ");
		result.append(rTCComponentProfile);
		result.append(", rTCExecutionContexts: ");
		result.append(rTCExecutionContexts);
		result.append(", rTCParticipationContexts: ");
		result.append(rTCParticipationContexts);
		result.append(", sDOConfiguration: ");
		result.append(sDOConfiguration);
		result.append(", sDOOrganization: ");
		result.append(sDOOrganization);
		result.append(", rTCRTObjects: ");
		result.append(rTCRTObjects);
		result.append(", componentState: ");
		result.append(componentState);
		result.append(", ior: ");
		result.append(ior);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean updateConfigurationSetListR(List localConfigurationSets,
			ConfigurationSet localActiveConfigurationSet, List originalConfigurationSets) {
		try {
			_SDOPackage.Configuration configuration = getCorbaObjectInterface().get_configuration();
			for (Object o : localConfigurationSets) {
				ConfigurationSet local = (ConfigurationSet) o;
				ConfigurationSet updated = ComponentFactory.eINSTANCE.createConfigurationSet();
				updated.setId(local.getId());
				for (int i = 0; i < local.getConfigurationData().size(); i++) {
					NameValue lnv = (NameValue) local.getConfigurationData().get(i);
					NameValue nv = ComponentFactory.eINSTANCE.createNameValue();
					nv.setName(lnv.getName());
					nv.setValue(lnv.getValue());
					updated.getConfigurationData().add(nv);
				}
				boolean result = configuration.set_configuration_set_values(SDOUtil.createSdoConfigurationSet(updated));
				if (!result) {
					result = configuration.add_configuration_set(SDOUtil.createSdoConfigurationSet(updated));
				}
				if (!result) {
					return false;
				}
			}
			if (localActiveConfigurationSet != null) {
				boolean result = configuration.activate_configuration_set(localActiveConfigurationSet.getId());
				if (!result) {
					return false;
				}
			}
		} catch (Exception e) {
			LOGGER.error("Fail to activate configuration-set", e);
			return false;
		}
		return true;
	}

	@Override
	public boolean setComponentsR(List<Component> componentList) {
		try {
			Organization org = getSDOOrganization();
			if (org == null) {
				return false;
			}
			List<SDO> list = new ArrayList<SDO>();
			for (Object obj : componentList) {
				if (!(obj instanceof CorbaComponent)) {
					continue;
				}
				CorbaComponent comp = (CorbaComponent) obj;
				RTObject ro = comp.getCorbaObjectInterface();
				if (list.contains(ro)) {
					continue; // 重複除外
				}
				list.add(ro);
				if (!getComponents().contains(comp)) {
					addComponent(comp);
				}
			}
			LOGGER.trace("setComponentsR: members=<{}>", list);
			return org.set_members(list.toArray(new SDO[0]));
		} catch (NotAvailable e) {
			LOGGER.error("Fail to set members (not available)", e);
		} catch (InternalError e) {
			LOGGER.error("Fail to set members (internal)", e);
		} catch (InvalidParameter e) {
			LOGGER.error("Fail to set members (invalid parameter)", e);
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean addComponentsR(List<Component> componentList) {
		try {
			Organization org = getSDOOrganization();
			if (org == null) {
				return false;
			}
			List<SDO> list = new ArrayList<SDO>();
			EList<Component> components = getComponents();
			for (Component c : componentList) {
				if (!(c instanceof CorbaComponent)) {
					continue;
				}
				CorbaComponent comp = (CorbaComponent) c;
				RTObject ro = comp.getCorbaObjectInterface();
				// リモートオブジェクトが一致するコンポーネントがなければ、
				// 自身を追加する(同期処理で子コンポーネントを複製防止)
				if (this._findChildComponentByRemoteObject(ro) == null) {
					list.add(ro);
					components.add(comp);
				}
			}
			LOGGER.trace("addComponentsR: members=<{}>", list);
			return org.add_members(list.toArray(new SDO[0]));
		} catch (NotAvailable e) {
			LOGGER.error("Fail to add members (not available)", e);
		} catch (InternalError e) {
			LOGGER.error("Fail to add members (internal)", e);
		} catch (InvalidParameter e) {
			LOGGER.error("Fail to add members (invalid parameter)", e);
		}
		return false;
	}

	/**
	 * this.componentsからremoteとリモートオブジェクトが一致するコンポーネントを検索します
	 */
	CorbaComponent _findChildComponentByRemoteObject(Object remote) {
		EList<Component> components = getComponents();
		for (Component c : components) {
			if (!(c instanceof CorbaComponent)) {
				continue;
			}
			CorbaComponent local = (CorbaComponent) c;
			if (local.getCorbaObject() != null
					&& local.getCorbaObject().equals(remote)) {
				return local;
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
	public boolean removeComponentR(Component component) {
		try {
			Organization org = getSDOOrganization();
			if (org == null) {
				return false;
			}
			CorbaComponent corbaComponent = (CorbaComponent) component;
			if (corbaComponent == null) {
				return false;
			}
			RTObject ro = corbaComponent.getCorbaObjectInterface();
			LOGGER.trace("removeComponentR: member=<{}>", ro);
			if (ro == null) {
				return false;
			}
			return org.remove_member(ro.get_sdo_id());
		} catch (NotAvailable e) {
			LOGGER.error("Fail to remove member (not available)", e);
		} catch (InternalError e) {
			LOGGER.error("Fail to remove member (internal)", e);
		} catch (InvalidParameter e) {
			LOGGER.error("Fail to remove member (invalid parameter)", e);
		}
		return false;
	}

	@Override
	public java.lang.Object getAdapter(Class adapter) {
		java.lang.Object result = null;
		if (IPropertySource.class.equals(adapter)) {
			result = new ComponentPropertySource(this);
		}

		if (result == null) {
			result = super.getAdapter(adapter);
		}
		return result;
	}

	@Override
	public boolean updateConfigurationSetR(ConfigurationSet configSet, boolean isActive) {
		boolean result = false;
		try {
			boolean exist = false;
			for (Object o : this.getConfigurationSets()) {
				ConfigurationSet cs = (ConfigurationSet) o;
				if (cs.getId().equals(configSet.getId())) {
					exist = true;
					break;
				}
			}
			Configuration configuration = getSDOConfiguration();
			_SDOPackage.ConfigurationSet sdoConfigurationSet =
				SDOUtil.createSdoConfigurationSet(configSet);
			if (!exist) {
				configuration.add_configuration_set(sdoConfigurationSet);
			} else {
				configuration.set_configuration_set_values(sdoConfigurationSet);
			}
			if (isActive){
				configuration.activate_configuration_set(configSet.getId());
			}
			result = true;
		} catch (Exception e) {
			LOGGER.error("Fail to update configuration-set", e);
			result = false;
		}
		return result;
	}

	public org.omg.CORBA.Object getCorbaBaseObject() {
		return corbaObject;
	}

	@Override
	public String getComponentId() {
		if (componentId != null) return componentId;
		return "RTC:" + getVenderL() + ":"
		+ getCategoryL() + ":"
		+ getTypeNameL() + ":"
		+ getVersionL();
	}

	@Override
	public String getCategoryL() {
		return nvl((nvl(getRTCComponentProfile(), new ComponentProfile())).category, "");
	}

	@Override
	public String getDescriptionL() {
		return nvl((nvl(getRTCComponentProfile(), new ComponentProfile())).description, "");
	}

	@Override
	public String getInstanceNameL() {
		return nvl((nvl(getRTCComponentProfile(), new ComponentProfile())).instance_name, "");
	}

	@Override
	public String getTypeNameL() {
		return nvl((nvl(getRTCComponentProfile(), new ComponentProfile())).type_name, "");
	}

	@Override
	public String getVenderL() {
		return nvl((nvl(getRTCComponentProfile(), new ComponentProfile())).vendor, "");
	}

	@Override
	public String getVersionL() {
		return nvl((nvl(getRTCComponentProfile(), new ComponentProfile())).version, "");
	}

	@Override
	public void setCategoryL(String newCategoryL) {
		initRTCComponentProfile();
		getRTCComponentProfile().category = newCategoryL;
	}

	@Override
	public void setDescriptionL(String newDescriptionL) {
		initRTCComponentProfile();
		getRTCComponentProfile().description = newDescriptionL;
	}

	@Override
	public void setInstanceNameL(String newInstanceNameL) {
		initRTCComponentProfile();
		getRTCComponentProfile().instance_name = newInstanceNameL;
	}

	@Override
	public void setTypeNameL(String newTypeNameL) {
		initRTCComponentProfile();
		getRTCComponentProfile().type_name = newTypeNameL;
	}

	@Override
	public void setVenderL(String newVenderL) {
		initRTCComponentProfile();
		getRTCComponentProfile().vendor = newVenderL;
	}

	@Override
	public void setVersionL(String newVersionL) {
		initRTCComponentProfile();
		getRTCComponentProfile().version = newVersionL;
	}

	<T> T nvl(T t, T default_t) {
		return (t != null) ? t : default_t;
	}

	void initRTCComponentProfile() {
		if (getRTCComponentProfile() == null) {
			setRTCComponentProfile(new ComponentProfile());
		}
	}

	@Override
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	@Override
	public void setProperty(String key, String value) {
		properties.setProperty(key, value);
	}

	@Override
	public String removeProperty(String key) {
		return properties.removeProperty(key);
	}

	@Override
	public EList<String> getPropertyKeys() {
		return properties.getPropertyKeys();
	}

	@Override
	public IPropertyMap getPropertyMap() {
		return properties;
	}

	// Mapping Rule
	public static final MappingRule MAPPING_RULE = new MappingRule(
			null,
			new ClassMapping(
					CorbaComponentImpl.class,
					new ConstructorParamMapping[] { new ConstructorParamMapping(
							CorePackage.eINSTANCE
									.getCorbaWrapperObject_CorbaObject()) }) {
				@Override
				public boolean isTarget(LocalObject parent,
						Object[] remoteObjects, java.lang.Object link) {
					return remoteObjects[0] instanceof org.omg.CORBA.Object
							&& ((org.omg.CORBA.Object) remoteObjects[0])
									._is_a(RTC.RTObjectHelper.id());
				}

				@Override
				public boolean needsPing() {
					return true;
				}

				@Override
				public Object[] narrow(Object[] remoteObjects) {
					return new Object[] { RTC.RTObjectHelper
							.narrow(((org.omg.CORBA.Object) remoteObjects[0])) };
				}
			}, new AttributeMapping[] {}, new ReferenceMapping[] {});

	@Override
	public void synchronizeRemoteAttribute(EStructuralFeature reference) {
		ComponentPackage pkg = ComponentPackage.eINSTANCE;
		RTC.RTObject ro = getCorbaObjectInterface();
		if (pkg.getCorbaComponent_RTCComponentProfile().equals(reference) || reference == null) {
			synchronizeRemote_RTCComponentProfile();
		}
		//
		if (pkg.getCorbaComponent_RTCExecutionContexts().equals(reference) || reference == null) {
			synchronizeRemote_RTCExecutionContexts();
			// owned context
			RTC.ExecutionContext[] oec = CorbaObjectStore.eINSTANCE.findOwnedContexts(ro);
			if (oec != null) {
				for (RTC.ExecutionContext ec : oec) {
					// ec profile
					synchronizeRemote_EC_ECProfile(ec);
					// ec state
					synchronizeRemote_EC_ECState(ec);
					// component state
					synchronizeRemote_EC_ComponentState(ec);
				}
			}
			// participating context
			RTC.ExecutionContext[] pec = CorbaObjectStore.eINSTANCE.findParticipatingContexts(ro);
			if (pec != null) {
				for (RTC.ExecutionContext ec : pec) {
					// component state
					synchronizeRemote_EC_ComponentState(ec);
				}
			}
		}
		//
		if (pkg.getComponent_ConfigurationSets().equals(reference) || reference == null) {
			synchronizeRemote_ConfigurationSets();
		}
		//
		if (pkg.getComponent_ActiveConfigurationSet().equals(reference) || reference == null) {
			synchronizeRemote_ActiveConfigurationSet();
		}
		//
		if (pkg.getCorbaComponent_RTCRTObjects().equals(reference) || reference == null) {
			synchronizeRemote_RTCRTObjects();
		}
	}

	@Override
	public void synchronizeLocalAttribute(EStructuralFeature reference) {
		ComponentPackage pkg = ComponentPackage.eINSTANCE;
		if (pkg.getCorbaComponent_RTCComponentProfile().equals(reference) || reference == null) {
			synchronizeLocal_RTCComponentProfile();
		}
		//
		if (pkg.getCorbaComponent_RTCExecutionContexts().equals(reference) || reference == null) {
			synchronizeLocal_RTCExecutionContexts();
		}
		//
		if (pkg.getComponent_ConfigurationSets().equals(reference) || reference == null) {
			synchronizeLocal_ConfigurationSets();
		}
		//
		if (pkg.getComponent_ActiveConfigurationSet().equals(reference) || reference == null) {
			synchronizeLocal_ActiveConfigurationSet();
		}
		//
		if (pkg.getCorbaComponent_RTCRTObjects().equals(reference) || reference == null) {
			synchronizeLocal_RTCRTObjects();
		}
	}

	/** RTC.RTObject単位のオブジェクトDBのエントリを削除 */
	static void removeRemote_FullObject(RTC.RTObject ro) {
		LOGGER.debug("removeRemote_FullObject: START ro=<{}>", ro);
		//
		CorbaObjectStore.eINSTANCE.removeRTCProfile(ro);
		//
		CorbaObjectStore.eINSTANCE.removeOwnedContexts(ro);
		CorbaObjectStore.eINSTANCE.removeParticipatingContexts(ro);
		CorbaObjectStore.eINSTANCE.removePrimaryContext(ro);
		CorbaObjectStore.eINSTANCE.clearContext(ro);
		//
		CorbaObjectStore.eINSTANCE.removeActiveConfigSet(ro);
		CorbaObjectStore.eINSTANCE.removeConfigSet(ro);
		//
		CorbaObjectStore.eINSTANCE.removeCompositeMemberList(ro);
		//
		LOGGER.debug("removeRemote_FullObject: END");
	}

	/** RTC.ComponentProfileの同期 (CORBA=>オブジェクトDB) */
	public void synchronizeRemote_RTCComponentProfile() {
		RTC.RTObject ro = getCorbaObjectInterface();
		synchronizeRemote_RTCComponentProfile(ro);
	}

	public static void synchronizeRemote_RTCComponentProfile(RTC.RTObject ro) {
		synchronized (CorbaObjectStore.eINSTANCE) {
			try {
				RTC.ComponentProfile prof = ro.get_component_profile();
				CorbaObjectStore.eINSTANCE.registRTCProfile(ro, prof);
			} catch (Exception e) {
				removeRemote_FullObject(ro);
				throw new RuntimeException(String.format("Fail to sync RTCComponentProfile. ro=<%s>", ro), e);
			}
		}
	}

	/** RTC.PortProfileの同期 (CORBA=>オブジェクトDB) */
	public void synchronizeRemote_RTCPortProfile(String name) {
		RTC.RTObject ro = getCorbaObjectInterface();
		synchronizeRemote_RTCPortProfile(ro, name);
	}

	public static void synchronizeRemote_RTCPortProfile(RTC.RTObject ro, String name) {
		synchronized (CorbaObjectStore.eINSTANCE) {
			LOGGER.debug("synchronizeRemote_RTCPortProfile: name={} ro={}", name, ro);
			RTC.PortProfile prof = CorbaObjectStore.eINSTANCE.findRTCPortProfile(ro, name);
			if (prof != null) {
				try {
					RTC.PortProfile pprof = prof.port_ref.get_port_profile();
					CorbaObjectStore.eINSTANCE.registRTCPortProfile(ro, name, pprof);
				} catch (Exception e) {
					removeRemote_FullObject(ro);
					throw new RuntimeException(
							String.format("Fail to sync RTCPortProfile. ro=<%s> name=<%s>", ro, name), e);
				}
			}
		}
	}

	/** RTC.ComponentProfileの同期 (オブジェクトDB=>モデル) */
	public void synchronizeLocal_RTCComponentProfile() {
		RTC.RTObject ro = getCorbaObjectInterface();
		RTC.ComponentProfile prof = CorbaObjectStore.eINSTANCE.findRTCProfile(ro);
		if (!eql(getRTCComponentProfile(), prof)) {
			setRTCComponentProfile(prof);
		}
	}

	/** RTC.ExecutionContextの同期 (CORBA=>オブジェクトDB) */
	public void synchronizeRemote_RTCExecutionContexts() {
		RTC.RTObject ro = getCorbaObjectInterface();
		synchronizeRemote_RTCExecutionContexts(ro);
	}

	public static void synchronizeRemote_RTCExecutionContexts(RTC.RTObject ro) {
		synchronized (CorbaObjectStore.eINSTANCE) {
			// LOGGER.debug("synchronizeRemote_RTCExecutionContexts: ro={}", ro);
			boolean update = false;
			try {
				// owned context
				RTC.ExecutionContext[] oec = ro.get_owned_contexts();
				RTC.ExecutionContext[] oecOld = CorbaObjectStore.eINSTANCE.findOwnedContexts(ro);
				if (!eql(oec, oecOld)) {
					CorbaObjectStore.eINSTANCE.registOwnedContexts(ro, oec);
					update = true;
				}
				// participating context
				RTC.ExecutionContext[] pec = ro.get_participating_contexts();
				RTC.ExecutionContext[] pecOld = CorbaObjectStore.eINSTANCE.findParticipatingContexts(ro);
				if (!eql(pec, pecOld)) {
					CorbaObjectStore.eINSTANCE.registParticipatingContexts(ro, pec);
					update = true;
				}
				//
				if (update) {
					CorbaObjectStore.eINSTANCE.clearContext(ro);
					for (RTC.ExecutionContext ec : oec) {
						int handle = ro.get_context_handle(ec);
						CorbaObjectStore.eINSTANCE.registContext(ro, Integer.toString(handle), ec);
					}
					for (RTC.ExecutionContext ec : pec) {
						int handle = ro.get_context_handle(ec);
						CorbaObjectStore.eINSTANCE.registContext(ro, Integer.toString(handle), ec);
					}
				}
			} catch (Exception e) {
				removeRemote_FullObject(ro);
				throw new RuntimeException(String.format("Fail to sync RTCExecutionContexts. ro=<%s>", ro), e);
			}
		}
	}

	/** RTC.ExecutionContextの同期(comp_state) (CORBA=>オブジェクトDB) */
	public void synchronizeRemote_EC_ComponentState(RTC.ExecutionContext ec) {
		RTC.RTObject ro = getCorbaObjectInterface();
		synchronizeRemote_EC_ComponentState(ro, ec);
	}

	public static void synchronizeRemote_EC_ComponentState(RTC.RTObject ro, RTC.ExecutionContext ec) {
		synchronized (CorbaObjectStore.eINSTANCE) {
			// LOGGER.debug("synchronizeRemote_EC_ComponentState: ro={}", ro);
			try {
				RTC.LifeCycleState state = ec.get_component_state(ro);
				int stateValue = RTC_STATUS(state);
				CorbaObjectStore.eINSTANCE.registComponentState(ec, ro, new Integer(stateValue));
			} catch (Exception e) {
				CorbaObjectStore.eINSTANCE.removeComponentStateMap(ec);
				throw new RuntimeException(String.format("Fail to sync ComponentState. ec=<%s>", ec), e);
			}
		}
	}

	/** RTC.ExecutionContextの同期(ec_state) (CORBA=>オブジェクトDB) */
	public static void synchronizeRemote_EC_ECState(RTC.ExecutionContext ec) {
		synchronized (CorbaObjectStore.eINSTANCE) {
			// LOGGER.debug("synchronizeRemote_EC_ECState: ec={}", ec);
			try {
				int ecStateValue = ExecutionContext.STATE_UNKNOWN;
				if (ec.is_running()) {
					ecStateValue = ExecutionContext.STATE_RUNNING;
				} else {
					ecStateValue = ExecutionContext.STATE_STOPPED;
				}
				CorbaObjectStore.eINSTANCE.registECState(ec, ecStateValue);
			} catch (Exception e) {
				CorbaObjectStore.eINSTANCE.removeECState(ec);
				throw new RuntimeException(String.format("Fail to sync ECState. ec=<%s>", ec), e);
			}
		}
	}

	/** RTC.ExecutionContextの同期(ec_profile) (CORBA=>オブジェクトDB) */
	public static void synchronizeRemote_EC_ECProfile(RTC.ExecutionContext ec) {
		synchronized (CorbaObjectStore.eINSTANCE) {
			// LOGGER.debug("synchronizeRemote_EC_ECProfile: ec={}", ec);
			try {
				RTC.ExecutionContextProfile prof;
				if (ec._is_a(RTC.ExecutionContextServiceHelper.id())) {
					RTC.ExecutionContextService ecs = RTC.ExecutionContextServiceHelper.narrow(ec);
					prof = ecs.get_profile();
				} else {
					prof = new RTC.ExecutionContextProfile();
					prof.rate = ec.get_rate();
					prof.kind = ec.get_kind();
				}
				if (prof.owner == null) {
					RTObject owner = CorbaObjectStore.eINSTANCE.findContextOwner(ec);
					if (owner != null) {
						prof.owner = owner;
					}
				}
				CorbaObjectStore.eINSTANCE.registECProfile(ec, prof);
			} catch (Exception e) {
				CorbaObjectStore.eINSTANCE.removeECProfile(ec);
				throw new RuntimeException(String.format("Fail to sync ECProfile. ec=<%s>", ec), e);
			}
		}
	}

	/** RTC.ExecutionContextの同期 (オブジェクトDB=>モデル) */
	public void synchronizeLocal_RTCExecutionContexts() {
		RTC.RTObject ro = getCorbaObjectInterface();
		// owned context
		RTC.ExecutionContext[] oec = CorbaObjectStore.eINSTANCE.findOwnedContexts(ro);
		if (oec == null) {
			getRTCExecutionContexts().clear();
		} else {
			if (!eql(getRTCExecutionContexts().toArray(new RTC.ExecutionContext[0]), oec)) {
				getRTCExecutionContexts().clear();
				for (RTC.ExecutionContext ec : oec) {
					getRTCExecutionContexts().add(ec);
				}
			}
		}
		// participating context
		RTC.ExecutionContext[] pec = CorbaObjectStore.eINSTANCE.findParticipatingContexts(ro);
		if (pec == null) {
			getRTCParticipationContexts().clear();
		} else {
			if (!eql(getRTCParticipationContexts().toArray(new RTC.ExecutionContext[0]), pec)) {
				getRTCParticipationContexts().clear();
				for (RTC.ExecutionContext ec : pec) {
					getRTCParticipationContexts().add(ec);
				}
			}
		}
		// プライマリEC からコンポーネント状態を判定
		CorbaExecutionContext priEc = (CorbaExecutionContext) getPrimaryExecutionContext();
		if (priEc != null) {
			RTC.ExecutionContext ec = priEc.getCorbaObjectInterface();
			Integer newState = CorbaObjectStore.eINSTANCE.findComponentState(ec, ro);
			Integer oldState = getComponentState();
			if (!eql(oldState, newState)) {
				setComponentState(newState);
			}
		}
	}

	/** SDO.ConfigurationSetの同期 (CORBA=>オブジェクトDB) */
	public void synchronizeRemote_ConfigurationSets() {
		RTC.RTObject ro = getCorbaObjectInterface();
		synchronizeRemote_ConfigurationSets(ro);
	}

	public static void synchronizeRemote_ConfigurationSets(RTC.RTObject ro) {
		synchronized (CorbaObjectStore.eINSTANCE) {
			// LOGGER.debug("synchronizeRemote_ConfigurationSets: ro={}", ro);
			try {
				_SDOPackage.Configuration conf = ro.get_configuration();
				_SDOPackage.ConfigurationSet[] cs = conf.get_configuration_sets();
				CorbaObjectStore.eINSTANCE.registConfigSet(ro, cs);
			} catch (Exception e) {
				removeRemote_FullObject(ro);
				throw new RuntimeException(String.format("Fail to sync ConfigurationSets. ro=<%s>", ro), e);
			}
		}
	}

	/** SDO.ConfigurationSetの同期 (オブジェクトDB=>モデル) */
	public void synchronizeLocal_ConfigurationSets() {
		RTC.RTObject ro = getCorbaObjectInterface();
		boolean update = false;
		_SDOPackage.ConfigurationSet[] cs = CorbaObjectStore.eINSTANCE.findConfigSet(ro);
		if (cs == null) {
			getConfigurationSets().clear();
			return;
		}
		if (cs.length != getConfigurationSets().size()) {
			update = true;
		} else {
			for (int i = 0; i < cs.length; i++) {
				if (!(getConfigurationSets().get(i) instanceof CorbaConfigurationSet)) {
					update = true;
					break;
				}
				CorbaConfigurationSet ccs = (CorbaConfigurationSet) getConfigurationSets().get(i);
				if (!eql(cs[i], ccs.getSDOConfigurationSet())) {
					update = true;
					break;
				}
			}
		}
		if (update) {
			getConfigurationSets().clear();
			for (_SDOPackage.ConfigurationSet config : cs) {
				CorbaConfigurationSet ccs = (CorbaConfigurationSet) CorbaWrapperFactory.getInstance()
						.createWrapperObject(config);
				getConfigurationSets().add(ccs);
			}
		}
	}

	/** SDO.ConfigurationSet(active)の同期 (CORBA=>オブジェクトDB) */
	public void synchronizeRemote_ActiveConfigurationSet() {
		RTC.RTObject ro = getCorbaObjectInterface();
		synchronizeRemote_ActiveConfigurationSet(ro);
	}

	public static void synchronizeRemote_ActiveConfigurationSet(RTC.RTObject ro) {
		synchronized (CorbaObjectStore.eINSTANCE) {
			// LOGGER.debug("synchronizeRemote_ActiveConfigurationSet: ro={}", ro);
			try {
				_SDOPackage.Configuration conf = ro.get_configuration();
				_SDOPackage.ConfigurationSet cs = conf.get_active_configuration_set();
				CorbaObjectStore.eINSTANCE.registActiveConfigSet(ro, cs);
			} catch (Exception e) {
				removeRemote_FullObject(ro);
				throw new RuntimeException(String.format("Fail to sync ActiveConfigurationSet. ro=<%s>", ro), e);
			}
		}
	}

	/** SDO.ConfigurationSet(active)の同期 (オブジェクトDB=>モデル) */
	public void synchronizeLocal_ActiveConfigurationSet() {
		RTC.RTObject ro = getCorbaObjectInterface();
		boolean update = false;
		_SDOPackage.ConfigurationSet cs = CorbaObjectStore.eINSTANCE.findActiveConfigSet(ro);
		if (cs == null) {
			setActiveConfigurationSet(null);
			return;
		}
		if (!(getActiveConfigurationSet() instanceof CorbaConfigurationSet)) {
			update = true;
		} else {
			CorbaConfigurationSet ccs = (CorbaConfigurationSet) getActiveConfigurationSet();
			if (ccs == null || !eql(ccs.getSDOConfigurationSet(), cs)) {
				update = true;
			}
		}
		if (update) {
			boolean found = false;
			for (ConfigurationSet c : getConfigurationSets()) {
				if (eql(c.getId(), cs.id)) {
					setActiveConfigurationSet(c);
					found = true;
					break;
				}
			}
			if (!found) {
				setActiveConfigurationSet(null);
			}
		}
	}

	/** RTC.RTObjectメンバの同期 (CORBA=>オブジェクトDB) */
	public void synchronizeRemote_RTCRTObjects() {
		RTC.RTObject ro = getCorbaObjectInterface();
		synchronizeRemote_RTCRTObjects(ro);
	}

	public static void synchronizeRemote_RTCRTObjects(RTC.RTObject ro) {
		synchronized (CorbaObjectStore.eINSTANCE) {
			// LOGGER.debug("synchronizeRemote_RTCRTObjects: ro={}", ro);
			List<RTC.RTObject> list = CorbaObjectStore.eINSTANCE.getCompositeMemberList(ro);
			// LOGGER.debug("synchronizeRemote_RTCRTObjects: comp.members=<{}>", list);
			try {
				Organization[] orgs = ro.get_owned_organizations();
				if (orgs.length == 0) {
					// LOGGER.debug("synchronizeRemote_RTCRTObjects: No organizations");
					return;
				}
				_SDOPackage.SDO[] sdo_list = orgs[0].get_members();
				if (sdo_list == null) {
					LOGGER.debug("synchronizeRemote_RTCRTObjects: No sdo list");
					return;
				}
				list.clear();
				for (SDO sdo : sdo_list) {
					RTC.RTObject r = RTC.RTObjectHelper.narrow(sdo);
					if (r != null) {
						list.add(r);
						LOGGER.debug("synchronizeRemote_RTCRTObjects: add member=<{}>", r);
					}
				}
			} catch (Exception e) {
				removeRemote_FullObject(ro);
				throw new RuntimeException(String.format("Fail to sync RTCRTObjects. ro=<%s>", ro), e);
			}
		}
	}

	/** RTC.RTObjectメンバの同期 (オブジェクトDB=>モデル) */
	public void synchronizeLocal_RTCRTObjects() {
		RTC.RTObject ro = getCorbaObjectInterface();
		List<RTC.RTObject> list = CorbaObjectStore.eINSTANCE.getCompositeMemberList(ro);
		boolean update = false;
		List<RTC.RTObject> curr = getRTCRTObjects();
		if (curr.size() != list.size()) {
			update = true;
		} else {
			for (int i = 0; i < list.size(); i++) {
				if (!eql(list.get(i), curr.get(i))) {
					update = true;
					break;
				}
			}
		}
		if (update) {
			getRTCRTObjects().clear();
			getRTCRTObjects().addAll(list);
		}
	}

	@Override
	public void synchronizeLocalReference() {
		for (ReferenceMapping referenceMapping : getReferenceMappings()) {
			try {
				referenceMapping.syncronizeLocal(this);
			} catch (Exception e) {
				LOGGER.error("Fail to synchronize local", e);
				return;
			}
		}
	}

	public static ReferenceMapping[] getReferenceMappings() {
		return new ReferenceMapping[] {
				new ManyReferenceMapping(ComponentPackage.eINSTANCE
						.getComponent_Ports()) {
					@SuppressWarnings("unchecked")
					@Override
					protected List getNewRemoteLinkList(LocalObject localObject) {
						CorbaComponent component = (CorbaComponent) localObject;
						ComponentProfile componentProfile = component
								.getRTCComponentProfile();
						if (componentProfile == null)
							return Collections.EMPTY_LIST;
						RTC.PortProfile[] ports = componentProfile.port_profiles;
						if (ports == null)
							return Collections.EMPTY_LIST;
						List result = new ArrayList<RTC.PortService>();
						for (RTC.PortProfile portProfile : ports) {
							result.add(portProfile);
						}
						return result;
					}

					@SuppressWarnings("unchecked")
					@Override
					public List getOldRemoteLinkList(LocalObject localObject) {
						List result = new ArrayList<Object[]>();
						Component component = (Component) localObject;
						for (Port port : component.getPorts()) {
							Object[] remoteObjects = getRemoteObjects(port);
							if (remoteObjects.length == 0)
								continue;
							result.add(remoteObjects[0]);
						}
						return result;
					}

					@Override
					public boolean isLinkEquals(Object link1, Object link2) {
						if (!(link1 instanceof RTC.PortProfile))
							return false;
						if (!(link2 instanceof RTC.PortProfile))
							return false;
						RTC.PortProfile port1 = (RTC.PortProfile) link1;
						RTC.PortProfile port2 = (RTC.PortProfile) link2;
						boolean result = port1.port_ref.equals(port2.port_ref);
						if (result) {
							// コネクタ情報をここで更新する
							// link1が新しく、link2が古い
							port2.connector_profiles = port1.connector_profiles;
						}
						return result;
					}

					@Override
					protected Object[] getRemoteObjects(LocalObject localObject) {
						if (!(localObject instanceof Port))
							return new Object[0];
						Port port = (Port) localObject;
						if (!(port.getSynchronizer() instanceof CorbaPortSynchronizer))
							return new Object[0];
						CorbaPortSynchronizer synchronizer = (CorbaPortSynchronizer) port
								.getSynchronizer();
						return new Object[] { synchronizer.getRTCPortProfile() };
					}
				},
				new ManyReferenceMapping(ComponentPackage.eINSTANCE
						.getComponent_ExecutionContexts()) {
					@Override
					public List getNewRemoteLinkList(LocalObject localObject) {
						CorbaComponent component = (CorbaComponent) localObject;
						return component.getRTCExecutionContexts();
					}

					@Override
					public List getOldRemoteLinkList(LocalObject localObject) {
						List<org.omg.CORBA.Object> result = new ArrayList<>();
						CorbaComponent component = (CorbaComponent) localObject;
						for (Object obj : component.getExecutionContexts()) {
							CorbaExecutionContext ec = (CorbaExecutionContext) obj;
							if (ec != null) {
								result.add(ec.getCorbaObject());
							}
						}
						return result;
					}

					@Override
					public void postSynchronizeLocal(LocalObject localObject, boolean updated) {
						super.postSynchronizeLocal(localObject, updated);
						//
						CorbaComponent component = (CorbaComponent) localObject;
						//
						for (Object obj : component.getExecutionContexts()) {
							CorbaExecutionContext ec = (CorbaExecutionContext) obj;
							if (ec != null) {
								ec.getSynchronizationSupport().synchronizeLocal();
							}
						}
						//
						CorbaExecutionContext cec = (CorbaExecutionContext) component.getPrimaryExecutionContext();
						if (cec == null || (!component.getExecutionContexts().contains(cec)
								&& !component.getParticipationContexts().contains(cec))) {
							// プライマリEC が未設定/無効な場合は１つ目の ECを割り当てる
							if (component.getExecutionContexts().isEmpty()) {
								LOGGER.info("postSynchronizeLocal: set primary ec is empty. ec={}",
										component.getPrimaryExecutionContext());
							} else {
								component.setPrimaryExecutionContext(component.getExecutionContexts().get(0));
								LOGGER.info("postSynchronizeLocal: set primary ec={}",
										component.getPrimaryExecutionContext());
							}
						}
					}

					@Override
					public void postSynchronizeLocal(LocalObject localObject) {
						CorbaComponent component = (CorbaComponent) localObject;
						component.getExecutionContextHandler().sync();
					}
				},
				new ManyReferenceMapping(ComponentPackage.eINSTANCE
						.getComponent_ParticipationContexts()) {
					@Override
					public List getNewRemoteLinkList(LocalObject localObject) {
						CorbaComponent component = (CorbaComponent) localObject;
						return component.getRTCParticipationContexts();
					}

					@Override
					public List getOldRemoteLinkList(LocalObject localObject) {
						List<org.omg.CORBA.Object> result = new ArrayList<org.omg.CORBA.Object>();
						CorbaComponent component = (CorbaComponent) localObject;
						for (Object obj : component.getParticipationContexts()) {
							CorbaExecutionContext ec = (CorbaExecutionContext) obj;
							if (ec != null) {
								result.add(ec.getCorbaObject());
							}
						}
						return result;
					}

					@Override
					public void postSynchronizeLocal(LocalObject localObject) {
						CorbaComponent component = (CorbaComponent) localObject;
						component.getParticipationContextHandler().sync();
					}
				},
				new ManyReferenceMapping(ComponentPackage.eINSTANCE
						.getComponent_Components()) {
					@Override
					public List getNewRemoteLinkList(LocalObject localObject) {
						CorbaComponent component = (CorbaComponent) localObject;
						return component.getRTCRTObjects();
					}

					@Override
					public LocalObject loadLocalObjectByRemoteObject(
							LocalObject localObject,
							SynchronizationManager synchronizationManager,
							Object link, Object[] remoteObject) {
						if (localObject.eContainer() instanceof SystemDiagram) {
							// 親がSystemDiagramの場合は、Diagramからローカルを検索
							LocalObject lo = SynchronizationSupport
									.findLocalObjectByRemoteObject(
											remoteObject,
											(SystemDiagram) localObject
													.eContainer());
							if (lo != null) {
								return lo;
							}
						}
						return super.loadLocalObjectByRemoteObject(localObject,
								synchronizationManager, link, remoteObject);
					}
				}, };
	}

	@Override
	public String getPath() {
		if (getPathId() == null)
			return null;
		int index = getPathId().indexOf("/");
		if (index < 0)
			return getPathId();
		return getPathId().substring(0, index);
	}

	private long lastExecutedTime;
	private static long SYNC_MANUAL_INTERVAL = 1000L;

	@Override
	public void synchronizeManually() {
		if (eContainer() instanceof SystemDiagram)
			return;
		if (System.currentTimeMillis() - lastExecutedTime < SYNC_MANUAL_INTERVAL) {
			return;
		}
		//
		try {
			synchronizeRemoteAttribute(null);
			synchronizeRemoteChildComponents();
		} catch (RuntimeException e) {
			LOGGER.error("Fail to sync remote: <{}>", this);
			LOGGER.error("ERROR:", e);
			return;
		}
		//
		synchronizeLocalAttribute(null);
		synchronizeLocalReference();
		synchronizeChildComponents();
		lastExecutedTime = System.currentTimeMillis();
	}

	static _SDOPackage.ServiceProfile dummyServiceProfile;
	Boolean supportCorbaObserver = null;

	@Override
	public boolean supportedCorbaObserver() {
		// OpenRTM-aistバージョンチェック
		if (dummyServiceProfile == null) {
			dummyServiceProfile = new _SDOPackage.ServiceProfile();
			dummyServiceProfile.id = "";
			dummyServiceProfile.interface_type = "";
			dummyServiceProfile.properties = new _SDOPackage.NameValue[0];
		}
		if (supportCorbaObserver == null) {
			boolean result = true;
			try {
				result = !getSDOConfiguration().add_service_profile(
						dummyServiceProfile);
			} catch (Exception e) {
				result = false;
			}
			supportCorbaObserver = Boolean.valueOf(result);
		}
		return supportCorbaObserver.booleanValue();
	}

	@Override
	public Component copy() {
		Component copy = (Component) EcoreUtil.copy(this);
		getSynchronizationSupport().getSynchronizationManager()
				.assignSynchonizationSupport(copy);
		copy.synchronizeManually();
		adjustPathId(copy.getAllComponents());
		return copy;
	}

	@Override
	public boolean isDead() {
		return !SynchronizationSupport.ping(getCorbaObject());
	}

	@Override
	public int hashCode() {
		if (getCorbaObjectInterface() == null) {
			return super.hashCode();
		}
		return super.hashCode() + getCorbaObjectInterface().hashCode();
	}

} // CorbaComponentImpl
