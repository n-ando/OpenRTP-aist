package jp.go.aist.rtm.systemeditor.ui.handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.go.aist.rtm.systemeditor.ui.editor.SystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ComponentEditPart;
import jp.go.aist.rtm.systemeditor.ui.util.RTMixin;
import jp.go.aist.rtm.toolscommon.manager.ToolsCommonPreferenceManager;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.util.CorbaObserverHandler;

/**
 * RTCへの状態通知オブザーバの割当/割当解除を実行するハンドラ
 */
public class AttachStatusObserverCommandHandler extends AbstractHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(AttachStatusObserverCommandHandler.class);

	/** [コマンドID] 状態通知オブザーバ割当 */
	public static final String ATTACH_STATUS_OBSERVER_ID = "rtse.command.observer.status.Attach";
	/** [コマンドID] 状態通知オブザーバ割当解除 */
	public static final String DETACH_STATUS_OBSERVER_ID = "rtse.command.observer.status.Detach";

	/** [コマンドID] 状態通知のデフォルトモードを設定 */
	public static final String ATTACH_GLOBAL_STATUS_OBSERVER_ID = "rtse.command.observer.status.Attach_GLOBAL";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Command command = event.getCommand();
		boolean oldValue = HandlerUtil.toggleCommandState(command);
		boolean doAttach = !oldValue;
		LOGGER.debug("execute: command=<{}> doAttach=<{}>", command.getId(), doAttach);

		if (ATTACH_GLOBAL_STATUS_OBSERVER_ID.equals(command.getId())) {
			IEditorPart editor = HandlerUtil.getActiveEditor(event);
			if (editor == null || !(editor instanceof SystemDiagramEditor)) {
				return null;
			}
			Object ret = null;
			ret = executeGlobalAttachSetting(doAttach);
			ret = executeGlobalAttachApply((SystemDiagramEditor) editor);
			return ret;
		}

		ISelection selection = HandlerUtil.getCurrentSelection(event);
		List<Component> comps = getSelectedCorbaComponentEditPart(selection);
		if (command == null || comps.isEmpty()) {
			return null;
		}
		return executeInternal(doAttach, comps.toArray(new Component[0]));
	}

	// 現在の選択から、CorbaComponentの一覧を抽出
	List<Component> getSelectedCorbaComponentEditPart(ISelection selection) {
		List<Component> ret = new ArrayList<>();
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			Iterator<?> iterator = structuredSelection.iterator();
			while (iterator.hasNext()) {
				Object o = iterator.next();
				if (!(o instanceof ComponentEditPart)) {
					continue;
				}
				ComponentEditPart part = (ComponentEditPart) o;
				Component comp = part.getModel();
				if (!(comp instanceof CorbaComponent)) {
					continue;
				}
				ret.add(comp);
			}
		}
		return ret;
	}

	// コマンド実行本体
	Object executeInternal(boolean doAttach, Component... comps) {
		for (Component comp : comps) {
			if (!(comp instanceof CorbaComponent)) {
				continue;
			}
			if (doAttach) {
				LOGGER.debug("executeInternal: status observer is attached. comp=<{}>", RTMixin.to_cid(comp));
				CorbaObserverHandler.eINSTANCE.attachStatusObserver(comp);
			} else {
				LOGGER.debug("executeInternal: status observer is detached. comp=<{}>", RTMixin.to_cid(comp));
				CorbaObserverHandler.eINSTANCE.detachStatusObserver(comp);
			}
		}
		return null;
	}

	// デフォルトの状態通知モードを設定
	Object executeGlobalAttachSetting(boolean doAttach) {
		LOGGER.debug("executeGlobalAttachSetting: doAttach=<{}>", doAttach);
		ToolsCommonPreferenceManager.getInstance().setSTATUS_OBSERVER_ATTACH_ENABLE(doAttach);
		return null;
	}

	// 現在のエディタにデフォルト状態通知モードを適用
	Object executeGlobalAttachApply(SystemDiagramEditor editor) {
		boolean doAttach = ToolsCommonPreferenceManager.getInstance().isSTATUS_OBSERVER_ATTACH_ENABLE();
		LOGGER.debug("executeGlobalAttachApply: doAttach=<{}> target=<{}>", doAttach, editor);
		if (editor.getSystemDiagram() == null) {
			return null;
		}
		return executeInternal(doAttach, editor.getSystemDiagram().getRegisteredComponents().toArray(new Component[0]));
	}

}
