package jp.go.aist.rtm.rtcbuilder.fsm;

public class EventParam {
	private String name;
	private String dataType;
	private String condition;
	private String source;
	private String target;
	//
	private String doc_description;
	private String doc_type;
	private String doc_num;
	private String doc_semantics;
	private String doc_unit;
	private String doc_operation;
	
	public EventParam() {
		this.name = "";
		this.condition = "";
		this.source = "";
		this.target = "";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name==null) this.name = "";
		else this.name = name;
	}
	
	public String getDataType() {
		if(dataType==null) return "";
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDataTypeStr() {
		if(dataType==null || dataType.length()==0 ) return "";
		return dataType + " data";
	}
	
	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		if(condition==null) this.condition = "";
		else this.condition = condition;
	}

	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		if(source==null) this.source = "";
		else this.source = source;
	}

	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		if(target==null) this.target = "";
		else this.target = target;
	}
	/////
	public String getDoc_description() {
		return doc_description;
	}
	public void setDoc_description(String doc_description) {
		this.doc_description = doc_description;
	}
	public String getDoc_descriptionStr() {
		if(doc_description==null) return "";
		return doc_description;
	}
	
	public String getDoc_type() {
		return doc_type;
	}
	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}
	public String getDoc_typeStr() {
		if(doc_type==null) return "";
		return doc_type;
	}
	
	public String getDoc_num() {
		return doc_num;
	}
	public void setDoc_num(String doc_num) {
		this.doc_num = doc_num;
	}
	public String getDoc_numStr() {
		if(doc_num==null) return "";
		return doc_num;
	}
	
	public String getDoc_semantics() {
		return doc_semantics;
	}
	public void setDoc_semantics(String doc_semantics) {
		this.doc_semantics = doc_semantics;
	}
	public String getDoc_semanticsStr() {
		if(doc_semantics==null) return "";
		return doc_semantics;
	}
	
	public String getDoc_unit() {
		return doc_unit;
	}
	public void setDoc_unit(String doc_unit) {
		this.doc_unit = doc_unit;
	}
	public String getDoc_unitStr() {
		if(doc_unit==null) return "";
		return doc_unit;
	}
	
	public String getDoc_operation() {
		return doc_operation;
	}
	public void setDoc_operation(String doc_operation) {
		this.doc_operation = doc_operation;
	}
	public String getDoc_operationStr() {
		if(doc_operation==null) return "";
		return doc_operation;
	}
	
	public boolean checkSame(EventParam source) {
		if(this.name.equals(source.name)
				&& this.condition.equals(source.condition)
				&& this.source.equals(source.source)
				&& this.target.equals(source.target) ) {
			return true;
		}
		return false;
	}
	
	public void replaceContents(EventParam source) {
		this.name = source.name;
		this.dataType = source.dataType;
		//
		this.doc_description = source.doc_description;
		this.doc_type = source.doc_type;
		this.doc_num = source.doc_num;
		this.doc_semantics = source.doc_semantics;
		this.doc_unit = source.doc_unit;
		this.doc_operation = source.doc_operation;
	}
}
