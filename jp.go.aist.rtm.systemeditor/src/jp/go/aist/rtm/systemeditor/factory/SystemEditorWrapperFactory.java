package jp.go.aist.rtm.systemeditor.factory;

import java.io.IOException;
import java.net.URLDecoder;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.openrtp.namespaces.rts.version02.RtsProfileExt;

import jp.go.aist.rtm.toolscommon.factory.MappingRuleFactory;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind;
import jp.go.aist.rtm.toolscommon.profiles.util.XmlHandler;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationManager;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.MappingRule;
import jp.go.aist.rtm.toolscommon.util.RtsProfileHandler;

/**
 * システムエディタで必要となるWrapperObjectのファクトリ
 * 現状はCORBA用のマッピングルールのみに対応
 *
 */
public class SystemEditorWrapperFactory {

	private static SystemEditorWrapperFactory __instance = null;

	private SynchronizationManager synchronizationManager;

	/**
	 * コンストラクタ
	 * <p>
	 * 他のマッピングルールを使用したファクトリを作成することができるようにコンストラクタを公開するが、基本的にはgetInstance()を利用してシングルトンを作成すること
	 * 
	 * @param mappingRules
	 */
	public SystemEditorWrapperFactory(MappingRule[] mappingRules) {
		synchronizationManager = new SynchronizationManager(mappingRules);
	}

	/**
	 * ファクトリのシングルトンを取得する
	 * 
	 * @return ファクトリのシングルトン
	 */
	public static SystemEditorWrapperFactory getInstance() {
		if (__instance == null) {
			__instance = new SystemEditorWrapperFactory(MappingRuleFactory
					.getMappingRule());
		}

		return __instance;
	}

	/**
	 * シングルトンをセットする。（基本的に使用してはならない。コマンドラインからの実行のために追加）
	 * 
	 */
	public static void setInstance(SystemEditorWrapperFactory instance) {
		__instance = instance;
	}

	/**
	 * XMLからドメインオブジェクトツリーを復元する
	 * <p>
	 * 内部では、EMFのオブジェクトをロードし、同期フレームワークのオブジェクトの復元を行う
	 * 
	 * @param strPath
	 *            ファイルのパス
	 * @param restore	IORからの復元を行うかを判断する
	 * @return ドメインオブジェクトルート
	 * @throws IOException
	 *             ファイルが読み込めない場合など
	 */
	//Restore方式変更により変更
//	public EObject loadContentFromResource(String strPath, RestoreOption restore)
//			throws Exception {
	@Deprecated
	public EObject loadContentFromResource(String strPath) throws Exception {
		RtsProfileHandler handler = new RtsProfileHandler();
		SystemDiagram diagram = handler.load(strPath, SystemDiagramKind.ONLINE_LITERAL);
//		if (restore.doQuick()) handler.populateCorbaBaseObject(diagram);

		return postLoad(handler, diagram);
	}

	public EObject postLoad(RtsProfileHandler handler, SystemDiagram diagram) {
		getSynchronizationManager().assignSynchonizationSupportToDiagram(diagram);
		Rehabilitation.rehabilitation(diagram);
		
		// 読み込み時に明示的に状態の同期を実行
		for (Object obj : diagram.getComponents()) {
			((Component)obj).getSynchronizationSupport().synchronizeLocal();
		}

		handler.restoreCompositeComponentPort(diagram);
		return diagram;
	}

	/**
	 * XMLにオブジェクトツリーを保存する
	 * <p>
	 * 内部では、EMFのオブジェクトをセーブする。 同期フレームワークのオブジェクトはセーブされないので、ロード時に復元を行う必要がある。
	 * 
	 * @param resource
	 *            リソース
	 * @param content
	 *            ドメインオブジェクトルート
	 * @throws IOException
	 *             ファイルに保存できない場合など
	 */
	@Deprecated
	public void saveContentsToResource(Resource resource, EObject content)
			throws Exception {
		RtsProfileHandler handler = new RtsProfileHandler();
		SystemDiagram diagram = (SystemDiagram) content;
		RtsProfileExt profile = handler.save(diagram);
		diagram.setProfile(profile);
		
		String targetFileName =	resource.getURI().devicePath();
		XmlHandler xmlHandler = new XmlHandler();
		xmlHandler.saveXmlRts(profile, URLDecoder.decode(targetFileName, "UTF-8"));
	}

	/**
	 * SynchronizationManagerを取得する
	 */
	public SynchronizationManager getSynchronizationManager() {
		return synchronizationManager;
	}
}
