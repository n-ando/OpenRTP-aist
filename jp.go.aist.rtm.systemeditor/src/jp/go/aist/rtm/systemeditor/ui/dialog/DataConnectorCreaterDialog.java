package jp.go.aist.rtm.systemeditor.ui.dialog;

import java.util.Arrays;
import java.util.List;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.InPort;
import jp.go.aist.rtm.toolscommon.model.component.OutPort;
import jp.go.aist.rtm.toolscommon.util.ConnectorUtil;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * データポート間の接続のコネクタプロファイルの選択ダイアログ
 * <P>
 * コネクタプロファイルの選択可能なタイプを、OutPortおよびInPortから判断しプルダウンとして表示する。（「Any」自体は表示されない）(文字のケースは無視してマッチングし、マッチした際に表示されるのはOutPortの文字列とする)<br>
 * 選択可能であるのは、データポートだけであり、サービスポートは含まれない。<br>
 * OutPortもしくはInPortに「Any」が含まれている場合には、相手のすべて型を受け入れるものとする。<br>
 * OutPortおよびInportにAnyが含まれている場合には、コンボボックスに任意の文字列を入力可能とし、「*任意入力可」を表示する。<br>
 * サブスクリプションタイプは、データフロータイプが「Push」の時のみ表示される。<br>
 * PushRateは、サブスクリプションタイプが「Periodic」であり、かつデータフロータイプが「Push」の時のみ表示される<br>
 */
public class DataConnectorCreaterDialog extends ConnectorDialogBase {

	static final String LABEL_DETAIL = Messages.getString("DataConnectorCreaterDialog.22");

	static final String MSG_ERROR_PUSH_RATE_NOT_NUMERIC = Messages.getString("DataConnectorCreaterDialog.19");
	static final String MSG_ERROR_SKIP_COUNT_NOT_INTEGER = Messages.getString("DataConnectorCreaterDialog.30");
	static final String MSG_ERROR_OUTPORT_BUFF_LENGTH_NOT_INTEGER = Messages.getString("DataConnectorCreaterDialog.31");
	static final String MSG_ERROR_OUTPORT_WRITE_TIMEOUT_NOT_NUMERIC = Messages.getString("DataConnectorCreaterDialog.32");
	static final String MSG_ERROR_OUTPORT_READ_TIMEOUT_NOT_NUMERIC = Messages.getString("DataConnectorCreaterDialog.33");
	static final String MSG_ERROR_INPORT_BUFF_LENGTH_NOT_INTEGER = Messages.getString("DataConnectorCreaterDialog.34");
	static final String MSG_ERROR_INPORT_WRITE_TIMEOUT_NOT_NUMERIC = Messages.getString("DataConnectorCreaterDialog.35");
	static final String MSG_ERROR_INPORT_READ_TIMEOUT_NOT_NUMERIC = Messages.getString("DataConnectorCreaterDialog.36");

	private Text nameText;
	private Combo dataTypeCombo;
	private Combo interfaceTypeCombo;
	private Combo dataflowTypeCombo;
	private Combo subscriptionTypeCombo;
	private Text pushRateText;
	private Combo pushPolicyCombo;
	private Text skipCountText;
	private Combo timePolicyCombo;

	Composite detailComposite;
	Point defaultDialogSize;
	private ConnectorProfile connectorProfile;
	private ConnectorProfile dialogResult;
	private OutPort outport;
	private InPort inport;
	BufferPackage ob;
	BufferPackage ib;

	TableViewer additionalTableViewer;
	boolean disableNotify;

	static class BufferPackage {
		Text lengthText;
		Combo fullPolicyCombo;
		Text writeTimeoutText;
		Combo emptyPolicyCombo;
		Text readTimeoutText;
		boolean enable;

		public BufferPackage() {
			this.enable = true;
		}
	}

