package jp.go.aist.rtm.rtcbuilder.python._test._100;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataTypeParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;
import jp.go.aist.rtm.rtcbuilder.python.IRtcBuilderConstantsPython;
import jp.go.aist.rtm.rtcbuilder.python._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.python.manager.PythonCMakeGenerateManager;
import jp.go.aist.rtm.rtcbuilder.python.manager.PythonGenerateManager;
import jp.go.aist.rtm.rtcbuilder.util.RTCUtil;

public class IDLPathTest extends TestBase {

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

		rtcParam.setName("ModuleName");
		rtcParam.setDescription("ModuleDescription");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("VenderName");
		rtcParam.setCategory("Category");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setMaxInstance(1);

		genParam.setRtcParam(rtcParam);

		generator = new Generator();
		generator.addGenerateManager(new PythonGenerateManager());
		generator.addGenerateManager(new PythonCMakeGenerateManager());
	}

	public void testIDLPath() throws Exception {
		DataTypeParam param1 = new DataTypeParam();
		param1.setFullPath("C:\\Program Files\\OpenRTM-aist\\1.2.0\\rtm\\idl\\CameraCommonInterface.idl");
		param1.setDefault(true);
		genParam.getDataTypeParams().add(param1);

		DataTypeParam param2 = new DataTypeParam();
		param2.setFullPath("C:\\Program Files\\OpenRTM-aist\\1.2.0\\rtm\\idl\\InterfaceDataTypes.idl");
		param2.setDefault(true);
		genParam.getDataTypeParams().add(param2);

		DataTypeParam param3 = new DataTypeParam();
		param3.setFullPath("C:\\Program Files\\OpenRTM-aist\\1.2.0\\rtm\\idl\\BasicDataType.idl");
		param3.setDefault(true);
		genParam.getDataTypeParams().add(param3);

		DataTypeParam param4 = new DataTypeParam();
		param4.setFullPath("C:\\Program Files\\OpenRTM-aist\\1.2.0\\rtm\\idl\\ExtendedDataTypes.idl");
		param4.setDefault(true);
		genParam.getDataTypeParams().add(param4);

		ServicePortParam service1 = new ServicePortParam("sv_name", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(
				service1, "if_name", "", "",
				rootPath + "/resource/CalibrationService.idl",
				"ImageCalibService::CalibrationService",
				0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);

		RTCUtil.getIDLPathes(rtcParam);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam, rtcParam.getIdlSearchPathList());

		String resourceDir = rootPath + "/resource/Python/IDLPath/";

		assertEquals(default_file_num+service_file_num, result.size());
		checkCode(result, resourceDir, "idlcompile.bat");
		checkCode(result, resourceDir, "idlcompile.sh", "\n");
	}
}
