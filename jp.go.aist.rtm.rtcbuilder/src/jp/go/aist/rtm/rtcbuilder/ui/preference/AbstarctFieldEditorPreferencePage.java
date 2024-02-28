package jp.go.aist.rtm.rtcbuilder.ui.preference;

import jp.go.aist.rtm.rtcbuilder.nl.Messages;
import jp.go.aist.rtm.rtcbuilder.ui.editors.IMessageConstants;
import jp.go.aist.rtm.rtcbuilder.util.StringUtil;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

public abstract class AbstarctFieldEditorPreferencePage extends FieldEditorPreferencePage{
	
	public AbstarctFieldEditorPreferencePage(){
		super(GRID);
	}

	protected Combo createLabelAndCombo(Composite baseGroup, String labelString, String[] items) {
		Label label = new Label(baseGroup, SWT.NULL);
		label.setText(labelString);
		Combo combo = new Combo(baseGroup, SWT.DROP_DOWN | SWT.READ_ONLY);
		combo.setItems(items);
		combo.select(0);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		combo.setLayoutData(gridData);

		return combo;
	}

	protected Composite createGroup(Composite composite, String label) {
		GridLayout gridLayout;
		GridData gd;
		Group targetGroup = new Group(composite, SWT.NONE);
		gridLayout = new GridLayout();
		gridLayout.marginWidth = 5;
		gridLayout.marginTop = -3;
		targetGroup.setLayout(gridLayout);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		targetGroup.setLayoutData(gd);

		targetGroup.setText(label);
		
		Composite result = new Composite(targetGroup, SWT.NULL);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.grabExcessHorizontalSpace = true;
		result.setLayoutData(gd);
		
		return result;
	}
	
	protected class DigitAlphabetStringFieldEditor extends StringFieldEditor {
	    public DigitAlphabetStringFieldEditor(String name, String labelText, Composite parent) {
	    	super(name, labelText, parent);
	    }
	    
		@Override
		protected boolean doCheckState() {
			if( !StringUtil.checkDigitAlphabet(super.getStringValue()) ) {
				super.setErrorMessage(IMessageConstants.BASIC_VALIDATE_NAME2);
				return false;
			}
			return super.doCheckState();
		}
	}
	
	protected class DoubleStringFieldEditor extends StringFieldEditor {
	    public DoubleStringFieldEditor(String name, String labelText, Composite parent) {
	        super(name, labelText, parent);
	    }
	    
		@Override
		protected boolean doCheckState() {
			Double parseDbl = null;
			try {
				parseDbl = new Double(Double.parseDouble(super.getStringValue()));
			} catch (Exception e) {
			}
			if (parseDbl == null) {
				super.setErrorMessage(Messages.getString("IMC.BASIC_VALIDATE_ECRATE1"));
				return false;
			}
			if (parseDbl != null && parseDbl.intValue() < 0) {
				super.setErrorMessage(Messages.getString("IMC.BASIC_VALIDATE_ECRATE2"));
				return false;
			}
			return super.doCheckState();
		}
	}
	
}
