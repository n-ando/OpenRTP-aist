package jp.go.aist.rtm.rtcbuilder._test.generateCode;

import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.DEFAULT_RTM_VERSION;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.LANG_CPP;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.LANG_CPP_ARG;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;
import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;

public class TestComp extends TestBase {
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
	
	public void testCompXXX() throws Exception {
		rtcParam.setName("XXX");
		rtcParam.setDescription("ModuleDescription");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("VenderName");
		rtcParam.setCategory("Category");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setExecutionRate(1000.0);
		rtcParam.setMaxInstance(1);
		
		rtcParam.setActionImplemented(IRtcBuilderConstants.ACTIVITY_INITIALIZE, true);
		rtcParam.setActionImplemented(IRtcBuilderConstants.ACTIVITY_ACTIVATED, true);
		rtcParam.setActionImplemented(IRtcBuilderConstants.ACTIVITY_DEACTIVATED, true);
		rtcParam.setActionImplemented(IRtcBuilderConstants.ACTIVITY_EXECUTE, true);
		
		List<DataPortParam> inport = new ArrayList<DataPortParam>();
		inport.add(new DataPortParam("in", "RTC::TimedLong", "", 0));
		rtcParam.getInports().addAll(inport);
		
		List<DataPortParam> outport = new ArrayList<DataPortParam>();
		outport.add(new DataPortParam("out", "RTC::TimedLong", "", 0));
		rtcParam.getOutports().addAll(outport);
		
		ServicePortParam service1 = new ServicePortParam("MyServide", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(
				service1, "myservice0", "", "", rootPath + "/resource/MyServiceTest.idl",
				"SimpleService::MyService", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		
		rtcParam.getServicePorts().addAll(srvports);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);
		
		String resourceDir = rootPath + "resource/100/TestComp/XXX/";

		assertEquals(default_file_num+2, result.size());
		checkCode(result, resourceDir, "CMakeLists.txt");
		checkCode(result, resourceDir, "COPYING");
		checkCode(result, resourceDir, "COPYING.LESSER");
		checkCode(result, resourceDir, "README.md");
		checkCode(result, resourceDir, "rtc.conf");
		checkCode(result, resourceDir, "XXX.conf");
		
		checkCode(result, resourceDir, "cmake/CMakeLists.txt");
		checkCode(result, resourceDir, "cmake/cpack_options.cmake.in");
		checkCode(result, resourceDir, "cmake/License.rtf");
		checkCode(result, resourceDir, "cmake/uninstall_target.cmake.in");
		checkCode(result, resourceDir, "cmake/utils.cmake");
		checkCode(result, resourceDir, "cmake/xxx-config-version.cmake.in");
		checkCode(result, resourceDir, "cmake/xxx-config.cmake.in");
		checkCode(result, resourceDir, "cmake/xxx.pc.in");
		
		checkCode(result, resourceDir, "doc/CMakeLists.txt");
		checkCode(result, resourceDir, "doc/conf.py.in");
		checkCode(result, resourceDir, "doc/doxyfile.in");
		
		checkCode(result, resourceDir, "idl/CMakeLists.txt");
		
		checkCode(result, resourceDir, "include/CMakeLists.txt");
		checkCode(result, resourceDir, "include/XXX/CMakeLists.txt");
		checkCode(result, resourceDir, "include/XXX/XXX.h");
		checkCode(result, resourceDir, "include/XXX/MyServiceTestSVC_impl.h");
		
		checkCode(result, resourceDir, "src/CMakeLists.txt");
		checkCode(result, resourceDir, "src/XXXComp.cpp");
		checkCode(result, resourceDir, "src/XXX.cpp");
		checkCode(result, resourceDir, "src/MyServiceTestSVC_impl.cpp");
		
//		checkCode(result, resourceDir, "test/CMakeLists.txt");
		
		checkCode(result, resourceDir, "test/include/CMakeLists.txt");
		checkCode(result, resourceDir, "test/include/XXXTest/CMakeLists.txt");
		checkCode(result, resourceDir, "test/include/XXXTest/XXXTest.h");
		
		checkCode(result, resourceDir, "test/src/CMakeLists.txt");
		checkCode(result, resourceDir, "test/src/XXXTestComp.cpp");
		checkCode(result, resourceDir, "test/src/XXXTest.cpp");
	}
	
	public void testCompYYY() throws Exception {
		rtcParam.setName("YYY");
		rtcParam.setDescription("ModuleDescription");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("VenderName");
		rtcParam.setCategory("Category");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setExecutionRate(1000.0);
		rtcParam.setMaxInstance(1);
		
		rtcParam.setActionImplemented(IRtcBuilderConstants.ACTIVITY_INITIALIZE, true);
		
		ServicePortParam service1 = new ServicePortParam("MyService", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(
				service1, "myservice0", "", "", rootPath + "/resource/MyServiceTest.idl",
				"SimpleService::MyService", 1);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		
		rtcParam.getServicePorts().addAll(srvports);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);
		
		String resourceDir = rootPath + "resource/100/TestComp/YYY/";

		assertEquals(default_file_num+2, result.size());
		checkCode(result, resourceDir, "CMakeLists.txt");
		checkCode(result, resourceDir, "COPYING");
		checkCode(result, resourceDir, "COPYING.LESSER");
		checkCode(result, resourceDir, "README.md");
		checkCode(result, resourceDir, "rtc.conf");
		checkCode(result, resourceDir, "YYY.conf");
		
		checkCode(result, resourceDir, "cmake/CMakeLists.txt");
		checkCode(result, resourceDir, "cmake/cpack_options.cmake.in");
		checkCode(result, resourceDir, "cmake/License.rtf");
		checkCode(result, resourceDir, "cmake/uninstall_target.cmake.in");
		checkCode(result, resourceDir, "cmake/utils.cmake");
		checkCode(result, resourceDir, "cmake/yyy-config-version.cmake.in");
		checkCode(result, resourceDir, "cmake/yyy-config.cmake.in");
		checkCode(result, resourceDir, "cmake/yyy.pc.in");
		
		checkCode(result, resourceDir, "doc/CMakeLists.txt");
		checkCode(result, resourceDir, "doc/conf.py.in");
		checkCode(result, resourceDir, "doc/doxyfile.in");
		
		checkCode(result, resourceDir, "idl/CMakeLists.txt");
		
		checkCode(result, resourceDir, "include/CMakeLists.txt");
		checkCode(result, resourceDir, "include/YYY/CMakeLists.txt");
		checkCode(result, resourceDir, "include/YYY/YYY.h");
		
		checkCode(result, resourceDir, "src/CMakeLists.txt");
		checkCode(result, resourceDir, "src/YYYComp.cpp");
		checkCode(result, resourceDir, "src/YYY.cpp");
		
//		checkCode(result, resourceDir, "test/CMakeLists.txt");
		
		checkCode(result, resourceDir, "test/include/CMakeLists.txt");
		checkCode(result, resourceDir, "test/include/YYYTest/CMakeLists.txt");
		checkCode(result, resourceDir, "test/include/YYYTest/YYYTest.h");
		checkCode(result, resourceDir, "test/include/YYYTest/MyServiceTestSVC_impl.h");
		
		checkCode(result, resourceDir, "test/src/CMakeLists.txt");
		checkCode(result, resourceDir, "test/src/YYYTestComp.cpp");
		checkCode(result, resourceDir, "test/src/YYYTest.cpp");
		checkCode(result, resourceDir, "test/src/MyServiceTestSVC_impl.cpp");
	}
}
