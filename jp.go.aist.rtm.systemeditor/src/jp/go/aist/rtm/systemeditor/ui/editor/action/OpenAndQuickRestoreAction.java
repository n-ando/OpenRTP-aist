package jp.go.aist.rtm.systemeditor.ui.editor.action;

import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;

import org.eclipse.gef.ui.actions.EditorPartAction;
import org.eclipse.ui.IEditorPart;

/**
 * RTSプロファイルを読み込んで、IORに基づく復元、パスIDに基づく復元を行うアクション
 *
 */
//Restore方式変更により削除
//public class OpenAndQuickRestoreAction extends EditorPartAction {
//	public static final String ID = OpenAndQuickRestoreAction.class.getName();
//
//	public OpenAndQuickRestoreAction(IEditorPart editor) {
//		super(editor);
//	}
//	
//	@Override
//	protected void init() {
//		setId(ID);
//		setText("Open and Quick Restore...");
//		setToolTipText("Open and Quick Restore...");
//	}
//
//	@Override
//	protected boolean calculateEnabled() {
//		return true;
//	}
//	
//	@Override
//	public void run() {
//		((AbstractSystemDiagramEditor) getEditorPart()).open(RestoreOption.QUICK);
//	}
//
//}
