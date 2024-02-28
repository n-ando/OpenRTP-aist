package jp.go.aist.rtm.toolscommon.model.component.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.CorbaStatusObserver;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl;

/**
 * オブザーバ操作を提供します
 */
public class CorbaObserverHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(CorbaObserverHandler.class);

	public static CorbaObserverHandler eINSTANCE = new CorbaObserverHandler();

	/** コンポーネントへ状態通知オブザーバを割り当てます */
	public CorbaStatusObserver attachStatusObserver(Component comp) {
		LOGGER.debug("attachStatusObserver: comp=<{}>", comp);
		if (!(comp instanceof CorbaComponentImpl)) {
			return null;
		}
		if (isCompositeMember(comp)) {
			return null;
		}
		CorbaComponentImpl corbaComp = (CorbaComponentImpl) comp;
		if (!corbaComp.supportedCorbaObserver()) {
			return null;
		}
		// 状態通知オブザーバ登録
		CorbaStatusObserver obs = ComponentFactory.eINSTANCE.createCorbaStatusObserver();
		obs.attachComponent(corbaComp);
		//
		CorbaObserverStore.eINSTANCE.addComponentReference(corbaComp);
		return obs;
	}

	/** コンポーネントから状態通知オブザーバの割当を解除します */
	public CorbaStatusObserver detachStatusObserver(Component comp) {
		if (!(comp instanceof CorbaComponentImpl)) {
			return null;
		}
		if (isCompositeMember(comp)) {
			return null;
		}
		CorbaComponentImpl corbaComp = (CorbaComponentImpl) comp;
		//
		CorbaObserverStore.eINSTANCE.removeComponentReference(corbaComp);
		// 状態通知オブザーバ解除
		CorbaStatusObserver obs = null;
		if (corbaComp.getStatusObserver() != null) {
			obs = corbaComp.getStatusObserver();
			obs.detachComponent();
		}
		return obs;
	}

	public boolean isCompositeMember(Component comp) {
		if (comp.eContainer() instanceof SystemDiagram) {
			SystemDiagram sd = (SystemDiagram) comp.eContainer();
			if (sd.getCompositeComponent() != null) {
				return true;
			}
		}
		return false;
	}

}
