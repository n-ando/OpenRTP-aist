package jp.go.aist.rtm.systemeditor.ui.dialog.param;

import java.util.ArrayList;
import java.util.List;

import org.openrtp.namespaces.rts.version02.Component;
import org.openrtp.namespaces.rts.version02.ConfigurationSet;

import jp.go.aist.rtm.systemeditor.corba.CORBAHelper;
import jp.go.aist.rtm.systemeditor.corba.CORBAHelper.CreateComponentParameter;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.dialog.RestoreComponentDialog.Endpoint;
import jp.go.aist.rtm.systemeditor.ui.dialog.RestoreComponentDialog.EndpointCache;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;

public class ComponentInfo {
	static final String MSG_STATUS_EP_UNREACHABLE = Messages
			.getString("RestoreComponentDialog.msg_ep_unreach");
	
	static final String PROP_IMPLEMENTATION_ID = "implementation_id";
	static final String PROP_INSTANCE_NAME = "instance_name";
	static final String PROP_LANGUAGE = "language";
	static final String PROP_VENDOR = "vendor";
	static final String PROP_CATEGORY = "category";
	static final String PROP_VERSION = "version";
	static final String PROP_CORBA_ENDPOINTS = "corba.endpoints";
	static final String PROP_MANAGER_NAME = "manager.instance_name";
	
	private boolean isRestore;
	private boolean isCreate;
	private String targetRTC;
	private boolean isActivate;
	private boolean isConfig;
	private boolean hasConfig;
	
	private String compName;
	private String compId;
	private String compRawId;
	private String node;
	private String containerName;
	private String status;
	private boolean isError;
	private List<ConnectorInfo> connectorList = new ArrayList<ConnectorInfo>();

	private CorbaComponent corbaComponent;
	private CorbaComponent selectedRTC;
	private Component profile;

	public CorbaComponent getCorbaComponent() {
		return corbaComponent;
	}
	public void setCorbaComponent(CorbaComponent corbaComponent) {
		this.corbaComponent = corbaComponent;
	}

	public ComponentInfo(CorbaComponent component, Component profile) {
		this.corbaComponent = component;
		this.profile = profile;
		
		this.isRestore = true;
		this.compName = component.getInstanceNameL();
		this.compRawId = component.getComponentId();
		if(profile.getConfigurationSets() == null ) {
			this.hasConfig = false;
			
		} else {
			List<ConfigurationSet> configList = profile.getConfigurationSets();
			if(configList.size() == 0) {
				this.hasConfig = false;
			} else {
				if(configList.get(0).getConfigurationData() == null
						|| configList.get(0).getConfigurationData().size() == 0) {
					this.hasConfig = false;
				} else {
					this.hasConfig = true;
				}
			}
		}
		this.isConfig = this.hasConfig;
		
		String lang = "";
		String vendor = "";
		String category = "";
		String version = "";

		String type = component.getProperty(PROP_IMPLEMENTATION_ID);
		if(type == null) {
			component.setProperty(CreateComponentParameter.KEY_INSTANCE_NAME, this.compName);
			String componentId = component.getComponentId();
			String elems[] = componentId.split(":");
			if(elems.length < 5) return;
			vendor = elems[1];
			category = elems[2];
			type = elems[3];
			component.setProperty(CreateComponentParameter.KEY_IMPLEMENTATION_ID, type);
			version = elems[4];
			
		} else {
			lang = component.getProperty(PROP_LANGUAGE);
			vendor = component.getProperty(PROP_VENDOR);
			category = component.getProperty(PROP_CATEGORY);
			version = component.getProperty(PROP_VERSION);
		}
		
		this.compId = "RTC:" + vendor + ":" + category + ":" + type + ":" + lang + ":" + version;
		
		String endPoints = component.getProperty(PROP_CORBA_ENDPOINTS);
		if(endPoints != null) {
			String[] epList = endPoints.split(",");
			StringBuilder builder = new StringBuilder();
			for(String each : epList) {
				if(0<builder.length()) {
					builder.append(",");
				}
				builder.append(getHostName(each));
			}
			this.node = builder.toString();
		}
		
		this.containerName = component.getProperty(PROP_MANAGER_NAME);
		//
		if (this.node == null) {
			// エンドポイントのプロパティ設定がない場合は、パスURIから取得(既存互換)
			String path = component.getPathId();
			if (path != null && !path.isEmpty()) {
				path = path.substring(0, path.indexOf("/"));
				this.node = path;
			}
		}
	}

