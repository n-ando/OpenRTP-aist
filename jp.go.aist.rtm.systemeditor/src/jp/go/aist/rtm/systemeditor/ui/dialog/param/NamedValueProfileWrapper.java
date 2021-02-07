package jp.go.aist.rtm.systemeditor.ui.dialog.param;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.CompareToBuilder;

import jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper.Secretable;

/**
 * NamedValueを編集するためのラッパー
 */
public class NamedValueProfileWrapper implements Comparable<NamedValueProfileWrapper>, Secretable {

	private String key;
	private String value;

	private boolean isKeyModified = false;
	private boolean isValueModified = false;

	private String typeName;

	public NamedValueProfileWrapper(String key, String value, String typeName) {
		this.key = key;
		this.value = value;
		this.typeName = typeName;
	}

	public NamedValueProfileWrapper(String key) {
		this(key, null, null);
	}

	public NamedValueProfileWrapper(String key, String value) {
		this(key, value, null);
	}

	/**
	 * 設定値のキー名を取得します
	 * 
	 * @return キー名
	 */
	public String getKey() {
		return this.key;
	}

	/**
	 * 設定値のキー名を設定します。<br>
	 * 現在のキー名から変更になる場合は、キー変更中状態になります。
	 * 
	 * @param key
	 *            キー名
	 */
	public void setKey(String key) {
		if (this.key != null && this.key.equals(key)) {
			return;
		}
		this.key = key;
		this.isKeyModified = true;
	}

	/**
	 * 設定値の値を取得します。
	 * 
	 * @return 設定値
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * 設定値の値を設定します。<br>
	 * 現在の設定値から変更になる場合は、値変更中状態になります。
	 * 
	 * @param value
	 *            設定値
	 */
	public void setValue(String value) {
		if (this.value != null && this.value.equals(value)) {
			return;
		}
		this.value = value;
		this.typeName = null;
		this.isValueModified = true;
	}

	/**
	 * キー変更状態を判定します。
	 * 
	 * @return キー変更中の場合はtrue
	 */
	public boolean isKeyModified() {
		return isKeyModified;
	}

	/**
	 * 設定値変更状態を判定します。
	 * 
	 * @return 設定値変更中の場合はtrue
	 */
	public boolean isValueModified() {
		return isValueModified;
	}

	public String getValueAsString() {
		if (value == null)
			return typeName;
		return value;
	}


	/**
	 * 隠し設定値(キー名が「__」で始まる)を判定します
	 * 
	 * @return 隠し設定値の場合はtrue
	 */
	@Override
	public boolean isSecret() {
		return (this.key != null && this.key.startsWith("__"));
	}

	@Override
	public NamedValueProfileWrapper clone() {
		NamedValueProfileWrapper result = new NamedValueProfileWrapper(key);
		result.setValue(getValueAsString());
		result.isKeyModified = isKeyModified;
		result.isValueModified = isValueModified;
		return result;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("key=").append(key).append(" keyModify=").append(isKeyModified).append(" value=")
				.append(getValueAsString()).append(" valueModify=").append(isValueModified);
		return buffer.toString();
	}

	/**
	 * @see java.lang.Comparable#compareTo(Object)
	 */
	@Override
	public int compareTo(NamedValueProfileWrapper object) {
		return new CompareToBuilder().append(this.key, object.key).toComparison();
	}

	public boolean canModify() {
		if (value != null)
			return true;
		return typeName == null;
	}

}
