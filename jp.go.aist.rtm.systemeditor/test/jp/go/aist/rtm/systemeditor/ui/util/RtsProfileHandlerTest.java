package jp.go.aist.rtm.systemeditor.ui.util;

import static jp.go.aist.rtm.toolscommon.profiles.util.XmlHandler.createXMLGregorianCalendar;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.junit.Before;
import org.junit.Test;
import org.openrtp.namespaces.rts.version02.Activation;
import org.openrtp.namespaces.rts.version02.Component;
import org.openrtp.namespaces.rts.version02.ComponentExt;
import org.openrtp.namespaces.rts.version02.ComponentGroup;
import org.openrtp.namespaces.rts.version02.Condition;
import org.openrtp.namespaces.rts.version02.ConditionExt;
import org.openrtp.namespaces.rts.version02.ConfigurationData;
import org.openrtp.namespaces.rts.version02.ConfigurationSet;
import org.openrtp.namespaces.rts.version02.DataportConnector;
import org.openrtp.namespaces.rts.version02.DataportConnectorExt;
import org.openrtp.namespaces.rts.version02.DataportExt;
import org.openrtp.namespaces.rts.version02.Deactivation;
import org.openrtp.namespaces.rts.version02.ExecutionContext;
import org.openrtp.namespaces.rts.version02.ExecutionContextExt;
import org.openrtp.namespaces.rts.version02.Finalize;
import org.openrtp.namespaces.rts.version02.Initialize;
import org.openrtp.namespaces.rts.version02.Location;
import org.openrtp.namespaces.rts.version02.ObjectFactory;
import org.openrtp.namespaces.rts.version02.Participants;
import org.openrtp.namespaces.rts.version02.Preceding;
import org.openrtp.namespaces.rts.version02.Property;
import org.openrtp.namespaces.rts.version02.Resetting;
import org.openrtp.namespaces.rts.version02.RtsProfileExt;
import org.openrtp.namespaces.rts.version02.Serviceport;
import org.openrtp.namespaces.rts.version02.ServiceportConnector;
import org.openrtp.namespaces.rts.version02.ServiceportConnectorExt;
import org.openrtp.namespaces.rts.version02.ServiceportExt;
import org.openrtp.namespaces.rts.version02.Shutdown;
import org.openrtp.namespaces.rts.version02.Startup;
import org.openrtp.namespaces.rts.version02.TargetComponent;
import org.openrtp.namespaces.rts.version02.TargetComponentExt;
import org.openrtp.namespaces.rts.version02.TargetExecutioncontext;
import org.openrtp.namespaces.rts.version02.TargetPort;
import org.openrtp.namespaces.rts.version02.TargetPortExt;
import org.openrtp.namespaces.rts.version02.Waittime;

import RTC.ComponentProfile;
import jp.go.aist.rtm.nameserviceview.corba.NameServerAccesserTest;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingContextNode;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingObjectNode;
import jp.go.aist.rtm.systemeditor.factory.SystemEditorWrapperFactory;
import jp.go.aist.rtm.toolscommon.corba.CorbaObjectMock;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;
import jp.go.aist.rtm.toolscommon.model.component.PortSynchronizer;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind;
import jp.go.aist.rtm.toolscommon.model.component.impl.ConfigurationSetImpl;
import jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl;
import jp.go.aist.rtm.toolscommon.model.component.impl.ExecutionContextImpl;
import jp.go.aist.rtm.toolscommon.model.component.util.PortConnectorFactory;
import jp.go.aist.rtm.toolscommon.model.core.Point;
import jp.go.aist.rtm.toolscommon.model.core.Rectangle;
import jp.go.aist.rtm.toolscommon.util.RtsProfileHandler;
import junit.framework.TestCase;

public class RtsProfileHandlerTest {
	private RtsProfileHandler handler;

	private Port port1;
	private Port port2;
	private Port port3;
	private Port port4;
	private Port port5;
	private Port port6;

	private CorbaComponent eofComponent1;
	private CorbaComponent eofComponent2;
	private CorbaComponent eofComponent3;

	private SystemDiagram diagram;

	private ObjectFactory objectFactory;

	@Before
	public void setUp() throws Exception {
		objectFactory = new ObjectFactory();
		handler = new RtsProfileHandler();
		SystemEditorWrapperFactory instance = new SystemEditorWrapperFactory(NameServerAccesserTest.setupMappingRule());
		SystemEditorWrapperFactory.setInstance(instance);
	}

	// ファイルがないとエラーになる環境依存テスト
	@Test
	public void testLoad() throws Exception {
		String targetFile = "/RTSystemEditor/RtsSampleVer0.2.xml";
		SystemDiagram diagram = handler.load(targetFile, SystemDiagramKind.ONLINE_LITERAL);
		assertEquals(SystemDiagramKind.ONLINE_LITERAL, diagram.getKind());
		assertNotNull(diagram.getProfile());
	}

