package jp.go.aist.rtm.systemeditor.ui.util;

import static jp.go.aist.rtm.toolscommon.profiles.util.XmlHandler.createXMLGregorianCalendar;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.junit.Test;
import org.openrtp.namespaces.rts.version02.Component;
import org.openrtp.namespaces.rts.version02.ComponentExt;
import org.openrtp.namespaces.rts.version02.ConfigurationData;
import org.openrtp.namespaces.rts.version02.ConfigurationSet;
import org.openrtp.namespaces.rts.version02.Dataport;
import org.openrtp.namespaces.rts.version02.Location;
import org.openrtp.namespaces.rts.version02.ObjectFactory;
import org.openrtp.namespaces.rts.version02.Participants;
import org.openrtp.namespaces.rts.version02.RtsProfileExt;
import org.openrtp.namespaces.rts.version02.Serviceport;
import org.openrtp.namespaces.rts.version02.ServiceportConnector;
import org.openrtp.namespaces.rts.version02.TargetComponent;
import org.openrtp.namespaces.rts.version02.TargetPort;

import jp.go.aist.rtm.nameserviceview.corba.NameServerAccesserTest;
import jp.go.aist.rtm.systemeditor.factory.SystemEditorWrapperFactory;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind;
import jp.go.aist.rtm.toolscommon.util.RtsProfileHandler;
import junit.framework.TestCase;

public class CompositeOfflineLoadTest {
	private ObjectFactory objectFactory;
	private SystemDiagram diagram;

	@Test
	public void testLoadAndSave() throws Exception {
		objectFactory = new ObjectFactory();
		SystemEditorWrapperFactory instance = new SystemEditorWrapperFactory(NameServerAccesserTest.setupMappingRule());
		SystemEditorWrapperFactory.setInstance(instance);

		RtsProfileHandler handler = new RtsProfileHandler();
		handler.setOnline(false);
		diagram = ComponentFactory.eINSTANCE.createSystemDiagram();
		diagram.setKind(SystemDiagramKind.OFFLINE_LITERAL);
		RtsProfileExt rtsProfile = createRtsProfile();
		handler.populate(diagram, rtsProfile);
		diagram.setProfile(rtsProfile);
		handler.restoreConfigSet(diagram);
		handler.restoreCompositeComponentPort(diagram);
		handler.restoreConnection(diagram);

		assertEquals("2009-03-06T10:15:43.000+09:00", diagram.getUpdateDate());
		assertEquals("2009-03-06T10:15:43.000+09:00", diagram.getCreationDate());
		assertEquals("RTSystem:d.d:d", diagram.getSystemId());

		assertEquals(2, diagram.getComponents().size());
		verfifyComponentS1((ComponentSpecification) diagram.getComponents().get(0));
//		verfifyComponentCameraComponent((ComponentSpecification) diagram.getComponents().get(1));
//		verfifyComponentImageProcess((ComponentSpecification) diagram.getComponents().get(2));
		verfifyComponentImageViewer((ComponentSpecification) diagram.getComponents().get(1));

		RtsProfileExt savedProfile = handler.save(diagram);
		assertEquals(rtsProfile.getUpdateDate(), savedProfile.getUpdateDate());
		assertEquals(rtsProfile.getCreationDate(), savedProfile.getCreationDate());
		assertEquals(rtsProfile.getId(), savedProfile.getId());

		verifyComponents(rtsProfile.getComponents(), savedProfile.getComponents());
		verifyServicePortConnectors(rtsProfile.getServicePortConnectors(), savedProfile.getServicePortConnectors());
	}

	private void verifyComponents(List<Component> c1,
			List<Component> c2) {
		assertEquals(c1.size(), c2.size());
		for(int i = 0; i < c1.size(); i++) {
			verifyComponent((ComponentExt)c1.get(i), (ComponentExt)c2.get(i));
		}
	}

