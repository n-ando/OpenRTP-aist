package jp.go.aist.rtm.rtcbuilder.generator.param;

import java.util.ArrayList;
import java.util.List;

public class DataTypeParam {
	private String fullPath;
	private String dispPath;
	private String content;
	private List<String> definedTypes;
	private boolean isDefault;
	
	public DataTypeParam() {
		fullPath = "";
		content = "";
		definedTypes = new ArrayList<String>();
		isDefault = false;
	}

	public DataTypeParam(String idlPath) {
		fullPath = idlPath;
		content = "";
		definedTypes = new ArrayList<String>();
		isDefault = false;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}
	
	public String getDispPath() {
		return dispPath;
	}

	public void setDispPath(String dispPath) {
		this.dispPath = dispPath;
	}

	public List<String> getDefinedTypes() {
		return definedTypes;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
}
