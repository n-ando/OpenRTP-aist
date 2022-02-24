package jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * NV設定用widgetと制約条件を表します。
 */
public class ConfigurationWidget {
	public static final String TEXT = "text";
	public static final String SLIDER = "slider";
	public static final String SPIN = "spin";
	public static final String RADIO = "radio";
	public static final String CHECKBOX = "checkbox";
	public static final String ORDERED_LIST = "ordered_list";
	
	private String type;
	private ConfigurationCondition condition;
	private String value = null;
	private boolean valueModified = false;
	private boolean isCancel = false;
	
	double sliderStep = 1.0;
	String sliderStepStr = "";
	double spinStep = 0.0;

	public void setCancel(boolean isCancel) {
		this.isCancel = isCancel;
	}
	public boolean isCancel() {
		return isCancel;
	}

	/**
	 * @param widgets	ウィジェット文字列（配列用）
	 * @param cc		制約条件
	 * @return			NV設定用widgetと制約条件のリスト
	 */
	public static List<ConfigurationWidget> parseArrayWidget(String widgets, ConfigurationCondition cc) {
		List<ConfigurationWidget> widgetList = new ArrayList<ConfigurationWidget>();
		for (int i = 0; i < cc.getArraySize(); i++) {
			ConfigurationWidget w = new ConfigurationWidget(parse(widgets,i), cc.getCondition(i));
			widgetList.add(w);
		}
		return widgetList;
	}

	private static String parse(String widgets, int index) {
		if (widgets == null) return null;
		String[] tokens = widgets.split(",");
		if (tokens.length <= index) return tokens[tokens.length -1].trim();
		return tokens[index].trim();
	}

	/**
	 * @param widgets		ウィジェット文字列（マップ用）
	 * @param cc			制約条件
	 * @return				NV設定用widgetと制約条件のマップ
	 */
	public static Map<String, ConfigurationWidget> parseHashWidget(String widgets, ConfigurationCondition cc) {
		Map<String, ConfigurationWidget> widgetMap = new HashMap<String, ConfigurationWidget>();
		for (String key : cc.getHashKeySet()) {
			ConfigurationWidget w = new ConfigurationWidget(parse(widgets, key), cc.getCondition(key));
			widgetMap.put(key, w);
		}
		return widgetMap;
	}

	private static String parse(String widgets, String key) {
		if (widgets == null) return null;

		String s = widgets.trim();
		if (s.charAt(0) != '{')  return s;

		s = s.substring(1, s.length() - 1).trim();	// { }除去

		String[] tokens = s.split(",");
		for(String token : tokens) {
			int delimiter = token.indexOf(":");
			if (delimiter == -1) continue;
			String currentKey = token.substring(0, delimiter).trim();
			if (currentKey.equals(key)) return token.substring(delimiter+1).trim();
		}
		return null;
	}

	/**
	 * @param widgets	ウィジェット文字列（スカラー用）
	 * @param cc		制約条件
	 * @return			NV設定用widgetと制約条件のリスト（要素は1つ）
	 */
	public static List<ConfigurationWidget> parseSimpleWidget(String widgets, ConfigurationCondition cc) {
		List<ConfigurationWidget> widgetList = new ArrayList<ConfigurationWidget>();
		ConfigurationWidget w = new ConfigurationWidget(parse(widgets, 0), cc);
		widgetList.add(w);
		return widgetList;
	}

	ConfigurationWidget(String type, ConfigurationCondition condition) {
		this.type = parseType(type);
		if (condition == null || condition.isNull()) {
			// 制約条件がない場合はテキスト
			this.type = TEXT;
		} else {
			if (this.isSlider() || this.isSpinner()) {
				if (condition.getMax() == null || condition.getMin() == null) {
					// slider、spinnerで最大、最小値がなければテキスト
					this.type = TEXT;
				}
			} else if (this.isRadio() || this.isCheckbox()
					|| this.isOrderedList()) {
				if (!condition.hasEnumList()) {
					// radioは列挙設定がなければテキスト
					this.type = TEXT;
				}
			}
		}
		this.condition = condition;
	}

