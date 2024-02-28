/*
 * $Id: EditorActions.java,v 1.6 2009/12/08 19:52:50 gaudenz Exp $
 * Copyright (c) 2001-2009, JGraph Ltd
 * 
 * All rights reserved.
 * 
 * See LICENSE file for license details. If you are unable to locate
 * this file please contact info (at) jgraph (dot) com.
 */
package jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml;

import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.mxgraph.layout.mxClusterLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.util.mxGraphActions.DeleteAction;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxResources;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxGraphView;

import jp.go.aist.rtm.rtcbuilder.fsm.editor.SCXMLGraphEditor;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.SCXMLGraphEditor.EditorStatus;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.config.SCXMLConstraints.RestrictedState;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.fileimportexport.SCXMLEdge;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.fileimportexport.SCXMLImportExport;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.fileimportexport.SCXMLNode;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.eleditor.SCXMLEditorRoot;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.eleditor.SCXMLEditorRoot.Type;

/**
 * @author Administrator
 * 
 */
public class SCXMLEditorActions {

	/**
	 * 
	 * @param e
	 * @return Returns the graph for the given action event.
	 */
	public static final SCXMLGraphEditor getEditor(ActionEvent e) {
		if (e.getSource() instanceof Component) {
			Component component = (Component) e.getSource();

			while (component != null && !(component instanceof SCXMLGraphEditor)) {
				component = component.getParent();
			}

			return (SCXMLGraphEditor) component;
		}

		return null;
	}

	public static class AddAction extends AbstractAction {
		private Point pos;
		mxCell parent;

		// p must be a swimlane
		public AddAction(Point pt, mxCell p) {
			pos = pt;
			parent = p;
		}

		public void actionPerformed(ActionEvent e) {
			SCXMLGraphEditor editor = getEditor(e);
			mxGraph graph = editor.getGraphComponent().getGraph();
			SCXMLGraphComponent c = (SCXMLGraphComponent) editor.getGraphComponent();
			SCXMLNode value = (SCXMLNode) editor.getCurrentFileIO().buildNodeValue();

			if (!(e.getActionCommand().equals(mxResources.get("addNode")))) {
				RestrictedState restrictedState = null;
				for (RestrictedState tempState : editor.getRestrictedStatesConfig().getRestrictedState()) {
					if (tempState.getName().equals(e.getActionCommand())) {
						restrictedState = tempState;
					}
				}
				value.setRestricted(true, restrictedState);
			}

			pos = c.mouseCoordToGraphCoord(pos);
			// the state contains the absolute coordinate
			mxGraphView view = graph.getView();
			double scale = view.getScale();
			mxCellState parentState = view.getState(parent);
			double parentX = parentState.getX() / scale;
			double parentY = parentState.getY() / scale;
			mxCell p = (mxCell) graph.insertVertex(parent, value.getInternalID(), value, pos.x - parentX,
					pos.y - parentY, 75, 75, value.getStyle());
		}
	}

	public static class AddInitialAction extends AbstractAction {
		private Point pos;
		mxCell parent;

		// p must be a swimlane
		public AddInitialAction(Point pt, mxCell p) {
			pos = pt;
			parent = p;
		}

		public void actionPerformed(ActionEvent e) {
			SCXMLGraphEditor editor = getEditor(e);
			mxGraph graph = editor.getGraphComponent().getGraph();
			SCXMLGraphComponent c = (SCXMLGraphComponent) editor.getGraphComponent();
			SCXMLNode value = (SCXMLNode) editor.getCurrentFileIO().buildNodeValue();

			pos = c.mouseCoordToGraphCoord(pos);
			// the state contains the absolute coordinate
			mxGraphView view = graph.getView();
			double scale = view.getScale();
			mxCellState parentState = view.getState(parent);
			double parentX = parentState.getX() / scale;
			double parentY = parentState.getY() / scale;
			mxCell p = (mxCell) graph.insertVertex(parent, value.getInternalID(), value, pos.x - parentX,
					pos.y - parentY, 75, 75, value.getStyle());
			////////
			SCXMLNode n = (SCXMLNode) p.getValue();
			mxIGraphModel model = editor.getGraphComponent().getGraph().getModel();
			model.beginUpdate();
			try {
				SCXMLChangeHandler.addStateOfNodeInCurrentEdit(p, model);
				n.setInitial(true);
				graph.setCellStyle(n.getStyle(), p);
			} finally {
				model.endUpdate();
			}
		}
	}
	