	// XMLのロードだけを行うテスト
	@Test
	public void testPopulate() throws Exception {
		SystemDiagram diagram = ComponentFactory.eINSTANCE.createSystemDiagram();
//		diagram.setKind(SystemDiagramKind.ONLINE_LITERAL);
		handler.setOnline(true);
		handler.populate(diagram, setupProfile());

		assertEquals("id1", diagram.getSystemId());
		assertEquals("2009-01-14T18:49:18.892+09:00", diagram.getCreationDate());
		assertEquals("2009-01-15T18:49:18.892+09:00", diagram.getUpdateDate());
//		jp.go.aist.rtm.toolscommon.model.component.Property property =
//			(jp.go.aist.rtm.toolscommon.model.component.Property) diagram.getProperties().get(0);
//		assertEquals("property1", property.getName());
//		assertEquals("value1", property.getValue());

		assertEquals(1, diagram.getComponents().size());
		jp.go.aist.rtm.toolscommon.model.component.Component component = diagram.getComponents().get(0);
		assertTrue(component instanceof CorbaComponent);
		assertEquals("id3", component.getComponentId());
		assertEquals("192.168.1.164/client164.local.tech-arts.co.jp.host_cxt/Composite0.rtc", component.getPathId());
		assertEquals("Composite0", component.getInstanceNameL());
		assertEquals("composite.PeriodicECShared", component.getCategoryL());
		assertTrue(component.isRequired());
		Rectangle constraint = component.getConstraint();
		assertEquals(10, constraint.getX());
		assertEquals(20, constraint.getY());
		assertEquals(30, constraint.getWidth());
		assertEquals(40, constraint.getHeight());
		assertEquals("RIGHT", component.getOutportDirection());
//		jp.go.aist.rtm.toolscommon.model.component.Property property =
//			(jp.go.aist.rtm.toolscommon.model.component.Property) component.getProperties().get(0);
//		assertEquals("property2", property.getName());
//		assertEquals("value2", property.getValue());

		// XMLのロードを行った段階ではポートの情報はセットできない
		assertTrue(component.getPorts().isEmpty());

		// XMLのロードを行った段階ではコンフィグの情報はセットしない
		assertTrue(component.getConfigurationSets().isEmpty());

		jp.go.aist.rtm.toolscommon.model.component.Component component2 = component.getComponents().get(0);
		assertNull(component2.getCategoryL());
		assertFalse(component2.isRequired());
		assertEquals(0, component2.getComponents().size());

		jp.go.aist.rtm.toolscommon.model.component.Component component3 = component.getComponents().get(1);
		assertEquals(0, component3.getComponents().size());
	}

	// オフラインでロードを行うテスト
	@Test
	public void testPopulate2() throws Exception {
		SystemDiagram diagram = ComponentFactory.eINSTANCE.createSystemDiagram();
		diagram.setKind(SystemDiagramKind.OFFLINE_LITERAL);
		handler.setOnline(false);
		RtsProfileExt profile = setupProfile();
		handler.populate(diagram, profile);
		diagram.setProfile(profile);

		jp.go.aist.rtm.toolscommon.model.component.Component component = diagram.getComponents().get(0);
		assertTrue(component instanceof ComponentSpecification);

		handler.restoreConfigSet(diagram);
		handler.restoreCompositeComponentPort(diagram);

		Port port = component.getPorts().get(0);
		assertEquals("ConsoleIn0.out", port.getNameL());
		port = component.getPorts().get(1);
		assertEquals("ConsoleIn0.client", port.getNameL());
		assertEquals("{componentId:id2,pathId:192.168.1.164/client164.local.tech-arts.co.jp.host_cxt/ConsoleIn0.rtc,instanceName:ConsoleIn0,portName:ConsoleIn0.client}", port.getOriginalPortString());
		port = component.getPorts().get(2);
		assertEquals("ConsoleOut0.in", port.getNameL());
		port = component.getPorts().get(3);
		assertEquals("ConsoleOut0.server", port.getNameL());

		handler.restoreConnection(diagram);
		port = component.getPorts().get(0);
		assertEquals("ConsoleIn0.out", port.getNameL());
		assertEquals("{componentId:id2,pathId:192.168.1.164/client164.local.tech-arts.co.jp.host_cxt/ConsoleIn0.rtc,instanceName:ConsoleIn0,portName:ConsoleIn0.out}", port.getOriginalPortString());
		ConnectorProfile connector1 = port.getConnectorProfiles().get(0);
		assertEquals("connector1", connector1.getConnectorId());
		assertEquals("conectorName1", connector1.getName());
		assertEquals("TimedOctetSeq", connector1.getDataType());
		assertEquals("CORBA_Any", connector1.getInterfaceType());
		assertEquals("PUSH", connector1.getDataflowType());
		assertEquals("Flush", connector1.getSubscriptionType());
		assertNull(connector1.getPushRate());
		// ベンディングポイントの復元
		PortConnector portConnector = diagram.getConnectorMap().get("connector1");
		Point point = portConnector.getRoutingConstraint().map().get(1);
		assertEquals(392, point.getX());
		assertEquals(110, point.getY());

		port = component.getPorts().get(1);
		ConnectorProfile connector2 = port.getConnectorProfiles().get(0);
		assertEquals("connector2", connector2.getConnectorId());
		assertEquals("conectorName2", connector2.getName());
		// ベンディングポイントの復元
		portConnector = diagram.getConnectorMap().get("connector2");
		assertTrue(portConnector.getRoutingConstraint().map().isEmpty());

		port = component.getPorts().get(1);
		assertEquals("ConsoleIn0.client", port.getNameL());

		jp.go.aist.rtm.toolscommon.model.component.Component component3 = component.getComponents().get(1);
		port = component3.getPorts().get(0);
		assertEquals(connector1, port.getConnectorProfiles().get(0));
		assertEquals(port.getOriginalPortString(), connector1.getTargetString());
		port = component3.getPorts().get(1);
		assertEquals(connector2, port.getConnectorProfiles().get(0));
		assertEquals(port.getOriginalPortString(), connector2.getTargetString());

		jp.go.aist.rtm.toolscommon.model.component.Component component2 = component.getComponents().get(0);
		jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet configSet = component2.getConfigurationSets().get(0);
		assertEquals(component2.getActiveConfigurationSet(), configSet);
		assertEquals("config1", configSet.getId());
		NameValue configData = configSet.getConfigurationData().get(0);
		assertEquals("name1", configData.getName());
		assertEquals("value1", configData.getValueAsString());
	}

	// IORの復元を行うテスト
	// 192.168.1.164にネームサーバとrtcdが起動しているという前提
	public void _testLoadIOR() throws Exception {
		NamingContextNode context = NameServerAccesserTest.getNamingContext();
		TreeIterator allContents = context.eAllContents();
		boolean didAssert = false;
		List<CorbaComponentImpl> existComponents = new ArrayList<CorbaComponentImpl>();
		SystemDiagram diagram = ComponentFactory.eINSTANCE.createSystemDiagram();

		while (allContents.hasNext()) {
			Object obj = allContents.next();
			if (obj instanceof NamingObjectNode) {
				Object entry = ((NamingObjectNode) obj).getEntry();
				if (entry instanceof CorbaComponentImpl) {
					CorbaComponentImpl component = (CorbaComponentImpl)entry;
					String ior = component.getCorbaObjectInterface().toString();
					existComponents.add(component);
					populateComponentImpl(diagram, ior);
				}
			}
		}
		assertTrue(didAssert);
	}