	public DataConnectorCreaterDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * ConnectorProfileCreaterインタフェースの実装メソッド
	 * <p>
	 * ConnectorProfileとなる候補が複数ある場合には、ダイアログを表示し、ConnectorProfileを作成する。
	 */
	public ConnectorProfile getConnectorProfile(OutPort outport, InPort inport, boolean isReverse) {
		this.outport = outport;
		this.inport = inport;

		String outName = (outport != null) ? outport.getNameL() : "none";
		String inName = (inport != null) ? inport.getNameL() : "none";

		connectorProfile = ComponentFactory.eINSTANCE.createConnectorProfile();
		if(isReverse) {
			connectorProfile.setName(inName + "_" + outName);
		} else {
			connectorProfile.setName(outName + "_" + inName);
		}
		connectorProfile.setIsReverse(isReverse);
		

		setShellStyle(this.getShellStyle() | SWT.RESIZE);
		open();

		return dialogResult;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		GridLayout gl;
		GridData gd;

		disableNotify = false;

		Composite mainComposite = (Composite) super.createDialogArea(parent);
		gl = new GridLayout();
		gd = new GridData(GridData.FILL_BOTH);
		mainComposite.setLayout(gl);
		mainComposite.setLayoutData(gd);

		Label label = createLabel(mainComposite, Messages
				.getString("ConnectorCreaterDialogBase.1"));
		GridData labelLayloutData = new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING);
		label.setLayoutData(labelLayloutData);
		labelLayloutData.heightHint = 20;

		createConnectorProfileComposite(mainComposite);

