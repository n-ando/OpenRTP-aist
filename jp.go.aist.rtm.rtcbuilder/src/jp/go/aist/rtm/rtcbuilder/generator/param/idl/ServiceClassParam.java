package jp.go.aist.rtm.rtcbuilder.generator.param.idl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;

/**
 * サービスクラスをあらわすクラス
 */
public class ServiceClassParam implements Serializable {
	private static final long serialVersionUID = 6794300037580191558L;

	private String name;
	private String idlPath;
    private String idlFile;
    private String idlDispFile;
	private String module;
	private List<ServiceMethodParam> methods = new ArrayList<ServiceMethodParam>();
	private RtcParam parent;
	private Map<String,TypeDefParam> typeDef = new HashMap<String,TypeDefParam>();
	private List<String> superInterfaceList = new ArrayList<String>();

	public ServiceClassParam() {
	}

    public ServiceClassParam(String name, String filePath, String searchPath) {
		this.name = name;
        this.idlFile = filePath;
        this.idlPath = searchPath;
	}

	public List<ServiceMethodParam> getMethods() {
		return methods;
	}

	public String getName() {
		return name;
	}

	public void setName(String serviceName) {
		this.name = serviceName;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public RtcParam getParent() {
		return parent;
	}

	public void setParent(RtcParam parent) {
		this.parent = parent;
	}

	public String getIdlPath() {
		return this.idlPath;
	}

	public void setIdlPath(String idlPath) {
		this.idlPath = idlPath;
	}

    public String getIdlFile() {
        return idlFile;
    }

    public void setIdlFile(String idlFile) {
        this.idlFile = idlFile;
    }

	public String getIdlDispFile() {
		return idlDispFile;
	}

	public void setIdlDispFile(String idlDispFile) {
		this.idlDispFile = idlDispFile;
	}

	public Map<String,TypeDefParam> getTypeDef() {
		return this.typeDef;
	}

	public void setTypeDef(List<TypeDefParam> typeDef) {
		Iterator<TypeDefParam> iterator = typeDef.iterator();
		this.typeDef = new HashMap<String, TypeDefParam>();
		while(iterator.hasNext()) {
			TypeDefParam typedef = iterator.next();
//			this.typeDef.put(typedef.getTargetDef(), typedef.getOriginalDef());
			this.typeDef.put(typedef.getTargetDef(), typedef);
		}
//		this.typeDef = typeDef;
	}

	public List<TypeDefParam> getTypeDefList() {
		List<TypeDefParam> typeDefList = new ArrayList<TypeDefParam>();
		Set<String> keys = typeDef.keySet();
		Iterator<String> iterator = keys.iterator();
		while(iterator.hasNext()) {
			TypeDefParam typedef = new TypeDefParam();
			String key = iterator.next();
			String value = this.typeDef.get(key).getOriginalDef();
			typedef.setTargetDef(key);
			typedef.setOriginalDef(value);
			typeDefList.add(typedef);
		}
		return typeDefList;
	}

	public List<String> getSuperInterfaceList() {
		return superInterfaceList;
	}

}
