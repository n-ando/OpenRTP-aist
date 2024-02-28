package jp.go.aist.rtm.rtcbuilder.java.manager;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.fsm.EventParam;
import jp.go.aist.rtm.rtcbuilder.fsm.StateParam;
import jp.go.aist.rtm.rtcbuilder.fsm.TransitionParam;
import jp.go.aist.rtm.rtcbuilder.java.IRtcBuilderConstantsJava;
import jp.go.aist.rtm.rtcbuilder.util.StringUtil;

/**
 * テンプレートを出力する際に使用されるヘルパー
 */
public class TemplateHelperJava {

	//
	public String convertDescDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsJava.DOC_DESC_PREFIX_JAVA, IRtcBuilderConstantsJava.DOC_DESC_OFFSET_JAVA);
	}
	public String convertPreDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsJava.DOC_DESC_PREFIX_JAVA, IRtcBuilderConstantsJava.DOC_PRE_OFFSET_JAVA);
	}
	public String convertPostDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsJava.DOC_DESC_PREFIX_JAVA, IRtcBuilderConstantsJava.DOC_POST_OFFSET_JAVA);
	}
	public String convertUnitDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsJava.DOC_UNIT_PREFIX_JAVA, IRtcBuilderConstantsJava.DOC_UNIT_OFFSET_JAVA);
	}
	public String convertRangeDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsJava.DOC_RANGE_PREFIX_JAVA, IRtcBuilderConstantsJava.DOC_RANGE_OFFSET_JAVA);
	}
	public String convertConstraintDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsJava.DOC_CONSTRAINT_PREFIX_JAVA, IRtcBuilderConstantsJava.DOC_CONSTRAINT_OFFSET_JAVA);
	}
	public String convertTypeDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsJava.DOC_UNIT_PREFIX_JAVA, IRtcBuilderConstantsJava.DOC_UNIT_OFFSET_JAVA);
	}
	public String convertNumberDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsJava.DOC_NUMBER_PREFIX_JAVA, IRtcBuilderConstantsJava.DOC_NUMBER_OFFSET_JAVA);
	}
	public String convertSemanticsDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsJava.DOC_SEMANTICS_PREFIX_JAVA, IRtcBuilderConstantsJava.DOC_SEMANTICS_OFFSET_JAVA);
	}
	public String convertFrequencyDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsJava.DOC_SEMANTICS_PREFIX_JAVA, IRtcBuilderConstantsJava.DOC_SEMANTICS_OFFSET_JAVA);
	}
	public String convertCycleDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsJava.DOC_CYCLE_PREFIX_JAVA, IRtcBuilderConstantsJava.DOC_CYCLE_OFFSET_JAVA);
	}
	public String convertInterfaceDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsJava.DOC_INTERFACE_PREFIX_JAVA, IRtcBuilderConstantsJava.DOC_INTERFACE_OFFSET_JAVA);
	}
	public String convertInterfaceDetailDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsJava.DOC_INTERFACE_DETAIL_PREFIX_JAVA, IRtcBuilderConstantsJava.DOC_INTERFACE_DETAIL_OFFSET_JAVA);
	}
	//
	public boolean notNullRTMRoot() {
		String defaultPath = System.getenv("RTM_ROOT");
		if( defaultPath==null ) return false;
		return true;
	}
	
	public String getHistoryImport(StateParam param) {
		if(param.getHistory()==2) {
			return "import jp.go.aist.rtm.RTC.jfsm.DeepHistory;";
		} else if(param.getHistory()==1) {
			return "import jp.go.aist.rtm.RTC.jfsm.History;";
		}
		return "  ";
	}
	
	public String getHistory(StateParam param) {
		if(param.getHistory()==2) {
			return "@DeepHistory";
		} else if(param.getHistory()==1) {
			return "@History";
		}
		return "  ";
	}
	
	public List<String> getInEventList(StateParam parent, StateParam param) {
		List<String> result = new ArrayList<String>();
		
		for(TransitionParam trans : parent.getAllTransList()) {
			if(trans.getTarget().equals(param.getName())) {
				if(trans.getEvent()!=null && 0<trans.getEvent().length()) {
					result.add(trans.getEvent());
				}
			}
		}
		
		return result;
	}
	
	public List<String> getEventDataTypes(StateParam param) {
		JavaConverter converter = new JavaConverter();
		List<String> result = new ArrayList<String>();
		
		for(TransitionParam trans : param.getAllTransList()) {
			if(trans.existDataType()==false) continue;
			String dataType = converter.getDataportPackageName(trans.getDataType());
			if(result.contains(dataType)) continue;
			result.add(dataType);
		}
		
		return result;
	}
	
	public List<String> getEventDataTypes4Test(StateParam param) {
		List<String> result = getEventDataTypes(param);
		if(result.contains("import RTC.TimedLong;")==false) {
			result.add("import RTC.TimedLong;");
		}
		return result;
	}
	
	public String getInitialValue(TransitionParam trans) {
		String result = "0";
		
		EventParam param = trans.getEventParam();
		if(param!=null) {
			if(param.getDataType().contains("String")) {
				result = "\"0\"";
			}
		}
		
		return result;
	}
	
	public String checkTransition(StateParam state) {
		TransitionParam targetTans = null;
		for(TransitionParam trans : state.getTransList()) {
			if(trans.getEvent().trim().length()==0) {
				targetTans = trans;
			}
		}
		if(targetTans!=null) {
			StringBuilder builder = new StringBuilder();
			builder.append("    setState(new State(").append(targetTans.getTarget()).append(".class));");
			return builder.toString();
		}
		return "";
	}
}
