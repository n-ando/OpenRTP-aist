package jp.go.aist.rtm.systemeditor.ui.editor.action;

import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;

import org.eclipse.gef.ui.actions.EditorPartAction;

/**
 * RTSプロファイルを読み込んで、システムダイアグラムエディタにロードするアクション
 *
 */
public class OpenAction extends EditorPartAction {
	public static final String ID = OpenAction.class.getName();

	public OpenAction(AbstractSystemDiagramEditor editor) {
		super(editor);
	}

	@Override
	protected void init() {
		setId(ID);
		setText("Load System");
		setToolTipText("Load System");
	}

	@Override
	protected boolean calculateEnabled() {
		return true;
	}

	@Override
	public void run() {
		((AbstractSystemDiagramEditor) getEditorPart()).open();
	}
}
