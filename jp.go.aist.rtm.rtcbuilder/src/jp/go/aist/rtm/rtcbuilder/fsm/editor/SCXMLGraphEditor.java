package jp.go.aist.rtm.rtcbuilder.fsm.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.prefs.Preferences;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.xml.bind.JAXBContext;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.layout.mxEdgeLabelLayout;
import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.layout.mxPartitionLayout;
import com.mxgraph.layout.mxStackLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphOutline;
import com.mxgraph.swing.handler.mxKeyboardHandler;
import com.mxgraph.swing.handler.mxRubberband;
import com.mxgraph.swing.util.CellSelector;
import com.mxgraph.util.StringUtils;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.util.mxResources;
import com.mxgraph.util.mxUndoManager;
import com.mxgraph.util.mxUndoableEdit;
import com.mxgraph.util.mxUndoableEdit.mxUndoableChange;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxMultiplicity;

import jp.go.aist.rtm.rtcbuilder.fsm.EventParam;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.SCXMLNotifier;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.config.SCXMLConstraints;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.fileimportexport.SCXMLEdge;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.fileimportexport.SCXMLImportExport;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.fileimportexport.SCXMLNode;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.SCXMLEditorActions.NewSCXMLAction;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.SCXMLEditorActions.OpenAction;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.SCXMLEditorActions.ToggleIgnoreStoredLayout;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.SCXMLEditorMenuBar;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.SCXMLEditorPopupMenu;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.SCXMLGraph;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.SCXMLGraphComponent;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.SCXMLKeyboardHandler;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.eleditor.SCXMLEditorRoot.Type;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.eleditor.SCXMLStateEditor;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.eleditor.SCXMLTransitionEditor;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.utils.AbstractActionWrapper;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.utils.ListCellSelector;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.utils.Pair;
import jp.go.aist.rtm.rtcbuilder.nl.Messages;

public class SCXMLGraphEditor extends JPanel {
	public Preferences preferences = Preferences.userRoot();
	private ValidationWarningStatusPane validationStatus;
	
	private SCXMLNotifier observer = null;
	private boolean isReadOnly = false;
	private CellSelector cellHighlighter = null;
	public static final String PREFERENCE_IGNORE_STORED_LAYOUT = "IGNORE_STORED_LAYOUT";
	private String[] defaultTypeList;
	private List<EventParam> eventList = new ArrayList<EventParam>();

	public void setObserver(SCXMLNotifier target) {
		this.observer = target;
	}
	public void setReadOnly(boolean value) {
		this.isReadOnly = value;
	}
	public void setDataTypes(String[] defaultTypeList) {
		this.defaultTypeList = defaultTypeList;
	}
	public List<EventParam> getEventList() {
		return this.eventList;
	}
	
	public SCXMLEditorMenuBar menuBar;

	public enum EditorStatus {
		STARTUP, EDITING, LAYOUT, POPULATING
	};

	private EditorStatus status = EditorStatus.STARTUP;

	public void setStatus(EditorStatus status) {
		this.status = status;
	}

	public EditorStatus getStatus() {
		return status;
	}

	private static final long serialVersionUID = -6561623072112577140L;
	static {
		mxResources.add("jp/go/aist/rtm/rtcbuilder/fsm/editor/resources/editor");
	}

	/**
	 * the zoomed in view of the graph (just a small portion of the outline
	 * (typically))
	 */
	protected SCXMLGraphComponent graphComponent;

	/**
	 * a summary view of the entire graph
	 */
	protected mxGraphOutline graphOutline;

	protected mxUndoManager undoManager;
	protected String appTitle;
	protected JLabel statusBar;
	protected File currentFile;
	protected SCXMLImportExport currentFileIOMethod;
	protected Long lastModifiedDate;
	private static boolean doLayout;
	private static String inputFileName, outputFileName, outputFormat;

	/*
	 * Restricted states configuration
	 */
	private SCXMLConstraints restrictedStatesConfig;

	protected boolean modified = false;
	protected mxRubberband rubberband;
	protected mxKeyboardHandler keyboardHandler;
	
	protected mxIEventListener undoHandler = new mxIEventListener() {
		public void invoke(Object source, mxEventObject evt) {
			undoManager.undoableEditHappened((mxUndoableEdit) evt.getProperty("edit"));
			updateUndoRedoActionState();
			changeTracker.invoke(null, null);
		}
	};
	private Action undo;
	private Action redo;

	public void setUndoMenuAction(Action externalAction) {
		this.undo = externalAction;
		updateUndoRedoActionState();
	}

	public void setRedoMenuAction(Action externalAction) {
		this.redo = externalAction;
		updateUndoRedoActionState();
	}

	public void updateUndoRedoActionState() {
		if (redo != null)
			redo.setEnabled(undoManager.canRedo());
		if (undo != null)
			undo.setEnabled(undoManager.canUndo());
	}

	private JCheckBoxMenuItem ignoreStoredLayout;

	public void setIgnoreStoredLayoutMenu(JCheckBoxMenuItem menuItem) {
		this.ignoreStoredLayout = menuItem;
		updateIgnoreStoredLayoutMenuState();
	}

	public void updateIgnoreStoredLayoutMenuState() {
		if (ignoreStoredLayout != null)
			ignoreStoredLayout.setSelected(ToggleIgnoreStoredLayout.isSelected(this));
	}

	private HashMap<String, SCXMLGraph> file2graph = new HashMap<String, SCXMLGraph>();
	private HashMap<String, SCXMLImportExport> file2importer = new HashMap<String, SCXMLImportExport>();

	public void clearDisplayOutsourcedContentStatus() {
		file2graph.clear();
		file2importer.clear();
	}

