package jp.go.aist.rtm.rtcbuilder._test.generateCode;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.ConfigSetParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;

public class PrefixSuffixTest extends TestBase {

	private RtcParam rtcParam;
	private GeneratorParam genParam;

	protected void setUp() throws Exception {
		genParam = new GeneratorParam();
		rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "/resource/work");
		rtcParam.setLanguage(IRtcBuilderConstants.LANG_CPP);
		rtcParam.setLanguageArg(IRtcBuilderConstants.LANG_CPP_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setMaxInstance(5);
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setRtmVersion("1.0.0");
		rtcParam.setIsTest(true);
		genParam.setRtcParam(rtcParam);
		//
		List<DataPortParam> dataport = new ArrayList<DataPortParam>();
		dataport.add(new DataPortParam("InP1", "RTC::TimedShort", "", 0));
		dataport.add(new DataPortParam("InP2", "RTC::TimedLong", "", 0));
		rtcParam.getInports().addAll(dataport);

		List<DataPortParam> outport = new ArrayList<DataPortParam>();
		outport.add(new DataPortParam("OutP1", "RTC::TimedOctet", "", 0));
		outport.add(new DataPortParam("OutP2", "RTC::TimedFloat", "", 0));
		rtcParam.getOutports().addAll(outport);

		////
		ServicePortParam service1 = new ServicePortParam("svPort",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "acc", "", "", 
				rootPath + "/resource/MyService.idl", "MyService", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);

		ServicePortParam service2 = new ServicePortParam("cmPort",0);
		List<ServicePortInterfaceParam> srvinterts2 = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(service2, "rate", "", "", 
				rootPath + "/resource/DAQService.idl", "DAQService", 1);
		srvinterts2.add(int2);
		service2.getServicePortInterfaces().addAll(srvinterts2);
		srvports.add(service2);

		rtcParam.getServicePorts().addAll(srvports);
		//
		List<ConfigSetParam> configset = new ArrayList<ConfigSetParam>();
		configset.add(new ConfigSetParam("int_param0", "int", "", "0"));
		rtcParam.getConfigParams().addAll(configset);
	}

	public void testSuffixConf() throws Exception {
		rtcParam.setCommonPrefix("p_");
		rtcParam.setCommonSuffix("_s");
		rtcParam.setDataPortPrefix("dt");
		rtcParam.setDataPortSuffix("ds");
		rtcParam.setServicePortPrefix("sp");
		rtcParam.setServicePortSuffix("ss");
		rtcParam.setServiceIFPrefix("sip");
		rtcParam.setServiceIFSuffix("sis");
		rtcParam.setConfigurationPrefix("cp");
		rtcParam.setConfigurationSuffix("cs");

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "/resource/100/CXX/PrefixSuffix/confsuffix/";
		checkResults(result, resourceDir);
	}

	public void testPrefixConf() throws Exception {
		rtcParam.setCommonPrefix("p_");
		rtcParam.setCommonSuffix("_s");
		rtcParam.setDataPortPrefix("dt");
		rtcParam.setDataPortSuffix("ds");
		rtcParam.setServicePortPrefix("sp");
		rtcParam.setServicePortSuffix("ss");
		rtcParam.setServiceIFPrefix("sip");
		rtcParam.setServiceIFSuffix("sis");
		rtcParam.setConfigurationPrefix("cp");

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "/resource/100/CXX/PrefixSuffix/confprefix/";
		checkResults(result, resourceDir);
	}

	public void testSuffixIF() throws Exception {
		rtcParam.setCommonPrefix("p_");
		rtcParam.setCommonSuffix("_s");
		rtcParam.setDataPortPrefix("dt");
		rtcParam.setDataPortSuffix("ds");
		rtcParam.setServicePortPrefix("sp");
		rtcParam.setServicePortSuffix("ss");
		rtcParam.setServiceIFPrefix("sip");
		rtcParam.setServiceIFSuffix("sis");

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "/resource/100/CXX/PrefixSuffix/sisuffix/";
		checkResults(result, resourceDir);
	}

	public void testPrefixIF() throws Exception {
		rtcParam.setCommonPrefix("p_");
		rtcParam.setCommonSuffix("_s");
		rtcParam.setDataPortPrefix("dt");
		rtcParam.setDataPortSuffix("ds");
		rtcParam.setServicePortPrefix("sp");
		rtcParam.setServicePortSuffix("ss");
		rtcParam.setServiceIFPrefix("sip");

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "/resource/100/CXX/PrefixSuffix/siprefix/";
		checkResults(result, resourceDir);
	}

	public void testSuffixService() throws Exception {
		rtcParam.setCommonPrefix("p_");
		rtcParam.setCommonSuffix("_s");
		rtcParam.setDataPortPrefix("dt");
		rtcParam.setDataPortSuffix("ds");
		rtcParam.setServicePortPrefix("sp");
		rtcParam.setServicePortSuffix("ss");

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "/resource/100/CXX/PrefixSuffix/svsuffix/";
		checkResults(result, resourceDir);
	}
	
	public void testPrefixService() throws Exception {
		rtcParam.setCommonPrefix("p_");
		rtcParam.setCommonSuffix("_s");
		rtcParam.setDataPortPrefix("dt");
		rtcParam.setDataPortSuffix("ds");
		rtcParam.setServicePortPrefix("sp");

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "/resource/100/CXX/PrefixSuffix/svprefix/";
		checkResults(result, resourceDir);
	}
	
	public void testSuffixData() throws Exception {
		rtcParam.setCommonPrefix("p_");
		rtcParam.setCommonSuffix("_s");
		rtcParam.setDataPortPrefix("dt");
		rtcParam.setDataPortSuffix("ds");

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "/resource/100/CXX/PrefixSuffix/dtsuffix/";
		checkResults(result, resourceDir);
	}
	
	public void testPrefixData() throws Exception{
		rtcParam.setCommonPrefix("p_");
		rtcParam.setCommonSuffix("_s");
		rtcParam.setDataPortPrefix("dt");

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "/resource/100/CXX/PrefixSuffix/dtprefix/";
		checkResults(result, resourceDir);
	}
	
	public void testSuffixCommon() throws Exception{
		rtcParam.setCommonPrefix("p_");
		rtcParam.setCommonSuffix("_s");

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "/resource/100/CXX/PrefixSuffix/suffix/";
		checkResults(result, resourceDir);
	}

	public void testPrefixCommon() throws Exception{
		rtcParam.setCommonPrefix("p_");

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "/resource/100/CXX/PrefixSuffix/prefix/";
		checkResults(result, resourceDir);
	}

	private void checkResults(List<GeneratedResult> result, String resourceDir) {
		assertEquals(default_file_num+4, result.size());
		checkCode(result, resourceDir, "fooComp.cpp");
		checkCode(result, resourceDir, "foo.h");
		checkCode(result, resourceDir, "foo.cpp");
		checkCode(result, resourceDir, "MyServiceSVC_impl.h");
		checkCode(result, resourceDir, "MyServiceSVC_impl.cpp");
	}
}