	// ベンドポイントを復元するテスト
	@Test
	public void testConvertFromBendPointString() throws Exception {
		Map<Integer, jp.go.aist.rtm.toolscommon.model.core.Point> result
			= handler.convertFromBendPointString("{1:(392,110)}");
		assertEquals(1, result.keySet().size());
		jp.go.aist.rtm.toolscommon.model.core.Point point = result.get(1);
		assertEquals(392, point.getX());
		assertEquals(110, point.getY());
	}

	@Test
	public void testConvertFromBendPointString2() throws Exception {
		Map<Integer, jp.go.aist.rtm.toolscommon.model.core.Point> result
			= handler.convertFromBendPointString("{1:(392,110), 2:(23,45)}");
		assertEquals(2, result.keySet().size());
		jp.go.aist.rtm.toolscommon.model.core.Point point = result.get(1);
		assertEquals(392, point.getX());
		assertEquals(110, point.getY());
		point = result.get(2);
		assertEquals(23, point.getX());
		assertEquals(45, point.getY());
	}

	// ダイアグラムを保存するテスト(online)
	@Test
	public void testSaveOnline() throws Exception {
		SystemDiagram diagram = setupDiagram();
		diagram.setKind(SystemDiagramKind.ONLINE_LITERAL);

		RtsProfileExt result = handler.save(diagram);

		ComponentExt component = (ComponentExt) result.getComponents().get(1);

		// コンポーネント1のEC
		ExecutionContextExt executionContext = (ExecutionContextExt) component.getExecutionContexts().get(0);
		assertEquals("default", executionContext.getId());
		assertEquals("PERIODIC", executionContext.getKind());
		assertEquals(Double.valueOf(15.0), executionContext.getRate());
		assertEquals("id2", executionContext.getParticipants().get(0).getComponentId());
		Property property4 = executionContext.getProperties().get(0);
		assertEquals("property5", property4.getName());
		assertEquals("value5", property4.getValue());

		assertEquals("default2", component.getExecutionContexts().get(1).getId());
	}

