package jp.go.aist.rtm.rtcbuilder.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;


public class StringUtil {

	private static final String START_MARK = "<";
	private static final String END_MARK = ">";

	public static boolean checkDigitAlphabet(String source) {
		if(source==null) return true;
	    for(int intIdx = 0; intIdx < source.length(); intIdx++) {
	        char target = source.charAt(intIdx);
	        if( (target < '0' || target > '9') &&    //数字チェック
	            (target < 'a' || target > 'z') &&    //小文字アルファベットチェック
	            (target < 'A' || target > 'Z') &&    //大文字アルファベットチェック
	            (target != '_') && (target != '-') && (target != ':')) {
	             return false;
	        }
	    }
	    return true;
	}

	public static boolean checkProhibitedChar(String source) {
		if(source.length()==0) return true;
		Pattern p = Pattern.compile("^[\\p{Alnum}|_]*$");
		Matcher m = p.matcher(source);
		return m.find();
	}
	
	public static String splitString(String source, int width, String prefix, int offset) {
		if( source==null || source.equals("") || width<=0 ) return "";

		String sep = System.getProperty("line.separator");
		String lines[] = source.split(IRtcBuilderConstants.NEWLINE_CODE);

		// 一文字ずつ保持
		StringBuffer strBuf = new StringBuffer();
		// 戻り値用
		StringBuffer result = new StringBuffer();
		
		// resultに投入する前のwork
		ArrayList<StringBuffer> workResult = new ArrayList<StringBuffer>();
		ArrayList<StringBuffer> temp = new ArrayList<StringBuffer>();
		
		int length = offset;
		boolean bolFlg = false;
		
		for( int intline=0; intline<lines.length; intline++ ) {
			source = lines[intline];
			String[] eachWord = source.split(" ");
			
			for(int idxWord=0;idxWord<eachWord.length;idxWord++) {
				String target = eachWord[idxWord];
				for( int intIdx=0; intIdx<target.length(); intIdx++ ) {
					char c = target.charAt(intIdx);
					if ((c <= '\u007e') || // 英数字
							(c == '\u00a5') || // \記号
							(c == '\u203e') || // ~記号
							(c >= '\uff61' && c <= '\uff9f') // 半角カナ
					) {
						length += 1;
					} else {
						length += 2;
					}
					// 一文字ずつ取得する
					strBuf.append(c);
					// 改行文字の場合は，その前までを投入
					if (String.valueOf(c).equals(sep)) {
						workResult.add(strBuf);
						strBuf = new StringBuffer();
						length = offset;
					}
					
					if (String.valueOf(c).equals(START_MARK)) {
						// tempの値をworkに投入
						if (temp.size() > 0) {
							workResult.addAll(temp);
							temp = new ArrayList<StringBuffer>();
						}
						bolFlg = false;
					}
					
					if (String.valueOf(c).equals(END_MARK)) {
						bolFlg = true;
						if (temp.size() > 0) {
							// 終了文字までをStringBufferにため、workに投入
							StringBuffer workBuffer = new StringBuffer();
							for (int intIdx2=0; intIdx2 < temp.size(); intIdx2++) {
								workBuffer.append(temp.get(intIdx2));
							}
							workBuffer.append(strBuf);
							workResult.add(workBuffer);
							// 初期化
							bolFlg = false;
							temp = new ArrayList<StringBuffer>();
							strBuf = new StringBuffer();
							length = offset;
						}
					}
					
					if(length >= width) {
						//単語の途中で改行になった場合
						if(intIdx<target.length()) {
							if(intIdx+2< strBuf.length()) {
								//追加した単語の長さ分を削除
								strBuf.delete(strBuf.length()-intIdx-2,strBuf.length());
								//再度同じ単語を解析
								intIdx = -1;
							}
						}
						// width分文字列を取得した時に終了文字が含まれていなければtempへ
						// 含まれていたらworkへ。
						if (bolFlg == false) {
							temp.add(strBuf);
						} else {
							workResult.add(strBuf);
						}
						strBuf = new StringBuffer();
						length = offset;
					}
					
				}
				if( idxWord<eachWord.length-1 && 0<strBuf.length()) {
					strBuf.append(" ");
					length += 1;
				}
			}
			
			// tempに残っている文字列をworkへ
			if (temp.size() > 0) workResult.addAll(temp);
			// strBufに残っている文字列をworkへ
			if (strBuf.length() > 0) workResult.add(strBuf);
			temp = new ArrayList<StringBuffer>();
			strBuf = new StringBuffer();
			length = offset;
		}
		
		// workResultからresultを成形する
		for (int intIdx=0; intIdx < workResult.size();intIdx++){
			if (intIdx > 0)	result.append(prefix);
			result.append(workResult.get(intIdx));
			if (intIdx+1 < workResult.size() ) result.append("\r\n");
		}

		return result.toString();
	}

	public static String connectMessageWithSepalator(String[] ss){
		if( ss==null ) return "";
		
		String result = "";
		for( int i=0; i<ss.length; i++ ){
			result += ss[i];
			if( !"".equals(ss[i]) )
				result += System.getProperty("line.separator");
		}
		
		return result;
	}
	
	public static boolean matchKey(String source, String key) {
		if(key.equals("*")) return true;
		String[] keyList = key.split(",");
		for(String target : keyList) {
			if(source.trim().equals(target.trim())) return true;
		}
		
		return false;
	}

	public static boolean matchKey(List<String> source, String key) {
		if(key.equals("*")) return true;
		String[] keyList = key.split(",");
		for(String target : keyList) {
			if(source.contains(target.trim())) return true;
		}
		
		return false;
	}
	
	public static String getDocText(String text) {
		String result = text;
		if ("".equals(result)) {
			return "";
		}
		String sep = System.getProperty("line.separator");
		String lines[] = result.split(sep);
		StringBuffer buffer = new StringBuffer();
		for( int index=0; index<lines.length; index++ ) {
			buffer.append(lines[index]);
			if(index<lines.length-1) buffer.append(IRtcBuilderConstants.NEWLINE_CODE);
		}
		return buffer.toString();
	}

	public static String getDisplayDocText(String text) {
		String result = text;
		if( text==null || "".equals(result) ) {
			return "";
		}
		String sep = System.getProperty("line.separator");
		String lines[] = result.split(IRtcBuilderConstants.NEWLINE_CODE);
		StringBuffer buffer = new StringBuffer();
		for( int index=0; index<lines.length; index++ ) {
			buffer.append(lines[index]);
			if(index<lines.length-1) buffer.append(sep);
		}
		return buffer.toString();
	}
	
	public static String removeLastNewLine(String source) {
        String sep = System.getProperty("line.separator");
        if(source.endsWith(sep)==false) return source;
        //
        StringBuilder builder = new StringBuilder(source);
        int n = sep.length();
        int size = builder.length();        
        builder.delete(size-n, size);
        
        return builder.toString();
	}
	
	public static String removeNewLine(String source) {
		source = source.replace("\r","");
		source = source.replace("\n","");
        
        return source;
	}
}
