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

public class JavaIDLTypeTest extends TestBase {

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

	public void testType() throws Exception {
		rtcParam.setName("test");
		rtcParam.setDescription("test component");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("S.Kurihara");
		rtcParam.setCategory("exmple");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);

		ServicePortParam service1 = new ServicePortParam("sV1", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(
				service1, "sIF1", "", "sIFv", rootPath
						+ "resource/100/idltype/type1/TestIDL.idl", "ComFk", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/idltype/type1/";

		checkCode(result, resourceDir, "src/testComp.java");
		checkCode(result, resourceDir, "src/test.java");
		checkCode(result, resourceDir, "src/testImpl.java");
		checkCode(result, resourceDir, "src/ComFkSVC_impl.java");
	}

	public void testBasicType() throws Exception {
		rtcParam.setName("test");
		rtcParam.setDescription("test component");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("S.Kurihara");
		rtcParam.setCategory("exmple");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);

		ServicePortParam service1 = new ServicePortParam("MySVPro", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(
				service1, "myservice", "", "", rootPath
						+ "resource/100/idltype/type2/MyService.idl",
				"MyService", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/idltype/type2/";

		checkCode(result, resourceDir, "src/testComp.java");
		checkCode(result, resourceDir, "src/test.java");
		checkCode(result, resourceDir, "src/testImpl.java");
		checkCode(result, resourceDir, "src/MyServiceSVC_impl.java");
	}

}
