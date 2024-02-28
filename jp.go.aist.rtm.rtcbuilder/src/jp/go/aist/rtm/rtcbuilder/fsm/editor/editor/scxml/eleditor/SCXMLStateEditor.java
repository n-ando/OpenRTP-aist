package jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.eleditor;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Point;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.mxgraph.model.mxCell;

import jp.go.aist.rtm.rtcbuilder.fsm.editor.SCXMLGraphEditor;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.fileimportexport.SCXMLNode;
import jp.go.aist.rtm.rtcbuilder.nl.Messages;
import jp.go.aist.rtm.rtcbuilder.util.StringUtil;

public class SCXMLStateEditor extends SCXMLEditorRoot {
	private static final long serialVersionUID = -1937899100474024531L;
	
	private static final String STR_ON = "<log label=\"ON\"></log>";
	
	private SCXMLNode node;
	private JTextField txtName;
	private JCheckBox chkEntry;
	private JCheckBox chkExit;
	private boolean isRoot = false;
	private Shell shell;
	private String orgName;

	public String getOrgName() {
		return orgName;
	}

	public SCXMLStateEditor(JFrame parent, mxCell nn, mxCell rootOfGraph, SCXMLGraphEditor editor, Point pos) {
		super(parent, editor, nn);
		setTitle("State Editor");
		setLocation(pos);
		if(shell==null) {
			Display display = new Display();
		    shell = new Shell(display);
		    display.dispose();
		}
		
		node = (SCXMLNode) nn.getValue();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		GridBagLayout gbl = new GridBagLayout();
		panel.setLayout(gbl);
		editor.toString();
		
        addUIParts(panel, new JLabel("State Name"), gbl, 0, 0, 1, 1);
		if (nn!=rootOfGraph) {
	        txtName = new JTextField(node.getID());
		} else {
	        txtName = new JTextField(node.getName());
	        isRoot = true;
		}
		orgName = txtName.getText();
		
        txtName.setPreferredSize(new Dimension(300, 20));
        addUIParts(panel, txtName, gbl, 1, 0, 2, 1);
        //
        addUIParts(panel, new JLabel("Actions"), gbl, 0, 1, 1, 1);
        chkEntry = new JCheckBox("On Entry");
        addUIParts(panel, chkEntry, gbl, 1, 1, 1, 1);
        if(node.getOnEntry().equals(STR_ON)) {
        	chkEntry.setSelected(true);
        }
        //
        chkExit = new JCheckBox("On Exit");
        addUIParts(panel, chkExit, gbl, 2, 1, 1, 1);
        if(node.getOnExit().equals(STR_ON)) {
        	chkExit.setSelected(true);
        }
        
        if(node.isInitial() || node.isFinal()) {
        	chkEntry.setEnabled(false);
        	chkExit.setEnabled(false);
        }
		
		pack();
		setVisible(true);
	}
	
	protected boolean performOK() {
		String strName = txtName.getText();
		if(strName==null || strName.length()==0) {
			JOptionPane.showMessageDialog(this,
					Messages.getString("IMC.FSM_STATE_NAME_EMPTY"),
					"Validation Error", 
					JOptionPane.ERROR_MESSAGE);
			if (isRoot) {
				txtName.setText(node.getName());
			} else {
				txtName.setText(node.getID());
			}
			txtName.requestFocusInWindow();
			txtName.selectAll();
			return false;
		}
		if(StringUtil.checkProhibitedChar(strName)==false) {
			JOptionPane.showMessageDialog(this,
					Messages.getString("IMC.FSM_STATE_NAME_PROHIBITED"),
					"Validation Error", 
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		node.setID(strName);
		if(chkEntry.isSelected()) {
			node.setOnEntry(STR_ON);	
		} else {
			node.setOnEntry("");	
		}
		if(chkExit.isSelected()) {
			node.setOnExit(STR_ON);	
		} else {
			node.setOnExit("");	
		}
		
		return true;
	}
}
