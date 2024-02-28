package jp.go.aist.rtm.rtcbuilder._test.generateCode;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.IdlPathParam;

public class IDLStructTest extends TestBase {

	RtcParam rtcParam;
	GeneratorParam genParam;

	protected void setUp() throws Exception {
		genParam = new GeneratorParam();
		rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "/resource/work");
		rtcParam.setLanguage(IRtcBuilderConstants.LANG_CPP);
		rtcParam.setLanguageArg(IRtcBuilderConstants.LANG_CPP_ARG);
		rtcParam.setRtmVersion("1.0.0");
		rtcParam.setIsTest(true);
		genParam.setRtcParam(rtcParam);
	}

	public void testIDLStruct() throws Exception {
		rtcParam.setName("TestModule");
		rtcParam.setDescription("ModuleDescription");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("ysuga_net");
		rtcParam.setCategory("Category");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);
		rtcParam.setComponentKind("DataFlowComponent");
		
		rtcParam.getIdlSearchPathList().add(new IdlPathParam(rootPath + "/resource/100/CXX/idlstruct/", false));

		ServicePortParam service1 = new ServicePortParam("svcPort", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(
				service1, "inf0", "inf0", "", rootPath + "/resource/100/CXX/idlstruct/module_ysuga_net.idl",
				"ysuga_net::Hoge", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);

		rtcParam.getServicePorts().addAll(srvports);

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/CXX/idlstruct/";

		assertEquals(default_file_num+2, result.size());
		checkCode(result, resourceDir, "TestModuleComp.cpp");
		checkCode(result, resourceDir, "TestModule.h");
		checkCode(result, resourceDir, "TestModule.cpp");
		checkCode(result, resourceDir, "module_ysuga_netSVC_impl.h");
		checkCode(result, resourceDir, "module_ysuga_netSVC_impl.cpp");
	}
}
