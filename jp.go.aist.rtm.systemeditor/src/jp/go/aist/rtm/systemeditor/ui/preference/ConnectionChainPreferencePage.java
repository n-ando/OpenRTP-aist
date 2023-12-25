package jp.go.aist.rtm.systemeditor.ui.preference;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.nl.Messages;

public class ConnectionChainPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	private SystemEditorPreferenceManager manager;
	private Text intervalText;

	public ConnectionChainPreferencePage() {
	}

	public ConnectionChainPreferencePage(String title) {
		super(title);
	}

	public ConnectionChainPreferencePage(String title, ImageDescriptor image) {
		super(title, image);
	}

	@Override
	protected Control createContents(Composite parent) {
		GridLayout gl;
		GridData gd;

		manager = SystemEditorPreferenceManager.getInstance();

		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout());
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.grabExcessHorizontalSpace = true;
		composite.setLayoutData(gd);
		
		Group intervalGroup = new Group(composite, SWT.NONE);
		gl = new GridLayout();
		gl.numColumns = 3;
		intervalGroup.setLayout(gl);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		intervalGroup.setLayoutData(gd);
		intervalGroup.setText(Messages.getString("ConnectionChainPreferencePage.0"));
		
		createLabel(intervalGroup, Messages.getString("ConnectionChainPreferencePage.1"));
		
		intervalText = new Text(intervalGroup, SWT.SINGLE | SWT.BORDER | SWT.RIGHT);
		gd = new GridData();
		gd.widthHint = 100;
		intervalText.setLayoutData(gd);
		intervalText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateStatus();
			}
		});

		createLabel(intervalGroup, Messages.getString("ConnectionChainPreferencePage.2"));

		buildData();
		return composite;
	}

	void buildData() {
		intervalText.setText(Integer.valueOf(manager.getCheckInterval()).toString());
	}
	Label createLabel(Composite parent, String text) {
		Label label = new Label(parent, SWT.NULL);
		label.setText(text);
		return label;
	}

	void updateStatus() {
		try {
			Integer d = Integer.valueOf(intervalText.getText());
			setErrorMessage(null);
			if (d <= 0) {
				setValid(false);
				return;
			}
		} catch (NumberFormatException e) {
			setErrorMessage("'" + intervalText.getText() + "'" + Messages.getString("SystemEditorPreferencePage.5"));
			setValid(false);
			return;
		}
		//
		setValid(true);
	}

	@Override
	public void init(IWorkbench workbench) {
	}
	@Override
	public boolean performOk() {
		Integer d = Integer.valueOf(intervalText.getText());
		manager.setCheckInterval(d);
		return super.performOk();
	}

	@Override
	protected void performDefaults() {
		manager.resetCheckInterval();
		buildData();
		super.performDefaults();
	}
}
