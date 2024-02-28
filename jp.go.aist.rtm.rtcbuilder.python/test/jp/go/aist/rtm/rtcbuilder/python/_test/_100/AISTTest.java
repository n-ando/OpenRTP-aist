package jp.go.aist.rtm.rtcbuilder.python._test._100;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.ConfigSetParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;
import jp.go.aist.rtm.rtcbuilder.python.IRtcBuilderConstantsPython;
import jp.go.aist.rtm.rtcbuilder.python._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.python.manager.PythonCMakeGenerateManager;
import jp.go.aist.rtm.rtcbuilder.python.manager.PythonGenerateManager;
import jp.go.aist.rtm.rtcbuilder.util.RTCUtil;

public class AISTTest extends TestBase {

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

	public void testAIST7() throws Exception {
		rtcParam.setName("ModuleName");
		rtcParam.setDescription("ModuleDescription");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("VenderName");
		rtcParam.setCategory("Category");
		rtcParam.setComponentType("DataFlowComponent");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);
		rtcParam.setComponentKind("DataFlowComponent");

		List<ConfigSetParam> configset = new ArrayList<ConfigSetParam>();
		ConfigSetParam param01 = new ConfigSetParam("test", "double", "", "0");
		param01.setWidget("slider");
		param01.setStep("0.2");
		param01.setConstraint("-1.0<x<1.0");
		configset.add(param01);
		rtcParam.getConfigParams().addAll(configset);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/aist/AIST7/";