	// ダイアグラムを保存するテスト
	@Test
	public void testSave() throws Exception {
		SystemDiagram diagram = setupDiagram();
		diagram.setKind(SystemDiagramKind.OFFLINE_LITERAL);
		setupConnectors();

		RtsProfileExt result = handler.save(diagram);

		// プロファイル直下の属性
		assertEquals("id1", result.getId());
		assertEquals("abstract1", result.getAbstract());
		assertEquals("2009-01-14T18:49:18.000+09:00", result.getCreationDate().toString());
		assertEquals("2009-01-25T09:35:18.000+09:00", result.getUpdateDate().toString());
		assertEquals("0.2", result.getVersion());
		// 拡張属性
		assertEquals("comment1", result.getComment());
		assertEquals("version up log1", result.getVersionUpLogs().get(0));
//		Property property3 = result.getProperties().get(0);
//		assertEquals("proverty1N", property3.getName());
//		assertEquals("value1N", property3.getValue());
		Property property1 = result.getProperties().get(0);
		assertEquals("property1", property1.getName());
		assertEquals("value1", property1.getValue());

		// コンポーネント１：通常オブジェクト
		assertEquals(3, result.getComponents().size());
		ComponentExt component = (ComponentExt) result.getComponents().get(1);
		assertEquals("id2", component.getId());
		assertEquals("192.168.1.164/client164.local.tech-arts.co.jp.host_cxt/ConsoleIn0.rtc", component.getPathUri());
		assertEquals("config1", component.getActiveConfigurationSet());
		assertEquals("ConsoleIn0", component.getInstanceName());
		assertEquals("None", component.getCompositeType());
		assertTrue(component.isIsRequired());

		// コンポーネント1のデータポート
		DataportExt dataport = (DataportExt) component.getDataPorts().get(0);
		assertEquals("out", dataport.getName());
		assertEquals("comment3", dataport.getComment());
		assertTrue(dataport.isVisible());
		Property property7 = dataport.getProperties().get(0);
		assertEquals("property3", property7.getName());
		assertEquals("value3", property7.getValue());

		// コンポーネント1のサービスポート
		ServiceportExt serviceport = (ServiceportExt)component.getServicePorts().get(0);
		assertEquals("client", serviceport.getName());
		assertEquals("comment4", serviceport.getComment());
		assertTrue(serviceport.isVisible());
		Property property8 = serviceport.getProperties().get(0);
		assertEquals("property4", property8.getName());
		assertEquals("value4", property8.getValue());

		// コンポーネント1のコンフィグセット
		ConfigurationSet configurationSet = component.getConfigurationSets().get(0);
		assertEquals("config1", configurationSet.getId());
		ConfigurationData configurationData = configurationSet.getConfigurationData().get(0);
		assertEquals("name1", configurationData.getName());
		assertEquals("value1", configurationData.getData());

		// コンポーネント1の追加属性
		assertEquals("comment2", component.getComment());
		assertTrue(component.isVisible());
		Location location = component.getLocation();
		assertEquals(92, location.getX().intValue());
		assertEquals(83, location.getY().intValue());
		assertEquals(-1, location.getHeight().intValue());
		assertEquals(-1, location.getWidth().intValue());
		assertEquals("RIGHT", location.getDirection());
		Property property5 = component.getProperties().get(0);
		assertEquals("property2", property5.getName());
		assertEquals("value2", property5.getValue());
		Property property6 = component.getProperties().get(1);
		assertEquals("IOR", property6.getName());
		assertEquals("newIOR", property6.getValue());

		// コンポーネントグループ
		ComponentGroup componentGroup = result.getGroups().get(0);
		assertEquals("groupId1", componentGroup.getGroupId());
		TargetComponentExt targetComponent = (TargetComponentExt) componentGroup.getMembers().get(0);
		assertEquals("id2", targetComponent.getComponentId());
		assertEquals("ConsoleIn0", targetComponent.getInstanceName());
		Property property = targetComponent.getProperties().get(0);
		assertEquals("property8", property.getName());
		assertEquals("value8", property.getValue());

		// コンポーネント2：複合コンポーネント
		ComponentExt component2 = (ComponentExt) result.getComponents().get(0);
		assertEquals("id3", component2.getId());
		assertEquals("PeriodicECShared", component2.getCompositeType());
		TargetComponentExt participant = (TargetComponentExt) component2.getParticipants().get(0).getParticipant();
		assertEquals("id2", participant.getComponentId());
		assertEquals("ConsoleIn0", participant.getInstanceName());
		property = participant.getProperties().get(0);
		assertEquals("property8", property.getName());
		assertEquals("value8", property.getValue());

		dataport = (DataportExt) component2.getDataPorts().get(0);
		assertEquals("out", dataport.getName());

		// コンポーネント3：通常コンポーネント
		ComponentExt component3 = (ComponentExt) result.getComponents().get(2);
		assertEquals("id4", component3.getId());
		assertEquals("ConsoleOut0", component3.getInstanceName());

		TargetComponentExt participant2 = (TargetComponentExt) component2.getParticipants().get(1).getParticipant();
		assertEquals("id4", participant2.getComponentId());
		assertEquals("ConsoleOut0", participant2.getInstanceName());

		// DataportConnectors
		assertEquals(2, result.getDataPortConnectors().size());
		DataportConnectorExt connector1 = (DataportConnectorExt) result.getDataPortConnectors().get(0);
		assertEquals("connector1", connector1.getConnectorId());
		assertEquals("conectorName1", connector1.getName());
		assertEquals("TimedOctetSeq", connector1.getDataType());
		assertEquals("CORBA_Any", connector1.getInterfaceType());
		assertEquals("PUSH", connector1.getDataflowType());
		assertEquals("Flush", connector1.getSubscriptionType());
		assertNull(connector1.getPushInterval());
		assertEquals("comment6", connector1.getComment());
		assertTrue(connector1.isVisible());
		// ベンドポイントの保存
		assertEquals(2, connector1.getProperties().size());
		property = connector1.getProperties().get(0);
		assertEquals("BEND_POINT", property.getName());
		assertEquals("{1:(25,45)}", property.getValue());
		property = connector1.getProperties().get(1);
		assertEquals("property6", property.getName());
		assertEquals("value6", property.getValue());


		// データポート接続のターゲットポート
		TargetPortExt sourceDataPort = (TargetPortExt) connector1.getSourceDataPort();
		assertEquals("id2", sourceDataPort.getComponentId());
		assertEquals("ConsoleIn0", sourceDataPort.getInstanceName());
		assertEquals("out", sourceDataPort.getPortName());
		assertEquals(1, sourceDataPort.getProperties().size());
		assertEquals("method", sourceDataPort.getProperties().get(0).getName());
		assertEquals("setupSourceDataport", sourceDataPort.getProperties().get(0).getValue());
		TargetPortExt targetDataPort = (TargetPortExt) connector1.getTargetDataPort();
		// id3とid4のコンポーネントに同じポートが存在する
		assertEquals("id4", targetDataPort.getComponentId());
		assertEquals("ConsoleOut0", targetDataPort.getInstanceName());
		assertEquals("in", targetDataPort.getPortName());

		DataportConnectorExt connector1N = (DataportConnectorExt) result.getDataPortConnectors().get(1);
		assertEquals("connector1N", connector1N.getConnectorId());
		sourceDataPort = (TargetPortExt) connector1N.getSourceDataPort();
		assertEquals("id4", sourceDataPort.getComponentId());
		assertEquals("ConsoleOut0", sourceDataPort.getInstanceName());
		assertEquals("in2", sourceDataPort.getPortName());
		targetDataPort = (TargetPortExt) connector1N.getTargetDataPort();
		assertEquals("id2", targetDataPort.getComponentId());
		assertEquals("ConsoleIn0", targetDataPort.getInstanceName());
		assertEquals("out2", targetDataPort.getPortName());

		// ServiceportConnectors
		assertEquals(1, result.getServicePortConnectors().size());
		ServiceportConnectorExt connector2 = (ServiceportConnectorExt) result.getServicePortConnectors().get(0);
		assertEquals("connector2", connector2.getConnectorId());
		assertEquals("conectorName2", connector2.getName());
		assertEquals("trans1", connector2.getTransMethod());

		// Condition
		ConditionExt condition = (ConditionExt) result.getStartUp().getTargets().get(0);
		assertEquals(BigInteger.ONE, condition.getSequence());
		TargetExecutioncontext targetComponent2 = condition.getTargetComponent();
		assertEquals("id4", targetComponent2.getComponentId());
		assertEquals("ConsoleOut0", targetComponent2.getInstanceName());
		assertEquals("id5", targetComponent2.getId());
		assertEquals(BigInteger.TEN, condition.getWaitTime().getWaitTime());
		Preceding preceding = condition.getPreceding();
		assertEquals("10s", preceding.getTimeout());
		assertEquals("5.0ms", preceding.getSendingTiming());
		assertEquals("id5", preceding.getPrecedingComponents().get(0).getId());
		Property property2 = condition.getProperties().get(0);
		assertEquals("property10", property2.getName());
		assertEquals("value10", property2.getValue());

		// message_sending
		assertEquals(BigInteger.ONE, result.getShutDown().getTargets().get(0).getSequence());
		assertEquals(BigInteger.ONE, result.getActivation().getTargets().get(0).getSequence());
		assertEquals(BigInteger.ONE, result.getDeactivation().getTargets().get(0).getSequence());
		assertEquals(BigInteger.ONE, result.getResetting().getTargets().get(0).getSequence());
		assertEquals(BigInteger.ONE, result.getInitializing().getTargets().get(0).getSequence());
		assertEquals(BigInteger.ONE, result.getFinalizing().getTargets().get(0).getSequence());

	}

	private SystemDiagram setupDiagram() {
		diagram = ComponentFactory.eINSTANCE.createSystemDiagram();
		diagram.setProfile(setupProfile());
		diagram.setSystemId("id1");
		diagram.setCreationDate("2009-01-14T18:49:18.892+09:00");
		diagram.setUpdateDate("2009-01-25T09:35:18.892+09:00");
//		diagram.getComponents().add(setupEofComponent1());
		diagram.getComponents().add(setupEofComponent2());
//		diagram.getComponents().add(setupEofComponent3());
		return diagram;
	}

