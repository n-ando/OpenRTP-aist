package jp.go.aist.rtm.systemeditor.ui.editor;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IMenuManager;

/**
 * システムダイアグラムのContextMenuProviderクラス
 */
public class SystemDiagramContextMenuProvider extends AbstractSystemDiagramContextMenuProvider {

	public SystemDiagramContextMenuProvider(EditPartViewer viewer, ActionRegistry actionRegistry) {
		super(viewer, actionRegistry);
	}

	public void buildContextMenu(IMenuManager menuManager) {

		super.buildContextMenu(menuManager);

		//Restore方式変更により削除
//		appendAction(menuManager, OpenAndRestoreAction.ID, "save");
//		appendAction(menuManager, OpenAndQuickRestoreAction.ID, "save");
//		appendAction(menuManager, OpenAndCreateRestoreAction.ID, "save");
//		appendAction(menuManager, OpenWithMappingRestoreAction.ID, "save");
	}

}