		checkCode(result, resourceDir, "ModuleName.py");
	}
	
	public void testAIST6() throws Exception {
		rtcParam.setName("test");
		rtcParam.setDescription("test component");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("S.Kurihara");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);
		rtcParam.setComponentKind("DataFlowComponent");

		ServicePortParam service1 = new ServicePortParam("MySVPro0", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(
				service1, "myservice0", "", "", rootPath
						+ "resource/Python/AIST6/work/MyService.idl",
				"MyService", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);

		ServicePortParam service2 = new ServicePortParam("MySVPro1", 0);
		List<ServicePortInterfaceParam> srvinterts2 = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(
				service1, "myservice", "", "", rootPath
						+ "resource/Python/AIST6/work/MyServiceAIST.idl",
				"MyService2", 0);
		srvinterts2.add(int2);
		service2.getServicePortInterfaces().addAll(srvinterts2);
		srvports.add(service2);
		rtcParam.getServicePorts().addAll(srvports);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);
		//
		rtcParam.setOutputProject(rootPath + "/resource/work");
		rtcParam.getServicePorts().clear();
		result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/aist/AIST6/";

		assertEquals(default_file_num, result.size());
		checkCode(result, resourceDir, "test.py");
	}

	public void testAIST4() throws Exception {
		rtcParam.setName("test");
		rtcParam.setDescription("test component");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("S.Kurihara");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);
		rtcParam.setComponentKind("DataFlowComponent");

		ServicePortParam service1 = new ServicePortParam("MySVPro0", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(
				service1, "myservice0", "", "", rootPath
						+ "resource/Python/AIST4/MyService.idl", "MyService", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);

		ServicePortParam service2 = new ServicePortParam("MySVPro1", 0);
		List<ServicePortInterfaceParam> srvinterts2 = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(
				service1, "myservice", "", "", rootPath
						+ "resource/Python/AIST4/MyService.idl", "MyService", 0);
		srvinterts2.add(int2);
		service2.getServicePortInterfaces().addAll(srvinterts2);
		srvports.add(service2);
		rtcParam.getServicePorts().addAll(srvports);

		RTCUtil.getIDLPathes(rtcParam);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam, rtcParam.getIdlSearchPathList());

		String resourceDir = rootPath + "/resource/100/aist/AIST4/";

		assertEquals(default_file_num+7, result.size());
		checkCode(result, resourceDir, "test.py");
		checkCode(result, resourceDir, "MyService_idl_example.py");
		checkCode(result, resourceDir, "idlcompile.bat");
		checkCode(result, resourceDir, "idlcompile.sh", "\n");
	}

	public void testAIST3() throws Exception {
		rtcParam.setName("test");
		rtcParam.setDescription("test component");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("S.Kurihara");
		rtcParam.setCategory("example");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);
		rtcParam.setComponentKind("DataFlowComponent");

		List<DataPortParam> dataport = new ArrayList<DataPortParam>();
		dataport.add(new DataPortParam("in", "RTC::TimedFloatSeq", "", 0));
		rtcParam.getInports().addAll(dataport);

		List<DataPortParam> dataoutport = new ArrayList<DataPortParam>();
		dataoutport.add(new DataPortParam("out", "RTC::TimedFloatSeq", "", 0));
		rtcParam.getOutports().addAll(dataoutport);

		ServicePortParam service1 = new ServicePortParam("MySVPro", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(
				service1, "myservice0", "", "", rootPath
						+ "resource/Python/AIST3/MyService.idl", "MyService", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);

		ServicePortParam service2 = new ServicePortParam("MySVCon", 0);
		List<ServicePortInterfaceParam> srvinterts2 = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(
				service1, "myservice1", "", "", rootPath
						+ "resource/Python/AIST3/MyService.idl", "MyService", 1);
		srvinterts2.add(int2);
		service2.getServicePortInterfaces().addAll(srvinterts2);
		srvports.add(service2);
		rtcParam.getServicePorts().addAll(srvports);

		RTCUtil.getIDLPathes(rtcParam);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam, rtcParam.getIdlSearchPathList());

		String resourceDir = rootPath + "/resource/100/aist/AIST3/";

		assertEquals(default_file_num+service_file_num, result.size());
		checkCode(result, resourceDir, "test.py");
		checkCode(result, resourceDir, "MyService_idl_example.py");
		checkCode(result, resourceDir, "idlcompile.bat");
		checkCode(result, resourceDir, "idlcompile.sh", "\n");
	}

	public void testAIST2() throws Exception {
		rtcParam.setName("test");
		rtcParam.setDescription("test component");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("S.Kurihara");
		rtcParam.setCategory("example");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);
		rtcParam.setComponentKind("DataFlowComponent");

		List<DataPortParam> dataport = new ArrayList<DataPortParam>();
		dataport.add(new DataPortParam("in", "RTC::TimedFloatSeq", "", 0));
		rtcParam.getInports().addAll(dataport);

		List<DataPortParam> dataoutport = new ArrayList<DataPortParam>();
		dataoutport.add(new DataPortParam("out", "RTC::TimedFloatSeq", "", 0));
		rtcParam.getOutports().addAll(dataoutport);

		ServicePortParam service1 = new ServicePortParam("MySVCon", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(
				service1, "myservice", "", "", rootPath
						+ "resource/Python/AIST2/MyService.idl", "MyService", 1);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);

		RTCUtil.getIDLPathes(rtcParam);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam, rtcParam.getIdlSearchPathList());

		String resourceDir = rootPath + "/resource/100/aist/AIST2/";

		assertEquals(default_file_num+7, result.size());
		checkCode(result, resourceDir, "test.py");
		checkCode(result, resourceDir, "idlcompile.bat");
		checkCode(result, resourceDir, "idlcompile.sh", "\n");
	}

	public void testAIST1() throws Exception {
		rtcParam.setName("test");
		rtcParam.setDescription("test component");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("S.Kurihara");
		rtcParam.setCategory("exmple");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);
		rtcParam.setComponentKind("DataFlowComponent");

		ServicePortParam service1 = new ServicePortParam("MySVPro", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(
				service1, "myservice", "", "", rootPath
						+ "resource/Python/AIST1/MyService.idl", "MyService", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);
		
		RTCUtil.getIDLPathes(rtcParam);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam, rtcParam.getIdlSearchPathList());

		String resourceDir = rootPath + "/resource/100/aist/AIST1/";

		assertEquals(default_file_num+service_file_num, result.size());
		checkCode(result, resourceDir, "test.py");
		checkCode(result, resourceDir, "MyService_idl_example.py");
		checkCode(result, resourceDir, "idlcompile.bat");
		checkCode(result, resourceDir, "idlcompile.sh", "\n");
	}

}
