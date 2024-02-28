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
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.IdlPathParam;
import jp.go.aist.rtm.rtcbuilder.java.IRtcBuilderConstantsJava;
import jp.go.aist.rtm.rtcbuilder.java._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.java.manager.JavaCMakeGenerateManager;
import jp.go.aist.rtm.rtcbuilder.java.manager.JavaGenerateManager;

public class JavaIDLInheritTest extends TestBase {

	GeneratorParam genParam;
	RtcParam rtcParam;
	Generator generator;

	protected void setUp() throws Exception {
		genParam = new GeneratorParam();
		rtcParam = new RtcParam(genParam, true);

		rtcParam.setOutputProject(rootPath + "/resource/work");
		rtcParam.setLanguage(IRtcBuilderConstantsJava.LANG_JAVA);
		rtcParam.setLanguageArg(IRtcBuilderConstantsJava.LANG_JAVA_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("test module");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(2);
		rtcParam.setComponentKind("DataFlowComponent");
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

	public void testInherit1() throws Exception {
		String name = "idlinherit/inherit1";
		
		rtcParam.getIdlSearchPathList().add(new IdlPathParam(fixturePath(name), false));

		List<ServicePortParam> svports = new ArrayList<ServicePortParam>();

		ServicePortParam sv1 = new ServicePortParam("MyServiceProvider", 0);
		List<ServicePortInterfaceParam> iflist = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam sif1 = new ServicePortInterfaceParam(sv1,
				"MyServiceProvider", "", "", fixturePath(name)
						+ "/MyServiceChildMulti.idl", "MyServiceChild", 0);
		iflist.add(sif1);
		sv1.getServicePortInterfaces().addAll(iflist);
		svports.add(sv1);

		ServicePortParam sv2 = new ServicePortParam("MyServiceRequire", 0);
		iflist = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam sif2 = new ServicePortInterfaceParam(sv2,
				"MyServiceRequire", "", "", fixturePath(name)
						+ "/MyServiceChildMulti.idl", "MyServiceChild", 1);
		iflist.add(sif2);
		sv2.getServicePortInterfaces().addAll(iflist);
		svports.add(sv2);

		rtcParam.getServicePorts().addAll(svports);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = fixturePath(name) + "/";

		assertEquals(default_file_num+3, result.size());
		checkCode(result, resourceDir, "src/fooComp.java");
		checkCode(result, resourceDir, "src/foo.java");
		checkCode(result, resourceDir, "src/fooImpl.java");
		checkCode(result, resourceDir, "src/MyServiceChildSVC_impl.java");
		checkCode(result, resourceDir, "src/MyServiceSVC_impl.java");
		checkCode(result, resourceDir, "src/MyService2SVC_impl.java");
		//
//		checkCode(result, resourceDir, "build_foo.xml");
	}

	public void testInherit2() throws Exception {
		String name = "idlinherit/inherit2";

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
		checkCode(result, resourceDir, "src/MyServiceChildSVC_impl.java");
		checkCode(result, resourceDir, "src/MyServiceSVC_impl.java");
		checkCode(result, resourceDir, "src/MyService2SVC_impl.java");
		checkCode(result, resourceDir,
				"src/MyServiceWithTypeChildSVC_impl.java");
		checkCode(result, resourceDir, "src/MyServiceWithTypeSVC_impl.java");
		//
//		checkCode(result, resourceDir, "build_foo.xml");
	}

}