	private void setupConnectors() {
		setupConnector(setupEofDataport1(), setupEofDataport2(), "connector1"
				, "conectorName1", "TimedOctetSeq", "CORBA_Any", "PUSH"
				, "Flush");
		setupConnector(setupEofServiceport1(), setupEofServiceport2(), "connector2"
				, "conectorName2", null, null, null
				, null);
		setupConnector(setupEofDataport4(), setupEofDataport3(),  "connector1N"
				, "conectorName1", "TimedOctetSeq", "CORBA_Any", "PUSH"
				, "Flush");
		setupConnector(setupEofDataport1().proxy(), setupEofDataport2().proxy(), "connector1"
				, "conectorName1", "TimedOctetSeq", "CORBA_Any", "PUSH"
				, "Flush");
	}

	private void populateComponentImpl(SystemDiagram diagram, String ior) {
		CorbaComponent component = ComponentFactory.eINSTANCE.createCorbaComponent();
		component.setIor(ior);
		diagram.getComponents().add(component);
	}

	private RtsProfileExt setupProfile() {
		RtsProfileExt profile = new RtsProfileExt();
		profile.setId("id1");
		profile.setAbstract("abstract1");
		profile.setCreationDate(createXMLGregorianCalendar("2009-01-14T18:49:18.892+09:00"));
		profile.setUpdateDate(createXMLGregorianCalendar("2009-01-15T18:49:18.892+09:00"));
		profile.setVersion("0.2");
		profile.getComponents().add(setupComponent1());
		profile.getComponents().add(setupComponent2());
		profile.getComponents().add(setupComponent3());
		profile.getGroups().add(setupGroup());
		profile.getDataPortConnectors().add(setupDataConnector());
		profile.getServicePortConnectors().add(setupServiceConnector());
		profile.setStartUp(setupStartup());
		profile.setShutDown(setupShutdown());
		profile.setActivation(setupActivation());
		profile.setDeactivation(setupDeactivation());
		profile.setResetting(setupResetting());
		profile.setInitializing(setupInitializing());
		profile.setFinalizing(setupFinalizing());
		profile.setComment("comment1");
		profile.getVersionUpLogs().add("version up log1");
		profile.getProperties().add(createPropery("property1", "value1"));
		return profile;
	}

//	private List<RepositoryViewItem> setupRepositoryModel() {
//		List<RepositoryViewItem> result = new ArrayList<RepositoryViewItem>();
//		result.add(setupRepositoryViewItem1());
//		result.add(setupRepositoryViewItem2());
//		return result;
//	}

//	private RepositoryViewItem setupRepositoryViewItem1() {
//		RepositoryViewLeafItem result = new RepositoryViewLeafItem("name1");
//		result.setComponent(setupComponentSpecification1());
//		return result;
//	}

//	private RepositoryViewItem setupRepositoryViewItem2() {
//		RepositoryViewLeafItem result = new RepositoryViewLeafItem("name2");
//		result.setComponent(setupComponentSpecification2());
//		return result;
//	}

//	private ComponentSpecification setupComponentSpecification1() {
//		ComponentSpecification result = ComponentFactory.eINSTANCE.createComponentSpecification();
//		result.setComponentId("id2");
//		result.setPathId("192.168.1.164/client164.local.tech-arts.co.jp.host_cxt/ConsoleIn0.rtc");
//		result.setInstanceNameL("ConsoleIn0");
//		result.getPorts().add(setupOutport1());
//		result.getPorts().add(createServiceport("client"));
//		return result;
//	}

//	private ServicePort createServiceport(String name) {
//		ServicePort result = ComponentFactory.eINSTANCE.createServicePort();
//		result.setSynchronizer(ComponentFactory.eINSTANCE.createPortSynchronizer());
//		result.setNameL(name);
//		return result;
//	}

//	private ComponentSpecification setupComponentSpecification2() {
//		ComponentSpecification result = ComponentFactory.eINSTANCE.createComponentSpecification();
//		result.setComponentId("id4");
//		result.setPathId("192.168.1.164/client164.local.tech-arts.co.jp.host_cxt/ConsoleOut0.rtc");
//		result.setInstanceNameL("ConsoleOut0");
//		result.getPorts().add(setupInport1());
//		result.getPorts().add(createServiceport("server"));
//		return result;
//	}

//	private OutPort setupOutport1() {
//		OutPort result = ComponentFactory.eINSTANCE.createOutPort();
//		result.setSynchronizer(ComponentFactory.eINSTANCE.createPortSynchronizer());
//		result.setNameL("out");
//		return result;
//	}

//	private InPort setupInport1() {
//		InPort result = ComponentFactory.eINSTANCE.createInPort();
//		result.setSynchronizer(ComponentFactory.eINSTANCE.createPortSynchronizer());
//		result.setNameL("in");
//		return result;
//	}

	private Property createPropery(String name, String value) {
		Property result = new Property();
		result.setName(name);
		result.setValue(value);
		return result;
	}

	private Component setupComponent1() {
		ComponentExt result = new ComponentExt();
		result.setId("id2");
		result.setPathUri("192.168.1.164/client164.local.tech-arts.co.jp.host_cxt/ConsoleIn0.rtc");
		result.setActiveConfigurationSet("config1");
		result.setInstanceName("ConsoleIn0");
		result.setCompositeType("None");
		result.setIsRequired(false);
		result.getDataPorts().add(setupDataPort1());
		result.getServicePorts().add(setupServicePort1());
		result.getConfigurationSets().add(setupConfigurationSet1());
		result.getExecutionContexts().add(setupExecutionContext());
		result.getExecutionContexts().add(setupExecutionContext());
		result.setComment("comment2");
		result.setVisible(true);
		result.setLocation(createLocation(10,20,30,40));
		result.getProperties().add(createPropery("property2", "value2"));
		return result;
	}