	public SCXMLGraph attachOutsourcedContentToThisNode(mxCell ond, SCXMLGraph g, boolean display, boolean refresh)
			throws Exception {
		SCXMLGraph rootg = getGraphComponent().getGraph();
		SCXMLNode v = (SCXMLNode) ond.getValue();
		// get the outsourcing url (SRC field)
		String src = v.getOutsourcedLocation();
		// get the file name, the optional namespace and the optional node name
		// syntax handled: filename#namespace:nodename
		// or filename#nodename
		// or filename
		String namespace, node;
		int pos = src.indexOf('#', 0);
		if (pos >= 0) {
			int nmpos = src.indexOf(':', pos);
			if (nmpos >= 0) {
				namespace = src.substring(pos + 1, nmpos);
				node = src.substring(nmpos + 1);
			} else {
				namespace = null;
				node = src.substring(pos + 1);
			}
		} else {
			namespace = null;
			node = null;
		}
		if ((namespace != null) && (node == null))
			throw new Exception("node name not given but namespace given in: '" + src + "'");
		String SCXMLnodename = (node != null) ? (((namespace != null) ? namespace + ":" : "") + node) : v.getID();
		// normalize the file name to the system absolute path of that file

		File f = getThisFileInCurrentDirectory(src);

		String fileName = f.getAbsolutePath();
		while (!f.exists()) {
			JFileChooser fc = new JFileChooser(f.getParent());
			final String inputFileName = fileName;
			fc.setFileFilter(new FileFilter() {

				@Override
				public String getDescription() {
					return "Find '" + inputFileName + "' file.";
				}

				@Override
				public boolean accept(File f) {
					if (f.getName().equals(inputFileName) || f.isDirectory())
						return true;
					else
						return false;
				}
			});
			fc.setAcceptAllFileFilterUsed(false);
			int rc = fc.showDialog(this, mxResources.get("findFile") + " '" + fileName + "'");
			if (rc == JFileChooser.APPROVE_OPTION) {
				System.out.println("trying this file: '" + fc.getSelectedFile() + "'");
				f = fc.getSelectedFile();
			} else {
				throw new Exception("Aborted by the user.");
			}
		}
		fileName = f.getAbsolutePath();
		// check to see if the required file has already been read
		SCXMLImportExport ie = file2importer.get(fileName);
		SCXMLGraph ig = file2graph.get(fileName);
		if ((ig == null) || refresh) {
			// load the required graph
			assert (!file2importer.containsKey(fileName) || refresh);
			file2importer.put(fileName, ie = new SCXMLImportExport());
			// read the graph, this will throw an exception if something goes
			// wrong
			System.out.println("reading " + fileName);
			ie.readInGraph(ig = new SCXMLGraph(),
					fileName, preferences
							.getBoolean(PREFERENCE_IGNORE_STORED_LAYOUT, true),
					getRestrictedStatesConfig());
			ig.setEditor(this);
			file2graph.put(fileName, ig);
		}
		assert ((ig != null) && (ie != null));
		System.out.println("attaching node: '" + SCXMLnodename + "' from file '" + fileName + "'");
		// check that the requested node is there
		SCXMLNode SCXMLn = ie.getNodeFromSCXMLID(SCXMLnodename);
		if (SCXMLn == null)
			SCXMLn = ie.getRoot();
		if (SCXMLn == null) {
			JOptionPane.showMessageDialog(this, mxResources.get("nodeNotFound") + ": '" + SCXMLnodename + "'",
					mxResources.get("error"), JOptionPane.ERROR_MESSAGE);
			return null;
		} else {
			String internalID = SCXMLn.getInternalID();
			assert (!StringUtils.isEmptyString(internalID));
			// get the cell corresponding to that node
			mxCell oc = ie.getCellFromInternalID(internalID);
			assert (oc != null);
			// check whether ond has children, issue a warning if it has
			int cc = ond.getChildCount();
			if (cc > 0) {
				// remove all children of ond
				if (display)
					System.out.println("WARNING: the node: " + v + " has " + cc + " child(ren). Removing all of them.");

				// insure first that the cells are deletable
				Set<Object> descendants = new HashSet<Object>();
				rootg.getAllDescendants(ond, descendants);
				// don't change ond (ond is the original node in the graph (the
				// one to which we are adding the outsourced content))
				descendants.remove(ond);
				for (Object d : descendants)
					rootg.setCellAsDeletable(d, true);

				Object[] children = new Object[cc];
				for (int i = 0; i < cc; i++)
					children[i] = ond.getChildAt(i);
				rootg.removeCells(children);
			}
			if (display) {
				// attach copy of oc as only children of ond
				v.setCluster(true);
				rootg.setCellStyle(v.getStyle(), ond);
				HashMap<Object, Object> original2clone = new HashMap<Object, Object>();
				Object[] noc = g.cloneCells(new Object[] { oc }, false, original2clone);

				// loop through the mapping now created while cloning, if there
				// are
				// any cells that are outsourced add this clone to the list of
				// clones
				// for them in the graph ig.
				for (mxCell c : ig.getOutsourcedNodes()) {
					mxCell clone = (mxCell) original2clone.get(c);
					if (clone != null) {
						HashSet<mxCell> clones4ThisOriginal = ig.getOriginal2Clones().get(c);
						if (clones4ThisOriginal == null)
							ig.getOriginal2Clones().put(c, clones4ThisOriginal = new HashSet<mxCell>());
						assert (!clones4ThisOriginal.contains(clone));
						clones4ThisOriginal.add(clone);
					}
				}

				assert (noc.length == 1);
				mxCell ocCopy = (mxCell) noc[0];
				rootg.addCell(ocCopy, ond);
				// block all editing for ocCopy and all its children
				Set<Object> descendants = new HashSet<Object>();
				rootg.getAllDescendants(ocCopy, descendants);
				rootg.setConnectableEdges(false);
				for (Object d : descendants) {
					rootg.setCellAsDeletable(d, false);
					rootg.setCellAsEditable(d, false);
					rootg.setCellAsConnectable(d, false);
					// rootg.setCellAsMovable(d, false);
				}
			} else {
				v.setCluster(false);
				rootg.setCellStyle(v.getStyle(), ond);
			}
			return ig;
		}
	}

