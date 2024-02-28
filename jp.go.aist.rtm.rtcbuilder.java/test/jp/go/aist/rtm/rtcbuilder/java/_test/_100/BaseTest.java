package jp.go.aist.rtm.rtcbuilder.java._test._100;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;
import jp.go.aist.rtm.rtcbuilder.java.IRtcBuilderConstantsJava;
import jp.go.aist.rtm.rtcbuilder.java._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.java.manager.JavaCMakeGenerateManager;
import jp.go.aist.rtm.rtcbuilder.java.manager.JavaGenerateManager;

public class BaseTest extends TestBase {

	Generator generator;
	GeneratorParam genParam;
	RtcParam rtcParam;

	protected void setUp() throws Exception {
		genParam = new GeneratorParam();
		rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "/resource/work");
		rtcParam.setLanguage(IRtcBuilderConstantsJava.LANG_JAVA);
		rtcParam.setLanguageArg(IRtcBuilderConstantsJava.LANG_JAVA_ARG);
		rtcParam.setRtmVersion(IRtcBuilderConstants.RTM_VERSION_100);
		rtcParam.setIsTest(true);
		genParam.setRtcParam(rtcParam);

		generator = new Generator();
		generator.addGenerateManager(new JavaGenerateManager());
		generator.addGenerateManager(new JavaCMakeGenerateManager());
	}

	public void testServicePort2() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setRtmVersion("1.0.0");
		rtcParam.setMaxInstance(5);
		rtcParam.setComponentKind("DataFlowComponent");

		List<DataPortParam> dataport = new ArrayList<DataPortParam>();
		dataport.add(new DataPortParam("InP1", "RTC::TimedShort", "", 0));
		dataport.add(new DataPortParam("InP2", "RTC::TimedLong", "", 0));
		rtcParam.getInports().addAll(dataport);
		List<DataPortParam> outport = new ArrayList<DataPortParam>();
		outport.add(new DataPortParam("OutP1", "RTC::TimedLong", "", 0));
		outport.add(new DataPortParam("OutP2", "RTC::TimedFloat", "", 0));
		rtcParam.getOutports().addAll(outport);

		ServicePortParam service1 = new ServicePortParam("svPort", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(
				service1, "acc", "", "", rootPath + "/resource/MyService.idl",
				"MyService",  0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);

		ServicePortParam service2 = new ServicePortParam("cmPort", 0);
		List<ServicePortInterfaceParam> srvinterts2 = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(
				service2, "rate", "", "",
				rootPath + "/resource/DAQService.idl", "DAQService", 1);
		srvinterts2.add(int2);
		service2.getServicePortInterfaces().addAll(srvinterts2);
		srvports.add(service2);

		rtcParam.getServicePorts().addAll(srvports);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/base/service2/";

		assertEquals(default_file_num+2, result.size());
		checkCode(result, resourceDir, "src/fooComp.java");
		checkCode(result, resourceDir, "src/foo.java");
		checkCode(result, resourceDir, "src/fooImpl.java");
		checkCode(result, resourceDir, "src/MyServiceSVC_impl.java");
		//
		checkCode(result, resourceDir, "build_foo.xml");
	}

	public void testServicePort1() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setMaxInstance(5);
		rtcParam.setComponentKind("DataFlowComponent");

		List<DataPortParam> dataport = new ArrayList<DataPortParam>();
		dataport.add(new DataPortParam("InP1", "RTC::TimedShort", "", 0));
		dataport.add(new DataPortParam("InP2", "RTC::TimedLong", "", 0));
		rtcParam.getInports().addAll(dataport);
		List<DataPortParam> outport = new ArrayList<DataPortParam>();
		outport.add(new DataPortParam("OutP1", "RTC::TimedLong", "", 0));
		outport.add(new DataPortParam("OutP2", "RTC::TimedFloat", "", 0));
		rtcParam.getOutports().addAll(outport);

		ServicePortParam service1 = new ServicePortParam("svPort", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(
				service1, "acc", "", "", rootPath + "\\resource/MyService.idl",
				"MyService", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/base/service1/";

		assertEquals(default_file_num+1, result.size());
		checkCode(result, resourceDir, "src/fooComp.java");
		checkCode(result, resourceDir, "src/foo.java");
		checkCode(result, resourceDir, "src/fooImpl.java");
		checkCode(result, resourceDir, "src/MyServiceSVC_impl.java");
		//
		checkCode(result, resourceDir, "build_foo.xml");
	}

	public void testOutPort2() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setMaxInstance(5);
		rtcParam.setComponentKind("DataFlowComponent");

		List<DataPortParam> dataport = new ArrayList<DataPortParam>();
		dataport.add(new DataPortParam("InP1", "RTC::TimedShort", "", 0));
		dataport.add(new DataPortParam("InP2", "RTC::TimedLong", "", 0));
		rtcParam.getInports().addAll(dataport);
		List<DataPortParam> outport = new ArrayList<DataPortParam>();
		outport.add(new DataPortParam("OutP1", "RTC::TimedLong", "", 0));
		outport.add(new DataPortParam("OutP2", "RTC::TimedFloat", "", 0));
		rtcParam.getOutports().addAll(outport);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/base/outport2/";

		assertEquals(default_file_num, result.size());
		checkCode(result, resourceDir, "src/fooComp.java");
		checkCode(result, resourceDir, "src/foo.java");
		checkCode(result, resourceDir, "src/fooImpl.java");
		//
		checkCode(result, resourceDir, "build_foo.xml");
	}

	public void testOutPort1() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setMaxInstance(5);

		List<DataPortParam> dataport = new ArrayList<DataPortParam>();
		dataport.add(new DataPortParam("InP1", "RTC::TimedShort", "", 0));
		dataport.add(new DataPortParam("InP2", "RTC::TimedLong", "", 0));
		rtcParam.getInports().addAll(dataport);
		List<DataPortParam> outport = new ArrayList<DataPortParam>();
		outport.add(new DataPortParam("OutP1", "RTC::TimedLong", "", 0));
		rtcParam.getOutports().addAll(outport);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/base/outport1/";

		assertEquals(default_file_num, result.size());
		checkCode(result, resourceDir, "src/fooComp.java");
		checkCode(result, resourceDir, "src/foo.java");
		checkCode(result, resourceDir, "src/fooImpl.java");
		//
		checkCode(result, resourceDir, "build_foo.xml");
	}

	public void testInPort2() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setMaxInstance(5);

		List<DataPortParam> dataport = new ArrayList<DataPortParam>();
		dataport.add(new DataPortParam("InP1", "RTC::TimedShort", "", 0));
		dataport.add(new DataPortParam("InP2", "RTC::TimedLong", "", 0));
		rtcParam.getInports().addAll(dataport);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/base/inport2/";

		assertEquals(default_file_num, result.size());
		checkCode(result, resourceDir, "src/fooComp.java");
		checkCode(result, resourceDir, "src/foo.java");
		checkCode(result, resourceDir, "src/fooImpl.java");
		//
		checkCode(result, resourceDir, "build_foo.xml");
	}

	public void testInPort() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setMaxInstance(5);

		List<DataPortParam> dataport = new ArrayList<DataPortParam>();
		dataport.add(new DataPortParam("InP1", "RTC::TimedShort", "", 0));
		rtcParam.getInports().addAll(dataport);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/base/inport1/";

		assertEquals(default_file_num, result.size());
		checkCode(result, resourceDir, "src/fooComp.java");
		checkCode(result, resourceDir, "src/foo.java");
		checkCode(result, resourceDir, "src/fooImpl.java");
		//
		checkCode(result, resourceDir, "build_foo.xml");
	}

	public void testBasic() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setMaxInstance(5);
		rtcParam.setComponentKind("DataFlowComponent");

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/base/name/";

		assertEquals(default_file_num, result.size());
		checkCode(result, resourceDir, "src/fooComp.java");
		checkCode(result, resourceDir, "src/foo.java");
		checkCode(result, resourceDir, "src/fooImpl.java");
		//
		checkCode(result, resourceDir, "build_foo.xml");
	}

}
