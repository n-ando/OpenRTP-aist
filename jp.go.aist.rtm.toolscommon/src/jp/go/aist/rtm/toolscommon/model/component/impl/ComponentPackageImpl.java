/**
 * <copyright>
 * </copyright>
 *
 * $Id: ComponentPackageImpl.java,v 1.11 2008/03/06 04:01:49 yamashita Exp $
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.omg.PortableServer.Servant;

import RTC.ComponentProfile;
import RTC.ExecutionContextProfile;
import RTC.PortProfile;
import RTC.RTObject;
import _SDOPackage.Configuration;
import _SDOPackage.Organization;
import _SDOPackage.ServiceProfile;
import jp.go.aist.rtm.toolscommon.model.component.Component;
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
import jp.go.aist.rtm.toolscommon.model.component.IPropertyMap;
import jp.go.aist.rtm.toolscommon.model.component.InPort;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.component.OutPort;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;
import jp.go.aist.rtm.toolscommon.model.component.PortInterfaceProfile;
import jp.go.aist.rtm.toolscommon.model.component.PortSynchronizer;
import jp.go.aist.rtm.toolscommon.model.component.ServicePort;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind;
import jp.go.aist.rtm.toolscommon.model.component.util.ICorbaPortEventObserver;
import jp.go.aist.rtm.toolscommon.model.core.CorePackage;
import jp.go.aist.rtm.toolscommon.model.core.impl.CorePackageImpl;
import jp.go.aist.rtm.toolscommon.model.manager.ManagerPackage;
import jp.go.aist.rtm.toolscommon.model.manager.impl.ManagerPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ComponentPackageImpl extends EPackageImpl implements ComponentPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass corbaComponentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass executionContextEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contextHandlerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass inPortEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nameValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass outPortEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portConnectorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass servicePortEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass systemDiagramEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectorProfileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass configurationSetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eIntegerObjectToPointMapEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iPropertyMapEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portSynchronizerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass corbaPortSynchronizerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass corbaConnectorProfileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass corbaConfigurationSetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass corbaExecutionContextEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass corbaContextHandlerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass corbaObserverEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass corbaStatusObserverEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass corbaLogObserverEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentSpecificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum systemDiagramKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType listEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType servantEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType iCorbaPortEventObserverEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType rtcComponentProfileEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType rtcrtObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType sdoConfigurationEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType sdoConfigurationSetEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType rtcConnectorProfileEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType rtcPortProfileEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType rtcExecutionContextEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType propertyChangeListenerEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType sdoOrganizationEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType sdoServiceProfileEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType portInterfaceProfileEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType rtcExecutionContextProfileEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ComponentPackageImpl() {
		super(eNS_URI, ComponentFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ComponentPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ComponentPackage init() {
		if (isInited) return (ComponentPackage)EPackage.Registry.INSTANCE.getEPackage(ComponentPackage.eNS_URI);

		// Obtain or create and register package
		ComponentPackageImpl theComponentPackage = (ComponentPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ComponentPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ComponentPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		ManagerPackageImpl theManagerPackage = (ManagerPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ManagerPackage.eNS_URI) instanceof ManagerPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ManagerPackage.eNS_URI) : ManagerPackage.eINSTANCE);
		CorePackageImpl theCorePackage = (CorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) instanceof CorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) : CorePackage.eINSTANCE);

		// Create package meta-data objects
		theComponentPackage.createPackageContents();
		theManagerPackage.createPackageContents();
		theCorePackage.createPackageContents();

		// Initialize created meta-data
		theComponentPackage.initializePackageContents();
		theManagerPackage.initializePackageContents();
		theCorePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theComponentPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ComponentPackage.eNS_URI, theComponentPackage);
		return theComponentPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComponent() {
		return componentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_ConfigurationSets() {
		return (EReference)componentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_ActiveConfigurationSet() {
		return (EReference)componentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_Ports() {
		return (EReference)componentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_Inports() {
		return (EReference)componentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_Outports() {
		return (EReference)componentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_Serviceports() {
		return (EReference)componentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_ExecutionContexts() {
		return (EReference)componentEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_ParticipationContexts() {
		return (EReference)componentEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_ExecutionContextHandler() {
		return (EReference)componentEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_ParticipationContextHandler() {
		return (EReference)componentEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_InstanceNameL() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_VenderL() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_DescriptionL() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_CategoryL() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_TypeNameL() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_VersionL() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_PathId() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_OutportDirection() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_CompositeTypeL() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_Components() {
		return (EReference)componentEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_PrimaryExecutionContext() {
		return (EReference)componentEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_ComponentId() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_Required() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_StartUp() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_ShutDown() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(25);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_Activation() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(26);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_DeActivation() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(27);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_Resetting() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(28);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_Initialize() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(29);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_Finalize() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(30);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_ChildSystemDiagram() {
		return (EReference)componentEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCorbaComponent() {
		return corbaComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCorbaComponent_SDOConfiguration() {
		return (EAttribute)corbaComponentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCorbaComponent_RTCComponentProfile() {
		return (EAttribute)corbaComponentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCorbaComponent_RTCExecutionContexts() {
		return (EAttribute)corbaComponentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCorbaComponent_RTCParticipationContexts() {
		return (EAttribute)corbaComponentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCorbaComponent_SDOOrganization() {
		return (EAttribute)corbaComponentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCorbaComponent_RTCRTObjects() {
		return (EAttribute)corbaComponentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCorbaComponent_ComponentState() {
		return (EAttribute)corbaComponentEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCorbaComponent_Ior() {
		return (EAttribute)corbaComponentEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCorbaComponent_StatusObserver() {
		return (EReference)corbaComponentEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCorbaComponent_LogObserver() {
		return (EReference)corbaComponentEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExecutionContext() {
		return executionContextEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecutionContext_KindL() {
		return (EAttribute)executionContextEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecutionContext_RateL() {
		return (EAttribute)executionContextEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecutionContext_StateL() {
		return (EAttribute)executionContextEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExecutionContext_Owner() {
		return (EReference)executionContextEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExecutionContext_Participants() {
		return (EReference)executionContextEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContextHandler() {
		return contextHandlerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInPort() {
		return inPortEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNameValue() {
		return nameValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNameValue_Name() {
		return (EAttribute)nameValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNameValue_Value() {
		return (EAttribute)nameValueEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNameValue_TypeName() {
		return (EAttribute)nameValueEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOutPort() {
		return outPortEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPort() {
		return portEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPort_OriginalPortString() {
		return (EAttribute)portEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPort_Synchronizer() {
		return (EReference)portEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPort_NameL() {
		return (EAttribute)portEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPort_AllowAnyDataType() {
		return (EAttribute)portEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPort_AllowAnyInterfaceType() {
		return (EAttribute)portEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPort_AllowAnyDataflowType() {
		return (EAttribute)portEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPort_AllowAnySubscriptionType() {
		return (EAttribute)portEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPort_ConnectorProfiles() {
		return (EReference)portEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPort_Interfaces() {
		return (EAttribute)portEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPort_DataflowType() {
		return (EAttribute)portEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPort_SubscriptionType() {
		return (EAttribute)portEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPort_DataType() {
		return (EAttribute)portEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPort_InterfaceType() {
		return (EAttribute)portEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPortConnector() {
		return portConnectorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortConnector_ConnectorProfile() {
		return (EReference)portConnectorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortConnector_RoutingConstraint() {
		return (EReference)portConnectorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortConnector_Source() {
		return (EReference)portConnectorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortConnector_Target() {
		return (EReference)portConnectorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getServicePort() {
		return servicePortEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSystemDiagram() {
		return systemDiagramEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemDiagram_Components() {
		return (EReference)systemDiagramEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSystemDiagram_Kind() {
		return (EAttribute)systemDiagramEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSystemDiagram_ConnectorProcessing() {
		return (EAttribute)systemDiagramEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSystemDiagram_SystemId() {
		return (EAttribute)systemDiagramEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSystemDiagram_CreationDate() {
		return (EAttribute)systemDiagramEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSystemDiagram_UpdateDate() {
		return (EAttribute)systemDiagramEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemDiagram_ParentSystemDiagram() {
		return (EReference)systemDiagramEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemDiagram_CompositeComponent() {
		return (EReference)systemDiagramEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_DataflowType() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_SubscriptionType() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_SubscriptionTypeAvailable() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_PushIntervalAvailable() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_Name() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_ConnectorId() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_DataType() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_InterfaceType() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_PushRate() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_PushPolicy() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_SkipCount() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_PushPolicyAvailable() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_SkipCountAvailable() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_SourceString() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_TargetString() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_OutportSerializerType() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(27);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_OutportBufferLength() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_OutportBufferFullPolicy() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_OutportBufferWriteTimeout() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_OutportBufferEmptyPolicy() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_OutportBufferReadTimeout() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_InportBufferLength() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_InportBufferFullPolicy() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_InportBufferWriteTimeout() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_InportBufferEmptyPolicy() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_InportBufferReadTimeout() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_InportSerializerType() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(28);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_TimestampPolicy() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(25);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorProfile_IsReverse() {
		return (EAttribute)connectorProfileEClass.getEStructuralFeatures().get(26);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConfigurationSet() {
		return configurationSetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConfigurationSet_Id() {
		return (EAttribute)configurationSetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConfigurationSet_ConfigurationData() {
		return (EReference)configurationSetEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEIntegerObjectToPointMapEntry() {
		return eIntegerObjectToPointMapEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEIntegerObjectToPointMapEntry_Key() {
		return (EAttribute)eIntegerObjectToPointMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEIntegerObjectToPointMapEntry_Value() {
		return (EAttribute)eIntegerObjectToPointMapEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIPropertyMap() {
		return iPropertyMapEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPortSynchronizer() {
		return portSynchronizerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortSynchronizer_OriginalPortString() {
		return (EAttribute)portSynchronizerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCorbaPortSynchronizer() {
		return corbaPortSynchronizerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCorbaPortSynchronizer_RTCPortProfile() {
		return (EAttribute)corbaPortSynchronizerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCorbaConnectorProfile() {
		return corbaConnectorProfileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCorbaConnectorProfile_RtcConnectorProfile() {
		return (EAttribute)corbaConnectorProfileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCorbaConfigurationSet() {
		return corbaConfigurationSetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCorbaConfigurationSet_SDOConfigurationSet() {
		return (EAttribute)corbaConfigurationSetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCorbaExecutionContext() {
		return corbaExecutionContextEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCorbaExecutionContext_RtcExecutionContextProfile() {
		return (EAttribute)corbaExecutionContextEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCorbaContextHandler() {
		return corbaContextHandlerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCorbaObserver() {
		return corbaObserverEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCorbaObserver_ServiceProfile() {
		return (EAttribute)corbaObserverEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCorbaObserver_Servant() {
		return (EAttribute)corbaObserverEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCorbaStatusObserver() {
		return corbaStatusObserverEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCorbaLogObserver() {
		return corbaLogObserverEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComponentSpecification() {
		return componentSpecificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponentSpecification_AliasName() {
		return (EAttribute)componentSpecificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponentSpecification_SpecUnLoad() {
		return (EAttribute)componentSpecificationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponentSpecification_RtcType() {
		return (EAttribute)componentSpecificationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getSystemDiagramKind() {
		return systemDiagramKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConnectorProfile() {
		return connectorProfileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getList() {
		return listEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getServant() {
		return servantEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getICorbaPortEventObserver() {
		return iCorbaPortEventObserverEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getRTCComponentProfile() {
		return rtcComponentProfileEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getRTCRTObject() {
		return rtcrtObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getSDOConfiguration() {
		return sdoConfigurationEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getSDOConfigurationSet() {
		return sdoConfigurationSetEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getRTCConnectorProfile() {
		return rtcConnectorProfileEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getRTCPortProfile() {
		return rtcPortProfileEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getRTCExecutionContext() {
		return rtcExecutionContextEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getPropertyChangeListener() {
		return propertyChangeListenerEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getSDOOrganization() {
		return sdoOrganizationEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getSDOServiceProfile() {
		return sdoServiceProfileEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getPortInterfaceProfile() {
		return portInterfaceProfileEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getRTCExecutionContextProfile() {
		return rtcExecutionContextProfileEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentFactory getComponentFactory() {
		return (ComponentFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		systemDiagramEClass = createEClass(SYSTEM_DIAGRAM);
		createEReference(systemDiagramEClass, SYSTEM_DIAGRAM__COMPONENTS);
		createEAttribute(systemDiagramEClass, SYSTEM_DIAGRAM__KIND);
		createEAttribute(systemDiagramEClass, SYSTEM_DIAGRAM__CONNECTOR_PROCESSING);
		createEAttribute(systemDiagramEClass, SYSTEM_DIAGRAM__SYSTEM_ID);
		createEAttribute(systemDiagramEClass, SYSTEM_DIAGRAM__CREATION_DATE);
		createEAttribute(systemDiagramEClass, SYSTEM_DIAGRAM__UPDATE_DATE);
		createEReference(systemDiagramEClass, SYSTEM_DIAGRAM__PARENT_SYSTEM_DIAGRAM);
		createEReference(systemDiagramEClass, SYSTEM_DIAGRAM__COMPOSITE_COMPONENT);

		componentEClass = createEClass(COMPONENT);
		createEReference(componentEClass, COMPONENT__CONFIGURATION_SETS);
		createEReference(componentEClass, COMPONENT__ACTIVE_CONFIGURATION_SET);
		createEReference(componentEClass, COMPONENT__PORTS);
		createEReference(componentEClass, COMPONENT__INPORTS);
		createEReference(componentEClass, COMPONENT__OUTPORTS);
		createEReference(componentEClass, COMPONENT__SERVICEPORTS);
		createEReference(componentEClass, COMPONENT__COMPONENTS);
		createEReference(componentEClass, COMPONENT__PRIMARY_EXECUTION_CONTEXT);
		createEReference(componentEClass, COMPONENT__EXECUTION_CONTEXTS);
		createEReference(componentEClass, COMPONENT__PARTICIPATION_CONTEXTS);
		createEReference(componentEClass, COMPONENT__EXECUTION_CONTEXT_HANDLER);
		createEReference(componentEClass, COMPONENT__PARTICIPATION_CONTEXT_HANDLER);
		createEReference(componentEClass, COMPONENT__CHILD_SYSTEM_DIAGRAM);
		createEAttribute(componentEClass, COMPONENT__INSTANCE_NAME_L);
		createEAttribute(componentEClass, COMPONENT__VENDER_L);
		createEAttribute(componentEClass, COMPONENT__DESCRIPTION_L);
		createEAttribute(componentEClass, COMPONENT__CATEGORY_L);
		createEAttribute(componentEClass, COMPONENT__TYPE_NAME_L);
		createEAttribute(componentEClass, COMPONENT__VERSION_L);
		createEAttribute(componentEClass, COMPONENT__PATH_ID);
		createEAttribute(componentEClass, COMPONENT__OUTPORT_DIRECTION);
		createEAttribute(componentEClass, COMPONENT__COMPOSITE_TYPE_L);
		createEAttribute(componentEClass, COMPONENT__COMPONENT_ID);
		createEAttribute(componentEClass, COMPONENT__REQUIRED);
		createEAttribute(componentEClass, COMPONENT__START_UP);
		createEAttribute(componentEClass, COMPONENT__SHUT_DOWN);
		createEAttribute(componentEClass, COMPONENT__ACTIVATION);
		createEAttribute(componentEClass, COMPONENT__DE_ACTIVATION);
		createEAttribute(componentEClass, COMPONENT__RESETTING);
		createEAttribute(componentEClass, COMPONENT__INITIALIZE);
		createEAttribute(componentEClass, COMPONENT__FINALIZE);

		componentSpecificationEClass = createEClass(COMPONENT_SPECIFICATION);
		createEAttribute(componentSpecificationEClass, COMPONENT_SPECIFICATION__ALIAS_NAME);
		createEAttribute(componentSpecificationEClass, COMPONENT_SPECIFICATION__SPEC_UN_LOAD);
		createEAttribute(componentSpecificationEClass, COMPONENT_SPECIFICATION__RTC_TYPE);

		executionContextEClass = createEClass(EXECUTION_CONTEXT);
		createEAttribute(executionContextEClass, EXECUTION_CONTEXT__KIND_L);
		createEAttribute(executionContextEClass, EXECUTION_CONTEXT__RATE_L);
		createEAttribute(executionContextEClass, EXECUTION_CONTEXT__STATE_L);
		createEReference(executionContextEClass, EXECUTION_CONTEXT__OWNER);
		createEReference(executionContextEClass, EXECUTION_CONTEXT__PARTICIPANTS);

		contextHandlerEClass = createEClass(CONTEXT_HANDLER);

		configurationSetEClass = createEClass(CONFIGURATION_SET);
		createEAttribute(configurationSetEClass, CONFIGURATION_SET__ID);
		createEReference(configurationSetEClass, CONFIGURATION_SET__CONFIGURATION_DATA);

		nameValueEClass = createEClass(NAME_VALUE);
		createEAttribute(nameValueEClass, NAME_VALUE__NAME);
		createEAttribute(nameValueEClass, NAME_VALUE__VALUE);
		createEAttribute(nameValueEClass, NAME_VALUE__TYPE_NAME);

		portEClass = createEClass(PORT);
		createEAttribute(portEClass, PORT__ORIGINAL_PORT_STRING);
		createEReference(portEClass, PORT__SYNCHRONIZER);
		createEAttribute(portEClass, PORT__NAME_L);
		createEAttribute(portEClass, PORT__ALLOW_ANY_DATA_TYPE);
		createEAttribute(portEClass, PORT__ALLOW_ANY_INTERFACE_TYPE);
		createEAttribute(portEClass, PORT__ALLOW_ANY_DATAFLOW_TYPE);
		createEAttribute(portEClass, PORT__ALLOW_ANY_SUBSCRIPTION_TYPE);
		createEReference(portEClass, PORT__CONNECTOR_PROFILES);
		createEAttribute(portEClass, PORT__INTERFACES);
		createEAttribute(portEClass, PORT__DATAFLOW_TYPE);
		createEAttribute(portEClass, PORT__SUBSCRIPTION_TYPE);
		createEAttribute(portEClass, PORT__DATA_TYPE);
		createEAttribute(portEClass, PORT__INTERFACE_TYPE);

		inPortEClass = createEClass(IN_PORT);

		outPortEClass = createEClass(OUT_PORT);

		servicePortEClass = createEClass(SERVICE_PORT);

		portSynchronizerEClass = createEClass(PORT_SYNCHRONIZER);
		createEAttribute(portSynchronizerEClass, PORT_SYNCHRONIZER__ORIGINAL_PORT_STRING);

		portConnectorEClass = createEClass(PORT_CONNECTOR);
		createEReference(portConnectorEClass, PORT_CONNECTOR__CONNECTOR_PROFILE);
		createEReference(portConnectorEClass, PORT_CONNECTOR__ROUTING_CONSTRAINT);
		createEReference(portConnectorEClass, PORT_CONNECTOR__SOURCE);
		createEReference(portConnectorEClass, PORT_CONNECTOR__TARGET);

		connectorProfileEClass = createEClass(CONNECTOR_PROFILE);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__DATAFLOW_TYPE);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__SUBSCRIPTION_TYPE);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__SUBSCRIPTION_TYPE_AVAILABLE);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__PUSH_INTERVAL_AVAILABLE);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__NAME);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__CONNECTOR_ID);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__DATA_TYPE);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__INTERFACE_TYPE);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__PUSH_RATE);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__PUSH_POLICY);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__SKIP_COUNT);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__PUSH_POLICY_AVAILABLE);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__SKIP_COUNT_AVAILABLE);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__SOURCE_STRING);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__TARGET_STRING);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__OUTPORT_BUFFER_LENGTH);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__OUTPORT_BUFFER_FULL_POLICY);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__OUTPORT_BUFFER_WRITE_TIMEOUT);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__OUTPORT_BUFFER_EMPTY_POLICY);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__OUTPORT_BUFFER_READ_TIMEOUT);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__INPORT_BUFFER_LENGTH);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__INPORT_BUFFER_FULL_POLICY);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__INPORT_BUFFER_WRITE_TIMEOUT);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__INPORT_BUFFER_EMPTY_POLICY);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__INPORT_BUFFER_READ_TIMEOUT);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__TIMESTAMP_POLICY);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__IS_REVERSE);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__OUTPORT_SERIALIZER_TYPE);
		createEAttribute(connectorProfileEClass, CONNECTOR_PROFILE__INPORT_SERIALIZER_TYPE);

		eIntegerObjectToPointMapEntryEClass = createEClass(EINTEGER_OBJECT_TO_POINT_MAP_ENTRY);
		createEAttribute(eIntegerObjectToPointMapEntryEClass, EINTEGER_OBJECT_TO_POINT_MAP_ENTRY__KEY);
		createEAttribute(eIntegerObjectToPointMapEntryEClass, EINTEGER_OBJECT_TO_POINT_MAP_ENTRY__VALUE);

		iPropertyMapEClass = createEClass(IPROPERTY_MAP);

		corbaComponentEClass = createEClass(CORBA_COMPONENT);
		createEAttribute(corbaComponentEClass, CORBA_COMPONENT__RTC_COMPONENT_PROFILE);
		createEAttribute(corbaComponentEClass, CORBA_COMPONENT__RTC_EXECUTION_CONTEXTS);
		createEAttribute(corbaComponentEClass, CORBA_COMPONENT__RTC_PARTICIPATION_CONTEXTS);
		createEAttribute(corbaComponentEClass, CORBA_COMPONENT__SDO_CONFIGURATION);
		createEAttribute(corbaComponentEClass, CORBA_COMPONENT__SDO_ORGANIZATION);
		createEAttribute(corbaComponentEClass, CORBA_COMPONENT__RTCRT_OBJECTS);
		createEAttribute(corbaComponentEClass, CORBA_COMPONENT__COMPONENT_STATE);
		createEAttribute(corbaComponentEClass, CORBA_COMPONENT__IOR);
		createEReference(corbaComponentEClass, CORBA_COMPONENT__STATUS_OBSERVER);
		createEReference(corbaComponentEClass, CORBA_COMPONENT__LOG_OBSERVER);

		corbaPortSynchronizerEClass = createEClass(CORBA_PORT_SYNCHRONIZER);
		createEAttribute(corbaPortSynchronizerEClass, CORBA_PORT_SYNCHRONIZER__RTC_PORT_PROFILE);

		corbaConnectorProfileEClass = createEClass(CORBA_CONNECTOR_PROFILE);
		createEAttribute(corbaConnectorProfileEClass, CORBA_CONNECTOR_PROFILE__RTC_CONNECTOR_PROFILE);

		corbaConfigurationSetEClass = createEClass(CORBA_CONFIGURATION_SET);
		createEAttribute(corbaConfigurationSetEClass, CORBA_CONFIGURATION_SET__SDO_CONFIGURATION_SET);

		corbaExecutionContextEClass = createEClass(CORBA_EXECUTION_CONTEXT);
		createEAttribute(corbaExecutionContextEClass, CORBA_EXECUTION_CONTEXT__RTC_EXECUTION_CONTEXT_PROFILE);

		corbaContextHandlerEClass = createEClass(CORBA_CONTEXT_HANDLER);

		corbaObserverEClass = createEClass(CORBA_OBSERVER);
		createEAttribute(corbaObserverEClass, CORBA_OBSERVER__SERVICE_PROFILE);
		createEAttribute(corbaObserverEClass, CORBA_OBSERVER__SERVANT);

		corbaStatusObserverEClass = createEClass(CORBA_STATUS_OBSERVER);

		corbaLogObserverEClass = createEClass(CORBA_LOG_OBSERVER);

		// Create enums
		systemDiagramKindEEnum = createEEnum(SYSTEM_DIAGRAM_KIND);

		// Create data types
		sdoConfigurationEDataType = createEDataType(SDO_CONFIGURATION);
		sdoConfigurationSetEDataType = createEDataType(SDO_CONFIGURATION_SET);
		sdoOrganizationEDataType = createEDataType(SDO_ORGANIZATION);
		sdoServiceProfileEDataType = createEDataType(SDO_SERVICE_PROFILE);
		rtcrtObjectEDataType = createEDataType(RTCRT_OBJECT);
		rtcComponentProfileEDataType = createEDataType(RTC_COMPONENT_PROFILE);
		rtcConnectorProfileEDataType = createEDataType(RTC_CONNECTOR_PROFILE);
		rtcPortProfileEDataType = createEDataType(RTC_PORT_PROFILE);
		rtcExecutionContextEDataType = createEDataType(RTC_EXECUTION_CONTEXT);
		rtcExecutionContextProfileEDataType = createEDataType(RTC_EXECUTION_CONTEXT_PROFILE);
		propertyChangeListenerEDataType = createEDataType(PROPERTY_CHANGE_LISTENER);
		portInterfaceProfileEDataType = createEDataType(PORT_INTERFACE_PROFILE);
		listEDataType = createEDataType(LIST);
		servantEDataType = createEDataType(SERVANT);
		iCorbaPortEventObserverEDataType = createEDataType(ICORBA_PORT_EVENT_OBSERVER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		CorePackage theCorePackage = (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		systemDiagramEClass.getESuperTypes().add(theCorePackage.getModelElement());
		systemDiagramEClass.getESuperTypes().add(this.getIPropertyMap());
		componentEClass.getESuperTypes().add(theCorePackage.getWrapperObject());
		componentEClass.getESuperTypes().add(this.getIPropertyMap());
		componentSpecificationEClass.getESuperTypes().add(this.getComponent());
		executionContextEClass.getESuperTypes().add(theCorePackage.getWrapperObject());
		executionContextEClass.getESuperTypes().add(this.getIPropertyMap());
		contextHandlerEClass.getESuperTypes().add(theCorePackage.getIAdaptable());
		configurationSetEClass.getESuperTypes().add(theCorePackage.getWrapperObject());
		nameValueEClass.getESuperTypes().add(theCorePackage.getWrapperObject());
		portEClass.getESuperTypes().add(theCorePackage.getWrapperObject());
		inPortEClass.getESuperTypes().add(this.getPort());
		outPortEClass.getESuperTypes().add(this.getPort());
		servicePortEClass.getESuperTypes().add(this.getPort());
		portSynchronizerEClass.getESuperTypes().add(this.getIPropertyMap());
		portConnectorEClass.getESuperTypes().add(theCorePackage.getWrapperObject());
		connectorProfileEClass.getESuperTypes().add(theCorePackage.getWrapperObject());
		connectorProfileEClass.getESuperTypes().add(this.getIPropertyMap());
		iPropertyMapEClass.getESuperTypes().add(theCorePackage.getIAdaptable());
		corbaComponentEClass.getESuperTypes().add(this.getComponent());
		corbaComponentEClass.getESuperTypes().add(theCorePackage.getCorbaWrapperObject());
		corbaPortSynchronizerEClass.getESuperTypes().add(theCorePackage.getCorbaWrapperObject());
		corbaPortSynchronizerEClass.getESuperTypes().add(this.getPortSynchronizer());
		corbaConnectorProfileEClass.getESuperTypes().add(this.getConnectorProfile());
		corbaConfigurationSetEClass.getESuperTypes().add(this.getConfigurationSet());
		corbaExecutionContextEClass.getESuperTypes().add(this.getExecutionContext());
		corbaExecutionContextEClass.getESuperTypes().add(theCorePackage.getCorbaWrapperObject());
		corbaContextHandlerEClass.getESuperTypes().add(this.getContextHandler());
		corbaObserverEClass.getESuperTypes().add(this.getIPropertyMap());
		corbaObserverEClass.getESuperTypes().add(theCorePackage.getIAdaptable());
		corbaStatusObserverEClass.getESuperTypes().add(this.getCorbaObserver());
		corbaLogObserverEClass.getESuperTypes().add(this.getCorbaObserver());

		// Initialize classes and features; add operations and parameters
		initEClass(systemDiagramEClass, SystemDiagram.class, "SystemDiagram", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSystemDiagram_Components(), this.getComponent(), null, "components", null, 0, -1, SystemDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSystemDiagram_Kind(), this.getSystemDiagramKind(), "kind", null, 0, 1, SystemDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSystemDiagram_ConnectorProcessing(), ecorePackage.getEBoolean(), "ConnectorProcessing", null, 0, 1, SystemDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSystemDiagram_SystemId(), ecorePackage.getEString(), "systemId", null, 0, 1, SystemDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSystemDiagram_CreationDate(), ecorePackage.getEString(), "creationDate", null, 0, 1, SystemDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSystemDiagram_UpdateDate(), ecorePackage.getEString(), "updateDate", null, 0, 1, SystemDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSystemDiagram_ParentSystemDiagram(), this.getSystemDiagram(), null, "parentSystemDiagram", null, 0, 1, SystemDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSystemDiagram_CompositeComponent(), this.getComponent(), null, "compositeComponent", null, 0, 1, SystemDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(systemDiagramEClass, null, "setSynchronizeInterval", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getELong(), "milliSecond", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(systemDiagramEClass, null, "addPropertyChangeListener", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getPropertyChangeListener(), "listener", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(systemDiagramEClass, null, "removePropertyChangeListener", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getPropertyChangeListener(), "listener", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(componentEClass, Component.class, "Component", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComponent_ConfigurationSets(), this.getConfigurationSet(), null, "configurationSets", null, 0, -1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_ActiveConfigurationSet(), this.getConfigurationSet(), null, "activeConfigurationSet", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_Ports(), this.getPort(), null, "ports", null, 0, -1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_Inports(), this.getInPort(), null, "inports", null, 0, -1, Component.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_Outports(), this.getOutPort(), null, "outports", null, 0, -1, Component.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_Serviceports(), this.getServicePort(), null, "serviceports", null, 0, -1, Component.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_Components(), this.getComponent(), null, "components", null, 0, -1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_PrimaryExecutionContext(), this.getExecutionContext(), null, "primaryExecutionContext", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_ExecutionContexts(), this.getExecutionContext(), null, "executionContexts", null, 0, -1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_ParticipationContexts(), this.getExecutionContext(), null, "participationContexts", null, 0, -1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_ExecutionContextHandler(), this.getContextHandler(), null, "executionContextHandler", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_ParticipationContextHandler(), this.getContextHandler(), null, "participationContextHandler", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_ChildSystemDiagram(), this.getSystemDiagram(), null, "childSystemDiagram", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_InstanceNameL(), ecorePackage.getEString(), "instanceNameL", "", 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_VenderL(), ecorePackage.getEString(), "venderL", "", 0, 1, Component.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_DescriptionL(), ecorePackage.getEString(), "descriptionL", "", 0, 1, Component.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_CategoryL(), ecorePackage.getEString(), "categoryL", "", 0, 1, Component.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_TypeNameL(), ecorePackage.getEString(), "typeNameL", "", 0, 1, Component.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_VersionL(), ecorePackage.getEString(), "versionL", "", 0, 1, Component.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_PathId(), ecorePackage.getEString(), "pathId", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_OutportDirection(), ecorePackage.getEString(), "outportDirection", "RIGHT", 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_CompositeTypeL(), ecorePackage.getEString(), "compositeTypeL", null, 0, 1, Component.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_ComponentId(), ecorePackage.getEString(), "componentId", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_Required(), ecorePackage.getEBoolean(), "required", "false", 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_StartUp(), ecorePackage.getEString(), "startUp", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_ShutDown(), ecorePackage.getEString(), "shutDown", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_Activation(), ecorePackage.getEString(), "activation", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_DeActivation(), ecorePackage.getEString(), "deActivation", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_Resetting(), ecorePackage.getEString(), "resetting", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_Initialize(), ecorePackage.getEString(), "initialize", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_Finalize(), ecorePackage.getEString(), "finalize", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(componentEClass, this.getComponent(), "getAllComponents", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(componentEClass, ecorePackage.getEBoolean(), "isCompositeComponent", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(componentEClass, ecorePackage.getEBoolean(), "isGroupingCompositeComponent", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(componentEClass, ecorePackage.getEBoolean(), "hasComponentAction", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(componentEClass, ecorePackage.getEBoolean(), "inOnlineSystemDiagram", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(componentEClass, ecorePackage.getEBoolean(), "setComponentsR", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getList(), "componentList", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(componentEClass, ecorePackage.getEBoolean(), "addComponentsR", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getList(), "componentList", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(componentEClass, ecorePackage.getEBoolean(), "removeComponentR", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getComponent(), "component", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(componentEClass, ecorePackage.getEBoolean(), "setExportedPorts", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "values", 0, -1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(componentEClass, ecorePackage.getEBoolean(), "updateConfigurationSetR", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getConfigurationSet(), "configSet", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "isActive", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(componentEClass, ecorePackage.getEBoolean(), "updateConfigurationSetListR", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getList(), "list", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getConfigurationSet(), "activeConfigurationSet", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getList(), "originallist", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(componentEClass, ecorePackage.getEString(), "getPath", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(componentSpecificationEClass, ComponentSpecification.class, "ComponentSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getComponentSpecification_AliasName(), ecorePackage.getEString(), "aliasName", null, 0, 1, ComponentSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentSpecification_SpecUnLoad(), ecorePackage.getEBoolean(), "specUnLoad", "false", 0, 1, ComponentSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentSpecification_RtcType(), ecorePackage.getEString(), "rtcType", null, 0, 1, ComponentSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(executionContextEClass, ExecutionContext.class, "ExecutionContext", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExecutionContext_KindL(), ecorePackage.getEInt(), "kindL", "-1", 0, 1, ExecutionContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecutionContext_RateL(), ecorePackage.getEDoubleObject(), "rateL", null, 0, 1, ExecutionContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecutionContext_StateL(), ecorePackage.getEInt(), "stateL", null, 0, 1, ExecutionContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExecutionContext_Owner(), this.getComponent(), null, "owner", null, 0, 1, ExecutionContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExecutionContext_Participants(), this.getComponent(), null, "participants", null, 0, -1, ExecutionContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(executionContextEClass, ecorePackage.getEString(), "getId", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(executionContextEClass, ecorePackage.getEString(), "getKindName", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(executionContextEClass, ecorePackage.getEString(), "getStateName", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(executionContextEClass, ecorePackage.getEBoolean(), "setRateR", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDoubleObject(), "rate", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(executionContextEClass, ecorePackage.getEBoolean(), "addComponentR", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getComponent(), "comp", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(executionContextEClass, ecorePackage.getEBoolean(), "removeComponentR", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getComponent(), "comp", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(executionContextEClass, ecorePackage.getEBoolean(), "containsComponent", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getComponent(), "comp", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(executionContextEClass, ecorePackage.getEBoolean(), "isOwner", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getComponent(), "comp", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(contextHandlerEClass, ContextHandler.class, "ContextHandler", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		op = addEOperation(contextHandlerEClass, this.getExecutionContext(), "setContext", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "id", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getExecutionContext(), "ec", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(contextHandlerEClass, this.getExecutionContext(), "getContext", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "id", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(contextHandlerEClass, ecorePackage.getEString(), "getId", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getExecutionContext(), "ec", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(contextHandlerEClass, this.getExecutionContext(), "removeContext", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "id", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(contextHandlerEClass, ecorePackage.getEString(), "removeId", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getExecutionContext(), "ec", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(contextHandlerEClass, null, "sync", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(contextHandlerEClass, this.getList(), "values", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(contextHandlerEClass, this.getList(), "keys", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(contextHandlerEClass, null, "clear", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(contextHandlerEClass, ecorePackage.getEString(), "getType", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(contextHandlerEClass, this.getExecutionContext(), "getOwnerContexts", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(configurationSetEClass, ConfigurationSet.class, "ConfigurationSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConfigurationSet_Id(), ecorePackage.getEString(), "id", null, 0, 1, ConfigurationSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConfigurationSet_ConfigurationData(), this.getNameValue(), null, "configurationData", null, 0, -1, ConfigurationSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nameValueEClass, NameValue.class, "NameValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNameValue_Name(), ecorePackage.getEString(), "name", null, 0, 1, NameValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNameValue_Value(), ecorePackage.getEString(), "value", "", 0, 1, NameValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNameValue_TypeName(), ecorePackage.getEString(), "typeName", "", 0, 1, NameValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(portEClass, Port.class, "Port", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPort_OriginalPortString(), ecorePackage.getEString(), "originalPortString", null, 0, 1, Port.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPort_Synchronizer(), this.getPortSynchronizer(), null, "synchronizer", null, 0, 1, Port.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPort_NameL(), ecorePackage.getEString(), "nameL", null, 0, 1, Port.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPort_AllowAnyDataType(), ecorePackage.getEBoolean(), "allowAnyDataType", null, 0, 1, Port.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPort_AllowAnyInterfaceType(), ecorePackage.getEBoolean(), "allowAnyInterfaceType", null, 0, 1, Port.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPort_AllowAnyDataflowType(), ecorePackage.getEBoolean(), "allowAnyDataflowType", null, 0, 1, Port.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPort_AllowAnySubscriptionType(), ecorePackage.getEBoolean(), "allowAnySubscriptionType", null, 0, 1, Port.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPort_ConnectorProfiles(), this.getConnectorProfile(), null, "connectorProfiles", null, 0, -1, Port.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPort_Interfaces(), this.getPortInterfaceProfile(), "interfaces", null, 0, -1, Port.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPort_DataflowType(), ecorePackage.getEString(), "dataflowType", null, 0, 1, Port.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPort_SubscriptionType(), ecorePackage.getEString(), "subscriptionType", null, 0, 1, Port.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPort_DataType(), ecorePackage.getEString(), "dataType", null, 0, 1, Port.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPort_InterfaceType(), ecorePackage.getEString(), "interfaceType", null, 0, 1, Port.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(portEClass, null, "disconnectAll", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(portEClass, this.getPort(), "findPort", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getSystemDiagram(), "diagram", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "originalPortString", 0, 1, !IS_UNIQUE, IS_ORDERED);

		op = addEOperation(portEClass, ecorePackage.getEBoolean(), "validateTargetConnector", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getPort(), "target", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(portEClass, ecorePackage.getEBoolean(), "validateSourceConnector", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getPort(), "source", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(inPortEClass, InPort.class, "InPort", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(outPortEClass, OutPort.class, "OutPort", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(servicePortEClass, ServicePort.class, "ServicePort", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(portSynchronizerEClass, PortSynchronizer.class, "PortSynchronizer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPortSynchronizer_OriginalPortString(), ecorePackage.getEString(), "originalPortString", null, 0, 1, PortSynchronizer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(portSynchronizerEClass, ecorePackage.getEBoolean(), "disconnect", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "conn_id", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(portSynchronizerEClass, ecorePackage.getEBoolean(), "disconnect", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getConnectorProfile(), "conn_prof", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(portSynchronizerEClass, ecorePackage.getEBoolean(), "disconnectAll", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(portConnectorEClass, PortConnector.class, "PortConnector", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPortConnector_ConnectorProfile(), this.getConnectorProfile(), null, "connectorProfile", null, 0, 1, PortConnector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPortConnector_RoutingConstraint(), this.getEIntegerObjectToPointMapEntry(), null, "routingConstraint", null, 0, -1, PortConnector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPortConnector_Source(), this.getPort(), null, "source", null, 0, 1, PortConnector.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPortConnector_Target(), this.getPort(), null, "target", null, 0, 1, PortConnector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(portConnectorEClass, ecorePackage.getEBoolean(), "createConnectorR", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(portConnectorEClass, ecorePackage.getEBoolean(), "deleteConnectorR", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(connectorProfileEClass, ConnectorProfile.class, "ConnectorProfile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConnectorProfile_DataflowType(), ecorePackage.getEString(), "dataflowType", null, 0, 1, ConnectorProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorProfile_SubscriptionType(), ecorePackage.getEString(), "subscriptionType", null, 0, 1, ConnectorProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorProfile_SubscriptionTypeAvailable(), ecorePackage.getEBoolean(), "subscriptionTypeAvailable", null, 0, 1, ConnectorProfile.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorProfile_PushIntervalAvailable(), ecorePackage.getEBoolean(), "pushIntervalAvailable", null, 0, 1, ConnectorProfile.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorProfile_Name(), ecorePackage.getEString(), "name", null, 0, 1, ConnectorProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorProfile_ConnectorId(), ecorePackage.getEString(), "connectorId", null, 0, 1, ConnectorProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorProfile_DataType(), ecorePackage.getEString(), "dataType", null, 0, 1, ConnectorProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorProfile_InterfaceType(), ecorePackage.getEString(), "interfaceType", null, 0, 1, ConnectorProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorProfile_PushRate(), ecorePackage.getEDoubleObject(), "pushRate", null, 0, 1, ConnectorProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorProfile_PushPolicy(), ecorePackage.getEString(), "pushPolicy", null, 0, 1, ConnectorProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorProfile_SkipCount(), ecorePackage.getEIntegerObject(), "skipCount", null, 0, 1, ConnectorProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorProfile_PushPolicyAvailable(), ecorePackage.getEBoolean(), "pushPolicyAvailable", null, 0, 1, ConnectorProfile.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorProfile_SkipCountAvailable(), ecorePackage.getEBoolean(), "skipCountAvailable", null, 0, 1, ConnectorProfile.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorProfile_SourceString(), ecorePackage.getEString(), "sourceString", null, 0, 1, ConnectorProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorProfile_TargetString(), ecorePackage.getEString(), "targetString", null, 0, 1, ConnectorProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorProfile_OutportBufferLength(), ecorePackage.getEIntegerObject(), "outportBufferLength", null, 0, 1, ConnectorProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorProfile_OutportBufferFullPolicy(), ecorePackage.getEString(), "outportBufferFullPolicy", null, 0, 1, ConnectorProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorProfile_OutportBufferWriteTimeout(), ecorePackage.getEDoubleObject(), "outportBufferWriteTimeout", null, 0, 1, ConnectorProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorProfile_OutportBufferEmptyPolicy(), ecorePackage.getEString(), "outportBufferEmptyPolicy", null, 0, 1, ConnectorProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorProfile_OutportBufferReadTimeout(), ecorePackage.getEDoubleObject(), "outportBufferReadTimeout", null, 0, 1, ConnectorProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorProfile_InportBufferLength(), ecorePackage.getEIntegerObject(), "inportBufferLength", null, 0, 1, ConnectorProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorProfile_InportBufferFullPolicy(), ecorePackage.getEString(), "inportBufferFullPolicy", null, 0, 1, ConnectorProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorProfile_InportBufferWriteTimeout(), ecorePackage.getEDoubleObject(), "inportBufferWriteTimeout", null, 0, 1, ConnectorProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorProfile_InportBufferEmptyPolicy(), ecorePackage.getEString(), "inportBufferEmptyPolicy", null, 0, 1, ConnectorProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorProfile_InportBufferReadTimeout(), ecorePackage.getEDoubleObject(), "inportBufferReadTimeout", null, 0, 1, ConnectorProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorProfile_TimestampPolicy(), ecorePackage.getEString(), "timestampPolicy", null, 0, 1, ConnectorProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorProfile_IsReverse(), ecorePackage.getEBoolean(), "isReverse", null, 0, 1, ConnectorProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorProfile_OutportSerializerType(), ecorePackage.getEString(), "outportSerializerType", null, 0, 1, ConnectorProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorProfile_InportSerializerType(), ecorePackage.getEString(), "inportSerializerType", null, 0, 1, ConnectorProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eIntegerObjectToPointMapEntryEClass, Map.Entry.class, "EIntegerObjectToPointMapEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEIntegerObjectToPointMapEntry_Key(), ecorePackage.getEIntegerObject(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEIntegerObjectToPointMapEntry_Value(), theCorePackage.getPoint(), "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iPropertyMapEClass, IPropertyMap.class, "IPropertyMap", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		op = addEOperation(iPropertyMapEClass, ecorePackage.getEString(), "getProperty", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "key", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(iPropertyMapEClass, null, "setProperty", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "key", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "value", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(iPropertyMapEClass, ecorePackage.getEString(), "removeProperty", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "key", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(iPropertyMapEClass, ecorePackage.getEString(), "getPropertyKeys", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(iPropertyMapEClass, this.getIPropertyMap(), "getPropertyMap", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(corbaComponentEClass, CorbaComponent.class, "CorbaComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCorbaComponent_RTCComponentProfile(), this.getRTCComponentProfile(), "rTCComponentProfile", null, 0, 1, CorbaComponent.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCorbaComponent_RTCExecutionContexts(), this.getRTCExecutionContext(), "rTCExecutionContexts", "", 0, -1, CorbaComponent.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCorbaComponent_RTCParticipationContexts(), this.getRTCExecutionContext(), "rTCParticipationContexts", "", 0, -1, CorbaComponent.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCorbaComponent_SDOConfiguration(), this.getSDOConfiguration(), "sDOConfiguration", null, 0, 1, CorbaComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCorbaComponent_SDOOrganization(), this.getSDOOrganization(), "sDOOrganization", "", 0, 1, CorbaComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCorbaComponent_RTCRTObjects(), this.getRTCRTObject(), "rTCRTObjects", "", 0, -1, CorbaComponent.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCorbaComponent_ComponentState(), ecorePackage.getEInt(), "componentState", null, 0, 1, CorbaComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCorbaComponent_Ior(), ecorePackage.getEString(), "ior", null, 0, 1, CorbaComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCorbaComponent_StatusObserver(), this.getCorbaStatusObserver(), null, "statusObserver", null, 0, 1, CorbaComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCorbaComponent_LogObserver(), this.getCorbaLogObserver(), null, "logObserver", null, 0, 1, CorbaComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(corbaComponentEClass, ecorePackage.getEInt(), "startR", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(corbaComponentEClass, ecorePackage.getEInt(), "stopR", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(corbaComponentEClass, ecorePackage.getEInt(), "activateR", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(corbaComponentEClass, ecorePackage.getEInt(), "deactivateR", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(corbaComponentEClass, ecorePackage.getEInt(), "resetR", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(corbaComponentEClass, ecorePackage.getEInt(), "finalizeR", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(corbaComponentEClass, ecorePackage.getEInt(), "exitR", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(corbaComponentEClass, ecorePackage.getEInt(), "getExecutionContextState", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(corbaComponentEClass, ecorePackage.getEInt(), "getExecutionContextState", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getExecutionContext(), "ec", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(corbaComponentEClass, ecorePackage.getEString(), "getExecutionContextStateName", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(corbaComponentEClass, ecorePackage.getEString(), "getExecutionContextStateName", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getExecutionContext(), "ec", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(corbaComponentEClass, ecorePackage.getEString(), "getComponentStateName", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(corbaComponentEClass, this.getRTCRTObject(), "getCorbaObjectInterface", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(corbaComponentEClass, ecorePackage.getEBoolean(), "supportedCorbaObserver", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(corbaComponentEClass, null, "activateAll", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(corbaComponentEClass, null, "deactivateAll", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(corbaComponentEClass, null, "startAll", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(corbaComponentEClass, null, "stopAll", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(corbaComponentEClass, null, "attachPortEventObserver", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getICorbaPortEventObserver(), "observer", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(corbaComponentEClass, null, "detatchPortEventObserver", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getICorbaPortEventObserver(), "observer", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(corbaPortSynchronizerEClass, CorbaPortSynchronizer.class, "CorbaPortSynchronizer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCorbaPortSynchronizer_RTCPortProfile(), this.getRTCPortProfile(), "rTCPortProfile", null, 0, 1, CorbaPortSynchronizer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(corbaConnectorProfileEClass, CorbaConnectorProfile.class, "CorbaConnectorProfile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCorbaConnectorProfile_RtcConnectorProfile(), this.getRTCConnectorProfile(), "rtcConnectorProfile", null, 0, 1, CorbaConnectorProfile.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(corbaConfigurationSetEClass, CorbaConfigurationSet.class, "CorbaConfigurationSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCorbaConfigurationSet_SDOConfigurationSet(), this.getSDOConfigurationSet(), "sDOConfigurationSet", null, 0, 1, CorbaConfigurationSet.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(corbaExecutionContextEClass, CorbaExecutionContext.class, "CorbaExecutionContext", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCorbaExecutionContext_RtcExecutionContextProfile(), this.getRTCExecutionContextProfile(), "rtcExecutionContextProfile", null, 0, 1, CorbaExecutionContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(corbaExecutionContextEClass, ecorePackage.getEInt(), "startR", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(corbaExecutionContextEClass, ecorePackage.getEInt(), "stopR", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(corbaExecutionContextEClass, ecorePackage.getEInt(), "activateR", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getComponent(), "comp", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(corbaExecutionContextEClass, ecorePackage.getEInt(), "deactivateR", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getComponent(), "comp", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(corbaExecutionContextEClass, ecorePackage.getEInt(), "resetR", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getComponent(), "comp", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(corbaExecutionContextEClass, ecorePackage.getEInt(), "getComponentState", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getComponent(), "comp", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(corbaExecutionContextEClass, ecorePackage.getEString(), "getComponentStateName", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getComponent(), "comp", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(corbaContextHandlerEClass, CorbaContextHandler.class, "CorbaContextHandler", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(corbaObserverEClass, CorbaObserver.class, "CorbaObserver", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCorbaObserver_ServiceProfile(), this.getSDOServiceProfile(), "serviceProfile", null, 0, 1, CorbaObserver.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCorbaObserver_Servant(), this.getServant(), "servant", null, 0, 1, CorbaObserver.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(corbaObserverEClass, null, "activate", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(corbaObserverEClass, null, "deactivate", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(corbaObserverEClass, ecorePackage.getEBoolean(), "attachComponent", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getCorbaComponent(), "component", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(corbaObserverEClass, ecorePackage.getEBoolean(), "detachComponent", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(corbaObserverEClass, ecorePackage.getEBoolean(), "finish", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(corbaStatusObserverEClass, CorbaStatusObserver.class, "CorbaStatusObserver", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(corbaStatusObserverEClass, ecorePackage.getEBoolean(), "isTimeOut", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(corbaLogObserverEClass, CorbaLogObserver.class, "CorbaLogObserver", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(systemDiagramKindEEnum, SystemDiagramKind.class, "SystemDiagramKind");
		addEEnumLiteral(systemDiagramKindEEnum, SystemDiagramKind.ONLINE_LITERAL);
		addEEnumLiteral(systemDiagramKindEEnum, SystemDiagramKind.OFFLINE_LITERAL);

		// Initialize data types
		initEDataType(sdoConfigurationEDataType, Configuration.class, "SDOConfiguration", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(sdoConfigurationSetEDataType, _SDOPackage.ConfigurationSet.class, "SDOConfigurationSet", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(sdoOrganizationEDataType, Organization.class, "SDOOrganization", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(sdoServiceProfileEDataType, ServiceProfile.class, "SDOServiceProfile", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(rtcrtObjectEDataType, RTObject.class, "RTCRTObject", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(rtcComponentProfileEDataType, ComponentProfile.class, "RTCComponentProfile", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(rtcConnectorProfileEDataType, RTC.ConnectorProfile.class, "RTCConnectorProfile", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(rtcPortProfileEDataType, PortProfile.class, "RTCPortProfile", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(rtcExecutionContextEDataType, RTC.ExecutionContext.class, "RTCExecutionContext", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(rtcExecutionContextProfileEDataType, ExecutionContextProfile.class, "RTCExecutionContextProfile", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(propertyChangeListenerEDataType, PropertyChangeListener.class, "PropertyChangeListener", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(portInterfaceProfileEDataType, PortInterfaceProfile.class, "PortInterfaceProfile", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(listEDataType, List.class, "List", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(servantEDataType, Servant.class, "Servant", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(iCorbaPortEventObserverEDataType, ICorbaPortEventObserver.class, "ICorbaPortEventObserver", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //ComponentPackageImpl
