package jp.go.aist.rtm.rtcbuilder.python.util;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.generator.param.DataTypeParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.IdlFileParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceClassParam;
import jp.go.aist.rtm.rtcbuilder.util.RTCUtil;

public class RTCUtilPy {
	public static boolean checkDefault(String target, List<DataTypeParam> typeList) {
		for(DataTypeParam type : typeList) {
			if(type.isDefault()) {
				if(target.trim().equals(type.getFullPath().trim())) {
					if(0<type.getDefinedTypes().size()) {
						if(type.getDefinedTypes().get(0).startsWith("Img")
								||type.getDefinedTypes().get(0).startsWith("JARA_ARM")) {
							continue;
						}
					}
					return true;
				}
			}
		}
		return false;
	}
	
	public static List<String> checkDefaultModuile(List<IdlFileParam> targetFiles, boolean isProvided, List<DataTypeParam> typeList) {
		List<String> result = new ArrayList<String>();
		List<String> check = new ArrayList<String>();
		check.add("RTC");
		check.add("OpenRTM_aist");
		
		for(IdlFileParam target : targetFiles) {
			if(RTCUtil.checkDefault(target.getIdlPath(), typeList) == false) continue;
			if(target.isDataPort()) {
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
					}
				}
			} else {
				String targetType = "";
				if(isProvided) {
					for(ServiceClassParam targetTypes : target.getTestServiceClassParams()) {
						targetType = targetTypes.getModule();
						targetType = targetType.replace("::", "");
						if(check.contains(targetType)==false) {
							check.add(targetType);
							result.add(targetType);
						}
					}
				} else {
					for(ServiceClassParam targetTypes : target.getServiceClassParams()) {
						targetType = targetTypes.getModule();
						targetType = targetType.replace("::", "");
						if(check.contains(targetType)==false) {
							check.add(targetType);
							result.add(targetType);
						}
					}
				}
			}
		}
		return result;
	}
}
