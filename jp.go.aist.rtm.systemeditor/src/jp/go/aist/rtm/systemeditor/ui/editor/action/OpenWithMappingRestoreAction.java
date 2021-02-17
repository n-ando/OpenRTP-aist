package jp.go.aist.rtm.systemeditor.ui.editor.action;

import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;

import org.eclipse.gef.ui.actions.EditorPartAction;

/**
 * RTSプロファイルを読み込み、コンポーネントの割当・生成方法を対話的に指定するアクション
 */
//Restore方式変更により削除
//public class OpenWithMappingRestoreAction extends EditorPartAction {
//
//	public static final String ID = OpenWithMappingRestoreAction.class
//			.getName();
//
//	public OpenWithMappingRestoreAction(AbstractSystemDiagramEditor editor) {
//		super(editor);
//	}
//
//	@Override
//	protected void init() {
//		setId(ID);
//		setText("Open with Mapping Restore...");
//		setToolTipText("Open with Mapping Restore...");
//	}
//
//	@Override
//	protected boolean calculateEnabled() {
//		return true;
//	}
//
//	@Override
//	public void run() {
//		((AbstractSystemDiagramEditor) getEditorPart())
//				.open(RestoreOption.MAPPING);
//	}
//
//}
