package jp.go.aist.rtm.systemeditor.ui.views.logview;

import java.util.Calendar;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

import jp.go.aist.rtm.systemeditor.nl.Messages;

public class TimeRangeDialog extends Dialog {
	private FilteringParam targetParam;

	private Button chkFrom;
	private DateTime dtFromDate;
	private DateTime dtFromTime;
	private Button chkTo;
	private DateTime dtToDate;
	private DateTime dtToTime;

	public void setTargetParam(FilteringParam targetParam) {
		this.targetParam = targetParam;
	}
	
	public TimeRangeDialog(Shell parentShell) {
		super(parentShell);
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		GridLayout gridLayout = new GridLayout(1, true);

		Composite mainComposite = (Composite) super.createDialogArea(parent);
		mainComposite.setLayout(gridLayout);
		mainComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Group compGroup = new Group(mainComposite, SWT.NONE);
		compGroup.setLayout(new GridLayout(3, false));
		compGroup.setText("Time Range");
		GridData gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		compGroup.setLayoutData(gd);
		//
		chkFrom = new Button(compGroup, SWT.CHECK);
		chkFrom.setText("From:");
		gd = new GridData();
		gd.grabExcessHorizontalSpace = true;
		chkFrom.setLayoutData(gd);
		
		dtFromDate = new DateTime(compGroup, SWT.DATE);
		gd = new GridData();
		gd.grabExcessHorizontalSpace = true;
		dtFromDate.setLayoutData(gd);
		
		dtFromTime = new DateTime(compGroup, SWT.TIME);
		gd = new GridData();
		gd.grabExcessHorizontalSpace = true;
		dtFromTime.setLayoutData(gd);
		//
		chkTo = new Button(compGroup, SWT.CHECK);
		chkTo.setText("To:");
		gd = new GridData();
		gd.grabExcessHorizontalSpace = true;
		chkTo.setLayoutData(gd);
		
		dtToDate = new DateTime(compGroup, SWT.DATE);
		gd = new GridData();
		gd.grabExcessHorizontalSpace = true;
		dtToDate.setLayoutData(gd);
		
		dtToTime = new DateTime(compGroup, SWT.TIME);
		gd = new GridData();
		gd.grabExcessHorizontalSpace = true;
		dtToTime.setLayoutData(gd);
		/////
		chkFrom.setSelection(targetParam.isFrom());
		if(chkFrom.getSelection()) {
			String strFromData = targetParam.getFromDate();
			String[] eachFD = strFromData.split("/");
			if(eachFD.length==3) {
				dtFromDate.setDate(Integer.parseInt(eachFD[0]),
						Integer.parseInt(eachFD[1]),
						Integer.parseInt(eachFD[2]));
			}
			
			String strFromTime = targetParam.getFromTime();
			String[] eachFT = strFromTime.split(":");
			if(eachFT.length==3) {
				dtFromTime.setTime(Integer.parseInt(eachFT[0]),
						Integer.parseInt(eachFT[1]),
						Integer.parseInt(eachFT[2]));
			}
		}
		
		chkTo.setSelection(targetParam.isTo());
		if(chkTo.getSelection()) {
			String strToData = targetParam.getToDate();
			String[] eachTD = strToData.split("/");
			if(eachTD.length==3) {
				dtToDate.setDate(Integer.parseInt(eachTD[0]),
						Integer.parseInt(eachTD[1]),
						Integer.parseInt(eachTD[2]));
			}
			
			String strToTime = targetParam.getToTime();
			String[] eachTT = strToTime.split(":");
			if(eachTT.length==3) {
				dtToTime.setTime(Integer.parseInt(eachTT[0]),
						Integer.parseInt(eachTT[1]),
						Integer.parseInt(eachTT[2]));
			}
		}

		return mainComposite;
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Time RangeCondition"); //$NON-NLS-1$
	}
	
	protected Point getInitialSize() {
		return new Point(250, 170);
	}
	@Override
	protected void okPressed() {
		targetParam.setFrom(chkFrom.getSelection());
		if(chkFrom.getSelection() && chkTo.getSelection()) {
			Calendar cldFrom = Calendar.getInstance();
			cldFrom.set(dtFromDate.getYear(), dtFromDate.getMonth(), dtFromDate.getDay(),
							dtFromTime.getHours(), dtFromTime.getMinutes(), dtFromTime.getSeconds());
			Calendar cldTo = Calendar.getInstance();
			cldTo.set(dtToDate.getYear(), dtToDate.getMonth(), dtToDate.getDay(),
							dtToTime.getHours(), dtToTime.getMinutes(), dtToTime.getSeconds());
			int diff = cldFrom.compareTo(cldTo);
			if(0<diff) {
				MessageDialog.openWarning(getShell(),
						Messages.getString("LogView.columnTime"),
						Messages.getString("LogView.timeWarning"));
				return;
			}
		}
		
		if(chkFrom.getSelection()) {
			targetParam.setFromDate(
					String.format("%04d",dtFromDate.getYear()) + "/"
							+ String.format("%02d", dtFromDate.getMonth()) + "/"
							+ String.format("%02d",dtFromDate.getDay()));
			targetParam.setFromTime(
					String.format("%02d",dtFromTime.getHours()) + ":"
							+ String.format("%02d",dtFromTime.getMinutes()) + ":"
							+ String.format("%02d",dtFromTime.getSeconds()));
		} else {
			targetParam.setFromDate("");
			targetParam.setFromTime("");
		}
		
		targetParam.setTo(chkTo.getSelection());
		if(chkTo.getSelection()) {
			targetParam.setToDate(String.format("%04d",dtToDate.getYear()) + "/"
						+ String.format("%02d",dtToDate.getMonth()) + "/"
						+ String.format("%02d",dtToDate.getDay()));
			targetParam.setToTime(String.format("%02d",dtToTime.getHours()) + ":"
						+ String.format("%02d",dtToTime.getMinutes()) + ":"
						+ String.format("%02d",dtToTime.getSeconds()));
		} else {
			targetParam.setFromDate("");
			targetParam.setFromTime("");
		}
		
		super.okPressed();
	}
}
