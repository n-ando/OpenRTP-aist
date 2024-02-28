package jp.go.aist.rtm.rtcbuilder._test.generateCode;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;

public class LuckTest extends TestBase {

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

	public void testConsumerNoType() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("test module");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(2);
		
		List<DataPortParam> dataInport = new ArrayList<DataPortParam>();
		dataInport.add(new DataPortParam("in1", "RTC::TimedShort", "", 0));
		rtcParam.getInports().addAll(dataInport);

		List<DataPortParam> dataOutport = new ArrayList<DataPortParam>(); 
		dataOutport.add(new DataPortParam("out1", "RTC::TimedLong", "", 0));
		rtcParam.getOutports().addAll(dataOutport);

		ServicePortParam service1 = new ServicePortParam("MySVPro",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice0", "", "", 
				rootPath + "/resource/CXX/Exception/MyService.idl", "MyService", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		
		ServicePortParam service2 = new ServicePortParam("MyConPro",0);
		List<ServicePortInterfaceParam> srvinterts2 = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(service1, "myservice1", "", "", 
				rootPath + "/resource/CXX/Exception/MyService.idl", "", 1);
		srvinterts2.add(int2);
		service2.getServicePortInterfaces().addAll(srvinterts2);
		srvports.add(service2);

		rtcParam.getServicePorts().addAll(srvports);

		Generator generator = new Generator();
		try {
			generator.generateTemplateCode(genParam);
			fail();
		} catch (Exception ex) {
		}
	}

	public void testConsumerNoName() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("test module");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(2);
		
		List<DataPortParam> dataInport = new ArrayList<DataPortParam>();
		dataInport.add(new DataPortParam("in1", "RTC::TimedShort", "", 0));
		rtcParam.getInports().addAll(dataInport);

		List<DataPortParam> dataOutport = new ArrayList<DataPortParam>(); 
		dataOutport.add(new DataPortParam("out1", "RTC::TimedLong", "", 0));
		rtcParam.getOutports().addAll(dataOutport);

		ServicePortParam service1 = new ServicePortParam("MySVPro",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice0", "", "", 
				rootPath + "/resource/CXX/Exception/MyService.idl", "MyService", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		
		ServicePortParam service2 = new ServicePortParam("MyConPro",0);
		List<ServicePortInterfaceParam> srvinterts2 = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(service1, "", "", "", 
				rootPath + "/resource/CXX/Exception/MyService.idl", "MyService", 1);
		srvinterts2.add(int2);
		service2.getServicePortInterfaces().addAll(srvinterts2);
		srvports.add(service2);

		rtcParam.getServicePorts().addAll(srvports);

		Generator generator = new Generator();
		try {
			generator.generateTemplateCode(genParam);
			fail();
		} catch (Exception e) {
		}
	}

	public void testConsumerNoPortName() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("test module");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(2);
		
		List<DataPortParam> dataInport = new ArrayList<DataPortParam>();
		dataInport.add(new DataPortParam("in1", "RTC::TimedShort", "", 0));
		rtcParam.getInports().addAll(dataInport);

		List<DataPortParam> dataOutport = new ArrayList<DataPortParam>(); 
		dataOutport.add(new DataPortParam("out1", "RTC::TimedLong", "", 0));
		rtcParam.getOutports().addAll(dataOutport);

		ServicePortParam service1 = new ServicePortParam("MySVPro",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice0", "", "", 
				rootPath + "/resource/CXX/Exception/MyService.idl", "MyService", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);

		ServicePortParam service2 = new ServicePortParam("",0);
		List<ServicePortInterfaceParam> srvinterts2 = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(service1, "myservice1", "", "", 
				rootPath + "/resource/CXX/Exception/MyService.idl", "MyService", 1);
		srvinterts2.add(int2);
		service2.getServicePortInterfaces().addAll(srvinterts2);
		srvports.add(service2);

		rtcParam.getServicePorts().addAll(srvports);

