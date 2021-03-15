package jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.scxml.eleditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.mxgraph.model.mxCell;

import jp.go.aist.rtm.rtcbuilder.fsm.EventParam;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.SCXMLGraphEditor;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.editor.fileimportexport.SCXMLEdge;
import jp.go.aist.rtm.rtcbuilder.nl.Messages;
import jp.go.aist.rtm.rtcbuilder.util.StringUtil;

public class SCXMLTransitionEditor extends SCXMLEditorRoot {

	private static final long serialVersionUID = -6967742868602449986L;
	
	private SCXMLEdge edge;
	private JTextField txtEvent;
	private JTextField txtCondition;
	
	private JComboBox<String> cmbType;;
	private JTextField txtDescription;
	private JTextField txtType;
	private JTextField txtNumber;
	private JTextField txtSemantics;
	private JTextField txtUnit;
	private JTextField txtOperation;
	
	private EventParam targetParam;
	private boolean canEdit;
	private String[] defaultTypeList;
	private mxCell cell = null;
	
	public SCXMLTransitionEditor(JFrame parent, mxCell en, SCXMLGraphEditor editor, Point pos) {
		super(parent, editor, en);
		this.cell = en;
		setTitle("Transition Editor");
		setLocation(pos);
		
		edge = (SCXMLEdge) en.getValue();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	public void setStatus(String[] defaultTypeList, boolean canEdit, EventParam target) {
		this.defaultTypeList = defaultTypeList;
		this.canEdit = canEdit;
		this.targetParam = target;
	}
	
	public EventParam getEventParam() {
		return this.targetParam;
	}
	
	public void buildDialog() {
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		GridBagLayout gbl = new GridBagLayout();
		panel.setLayout(gbl);
		
        addUIParts(panel, new JLabel(Messages.getString("IMC.EVENT_NAME")), gbl, 0, 0, 1, 1);
        txtEvent = new JTextField(edge.getEvent());
        txtEvent.setPreferredSize(new Dimension(300, 20));
        addUIParts(panel, txtEvent, gbl, 1, 0, 2, 1);
		//
        addUIParts(panel, new JLabel(Messages.getString("IMC.EVENT_CONDITION")), gbl, 0, 1, 1, 1);
        txtCondition = new JTextField(edge.getCondition());
        txtCondition.setPreferredSize(new Dimension(300, 20));
        addUIParts(panel, txtCondition, gbl, 1, 1, 2, 1);
        //
        if(this.canEdit) {
	        Border lineBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
			TitledBorder titledBorder = BorderFactory.createTitledBorder(lineBorder, Messages.getString("IMC.EVENT_DETAIL"));
			JPanel eventPanel = new JPanel();
			eventPanel.setBorder(titledBorder);
			eventPanel.setLayout(gbl);
	        addUIParts(panel, eventPanel, gbl, 0, 2, 3, 1);
	        
	        addUIParts(eventPanel, new JLabel(Messages.getString("IMC.EVENT_DOC_TYPE")), gbl, 0, 0, 1, 1);
	        cmbType = new JComboBox<>();
        	cmbType.addItem("");
	        for(String item : defaultTypeList) {
	        	cmbType.addItem(item);
	        }
	        if(targetParam!=null && targetParam.getDataType()!=null) {
	        	cmbType.setSelectedItem(targetParam.getDataType());
	        }
	        cmbType.setPreferredSize(new Dimension(300, 20));
	        addUIParts(eventPanel, cmbType, gbl, 1, 0, 1, 1);
	        //
			TitledBorder titledDoc = BorderFactory.createTitledBorder(lineBorder, Messages.getString("IMC.HINT_DOCUMENT_TITLE"));
			JPanel docPanel = new JPanel();
			docPanel.setBorder(titledDoc);
			docPanel.setLayout(gbl);
	        addUIParts(eventPanel, docPanel, gbl, 0, 1, 2, 1);
	        
	        JLabel lblDesc = new JLabel(Messages.getString("IMC.EVENT_DOC_DESCRIPTION"));
	        lblDesc.setHorizontalAlignment(JLabel.RIGHT);
	        addUIParts(docPanel, lblDesc, gbl, 0, 0, 1, 1);
	        txtDescription = new JTextField();
	        if(targetParam!=null && targetParam.getDoc_description()!=null) {
	        	txtDescription.setText(targetParam.getDoc_description());
	        }
	        txtDescription.setPreferredSize(new Dimension(300, 40));
	        addUIParts(docPanel, txtDescription, gbl, 1, 0, 1, 1);
	        
	        JLabel lblType = new JLabel(Messages.getString("IMC.EVENT_DOC_TYPE"));
	        lblType.setHorizontalAlignment(JLabel.RIGHT);
	        addUIParts(docPanel, lblType, gbl, 0, 1, 1, 1);
	        txtType = new JTextField();
	        if(targetParam!=null && targetParam.getDoc_type()!=null) {
	        	txtType.setText(targetParam.getDoc_type());
	        }
	        txtType.setPreferredSize(new Dimension(300, 20));
	        addUIParts(docPanel, txtType, gbl, 1, 1, 1, 1);
	        
	        JLabel lblNum = new JLabel(Messages.getString("IMC.DATAPORT_LBL_DATANUM"));
	        lblNum.setHorizontalAlignment(JLabel.RIGHT);
	        addUIParts(docPanel, lblNum, gbl, 0, 2, 1, 1);
	        txtNumber = new JTextField();
	        if(targetParam!=null && targetParam.getDoc_num()!=null) {
	        	txtNumber.setText(targetParam.getDoc_num());
	        }
	        txtNumber.setPreferredSize(new Dimension(300, 20));
	        addUIParts(docPanel, txtNumber, gbl, 1, 2, 1, 1);
	        
	        JLabel lblDetail = new JLabel(Messages.getString("IMC.DATAPORT_LBL_SEMANTICS"));
	        lblDetail.setHorizontalAlignment(JLabel.RIGHT);
	        addUIParts(docPanel, lblDetail, gbl, 0, 3, 1, 1);
	        txtSemantics = new JTextField();
	        if(targetParam!=null && targetParam.getDoc_semantics()!=null) {
	        	txtSemantics.setText(targetParam.getDoc_semantics());
	        }
	        txtSemantics.setPreferredSize(new Dimension(300, 20));
	        addUIParts(docPanel, txtSemantics, gbl, 1, 3, 1, 1);
	        
	        JLabel lblUnit = new JLabel(Messages.getString("IMC.DATAPORT_LBL_UNIT"));
	        lblUnit.setHorizontalAlignment(JLabel.RIGHT);
	        addUIParts(docPanel, lblUnit, gbl, 0, 4, 1, 1);
	        txtUnit = new JTextField();
	        if(targetParam!=null && targetParam.getDoc_unit()!=null) {
	        	txtUnit.setText(targetParam.getDoc_unit());
	        }
	        txtUnit.setPreferredSize(new Dimension(300, 20));
	        addUIParts(docPanel, txtUnit, gbl, 1, 4, 1, 1);
	        
	        JLabel lblOpe = new JLabel(Messages.getString("IMC.DATAPORT_LBL_OPERAT"));
	        lblOpe.setHorizontalAlignment(JLabel.RIGHT);
	        addUIParts(docPanel, lblOpe, gbl, 0, 5, 1, 1);
	        txtOperation = new JTextField();
	        if(targetParam!=null && targetParam.getDoc_operation()!=null) {
	        	txtOperation.setText(targetParam.getDoc_operation());
	        }
	        txtOperation.setPreferredSize(new Dimension(300, 20));
	        addUIParts(docPanel, txtOperation, gbl, 1, 5, 1, 1);
        }
		
		pack();
		setVisible(true);
	}
	
	protected boolean performOK() {
		String strName = txtEvent.getText();
		if(StringUtil.checkProhibitedChar(strName)==false) {
			JOptionPane.showMessageDialog(this,
					Messages.getString("IMC.FSM_EVENT_NAME_PROHIBITED"),
					"Validation Error", 
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		edge.setEvent(txtEvent.getText());
		edge.setCondition(txtCondition.getText());
		//
		targetParam.setName(txtEvent.getText());
		targetParam.setCondition(txtCondition.getText());
		targetParam.setDataType((String)cmbType.getSelectedItem());
		targetParam.setDoc_description(txtDescription.getText());
		targetParam.setDoc_type(txtType.getText());
		targetParam.setDoc_num(txtNumber.getText());
		targetParam.setDoc_semantics(txtSemantics.getText());
		targetParam.setDoc_unit(txtUnit.getText());
		targetParam.setDoc_operation(txtOperation.getText());
		
		return true;
	}
}
