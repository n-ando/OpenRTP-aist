package jp.go.aist.rtm.rtcbuilder.python._test._100;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;
import jp.go.aist.rtm.rtcbuilder.python.IRtcBuilderConstantsPython;
import jp.go.aist.rtm.rtcbuilder.python._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.python.manager.PythonCMakeGenerateManager;
import jp.go.aist.rtm.rtcbuilder.python.manager.PythonGenerateManager;

public class PyModuleTest extends TestBase {

	Generator generator;
	GeneratorParam genParam;
	RtcParam rtcParam;

	protected void setUp() throws Exception {
		genParam = new GeneratorParam();
		rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "/resource/work");
		rtcParam.setLanguage(IRtcBuilderConstantsPython.LANG_PYTHON);
		rtcParam.setLanguageArg(IRtcBuilderConstantsPython.LANG_PYTHON_ARG);
		rtcParam.setRtmVersion(IRtcBuilderConstants.RTM_VERSION_100);
		rtcParam.setIsTest(true);
		genParam.setRtcParam(rtcParam);

		generator = new Generator();
		generator.addGenerateManager(new PythonGenerateManager());
		generator.addGenerateManager(new PythonCMakeGenerateManager());
	}

	public void testServicePortProv() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("ModuleDescription");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("VenderName");
		rtcParam.setCategory("Category");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("EVENTDRIVEN");
		rtcParam.setMaxInstance(1);
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setRtmVersion("1.0.0");

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

		assertEquals(default_file_num+service_file_num, result.size());
		checkCode(result, resourceDir, "foo.py");
		checkCode(result, resourceDir, "MyService_idl_example.py");
//		checkCode(result, resourceDir, "idlcompile.bat");
//		checkCode(result, resourceDir, "idlcompile.sh");
	}

	public void testServicePortProv2() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("ModuleDescription");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("VenderName");
		rtcParam.setCategory("Category");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("EVENTDRIVEN");
		rtcParam.setMaxInstance(1);
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setRtmVersion("1.0.0");

		ServicePortParam service1 = new ServicePortParam("sv_name", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(
				service1, "if_name1", "", "", rootPath
						+ "/resource/100/module/serviceM2/MyService.idl",
				"SimpleService::MyService", 0);
		srvinterts.add(int1);
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(
				service1, "if_name2", "", "", rootPath
						+ "/resource/100/module/serviceM2/DAQService.idl",
				"DAQService", 0);
		srvinterts.add(int2);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/module/serviceM2/";

		assertEquals(default_file_num+8, result.size());
		checkCode(result, resourceDir, "foo.py");
		checkCode(result, resourceDir, "MyService_idl_example.py");
		checkCode(result, resourceDir, "DAQService_idl_example.py");
//		checkCode(result, resourceDir, "idlcompile.bat");
//		checkCode(result, resourceDir, "idlcompile.sh");
	}

	public void testServicePortCon() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("ModuleDescription");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("VenderName");
		rtcParam.setCategory("Category");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("EVENTDRIVEN");
		rtcParam.setMaxInstance(1);
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setRtmVersion("1.0.0");

		ServicePortParam service1 = new ServicePortParam("sv_name", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(
				service1, "if_name", "", "", rootPath
						+ "/resource/100/module/serviceMC/MyService.idl",
				"SimpleService::MyService", 1);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/module/serviceMC/";

		assertEquals(default_file_num+7, result.size());
		checkCode(result, resourceDir, "foo.py");
	}

	public void testServicePortCon2() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("ModuleDescription");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("VenderName");
		rtcParam.setCategory("Category");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("EVENTDRIVEN");
		rtcParam.setMaxInstance(1);
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setRtmVersion("1.0.0");

		ServicePortParam service1 = new ServicePortParam("sv_name", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(
				service1, "if_name1", "", "", rootPath
						+ "/resource/100/module/serviceMC2/MyService.idl",
				"SimpleService::MyService", 1);
		srvinterts.add(int1);
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(
				service1, "if_name2", "", "", rootPath
						+ "/resource/100/module/serviceMC2/DAQService.idl",
				"DAQService", 1);
		srvinterts.add(int2);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/module/serviceMC2/";

		assertEquals(default_file_num+8, result.size());
		checkCode(result, resourceDir, "foo.py");
	}

}
