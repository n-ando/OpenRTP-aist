package jp.go.aist.rtm.rtcbuilder.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceArgumentParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceClassParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceMethodParam;
import jp.go.aist.rtm.rtcbuilder.template.TemplateHelper;

/**
 * CXXソースを出力する際に使用されるユーティリティ
 */
public class CXXConverter {
	protected Map<String, String> mapType;
	
	private final String idlShort = "short";
	private final String idlLong = "long";
	private final String idlLongLong = "longlong";
	private final String idlUnsignedShort = "unsignedshort";
	private final String idlUnsignedLong = "unsignedlong";
	private final String idlUnsignedLongLong = "unsignedlonglong";
	private final String idlFloat = "float";
	private final String idlDouble = "double";
	private final String idlLongDouble = "longdouble";
	private final String idlBoolean = "boolean";
	private final String idlChar = "char";
	private final String idlWchar = "wchar";
	private final String idlOctet = "octet";
	private final String idlString = "string";
	private final String idlWstring = "wstring";
	private final String idlAny = "any";
	private final String idlVoid= "void";

	private final String cppShort = "::CORBA::Short";
	private final String cppLong = "::CORBA::Long";
	private final String cppLongLong = "::CORBA::LongLong";
	private final String cppUnsignedShort = "::CORBA::UShort";
	private final String cppUnsignedLong = "::CORBA::ULong";
	private final String cppUnsignedLongLong = "::CORBA::ULongLong";
	private final String cppFloat = "::CORBA::Float";
	private final String cppDouble = "::CORBA::Double";
	private final String cppLongDouble = "::CORBA::LongDouble";
	private final String cppBoolean = "::CORBA::Boolean";
	private final String cppChar = "::CORBA::Char";
	private final String cppWchar = "::CORBA::WChar";
	private final String cppOctet = "::CORBA::Octet";
	private final String cppString = "char*";
	private final String cppWstring = "::CORBA::WChar*";
	private final String cppAny = "::CORBA::Any*";
	private final String cppVoid= "void";

	public CXXConverter() {
		mapType = new HashMap<String, String>();
		mapType.put(idlLongLong, cppLongLong);
		mapType.put(idlLong, cppLong);
		mapType.put(idlUnsignedLong, cppUnsignedLong);
		mapType.put(idlUnsignedLongLong, cppUnsignedLongLong);
		mapType.put(idlShort, cppShort);
		mapType.put(idlUnsignedShort, cppUnsignedShort);
		mapType.put(idlFloat, cppFloat);
		mapType.put(idlDouble, cppDouble);
		mapType.put(idlLongDouble, cppLongDouble);
		mapType.put(idlChar, cppChar);
		mapType.put(idlWchar, cppWchar);
		mapType.put(idlOctet, cppOctet);
		mapType.put(idlBoolean, cppBoolean);
		mapType.put(idlString, cppString);
		mapType.put(idlWstring, cppWstring);
		mapType.put(idlAny, cppAny);
		mapType.put(idlVoid, cppVoid);
	}

	/**
	 * サービススタブ名を取得する
	 * 
	 * @param serviceClassParam
	 *            ServiceClassParam
	 * @return
	 */
	public static StringBuffer getSvcStubName(
			ServiceClassParam serviceClassParam) {
		StringBuffer result = new StringBuffer();

		result.append(serviceClassParam.getName());
		if (TemplateHelper.getServiceStubSuffix() != null
				&& !TemplateHelper.getServiceStubSuffix().equals(""))
			result.append(TemplateHelper.getServiceStubSuffix());
		else
			result.append(IRtcBuilderConstants.DEFAULT_SVC_STUB_SUFFIX);
		return result;
	}

	/**
	 * サービススケルトン名を取得する
	 * 
	 * @param serviceClassParam
	 *            ServiceClassParam
	 * @return
	 */
	public static StringBuffer getSvcSkelName(
			ServiceClassParam serviceClassParam) {
		StringBuffer result = new StringBuffer();

		result.append(serviceClassParam.getName());
		if (TemplateHelper.getServiceSkelSuffix() != null
				&& !TemplateHelper.getServiceSkelSuffix().equals(""))
			result.append(TemplateHelper.getServiceSkelSuffix());
		else
			result.append(IRtcBuilderConstants.DEFAULT_SVC_SKEL_SUFFIX);
		return result;
	}

	/**
	 * サービスインプル名を取得する
	 * 
	 * @param serviceClassParam
	 *            ServiceClassParam
	 * @return
	 */
	public static StringBuffer getSvcImplName(
			ServiceClassParam serviceClassParam) {
		StringBuffer result = new StringBuffer();

		result.append(serviceClassParam.getName());
		if (!TemplateHelper.getServiceImplSuffix().equals(""))
			result.append(TemplateHelper.getServiceImplSuffix());
		else
			result.append(IRtcBuilderConstants.DEFAULT_SVC_IMPL_SUFFIX);
		return result;
	}

