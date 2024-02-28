package jp.go.aist.rtm.toolscommon.manager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.jface.preference.IPreferenceStore;

import jp.go.aist.rtm.toolscommon.ToolsCommonPlugin;

/**
 * 設定を管理するマネージャ
 * <p>
 * 設定情報にアクセスするにはこのインスタンスを使用する
 */
public class ToolsCommonPreferenceManager {
	private static ToolsCommonPreferenceManager __instance = new ToolsCommonPreferenceManager();

	private static final String __PREFIX = ToolsCommonPreferenceManager.class.getName();

	/**
	 * タイムアウト判定時間
	 */
	public static final String DEFAULT_TIMEOUT_PERIOD = __PREFIX + "DEFAULT_TIMEOUT_PERIOD";

	/** 状態通知オブザーバの有効/無効設定 */
	public static final String KEY_STATUS_OBSERVER_HB_ENABLE = __PREFIX + ".STATUS_OBSERVER_HB_ENABLE";

	/** 状態通知オブザーバの H.B受信間隔 [sec] */
	public static final String KEY_STATUS_OBSERVER_HB_INTERVAL = __PREFIX + ".STATUS_OBSERVER_HB_INTERVAL";
	/** 状態通知オブザーバのタイムアウト判定回数 */
	public static final String KEY_STATUS_OBSERVER_HB_TRYCOUNT = __PREFIX + ".STATUS_OBSERVER_HB_TRYCOUNT";

	/** ポートイベント通知 送信間隔 [sec] */
	public static final String KEY_STATUS_OBSERVER_PORT_EVENT_SEND_MIN_INTERVAL = __PREFIX
			+ ".STATUS_OBSERVER_PORT_EVENT_SEND_MIN_INTERVAL";
	/** ポートイベント通知 受信間隔 [sec] */
	public static final String KEY_STATUS_OBSERVER_PORT_EVENT_RECV_MIN_INTERVAL = __PREFIX
			+ ".STATUS_OBSERVER_PORT_EVENT_RECV_MIN_INTERVAL";

	// 画面なし
	/** コンポーネント状態検出の設定 */
	private static final String KEY_STATUS_OBSERVER_ATTACH_ENABLE = __PREFIX + ".STATUS_OBSERVER_ATTACH_ENABLE";

	/**
	 * コンストラクタ
	 * 
	 * @return シングルトン
	 */
	public static ToolsCommonPreferenceManager getInstance() {
		return __instance;
	}

	protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	IPreferenceStore store;

	public ToolsCommonPreferenceManager() {
		this.propertyChangeSupport = new PropertyChangeSupport(this);
		this.store = ToolsCommonPlugin.getDefault().getPreferenceStore();
	}

	/**
	 * デフォルトタイムアウト判定時間
	 */
	public static final int defaultTimeoutPeriod = 10000;

	/** 状態通知オブザーバ (デフォルト 有効) */
	public static final Boolean DEFAULT_STATUS_OBSERVER_HB_ENABLE = true;

	/** 状態通知オブザーバの H.B受信間隔 (デフォルト 1.0[sec]) */
	public static final Double DEFAULT_STATUS_OBSERVER_HB_INTERVAL = 1.0;
	/** 状態通知オブザーバのタイムアウト判定回数 (デフォルト 5回) */
	public static final Integer DEFAULT_STATUS_OBSERVER_HB_TRYCOUNT = 5;

	/** ポートイベント通知 送信間隔 (デフォルト 1.0[sec]) */
	public static final Double DEFAULT_STATUS_OBSERVER_PORT_EVENT_SEND_MIN_INTERVAL = 1.0;
	/** ポートイベント通知 受信間隔 (デフォルト 1.0[sec]) */
	public static final Double DEFAULT_STATUS_OBSERVER_PORT_EVENT_RECV_MIN_INTERVAL = 1.0;

	/**
	 * デフォルトタイムアウト判定時間を取得する
	 * 
	 * @param key
	 *            キー
	 * @return デフォルトポート
	 */
	public int getDefaultTimeout(String key) {
		// コンソールから起動された場合には、pluginは存在しない為、nullとなる。
		if (ToolsCommonPlugin.getDefault() == null) {
			return defaultTimeoutPeriod;
		}
		ToolsCommonPlugin.getDefault().getPreferenceStore().setDefault(key, -1);
		int result = ToolsCommonPlugin.getDefault().getPreferenceStore().getInt(key);
		if (result == -1) { // defaultvalue
			result = defaultTimeoutPeriod;
		}
		return result;
	}

