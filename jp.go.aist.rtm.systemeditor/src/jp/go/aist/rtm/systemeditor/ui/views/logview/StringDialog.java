package jp.go.aist.rtm.systemeditor.ui.views.logview;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.views.logview.FilteringParam.FilteringKind;

public class StringDialog extends Dialog {
	private FilteringParam targetParam;
	private FilteringKind kind;
	
	private Text txtCond;
	
	public void setTargetParam(FilteringParam targetParam) {
		this.targetParam = targetParam;
	}
	
	public void setKind(FilteringKind kind) {
		this.kind = kind;
	}

	public StringDialog(Shell parentShell) {
		super(parentShell);
	}
	@Override
	protected Control createDialogArea(Composite parent) {
		GridLayout gridLayout = new GridLayout(1, true);

		Composite mainComposite = (Composite) super.createDialogArea(parent);
		mainComposite.setLayout(gridLayout);
		mainComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		String strName = "";
		switch(kind) {
		case MANAGER:
			strName = Messages.getString("LogView.columnManager");
			break;
		case IDENTIFIER:
			strName = Messages.getString("LogView.columnID");
			break;
		case MESSAGE:
			strName = Messages.getString("LogView.columnMessage");
			break;
		}
		
		Group compGroup = new Group(mainComposite, SWT.NONE);
		compGroup.setLayout(new GridLayout(1, false));
		compGroup.setText(strName);
		GridData gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		compGroup.setLayoutData(gd);
		
		txtCond = new Text(compGroup, SWT.BORDER);
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		txtCond.setLayoutData(gd);
		switch(kind) {
		case MANAGER:
			if(targetParam.getManagerCond()!=null) {
				txtCond.setText(targetParam.getManagerCond());
			}
			break;
		case IDENTIFIER:
			if(targetParam.getIdentifierCond()!=null) {
				txtCond.setText(targetParam.getIdentifierCond());
			}
			break;
		case MESSAGE:
			if(targetParam.getMessageCond()!=null) {
				txtCond.setText(targetParam.getMessageCond());
			}
			break;
		}
		return mainComposite;
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Condition"); //$NON-NLS-1$
	}
	
	protected Point getInitialSize() {
		return new Point(500, 130);
	}
	@Override
	protected void okPressed() {
		switch(kind) {
		case MANAGER:
			targetParam.setManagerCond(txtCond.getText());
			break;
		case IDENTIFIER:
			targetParam.setIdentifierCond(txtCond.getText());
			break;
		case MESSAGE:
			targetParam.setMessageCond(txtCond.getText());
			break;
		}
		super.okPressed();
	}

}
