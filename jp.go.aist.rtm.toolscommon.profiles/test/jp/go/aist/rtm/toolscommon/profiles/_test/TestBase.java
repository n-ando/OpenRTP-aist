package jp.go.aist.rtm.toolscommon.profiles._test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.openrtp.namespaces.rtc.version02.ConstraintType;

import org.openrtp.namespaces.rtc.version03.ActionStatusDoc;
import org.openrtp.namespaces.rtc.version03.Actions;
import org.openrtp.namespaces.rtc.version03.BasicInfoExt;
import org.openrtp.namespaces.rtc.version03.Configuration;
import org.openrtp.namespaces.rtc.version03.ConfigurationDoc;
import org.openrtp.namespaces.rtc.version03.ConfigurationExt;
import org.openrtp.namespaces.rtc.version03.ConfigurationSet;
import org.openrtp.namespaces.rtc.version03.DataportExt;
import org.openrtp.namespaces.rtc.version03.DocAction;
import org.openrtp.namespaces.rtc.version03.DocBasic;
import org.openrtp.namespaces.rtc.version03.DocConfiguration;
import org.openrtp.namespaces.rtc.version03.DocDataport;
import org.openrtp.namespaces.rtc.version03.DocServiceinterface;
import org.openrtp.namespaces.rtc.version03.DocServiceport;
import org.openrtp.namespaces.rtc.version03.LanguageExt;
import org.openrtp.namespaces.rtc.version03.ObjectFactory;
import org.openrtp.namespaces.rtc.version03.Position;
import org.openrtp.namespaces.rtc.version03.RtcProfile;
import org.openrtp.namespaces.rtc.version03.ServiceinterfaceExt;
import org.openrtp.namespaces.rtc.version03.ServiceportExt;

import junit.framework.TestCase;

public class TestBase extends TestCase {
	protected String rootPath;

	public TestBase () {
		File fileCurrent = new File(".");
		rootPath = fileCurrent.getAbsolutePath();
		rootPath = rootPath.substring(0,rootPath.length()-1);
	}
	protected String readFile(String fileName, String splitter) {
		StringBuffer stbRet = new StringBuffer();
		try (FileReader fr = new FileReader(fileName); BufferedReader br = new BufferedReader(fr) ) {
			String str = new String();
			while( (str = br.readLine()) != null ){
				stbRet.append(str + splitter);
			}
		} catch (IOException e){
			e.printStackTrace();
		}
		return stbRet.toString();
	}

	protected RtcProfile createConstraintBase(ConstraintType source) {
		ObjectFactory factory = new ObjectFactory();

		RtcProfile profile = factory.createRtcProfile();
		profile.setVersion("0.3");
		ConfigurationSet configset = factory.createConfigurationSet();
		ConfigurationExt config = factory.createConfigurationExt();
		configset.getConfiguration().add(config);
		profile.setConfigurationSet(configset);

		config.setConstraint(source);

		return profile;
	}

	protected void checkDetailXml(RtcProfile profile){
		assertEquals("RTC:SampleVendor:SampleCategory:SampleComponent:1.0.0",profile.getId());
		assertEquals("0.2", profile.getVersion());
		//
		checkDetailCommon(profile);
	}

	protected void checkDetailYaml01(RtcProfile profile){
		assertEquals("RTC:SampleVendor.SampleCategory.SampleComponent:1.0.0",profile.getId());
		assertEquals("0.3", profile.getVersion());
		//
		checkDetailCommon(profile);
	}

