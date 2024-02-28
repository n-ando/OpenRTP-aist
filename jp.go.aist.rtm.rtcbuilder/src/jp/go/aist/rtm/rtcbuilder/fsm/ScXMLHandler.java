package jp.go.aist.rtm.rtcbuilder.fsm;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;

import org.apache.commons.scxml.io.SCXMLParser;
import org.apache.commons.scxml.model.Executable;
import org.apache.commons.scxml.model.History;
import org.apache.commons.scxml.model.Initial;
import org.apache.commons.scxml.model.Log;
import org.apache.commons.scxml.model.SCXML;
import org.apache.commons.scxml.model.State;
import org.apache.commons.scxml.model.Transition;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ScXMLHandler {
	public StateParam parseSCXML(String source, StringBuffer buffer) {
		StateParam result = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(source), "UTF-8"));
			String tmp_str = null;
			String topTag = null;
		    while((tmp_str = br.readLine()) != null){
		    	buffer.append(tmp_str + "\r\n");
		    	if(topTag==null && tmp_str.trim().startsWith("<scxml")) {
		    		topTag = tmp_str;
		    	}
		    }
		    br.close();
		    
		    SCXML scxml = null;
		    try {
		    	scxml =  (SCXML)SCXMLParser.newInstance().parse(new InputSource(new CharArrayReader((buffer.toString()).toCharArray())));
			} catch (IOException | SAXException e1) {
				e1.printStackTrace();
			}
		    String topName = "";
		    if(topTag!=null) {
		    	String[] elems = topTag.split(" ");
		    	for(String elem : elems) {
		    		if(elem.startsWith("name=")) {
		    			topName = elem.substring(6, elem.length()-1);
		    			break;
		    		}
		    	}
		    }
		    //
		    result = new StateParam();
		    result.setName(topName);
		    result.setInitialState(scxml.getInitial());
		    
		    Set<String> keysState = scxml.getChildren().keySet();
		    for(String key : keysState) {
		    	parseState(result, (State)scxml.getChildren().get(key), "Top", result.getAllStateList(), result.getAllTransList());
		    }
		    for(TransitionParam trans : result.getAllTransList()) {
		    	String nextState = trans.getTarget();
		    	trans.setTarget(findStateName(nextState, result.getAllStateList()));
		    }

		} catch (FileNotFoundException ex1) {
		} catch (IOException ex) {
		}
		return result;
	}
	
	public StateParam parseSCXMLStr(String source) {
		StateParam result = null;
		String topTag = null;
	    
		String[] eachLines = source.split("\r\n");
		StringBuffer tmp_sb = new StringBuffer();
		for(String eachLine : eachLines) {
	    	tmp_sb.append(eachLine + "\r\n");
	    	if(topTag==null && eachLine.trim().startsWith("<scxml")) {
	    		topTag = eachLine;
	    	}
	    }
	    
	    SCXML scxml = null;
	    try {
	    	scxml =  (SCXML)SCXMLParser.newInstance().parse(new InputSource(new CharArrayReader(source.toCharArray())));
		} catch (IOException | SAXException e1) {
			e1.printStackTrace();
		}
	    String topName = "";
	    if(topTag!=null) {
	    	String[] elems = topTag.split(" ");
	    	for(String elem : elems) {
	    		if(elem.startsWith("name=")) {
	    			topName = elem.substring(6, elem.length()-1);
	    			break;
	    		}
	    	}
	    }
	    //
	    result = new StateParam();
	    result.setName(topName);
	    result.setInitialState(scxml.getInitial());
	    
	    Set<String> keysState = scxml.getChildren().keySet();
	    for(String key : keysState) {
	    	parseState(result, (State)scxml.getChildren().get(key), "Top", result.getAllStateList(), result.getAllTransList());
	    }
	    for(TransitionParam trans : result.getAllTransList()) {
	    	String nextState = trans.getTarget();
	    	trans.setTarget(findStateName(nextState, result.getAllStateList()));
	    }

		return result;
	}
	
	private String findStateName(String target, List<StateParam> source) {
		for(StateParam each : source) {
			if(each.getHistory() != 0) {
				if(each.getHistoryNodeName().equals(target)) {
					return each.getName();
				}
			}
		}
		return target;
	}

	private void parseState(StateParam parentParam, State state, String parentName, List<StateParam> stateList, List<TransitionParam> transList) {
		StateParam child = new StateParam();
		parentParam.getStateList().add(child);
		stateList.add(child);

		String strId = state.getId();
		child.setName(strId);
		child.setParentName(parentName);
		child.setHasEntry(parseEntryExit(state.getOnEntry()));
		child.setHasExit(parseEntryExit(state.getOnExit()));
		if(strId!=null && strId.equals(parentParam.getInitialState())) {
			child.setInitial(true);
		}
		if(state.isFinal()) {
			child.setFinal(true);
		}
		
		List<Transition> trans = state.getTransitionsList();
		for(Transition tran : trans) {
			TransitionParam tranParam = new TransitionParam();
			child.getTransList().add(tranParam);
			tranParam.setEvent(tran.getEvent());
			tranParam.setCondition(tran.getCond());
			tranParam.setSource(strId);
			tranParam.setTarget(tran.getNext());
			transList.add(tranParam);
		}
		
		for(Object param : state.getHistory()) {
			History history = (History)param;
	    	if( history.isDeep() ) {
	    		child.setHistory(2);
	    	} else {
	    		child.setHistory(1);
	    	}
	    	child.setHistoryNodeName(history.getId());
		}
		//
		Initial initNode = state.getInitial();
		if(initNode!=null) {
			Transition tran = initNode.getTransition();
			child.setInitialState(tran.getNext());
		}
		Set<String> keysState = state.getChildren().keySet();
	    for(String key : keysState) {
	    	parseState(child, (State)state.getChildren().get(key), strId, stateList, transList);
	    }
	}

	private boolean parseEntryExit(Executable target) {
		List actionList = target.getActions();
		if(actionList.size()==0) return false;
		Object action = actionList.get(0);
		if(action instanceof Log) {
			Log log = (Log)action;
			String strON = log.getLabel();
			if(strON.equals("ON")) {
				return true;
			}
		}
		return false;
	}
}
