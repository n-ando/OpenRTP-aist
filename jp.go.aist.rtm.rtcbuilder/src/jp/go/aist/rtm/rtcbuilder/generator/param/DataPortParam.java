package jp.go.aist.rtm.rtcbuilder.generator.param;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * データポートを表すクラス
 */
public class DataPortParam extends PortBaseParam implements Serializable {

	private static final long serialVersionUID = 533482869407934298L;

	private String name;
	private String type;
	private String varname;
	private String idlFile;
	private String dispIdlFile;
	private String dataFlowType;
	private String interfaceType;
	private String subscriptionType;
	private String unit;
	private String constraint;
	//
	private String doc_description;
	private String doc_type;
	private String doc_num;
	private String doc_semantics;
	private String doc_unit;
	private String doc_occurrence;
	private String doc_operation;
	//Properties
	private List<PropertyParam> properties = new ArrayList<PropertyParam>();

	public DataPortParam() {
		this("", "", "", "", "", 0);
	}

	public DataPortParam(String name, String type, String varname, int selection) {
		this(name, type, varname, "", "", selection);
	}

	public DataPortParam(String name, String type, String varname,
			String constraint, int selection) {
		this(name, type, varname, constraint, "", selection);
	}

	public DataPortParam(String name, String type, String varname,
			String constraint, String unit, int selection) {
		this.name = name;
		this.type = type;
		this.varname = varname;
		this.position = DataPortParam.COMBO_ITEM[selection];
		this.constraint = constraint;
		this.unit = unit;
		this.selection = selection;
		//
		this.doc_description = "";
		this.doc_type = "";
		this.doc_num = "";
		this.doc_semantics = "";
		this.doc_unit = "";
		this.doc_occurrence = "";
		this.doc_operation = "";
		//
		this.idlFile = "";
		this.dispIdlFile = "";
		this.dataFlowType = "";
		this.interfaceType = "";
		this.subscriptionType = "";
		//
		setUpdated(false);
	}

	public DataPortParam(String name, String type, String varname,
			String selection, String doc_description, String doc_type,
			String doc_num, String doc_semantics, String doc_unit,
			String doc_occurrence, String doc_operation) {
		this.name = name;
		this.type = type;
		this.varname = varname;
		this.setPosition(selection);
		//
		this.doc_description = doc_description;
		this.doc_type = doc_type;
		this.doc_num = doc_num;
		this.doc_semantics = doc_semantics;
		this.doc_unit = doc_unit;
		this.doc_occurrence = doc_occurrence;
		this.doc_operation = doc_operation;
		//
		this.idlFile = "";
		this.dispIdlFile = "";
		this.dataFlowType = "";
		this.interfaceType = "";
		this.subscriptionType = "";
		this.constraint = "";
		//
		setUpdated(false);
	}

	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public String getVarName() {
		return varname;
	}
	public String getIdlFile() {
		return idlFile;
	}
	public String getDispIdlFile() {
		return dispIdlFile;
	}
	public String getDataFlowType() {
		return dataFlowType;
	}
	public String getInterfaceType() {
		return interfaceType;
	}
	public String getSubscriptionType() {
		return subscriptionType;
	}
	public String getConstraint() {
		return constraint;
	}
	public String getUnit() {
		return unit;
	}
	//
	public String getTmplVarName() {
		if( varname==null || varname.equals(""))
			return this.name;
		return this.varname;
	}
	//
	public void setName(String name) {
		checkUpdated(this.name, name);
		this.name = name;
	}
	public void setType(String type) {
		checkUpdated(this.type, type);
		this.type = type;
	}
	public void setVarName(String varname) {
		checkUpdated(this.varname, varname);
		this.varname = varname;
	}
	public void setDispIdlFile(String file) {
		this.dispIdlFile = file;
	}
	public void setIdlFile(String file) {
		checkUpdated(this.idlFile, file);
		this.idlFile = file;
	}
	public void setDataFlowType(String dataflow) {
		checkUpdated(this.dataFlowType, dataflow);
		this.dataFlowType = dataflow;
	}
	public void setInterfaceType(String type) {
		checkUpdated(this.interfaceType, type);
		this.interfaceType = type;
	}
	public void setSubscriptionType(String type) {
		checkUpdated(this.subscriptionType, type);
		this.subscriptionType = type;
	}
	public void setConstraint(String constraint) {
		checkUpdated(this.constraint, constraint);
		this.constraint = constraint;
	}
	public void setUnit(String unit) {
		checkUpdated(this.unit, unit);
		this.unit = unit;
	}
	//
	public String getDocDescription() {
		return doc_description;
	}
	public String getDocType() {
		return doc_type;
	}
	public String getDocNum() {
		return doc_num;
	}
	public String getDocSemantics() {
		return doc_semantics;
	}
	public String getDocUnit() {
		return doc_unit;
	}
	public String getDocOccurrence() {
		return doc_occurrence;
	}
	public String getDocOperation() {
		return doc_operation;
	}
	//
	public void setDocDescription(String description) {
		checkUpdated(this.doc_description, description);
		this.doc_description = description;
	}
	public void setDocType(String type) {
		checkUpdated(this.doc_type, type);
		this.doc_type = type;
	}
	public void setDocNum(String num) {
		checkUpdated(this.doc_num, num);
		this.doc_num = num;
	}
	public void setDocSemantics(String semantics) {
		checkUpdated(this.doc_semantics, semantics);
		this.doc_semantics = semantics;
	}
	public void setDocUnit(String unit) {
		checkUpdated(this.doc_unit, unit);
		this.doc_unit = unit;
	}
	public void setDocOccurrence(String occurrence) {
		checkUpdated(this.doc_occurrence, occurrence);
		this.doc_occurrence = occurrence;
	}
	public void setDocOperation(String operation) {
		checkUpdated(this.doc_operation, operation);
		this.doc_operation = operation;
	}
	//
	public List<PropertyParam> getProperties() {
		return properties;
	}

}
