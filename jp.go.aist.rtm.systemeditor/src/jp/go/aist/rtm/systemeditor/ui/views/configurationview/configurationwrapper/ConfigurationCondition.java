package jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jp.go.aist.rtm.systemeditor.nl.Messages;

/**
 * コンフィグデータの制約条件
 *
 */
public class ConfigurationCondition {

	/** 不正な数値の例外 */
	public static class NumberException extends Exception {
		private static final long serialVersionUID = -7918405683075295181L;

		public NumberException(String m) {
			super(m);
		}

		public NumberException(String m, Exception e) {
			super(m, e);
		}
	};

	/** 不正な形式の例外 */
	public static class FormatException extends Exception {
		private static final long serialVersionUID = 2976559830653469461L;

		public FormatException(String m) {
			super(m);
		}

		public FormatException(String m, Exception e) {
			super(m, e);
		}
	};

	/** 空条件オブジェクト(Nullオブジェクト) */
	public static final ConfigurationCondition NULL_CONDITION = new ConfigurationCondition(
			true);

	/** 空条件の場合はtrue */
	boolean isNull = false;

	/** 即値設定の値 */
	String constValue;

	/** 上限値 */
	String max;

	/** 上限値を含む場合はtrue */
	boolean maxEquals = false;

	/** 下限値 */
	String min;

	/** 下限値を含む場合はtrue */
	boolean minEquals = false;

	/** 値の精度 (digits=3 -> 0.001) */
	int digits = -1;

	/** 列挙型のリスト */
	List<String> enumList = null;

	/** 配列型条件のリスト */
	List<ConfigurationCondition> conditionList = null;

	/** ハッシュ型条件のマップ */
	Map<String, ConfigurationCondition> conditionMap = null;

	/**
	 * 制約条件文字列をパースして、条件のリストを生成します。
	 * @param s 制約条件文字列
	 * @return ConfigurationConditionオブジェクト
	 * @throws NumberException 数値フォーマットエラー
	 * @throws FormatException 条件式フォーマットエラー
	 */
	public static ConfigurationCondition parse(String s)
			throws NumberException, FormatException {
		ConfigurationCondition result = NULL_CONDITION;
		if (s == null) {
			return result;
		}
		s = s.trim();
		if (s.equals("")) { //$NON-NLS-1$
			return result;
		}
		boolean isEnum = false;
		if (s.charAt(0) == '(') {
			int index = s.indexOf(")", 1); //$NON-NLS-1$
			if (index < s.length()) {
				index = skipNextSpace(index, s);
			}
			if (index == s.length() - 1) {
				isEnum = true;
			}
		}
		if (isEnum) {
			// 列挙型
			ConfigurationCondition cc = parseEnum(s);
			if (cc != null)
				result = cc;
		} else if (s.charAt(0) == '{') {
			// ハッシュ型
			ConfigurationCondition cc = parseHash(s);
			if (cc != null)
				result = cc;
		} else if (s.split(",").length > 1) { //$NON-NLS-1$
			// 配列型
			ConfigurationCondition cc = parseArray(s);
			if (cc != null)
				result = cc;
		} else {
			ConfigurationCondition cc = parseX(s);
			if (cc != null)
				result = cc;
		}
		return result;
	}