	private void verifyComponent(ComponentExt c1, ComponentExt c2) {
		assertEquals(c1.isIsRequired(), c2.isIsRequired());
		assertEquals(c1.getCompositeType(), c2.getCompositeType());
		assertEquals(c1.getActiveConfigurationSet(), c2.getActiveConfigurationSet());
		assertEquals(c1.getInstanceName(), c2.getInstanceName());
		assertEquals(c1.getPathUri(), c2.getPathUri());
		assertEquals(c1.getId(), c2.getId());
		verifyDataPorts(c1.getDataPorts(), c2.getDataPorts());
		verifyServicePorts(c1.getServicePorts(), c2.getServicePorts());
		verifyConfigurationSets(c1.getConfigurationSets(), c2.getConfigurationSets());
		verifyParticipants(c1.getParticipants(), c2.getParticipants());
		verifyLocation(c1.getLocation(), c2.getLocation());
	}

	private void verifyDataPorts(List<Dataport> p1,
			List<Dataport> p2) {
		assertEquals(p1.size(), p2.size());
		for(int i = 0; i < p1.size(); i++) {
			assertEquals(p1.get(i).getName(), p2.get(i).getName());
		}
	}

	private void verifyServicePorts(List<Serviceport> p1,
			List<Serviceport> p2) {
		assertEquals(p1.size(), p2.size());
		for(int i = 0; i < p1.size(); i++) {
			assertEquals(p1.get(i).getName(), p2.get(i).getName());
		}
	}

	private void verifyConfigurationSets(
			List<ConfigurationSet> cs1,
			List<ConfigurationSet> cs2) {
		assertEquals(cs1.size(), cs2.size());
		for (int i = 0; i < cs1.size(); i++) {
			verifyConfigurationSet(cs1.get(i), cs2.get(i));
		}
	}

	private void verifyConfigurationSet(ConfigurationSet cs1,
			ConfigurationSet cs2) {
		assertEquals(cs1.getId(), cs2.getId());
		assertEquals(cs1.getConfigurationData().size(), cs2.getConfigurationData().size());
		for (int i = 0; i < cs1.getConfigurationData().size(); i++) {
			ConfigurationData d1 = cs1.getConfigurationData().get(i);
			ConfigurationData d2 = cs2.getConfigurationData().get(i);
			assertEquals(d1.getData(), d2.getData());
			assertEquals(d1.getName(), d2.getName());
		}
	}

	private void verifyParticipants(List<Participants> p1,
			List<Participants> p2) {
		assertEquals(p1.size(), p2.size());
		for (int i = 0; i < p1.size(); i++) {
			TargetComponent t1 = p1.get(i).getParticipant();
			TargetComponent t2 = p2.get(i).getParticipant();
			assertEquals(t1.getComponentId(), t2.getComponentId());
			assertEquals(t1.getInstanceName(), t2.getInstanceName());
		}
	}

	private void verifyLocation(Location l1, Location l2) {
		assertEquals(l1.getDirection(), l2.getDirection());
		assertEquals(l1.getX(), l2.getX());
		assertEquals(l1.getY(), l2.getY());
	}

	private void verifyServicePortConnectors(
			List<ServiceportConnector> pc1,
			List<ServiceportConnector> pc2) {
		assertEquals(pc1.size(), pc2.size());
		for (int i = 0; i < pc1.size(); i++) {
			verifyServicePortConnector(pc1.get(i), pc2.get(i));
		}
	}

	private void verifyServicePortConnector(
			ServiceportConnector pc1,
			ServiceportConnector pc2) {
		assertEquals(pc1.getConnectorId(), pc2.getConnectorId());
		assertEquals(pc1.getName(), pc2.getName());
		verifyTargetPort(pc1.getSourceServicePort(), pc2.getSourceServicePort());
		verifyTargetPort(pc1.getTargetServicePort(), pc2.getTargetServicePort());
	}

	private void verifyTargetPort(TargetPort p1,
			TargetPort p2) {
		assertEquals(p1.getComponentId(), p2.getComponentId());
		assertEquals(p1.getInstanceName(), p2.getInstanceName());
		assertEquals(p1.getPortName(), p2.getPortName());
	}

