package jp.go.aist.rtm.systemeditor.manager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.systemeditor.RTSystemEditorPlugin;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.PlatformUI;

/**
 * 設定を管理するマネージャ
 * <p>
 * 設定情報にアクセスするにはこのインスタンスを使用する
 */
public class SystemEditorPreferenceManager {
	private static final String Separator = ":";

	private static SystemEditorPreferenceManager __instance;
	static {
		__instance = new SystemEditorPreferenceManager();
	}

	/**
	 * RTC状態色のキー： Start
	 */
	public static final String COLOR_RTC_STATE_CREATED = SystemEditorPreferenceManager.class
			.getName()
			+ "COLOR_RTC_STATE_CREATED";

	/**
	 * RTC状態色のキー： InActive
	 */
	public static final String COLOR_RTC_STATE_INACTIVE = SystemEditorPreferenceManager.class
			.getName()
			+ "COLOR_RTC_STATE_INACTIVE";

	/**
	 * RTC状態色のキー： Active
	 */
	public static final String COLOR_RTC_STATE_ACTIVE = SystemEditorPreferenceManager.class
			.getName()
			+ "COLOR_RTC_STATE_ACTIVE";

	/**
	 * RTC状態色のキー： Error
	 */
	public static final String COLOR_RTC_STATE_ERROR = SystemEditorPreferenceManager.class
			.getName()
			+ "COLOR_RTC_STATE_ERROR";

	/**
	 * RTC状態色のキー： UnKnown
	 */
	public static final String COLOR_RTC_STATE_UNKNOWN = SystemEditorPreferenceManager.class
			.getName()
			+ "COLOR_RTC_STATE_UNKNOWN";

	/**
	 * RTC状態色のキー： UnKnown
	 */
	public static final String COLOR_RTC_STATE_UNCERTAIN = SystemEditorPreferenceManager.class
			.getName()
			+ "COLOR_RTC_STATE_UNCERTAIN";

	/**
	 * RTCExecutionContext色のキー： Running
	 */
	public static final String COLOR_RTC_EXECUTION_CONTEXT_RUNNING = SystemEditorPreferenceManager.class
			.getName()
			+ "COLOR_RTC_EXECUTION_CONTEXT_RUNNING";

	/**
	 * RTCExecutionContext色のキー： Stopped
	 */
	public static final String COLOR_RTC_EXECUTION_CONTEXT_STOPPED = SystemEditorPreferenceManager.class
			.getName()
			+ "COLOR_RTC_EXECUTION_CONTEXT_STOPPED";

	/**
	 * DataPort色のキー： 未接続
	 */
	public static final String COLOR_DATAPORT_NO_CONNECT = SystemEditorPreferenceManager.class
			.getName()
			+ "COLOR_DATAPORT_NO_CONNECT";

	/**
	 * DataPort色のキー： 接続済
	 */
	public static final String COLOR_DATAPORT_CONNECTED = SystemEditorPreferenceManager.class
			.getName()
			+ "COLOR_DATAPORT_CONNECTED";

	/**
	 * ServicePort色のキー： 未接続
	 */
	public static final String COLOR_SERVICEPORT_NO_CONNECT = SystemEditorPreferenceManager.class
			.getName()
			+ "COLOR_SERVICEPORT_NO_CONNECT";

	/**
	 * ServicePort色のキー： 接続済
	 */
	public static final String COLOR_SERVICEPORT_CONNECTED = SystemEditorPreferenceManager.class
			.getName()
			+ "COLOR_SERVICEPORT_CONNECTED";

	/**
	 * 同期間隔のキー： システムエディタ
	 */
	public static final String SYNC_SYSTEMEDITOR_INTERVAL = SystemEditorPreferenceManager.class
			.getName()
			+ "SYNC_SYSTEMEDITOR_INTERVAL";

	//接続情報
	/**
	 * Interface Typeのキー
	 */
	private static final String CONNECT_INTERFACE_TYPE = SystemEditorPreferenceManager.class.getName()
			+ "CONNECT_INTERFACE_TYPE";

