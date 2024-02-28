/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component;

import jp.go.aist.rtm.toolscommon.model.core.CorePackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentFactory
 * @model kind="package"
 * @generated
 */
public interface ComponentPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "component";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///jp/go/aist/rtm/toolscommon/model/component.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "jp.go.aist.rtm.toolscommon.model.component";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ComponentPackage eINSTANCE = jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl.init();

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.SystemDiagramImpl <em>System Diagram</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.SystemDiagramImpl
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getSystemDiagram()
	 * @generated
	 */
	int SYSTEM_DIAGRAM = 0;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_DIAGRAM__CONSTRAINT = CorePackage.MODEL_ELEMENT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Components</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_DIAGRAM__COMPONENTS = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_DIAGRAM__KIND = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Connector Processing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_DIAGRAM__CONNECTOR_PROCESSING = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>System Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_DIAGRAM__SYSTEM_ID = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Creation Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_DIAGRAM__CREATION_DATE = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Update Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_DIAGRAM__UPDATE_DATE = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Parent System Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_DIAGRAM__PARENT_SYSTEM_DIAGRAM = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Composite Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_DIAGRAM__COMPOSITE_COMPONENT = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>System Diagram</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_DIAGRAM_FEATURE_COUNT = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentImpl <em>Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentImpl
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getComponent()
	 * @generated
	 */
	int COMPONENT = 1;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__CONSTRAINT = CorePackage.WRAPPER_OBJECT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Configuration Sets</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__CONFIGURATION_SETS = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Active Configuration Set</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__ACTIVE_CONFIGURATION_SET = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__PORTS = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Inports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__INPORTS = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Outports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__OUTPORTS = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Serviceports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__SERVICEPORTS = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__COMPONENTS = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Primary Execution Context</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__PRIMARY_EXECUTION_CONTEXT = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Execution Contexts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__EXECUTION_CONTEXTS = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Participation Contexts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__PARTICIPATION_CONTEXTS = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Execution Context Handler</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__EXECUTION_CONTEXT_HANDLER = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Participation Context Handler</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__PARTICIPATION_CONTEXT_HANDLER = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Child System Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__CHILD_SYSTEM_DIAGRAM = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Instance Name L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__INSTANCE_NAME_L = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Vender L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__VENDER_L = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Description L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__DESCRIPTION_L = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Category L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__CATEGORY_L = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Type Name L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__TYPE_NAME_L = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>Version L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__VERSION_L = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>Path Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__PATH_ID = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 19;

	/**
	 * The feature id for the '<em><b>Outport Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__OUTPORT_DIRECTION = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 20;

	/**
	 * The feature id for the '<em><b>Composite Type L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__COMPOSITE_TYPE_L = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 21;

	/**
	 * The feature id for the '<em><b>Component Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__COMPONENT_ID = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 22;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__REQUIRED = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 23;

	/**
	 * The feature id for the '<em><b>Start Up</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__START_UP = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 24;

	/**
	 * The feature id for the '<em><b>Shut Down</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__SHUT_DOWN = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 25;

	/**
	 * The feature id for the '<em><b>Activation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__ACTIVATION = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 26;

	/**
	 * The feature id for the '<em><b>De Activation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__DE_ACTIVATION = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 27;

	/**
	 * The feature id for the '<em><b>Resetting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__RESETTING = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 28;

	/**
	 * The feature id for the '<em><b>Initialize</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__INITIALIZE = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 29;

	/**
	 * The feature id for the '<em><b>Finalize</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__FINALIZE = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 30;

	/**
	 * The number of structural features of the '<em>Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_FEATURE_COUNT = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 31;

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl <em>Corba Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getCorbaComponent()
	 * @generated
	 */
	int CORBA_COMPONENT = 16;

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentSpecificationImpl <em>Specification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentSpecificationImpl
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getComponentSpecification()
	 * @generated
	 */
	int COMPONENT_SPECIFICATION = 2;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__CONSTRAINT = COMPONENT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Configuration Sets</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__CONFIGURATION_SETS = COMPONENT__CONFIGURATION_SETS;

	/**
	 * The feature id for the '<em><b>Active Configuration Set</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__ACTIVE_CONFIGURATION_SET = COMPONENT__ACTIVE_CONFIGURATION_SET;

	/**
	 * The feature id for the '<em><b>Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__PORTS = COMPONENT__PORTS;

	/**
	 * The feature id for the '<em><b>Inports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__INPORTS = COMPONENT__INPORTS;

	/**
	 * The feature id for the '<em><b>Outports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__OUTPORTS = COMPONENT__OUTPORTS;

	/**
	 * The feature id for the '<em><b>Serviceports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__SERVICEPORTS = COMPONENT__SERVICEPORTS;

	/**
	 * The feature id for the '<em><b>Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__COMPONENTS = COMPONENT__COMPONENTS;

	/**
	 * The feature id for the '<em><b>Primary Execution Context</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__PRIMARY_EXECUTION_CONTEXT = COMPONENT__PRIMARY_EXECUTION_CONTEXT;

	/**
	 * The feature id for the '<em><b>Execution Contexts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__EXECUTION_CONTEXTS = COMPONENT__EXECUTION_CONTEXTS;

	/**
	 * The feature id for the '<em><b>Participation Contexts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__PARTICIPATION_CONTEXTS = COMPONENT__PARTICIPATION_CONTEXTS;

	/**
	 * The feature id for the '<em><b>Execution Context Handler</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__EXECUTION_CONTEXT_HANDLER = COMPONENT__EXECUTION_CONTEXT_HANDLER;

	/**
	 * The feature id for the '<em><b>Participation Context Handler</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__PARTICIPATION_CONTEXT_HANDLER = COMPONENT__PARTICIPATION_CONTEXT_HANDLER;

	/**
	 * The feature id for the '<em><b>Child System Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__CHILD_SYSTEM_DIAGRAM = COMPONENT__CHILD_SYSTEM_DIAGRAM;

	/**
	 * The feature id for the '<em><b>Instance Name L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__INSTANCE_NAME_L = COMPONENT__INSTANCE_NAME_L;

	/**
	 * The feature id for the '<em><b>Vender L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__VENDER_L = COMPONENT__VENDER_L;

	/**
	 * The feature id for the '<em><b>Description L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__DESCRIPTION_L = COMPONENT__DESCRIPTION_L;

	/**
	 * The feature id for the '<em><b>Category L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__CATEGORY_L = COMPONENT__CATEGORY_L;

	/**
	 * The feature id for the '<em><b>Type Name L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__TYPE_NAME_L = COMPONENT__TYPE_NAME_L;

	/**
	 * The feature id for the '<em><b>Version L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__VERSION_L = COMPONENT__VERSION_L;

	/**
	 * The feature id for the '<em><b>Path Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__PATH_ID = COMPONENT__PATH_ID;

	/**
	 * The feature id for the '<em><b>Outport Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__OUTPORT_DIRECTION = COMPONENT__OUTPORT_DIRECTION;

	/**
	 * The feature id for the '<em><b>Composite Type L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__COMPOSITE_TYPE_L = COMPONENT__COMPOSITE_TYPE_L;

	/**
	 * The feature id for the '<em><b>Component Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__COMPONENT_ID = COMPONENT__COMPONENT_ID;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__REQUIRED = COMPONENT__REQUIRED;

	/**
	 * The feature id for the '<em><b>Start Up</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__START_UP = COMPONENT__START_UP;

	/**
	 * The feature id for the '<em><b>Shut Down</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__SHUT_DOWN = COMPONENT__SHUT_DOWN;

	/**
	 * The feature id for the '<em><b>Activation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__ACTIVATION = COMPONENT__ACTIVATION;

	/**
	 * The feature id for the '<em><b>De Activation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__DE_ACTIVATION = COMPONENT__DE_ACTIVATION;

	/**
	 * The feature id for the '<em><b>Resetting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__RESETTING = COMPONENT__RESETTING;

	/**
	 * The feature id for the '<em><b>Initialize</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__INITIALIZE = COMPONENT__INITIALIZE;

	/**
	 * The feature id for the '<em><b>Finalize</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__FINALIZE = COMPONENT__FINALIZE;

	/**
	 * The feature id for the '<em><b>Alias Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__ALIAS_NAME = COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Spec Un Load</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__SPEC_UN_LOAD = COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Rtc Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION__RTC_TYPE = COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Specification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPECIFICATION_FEATURE_COUNT = COMPONENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.PortConnectorImpl <em>Port Connector</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.PortConnectorImpl
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getPortConnector()
	 * @generated
	 */
	int PORT_CONNECTOR = 12;

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.ExecutionContextImpl <em>Execution Context</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ExecutionContextImpl
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getExecutionContext()
	 * @generated
	 */
	int EXECUTION_CONTEXT = 3;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTEXT__CONSTRAINT = CorePackage.WRAPPER_OBJECT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Kind L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTEXT__KIND_L = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Rate L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTEXT__RATE_L = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>State L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTEXT__STATE_L = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTEXT__OWNER = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTEXT__PARTICIPANTS = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Execution Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTEXT_FEATURE_COUNT = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.ContextHandlerImpl <em>Context Handler</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ContextHandlerImpl
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getContextHandler()
	 * @generated
	 */
	int CONTEXT_HANDLER = 4;

	/**
	 * The number of structural features of the '<em>Context Handler</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_HANDLER_FEATURE_COUNT = CorePackage.IADAPTABLE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.PortImpl <em>Port</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.PortImpl
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getPort()
	 * @generated
	 */
	int PORT = 7;

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.InPortImpl <em>In Port</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.InPortImpl
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getInPort()
	 * @generated
	 */
	int IN_PORT = 8;

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.NameValueImpl <em>Name Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.NameValueImpl
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getNameValue()
	 * @generated
	 */
	int NAME_VALUE = 6;

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.OutPortImpl <em>Out Port</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.OutPortImpl
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getOutPort()
	 * @generated
	 */
	int OUT_PORT = 9;

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.ServicePortImpl <em>Service Port</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ServicePortImpl
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getServicePort()
	 * @generated
	 */
	int SERVICE_PORT = 10;

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl <em>Connector Profile</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getConnectorProfile()
	 * @generated
	 */
	int CONNECTOR_PROFILE = 13;

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConfigurationSetImpl <em>Configuration Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ConfigurationSetImpl
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getConfigurationSet()
	 * @generated
	 */
	int CONFIGURATION_SET = 5;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_SET__CONSTRAINT = CorePackage.WRAPPER_OBJECT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_SET__ID = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Configuration Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_SET__CONFIGURATION_DATA = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Configuration Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_SET_FEATURE_COUNT = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_VALUE__CONSTRAINT = CorePackage.WRAPPER_OBJECT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_VALUE__NAME = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_VALUE__VALUE = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_VALUE__TYPE_NAME = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Name Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_VALUE_FEATURE_COUNT = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__CONSTRAINT = CorePackage.WRAPPER_OBJECT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Original Port String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__ORIGINAL_PORT_STRING = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Synchronizer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__SYNCHRONIZER = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__NAME_L = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Allow Any Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__ALLOW_ANY_DATA_TYPE = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Allow Any Interface Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__ALLOW_ANY_INTERFACE_TYPE = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Allow Any Dataflow Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__ALLOW_ANY_DATAFLOW_TYPE = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Allow Any Subscription Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__ALLOW_ANY_SUBSCRIPTION_TYPE = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Connector Profiles</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__CONNECTOR_PROFILES = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Interfaces</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__INTERFACES = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Dataflow Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__DATAFLOW_TYPE = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Subscription Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__SUBSCRIPTION_TYPE = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__DATA_TYPE = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Interface Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__INTERFACE_TYPE = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 12;

	/**
	 * The number of structural features of the '<em>Port</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_FEATURE_COUNT = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PORT__CONSTRAINT = PORT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Original Port String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PORT__ORIGINAL_PORT_STRING = PORT__ORIGINAL_PORT_STRING;

	/**
	 * The feature id for the '<em><b>Synchronizer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PORT__SYNCHRONIZER = PORT__SYNCHRONIZER;

	/**
	 * The feature id for the '<em><b>Name L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PORT__NAME_L = PORT__NAME_L;

	/**
	 * The feature id for the '<em><b>Allow Any Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PORT__ALLOW_ANY_DATA_TYPE = PORT__ALLOW_ANY_DATA_TYPE;

	/**
	 * The feature id for the '<em><b>Allow Any Interface Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PORT__ALLOW_ANY_INTERFACE_TYPE = PORT__ALLOW_ANY_INTERFACE_TYPE;

	/**
	 * The feature id for the '<em><b>Allow Any Dataflow Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PORT__ALLOW_ANY_DATAFLOW_TYPE = PORT__ALLOW_ANY_DATAFLOW_TYPE;

	/**
	 * The feature id for the '<em><b>Allow Any Subscription Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PORT__ALLOW_ANY_SUBSCRIPTION_TYPE = PORT__ALLOW_ANY_SUBSCRIPTION_TYPE;

	/**
	 * The feature id for the '<em><b>Connector Profiles</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PORT__CONNECTOR_PROFILES = PORT__CONNECTOR_PROFILES;

	/**
	 * The feature id for the '<em><b>Interfaces</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PORT__INTERFACES = PORT__INTERFACES;

	/**
	 * The feature id for the '<em><b>Dataflow Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PORT__DATAFLOW_TYPE = PORT__DATAFLOW_TYPE;

	/**
	 * The feature id for the '<em><b>Subscription Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PORT__SUBSCRIPTION_TYPE = PORT__SUBSCRIPTION_TYPE;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PORT__DATA_TYPE = PORT__DATA_TYPE;

	/**
	 * The feature id for the '<em><b>Interface Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PORT__INTERFACE_TYPE = PORT__INTERFACE_TYPE;

	/**
	 * The number of structural features of the '<em>In Port</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PORT_FEATURE_COUNT = PORT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PORT__CONSTRAINT = PORT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Original Port String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PORT__ORIGINAL_PORT_STRING = PORT__ORIGINAL_PORT_STRING;

	/**
	 * The feature id for the '<em><b>Synchronizer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PORT__SYNCHRONIZER = PORT__SYNCHRONIZER;

	/**
	 * The feature id for the '<em><b>Name L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PORT__NAME_L = PORT__NAME_L;

	/**
	 * The feature id for the '<em><b>Allow Any Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PORT__ALLOW_ANY_DATA_TYPE = PORT__ALLOW_ANY_DATA_TYPE;

	/**
	 * The feature id for the '<em><b>Allow Any Interface Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PORT__ALLOW_ANY_INTERFACE_TYPE = PORT__ALLOW_ANY_INTERFACE_TYPE;

	/**
	 * The feature id for the '<em><b>Allow Any Dataflow Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PORT__ALLOW_ANY_DATAFLOW_TYPE = PORT__ALLOW_ANY_DATAFLOW_TYPE;

	/**
	 * The feature id for the '<em><b>Allow Any Subscription Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PORT__ALLOW_ANY_SUBSCRIPTION_TYPE = PORT__ALLOW_ANY_SUBSCRIPTION_TYPE;

	/**
	 * The feature id for the '<em><b>Connector Profiles</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PORT__CONNECTOR_PROFILES = PORT__CONNECTOR_PROFILES;

	/**
	 * The feature id for the '<em><b>Interfaces</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PORT__INTERFACES = PORT__INTERFACES;

	/**
	 * The feature id for the '<em><b>Dataflow Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PORT__DATAFLOW_TYPE = PORT__DATAFLOW_TYPE;

	/**
	 * The feature id for the '<em><b>Subscription Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PORT__SUBSCRIPTION_TYPE = PORT__SUBSCRIPTION_TYPE;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PORT__DATA_TYPE = PORT__DATA_TYPE;

	/**
	 * The feature id for the '<em><b>Interface Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PORT__INTERFACE_TYPE = PORT__INTERFACE_TYPE;

	/**
	 * The number of structural features of the '<em>Out Port</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PORT_FEATURE_COUNT = PORT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PORT__CONSTRAINT = PORT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Original Port String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PORT__ORIGINAL_PORT_STRING = PORT__ORIGINAL_PORT_STRING;

	/**
	 * The feature id for the '<em><b>Synchronizer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PORT__SYNCHRONIZER = PORT__SYNCHRONIZER;

	/**
	 * The feature id for the '<em><b>Name L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PORT__NAME_L = PORT__NAME_L;

	/**
	 * The feature id for the '<em><b>Allow Any Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PORT__ALLOW_ANY_DATA_TYPE = PORT__ALLOW_ANY_DATA_TYPE;

	/**
	 * The feature id for the '<em><b>Allow Any Interface Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PORT__ALLOW_ANY_INTERFACE_TYPE = PORT__ALLOW_ANY_INTERFACE_TYPE;

	/**
	 * The feature id for the '<em><b>Allow Any Dataflow Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PORT__ALLOW_ANY_DATAFLOW_TYPE = PORT__ALLOW_ANY_DATAFLOW_TYPE;

	/**
	 * The feature id for the '<em><b>Allow Any Subscription Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PORT__ALLOW_ANY_SUBSCRIPTION_TYPE = PORT__ALLOW_ANY_SUBSCRIPTION_TYPE;

	/**
	 * The feature id for the '<em><b>Connector Profiles</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PORT__CONNECTOR_PROFILES = PORT__CONNECTOR_PROFILES;

	/**
	 * The feature id for the '<em><b>Interfaces</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PORT__INTERFACES = PORT__INTERFACES;

	/**
	 * The feature id for the '<em><b>Dataflow Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PORT__DATAFLOW_TYPE = PORT__DATAFLOW_TYPE;

	/**
	 * The feature id for the '<em><b>Subscription Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PORT__SUBSCRIPTION_TYPE = PORT__SUBSCRIPTION_TYPE;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PORT__DATA_TYPE = PORT__DATA_TYPE;

	/**
	 * The feature id for the '<em><b>Interface Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PORT__INTERFACE_TYPE = PORT__INTERFACE_TYPE;

	/**
	 * The number of structural features of the '<em>Service Port</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PORT_FEATURE_COUNT = PORT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.toolscommon.model.component.IPropertyMap <em>IProperty Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.component.IPropertyMap
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getIPropertyMap()
	 * @generated
	 */
	int IPROPERTY_MAP = 15;

	/**
	 * The number of structural features of the '<em>IProperty Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPROPERTY_MAP_FEATURE_COUNT = CorePackage.IADAPTABLE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.EIntegerObjectToPointMapEntryImpl <em>EInteger Object To Point Map Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.EIntegerObjectToPointMapEntryImpl
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getEIntegerObjectToPointMapEntry()
	 * @generated
	 */
	int EINTEGER_OBJECT_TO_POINT_MAP_ENTRY = 14;

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.PortSynchronizerImpl <em>Port Synchronizer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.PortSynchronizerImpl
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getPortSynchronizer()
	 * @generated
	 */
	int PORT_SYNCHRONIZER = 11;

	/**
	 * The feature id for the '<em><b>Original Port String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_SYNCHRONIZER__ORIGINAL_PORT_STRING = IPROPERTY_MAP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Port Synchronizer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_SYNCHRONIZER_FEATURE_COUNT = IPROPERTY_MAP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_CONNECTOR__CONSTRAINT = CorePackage.WRAPPER_OBJECT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Connector Profile</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_CONNECTOR__CONNECTOR_PROFILE = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Routing Constraint</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_CONNECTOR__ROUTING_CONSTRAINT = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_CONNECTOR__SOURCE = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_CONNECTOR__TARGET = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Port Connector</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_CONNECTOR_FEATURE_COUNT = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__CONSTRAINT = CorePackage.WRAPPER_OBJECT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Dataflow Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__DATAFLOW_TYPE = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Subscription Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__SUBSCRIPTION_TYPE = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Subscription Type Available</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__SUBSCRIPTION_TYPE_AVAILABLE = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Push Interval Available</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__PUSH_INTERVAL_AVAILABLE = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__NAME = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Connector Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__CONNECTOR_ID = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__DATA_TYPE = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Interface Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__INTERFACE_TYPE = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Push Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__PUSH_RATE = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Push Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__PUSH_POLICY = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Skip Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__SKIP_COUNT = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Push Policy Available</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__PUSH_POLICY_AVAILABLE = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Skip Count Available</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__SKIP_COUNT_AVAILABLE = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Source String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__SOURCE_STRING = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Target String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__TARGET_STRING = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Outport Buffer Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__OUTPORT_BUFFER_LENGTH = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Outport Buffer Full Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__OUTPORT_BUFFER_FULL_POLICY = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Outport Buffer Write Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__OUTPORT_BUFFER_WRITE_TIMEOUT = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>Outport Buffer Empty Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__OUTPORT_BUFFER_EMPTY_POLICY = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>Outport Buffer Read Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__OUTPORT_BUFFER_READ_TIMEOUT = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 19;

	/**
	 * The feature id for the '<em><b>Inport Buffer Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__INPORT_BUFFER_LENGTH = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 20;

	/**
	 * The feature id for the '<em><b>Inport Buffer Full Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__INPORT_BUFFER_FULL_POLICY = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 21;

	/**
	 * The feature id for the '<em><b>Inport Buffer Write Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__INPORT_BUFFER_WRITE_TIMEOUT = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 22;

	/**
	 * The feature id for the '<em><b>Inport Buffer Empty Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__INPORT_BUFFER_EMPTY_POLICY = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 23;

	/**
	 * The feature id for the '<em><b>Inport Buffer Read Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__INPORT_BUFFER_READ_TIMEOUT = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 24;

	/**
	 * The feature id for the '<em><b>Timestamp Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__TIMESTAMP_POLICY = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 25;

	/**
	 * The feature id for the '<em><b>Is Reverse</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__IS_REVERSE = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 26;

	/**
	 * The feature id for the '<em><b>Outport Serializer Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__OUTPORT_SERIALIZER_TYPE = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 27;

	/**
	 * The feature id for the '<em><b>Inport Serializer Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE__INPORT_SERIALIZER_TYPE = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 28;

	/**
	 * The number of structural features of the '<em>Connector Profile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PROFILE_FEATURE_COUNT = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 29;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EINTEGER_OBJECT_TO_POINT_MAP_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EINTEGER_OBJECT_TO_POINT_MAP_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>EInteger Object To Point Map Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EINTEGER_OBJECT_TO_POINT_MAP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__CONSTRAINT = COMPONENT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Configuration Sets</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__CONFIGURATION_SETS = COMPONENT__CONFIGURATION_SETS;

	/**
	 * The feature id for the '<em><b>Active Configuration Set</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__ACTIVE_CONFIGURATION_SET = COMPONENT__ACTIVE_CONFIGURATION_SET;

	/**
	 * The feature id for the '<em><b>Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__PORTS = COMPONENT__PORTS;

	/**
	 * The feature id for the '<em><b>Inports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__INPORTS = COMPONENT__INPORTS;

	/**
	 * The feature id for the '<em><b>Outports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__OUTPORTS = COMPONENT__OUTPORTS;

	/**
	 * The feature id for the '<em><b>Serviceports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__SERVICEPORTS = COMPONENT__SERVICEPORTS;

	/**
	 * The feature id for the '<em><b>Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__COMPONENTS = COMPONENT__COMPONENTS;

	/**
	 * The feature id for the '<em><b>Primary Execution Context</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__PRIMARY_EXECUTION_CONTEXT = COMPONENT__PRIMARY_EXECUTION_CONTEXT;

	/**
	 * The feature id for the '<em><b>Execution Contexts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__EXECUTION_CONTEXTS = COMPONENT__EXECUTION_CONTEXTS;

	/**
	 * The feature id for the '<em><b>Participation Contexts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__PARTICIPATION_CONTEXTS = COMPONENT__PARTICIPATION_CONTEXTS;

	/**
	 * The feature id for the '<em><b>Execution Context Handler</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__EXECUTION_CONTEXT_HANDLER = COMPONENT__EXECUTION_CONTEXT_HANDLER;

	/**
	 * The feature id for the '<em><b>Participation Context Handler</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__PARTICIPATION_CONTEXT_HANDLER = COMPONENT__PARTICIPATION_CONTEXT_HANDLER;

	/**
	 * The feature id for the '<em><b>Child System Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__CHILD_SYSTEM_DIAGRAM = COMPONENT__CHILD_SYSTEM_DIAGRAM;

	/**
	 * The feature id for the '<em><b>Instance Name L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__INSTANCE_NAME_L = COMPONENT__INSTANCE_NAME_L;

	/**
	 * The feature id for the '<em><b>Vender L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__VENDER_L = COMPONENT__VENDER_L;

	/**
	 * The feature id for the '<em><b>Description L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__DESCRIPTION_L = COMPONENT__DESCRIPTION_L;

	/**
	 * The feature id for the '<em><b>Category L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__CATEGORY_L = COMPONENT__CATEGORY_L;

	/**
	 * The feature id for the '<em><b>Type Name L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__TYPE_NAME_L = COMPONENT__TYPE_NAME_L;

	/**
	 * The feature id for the '<em><b>Version L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__VERSION_L = COMPONENT__VERSION_L;

	/**
	 * The feature id for the '<em><b>Path Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__PATH_ID = COMPONENT__PATH_ID;

	/**
	 * The feature id for the '<em><b>Outport Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__OUTPORT_DIRECTION = COMPONENT__OUTPORT_DIRECTION;

	/**
	 * The feature id for the '<em><b>Composite Type L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__COMPOSITE_TYPE_L = COMPONENT__COMPOSITE_TYPE_L;

	/**
	 * The feature id for the '<em><b>Component Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__COMPONENT_ID = COMPONENT__COMPONENT_ID;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__REQUIRED = COMPONENT__REQUIRED;

	/**
	 * The feature id for the '<em><b>Start Up</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__START_UP = COMPONENT__START_UP;

	/**
	 * The feature id for the '<em><b>Shut Down</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__SHUT_DOWN = COMPONENT__SHUT_DOWN;

	/**
	 * The feature id for the '<em><b>Activation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__ACTIVATION = COMPONENT__ACTIVATION;

	/**
	 * The feature id for the '<em><b>De Activation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__DE_ACTIVATION = COMPONENT__DE_ACTIVATION;

	/**
	 * The feature id for the '<em><b>Resetting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__RESETTING = COMPONENT__RESETTING;

	/**
	 * The feature id for the '<em><b>Initialize</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__INITIALIZE = COMPONENT__INITIALIZE;

	/**
	 * The feature id for the '<em><b>Finalize</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__FINALIZE = COMPONENT__FINALIZE;

	/**
	 * The feature id for the '<em><b>Corba Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__CORBA_OBJECT = COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>RTC Component Profile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__RTC_COMPONENT_PROFILE = COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>RTC Execution Contexts</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__RTC_EXECUTION_CONTEXTS = COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>RTC Participation Contexts</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__RTC_PARTICIPATION_CONTEXTS = COMPONENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>SDO Configuration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__SDO_CONFIGURATION = COMPONENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>SDO Organization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__SDO_ORGANIZATION = COMPONENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>RTCRT Objects</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__RTCRT_OBJECTS = COMPONENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Component State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__COMPONENT_STATE = COMPONENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Ior</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__IOR = COMPONENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Status Observer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__STATUS_OBSERVER = COMPONENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Log Observer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT__LOG_OBSERVER = COMPONENT_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Corba Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_COMPONENT_FEATURE_COUNT = COMPONENT_FEATURE_COUNT + 11;

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaPortSynchronizerImpl <em>Corba Port Synchronizer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.CorbaPortSynchronizerImpl
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getCorbaPortSynchronizer()
	 * @generated
	 */
	int CORBA_PORT_SYNCHRONIZER = 17;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_PORT_SYNCHRONIZER__CONSTRAINT = CorePackage.CORBA_WRAPPER_OBJECT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Corba Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_PORT_SYNCHRONIZER__CORBA_OBJECT = CorePackage.CORBA_WRAPPER_OBJECT__CORBA_OBJECT;

	/**
	 * The feature id for the '<em><b>Original Port String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_PORT_SYNCHRONIZER__ORIGINAL_PORT_STRING = CorePackage.CORBA_WRAPPER_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>RTC Port Profile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_PORT_SYNCHRONIZER__RTC_PORT_PROFILE = CorePackage.CORBA_WRAPPER_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Corba Port Synchronizer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_PORT_SYNCHRONIZER_FEATURE_COUNT = CorePackage.CORBA_WRAPPER_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaConnectorProfileImpl <em>Corba Connector Profile</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.CorbaConnectorProfileImpl
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getCorbaConnectorProfile()
	 * @generated
	 */
	int CORBA_CONNECTOR_PROFILE = 18;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__CONSTRAINT = CONNECTOR_PROFILE__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Dataflow Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__DATAFLOW_TYPE = CONNECTOR_PROFILE__DATAFLOW_TYPE;

	/**
	 * The feature id for the '<em><b>Subscription Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__SUBSCRIPTION_TYPE = CONNECTOR_PROFILE__SUBSCRIPTION_TYPE;

	/**
	 * The feature id for the '<em><b>Subscription Type Available</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__SUBSCRIPTION_TYPE_AVAILABLE = CONNECTOR_PROFILE__SUBSCRIPTION_TYPE_AVAILABLE;

	/**
	 * The feature id for the '<em><b>Push Interval Available</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__PUSH_INTERVAL_AVAILABLE = CONNECTOR_PROFILE__PUSH_INTERVAL_AVAILABLE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__NAME = CONNECTOR_PROFILE__NAME;

	/**
	 * The feature id for the '<em><b>Connector Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__CONNECTOR_ID = CONNECTOR_PROFILE__CONNECTOR_ID;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__DATA_TYPE = CONNECTOR_PROFILE__DATA_TYPE;

	/**
	 * The feature id for the '<em><b>Interface Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__INTERFACE_TYPE = CONNECTOR_PROFILE__INTERFACE_TYPE;

	/**
	 * The feature id for the '<em><b>Push Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__PUSH_RATE = CONNECTOR_PROFILE__PUSH_RATE;

	/**
	 * The feature id for the '<em><b>Push Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__PUSH_POLICY = CONNECTOR_PROFILE__PUSH_POLICY;

	/**
	 * The feature id for the '<em><b>Skip Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__SKIP_COUNT = CONNECTOR_PROFILE__SKIP_COUNT;

	/**
	 * The feature id for the '<em><b>Push Policy Available</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__PUSH_POLICY_AVAILABLE = CONNECTOR_PROFILE__PUSH_POLICY_AVAILABLE;

	/**
	 * The feature id for the '<em><b>Skip Count Available</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__SKIP_COUNT_AVAILABLE = CONNECTOR_PROFILE__SKIP_COUNT_AVAILABLE;

	/**
	 * The feature id for the '<em><b>Source String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__SOURCE_STRING = CONNECTOR_PROFILE__SOURCE_STRING;

	/**
	 * The feature id for the '<em><b>Target String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__TARGET_STRING = CONNECTOR_PROFILE__TARGET_STRING;

	/**
	 * The feature id for the '<em><b>Outport Buffer Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__OUTPORT_BUFFER_LENGTH = CONNECTOR_PROFILE__OUTPORT_BUFFER_LENGTH;

	/**
	 * The feature id for the '<em><b>Outport Buffer Full Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__OUTPORT_BUFFER_FULL_POLICY = CONNECTOR_PROFILE__OUTPORT_BUFFER_FULL_POLICY;

	/**
	 * The feature id for the '<em><b>Outport Buffer Write Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__OUTPORT_BUFFER_WRITE_TIMEOUT = CONNECTOR_PROFILE__OUTPORT_BUFFER_WRITE_TIMEOUT;

	/**
	 * The feature id for the '<em><b>Outport Buffer Empty Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__OUTPORT_BUFFER_EMPTY_POLICY = CONNECTOR_PROFILE__OUTPORT_BUFFER_EMPTY_POLICY;

	/**
	 * The feature id for the '<em><b>Outport Buffer Read Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__OUTPORT_BUFFER_READ_TIMEOUT = CONNECTOR_PROFILE__OUTPORT_BUFFER_READ_TIMEOUT;

	/**
	 * The feature id for the '<em><b>Inport Buffer Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__INPORT_BUFFER_LENGTH = CONNECTOR_PROFILE__INPORT_BUFFER_LENGTH;

	/**
	 * The feature id for the '<em><b>Inport Buffer Full Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__INPORT_BUFFER_FULL_POLICY = CONNECTOR_PROFILE__INPORT_BUFFER_FULL_POLICY;

	/**
	 * The feature id for the '<em><b>Inport Buffer Write Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__INPORT_BUFFER_WRITE_TIMEOUT = CONNECTOR_PROFILE__INPORT_BUFFER_WRITE_TIMEOUT;

	/**
	 * The feature id for the '<em><b>Inport Buffer Empty Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__INPORT_BUFFER_EMPTY_POLICY = CONNECTOR_PROFILE__INPORT_BUFFER_EMPTY_POLICY;

	/**
	 * The feature id for the '<em><b>Inport Buffer Read Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__INPORT_BUFFER_READ_TIMEOUT = CONNECTOR_PROFILE__INPORT_BUFFER_READ_TIMEOUT;

	/**
	 * The feature id for the '<em><b>Timestamp Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__TIMESTAMP_POLICY = CONNECTOR_PROFILE__TIMESTAMP_POLICY;

	/**
	 * The feature id for the '<em><b>Is Reverse</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__IS_REVERSE = CONNECTOR_PROFILE__IS_REVERSE;

	/**
	 * The feature id for the '<em><b>Outport Serializer Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__OUTPORT_SERIALIZER_TYPE = CONNECTOR_PROFILE__OUTPORT_SERIALIZER_TYPE;

	/**
	 * The feature id for the '<em><b>Inport Serializer Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__INPORT_SERIALIZER_TYPE = CONNECTOR_PROFILE__INPORT_SERIALIZER_TYPE;

	/**
	 * The feature id for the '<em><b>Rtc Connector Profile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE__RTC_CONNECTOR_PROFILE = CONNECTOR_PROFILE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Corba Connector Profile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONNECTOR_PROFILE_FEATURE_COUNT = CONNECTOR_PROFILE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaConfigurationSetImpl <em>Corba Configuration Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.CorbaConfigurationSetImpl
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getCorbaConfigurationSet()
	 * @generated
	 */
	int CORBA_CONFIGURATION_SET = 19;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONFIGURATION_SET__CONSTRAINT = CONFIGURATION_SET__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONFIGURATION_SET__ID = CONFIGURATION_SET__ID;

	/**
	 * The feature id for the '<em><b>Configuration Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONFIGURATION_SET__CONFIGURATION_DATA = CONFIGURATION_SET__CONFIGURATION_DATA;

	/**
	 * The feature id for the '<em><b>SDO Configuration Set</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONFIGURATION_SET__SDO_CONFIGURATION_SET = CONFIGURATION_SET_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Corba Configuration Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONFIGURATION_SET_FEATURE_COUNT = CONFIGURATION_SET_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaExecutionContextImpl <em>Corba Execution Context</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.CorbaExecutionContextImpl
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getCorbaExecutionContext()
	 * @generated
	 */
	int CORBA_EXECUTION_CONTEXT = 20;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_EXECUTION_CONTEXT__CONSTRAINT = EXECUTION_CONTEXT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Kind L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_EXECUTION_CONTEXT__KIND_L = EXECUTION_CONTEXT__KIND_L;

	/**
	 * The feature id for the '<em><b>Rate L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_EXECUTION_CONTEXT__RATE_L = EXECUTION_CONTEXT__RATE_L;

	/**
	 * The feature id for the '<em><b>State L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_EXECUTION_CONTEXT__STATE_L = EXECUTION_CONTEXT__STATE_L;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_EXECUTION_CONTEXT__OWNER = EXECUTION_CONTEXT__OWNER;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_EXECUTION_CONTEXT__PARTICIPANTS = EXECUTION_CONTEXT__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Corba Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_EXECUTION_CONTEXT__CORBA_OBJECT = EXECUTION_CONTEXT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Rtc Execution Context Profile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_EXECUTION_CONTEXT__RTC_EXECUTION_CONTEXT_PROFILE = EXECUTION_CONTEXT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Corba Execution Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_EXECUTION_CONTEXT_FEATURE_COUNT = EXECUTION_CONTEXT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaContextHandlerImpl <em>Corba Context Handler</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.CorbaContextHandlerImpl
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getCorbaContextHandler()
	 * @generated
	 */
	int CORBA_CONTEXT_HANDLER = 21;

	/**
	 * The number of structural features of the '<em>Corba Context Handler</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_CONTEXT_HANDLER_FEATURE_COUNT = CONTEXT_HANDLER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaObserverImpl <em>Corba Observer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.CorbaObserverImpl
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getCorbaObserver()
	 * @generated
	 */
	int CORBA_OBSERVER = 22;

	/**
	 * The feature id for the '<em><b>Service Profile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_OBSERVER__SERVICE_PROFILE = IPROPERTY_MAP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Servant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_OBSERVER__SERVANT = IPROPERTY_MAP_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Corba Observer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_OBSERVER_FEATURE_COUNT = IPROPERTY_MAP_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaStatusObserverImpl <em>Corba Status Observer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.CorbaStatusObserverImpl
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getCorbaStatusObserver()
	 * @generated
	 */
	int CORBA_STATUS_OBSERVER = 23;

	/**
	 * The feature id for the '<em><b>Service Profile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_STATUS_OBSERVER__SERVICE_PROFILE = CORBA_OBSERVER__SERVICE_PROFILE;

	/**
	 * The feature id for the '<em><b>Servant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_STATUS_OBSERVER__SERVANT = CORBA_OBSERVER__SERVANT;

	/**
	 * The number of structural features of the '<em>Corba Status Observer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_STATUS_OBSERVER_FEATURE_COUNT = CORBA_OBSERVER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaLogObserverImpl <em>Corba Log Observer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.CorbaLogObserverImpl
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getCorbaLogObserver()
	 * @generated
	 */
	int CORBA_LOG_OBSERVER = 24;

	/**
	 * The feature id for the '<em><b>Service Profile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_LOG_OBSERVER__SERVICE_PROFILE = CORBA_OBSERVER__SERVICE_PROFILE;

	/**
	 * The feature id for the '<em><b>Servant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_LOG_OBSERVER__SERVANT = CORBA_OBSERVER__SERVANT;

	/**
	 * The number of structural features of the '<em>Corba Log Observer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_LOG_OBSERVER_FEATURE_COUNT = CORBA_OBSERVER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind <em>System Diagram Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getSystemDiagramKind()
	 * @generated
	 */
	int SYSTEM_DIAGRAM_KIND = 25;

	/**
	 * The meta object id for the '<em>RTC Component Profile</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see RTC.ComponentProfile
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getRTCComponentProfile()
	 * @generated
	 */
	int RTC_COMPONENT_PROFILE = 31;

	/**
	 * The meta object id for the '<em>RTCRT Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see RTC.RTObject
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getRTCRTObject()
	 * @generated
	 */
	int RTCRT_OBJECT = 30;

	/**
	 * The meta object id for the '<em>List</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.List
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getList()
	 * @generated
	 */
	int LIST = 38;

	/**
	 * The meta object id for the '<em>Servant</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.PortableServer.Servant
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getServant()
	 * @generated
	 */
	int SERVANT = 39;

	/**
	 * The meta object id for the '<em>ICorba Port Event Observer</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.component.util.ICorbaPortEventObserver
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getICorbaPortEventObserver()
	 * @generated
	 */
	int ICORBA_PORT_EVENT_OBSERVER = 40;

	/**
	 * The meta object id for the '<em>SDO Configuration</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see _SDOPackage.Configuration
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getSDOConfiguration()
	 * @generated
	 */
	int SDO_CONFIGURATION = 26;

	/**
	 * The meta object id for the '<em>SDO Configuration Set</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see _SDOPackage.ConfigurationSet
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getSDOConfigurationSet()
	 * @generated
	 */
	int SDO_CONFIGURATION_SET = 27;

	/**
	 * The meta object id for the '<em>RTC Connector Profile</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see RTC.ConnectorProfile
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getRTCConnectorProfile()
	 * @generated
	 */
	int RTC_CONNECTOR_PROFILE = 32;

	/**
	 * The meta object id for the '<em>RTC Port Profile</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see RTC.PortProfile
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getRTCPortProfile()
	 * @generated
	 */
	int RTC_PORT_PROFILE = 33;

	/**
	 * The meta object id for the '<em>RTC Execution Context</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see RTC.ExecutionContext
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getRTCExecutionContext()
	 * @generated
	 */
	int RTC_EXECUTION_CONTEXT = 34;

	/**
	 * The meta object id for the '<em>Property Change Listener</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.beans.PropertyChangeListener
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getPropertyChangeListener()
	 * @generated
	 */
	int PROPERTY_CHANGE_LISTENER = 36;

	/**
	 * The meta object id for the '<em>SDO Organization</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see _SDOPackage.Organization
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getSDOOrganization()
	 * @generated
	 */
	int SDO_ORGANIZATION = 28;


	/**
	 * The meta object id for the '<em>SDO Service Profile</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see _SDOPackage.ServiceProfile
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getSDOServiceProfile()
	 * @generated
	 */
	int SDO_SERVICE_PROFILE = 29;

	/**
	 * The meta object id for the '<em>Port Interface Profile</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.component.PortInterfaceProfile
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getPortInterfaceProfile()
	 * @generated
	 */
	int PORT_INTERFACE_PROFILE = 37;


	/**
	 * The meta object id for the '<em>RTC Execution Context Profile</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see RTC.ExecutionContextProfile
	 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getRTCExecutionContextProfile()
	 * @generated
	 */
	int RTC_EXECUTION_CONTEXT_PROFILE = 35;


	/**
	 * Returns the meta object for class '{@link jp.go.aist.rtm.toolscommon.model.component.SystemDiagram <em>System Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System Diagram</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.SystemDiagram
	 * @generated
	 */
	EClass getSystemDiagram();

	/**
	 * Returns the meta object for the containment reference list '{@link jp.go.aist.rtm.toolscommon.model.component.SystemDiagram#getComponents <em>Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Components</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.SystemDiagram#getComponents()
	 * @see #getSystemDiagram()
	 * @generated
	 */
	EReference getSystemDiagram_Components();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.SystemDiagram#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.SystemDiagram#getKind()
	 * @see #getSystemDiagram()
	 * @generated
	 */
	EAttribute getSystemDiagram_Kind();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.SystemDiagram#isConnectorProcessing <em>Connector Processing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connector Processing</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.SystemDiagram#isConnectorProcessing()
	 * @see #getSystemDiagram()
	 * @generated
	 */
	EAttribute getSystemDiagram_ConnectorProcessing();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.SystemDiagram#getSystemId <em>System Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>System Id</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.SystemDiagram#getSystemId()
	 * @see #getSystemDiagram()
	 * @generated
	 */
	EAttribute getSystemDiagram_SystemId();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.SystemDiagram#getCreationDate <em>Creation Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Creation Date</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.SystemDiagram#getCreationDate()
	 * @see #getSystemDiagram()
	 * @generated
	 */
	EAttribute getSystemDiagram_CreationDate();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.SystemDiagram#getUpdateDate <em>Update Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Update Date</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.SystemDiagram#getUpdateDate()
	 * @see #getSystemDiagram()
	 * @generated
	 */
	EAttribute getSystemDiagram_UpdateDate();

	/**
	 * Returns the meta object for the reference '{@link jp.go.aist.rtm.toolscommon.model.component.SystemDiagram#getParentSystemDiagram <em>Parent System Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent System Diagram</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.SystemDiagram#getParentSystemDiagram()
	 * @see #getSystemDiagram()
	 * @generated
	 */
	EReference getSystemDiagram_ParentSystemDiagram();

	/**
	 * Returns the meta object for the reference '{@link jp.go.aist.rtm.toolscommon.model.component.SystemDiagram#getCompositeComponent <em>Composite Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Composite Component</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.SystemDiagram#getCompositeComponent()
	 * @see #getSystemDiagram()
	 * @generated
	 */
	EReference getSystemDiagram_CompositeComponent();

	/**
	 * Returns the meta object for class '{@link jp.go.aist.rtm.toolscommon.model.component.Component <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component
	 * @generated
	 */
	EClass getComponent();

	/**
	 * Returns the meta object for the containment reference list '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getConfigurationSets <em>Configuration Sets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Configuration Sets</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getConfigurationSets()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_ConfigurationSets();

	/**
	 * Returns the meta object for the reference '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getActiveConfigurationSet <em>Active Configuration Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Active Configuration Set</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getActiveConfigurationSet()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_ActiveConfigurationSet();

	/**
	 * Returns the meta object for the containment reference list '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getPorts <em>Ports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ports</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getPorts()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Ports();

	/**
	 * Returns the meta object for the reference list '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getInports <em>Inports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Inports</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getInports()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Inports();

	/**
	 * Returns the meta object for the reference list '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getOutports <em>Outports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Outports</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getOutports()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Outports();

	/**
	 * Returns the meta object for the reference list '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getServiceports <em>Serviceports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Serviceports</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getServiceports()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Serviceports();

	/**
	 * Returns the meta object for the containment reference list '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getExecutionContexts <em>Execution Contexts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Execution Contexts</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getExecutionContexts()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_ExecutionContexts();

	/**
	 * Returns the meta object for the containment reference list '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getParticipationContexts <em>Participation Contexts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Participation Contexts</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getParticipationContexts()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_ParticipationContexts();

	/**
	 * Returns the meta object for the containment reference '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getExecutionContextHandler <em>Execution Context Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Execution Context Handler</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getExecutionContextHandler()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_ExecutionContextHandler();

	/**
	 * Returns the meta object for the containment reference '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getParticipationContextHandler <em>Participation Context Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Participation Context Handler</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getParticipationContextHandler()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_ParticipationContextHandler();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getInstanceNameL <em>Instance Name L</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Instance Name L</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getInstanceNameL()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_InstanceNameL();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getVenderL <em>Vender L</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vender L</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getVenderL()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_VenderL();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getDescriptionL <em>Description L</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description L</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getDescriptionL()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_DescriptionL();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getCategoryL <em>Category L</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Category L</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getCategoryL()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_CategoryL();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getTypeNameL <em>Type Name L</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Name L</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getTypeNameL()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_TypeNameL();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getVersionL <em>Version L</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version L</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getVersionL()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_VersionL();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getPathId <em>Path Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path Id</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getPathId()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_PathId();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getOutportDirection <em>Outport Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Outport Direction</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getOutportDirection()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_OutportDirection();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getCompositeTypeL <em>Composite Type L</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Composite Type L</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getCompositeTypeL()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_CompositeTypeL();

	/**
	 * Returns the meta object for the reference list '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getComponents <em>Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Components</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getComponents()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Components();

	/**
	 * Returns the meta object for the containment reference '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getPrimaryExecutionContext <em>Primary Execution Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Primary Execution Context</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getPrimaryExecutionContext()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_PrimaryExecutionContext();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getComponentId <em>Component Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Component Id</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getComponentId()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_ComponentId();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.Component#isRequired <em>Required</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Required</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#isRequired()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Required();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getStartUp <em>Start Up</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Up</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getStartUp()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_StartUp();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getShutDown <em>Shut Down</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Shut Down</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getShutDown()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_ShutDown();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getActivation <em>Activation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activation</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getActivation()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Activation();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getDeActivation <em>De Activation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>De Activation</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getDeActivation()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_DeActivation();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getResetting <em>Resetting</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resetting</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getResetting()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Resetting();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getInitialize <em>Initialize</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Initialize</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getInitialize()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Initialize();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getFinalize <em>Finalize</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Finalize</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getFinalize()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Finalize();

	/**
	 * Returns the meta object for the reference '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getChildSystemDiagram <em>Child System Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Child System Diagram</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component#getChildSystemDiagram()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_ChildSystemDiagram();

	/**
	 * Returns the meta object for class '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaComponent <em>Corba Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Corba Component</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaComponent
	 * @generated
	 */
	EClass getCorbaComponent();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getSDOConfiguration <em>SDO Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>SDO Configuration</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getSDOConfiguration()
	 * @see #getCorbaComponent()
	 * @generated
	 */
	EAttribute getCorbaComponent_SDOConfiguration();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getRTCComponentProfile <em>RTC Component Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>RTC Component Profile</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getRTCComponentProfile()
	 * @see #getCorbaComponent()
	 * @generated
	 */
	EAttribute getCorbaComponent_RTCComponentProfile();

	/**
	 * Returns the meta object for the attribute list '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getRTCExecutionContexts <em>RTC Execution Contexts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>RTC Execution Contexts</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getRTCExecutionContexts()
	 * @see #getCorbaComponent()
	 * @generated
	 */
	EAttribute getCorbaComponent_RTCExecutionContexts();

	/**
	 * Returns the meta object for the attribute list '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getRTCParticipationContexts <em>RTC Participation Contexts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>RTC Participation Contexts</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getRTCParticipationContexts()
	 * @see #getCorbaComponent()
	 * @generated
	 */
	EAttribute getCorbaComponent_RTCParticipationContexts();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getSDOOrganization <em>SDO Organization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>SDO Organization</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getSDOOrganization()
	 * @see #getCorbaComponent()
	 * @generated
	 */
	EAttribute getCorbaComponent_SDOOrganization();

	/**
	 * Returns the meta object for the attribute list '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getRTCRTObjects <em>RTCRT Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>RTCRT Objects</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getRTCRTObjects()
	 * @see #getCorbaComponent()
	 * @generated
	 */
	EAttribute getCorbaComponent_RTCRTObjects();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getComponentState <em>Component State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Component State</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getComponentState()
	 * @see #getCorbaComponent()
	 * @generated
	 */
	EAttribute getCorbaComponent_ComponentState();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getIor <em>Ior</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ior</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getIor()
	 * @see #getCorbaComponent()
	 * @generated
	 */
	EAttribute getCorbaComponent_Ior();

	/**
	 * Returns the meta object for the reference '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getStatusObserver <em>Status Observer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Status Observer</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getStatusObserver()
	 * @see #getCorbaComponent()
	 * @generated
	 */
	EReference getCorbaComponent_StatusObserver();

	/**
	 * Returns the meta object for the reference '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getLogObserver <em>Log Observer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Log Observer</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getLogObserver()
	 * @see #getCorbaComponent()
	 * @generated
	 */
	EReference getCorbaComponent_LogObserver();

	/**
	 * Returns the meta object for class '{@link jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification <em>Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Specification</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification
	 * @generated
	 */
	EClass getComponentSpecification();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification#getAliasName <em>Alias Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Alias Name</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification#getAliasName()
	 * @see #getComponentSpecification()
	 * @generated
	 */
	EAttribute getComponentSpecification_AliasName();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification#isSpecUnLoad <em>Spec Un Load</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Spec Un Load</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification#isSpecUnLoad()
	 * @see #getComponentSpecification()
	 * @generated
	 */
	EAttribute getComponentSpecification_SpecUnLoad();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification#getRtcType <em>Rtc Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rtc Type</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification#getRtcType()
	 * @see #getComponentSpecification()
	 * @generated
	 */
	EAttribute getComponentSpecification_RtcType();

	/**
	 * Returns the meta object for class '{@link jp.go.aist.rtm.toolscommon.model.component.PortConnector <em>Port Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Connector</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.PortConnector
	 * @generated
	 */
	EClass getPortConnector();

	/**
	 * Returns the meta object for the containment reference '{@link jp.go.aist.rtm.toolscommon.model.component.PortConnector#getConnectorProfile <em>Connector Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Connector Profile</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.PortConnector#getConnectorProfile()
	 * @see #getPortConnector()
	 * @generated
	 */
	EReference getPortConnector_ConnectorProfile();

	/**
	 * Returns the meta object for the map '{@link jp.go.aist.rtm.toolscommon.model.component.PortConnector#getRoutingConstraint <em>Routing Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Routing Constraint</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.PortConnector#getRoutingConstraint()
	 * @see #getPortConnector()
	 * @generated
	 */
	EReference getPortConnector_RoutingConstraint();

	/**
	 * Returns the meta object for the reference '{@link jp.go.aist.rtm.toolscommon.model.component.PortConnector#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.PortConnector#getSource()
	 * @see #getPortConnector()
	 * @generated
	 */
	EReference getPortConnector_Source();

	/**
	 * Returns the meta object for the reference '{@link jp.go.aist.rtm.toolscommon.model.component.PortConnector#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.PortConnector#getTarget()
	 * @see #getPortConnector()
	 * @generated
	 */
	EReference getPortConnector_Target();

	/**
	 * Returns the meta object for class '{@link jp.go.aist.rtm.toolscommon.model.component.ExecutionContext <em>Execution Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Execution Context</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ExecutionContext
	 * @generated
	 */
	EClass getExecutionContext();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ExecutionContext#getKindL <em>Kind L</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind L</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ExecutionContext#getKindL()
	 * @see #getExecutionContext()
	 * @generated
	 */
	EAttribute getExecutionContext_KindL();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ExecutionContext#getRateL <em>Rate L</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rate L</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ExecutionContext#getRateL()
	 * @see #getExecutionContext()
	 * @generated
	 */
	EAttribute getExecutionContext_RateL();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ExecutionContext#getStateL <em>State L</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>State L</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ExecutionContext#getStateL()
	 * @see #getExecutionContext()
	 * @generated
	 */
	EAttribute getExecutionContext_StateL();

	/**
	 * Returns the meta object for the reference '{@link jp.go.aist.rtm.toolscommon.model.component.ExecutionContext#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Owner</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ExecutionContext#getOwner()
	 * @see #getExecutionContext()
	 * @generated
	 */
	EReference getExecutionContext_Owner();

	/**
	 * Returns the meta object for the reference list '{@link jp.go.aist.rtm.toolscommon.model.component.ExecutionContext#getParticipants <em>Participants</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Participants</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ExecutionContext#getParticipants()
	 * @see #getExecutionContext()
	 * @generated
	 */
	EReference getExecutionContext_Participants();

	/**
	 * Returns the meta object for class '{@link jp.go.aist.rtm.toolscommon.model.component.ContextHandler <em>Context Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Context Handler</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ContextHandler
	 * @generated
	 */
	EClass getContextHandler();

	/**
	 * Returns the meta object for class '{@link jp.go.aist.rtm.toolscommon.model.component.InPort <em>In Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>In Port</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.InPort
	 * @generated
	 */
	EClass getInPort();

	/**
	 * Returns the meta object for class '{@link jp.go.aist.rtm.toolscommon.model.component.NameValue <em>Name Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Name Value</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.NameValue
	 * @generated
	 */
	EClass getNameValue();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.NameValue#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.NameValue#getName()
	 * @see #getNameValue()
	 * @generated
	 */
	EAttribute getNameValue_Name();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.NameValue#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.NameValue#getValue()
	 * @see #getNameValue()
	 * @generated
	 */
	EAttribute getNameValue_Value();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.NameValue#getTypeName <em>Type Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Name</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.NameValue#getTypeName()
	 * @see #getNameValue()
	 * @generated
	 */
	EAttribute getNameValue_TypeName();

	/**
	 * Returns the meta object for class '{@link jp.go.aist.rtm.toolscommon.model.component.OutPort <em>Out Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Out Port</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.OutPort
	 * @generated
	 */
	EClass getOutPort();

	/**
	 * Returns the meta object for class '{@link jp.go.aist.rtm.toolscommon.model.component.Port <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Port
	 * @generated
	 */
	EClass getPort();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.Port#getOriginalPortString <em>Original Port String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Original Port String</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Port#getOriginalPortString()
	 * @see #getPort()
	 * @generated
	 */
	EAttribute getPort_OriginalPortString();

	/**
	 * Returns the meta object for the containment reference '{@link jp.go.aist.rtm.toolscommon.model.component.Port#getSynchronizer <em>Synchronizer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Synchronizer</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Port#getSynchronizer()
	 * @see #getPort()
	 * @generated
	 */
	EReference getPort_Synchronizer();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.Port#getNameL <em>Name L</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name L</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Port#getNameL()
	 * @see #getPort()
	 * @generated
	 */
	EAttribute getPort_NameL();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.Port#isAllowAnyDataType <em>Allow Any Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Allow Any Data Type</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Port#isAllowAnyDataType()
	 * @see #getPort()
	 * @generated
	 */
	EAttribute getPort_AllowAnyDataType();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.Port#isAllowAnyInterfaceType <em>Allow Any Interface Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Allow Any Interface Type</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Port#isAllowAnyInterfaceType()
	 * @see #getPort()
	 * @generated
	 */
	EAttribute getPort_AllowAnyInterfaceType();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.Port#isAllowAnyDataflowType <em>Allow Any Dataflow Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Allow Any Dataflow Type</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Port#isAllowAnyDataflowType()
	 * @see #getPort()
	 * @generated
	 */
	EAttribute getPort_AllowAnyDataflowType();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.Port#isAllowAnySubscriptionType <em>Allow Any Subscription Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Allow Any Subscription Type</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Port#isAllowAnySubscriptionType()
	 * @see #getPort()
	 * @generated
	 */
	EAttribute getPort_AllowAnySubscriptionType();

	/**
	 * Returns the meta object for the reference list '{@link jp.go.aist.rtm.toolscommon.model.component.Port#getConnectorProfiles <em>Connector Profiles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Connector Profiles</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Port#getConnectorProfiles()
	 * @see #getPort()
	 * @generated
	 */
	EReference getPort_ConnectorProfiles();

	/**
	 * Returns the meta object for the attribute list '{@link jp.go.aist.rtm.toolscommon.model.component.Port#getInterfaces <em>Interfaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Interfaces</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Port#getInterfaces()
	 * @see #getPort()
	 * @generated
	 */
	EAttribute getPort_Interfaces();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.Port#getDataflowType <em>Dataflow Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dataflow Type</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Port#getDataflowType()
	 * @see #getPort()
	 * @generated
	 */
	EAttribute getPort_DataflowType();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.Port#getSubscriptionType <em>Subscription Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Subscription Type</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Port#getSubscriptionType()
	 * @see #getPort()
	 * @generated
	 */
	EAttribute getPort_SubscriptionType();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.Port#getDataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Type</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Port#getDataType()
	 * @see #getPort()
	 * @generated
	 */
	EAttribute getPort_DataType();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.Port#getInterfaceType <em>Interface Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interface Type</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Port#getInterfaceType()
	 * @see #getPort()
	 * @generated
	 */
	EAttribute getPort_InterfaceType();

	/**
	 * Returns the meta object for class '{@link jp.go.aist.rtm.toolscommon.model.component.ServicePort <em>Service Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service Port</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ServicePort
	 * @generated
	 */
	EClass getServicePort();

	/**
	 * Returns the meta object for class '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile <em>Connector Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connector Profile</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile
	 * @generated
	 */
	EClass getConnectorProfile();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getDataflowType <em>Dataflow Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dataflow Type</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getDataflowType()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_DataflowType();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getSubscriptionType <em>Subscription Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Subscription Type</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getSubscriptionType()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_SubscriptionType();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#isSubscriptionTypeAvailable <em>Subscription Type Available</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Subscription Type Available</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#isSubscriptionTypeAvailable()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_SubscriptionTypeAvailable();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#isPushIntervalAvailable <em>Push Interval Available</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Push Interval Available</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#isPushIntervalAvailable()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_PushIntervalAvailable();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getName()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_Name();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getConnectorId <em>Connector Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connector Id</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getConnectorId()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_ConnectorId();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getDataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Type</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getDataType()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_DataType();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getInterfaceType <em>Interface Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interface Type</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getInterfaceType()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_InterfaceType();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getPushRate <em>Push Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Push Rate</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getPushRate()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_PushRate();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getPushPolicy <em>Push Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Push Policy</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getPushPolicy()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_PushPolicy();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getSkipCount <em>Skip Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Skip Count</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getSkipCount()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_SkipCount();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#isPushPolicyAvailable <em>Push Policy Available</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Push Policy Available</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#isPushPolicyAvailable()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_PushPolicyAvailable();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#isSkipCountAvailable <em>Skip Count Available</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Skip Count Available</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#isSkipCountAvailable()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_SkipCountAvailable();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getSourceString <em>Source String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source String</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getSourceString()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_SourceString();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getTargetString <em>Target String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target String</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getTargetString()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_TargetString();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getOutportSerializerType <em>Outport Serializer Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Outport Serializer Type</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getOutportSerializerType()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_OutportSerializerType();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getOutportBufferLength <em>Outport Buffer Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Outport Buffer Length</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getOutportBufferLength()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_OutportBufferLength();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getOutportBufferFullPolicy <em>Outport Buffer Full Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Outport Buffer Full Policy</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getOutportBufferFullPolicy()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_OutportBufferFullPolicy();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getOutportBufferWriteTimeout <em>Outport Buffer Write Timeout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Outport Buffer Write Timeout</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getOutportBufferWriteTimeout()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_OutportBufferWriteTimeout();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getOutportBufferEmptyPolicy <em>Outport Buffer Empty Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Outport Buffer Empty Policy</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getOutportBufferEmptyPolicy()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_OutportBufferEmptyPolicy();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getOutportBufferReadTimeout <em>Outport Buffer Read Timeout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Outport Buffer Read Timeout</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getOutportBufferReadTimeout()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_OutportBufferReadTimeout();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getInportBufferLength <em>Inport Buffer Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inport Buffer Length</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getInportBufferLength()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_InportBufferLength();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getInportBufferFullPolicy <em>Inport Buffer Full Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inport Buffer Full Policy</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getInportBufferFullPolicy()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_InportBufferFullPolicy();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getInportBufferWriteTimeout <em>Inport Buffer Write Timeout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inport Buffer Write Timeout</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getInportBufferWriteTimeout()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_InportBufferWriteTimeout();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getInportBufferEmptyPolicy <em>Inport Buffer Empty Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inport Buffer Empty Policy</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getInportBufferEmptyPolicy()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_InportBufferEmptyPolicy();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getInportBufferReadTimeout <em>Inport Buffer Read Timeout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inport Buffer Read Timeout</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getInportBufferReadTimeout()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_InportBufferReadTimeout();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getInportSerializerType <em>Inport Serializer Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inport Serializer Type</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getInportSerializerType()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_InportSerializerType();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getTimestampPolicy <em>Timestamp Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timestamp Policy</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getTimestampPolicy()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_TimestampPolicy();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#isIsReverse <em>Is Reverse</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Reverse</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#isIsReverse()
	 * @see #getConnectorProfile()
	 * @generated
	 */
	EAttribute getConnectorProfile_IsReverse();

	/**
	 * Returns the meta object for class '{@link jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet <em>Configuration Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Configuration Set</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet
	 * @generated
	 */
	EClass getConfigurationSet();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet#getId()
	 * @see #getConfigurationSet()
	 * @generated
	 */
	EAttribute getConfigurationSet_Id();

	/**
	 * Returns the meta object for the containment reference list '{@link jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet#getConfigurationData <em>Configuration Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Configuration Data</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet#getConfigurationData()
	 * @see #getConfigurationSet()
	 * @generated
	 */
	EReference getConfigurationSet_ConfigurationData();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>EInteger Object To Point Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EInteger Object To Point Map Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EIntegerObject"
	 *        valueDataType="jp.go.aist.rtm.toolscommon.model.core.Point"
	 * @generated
	 */
	EClass getEIntegerObjectToPointMapEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getEIntegerObjectToPointMapEntry()
	 * @generated
	 */
	EAttribute getEIntegerObjectToPointMapEntry_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getEIntegerObjectToPointMapEntry()
	 * @generated
	 */
	EAttribute getEIntegerObjectToPointMapEntry_Value();

	/**
	 * Returns the meta object for class '{@link jp.go.aist.rtm.toolscommon.model.component.IPropertyMap <em>IProperty Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IProperty Map</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.IPropertyMap
	 * @generated
	 */
	EClass getIPropertyMap();

	/**
	 * Returns the meta object for class '{@link jp.go.aist.rtm.toolscommon.model.component.PortSynchronizer <em>Port Synchronizer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Synchronizer</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.PortSynchronizer
	 * @generated
	 */
	EClass getPortSynchronizer();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.PortSynchronizer#getOriginalPortString <em>Original Port String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Original Port String</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.PortSynchronizer#getOriginalPortString()
	 * @see #getPortSynchronizer()
	 * @generated
	 */
	EAttribute getPortSynchronizer_OriginalPortString();

	/**
	 * Returns the meta object for class '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaPortSynchronizer <em>Corba Port Synchronizer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Corba Port Synchronizer</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaPortSynchronizer
	 * @generated
	 */
	EClass getCorbaPortSynchronizer();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaPortSynchronizer#getRTCPortProfile <em>RTC Port Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>RTC Port Profile</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaPortSynchronizer#getRTCPortProfile()
	 * @see #getCorbaPortSynchronizer()
	 * @generated
	 */
	EAttribute getCorbaPortSynchronizer_RTCPortProfile();

	/**
	 * Returns the meta object for class '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaConnectorProfile <em>Corba Connector Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Corba Connector Profile</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaConnectorProfile
	 * @generated
	 */
	EClass getCorbaConnectorProfile();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaConnectorProfile#getRtcConnectorProfile <em>Rtc Connector Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rtc Connector Profile</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaConnectorProfile#getRtcConnectorProfile()
	 * @see #getCorbaConnectorProfile()
	 * @generated
	 */
	EAttribute getCorbaConnectorProfile_RtcConnectorProfile();

	/**
	 * Returns the meta object for class '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaConfigurationSet <em>Corba Configuration Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Corba Configuration Set</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaConfigurationSet
	 * @generated
	 */
	EClass getCorbaConfigurationSet();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaConfigurationSet#getSDOConfigurationSet <em>SDO Configuration Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>SDO Configuration Set</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaConfigurationSet#getSDOConfigurationSet()
	 * @see #getCorbaConfigurationSet()
	 * @generated
	 */
	EAttribute getCorbaConfigurationSet_SDOConfigurationSet();

	/**
	 * Returns the meta object for class '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext <em>Corba Execution Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Corba Execution Context</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext
	 * @generated
	 */
	EClass getCorbaExecutionContext();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext#getRtcExecutionContextProfile <em>Rtc Execution Context Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rtc Execution Context Profile</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext#getRtcExecutionContextProfile()
	 * @see #getCorbaExecutionContext()
	 * @generated
	 */
	EAttribute getCorbaExecutionContext_RtcExecutionContextProfile();

	/**
	 * Returns the meta object for class '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaContextHandler <em>Corba Context Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Corba Context Handler</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaContextHandler
	 * @generated
	 */
	EClass getCorbaContextHandler();

	/**
	 * Returns the meta object for class '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaObserver <em>Corba Observer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Corba Observer</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaObserver
	 * @generated
	 */
	EClass getCorbaObserver();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaObserver#getServiceProfile <em>Service Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service Profile</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaObserver#getServiceProfile()
	 * @see #getCorbaObserver()
	 * @generated
	 */
	EAttribute getCorbaObserver_ServiceProfile();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaObserver#getServant <em>Servant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Servant</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaObserver#getServant()
	 * @see #getCorbaObserver()
	 * @generated
	 */
	EAttribute getCorbaObserver_Servant();

	/**
	 * Returns the meta object for class '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaStatusObserver <em>Corba Status Observer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Corba Status Observer</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaStatusObserver
	 * @generated
	 */
	EClass getCorbaStatusObserver();

	/**
	 * Returns the meta object for class '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaLogObserver <em>Corba Log Observer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Corba Log Observer</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaLogObserver
	 * @generated
	 */
	EClass getCorbaLogObserver();

	/**
	 * Returns the meta object for enum '{@link jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind <em>System Diagram Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>System Diagram Kind</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind
	 * @generated
	 */
	EEnum getSystemDiagramKind();

	/**
	 * Returns the meta object for data type '{@link RTC.ComponentProfile <em>RTC Component Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>RTC Component Profile</em>'.
	 * @see RTC.ComponentProfile
	 * @model instanceClass="RTC.ComponentProfile"
	 * @generated
	 */
	EDataType getRTCComponentProfile();

	/**
	 * Returns the meta object for data type '{@link RTC.RTObject <em>RTCRT Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>RTCRT Object</em>'.
	 * @see RTC.RTObject
	 * @model instanceClass="RTC.RTObject"
	 * @generated
	 */
	EDataType getRTCRTObject();

	/**
	 * Returns the meta object for data type '{@link java.util.List <em>List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>List</em>'.
	 * @see java.util.List
	 * @model instanceClass="java.util.List"
	 * @generated
	 */
	EDataType getList();

	/**
	 * Returns the meta object for data type '{@link org.omg.PortableServer.Servant <em>Servant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Servant</em>'.
	 * @see org.omg.PortableServer.Servant
	 * @model instanceClass="org.omg.PortableServer.Servant"
	 * @generated
	 */
	EDataType getServant();

	/**
	 * Returns the meta object for data type '{@link jp.go.aist.rtm.toolscommon.model.component.util.ICorbaPortEventObserver <em>ICorba Port Event Observer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>ICorba Port Event Observer</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.util.ICorbaPortEventObserver
	 * @model instanceClass="jp.go.aist.rtm.toolscommon.model.component.util.ICorbaPortEventObserver"
	 * @generated
	 */
	EDataType getICorbaPortEventObserver();

	/**
	 * Returns the meta object for data type '{@link _SDOPackage.Configuration <em>SDO Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>SDO Configuration</em>'.
	 * @see _SDOPackage.Configuration
	 * @model instanceClass="_SDOPackage.Configuration"
	 * @generated
	 */
	EDataType getSDOConfiguration();

	/**
	 * Returns the meta object for data type '{@link _SDOPackage.ConfigurationSet <em>SDO Configuration Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>SDO Configuration Set</em>'.
	 * @see _SDOPackage.ConfigurationSet
	 * @model instanceClass="_SDOPackage.ConfigurationSet"
	 * @generated
	 */
	EDataType getSDOConfigurationSet();

	/**
	 * Returns the meta object for data type '{@link RTC.ConnectorProfile <em>RTC Connector Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>RTC Connector Profile</em>'.
	 * @see RTC.ConnectorProfile
	 * @model instanceClass="RTC.ConnectorProfile"
	 * @generated
	 */
	EDataType getRTCConnectorProfile();

	/**
	 * Returns the meta object for data type '{@link RTC.PortProfile <em>RTC Port Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>RTC Port Profile</em>'.
	 * @see RTC.PortProfile
	 * @model instanceClass="RTC.PortProfile"
	 * @generated
	 */
	EDataType getRTCPortProfile();

	/**
	 * Returns the meta object for data type '{@link RTC.ExecutionContext <em>RTC Execution Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>RTC Execution Context</em>'.
	 * @see RTC.ExecutionContext
	 * @model instanceClass="RTC.ExecutionContext"
	 * @generated
	 */
	EDataType getRTCExecutionContext();

	/**
	 * Returns the meta object for data type '{@link java.beans.PropertyChangeListener <em>Property Change Listener</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Property Change Listener</em>'.
	 * @see java.beans.PropertyChangeListener
	 * @model instanceClass="java.beans.PropertyChangeListener"
	 * @generated
	 */
	EDataType getPropertyChangeListener();

	/**
	 * Returns the meta object for data type '{@link _SDOPackage.Organization <em>SDO Organization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>SDO Organization</em>'.
	 * @see _SDOPackage.Organization
	 * @model instanceClass="_SDOPackage.Organization"
	 * @generated
	 */
	EDataType getSDOOrganization();

	/**
	 * Returns the meta object for data type '{@link _SDOPackage.ServiceProfile <em>SDO Service Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>SDO Service Profile</em>'.
	 * @see _SDOPackage.ServiceProfile
	 * @model instanceClass="_SDOPackage.ServiceProfile"
	 * @generated
	 */
	EDataType getSDOServiceProfile();

	/**
	 * Returns the meta object for data type '{@link jp.go.aist.rtm.toolscommon.model.component.PortInterfaceProfile <em>Port Interface Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Port Interface Profile</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.component.PortInterfaceProfile
	 * @model instanceClass="jp.go.aist.rtm.toolscommon.model.component.PortInterfaceProfile"
	 * @generated
	 */
	EDataType getPortInterfaceProfile();

	/**
	 * Returns the meta object for data type '{@link RTC.ExecutionContextProfile <em>RTC Execution Context Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>RTC Execution Context Profile</em>'.
	 * @see RTC.ExecutionContextProfile
	 * @model instanceClass="RTC.ExecutionContextProfile"
	 * @generated
	 */
	EDataType getRTCExecutionContextProfile();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ComponentFactory getComponentFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals  {
		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.SystemDiagramImpl <em>System Diagram</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.SystemDiagramImpl
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getSystemDiagram()
		 * @generated
		 */
		EClass SYSTEM_DIAGRAM = eINSTANCE.getSystemDiagram();

		/**
		 * The meta object literal for the '<em><b>Components</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_DIAGRAM__COMPONENTS = eINSTANCE.getSystemDiagram_Components();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYSTEM_DIAGRAM__KIND = eINSTANCE.getSystemDiagram_Kind();

		/**
		 * The meta object literal for the '<em><b>Connector Processing</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYSTEM_DIAGRAM__CONNECTOR_PROCESSING = eINSTANCE.getSystemDiagram_ConnectorProcessing();

		/**
		 * The meta object literal for the '<em><b>System Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYSTEM_DIAGRAM__SYSTEM_ID = eINSTANCE.getSystemDiagram_SystemId();

		/**
		 * The meta object literal for the '<em><b>Creation Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYSTEM_DIAGRAM__CREATION_DATE = eINSTANCE.getSystemDiagram_CreationDate();

		/**
		 * The meta object literal for the '<em><b>Update Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYSTEM_DIAGRAM__UPDATE_DATE = eINSTANCE.getSystemDiagram_UpdateDate();

		/**
		 * The meta object literal for the '<em><b>Parent System Diagram</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_DIAGRAM__PARENT_SYSTEM_DIAGRAM = eINSTANCE.getSystemDiagram_ParentSystemDiagram();

		/**
		 * The meta object literal for the '<em><b>Composite Component</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_DIAGRAM__COMPOSITE_COMPONENT = eINSTANCE.getSystemDiagram_CompositeComponent();

		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentImpl <em>Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentImpl
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getComponent()
		 * @generated
		 */
		EClass COMPONENT = eINSTANCE.getComponent();

		/**
		 * The meta object literal for the '<em><b>Configuration Sets</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__CONFIGURATION_SETS = eINSTANCE.getComponent_ConfigurationSets();

		/**
		 * The meta object literal for the '<em><b>Active Configuration Set</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__ACTIVE_CONFIGURATION_SET = eINSTANCE.getComponent_ActiveConfigurationSet();

		/**
		 * The meta object literal for the '<em><b>Ports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__PORTS = eINSTANCE.getComponent_Ports();

		/**
		 * The meta object literal for the '<em><b>Inports</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__INPORTS = eINSTANCE.getComponent_Inports();

		/**
		 * The meta object literal for the '<em><b>Outports</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__OUTPORTS = eINSTANCE.getComponent_Outports();

		/**
		 * The meta object literal for the '<em><b>Serviceports</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__SERVICEPORTS = eINSTANCE.getComponent_Serviceports();

		/**
		 * The meta object literal for the '<em><b>Execution Contexts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__EXECUTION_CONTEXTS = eINSTANCE.getComponent_ExecutionContexts();

		/**
		 * The meta object literal for the '<em><b>Participation Contexts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__PARTICIPATION_CONTEXTS = eINSTANCE.getComponent_ParticipationContexts();

		/**
		 * The meta object literal for the '<em><b>Execution Context Handler</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__EXECUTION_CONTEXT_HANDLER = eINSTANCE.getComponent_ExecutionContextHandler();

		/**
		 * The meta object literal for the '<em><b>Participation Context Handler</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__PARTICIPATION_CONTEXT_HANDLER = eINSTANCE.getComponent_ParticipationContextHandler();

		/**
		 * The meta object literal for the '<em><b>Instance Name L</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__INSTANCE_NAME_L = eINSTANCE.getComponent_InstanceNameL();

		/**
		 * The meta object literal for the '<em><b>Vender L</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__VENDER_L = eINSTANCE.getComponent_VenderL();

		/**
		 * The meta object literal for the '<em><b>Description L</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__DESCRIPTION_L = eINSTANCE.getComponent_DescriptionL();

		/**
		 * The meta object literal for the '<em><b>Category L</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__CATEGORY_L = eINSTANCE.getComponent_CategoryL();

		/**
		 * The meta object literal for the '<em><b>Type Name L</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__TYPE_NAME_L = eINSTANCE.getComponent_TypeNameL();

		/**
		 * The meta object literal for the '<em><b>Version L</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__VERSION_L = eINSTANCE.getComponent_VersionL();

		/**
		 * The meta object literal for the '<em><b>Path Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__PATH_ID = eINSTANCE.getComponent_PathId();

		/**
		 * The meta object literal for the '<em><b>Outport Direction</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__OUTPORT_DIRECTION = eINSTANCE.getComponent_OutportDirection();

		/**
		 * The meta object literal for the '<em><b>Composite Type L</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__COMPOSITE_TYPE_L = eINSTANCE.getComponent_CompositeTypeL();

		/**
		 * The meta object literal for the '<em><b>Components</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__COMPONENTS = eINSTANCE.getComponent_Components();

		/**
		 * The meta object literal for the '<em><b>Primary Execution Context</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__PRIMARY_EXECUTION_CONTEXT = eINSTANCE.getComponent_PrimaryExecutionContext();

		/**
		 * The meta object literal for the '<em><b>Component Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__COMPONENT_ID = eINSTANCE.getComponent_ComponentId();

		/**
		 * The meta object literal for the '<em><b>Required</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__REQUIRED = eINSTANCE.getComponent_Required();

		/**
		 * The meta object literal for the '<em><b>Start Up</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__START_UP = eINSTANCE.getComponent_StartUp();

		/**
		 * The meta object literal for the '<em><b>Shut Down</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__SHUT_DOWN = eINSTANCE.getComponent_ShutDown();

		/**
		 * The meta object literal for the '<em><b>Activation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__ACTIVATION = eINSTANCE.getComponent_Activation();

		/**
		 * The meta object literal for the '<em><b>De Activation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__DE_ACTIVATION = eINSTANCE.getComponent_DeActivation();

		/**
		 * The meta object literal for the '<em><b>Resetting</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__RESETTING = eINSTANCE.getComponent_Resetting();

		/**
		 * The meta object literal for the '<em><b>Initialize</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__INITIALIZE = eINSTANCE.getComponent_Initialize();

		/**
		 * The meta object literal for the '<em><b>Finalize</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__FINALIZE = eINSTANCE.getComponent_Finalize();

		/**
		 * The meta object literal for the '<em><b>Child System Diagram</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__CHILD_SYSTEM_DIAGRAM = eINSTANCE.getComponent_ChildSystemDiagram();

		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl <em>Corba Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getCorbaComponent()
		 * @generated
		 */
		EClass CORBA_COMPONENT = eINSTANCE.getCorbaComponent();

		/**
		 * The meta object literal for the '<em><b>SDO Configuration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CORBA_COMPONENT__SDO_CONFIGURATION = eINSTANCE.getCorbaComponent_SDOConfiguration();

		/**
		 * The meta object literal for the '<em><b>RTC Component Profile</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CORBA_COMPONENT__RTC_COMPONENT_PROFILE = eINSTANCE.getCorbaComponent_RTCComponentProfile();

		/**
		 * The meta object literal for the '<em><b>RTC Execution Contexts</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CORBA_COMPONENT__RTC_EXECUTION_CONTEXTS = eINSTANCE.getCorbaComponent_RTCExecutionContexts();

		/**
		 * The meta object literal for the '<em><b>RTC Participation Contexts</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CORBA_COMPONENT__RTC_PARTICIPATION_CONTEXTS = eINSTANCE.getCorbaComponent_RTCParticipationContexts();

		/**
		 * The meta object literal for the '<em><b>SDO Organization</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CORBA_COMPONENT__SDO_ORGANIZATION = eINSTANCE.getCorbaComponent_SDOOrganization();

		/**
		 * The meta object literal for the '<em><b>RTCRT Objects</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CORBA_COMPONENT__RTCRT_OBJECTS = eINSTANCE.getCorbaComponent_RTCRTObjects();

		/**
		 * The meta object literal for the '<em><b>Component State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CORBA_COMPONENT__COMPONENT_STATE = eINSTANCE.getCorbaComponent_ComponentState();

		/**
		 * The meta object literal for the '<em><b>Ior</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CORBA_COMPONENT__IOR = eINSTANCE.getCorbaComponent_Ior();

		/**
		 * The meta object literal for the '<em><b>Status Observer</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORBA_COMPONENT__STATUS_OBSERVER = eINSTANCE.getCorbaComponent_StatusObserver();

		/**
		 * The meta object literal for the '<em><b>Log Observer</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORBA_COMPONENT__LOG_OBSERVER = eINSTANCE.getCorbaComponent_LogObserver();

		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentSpecificationImpl <em>Specification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentSpecificationImpl
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getComponentSpecification()
		 * @generated
		 */
		EClass COMPONENT_SPECIFICATION = eINSTANCE.getComponentSpecification();

		/**
		 * The meta object literal for the '<em><b>Alias Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_SPECIFICATION__ALIAS_NAME = eINSTANCE.getComponentSpecification_AliasName();

		/**
		 * The meta object literal for the '<em><b>Spec Un Load</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_SPECIFICATION__SPEC_UN_LOAD = eINSTANCE.getComponentSpecification_SpecUnLoad();

		/**
		 * The meta object literal for the '<em><b>Rtc Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_SPECIFICATION__RTC_TYPE = eINSTANCE.getComponentSpecification_RtcType();

		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.PortConnectorImpl <em>Port Connector</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.PortConnectorImpl
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getPortConnector()
		 * @generated
		 */
		EClass PORT_CONNECTOR = eINSTANCE.getPortConnector();

		/**
		 * The meta object literal for the '<em><b>Connector Profile</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_CONNECTOR__CONNECTOR_PROFILE = eINSTANCE.getPortConnector_ConnectorProfile();

		/**
		 * The meta object literal for the '<em><b>Routing Constraint</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_CONNECTOR__ROUTING_CONSTRAINT = eINSTANCE.getPortConnector_RoutingConstraint();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_CONNECTOR__SOURCE = eINSTANCE.getPortConnector_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_CONNECTOR__TARGET = eINSTANCE.getPortConnector_Target();

		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.ExecutionContextImpl <em>Execution Context</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ExecutionContextImpl
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getExecutionContext()
		 * @generated
		 */
		EClass EXECUTION_CONTEXT = eINSTANCE.getExecutionContext();

		/**
		 * The meta object literal for the '<em><b>Kind L</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION_CONTEXT__KIND_L = eINSTANCE.getExecutionContext_KindL();

		/**
		 * The meta object literal for the '<em><b>Rate L</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION_CONTEXT__RATE_L = eINSTANCE.getExecutionContext_RateL();

		/**
		 * The meta object literal for the '<em><b>State L</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION_CONTEXT__STATE_L = eINSTANCE.getExecutionContext_StateL();

		/**
		 * The meta object literal for the '<em><b>Owner</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_CONTEXT__OWNER = eINSTANCE.getExecutionContext_Owner();

		/**
		 * The meta object literal for the '<em><b>Participants</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_CONTEXT__PARTICIPANTS = eINSTANCE.getExecutionContext_Participants();

		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.ContextHandlerImpl <em>Context Handler</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ContextHandlerImpl
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getContextHandler()
		 * @generated
		 */
		EClass CONTEXT_HANDLER = eINSTANCE.getContextHandler();

		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.InPortImpl <em>In Port</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.InPortImpl
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getInPort()
		 * @generated
		 */
		EClass IN_PORT = eINSTANCE.getInPort();

		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.NameValueImpl <em>Name Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.NameValueImpl
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getNameValue()
		 * @generated
		 */
		EClass NAME_VALUE = eINSTANCE.getNameValue();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAME_VALUE__NAME = eINSTANCE.getNameValue_Name();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAME_VALUE__VALUE = eINSTANCE.getNameValue_Value();

		/**
		 * The meta object literal for the '<em><b>Type Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAME_VALUE__TYPE_NAME = eINSTANCE.getNameValue_TypeName();

		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.OutPortImpl <em>Out Port</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.OutPortImpl
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getOutPort()
		 * @generated
		 */
		EClass OUT_PORT = eINSTANCE.getOutPort();

		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.PortImpl <em>Port</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.PortImpl
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getPort()
		 * @generated
		 */
		EClass PORT = eINSTANCE.getPort();

		/**
		 * The meta object literal for the '<em><b>Original Port String</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT__ORIGINAL_PORT_STRING = eINSTANCE.getPort_OriginalPortString();

		/**
		 * The meta object literal for the '<em><b>Synchronizer</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT__SYNCHRONIZER = eINSTANCE.getPort_Synchronizer();

		/**
		 * The meta object literal for the '<em><b>Name L</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT__NAME_L = eINSTANCE.getPort_NameL();

		/**
		 * The meta object literal for the '<em><b>Allow Any Data Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT__ALLOW_ANY_DATA_TYPE = eINSTANCE.getPort_AllowAnyDataType();

		/**
		 * The meta object literal for the '<em><b>Allow Any Interface Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT__ALLOW_ANY_INTERFACE_TYPE = eINSTANCE.getPort_AllowAnyInterfaceType();

		/**
		 * The meta object literal for the '<em><b>Allow Any Dataflow Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT__ALLOW_ANY_DATAFLOW_TYPE = eINSTANCE.getPort_AllowAnyDataflowType();

		/**
		 * The meta object literal for the '<em><b>Allow Any Subscription Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT__ALLOW_ANY_SUBSCRIPTION_TYPE = eINSTANCE.getPort_AllowAnySubscriptionType();

		/**
		 * The meta object literal for the '<em><b>Connector Profiles</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT__CONNECTOR_PROFILES = eINSTANCE.getPort_ConnectorProfiles();

		/**
		 * The meta object literal for the '<em><b>Interfaces</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT__INTERFACES = eINSTANCE.getPort_Interfaces();

		/**
		 * The meta object literal for the '<em><b>Dataflow Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT__DATAFLOW_TYPE = eINSTANCE.getPort_DataflowType();

		/**
		 * The meta object literal for the '<em><b>Subscription Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT__SUBSCRIPTION_TYPE = eINSTANCE.getPort_SubscriptionType();

		/**
		 * The meta object literal for the '<em><b>Data Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT__DATA_TYPE = eINSTANCE.getPort_DataType();

		/**
		 * The meta object literal for the '<em><b>Interface Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT__INTERFACE_TYPE = eINSTANCE.getPort_InterfaceType();

		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.ServicePortImpl <em>Service Port</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ServicePortImpl
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getServicePort()
		 * @generated
		 */
		EClass SERVICE_PORT = eINSTANCE.getServicePort();

		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl <em>Connector Profile</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getConnectorProfile()
		 * @generated
		 */
		EClass CONNECTOR_PROFILE = eINSTANCE.getConnectorProfile();

		/**
		 * The meta object literal for the '<em><b>Dataflow Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__DATAFLOW_TYPE = eINSTANCE.getConnectorProfile_DataflowType();

		/**
		 * The meta object literal for the '<em><b>Subscription Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__SUBSCRIPTION_TYPE = eINSTANCE.getConnectorProfile_SubscriptionType();

		/**
		 * The meta object literal for the '<em><b>Subscription Type Available</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__SUBSCRIPTION_TYPE_AVAILABLE = eINSTANCE.getConnectorProfile_SubscriptionTypeAvailable();

		/**
		 * The meta object literal for the '<em><b>Push Interval Available</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__PUSH_INTERVAL_AVAILABLE = eINSTANCE.getConnectorProfile_PushIntervalAvailable();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__NAME = eINSTANCE.getConnectorProfile_Name();

		/**
		 * The meta object literal for the '<em><b>Connector Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__CONNECTOR_ID = eINSTANCE.getConnectorProfile_ConnectorId();

		/**
		 * The meta object literal for the '<em><b>Data Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__DATA_TYPE = eINSTANCE.getConnectorProfile_DataType();

		/**
		 * The meta object literal for the '<em><b>Interface Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__INTERFACE_TYPE = eINSTANCE.getConnectorProfile_InterfaceType();

		/**
		 * The meta object literal for the '<em><b>Push Rate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__PUSH_RATE = eINSTANCE.getConnectorProfile_PushRate();

		/**
		 * The meta object literal for the '<em><b>Push Policy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__PUSH_POLICY = eINSTANCE.getConnectorProfile_PushPolicy();

		/**
		 * The meta object literal for the '<em><b>Skip Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__SKIP_COUNT = eINSTANCE.getConnectorProfile_SkipCount();

		/**
		 * The meta object literal for the '<em><b>Push Policy Available</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__PUSH_POLICY_AVAILABLE = eINSTANCE.getConnectorProfile_PushPolicyAvailable();

		/**
		 * The meta object literal for the '<em><b>Skip Count Available</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__SKIP_COUNT_AVAILABLE = eINSTANCE.getConnectorProfile_SkipCountAvailable();

		/**
		 * The meta object literal for the '<em><b>Source String</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__SOURCE_STRING = eINSTANCE.getConnectorProfile_SourceString();

		/**
		 * The meta object literal for the '<em><b>Target String</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__TARGET_STRING = eINSTANCE.getConnectorProfile_TargetString();

		/**
		 * The meta object literal for the '<em><b>Outport Serializer Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__OUTPORT_SERIALIZER_TYPE = eINSTANCE.getConnectorProfile_OutportSerializerType();

		/**
		 * The meta object literal for the '<em><b>Outport Buffer Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__OUTPORT_BUFFER_LENGTH = eINSTANCE.getConnectorProfile_OutportBufferLength();

		/**
		 * The meta object literal for the '<em><b>Outport Buffer Full Policy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__OUTPORT_BUFFER_FULL_POLICY = eINSTANCE.getConnectorProfile_OutportBufferFullPolicy();

		/**
		 * The meta object literal for the '<em><b>Outport Buffer Write Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__OUTPORT_BUFFER_WRITE_TIMEOUT = eINSTANCE.getConnectorProfile_OutportBufferWriteTimeout();

		/**
		 * The meta object literal for the '<em><b>Outport Buffer Empty Policy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__OUTPORT_BUFFER_EMPTY_POLICY = eINSTANCE.getConnectorProfile_OutportBufferEmptyPolicy();

		/**
		 * The meta object literal for the '<em><b>Outport Buffer Read Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__OUTPORT_BUFFER_READ_TIMEOUT = eINSTANCE.getConnectorProfile_OutportBufferReadTimeout();

		/**
		 * The meta object literal for the '<em><b>Inport Buffer Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__INPORT_BUFFER_LENGTH = eINSTANCE.getConnectorProfile_InportBufferLength();

		/**
		 * The meta object literal for the '<em><b>Inport Buffer Full Policy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__INPORT_BUFFER_FULL_POLICY = eINSTANCE.getConnectorProfile_InportBufferFullPolicy();

		/**
		 * The meta object literal for the '<em><b>Inport Buffer Write Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__INPORT_BUFFER_WRITE_TIMEOUT = eINSTANCE.getConnectorProfile_InportBufferWriteTimeout();

		/**
		 * The meta object literal for the '<em><b>Inport Buffer Empty Policy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__INPORT_BUFFER_EMPTY_POLICY = eINSTANCE.getConnectorProfile_InportBufferEmptyPolicy();

		/**
		 * The meta object literal for the '<em><b>Inport Buffer Read Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__INPORT_BUFFER_READ_TIMEOUT = eINSTANCE.getConnectorProfile_InportBufferReadTimeout();

		/**
		 * The meta object literal for the '<em><b>Inport Serializer Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__INPORT_SERIALIZER_TYPE = eINSTANCE.getConnectorProfile_InportSerializerType();

		/**
		 * The meta object literal for the '<em><b>Timestamp Policy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__TIMESTAMP_POLICY = eINSTANCE.getConnectorProfile_TimestampPolicy();

		/**
		 * The meta object literal for the '<em><b>Is Reverse</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_PROFILE__IS_REVERSE = eINSTANCE.getConnectorProfile_IsReverse();

		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConfigurationSetImpl <em>Configuration Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ConfigurationSetImpl
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getConfigurationSet()
		 * @generated
		 */
		EClass CONFIGURATION_SET = eINSTANCE.getConfigurationSet();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONFIGURATION_SET__ID = eINSTANCE.getConfigurationSet_Id();

		/**
		 * The meta object literal for the '<em><b>Configuration Data</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONFIGURATION_SET__CONFIGURATION_DATA = eINSTANCE.getConfigurationSet_ConfigurationData();

		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.EIntegerObjectToPointMapEntryImpl <em>EInteger Object To Point Map Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.EIntegerObjectToPointMapEntryImpl
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getEIntegerObjectToPointMapEntry()
		 * @generated
		 */
		EClass EINTEGER_OBJECT_TO_POINT_MAP_ENTRY = eINSTANCE.getEIntegerObjectToPointMapEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EINTEGER_OBJECT_TO_POINT_MAP_ENTRY__KEY = eINSTANCE.getEIntegerObjectToPointMapEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EINTEGER_OBJECT_TO_POINT_MAP_ENTRY__VALUE = eINSTANCE.getEIntegerObjectToPointMapEntry_Value();

		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.toolscommon.model.component.IPropertyMap <em>IProperty Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.component.IPropertyMap
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getIPropertyMap()
		 * @generated
		 */
		EClass IPROPERTY_MAP = eINSTANCE.getIPropertyMap();

		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.PortSynchronizerImpl <em>Port Synchronizer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.PortSynchronizerImpl
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getPortSynchronizer()
		 * @generated
		 */
		EClass PORT_SYNCHRONIZER = eINSTANCE.getPortSynchronizer();

		/**
		 * The meta object literal for the '<em><b>Original Port String</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT_SYNCHRONIZER__ORIGINAL_PORT_STRING = eINSTANCE.getPortSynchronizer_OriginalPortString();

		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaPortSynchronizerImpl <em>Corba Port Synchronizer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.CorbaPortSynchronizerImpl
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getCorbaPortSynchronizer()
		 * @generated
		 */
		EClass CORBA_PORT_SYNCHRONIZER = eINSTANCE.getCorbaPortSynchronizer();

		/**
		 * The meta object literal for the '<em><b>RTC Port Profile</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CORBA_PORT_SYNCHRONIZER__RTC_PORT_PROFILE = eINSTANCE.getCorbaPortSynchronizer_RTCPortProfile();

		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaConnectorProfileImpl <em>Corba Connector Profile</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.CorbaConnectorProfileImpl
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getCorbaConnectorProfile()
		 * @generated
		 */
		EClass CORBA_CONNECTOR_PROFILE = eINSTANCE.getCorbaConnectorProfile();

		/**
		 * The meta object literal for the '<em><b>Rtc Connector Profile</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CORBA_CONNECTOR_PROFILE__RTC_CONNECTOR_PROFILE = eINSTANCE.getCorbaConnectorProfile_RtcConnectorProfile();

		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaConfigurationSetImpl <em>Corba Configuration Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.CorbaConfigurationSetImpl
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getCorbaConfigurationSet()
		 * @generated
		 */
		EClass CORBA_CONFIGURATION_SET = eINSTANCE.getCorbaConfigurationSet();

		/**
		 * The meta object literal for the '<em><b>SDO Configuration Set</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CORBA_CONFIGURATION_SET__SDO_CONFIGURATION_SET = eINSTANCE.getCorbaConfigurationSet_SDOConfigurationSet();

		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaExecutionContextImpl <em>Corba Execution Context</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.CorbaExecutionContextImpl
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getCorbaExecutionContext()
		 * @generated
		 */
		EClass CORBA_EXECUTION_CONTEXT = eINSTANCE.getCorbaExecutionContext();

		/**
		 * The meta object literal for the '<em><b>Rtc Execution Context Profile</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CORBA_EXECUTION_CONTEXT__RTC_EXECUTION_CONTEXT_PROFILE = eINSTANCE.getCorbaExecutionContext_RtcExecutionContextProfile();

		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaContextHandlerImpl <em>Corba Context Handler</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.CorbaContextHandlerImpl
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getCorbaContextHandler()
		 * @generated
		 */
		EClass CORBA_CONTEXT_HANDLER = eINSTANCE.getCorbaContextHandler();

		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaObserverImpl <em>Corba Observer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.CorbaObserverImpl
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getCorbaObserver()
		 * @generated
		 */
		EClass CORBA_OBSERVER = eINSTANCE.getCorbaObserver();

		/**
		 * The meta object literal for the '<em><b>Service Profile</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CORBA_OBSERVER__SERVICE_PROFILE = eINSTANCE.getCorbaObserver_ServiceProfile();

		/**
		 * The meta object literal for the '<em><b>Servant</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CORBA_OBSERVER__SERVANT = eINSTANCE.getCorbaObserver_Servant();

		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaStatusObserverImpl <em>Corba Status Observer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.CorbaStatusObserverImpl
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getCorbaStatusObserver()
		 * @generated
		 */
		EClass CORBA_STATUS_OBSERVER = eINSTANCE.getCorbaStatusObserver();

		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaLogObserverImpl <em>Corba Log Observer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.CorbaLogObserverImpl
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getCorbaLogObserver()
		 * @generated
		 */
		EClass CORBA_LOG_OBSERVER = eINSTANCE.getCorbaLogObserver();

		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind <em>System Diagram Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getSystemDiagramKind()
		 * @generated
		 */
		EEnum SYSTEM_DIAGRAM_KIND = eINSTANCE.getSystemDiagramKind();

		/**
		 * The meta object literal for the '<em>RTC Component Profile</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see RTC.ComponentProfile
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getRTCComponentProfile()
		 * @generated
		 */
		EDataType RTC_COMPONENT_PROFILE = eINSTANCE.getRTCComponentProfile();

		/**
		 * The meta object literal for the '<em>RTCRT Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see RTC.RTObject
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getRTCRTObject()
		 * @generated
		 */
		EDataType RTCRT_OBJECT = eINSTANCE.getRTCRTObject();

		/**
		 * The meta object literal for the '<em>List</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.List
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getList()
		 * @generated
		 */
		EDataType LIST = eINSTANCE.getList();

		/**
		 * The meta object literal for the '<em>Servant</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.PortableServer.Servant
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getServant()
		 * @generated
		 */
		EDataType SERVANT = eINSTANCE.getServant();

		/**
		 * The meta object literal for the '<em>ICorba Port Event Observer</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.component.util.ICorbaPortEventObserver
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getICorbaPortEventObserver()
		 * @generated
		 */
		EDataType ICORBA_PORT_EVENT_OBSERVER = eINSTANCE.getICorbaPortEventObserver();

		/**
		 * The meta object literal for the '<em>SDO Configuration</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see _SDOPackage.Configuration
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getSDOConfiguration()
		 * @generated
		 */
		EDataType SDO_CONFIGURATION = eINSTANCE.getSDOConfiguration();

		/**
		 * The meta object literal for the '<em>SDO Configuration Set</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see _SDOPackage.ConfigurationSet
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getSDOConfigurationSet()
		 * @generated
		 */
		EDataType SDO_CONFIGURATION_SET = eINSTANCE.getSDOConfigurationSet();

		/**
		 * The meta object literal for the '<em>RTC Connector Profile</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see RTC.ConnectorProfile
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getRTCConnectorProfile()
		 * @generated
		 */
		EDataType RTC_CONNECTOR_PROFILE = eINSTANCE.getRTCConnectorProfile();

		/**
		 * The meta object literal for the '<em>RTC Port Profile</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see RTC.PortProfile
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getRTCPortProfile()
		 * @generated
		 */
		EDataType RTC_PORT_PROFILE = eINSTANCE.getRTCPortProfile();

		/**
		 * The meta object literal for the '<em>RTC Execution Context</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see RTC.ExecutionContext
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getRTCExecutionContext()
		 * @generated
		 */
		EDataType RTC_EXECUTION_CONTEXT = eINSTANCE.getRTCExecutionContext();

		/**
		 * The meta object literal for the '<em>Property Change Listener</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.beans.PropertyChangeListener
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getPropertyChangeListener()
		 * @generated
		 */
		EDataType PROPERTY_CHANGE_LISTENER = eINSTANCE.getPropertyChangeListener();

		/**
		 * The meta object literal for the '<em>SDO Organization</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see _SDOPackage.Organization
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getSDOOrganization()
		 * @generated
		 */
		EDataType SDO_ORGANIZATION = eINSTANCE.getSDOOrganization();

		/**
		 * The meta object literal for the '<em>SDO Service Profile</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see _SDOPackage.ServiceProfile
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getSDOServiceProfile()
		 * @generated
		 */
		EDataType SDO_SERVICE_PROFILE = eINSTANCE.getSDOServiceProfile();

		/**
		 * The meta object literal for the '<em>Port Interface Profile</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.component.PortInterfaceProfile
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getPortInterfaceProfile()
		 * @generated
		 */
		EDataType PORT_INTERFACE_PROFILE = eINSTANCE.getPortInterfaceProfile();

		/**
		 * The meta object literal for the '<em>RTC Execution Context Profile</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see RTC.ExecutionContextProfile
		 * @see jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl#getRTCExecutionContextProfile()
		 * @generated
		 */
		EDataType RTC_EXECUTION_CONTEXT_PROFILE = eINSTANCE.getRTCExecutionContextProfile();

	}

} //ComponentPackage