	/**
	 * 範囲設定のパースを行います。(例 0<x<100, 100(即値))
	 * @param s 制約条件文字列
	 * @return ConfigurationConditionのオブジェクト
	 * @throws NumberException 数値フォーマットエラー
	 * @throws FormatException 条件式フォーマットエラー
	 */
	public static ConfigurationCondition parseX(String s)
			throws NumberException, FormatException {
		ConfigurationCondition result = new ConfigurationCondition();

		if (s.indexOf('x') == -1) {
			try {
				Double.valueOf(s);
				result.constValue = s;
				return result;
			} catch (NumberFormatException e) {
				throw new NumberException(Messages.getString("ConfigurationCondition.3") + s, e); //$NON-NLS-1$
			}
		}
		s = s.trim();

		String lPart = s.substring(0, s.indexOf("x")).trim(); //$NON-NLS-1$
		String rPart = s.substring(s.indexOf("x") + 1).trim(); //$NON-NLS-1$
		String value = null;
		char sign = '\0';

		if (lPart.length() > 0) {
			int index = lPart.length() - 1;
			value = null;
			try {
				boolean eq = false;
				if (lPart.charAt(index) == '=') {
					eq = true;
					index = skipPrevSpace(index - 1, lPart);
				}
				sign = lPart.charAt(index);
				if (sign == '<' || sign == '>') {
					index = skipPrevSpace(index - 1, lPart);
					if (index < 0) {
						throw new FormatException(Messages.getString("ConfigurationCondition.6") + lPart); //$NON-NLS-1$
					}
					value = lPart.substring(0, index + 1);
					Double.valueOf(value);
					if (sign == '<') {
						result.minEquals = eq;
						result.min = value;
					} else {
						result.maxEquals = eq;
						result.max = value;
					}
				} else {
					throw new FormatException(Messages.getString("ConfigurationCondition.7") + lPart); //$NON-NLS-1$
				}
			} catch (NumberFormatException e) {
				throw new NumberException(Messages.getString("ConfigurationCondition.8") + value, e); //$NON-NLS-1$
			}
		}
		if (rPart.length() > 0) {
			int index = skipNextSpace(0, rPart);
			value = null;
			try {
				if (sign != '\0' && sign != rPart.charAt(index)) {
					throw new FormatException(Messages.getString("ConfigurationCondition.9") + lPart //$NON-NLS-1$
							+ Messages.getString("ConfigurationCondition.10") + rPart); //$NON-NLS-1$
				}
				sign = rPart.charAt(index);
				if (sign == '<' || sign == '>') {
					index = skipNextSpace(index + 1, rPart);
					if (index >= rPart.length()) {
						throw new FormatException(Messages.getString("ConfigurationCondition.11") + rPart); //$NON-NLS-1$
					}
					boolean eq = false;
					if (rPart.charAt(index) == '=') {
						eq = true;
						index = skipNextSpace(index + 1, rPart);
					}
					value = rPart.substring(index);
					Double.valueOf(value);
					if (sign == '<') {
						result.max = value;
						result.maxEquals = eq;
					} else {
						result.min = value;
						result.minEquals = eq;
					}
				} else {
					throw new FormatException(Messages.getString("ConfigurationCondition.12") + rPart); //$NON-NLS-1$
				}
			} catch (NumberFormatException e) {
				throw new NumberException(Messages.getString("ConfigurationCondition.13") + value, e); //$NON-NLS-1$
			}
		}
		if (result.min != null && result.max != null) {
			if (Double.valueOf(result.min) > Double.valueOf(result.max)) {
				throw new NumberException(Messages.getString("ConfigurationCondition.14") + result.min //$NON-NLS-1$
						+ Messages.getString("ConfigurationCondition.15") + result.max); //$NON-NLS-1$
			}
		}
		return result;
	}

	/**
	 * 列挙型のパースを行います。(例 (9200,9500,10000))
	 * @param s 制約条件文字列
	 * @return ConfigurationConditionのオブジェクト
	 * @throws FormatException 条件式フォーマットエラー
	 */
	public static ConfigurationCondition parseEnum(String s) throws FormatException {
		ConfigurationCondition result = new ConfigurationCondition();
		s = s.trim();
		if (s.charAt(0) != '(' || s.charAt(s.length() - 1) != ')') {
			throw new FormatException(Messages.getString("ConfigurationCondition.16") + s); //$NON-NLS-1$
		}
		// (, ) を除去
		s = s.substring(1, s.length() - 1).trim();
		result.enumList = new ArrayList<String>();
		for (String ss : s.split(",")) { //$NON-NLS-1$
			if (!result.enumList.contains(ss.trim())) {
				result.enumList.add(ss.trim());
			}
		}
		return result;
	}

