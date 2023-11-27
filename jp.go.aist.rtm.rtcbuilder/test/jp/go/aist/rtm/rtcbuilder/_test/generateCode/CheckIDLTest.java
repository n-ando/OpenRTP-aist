package jp.go.aist.rtm.rtcbuilder._test.generateCode;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.HeaderException;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.IdlPathParam;
import jp.go.aist.rtm.rtcbuilder.util.RTCUtil;

public class CheckIDLTest extends TestBase {

	RtcParam rtcParam;
	GeneratorParam genParam;

	protected void setUp() throws Exception {
		genParam = new GeneratorParam();
		rtcParam = new RtcParam(genParam, true);

		rtcParam.setName("Sample");
		rtcParam.setDescription("Sample Comp");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("VenderName");
		rtcParam.setCategory("Category");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);
		rtcParam.setComponentKind("DataFlowComponent");

		rtcParam.setOutputProject(rootPath + "/resource/work");
		rtcParam.setLanguage(IRtcBuilderConstants.LANG_CPP);
		rtcParam.setLanguageArg(IRtcBuilderConstants.LANG_CPP_ARG);
		rtcParam.setRtmVersion("1.0.0");
		rtcParam.setIsTest(true);
		genParam.setRtcParam(rtcParam);
	}

	public void testServicePort01() throws Exception {
		ServicePortParam service1 = new ServicePortParam("svPort", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(
				service1, "acc", "", "", rootPath + "/resource/TestXXX.idl",
				"MyModule::MyInterface", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);

		List<IdlPathParam> idlDirs = new ArrayList<IdlPathParam>();
		Generator generator = new Generator();
		try {
			generator.validateIDLDef(genParam, idlDirs);
			assertTrue(false);
		} catch (FileNotFoundException ex) {
			assertTrue(ex.getMessage().startsWith("Target IDL File"));
			assertTrue(ex.getMessage().endsWith("NOT EXists."));
		}
	}

	public void testServicePort02() throws Exception {
		ServicePortParam service1 = new ServicePortParam("svPort", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(
				service1, "acc", "", "", rootPath + "/resource/Test.idl",
				"MyModule::MyInterface", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);

		List<IdlPathParam> idlDirs = new ArrayList<IdlPathParam>();
		Generator generator = new Generator();
		try {
			generator.validateIDLDef(genParam, idlDirs);
			assertTrue(false);
		} catch (HeaderException ex) {
		}
	}

	public void testServicePort03() throws Exception {
		ServicePortParam service1 = new ServicePortParam("svPort", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(
				service1, "acc", "", "", rootPath + "/resource/Test.idl",
				"MyModule::MyInterface", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);

		List<IdlPathParam> idlDirs = new ArrayList<IdlPathParam>();
		Generator generator = new Generator();
		RTCUtil.getIDLPathes(rtcParam);
		
		try {
			generator.validateIDLDef(genParam, idlDirs);
			assertTrue(false);
		} catch (HeaderException ex) {
		}
	}

	public void testServicePort04() throws Exception {
		ServicePortParam service1 = new ServicePortParam("svPort", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(
				service1, "acc", "", "", rootPath + "/resource/Test2.idl",
				"MyModule::MyInterface", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);

		List<IdlPathParam> idlDirs = new ArrayList<IdlPathParam>();
		Generator generator = new Generator();
		try {
			generator.validateIDLDef(genParam, idlDirs);
			assertTrue(false);
		} catch (HeaderException ex) {
		}
	}

	public void testServicePort05() throws Exception {
		ServicePortParam service1 = new ServicePortParam("svPort", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(
				service1, "acc", "", "", rootPath + "/resource/Test2.idl",
				"MyModule::MyInterface", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);

		List<IdlPathParam> idlDirs = new ArrayList<IdlPathParam>();
		idlDirs.add(new IdlPathParam("C:/Program Files/OpenRTM-aist/1.2.0/rtm/idl",true));
		Generator generator = new Generator();
		try {
			generator.validateIDLDef(genParam, idlDirs);
			assertTrue(false);
		} catch (HeaderException ex) {
		}
	}

	public void testGenerate01() throws Exception {
		ServicePortParam service1 = new ServicePortParam("Sample", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(
				service1, "SampleIF", "", "", rootPath + "/resource/Test.idl",
				"MyModule::MyInterface", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);

		rtcParam.setActionImplemented(0, true);
		rtcParam.setActionImplemented(4, true);
		rtcParam.setActionImplemented(5, true);
		rtcParam.setActionImplemented(9, true);
		
		RTCUtil.getIDLPathes(rtcParam);

		List<IdlPathParam> idlDirs = new ArrayList<IdlPathParam>();
		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam, idlDirs);

		String resourceDir = rootPath + "/resource/100/CXX/CheckIDL/";

		checkCode(result, resourceDir, "Sample.cpp");
		checkCode(result, resourceDir, "Sample.h");
		checkCode(result, resourceDir, "SampleComp.cpp");
		checkCode(result, resourceDir, "TestSVC_impl.h");
		checkCode(result, resourceDir, "TestSVC_impl.cpp");
	}

	public void testGenerate02() throws Exception {
		ServicePortParam service1 = new ServicePortParam("Sample", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(
				service1, "SampleIF", "", "", rootPath + "/resource/Test.idl",
				"MyModule::MyInterface", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);

		rtcParam.setActionImplemented(0, true);
		rtcParam.setActionImplemented(4, true);
		rtcParam.setActionImplemented(5, true);
		rtcParam.setActionImplemented(9, true);

		List<IdlPathParam> idlDirs = new ArrayList<IdlPathParam>();
		idlDirs.add(new IdlPathParam("C:/Program Files/OpenRTM-aist/1.2.0/rtm/idl", true));
		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam, idlDirs);

		String resourceDir = rootPath + "/resource/100/CXX/CheckIDLDef/";

		checkCode(result, resourceDir, "Sample.cpp");
		checkCode(result, resourceDir, "Sample.h");
		checkCode(result, resourceDir, "SampleComp.cpp");
		checkCode(result, resourceDir, "TestSVC_impl.h");
		checkCode(result, resourceDir, "TestSVC_impl.cpp");
	}
}