	public File getThisFileInCurrentDirectory(String src) {
		int pos = src.indexOf('#', 0);
		String file;
		if (pos >= 0)
			file = src.substring(0, pos);
		else
			file = src;
		// add the base directory information
		String wd = (getCurrentFile() != null) ? getCurrentFile().getParent() : System.getProperty("user.dir");
		return new File(wd + File.separator + file);
	}

	public void displayOutsourcedContentInNode(mxCell node, SCXMLGraph g, boolean display, boolean refresh)
			throws Exception {
		attachOutsourcedContentToThisNode(node, g, display, refresh);
	}

	HashSet<mxCell> alreadyDone = new HashSet<mxCell>();

	public void displayOutsourcedContent(SCXMLGraph g, boolean display, boolean isRoot) throws Exception {
		if (isRoot)
			alreadyDone.clear();
		// get the nodes that are outsourced
		HashSet<mxCell> onds = g.getOutsourcedNodes();
		for (mxCell ond : onds) {
			// ig contains the graph from which the content of ond (or all its
			// clones) is imported
			SCXMLGraph ig = null;
			// if isRoot is true, use the original node.
			// else: check if there are clones for this original node and use
			// those clones
			if (isRoot) {
				if (!alreadyDone.contains(ond)) {
					ig = attachOutsourcedContentToThisNode(ond, g, display, true);
					alreadyDone.add(ond);
				}
			} else {
				HashSet<mxCell> clones4Ond = g.getOriginal2Clones().get(ond);
				if (clones4Ond != null)
					for (mxCell clonedOnd : clones4Ond) {
						if (!alreadyDone.contains(clonedOnd)) {
							ig = attachOutsourcedContentToThisNode(clonedOnd, g, display, true);
							alreadyDone.add(clonedOnd);
						}
					}
			}
			// recursively call this function on the graph just created
			if (ig != null)
				displayOutsourcedContent(ig, display, false);
		}
	}

	private boolean doDisplayOfOutsourcedContent = false;
	private JCheckBoxMenuItem displayOutsourcedContentMenuItem;

	public void setDisplayOutsourcedContentMenuItem(JCheckBoxMenuItem mi) {
		displayOutsourcedContentMenuItem = mi;
	}

	public boolean isDisplayOfOutsourcedContentSelected() {
		return doDisplayOfOutsourcedContent;
	}

	public void setDisplayOfOutsourcedContentSelected(boolean b) {
		doDisplayOfOutsourcedContent = b;
		if (displayOutsourcedContentMenuItem != null)
			displayOutsourcedContentMenuItem.setSelected(isDisplayOfOutsourcedContentSelected());
	}

	/**
	 * 
	 */
	protected mxIEventListener changeTracker = new mxIEventListener() {
		public void invoke(Object source, mxEventObject evt) {
			if (undoManager.isUnmodifiedState())
				setModified(false);
			else
				setModified(true);
		}
	};

	/**
	 * 
	 */
	public SCXMLGraphEditor(String appTitle, SCXMLGraphComponent component, boolean isReadonly) {
		// Stores and updates the frame title
		this.appTitle = appTitle;
		this.isReadOnly = isReadonly;

		// Stores a reference to the graph and creates the command history
		graphComponent = component;
		final SCXMLGraph graph = graphComponent.getGraph();
		undoManager = new mxUndoManager(100);

		// Updates the modified flag if the graph model changes
		graph.getModel().addListener(mxEvent.CHANGE, changeTracker);

		// Adds the command history to the model and view
		graph.getModel().addListener(mxEvent.UNDO, undoHandler);
		graph.getView().addListener(mxEvent.UNDO, undoHandler);

		// Keeps the selection in sync with the command history
		mxIEventListener undoHandler = new mxIEventListener() {
			public void invoke(Object source, mxEventObject evt) {
				List<mxUndoableChange> changes = ((mxUndoableEdit) evt.getProperty("edit")).getChanges();
				graph.setSelectionCells(graph.getSelectionCellsForChanges(changes));
			}
		};

		undoManager.addListener(mxEvent.UNDO, undoHandler);
		undoManager.addListener(mxEvent.REDO, undoHandler);

		// Creates the status bar
		statusBar = createStatusBar();

		// Display some useful information about repaint events
		installRepaintListener();

		// Puts everything together
		setLayout(new BorderLayout());
		add(graphComponent, BorderLayout.CENTER);
		add(statusBar, BorderLayout.SOUTH);

		updateTitle();

		graph.setAutoSizeCells(true);
		graph.setEditor(this);
		graph.setMultigraph(true);
		graph.setAllowDanglingEdges(false);
		graph.setConnectableEdges(false);
		// the following 2 lines are required by the graph validation routines,
		// otherwise a null pointer exception is generated.
		mxMultiplicity[] m = {};
		graph.setMultiplicities(m);

		preferences = Preferences.userRoot();

		/*
		 * Parse restricted states configuration file
		 */
		restrictedStatesConfig = null;
		InputStream fileInputStream = null;
		try {
			File file = new File("restrictedStates.xml");
			fileInputStream = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			System.out.println(
					"Restriction configuration file not found. The application starts in normal mode without restriction handling.");
		}
		try {
			JAXBContext context = JAXBContext.newInstance(SCXMLConstraints.class);
			if (fileInputStream != null) {
				restrictedStatesConfig = (SCXMLConstraints) context.createUnmarshaller().unmarshal(fileInputStream);
			}
		} catch (Exception e) {
			System.out.println("Error while parsing restrictedStates.xml file: " + e.getMessage());
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (IOException e) {
				System.out.println("Error while closing restriction configuration file!" + e.getMessage());
			}
		}
	}

