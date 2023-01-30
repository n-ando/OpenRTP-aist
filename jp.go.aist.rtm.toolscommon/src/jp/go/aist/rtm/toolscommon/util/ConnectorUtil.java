package jp.go.aist.rtm.toolscommon.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.InPort;
import jp.go.aist.rtm.toolscommon.model.component.OutPort;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.impl.PortImpl;

/**
 * ポート間で接続可能なプロパティを管理するユーティリティ
 * 
 */
public class ConnectorUtil {

	/**
	 * 両端のポートがともにAnyのデータ型を許すかを返す
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static boolean isAllowAnyDataType(OutPort source, InPort target) {
		if (source != null && target != null) {
			return source.isAllowAnyDataType() && target.isAllowAnyDataType();
		} else if (source != null && target == null) {
			return source.isAllowAnyDataType();
		} else if (source == null && target != null) {
			return target.isAllowAnyDataType();
		}
		return false;
	}

	/**
	 * 両端のポートがともにAnyのインターフェース型を許すかを返す
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static boolean isAllowAnyInterfaceType(OutPort source, InPort target) {
		if (source != null && target != null) {
			return source.isAllowAnyInterfaceType()
					&& target.isAllowAnyInterfaceType();
		} else if (source != null && target == null) {
			return source.isAllowAnyInterfaceType();
		} else if (source == null && target != null) {
			return target.isAllowAnyInterfaceType();
		}
		return false;
	}

	/**
	 * 両端のポートがともにAnyのデータフロー型を許すかを返す
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static boolean isAllowAnyDataflowType(OutPort source, InPort target) {
		if (source != null && target != null) {
			return source.isAllowAnyDataflowType()
					&& target.isAllowAnyDataflowType();
		} else if (source != null && target == null) {
			return source.isAllowAnyDataflowType();
		} else if (source == null && target != null) {
			return target.isAllowAnyDataflowType();
		}
		return false;
	}

	/**
	 * 両端のポートがともにAnyのサブスクリプション型を許すかを返す
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static boolean isAllowAnySubscriptionType(OutPort source,
			InPort target) {
		if (source != null && target != null) {
			return source.isAllowAnySubscriptionType()
					&& target.isAllowAnySubscriptionType();
		} else if (source != null && target == null) {
			return source.isAllowAnySubscriptionType();
		} else if (source == null && target != null) {
			return target.isAllowAnySubscriptionType();
		}
		return false;
	}

	static List<String> emptyList = new ArrayList<String>();
	
	public static class SerializerInfo {
		public boolean useSerializer;
		public String dataType;
		public String outPortSerializer;
		public String inPortSerializer;
		
		public SerializerInfo(String dataType) {
			this.useSerializer = false;
			this.dataType = dataType;
			this.outPortSerializer = "";
			this.inPortSerializer = "";
		}
		
		public SerializerInfo(String dataType, String outPortSerializer, String inPortSerializer) {
			this.useSerializer = true;
			this.dataType = dataType;
			this.outPortSerializer = outPortSerializer;
			this.inPortSerializer = inPortSerializer;
		}
		
		public String toString() {
			String result = "";
			
			if(useSerializer) {
				result = outPortSerializer + " - " + inPortSerializer;
			} else {
				result = "cdr - cdr";
			}
			return result;
		}
	}
	static List<SerializerInfo> emptySerList = new ArrayList<SerializerInfo>();

	/**
	 * 使用可能なデータ型のリストを返す
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static List<SerializerInfo> getAllowDataTypes(OutPort source, InPort target) {
		List<SerializerInfo> result = new ArrayList<SerializerInfo>();
		
		if (source == null && target == null) {
			return emptySerList;
		} else if (source != null && target == null) {
			List<String> sourceTypes = source.getDataTypes();
			for(String each : sourceTypes) {
				result.add(new SerializerInfo(each));
			}
			return result;
		} else if (source == null && target != null) {
			List<String> targetTypes = target.getDataTypes();
			for(String each : targetTypes) {
				result.add(new SerializerInfo(each));
			}
			return result;
		}
		List<String> sourceTypes = source.getDataTypes();
		List<String> targetTypes = target.getDataTypes();
		//
		List<String> resultCheck = getAllowList(sourceTypes, targetTypes,
				dataTypeComparer);
		if(resultCheck.isEmpty()==false) {
			resultCheck = sortTypes(resultCheck);
			for(String each : resultCheck) {
				result.add(new SerializerInfo(each));
			}
		}
		///
		List<String> sourceSerializers = getSerializerList(source);
		List<String> targetSerializers = getSerializerList(target);
		
		if(sourceSerializers.isEmpty()) sourceSerializers.add("cdr");
		if(targetSerializers.isEmpty()) targetSerializers.add("cdr");
		
		for(String srcSer : sourceSerializers) {
			String[] srcElems = srcSer.split(":");
			for(String trgSer : targetSerializers) {
				String[] trgElems = trgSer.split(":");
				if( 2 <= srcElems.length && 2 <= trgElems.length) {
					if(srcElems[0].equals(trgElems[0]) && srcElems[1].equals(trgElems[1])) {
						result.add(new SerializerInfo(srcElems[1], srcSer, trgSer));
					}
				} else if(srcElems.length == 1 &&  3 <= trgElems.length) {
					if(srcElems[0].equals(trgElems[0])) {
						for(String srcType : sourceTypes) {
							String[] srcTypeElem = srcType.split(":");
							if(srcTypeElem[1].equals(trgElems[1])) {
								result.add(new SerializerInfo(trgElems[1], srcSer, trgSer));
							}
						}
					}
				} else if(trgElems.length == 1 &&  3 <= srcElems.length) {
					if(srcElems[0].equals(trgElems[0])) {
						for(String trgType : targetTypes) {
							String[] trgTypeElem = trgType.split(":");
							if(trgTypeElem[1].equals(srcElems[1])) {
								result.add(new SerializerInfo(srcElems[1], srcSer, trgSer));
							}
						}
					}
				}
			}
		}
		return result;
	}
	
	private static List<String> getSerializerList(Port target) {
		List<String> result = new ArrayList<String>();
		String serializers = target.getProperty("dataport.marshaling_types");
		if(serializers!=null && 0<serializers.length()) {
			String[] serArray = serializers.split(",");
			result = new ArrayList<String>(serArray.length);
			for (String item : serArray) result.add(item.trim());
			result.sort( (a, b) -> a.length() == b.length() ? a.compareTo(b) : b.length() - a.length());
		}
		return result;
		
	}

	/**
	 * 使用可能なインターフェース型のリストを返す
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static List<String> getAllowInterfaceTypes(OutPort source,
			InPort target) {
		if (source == null && target == null) {
			return emptyList;
		} else if (source != null && target == null) {
			return source.getInterfaceTypes();
		} else if (source == null && target != null) {
			return target.getInterfaceTypes();
		}
		List<String> sourceTypes = source.getInterfaceTypes();
		List<String> targetTypes = target.getInterfaceTypes();
		//
		List<String> result = getAllowList(sourceTypes, targetTypes,
				ignoreCaseComparer);
		result = sortTypes(result);
		return result;
	}

	/**
	 * 使用可能なデータフロー型のリストを返す
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static List<String> getAllowDataflowTypes(OutPort source,
			InPort target) {
		if (source == null && target == null) {
			return emptyList;
		} else if (source != null && target == null) {
			return source.getDataflowTypes();
		} else if (source == null && target != null) {
			return target.getDataflowTypes();
		}
		List<String> sourceTypes = source.getDataflowTypes();
		List<String> targetTypes = target.getDataflowTypes();
		//
		List<String> result = getAllowList(sourceTypes, targetTypes,
				ignoreCaseComparer);
		return result;
	}

	/**
	 * 使用可能なサブスクリプション型のリストを返す
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static List<String> getAllowSubscriptionTypes(OutPort source,
			InPort target) {
		if (source == null && target == null) {
			return emptyList;
		} else if (source != null && target == null) {
			return source.getSubscriptionTypes();
		} else if (source == null && target != null) {
			return target.getSubscriptionTypes();
		}
		List<String> sourceTypes = source.getSubscriptionTypes();
		List<String> targetTypes = target.getSubscriptionTypes();
		//
		List<String> result = getAllowList(sourceTypes, targetTypes,
				ignoreCaseComparer);
		return result;
	}

	/**
	 * 2つの文字列のリストを受け取り、両方に存在する文字列だけのリストを作成する。 「Any」が含まれる場合には、相手先すべての文字列を許す。
	 * 返り値のリストに「Any」自体は含まれないことに注意すること。
	 * <p>
	 * 文字列はCaseを無視して比較が行われる。<br>
	 * Caseが違う文字列の場合、結果のリストに含まれるのは1番目の引数の文字列とおなじCaseとなる。<br>
	 * 順番は、oneの出現順の後に、twoの出現順（oneがanyの場合のみ）で表示される。
	 * 
	 * @param one
	 * @param two
	 * @param comparer
	 * @return
	 */
	public static List<String> getAllowList(List<String> one, List<String> two,
			TypeComparer comparer) {
		boolean isAllowAny_One = PortImpl.isExistAny(one);
		boolean isAllowAny_Two = PortImpl.isExistAny(two);

		List<String> result = new ArrayList<String>();
		for (String type1 : one) {
			if (PortImpl.isAnyString(type1)) {
				continue;
			}
			if (isAllowAny_Two) {
				result.add(type1);
			} else {
				String match = null;
				for (String type2 : two) {
					match = comparer.match(type1, type2);
					if (match != null) {
						break;
					}
				}
				if (match != null) {
					result.add(match);
				}
			}
		}
		if (isAllowAny_One) {
			for (String type1 : two) {
				if (PortImpl.isAnyString(type1)) {
					continue;
				}
				String match = null;
				for (String type2 : result) {
					match = comparer.match(type1, type2);
					if (match != null) {
						break;
					}
				}
				if (match == null) {
					result.add(type1);
				}
			}
		}
		for (String type : new ArrayList<String>(result)) {
			if (PortImpl.isAnyString(type)) {
				result.remove(type);
			}
		}
		return result;
	}