	public static class AddFinalAction extends AbstractAction {
		private Point pos;
		mxCell parent;

		// p must be a swimlane
		public AddFinalAction(Point pt, mxCell p) {
			pos = pt;
			parent = p;
		}

		public void actionPerformed(ActionEvent e) {
			SCXMLGraphEditor editor = getEditor(e);
			mxGraph graph = editor.getGraphComponent().getGraph();
			SCXMLGraphComponent c = (SCXMLGraphComponent) editor.getGraphComponent();
			SCXMLNode value = (SCXMLNode) editor.getCurrentFileIO().buildNodeValue();

			pos = c.mouseCoordToGraphCoord(pos);
			// the state contains the absolute coordinate
			mxGraphView view = graph.getView();
			double scale = view.getScale();
			mxCellState parentState = view.getState(parent);
			double parentX = parentState.getX() / scale;
			double parentY = parentState.getY() / scale;
			mxCell p = (mxCell) graph.insertVertex(parent, value.getInternalID(), value, pos.x - parentX,
					pos.y - parentY, 75, 75, value.getStyle());
			////////
			SCXMLNode n = (SCXMLNode) p.getValue();
			mxIGraphModel model = editor.getGraphComponent().getGraph().getModel();
			model.beginUpdate();
			try {
				SCXMLChangeHandler.addStateOfNodeInCurrentEdit(p, model);
				n.setFinal(true);
				graph.setCellStyle(n.getStyle(), p);
			} finally {
				model.endUpdate();
			}
		}
	}
	
	public static class EditEdgeAction extends AbstractAction {
		private Point pos;
		private mxCell cell;

		public EditEdgeAction(mxCell c, Point pt) {
			cell = c;
			pos = pt;
		}

		public void actionPerformed(ActionEvent e) {
			assert (cell.isEdge());
			SCXMLGraphEditor editor = getEditor(e);
			JFrame frame = (JFrame) SwingUtilities.windowForComponent(editor);
			mxIGraphModel model = editor.getGraphComponent().getGraph().getModel();
			model.beginUpdate();
			try {
				SCXMLChangeHandler.addStateOfEdgeInCurrentEdit(cell, model);
				editor.openElementEditorFor(cell, SCXMLEditorRoot.Type.EDGE, pos);
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				model.endUpdate();
			}
		}
	}

	public static class AddCornerToEdgeAction extends AbstractAction {
		private Point pos, unscaledPos;
		private mxCell cell;
		private int index;

		public AddCornerToEdgeAction(mxCell c, Point pt, Point unscaledPt, int i) {
			this.cell = c;
			this.pos = pt;
			this.unscaledPos = unscaledPt;
			this.index = i;
		}

		public void actionPerformed(ActionEvent e) {
			assert (cell.isEdge());
			SCXMLGraphEditor editor = getEditor(e);
			mxGraphComponent gc = editor.getGraphComponent();
			mxGraphView gv = gc.getGraph().getView();
			mxCellState cs = gv.getState(cell);
			// List<mxPoint> pts = cs.getAbsolutePoints();
			mxGeometry cg = cell.getGeometry();

			if (cg.isRelative()) {
				mxCellState ps = gv.getState(cell.getParent());
				pos = ps.relativizePointToThisState(unscaledPos, gv.getScale(), gv.getTranslate());
			}

			List<mxPoint> ptsAlreadyThere = (cg.getPoints() == null) ? new ArrayList<mxPoint>()
					: new ArrayList<mxPoint>(cg.getPoints());
			if (index >= ptsAlreadyThere.size())
				ptsAlreadyThere.add(new mxPoint(pos.x, pos.y));
			else
				ptsAlreadyThere.add(index, new mxPoint(pos.x, pos.y));

			mxGraphModel model = (mxGraphModel) gc.getGraph().getModel();
			mxGeometry geometry = (mxGeometry) cg.clone();
			geometry.setPoints(ptsAlreadyThere);
			model.setGeometry(cell, geometry);

			// cg.setPoints(ptsAlreadyThere);
			// mxGraphModel model=(mxGraphModel) gc.getGraph().getModel();
			// model.execute(new mxGeometryChange(model, cell, cg));
		}
	}

