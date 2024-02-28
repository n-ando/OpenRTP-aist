package jp.go.aist.rtm.toolscommon.profiles.util;

import java.io.InputStream;
import java.util.Map;

import org.ho.yaml.Yaml;
import org.openrtp.namespaces.RtcProfileHolder;
import org.openrtp.namespaces.rtc.version03.RtcProfile;

public class YamlHandler {

	final static private String containClassName = ": !";
	final static private String prefixClassName = "- !";

	public String convertToYamlRtc(RtcProfile profile) throws Exception {
		RtcProfileHolder holder = new RtcProfileHolder();
		holder.setRtcProfile(profile);

		String yamlText = Yaml.dump(holder, true);
		String[] yamlSplt = removeClassInfo(yamlText);
		StringBuffer buffer = new StringBuffer();
		String lineSeparator = System.getProperty( "line.separator" );
		if( lineSeparator==null || lineSeparator.equals("") ) lineSeparator = "\n";
		for(int intIdx=0;intIdx<yamlSplt.length;intIdx++) {
			buffer.append(yamlSplt[intIdx] + lineSeparator);
		}
		return buffer.toString();
	}

	private String[] removeClassInfo(String strInput) {
		String lineSeparator = System.getProperty( "line.separator" );
		if( lineSeparator==null || lineSeparator.equals("") ) lineSeparator = "\n";
		String splitStr[] = strInput.split(lineSeparator);

		String strWork = "";
		for( int intidx=0;intidx<splitStr.length;intidx++ ) {
			strWork = splitStr[intidx];
			if(strWork.trim().contains(containClassName)) {
				int end = strWork.indexOf(containClassName);
				splitStr[intidx] = strWork.substring(0,end+1);
			}
			if(strWork.trim().startsWith(prefixClassName)) {
				int end = strWork.indexOf(prefixClassName);
				splitStr[intidx] = strWork.substring(0,end+1);
			}
		}
		return splitStr;
	}

	public RtcProfile restoreFromYamlRtc(InputStream input) throws Exception {
		Map profileYOrg  = (Map)Yaml.load(input);
		return mapToRtc(profileYOrg);
	}

	public RtcProfile restoreFromYamlRtc(String targetYaml) throws Exception {
		Map profileYOrg  = (Map)Yaml.load(targetYaml);
		return mapToRtc(profileYOrg);
	}

	private RtcProfile mapToRtc(Map profileYOrg) throws Exception {
		RtcProfile result = null;

		if( profileYOrg != null ) {
			Map profileY  = (Map)profileYOrg.get("rtcProfile");
			if( profileY != null ) {
				String version = (String)profileY.get("version");
				if(version.equals("0.2")) {
					YamlSubHandlerVer02 handler = new YamlSubHandlerVer02();
					result = handler.mapToRtc(profileYOrg);
				} else if(version.equals("0.3")) {
					YamlSubHandlerVer03 handler = new YamlSubHandlerVer03();
					result = handler.mapToRtc(profileYOrg);
				}
			}
		}

		return result;
	}
}