	/**
	 * DataFlow Typeのキー
	 */
	private static final String CONNECT_DATAFLOW_TYPE = SystemEditorPreferenceManager.class.getName()
			+ "CONNECT_DATAFLOW_TYPE";

	/**
	 * Subscription Typeのキー
	 */
	private static final String CONNECT_SUBSCRIPTION_TYPE = SystemEditorPreferenceManager.class.getName()
			+ "CONNECT_SUBSCRIPTION_TYPE";

	/**
	 * Push Policyのキー
	 */
	private static final String CONNECT_PUSH_POLICY = SystemEditorPreferenceManager.class.getName()
			+ "CONNECT_PUSH_POLICY";
	
	/**
	 * Buffer Full Policyのキー
	 */
	private static final String CONNECT_BUFFER_FULL_POLICY = SystemEditorPreferenceManager.class.getName()
			+ "CONNECT_BUFFER_FULL_POLICY";
	
	/**
	 * Buffer Empty Policyのキー
	 */
	private static final String CONNECT_BUFFER_EMPTY_POLICY = SystemEditorPreferenceManager.class.getName()
			+ "CONNECT_BUFFER_EMPTY_POLICY";
	
	// オンラインエディタ
	/** コンポーネントアクションの実行確認 */
	public static final String CONFIRM_COMPONENT_ACTION = SystemEditorPreferenceManager.class
			.getName()
			+ ".CONFIRM_COMPONENT_ACTION";

	// アイコン
	/** コンポーネントアイコン設定 */
	public static final String COMPONENT_ICONS = SystemEditorPreferenceManager.class
			.getName()
			+ ".COMPONENT_ICONS";

	public static final String CHECK_INTERVAL = SystemEditorPreferenceManager.class
			.getName()
			+ ".CHECK_INTERVAL";

	/**
	 * デフォルトの色を管理するマップ
	 */
	public static final Map<String, RGB> defaultRGBMap = new HashMap<String, RGB>();
	static {
		defaultRGBMap.put(COLOR_RTC_STATE_CREATED, new RGB(255, 255, 255));
		defaultRGBMap.put(COLOR_RTC_STATE_INACTIVE, new RGB(0, 0, 255));
		defaultRGBMap.put(COLOR_RTC_STATE_ACTIVE, new RGB(0, 255, 0));
		defaultRGBMap.put(COLOR_RTC_STATE_ERROR, new RGB(255, 0, 0));
		defaultRGBMap.put(COLOR_RTC_STATE_UNKNOWN, new RGB(0, 0, 0));
		defaultRGBMap.put(COLOR_RTC_STATE_UNCERTAIN, new RGB(127, 127, 127));
		defaultRGBMap.put(COLOR_RTC_EXECUTION_CONTEXT_RUNNING, new RGB(128,
				128, 128));
		defaultRGBMap
				.put(COLOR_RTC_EXECUTION_CONTEXT_STOPPED, new RGB(0, 0, 0));
		defaultRGBMap.put(COLOR_DATAPORT_NO_CONNECT, new RGB(0, 0, 255));
		defaultRGBMap.put(COLOR_DATAPORT_CONNECTED, new RGB(96, 255, 96));
		defaultRGBMap.put(COLOR_SERVICEPORT_NO_CONNECT, new RGB(127, 127, 255));
		defaultRGBMap.put(COLOR_SERVICEPORT_CONNECTED, new RGB(0, 255, 255));
	}

	/**
	 * デフォルトの同期間隔を管理するマップ
	 */
	public static final Map<String, Integer> defaultInvervalMap = new HashMap<String, Integer>();
	static {
		defaultInvervalMap.put(SYNC_SYSTEMEDITOR_INTERVAL, Integer.valueOf(1000));
	}

	/**
	 * キャッシュした色（リソース）を管理するマップ
	 */
	private static transient final Map<String, Color> cachedColorMap = new HashMap<String, Color>();

	/**
	 * Interface Typeのデフォルト値
	 */
	public static String[] defaultConnectInterfaceType = {"corba_cdr"};

	/**
	 * DataFlow Typeのデフォルト値
	 */
	public static String[] defaultConnectDataFlowType = {"push", "pull"};
	