	public static class RemoveCornerToEdgeAction extends AbstractAction {
		private int index;
		private mxCell cell;

		public RemoveCornerToEdgeAction(mxCell c, int i) {
			cell = c;
			index = i;
		}

		public void actionPerformed(ActionEvent e) {
			assert (cell.isEdge());
			SCXMLGraphEditor editor = getEditor(e);
			mxGraphComponent gc = editor.getGraphComponent();
			mxCellState cs = gc.getGraph().getView().getState(cell);
			// List<mxPoint> pts = cs.getAbsolutePoints();
			mxGeometry cg = cell.getGeometry();
			List<mxPoint> ptsAlreadyThere = new ArrayList<mxPoint>(cg.getPoints());

			ptsAlreadyThere.remove(index);

			mxGraphModel model = (mxGraphModel) gc.getGraph().getModel();
			mxGeometry geometry = (mxGeometry) cg.clone();
			geometry.setPoints(ptsAlreadyThere);
			model.setGeometry(cell, geometry);

			// mxGraphModel model=(mxGraphModel) gc.getGraph().getModel();
			// model.execute(new mxGeometryChange(model, cell, cg));
		}
	}

	public static class EditNodeAction extends AbstractAction {
		private Point pos;
		private mxCell cell;
		private mxCell rootOfGraph;

		public EditNodeAction(mxCell c, mxCell root, Point pt) {
			cell = c;
			rootOfGraph = root;
			pos = pt;
		}

		public void actionPerformed(ActionEvent e) {
			assert (cell.isVertex());
			SCXMLGraphEditor editor = getEditor(e);
			JFrame frame = (JFrame) SwingUtilities.windowForComponent(editor);
			mxIGraphModel model = editor.getGraphComponent().getGraph().getModel();
			model.beginUpdate();
			try {
				SCXMLChangeHandler.addStateOfNodeInCurrentEdit(cell, model);
				editor.openElementEditorFor(cell, Type.NODE, pos);
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				model.endUpdate();
			}
		}
	}

	public static class ToggleWithTargetAction extends AbstractAction {
		private mxCell cell;

		public ToggleWithTargetAction(mxCell c) {
			cell = c;
		}

		public void actionPerformed(ActionEvent e) {
			SCXMLGraphEditor editor = getEditor(e);
			mxGraph graph = editor.getGraphComponent().getGraph();
			assert (cell.isEdge());
			SCXMLEdge n = (SCXMLEdge) cell.getValue();

			mxIGraphModel model = editor.getGraphComponent().getGraph().getModel();
			model.beginUpdate();
			try {
				SCXMLChangeHandler.addStateOfEdgeInCurrentEdit(cell, model);
				n.setCycleWithTarget(!n.isCycleWithTarget());
				graph.setCellStyle(n.getStyle(cell), cell);
			} finally {
				model.endUpdate();
			}
		}
	}

	public static class ToggleIgnoreStoredLayout extends AbstractAction {
		public static boolean isSelected(SCXMLGraphEditor editor) {
			return editor.preferences
					.getBoolean(SCXMLGraphEditor.PREFERENCE_IGNORE_STORED_LAYOUT, true);
		}

		public void actionPerformed(ActionEvent e) {
			SCXMLGraphEditor editor = getEditor(e);
			boolean ignoreStoredLayout = isSelected(editor);
			editor.preferences.putBoolean(SCXMLGraphEditor.PREFERENCE_IGNORE_STORED_LAYOUT,
					!ignoreStoredLayout);
			editor.updateIgnoreStoredLayoutMenuState();
		}
	}

	public static class SetNodeAsCluster extends AbstractAction {
		private mxCell cell;

		public SetNodeAsCluster(mxCell c) {
			cell = c;
		}