	protected void checkDetailCommon(RtcProfile profile){
		BasicInfoExt basic = (BasicInfoExt)profile.getBasicInfo();
		assertEquals("SampleComponent", basic.getName());
		assertEquals("STATIC", basic.getComponentType());
		assertEquals("PERIODIC", basic.getActivityType());
		assertEquals("DataFlowComponent", basic.getComponentKind());
		assertEquals("SampleCategory", basic.getCategory());
		assertEquals("SampleDescription", basic.getDescription());
		assertEquals(Double.valueOf(1000.0d), basic.getExecutionRate());
		assertEquals("PeriodicExecutionContext", basic.getExecutionType());
		assertEquals(1, basic.getMaxInstances().longValue());
		assertEquals("SampleVendor", basic.getVendor());
		assertEquals("1.0.0", basic.getVersion());
		assertEquals("SampleAbstract", basic.getAbstract());
		assertEquals("2008-04-18T14:00:00.000+09:00", basic.getCreationDate().toString());
		assertEquals("2008-04-17T14:00:00.000+09:00", basic.getUpdateDate().toString());
		//
		DocBasic docbasic = basic.getDoc();
		assertEquals("SampleBasicDecription", docbasic.getDescription());
		assertEquals("SampleBasicInout", docbasic.getInout());
		assertEquals("SampleAlgorithm", docbasic.getAlgorithm());
		assertEquals("SampleCreator", docbasic.getCreator());
		assertEquals("SampleLicense", docbasic.getLicense());
		assertEquals("SampleReference", docbasic.getReference());
		//
		assertEquals("2008/04/18 14:00:00:Ver1.0", basic.getVersionUpLogs().get(0));
		assertEquals("2008/04/18 17:00:00:Ver1.1", basic.getVersionUpLogs().get(1));
		//
		Actions actions = profile.getActions();
		ActionStatusDoc oninit = (ActionStatusDoc)actions.getOnInitialize();
		assertEquals("true", oninit.getImplemented());
		DocAction initdoc = oninit.getDoc();
		assertEquals("on_initialize description", initdoc.getDescription());
		assertEquals("on_initialize Pre_condition", initdoc.getPreCondition());
		assertEquals("on_initialize Post_condition", initdoc.getPostCondition());
		//
		ActionStatusDoc onfinal = (ActionStatusDoc)actions.getOnFinalize();
		assertEquals("false", onfinal.getImplemented());
		DocAction finaldoc = onfinal.getDoc();
		assertEquals("on_finalize description", finaldoc.getDescription());
		assertEquals("on_finalize Pre_condition", finaldoc.getPreCondition());
		assertEquals("on_finalize Post_condition", finaldoc.getPostCondition());
		//
		ActionStatusDoc onstart = (ActionStatusDoc)actions.getOnStartup();
		assertEquals("false", onstart.getImplemented());
		DocAction startdoc = onstart.getDoc();
		assertEquals("on_startup description", startdoc.getDescription());
		assertEquals("on_startup Pre_condition", startdoc.getPreCondition());
		assertEquals("on_startup Post_condition", startdoc.getPostCondition());
		//
		ActionStatusDoc onshut = (ActionStatusDoc)actions.getOnShutdown();
		assertEquals("true", onshut.getImplemented());
		DocAction shutdoc = onshut.getDoc();
		assertEquals("on_shutdown description", shutdoc.getDescription());
		assertEquals("on_shutdown Pre_condition", shutdoc.getPreCondition());
		assertEquals("on_shutdown Post_condition", shutdoc.getPostCondition());
		//
		ActionStatusDoc onact = (ActionStatusDoc)actions.getOnActivated();
		assertEquals("true", onact.getImplemented());
		DocAction actdoc = onact.getDoc();
		assertEquals("on_activated description", actdoc.getDescription());
		assertEquals("on_activated Pre_condition", actdoc.getPreCondition());
		assertEquals("on_activated Post_condition", actdoc.getPostCondition());
		//
		ActionStatusDoc ondeact = (ActionStatusDoc)actions.getOnDeactivated();
		assertEquals("false", ondeact.getImplemented());
		DocAction deactdoc = ondeact.getDoc();
		assertEquals("on_deactivated description", deactdoc.getDescription());
		assertEquals("on_deactivated Pre_condition", deactdoc.getPreCondition());
		assertEquals("on_deactivated Post_condition", deactdoc.getPostCondition());
		//
		ActionStatusDoc onabort = (ActionStatusDoc)actions.getOnAborting();
		assertEquals("true", onabort.getImplemented());
		DocAction abortdoc = onabort.getDoc();
		assertEquals("on_aborting description", abortdoc.getDescription());
		assertEquals("on_aborting Pre_condition", abortdoc.getPreCondition());
		assertEquals("on_aborting Post_condition", abortdoc.getPostCondition());
		//
		ActionStatusDoc onerrort = (ActionStatusDoc)actions.getOnError();
		assertEquals("false", onerrort.getImplemented());
		DocAction errordoc = onerrort.getDoc();
		assertEquals("on_error description", errordoc.getDescription());
		assertEquals("on_error Pre_condition", errordoc.getPreCondition());
		assertEquals("on_error Post_condition", errordoc.getPostCondition());
		//
		ActionStatusDoc onreset = (ActionStatusDoc)actions.getOnReset();
		assertEquals("false", onerrort.getImplemented());
		DocAction resetdoc = onreset.getDoc();
		assertEquals("on_reset description", resetdoc.getDescription());
		assertEquals("on_reset Pre_condition", resetdoc.getPreCondition());
		assertEquals("on_reset Post_condition", resetdoc.getPostCondition());
		//
		ActionStatusDoc onexec = (ActionStatusDoc)actions.getOnExecute();
		assertEquals("false", onerrort.getImplemented());
		DocAction execdoc = onexec.getDoc();
		assertEquals("on_execute description", execdoc.getDescription());
		assertEquals("on_execute Pre_condition", execdoc.getPreCondition());
		assertEquals("on_execute Post_condition", execdoc.getPostCondition());
		//
		ActionStatusDoc onstate = (ActionStatusDoc)actions.getOnStateUpdate();
		assertEquals("false", onstate.getImplemented());
		DocAction statedoc = onstate.getDoc();
		assertEquals("on_state_update description", statedoc.getDescription());
		assertEquals("on_state_update Pre_condition", statedoc.getPreCondition());
		assertEquals("on_state_update Post_condition", statedoc.getPostCondition());
		//
		ActionStatusDoc onrate = (ActionStatusDoc)actions.getOnRateChanged();
		assertEquals("false", onrate.getImplemented());
		DocAction rateedoc = onrate.getDoc();
		assertEquals("on_rate_changed description", rateedoc.getDescription());
		assertEquals("on_rate_changed Pre_condition", rateedoc.getPreCondition());
		assertEquals("on_rate_changed Post_condition", rateedoc.getPostCondition());
		//
		ConfigurationSet configset = profile.getConfigurationSet();
		ConfigurationExt config = (ConfigurationExt)configset.getConfiguration().get(0);
		assertEquals("config1", config.getName());
		assertEquals("int", config.getType());
		assertEquals("var1", config.getVariableName());
		assertEquals("1", config.getDefaultValue());
		DocConfiguration docconfig = config.getDoc();
		assertEquals("dataname1", docconfig.getDataname());
		assertEquals("default1", docconfig.getDefaultValue());
		assertEquals("config_Desc1", docconfig.getDescription());
		assertEquals("config_unit1", docconfig.getUnit());
		assertEquals("config_range1", docconfig.getRange());
		assertEquals("config_constraint1", docconfig.getConstraint());
		//
		ConfigurationExt config2 = (ConfigurationExt)configset.getConfiguration().get(1);
		assertEquals("config2", config2.getName());
		assertEquals("String", config2.getType());
		assertEquals("var2", config2.getVariableName());
		assertEquals("Sample", config2.getDefaultValue());
		//
		DataportExt dataport1 = (DataportExt)profile.getDataPorts().get(0);
		assertEquals("DataInPort", dataport1.getPortType());
		assertEquals("inport1", dataport1.getName());
		assertEquals("RTC::TimedLong", dataport1.getType());
		assertEquals("In1Var", dataport1.getVariableName());
		assertEquals(Position.LEFT.toString(), dataport1.getPosition().toString());
		assertEquals("DataPort1.idl", dataport1.getIdlFile());
		assertEquals("CorbaPort", dataport1.getInterfaceType());
		assertEquals("Push,Pull", dataport1.getDataflowType());
		assertEquals("Periodic,New,Flush", dataport1.getSubscriptionType());
		//
		DocDataport docdatp1 = dataport1.getDoc();
		assertEquals("In1Description", docdatp1.getDescription());
		assertEquals("In1Type", docdatp1.getType());
		assertEquals("In1Number", docdatp1.getNumber());
		assertEquals("In1Semantics", docdatp1.getSemantics());
		assertEquals("In1Unit", docdatp1.getUnit());
		assertEquals("In1Occerrence", docdatp1.getOccerrence());
		assertEquals("In1Operation", docdatp1.getOperation());
		//
		DataportExt dataport2 = (DataportExt)profile.getDataPorts().get(1);
		assertEquals("DataInPort", dataport2.getPortType());
		assertEquals("inport2", dataport2.getName());
		assertEquals("RTC::TimedDouble", dataport2.getType());
		assertEquals("In2Var", dataport2.getVariableName());
		assertEquals(Position.LEFT.toString(), dataport2.getPosition().toString());
		assertEquals("CorbaPort", dataport2.getInterfaceType());
		assertEquals("Push,Pull", dataport2.getDataflowType());
		assertEquals("New,Periodic", dataport2.getSubscriptionType());
		//
		DataportExt dataport3 = (DataportExt)profile.getDataPorts().get(2);
		assertEquals("DataOutPort", dataport3.getPortType());
		assertEquals("outport1", dataport3.getName());
		assertEquals("RTC::TimedLong", dataport3.getType());
		assertEquals("Out1Var", dataport3.getVariableName());
		assertEquals(Position.RIGHT.toString(), dataport3.getPosition().toString());
		assertEquals("CorbaPort", dataport3.getInterfaceType());
		assertEquals("Push", dataport3.getDataflowType());
		assertEquals("New,Periodic", dataport3.getSubscriptionType());
		//
		DocDataport docdatp3 = dataport3.getDoc();
		assertEquals("Out1Description", docdatp3.getDescription());
		assertEquals("Out1Type", docdatp3.getType());
		assertEquals("Out1Number", docdatp3.getNumber());
		assertEquals("Out1Semantics", docdatp3.getSemantics());
		assertEquals("Out1Unit", docdatp3.getUnit());
		assertEquals("Out1Occerrence", docdatp3.getOccerrence());
		assertEquals("Out1Operation", docdatp3.getOperation());
		//
		DataportExt dataport4 = (DataportExt)profile.getDataPorts().get(3);
		assertEquals("DataOutPort", dataport4.getPortType());
		assertEquals("outport2", dataport4.getName());
		assertEquals("RTC::TimedDouble", dataport4.getType());
		assertEquals("Out2Var", dataport4.getVariableName());
		assertEquals(Position.RIGHT.toString().toString(), dataport4.getPosition().toString());
		assertEquals("CorbaPort", dataport4.getInterfaceType());
		assertEquals("Push,Pull", dataport4.getDataflowType());
		assertEquals("New,Periodic", dataport4.getSubscriptionType());
		//
		ServiceportExt service1 = (ServiceportExt)profile.getServicePorts().get(0);
		assertEquals("SrvPort1", service1.getName());
		assertEquals(Position.LEFT.toString().toString(), service1.getPosition().toString());
		DocServiceport serviceDoc1 = service1.getDoc();
		assertEquals("ServicePort1 description", serviceDoc1.getDescription());
		assertEquals("ServicePort1 I/F description", serviceDoc1.getIfdescription());
		//
		ServiceinterfaceExt serviceIF1 = (ServiceinterfaceExt)service1.getServiceInterface().get(0);
		assertEquals("S1IF1", serviceIF1.getName());
		assertEquals("Provided", serviceIF1.getDirection());
		assertEquals("IF1Instance", serviceIF1.getInstanceName());
		assertEquals("IF1VarName", serviceIF1.getVariableName());
		assertEquals("IF1Idlfile.idl", serviceIF1.getIdlFile());
		assertEquals("IF1Type", serviceIF1.getType());
		//
		DocServiceinterface docIf1 = serviceIF1.getDoc();
		assertEquals("if1 Description", docIf1.getDescription());
		assertEquals("if1 Argument", docIf1.getDocArgument());
		assertEquals("if1 Return", docIf1.getDocReturn());
		assertEquals("if1 Exception", docIf1.getDocException());
		assertEquals("if1 PreCond", docIf1.getDocPreCondition());
		assertEquals("if1 PostCond", docIf1.getDocPostCondition());
		//
		ServiceinterfaceExt serviceIF2 = (ServiceinterfaceExt)service1.getServiceInterface().get(1);
		assertEquals("S1IF2", serviceIF2.getName());
		assertEquals("Required", serviceIF2.getDirection());
		assertEquals("IF2Instance", serviceIF2.getInstanceName());
		assertEquals("IF2VarName", serviceIF2.getVariableName());
		assertEquals("IF2Idlfile.idl", serviceIF2.getIdlFile());
		assertEquals("IF2Type", serviceIF2.getType());
		//
		ServiceportExt service2 = (ServiceportExt)profile.getServicePorts().get(1);
		assertEquals("SrvPort2", service2.getName());
		assertEquals(Position.RIGHT.toString().toString(), service2.getPosition().toString());
		DocServiceport serviceDoc2 = service2.getDoc();
		assertEquals("ServicePort2 description", serviceDoc2.getDescription());
		assertEquals("ServicePort2 I/F description", serviceDoc2.getIfdescription());
		//
		LanguageExt lang = (LanguageExt)profile.getLanguage();
		assertEquals("Java", lang.getKind());
		assertEquals("library1", lang.getTargets().get(0).getLibraries().get(0).getName());
	}

