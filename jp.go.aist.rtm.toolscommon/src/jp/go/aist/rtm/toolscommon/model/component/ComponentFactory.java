/**
 * <copyright>
 * </copyright>
 *
 * $Id: ComponentFactory.java,v 1.4 2008/03/04 12:47:29 terui Exp $
 */
package jp.go.aist.rtm.toolscommon.model.component;


import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage
 * @generated
 */
public interface ComponentFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ComponentFactory eINSTANCE = jp.go.aist.rtm.toolscommon.model.component.impl.ComponentFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>System Diagram</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>System Diagram</em>'.
	 * @generated
	 */
	SystemDiagram createSystemDiagram();

	/**
	 * Returns a new object of class '<em>Corba Component</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Corba Component</em>'.
	 * @generated
	 */
	CorbaComponent createCorbaComponent();

	/**
	 * Returns a new object of class '<em>Connector Profile</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connector Profile</em>'.
	 * @generated
	 */
	ConnectorProfile createConnectorProfile();

	/**
	 * Returns a new object of class '<em>Configuration Set</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Configuration Set</em>'.
	 * @generated
	 */
	ConfigurationSet createConfigurationSet();

	/**
	 * Returns a new object of class '<em>Port Synchronizer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Port Synchronizer</em>'.
	 * @generated
	 */
	PortSynchronizer createPortSynchronizer();

	/**
	 * Returns a new object of class '<em>Corba Port Synchronizer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Corba Port Synchronizer</em>'.
	 * @generated
	 */
	CorbaPortSynchronizer createCorbaPortSynchronizer();

	/**
	 * Returns a new object of class '<em>Corba Connector Profile</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Corba Connector Profile</em>'.
	 * @generated
	 */
	CorbaConnectorProfile createCorbaConnectorProfile();

	/**
	 * Returns a new object of class '<em>Corba Configuration Set</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Corba Configuration Set</em>'.
	 * @generated
	 */
	CorbaConfigurationSet createCorbaConfigurationSet();

	/**
	 * Returns a new object of class '<em>Corba Execution Context</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Corba Execution Context</em>'.
	 * @generated
	 */
	CorbaExecutionContext createCorbaExecutionContext();

	/**
	 * Returns a new object of class '<em>Corba Context Handler</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Corba Context Handler</em>'.
	 * @generated
	 */
	CorbaContextHandler createCorbaContextHandler();

	/**
	 * Returns a new object of class '<em>Corba Observer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Corba Observer</em>'.
	 * @generated
	 */
	CorbaObserver createCorbaObserver();

	/**
	 * Returns a new object of class '<em>Corba Status Observer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Corba Status Observer</em>'.
	 * @generated
	 */
	CorbaStatusObserver createCorbaStatusObserver();

	/**
	 * Returns a new object of class '<em>Specification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Specification</em>'.
	 * @generated
	 */
	ComponentSpecification createComponentSpecification();

	/**
	 * Returns a new object of class '<em>In Port</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>In Port</em>'.
	 * @generated
	 */
	InPort createInPort();

	/**
	 * Returns a new object of class '<em>Out Port</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Out Port</em>'.
	 * @generated
	 */
	OutPort createOutPort();

	/**
	 * Returns a new object of class '<em>Port</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Port</em>'.
	 * @generated
	 */
	Port createPort();

	/**
	 * Returns a new object of class '<em>Service Port</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Service Port</em>'.
	 * @generated
	 */
	ServicePort createServicePort();

	/**
	 * Returns a new object of class '<em>Execution Context</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Execution Context</em>'.
	 * @generated
	 */
	ExecutionContext createExecutionContext();

	/**
	 * Returns a new object of class '<em>Context Handler</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Context Handler</em>'.
	 * @generated
	 */
	ContextHandler createContextHandler();

	/**
	 * Returns a new object of class '<em>Name Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Name Value</em>'.
	 * @generated
	 */
	NameValue createNameValue();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ComponentPackage getComponentPackage();

} //ComponentFactory
