package jp.go.aist.rtm.rtcbuilder.generator.param.idl;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;

/**
 * IDLファイルをあらわすクラス
 */
public class IdlFileParam implements Serializable {
	private static final long serialVersionUID = 5842336537244727703L;
	
	private String idlPath;
	private List<ServiceClassParam> serviceClass = new ArrayList<ServiceClassParam>();
	private List<ServiceClassParam> testServiceClass = new ArrayList<ServiceClassParam>();
	private RtcParam parent;
	private List<String> idlSearchPathes = new ArrayList<String>();
	private List<String> includeIdlPathes = new ArrayList<String>();
	private boolean isDataPort = false;
	private List<String> targetTypes = new ArrayList<String>();

	public IdlFileParam() {
	}

	public IdlFileParam(String idlPath, RtcParam parent) {
		this.idlPath = idlPath;
		this.parent = parent;
	}

	public RtcParam getParent() {
		return parent;
	}

	public void setParent(RtcParam parent) {
		this.parent = parent;
	}

	public List<ServiceClassParam> getServiceClassParams() {
		return serviceClass;
	}

	public void addServiceClassParams(	ServiceClassParam serviceClassParam) {
		this.serviceClass.add(serviceClassParam);
	}

	public void setServiceClassParams(	List<ServiceClassParam> serviceClassParamList) {
		this.serviceClass = serviceClassParamList;
	}

	public List<ServiceClassParam> getTestServiceClassParams() {
		return testServiceClass;
	}

	public void addTestServiceClassParams(	ServiceClassParam serviceClassParam) {
		this.testServiceClass.add(serviceClassParam);
	}
	
	public String getIdlFile() {
		File file = new File( idlPath);
		return file.getName();
	}

	public String getIdlFileNoExt() {
		File file = new File(idlPath);
		String fileName = file.getName();
		if(fileName == null) return "";
		
		int index = fileName.lastIndexOf('.');
		if(index>0 && index<fileName.length()-1) {
			return fileName.substring(0,index);
		}
		return "";
	}

	public String getIdlPath() {
		return idlPath;
	}

	public void setIdlPath(String idlPath) {
		this.idlPath = idlPath;
	}

	public List<String> getIdlSearchPathes() {
		return idlSearchPathes;
	}

	public void setIdlSearchPathes(List<String> idlFiles) {
		this.idlSearchPathes = idlFiles;
	}

	public List<String> getIncludeIdlPathes() {
		return includeIdlPathes;
	}

	public List<IdlFileParam> getIncludeIdlParams() {
		List<IdlFileParam> result = new ArrayList<IdlFileParam>();
		for (String s : includeIdlPathes) {
			result.add(new IdlFileParam(s, this.parent));
		}
		return result;
	}

	public List<IdlFileParam> getIncludeIdlParamsWithoutDefault() {
		List<IdlFileParam> result = new ArrayList<IdlFileParam>();
		String defaultPath = System.getenv("RTM_ROOT");
		for (String s : includeIdlPathes) {
			if(s.startsWith(defaultPath)) continue;
			result.add(new IdlFileParam(s, this.parent));
		}
		return result;
	}
	
	public boolean isDataPort() {
		return isDataPort;
	}
	public void setDataPort(boolean isDataPort) {
		this.isDataPort = isDataPort;
	}

	public List<String> getTargetType() {
		return targetTypes;
	}
	
	public boolean checkDefault() {
		String defaultPath = System.getenv("RTM_ROOT");
		return idlPath.startsWith(defaultPath);
	}
}
