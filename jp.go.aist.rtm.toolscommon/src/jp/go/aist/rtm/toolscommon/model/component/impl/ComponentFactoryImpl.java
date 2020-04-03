/**
 * <copyright>
 * </copyright>
 *
 * $Id: ComponentFactoryImpl.java,v 1.10 2008/03/27 08:40:15 terui Exp $
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.omg.CORBA.Any;
import org.omg.CORBA.TCKind;
import org.omg.PortableServer.Servant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import RTC.ComponentProfile;
import RTC.ExecutionContextProfile;
import RTC.PortProfile;
import RTC.RTObject;
import _SDOPackage.Configuration;
import _SDOPackage.ConfigurationHelper;
import _SDOPackage.Organization;
import _SDOPackage.ServiceProfile;
import jp.go.aist.rtm.toolscommon.corba.CorbaUtil;
import jp.go.aist.rtm.toolscommon.model.component.*;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.ContextHandler;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.CorbaConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.CorbaContextHandler;
import jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.CorbaLogObserver;
import jp.go.aist.rtm.toolscommon.model.component.CorbaObserver;
import jp.go.aist.rtm.toolscommon.model.component.CorbaPortSynchronizer;
import jp.go.aist.rtm.toolscommon.model.component.CorbaStatusObserver;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.InPort;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.component.OutPort;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortInterfaceProfile;
import jp.go.aist.rtm.toolscommon.model.component.PortSynchronizer;
import jp.go.aist.rtm.toolscommon.model.component.ServicePort;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind;
import jp.go.aist.rtm.toolscommon.model.component.util.ICorbaPortEventObserver;
import jp.go.aist.rtm.toolscommon.model.core.Point;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class ComponentFactoryImpl extends EFactoryImpl implements
		ComponentFactory {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ComponentFactoryImpl.class);

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ComponentFactory init() {
		try {
			ComponentFactory theComponentFactory = (ComponentFactory)EPackage.Registry.INSTANCE.getEFactory(ComponentPackage.eNS_URI);
			if (theComponentFactory != null) {
				return theComponentFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ComponentFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public ComponentFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ComponentPackage.SYSTEM_DIAGRAM: return createSystemDiagram();
			case ComponentPackage.COMPONENT_SPECIFICATION: return createComponentSpecification();
			case ComponentPackage.EXECUTION_CONTEXT: return createExecutionContext();
			case ComponentPackage.CONTEXT_HANDLER: return createContextHandler();
			case ComponentPackage.CONFIGURATION_SET: return createConfigurationSet();
			case ComponentPackage.NAME_VALUE: return createNameValue();
			case ComponentPackage.PORT: return createPort();
			case ComponentPackage.IN_PORT: return createInPort();
			case ComponentPackage.OUT_PORT: return createOutPort();
			case ComponentPackage.SERVICE_PORT: return createServicePort();
			case ComponentPackage.PORT_SYNCHRONIZER: return createPortSynchronizer();
			case ComponentPackage.CONNECTOR_PROFILE: return createConnectorProfile();
			case ComponentPackage.EINTEGER_OBJECT_TO_POINT_MAP_ENTRY: return (EObject)createEIntegerObjectToPointMapEntry();
			case ComponentPackage.CORBA_COMPONENT: return createCorbaComponent();
			case ComponentPackage.CORBA_PORT_SYNCHRONIZER: return createCorbaPortSynchronizer();
			case ComponentPackage.CORBA_CONNECTOR_PROFILE: return createCorbaConnectorProfile();
			case ComponentPackage.CORBA_CONFIGURATION_SET: return createCorbaConfigurationSet();
			case ComponentPackage.CORBA_EXECUTION_CONTEXT: return createCorbaExecutionContext();
			case ComponentPackage.CORBA_CONTEXT_HANDLER: return createCorbaContextHandler();
			case ComponentPackage.CORBA_OBSERVER: return createCorbaObserver();
			case ComponentPackage.CORBA_STATUS_OBSERVER: return createCorbaStatusObserver();
			case ComponentPackage.CORBA_LOG_OBSERVER: return createCorbaLogObserver();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ComponentPackage.SYSTEM_DIAGRAM_KIND:
				return createSystemDiagramKindFromString(eDataType, initialValue);
			case ComponentPackage.SDO_CONFIGURATION:
				return createSDOConfigurationFromString(eDataType, initialValue);
			case ComponentPackage.SDO_CONFIGURATION_SET:
				return createSDOConfigurationSetFromString(eDataType, initialValue);
			case ComponentPackage.SDO_ORGANIZATION:
				return createSDOOrganizationFromString(eDataType, initialValue);
			case ComponentPackage.SDO_SERVICE_PROFILE:
				return createSDOServiceProfileFromString(eDataType, initialValue);
			case ComponentPackage.RTCRT_OBJECT:
				return createRTCRTObjectFromString(eDataType, initialValue);
			case ComponentPackage.RTC_COMPONENT_PROFILE:
				return createRTCComponentProfileFromString(eDataType, initialValue);
			case ComponentPackage.RTC_CONNECTOR_PROFILE:
				return createRTCConnectorProfileFromString(eDataType, initialValue);
			case ComponentPackage.RTC_PORT_PROFILE:
				return createRTCPortProfileFromString(eDataType, initialValue);
			case ComponentPackage.RTC_EXECUTION_CONTEXT:
				return createRTCExecutionContextFromString(eDataType, initialValue);
			case ComponentPackage.RTC_EXECUTION_CONTEXT_PROFILE:
				return createRTCExecutionContextProfileFromString(eDataType, initialValue);
			case ComponentPackage.PROPERTY_CHANGE_LISTENER:
				return createPropertyChangeListenerFromString(eDataType, initialValue);
			case ComponentPackage.PORT_INTERFACE_PROFILE:
				return createPortInterfaceProfileFromString(eDataType, initialValue);
			case ComponentPackage.LIST:
				return createListFromString(eDataType, initialValue);
			case ComponentPackage.SERVANT:
				return createServantFromString(eDataType, initialValue);
			case ComponentPackage.ICORBA_PORT_EVENT_OBSERVER:
				return createICorbaPortEventObserverFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ComponentPackage.SYSTEM_DIAGRAM_KIND:
				return convertSystemDiagramKindToString(eDataType, instanceValue);
			case ComponentPackage.SDO_CONFIGURATION:
				return convertSDOConfigurationToString(eDataType, instanceValue);
			case ComponentPackage.SDO_CONFIGURATION_SET:
				return convertSDOConfigurationSetToString(eDataType, instanceValue);
			case ComponentPackage.SDO_ORGANIZATION:
				return convertSDOOrganizationToString(eDataType, instanceValue);
			case ComponentPackage.SDO_SERVICE_PROFILE:
				return convertSDOServiceProfileToString(eDataType, instanceValue);
			case ComponentPackage.RTCRT_OBJECT:
				return convertRTCRTObjectToString(eDataType, instanceValue);
			case ComponentPackage.RTC_COMPONENT_PROFILE:
				return convertRTCComponentProfileToString(eDataType, instanceValue);
			case ComponentPackage.RTC_CONNECTOR_PROFILE:
				return convertRTCConnectorProfileToString(eDataType, instanceValue);
			case ComponentPackage.RTC_PORT_PROFILE:
				return convertRTCPortProfileToString(eDataType, instanceValue);
			case ComponentPackage.RTC_EXECUTION_CONTEXT:
				return convertRTCExecutionContextToString(eDataType, instanceValue);
			case ComponentPackage.RTC_EXECUTION_CONTEXT_PROFILE:
				return convertRTCExecutionContextProfileToString(eDataType, instanceValue);
			case ComponentPackage.PROPERTY_CHANGE_LISTENER:
				return convertPropertyChangeListenerToString(eDataType, instanceValue);
			case ComponentPackage.PORT_INTERFACE_PROFILE:
				return convertPortInterfaceProfileToString(eDataType, instanceValue);
			case ComponentPackage.LIST:
				return convertListToString(eDataType, instanceValue);
			case ComponentPackage.SERVANT:
				return convertServantToString(eDataType, instanceValue);
			case ComponentPackage.ICORBA_PORT_EVENT_OBSERVER:
				return convertICorbaPortEventObserverToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public SystemDiagram createSystemDiagram() {
		SystemDiagramImpl systemDiagram = new SystemDiagramImpl();
		return systemDiagram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CorbaComponent createCorbaComponent() {
		CorbaComponentImpl corbaComponent = new CorbaComponentImpl();
		return corbaComponent;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public InPort createInPort() {
		InPortImpl inPort = new InPortImpl();
		return inPort;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public OutPort createOutPort() {
		OutPortImpl outPort = new OutPortImpl();
		return outPort;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectorProfile createConnectorProfile() {
		ConnectorProfileImpl connectorProfile = new ConnectorProfileImpl();
		return connectorProfile;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ConfigurationSet createConfigurationSet() {
		ConfigurationSetImpl configurationSet = new ConfigurationSetImpl();
		return configurationSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<Integer, Point> createEIntegerObjectToPointMapEntry() {
		EIntegerObjectToPointMapEntryImpl eIntegerObjectToPointMapEntry = new EIntegerObjectToPointMapEntryImpl();
		return eIntegerObjectToPointMapEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortSynchronizer createPortSynchronizer() {
		PortSynchronizerImpl portSynchronizer = new PortSynchronizerImpl();
		return portSynchronizer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CorbaPortSynchronizer createCorbaPortSynchronizer() {
		CorbaPortSynchronizerImpl corbaPortSynchronizer = new CorbaPortSynchronizerImpl();
		return corbaPortSynchronizer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CorbaConnectorProfile createCorbaConnectorProfile() {
		CorbaConnectorProfileImpl corbaConnectorProfile = new CorbaConnectorProfileImpl();
		return corbaConnectorProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CorbaConfigurationSet createCorbaConfigurationSet() {
		CorbaConfigurationSetImpl corbaConfigurationSet = new CorbaConfigurationSetImpl();
		return corbaConfigurationSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CorbaExecutionContext createCorbaExecutionContext() {
		CorbaExecutionContextImpl corbaExecutionContext = new CorbaExecutionContextImpl();
		return corbaExecutionContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CorbaContextHandler createCorbaContextHandler() {
		CorbaContextHandlerImpl corbaContextHandler = new CorbaContextHandlerImpl();
		return corbaContextHandler;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CorbaObserver createCorbaObserver() {
		CorbaObserverImpl corbaObserver = new CorbaObserverImpl();
		return corbaObserver;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CorbaStatusObserver createCorbaStatusObserver() {
		CorbaStatusObserverImpl corbaStatusObserver = new CorbaStatusObserverImpl();
		return corbaStatusObserver;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CorbaLogObserver createCorbaLogObserver() {
		CorbaLogObserverImpl corbaLogObserver = new CorbaLogObserverImpl();
		return corbaLogObserver;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentSpecification createComponentSpecification() {
		ComponentSpecificationImpl componentSpecification = new ComponentSpecificationImpl();
		return componentSpecification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemDiagramKind createSystemDiagramKindFromString(EDataType eDataType, String initialValue) {
		SystemDiagramKind result = SystemDiagramKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSystemDiagramKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Port createPort() {
		PortImpl port = new PortImpl();
		return port;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ServicePort createServicePort() {
		ServicePortImpl servicePort = new ServicePortImpl();
		return servicePort;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionContext createExecutionContext() {
		ExecutionContextImpl executionContext = new ExecutionContextImpl();
		return executionContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContextHandler createContextHandler() {
		ContextHandlerImpl contextHandler = new ContextHandlerImpl();
		return contextHandler;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NameValue createNameValue() {
		NameValueImpl nameValue = new NameValueImpl();
		return nameValue;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public List createListFromString(EDataType eDataType, String initialValue) {
		return (List)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertListToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Servant createServantFromString(EDataType eDataType, String initialValue) {
		return (Servant)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertServantToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ICorbaPortEventObserver createICorbaPortEventObserverFromString(EDataType eDataType, String initialValue) {
		return (ICorbaPortEventObserver)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertICorbaPortEventObserverToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentProfile createRTCComponentProfileFromString(EDataType eDataType, String initialValue) {
		return (ComponentProfile)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertRTCComponentProfileToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RTObject createRTCRTObjectFromString(EDataType eDataType, String initialValue) {
		return (RTObject)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertRTCRTObjectToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Configuration createSDOConfigurationFromString(EDataType eDataType, String initialValue) {
		return ConfigurationHelper.narrow(CorbaUtil
				.stringToObject(initialValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSDOConfigurationToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public _SDOPackage.ConfigurationSet createSDOConfigurationSetFromString(EDataType eDataType, String initialValue) {
		return (_SDOPackage.ConfigurationSet)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSDOConfigurationSetToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RTC.ConnectorProfile createRTCConnectorProfileFromString(EDataType eDataType, String initialValue) {
		return (RTC.ConnectorProfile)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertRTCConnectorProfileToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortProfile createRTCPortProfileFromString(EDataType eDataType, String initialValue) {
		return (PortProfile)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertRTCPortProfileToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RTC.ExecutionContext createRTCExecutionContextFromString(EDataType eDataType, String initialValue) {
		return (RTC.ExecutionContext)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertRTCExecutionContextToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyChangeListener createPropertyChangeListenerFromString(EDataType eDataType, String initialValue) {
		return (PropertyChangeListener)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPropertyChangeListenerToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Organization createSDOOrganizationFromString(EDataType eDataType, String initialValue) {
		return (Organization)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSDOOrganizationToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceProfile createSDOServiceProfileFromString(EDataType eDataType, String initialValue) {
		return (ServiceProfile)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSDOServiceProfileToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortInterfaceProfile createPortInterfaceProfileFromString(EDataType eDataType, String initialValue) {
		return (PortInterfaceProfile)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPortInterfaceProfileToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionContextProfile createRTCExecutionContextProfileFromString(EDataType eDataType, String initialValue) {
		return (ExecutionContextProfile)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertRTCExecutionContextProfileToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	public Configuration createConfigurationFromString(EDataType eDataType,
			String initialValue) {
		return ConfigurationHelper.narrow(CorbaUtil
				.stringToObject(initialValue));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	public Any createAnyFromString(EDataType eDataType, String initialValue) {
		Any any = null;
		try {
			any = CorbaUtil.getOrb().create_any();

			org.omg.CORBA.Object remote = null;
			try {
				remote = CorbaUtil.stringToObject(initialValue);
			} catch (Exception e) {
				// void
			}

			if (remote != null) {
				any.insert_Object(remote);
			} else {
				if( StringUtils.isAsciiPrintable((String) initialValue) ) {
					any.insert_string(initialValue);
				} else {
					any.insert_wstring(initialValue);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Fail to insert string. value=" + initialValue, e);
		}

		return any;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	public String convertAnyToString(EDataType eDataType, Object instanceValue) {
		String result = null;
		try {
			if (((Any) instanceValue).type().kind() == TCKind.tk_wstring) {
				result = ((Any) instanceValue).extract_wstring();
			} else if (((Any) instanceValue).type().kind() == TCKind.tk_string) {
				result = ((Any) instanceValue).extract_string();
			} else {
				result = ((Any) instanceValue).extract_Object().toString();
			}
		} catch (Exception e) {
			LOGGER.error("Fail to extract string. value=" + instanceValue, e);
		}

		return (result != null) ? result : "";
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentPackage getComponentPackage() {
		return (ComponentPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ComponentPackage getPackage() {
		return ComponentPackage.eINSTANCE;
	}

} // ComponentFactoryImpl