		Generator generator = new Generator();
		try {
			generator.generateTemplateCode(genParam);
			fail();
		} catch (Exception e) {
		}
	}

	public void testProviderNoType() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("test module");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(2);

		List<DataPortParam> dataInport = new ArrayList<DataPortParam>();
		dataInport.add(new DataPortParam("in1", "RTC::TimedShort", "", 0));
		rtcParam.getInports().addAll(dataInport);

		List<DataPortParam> dataOutport = new ArrayList<DataPortParam>(); 
		dataOutport.add(new DataPortParam("out1", "RTC::TimedLong", "", 0));
		rtcParam.getOutports().addAll(dataOutport);

		ServicePortParam service1 = new ServicePortParam("MySVPro",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice0", "", "", 
				rootPath + "/resource/CXX/Exception/MyService.idl", "", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);

		Generator generator = new Generator();
		try {
			generator.generateTemplateCode(genParam);
			fail();
		} catch (Exception ex) {
		}
	}

	public void testProviderNoName() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("test module");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(2);

		List<DataPortParam> dataInport = new ArrayList<DataPortParam>();
		dataInport.add(new DataPortParam("in1", "RTC::TimedShort", "", 0));
		rtcParam.getInports().addAll(dataInport);

		List<DataPortParam> dataOutport = new ArrayList<DataPortParam>(); 
		dataOutport.add(new DataPortParam("out1", "RTC::TimedLong", "", 0));
		rtcParam.getOutports().addAll(dataOutport);

		ServicePortParam service1 = new ServicePortParam("MySVPro",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "", "", "", 
				rootPath + "/resource/CXX/Exception/MyService.idl", "MyService", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);

		Generator generator = new Generator();
		try {
			generator.generateTemplateCode(genParam);
			fail();
		} catch (Exception e) {
		}
	}

	public void testProviderNoPortName() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("test module");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(2);

		List<DataPortParam> dataInport = new ArrayList<DataPortParam>();
		dataInport.add(new DataPortParam("in1", "RTC::TimedShort", "", 0));
		rtcParam.getInports().addAll(dataInport);

		List<DataPortParam> dataOutport = new ArrayList<DataPortParam>(); 
		dataOutport.add(new DataPortParam("out1", "RTC::TimedLong", "", 0));
		rtcParam.getOutports().addAll(dataOutport);

		ServicePortParam service1 = new ServicePortParam("",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice0", "", "", 
				rootPath + "/resource/CXX/Exception/MyService.idl", "MyService", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);

		Generator generator = new Generator();
		try {
			generator.generateTemplateCode(genParam);
			fail();
		} catch (Exception e) {
		}
	}

	public void testOutPortNoType() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("test module");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(2);

		List<DataPortParam> dataInport = new ArrayList<DataPortParam>();
		dataInport.add(new DataPortParam("in1", "RTC::TimedShort", "", 0));
		rtcParam.getInports().addAll(dataInport);

		List<DataPortParam> dataOutport = new ArrayList<DataPortParam>();
		dataOutport.add(new DataPortParam("out1", "", "", 0));
		rtcParam.getOutports().addAll(dataOutport);

		Generator generator = new Generator();
		try {
			generator.generateTemplateCode(genParam);
			fail();
		} catch (Exception e) {
		}
	}

	public void testOutPortNoName() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("test module");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(2);

		List<DataPortParam> dataInport = new ArrayList<DataPortParam>();
		dataInport.add(new DataPortParam("in1", "RTC::TimedShort", "", 0));
		rtcParam.getInports().addAll(dataInport);

		List<DataPortParam> dataOutport = new ArrayList<DataPortParam>();
		dataOutport.add(new DataPortParam("", "RTC::TimedLong", "", 0));
		rtcParam.getOutports().addAll(dataOutport);

		Generator generator = new Generator();
		try {
			generator.generateTemplateCode(genParam);
			fail();
		} catch (Exception e) {
		}
	}

	public void testInPortNoType() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("test module");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(2);

		List<DataPortParam> dataport = new ArrayList<DataPortParam>();
		dataport.add(new DataPortParam("in1", "", "", 0));
		rtcParam.getInports().addAll(dataport);

		Generator generator = new Generator();
		try {
			generator.generateTemplateCode(genParam);
			fail();
		} catch (Exception e) {
		}
	}

	public void testInPortNoName() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("test module");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(2);

		List<DataPortParam> dataport = new ArrayList<DataPortParam>();
		dataport.add(new DataPortParam("", "RTC::TimedShort", "", 0));
		rtcParam.getInports().addAll(dataport);

		Generator generator = new Generator();
		try {
			generator.generateTemplateCode(genParam);
			fail();
		} catch (Exception e) {
		}
	}

}
