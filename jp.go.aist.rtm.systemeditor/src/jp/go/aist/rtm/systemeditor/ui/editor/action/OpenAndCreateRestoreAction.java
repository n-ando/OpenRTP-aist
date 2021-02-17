package jp.go.aist.rtm.systemeditor.ui.editor.action;

import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;

import org.eclipse.gef.ui.actions.EditorPartAction;

/**
 * RTSプロファイルを読み込んで、コンポーネント生成まで行うアクション
 */
//Restore方式変更により削除
//public class OpenAndCreateRestoreAction extends EditorPartAction {
//
//	public static final String ID = OpenAndCreateRestoreAction.class.getName();
//
//	public OpenAndCreateRestoreAction(AbstractSystemDiagramEditor editor) {
//		super(editor);
//	}
//
//	@Override
//	protected void init() {
//		setId(ID);
//		setText("Open and Create Restore...");
//		setToolTipText("Open and Create Restore...");
//	}
//
//	@Override
//	protected boolean calculateEnabled() {
//		return true;
//	}
//
//	@Override
//	public void run() {
//		((AbstractSystemDiagramEditor) getEditorPart()).open(RestoreOption.CREATE);
//	}
//
//}
