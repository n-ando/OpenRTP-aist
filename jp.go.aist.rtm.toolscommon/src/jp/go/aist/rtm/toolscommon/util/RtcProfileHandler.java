package jp.go.aist.rtm.toolscommon.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortInterfaceProfile;
import jp.go.aist.rtm.toolscommon.profiles.util.XmlHandler;

import org.openrtp.namespaces.rtc.version03.BasicInfo;
import org.openrtp.namespaces.rtc.version03.BasicInfoExt;
import org.openrtp.namespaces.rtc.version03.Dataport;
import org.openrtp.namespaces.rtc.version03.DataportExt;
import org.openrtp.namespaces.rtc.version03.Property;
import org.openrtp.namespaces.rtc.version03.RtcProfile;
import org.openrtp.namespaces.rtc.version03.Serviceinterface;
import org.openrtp.namespaces.rtc.version03.ServiceinterfaceDoc;
import org.openrtp.namespaces.rtc.version03.Serviceport;
import org.openrtp.namespaces.rtc.version03.ServiceportExt;

//
/**
 * RTCプロファイルを読み込むクラス
 *
 */
public class RtcProfileHandler {
	private static final String PORTTYPE_IN = "DataInPort";
	private static final String SPEC_SUFFIX = "RTC";
	private static final String SPEC_MAJOR_SEPARATOR = ":";
	// private static final String SPEC_MINOR_SEPARATOR = ".";

	private static final String INTERFACE_DIRECTION_PROVIDED = "Provided";
	private static final String INTERFACE_DIRECTION_REQUIRED = "Required";

	private final String CATEGORY_COMPOSITE =  "composite.";

	public static boolean validateXml(String targetString) throws Exception {
		XmlHandler handler = new XmlHandler();
		try {
			handler.restoreFromXmlRtc(targetString);
		} catch (IOException e) {
			throw new Exception("XML Validation Error", e);
		}
		return true;
	}

	public ComponentSpecification createComponentFromXML(String targetXML) throws Exception {
		XmlHandler handler = new XmlHandler();
		RtcProfile profile = handler.restoreFromXmlRtc(targetXML);

		ComponentSpecification component = profile2ComponentEMF(profile, null);
		return component;
	}

