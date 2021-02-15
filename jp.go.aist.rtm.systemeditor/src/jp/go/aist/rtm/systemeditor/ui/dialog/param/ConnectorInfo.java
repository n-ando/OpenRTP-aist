package jp.go.aist.rtm.systemeditor.ui.dialog.param;

import org.openrtp.namespaces.rts.version02.DataportConnector;
import org.openrtp.namespaces.rts.version02.ServiceportConnector;

public class ConnectorInfo {
	private boolean isConnect;
	private String sourceRTC;
	private String sourcePort;
	private String targetPort;
	private String targetRTC;
	private String dataType;
	private DataportConnector dataConnector;
	private ServiceportConnector serviceConnector;
	
	public void setConnect(boolean isConnect) {
		this.isConnect = isConnect;
	}
	public boolean isConnect() {
		return isConnect;
	}

	public String getSourceRTC() {
		return sourceRTC;
	}

	public String getSourcePort() {
		return sourcePort;
	}

	public String getTargetRTC() {
		return targetRTC;
	}

	public String getTargetPort() {
		return targetPort;
	}

	public String getDataType() {
		return dataType;
	}
	
	public DataportConnector getDataConnector() {
		return dataConnector;
	}
	public ServiceportConnector getServiceConnector() {
		return serviceConnector;
	}
	public ConnectorInfo(DataportConnector conn) {
		this.dataConnector = conn;
		
		this.sourceRTC = conn.getSourceDataPort().getComponentId();
		String srcPort = conn.getSourceDataPort().getPortName();
		String[] srvelems = srcPort.split("\\.");
		this.sourcePort = srvelems[1];
		
		this.targetRTC = conn.getTargetDataPort().getComponentId();
		String trgPort = conn.getTargetDataPort().getPortName();
		String[] trgelems = trgPort.split("\\.");
		this.targetPort = trgelems[1];
		
		this.dataType = conn.getDataType();
	}
	
	public ConnectorInfo(ServiceportConnector conn) {
		this.serviceConnector = conn;
		
		this.sourceRTC = conn.getSourceServicePort().getComponentId();
		String srcPort = conn.getSourceServicePort().getPortName();
		String[] srvelems = srcPort.split("\\.");
		this.sourcePort = srvelems[1];
		
		this.targetRTC = conn.getTargetServicePort().getComponentId();
		String trgPort = conn.getTargetServicePort().getPortName();
		String[] trgelems = trgPort.split("\\.");
		this.targetPort = trgelems[1];

		this.dataType = "";
	}
}
