package jp.go.aist.rtm.rtcbuilder.fsm;

import jp.go.aist.rtm.rtcbuilder.generator.param.EventPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;

public class TransitionParam {
	private String event;
	private String condition;
	private String target;
	private String source;
	private EventParam eventParam; 
	
	public TransitionParam() {
		this.event = "";
		this.condition = "";
		this.source = "";
		this.target = "";
	}
	
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		if(event==null) event = "";
		this.event = event;
	}
	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		if(condition==null) condition = "";
		this.condition = condition;
	}
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		if(source==null) source = "";
		this.source = source;
	}
	
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		if(target==null) target = "";
		this.target = target;
	}

	public EventParam getEventParam() {
		return eventParam;
	}
	public void setEventParam(EventParam eventParam) {
		this.eventParam = eventParam;
	}
	public boolean existEventParam() {
		return eventParam != null;
	}
	
	public String getDataType() {
		if(this.eventParam==null) {
			return "RTC::TimedLong";
		}
		if(this.eventParam.getDataType()==null || this.eventParam.getDataType().length()==0) {
			return "RTC::TimedLong";
		}
		return this.eventParam.getDataType();
	}
	
	public boolean existDataType() {
		if(this.eventParam==null) return false;
		if(this.eventParam.getDataType()==null || this.eventParam.getDataType().length()==0) return false;
		return true;
	}
	
	public void searchEventParam(RtcParam rtcParam) {
		String transName = this.event;
		if(transName == null) transName = "";
		
		String transCondition = this.condition;
		if(transCondition == null) transCondition = "";
		
		String transSource = this.source;
		if(transSource == null) transSource = "";
		
		String transTarget = this.target;
		if(transTarget == null) transTarget = "";

		EventPortParam eventPort = rtcParam.getEventport();
		if(eventPort==null) return;
		for(EventParam event : eventPort.getEvents()) {
			if(event.getName().equals(transName)
					&& event.getCondition().equals(transCondition)
					&& event.getSource().equals(transSource)
					&& event.getTarget().equals(transTarget) ) {
				this.eventParam = event;
				break;
			}
		}
	}
}