	private CorbaComponent setupEofComponent1() {
		if (eofComponent1 != null) return eofComponent1;
		eofComponent1 = ComponentFactory.eINSTANCE.createCorbaComponent();
		eofComponent1.setComponentId("id2");
		eofComponent1.setPathId("192.168.1.164/client164.local.tech-arts.co.jp.host_cxt/ConsoleIn0.rtc");
		eofComponent1.setRTCComponentProfile(new ComponentProfile());
		eofComponent1.getRTCComponentProfile().instance_name = "ConsoleIn0";
		eofComponent1.setRequired(true);
		eofComponent1.getExecutionContexts().add(setupEofExecutionContext());
		eofComponent1.setConstraint(new Rectangle());
		eofComponent1.getConstraint().setX(92);
		eofComponent1.getConstraint().setY(83);
		eofComponent1.getConstraint().setHeight(-1);
		eofComponent1.getConstraint().setWidth(-1);
		eofComponent1.setOutportDirection("RIGHT");
//		eofComponent1.getProperties().add(createEOFProperty("property2N", "value2N"));
//		eofComponent1.getProperties().add(createEOFProperty("IOR", "oldIOR"));
		eofComponent1.setCorbaObject(setupCorbaObject());
		eofComponent1.getPorts().add(setupEofDataport1());
		eofComponent1.getPorts().add(setupEofServiceport1());
		eofComponent1.getPorts().add(setupEofDataport3());
		jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet setupEofConfigurationSet1 = setupEofConfigurationSet1();
		eofComponent1.getConfigurationSets().add(setupEofConfigurationSet1);
		eofComponent1.setActiveConfigurationSet(setupEofConfigurationSet1);
		return eofComponent1;
	}

	private org.omg.CORBA.Object setupCorbaObject() {
		return new CorbaObjectMock(new StringBuffer());
	}

	private jp.go.aist.rtm.toolscommon.model.component.ExecutionContext setupEofExecutionContext() {
		jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext result = ComponentFactory.eINSTANCE
				.createCorbaExecutionContext();
		result.setRateL(15.0);
		return result;
	}

	private Location createLocation(int x, int y, int w, int h) {
		Location result = new Location();
		result.setX(BigInteger.valueOf(x));
		result.setY(BigInteger.valueOf(y));
		result.setWidth(BigInteger.valueOf(w));
		result.setHeight(BigInteger.valueOf(h));
		result.setDirection("RIGHT");
		return result;
	}

	private Component setupComponent2() {
		ComponentExt result = new ComponentExt();
		result.setId("id3");
		result.setPathUri("192.168.1.164/client164.local.tech-arts.co.jp.host_cxt/Composite0.rtc");
		result.setInstanceName("Composite0");
		result.setCompositeType("PeriodicECShared");
		result.setIsRequired(true);
		result.setActiveConfigurationSet("default");
		result.getParticipants().add(setupParticipant1());
		result.getParticipants().add(setupParticipant2());
		result.getDataPorts().add(createDataPort("ConsoleOut0.in"));
		result.getDataPorts().add(createDataPort("ConsoleIn0.out"));
		result.getServicePorts().add(createRtsServicePort("ConsoleOut0.server"));
		result.getServicePorts().add(createRtsServicePort("ConsoleIn0.client"));
		result.getConfigurationSets().add(createConfigurationSet());
		result.setLocation(createLocation(10,20,30,40));
		return result;
	}

	private ConfigurationSet createConfigurationSet() {
		ConfigurationSet config = objectFactory.createConfigurationSet();
		config.setId("default");
		config.getConfigurationData().add(createConfigurationData());
		return config;
	}

	private ConfigurationData createConfigurationData() {
		ConfigurationData data = objectFactory.createConfigurationData();
		data.setData("ConsoleOut0.in,ConsoleIn0.out,ConsoleOut0.server,ConsoleIn0.client");
		data.setName("exported_ports");
		return data;
	}

	private CorbaComponent setupEofComponent2() {
		if (eofComponent2 != null) return eofComponent2;
		eofComponent2 = ComponentFactory.eINSTANCE.createCorbaComponent();
		eofComponent2.setComponentId("id3");
		eofComponent2.setPathId("192.168.1.164/client164.local.tech-arts.co.jp.host_cxt/Composite0.rtc");
		eofComponent2.setRTCComponentProfile(new ComponentProfile());
		eofComponent2.getRTCComponentProfile().category = "composite.PeriodicECShared";
		eofComponent2.getRTCComponentProfile().instance_name = "Composite0";
		eofComponent2.setRequired(true);
		eofComponent2.getComponents().add(setupEofComponent1());
		eofComponent2.getComponents().add(setupEofComponent3());
		eofComponent2.getExecutionContexts().add(setupEofExecutionContext());
		eofComponent2.setConstraint(new Rectangle());
		eofComponent2.getConstraint().setX(92);
		eofComponent2.getConstraint().setY(83);
		eofComponent2.getConstraint().setHeight(-1);
		eofComponent2.getConstraint().setWidth(-1);
		eofComponent2.setOutportDirection("RIGHT");
//		eofComponent2.getProperties().add(createEOFProperty("property2N", "value2N"));
//		eofComponent2.getProperties().add(createEOFProperty("IOR", "oldIOR2"));
		eofComponent2.setCorbaObject(setupCorbaObject());
		eofComponent2.getPorts().add(setupEofDataport1().proxy());
		eofComponent2.getPorts().add(setupEofDataport2().proxy());
		eofComponent2.getPorts().add(setupEofServiceport1().proxy());
		eofComponent2.getPorts().add(setupEofServiceport2().proxy());
		jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet setupEofConfigurationSet1 = setupEofConfigurationSet1();
		eofComponent2.getConfigurationSets().add(setupEofConfigurationSet1);
		eofComponent2.setActiveConfigurationSet(setupEofConfigurationSet1);
		return eofComponent2;
	}

	private Component setupComponent3() {
		Component result = new Component();
		result.setId("id4");
		result.setPathUri("192.168.1.164/client164.local.tech-arts.co.jp.host_cxt/ConsoleOut0.rtc");
		result.setInstanceName("ConsoleOut0");
		result.setCompositeType("None");
		result.setIsRequired(false);
		result.getDataPorts().add(createDataPort("in"));
		result.getServicePorts().add(createRtsServicePort("server"));
		return result;
	}