	/**
	 * 配列型条件のパースを行います。(例 0<x<1, 2<x<=5)
	 * @param s 制約条件文字列
	 * @return ConfigurationConditionのオブジェクト
	 * @throws NumberException 数値フォーマットエラー
	 * @throws FormatException 条件式フォーマットエラー
	 */
	public static ConfigurationCondition parseArray(String s)
			throws NumberException, FormatException {
		ConfigurationCondition result = new ConfigurationCondition();
		result.conditionList = new ArrayList<ConfigurationCondition>();
		s = s.trim();
		int start = 0, end = 0;
		while (start <= s.length()) {
			if (start == s.length()) {
				result.conditionList.add(NULL_CONDITION);
				break;
			}
			if (s.charAt(start) == '(') {
				end = s.indexOf(")", start); //$NON-NLS-1$
				if (end != -1) {
					end = s.indexOf(",", end); //$NON-NLS-1$
				}
			} else {
				end = s.indexOf(",", start); //$NON-NLS-1$
			}
			if (end == -1) {
				end = s.length();
			}
			String p = s.substring(start, end).trim();
			if (p.length() == 0) {
				result.conditionList.add(NULL_CONDITION);
				start = skipNextSpace(end + 1, s);
				continue;
			}
			try {
				ConfigurationCondition cc;
				if (p.charAt(0) == '(') {
					// 列挙型
					cc = parseEnum(p);
				} else {
					cc = parseX(p);
				}
				result.conditionList.add((cc != null) ? cc : NULL_CONDITION);
			} catch (NumberException e) {
				result.conditionList.add(NULL_CONDITION);
			} catch (FormatException e) {
				result.conditionList.add(NULL_CONDITION);
			}

			start = skipNextSpace(end + 1, s);
		}
		return result;
	}

	/**
	 * ハッシュ型条件のパースを行います。(例 {key0: 0<x<1, key1: 2<x<=5} )
	 * @param s 制約条件文字列
	 * @return ConfigurationConditionのオブジェクト
	 * @throws NumberException 数値フォーマットエラー
	 * @throws FormatException 条件式フォーマットエラー
	 */
	public static ConfigurationCondition parseHash(String s)
			throws NumberException, FormatException {
		ConfigurationCondition result = new ConfigurationCondition();
		result.conditionMap = new HashMap<String, ConfigurationCondition>();
		s = s.trim();
		if (s.charAt(0) != '{' || s.charAt(s.length() - 1) != '}') {
			throw new FormatException(Messages.getString("ConfigurationCondition.21") + s); //$NON-NLS-1$
		}
		// { }除去
		s = s.substring(1, s.length() - 1).trim();

		int start = 0, end = 0;
		String key;
		while (start <= s.length()) {
			end = s.indexOf(":", start); //$NON-NLS-1$
			if (end == -1) {
				// キーなし
				break;
			}
			key = s.substring(start, end).trim();
			if (key.length() == 0) {
				throw new FormatException(Messages.getString("ConfigurationCondition.23") + key); //$NON-NLS-1$
			}

			start = skipNextSpace(end + 1, s);
			if (start == s.length()) {
				result.conditionMap.put(key, NULL_CONDITION);
				break;
			}
			if (s.charAt(start) == '(') {
				end = s.indexOf(")", start); //$NON-NLS-1$
				if (end != -1) {
					end = s.indexOf(",", end); //$NON-NLS-1$
				}
			} else {
				end = s.indexOf(",", start); //$NON-NLS-1$
			}
			if (end == -1) {
				end = s.length();
			}
			String p = s.substring(start, end).trim();
			if (p.length() == 0) {
				result.conditionMap.put(key, NULL_CONDITION);
				start = skipNextSpace(end + 1, s);
				continue;
			}
			try {
				ConfigurationCondition cc;
				if (p.charAt(0) == '(') {
					// 列挙型
					cc = parseEnum(p);
				} else {
					cc = parseX(p);
				}
				result.conditionMap
						.put(key, (cc != null) ? cc : NULL_CONDITION);
			} catch (NumberException e) {
				result.conditionMap.put(key, NULL_CONDITION);
			} catch (FormatException e) {
				result.conditionMap.put(key, NULL_CONDITION);
			}

			start = skipNextSpace(end + 1, s);
		}
		return result;
	}


	ConfigurationCondition() {
	}

	ConfigurationCondition(boolean isNull) {
		this.isNull = isNull;
	}

	/**
	 * 空条件を判定します。
	 * @return 空条件の場合はtrue
	 */
	public boolean isNull() {
		return this.isNull;
	}

