package jp.go.aist.rtm.rtcbuilder._test.generateCode;

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

public class MultiTest extends TestBase {

	GeneratorParam genParam;
	RtcParam rtcParam;

	protected void setUp() throws Exception {
		genParam = new GeneratorParam();
		rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "/resource/work");
		rtcParam.setLanguage(IRtcBuilderConstants.LANG_CPP);
		rtcParam.setLanguageArg(IRtcBuilderConstants.LANG_CPP_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("test module");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setMaxInstance(2);
		rtcParam.setRtmVersion("1.0.0");
		rtcParam.setIsTest(true);

		genParam.setRtcParam(rtcParam);

		List<DataPortParam> dataInport = new ArrayList<DataPortParam>();
		dataInport.add(new DataPortParam("in1", "TimedShort", "", 0));
		rtcParam.getInports().addAll(dataInport);

		List<DataPortParam> dataOutport = new ArrayList<DataPortParam>();
		dataOutport.add(new DataPortParam("out1", "TimedLong", "", 0));
		rtcParam.getOutports().addAll(dataOutport);
	}

	public void testProConMulti() throws Exception {
		ServicePortParam service1 = new ServicePortParam("MySVPro", 0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myserviceP1", "", "", 
				rootPath + "resource/100/CXX/Multi/ProConMulti/MyService.idl", "MyService", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);

		ServicePortParam service2 = new ServicePortParam("MySVPro2",0);
		List<ServicePortInterfaceParam> srvinterts2 = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(service1, "myserviceP2", "", "", 
				rootPath + "resource/100/CXX/Multi/ProConMulti/MyService2.idl", "MyService2", 0);
		srvinterts2.add(int2);
		service2.getServicePortInterfaces().addAll(srvinterts2);
		srvports.add(service2);

		ServicePortParam service3 = new ServicePortParam("MyConPro",0);
		List<ServicePortInterfaceParam> srvintert3 = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int3 = new ServicePortInterfaceParam(service1, "myservice0", "", "", 
				rootPath + "resource/100/CXX/Multi/ProConMulti/MyService.idl", "MyService", 1);
		srvintert3.add(int3);
		service3.getServicePortInterfaces().addAll(srvintert3);
		srvports.add(service3);

		ServicePortParam service4 = new ServicePortParam("MyConPro2",0);
		List<ServicePortInterfaceParam> srvinterts4 = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int4 = new ServicePortInterfaceParam(service1, "myservice2", "", "", 
				rootPath + "resource/100/CXX/Multi/ProConMulti/DAQService.idl", "DAQService", 1);
		srvinterts4.add(int4);
		service4.getServicePortInterfaces().addAll(srvinterts4);
		srvports.add(service4);
		rtcParam.getServicePorts().addAll(srvports);

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/CXX/Multi/ProConMulti/";

		assertEquals(default_file_num+6, result.size());
		checkResults(result, resourceDir);
		checkCode(result, resourceDir, "MyServiceSVC_impl.h");
		checkCode(result, resourceDir, "MyServiceSVC_impl.cpp");
		checkCode(result, resourceDir, "MyService2SVC_impl.h");
		checkCode(result, resourceDir, "MyService2SVC_impl.cpp");
	}

	public void testConsumerMulti() throws Exception{
		ServicePortParam service1 = new ServicePortParam("MyConPro",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice0", "", "", 
				rootPath + "resource/100/CXX/Multi/ConMulti/MyService.idl", "MyService", 1);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);

		ServicePortParam service2 = new ServicePortParam("MyConPro2",0);
		List<ServicePortInterfaceParam> srvinterts2 = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(service1, "myservice2", "", "", 
				rootPath + "resource/100/CXX/Multi/ConMulti/DAQService.idl", "DAQService", 1);
		srvinterts2.add(int2);
		service2.getServicePortInterfaces().addAll(srvinterts2);
		srvports.add(service2);
		rtcParam.getServicePorts().addAll(srvports);
		
		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/CXX/Multi/ConMulti/";

		assertEquals(default_file_num + 4, result.size());
		checkResults(result, resourceDir);
		//Test Code
//		nonexist(result, resourceDir, "MyServiceSVC_impl.h");
//		nonexist(result, resourceDir, "MyServiceSVC_impl.cpp");
//		nonexist(result, resourceDir, "DAQServiceSVC_impl.h");
//		nonexist(result, resourceDir, "DAQServiceSVC_impl.cpp");
	}

	public void testProviderMulti() throws Exception{
		ServicePortParam service1 = new ServicePortParam("MySVPro",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice0", "", "", 
				rootPath + "resource/100/CXX/Multi/ProMulti/MyService.idl", "MyService", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);

		ServicePortParam service2 = new ServicePortParam("MySVPro2",0);
		List<ServicePortInterfaceParam> srvinterts2 = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(service1, "myservice2", "", "", 
				rootPath + "resource/100/CXX/Multi/ProMulti/DAQService.idl", "DAQService", 0);
		srvinterts2.add(int2);
		service2.getServicePortInterfaces().addAll(srvinterts2);
		srvports.add(service2);
		rtcParam.getServicePorts().addAll(srvports);

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/CXX/Multi/ProMulti/";

		assertEquals(default_file_num+4, result.size());
		checkResults(result, resourceDir);
		checkCode(result, resourceDir, "MyServiceSVC_impl.h");
		checkCode(result, resourceDir, "MyServiceSVC_impl.cpp");
		checkCode(result, resourceDir, "DAQServiceSVC_impl.h");
		checkCode(result, resourceDir, "DAQServiceSVC_impl.cpp");
	}

	private void checkResults(List<GeneratedResult> result, String resourceDir) {
		checkCode(result, resourceDir, "fooComp.cpp");
		checkCode(result, resourceDir, "foo.h");
		checkCode(result, resourceDir, "foo.cpp");
	}

}