	/**
	 * CPP型からCORBA型へのマッピングを定義する
	 * 
	 * @param strcpp
	 *            CPP型
	 * @return CORBA型
	 */
	public String convCpp2CORBA(ServiceMethodParam typeDef) {
		String result = mapType.get(typeDef.getType());
		if( result == null ) {
			result = typeDef.getType();

			if(typeDef.isArray()) {
				result = result + "_slice*";

			}else if(typeDef.isSequence() ){
				result = result + "*";

			} else if(typeDef.isStruct()) {
			   if(typeDef.isUnbounded()) {
				result = result + "*";
			   }

			} else if(typeDef.isInterface()) {
				if(typeDef.isAlias()) {
					result = typeDef.getOriginalType() + "_ptr";
				}else{
					result = result + "_ptr";
				}

			} else if(typeDef.isAlias()) {
				if (typeDef.getOriginalType().equals("string")){
					result = "char*";

				}else if (typeDef.getOriginalType().equals("wstring")){
					result = "::CORBA::WChar*";
				}else if (typeDef.getOriginalType().equals("any")){
					result = result + "*";
				}
			}
			if(typeDef.getModule()!=null && typeDef.getModule().length()>0 && result.startsWith("RTC")==false) {
				result = typeDef.getModule() + result;
			}
		}
		return result;
	}

	/**
	 * CPP型からCORBA型へのマッピングを定義する(引数用)
	 * 
	 * @param strType
	 *            CPP型
	 * @param strDir
	 *            入出力
	 * @return CORBA型
	 */
	public String convCpp2CORBAforArg(ServiceArgumentParam typeDef) {
		String result = null;
		
		result = mapType.get(typeDef.getType());
		if( result == null ) {
			result = typeDef.getType();
//			if( !typeDef.getType().contains("::") && typeDef.isSequence()) {
//				result = result + "*";
//			}
		}
		
		if(typeDef.getType().equals("string")) {
			if(typeDef.getDirection().equals("in"))
				result = "const char*";
			else if(typeDef.getDirection().equals("out")) {
				if(typeDef.isUnbounded()) {
					result = typeDef.getOriginalType() + "_out";
				} else {
					result = "::CORBA::String_out";
				}
			}
			else if(typeDef.getDirection().equals("inout"))
				result = "char*&";
			
		} else if(typeDef.getType().equals("wstring")) {
			if(typeDef.getDirection().equals("in"))
				result = "const ::CORBA::WChar*";
			else if(typeDef.getDirection().equals("out")) {
				if(typeDef.isUnbounded()) {
					result = typeDef.getOriginalType() + "_out";
				} else {
					result = "::CORBA::WString_out";
				}
			}
			else if(typeDef.getDirection().equals("inout"))
				result = "::CORBA::WChar*&";
			
		} else if(typeDef.getType().equals("any")) {
			if(typeDef.getDirection().equals("in"))
				result = "const ::CORBA::Any&";
			else if(typeDef.getDirection().equals("out"))
				result = "::CORBA::Any_OUT_arg";
			else if(typeDef.getDirection().equals("inout"))
				result = "::CORBA::Any&";

		} else if(typeDef.isArray()) {
			if( typeDef.getModule() != "" ){
				result = typeDef.getModule()+result;
			}
			if(typeDef.isUnbounded() || typeDef.isInterface()
			   || typeDef.getOriginalType().equals("string") 
			   || typeDef.getOriginalType().equals("wstring") 
			   || typeDef.getOriginalType().equals("any") 
			){
				if(typeDef.getDirection().equals("in"))
					result = "const " + result;
				else if(typeDef.getDirection().equals("out"))
					result = result + "_out";
		 	}else{
				if(typeDef.getDirection().equals("in"))
					result = "const " + result;
		 	}

		} else if(typeDef.isStruct() ) {
			if( typeDef.getModule() != "" ){
				result = typeDef.getModule()+result;
			}
			if(typeDef.getDirection().equals("in")){
				result = "const " + result + "&";
			}else if(typeDef.getDirection().equals("out")){
				if( typeDef.isUnbounded() ){
					result = result + "_out";
				}else{
					result = result + "&";
				}
			}else if(typeDef.getDirection().equals("inout")){
				result = result + "&";
			}

		} else if(typeDef.isSequence() ) {
			if( typeDef.getModule() != "" ){
				result = typeDef.getModule()+result;
			}
			if(typeDef.getDirection().equals("in"))
				result = "const " + result + "&";
			else if(typeDef.getDirection().equals("out"))
				result = result + "_out";
			else if(typeDef.getDirection().equals("inout"))
				result = result + "&";

		} else if(typeDef.isInterface()) {
			if(typeDef.getDirection().equals("in") || typeDef.getDirection().equals("inout") ){
				if(typeDef.isAlias()) result = typeDef.getOriginalType();
			 }

			if(typeDef.getDirection().equals("in") ){
				result = result + "_ptr";
			}else if(typeDef.getDirection().equals("out"))
				result = result + "_out";
			else if(typeDef.getDirection().equals("inout")){
				result = result + "_ptr&";
			}

		}else if(typeDef.isAlias()) {
			if (typeDef.getOriginalType().equals("string")){
				if(typeDef.getDirection().equals("in") )
					result = "const char*";
				else if(typeDef.getDirection().equals("out") )
					result = result + "_out";
				else if(typeDef.getDirection().equals("inout"))
					result = "char*&";

			}else if (typeDef.getOriginalType().equals("wstring")){
				if(typeDef.getDirection().equals("in") )
					result = "const ::CORBA::WChar*";
				else if(typeDef.getDirection().equals("out") )
					result = result + "_out";
				else if(typeDef.getDirection().equals("inout"))
					result = "::CORBA::WChar*&";

			}else if (typeDef.getOriginalType().equals("any")){
				if(typeDef.getDirection().equals("in") )
					result = "const "+result+"&";
				else if(typeDef.getDirection().equals("out") )
					result = "::CORBA::Any_OUT_arg";
				else if(typeDef.getDirection().equals("inout"))
					result = result + "&";
			} else {
				if( typeDef.getModule() != "" ){
			 		if( result.indexOf("::") < 0 ){
						result = typeDef.getModule()+result;
			 		}
				}

				if(typeDef.getDirection().equals("out") || typeDef.getDirection().equals("inout")){
					result = result + "&";
				}
			}

		} else {
			if( typeDef.getModule() != "" ){
			   if( result.indexOf("::") < 0 ){
						result = typeDef.getModule()+result;
			   }
			}

			if(typeDef.getDirection().equals("out") || typeDef.getDirection().equals("inout")) {
					result = result + "&";
			}
		}

		return result;
	}