	public ComponentSpecification createComponent(String targetFile) throws Exception  {
		//対象ファイルの読み込み
		StringBuffer tmp_sb = new StringBuffer();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(targetFile), "UTF-8")) ) {
			String tmp_str = null;
		    while((tmp_str = br.readLine()) != null){
		    	tmp_sb.append(tmp_str + "\r\n");
		    }
		}
	    ComponentSpecification component = createComponentFromXML(tmp_sb.toString());
		return component;
	}

	@SuppressWarnings("static-access")
	private ComponentSpecification profile2ComponentEMF(RtcProfile module,
			ComponentSpecification specification) throws Exception {
		if( !checkProfile(module) ) throw new Exception("Incorrect Profile.");
		if (specification == null) {
			specification = ComponentFactory.eINSTANCE.createComponentSpecification();
		}
		BasicInfo bi = module.getBasicInfo();
		specification.setInstanceNameL(bi.getName());
		specification.setTypeNameL(bi.getName());
		specification.setCategoryL(bi.getCategory());
		specification.setVenderL(bi.getVendor());
		specification.setVersionL(bi.getVersion());
		specification.setDescriptionL(bi.getDescription());
		specification.setRtcType(bi.getRtcType());
		specification.getPorts().addAll(profile2PortEMF(module));

		if (bi instanceof BasicInfoExt) {
			BasicInfoExt biExt = (BasicInfoExt) bi;
			for (Property p : biExt.getProperties()) {
				specification.setProperty(p.getName(), p.getValue());
			}
		}

		// BasicInfoからデフォルトのExecutionContextを作成
		jp.go.aist.rtm.toolscommon.model.component.ExecutionContext ec = ComponentFactory.eINSTANCE
				.createExecutionContext();
		String type = bi.getExecutionType();
		if ("PeriodicExecutionContext".equals(type)) {
			ec.setKindL(ec.KIND_PERIODIC);
		} else if ("ExtTrigExecutionContext".equals(type)) {
			ec.setKindL(ec.KIND_EVENT_DRIVEN);
		} else {
			ec.setKindL(ec.KIND_OTHER);
		}
		ec.setRateL(bi.getExecutionRate());
		ec.setOwner(specification);
		specification.getExecutionContexts().add(ec);
		specification.getExecutionContextHandler().sync();

		// Constraint設定とWidget設定に対応 2008.12.25
		RTCConfigurationParser parser = new RTCConfigurationParser();
		List<ConfigurationSet> configurationSets = parser.parse(module);
		if (configurationSets != null) {
			specification.getConfigurationSets().addAll(configurationSets);
			specification.setActiveConfigurationSet(parser
					.getActiveConfigurationSet());
		}
		
		specification.setProperty("language", module.getLanguage().getKind());

		String moduleId = SPEC_SUFFIX + SPEC_MAJOR_SEPARATOR
				+ specification.getVenderL() + SPEC_MAJOR_SEPARATOR
				+ specification.getCategoryL() + SPEC_MAJOR_SEPARATOR
				+ specification.getTypeNameL() + SPEC_MAJOR_SEPARATOR
				+ specification.getVersionL();
		specification.setComponentId(moduleId);

		return specification;
	}

	private boolean checkProfile(RtcProfile module) {
		if( !module.getBasicInfo().getCategory().startsWith(CATEGORY_COMPOSITE) ) return true;
		if( module.getDataPorts().size()>0 ) return false;
		if( module.getServicePorts().size()>0 ) return false;

		return true;
	}

	private List<Port> profile2PortEMF(RtcProfile module) {
		List<Port> list = new ArrayList<Port>();
		Port port = null;
		for (Dataport dp : module.getDataPorts()) {
			DataportExt dataPortExt = (DataportExt) dp;
			if (dataPortExt.getPortType().equals(PORTTYPE_IN)) {
				port = ComponentFactory.eINSTANCE.createInPort();
			} else {
				port = ComponentFactory.eINSTANCE.createOutPort();
			}
			port.setSynchronizer(ComponentFactory.eINSTANCE
					.createPortSynchronizer());
			port.setNameL(dataPortExt.getName());
			port.setDataType(dataPortExt.getType());
			for (Property p : dataPortExt.getProperties()) {
				port.getSynchronizer().setProperty(p.getName(), p.getValue());
			}
			list.add(port);
		}

		for (Serviceport sp : module.getServicePorts()) {
			ServiceportExt servicePortExt = (ServiceportExt) sp;
			port = ComponentFactory.eINSTANCE.createServicePort();
			port.setSynchronizer(ComponentFactory.eINSTANCE
					.createPortSynchronizer());
			port.setNameL(servicePortExt.getName());
			for (Property p : servicePortExt.getProperties()) {
				port.getSynchronizer().setProperty(p.getName(), p.getValue());
			}

			for (Serviceinterface si : servicePortExt.getServiceInterface()) {
				ServiceinterfaceDoc serviceIF = (ServiceinterfaceDoc) si;
				PortInterfaceProfile portIF = new PortInterfaceProfile();
				portIF.setInstanceName(serviceIF.getName());
				portIF.setTypeName(serviceIF.getType());
				if (serviceIF.getDirection().equals(
						INTERFACE_DIRECTION_PROVIDED)) {
					portIF.setProvidedPolarity();
				} else if (serviceIF.getDirection().equals(
						INTERFACE_DIRECTION_REQUIRED)) {
					portIF.setRequiredPolarity();
				}
				port.getInterfaces().add(portIF);
			}
			list.add(port);
		}
		return list;
	}

}