	private void verfifyComponentS1(ComponentSpecification component) {
		assertTrue(component.isRequired());
		assertEquals("PeriodicECShared", component.getCompositeTypeL());
		assertEquals("default", component.getActiveConfigurationSet().getId());
		assertEquals("s1", component.getInstanceNameL());
		assertEquals("file://localhost/C:\\RTSystemEditor\\rsmtj\\s1.xml", component.getPathId());
		assertEquals("RTC:.composite.PeriodicECShared.:", component.getComponentId());
		assertEquals(5, component.getPorts().size());
		verifyPort("CameraComponent_1.ImageData"
				, "{componentId:RTC:Sample Vender.example.CameraComponent:1.0.0,pathId:file://localhost/C:\\RTSystemEditor\\rsmtj\\RTC_Sample Vender.example.CameraComponent_1.0.0.xml:1,instanceName:CameraComponent_1,portName:CameraComponent_1.ImageData}"
				, component.getPorts().get(0)
				, new String[]{});
		verifyPort("CameraComponent_1.CapPORT"
				, "{componentId:RTC:Sample Vender.example.CameraComponent:1.0.0,pathId:file://localhost/C:\\RTSystemEditor\\rsmtj\\RTC_Sample Vender.example.CameraComponent_1.0.0.xml:1,instanceName:CameraComponent_1,portName:CameraComponent_1.CapPORT}"
				, component.getPorts().get(1)
				, new String[]{"9b4074b4-29b5-4a22-9956-22070915ef25"});
		verifyPort("ImageProcess_1.Out"
				, "{componentId:RTC:Sample Vender.example.ImageProcess:1.0.0,pathId:file://localhost/C:\\RTSystemEditor\\rsmtj\\RTC_Sample Vender.example.ImageProcess_1.0.0.xml:1,instanceName:ImageProcess_1,portName:ImageProcess_1.Out}"
				, component.getPorts().get(2)
				, new String[]{});
		verifyPort("ImageProcess_1.In"
				, "{componentId:RTC:Sample Vender.example.ImageProcess:1.0.0,pathId:file://localhost/C:\\RTSystemEditor\\rsmtj\\RTC_Sample Vender.example.ImageProcess_1.0.0.xml:1,instanceName:ImageProcess_1,portName:ImageProcess_1.In}"
				, component.getPorts().get(3)
				, new String[]{});
		verifyPort("ImageProcess_1.CapPORT"
				, "{componentId:RTC:Sample Vender.example.ImageProcess:1.0.0,pathId:file://localhost/C:\\RTSystemEditor\\rsmtj\\RTC_Sample Vender.example.ImageProcess_1.0.0.xml:1,instanceName:ImageProcess_1,portName:ImageProcess_1.CapPORT}"
				, component.getPorts().get(4)
				, new String[]{});
		assertEquals(1, component.getConfigurationSets().size());
		verifyConfigSet(component.getConfigurationSets().get(0));
		EList components = component.getComponents();
		assertEquals(2, components.size());
//		assertEquals(diagram.getComponents().get(0), components.get(0));
//		assertEquals(diagram.getComponents().get(1), components.get(1));
		verifyLocation("RIGHT", 255, 131, component);
	}

//	private void verfifyComponentCameraComponent(
//			ComponentSpecification component) {
//		assertTrue(component.isRequired());
//		assertEquals("None", component.getCompositeTypeL());
//		assertNull(component.getActiveConfigurationSet());
//		assertEquals("CameraComponent_1", component.getInstanceNameL());
//		assertEquals("file://localhost/C:\\RTSystemEditor\\rsmtj\\RTC_Sample Vender.example.CameraComponent_1.0.0.xml:1", component.getPathId());
//		assertEquals("RTC:Sample Vender.example.CameraComponent:1.0.0", component.getComponentId());
//		assertEquals(2, component.getPorts().size());
//		verifyPort("CameraComponent_1.ImageData"
//				, "{componentId:RTC:Sample Vender.example.CameraComponent:1.0.0,pathId:file://localhost/C:\\RTSystemEditor\\rsmtj\\RTC_Sample Vender.example.CameraComponent_1.0.0.xml:1,instanceName:CameraComponent_1,portName:CameraComponent_1.ImageData}"
//				, (Port) component.getPorts().get(0)
//				, new String[]{});
//		verifyPort("CameraComponent_1.CapPORT"
//				, "{componentId:RTC:Sample Vender.example.CameraComponent:1.0.0,pathId:file://localhost/C:\\RTSystemEditor\\rsmtj\\RTC_Sample Vender.example.CameraComponent_1.0.0.xml:1,instanceName:CameraComponent_1,portName:CameraComponent_1.CapPORT}"
//				, (Port) component.getPorts().get(1)
//				, new String[]{"9b4074b4-29b5-4a22-9956-22070915ef25"});
//		assertEquals(0, component.getConfigurationSets().size());
//		verifyLocation("RIGHT", 255, 131, component);
//	}

//	private void verfifyComponentImageProcess(
//			ComponentSpecification component) {
//		assertTrue(component.isRequired());
//		assertEquals("None", component.getCompositeTypeL());
//		assertNull(component.getActiveConfigurationSet());
//		assertEquals("ImageProcess_1", component.getInstanceNameL());
//		assertEquals("file://localhost/C:\\RTSystemEditor\\rsmtj\\RTC_Sample Vender.example.ImageProcess_1.0.0.xml:1", component.getPathId());
//		assertEquals("RTC:Sample Vender.example.ImageProcess:1.0.0", component.getComponentId());
//		assertEquals(3, component.getPorts().size());
//		verifyPort("ImageProcess_1.In"
//				, "{componentId:RTC:Sample Vender.example.ImageProcess:1.0.0,pathId:file://localhost/C:\\RTSystemEditor\\rsmtj\\RTC_Sample Vender.example.ImageProcess_1.0.0.xml:1,instanceName:ImageProcess_1,portName:ImageProcess_1.In}"
//				, (Port) component.getPorts().get(0)
//				, new String[]{});
//		verifyPort("ImageProcess_1.Out"
//				, "{componentId:RTC:Sample Vender.example.ImageProcess:1.0.0,pathId:file://localhost/C:\\RTSystemEditor\\rsmtj\\RTC_Sample Vender.example.ImageProcess_1.0.0.xml:1,instanceName:ImageProcess_1,portName:ImageProcess_1.Out}"
//				, (Port) component.getPorts().get(1)
//				, new String[]{});
//		verifyPort("ImageProcess_1.CapPORT"
//				, "{componentId:RTC:Sample Vender.example.ImageProcess:1.0.0,pathId:file://localhost/C:\\RTSystemEditor\\rsmtj\\RTC_Sample Vender.example.ImageProcess_1.0.0.xml:1,instanceName:ImageProcess_1,portName:ImageProcess_1.CapPORT}"
//				, (Port) component.getPorts().get(2)
//				, new String[]{});
//		assertEquals(0, component.getConfigurationSets().size());
//		verifyLocation("RIGHT", 474, 113, component);
//	}

