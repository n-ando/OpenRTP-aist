package jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml;

import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.TransferHandler;

import com.mxgraph.swing.util.mxGraphActions;
import com.mxgraph.util.mxResources;

import jp.go.aist.rtm.rtcbuilder.fsm.editor.SCXMLGraphEditor;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.SCXMLEditorActions.HistoryAction;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.SCXMLEditorActions.SCXMLDelete;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.SCXMLEditorActions.ToggleDisplayOutsourcedContent;

public class SCXMLEditorMenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4060203894740766714L;
	private static final String PREFERENCE_LASTFILE_KEY = "LASTFILE_";
	SCXMLGraphEditor editor;

	public String getLastOpenedDir() {
		String fileName = editor.preferences.get(PREFERENCE_LASTFILE_KEY, null);
		if (fileName != null) {
			File file = new File(fileName);
			return file.getParent();
		}
		return null;
	}

	@SuppressWarnings("serial")
	public SCXMLEditorMenuBar(final SCXMLGraphEditor ed) {
		this.editor = ed;
		JMenu menu = null;

		// Creates the edit menu
		menu = add(new JMenu(mxResources.get("edit")));
		menu.setMnemonic(KeyEvent.VK_E);

		AbstractAction internalAction = new HistoryAction(true);
		Action externalAction = editor.bind(mxResources.get("undo"), internalAction,
				"/jp/go/aist/rtm/rtcbuilder/fsm/editor/images/undo.gif");
		menu.add(externalAction);
		editor.setUndoMenuAction(externalAction);
		internalAction = new HistoryAction(false);
		externalAction = editor.bind(mxResources.get("redo"), internalAction,
				"/jp/go/aist/rtm/rtcbuilder/fsm/editor/images/redo.gif");
		menu.add(externalAction);
		editor.setRedoMenuAction(externalAction);

		menu.addSeparator();

		menu.add(editor.bind(mxResources.get("cut"), TransferHandler.getCutAction(),
				"/jp/go/aist/rtm/rtcbuilder/fsm/editor/images/cut.gif"));
		menu.add(editor.bind(mxResources.get("copy"), TransferHandler.getCopyAction(),
				"/jp/go/aist/rtm/rtcbuilder/fsm/editor/images/copy.gif"));
		menu.add(editor.bind(mxResources.get("paste"), TransferHandler.getPasteAction(),
				"/jp/go/aist/rtm/rtcbuilder/fsm/editor/images/paste.gif"));

		menu.addSeparator();

		menu.add(editor.bind(mxResources.get("delete"), new SCXMLDelete(),
				"/jp/go/aist/rtm/rtcbuilder/fsm/editor/images/delete.gif"));

		menu.addSeparator();

		menu.add(editor.bind(mxResources.get("selectAll"), mxGraphActions.getSelectAllAction()));
		menu.add(editor.bind(mxResources.get("selectNone"), mxGraphActions.getSelectNoneAction()));

		menu = add(new JMenu(mxResources.get("view")));
		menu.setMnemonic(KeyEvent.VK_V);
		internalAction = new ToggleDisplayOutsourcedContent();
		JCheckBoxMenuItem menuItem = new JCheckBoxMenuItem(
				externalAction = editor.bind(mxResources.get("toggleDisplayContentOutsourced"), internalAction));
		menu.add(menuItem);
		editor.setDisplayOutsourcedContentMenuItem(menuItem);
		editor.setDisplayOfOutsourcedContentSelected(editor.isDisplayOfOutsourcedContentSelected());

		add(Box.createHorizontalGlue());
	}
}