	public String getConstValue() {
		return this.constValue;
	}

	public boolean hasConstValue() {
		return getConstValue() != null;
	}

	public String getMax() {
		return this.max;
	}

	public Double getMaxValue() {
		return Double.valueOf(this.max);
	}

	public boolean isMaxEquals() {
		return this.maxEquals;
	}

	public String getMin() {
		return this.min;
	}

	public Double getMinValue() {
		return Double.valueOf(this.min);
	}

	public boolean isMinEquals() {
		return this.minEquals;
	}

	public boolean hasEnumList() {
		return this.enumList != null;
	}

	public List<String> getEnumList() {
		return this.enumList;
	}

	/**
	 * 最大値の精度を整数に変換します (10.0 -> 100)
	 * @return 最大値(整数)
	 */
	public Integer getMaxByInteger() {
		return this.getIntegerByDigits(this.getMaxValue().doubleValue());
	}

	/**
	 * 最小値の精度を整数に変換します (10.0 -> 100)
	 * @return 最小値(整数)
	 */
	public Integer getMinByInteger() {
		return this.getIntegerByDigits(this.getMinValue().doubleValue());
	}

	/**
	 * 小数をdigits桁ずらして整数に変換します (digits=2 10 -> 1000)
	 * @param dvalue 小数値
	 * @return 整数値
	 */
	public Integer getIntegerByDigits(double dvalue) {
		dvalue *= Math.pow(10.0, this.getDigits());
		return new Integer((int) dvalue);
	}

	/**
	 * 整数をdigits桁の小数に変換します (digits=2 1000 -> 10.0)
	 * @param ivalue 整数値
	 * @return 小数値
	 */
	public Double getDecimalByDigits(int ivalue) {
		Double d = Double.valueOf(ivalue);
		d /= Math.pow(10.0, this.getDigits());
		return d;
	}

	/**
	 * min、maxの設定値より、精度の高い方(小数桁の大きい方)の桁数を取得します
	 * @return 精度(小数桁)
	 */
	public int getDigits() {
		if (this.digits != -1)
			return this.digits;
		int i = 0;
		int d = 0;
		if (this.min != null) {
			i = this.min.indexOf("."); //$NON-NLS-1$
			if (i != -1) {
				d = this.min.length() - i - 1;
			}
		}
		this.digits = d;
		d = 0;
		if (this.max != null) {
			i = this.max.indexOf("."); //$NON-NLS-1$
			if (i != -1) {
				d = this.max.length() - i - 1;
			}
		}
		if (this.digits < d)
			this.digits = d;
		return this.digits;
	}

	/**
	 * valueをmin〜max内でmaxStep段階でステップ位置に変換します
	 * @param value 値
	 * @param widget Widgetオブジェクト
	 * @return 換算したステップ位置
	 */
	public int getStepByValue(String value, ConfigurationWidget widget) {
		if (this.min == null || this.max == null)
			return 0;
		Double dvalue;
		try {
			dvalue = Double.valueOf(value);
		} catch (NumberFormatException e) {
			return 0;
		}
		Double dmin = Double.valueOf(this.min);
		Double dmax = Double.valueOf(this.max);
		if (dvalue < dmin)
			return 0;
		if (dvalue > dmax)
			return widget.getSliderMaxStep();
		Double step = (dmax - dmin) / widget.getSliderMaxStep();
//		int result = (int) ((dvalue - dmin) / step );
		int result = (int) ((dvalue - dmin) / step + 0.5);
		return result;
	}

