package jp.go.aist.rtm.rtcbuilder.generator.param;

import java.io.Serializable;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.fsm.EventParam;

/**
 * FSMのイベントポートを表すクラス
 */
public class EventPortParam extends PortBaseParam implements Serializable {

	private static final long serialVersionUID = 8617430815004190346L;

	private String name;
	private String varname;
	
	private RecordedList<EventParam> events = new RecordedList<EventParam>();

	public EventPortParam() {
		this.name = "";
		this.varname = "";
	}
	
	public EventPortParam(String name, String varname, int selection) {
		this.name = name;
		this.varname = varname;
		this.selection = selection;
		this.position = DataPortParam.COMBO_ITEM[selection];
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getVarname() {
		return varname;
	}
	public void setVarname(String varname) {
		this.varname = varname;
	}

	public List<EventParam> getEvents() {
		return events;
	}
	
	public String getTmplVarName() {
		if( varname==null || varname.equals(""))
			return this.name;
		return this.varname;
	}
}
