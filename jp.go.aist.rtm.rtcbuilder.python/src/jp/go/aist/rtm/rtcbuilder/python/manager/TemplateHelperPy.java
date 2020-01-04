package jp.go.aist.rtm.rtcbuilder.python.manager;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.fsm.StateParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.IdlFileParam;
import jp.go.aist.rtm.rtcbuilder.python.IRtcBuilderConstantsPython;
import jp.go.aist.rtm.rtcbuilder.util.RTCUtil;
import jp.go.aist.rtm.rtcbuilder.util.StringUtil;

/**
 * テンプレートを出力する際に使用されるヘルパー Python用
 */
public class TemplateHelperPy {
	//
	public String convertDocPy(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstantsPython.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsPython.DOC_DEFAULT_PREFIX_PY, IRtcBuilderConstantsPython.DOC_DEFAULT_OFFSET_PY);
	}
	public String convertAuthorDocPy(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstantsPython.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsPython.DOC_DEFAULT_PREFIX_PY, IRtcBuilderConstantsPython.DOC_AUTHOR_OFFSET_PY);
	}
	public String convertModuleDocPy(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstantsPython.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsPython.DOC_MODULE_PREFIX_PY, IRtcBuilderConstantsPython.DOC_DEFAULT_OFFSET_PY);
	}
	public String convertDescDocPy(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstantsPython.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsPython.DOC_DESC_PREFIX_PY, IRtcBuilderConstantsPython.DOC_DESC_OFFSET_PY);
	}
	public String convertTypeDocPy(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstantsPython.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsPython.DOC_UNIT_PREFIX_PY, IRtcBuilderConstants.DOC_UNIT_OFFSET);
	}
	public String convertNumberDocPy(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstantsPython.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsPython.DOC_NUMBER_PREFIX_PY, IRtcBuilderConstantsPython.DOC_NUMBER_OFFSET_PY);
	}
	public String convertSemanticsDocPy(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstantsPython.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsPython.DOC_SEMANTICS_PREFIX_PY, IRtcBuilderConstants.DOC_SEMANTICS_OFFSET);
	}
	public String convertFrequencyDocPy(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstantsPython.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsPython.DOC_FREQUENCY_PREFIX_PY, IRtcBuilderConstantsPython.DOC_FREQUENCY_OFFSET_PY);
	}
	public String convertCycleDocPy(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstantsPython.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsPython.DOC_CYCLE_PREFIX_PY, IRtcBuilderConstantsPython.DOC_CYCLE_OFFSET_PY);
	}
	public String convertInterfacePy(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstantsPython.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsPython.DOC_NUMBER_PREFIX_PY, IRtcBuilderConstantsPython.DOC_NUMBER_OFFSET_PY);
	}
	public String convertDetailPy(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstantsPython.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsPython.DOC_DETAIL_PREFIX_PY, IRtcBuilderConstantsPython.DOC_DETAIL_OFFSET_PY);
	}
	public String convertUnitDocPy(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstantsPython.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsPython.DOC_UNIT_PREFIX_PY, IRtcBuilderConstants.DOC_UNIT_OFFSET);
	}
	public String convertRangeDocPy(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstantsPython.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsPython.DOC_RANGE_PREFIX_PY, IRtcBuilderConstantsPython.DOC_RANGE_OFFSET_PY);
	}
	public String convertConstraintDocPy(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstantsPython.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsPython.DOC_CONSTRAINT_PREFIX_PY, IRtcBuilderConstantsPython.DOC_CONSTRAINT_OFFSET_PY);
	}
	public String convertPreDocPy(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstantsPython.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsPython.DOC_PRE_PREFIX_PY, IRtcBuilderConstantsPython.DOC_PRE_OFFSET_PY);
	}
	public String convertPostDocPy(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstantsPython.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsPython.DOC_POST_PREFIX_PY, IRtcBuilderConstantsPython.DOC_POST_OFFSET_PY);
	}
	public String convertActivityDocPy(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstantsPython.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsPython.DOC_ACTIVITY_PREFIX_PY, IRtcBuilderConstantsPython.DOC_ACTIVITY_OFFSET_PY);
	}
	public String convertPreShDocPy(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstantsPython.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsPython.DOC_PRESH_PREFIX_PY, IRtcBuilderConstantsPython.DOC_PRE_OFFSET_PY);
	}
	public String convertPostShDocPy(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstantsPython.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsPython.DOC_POSTSH_PREFIX_PY, IRtcBuilderConstantsPython.DOC_POST_OFFSET_PY);
	}
	//
	public boolean hasDataPortType(List<IdlFileParam> targetFiles) {
		for(IdlFileParam target : targetFiles) {
			if(target.isDataPort()) return true;
		}
		return false;
	}
	
	public List<String> getDataPortTypes(List<IdlFileParam> targetFiles) {
		List<String> result = new ArrayList<String>();
		List<String> check = new ArrayList<String>();
		check.add("RTC");
		check.add("OpenRTM_aist");
		
		for(IdlFileParam target : targetFiles) {
			if(target.isDataPort()==false) continue;
			String targetType = "";
			for(String targetTypes : target.getTargetType()) {
				if( targetTypes.contains("::") ) {
					String[] types = targetTypes.split("::");
					/////
					targetType = types[0];
					if(check.contains(targetType)==false) {
						check.add(targetType);
						result.add(targetType);
					}
				
				} else {
					targetType = "_GlobalIDL";
					if(check.contains(targetType)==false) {
						check.add(targetType);
						result.add(targetType);
					}
				}
			}
		}
		return result;
	}
	
	public boolean checkUserDefined(RtcParam rtcParam) {
		boolean result = false;
		for(IdlFileParam target : rtcParam.getProviderIdlPathes()) {
			if(RTCUtil.checkDefault(target.getIdlPath(), rtcParam.getParent().getDataTypeParams())) continue;
			result = true;
			break;
		}
		if(result == false) {
			for(IdlFileParam target : rtcParam.getConsumerIdlPathes()) {
				if(RTCUtil.checkDefault(target.getIdlPath(), rtcParam.getParent().getDataTypeParams())) continue;
				result = true;
				break;
			}
		}
		return result;
	}
	
	public String getHistory(StateParam param) {
		if(param.getHistory()==2) {
			return "@StaticFSM.deephistory";
		} else if(param.getHistory()==1) {
			return "@StaticFSM.history";
		}
		return "  ";
	}
}