	/**
	 * maxStep内のstepをmin〜max内の値に変換します
	 * @param step ステップ位置
	 * @param widget Widgetオブジェクト
	 * @param previousValue
	 * @return 換算した値
	 */
	public String getValueByStep(int step, ConfigurationWidget widget,
			String previousValue, String stepSizeStr) {
		if (this.min == null || this.max == null)
			return "0"; //$NON-NLS-1$
		if (step < 0) {
			step = 0;
		}
		if (step > widget.getSliderMaxStep()) {
			step = widget.getSliderMaxStep();
		}
		double dmin = Double.valueOf(this.min);
		double dmax = Double.valueOf(this.max);
		double dprev = Double.valueOf(previousValue);
		double dvalue = dmin + (widget.getSliderStep() * step);
		dvalue = (dvalue > dmax) ? dmax : dvalue;
		if (isInt() && stepSizeStr.indexOf(".") == -1) {
			if (dvalue > dprev && dvalue < dprev + 1) {
				dvalue = dprev + 1;
			} else if (dvalue < dprev && dvalue > dprev - 1) {
				dvalue = dprev - 1;
			}
			return Integer.toString((int) dvalue);
		} else {
			int digit = getDigits();
			int d_point = stepSizeStr.indexOf(".");
			if (d_point != -1) {
				int digitSS = stepSizeStr.length() - d_point - 1;
				if(digit < digitSS) {
					digit = digitSS;
				}
			}
			return Double.toString(new BigDecimal(dvalue).setScale(digit,
					BigDecimal.ROUND_HALF_EVEN).doubleValue());
		}
	}

	private boolean isInt() {
		if (this.min.indexOf(".") != -1 || this.max.indexOf(".") != -1) { //$NON-NLS-1$ //$NON-NLS-2$
			// 最大値、最小値のいずれかが小数の場合は小数扱い
			return false;
		}
		return true;
	}

	/**
	 * 最小/最大値を超える値を有効範囲内に丸める
	 * @param value 入力値
	 * @return 最小/最大の範囲内の有効な値
	 */
	public String adjustMinMaxValue(String value) {
		if (validate(value)) {
			return value;
		}
		double dmin = Double.valueOf(this.min);
		double dmax = Double.valueOf(this.max);
		double dvalue = Double.valueOf(value);
		double d = 1.0 / Math.pow(10.0, this.getDigits());
		if (dvalue >= dmax) {
			dvalue = dmax;
			if (this.maxEquals) {
				return this.max;
			}
			if (isInt()) {
				return Integer.toString((int) (dvalue - 1.0));
			}
			return Double.toString(dvalue - d);
		} else if (dvalue <= dmin) {
			dvalue = dmin;
			if (this.minEquals) {
				return this.min;
			}
			if (isInt()) {
				return Integer.toString((int) (dvalue + 1.0));
			}
			return Double.toString(dvalue + d);
		}
		return value;
	}

	/**
	 * 制約条件判定
	 * @param value 判定対象
	 * @return valueが制約条件を満たせばtrue
	 */
	public boolean validate(String value) {
		if (this.isNull) {
			// 空条件の場合はtrue
			return true;
		}
		if (this.hasConstValue()) {
			// 即値の場合
			try {
				Double dconst = Double.valueOf(this.getConstValue());
				Double dvalue = Double.valueOf(value);
				return dconst.doubleValue() == dvalue.doubleValue();
			} catch (NumberFormatException e) {
				return false;
			}
		} else if (this.hasEnumList()) {
			// 列挙型の場合
			for (String es : this.getEnumList()) {
				if (es.equals(value)) {
					return true;
				}
			}
			return false;
		} else {
			try {
				// 範囲指定
				Double dvalue = Double.valueOf(value);
				boolean result = false;
				if (this.min != null) {
					Double dm = Double.valueOf(this.min);
					if (this.minEquals) {
						result = dvalue.doubleValue() >= dm.doubleValue();
					} else {
						result = dvalue.doubleValue() > dm.doubleValue();
					}
					if (!result)
						return false;
				}
				if (this.max != null) {
					Double dm = Double.valueOf(this.max);
					if (this.maxEquals) {
						result = dvalue.doubleValue() <= dm.doubleValue();
					} else {
						result = dvalue.doubleValue() < dm.doubleValue();
					}
					if (!result)
						return false;
				}
				return result;
			} catch (NumberFormatException e) {
				return false;
			}
		}
	}


	/**
	 * 配列条件を判定します。
	 * @return 配列条件の場合はtrue
	 */
	public boolean isArrayCondition() {
		return (this.conditionList != null && this.conditionList.size() > 0);
	}

	/**
	 * 配列条件の要素数を返します。
	 * @return 要素数
	 */
	public int getArraySize() {
		if (!this.isArrayCondition()) {
			return 0;
		}
		return this.conditionList.size();
	}

