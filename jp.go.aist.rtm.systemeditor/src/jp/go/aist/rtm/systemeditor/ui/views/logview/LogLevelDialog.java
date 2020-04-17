package jp.go.aist.rtm.systemeditor.ui.views.logview;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

import jp.go.aist.rtm.systemeditor.nl.Messages;

public class LogLevelDialog extends Dialog {
	private FilteringParam targetParam;
	
	private Button chkSilent;
	private Button chkError;
	private Button chkWarn;
	private Button chkInfo;
	private Button chkDebug;
	private Button chkTrace;
	private Button chkVerbose;
	private Button chkParanoid;
	
	public void setTargetParam(FilteringParam targetParam) {
		this.targetParam = targetParam;
	}
	
	public LogLevelDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(getShellStyle() | SWT.CENTER | SWT.RESIZE);
	}
	@Override
	protected Control createDialogArea(Composite parent) {
		GridLayout gridLayout = new GridLayout(1, true);

		Composite mainComposite = (Composite) super.createDialogArea(parent);
		mainComposite.setLayout(gridLayout);
		mainComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Group compGroup = new Group(mainComposite, SWT.NONE);
		compGroup.setLayout(new GridLayout(9, false));
		compGroup.setText(Messages.getString("LogView.columnLevel"));
		GridData gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		compGroup.setLayoutData(gd);
		
		chkSilent = createCheckBox(compGroup, "SILENT");
		chkError = createCheckBox(compGroup, "ERROR");
		chkWarn = createCheckBox(compGroup, "WARN");
		chkInfo = createCheckBox(compGroup, "INFO");
		chkDebug = createCheckBox(compGroup, "DEBUG");
		chkTrace = createCheckBox(compGroup, "TRACE");
		chkVerbose = createCheckBox(compGroup, "VERBOSE");
		chkParanoid = createCheckBox(compGroup, "PARANOID");
		
		return mainComposite;
	}

	private Button createCheckBox(Group compGroup, String name) {
		GridData gd;
		Button result = new Button(compGroup, SWT.CHECK);
		result.setText(name);
		gd = new GridData();
		gd.grabExcessHorizontalSpace = true;
		result.setLayoutData(gd);
		
		result.setSelection(targetParam.getLevelList().contains(name));
		
		return result;
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Log Level Condition"); //$NON-NLS-1$
	}
	
	protected Point getInitialSize() {
		return new Point(600, 150);
	}
	@Override
	protected void okPressed() {
		targetParam.getLevelList().clear();
		
		if(chkSilent.getSelection()) {
			targetParam.getLevelList().add("SILENT");
		}
		if(chkError.getSelection()) {
			targetParam.getLevelList().add("ERROR");
		}
		if(chkWarn.getSelection()) {
			targetParam.getLevelList().add("WARN");
		}
		if(chkInfo.getSelection()) {
			targetParam.getLevelList().add("INFO");
		}
		if(chkDebug.getSelection()) {
			targetParam.getLevelList().add("DEBUG");
		}
		if(chkTrace.getSelection()) {
			targetParam.getLevelList().add("TRACE");
		}
		if(chkVerbose.getSelection()) {
			targetParam.getLevelList().add("VERBOSE");
		}
		if(chkParanoid.getSelection()) {
			targetParam.getLevelList().add("PARANOID");
		}
		
		super.okPressed();
	}
}
