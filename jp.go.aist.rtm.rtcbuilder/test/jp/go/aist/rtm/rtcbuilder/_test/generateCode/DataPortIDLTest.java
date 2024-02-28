package jp.go.aist.rtm.rtcbuilder._test.generateCode;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataTypeParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.IdlPathParam;

public class DataPortIDLTest extends TestBase {
	private RtcParam rtcParam;
	private GeneratorParam genParam;

	public void testOutPortIDL() throws Exception{
		genParam = new GeneratorParam();
		rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "/resource/work");
		rtcParam.setLanguage(IRtcBuilderConstants.LANG_CPP);
		rtcParam.setLanguageArg(IRtcBuilderConstants.LANG_CPP_ARG);
		rtcParam.setRtmVersion("1.0.0");
		rtcParam.setIsTest(true);
		genParam.setRtcParam(rtcParam);

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
		DataPortParam dport1 = new DataPortParam("InP1", "MyType::Frame1", "", 0);
		dport1.setIdlFile("TestIDL.idl");
		dataport.add(dport1);
		dataport.add(new DataPortParam("InP2", "RTC::TimedLong", "", 0));
		rtcParam.getInports().addAll(dataport);
		List<DataPortParam> outport = new ArrayList<DataPortParam>();
		outport.add(new DataPortParam("OutP1", "RTC::TimedInt", "", 0));
		rtcParam.getOutports().addAll(outport);
		//
		DataTypeParam param = new DataTypeParam();
		param.getDefinedTypes().add("MyType::Frame1");
		param.setContent("struct Frame0 {\n  double mat[3][3];\n  double pos[3];\n};");
		param.setFullPath("TestIDL.idl");
		genParam.getDataTypeParams().add(param);

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/CXX/DataPortIDL/";

		assertEquals(default_file_num, result.size());
		checkCode(result, resourceDir, "fooComp.cpp");
		checkCode(result, resourceDir, "foo.h");
		checkCode(result, resourceDir, "foo.cpp");
	}

	public void testDataPortIDL() throws Exception{
		genParam = new GeneratorParam();
		rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "/resource/work");
		rtcParam.setLanguage(IRtcBuilderConstants.LANG_CPP);
		rtcParam.setLanguageArg(IRtcBuilderConstants.LANG_CPP_ARG);
		rtcParam.setRtmVersion("1.0.0");
		rtcParam.setIsTest(true);
		genParam.setRtcParam(rtcParam);

		rtcParam.setName("MarkerPosition");
		rtcParam.setDescription("ModuleDescription");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("Mayuka_Shii");
		rtcParam.setCategory("Category");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);
		rtcParam.setComponentKind("DataFlowComponent");

		rtcParam.setActionImplemented(IRtcBuilderConstants.ACTIVITY_ACTIVATED, true);
		rtcParam.setActionImplemented(IRtcBuilderConstants.ACTIVITY_DEACTIVATED, true);
		rtcParam.setActionImplemented(IRtcBuilderConstants.ACTIVITY_EXECUTE, true);
		/////
		DataTypeParam param1 = new DataTypeParam();
		param1.setFullPath(rootPath + "/resource/100/DataPortIDL/sourceIDL/ArUco.idl");
		param1.getDefinedTypes().add("arUco::arUcoPoint2D");
		param1.setDefault(false);
		genParam.getDataTypeParams().add(param1);

		DataTypeParam param2 = new DataTypeParam();
		param2.setFullPath(rootPath + "/resource/100/DataPortIDL/sourceIDL/GameFramework.idl");
		param2.getDefinedTypes().add("GameFramework::CenterPosition");
		param2.setDefault(false);
		genParam.getDataTypeParams().add(param2);
		/////

		List<DataPortParam> dataport = new ArrayList<DataPortParam>();
		DataPortParam dport1 = new DataPortParam("arUcoPoint2D", "arUco::arUcoPoint2D", "", 0);
		dataport.add(dport1);
		rtcParam.getInports().addAll(dataport);

		List<DataPortParam> outport = new ArrayList<DataPortParam>();
		outport.add(new DataPortParam("CenterPosition", "GameFramework::CenterPosition", "", 0));
		rtcParam.getOutports().addAll(outport);

		List<IdlPathParam> idlDirs = new ArrayList<IdlPathParam>();
		idlDirs.add(new IdlPathParam(rootPath + "/resource/100/DataPortIDL/sourceIDL/", false));
		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam, idlDirs);

		String resourceDir = rootPath + "/resource/100/DataPortIDL/";

		checkCode(result, resourceDir, "src/MarkerPositionComp.cpp");
		checkCode(result, resourceDir, "src/MarkerPosition.cpp");
		checkCode(result, resourceDir, "src/CMakeLists.txt");
		checkCode(result, resourceDir, "include/CMakeLists.txt");
		checkCode(result, resourceDir, "include/MarkerPosition/MarkerPosition.h");
		checkCode(result, resourceDir, "include/MarkerPosition/CMakeLists.txt");
		checkCode(result, resourceDir, "idl/CMakeLists.txt");
	}
}