	/**
	 * index番目の条件を取得します。
	 * @param index 条件オブジェクトのインデックス
	 * @return index番目の条件
	 */
	public ConfigurationCondition getCondition(int index) {
		if (index >= this.getArraySize()) {
			return null;
		}
		return this.conditionList.get(index);
	}


	/**
	 * ハッシュ条件を判定します。
	 * @return ハッシュ条件の場合はtrue
	 */
	public boolean isHashCondition() {
		return (this.conditionMap != null && this.conditionMap.keySet().size() > 0);
	}

	/**
	 * ハッシュ条件のキーセットを返します。
	 * @return ハッシュ条件のキーセット
	 */
	public Set<String> getHashKeySet() {
		if (!this.isHashCondition()) {
			return new HashSet<String>();
		}
		return this.conditionMap.keySet();
	}

	/**
	 * keyをキーにする条件を取得します。
	 * @param key 条件オブジェクトのキー
	 * @return keyに対応する条件
	 */
	public ConfigurationCondition getCondition(String key) {
		if (this.conditionMap == null) {
			return null;
		}
		return this.conditionMap.get(key);
	}


	@Override
	public ConfigurationCondition clone() {
		ConfigurationCondition result = new ConfigurationCondition();
		result.isNull = this.isNull;
		result.constValue = this.constValue;
		result.max = this.max;
		result.min = this.min;
		result.maxEquals = this.maxEquals;
		result.minEquals = this.minEquals;
		result.digits = this.digits;
		if (this.enumList != null) {
			result.enumList = new ArrayList<String>();
			for (String s : this.enumList) {
				result.enumList.add(s);
			}
		}
		if (this.conditionList != null) {
			result.conditionList = new ArrayList<ConfigurationCondition>();
			for (ConfigurationCondition cc : this.conditionList) {
				result.conditionList.add(cc.clone());
			}
		}
		if (this.conditionMap != null) {
			result.conditionMap = new HashMap<String, ConfigurationCondition>();
			for (String key : this.conditionMap.keySet()) {
				ConfigurationCondition cc = this.conditionMap.get(key);
				result.conditionMap.put(key, (cc.isNull ? NULL_CONDITION : cc));
			}
		}
		return result;
	}

	@Override
	public String toString() {
		if (this.isNull) {
			return "null"; //$NON-NLS-1$
		} else if (this.hasConstValue()) {
			return this.constValue;
		} else if (this.hasEnumList()) {
			String s = "("; //$NON-NLS-1$
			for (String ss : this.enumList) {
				if (!s.equals("(")) { //$NON-NLS-1$
					s += ","; //$NON-NLS-1$
				}
				s += ss;
			}
			return s + ")"; //$NON-NLS-1$
		} else if (this.min != null && this.max != null) {
			return this.min + "<" + (this.minEquals ? "=" : "") + "x<" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					+ (this.maxEquals ? "=" : "") + this.max; //$NON-NLS-1$ //$NON-NLS-2$
		} else if (this.min != null) {
			return "x>" + (this.minEquals ? "=" : "") + this.min; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		} else if (this.max != null) {
			return "x<" + (this.maxEquals ? "=" : "") + this.max; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		} else if (this.isArrayCondition()) {
			return this.conditionList.toString();
		} else if (this.isHashCondition()) {
			String s = "{"; //$NON-NLS-1$
			for (String key : this.conditionMap.keySet()) {
				if (!s.equals("{")) { //$NON-NLS-1$
					s += ", "; //$NON-NLS-1$
				}
				s += key + ":" + this.conditionMap.get(key); //$NON-NLS-1$
			}
			return s + "}"; //$NON-NLS-1$
		}
		return ""; //$NON-NLS-1$
	}

	static int skipNextSpace(int index, String s) {
		if (index < 0 || index >= s.length())
			return index;
		while (s.charAt(index) == ' ')
			index++;
		return index;
	}

	static int skipPrevSpace(int index, String s) {
		if (index < 0 || index >= s.length())
			return index;
		while (s.charAt(index) == ' ')
			index--;
		return index;
	}
}