	/**
	 * デフォルトタイムアウト判定時間を設定する
	 * 
	 * @param key      キー
	 * @param interval 間隔
	 */
	public void setDefaultTimeout(String key, int defaultTimeout) {
		int oldDefaultTimeout = getDefaultTimeout(key);
		ToolsCommonPlugin.getDefault().getPreferenceStore().setValue(key, defaultTimeout);
		propertyChangeSupport.firePropertyChange(key, oldDefaultTimeout, defaultTimeout);
	}

	// 状態通知オブザーバ有効/無効 (STATUS_OBSERVER_HB_ENABLE)

	/** STATUS_OBSERVER_HB_ENABLE の取得 */
	public final Boolean isSTATUS_OBSERVER_HB_ENABLE() {
		String key = KEY_STATUS_OBSERVER_HB_ENABLE;
		store.setDefault(key, DEFAULT_STATUS_OBSERVER_HB_ENABLE);
		return store.getBoolean(key);
	}

	/** STATUS_OBSERVER_HB_ENABLE の設定 */
	public void setSTATUS_OBSERVER_HB_ENABLE(Boolean value) {
		String key = KEY_STATUS_OBSERVER_HB_ENABLE;
		Boolean oldValue = isSTATUS_OBSERVER_HB_ENABLE();
		store.setValue(key, (value == null) ? false : value);
		Boolean newValue = isSTATUS_OBSERVER_HB_ENABLE();
		//
		propertyChangeSupport.firePropertyChange(key, oldValue, newValue);
	}

	/** STATUS_OBSERVER_HB_ENABLE の復元 */
	public void resetSTATUS_OBSERVER_HB_ENABLE() {
		setSTATUS_OBSERVER_HB_ENABLE(DEFAULT_STATUS_OBSERVER_HB_ENABLE);
	}

	// 状態通知オブザーバ受信間隔 (STATUS_OBSERVER_HB_INTERVAL)

	/** STATUS_OBSERVER_HB_INTERVAL の取得 */
	public final Double getSTATUS_OBSERVER_HB_INTERVAL() {
		String key = KEY_STATUS_OBSERVER_HB_INTERVAL;
		store.setDefault(key, DEFAULT_STATUS_OBSERVER_HB_INTERVAL);
		return store.getDouble(key);
	}

	/** STATUS_OBSERVER_HB_INTERVAL の設定 */
	public void setSTATUS_OBSERVER_HB_INTERVAL(Double value) {
		String key = KEY_STATUS_OBSERVER_HB_INTERVAL;
		Double oldValue = getSTATUS_OBSERVER_HB_INTERVAL();
		store.setValue(key, (value == null) ? DEFAULT_STATUS_OBSERVER_HB_INTERVAL : value);
		Double newValue = getSTATUS_OBSERVER_HB_INTERVAL();
		//
		propertyChangeSupport.firePropertyChange(key, oldValue, newValue);
	}

	/** STATUS_OBSERVER_HB_INTERVAL の復元 */
	public void resetSTATUS_OBSERVER_HB_INTERVAL() {
		setSTATUS_OBSERVER_HB_INTERVAL(DEFAULT_STATUS_OBSERVER_HB_INTERVAL);
	}

	// 状態通知オブザーバ受信回数 (STATUS_OBSERVER_HB_TRYCOUNT)

	/** STATUS_OBSERVER_HB_TRYCOUNT の取得 */
	public final Integer getSTATUS_OBSERVER_HB_TRYCOUNT() {
		String key = KEY_STATUS_OBSERVER_HB_TRYCOUNT;
		store.setDefault(key, DEFAULT_STATUS_OBSERVER_HB_TRYCOUNT);
		return store.getInt(key);
	}

	/** STATUS_OBSERVER_HB_TRYCOUNT の設定 */
	public void setSTATUS_OBSERVER_HB_TRYCOUNT(Integer value) {
		String key = KEY_STATUS_OBSERVER_HB_TRYCOUNT;
		Integer oldValue = getSTATUS_OBSERVER_HB_TRYCOUNT();
		store.setValue(key, (value == null) ? DEFAULT_STATUS_OBSERVER_HB_TRYCOUNT : value);
		Integer newValue = getSTATUS_OBSERVER_HB_TRYCOUNT();
		//
		propertyChangeSupport.firePropertyChange(key, oldValue, newValue);
	}

	/** STATUS_OBSERVER_HB_TRYCOUNT の復元 */
	public void resetSTATUS_OBSERVER_HB_TRYCOUNT() {
		setSTATUS_OBSERVER_HB_TRYCOUNT(DEFAULT_STATUS_OBSERVER_HB_TRYCOUNT);
	}

	// ポートイベント通知 送信間隔 (STATUS_OBSERVER_PORT_EVENT_SEND_MIN_INTERVAL)