	protected void checkDetailVer02(RtcProfile profile){

		assertEquals("RTC:SampleVender:SampleCategory:SampleComponent:1.0.0",profile.getId());
		assertEquals("0.3", profile.getVersion());
		//
		BasicInfoExt basic = (BasicInfoExt)profile.getBasicInfo();
		assertEquals("SampleComponent", basic.getName());
		assertEquals("STATIC", basic.getComponentType());
		assertEquals("PERIODIC", basic.getActivityType());
		assertEquals("DataFlowComponent", basic.getComponentKind());
		assertEquals("Normal", basic.getRtcType());
		assertEquals("SampleCategory", basic.getCategory());
		assertEquals("SampleDescription", basic.getDescription());
		assertEquals(1000.0, basic.getExecutionRate());
		assertEquals("PeriodicExecutionContext", basic.getExecutionType());
		assertEquals(1, basic.getMaxInstances().longValue());
		assertEquals("SampleVendor", basic.getVendor());
		assertEquals("1.0.0", basic.getVersion());
		assertEquals("SampleAbstract", basic.getAbstract());
		assertEquals("SampleProfile", basic.getHardwareProfile());
		assertEquals("2008-04-18T14:00:00.000+09:00", basic.getCreationDate().toString());
		assertEquals("2008-04-18T14:00:00.000+09:00", basic.getUpdateDate().toString());
		assertEquals("Output Project", basic.getSaveProject());
		//
		DocBasic docbasic = basic.getDoc();
		assertEquals("SampleBasicDecription", docbasic.getDescription());
		assertEquals("SampleBasicInout", docbasic.getInout());
		assertEquals("SampleAlgorithm", docbasic.getAlgorithm());
		assertEquals("SampleCreator", docbasic.getCreator());
		assertEquals("SampleLicense", docbasic.getLicense());
		assertEquals("SampleReference", docbasic.getReference());
		//
		assertEquals("2008/04/18 14:00:00:Ver1.0", basic.getVersionUpLogs().get(0));
		assertEquals("2008/04/18 17:00:00:Ver1.1", basic.getVersionUpLogs().get(1));
		//
		Actions actions = profile.getActions();
		ActionStatusDoc oninit = (ActionStatusDoc)actions.getOnInitialize();
		assertEquals("true", oninit.getImplemented());
		DocAction initdoc = oninit.getDoc();
		assertEquals("on_initialize description", initdoc.getDescription());
		assertEquals("on_initialize Pre_condition", initdoc.getPreCondition());
		assertEquals("on_initialize Post_condition", initdoc.getPostCondition());
		//
		ActionStatusDoc onfinal = (ActionStatusDoc)actions.getOnFinalize();
		assertEquals("false", onfinal.getImplemented());
		DocAction finaldoc = onfinal.getDoc();
		assertEquals("on_finalize description", finaldoc.getDescription());
		assertEquals("on_finalize Pre_condition", finaldoc.getPreCondition());
		assertEquals("on_finalize Post_condition", finaldoc.getPostCondition());
		//
		ActionStatusDoc onstart = (ActionStatusDoc)actions.getOnStartup();
		assertEquals("false", onstart.getImplemented());
		DocAction startdoc = onstart.getDoc();
		assertEquals("on_startup description", startdoc.getDescription());
		assertEquals("on_startup Pre_condition", startdoc.getPreCondition());
		assertEquals("on_startup Post_condition", startdoc.getPostCondition());
		//
		ActionStatusDoc onshut = (ActionStatusDoc)actions.getOnShutdown();
		assertEquals("true", onshut.getImplemented());
		DocAction shutdoc = onshut.getDoc();
		assertEquals("on_shutdown description", shutdoc.getDescription());
		assertEquals("on_shutdown Pre_condition", shutdoc.getPreCondition());
		assertEquals("on_shutdown Post_condition", shutdoc.getPostCondition());
		//
		ActionStatusDoc onact = (ActionStatusDoc)actions.getOnActivated();
		assertEquals("true", onact.getImplemented());
		DocAction actdoc = onact.getDoc();
		assertEquals("on_activated description", actdoc.getDescription());
		assertEquals("on_activated Pre_condition", actdoc.getPreCondition());
		assertEquals("on_activated Post_condition", actdoc.getPostCondition());
		//
		ActionStatusDoc ondeact = (ActionStatusDoc)actions.getOnDeactivated();
		assertEquals("false", ondeact.getImplemented());
		DocAction deactdoc = ondeact.getDoc();
		assertEquals("on_deactivated description", deactdoc.getDescription());
		assertEquals("on_deactivated Pre_condition", deactdoc.getPreCondition());
		assertEquals("on_deactivated Post_condition", deactdoc.getPostCondition());
		//
		ActionStatusDoc onabort = (ActionStatusDoc)actions.getOnAborting();
		assertEquals("true", onabort.getImplemented());
		DocAction abortdoc = onabort.getDoc();
		assertEquals("on_aborting description", abortdoc.getDescription());
		assertEquals("on_aborting Pre_condition", abortdoc.getPreCondition());
		assertEquals("on_aborting Post_condition", abortdoc.getPostCondition());
		//
		ActionStatusDoc onerrort = (ActionStatusDoc)actions.getOnError();
		assertEquals("false", onerrort.getImplemented());
		DocAction errordoc = onerrort.getDoc();
		assertEquals("on_error description", errordoc.getDescription());
		assertEquals("on_error Pre_condition", errordoc.getPreCondition());
		assertEquals("on_error Post_condition", errordoc.getPostCondition());
		//
		ActionStatusDoc onreset = (ActionStatusDoc)actions.getOnReset();
		assertEquals("false", onerrort.getImplemented());
		DocAction resetdoc = onreset.getDoc();
		assertEquals("on_reset description", resetdoc.getDescription());
		assertEquals("on_reset Pre_condition", resetdoc.getPreCondition());
		assertEquals("on_reset Post_condition", resetdoc.getPostCondition());
		//
		ActionStatusDoc onexec = (ActionStatusDoc)actions.getOnExecute();
		assertEquals("false", onerrort.getImplemented());
		DocAction execdoc = onexec.getDoc();
		assertEquals("on_execute description", execdoc.getDescription());
		assertEquals("on_execute Pre_condition", execdoc.getPreCondition());
		assertEquals("on_execute Post_condition", execdoc.getPostCondition());
		//
		ActionStatusDoc onstate = (ActionStatusDoc)actions.getOnStateUpdate();
		assertEquals("false", onstate.getImplemented());
		DocAction statedoc = onstate.getDoc();
		assertEquals("on_state_update description", statedoc.getDescription());
		assertEquals("on_state_update Pre_condition", statedoc.getPreCondition());
		assertEquals("on_state_update Post_condition", statedoc.getPostCondition());
		//
		ActionStatusDoc onrate = (ActionStatusDoc)actions.getOnRateChanged();
		assertEquals("false", onrate.getImplemented());
		DocAction rateedoc = onrate.getDoc();
		assertEquals("on_rate_changed description", rateedoc.getDescription());
		assertEquals("on_rate_changed Pre_condition", rateedoc.getPreCondition());
		assertEquals("on_rate_changed Post_condition", rateedoc.getPostCondition());
		//
		ConfigurationSet configset = profile.getConfigurationSet();
		ConfigurationExt config = (ConfigurationExt)configset.getConfiguration().get(0);
		assertEquals("config1", config.getName());
		assertEquals("int", config.getType());
		assertEquals("10", config.getDefaultValue());
		assertEquals("config_unit1", config.getUnit());
//		assertEquals("100", config.getConstraint().getConstraintUnitType().getPropertyIsLessThan().getLiteral());
		assertEquals("Sample", config.getComment());
		assertEquals("var1", config.getVariableName());
		DocConfiguration docconfig = config.getDoc();
		assertEquals("config_Desc1", docconfig.getDescription());
		assertEquals("dataname1", docconfig.getDataname());
		assertEquals("default1", docconfig.getDefaultValue());
		assertEquals("config_unit1", docconfig.getUnit());
		assertEquals("config_range1", docconfig.getRange());
		assertEquals("config_constraint1", docconfig.getConstraint());
		//
		ConfigurationDoc config2 = (ConfigurationDoc)configset.getConfiguration().get(1);
		assertEquals("config2", config2.getName());
		assertEquals("String", config2.getType());
		assertEquals("Sample", config2.getDefaultValue());
//		assertEquals("up", config2.getConstraint().getConstraintUnitType().getOr().getConstraint().get(0).getConstraintUnitType().getPropertyIsEqualTo().getLiteral());
//		assertEquals("down", config2.getConstraint().getConstraintUnitType().getOr().getConstraint().get(1).getConstraintUnitType().getPropertyIsEqualTo().getLiteral());
//		assertEquals("left", config2.getConstraint().getConstraintUnitType().getOr().getConstraint().get(2).getConstraintUnitType().getPropertyIsEqualTo().getLiteral());
//		assertEquals("right", config2.getConstraint().getConstraintUnitType().getOr().getConstraint().get(3).getConstraintUnitType().getPropertyIsEqualTo().getLiteral());
		//
		DataportExt dataport1 = (DataportExt)profile.getDataPorts().get(0);
		assertEquals("DataInPort", dataport1.getPortType());
		assertEquals("inport1", dataport1.getName());
		assertEquals("RTC::TimedLong", dataport1.getType());
		assertEquals("DataPort1.idl", dataport1.getIdlFile());
		assertEquals("CorbaPort", dataport1.getInterfaceType());
		assertEquals("Push,Pull", dataport1.getDataflowType());
		assertEquals("Periodic,New,Flush", dataport1.getSubscriptionType());
		assertEquals("In1Unit", dataport1.getUnit());
//		assertEquals("100", dataport1.getConstraint().getConstraintUnitType().getPropertyIsBetween().getLowerBoundary());
//		assertEquals("200", dataport1.getConstraint().getConstraintUnitType().getPropertyIsBetween().getUpperBoundary());
		assertEquals("In1Var", dataport1.getVariableName());
		assertEquals(Position.LEFT.toString(), dataport1.getPosition().toString());
		//
		DocDataport docdatp1 = dataport1.getDoc();
		assertEquals("In1Description", docdatp1.getDescription());
		assertEquals("In1Type", docdatp1.getType());
		assertEquals("In1Number", docdatp1.getNumber());
		assertEquals("In1Semantics", docdatp1.getSemantics());
		assertEquals("In1Unit", docdatp1.getUnit());
		assertEquals("In1Occerrence", docdatp1.getOccerrence());
		assertEquals("In1Operation", docdatp1.getOperation());
		//
		DataportExt dataport2 = (DataportExt)profile.getDataPorts().get(1);
		assertEquals("DataInPort", dataport2.getPortType());
		assertEquals("inport2", dataport2.getName());
		assertEquals("RTC::TimedDouble", dataport2.getType());
		assertEquals(Position.LEFT.toString(), dataport2.getPosition().toString());
		assertEquals("CorbaPort", dataport2.getInterfaceType());
		assertEquals("Push,Pull", dataport2.getDataflowType());
		assertEquals("New,Periodic", dataport2.getSubscriptionType());
		//
		DataportExt dataport3 = (DataportExt)profile.getDataPorts().get(2);
		assertEquals("DataOutPort", dataport3.getPortType());
		assertEquals("outport1", dataport3.getName());
		assertEquals("RTC::TimedLong", dataport3.getType());
		assertEquals("CorbaPort", dataport3.getInterfaceType());
		assertEquals("Push", dataport3.getDataflowType());
		assertEquals("New,Periodic", dataport3.getSubscriptionType());
		assertEquals(Position.RIGHT.toString(), dataport3.getPosition().toString());
		//
		DocDataport docdatp3 = dataport3.getDoc();
		assertEquals("Out1Description", docdatp3.getDescription());
		assertEquals("Out1Type", docdatp3.getType());
		assertEquals("Out1Number", docdatp3.getNumber());
		assertEquals("Out1Semantics", docdatp3.getSemantics());
		//
		DataportExt dataport4 = (DataportExt)profile.getDataPorts().get(3);
		assertEquals("DataOutPort", dataport4.getPortType());
		assertEquals("outport2", dataport4.getName());
		assertEquals("RTC::TimedDouble", dataport4.getType());
		assertEquals(Position.RIGHT.toString().toString(), dataport4.getPosition().toString());
		assertEquals("CorbaPort", dataport4.getInterfaceType());
		assertEquals("Push,Pull", dataport4.getDataflowType());
		assertEquals("New,Periodic", dataport4.getSubscriptionType());
		//
		ServiceportExt service1 = (ServiceportExt)profile.getServicePorts().get(0);
		assertEquals("SrvPort1", service1.getName());
		assertEquals(Position.LEFT.toString().toString(), service1.getPosition().toString());
		DocServiceport serviceDoc1 = service1.getDoc();
		assertEquals("ServicePort1 description", serviceDoc1.getDescription());
		assertEquals("ServicePort1 I/F description", serviceDoc1.getIfdescription());
		//
		ServiceinterfaceExt serviceIF1 = (ServiceinterfaceExt)service1.getServiceInterface().get(0);
		assertEquals("S1IF1", serviceIF1.getName());
		assertEquals("IF1Type", serviceIF1.getType());
		assertEquals("Provided", serviceIF1.getDirection());
		assertEquals("IF1Instance", serviceIF1.getInstanceName());
		assertEquals("IF1Idlfile.idl", serviceIF1.getIdlFile());
		assertEquals("IF1VarName", serviceIF1.getVariableName());
		//
		DocServiceinterface docIf1 = serviceIF1.getDoc();
		assertEquals("if1 description", docIf1.getDescription());
		assertEquals("if1 Argument", docIf1.getDocArgument());
		assertEquals("if1 Return", docIf1.getDocReturn());
		assertEquals("if1 Exception", docIf1.getDocException());
		assertEquals("if1 PreCond", docIf1.getDocPreCondition());
		assertEquals("if1 PostCond", docIf1.getDocPostCondition());
		//
		ServiceinterfaceExt serviceIF2 = (ServiceinterfaceExt)service1.getServiceInterface().get(1);
		assertEquals("S1IF2", serviceIF2.getName());
		assertEquals("IF2Type", serviceIF2.getType());
		assertEquals("Required", serviceIF2.getDirection());
		assertEquals("IF2Instance", serviceIF2.getInstanceName());
		assertEquals("IF2Idlfile.idl", serviceIF2.getIdlFile());
		//
		ServiceportExt service2 = (ServiceportExt)profile.getServicePorts().get(1);
		assertEquals("SrvPort2", service2.getName());
		assertEquals(Position.LEFT.toString().toString(), service2.getPosition().toString());
		DocServiceport serviceDoc2 = service2.getDoc();
		assertEquals("ServicePort2 description", serviceDoc2.getDescription());
		assertEquals("ServicePort2 I/F description", serviceDoc2.getIfdescription());
		//
		LanguageExt lang = (LanguageExt)profile.getLanguage();
		assertEquals("Java", lang.getKind());
		assertEquals("JDK6", lang.getTargets().get(0).getLangVersion());
		assertEquals("Linux", lang.getTargets().get(0).getOs());
		assertEquals("2.4", lang.getTargets().get(0).getOsVersions().get(0));
		assertEquals("2.6", lang.getTargets().get(0).getOsVersions().get(1));
		assertEquals("i386", lang.getTargets().get(0).getCpus().get(0));
		assertEquals("ARM", lang.getTargets().get(0).getCpus().get(1));
		assertEquals("SampleLib", lang.getTargets().get(0).getLibraries().get(0).getName());
		assertEquals("1.0", lang.getTargets().get(0).getLibraries().get(0).getVersion());
		assertEquals("Sample1", lang.getTargets().get(0).getLibraries().get(0).getOther());
		assertEquals("SampleLib2", lang.getTargets().get(0).getLibraries().get(1).getName());
		assertEquals("1.5", lang.getTargets().get(0).getLibraries().get(1).getVersion());
		assertEquals("Sample2", lang.getTargets().get(0).getLibraries().get(1).getOther());
	}
}