	private void verfifyComponentImageViewer(
			ComponentSpecification component) {
		assertTrue(component.isRequired());
		assertEquals("None", component.getCompositeTypeL());
		assertNull(component.getActiveConfigurationSet());
		assertEquals("ImageViewer_1", component.getInstanceNameL());
		assertEquals("file://localhost/C:\\RTSystemEditor\\rsmtj\\RTC_Sample Vender.example.ImageViewer_1.0.0.xml:1", component.getPathId());
		assertEquals("RTC:Sample Vender.example.ImageViewer:1.0.0", component.getComponentId());
		assertEquals(2, component.getPorts().size());
		verifyPort("ImageViewer_1.ImageDataIn"
				, "{componentId:RTC:Sample Vender.example.ImageViewer:1.0.0,pathId:file://localhost/C:\\RTSystemEditor\\rsmtj\\RTC_Sample Vender.example.ImageViewer_1.0.0.xml:1,instanceName:ImageViewer_1,portName:ImageViewer_1.ImageDataIn}"
				, component.getPorts().get(0)
				, new String[]{});
		verifyPort("ImageViewer_1.CapPORT"
				, "{componentId:RTC:Sample Vender.example.ImageViewer:1.0.0,pathId:file://localhost/C:\\RTSystemEditor\\rsmtj\\RTC_Sample Vender.example.ImageViewer_1.0.0.xml:1,instanceName:ImageViewer_1,portName:ImageViewer_1.CapPORT}"
				, component.getPorts().get(1)
				, new String[]{"9b4074b4-29b5-4a22-9956-22070915ef25"});
		assertEquals(0, component.getConfigurationSets().size());
		verifyLocation("LEFT", 495, 160, component);
	}

