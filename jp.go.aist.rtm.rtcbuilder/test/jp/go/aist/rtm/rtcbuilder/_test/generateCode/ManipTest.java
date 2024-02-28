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

public class ManipTest extends TestBase {

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

	public void testArgStructModule() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("ModuleDescription");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("VenderName");
		rtcParam.setCategory("Category");
		rtcParam.setComponentType("UNIQUE");
		rtcParam.setActivityType("EVENTDRIVEN");
		rtcParam.setMaxInstance(0);
		rtcParam.setComponentKind("DataFlowComponent");

		ServicePortParam service1 = new ServicePortParam("sv_name", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "if_name", "", "", 
				rootPath + "/resource/100/CXX/idlmodule/serviceArgStruct/MyServiceModuleTypeDef.idl", "MyService", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/CXX/idlmodule/serviceArgStruct/";

		assertEquals(default_file_num+2, result.size());
		checkCode(result, resourceDir, "fooComp.cpp");
		checkCode(result, resourceDir, "foo.h");
		checkCode(result, resourceDir, "foo.cpp");
		checkCode(result, resourceDir, "MyServiceModuleTypeDefSVC_impl.h");
		checkCode(result, resourceDir, "MyServiceModuleTypeDefSVC_impl.cpp");
	}
	
	public void testIDLType() throws Exception {
		rtcParam.setName("ModuleName");
		rtcParam.setDescription("ModuleDescription");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("VenderName");
		rtcParam.setCategory("Category");
		rtcParam.setComponentType("STATIC");
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setExecutionRate(1.0);
		rtcParam.setMaxInstance(1);

		ServicePortParam service1 = new ServicePortParam("sV1",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "sIF1", "", "sIFv", 
				rootPath + "resource/100/CXX/idltype/IDLType1/TestIDL.idl", "ComFk", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		
		rtcParam.getServicePorts().addAll(srvports);

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/CXX/idltype/IDLType1/";

		assertEquals(default_file_num+2, result.size());
		checkCode(result, resourceDir, "ModuleNameComp.cpp");
		checkCode(result, resourceDir, "ModuleName.h");
		checkCode(result, resourceDir, "ModuleName.cpp");
		checkCode(result, resourceDir, "TestIDLSVC_impl.h");
		checkCode(result, resourceDir, "TestIDLSVC_impl.cpp");
	}
	
	public void testManip() throws Exception{
		rtcParam.setName("ModuleName");
		rtcParam.setDescription("ModuleDescription");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("VenderName");
		rtcParam.setCategory("Category");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);
		rtcParam.setExecutionRate(1000.0);;
		rtcParam.setComponentKind("DataFlowComponent");

		rtcParam.getIdlSearchPathList().add(new IdlPathParam(rootPath + "/resource/100/CXX/Manip", false));

		ServicePortParam service1 = new ServicePortParam("sv_name", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(
				service1, "if_name", "", "if_variable_name", rootPath + "/resource/100/CXX/Manip/ManipulatorCommonInterface_MiddleLevel.idl",
				"ManipulatorCommonInterface_Middle", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "/resource/100/CXX/Manip/";

		assertEquals(default_file_num+2, result.size());
		checkCode(result, resourceDir, "ModuleNameComp.cpp");
		checkCode(result, resourceDir, "ModuleName.h");
		checkCode(result, resourceDir, "ModuleName.cpp");
		checkCode(result, resourceDir, "ManipulatorCommonInterface_MiddleLevelSVC_impl.h");
		checkCode(result, resourceDir, "ManipulatorCommonInterface_MiddleLevelSVC_impl.cpp");
	}
}