	/**
	 * subscription Typeのデフォルト値
	 */
	public static String[] defaultConnectSubscriptionType = {"flush", "new", "periodic"};

	/**
	 * Push Policyのデフォルト値
	 */
	public static String[] defaultConnectPushPolicy = {"all", "fifo", "skip", "new"};
	
	/**
	 * Buffer Full Policyのデフォルト値
	 */
	public static String[] defaultConnectBufferFullPolicy = {"overerite", "do_nothing", "block"};
	
	/**
	 * Buffer Empty Policyのデフォルト値
	 */
	public static String[] defaultConnectBufferEmptyPolicy = {"readback", "do_nothing", "block"};
	
	IPreferenceStore store;

	public SystemEditorPreferenceManager() {
		this.store = RTSystemEditorPlugin.getDefault().getPreferenceStore();
	}

	/**
	 * コンストラクタ
	 * 
	 * @return シングルトン
	 */
	public static SystemEditorPreferenceManager getInstance() {
		return __instance;
	}
	/**
	 * シングルトンをセットする。（基本的に使用してはならない。ユニットテストからの実行のために追加）
	 * 
	 */
	public static void setInstance(SystemEditorPreferenceManager instance) {
		__instance = instance;
	}

	protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	/**
	 * キーから色を返す
	 * <p>
	 * 色はりソースであるため、キャッシュして使用している。
	 * 
	 * @param key
	 * @return 色
	 */
	public synchronized Color getColor(String key) {
		RGB rgb = getRGB(key);

		Color result = cachedColorMap.get(key);
		if (result == null || rgb.equals(result.getRGB()) == false) {
			if (result != null) {
				result.dispose();
			}
			result = new Color(PlatformUI.getWorkbench().getDisplay(), rgb);
			cachedColorMap.put(key, result);
		}

		return result;
	}

	/**
	 * キーからRGBを取得する
	 * 
	 * @param key
	 * @return RGB
	 */
	public RGB getRGB(String key) {
		RGB result = PreferenceConverter.getColor(RTSystemEditorPlugin.getDefault()
				.getPreferenceStore(), key);
		if (result == PreferenceConverter.COLOR_DEFAULT_DEFAULT) { // caution
			// "=="
			result = defaultRGBMap.get(key);
		}

		return result;
	}

	/**
	 * キーに、RGBを関連付ける
	 * 
	 * @param key
	 *            キー
	 * @param newRGB
	 *            関連付けるRGB
	 */
	public void setRGB(String key, RGB newRGB) {
		RGB oldRgb = getRGB(key);

		PreferenceConverter.setValue(RTSystemEditorPlugin.getDefault()
				.getPreferenceStore(), key, newRGB);

		propertyChangeSupport.firePropertyChange(key, oldRgb, newRGB);
	}

	/**
	 * 間隔を取得する
	 * 
	 * @param key
	 *            キー
	 * @return 間隔
	 */
	public int getInterval(String key) {
		RTSystemEditorPlugin.getDefault().getPreferenceStore().setDefault(key, -1);

		int result = RTSystemEditorPlugin.getDefault().getPreferenceStore().getInt(key);
		if (result == -1) { // defaultvalue
			result = defaultInvervalMap.get(key).intValue();
		}

		return result;
	}

	/**
	 * 間隔を設定する
	 * 
	 * @param key
	 *            キー
	 * @param interval
	 *            間隔
	 */
	public void setInterval(String key, int interval) {
		int oldInterval = getInterval(key);

		RTSystemEditorPlugin.getDefault().getPreferenceStore().setValue(key, interval);

		propertyChangeSupport.firePropertyChange(key, oldInterval, interval);
	}

	/**
	 * @see PropertyChangeSupport#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	/**
	 * @see PropertyChangeSupport#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	/**
	 * デフォルト色のマップを取得する
	 * 
	 * @return デフォルト色のマップ
	 */
	public Map<String, RGB> getDefaultRGBMap() {
		return defaultRGBMap;
	}

	/**
	 * デフォルト間隔のマップを取得する
	 * 
	 * @return デフォルト間隔のマップ
	 */
	public Map<String, Integer> getDefaultIntervalMap() {
		return defaultInvervalMap;
	}