	public String convConfigSetType(String typeDef) {
		if( typeDef.trim().equals("short") ) {
			return "short int";
		} else if( typeDef.trim().equals("long") ) {
			return "long int";
		} else if( typeDef.trim().equals("string") ) {
			return "std::string";
		}
		return typeDef;
	}

	/**
	 * パッケージの区切り文字を「.」から「::」に変更する
	 * @param fullName
	 * @return
	 */
	public static String convertDelimiter(String fullName) {
		return fullName.replaceAll("\\.", "::");
	}
	
	/**
	 * Portに設定されたModuleの一覧を取得する
	 * 
	 * @param param  RtcParam
	 * @return Module一覧リスト
	 */
	public static List<String> getPortModules(RtcParam param) {
		List<String> modules = new ArrayList<String>();
		String dataTypeString[];
		for( DataPortParam dataPort : param.getInports() ) {
			dataTypeString = dataPort.getType().split("::", 0);
			if( !dataPort.getType().contains("::")) dataTypeString[0] = "RTC";
			if( !modules.contains(dataTypeString[0]) && !dataTypeString[0].equals("RTC") ) {
				modules.add(dataTypeString[0]);
			}
		}
		for( DataPortParam dataPort : param.getOutports() ) {
			dataTypeString = dataPort.getType().split("::", 0);
			if( !dataPort.getType().contains("::")) dataTypeString[0] = "RTC";
			if( !modules.contains(dataTypeString[0]) && !dataTypeString[0].equals("RTC") ) {
				modules.add(dataTypeString[0]);
			}
		}
		return modules;
	}
	
	/**
	 * データポート用のデータ型include文を返す
	 * 
	 * @param rtcType ポートの型
	 * @return using文字列
	 */
	public String getDataportIncludeName(String rtcType) {
		if(rtcType.matches("RTC.*")) return "";
		return "#include <rtm/idl/" + rtcType + ".h>";
	}

	/**
	 * データポート初期化用にmodule名をカットしたデータ型クラス名を返す
	 * 
	 * @param rtcType ポートの型
	 * @return クラス名
	 */
	public String getDataTypeName(String rtcType) {		
		//module名が付いていないデータ型（::が付いていない）はそのまま返す
		if(!rtcType.matches(".*::.*")) return rtcType;

		String dataTypeNames[] = rtcType.split("::", 0);
		return dataTypeNames[1];
	}
}