		public void actionPerformed(ActionEvent e) {
			SCXMLGraphEditor editor = getEditor(e);
			mxGraph graph = editor.getGraphComponent().getGraph();
			assert (cell.isVertex());
			SCXMLNode n = (SCXMLNode) cell.getValue();

			mxIGraphModel model = editor.getGraphComponent().getGraph().getModel();
			model.beginUpdate();
			try {
				SCXMLChangeHandler.addStateOfNodeInCurrentEdit(cell, model);
				n.setCluster(!n.isClusterNode());
				graph.setCellStyle(n.getStyle(), cell);
			} finally {
				model.endUpdate();
			}
		}
	}

	public static class SetNodeAsRestricted extends AbstractAction {

		private mxCell cell;

		public SetNodeAsRestricted(mxCell c) {
			cell = c;
		}

		public void actionPerformed(ActionEvent e) {
			SCXMLGraphEditor editor = getEditor(e);
			mxGraph graph = editor.getGraphComponent().getGraph();
			assert (cell.isVertex());
			SCXMLNode n = (SCXMLNode) cell.getValue();

			mxIGraphModel model = editor.getGraphComponent().getGraph().getModel();
			model.beginUpdate();
			try {
				SCXMLChangeHandler.addStateOfNodeInCurrentEdit(cell, model);
				RestrictedState restrictedState = null;
				for (RestrictedState tempState : editor.getRestrictedStatesConfig().getRestrictedState()) {
					if (tempState.getName().equals(e.getActionCommand())) {
						restrictedState = tempState;
					}
				}
				n.setRestricted(!n.isRestricted(restrictedState), restrictedState);
				graph.setCellStyle(n.getStyle(), cell);
			} finally {
				model.endUpdate();
			}
		}
	}

	public static class SetNodeAsHistory extends AbstractAction {
		private mxCell cell;
		private boolean deep;

		public SetNodeAsHistory(mxCell c, boolean d) {
			cell = c;
			this.deep = d;
		}

		public void actionPerformed(ActionEvent e) {
			SCXMLGraphEditor editor = getEditor(e);
			mxGraph graph = editor.getGraphComponent().getGraph();
			assert (cell.isVertex());
			SCXMLNode n = (SCXMLNode) cell.getValue();

			mxIGraphModel model = editor.getGraphComponent().getGraph().getModel();
			model.beginUpdate();
			try {
				SCXMLChangeHandler.addStateOfNodeInCurrentEdit(cell, model);
				if (deep) {
					if (n.isDeepHistory())
						n.setAsHistory(null);
					else
						n.setAsHistory(SCXMLNode.HISTORYTYPE.DEEP);
				} else {
					if (n.isShallowHistory())
						n.setAsHistory(null);
					else
						n.setAsHistory(SCXMLNode.HISTORYTYPE.SHALLOW);
				}
				graph.setCellStyle(n.getStyle(), cell);
			} finally {
				model.endUpdate();
			}
		}
	}

	/**
	 *
	 */
	@SuppressWarnings("serial")
	public static class HistoryAction extends AbstractAction {
		/**
		 * 
		 */
		protected boolean undo;