	/** STATUS_OBSERVER_PORT_EVENT_SEND_MIN_INTERVAL の取得 */
	public final Double getSTATUS_OBSERVER_PORT_EVENT_SEND_MIN_INTERVAL() {
		String key = KEY_STATUS_OBSERVER_PORT_EVENT_SEND_MIN_INTERVAL;
		store.setDefault(key, DEFAULT_STATUS_OBSERVER_PORT_EVENT_SEND_MIN_INTERVAL);
		return store.getDouble(key);
	}

	/** STATUS_OBSERVER_PORT_EVENT_SEND_MIN_INTERVAL の設定 */
	public void setSTATUS_OBSERVER_PORT_EVENT_SEND_MIN_INTERVAL(Double value) {
		String key = KEY_STATUS_OBSERVER_PORT_EVENT_SEND_MIN_INTERVAL;
		Double oldValue = getSTATUS_OBSERVER_PORT_EVENT_SEND_MIN_INTERVAL();
		store.setValue(key, (value == null) ? DEFAULT_STATUS_OBSERVER_PORT_EVENT_SEND_MIN_INTERVAL : value);
		Double newValue = getSTATUS_OBSERVER_PORT_EVENT_SEND_MIN_INTERVAL();
		//
		propertyChangeSupport.firePropertyChange(key, oldValue, newValue);
	}

	/** STATUS_OBSERVER_PORT_EVENT_SEND_MIN_INTERVAL の復元 */
	public void resetSTATUS_OBSERVER_PORT_EVENT_SEND_MIN_INTERVAL() {
		setSTATUS_OBSERVER_PORT_EVENT_SEND_MIN_INTERVAL(DEFAULT_STATUS_OBSERVER_PORT_EVENT_SEND_MIN_INTERVAL);
	}

	// ポートイベント通知 受信間隔 (STATUS_OBSERVER_PORT_EVENT_RECV_MIN_INTERVAL)

	/** STATUS_OBSERVER_PORT_EVENT_RECV_MIN_INTERVAL の取得 */
	public final Double getSTATUS_OBSERVER_PORT_EVENT_RECV_MIN_INTERVAL() {
		String key = KEY_STATUS_OBSERVER_PORT_EVENT_RECV_MIN_INTERVAL;
		store.setDefault(key, DEFAULT_STATUS_OBSERVER_PORT_EVENT_RECV_MIN_INTERVAL);
		return store.getDouble(key);
	}

	/** STATUS_OBSERVER_PORT_EVENT_RECV_MIN_INTERVAL の設定 */
	public void setSTATUS_OBSERVER_PORT_EVENT_RECV_MIN_INTERVAL(Double value) {
		String key = KEY_STATUS_OBSERVER_PORT_EVENT_RECV_MIN_INTERVAL;
		Double oldValue = getSTATUS_OBSERVER_PORT_EVENT_RECV_MIN_INTERVAL();
		store.setValue(key, (value == null) ? DEFAULT_STATUS_OBSERVER_PORT_EVENT_RECV_MIN_INTERVAL : value);
		Double newValue = getSTATUS_OBSERVER_PORT_EVENT_RECV_MIN_INTERVAL();
		//
		propertyChangeSupport.firePropertyChange(key, oldValue, newValue);
	}

	/** STATUS_OBSERVER_PORT_EVENT_RECV_MIN_INTERVAL の復元 */
	public void resetSTATUS_OBSERVER_PORT_EVENT_RECV_MIN_INTERVAL() {
		setSTATUS_OBSERVER_PORT_EVENT_RECV_MIN_INTERVAL(DEFAULT_STATUS_OBSERVER_PORT_EVENT_RECV_MIN_INTERVAL);
	}

	/** STATUS_OBSERVER_ATTACH_ENABLE の取得 */
	public final Boolean isSTATUS_OBSERVER_ATTACH_ENABLE() {
		String key = KEY_STATUS_OBSERVER_ATTACH_ENABLE;
		store.setDefault(key, false);
		return store.getBoolean(key);
	}

	/** STATUS_OBSERVER_ATTACH_ENABLE の設定 */
	public void setSTATUS_OBSERVER_ATTACH_ENABLE(Boolean b) {
		String key = KEY_STATUS_OBSERVER_ATTACH_ENABLE;
		store.setValue(key, (b == null) ? false : b);
	}

	/** STATUS_OBSERVER_ATTACH_ENABLE の復元 */
	public void resetSTATUS_OBSERVER_ATTACH_ENABLE() {
		setSTATUS_OBSERVER_ATTACH_ENABLE(false);
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

}