	/**
	 * 
	 */
	protected void installHandlers() {
		rubberband = new mxRubberband(graphComponent);
		if(isReadOnly==false) {
			keyboardHandler = new SCXMLKeyboardHandler(graphComponent);
		}
	}

	/**
	 * 
	 */
	protected JLabel createStatusBar() {
		JLabel statusBar = new JLabel(mxResources.get("ready"));
		statusBar.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));

		return statusBar;
	}

	/**
	 * 
	 */
	protected void installRepaintListener() {
		graphComponent.getGraph().addListener(mxEvent.REPAINT, new mxIEventListener() {
			public void invoke(Object source, mxEventObject evt) {
				String buffer = (graphComponent.getTripleBuffer() != null) ? "" : " (unbuffered)";
				mxRectangle dirty = (mxRectangle) evt.getProperty("region");

				if (dirty == null) {
					status("Repaint all" + buffer);
				} else {
					status("Repaint: x=" + (int) (dirty.getX()) + " y=" + (int) (dirty.getY()) + " w="
							+ (int) (dirty.getWidth()) + " h=" + (int) (dirty.getHeight()) + buffer);
				}
			}
		});
	}

	/**
	 * 
	 */
	protected void mouseWheelMoved(MouseWheelEvent e) {
		Point graphPoint;
		Point mousePoint = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), graphComponent);
		graphPoint = ((SCXMLGraphComponent) graphComponent).mouseCoordToGraphMouseCoord(mousePoint);
		if (e.getWheelRotation() < 0) {
			graphComponent.zoomIn(graphPoint);
		} else {
			graphComponent.zoomOut(graphPoint);
		}

		status(mxResources.get("scale") + ": " + (int) (100 * graphComponent.getGraph().getView().getScale()) + "%");
	}

	/**
	 * 
	 */
	protected void showOutlinePopupMenu(MouseEvent e) {
		Point pt = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), graphComponent);
		JCheckBoxMenuItem item = new JCheckBoxMenuItem(mxResources.get("magnifyPage"));
		item.setSelected(graphOutline.isFitPage());

		item.addActionListener(new ActionListener() {
			/**
			 * 
			 */
			public void actionPerformed(ActionEvent e) {
				graphOutline.setFitPage(!graphOutline.isFitPage());
				graphOutline.repaint();
			}
		});

		JCheckBoxMenuItem item2 = new JCheckBoxMenuItem(mxResources.get("showLabels"));
		item2.setSelected(graphOutline.isDrawLabels());

		item2.addActionListener(new ActionListener() {
			/**
			 * 
			 */
			public void actionPerformed(ActionEvent e) {
				graphOutline.setDrawLabels(!graphOutline.isDrawLabels());
				graphOutline.repaint();
			}
		});

		JCheckBoxMenuItem item3 = new JCheckBoxMenuItem(mxResources.get("buffering"));
		item3.setSelected(graphOutline.isTripleBuffered());

		item3.addActionListener(new ActionListener() {
			/**
			 * 
			 */
			public void actionPerformed(ActionEvent e) {
				graphOutline.setTripleBuffered(!graphOutline.isTripleBuffered());
				graphOutline.repaint();
			}
		});

		JPopupMenu menu = new JPopupMenu();
		menu.add(item);
		menu.add(item2);
		menu.add(item3);
		menu.show(graphComponent, pt.x, pt.y);

		e.consume();
	}

	/**
	 * 
	 */
	protected void showGraphPopupMenu(MouseEvent e) {
		Point screenCoord = e.getLocationOnScreen();
		Point mousePoint = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), graphComponent);
		Point graphPoint = ((SCXMLGraphComponent) graphComponent).mouseCoordToGraphMouseCoord(mousePoint);
		SCXMLEditorPopupMenu menu = new SCXMLEditorPopupMenu(this, mousePoint, graphPoint, screenCoord);
		menu.show(graphComponent, mousePoint.x, mousePoint.y);

		e.consume();
	}

	protected void showElementEditor(MouseEvent e) {
		Point mousePoint = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), graphComponent);
		Point graphPoint = ((SCXMLGraphComponent) graphComponent).mouseCoordToGraphMouseCoord(mousePoint);
		mxCell cell = (mxCell) graphComponent.getCellAt(graphPoint.x, graphPoint.y);
		if (cell != null) {
			if (cell.isVertex()) {
				try {
					openElementEditorFor(cell, Type.NODE, e.getLocationOnScreen());
				} catch (Exception e1) {
					System.out.println("Error while opening node editor.");
				}
			} else if (cell.isEdge()) {
				try {
					openElementEditorFor(cell, Type.EDGE, e.getLocationOnScreen());
				} catch (Exception e1) {
					System.out.println("Error while opening edge editor.");
				}
			}
		}
	}

	/**
	 * 
	 */
	protected void mouseLocationChanged(MouseEvent e) {
		status(e.getX() + ", " + e.getY());
	}

	public int getScroollingAmount(JScrollBar hs, MouseWheelEvent e) {
		return (int) (e.getWheelRotation() * hs.getModel().getExtent() * 0.3);
	}

	/**
	 * 
	 */
	protected void installListeners() {
		// Installs mouse wheel listener for zooming
		MouseWheelListener wheelTracker = new MouseWheelListener() {
			/**
			 * 
			 */
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (e.getSource() instanceof mxGraphOutline || e.isControlDown()) {
					SCXMLGraphEditor.this.mouseWheelMoved(e);
				} else {
					JScrollBar s = (e.isShiftDown()) ? graphComponent.getHorizontalScrollBar()
							: graphComponent.getVerticalScrollBar();
					if (s != null) {
						int d = getScroollingAmount(s, e);
						s.setValue(s.getValue() + d);
					}
				}
			}

		};

		// Handles mouse wheel events in the outline and graph component
		graphOutline.addMouseWheelListener(wheelTracker);
		graphComponent.addMouseWheelListener(wheelTracker);

		// Installs the popup menu in the outline
		graphOutline.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				// Handles context menu on the Mac where the trigger is on
				// mousepressed
				mouseReleased(e);
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showOutlinePopupMenu(e);
				}
			}
		});

		// Installs the popup menu in the graph component
		graphComponent.getGraphControl().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				// Handles context menu on the Mac where the trigger is on
				// mousepressed
				mouseReleased(e);
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && isReadOnly==false) {
					showGraphPopupMenu(e);
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && isReadOnly==false) {
					showElementEditor(e);
				}
			}
		});

		// Installs a mouse motion listener to display the mouse location
		graphComponent.getGraphControl().addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
				mouseLocationChanged(e);
			}
			public void mouseMoved(MouseEvent e) {
				mouseDragged(e);
			}
		});
	}

	/**
	 * 
	 */
	public void setCurrentFile(File file, SCXMLImportExport fie) {
		File oldValue = currentFile;
		currentFile = file;
		SCXMLImportExport oldFie = currentFileIOMethod;
		currentFileIOMethod = fie;

		firePropertyChange("currentFile", oldValue, file);
		firePropertyChange("currentFileIO", oldFie, fie);

		if (oldValue != file) {
			updateTitle();
		}
		setLastModifiedDate();
	}

	public void setLastModifiedDate() {
		File file = getCurrentFile();
		if (file != null)
			lastModifiedDate = file.lastModified();
		else
			lastModifiedDate = null;
	}

	public File getCurrentFile() {
		return currentFile;
	}

	public SCXMLImportExport getCurrentFileIO() {
		return currentFileIOMethod;
	}

	/**
	 * 
	 * @param modified
	 */
	public void setModified(boolean modified) {
		boolean oldValue = this.modified;
		this.modified = modified;

		firePropertyChange("modified", oldValue, modified);

		if (oldValue != modified) {
			updateTitle();
		}
	}

	public SCXMLGraphComponent getGraphComponent() {
		return graphComponent;
	}

	public mxUndoManager getUndoManager() {
		return undoManager;
	}

	/**
	 * 
	 * @param name
	 * @param action
	 * @return
	 */
	public Action bind(String name, final Action action) {
		return bind(name, action, null);
	}

	/**
	 * 
	 * @param name
	 * @param action
	 * @return
	 */
	@SuppressWarnings("serial")
	public AbstractActionWrapper bind(String name, final Action a, String iconUrl) {
		return new AbstractActionWrapper(getGraphComponent(), name, a,
				(iconUrl != null) ? new ImageIcon(SCXMLGraphEditor.class.getResource(iconUrl)) : null);
	}

	/**
	 * 
	 * @param msg
	 */
	public void status(String msg) {
		statusBar.setText(msg);
	}

	/**
	 * 
	 */
	public void updateTitle() {
		JFrame frame = (JFrame) SwingUtilities.windowForComponent(this);

		if (frame != null) {
			String title = (currentFile != null) ? currentFile.getAbsolutePath() : mxResources.get("newDiagram");

			if (modified) {
				title += "*";
			}

			frame.setTitle(title + " - " + appTitle);
		}
	}

	/**
	 * 
	 */
	public void exit() {
		JFrame frame = (JFrame) SwingUtilities.windowForComponent(this);

		if (frame != null) {
			frame.dispose();
		}
	}

	public class SCXMLEditorFrame extends JFrame implements WindowListener {
		private static final long serialVersionUID = -729543325068516592L;
		private SCXMLGraphEditor editor;

		public SCXMLEditorFrame(SCXMLGraphEditor e) {
			super();
			addWindowListener(this);
			editor = e;
		}

		@Override
		public void windowActivated(WindowEvent arg0) {
		}

		@Override
		public void windowClosed(WindowEvent arg0) {
		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			int errNo = validationStatus.getListModel().size();
			if(0<errNo) {
				int confirm = JOptionPane.showConfirmDialog(this,
								Messages.getString("IMC.FSM_SAVE_ERROR"),
								"SCXML Editor",
								JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (confirm!=JOptionPane.YES_OPTION) return;
			}
			//
			if(observer!=null) {
				SCXMLImportExport exporter = new SCXMLImportExport();
				try {
					observer.notifyContents(exporter.output(graphComponent), editor.getEventList());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			editor.exit();
			getGraphComponent().getValidator().kill();
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
		}
	}

	private class ValidationWarningStatusPane extends JPanel implements ListSelectionListener {
		private static final long serialVersionUID = -2024035745023293045L;
		
		private JList scxmlErrorsList;
		private final DefaultListModel listModel = new DefaultListModel();
		private ListCellSelector listSelectorHandler;

		
		public DefaultListModel getListModel() {
			return listModel;
		}

		public ValidationWarningStatusPane() {
			// Create the list and put it in a scroll pane.
			scxmlErrorsList = buildValidationWarningGUI();
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			add(new JLabel("Validation errors:"));
			add(new JScrollPane(scxmlErrorsList));

			listSelectorHandler = new ValidationCellSelector(scxmlErrorsList, graphComponent);
		}

		private JList buildValidationWarningGUI() {
			JList list = new JList(listModel);
			list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			list.addListSelectionListener(this);
			list.setVisibleRowCount(10);
			list.setCellRenderer((ListCellRenderer) new WarningRenderer());
			list.setForeground(Color.red);
			return list;
		}

		class ValidationCellSelector extends ListCellSelector {
			public ValidationCellSelector(JList list, SCXMLGraphComponent gc) {
				super(list, gc);
			}

			@Override
			public mxCell getCellFromListElement(int selectedIndex) {
				if (listModel.size() < selectedIndex)
					return null;
				Pair<Object, String> element = (Pair<Object, String>) listModel.get(selectedIndex);
				if (element != null) {
					Object cell = element.getFirst();
					if (cell instanceof mxCell)
						return (mxCell) cell;
					else
						return null;
				} else
					return null;
			}
		}

		class WarningRenderer extends JTextArea implements ListCellRenderer {
			public Component getListCellRendererComponent(JList list, Object value, // value
																					// to
																					// display
					int index, // cell index
					boolean isSelected, // is the cell selected
					boolean cellHasFocus) // the list and the cell have the
											// focus
			{
				String text = "";
				if (value != null) {
					text = ((Pair<Object, String>) value).getSecond();
				}
				setText(text);
				if (isSelected) {
					setBackground(list.getSelectionBackground());
					setForeground(list.getSelectionForeground());
				} else {
					setBackground(list.getBackground());
					setForeground(list.getForeground());
				}
				setEnabled(list.isEnabled());
				setFont(list.getFont());
				setOpaque(true);
				return this;
			}
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			listSelectorHandler.handleSelectEvent(e);
		}

		public void setWarnings(final HashMap<Object, String> warnings) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					ArrayList<Integer> indexToBeRemoved = new ArrayList<Integer>();
					for (int i = 0; i < listModel.size(); i++) {
						Pair<Object, String> el = (Pair<Object, String>) listModel.get(i);
						if (el != null) {
							String warningsForCell = warnings.get(el.getFirst());
							if (!StringUtils.isEmptyString(warningsForCell)) {
								warningsForCell = StringUtils.cleanupSpaces(warningsForCell);
								if (!warningsForCell.equals(el.getSecond()))
									listModel.set(i, new Pair<Object, String>(el.getFirst(), warningsForCell));
								warnings.remove(el.getFirst());
							} else
								indexToBeRemoved.add(i);
						} else {
							indexToBeRemoved.add(i);
						}
					}
					for (int i = indexToBeRemoved.size() - 1; i >= 0; i--)
						listModel.removeElementAt(indexToBeRemoved.get(i));
					for (Entry<Object, String> w : warnings.entrySet()) {
						String warning = StringUtils.cleanupSpaces(w.getValue());
						if (!StringUtils.isEmptyString(warning)) {
							System.out.println(warning);
							listModel.addElement(new Pair<Object, String>(w.getKey(), warning));
						}
					}
				}
			});
		}
	}

	public JFrame createFrame(SCXMLGraphEditor editor) throws IOException, SecurityException, IllegalArgumentException, ClassNotFoundException, NoSuchMethodException,
			InstantiationException, IllegalAccessException, InvocationTargetException {
		SCXMLEditorFrame frame = new SCXMLEditorFrame(this);
		// the contentPane of the JRootPane is a JPanel (that is the
		// FSMGraphEditor)
		// frame.setContentPane(this);

		// frame.getContentPane().add(this);
		// TODO: create menu bar

		// Creates the graph outline component
		graphOutline = new mxGraphOutline(graphComponent, 200, 200);

		JPanel inner = new JPanel();
		inner.setLayout(new BoxLayout(inner, BoxLayout.X_AXIS));
		validationStatus = new ValidationWarningStatusPane();
		inner.add(validationStatus);
		inner.add(graphOutline);

		JSplitPane outer = new JSplitPane(JSplitPane.VERTICAL_SPLIT, graphComponent, inner);
		outer.setDividerLocation(400);
		outer.setDividerSize(6);
		outer.setBorder(null);
		outer.setResizeWeight(1.0);

		// Puts everything together
		setLayout(new BorderLayout());
		add(outer, BorderLayout.CENTER);

		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		if(isReadOnly==false) {
			frame.setJMenuBar(menuBar = new SCXMLEditorMenuBar(editor));
		}
		frame.setSize(870, 640);

		// Updates the frame title
		// Installs rubberband selection and handling for some special
		// keystrokes such as F2, Control-C, -V, X, A etc.
		installHandlers();
		installListeners();
		updateTitle();

		// Installs automatic validation (use editor.validation = true
		// if you are using an mxEditor instance)
		graphComponent.getGraph().getModel().addListener(mxEvent.VALIDATION_DONE, new mxIEventListener() {
			public void invoke(Object sender, mxEventObject evt) {
				HashMap<Object, String> warnings = (HashMap<Object, String>) evt.getProperty("warnings");
				validationStatus.setWarnings(warnings);
			}
		});
		graphComponent.getGraph().getModel().addListener(mxEvent.VALIDATION_PRE_START, new mxIEventListener() {
			public void invoke(Object sender, mxEventObject evt) {
				graphComponent.clearSCXMLNodes();
			}
		});
		graphComponent.getGraph().getModel().addListener(mxEvent.CHANGE, new mxIEventListener() {
			public void invoke(Object sender, mxEventObject evt) {
				if (getStatus() == EditorStatus.EDITING)
					graphComponent.validateGraph();
			}
		});

		frame.getContentPane().add(this);

		return frame;
	}

	/**
	 * Creates and executes the specified layout.
	 * 
	 * @param key
	 *            Key to be used for getting the label from mxResources and also
	 *            to create the layout instance for the commercial graph editor
	 *            example.
	 * @return
	 */
	@SuppressWarnings("serial")
	public Action graphLayout(final String key) {
		final mxIGraphLayout layout = createLayout(key);

		if (layout != null) {
			return new AbstractAction(mxResources.get(key)) {
				public void actionPerformed(ActionEvent e) {
					if (layout != null) {
						Object cell = graphComponent.getGraph().getSelectionCell();

						if (cell == null || graphComponent.getGraph().getModel().getChildCount(cell) == 0) {
							cell = graphComponent.getGraph().getDefaultParent();
						}

						long t0 = System.currentTimeMillis();
						layout.execute(cell);
						status("Layout: " + (System.currentTimeMillis() - t0) + " ms");
					}
				}

			};
		} else {
			return new AbstractAction(mxResources.get(key)) {

				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(graphComponent, mxResources.get("noLayout"));
				}

			};
		}
	}

	/**
	 * Creates a layout instance for the given identifier.
	 */
	protected mxIGraphLayout createLayout(String ident) {
		mxIGraphLayout layout = null;

		if (ident != null) {
			mxGraph graph = graphComponent.getGraph();

			if (ident.equals("verticalHierarchical")) {
				layout = new mxHierarchicalLayout(graph);
			} else if (ident.equals("horizontalHierarchical")) {
				layout = new mxHierarchicalLayout(graph, JLabel.WEST);
			} else if (ident.equals("verticalTree")) {
				layout = new mxCompactTreeLayout(graph, false);
			} else if (ident.equals("horizontalTree")) {
				layout = new mxCompactTreeLayout(graph, true);
			} else if (ident.equals("parallelEdges")) {
				layout = new mxParallelEdgeLayout(graph);
			} else if (ident.equals("placeEdgeLabels")) {
				layout = new mxEdgeLabelLayout(graph);
			} else if (ident.equals("organicLayout")) {
				layout = new mxFastOrganicLayout(graph);
			}
			if (ident.equals("verticalPartition")) {
				layout = new mxPartitionLayout(graph, false) {
					/**
					 * Overrides the empty implementation to return the size of
					 * the graph control.
					 */
					public mxRectangle getContainerSize() {
						return graphComponent.getLayoutAreaSize();
					}
				};
			} else if (ident.equals("horizontalPartition")) {
				layout = new mxPartitionLayout(graph, true) {
					/**
					 * Overrides the empty implementation to return the size of
					 * the graph control.
					 */
					public mxRectangle getContainerSize() {
						return graphComponent.getLayoutAreaSize();
					}
				};
			} else if (ident.equals("verticalStack")) {
				layout = new mxStackLayout(graph, false) {
					/**
					 * Overrides the empty implementation to return the size of
					 * the graph control.
					 */
					public mxRectangle getContainerSize() {
						return graphComponent.getLayoutAreaSize();
					}
				};
			} else if (ident.equals("horizontalStack")) {
				layout = new mxStackLayout(graph, true) {
					/**
					 * Overrides the empty implementation to return the size of
					 * the graph control.
					 */
					public mxRectangle getContainerSize() {
						return graphComponent.getLayoutAreaSize();
					}
				};
			} else if (ident.equals("circleLayout")) {
				layout = new mxCircleLayout(graph);
			}
		}

		return layout;
	}

	public static SCXMLGraphEditor startEditor() {
		return startEditor(false, false);
	}

	public static SCXMLGraphEditor startEditor(boolean noGUI, boolean isReadOnly) {
		try {
			if (!noGUI)
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			mxConstants.SHADOW_COLOR = Color.LIGHT_GRAY;
			SCXMLGraphComponent gc = new SCXMLGraphComponent(new SCXMLGraph());
			SCXMLGraphEditor editor = new SCXMLGraphEditor("FSM Editor", gc, isReadOnly);
			if (!noGUI)
				editor.createFrame(editor).setVisible(true);
			else
				gc.getValidator().kill();
			editor.getGraphComponent().requestFocusInWindow();

			return editor;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static SCXMLGraphEditor openEditor(String targetFile, SCXMLNotifier target, String[] defaultTypeList, List<EventParam> eventList, boolean isReadOnly) throws Exception {
		SCXMLGraphEditor editor = startEditor(false, isReadOnly);
		editor.setObserver(target);
		editor.setReadOnly(isReadOnly);
		editor.setDataTypes(defaultTypeList);
		editor.getEventList().clear();
		editor.getEventList().addAll(eventList);
		if(targetFile==null || targetFile.length()==0) {
			NewSCXMLAction action = new NewSCXMLAction();
			action.openNewAction(editor);
		} else {
			File sourceFile = new File(targetFile);
			OpenAction action = new OpenAction(sourceFile);
			action.doStartAction(editor, isReadOnly);
		}
		return editor;
	}

	public static SCXMLGraphEditor openEditorWithContents(String contents, SCXMLNotifier target, boolean isReadOnly) throws Exception {
		SCXMLGraphEditor editor = startEditor(false, isReadOnly);
		editor.setObserver(target);
		OpenAction action = new OpenAction();
		action.doStartActionContents(contents, editor, isReadOnly);
		return editor;
	}
	
	private final HashMap<mxCell, HashMap<Type, JDialog>> editorForCellAndType = new HashMap<mxCell, HashMap<Type, JDialog>>();

	public void closeAllEditors() {
		for (HashMap<Type, JDialog> te : editorForCellAndType.values()) {
			for (JDialog e : te.values()) {
				e.dispose();
			}
		}
	}

	public boolean isCellBeingEdited(mxCell cell) {
		HashMap<Type, JDialog> editorsForCell = editorForCellAndType.get(cell);
		if (editorsForCell != null && !editorsForCell.isEmpty()) {
			for (JDialog e : editorsForCell.values())
				if (e != null)
					return true;
		}
		return false;
	}

	public JDialog getEditorForCellAndType(mxCell cell, Type type) {
		HashMap<Type, JDialog> editorsForCell = editorForCellAndType.get(cell);
		if (editorsForCell != null) {
			return editorsForCell.get(type);
		}
		return null;
	}

	public void setEditorForCellAndType(mxCell cell, Type type, JDialog editor) {
		if (type != null) {
			HashMap<Type, JDialog> editorsForCell = editorForCellAndType.get(cell);
			if (editorsForCell == null)
				editorForCellAndType.put(cell, editorsForCell = new HashMap<Type, JDialog>());
			editorsForCell.put(type, editor);
		}
	}

	public void clearEditorForCellAndType() {
		editorForCellAndType.clear();
	}

	public void openElementEditorFor(mxCell cell, Type type, Point pos) throws Exception {
		JDialog ee = getEditorForCellAndType(cell, type);
		if (ee != null) {
			ee.setVisible(true);
		} else {
			JFrame frame = (JFrame) SwingUtilities.windowForComponent(this);
			switch (type) {
			case EDGE:
				ee = new SCXMLTransitionEditor(frame, cell, this, pos);
				SCXMLEdge edge = (SCXMLEdge)cell.getValue();
				
				SCXMLNode sourceState = (SCXMLNode)cell.getSource().getValue();
				SCXMLNode targetState = (SCXMLNode)cell.getTarget().getValue();
				if(sourceState.isInitial() || targetState.isInitial()) {
					JOptionPane.showMessageDialog(getGraphComponent(),
							"The transition from the start node cannot be edited.",
							mxResources.get("error"),
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				EventParam source = new EventParam();
				source.setName(edge.getEvent());
				source.setCondition(edge.getCondition());
				source.setSource(edge.getSCXMLSource());
				source.setTarget(edge.getSCXMLTargets().get(0));
				
				EventParam targetParam = null;
				for(EventParam param : eventList) {
					if(param.checkSame(source)) {
						targetParam = param;
						break;
					}
				}
				if(targetParam == null ) {
					targetParam= new EventParam();
					targetParam.setSource(source.getSource());
					targetParam.setTarget(source.getTarget());
				}
				((SCXMLTransitionEditor)ee).setStatus(defaultTypeList, true, targetParam);
				((SCXMLTransitionEditor)ee).buildDialog();
				break;
			case NODE:
				SCXMLGraphComponent gc = getGraphComponent();
				SCXMLGraph graph = gc.getGraph();
				mxIGraphModel model = graph.getModel();
				mxCell root = SCXMLImportExport.followUniqueDescendantLineTillSCXMLValueIsFound(model);
				ee = new SCXMLStateEditor(frame, cell, root, this, pos);
				break;
			default:
				throw new Exception("Unknown element editor type requested: " + type);
			}
			setEditorForCellAndType(cell, type, ee);
		}
		int screenMaxX = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
		int screenMaxY = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
		int dialogMaxXCoordinateOnScreen = pos.x + ee.getWidth();
		int dialogMaxYCoordinateOnScreen = pos.y + ee.getHeight();
		int diff;
		if (dialogMaxXCoordinateOnScreen > screenMaxX) {
			diff = dialogMaxXCoordinateOnScreen - screenMaxX;
			pos.x = pos.x - diff;
		}
		if (dialogMaxYCoordinateOnScreen > screenMaxY) {
			diff = dialogMaxYCoordinateOnScreen - screenMaxY;
			pos.y = pos.y - diff;
		}
		ee.setLocation(pos);
	}

	public SCXMLConstraints getRestrictedStatesConfig() {
		return restrictedStatesConfig;
	}
	
	public void updateEditor(String target) {
		mxCell targetCell = null;
		mxIGraphModel model = getGraphComponent().getGraph().getModel();
		mxCell root = (mxCell)model.getRoot();
		int num = model.getChildCount(root);
		for(int index=0; index<num; index++) {
			mxCell child = (mxCell)model.getChildAt(root, index);
			targetCell = searchChildCell(child, target);
		}
		//
		if(targetCell!=null) {
			if(cellHighlighter==null) cellHighlighter = new CellSelector(getGraphComponent());
			cellHighlighter.unselectAll();
			cellHighlighter.selectCell(targetCell);
		}
	}
	
	private mxCell searchChildCell(mxCell parent, String target) {
		mxCell result = null;
		int num = parent.getChildCount();
		for(int index=0; index<num; index++) {
			mxCell child = (mxCell)parent.getChildAt(index);
			if(child.getValue()!=null && child.getValue() instanceof SCXMLNode) {
				SCXMLNode node = (SCXMLNode)child.getValue();
				if(node.getID().equals(target)) {
					return child;
				}
			}
			result = searchChildCell(child, target);
			if(result!=null) return result;
		}
		return null;
	}
}
