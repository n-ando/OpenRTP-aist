package jp.go.aist.rtm.rtcbuilder.generator.param;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * サービスポートを表すクラス
 */
public class ServicePortInterfaceParam extends AbstractRecordedParam implements
		Serializable {

	private static final long serialVersionUID = -6299754191292510189L;

	public static final String INTERFACE_DIRECTION_PROVIDED = "Provided";
	public static final String INTERFACE_DIRECTION_REQUIRED = "Required";
	public static final String[] COMBO_ITEM = 
		new String[] {INTERFACE_DIRECTION_PROVIDED, INTERFACE_DIRECTION_REQUIRED};
	private int selection = 0;

	private ServicePortParam parent;
	private String name;
	private String direction;
	private String instancename;
	private String varname;
	private String idlfile;
	private String idlDispfile;
	private String interfacetype;
	//
	private String doc_description;
	private String doc_argument;
	private String doc_return;
	private String doc_exception;
	private String doc_pre_condition;
	private String doc_post_condition;
	//Properties
	private List<PropertyParam> properties = new ArrayList<PropertyParam>();

	public ServicePortInterfaceParam(ServicePortParam parent) {
		this.parent = parent;
		this.name = "";
		this.instancename = "";
		this.varname = "";
		this.idlfile = "";
		this.idlDispfile = "";
		this.interfacetype = "";
		this.selection = 0;
		this.direction = "";
		//
		this.doc_description = "";
		this.doc_argument = "";
		this.doc_return = "";
		this.doc_exception = "";
		this.doc_pre_condition = "";
		this.doc_post_condition = "";
		//
		setUpdated(false);
	}

	public ServicePortInterfaceParam(ServicePortParam parent, String name,
			String instancename, String varname, String idlfile,
			String interfacetype, int selection) {
		this.parent = parent;
		this.name = name;
		this.instancename = instancename;
		this.varname = varname;
		this.idlfile = idlfile;
		this.idlDispfile = "";
		this.interfacetype = interfacetype;
		this.selection = selection;
		this.direction = ServicePortInterfaceParam.COMBO_ITEM[selection];
		//
		this.doc_description = "";
		this.doc_argument = "";
		this.doc_return = "";
		this.doc_exception = "";
		this.doc_pre_condition = "";
		this.doc_post_condition = "";
		//
		setUpdated(false);
	}

	public ServicePortParam getParent() {
		return parent;
	}
	public String getName() {
		return name;
	}
	public String getDirection() {
		return direction;
	}
	public String getInstanceName() {
		return instancename;
	}
	public String getVarName() {
		return varname;
	}
	public String getIdlFile() {
		return idlfile;
	}
	public String getIdlDispFile() {
		return idlDispfile;
	}
	public String getInterfaceType() {
		return interfacetype;
	}
	public String getInterfaceRawType() {
		if(interfacetype.contains("::")) {
			String[] elem = interfacetype.split("::");
			return elem[elem.length-1];
		}
		return interfacetype;
	}
	public int getIndex() {
		return selection;
	}
	public String getIdlFullPath() {
		return idlfile;
	}
	//
	public String getTmplVarName() {
		if( this.varname!=null && !this.varname.equals(""))
			return this.varname;
		if( this.instancename!=null && !this.instancename.equals(""))
			return this.instancename;
		return this.name;
	}
	//
	public void setName(String name) {
		checkUpdated(this.name, name);
		this.name = name;
	}
	public void setInstanceName(String instancename) {
		checkUpdated(this.instancename, instancename);
		this.instancename = instancename;
	}
	public void setVarName(String varname) {
		checkUpdated(this.varname, varname);
		this.varname = varname;
	}
	public void setIdlFile(String idlfile) {
		checkUpdated(this.idlfile, idlfile);
		this.idlfile = idlfile;
	}
	public void setIdlDispFile(String idlfile) {
		this.idlDispfile = idlfile;
	}
	public void setInterfaceType(String interfacetype) {
		checkUpdated(this.interfacetype, interfacetype);
		this.interfacetype = interfacetype;
	}
	public void setIndex(int index) {
		if (this.selection != index) {
			setUpdated(true);
		}
		this.selection = index;
		this.direction = COMBO_ITEM[index];
	}
	public void setDirection(String direction) {
		int index = -1;
		if (direction.equals(COMBO_ITEM[1])) {
			index = 1;
		} else {
			index = 0;
		}
		setIndex(index);
	}
	//
	public String getDocDescription() {
		return doc_description;
	}
	public String getDocArgument() {
		return doc_argument;
	}
	public String getDocReturn() {
		return doc_return;
	}
	public String getDocException() {
		return doc_exception;
	}
	public String getDocPreCondition() {
		return doc_pre_condition;
	}
	public String getDocPostCondition() {
		return doc_post_condition;
	}

	public void setDocDescription(String description) {
		checkUpdated(this.doc_description, description);
		this.doc_description = description;
	}
	public void setDocArgument(String argument) {
		checkUpdated(this.doc_argument, argument);
		this.doc_argument = argument;
	}
	public void setDocReturn(String returnDesc) {
		checkUpdated(this.doc_return, returnDesc);
		this.doc_return = returnDesc;
	}
	public void setDocException(String exception) {
		checkUpdated(this.doc_exception, exception);
		this.doc_exception = exception;
	}
	public void setDocPreCondition(String precondition) {
		checkUpdated(this.doc_pre_condition, precondition);
		this.doc_pre_condition = precondition;
	}
	public void setDocPostCondition(String postcondition) {
		checkUpdated(this.doc_post_condition, postcondition);
		this.doc_post_condition = postcondition;
	}
	//
	public List<PropertyParam> getProperties() {
		return properties;
	}

}