	/**
	 * Interface Typeを取得する
	 * 
	 * @return Interface Typeリスト
	 */
	public String[] getInterfaceTypes() {
		return getStringListStoreValue(CONNECT_INTERFACE_TYPE, defaultConnectInterfaceType);
	}
	/**
	 * Interface Typeを設定する
	 * 
	 * @param interfaceType
	 *            Interface Typeリスト
	 */
	public void setInterfaceTypes(List<String> interfaceType) {
		String[] oldInterfaceType = getInterfaceTypes();
		RTSystemEditorPlugin.getDefault().getPreferenceStore().setValue(CONNECT_INTERFACE_TYPE, convertList2String(interfaceType));
		propertyChangeSupport.firePropertyChange(CONNECT_INTERFACE_TYPE, oldInterfaceType, interfaceType);
	}

	/**
	 * Data Flow Typeを取得する
	 * 
	 * @return Data Flow Typeリスト
	 */
	public String[] getDataFlowTypes() {
		return getStringListStoreValue(CONNECT_DATAFLOW_TYPE, defaultConnectDataFlowType);
	}
	/**
	 * Data Flow Typeを設定する
	 * 
	 * @param dataFlowType
	 *            Data Flow Typeリスト
	 */
	public void setDataFlowTypes(List<String> dataflowType) {
		String[] oldDataFlowType = getDataFlowTypes();
		RTSystemEditorPlugin.getDefault().getPreferenceStore().setValue(CONNECT_DATAFLOW_TYPE, convertList2String(dataflowType));
		propertyChangeSupport.firePropertyChange(CONNECT_DATAFLOW_TYPE, oldDataFlowType, dataflowType);
	}

	/**
	 * Subscription Typeを取得する
	 * 
	 * @return Subscription Typeリスト
	 */
	public String[] getSubscriptionTypes() {
		return getStringListStoreValue(CONNECT_SUBSCRIPTION_TYPE, defaultConnectSubscriptionType);
	}
	/**
	 * subscription Typeを設定する
	 * 
	 * @param subscriptionType
	 *            subscription Typeリスト
	 */
	public void setSubscriptionTypes(List<String> subscriptionType) {
		String[] oldSubscriptionType = getSubscriptionTypes();
		RTSystemEditorPlugin.getDefault().getPreferenceStore().setValue(CONNECT_SUBSCRIPTION_TYPE, convertList2String(subscriptionType));
		propertyChangeSupport.firePropertyChange(CONNECT_SUBSCRIPTION_TYPE, oldSubscriptionType, subscriptionType);
	}

	/**
	 * Push Policyを取得する
	 * 
	 * @return Push Policyリスト
	 */
	public String[] getPushPolicies() {
		return getStringListStoreValue(CONNECT_PUSH_POLICY, defaultConnectPushPolicy);
	}
	/**
	 * Push Policyを設定する
	 * 
	 * @param pushPolicies
	 *            Push Policyリスト
	 */
	public void setPushPolicies(List<String> pushPolicies) {
		String[] oldPushPolicies = getPushPolicies();
		RTSystemEditorPlugin.getDefault().getPreferenceStore().setValue(CONNECT_PUSH_POLICY, convertList2String(pushPolicies));
		propertyChangeSupport.firePropertyChange(CONNECT_SUBSCRIPTION_TYPE, oldPushPolicies, pushPolicies);
	}

	/**
	 * Buffer Full Policyを取得する
	 * 
	 * @return Buffer Full Policyリスト
	 */
	public String[] getBufferFullPolicies() {
		return getStringListStoreValue(CONNECT_BUFFER_FULL_POLICY, defaultConnectBufferFullPolicy);
	}
	/**
	 * Buffer Full Policyを設定する
	 * 
	 * @param bufferFullPolicies
	 *            Buffer Full Policyリスト
	 */
	public void setBufferFullPolicies(List<String> bufferFullPolicies) {
		String[] oldPushPolicies = getBufferFullPolicies();
		RTSystemEditorPlugin.getDefault().getPreferenceStore().setValue(CONNECT_BUFFER_FULL_POLICY, convertList2String(bufferFullPolicies));
		propertyChangeSupport.firePropertyChange(CONNECT_BUFFER_FULL_POLICY, oldPushPolicies, bufferFullPolicies);
	}
	