	private CorbaComponent setupEofComponent3() {
		if (eofComponent3 != null) return eofComponent3;
		eofComponent3 = ComponentFactory.eINSTANCE.createCorbaComponent();
		eofComponent3.setComponentId("id4");
		eofComponent3.setPathId("192.168.1.164/client164.local.tech-arts.co.jp.host_cxt/ConsoleOut0.rtc");
		eofComponent3.setRTCComponentProfile(new ComponentProfile());
		eofComponent3.getRTCComponentProfile().instance_name = "ConsoleOut0";
		eofComponent3.setRequired(true);
		eofComponent3.getExecutionContexts().add(setupEofExecutionContext());
		eofComponent3.setConstraint(new Rectangle());
		eofComponent3.getConstraint().setX(92);
		eofComponent3.getConstraint().setY(83);
		eofComponent3.getConstraint().setHeight(-1);
		eofComponent3.getConstraint().setWidth(-1);
		eofComponent3.setOutportDirection("RIGHT");
//		eofComponent3.getProperties().add(createEOFProperty("property2N", "value2N"));
//		eofComponent3.getProperties().add(createEOFProperty("IOR", "oldIOR"));
		eofComponent3.setCorbaObject(setupCorbaObject());
		eofComponent3.getPorts().add(setupEofDataport2());
		eofComponent3.getPorts().add(setupEofServiceport2());
		eofComponent3.getPorts().add(setupEofDataport4());
		jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet setupEofConfigurationSet1 = setupEofConfigurationSet1();
		eofComponent3.getConfigurationSets().add(setupEofConfigurationSet1);
		eofComponent3.setActiveConfigurationSet(setupEofConfigurationSet1);
		return eofComponent3;
	}

	private ComponentGroup setupGroup() {
		ComponentGroup result = new ComponentGroup();
		result.setGroupId("groupId1");
		result.getMembers().add(setupTargetComponent());
		return result;
	}

	private Startup setupStartup() {
		Startup result = new Startup();
		result.getTargets().add(setupCondition());
		return result;
	}

	private Shutdown setupShutdown() {
		Shutdown result = new Shutdown();
		result.getTargets().add(setupCondition());
		return result;
	}

	private Activation setupActivation() {
		Activation result = new Activation();
		result.getTargets().add(setupCondition());
		return result;
	}

	private Deactivation setupDeactivation() {
		Deactivation result = new Deactivation();
		result.getTargets().add(setupCondition());
		return result;
	}

	private Resetting setupResetting() {
		Resetting result = new Resetting();
		result.getTargets().add(setupCondition());
		return result;
	}

	private Initialize setupInitializing() {
		Initialize result = new Initialize();
		result.getTargets().add(setupCondition());
		return result;
	}

	private Finalize setupFinalizing() {
		Finalize result = new Finalize();
		result.getTargets().add(setupCondition());
		return result;
	}

	private Condition setupCondition() {
		ConditionExt result = new ConditionExt();
		result.setSequence(BigInteger.ONE);
		result.setTargetComponent(setupTargeExecutionContext());
		result.setWaitTime(setupWaitTime());
		result.setPreceding(setupPreceding());
		result.getProperties().add(createPropery("property10", "value10"));
		return result;
	}

	private Preceding setupPreceding() {
		Preceding result = new Preceding();
		result.setTimeout("10s");
		result.setSendingTiming("5.0ms");
		result.getPrecedingComponents().add(setupTargeExecutionContext());
		return result;
	}

	private Waittime setupWaitTime() {
		Waittime result = new Waittime();
		result.setWaitTime(BigInteger.TEN);
		return result;
	}

	private TargetExecutioncontext setupTargeExecutionContext() {
		TargetExecutioncontext result = new TargetExecutioncontext();
		result.setComponentId("id4");
		result.setInstanceName("ConsoleOut0");
		result.setId("id5");
		return result;
	}

	private DataportExt setupDataPort1() {
		DataportExt result = new DataportExt();
		result.setName("out");
		result.setComment("comment3");
		result.setVisible(true);
		result.getProperties().add(createPropery("property3", "value3"));
		return result;
	}
//
//	private PortSynchronizer setupCorbaPortSynchronizer() {
//		CorbaPortSynchronizer result = ComponentFactory.eINSTANCE.createCorbaPortSynchronizer();
//		result.setCorbaObject(new CorbaObjectMock(new StringBuffer()));
//		return result;
//	}

	private PortSynchronizer setupPortSynchronizer(String originalPortString) {
		PortSynchronizer result = ComponentFactory.eINSTANCE.createPortSynchronizer();
		result.setOriginalPortString(originalPortString);
		return result;
	}

	private Port setupEofDataport1() {
		if (port1 != null) return port1;
		port1 = ComponentFactory.eINSTANCE.createOutPort();
		port1.setSynchronizer(setupPortSynchronizer("ConsoleIn0.out"));
		port1.setNameL("out");
		return port1;
	}

	private Port setupEofDataport2() {
		if (port2 != null) return port2;
		port2 = ComponentFactory.eINSTANCE.createInPort();
		port2.setSynchronizer(setupPortSynchronizer("ConsoleOut0.in"));
		port2.setNameL("in");
		return port2;
	}

	private Port setupEofDataport3() {
		if (port5 != null) return port5;
		port5 = ComponentFactory.eINSTANCE.createOutPort();
		port5.setSynchronizer(setupPortSynchronizer("ConsoleIn0.out2"));
		port5.setNameL("out2");
		return port5;
	}

	private Port setupEofDataport4() {
		if (port6 != null) return port6;
		port6 = ComponentFactory.eINSTANCE.createInPort();
		port6.setSynchronizer(setupPortSynchronizer("ConsoleOut0.in2"));
		port6.setNameL("in2");
		return port6;
	}

	private Port setupEofServiceport1() {
		if (port3 != null) return port3;
		port3 = ComponentFactory.eINSTANCE.createServicePort();
		port3.setSynchronizer(setupPortSynchronizer("ConsoleIn0.client"));
		port3.setNameL("client");
		return port3;
	}

