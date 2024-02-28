package jp.go.aist.rtm.rtcbuilder.java._test._100;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;
import jp.go.aist.rtm.rtcbuilder.java.IRtcBuilderConstantsJava;
import jp.go.aist.rtm.rtcbuilder.java._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.java.manager.JavaCMakeGenerateManager;
import jp.go.aist.rtm.rtcbuilder.java.manager.JavaGenerateManager;

public class ModuleTest extends TestBase {

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

	public void testServicePortProv() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("ModuleDescription");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("VenderName");
		rtcParam.setCategory("Category");
		rtcParam.setComponentType("UNIQUE");
		rtcParam.setActivityType("EVENTDRIVEN");
		rtcParam.setMaxInstance(5);
		rtcParam.setComponentKind("DataFlowComponent");

		ServicePortParam service1 = new ServicePortParam("sv_name", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(
				service1, "if_name", "", "", rootPath
						+ "/resource/100/module/serviceM/MyService.idl",
				"SimpleService::MyService", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/module/serviceM/";

		assertEquals(default_file_num+1, result.size());
		checkCode(result, resourceDir, "src/fooComp.java");
		checkCode(result, resourceDir, "src/foo.java");
		checkCode(result, resourceDir, "src/fooImpl.java");
		checkCode(result, resourceDir, "src/MyServiceSVC_impl.java");
		//
		checkCode(result, resourceDir, "build_foo.xml");
	}

	public void testServicePortCons() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("ModuleDescription");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("VenderName");
		rtcParam.setCategory("Category");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("EVENTDRIVEN");
		rtcParam.setMaxInstance(1);
		rtcParam.setComponentKind("DataFlowComponent");

		ServicePortParam service2 = new ServicePortParam("sv_name", 0);
		List<ServicePortInterfaceParam> srvinterts2 = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(
				service2, "if_name", "", "", rootPath
						+ "/resource/100/module/serviceCon/MyService.idl",
				"SimpleService::MyService", 1);
		srvinterts2.add(int2);
		service2.getServicePortInterfaces().addAll(srvinterts2);
		List<ServicePortParam> srvports2 = new ArrayList<ServicePortParam>();
		srvports2.add(service2);
		rtcParam.getServicePorts().addAll(srvports2);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/module/serviceCon/";

		assertEquals(default_file_num+1, result.size());
		checkCode(result, resourceDir, "src/fooComp.java");
		checkCode(result, resourceDir, "src/foo.java");
		checkCode(result, resourceDir, "src/fooImpl.java");
		//
		checkCode(result, resourceDir, "build_foo.xml");
	}

	public void testServicePortProvCons() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("ModuleDescription");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("VenderName");
		rtcParam.setCategory("Category");
		rtcParam.setComponentType("UNIQUE");
		rtcParam.setActivityType("EVENTDRIVEN");
		rtcParam.setMaxInstance(5);
		rtcParam.setComponentKind("DataFlowComponent");

		ServicePortParam service1 = new ServicePortParam("sv_name", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(
				service1, "if_name", "", "", rootPath
						+ "/resource/100/module/serviceMC/MyService.idl",
				"SimpleService::MyService", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);

		ServicePortParam service2 = new ServicePortParam("sv_name2", 0);
		List<ServicePortInterfaceParam> srvinterts2 = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(
				service2, "if_name2", "", "", rootPath
						+ "/resource/100/module/serviceMC/MyService.idl",
				"SimpleService::MyService", 1);
		srvinterts2.add(int2);
		service2.getServicePortInterfaces().addAll(srvinterts2);
		List<ServicePortParam> srvports2 = new ArrayList<ServicePortParam>();
		srvports2.add(service2);
		rtcParam.getServicePorts().addAll(srvports2);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/module/serviceMC/";

		assertEquals(default_file_num+1, result.size());
		checkCode(result, resourceDir, "src/fooComp.java");
		checkCode(result, resourceDir, "src/foo.java");
		checkCode(result, resourceDir, "src/fooImpl.java");
		checkCode(result, resourceDir, "src/MyServiceSVC_impl.java");
		//
		checkCode(result, resourceDir, "build_foo.xml");
	}

}