		return mainComposite;
	}

	/**
	 * メインとなる表示部を作成する
	 */
	private void createConnectorProfileComposite(final Composite mainComposite) {
		GridLayout gl;
		GridData gd;
		Composite portProfileEditComposite = new Composite(mainComposite,
				SWT.NONE);
		gl = new GridLayout(3, false);
		gl.marginBottom = 0;
		gl.marginHeight = 0;
		gl.marginLeft = 0;
		gl.marginRight = 0;
		gl.marginTop = 0;
		gl.marginWidth = 0;
		portProfileEditComposite.setLayout(gl);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		portProfileEditComposite.setLayoutData(gd);

		int style;

		Label name = new Label(portProfileEditComposite, SWT.NONE);
		name.setText("Name :"); //$NON-NLS-1$
		nameText = new Text(portProfileEditComposite, SWT.SINGLE | SWT.BORDER);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		nameText.setLayoutData(gd);
		nameText.setTextLimit(255);
		nameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				connectorProfile.setName(nameText.getText());
				notifyModified();
			}
		});
		createLabel(portProfileEditComposite, "");

		Label dataTypeLabel = new Label(portProfileEditComposite, SWT.NONE);
		dataTypeLabel.setText("Data Type :"); //$NON-NLS-1$
		style = ConnectorUtil.isAllowAnyDataType(outport, inport) ? SWT.DROP_DOWN
				: SWT.DROP_DOWN | SWT.READ_ONLY;
		dataTypeCombo = new Combo(portProfileEditComposite, style);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		dataTypeCombo.setLayoutData(gd);
		dataTypeCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				connectorProfile.setDataType(dataTypeCombo.getText());
				notifyModified();
			}
		});
		Label dataTypeFooterLabel = new Label(portProfileEditComposite,
				SWT.NONE);
		dataTypeFooterLabel.setText(ConnectorUtil.isAllowAnyDataType(
				outport, inport) ? Messages.getString("DataConnectorCreaterDialog.2") : ""); //$NON-NLS-1$ //$NON-NLS-2$

		Label interfaceTypeLabel = new Label(portProfileEditComposite, SWT.NONE);
		interfaceTypeLabel.setText("Interface Type :"); //$NON-NLS-1$
		style = ConnectorUtil.isAllowAnyInterfaceType(outport, inport) ? SWT.DROP_DOWN
				: SWT.DROP_DOWN | SWT.READ_ONLY;
		interfaceTypeCombo = new Combo(portProfileEditComposite, style);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		interfaceTypeCombo.setLayoutData(gd);
		interfaceTypeCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				connectorProfile.setInterfaceType(interfaceTypeCombo.getText());
				notifyModified();
			}
		});
		Label interfaceTypeFooterLabel = new Label(portProfileEditComposite,
				SWT.NONE);
		interfaceTypeFooterLabel.setText(ConnectorUtil
				.isAllowAnyInterfaceType(outport, inport) ? Messages.getString("DataConnectorCreaterDialog.2") : ""); //$NON-NLS-1$ //$NON-NLS-2$

		Label dataflowTypeLabel = new Label(portProfileEditComposite, SWT.NONE);
		dataflowTypeLabel.setText("Dataflow Type :"); //$NON-NLS-1$
		style = ConnectorUtil.isAllowAnyDataflowType(outport, inport) ? SWT.DROP_DOWN
				: SWT.DROP_DOWN | SWT.READ_ONLY;
		dataflowTypeCombo = new Combo(portProfileEditComposite, style);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		dataflowTypeCombo.setLayoutData(gd);
		dataflowTypeCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				connectorProfile.setDataflowType(dataflowTypeCombo.getText());
				if (!connectorProfile.isSubscriptionTypeAvailable()) {
					subscriptionTypeCombo.select(0);
				}
				notifyModified();
			}
		});
		Label dataflowTypeFooterLabel = new Label(portProfileEditComposite,
				SWT.NONE);
		dataflowTypeFooterLabel.setText(ConnectorUtil
				.isAllowAnyDataflowType(outport, inport) ? Messages.getString("DataConnectorCreaterDialog.2") : ""); //$NON-NLS-1$ //$NON-NLS-2$

		Label subscriptionTypeLabel = new Label(portProfileEditComposite,
				SWT.NONE);
		subscriptionTypeLabel.setText("Subscription Type :"); //$NON-NLS-1$
		style = ConnectorUtil.isAllowAnySubscriptionType(outport, inport) ? SWT.DROP_DOWN
				: SWT.DROP_DOWN | SWT.READ_ONLY;
		subscriptionTypeCombo = new Combo(portProfileEditComposite, style);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		subscriptionTypeCombo.setLayoutData(gd);
		subscriptionTypeCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				connectorProfile.setSubscriptionType(subscriptionTypeCombo
						.getText());
				if (!connectorProfile.isPushIntervalAvailable()) {
					pushRateText.setText("");
				} else {
					if (pushRateText.getText().isEmpty()) {
						pushRateText.setText("1000.0");
					}
				}
				if (!connectorProfile.isPushPolicyAvailable()) {
					pushPolicyCombo.select(0);
				}
				notifyModified();
			}
		});
		Label subscriptionTypeFooterLabel = new Label(portProfileEditComposite,
				SWT.NONE);
		subscriptionTypeFooterLabel.setText(ConnectorUtil
				.isAllowAnySubscriptionType(outport, inport) ? Messages.getString("DataConnectorCreaterDialog.2") : ""); //$NON-NLS-1$ //$NON-NLS-2$
		subscriptionTypeCombo.setEnabled(false);

		Label pushRate = new Label(portProfileEditComposite, SWT.NONE);
		pushRate.setText("Push Rate(Hz) :"); //$NON-NLS-1$
		pushRateText = new Text(portProfileEditComposite, SWT.SINGLE
				| SWT.BORDER);
		pushRateText.setEnabled(false);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		pushRateText.setLayoutData(gd);
		pushRateText.setTextLimit(9);
		pushRateText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String text = pushRateText.getText();

				boolean isDouble = false;
				try {
					Double.parseDouble(text);
					isDouble = true;
				} catch (Exception ex) {
					// void
				}

				if (isDouble) {
					connectorProfile.setPushRate(Double.parseDouble(text));
				}

				notifyModified();
			}
		});
		Label footerLabel = new Label(portProfileEditComposite, SWT.NONE);
		footerLabel.setText("");

		// Push Policy
		createLabel(portProfileEditComposite, "Push Policy :");
		style = SWT.DROP_DOWN | SWT.READ_ONLY;
		pushPolicyCombo = new Combo(portProfileEditComposite, style);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		pushPolicyCombo.setLayoutData(gd);
		pushPolicyCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				connectorProfile.setPushPolicy(pushPolicyCombo.getText());
				if (!connectorProfile.isSkipCountAvailable()) {
					skipCountText.setText("");
				} else {
					if (skipCountText.getText().isEmpty()) {
						skipCountText.setText("0");
					}
				}

				notifyModified();
			}
		});
		createLabel(portProfileEditComposite, "");

		// Skip Count
		createLabel(portProfileEditComposite, "Skip Count :");
		skipCountText = new Text(portProfileEditComposite, SWT.SINGLE
				| SWT.BORDER);
		skipCountText.setEnabled(false);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		skipCountText.setLayoutData(gd);
		skipCountText.setTextLimit(9);
		skipCountText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String text = skipCountText.getText();
				try {
					int i = Integer.parseInt(text);
					connectorProfile.setSkipCount(i);
				} catch (Exception ex) {
					// void
				}
				notifyModified();
			}
		});
		createLabel(portProfileEditComposite, "");

		// TimeStamp Policy
		createLabel(portProfileEditComposite, "TimeStamp Policy :");
		style = SWT.DROP_DOWN | SWT.READ_ONLY;
		timePolicyCombo = new Combo(portProfileEditComposite, style);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		timePolicyCombo.setLayoutData(gd);
		timePolicyCombo.add("");
		timePolicyCombo.add("on_write");
		timePolicyCombo.add("on_send");
		timePolicyCombo.add("on_received");
		timePolicyCombo.add("on_read");
		timePolicyCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				connectorProfile.setTimestampPolicy(timePolicyCombo.getText());
				notifyModified();
			}
		});
		createLabel(portProfileEditComposite, "");
		
		final Button detailCheck = new Button(portProfileEditComposite,
				SWT.CHECK);
		detailCheck.setText(LABEL_DETAIL);
		detailCheck.setSelection(false);
		createLabel(portProfileEditComposite, "");
		createLabel(portProfileEditComposite, "");

		detailCheck.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean selection = detailCheck.getSelection();
				if (selection && detailComposite == null) {
					disableNotify = true;
					createDetailComposite(mainComposite);
					disableNotify = false;
				}
				detailComposite.setVisible(selection);
				if (!selection) {
					// 詳細チェック解除時に、元のダイアログのサイズに戻す
					getShell().setSize(defaultDialogSize);
				} else {
					getShell().setSize(
							getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT));
				}
			}
		});

		loadData();
	}

	/**
	 * 詳細設定の表示部を作成する
	 */
	Composite createDetailComposite(Composite parent) {
		GridLayout gl;
		GridData gd;

		detailComposite = new Composite(parent, SWT.NONE);
		gl = new GridLayout(2, false);
		gd = new GridData(GridData.FILL_BOTH);
		detailComposite.setLayout(gl);
		detailComposite.setLayoutData(gd);
		detailComposite.setVisible(false);

		ob = new BufferPackage();
		createBufferComposite(detailComposite, "Buffer (Outport)", ob);

		ib = new BufferPackage();
		createBufferComposite(detailComposite, "Buffer (Inport)", ib);
		
		additionalTableViewer = createAdditionalTableViewer(detailComposite);
		
		loadDetailData();

		defaultDialogSize = getShell().getSize();
		getShell().setSize(getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT));

		return detailComposite;
	}

	/**
	 * ポートバッファ設定の表示部を作成する
	 */
	Composite createBufferComposite(Composite parent, String label,
			BufferPackage pkg) {
		GridLayout gl;
		GridData gd;

		Group composite = new Group(parent, SWT.NONE);
		gl = new GridLayout(3, false);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		composite.setLayout(gl);
		composite.setLayoutData(gd);
		composite.setText(label);

		final boolean isOutport = (pkg == ob);

		// Buffer length
		createLabel(composite, "Buffer length :");
		pkg.lengthText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		pkg.lengthText.setLayoutData(gd);
		pkg.lengthText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				BufferPackage bp = (isOutport) ? ob : ib;
				String text = bp.lengthText.getText();
				try {
					int i = Integer.parseInt(text);
					if (isOutport) {
						connectorProfile.setOutportBufferLength(i);
					} else {
						connectorProfile.setInportBufferLength(i);
					}
				} catch (Exception ex) {
					// void
				}
				notifyModified();
			}
		});
		createLabel(composite, "");

		// Buffer full policy
		createLabel(composite, "Buffer full policy :");
		pkg.fullPolicyCombo = new Combo(composite, SWT.DROP_DOWN
				| SWT.READ_ONLY);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		pkg.fullPolicyCombo.setLayoutData(gd);
		pkg.fullPolicyCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				BufferPackage bp = (isOutport) ? ob : ib;
				String value = bp.fullPolicyCombo.getText();
				if (isOutport) {
					connectorProfile.setOutportBufferFullPolicy(value);
				} else {
					connectorProfile.setInportBufferFullPolicy(value);
				}
				notifyModified();
			}
		});
		createLabel(composite, "");

		// Buffer write timeout
		createLabel(composite, "Buffer write timeout :");
		pkg.writeTimeoutText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		pkg.writeTimeoutText.setLayoutData(gd);
		pkg.writeTimeoutText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				BufferPackage bp = (isOutport) ? ob : ib;
				String text = bp.writeTimeoutText.getText();
				try {
					double d = Double.parseDouble(text);
					if (isOutport) {
						connectorProfile.setOutportBufferWriteTimeout(d);
					} else {
						connectorProfile.setInportBufferWriteTimeout(d);
					}
				} catch (Exception ex) {
					// void
				}
				notifyModified();
			}
		});
		createLabel(composite, "");

		// Buffer empty policy
		createLabel(composite, "Buffer empty policy :");
		pkg.emptyPolicyCombo = new Combo(composite, SWT.DROP_DOWN
				| SWT.READ_ONLY);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		pkg.emptyPolicyCombo.setLayoutData(gd);
		pkg.emptyPolicyCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				BufferPackage bp = (isOutport) ? ob : ib;
				String value = bp.emptyPolicyCombo.getText();
				if (isOutport) {
					connectorProfile.setOutportBufferEmptyPolicy(value);
				} else {
					connectorProfile.setInportBufferEmptyPolicy(value);
				}
				notifyModified();
			}
		});
		createLabel(composite, "");

		// Buffer read timeout
		createLabel(composite, "Buffer read timeout :");
		pkg.readTimeoutText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		pkg.readTimeoutText.setLayoutData(gd);
		pkg.readTimeoutText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				BufferPackage bp = (isOutport) ? ob : ib;
				String text = bp.readTimeoutText.getText();
				try {
					double d = Double.parseDouble(text);
					if (isOutport) {
						connectorProfile.setOutportBufferReadTimeout(d);
					} else {
						connectorProfile.setInportBufferReadTimeout(d);
					}
				} catch (Exception ex) {
					// void
				}
				notifyModified();
			}
		});
		createLabel(composite, "");

		return composite;
	}

	Label createLabel(Composite parent, String label) {
		Label l = new Label(parent, SWT.NONE);
		l.setText(label);
		return l;
	}

	boolean isOffline() {
		if (inport != null) {
			return inport.eContainer() instanceof ComponentSpecification;
		} else if (outport != null) {
			return outport.eContainer() instanceof ComponentSpecification;
		}
		return false;
	}

	/**
	 * モデル情報にアクセスし、表示に設定する
	 */
	void loadData() {
		//
		nameText.setText(connectorProfile.getName());
		//
		List<String> types = ConnectorUtil.getAllowDataTypes(outport, inport);
		boolean isAllowAny = ConnectorUtil.isAllowAnyDataType(outport, inport);
		String value = loadCombo(dataTypeCombo, types, connectorProfile
				.getDataType(), isAllowAny);
		connectorProfile.setDataType(value);

		SystemEditorPreferenceManager preference = SystemEditorPreferenceManager
				.getInstance();

		//
		if (!isOffline()) {
			types = ConnectorUtil.getAllowInterfaceTypes(outport, inport);
			isAllowAny = ConnectorUtil.isAllowAnyInterfaceType(outport, inport);
		} else {
			types = Arrays.asList(preference.getInterfaceTypes());
			isAllowAny = false;
		}
		value = loadCombo(interfaceTypeCombo, types, connectorProfile
				.getInterfaceType(), isAllowAny);
		connectorProfile.setInterfaceType(value);
		//
		if (!isOffline()) {
			types = ConnectorUtil.getAllowDataflowTypes(outport, inport);
			isAllowAny = ConnectorUtil.isAllowAnyDataflowType(outport, inport);
		} else {
			types = Arrays.asList(preference.getDataFlowTypes());
			isAllowAny = false;
		}
		value = loadCombo(dataflowTypeCombo, types, connectorProfile
				.getDataflowType(), isAllowAny);
		connectorProfile.setDataflowType(value);
		//
		if (!isOffline()) {
			types = ConnectorUtil.getAllowSubscriptionTypes(outport, inport);
			isAllowAny = ConnectorUtil.isAllowAnySubscriptionType(outport,
					inport);
		} else {
			types = Arrays.asList(preference.getSubscriptionTypes());
			isAllowAny = false;
		}
		value = loadCombo(subscriptionTypeCombo, types, connectorProfile
				.getSubscriptionType(), isAllowAny);
		connectorProfile.setSubscriptionType(value);
		//
		if (!isOffline()) {
			types = Arrays.asList(ConnectorProfile.PUSH_POLICY_TYPES);
		} else {
			types = Arrays.asList(preference.getPushPolicies());
		}
		isAllowAny = false;
		value = loadCombo(pushPolicyCombo, types, connectorProfile
				.getPushPolicy(), isAllowAny);
		connectorProfile.setPushPolicy(value);

		loadDetailData();
	}

	/**
	 * モデルの詳細設定項目を表示する
	 */
	void loadDetailData() {
		List<String> fullTypes;
		List<String> emptyTypes;
		String value;
		boolean isAllowAny = false;

		SystemEditorPreferenceManager preference = SystemEditorPreferenceManager
				.getInstance();

		if (!isOffline()) {
			fullTypes = Arrays
					.asList(ConnectorProfile.BUFFER_FULL_POLICY_TYPES);
			emptyTypes = Arrays
					.asList(ConnectorProfile.BUFFER_EMPTY_POLICY_TYPES);
		} else {
			fullTypes = Arrays.asList(preference.getBufferFullPolicies());
			emptyTypes = Arrays.asList(preference.getBufferEmptyPolicies());
		}

		if (ob != null && ob.enable) {
			//
			value = loadCombo(ob.fullPolicyCombo, fullTypes, connectorProfile
					.getOutportBufferFullPolicy(), isAllowAny);
			connectorProfile.setOutportBufferFullPolicy(value);
			//
			value = loadCombo(ob.emptyPolicyCombo, emptyTypes, connectorProfile
					.getOutportBufferEmptyPolicy(), isAllowAny);
			connectorProfile.setOutportBufferEmptyPolicy(value);
			//
			if (connectorProfile.getOutportBufferLength() == null) {
				connectorProfile.setOutportBufferLength(8);
			}
			value = connectorProfile.getOutportBufferLength().toString();
			ob.lengthText.setText(value);
			//
			if (connectorProfile.getOutportBufferWriteTimeout() == null) {
				connectorProfile.setOutportBufferWriteTimeout(1.0);
			}
			value = connectorProfile.getOutportBufferWriteTimeout().toString();
			ob.writeTimeoutText.setText(value);
			//
			if (connectorProfile.getOutportBufferReadTimeout() == null) {
				connectorProfile.setOutportBufferReadTimeout(1.0);
			}
			value = connectorProfile.getOutportBufferReadTimeout().toString();
			ob.readTimeoutText.setText(value);
		}
		//
		if (ib != null && ib.enable) {
			//
			value = loadCombo(ib.fullPolicyCombo, fullTypes, connectorProfile
					.getInportBufferFullPolicy(), isAllowAny);
			connectorProfile.setInportBufferFullPolicy(value);
			//
			value = loadCombo(ib.emptyPolicyCombo, emptyTypes, connectorProfile
					.getInportBufferEmptyPolicy(), isAllowAny);
			connectorProfile.setInportBufferEmptyPolicy(value);
			//
			if (connectorProfile.getInportBufferLength() == null) {
				connectorProfile.setInportBufferLength(8);
			}
			value = connectorProfile.getInportBufferLength().toString();
			ib.lengthText.setText(value);
			//
			if (connectorProfile.getInportBufferWriteTimeout() == null) {
				connectorProfile.setInportBufferWriteTimeout(1.0);
			}
			value = connectorProfile.getInportBufferWriteTimeout().toString();
			ib.writeTimeoutText.setText(value);
			//
			if (connectorProfile.getInportBufferReadTimeout() == null) {
				connectorProfile.setInportBufferReadTimeout(1.0);
			}
			value = connectorProfile.getInportBufferReadTimeout().toString();
			ib.readTimeoutText.setText(value);
		}
	}

	String loadCombo(Combo combo, List<String> types, String value,
			boolean isAllowAny) {
		combo.setItems(types.toArray(new String[0]));
		String def = getDefaultValue(types, value, isAllowAny);
		int index = types.indexOf(def);
		if (index != -1) {
			combo.select(index);
			return types.get(index);
		}
		return null;
	}

	/**
	 * コンボにおいて、「表示候補のリスト」と、「どのような文字列でも設定可能であるかどうか」を引数に取り、初期表示の文字列を決定する
	 * 
	 * @param candidateList
	 *            表示候補リスト
	 * @param isAllowAny
	 *            どのような文字列でも設定可能であるかどうか
	 * @return 初期表示の文字列
	 */
	private String getDefaultValue(List<String> candidateList, String value,
			boolean isAllowAny) {
		String result = null;
		if (candidateList.size() > 0) {
			if (candidateList.contains(value)) {
				result = value;
			} else {
				result = candidateList.get(0);
			}
		}
		if (result == null) {
			if (isAllowAny) {
				result = ConnectorProfile.ANY;
			}
		}
		return result;
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Connector Profile"); //$NON-NLS-1$
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void okPressed() {
		if (additionalTableViewer != null) {
			List<AdditionalEntry> additional = (List<AdditionalEntry>) additionalTableViewer
					.getInput();
			// 重複チェック
			if (checkProperties(additional) == false) {
				return;
			}
			for (AdditionalEntry target : additional) {
				connectorProfile.setProperty(target.getName(), target
						.getValue());
			}
		}
		dialogResult = connectorProfile;
		super.okPressed();
	}

	@Override
	/**
	 * メッセージを設定する。 メッセージとしてはエラーメッセージを想定しており、
	 * エラーメッセージが存在するか空文字かどうかにより、OKボタンのEnableの制御も行うように、オーバーライドした。
	 */
	public void setMessage(String newMessage, int newType) {
		super.setMessage(newMessage, newType);
		boolean isOkEnable = false;
		if (newMessage.length() == 0) {
			isOkEnable = true;
		}
		getButton(IDialogConstants.OK_ID).setEnabled(isOkEnable);
	}

	/**
	 * 設定に変更があった場合に呼び出されることを想定したメソッド。
	 * SubscriptionTypeコンボとPushInterbalのEnableを管理する。
	 * <p>
	 * 注意：設定値の変更がある場合には、必ずこのメソッドを呼び出すこと<br>
	 * 現在は、表示側で設定を変更した後に、このメソッドを必ず呼び出すように実装しているが、
	 * 項目数が増えるようならば、モデルの変更通知機能を使用して実装する方が良い。
	 */
	public void notifyModified() {
		if (disableNotify) {
			return;
		}
		if (getButton(IDialogConstants.OK_ID) != null) {
			setMessage("", IMessageProvider.NONE); //$NON-NLS-1$
		}

		if (connectorProfile.isPushIntervalAvailable()) {
			boolean isDouble = false;
			try {
				double value = Double.parseDouble(pushRateText.getText());
				if (value > 0) {
					isDouble = true;
				}
			} catch (Exception ex) {
				// void
			}

			if (!isDouble) {
				setMessage(MSG_ERROR_PUSH_RATE_NOT_NUMERIC,
						IMessageProvider.ERROR);
			}
		}

		if (connectorProfile.isSkipCountAvailable()) {
			boolean isInt = false;
			try {
				int i = Integer.parseInt(skipCountText.getText());
				if (i >= 0) {
					isInt = true;
				}
			} catch (Exception ex) {
				// void
			}
			if (!isInt) {
				setMessage(MSG_ERROR_SKIP_COUNT_NOT_INTEGER,
						IMessageProvider.ERROR);
			}
		}

		if (ob != null && ob.enable) {
			boolean isInt = false;
			try {
				int i = Integer.parseInt(ob.lengthText.getText());
				if (i >= 0) {
					isInt = true;
				}
			} catch (Exception ex) {
				// void
			}
			if (!isInt) {
				setMessage(MSG_ERROR_OUTPORT_BUFF_LENGTH_NOT_INTEGER,
						IMessageProvider.ERROR);
			}
			//
			boolean isDouble = false;
			try {
				double d = Double.parseDouble(ob.writeTimeoutText.getText());
				if (d >= 0.0) {
					isDouble = true;
				}
			} catch (Exception ex) {
				// void
			}
			if (!isDouble) {
				setMessage(MSG_ERROR_OUTPORT_WRITE_TIMEOUT_NOT_NUMERIC,
						IMessageProvider.ERROR);
			}
			//
			isDouble = false;
			try {
				double d = Double.parseDouble(ob.readTimeoutText.getText());
				if (d >= 0.0) {
					isDouble = true;
				}
			} catch (Exception ex) {
				// void
			}
			if (!isDouble) {
				setMessage(MSG_ERROR_OUTPORT_READ_TIMEOUT_NOT_NUMERIC,
						IMessageProvider.ERROR);
			}
		}

		if (ib != null && ib.enable) {
			boolean isInt = false;
			try {
				int i = Integer.parseInt(ib.lengthText.getText());
				if (i >= 0) {
					isInt = true;
				}
			} catch (Exception ex) {
				// void
			}
			if (!isInt) {
				setMessage(MSG_ERROR_INPORT_BUFF_LENGTH_NOT_INTEGER,
						IMessageProvider.ERROR);
			}
			//
			boolean isDouble = false;
			try {
				double d = Double.parseDouble(ib.writeTimeoutText.getText());
				if (d >= 0.0) {
					isDouble = true;
				}
			} catch (Exception ex) {
				// void
			}
			if (!isDouble) {
				setMessage(MSG_ERROR_INPORT_WRITE_TIMEOUT_NOT_NUMERIC,
						IMessageProvider.ERROR);
			}
			//
			isDouble = false;
			try {
				double d = Double.parseDouble(ib.readTimeoutText.getText());
				if (d >= 0.0) {
					isDouble = true;
				}
			} catch (Exception ex) {
				// void
			}
			if (!isDouble) {
				setMessage(MSG_ERROR_INPORT_READ_TIMEOUT_NOT_NUMERIC,
						IMessageProvider.ERROR);
			}
		}

		subscriptionTypeCombo.setEnabled(connectorProfile.isSubscriptionTypeAvailable());
		pushRateText.setEnabled(connectorProfile.isPushIntervalAvailable());
		pushPolicyCombo.setEnabled(connectorProfile.isPushPolicyAvailable());
		skipCountText.setEnabled(connectorProfile.isSkipCountAvailable());
	}

	@Override
	protected Point getInitialSize() {
		return getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
	}

}
