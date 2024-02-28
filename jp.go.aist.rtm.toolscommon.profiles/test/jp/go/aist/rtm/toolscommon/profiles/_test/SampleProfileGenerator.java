package jp.go.aist.rtm.toolscommon.profiles._test;

import static jp.go.aist.rtm.toolscommon.profiles.util.XmlHandler.createXMLGregorianCalendar;

import java.math.BigInteger;

import org.openrtp.namespaces.rtc.version03.ActionStatusDoc;
import org.openrtp.namespaces.rtc.version03.Actions;
import org.openrtp.namespaces.rtc.version03.BasicInfoExt;
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
import org.openrtp.namespaces.rtc.version03.Library;
import org.openrtp.namespaces.rtc.version03.ObjectFactory;
import org.openrtp.namespaces.rtc.version03.Position;
import org.openrtp.namespaces.rtc.version03.Property;
import org.openrtp.namespaces.rtc.version03.RtcProfile;
import org.openrtp.namespaces.rtc.version03.ServiceinterfaceExt;
import org.openrtp.namespaces.rtc.version03.ServiceportExt;
import org.openrtp.namespaces.rtc.version03.TargetEnvironment;
import org.openrtp.namespaces.rtc.version03.TransmissionMethod;
import org.openrtp.namespaces.rts.version02.Component;
import org.openrtp.namespaces.rts.version02.RtsProfileExt;

import jp.go.aist.rtm.toolscommon.profiles.util.XmlHandler;

public class SampleProfileGenerator {

	public RtsProfileExt generateRtsProfile() throws Exception {
		org.openrtp.namespaces.rts.version02.ObjectFactory factory = new org.openrtp.namespaces.rts.version02.ObjectFactory();
		RtsProfileExt profile = factory.createRtsProfileExt();
		profile.setId("RTSystem:jp.go.aist:SampleRTS:1.0.0");
		profile.setVersion("0.2");
		profile.setCreationDate(createXMLGregorianCalendar(2008, 4, 18, 14, 0, 0));
		profile.setUpdateDate(createXMLGregorianCalendar("2008-04-18T14:00:00"));
		profile.setComment("Sample Comment");
		profile.getVersionUpLogs().add("Version0.1");
		profile.getVersionUpLogs().add("Version0.2");
		//
		Component comp = factory.createComponent();
		comp.setId("RTC:SampleVendor:SampleCategory:SampleComponent:1.0.0");
		comp.setPathUri("file://localhost/C:\\RTM\\XMLSample\\XML\\SampleXML.xml");
		comp.setInstanceName("SampleComponent_1");
		comp.setCompositeType("None");
		comp.setIsRequired(false);
		//
		profile.getComponents().add(comp);
		
		return profile;
	}