		/**
		 * 
		 */
		public HistoryAction(boolean undo) {
			this.undo = undo;
			// setEnabled(shouldBeEnabled(undo));
		}

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e) {
			SCXMLGraphEditor editor = getEditor(e);

			if (editor != null) {
				Collection<Object> modifiedObjects;
				if (undo) {
					modifiedObjects = editor.getUndoManager().undo();
				} else {
					modifiedObjects = editor.getUndoManager().redo();
				}

				editor.updateUndoRedoActionState();
			}
		}

	}

	public static class NewSCXMLAction {
		public void openNewAction(SCXMLGraphEditor editor) {
				editor.setCurrentFile(null,new SCXMLImportExport());
				
				SCXMLGraphComponent gc = editor.getGraphComponent();
				SCXMLGraph graph = (SCXMLGraph) gc.getGraph();
				
				//Memory leak fix
				gc.clearSCXMLNodes();
				editor.clearEditorForCellAndType();
				graph.clearUndeletable();
				graph.getModel().clearCells();
				
				mxCell root = new mxCell();
				root.insert(new mxCell());
				graph.getModel().setRoot(root);
				graph.setDefaultParent(null);

				SCXMLNode value=(SCXMLNode)editor.getCurrentFileIO().buildNodeValue();
				((SCXMLImportExport)editor.getCurrentFileIO()).setRoot(value);
				value.setID(SCXMLNode.ROOTID);
				value.setName("Top");
				value.setNamespace("xmlns=\"http://www.w3.org/2005/07/scxml\"");
				value.setCluster(true);
				mxCell p = (mxCell) graph.insertVertex(null, value.getInternalID(), value, 0, 0, gc.getSize().width, gc.getSize().height, value.getStyle());
				p.setValue(value);
				graph.setDefaultParent(p);

				graph.setCellAsDeletable(p, false);
				editor.setModified(false);
				editor.getUndoManager().clear();
				editor.getUndoManager().resetUnmodifiedState();
				editor.updateUndoRedoActionState();
				editor.clearDisplayOutsourcedContentStatus();
				
				editor.closeAllEditors();
				editor.setDisplayOfOutsourcedContentSelected(false);
				
				editor.setStatus(EditorStatus.EDITING);
		}
	}

	/**
	 *
	 */
	@SuppressWarnings("serial")
	public static class DoLayoutAction extends AbstractAction {
		mxGraph graph;
		mxClusterLayout layout;
		mxCell parentToLayout;
		private int depth;

		public DoLayoutAction(mxGraph g, mxCell p, int depth) {
			graph = g;
			layout = new mxClusterLayout(g);
			parentToLayout = p;
			this.depth = depth;
		}

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e) {
			SCXMLGraphEditor editor = getEditor(e);
			EditorStatus oldStatus = editor.getStatus();
			editor.setStatus(EditorStatus.LAYOUT);
			editor.getUndoManager().setCollectionMode(true);
			layout.execute((parentToLayout == null) ? graph.getDefaultParent() : parentToLayout, depth);
			editor.getUndoManager().setCollectionMode(false);
			editor.setStatus(oldStatus);
		}
	}

	/**
	 *
	 */
	@SuppressWarnings("serial")
	public static class OpenAction extends AbstractAction {
		/**
		 * 
		 */
		protected String lastDir;
		protected File file;
		private boolean inNewWindow;

		public OpenAction(File file, boolean inNewWindow) {
			this.file = file;
			this.inNewWindow = inNewWindow;
		}

		public OpenAction(File file) {
			this(file, false);
		}

		public OpenAction() {
			this(null, false);
		}
		
		/**
		 * 
		 */
		public void doStartAction(SCXMLGraphEditor editor, boolean isReadOnly) {
			SCXMLGraphComponent gc = editor.getGraphComponent();
			SCXMLGraph graph = (SCXMLGraph) gc.getGraph();
			gc.clearSCXMLNodes();
			editor.clearEditorForCellAndType();
			graph.clearUndeletable();
			graph.getModel().clearCells();

			openInEditor(editor, isReadOnly);
		}
		
		public void doStartActionContents(String contents, SCXMLGraphEditor editor, boolean isReadOnly) {
			SCXMLGraphComponent gc = editor.getGraphComponent();
			SCXMLGraph graph = (SCXMLGraph) gc.getGraph();
			gc.clearSCXMLNodes();
			editor.clearEditorForCellAndType();
			graph.clearUndeletable();
			graph.getModel().clearCells();

			openInEditorContents(contents, editor, isReadOnly);
		}
		
		public void actionPerformed(ActionEvent e) {
			// Memory leak fix
			SCXMLGraphEditor editor = getEditor(e);
			SCXMLGraphComponent gc = editor.getGraphComponent();
			SCXMLGraph graph = (SCXMLGraph) gc.getGraph();
			gc.clearSCXMLNodes();
			editor.clearEditorForCellAndType();
			graph.clearUndeletable();
			graph.getModel().clearCells();

			if (inNewWindow) {
				Thread openingThread = new Thread(new Runnable() {
					@Override
					public void run() {
						openInEditor(SCXMLGraphEditor.startEditor(), false);
					}
				});
				openingThread.setName("threadFor" + file);
				openingThread.start();
			} else {
				openInEditor(getEditor(e), false);
			}
		}

		private void openInEditor(SCXMLGraphEditor editor, boolean isReadOnly) {
			if (editor != null) {
				editor.setStatus(EditorStatus.POPULATING);
				editor.closeAllEditors();
				openSelectedFile(editor, isReadOnly);
				editor.setDisplayOfOutsourcedContentSelected(false);
				editor.setStatus(EditorStatus.EDITING);
			}
		}

		private void openInEditorContents(String contents, SCXMLGraphEditor editor, boolean isReadOnly) {
			if (editor != null) {
				editor.setStatus(EditorStatus.POPULATING);
				editor.closeAllEditors();
				openSelectedContents(contents, editor, isReadOnly);
				editor.setDisplayOfOutsourcedContentSelected(false);
				editor.setStatus(EditorStatus.EDITING);
			}
		}
		
		private void openSelectedFile(SCXMLGraphEditor editor, boolean isReadOnly) {
			File selectedFile = file;
			SCXMLGraph graph = editor.getGraphComponent().getGraph();
			if ((graph != null) && (selectedFile != null)) {
				lastDir = selectedFile.getParent();
				try {
					editor.clearDisplayOutsourcedContentStatus();
					mxGraphComponent graphComponent = editor.getGraphComponent();
					SCXMLImportExport fie = new SCXMLImportExport();
					// run the selected importer
					fie.read(selectedFile.getAbsolutePath(), graphComponent, editor.getRestrictedStatesConfig());

					mxClusterLayout clusterLayout = new mxClusterLayout(graph);
					clusterLayout.execute(graph.getDefaultParent());

					editor.setModified(false);
					editor.setCurrentFile(selectedFile, fie);
					editor.getUndoManager().clear();
					editor.getUndoManager().resetUnmodifiedState();
					editor.updateUndoRedoActionState();
					
					graph.setReadOnly(isReadOnly);
					((SCXMLGraphComponent)editor.getGraphComponent()).setReadOnly(isReadOnly);

				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(editor.getGraphComponent(), ex.toString(), mxResources.get("error"),
							JOptionPane.ERROR_MESSAGE);
				}

			}
		}
		
		private void openSelectedContents(String contents, SCXMLGraphEditor editor, boolean isReadOnly) {
			File selectedFile = file;
			SCXMLGraph graph = editor.getGraphComponent().getGraph();
			if ((graph != null) && (contents != null)) {
				try {
					editor.clearDisplayOutsourcedContentStatus();
					mxGraphComponent graphComponent = editor.getGraphComponent();
					SCXMLImportExport io = new SCXMLImportExport();
					io.parse(contents, graphComponent, editor.getRestrictedStatesConfig());
					SCXMLImportExport fie = new SCXMLImportExport();

					// apply layout to each cluster from the leaves up:
					mxClusterLayout clusterLayout = new mxClusterLayout(graph);
					clusterLayout.execute(graph.getDefaultParent());

					editor.setModified(false);
					editor.setCurrentFile(selectedFile, fie);
					editor.getUndoManager().clear();
					editor.getUndoManager().resetUnmodifiedState();
					editor.updateUndoRedoActionState();

					graph.setReadOnly(isReadOnly);
					((SCXMLGraphComponent)editor.getGraphComponent()).setReadOnly(isReadOnly);
					
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(editor.getGraphComponent(), ex.toString(), mxResources.get("error"),
							JOptionPane.ERROR_MESSAGE);
				}

			}
		}
	}

	public static class EditSelectedCellAction extends AbstractAction {
		private mxCell cell;

		public EditSelectedCellAction(mxCell cell) {
			this.cell = cell;
		}

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e) {
			SCXMLGraphEditor editor = getEditor(e);
			SCXMLGraphComponent gc = editor.getGraphComponent();
			SCXMLGraph graph = gc.getGraph();

			if (cell == null) {
				cell = (mxCell) graph.getSelectionCell();

				if (cell != null && !graph.isCellEditable(cell)) {
					cell = null;
				}
			}
			if (cell != null) {
				Point screenCoord = MouseInfo.getPointerInfo().getLocation();
				Point mousePoint = SwingUtilities.convertPoint(gc, screenCoord, gc);
				if (cell.isEdge()) {
					new EditEdgeAction(cell, mousePoint).actionPerformed(e);
				} else if (cell.isVertex()) {
					mxIGraphModel model = graph.getModel();
					mxCell root = SCXMLImportExport.followUniqueDescendantLineTillSCXMLValueIsFound(model);
					new EditNodeAction(cell, root, mousePoint).actionPerformed(e);
				}
			}
			cell = null;
		}
	}

	public static class ToggleDisplayOutsourcedContentInNode extends AbstractAction {

		private mxCell node = null;
		private boolean refresh;

		public ToggleDisplayOutsourcedContentInNode(mxCell n) {
			this(n, false);
		}

		public ToggleDisplayOutsourcedContentInNode(mxCell n, boolean refresh) {
			this.node = n;
			this.refresh = refresh;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			SCXMLGraphEditor editor = getEditor(e);
			SCXMLGraph graph = editor.getGraphComponent().getGraph();
			try {
				editor.getUndoManager().setCollectionMode(true);
				if (refresh) {
					if (node.getChildCount() > 0) {
						editor.displayOutsourcedContentInNode(node, graph, false, false);
						editor.displayOutsourcedContentInNode(node, graph, true, true);
					}
				} else {
					if (node.getChildCount() > 0) {
						// disable
						editor.displayOutsourcedContentInNode(node, graph, false, false);
					} else {
						// enable
						editor.displayOutsourcedContentInNode(node, graph, true, false);
					}
				}
				// apply layout to each cluster from the leaves up:
				mxClusterLayout clusterLayout = new mxClusterLayout(graph);
				clusterLayout.execute(graph.getDefaultParent());
				editor.setDisplayOfOutsourcedContentSelected(false);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			editor.getUndoManager().setCollectionMode(false);
		}
	}

	public static class ToggleDisplayOutsourcedContent extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			SCXMLGraphEditor editor = getEditor(e);
			SCXMLGraph graph = editor.getGraphComponent().getGraph();
			mxIGraphModel model = graph.getModel();
			try {
				editor.getUndoManager().setCollectionMode(true);
				if (editor.isDisplayOfOutsourcedContentSelected()) {
					// disable
					editor.displayOutsourcedContent(graph, false, true);
				} else {
					// enable
					editor.displayOutsourcedContent(graph, true, true);
				}
				// apply layout to each cluster from the leaves up:
				mxClusterLayout clusterLayout = new mxClusterLayout(graph);
				clusterLayout.execute(graph.getDefaultParent());
				editor.setDisplayOfOutsourcedContentSelected(!editor.isDisplayOfOutsourcedContentSelected());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			editor.getUndoManager().setCollectionMode(false);
		}
	}

	public static class ZoomIN extends AbstractAction {
		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e) {
			SCXMLGraphEditor editor = getEditor(e);
			editor.getGraphComponent().zoomIn();
		}
	}

	public static class ZoomOUT extends AbstractAction {
		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e) {
			SCXMLGraphEditor editor = getEditor(e);
			editor.getGraphComponent().zoomOut();
		}
	}

	public static class SCXMLDelete extends DeleteAction {

		public SCXMLDelete() {
			super("delete");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			SCXMLGraphEditor editor = getEditor(e);
			SCXMLGraphComponent graphComponent = editor.getGraphComponent();
			SCXMLGraph graph = graphComponent.getGraph();
			Object[] cells = graph.getDeletableCells(graph.addAllEdges(graph.getSelectionCells()));
			boolean cannotDelete = false;
			if (cells != null && cells.length > 0) {
				for (Object cell : cells) {
					if (editor.isCellBeingEdited((mxCell) cell)) {
						cannotDelete = true;
						break;
					}
				}
			}
			if (cannotDelete) {
				JOptionPane.showMessageDialog(editor.getGraphComponent(),
						"Some elements are being edited. Please close all edit dialogs before deleting an element.\nSome may be indirectly deleted elements (e.g. edges connecting to a node being deleted)",
						mxResources.get("error"), JOptionPane.ERROR_MESSAGE);
			} else {
				super.actionPerformed(e);
			}
		}

	}
}
