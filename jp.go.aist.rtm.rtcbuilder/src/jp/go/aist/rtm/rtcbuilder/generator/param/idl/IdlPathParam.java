package jp.go.aist.rtm.rtcbuilder.generator.param.idl;

import java.io.Serializable;

public class IdlPathParam implements Serializable {

	private static final long serialVersionUID = 2531043621817335163L;
	
	private String path;
	private String dispPath;
	private boolean isDefault;
	
	public IdlPathParam() {
	}

	public IdlPathParam(String path, boolean isDefault) {
		this.path = path;
		this.dispPath = "";
		this.isDefault = isDefault;
	}
	
	public IdlPathParam(String path, String dispPath, boolean isDefault) {
		this.path = path;
		this.dispPath = dispPath;
		this.isDefault = isDefault;
	}
	
	public String getPath() {
		return path;
	}
	
	public String getDispPath() {
		return dispPath;
	}
	
	public boolean isDefault() {
		return isDefault;
	}
}
