package jp.go.aist.rtm.systemeditor.corba;

import static jp.go.aist.rtm.systemeditor.corba.CORBAHelper.CreateComponentParameter.KEY_IMPLEMENTATION_ID;
import static jp.go.aist.rtm.systemeditor.corba.CORBAHelper.CreateComponentParameter.KEY_INSTANCE_NAME;
import static jp.go.aist.rtm.systemeditor.corba.CORBAHelper.CreateComponentParameter.KEY_MANAGER_NAME;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;

import jp.go.aist.rtm.nameserviceview.corba.NameServerAccesser;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationSupport;
import jp.go.aist.rtm.toolscommon.util.RtsProfileHandler;

import org.omg.CORBA.ORB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CORBA操作に関するヘルパー
 */
public class CORBAHelper {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CORBAHelper.class);

	private static NS _ns = new NS();
	private static Factory _factory = new Factory();
	public static final int MANAGER_PORT = 2810;

	/**
	 * CORBA ネーミングサービスに関するヘルパーを取得します (Singleton)。
	 * 
	 * @return ネーミングサービスに関するヘルパー
	 */
	public static NS ns() {
		return _ns;
	}

	/**
	 * CORBA コンポーネント生成に関するヘルパーを取得します (Singleton)。
	 * 
	 * @return コンポーネント生成に関するヘルパー
	 */
	public static Factory factory() {
		return _factory;
	}

	/**
	 * ネーミングサービスに関するヘルパー
	 */
	public static class NS {

		private NS() {
		}

		/**
		 * パスから CORBA オブジェクトを解決します。
		 * 
		 * @param path
		 *            CORBAオブジェクトのパス
		 * @return CORBAオブジェクト
		 */
		public org.omg.CORBA.Object resolve(String path) {
			org.omg.CORBA.Object result = NameServerAccesser.getInstance()
					.getObjectFromPathId(path);
			return result;
		}

		/**
		 * パスのコンテキスト部に対するマネージャを検索します。
		 * 
		 * @param path
		 *            CORBAオブジェクトのパス
		 * @return マネージャ
		 */
		public RTM.Manager findManager(String path) {
			if (path == null || path.isEmpty()) {
				return null;
			}
			String cid = path;
			int index = path.lastIndexOf("/");
			if (index != -1) {
				cid = path.substring(0, index);
			}
			return NameServerAccesser.getInstance()
					.getManagerFromContextId(cid);
		}

	}

	/**
	 * コンポーネント生成に関するヘルパー
	 */
	public static class Factory {

		private Factory() {
		}

		/**
		 * CORBAコンポーネントのリモートオブジェクトが生存しているか判定します。
		 * 
		 * @param comp
		 *            CORBAコンポーネント
		 * @return リモートオブジェクトが生存している場合はtrue
		 */
		public boolean isAvailable(CorbaComponent comp) {
			return (comp.getCorbaObject() != null && SynchronizationSupport
					.ping(comp.getCorbaObject()));
		}

		/**
		 * CORBA コンポーネントに対するリモートオブジェクトを生成します。
		 * 
		 * @param comp
		 *            CORBA コンポーネント
		 * @param diagram
		 *            ダイアグラム
		 * @return リモートオブジェクト
		 * @throws CORBAException
		 */
		public RTC.RTObject createRTObject(CorbaComponent comp,
				SystemDiagram diagram) throws CORBAException {
			LOGGER.trace("createRTObject START comp=<{}> diagram=<{}>", comp,
					diagram);
			RTM.Manager manager = ns().findManager(comp.getPathId());
			if (manager == null) {
				throw new CORBAException(String.format(
						"Fail to find manager: path=<%s>", comp.getPathId()));
			}
			String param = buildCreateComponentParam(comp, null);
			LOGGER.info("createRTObject: cmd=<{}>", param);
			RTC.RTObject rtobj = manager.create_component(param);
			return rtobj;
		}

		/**
		 * CORBA コンポーネントに対するリモートオブジェクトを生成します。(マネージャ指定)
		 * 
		 * @param manager
		 *            マネージャ
		 * @param comp
		 *            CORBA コンポーネント
		 * @param diagram
		 *            ダイアグラム
		 * @return リモートオブジェクト
		 * @throws CORBAException
		 */
		public RTC.RTObject createRTObject(RTCManager manager,
				CorbaComponent comp, SystemDiagram diagram, String managerName)
				throws CORBAException {
			LOGGER.trace(
					"createRTObject START manager=<{}> comp=<{}> diagram=<{}>",
					manager, comp, diagram);
			if (manager == null) {
				throw new CORBAException(String.format(
						"No manager specified: path=<%s>", comp.getPathId()));
			}
			String param = buildCreateComponentParam(comp, managerName);
			LOGGER.info("createRTObject: cmd=<{}>", param);
			Component c = manager.createComponentR(param);
			if (c instanceof CorbaComponent) {
				return ((CorbaComponent) c).getCorbaObjectInterface();
			}
			return null;
		}

		/**
		 * CORBA 複合コンポーネントに対するリモートオブジェクトを生成します。
		 * 
		 * @param comp
		 *            CORBA 複合コンポーネント
		 * @param diagram
		 *            ダイアグラム
		 * @return リモートオブジェクト
		 * @throws CORBAException
		 */
		public RTC.RTObject createCompositeRTObject(CorbaComponent comp,
				SystemDiagram diagram) throws CORBAException {
			LOGGER.trace(
					"createCompositeRTObject START comp=<{}> diagram=<{}>",
					comp, diagram);
			RTM.Manager manager = ns().findManager(comp.getPathId());
			if (manager == null) {
				throw new CORBAException(String.format(
						"Fail to find manager: path=<%s>", comp.getPathId()));
			}
			String exportedPorts = findConfiguration("exported_ports", comp,
					diagram);
			String param = buildCreateCompositeComponentParam(comp,
					exportedPorts);
			LOGGER.info("createCompositeRTObject: cmd=<{}>", param);
			RTC.RTObject rtobj = manager.create_component(param);
			return rtobj;
		}

		/**
		 * CORBA 複合コンポーネントに対するリモートオブジェクトを生成します。
		 * 
		 * @param manager
		 *            マネージャ
		 * @param comp
		 *            CORBA 複合コンポーネント
		 * @param diagram
		 *            ダイアグラム
		 * @return リモートオブジェクト
		 * @throws CORBAException
		 */
		public RTC.RTObject createCompositeRTObject(RTCManager manager,
				CorbaComponent comp, SystemDiagram diagram)
				throws CORBAException {
			LOGGER.trace(
					"createCompositeRTObject START manager=<{}> comp=<{}> diagram=<{}>",
					manager, comp, diagram);
			if (manager == null) {
				throw new CORBAException(String.format(
						"No manager specified: path=<%s>", comp.getPathId()));
			}
			String exportedPorts = findConfiguration("exported_ports", comp,
					diagram);
			String param = buildCreateCompositeComponentParam(comp,
					exportedPorts);
			LOGGER.info("createCompositeRTObject: cmd=<{}>", param);
			Component c = manager.createComponentR(param);
			if (c instanceof CorbaComponent) {
				return ((CorbaComponent) c).getCorbaObjectInterface();
			}
			return null;
		}

		/**
		 * CORBA 複合コンポーネントのリモートオブジェクトへ、子コンポーネントを割り当てます。<br>
		 * あらかじめ複合コンポーネント自体のリモートオブジェクトを設定しておくこと
		 * 
		 * @param comp
		 *            CORBA 複合コンポーネント
		 * @throws CORBAException
		 */
		public void setCompositeMembers(CorbaComponent comp)
				throws CORBAException {
			LOGGER.trace("setCompositeMembers START comp=<{}>", comp);
			RTC.RTObject remote = comp.getCorbaObjectInterface();
			if (remote == null) {
				throw new CORBAException(String.format(
						"Remote object does not loaded: comp=<{}>", comp));
			}
			// 子RTCの CORBAオブジェクトリストを生成
			List<_SDOPackage.SDO> sdolist = new ArrayList<_SDOPackage.SDO>();
			for (Component o : comp.getComponents()) {
				CorbaComponent c = (CorbaComponent) o;
				RTC.RTObject rtobj = c.getCorbaObjectInterface();
				if (rtobj == null) {
					throw new CORBAException(
							String.format(
									"Remote object of child does not loaded: comp=<{}>",
									c));
				}
				sdolist.add(rtobj);
			}
			_SDOPackage.SDO[] sdos = sdolist.toArray(new _SDOPackage.SDO[0]);
			try {
				remote.get_owned_organizations()[0].set_members(sdos);
			} catch (Exception e) {
				remote.exit();
				throw new CORBAException(String.format(
						"Fail to set members: remote=<{}> sdos=<{}>", remote,
						sdos));
			}
		}

		/**
		 * このダイアグラムの RTSProfileから、コンポーネントの ConfigurationSet 設定値を検索します。
		 * 
		 * @param key
		 *            パラメータ名
		 * @param comp
		 *            EMFコンポーネント(CORBA)
		 * @param diagram
		 *            ダイアグラム
		 * @return 設定値、パラメータが存在しなかった場合はブランク("")
		 */
		public String findConfiguration(String key, CorbaComponent comp,
				SystemDiagram diagram) {
			org.openrtp.namespaces.rts.version02.Component orig = RtsProfileHandler
					.findComponent(comp, diagram.getProfile().getComponents());
			String activeId = orig.getActiveConfigurationSet();
			for (org.openrtp.namespaces.rts.version02.ConfigurationSet cs : orig
					.getConfigurationSets()) {
				if (!cs.getId().equals(activeId)) {
					continue;
				}
				for (org.openrtp.namespaces.rts.version02.ConfigurationData cd : cs
						.getConfigurationData()) {
					if (cd.getName().equals(key)) {
						return cd.getData();
					}
				}
			}
			return "";
		}

		/**
		 * コンポーネント生成のパラメータを生成します。<br>
		 * 次の情報が設定されていること
		 * <ul>
		 * <li>[必須] implementation_id : [プロパティ] コンポーネントの型</li>
		 * <li>[必須] instance_name : [プロパティ] コンポーネントのインスタンス名</li>
		 * </ul>
		 * 
		 * @param comp
		 *            CORBA コンポーネント
		 * @return コンポーネント生成パラメータ (失敗時はnull)
		 */
		public String buildCreateComponentParam(CorbaComponent comp, String managerName) {
			String implementationId = comp.getProperty(KEY_IMPLEMENTATION_ID);
			String instanceName = comp.getProperty(KEY_INSTANCE_NAME);
			if (implementationId == null || instanceName == null) {
				return null;
			}
			String manager = comp.getProperty(KEY_MANAGER_NAME);
			//
			CreateComponentParameter param = new CreateComponentParameter(
					implementationId);
			param.setInstanceName(instanceName);
			if(managerName!=null) {
				param.setManagerName(managerName);
			} else {
				if (manager != null) {
					param.setManagerName(manager);
				}
			}
			return param.buildCommand();
		}

		/**
		 * 複合コンポーネント生成のパラメータを生成します。<br>
		 * 次の情報が設定されていること
		 * <ul>
		 * <li>[必須] compositeType : [属性] 複合コンポーネントタイプ</li>
		 * <li>[必須] instance_name : [プロパティ] コンポーネントのインスタンス名</li>
		 * <li>[任意] exportedPorts : [引数] 公開ポート指定</li>
		 * </ul>
		 * 
		 * @param comp
		 *            CORBA 複合コンポーネント
		 * @param exportedPorts
		 *            公開ポート指定
		 * @return コンポーネント生成パラメータ (失敗時はnull)
		 */
		public String buildCreateCompositeComponentParam(CorbaComponent comp,
				String exportedPorts) {
			String compositeType = comp.getCompositeTypeL();
			String instanceName = comp.getProperty(KEY_INSTANCE_NAME);
			if (compositeType == null || instanceName == null) {
				return null;
			}
			String manager = comp.getProperty(KEY_MANAGER_NAME);
			//
			CreateComponentParameter param = new CreateComponentParameter(
					compositeType + "Composite");
			param.setInstanceName(instanceName);
			if (exportedPorts != null) {
				param.setExportedPorts(exportedPorts);
			}
			if (manager != null) {
				param.setManagerName(manager);
			}
			return param.buildCommand();
		}

		/**
		 * 複合コンポーネント生成のパラメータを生成します。
		 * 
		 * @param compositeType
		 *            複合コンポーネントタイプ
		 * @param instanceName
		 *            コンポーネントのインスタンス名
		 * @param exportedPorts
		 *            公開ポート指定 [任意]
		 * @return コンポーネント生成パラメータ (失敗時はnull)
		 */
		public String buildCreateCompositeComponentParam(String compositeType,
				String instanceName, String exportedPorts) {
			if (compositeType == null || instanceName == null) {
				return null;
			}
			//
			CreateComponentParameter param = new CreateComponentParameter(
					compositeType + "Composite");
			param.setInstanceName(instanceName);
			if (exportedPorts != null) {
				param.setExportedPorts(exportedPorts);
			}
			return param.buildCommand();
		}

	}

	/**
	 * ORBオブジェクトを生成、管理するヘルパー
	 */
	public static class ORBUtil {

	    private static ORB orb = null;

	    /**
	     * ORBオブジェクトを取得する
	     * 
	     * @return ORBオブジェクト
	     */
	    public static ORB getOrb() {
	        return getOrb(null);
	    }

	    /**
	     * 管理しているORBオブジェクトをクリアする
	     * 
	     */
	    public static void clearOrb() {
	        orb = null;        
	    }

	    /**
	     * ORBオブジェクトを取得する
	     *
	     * @param args 
	     *   アプリケーションの main メソッドのコマンド行引数
	     * 
	     * @return ORBオブジェクト
	     */
	    public static ORB getOrb(String[] args) {
	        return getOrb(args, null);
	    }
	    
	    /**
	     * 指定された引数に基づいてORBオブジェクトを生成後、取得する
	     * <p>
	     * すでにORBオブジェクトが生成済みの場合は、それが取得される
	     * 
	     * @param args 
	     *   ORBオブジェクト生成時の引数
	     * @return ORBオブジェクト
	     */
	    public static ORB getOrb(String[] args, Properties prop) {
	        
	        if (orb == null) {
	            orb = ORB.init(args, prop);
	            try {
	                if (orb instanceof com.sun.corba.se.spi.orb.ORB) {
	                    java.util.logging.Logger logger = ((com.sun.corba.se.spi.orb.ORB) orb).getLogger("");
	                    logger.setLevel(Level.SEVERE); // log 
	                }
	            } catch (Exception e) {
	                e.printStackTrace(); // system error

	            } catch (NoClassDefFoundError e) {
	                e.printStackTrace(); // system error
	            }
	        }
	        
	        return orb;
	    }
	}
	
	/**
	 * コンポーネントの生成コマンドのパラメータを表します。
	 */
	public static class CreateComponentParameter {

		public static final String KEY_IMPLEMENTATION_ID = "implementation_id";
		public static final String KEY_INSTANCE_NAME = "instance_name";
		public static final String KEY_EXPORTED_PORTS = "exported_ports";
		public static final String KEY_MANAGER_NAME = "manager_name";
		public static final String KEY_LANGUAGE = "language";
		public static final String KEY_VENDOR = "vendor";
		public static final String KEY_CATEGORY = "category";
		public static final String KEY_VERSION = "version";

		private String implementation_id;
		private String instance_name;
		private String exported_ports;
		private String manager_name;
		private String language;
		private Map<String, String> paramMap = new LinkedHashMap<>();

		public CreateComponentParameter(String implementation_id) {
			this.implementation_id = implementation_id;
		}

		/**
		 * コンポーネント生成のコマンド列を構築します。
		 */
		public String buildCommand() {
			StringBuffer ret = new StringBuffer();
			ret.append(this.implementation_id);
			//
			List<String> buf = new ArrayList<>();
			if (getInstanceName() != null) {
				buf.add(KEY_INSTANCE_NAME + "=" + getInstanceName());
			}
			if (getExportedPorts() != null) {
				buf.add(KEY_EXPORTED_PORTS + "=" + getExportedPorts());
			}
			if (getManagerName() != null) {
				buf.add(KEY_MANAGER_NAME + "=" + getManagerName());
			}
			if (getLanguage() != null) {
				buf.add(KEY_LANGUAGE + "=" + getLanguage());
			}
			for (String n : getParamNames()) {
				buf.add(n + "=" + getParam(n));
			}
			//
			if (!buf.isEmpty()) {
				ret.append("?");
				boolean first = true;
				for (String p : buf) {
					if (!first) {
						ret.append("&");
					}
					ret.append(p);
					first = false;
				}
			}
			return ret.toString();
		}

		/**
		 * インスタンス名を取得します。
		 * 
		 * @return
		 */
		public String getInstanceName() {
			return this.instance_name;
		}

		/**
		 * インスタンス名を設定します。
		 * 
		 * @param instance_name
		 */
		public void setInstanceName(String instance_name) {
			this.instance_name = instance_name;
		}

		/**
		 * 複合コンポーネントの公開ポート情報を取得します。
		 * 
		 * @return
		 */
		public String getExportedPorts() {
			return this.exported_ports;
		}

		/**
		 * 複合コンポーネントの公開ポート情報を設定します。
		 * 
		 * @param exported_ports
		 */
		public void setExportedPorts(String exported_ports) {
			this.exported_ports = exported_ports;
		}

		/**
		 * マネージャ名(プロセスグループ)を取得します。
		 * 
		 * @return
		 */
		public String getManagerName() {
			return this.manager_name;
		}

		/**
		 * マネージャ名(プロセスグループ)を設定します。
		 * 
		 * @param manager_name
		 */
		public void setManagerName(String manager_name) {
			this.manager_name = manager_name;
		}

		/**
		 * 言語を取得します。
		 * 
		 * @return
		 */
		public String getLanguage() {
			return this.language;
		}

		/**
		 * 言語を設定します。
		 * 
		 * @param language
		 */
		public void setLanguage(String language) {
			this.language = language;
		}

		/**
		 * 任意パラメータ群を取得します。<br>
		 * key1=value1&key2=value2... 形式
		 * 
		 * @return
		 */
		public String getParams() {
			StringBuffer ret = new StringBuffer();
			for (String name : getParamNames()) {
				String value = getParam(name);
				value = (value == null) ? "" : value;
				if (ret.length() > 0) {
					ret.append("&");
				}
				ret.append(String.format("%s=%s", name, value));
			}
			return ret.toString();
		}

		/**
		 * 任意パラメータ群を設定します。<br>
		 * key1=value1&key2=value2... 形式
		 * 
		 * @param params
		 */
		public void setParams(String params) {
			String[] pp = (params == null) ? new String[0] : params.split("&");
			for (String p : pp) {
				String[] nv = p.split("=");
				if (nv.length < 2) {
					continue;
				}
				setParam(nv[0], nv[1]);
			}
		}

		/**
		 * 名前と値を指定してパラメータを設定します。
		 * 
		 * @param name
		 * @param value
		 */
		public void setParam(String name, String value) {
			if (value == null) {
				return;
			}
			if (KEY_IMPLEMENTATION_ID.equals(name)) {
				this.implementation_id = value;
			} else if (KEY_INSTANCE_NAME.equals(name)) {
				setInstanceName(value);
			} else if (KEY_EXPORTED_PORTS.equals(name)) {
				setExportedPorts(value);
			} else if (KEY_MANAGER_NAME.equals(name)) {
				setManagerName(value);
			} else if (KEY_LANGUAGE.equals(name)) {
				setLanguage(value);
			} else {
				this.paramMap.put(name, value);
			}
		}

		/**
		 * 名前を指定してパラメータを取得します。
		 * 
		 * @param name
		 * @return
		 */
		public String getParam(String name) {
			if (KEY_IMPLEMENTATION_ID.equals(name)) {
				return this.implementation_id;
			} else if (KEY_INSTANCE_NAME.equals(name)) {
				return getInstanceName();
			} else if (KEY_EXPORTED_PORTS.equals(name)) {
				return getExportedPorts();
			} else if (KEY_MANAGER_NAME.equals(name)) {
				return getManagerName();
			} else if (KEY_LANGUAGE.equals(name)) {
				return getLanguage();
			} else {
				return this.paramMap.get(name);
			}
		}

		/**
		 * 任意パラメータの名前一覧を取得します。
		 * 
		 * @return
		 */
		public Set<String> getParamNames() {
			return Collections.unmodifiableSet(this.paramMap.keySet());
		}

		@Override
		public String toString() {
			return getClass().getSimpleName() + "<implementation_id="
					+ this.implementation_id + ", instance_name="
					+ this.instance_name + ", exported_ports="
					+ this.exported_ports + ", manager_name="
					+ this.manager_name + ", language=" + this.language + ", "
					+ this.paramMap + ">";
		}

	}

	/**
	 * CORBA 操作に関する例外
	 */
	public static class CORBAException extends Exception {

		private static final long serialVersionUID = 1L;

		public CORBAException(String msg) {
			super(msg);
		}

		public CORBAException(Throwable cause) {
			super(cause);
		}

		public CORBAException(String msg, Throwable cause) {
			super(msg, cause);
		}

	}

}