	/**
	 * Buffer Empty Policyを取得する
	 * 
	 * @return Buffer Empty Policyリスト
	 */
	public String[] getBufferEmptyPolicies() {
		return getStringListStoreValue(CONNECT_BUFFER_EMPTY_POLICY, defaultConnectBufferEmptyPolicy);
	}
	/**
	 * Buffer Empty Policyを設定する
	 * 
	 * @param bufferEmptyPolicies
	 *            Buffer Empty Policyリスト
	 */
	public void setBufferEmptyPolicies(List<String> bufferEmptyPolicies) {
		String[] oldPushPolicies = getBufferEmptyPolicies();
		RTSystemEditorPlugin.getDefault().getPreferenceStore().setValue(CONNECT_BUFFER_EMPTY_POLICY, convertList2String(bufferEmptyPolicies));
		propertyChangeSupport.firePropertyChange(CONNECT_BUFFER_EMPTY_POLICY, oldPushPolicies, bufferEmptyPolicies);
	}
	private static String convertList2String(List<String> source) {
		StringBuffer resultTemp = new StringBuffer();
		
		for(String target : source) {
			resultTemp.append(target);
			resultTemp.append(Separator);
		}
		String result = resultTemp.toString();
		if(result.length() ==0) return "";
		return result.substring(0, result.length()-1);
	}

	/**
	 * コンポーネントアクションの実行確認判定
	 * 
	 * @return 実行確認をする場合は true
	 */
	public boolean isConfirmComponentAction() {
		store.setDefault(CONFIRM_COMPONENT_ACTION, false);
		return store.getBoolean(CONFIRM_COMPONENT_ACTION);
	}

	/**
	 * コンポーネントアクションの実行確認設定
	 * 
	 * @param b
	 *            実行確認をする場合は true
	 */
	public void setConfirmComponentAction(boolean b) {
		store.setValue(CONFIRM_COMPONENT_ACTION, b);
	}

	/**
	 * コンポーネントアクションの実行確認初期化
	 */
	public void resetConfirmComponentAction() {
		setConfirmComponentAction(false);
	}

	private String[] getStringListStoreValue(String key, String[] defaultValue) {
		RTSystemEditorPlugin.getDefault().getPreferenceStore().setDefault(key, "");

		String resultTemp = RTSystemEditorPlugin.getDefault().getPreferenceStore().getString(key);
		String[] result;
		if (resultTemp.equals("")) { // defaultvalue
			result = defaultValue;
		} else {
			result = resultTemp.split(Separator);
		}
		return result;
	}

	/** コンポーネントアイコンの設定を読込 */
	public ComponentIconStore loadComponentIconStore(
			ComponentIconStore iconStore) {
		if (iconStore == null) {
			return null;
		}
		store.setDefault(COMPONENT_ICONS, "");
		String value = store.getString(COMPONENT_ICONS);
		iconStore.parsePreference(value);
		return iconStore;

	}

	public ComponentIconStore loadComponentIconStore() {
		return loadComponentIconStore(ComponentIconStore.eINSTANCE);
	}

	/** コンポーネントアイコンの設定を保存 */
	public void saveComponentIconStore(ComponentIconStore iconStore) {
		if (iconStore == null) {
			return;
		}
		String value = iconStore.toPreference();
		store.setValue(COMPONENT_ICONS, value);
	}

	public void saveComponentIconStore() {
		saveComponentIconStore(ComponentIconStore.eINSTANCE);
	}

	/** コンポーネントアイコンの設定をリセット */
	public void resetComponentIconStore() {
		store.setValue(COMPONENT_ICONS, "");
	}

	public int getCheckInterval() {
		store.setDefault(CHECK_INTERVAL, 1);
		return store.getInt(CHECK_INTERVAL);
	}

	public void setCheckInterval(int val) {
		store.setValue(CHECK_INTERVAL, val);
	}

	public void resetCheckInterval() {
		setCheckInterval(1);
	}
}