	private void verifyPort(String portName, String originalPortString, Port port, String[] connectorIds) {
		assertEquals(portName, port.getNameL());
		assertEquals(originalPortString, port.getOriginalPortString());
		assertEquals(connectorIds.length, port.getConnectorProfiles().size());
		for (int i = 0; i < connectorIds.length; i++) {
			ConnectorProfile connectorProfile = port.getConnectorProfiles().get(i);
			assertEquals(connectorIds[i], connectorProfile.getConnectorId());
		}
	}

	private void verifyConfigSet(jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet configSet) {
		assertEquals("default", configSet.getId());
		assertEquals(1, configSet.getConfigurationData().size());
		NameValue nv = configSet.getConfigurationData().get(0);
		assertEquals("CameraComponent_1.ImageData,CameraComponent_1.CapPORT,ImageProcess_1.In,ImageProcess_1.Out,ImageProcess_1.CapPORT"
				, nv.getValueAsString());
		assertEquals("exported_ports", nv.getName());
	}

	private void verifyLocation(String direction, int x, int y,
			ComponentSpecification component) {
		assertEquals(direction, component.getOutportDirection());
		assertEquals(x, component.getConstraint().getX());
		assertEquals(y, component.getConstraint().getY());
	}

	private RtsProfileExt createRtsProfile() {
		RtsProfileExt rtsProfile = objectFactory.createRtsProfileExt();
		rtsProfile.setUpdateDate(createXMLGregorianCalendar("2009-03-06T10:15:43.349+09:00"));
		rtsProfile.setCreationDate(createXMLGregorianCalendar("2009-03-06T10:15:43.349+09:00"));
		rtsProfile.setVersion("0.2");
		rtsProfile.setId("RTSystem:d.d:d");
		rtsProfile.getComponents().add(createComponentS1());
		rtsProfile.getComponents().add(craeteComponentImageViewer());
		rtsProfile.getComponents().add(craeteComponentCameraComponent());
		rtsProfile.getComponents().add(craeteComponentImageProcess());
		rtsProfile.getServicePortConnectors().add(createServiceportConnector());
		return rtsProfile;
	}

	private ComponentExt createComponentS1() {
		ComponentExt component = objectFactory.createComponentExt();
		component.setIsRequired(true);
		component.setCompositeType("PeriodicECShared");
		component.setActiveConfigurationSet("default");
		component.setInstanceName("s1");
		component.setPathUri("file://localhost/C:\\RTSystemEditor\\rsmtj\\s1.xml");
		component.setId("RTC:.composite.PeriodicECShared.:");
		component.getDataPorts().add(createDataPort("CameraComponent_1.ImageData"));
		component.getDataPorts().add(createDataPort("ImageProcess_1.Out"));
		component.getDataPorts().add(createDataPort("ImageProcess_1.In"));
		component.getServicePorts().add(createServicePort("CameraComponent_1.CapPORT"));
		component.getServicePorts().add(createServicePort("ImageProcess_1.CapPORT"));
		component.getConfigurationSets().add(createConfigurationSet());
		component.getParticipants().add(createParticipants("CameraComponent_1", "RTC:Sample Vender.example.CameraComponent:1.0.0"));
		component.getParticipants().add(createParticipants("ImageProcess_1", "RTC:Sample Vender.example.ImageProcess:1.0.0"));
		component.setLocation(createLocation("RIGHT", 255, 131));
		return component;
	}