	private String parseType(String type) {
		if (type == null)
			return TEXT;
		if (type.startsWith(SLIDER)) {
			setSliderStep(type);
			return SLIDER;
		}
		if (type.startsWith(SPIN)) {
			setSpinStep(type);
			return SPIN;
		}
		if (type.equals(RADIO)) {
			return RADIO;
		}
		if (type.equals(CHECKBOX)) {
			return CHECKBOX;
		}
		if (type.equals(ORDERED_LIST)) {
			return ORDERED_LIST;
		}
		return TEXT;
	}

	void setSliderStep(String type) {
		try {
			this.sliderStepStr = type.substring(SLIDER.length() + 1);
			sliderStep = Double.parseDouble(this.sliderStepStr);
			if (sliderStep <= 0.0) {
				sliderStep = 1.0;
			}
		} catch (Throwable t) {
		}
	}

	void setSpinStep(String type) {
		try {
			String step = type.substring(SPIN.length() + 1);
			spinStep = Double.parseDouble(step);
			if (spinStep < 0.0) {
				spinStep = 0.0;
			}
		} catch (Throwable t) {
		}
	}
	
	public String getType() {
		return this.type;
	}

	public boolean isSlider() {
		return this.type.equals(SLIDER);
	}

	public boolean isSpinner() {
		return this.type.equals(SPIN);
	}

	public boolean isRadio() {
		return this.type.equals(RADIO);
	}

	public boolean isCheckbox() {
		return this.type.equals(CHECKBOX);
	}

	public boolean isOrderedList() {
		return this.type.equals(ORDERED_LIST);
	}

	public boolean isText() {
		return !(this.isSlider() || this.isSpinner() || this.isRadio()
				|| this.isCheckbox() || this.isOrderedList());
	}

	public boolean hasCondition() {
		return (this.condition != null);
	}

	public ConfigurationCondition getCondition() {
		if (!hasCondition())
			return ConfigurationCondition.NULL_CONDITION;
		return this.condition;
	}

	public void setValue(String value) {
		if (value == null || value.equals(this.value)) {
			return;
		}
		this.value = value;
		this.valueModified = true;
	}

	public void setValueByArray(String[] values) {
		if (values == null) {
			return;
		}
		String val = "";
		for (String s : values) {
			if (!val.isEmpty()) {
				val += ",";
			}
			val += s;
		}
		setValue(val);
	}

	public String getValue() {
		return this.value;
	}

	public String[] getValueAsArray() {
		String[] values = new String[0];
		if (this.value != null && !this.value.isEmpty()) {
			values = this.value.split(",");
		}
		return values;
	}

	public void clearValueModified() {
		this.valueModified = false;
	}

	public boolean isValueModified() {
		return this.valueModified;
	}

	public int getSliderMaxStep() {
		if (hasCondition() && condition.getMax() != null
				&& condition.getMin() != null) {
			double d = condition.getMaxValue() - condition.getMinValue();
			return (int) (Math.ceil(d / sliderStep));
		}
		return 100;
	}

	public double getSliderStep() {
		return this.sliderStep;
	}

	public String getSliderStepStr() {
		return this.sliderStepStr;
	}
	
	public int getSpinIncrement() {
		double step = 1.0;
		if (hasCondition() && condition.getDigits() > 0) {
			step = 1.0 / Math.pow(10.0, condition.getDigits());
		}
		int inc = (int) (spinStep / step);
		return (inc == 0) ? 1 : inc;
	}

	@Override
	public ConfigurationWidget clone() {
		ConfigurationWidget result = new ConfigurationWidget(this.type,
				(this.condition != null) ? this.condition.clone() : null);
		result.sliderStep = sliderStep;
		result.sliderStepStr = sliderStepStr;
		result.spinStep = spinStep;
		return result;
	}

	@Override
	public String toString() {
		return "type=" + this.type + " condition=" + this.condition;
	}
}
