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
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.IdlPathParam;
import jp.go.aist.rtm.rtcbuilder.java.IRtcBuilderConstantsJava;
import jp.go.aist.rtm.rtcbuilder.java._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.java.manager.JavaCMakeGenerateManager;
import jp.go.aist.rtm.rtcbuilder.java.manager.JavaGenerateManager;

public class BuildTest extends TestBase {

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

	String fixturePath(String name) {
		return rootPath + "resource/100/" + name;
	}

	public void testCMake1() throws Exception {
		String name = "build/cmake1";

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

		assertEquals(default_file_num+2, result.size());
		checkCode(result, resourceDir, "src/fooComp.java");
		checkCode(result, resourceDir, "src/foo.java");
		checkCode(result, resourceDir, "src/fooImpl.java");
		checkCode(result, resourceDir, "src/MyServiceSVC_impl.java");
		checkCode(result, resourceDir, "foo.sh", "\n");
		checkCode(result, resourceDir, "foo.bat");
		//
		checkCode(result, resourceDir, "build_foo.xml");
	}

	public void testCMake2() throws Exception {
		String name = "build/cmake2";

		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setMaxInstance(5);
		rtcParam.setComponentKind("DataFlowComponent");
		
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

		assertEquals(default_file_num+5, result.size());
		checkCode(result, resourceDir, "src/fooComp.java");
		checkCode(result, resourceDir, "src/foo.java");
		checkCode(result, resourceDir, "src/fooImpl.java");
		checkCode(result, resourceDir, "src/MyServiceSVC_impl.java");
		checkCode(result, resourceDir, "foo.sh", "\n");
		checkCode(result, resourceDir, "foo.bat");
		//
//		checkCode(result, resourceDir, "build_foo.xml");
	}

}