	/** 型比較インターフェース */
	public static interface TypeComparer {
		String match(String type1, String type2);
	}

	/** デフォルト型比較(IgnoreCase) */
	static TypeComparer ignoreCaseComparer = new TypeComparer() {
		@Override
		public String match(String type1, String type2) {
			if (type1 != null && type1.equalsIgnoreCase(type2)) {
				return type1;
			}
			return null;
		}
	};

	/** データ型比較 */
	static TypeComparer dataTypeComparer = new TypeComparer() {
		@Override
		public String match(String type1, String type2) {
			boolean isIFR1 = isIFR(type1);
			boolean isIFR2 = isIFR(type2);
			// IFR形式同士(1.1)、単純形式同士(1.0)の場合はデフォルト型比較
			if (isIFR1 == isIFR2) {
				return ignoreCaseComparer.match(type1, type2);
			}
			// 1.1/1.0混在時は後方一致によるあいまい比較
			String ifrType = null;
			String oldType = null;
			if (isIFR1) {
				ifrType = type1;
				oldType = type2;
			} else if (isIFR2) {
				ifrType = type2;
				oldType = type1;
			}
			if (ifrType == null) {
				return null;
			}
			String ifr[] = ifrType.split(":");
			String ifrSeg[] = ifr[1].split("/");
			String oldSeg[] = oldType.split("::");
			if (oldSeg.length > ifrSeg.length) {
				return null;
			}
			for (int i = 1; i <= oldSeg.length; i++) {
				String s1 = oldSeg[oldSeg.length - i];
				String s2 = ifrSeg[ifrSeg.length - i];
				if (!s1.equalsIgnoreCase(s2)) {
					return null;
				}
			}
			// 1.1/1.0混在時のConnectorProfileにはIFR形式を使用
			// return oldType;
			return ifrType;
		}
	};

	/** IFR形式の場合はtrue (ex. IDL:RTC/TimedLong:1.0) */
	static boolean isIFR(String type) {
		String ifr[] = (type == null ? "" : type).split(":");
		if (ifr.length == 3 && ifr[0].equals("IDL")) {
			return true;
		}
		return false;
	}

	public static List<String> sortTypes(List<String> list) {
		return sortTypes(list, false);
	}

	public static List<String> sortTypes(List<String> list,
			final boolean reverse) {
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				return a.compareTo(b) * (reverse ? -1 : 1);
			}
		});
		return list;
	}

	public static String join(List<String> list, String d) {
		String result = "";
		for (String s : list) {
			result += (result.isEmpty() ? "" : d) + s;
		}
		return result;
	}

}