	public RtcProfile generateProfile() throws Exception {
		
		ObjectFactory factory = new ObjectFactory();
		RtcProfile profile =  factory.createRtcProfile();
		profile.setId("RTC:SampleVender:SampleCategory:SampleComponent:1.0.0");
		profile.setVersion("0.2");
		//
		BasicInfoExt basic = factory.createBasicInfoExt();
		basic.setName("SampleComponent");
		basic.setComponentType("STATIC");
		basic.setActivityType("PERIODIC");
		basic.setComponentKind("DataFlowComponent");
		basic.setCategory("SampleCategory");
		basic.setDescription("SampleDescription");
		basic.setExecutionRate(Double.valueOf(1000.0d));
		basic.setExecutionType("PeriodicExecutionContext");
		basic.setMaxInstances(BigInteger.valueOf(1));
		basic.setVendor("SampleVendor");
		basic.setVersion("1.0.0");
		basic.setAbstract("SampleAbstract");
		basic.setHardwareProfile("SampleProfile");
		basic.setRtcType("Normal");
		basic.setCreationDate(createXMLGregorianCalendar(2008, 4, 18, 14, 0, 0));
		basic.setUpdateDate(createXMLGregorianCalendar("2008-04-18T14:00:00"));
		basic.setSaveProject("Output Project");
		profile.setBasicInfo(basic);
		//
		DocBasic docbasic = factory.createDocBasic();
		docbasic.setDescription("SampleBasicDecription");
		docbasic.setInout("SampleBasicInout");
		docbasic.setAlgorithm("SampleAlgorithm");
		docbasic.setCreator("SampleCreator");
		docbasic.setLicense("SampleLicense");
		docbasic.setReference("SampleReference");
		basic.setDoc(docbasic);
		//
		basic.getVersionUpLogs().add("2008/04/18 14:00:00:Ver1.0");
		basic.getVersionUpLogs().add("2008/04/18 17:00:00:Ver1.1");
		//
		Actions actions = factory.createActions();
		ActionStatusDoc oninit = factory.createActionStatusDoc();
		oninit.setImplementedbln(true);
		DocAction initdoc = factory.createDocAction();
		initdoc.setDescription("on_initialize description");
		initdoc.setPreCondition("on_initialize Pre_condition");
		initdoc.setPostCondition("on_initialize Post_condition");
		oninit.setDoc(initdoc);
		actions.setOnInitialize(oninit);
		//
		ActionStatusDoc onfinal = factory.createActionStatusDoc();
		onfinal.setImplementedbln(false);
		DocAction finaldoc = factory.createDocAction();
		finaldoc.setDescription("on_finalize description");
		finaldoc.setPreCondition("on_finalize Pre_condition");
		finaldoc.setPostCondition("on_finalize Post_condition");
		onfinal.setDoc(finaldoc);
		actions.setOnFinalize(onfinal);
		//
		ActionStatusDoc onstart = factory.createActionStatusDoc();
		onstart.setImplementedbln(false);
		DocAction startdoc = factory.createDocAction();
		startdoc.setDescription("on_startup description");
		startdoc.setPreCondition("on_startup Pre_condition");
		startdoc.setPostCondition("on_startup Post_condition");
		onstart.setDoc(startdoc);
		actions.setOnStartup(onstart);
		//
		ActionStatusDoc onshut = factory.createActionStatusDoc();
		onshut.setImplementedbln(true);
		DocAction shutdoc = factory.createDocAction();
		shutdoc.setDescription("on_shutdown description");
		shutdoc.setPreCondition("on_shutdown Pre_condition");
		shutdoc.setPostCondition("on_shutdown Post_condition");
		onshut.setDoc(shutdoc);
		actions.setOnShutdown(onshut);
		//
		ActionStatusDoc onact = factory.createActionStatusDoc();
		onact.setImplementedbln(true);
		DocAction actdoc = factory.createDocAction();
		actdoc.setDescription("on_activated description");
		actdoc.setPreCondition("on_activated Pre_condition");
		actdoc.setPostCondition("on_activated Post_condition");
		onact.setDoc(actdoc);
		actions.setOnActivated(onact);
		//
		ActionStatusDoc ondeact = factory.createActionStatusDoc();
		ondeact.setImplementedbln(false);
		DocAction deactdoc = factory.createDocAction();
		deactdoc.setDescription("on_deactivated description");
		deactdoc.setPreCondition("on_deactivated Pre_condition");
		deactdoc.setPostCondition("on_deactivated Post_condition");
		ondeact.setDoc(deactdoc);
		actions.setOnDeactivated(ondeact);
		//
		ActionStatusDoc onabort = factory.createActionStatusDoc();
		onabort.setImplementedbln(true);
		DocAction abortdoc = factory.createDocAction();
		abortdoc.setDescription("on_aborting description");
		abortdoc.setPreCondition("on_aborting Pre_condition");
		abortdoc.setPostCondition("on_aborting Post_condition");
		onabort.setDoc(abortdoc);
		actions.setOnAborting(onabort);
		//
		ActionStatusDoc onerrort = factory.createActionStatusDoc();
		onerrort.setImplementedbln(false);
		DocAction errordoc = factory.createDocAction();
		errordoc.setDescription("on_error description");
		errordoc.setPreCondition("on_error Pre_condition");
		errordoc.setPostCondition("on_error Post_condition");
		onerrort.setDoc(errordoc);
		actions.setOnError(onerrort);
		//
		ActionStatusDoc onreset = factory.createActionStatusDoc();
		onreset.setImplementedbln(false);
		DocAction resetdoc = factory.createDocAction();
		resetdoc.setDescription("on_reset description");
		resetdoc.setPreCondition("on_reset Pre_condition");
		resetdoc.setPostCondition("on_reset Post_condition");
		onreset.setDoc(resetdoc);
		actions.setOnReset(onreset);
		//
		ActionStatusDoc onexec = factory.createActionStatusDoc();
		onexec.setImplementedbln(false);
		DocAction execdoc = factory.createDocAction();
		execdoc.setDescription("on_execute description");
		execdoc.setPreCondition("on_execute Pre_condition");
		execdoc.setPostCondition("on_execute Post_condition");
		onexec.setDoc(execdoc);
		actions.setOnExecute(onexec);
		//
		ActionStatusDoc onstate = factory.createActionStatusDoc();
		onstate.setImplementedbln(false);
		DocAction statedoc = factory.createDocAction();
		statedoc.setDescription("on_state_update description");
		statedoc.setPreCondition("on_state_update Pre_condition");
		statedoc.setPostCondition("on_state_update Post_condition");
		onstate.setDoc(statedoc);
		actions.setOnStateUpdate(onstate);
		//
		ActionStatusDoc onrate = factory.createActionStatusDoc();
		onrate.setImplementedbln(false);
		DocAction rateedoc = factory.createDocAction();
		rateedoc.setDescription("on_rate_changed description");
		rateedoc.setPreCondition("on_rate_changed Pre_condition");
		rateedoc.setPostCondition("on_rate_changed Post_condition");
		onrate.setDoc(rateedoc);
		actions.setOnRateChanged(onrate);
		//
		profile.setActions(actions);
		//
		ConfigurationSet configset = factory.createConfigurationSet();
		ConfigurationExt config = factory.createConfigurationExt();
		config.setName("config1");
		config.setType("int");
		config.setVariableName("var1");
		config.setDefaultValue("10");
		config.setConstraint(XmlHandler.convertToXmlConstraint("x<100"));
		config.setComment("Sample");
		config.setUnit("config_unit1");
		DocConfiguration docconfig = factory.createDocConfiguration(); 
		docconfig.setDataname("dataname1");
		docconfig.setDefaultValue("default1");
		docconfig.setDescription("config_Desc1");
		docconfig.setUnit("config_unit1");
		docconfig.setRange("config_range1");
		docconfig.setConstraint("config_constraint1");
		config.setDoc(docconfig);
		configset.getConfiguration().add(config);
		//
		ConfigurationDoc config2 = factory.createConfigurationDoc();
		config2.setName("config2");
		config2.setType("String");
		config2.setDefaultValue("Sample");
		config2.setConstraint(XmlHandler.convertToXmlConstraint("(up,down,left,right)"));
		configset.getConfiguration().add(config2);
		//
		profile.setConfigurationSet(configset);
		//
		DataportExt dataport1 = factory.createDataportExt();
		dataport1.setPortType("DataInPort");
		dataport1.setName("inport1");
		dataport1.setType("RTC::TimedLong");
		dataport1.setVariableName("In1Var");
		dataport1.setUnit("In1Unit");
		dataport1.setPosition(Position.LEFT);
		dataport1.setIdlFile("DataPort1.idl");
		dataport1.setInterfaceType("CorbaPort");
		dataport1.setDataflowType("Push,Pull");
		dataport1.setSubscriptionType("Periodic,New,Flush");
		dataport1.setConstraint(XmlHandler.convertToXmlConstraint("100<=x<=200"));
		
		DocDataport docdatp1 = factory.createDocDataport();
		docdatp1.setDescription("In1Description");
		docdatp1.setType("In1Type");
		docdatp1.setNumber("In1Number");
		docdatp1.setSemantics("In1Semantics");
		docdatp1.setUnit("In1Unit");
		docdatp1.setOccerrence("In1Occerrence");
		docdatp1.setOperation("In1Operation");
		dataport1.setDoc(docdatp1);
		profile.getDataPorts().add(dataport1);
		//
		DataportExt dataport2 = factory.createDataportExt();
		dataport2.setPortType("DataInPort");
		dataport2.setName("inport2");
		dataport2.setType("RTC::TimedDouble");
		dataport2.setVariableName("In2Var");
		dataport2.setPosition(Position.LEFT);
		dataport2.setInterfaceType("CorbaPort");
		dataport2.setDataflowType("Push,Pull");
		dataport2.setSubscriptionType("New,Periodic");
		profile.getDataPorts().add(dataport2);
		//
		DataportExt dataport3 = factory.createDataportExt();
		dataport3.setPortType("DataOutPort");
		dataport3.setName("outport1");
		dataport3.setType("RTC::TimedLong");
		dataport3.setVariableName("Out1Var");
		dataport3.setPosition(Position.RIGHT);
		dataport3.setInterfaceType("CorbaPort");
		dataport3.setDataflowType("Push");
		dataport3.setSubscriptionType("New,Periodic");

		DocDataport docdatp3 = factory.createDocDataport();
		docdatp3.setDescription("Out1Description");
		docdatp3.setType("Out1Type");
		docdatp3.setNumber("Out1Number");
		docdatp3.setSemantics("Out1Semantics");
		docdatp3.setUnit("Out1Unit");
		docdatp3.setOccerrence("Out1Occerrence");
		docdatp3.setOperation("Out1Operation");
		dataport3.setDoc(docdatp3);
		profile.getDataPorts().add(dataport3);
		//
		DataportExt dataport4 = factory.createDataportExt();
		dataport4.setPortType("DataOutPort");
		dataport4.setName("outport2");
		dataport4.setType("RTC::TimedDouble");
		dataport4.setVariableName("Out2Var");
		dataport4.setPosition(Position.RIGHT);
		dataport4.setInterfaceType("CorbaPort");
		dataport4.setDataflowType("Push,Pull");
		dataport4.setSubscriptionType("New,Periodic");
		profile.getDataPorts().add(dataport4);
		//
		ServiceportExt service1 = factory.createServiceportExt();
		service1.setName("SrvPort1");
		service1.setPosition(Position.LEFT);
		DocServiceport serviceDoc1 = factory.createDocServiceport();
		serviceDoc1.setDescription("ServicePort1 description");
		serviceDoc1.setIfdescription("ServicePort1 I/F description");
		service1.setDoc(serviceDoc1);
		//
		ServiceinterfaceExt serviceIF1 = factory.createServiceinterfaceExt();
		serviceIF1.setName("S1IF1");
		serviceIF1.setDirection("Provided");
		serviceIF1.setInstanceName("IF1Instance");
		serviceIF1.setVariableName("IF1VarName");
		serviceIF1.setIdlFile("IF1Idlfile.idl");
		serviceIF1.setType("IF1Type");
		//
		DocServiceinterface docIf1 = factory.createDocServiceinterface();
		docIf1.setDescription("if1 description");
		docIf1.setDocArgument("if1 Argument");
		docIf1.setDocReturn("if1 Return");
		docIf1.setDocException("if1 Exception");
		docIf1.setDocPreCondition("if1 PreCond");
		docIf1.setDocPostCondition("if1 PostCond");
		serviceIF1.setDoc(docIf1);
		service1.getServiceInterface().add(serviceIF1);
		//
		ServiceinterfaceExt serviceIF2 = factory.createServiceinterfaceExt();
		serviceIF2.setType("IF2Type");
		serviceIF2.setIdlFile("IF2Idlfile.idl");
		serviceIF2.setInstanceName("IF2Instance");
		serviceIF2.setName("S1IF2");
		serviceIF2.setDirection("Required");
		service1.getServiceInterface().add(serviceIF2);
		//
		TransmissionMethod trans = factory.createTransmissionMethod();
		trans.setKind("CORBA");
		service1.getTransMethods().add(trans);
		profile.getServicePorts().add(service1);
		//
		ServiceportExt service2 = factory.createServiceportExt();
		service2.setName("SrvPort2");
		service2.setPosition(Position.LEFT);
		TransmissionMethod trans2 = factory.createTransmissionMethod();
		trans2.setKind("TCP");
		service2.getTransMethods().add(trans2);
		DocServiceport serviceDoc2 = factory.createDocServiceport();
		serviceDoc2.setDescription("ServicePort2 description");
		serviceDoc2.setIfdescription("ServicePort2 I/F description");
		service2.setDoc(serviceDoc2);
		profile.getServicePorts().add(service2);
		//
		LanguageExt lang = factory.createLanguageExt();
		lang.setKind("Java");
		TargetEnvironment target = factory.createTargetEnvironment();
		target.setLangVersion("JDK6");
		target.setOs("Linux");
		target.getOsVersions().add("2.4");
		target.getOsVersions().add("2.6");
		target.getCpus().add("i386");
		target.getCpus().add("ARM");
		Library lib = factory.createLibrary();
		lib.setName("SampleLib");
		lib.setVersion("1.0");
		lib.setOther("Sample1");
		target.getLibraries().add(lib);
		Library lib2 = factory.createLibrary();
		lib2.setName("SampleLib2");
		lib2.setVersion("1.5");
		lib2.setOther("Sample2");
		target.getLibraries().add(lib2);
		lang.getTargets().add(target);
		profile.setLanguage(lang);
		
		return profile;
	}
	
