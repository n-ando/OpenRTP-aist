package jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.eleditor;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.swing.util.CellSelector;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxResources;
import com.mxgraph.util.mxUndoableEdit;
import com.mxgraph.util.mxUndoableEdit.mxUndoableChange;

import jp.go.aist.rtm.rtcbuilder.fsm.EventParam;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.SCXMLGraphEditor;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.fileimportexport.SCXMLNode;
	
public abstract class SCXMLEditorRoot extends JDialog implements ActionListener, WindowListener {
	private static final long serialVersionUID = -3257633395118679718L;
	
	public static final String undoAction = "Undo";
	public static final String redoAction = "Redo";
	public final CloseAction closeAction;

	protected HashMap<Object, Action> actions = new HashMap<Object, Action>();

	protected SCXMLGraphEditor editor;

	private mxCell cell = null;

	protected JButton idButton;
	protected JButton okButton, cancelButton;
	private CellSelector cellSelector;

	public static enum Type {
		EDGE, NODE
	};
	
	public SCXMLEditorRoot(JFrame parent, SCXMLGraphEditor e, mxCell cell) {
		super(parent);
		this.cell = cell;
		closeAction = new CloseAction();
		editor = e;

		cellSelector = new CellSelector(editor.getGraphComponent());

		idButton = new JButton(mxResources.get("showCell"));
		idButton.setActionCommand("id");
		idButton.addActionListener(this);
		idButton.setEnabled(true);
		
		okButton = new JButton("OK");
		okButton.setActionCommand("ok");
		okButton.addActionListener(this);
		okButton.setEnabled(true);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("cancel");
		cancelButton.addActionListener(this);
		cancelButton.setEnabled(true);

		Panel pnlButtonInner = new Panel();
		pnlButtonInner.setLayout(new GridLayout(1,3));
		pnlButtonInner.add(okButton);
		pnlButtonInner.add(new JLabel());
		pnlButtonInner.add(cancelButton);
		
		Panel pnlButton = new Panel();
		GridBagLayout layout = new GridBagLayout();		
		pnlButton.setLayout(layout);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1.0d;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 0, 3, 0);
		layout.setConstraints(idButton, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1.0d;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 0, 0);
		layout.setConstraints(pnlButtonInner, gbc);
		
		pnlButton.add(idButton);
		pnlButton.add(pnlButtonInner);
		
		addWindowListener(this);

		getContentPane().add(pnlButton, BorderLayout.SOUTH);

		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		rootPane.registerKeyboardAction(new ActionListener(){
		    public void actionPerformed(ActionEvent e) {
				closeAction.actionPerformed(null);
		    }
		  }, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
		getRootPane().setDefaultButton(okButton);
	}
	
	public class CloseAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			editor.setEditorForCellAndType(cell, getTypeForEditorClass(), null);
			cellSelector.unselectAll();
			dispose();
		}
	}

	public Type getTypeForEditorClass() {
		String cn = this.getClass().getName();
		if (cn.equals(SCXMLTransitionEditor.class.getName()))
			return Type.EDGE;
		else if (cn.equals(SCXMLStateEditor.class.getName()))
			return Type.NODE;
		return null;
	}

	public Action getActionByName(String name) {
		return actions.get(name);
	}
	
	protected void addUIParts(JPanel panel, JComponent comp, GridBagLayout gbl, int x, int y, int w, int h) {
		GridBagConstraints gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = x;
	    gbc.gridy = y;
	    gbc.gridwidth = w;
	    gbc.gridheight = h;
	    gbc.insets = new Insets(2, 5, 2, 5);
	    panel.add(comp, gbc);
    }
	
	// any time a change is made to the document, the scxml editor "modified"
	// flag is set
	protected class DocumentChangeListener implements DocumentListener {
		private SCXMLGraphEditor editor;
		private final List<mxUndoableChange> changes = new ArrayList<mxUndoableEdit.mxUndoableChange>();

		public DocumentChangeListener(SCXMLGraphEditor e) {
			this.editor = e;
		}

		public void insertUpdate(DocumentEvent e) {
			mxGraphModel model = (mxGraphModel) editor.getGraphComponent().getGraph().getModel();
			model.fireEvent(new mxEventObject(mxEvent.CHANGE, "changes", changes, "revalidate", false));
		}

		public void removeUpdate(DocumentEvent e) {
			mxGraphModel model = (mxGraphModel) editor.getGraphComponent().getGraph().getModel();
			model.fireEvent(new mxEventObject(mxEvent.CHANGE, "changes", changes, "revalidate", false));
		}

		public void changedUpdate(DocumentEvent e) {
			mxGraphModel model = (mxGraphModel) editor.getGraphComponent().getGraph().getModel();
			model.fireEvent(new mxEventObject(mxEvent.CHANGE, "changes", changes, "revalidate", false));
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("id")) {
			cellSelector.toggleSelection(cell);
		} else if (cmd.equals("ok")) {
			if(performOK()==false) return;
			if(this instanceof SCXMLTransitionEditor) {
				EventParam param = ((SCXMLTransitionEditor)this).getEventParam();
				List<EventParam> eventList = editor.getEventList();
				boolean isExist = false;
				for(EventParam item : eventList) {
					if(item.checkSame(param)) {
						item.replaceContents(param);
						isExist = true;
						break;
					}
				}
				if(isExist==false) {
					eventList.add(param);
				}
			} else if(this instanceof SCXMLStateEditor) {
				String orgName = ((SCXMLStateEditor)this).getOrgName();
				SCXMLNode newNode = (SCXMLNode) this.cell.getValue();
				String newName = newNode.getID();
				if(orgName.equals(newName)==false) {
					List<EventParam> eventList = editor.getEventList();
					for(EventParam item : eventList) {
						if(item.getSource().equals(orgName)) {
							item.setSource(newName);
						}
						if(item.getTarget().equals(orgName)) {
							item.setTarget(newName);
						}
					}
				}
				
			}
			List<mxUndoableChange> changes = new ArrayList<mxUndoableEdit.mxUndoableChange>();
			mxGraphModel model = (mxGraphModel) editor.getGraphComponent().getGraph().getModel();
			model.fireEvent(new mxEventObject(mxEvent.CHANGE, "changes", changes, "revalidate", false));
			closeAction.actionPerformed(null);
			
		} else if (cmd.equals("cancel")) {
			closeAction.actionPerformed(null);
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		closeAction.actionPerformed(null);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}
	
	abstract protected boolean performOK();
}
