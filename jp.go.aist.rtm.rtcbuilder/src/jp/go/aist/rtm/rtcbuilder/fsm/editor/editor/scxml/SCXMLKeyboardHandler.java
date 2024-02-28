package jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.handler.mxKeyboardHandler;
import com.mxgraph.swing.util.mxGraphActions;

/**
 * @author Administrator
 * 
 */
public class SCXMLKeyboardHandler extends mxKeyboardHandler {

	/**
	 * 
	 * @param graphComponent
	 */
	public SCXMLKeyboardHandler(mxGraphComponent graphComponent) {
		super(graphComponent);
	}

	/**
	 * Return JTree's input map.
	 */
	protected InputMap getInputMap(int condition) {
		InputMap map = super.getInputMap(condition);

		if ((condition == JComponent.WHEN_FOCUSED) && map != null) {
			map.put(KeyStroke.getKeyStroke("DELETE"), "delete");
			map.put(KeyStroke.getKeyStroke("control Z"), "undo");
			map.put(KeyStroke.getKeyStroke("control Y"), "redo");
			map.put(KeyStroke.getKeyStroke("control shift V"), "selectVertices");
			map.put(KeyStroke.getKeyStroke("control shift E"), "selectEdges");
			map.put(KeyStroke.getKeyStroke("control A"), "selectAll");
			map.put(KeyStroke.getKeyStroke("ESCAPE"), "selectNone");
			map.put(KeyStroke.getKeyStroke("control PAGE_UP"), "zoomIN");
			map.put(KeyStroke.getKeyStroke("control PAGE_DOWN"), "zoomOUT");
		}

		return map;
	}

	/**
	 * Return the mapping between JTree's input map and JGraph's actions.
	 */
	protected ActionMap createActionMap() {
		ActionMap map = super.createActionMap();

		map.put("delete", new SCXMLEditorActions.SCXMLDelete());
		map.put("undo", new SCXMLEditorActions.HistoryAction(true));
		map.put("redo", new SCXMLEditorActions.HistoryAction(false));
		map.put("selectVertices", mxGraphActions.getSelectVerticesAction());
		map.put("selectEdges", mxGraphActions.getSelectEdgesAction());
		map.put("selectAll", mxGraphActions.getSelectAllAction());
		map.put("selectNone", mxGraphActions.getSelectNoneAction());
		map.put("zoomIN", new SCXMLEditorActions.ZoomIN());
		map.put("zoomOUT", new SCXMLEditorActions.ZoomOUT());
		map.put("edit", new SCXMLEditorActions.EditSelectedCellAction(null));
		return map;
	}

}
