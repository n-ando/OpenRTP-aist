package jp.go.aist.rtm.rtcbuilder.fsm;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;

public class StateParam {
	private String initialState; //SubStateの開始ノード
	private int history;
	private String historyNodeName;
	private String name;
	private String parentName;
	private boolean hasEntry;
	private boolean hasExit;
	private boolean isInitial;
	private boolean isFinal;
	
	private List<StateParam> stateList = new ArrayList<StateParam>(); 
	private List<TransitionParam> transList = new ArrayList<TransitionParam>(); 

	private List<StateParam> allStateList = new ArrayList<StateParam>(); 
	private List<TransitionParam> allTransList = new ArrayList<TransitionParam>(); 
	
	public String getInitialState() {
		return initialState;
	}
	public void setInitialState(String initialState) {
		this.initialState = initialState;
	}
	
	public int getHistory() {
		return history;
	}
	public void setHistory(int history) {
		this.history = history;
	}
	
	public String getHistoryNodeName() {
		return historyNodeName;
	}
	public void setHistoryNodeName(String historyNodeName) {
		this.historyNodeName = historyNodeName;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	public boolean isHasEntry() {
		return hasEntry;
	}
	public void setHasEntry(boolean hasEntry) {
		this.hasEntry = hasEntry;
	}
	
	public boolean isHasExit() {
		return hasExit;
	}
	public void setHasExit(boolean hasExit) {
		this.hasExit = hasExit;
	}
	
	public boolean isInitial() {
		return isInitial;
	}
	public void setInitial(boolean initial) {
		this.isInitial = initial;
	}
	
	public boolean isFinal() {
		return isFinal;
	}
	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}
	
	public List<StateParam> getStateList() {
		return stateList;
	}
	public List<TransitionParam> getTransList() {
		return transList;
	}
	
	public List<StateParam> getAllStateList() {
		return allStateList;
	}
	
	public List<StateParam> getAllValidStateList() {
		List<StateParam> result = new ArrayList<StateParam>();
		for(StateParam each : allStateList) {
			if(each.isInitial() || each.isFinal()) continue;
			result.add(each);
		}
		return result;
	}
	public List<TransitionParam> getAllTransList() {
		return allTransList;
	}
	
	public StateParam getStateParam(String stateName) {
		StateParam result = null;
		for(StateParam each : allStateList) {
			if(each.getName().equals(stateName)) {
				result = each;
				break;
			}
		}
		return result;
		
	}
	
	public void setEventParam(RtcParam rtcParam) {
		for(TransitionParam each : allTransList) {
			each.searchEventParam(rtcParam);
		}
	}
	
	public boolean existInitialState() {
		boolean result = false;
		if(stateList==null || stateList.size()==0) return true;
		
		for(StateParam state : stateList) {
			if(state.existInitialState()==false) return false;
			if(state.isInitial) {
				result = true;
			}
		}
		return result;
	}
}
