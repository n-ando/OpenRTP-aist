package jp.go.aist.rtm.systemeditor.ui.dialog.param;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openrtp.namespaces.rts.version02.Component;
import org.openrtp.namespaces.rts.version02.ConfigurationData;
import org.openrtp.namespaces.rts.version02.ConfigurationSet;

/**
 * コンフィグセットの編集用データ
 *
 */
public class ComponentProfileWrapper {

	public static ComponentProfileWrapper create(Component target, boolean isSort) {
		ComponentProfileWrapper result = new ComponentProfileWrapper();
		List<ConfigurationSetProfileWrapper> configSetList = result.getConfigurationSetList();
		List<ConfigurationSetProfileWrapper> secretConfigSetList = new ArrayList<>();

		// パラメータ名−widget種別
		Map<String, String> widgets = new HashMap<>();
		// configurationSet名−制約条件マップ(パラメータ名−制約)
		Map<String, Map<String, String>> conditions = new HashMap<>();

		for (ConfigurationSet cs : target.getConfigurationSets()) {
			if (cs.getId().equals("__widget__")) {
				for (ConfigurationData nv : cs.getConfigurationData()) {
					widgets.put(nv.getName(), nv.getData());
				}
			} else if (cs.getId().startsWith("__")) {
				String key = cs.getId().substring(2);
				Map<String, String> kc = new HashMap<>();
				for (ConfigurationData nv : cs.getConfigurationData()) {
					kc.put(nv.getName(), nv.getData());
				}
				conditions.put(key, kc);
			}
		}

		result.widgetSetting = widgets;
		result.conditionSetting = conditions;

		for (ConfigurationSet cs : target.getConfigurationSets()) {
			ConfigurationSetProfileWrapper configSetWrapper = new ConfigurationSetProfileWrapper(cs,
					cs.getId());
			List<NamedValueProfileWrapper> namedValueList = configSetWrapper.getNamedValueList();
			List<NamedValueProfileWrapper> secretNamedValueList = new ArrayList<>();

			// configurationSetに対応する制約条件(なければデフォルトを使用)
			Map<String, String> conds = null;
			if (!cs.getId().startsWith("__")) {
				conds = conditions.get(cs.getId());
				if (conds == null) {
					conds = conditions.get("constraints__");
				}
			}
			for (ConfigurationData nv : cs.getConfigurationData()) {
				NamedValueProfileWrapper namedValueWrapper = new NamedValueProfileWrapper(nv.getName(),
						nv.getData());
//				if (conds != null) {
//					String type = widgets.get(nv.getName());
//					String cond = conds.get(nv.getName());
//					namedValueWrapper.setWidgetAndCondition(type, cond);
//				}
				if (namedValueWrapper.isSecret()) {
					secretNamedValueList.add(namedValueWrapper);
				} else {
					namedValueList.add(namedValueWrapper);
				}
			}
			if(isSort) {
				// 隠しNamedValueは後方へ整列
				Collections.sort(namedValueList);
			}
			namedValueList.addAll(secretNamedValueList);

			if (target.getActiveConfigurationSet() != null
					&& target.getActiveConfigurationSet().equals(cs.getId())) {
				result.setActiveConfigSet(configSetWrapper);
			}

			if (configSetWrapper.isSecret()) {
				secretConfigSetList.add(configSetWrapper);
			} else {
				configSetList.add(configSetWrapper);
			}
		}
		// 隠しConfiguratoinSetは後方へ整列
		Collections.sort(configSetList);
		configSetList.addAll(secretConfigSetList);

		return result;
	}

	private List<ConfigurationSetProfileWrapper> configurationSetList = new ArrayList<ConfigurationSetProfileWrapper>();
	private ConfigurationSetProfileWrapper activeConfigurationSet;
	private Map<String, String> widgetSetting;
	private Map<String, Map<String, String>> conditionSetting;

	public List<ConfigurationSetProfileWrapper> getConfigurationSetList() {
		return configurationSetList;
	}

	public void setActiveConfigSet(ConfigurationSetProfileWrapper configurationSet) {
		this.activeConfigurationSet = configurationSet;
	}

	public ConfigurationSetProfileWrapper getActiveConfigSet() {
		return activeConfigurationSet;
	}

	public void addConfigurationSet(ConfigurationSetProfileWrapper configurationSet) {
		configurationSetList.add(configurationSet);
	}

	public void removeConfigurationSet(ConfigurationSetProfileWrapper configurationSet) {
		configurationSetList.remove(configurationSet);
	}

	@Override
	public ComponentProfileWrapper clone() {
		ComponentProfileWrapper result = new ComponentProfileWrapper();
		List<ConfigurationSetProfileWrapper> configurationSetList = result.getConfigurationSetList();
		for (ConfigurationSetProfileWrapper cs : this.configurationSetList) {
			configurationSetList.add(cs.clone());
		}
		if (this.widgetSetting != null) {
			result.widgetSetting = new HashMap<String, String>();
			for (String name : this.widgetSetting.keySet()) {
				result.widgetSetting.put(name, this.widgetSetting.get(name));
			}
		}
		if (this.conditionSetting != null) {
			result.conditionSetting = new HashMap<String, Map<String, String>>();
			for (String key : this.conditionSetting.keySet()) {
				Map<String, String> c1 = this.conditionSetting.get(key);
				Map<String, String> c2 = new HashMap<String, String>();
				for (String name : c1.keySet()) {
					c2.put(name, c1.get(name));
				}
				result.conditionSetting.put(key, c2);
			}
		}
		return result;
	}
}
