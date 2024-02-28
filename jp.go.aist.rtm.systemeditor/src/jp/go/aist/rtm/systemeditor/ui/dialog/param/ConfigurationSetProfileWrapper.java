package jp.go.aist.rtm.systemeditor.ui.dialog.param;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.openrtp.namespaces.rts.version02.ConfigurationSet;

import jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper.Secretable;

/**
 * 変更前と変更後のConfigurationSetを保持するラッパー
 */
public class ConfigurationSetProfileWrapper
		implements Comparable<ConfigurationSetProfileWrapper>, Secretable {

	private List<NamedValueProfileWrapper> namedValueList = new ArrayList<NamedValueProfileWrapper>();
	private String id;
	private boolean isNameModified = false;
	private ConfigurationSet configurationSet;

	public ConfigurationSet getConfigurationSet() {
		return configurationSet;
	}

	public ConfigurationSetProfileWrapper(ConfigurationSet configurationSet, String name) {
		this.id = name;
		this.configurationSet = configurationSet;
	}

	public List<NamedValueProfileWrapper> getNamedValueList() {
		return namedValueList;
	}

	public void addNamedValue(NamedValueProfileWrapper namedValue) {
		namedValueList.add(namedValue);
	}

	public void removeNamedValue(NamedValueProfileWrapper namedValue) {
		namedValueList.remove(namedValue);
	}

	public String getId() {
		return id;
	}

	public void setId(String name) {
		if (name.equals(this.id)) {
			return;
		}

		this.id = name;
		isNameModified = true;
	}

	public boolean isNameModified() {
		return isNameModified;
	}

	public boolean isActivateModified() {
		return false;
	}

	/**
	 * 隠し設定値(ID名が「__」で始まる)を判定します
	 * 
	 * @return 隠し設定値の場合はtrue
	 */
	@Override
	public boolean isSecret() {
		return (this.id != null && this.id.startsWith("__"));
	}

	@Override
	public ConfigurationSetProfileWrapper clone() {
		ConfigurationSetProfileWrapper result = new ConfigurationSetProfileWrapper(this.configurationSet,
				this.id);
		for (NamedValueProfileWrapper nv : this.namedValueList) {
			result.namedValueList.add(nv.clone());
		}
		return result;
	}

	/**
	 * @see java.lang.Comparable#compareTo(Object)
	 */
	@Override
	public int compareTo(ConfigurationSetProfileWrapper object) {
		return new CompareToBuilder().append(this.id, object.id).toComparison();
	}

}