	private Component craeteComponentCameraComponent() {
		ComponentExt component = objectFactory.createComponentExt();
		component.setIsRequired(true);
		component.setCompositeType("None");
		component.setInstanceName("CameraComponent_1");
		component.setPathUri("file://localhost/C:\\RTSystemEditor\\rsmtj\\RTC_Sample Vender.example.CameraComponent_1.0.0.xml:1");
		component.setId("RTC:Sample Vender.example.CameraComponent:1.0.0");
		component.getDataPorts().add(createDataPort("CameraComponent_1.ImageData"));
		component.getServicePorts().add(createServicePort("CameraComponent_1.CapPORT"));
		component.setLocation(createLocation("RIGHT", 255, 131));
		return component;
	}

	private Component craeteComponentImageProcess() {
		ComponentExt component = objectFactory.createComponentExt();
		component.setIsRequired(true);
		component.setCompositeType("None");
		component.setInstanceName("ImageProcess_1");
		component.setPathUri("file://localhost/C:\\RTSystemEditor\\rsmtj\\RTC_Sample Vender.example.ImageProcess_1.0.0.xml:1");
		component.setId("RTC:Sample Vender.example.ImageProcess:1.0.0");
		component.getDataPorts().add(createDataPort("ImageProcess_1.Out"));
		component.getDataPorts().add(createDataPort("ImageProcess_1.In"));
		component.getServicePorts().add(createServicePort("ImageProcess_1.CapPORT"));
		component.setLocation(createLocation("RIGHT", 474, 113));
		return component;
	}

	private Component craeteComponentImageViewer() {
		ComponentExt component = objectFactory.createComponentExt();
		component.setIsRequired(true);
		component.setCompositeType("None");
		component.setInstanceName("ImageViewer_1");
		component.setPathUri("file://localhost/C:\\RTSystemEditor\\rsmtj\\RTC_Sample Vender.example.ImageViewer_1.0.0.xml:1");
		component.setId("RTC:Sample Vender.example.ImageViewer:1.0.0");
		component.getDataPorts().add(createDataPort("ImageViewer_1.ImageDataIn"));
		component.getServicePorts().add(createServicePort("ImageViewer_1.CapPORT"));
		component.setLocation(createLocation("LEFT", 495, 160));
		return component;
	}

	private Dataport createDataPort(String portName) {
		Dataport port = objectFactory.createDataportExt();
		port.setName(portName);
		return port;
	}

	private Serviceport createServicePort(String portName) {
		Serviceport port = objectFactory.createServiceportExt();
		port.setName(portName);
		return port;
	}

	private ConfigurationSet createConfigurationSet() {
		ConfigurationSet config = objectFactory.createConfigurationSet();
		config.setId("default");
		config.getConfigurationData().add(createConfigurationData());
		return config;
	}

	private ConfigurationData createConfigurationData() {
		ConfigurationData data = objectFactory.createConfigurationData();
		data.setData("CameraComponent_1.ImageData,CameraComponent_1.CapPORT,ImageProcess_1.In,ImageProcess_1.Out,ImageProcess_1.CapPORT");
		data.setName("exported_ports");
		return data;
	}

	private Participants createParticipants(String instanceName, String componentId) {
		Participants participants = objectFactory.createParticipants();
		participants.setParticipant(createParticipant(instanceName, componentId));
		return participants;
	}

	private TargetComponent createParticipant(String instanceName,
			String componentId) {
		TargetComponent participant = objectFactory.createTargetComponent();
		participant.setInstanceName(instanceName);
		participant.setComponentId(componentId);
		return participant;
	}

	private Location createLocation(String direction, int x, int y) {
		Location location = objectFactory.createLocation();
		location.setDirection(direction);
		location.setHeight(BigInteger.valueOf(-1));
		location.setWidth(BigInteger.valueOf(-1));
		location.setX(BigInteger.valueOf(x));
		location.setY(BigInteger.valueOf(y));
		return location;
	}


