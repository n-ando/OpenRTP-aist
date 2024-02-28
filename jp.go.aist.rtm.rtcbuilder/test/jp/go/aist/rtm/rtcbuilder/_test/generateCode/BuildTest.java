package jp.go.aist.rtm.rtcbuilder._test.generateCode;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.IdlPathParam;
import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.*;

public class BuildTest extends TestBase {

	GeneratorParam genParam;
	RtcParam rtcParam;
	Generator generator;
	GenerateManager manager;

	protected void setUp() throws Exception {
		genParam = new GeneratorParam();
		rtcParam = new RtcParam(genParam, true);

		rtcParam.setOutputProject(rootPath + "/resource/work");
		rtcParam.setLanguage(LANG_CPP);
		rtcParam.setLanguageArg(LANG_CPP_ARG);
		rtcParam.setRtmVersion(DEFAULT_RTM_VERSION);
		rtcParam.setIsTest(true);
		genParam.setRtcParam(rtcParam);

		generator = new Generator();
	}

	String fixturePath(String name) {
		return rootPath + "resource/100/CXX/" + name;
	}

	public void testCMake1() throws Exception {
		String name = "build/cmake1";

		rtcParam.setName("foo");
		rtcParam.setDescription("test module");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setMaxInstance(2);

		List<DataPortParam> dataport = new ArrayList<DataPortParam>();
		dataport.add(new DataPortParam("InP1", "RTC::TimedShort", "", 0));
		dataport.add(new DataPortParam("InP2", "RTC::TimedLong", "", 0));
		rtcParam.getInports().addAll(dataport);

		List<DataPortParam> outport = new ArrayList<DataPortParam>();
		outport.add(new DataPortParam("OutP1", "RTC::TimedInt", "", 0));
		outport.add(new DataPortParam("OutP2", "RTC::TimedFloat", "", 0));
		rtcParam.getOutports().addAll(outport);

		ServicePortParam service1 = new ServicePortParam("svPort", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(
				service1, "acc", "", "", rootPath + "/resource/MyService.idl",
				"MyService", 0);
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

		String resourceDir = fixturePath(name) + "/";

		assertEquals(default_file_num+4, result.size());
		checkCode(result, resourceDir, "src/fooComp.cpp");
		checkCode(result, resourceDir, "include/foo/foo.h");
		checkCode(result, resourceDir, "src/foo.cpp");
		checkCode(result, resourceDir, "include/foo/MyServiceSVC_impl.h");
		checkCode(result, resourceDir, "src/MyServiceSVC_impl.cpp");
		//
		nonexist(result, resourceDir, "Makefile.foo");
		nonexist(result, resourceDir, "foo_vc8.sln");
		nonexist(result, resourceDir, "foo_vc8.vcproj");
		nonexist(result, resourceDir, "fooComp_vc8.vcproj");
		nonexist(result, resourceDir, "foo_vc9.sln");
		nonexist(result, resourceDir, "foo_vc9.vcproj");
		nonexist(result, resourceDir, "fooComp_vc9.vcproj");
		nonexist(result, resourceDir, "copyprops.bat");
		nonexist(result, resourceDir, "user_config.vsprops");
	}

	public void testCMake2() throws Exception {
		String name = "build/cmake2";

		rtcParam.setName("foo");
		rtcParam.setDescription("test module");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setMaxInstance(2);
		
		rtcParam.getIdlSearchPathList().add(new IdlPathParam(fixturePath(name), false));

		List<ServicePortParam> svports = new ArrayList<ServicePortParam>();

		ServicePortParam sv1 = new ServicePortParam("MyServiceProvider", 0);
		List<ServicePortInterfaceParam> iflist = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam sif1 = new ServicePortInterfaceParam(sv1,
				"MyServiceProvider", "", "", fixturePath(name)
						+ "/MyServiceChildMulti.idl", "MyServiceChild", 0);
		iflist.add(sif1);
		ServicePortInterfaceParam sif2 = new ServicePortInterfaceParam(sv1,
				"MyServiceProvider2", "", "", fixturePath(name)
						+ "/MyServiceChildWithType.idl",
				"MyServiceWithTypeChild", 0);
		iflist.add(sif2);
		sv1.getServicePortInterfaces().addAll(iflist);
		svports.add(sv1);

		ServicePortParam sv2 = new ServicePortParam("MyServiceRequire", 0);
		iflist = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam sif3 = new ServicePortInterfaceParam(sv2,
				"MyServiceRequire", "", "", fixturePath(name)
						+ "/MyServiceChildMulti.idl", "MyServiceChild", 1);
		iflist.add(sif3);
		ServicePortInterfaceParam sif4 = new ServicePortInterfaceParam(sv2,
				"MyServiceRequire2", "", "", fixturePath(name)
						+ "/MyServiceChildWithType.idl",
				"MyServiceWithTypeChild", 1);
		iflist.add(sif4);
		sv2.getServicePortInterfaces().addAll(iflist);
		svports.add(sv2);

		rtcParam.getServicePorts().addAll(svports);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = fixturePath(name) + "/";

		assertEquals(default_file_num+4, result.size());
		checkCode(result, resourceDir, "src/fooComp.cpp");
		checkCode(result, resourceDir, "include/foo/foo.h");
		checkCode(result, resourceDir, "src/foo.cpp");
		checkCode(result, resourceDir, "include/foo/MyServiceChildMultiSVC_impl.h");
		checkCode(result, resourceDir, "src/MyServiceChildMultiSVC_impl.cpp");
		checkCode(result, resourceDir, "include/foo/MyServiceChildWithTypeSVC_impl.h");
		checkCode(result, resourceDir, "src/MyServiceChildWithTypeSVC_impl.cpp");
		//
		nonexist(result, resourceDir, "Makefile.foo");
		nonexist(result, resourceDir, "foo_vc8.sln");
		nonexist(result, resourceDir, "foo_vc8.vcproj");
		nonexist(result, resourceDir, "fooComp_vc8.vcproj");
		nonexist(result, resourceDir, "foo_vc9.sln");
		nonexist(result, resourceDir, "foo_vc9.vcproj");
		nonexist(result, resourceDir, "fooComp_vc9.vcproj");
		nonexist(result, resourceDir, "copyprops.bat");
		nonexist(result, resourceDir, "user_config.vsprops");
	}

}