	public void setTarget(RTC.RTObject rtc) {
		if (this.corbaComponent != null) {
			this.corbaComponent.setCorbaObject(rtc);
		}
	}

	public Component getProfile() {
		return profile;
	}

	public void setRestore(boolean isRestore) {
		this.isRestore = isRestore;
	}
	public boolean isRestore() {
		return isRestore;
	}

	public void setCreate(boolean isCreate) {
		this.isCreate = isCreate;
	}
	public boolean isCreate() {
		return isCreate;
	}

	public void setActivate(boolean isActivate) {
		this.isActivate = isActivate;
	}
	public boolean isActivate() {
		return isActivate;
	}
	
	public void setConfig(boolean isConfig) {
		this.isConfig = isConfig;
	}
	public boolean isConfig() {
		return isConfig;
	}
	public boolean isHasConfig() {
		return hasConfig;
	}

	public void setTargetRTC(String targetRTC) {
		this.targetRTC = targetRTC;
	}
	public String getTargetRTC() {
		return targetRTC;
	}

	public CorbaComponent getSelectedRTC() {
		return selectedRTC;
	}
	public void setSelectedRTC(CorbaComponent selectedRTC) {
		this.selectedRTC = selectedRTC;
	}
	
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getCompName() {
		return compName;
	}
	
	public void setCompId(String compId) {
		this.compId = compId;
	}
	public String getCompId() {
		return compId;
	}

	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}
	public String getContainerName() {
		return containerName;
	}
	
	public void setCompRawId(String compRawId) {
		this.compRawId = compRawId;
	}
	public String getCompRawId() {
		return compRawId;
	}

	public void setNode(String node) {
		this.node = node;
	}
	public String getNode() {
		return node;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	
	public void setError(boolean isError) {
		this.isError = isError;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "<" + this.compName + "|"
				+ this.compId + "|" + this.containerName + "|"
				+ this.node + "|"  + this.status + ">";
	}
	
	private String getHostName(String source) {
		String hostName = source;
		if(hostName.contains(":")) {
			String[] elems = source.split(":");
			hostName = elems[0];
		}
		return hostName;
	}
	
	public void addConnector(ConnectorInfo target) {
		this.connectorList.add(target);
	}
	public List<ConnectorInfo> getConnectorList() {
		return this.connectorList;
	}
	
	public void restoreComponent(EndpointCache endpoints, SystemDiagram diagram) {
		if(this.isRestore == false || this.isCreate == false) {
			return;
		}
		if(this.node==null || this.node.length()==0) {
			this.status = MSG_STATUS_EP_UNREACHABLE;
			this.isError = true;
			return;
		}
		Endpoint ep = null;
		String[] nodes = this.node.split(",");
		if(nodes.length==0) {
			this.status = MSG_STATUS_EP_UNREACHABLE;
			this.isError = true;
			return;
		}
		for(String node : nodes) {
			ep = endpoints.get(node);
			if(ep!=null) break;
		}
		if(ep==null) {
			this.status = MSG_STATUS_EP_UNREACHABLE;
			this.isError = true;
			return;
		}
		//
		RTCManager manager = ep.getManager();
		if(manager==null) {
			this.status = String.format("No manager, it can not create component: comp=<%s>", this.compId);
			this.isError = true;
			return;
		}
		try {
			RTC.RTObject rtobj = null;
			if (this.corbaComponent.isCompositeComponent()) {
				rtobj = CORBAHelper.factory().createCompositeRTObject(
						manager, this.corbaComponent, diagram);
			} else {
				rtobj = CORBAHelper.factory().createRTObject(
						manager, this.corbaComponent, diagram, this.containerName);
			}
			if (rtobj == null) {
				this.status = String.format("Fail to create rtobject: comp=<%s>", this.compId);
				this.isError = true;
				return;
			}
			this.corbaComponent.setCorbaObject(rtobj);
			this.selectedRTC = this.corbaComponent;
		} catch (Exception e1) {
			this.status = String.format("Fail to create rtobject: comp=<%s>", this.compId);
			this.isError = true;
			return;
		}
		this.status = String.format("Created: comp=<%s>", this.compId);
		this.isError = false;
	}
	
}