	private ServiceportConnector createServiceportConnector() {
		ServiceportConnector connector = objectFactory.createServiceportConnectorExt();
		connector.setName("CameraComponent_1.CapPORT_ImageViewer_1.CapPORT");
		connector.setConnectorId("9b4074b4-29b5-4a22-9956-22070915ef25");
		connector.setSourceServicePort(createTargetPort("CameraComponent_1.CapPORT", "CameraComponent_1", "RTC:Sample Vender.example.CameraComponent:1.0.0"));
		connector.setTargetServicePort(createTargetPort("ImageViewer_1.CapPORT", "ImageViewer_1", "RTC:Sample Vender.example.ImageViewer:1.0.0"));
		return connector;
	}

	private TargetPort createTargetPort(String portName, String instanceName,
			String componentId) {
		TargetPort port = objectFactory.createTargetPortExt();
		port.setPortName(portName);
		port.setInstanceName(instanceName);
		port.setComponentId(componentId);
		return port;
	}


//	private List<RepositoryViewItem> createRepositoryModel() {
//		List<RepositoryViewItem> repositoryModel = new ArrayList<RepositoryViewItem>();
//		repositoryModel.add(createEofComponentCameraComponent());
//		repositoryModel.add(createEofComponentImageProcess());
//		repositoryModel.add(createEofComponentImageViewer());
//		return repositoryModel;
//	}

//	private RepositoryViewItem createEofComponentCameraComponent() {
//		RepositoryViewLeafItem item = new RepositoryViewLeafItem("CameraComponent");
//		ComponentSpecification component = ComponentFactory.eINSTANCE.createComponentSpecification();
//		component.setCategoryL("None");
//		component.setInstanceNameL("CameraComponent");
//		component.setPathId("file://localhost/C:\\RTSystemEditor\\rsmtj\\RTC_Sample Vender.example.CameraComponent_1.0.0.xml:1");
//		component.setComponentId("RTC:Sample Vender.example.CameraComponent:1.0.0");
//		component.getPorts().add(createEofPort("ImageData", "out"));
//		component.getPorts().add(createEofPort("CapPORT", "service"));
//		item.setComponent(component);
//		return item;
//	}

//	private RepositoryViewItem createEofComponentImageProcess() {
//		RepositoryViewLeafItem item = new RepositoryViewLeafItem("ImageProcess");
//		ComponentSpecification component = ComponentFactory.eINSTANCE.createComponentSpecification();
//		component.setCategoryL("None");
//		component.setInstanceNameL("ImageProcess");
//		component.setPathId("file://localhost/C:\\RTSystemEditor\\rsmtj\\RTC_Sample Vender.example.ImageProcess_1.0.0.xml:1");
//		component.setComponentId("RTC:Sample Vender.example.ImageProcess:1.0.0");
//		component.getPorts().add(createEofPort("In", "in"));
//		component.getPorts().add(createEofPort("Out", "out"));
//		component.getPorts().add(createEofPort("CapPORT", "service"));
//		item.setComponent(component);
//		return item;
//	}

//	private RepositoryViewItem createEofComponentImageViewer() {
//		RepositoryViewLeafItem item = new RepositoryViewLeafItem("ImageViewer");
//		ComponentSpecification component = ComponentFactory.eINSTANCE.createComponentSpecification();
//		component.setCategoryL("None");
//		component.setInstanceNameL("ImageViewer");
//		component.setPathId("file://localhost/C:\\RTSystemEditor\\rsmtj\\RTC_Sample Vender.example.ImageViewer_1.0.0.xml:1");
//		component.setComponentId("RTC:Sample Vender.example.ImageViewer:1.0.0");
//		component.getPorts().add(createEofPort("ImageDataIn", "in"));
//		component.getPorts().add(createEofPort("CapPORT", "service"));
//		item.setComponent(component);
//		return item;
//	}

//	private Port createEofPort(String portName, String kind) {
//		Port port = createEofPort(kind);
//		port.setSynchronizer(ComponentFactory.eINSTANCE.createPortSynchronizer());
//		port.setNameL(portName);
//		return port;
//	}

//	private Port createEofPort(String kind) {
//		if (kind.equals("in")) return ComponentFactory.eINSTANCE.createInPort();
//		if (kind.equals("out")) return ComponentFactory.eINSTANCE.createOutPort();
//		if (kind.equals("service")) return ComponentFactory.eINSTANCE.createServicePort();
//		return null;
//	}

}
