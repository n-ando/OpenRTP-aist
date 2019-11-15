package jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml;

import java.awt.Point;
import java.io.File;
import java.util.List;

import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxICell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.util.mxResources;
import com.mxgraph.view.mxCellState;

import jp.go.aist.rtm.rtcbuilder.fsm.editor.SCXMLGraphEditor;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.config.SCXMLConstraints.RestrictedState;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.fileimportexport.OutSource;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.fileimportexport.SCXMLEdge;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.fileimportexport.SCXMLImportExport;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.fileimportexport.SCXMLNode;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.SCXMLEditorActions.AddAction;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.SCXMLEditorActions.AddCornerToEdgeAction;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.SCXMLEditorActions.AddInitialAction;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.SCXMLEditorActions.AddFinalAction;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.SCXMLEditorActions.DoLayoutAction;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.SCXMLEditorActions.EditEdgeAction;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.SCXMLEditorActions.EditNodeAction;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.SCXMLEditorActions.OpenAction;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.SCXMLEditorActions.RemoveCornerToEdgeAction;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.SCXMLEditorActions.SetNodeAsCluster;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.SCXMLEditorActions.SetNodeAsHistory;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.SCXMLEditorActions.SetNodeAsRestricted;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.SCXMLEditorActions.ToggleDisplayOutsourcedContentInNode;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.SCXMLEditorActions.ToggleWithTargetAction;

