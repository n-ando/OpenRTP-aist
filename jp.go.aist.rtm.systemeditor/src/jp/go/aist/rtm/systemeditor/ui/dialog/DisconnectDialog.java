package jp.go.aist.rtm.systemeditor.ui.dialog;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.Port;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import static jp.go.aist.rtm.systemeditor.nl.Messages.*;

public class DisconnectDialog extends TitleAreaDialog {

	static final String DIALOG_EXPLAN = getString("DisconnectDialog.explain");

	static final String PROPERTY_CONN_ID = "PROPERTY_CONN_ID";
	static final String PROPERTY_CONN_NAME = "PROPERTY_CONN_NAME";

	static final String COLUMN_LABEL_CONN_ID = "Connector Id";
	static final String COLUMN_LABEL_CONN_NAME = "Connector name";

	static final String BUTTON_LABEL_DELETE = getString("ConnectPortActionDelegate.disconnect");

	static final int EXEC_BUTTON_WIDTH = 70;

	TableViewer tableViewer;
	Table table;
	Button deleteButton;

	Port port;
	List<ConnectorProfile> profiles;
	List<ConnectorProfile> deleteProfiles;
	ConnectorProfile selectedConnectorProfile;

	public DisconnectDialog(Shell parentShell) {
		super(parentShell);
		setHelpAvailable(false);
		setShellStyle(getShellStyle() | SWT.CENTER | SWT.RESIZE);
	}

	/** コネクション切断対象のポートを設定 */
	public void setPort(Port port) {
		this.port = port;
	}

	/** 切断するコネクタプロファイルのリストを取得 */
	public List<ConnectorProfile> getDeleteConnectorProfiles() {
		return this.deleteProfiles;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		GridLayout gl;
		GridData gd;

		setTitle(DIALOG_EXPLAN);

		Composite mainComposite = new Composite((Composite) super
				.createDialogArea(parent), SWT.NONE);
		gl = new GridLayout(2, false);
		gd = new GridData(GridData.FILL_BOTH);
		mainComposite.setLayout(gl);
		mainComposite.setLayoutData(gd);
		mainComposite.setFont(parent.getFont());

		Label label = new Label(mainComposite, SWT.NONE);
		label.setText(port.getNameL());
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.horizontalSpan = 2;
		label.setLayoutData(gd);

		tableViewer = new TableViewer(mainComposite, SWT.FULL_SELECTION
				| SWT.SINGLE | SWT.BORDER);
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setColumnProperties(new String[] { PROPERTY_CONN_ID,
				PROPERTY_CONN_NAME });
		tableViewer.setLabelProvider(new ConnectorLabelProvider());
		tableViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						StructuredSelection selection = (StructuredSelection) event
								.getSelection();
						selectedConnectorProfile = (ConnectorProfile) selection
								.getFirstElement();
						notifyModified();
					}
				});

		table = tableViewer.getTable();
		gl = new GridLayout(1, false);
		gd = new GridData();
		gd.verticalAlignment = SWT.FILL;
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.grabExcessHorizontalSpace = true;
		gd.heightHint = 150;
		table.setLayout(gl);
		table.setLayoutData(gd);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		TableColumn col = new TableColumn(table, SWT.NONE);
		col.setText(COLUMN_LABEL_CONN_ID);
		col.setWidth(300);
		col = new TableColumn(table, SWT.NONE);
		col.setText(COLUMN_LABEL_CONN_NAME);
		col.setWidth(200);

		Composite buttonComposite = new Composite(mainComposite, SWT.NONE);
		gl = new GridLayout(1, false);
		gd = new GridData();
		gd.verticalAlignment = SWT.FILL;
		buttonComposite.setLayout(gl);
		buttonComposite.setLayoutData(gd);

		deleteButton = new Button(buttonComposite, SWT.PUSH);
		deleteButton.setText(BUTTON_LABEL_DELETE);
		gd = new GridData();
		gd.widthHint = EXEC_BUTTON_WIDTH;
		deleteButton.setLayoutData(gd);
		deleteButton.setEnabled(false);
		deleteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (selectedConnectorProfile != null) {
					profiles.remove(selectedConnectorProfile);
					deleteProfiles.add(selectedConnectorProfile);
					selectedConnectorProfile = null;
					tableViewer.refresh();
				}
				notifyModified();
			}
		});

		buildData();

		return mainComposite;
	}

	@Override
	protected Control createButtonBar(Composite parent) {
		Control composite = super.createButtonBar(parent);
		notifyModified();
		return composite;
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Disconnect Profile");
	}

	/** 表示内容を構築 */
	void buildData() {
		if (profiles == null) {
			profiles = new ArrayList<ConnectorProfile>();
			deleteProfiles = new ArrayList<ConnectorProfile>();
		}
		profiles.addAll(port.getConnectorProfiles());
		tableViewer.setInput(profiles);
	}

	/** 変更を通知します */
	void notifyModified() {
		if (selectedConnectorProfile != null) {
			deleteButton.setEnabled(true);
		} else {
			deleteButton.setEnabled(false);
		}
		if (deleteProfiles.isEmpty()) {
			getButton(IDialogConstants.OK_ID).setEnabled(false);
		} else {
			getButton(IDialogConstants.OK_ID).setEnabled(true);
		}
	}

	/** ラベルプロバイダ(コンポーネント) */
	public class ConnectorLabelProvider extends LabelProvider implements
			ITableLabelProvider {

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			ConnectorProfile entry = (ConnectorProfile) element;
			if (columnIndex == 0) {
				return entry.getConnectorId();
			} else if (columnIndex == 1) {
				return entry.getName();
			}
			return null;
		}
	}
}