	public RtcProfile generateProfileFull() {
		
		ObjectFactory factory = new ObjectFactory();
		RtcProfile profile =  factory.createRtcProfile();
		profile.setId("RTC:SampleVender:SampleCategory:SampleComponent:1.0.0");
		profile.setVersion("0.3");
		//Component
		////Basic
		BasicInfoExt basic = factory.createBasicInfoExt();
		basic.setName("SampleComponent");
		basic.setComponentType("STATIC");
		basic.setActivityType("PERIODIC");
		basic.setComponentKind("DataFlowComponent");
		basic.setRtcType("Normal");
		basic.setCategory("SampleCategory");
		basic.setDescription("SampleDescription");
		basic.setExecutionRate(Double.valueOf(1000.0d));
		basic.setExecutionType("PeriodicExecutionContext");
		basic.setMaxInstances(BigInteger.valueOf(1));
		basic.setVendor("SampleVendor");
		basic.setAbstract("SampleAbstract");
		basic.setVersion("1.0.0");
		basic.setHardwareProfile("hdProfile");
		basic.setCreationDate(createXMLGregorianCalendar(2008, 4, 18, 14, 0, 0));
		basic.setUpdateDate(createXMLGregorianCalendar("2008-04-18T14:00:00"));
		profile.setBasicInfo(basic);
		////Doc
		DocBasic docbasic = factory.createDocBasic();
		docbasic.setDescription("SampleBasicDecription");
		docbasic.setInout("SampleBasicInout");
		docbasic.setAlgorithm("SampleAlgorithm");
		docbasic.setCreator("SampleCreator");
		docbasic.setLicense("SampleLicense");
		docbasic.setReference("SampleReference");
		basic.setDoc(docbasic);
		////Ext
		basic.getVersionUpLogs().add("2008/04/18 14:00:00:Ver1.0");
		basic.getVersionUpLogs().add("2008/04/18 17:00:00:Ver1.1");
		basic.setComment("Basic Comment");
		basic.setSaveProject("Output Project");
		Property prop1 = factory.createProperty();
		prop1.setName("BasicPropKey1");
		prop1.setValue("BasicPropVal2");
		basic.getProperties().add(prop1);
		Property prop2 = factory.createProperty();
		prop2.setName("BasicPropKey2");
		prop2.setValue("BasicPropVal2");
		basic.getProperties().add(prop2);
		//
		//Actions
		Actions actions = factory.createActions();
		ActionStatusDoc oninit = factory.createActionStatusDoc();
		oninit.setImplementedbln(true);
		DocAction initdoc = factory.createDocAction();
		initdoc.setDescription("on_initialize description");
		initdoc.setPreCondition("on_initialize Pre_condition");
		initdoc.setPostCondition("on_initialize Post_condition");
		oninit.setDoc(initdoc);
		actions.setOnInitialize(oninit);
		//
		ActionStatusDoc onfinal = factory.createActionStatusDoc();
		onfinal.setImplementedbln(false);
		DocAction finaldoc = factory.createDocAction();
		finaldoc.setDescription("on_finalize description");
		finaldoc.setPreCondition("on_finalize Pre_condition");
		finaldoc.setPostCondition("on_finalize Post_condition");
		onfinal.setDoc(finaldoc);
		actions.setOnFinalize(onfinal);
		//
		ActionStatusDoc onstart = factory.createActionStatusDoc();
		onstart.setImplementedbln(false);
		DocAction startdoc = factory.createDocAction();
		startdoc.setDescription("on_startup description");
		startdoc.setPreCondition("on_startup Pre_condition");
		startdoc.setPostCondition("on_startup Post_condition");
		onstart.setDoc(startdoc);
		actions.setOnStartup(onstart);
		//
		ActionStatusDoc onshut = factory.createActionStatusDoc();
		onshut.setImplementedbln(true);
		DocAction shutdoc = factory.createDocAction();
		shutdoc.setDescription("on_shutdown description");
		shutdoc.setPreCondition("on_shutdown Pre_condition");
		shutdoc.setPostCondition("on_shutdown Post_condition");
		onshut.setDoc(shutdoc);
		actions.setOnShutdown(onshut);
		//
		ActionStatusDoc onact = factory.createActionStatusDoc();
		onact.setImplementedbln(true);
		DocAction actdoc = factory.createDocAction();
		actdoc.setDescription("on_activated description");
		actdoc.setPreCondition("on_activated Pre_condition");
		actdoc.setPostCondition("on_activated Post_condition");
		onact.setDoc(actdoc);
		actions.setOnActivated(onact);
		//
		ActionStatusDoc ondeact = factory.createActionStatusDoc();
		ondeact.setImplementedbln(false);
		DocAction deactdoc = factory.createDocAction();
		deactdoc.setDescription("on_deactivated description");
		deactdoc.setPreCondition("on_deactivated Pre_condition");
		deactdoc.setPostCondition("on_deactivated Post_condition");
		ondeact.setDoc(deactdoc);
		actions.setOnDeactivated(ondeact);
		//
		ActionStatusDoc onabort = factory.createActionStatusDoc();
		onabort.setImplementedbln(true);
		DocAction abortdoc = factory.createDocAction();
		abortdoc.setDescription("on_aborting description");
		abortdoc.setPreCondition("on_aborting Pre_condition");
		abortdoc.setPostCondition("on_aborting Post_condition");
		onabort.setDoc(abortdoc);
		actions.setOnAborting(onabort);
		//
		ActionStatusDoc onerrort = factory.createActionStatusDoc();
		onerrort.setImplementedbln(false);
		DocAction errordoc = factory.createDocAction();
		errordoc.setDescription("on_error description");
		errordoc.setPreCondition("on_error Pre_condition");
		errordoc.setPostCondition("on_error Post_condition");
		onerrort.setDoc(errordoc);
		actions.setOnError(onerrort);
		//
		ActionStatusDoc onreset = factory.createActionStatusDoc();
		onreset.setImplementedbln(false);
		DocAction resetdoc = factory.createDocAction();
		resetdoc.setDescription("on_reset description");
		resetdoc.setPreCondition("on_reset Pre_condition");
		resetdoc.setPostCondition("on_reset Post_condition");
		onreset.setDoc(resetdoc);
		actions.setOnReset(onreset);
		//
		ActionStatusDoc onexec = factory.createActionStatusDoc();
		onexec.setImplementedbln(false);
		DocAction execdoc = factory.createDocAction();
		execdoc.setDescription("on_execute description");
		execdoc.setPreCondition("on_execute Pre_condition");
		execdoc.setPostCondition("on_execute Post_condition");
		onexec.setDoc(execdoc);
		actions.setOnExecute(onexec);
		//
		ActionStatusDoc onstate = factory.createActionStatusDoc();
		onstate.setImplementedbln(false);
		DocAction statedoc = factory.createDocAction();
		statedoc.setDescription("on_state_update description");
		statedoc.setPreCondition("on_state_update Pre_condition");
		statedoc.setPostCondition("on_state_update Post_condition");
		onstate.setDoc(statedoc);
		actions.setOnStateUpdate(onstate);
		//
		ActionStatusDoc onrate = factory.createActionStatusDoc();
		onrate.setImplementedbln(false);
		DocAction rateedoc = factory.createDocAction();
		rateedoc.setDescription("on_rate_changed description");
		rateedoc.setPreCondition("on_rate_changed Pre_condition");
		rateedoc.setPostCondition("on_rate_changed Post_condition");
		onrate.setDoc(rateedoc);
		actions.setOnRateChanged(onrate);
		//
		ActionStatusDoc onaction = factory.createActionStatusDoc();
		onaction.setImplementedbln(false);
		DocAction actiondoc = factory.createDocAction();
		actiondoc.setDescription("on_action description");
		actiondoc.setPreCondition("on_action Pre_condition");
		actiondoc.setPostCondition("on_action Post_condition");
		onaction.setDoc(actiondoc);
		actions.setOnAction(onaction);
		//
		ActionStatusDoc onmodechange = factory.createActionStatusDoc();
		onmodechange.setImplementedbln(false);
		DocAction modedoc = factory.createDocAction();
		modedoc.setDescription("on_mode_cahnge description");
		modedoc.setPreCondition("on_mode_cahnge Pre_condition");
		modedoc.setPostCondition("on_mode_cahnge Post_condition");
		onmodechange.setDoc(modedoc);
		actions.setOnModeChanged(onmodechange);
		//
		profile.setActions(actions);
		//
		//DataPort
		////Basic
		DataportExt dataport1 = factory.createDataportExt();
		dataport1.setPortType("DataInPort");
		dataport1.setName("inport1");
		dataport1.setType("RTC::TimedLong");
		dataport1.setIdlFile("DataPort1.idl");
		dataport1.setInterfaceType("CorbaPort");
		dataport1.setDataflowType("Push,Pull");
		dataport1.setSubscriptionType("Periodic,New,Flush");
		dataport1.setUnit("dp1_unit");
		////Doc
		DocDataport docdatp1 = factory.createDocDataport();
		docdatp1.setDescription("In1Description");
		docdatp1.setType("In1Type");
		docdatp1.setNumber("In1Number");
		docdatp1.setSemantics("In1Semantics");
		docdatp1.setUnit("In1Unit");
		docdatp1.setOccerrence("In1Occerrence");
		docdatp1.setOperation("In1Operation");
		dataport1.setDoc(docdatp1);
		////Ext
		dataport1.setPosition(Position.LEFT);
		dataport1.setVariableName("In1Var");
		dataport1.setComment("dp1_comment");
		profile.getDataPorts().add(dataport1);
		prop1 = factory.createProperty();
		prop1.setName("Dp1PropKey1");
		prop1.setValue("Dp1PropVal2");
		dataport1.getProperties().add(prop1);
		prop2 = factory.createProperty();
		prop2.setName("Dp1PropKey2");
		prop2.setValue("Dp1PropVal2");
		dataport1.getProperties().add(prop2);
		//
		DataportExt dataport2 = factory.createDataportExt();
		dataport2.setPortType("DataInPort");
		dataport2.setName("inport2");
		dataport2.setType("RTC::TimedDouble");
		dataport2.setVariableName("In2Var");
		dataport2.setPosition(Position.LEFT);
		dataport2.setInterfaceType("CorbaPort");
		dataport2.setDataflowType("Push,Pull");
		dataport2.setSubscriptionType("New,Periodic");
		profile.getDataPorts().add(dataport2);
		//
		////Basic
		DataportExt dataport3 = factory.createDataportExt();
		dataport3.setPortType("DataOutPort");
		dataport3.setName("outport1");
		dataport3.setType("RTC::TimedLong");
		dataport3.setInterfaceType("CorbaPort");
		dataport3.setDataflowType("Push");
		dataport3.setSubscriptionType("New,Periodic");
		dataport3.setIdlFile("dp1out.idl");
		dataport3.setUnit("dp1out_unit");
		////Doc
		DocDataport docdatp3 = factory.createDocDataport();
		docdatp3.setDescription("Out1Description");
		docdatp3.setType("Out1Type");
		docdatp3.setNumber("Out1Number");
		docdatp3.setSemantics("Out1Semantics");
		docdatp3.setUnit("Out1Unit");
		docdatp3.setOccerrence("Out1Occerrence");
		docdatp3.setOperation("Out1Operation");
		dataport3.setDoc(docdatp3);
		////Ext
		dataport3.setVariableName("Out1Var");
		dataport3.setPosition(Position.RIGHT);
		dataport3.setComment("dp3_comment");
		prop1 = factory.createProperty();
		prop1.setName("Dp3PropKey1");
		prop1.setValue("Dp3PropVal1");
		dataport3.getProperties().add(prop1);
		prop2 = factory.createProperty();
		prop2.setName("Dp3PropKey2");
		prop2.setValue("Dp3PropVal2");
		dataport3.getProperties().add(prop2);
		profile.getDataPorts().add(dataport3);
		//
		DataportExt dataport4 = factory.createDataportExt();
		dataport4.setPortType("DataOutPort");
		dataport4.setName("outport2");
		dataport4.setType("RTC::TimedDouble");
		dataport4.setVariableName("Out2Var");
		dataport4.setPosition(Position.RIGHT);
		dataport4.setInterfaceType("CorbaPort");
		dataport4.setDataflowType("Push,Pull");
		dataport4.setSubscriptionType("New,Periodic");
		profile.getDataPorts().add(dataport4);
		//ServicePort
		////Baisc
		ServiceportExt service1 = factory.createServiceportExt();
		service1.setName("SrvPort1");
		TransmissionMethod trans1 = factory.createTransmissionMethod();
		trans1.setKind("CORBA");
		service1.getTransMethods().add(trans1);
		TransmissionMethod trans2 = factory.createTransmissionMethod();
		trans2.setKind("TCP/IP");
		service1.getTransMethods().add(trans2);
		////Doc
		DocServiceport serviceDoc1 = factory.createDocServiceport();
		serviceDoc1.setDescription("ServicePort1 description");
		serviceDoc1.setIfdescription("ServicePort1 I/F description");
		service1.setDoc(serviceDoc1);
		////Ext
		service1.setPosition(Position.LEFT);
		service1.setComment("srv1_comment");
		prop1 = factory.createProperty();
		prop1.setName("Srv1PropKey1");
		prop1.setValue("Srv1PropVal2");
		service1.getProperties().add(prop1);
		prop2 = factory.createProperty();
		prop2.setName("Srv1PropKey2");
		prop2.setValue("Srv1PropVal2");
		service1.getProperties().add(prop2);
		//
		//ServiceInterface
		////Basic
		ServiceinterfaceExt serviceIF1 = factory.createServiceinterfaceExt();
		serviceIF1.setName("S1IF1");
		serviceIF1.setDirection("Provided");
		serviceIF1.setInstanceName("IF1Instance");
		serviceIF1.setIdlFile("IF1Idlfile.idl");
		serviceIF1.setType("IF1Type");
		////Doc
		DocServiceinterface docIf1 = factory.createDocServiceinterface();
		docIf1.setDescription("if1 Description");
		docIf1.setDocArgument("if1 Argument");
		docIf1.setDocReturn("if1 Return");
		docIf1.setDocException("if1 Exception");
		docIf1.setDocPreCondition("if1 PreCond");
		docIf1.setDocPostCondition("if1 PostCond");
		serviceIF1.setDoc(docIf1);
		////Ext
		serviceIF1.setVariableName("IF1VarName");
		serviceIF1.setComment("srv1_comment");
		prop1 = factory.createProperty();
		prop1.setName("IF1PropKey1");
		prop1.setValue("IF1PropVal1");
		serviceIF1.getProperties().add(prop1);
		prop2 = factory.createProperty();
		prop2.setName("IF1PropKey2");
		prop2.setValue("IF1PropVal2");
		serviceIF1.getProperties().add(prop2);
		service1.getServiceInterface().add(serviceIF1);
		//
		//
		ServiceinterfaceExt serviceIF2 = factory.createServiceinterfaceExt();
		serviceIF2.setName("S1IF2");
		serviceIF2.setDirection("Required");
		serviceIF2.setInstanceName("IF2Instance");
		serviceIF2.setVariableName("IF2VarName");
		serviceIF2.setIdlFile("IF2Idlfile.idl");
		serviceIF2.setType("IF2Type");
		service1.getServiceInterface().add(serviceIF2);
		profile.getServicePorts().add(service1);
		//
		ServiceportExt service2 = factory.createServiceportExt();
		service2.setName("SrvPort2");
		service2.setPosition(Position.RIGHT);
		DocServiceport serviceDoc2 = factory.createDocServiceport();
		serviceDoc2.setDescription("ServicePort2 description");
		serviceDoc2.setIfdescription("ServicePort2 I/F description");
		service2.setDoc(serviceDoc2);
		profile.getServicePorts().add(service2);
		//
		//
		////Basic
		ConfigurationSet configset = factory.createConfigurationSet();
		ConfigurationExt config = factory.createConfigurationExt();
		config.setName("config1");
		config.setType("int");
		config.setDefaultValue("1");
		config.setUnit("conf1_unit");
		////Doc
		DocConfiguration docconfig = factory.createDocConfiguration(); 
		docconfig.setDataname("dataname1");
		docconfig.setDefaultValue("default1");
		docconfig.setDescription("config_Desc1");
		docconfig.setUnit("config_unit1");
		docconfig.setRange("config_range1");
		docconfig.setConstraint("config_constraint1");
		config.setDoc(docconfig);
		//
		config.setVariableName("var1");
		config.setComment("conf1_comment");
		prop1 = factory.createProperty();
		prop1.setName("Conf1PropKey1");
		prop1.setValue("Conf1PropVal1");
		config.getProperties().add(prop1);
		prop2 = factory.createProperty();
		prop2.setName("Conf1PropKey2");
		prop2.setValue("Conf1PropVal2");
		config.getProperties().add(prop2);
		configset.getConfiguration().add(config);
		//
		ConfigurationExt config2 = factory.createConfigurationExt();
		config2.setName("config2");
		config2.setType("String");
		config2.setVariableName("var2");
		config2.setDefaultValue("Sample");
		configset.getConfiguration().add(config2);
		//
		profile.setConfigurationSet(configset);
		//
		LanguageExt lang = factory.createLanguageExt();
		lang.setKind("Java");
		TargetEnvironment target = factory.createTargetEnvironment();
		target.setLangVersion("JDK6");
		target.setOs("Linux");
		target.getOsVersions().add("2.4");
		target.getOsVersions().add("2.6");
		target.getCpus().add("i386");
		target.getCpus().add("ARM");
		Library lib = factory.createLibrary();
		lib.setName("SampleLib1");
		lib.setVersion("1.0");
		lib.setOther("Sample1");
		target.getLibraries().add(lib);
		Library lib2 = factory.createLibrary();
		lib2.setName("SampleLib2");
		lib2.setVersion("1.5");
		lib2.setOther("Sample2");
		target.getLibraries().add(lib2);
		lang.getTargets().add(target);
		profile.setLanguage(lang);
		
		return profile;
	}

}