public class SCXMLEditorPopupMenu extends JPopupMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3132749140550242191L;

	public SCXMLEditorPopupMenu(SCXMLGraphEditor editor, Point mousePt, Point graphPt, Point screenCoord) {
		SCXMLGraphComponent gc = editor.getGraphComponent();
		mxCell c = (mxCell) gc.getCellAt(graphPt.x, graphPt.y);
		SCXMLGraph graph = gc.getGraph();
		mxIGraphModel model = graph.getModel();

		boolean inOutsourcedNode = false;
		// if the cell is not editable set it back to null so the menu doesn't
		// allow editing.
		inOutsourcedNode = (c != null) && !graph.isCellEditable(c);

		Point unscaledGraphPoint = gc.unscaledGraphCoordinates(graphPt);

		// edit node/transition (change text accordingly to type of element
		// under cursor)
		if (c != null) {
			if (c.isEdge()) {
				SCXMLNode source = (SCXMLNode) c.getSource().getValue();
				if (!inOutsourcedNode) {
					// for an edge, find out if the pointer is on a control
					// point, or not.
					// if on a control point find out if it's the beginning or
					// end of the endge.
					// -add control point if not on a control point
					// -remove control point if on one that is neither the
					// beginning nor the end.
					if (!((source != null) && (source.isHistoryNode()))) {
						add(editor.bind(mxResources.get("editEdge"), new EditEdgeAction(c, screenCoord)));
						addSeparator();
					}
					// if the edge is not a loop you can add/remove corners
					if (c.getSource() != c.getTarget()) {
						mxCellState cellState = graph.getView().getState(c);
						int index;
						int lastIndex = cellState.getAbsolutePointCount() - 1;
						if ((index = cellState.getIndexOfEdgePointAt(graphPt.x, graphPt.y, gc.getTolerance())) == -1) {
							int indexOfNewPoint = cellState.getIndexOfNewPoint(graphPt.x, graphPt.y, gc.getTolerance())
									- 1;
							if (indexOfNewPoint >= 0) {
								add(editor.bind(mxResources.get("addCorner"),
										new AddCornerToEdgeAction(c, unscaledGraphPoint, graphPt, indexOfNewPoint)));
							}
						} else if (index > 0 && index < lastIndex)
							add(editor.bind(mxResources.get("removeCorner"),
									new RemoveCornerToEdgeAction(c, index - 1)));
					} else {
						JCheckBoxMenuItem menuItem = new JCheckBoxMenuItem(
								editor.bind(mxResources.get("toggleWithTarget"), new ToggleWithTargetAction(c)));
						menuItem.setSelected(((SCXMLEdge) (c.getValue())).isCycleWithTarget());
						add(menuItem);
					}
				}
			} else if (c.isVertex()) {
				boolean nodeIsOutsourced = ((SCXMLNode) (c.getValue())).isOutsourcedNode();
				boolean nodeIsFake = ((SCXMLNode) (c.getValue())).getFake();
				mxICell parent = c.getParent();
				boolean isHistoryNode = ((SCXMLNode) (c.getValue())).isHistoryNode();
				boolean isParallelNode = ((SCXMLNode) (c.getValue())).isParallel();
				boolean isFinalNode = ((SCXMLNode) (c.getValue())).isFinal();
				boolean isClusterNode = ((SCXMLNode) (c.getValue())).isClusterNode();
				boolean existInitial = existInitial(c);
				
				if (!inOutsourcedNode) {
					// add node in case the cell under the pointer is a swimlane
					boolean addNodeEnabled = graph.isSwimlane(c) && (editor.getCurrentFileIO() != null);
					add(editor.bind(mxResources.get("addNode"), new AddAction(mousePt, c))).setEnabled(addNodeEnabled);
					add(editor.bind(mxResources.get("addInitialNode"), new AddInitialAction(mousePt, c))).setEnabled(addNodeEnabled && existInitial==false);
					add(editor.bind(mxResources.get("addFinalNode"), new AddFinalAction(mousePt, c))).setEnabled(addNodeEnabled);
					/*
					 * Add new, restricted node
					 */
					if (editor.getRestrictedStatesConfig() != null) {
						JMenu addRestrictedNodeMenu = new JMenu(mxResources.get("addRestrictedNode"));
						addRestrictedNodeMenu.setEnabled(addNodeEnabled);
						List<RestrictedState> restrictedStatesList = editor.getRestrictedStatesConfig()
								.getRestrictedState();
						if (restrictedStatesList != null) {
							for (RestrictedState restrictedState : restrictedStatesList) {
								JMenuItem addRestrictedStateMenuItem = new JMenuItem(
										editor.bind(restrictedState.getName(), new AddAction(mousePt, c)));
								addRestrictedStateMenuItem.setEnabled(addNodeEnabled);
								addRestrictedStateMenuItem.setToolTipText(restrictedState.getDocumentation());
								addRestrictedNodeMenu.add(addRestrictedStateMenuItem);
							}
						}
						add(addRestrictedNodeMenu);
					}

					addSeparator();
					mxCell root = SCXMLImportExport.followUniqueDescendantLineTillSCXMLValueIsFound(model);
					add(editor.bind(mxResources.get("editNode"), new EditNodeAction(c, root, screenCoord)));
					if (c != root) {
						if (!nodeIsFake) {
							if (!isHistoryNode)
								addSeparator();
							JCheckBoxMenuItem menuItem;
							if (!isHistoryNode) {
								menuItem = new JCheckBoxMenuItem(
										editor.bind(mxResources.get("setAsClusterNode"), new SetNodeAsCluster(c)));
								menuItem.setSelected(((SCXMLNode) (c.getValue())).isClusterNode());
								menuItem.setEnabled(!((SCXMLNode) (c.getValue())).isOutsourcedNode());
								add(menuItem);
								/*
								 * Toggle node as restricted
								 */
								if (editor.getRestrictedStatesConfig() != null) {
									JMenu toggleRestrictedMenu = new JMenu(mxResources.get("setAsRestrictedNode"));
									List<RestrictedState> restrictedStatesList = editor.getRestrictedStatesConfig()
											.getRestrictedState();
									SCXMLNode tempNode = (SCXMLNode) c.getValue();
									toggleRestrictedMenu.setEnabled(!tempNode.isFinal());
									if (restrictedStatesList != null) {
										for (RestrictedState restrictedState : restrictedStatesList) {
											menuItem = new JCheckBoxMenuItem(
													editor.bind(restrictedState.getName(), new SetNodeAsRestricted(c)));
											menuItem.setToolTipText(restrictedState.getDocumentation());
											menuItem.setSelected(tempNode.isRestricted(restrictedState));
											menuItem.setEnabled(!tempNode.isFinal());
											toggleRestrictedMenu.add(menuItem);
										}
									}
									add(toggleRestrictedMenu);
								}
							}
							if ((parent != root) && ((SCXMLNode) parent.getValue()).isClusterNode()) {
								if (!isParallelNode && !isClusterNode && !isFinalNode) {
									addSeparator();
									menuItem = new JCheckBoxMenuItem(editor.bind(
											mxResources.get("setAsDeepHistoryNode"), new SetNodeAsHistory(c, true)));
									menuItem.setSelected(((SCXMLNode) (c.getValue())).isDeepHistory());
									add(menuItem);
									menuItem = new JCheckBoxMenuItem(
											editor.bind(mxResources.get("setAsShallowHistoryNode"),
													new SetNodeAsHistory(c, false)));
									menuItem.setSelected(((SCXMLNode) (c.getValue())).isShallowHistory());
									add(menuItem);
								}
							}
						}
					}
				}
				if (nodeIsOutsourced) {
					JCheckBoxMenuItem menuItem = new JCheckBoxMenuItem(
							editor.bind(mxResources.get("toggleViewOutsourcedContent"),
									new ToggleDisplayOutsourcedContentInNode(c)));
					menuItem.setSelected(c.getChildCount() > 0);
					add(menuItem);
					if (c.getChildCount() > 0) {
						JMenuItem itemMenu = new JMenuItem(editor.bind(mxResources.get("refreshViewOutsourcedContent"),
								new ToggleDisplayOutsourcedContentInNode(c, true)));
						add(itemMenu);
					}

					SCXMLNode value = ((SCXMLNode) (c.getValue()));
					OutSource src = value.getSRC();
					File f = editor.getThisFileInCurrentDirectory(src.getLocation());
					Action itemAction = editor.bind(mxResources.get("openInNewWindow") + " " + f.getName(),
							new OpenAction(f, true));
					JMenuItem itemMenu = new JMenuItem(itemAction);
					add(itemMenu);
				}
				addSeparator();
				add(editor.bind(mxResources.get("doRecursiveLayout"), new DoLayoutAction(graph, c, -1)));
				add(editor.bind(mxResources.get("doSimpleLayout"), new DoLayoutAction(graph, c, 0)));
			}
		} else {
			add(editor.bind(mxResources.get("editNodeEdge"), null)).setEnabled(false);
		}
	}
	
	private boolean existInitial(mxCell n) {
		int nc = n.getChildCount();
		for (int i = 0; i < nc; i++) {
			mxCell c = (mxCell) n.getChildAt(i);
			if (c.isVertex()) {
				SCXMLNode value = (SCXMLNode) c.getValue();
				if (value.isInitial())
					return true;
			}
		}
		return false;
	}
}
