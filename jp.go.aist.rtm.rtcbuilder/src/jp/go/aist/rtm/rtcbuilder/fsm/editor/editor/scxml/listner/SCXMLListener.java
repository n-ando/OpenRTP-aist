package jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.listner;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.util.CellSelector;

import jp.go.aist.rtm.rtcbuilder.fsm.editor.SCXMLGraphEditor;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.SCXMLGraphComponent;

public class SCXMLListener {
	private CellSelector cellHighlighter;
	private SCXMLGraphComponent graphComponent;

	public SCXMLListener(SCXMLGraphEditor editor) {
		cellHighlighter = new CellSelector(editor.getGraphComponent());
	}
	
	public void showCell(mxCell cell) {
		cellHighlighter.selectCell(cell);
	}
}