	private Port setupEofServiceport2() {
		if (port4 != null) return port4;
		port4 = ComponentFactory.eINSTANCE.createServicePort();
		port4.setSynchronizer(setupPortSynchronizer("ConsoleOut0.server"));
		port4.setNameL("server");
		return port4;
	}

	private Serviceport setupServicePort1() {
		ServiceportExt result = new ServiceportExt();
		result.setName("client");
		result.setComment("comment4");
		result.setVisible(true);
		result.getProperties().add(createPropery("property4", "value4"));
		return result;
	}

	private DataportExt createDataPort(String name) {
		DataportExt result = new DataportExt();
		result.setName(name);
		return result;
	}

	private ServiceportExt createRtsServicePort(String name) {
		ServiceportExt result = new ServiceportExt();
		result.setName(name);
		return result;
	}

	private ConfigurationSet setupConfigurationSet1() {
		ConfigurationSet result = new ConfigurationSet();
		result.setId("config1");
		result.getConfigurationData().add(setupConfiguratonData());
		return result;
	}

	private jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet setupEofConfigurationSet1() {
		ConfigurationSetImpl result = new ConfigurationSetImpl();
		result.setId("config1");
		result.getConfigurationData().add(setupEofConfigurationData());
		return result;
	}

	private ExecutionContext setupExecutionContext() {
		ExecutionContextExt result = new ExecutionContextExt();
		result.setId("default2");
		result.setKind("PERIODIC2");
		result.setRate(10.0);
		result.getParticipants().add(setupTargetComponent());
		result.getProperties().add(createPropery("property5", "value5"));
		return result;
	}

	private ConfigurationData setupConfiguratonData() {
		ConfigurationData result = new ConfigurationData();
		result.setName("name1");
		result.setData("value1");
		return result;
	}

	private NameValue setupEofConfigurationData() {
		NameValue value = ComponentFactory.eINSTANCE.createNameValue();
		value.setName("name1");
		value.setValue("value1");
		return value;
	}

	private Participants setupParticipant1() {
		Participants result = new Participants();
		result.setParticipant(setupTargetComponent());
		return result;
	}

	private Participants setupParticipant2() {
		Participants result = new Participants();
		result.setParticipant(setupTargetComponent2());
		return result;
	}

	private DataportConnector setupDataConnector() {
		DataportConnectorExt result = new DataportConnectorExt();
		result.setConnectorId("connector1");
		result.setName("conectorName1");
		result.setDataType("TimedOctetSeq");
		result.setInterfaceType("CORBA_Any");
		result.setDataflowType("PUSH");
		result.setSubscriptionType("Flush");
		result.setPushInterval(null);
		result.setSourceDataPort(setupSourceDataport());
		result.setTargetDataPort(setupTargetDataport());
		result.setComment("comment6");
		result.setVisible(true);
		result.getProperties().add(createPropery("property6", "value6"));
		result.getProperties().add(createPropery("BEND_POINT", "{1:(392,110)}"));
		return result;
	}

	private TargetPort setupSourceDataport() {
		TargetPortExt port = new TargetPortExt();
		port.setComponentId("id2");
		port.setInstanceName("ConsoleIn0");
		port.setPortName("out");
		port.getProperties().add(createPropery("method", "setupSourceDataport"));
		return port;
	}

	private TargetPort setupTargetDataport() {
		TargetPortExt port = new TargetPortExt();
		port.setComponentId("id4");
		port.setInstanceName("ConsoleOut0");
		port.setPortName("in");
		port.getProperties().add(createPropery("method", "setupTargetDataport"));
		return port;
	}

	private ServiceportConnector setupServiceConnector() {
		ServiceportConnectorExt result = new ServiceportConnectorExt();
		result.setConnectorId("connector2");
		result.setName("conectorName2");
		result.setTransMethod("trans1");
		result.setSourceServicePort(setupSourceServiceport());
		result.setTargetServicePort(setupTargetServiceport());
		result.setComment("comment7");
		result.setVisible(true);
		result.getProperties().add(createPropery("property7", "value7"));
		return result;
	}

	private TargetPort setupSourceServiceport() {
		TargetPortExt result = new TargetPortExt();
		result.setComponentId("id2");
		result.setInstanceName("ConsoleIn0");
		result.setPortName("client");
		result.getProperties().add(createPropery("property9", "value9"));
		return result;
	}

	private TargetPort setupTargetServiceport() {
		TargetPort port = new TargetPort();
		port.setComponentId("id4");
		port.setInstanceName("ConsoleOut0");
		port.setPortName("server");
		return port;
	}

	private TargetComponentExt setupTargetComponent() {
		TargetComponentExt result = new TargetComponentExt();
		result.setComponentId("id2");
		result.setInstanceName("ConsoleIn0");
		result.getProperties().add(createPropery("property8", "value8"));
		return result;
	}

	private TargetComponent setupTargetComponent2() {
		TargetComponentExt result = new TargetComponentExt();
		result.setComponentId("id4");
		result.setInstanceName("ConsoleOut0");
		result.getProperties().add(createPropery("property10", "value10"));
		return result;
	}

	private void setupConnector(Port source, Port target, String connectorId
			, String connectorName, String dataType, String interfaceType
			, String dataFlowType, String subscriptionType) {
		PortConnector connector = PortConnectorFactory.createPortConnectorSpecification();
		connector.setConnectorProfile(ComponentFactory.eINSTANCE.createConnectorProfile());
		connector.getConnectorProfile().setConnectorId(connectorId);
		connector.getConnectorProfile().setName(connectorName);
		connector.getConnectorProfile().setDataType(dataType);
		connector.getConnectorProfile().setInterfaceType(interfaceType);
		connector.getConnectorProfile().setDataflowType(dataFlowType);
		connector.getConnectorProfile().setSubscriptionType(subscriptionType);
		connector.setSource(source);
		connector.setTarget(target);
		jp.go.aist.rtm.toolscommon.model.core.Point point = new Point();
		point.setX(25);
		point.setY(45);
		connector.getRoutingConstraint().map().put(new Integer(1), point);
		connector.createConnectorR();
		diagram.getConnectorMap().put(connectorId, connector);
	}
}
