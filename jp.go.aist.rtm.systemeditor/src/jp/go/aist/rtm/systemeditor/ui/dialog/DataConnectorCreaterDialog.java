package jp.go.aist.rtm.systemeditor.ui.dialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.omg.CORBA.NamedValue;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.dialog.ConfigurationDialog.Checkbox;
import jp.go.aist.rtm.systemeditor.ui.dialog.ConfigurationDialog.OrderedList;
import jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper.ConfigurationWidget;
import jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper.NamedValueConfigurationWrapper;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.InPort;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.component.OutPort;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.util.ConnectorUtil;
import jp.go.aist.rtm.toolscommon.util.ConnectorUtil.SerializerInfo;

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
	private static final int NAME_WIDTH = 150;

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
	private Combo serializerTypeCombo;
	private Combo interfaceTypeCombo;
	private Combo dataflowTypeCombo;
	private Combo subscriptionTypeCombo;
	private Text pushRateText;
	private Combo pushPolicyCombo;
	private Text skipCountText;
	private Combo timePolicyCombo;

	private ConnectorProfile connectorProfile;
	private ConnectorProfile dialogResult;
	private OutPort outport;
	private InPort inport;
	BufferPackage ob;
	BufferPackage ib;

	TableViewer additionalTableViewer;
	boolean disableNotify;
	
	private List<PropertyElem> propertyList = new ArrayList<PropertyElem>();

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
		GridData gd;
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.grabExcessVerticalSpace = true;

		TabFolder tabFolder = new TabFolder(mainComposite, SWT.NONE);
		tabFolder.setLayoutData(gd);
		/////
		TabItem itemBasic = new TabItem(tabFolder, SWT.NONE, 0);
		itemBasic.setText(Messages.getString("DataConnectorCreaterDialog.tab.basic"));
		itemBasic.setControl(createBasicTab(tabFolder));
		/////
		TabItem itemBuffer = new TabItem(tabFolder, SWT.NONE, 1);
		itemBuffer.setText(Messages.getString("DataConnectorCreaterDialog.tab.buffer"));
		itemBuffer.setControl(createBufferTab(tabFolder));
		/////
		TabItem itemProperty = new TabItem(tabFolder, SWT.NONE, 2);
		itemProperty.setText(Messages.getString("DataConnectorCreaterDialog.tab.properties"));
		itemProperty.setControl(createPropertyTab(tabFolder));
		
		loadData();
	}

	private Composite createBasicTab(TabFolder tabFolder) {
		GridData gd;
		GridLayout gl;
		Composite portProfileEditComposite = new Composite(tabFolder,
				SWT.NONE);
		gl = new GridLayout(3, false);
		gl.marginBottom = 3;
		gl.marginHeight = 3;
		gl.marginLeft = 3;
		gl.marginRight = 3;
		gl.marginTop = 3;
		gl.marginWidth = 3;
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
		/////
		Composite portDataTypeComposite = new Composite(portProfileEditComposite,
				SWT.NONE);
		gl = new GridLayout(4, false);
		gl.marginBottom = 0;
		gl.marginHeight = 0;
		gl.marginLeft = 0;
		gl.marginRight = 5;
		gl.marginTop = 0;
		gl.marginWidth = 0;
		portDataTypeComposite.setLayout(gl);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 3;
		portDataTypeComposite.setLayoutData(gd);
		
		Label dataTypeOutLabel = new Label(portDataTypeComposite, SWT.NONE);
		dataTypeOutLabel.setText("Data Type OutPort :"); //$NON-NLS-1$
		Combo dataTypeOutCombo = new Combo(portDataTypeComposite, SWT.DROP_DOWN | SWT.READ_ONLY);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		dataTypeOutCombo.setLayoutData(gd);
		if(outport != null) {
			List<String> outportTypes = outport.getDataTypes();
			dataTypeOutCombo.setItems(outportTypes.toArray(new String[0]));
			if(0<outportTypes.size()) dataTypeOutCombo.select(0);
		}
		
		Label dataTypeInLabel = new Label(portDataTypeComposite, SWT.NONE);
		dataTypeInLabel.setText("InPort :"); //$NON-NLS-1$
		Combo dataTypeInCombo = new Combo(portDataTypeComposite, SWT.DROP_DOWN | SWT.READ_ONLY);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		dataTypeInCombo.setLayoutData(gd);
		if(inport != null) {
			List<String> inportTypes = inport.getDataTypes();
			dataTypeInCombo.setItems(inportTypes.toArray(new String[0]));
			if(0<inportTypes.size()) dataTypeInCombo.select(0);
		}
		/////
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
		
		Label serializerTypeLabel = new Label(portProfileEditComposite, SWT.NONE);
		serializerTypeLabel.setText("Serializer Type :"); //$NON-NLS-1$
		style = ConnectorUtil.isAllowAnyDataType(outport, inport) ? SWT.DROP_DOWN
				: SWT.DROP_DOWN | SWT.READ_ONLY;
		serializerTypeCombo = new Combo(portProfileEditComposite, style);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		serializerTypeCombo.setLayoutData(gd);
		serializerTypeCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				connectorProfile.setDataType(serializerTypeCombo.getText());
				notifyModified();
			}
		});
		serializerTypeCombo.addSelectionListener(new SelectionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selected = serializerTypeCombo.getSelectionIndex();
				List<SerializerInfo> serTypes = (List<SerializerInfo>)serializerTypeCombo.getData();
				connectorProfile.setDataType(serTypes.get(selected).dataType);
				if( serTypes.get(selected).useSerializer ) {
					connectorProfile.setInportSerializerType(serTypes.get(selected).inPortSerializer);
					connectorProfile.setOutportSerializerType(serTypes.get(selected).outPortSerializer);
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		Label serializerTypeFooterLabel = new Label(portProfileEditComposite,
				SWT.NONE);
		serializerTypeFooterLabel.setText(ConnectorUtil.isAllowAnyDataType(
				outport, inport) ? Messages.getString("DataConnectorCreaterDialog.2") : ""); //$NON-NLS-1$ //$NON-NLS-2$

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
		return portProfileEditComposite;
	}

	/**
	 * 詳細設定の表示部を作成する
	 */
	private Composite createBufferTab(Composite parent) {
		GridLayout gl;
		GridData gd;

		Composite detailComposite = new Composite(parent, SWT.NONE);
		gl = new GridLayout(2, false);
		gd = new GridData(GridData.FILL_BOTH);
		detailComposite.setLayout(gl);
		detailComposite.setLayoutData(gd);
		detailComposite.setVisible(false);
		/////
		ob = new BufferPackage();
		createBufferComposite(detailComposite, "Buffer (Outport)", ob);

		ib = new BufferPackage();
		createBufferComposite(detailComposite, "Buffer (Inport)", ib);
		
		loadDetailData();

		return detailComposite;
	}
	/**
	 * ポートバッファ設定の表示部を作成する
	 */
	private Composite createBufferComposite(Composite parent, String label,
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

	private class PropertyElem {
		private String name;
		private int type;
		private List<String> elemList;
		
		private List<String> selectedValue = new ArrayList<String>();
		
		private String getSelectedValue() {
			StringBuilder builder = new StringBuilder();
			
			for(String each : selectedValue) {
				if(0 < builder.length()) {
					builder.append(", ");
				}
				builder.append(each);
			}
			
			return builder.toString();
		}
	}

	private Composite createPropertyTab(Composite parent) {
		
		GridLayout gl;
		GridData gd;

		Composite propertyComposite = new Composite(parent, SWT.NONE);
		gl = new GridLayout(1, false);
		gd = new GridData(GridData.FILL_BOTH);
		propertyComposite.setLayout(gl);
		propertyComposite.setLayoutData(gd);
		propertyComposite.setVisible(true);
		/////
		parseProperty();
		if(0 < propertyList.size()) {
			ScrolledComposite scroll = new ScrolledComposite(propertyComposite, SWT.V_SCROLL);
			scroll.setLayout(new FillLayout());
			scroll.setExpandHorizontal(true);
			scroll.setExpandVertical(true);
			scroll.setLayoutData(gd);
			
			Composite proprtyComposite = new Composite(scroll, SWT.NONE);
			proprtyComposite.setLayout(gl);
			proprtyComposite.setLayoutData(gd);
			
			scroll.setMinHeight(400);
			scroll.setContent(proprtyComposite);
			
			for(PropertyElem prop : propertyList) {
				createProprtyListComposite(proprtyComposite, prop);
			}
		}
		/////
		additionalTableViewer = createAdditionalTableViewer(propertyComposite);
		
		return propertyComposite;
	}
	
	private void parseProperty() {
		propertyList.clear();
		List<NameValue> basePropList;
		Port targetPort;
		if( outport != null ) {
			basePropList = outport.getProperties();
			targetPort = inport;
		} else {
			basePropList = inport.getProperties();
			targetPort = outport;
		}
		for(NameValue each : basePropList) {
			String baseProp = each.getValue().trim();
			if(baseProp == null || baseProp.length() == 0) continue;
			
			String targetProp = "Any";
			if(targetPort!=null) {
				targetProp = targetPort.getProperty(each.getName()).trim();
				if(targetProp == null || targetProp.length() == 0) continue;
			}
			if(baseProp.equals("Any") && targetProp.equals("Any")) continue;
			
			String propName = each.getName();
			List<String> elemList = new ArrayList<String>();
			int propType;
			boolean outPAny = false;
			boolean inPAny = false;
			if(baseProp.startsWith("[") && baseProp.endsWith("]")) {
				propType = 2;
				baseProp = baseProp.substring(1, baseProp.length() -2);
			} else if(baseProp.startsWith("(") && baseProp.endsWith(")")) {
				propType = 3;
				baseProp = baseProp.substring(1, baseProp.length() -2);
			} else {
				propType = 1;
				if(baseProp.equals("Any")) outPAny = true;
			}

			if(targetProp.startsWith("[") && targetProp.endsWith("]")) {
				targetProp = targetProp.substring(1, targetProp.length() -2);
			} else if(targetProp.startsWith("(") && targetProp.endsWith(")")) {
				targetProp = targetProp.substring(1, targetProp.length() -2);
			} else {
				if(targetProp.equals("Any")) inPAny = true;
			}
			
			String[] outPropElems = baseProp.split(",");
			outPropElems = StringUtils.stripAll(outPropElems);
			String[] inPropElems = targetProp.split(",");
			inPropElems = StringUtils.stripAll(inPropElems);
			if(outPAny) {
				elemList = Arrays.asList(inPropElems);
			} else if(inPAny) {
				elemList = Arrays.asList(outPropElems);
			} else {
				List<String> outElemList = Arrays.asList(outPropElems);
				List<String> inElemList = Arrays.asList(inPropElems);
				for(String elem : outElemList) {
					if(inElemList.contains(elem)) {
						elemList.add(elem);
					}
				}
			}
			if(elemList.size() == 0) continue; 
			
			PropertyElem propElem = new PropertyElem();
			propElem.name = propName;
			propElem.type = propType;
			propElem.elemList = elemList;
			propertyList.add(propElem);
		}
	}
	
	private void createProprtyListComposite(Composite parent, PropertyElem prop) {
		GridLayout gl;
		gl = new GridLayout(2, false);
		gl.marginTop = 2;
		gl.marginBottom = 4;

		GridData gd;
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;

		Group namedValueGroup = new Group(parent, SWT.NONE);
		namedValueGroup.setLayout(gl);
		namedValueGroup.setLayoutData(gd);

		gd = new GridData();
		gd.widthHint = NAME_WIDTH;

		Label keyLabel = new Label(namedValueGroup, SWT.WRAP | SWT.CENTER);
		keyLabel.setText(prop.name);
		keyLabel.setLayoutData(gd);

		gl = new GridLayout(1, false);
		gl.marginHeight = 0;
		gl.marginWidth = 0;

		Composite proprtyComposite = new Composite(namedValueGroup, SWT.NONE);
		proprtyComposite.setLayout(gl);
		proprtyComposite.setLayoutData(createGridData());

		createProprtyComposite(proprtyComposite, prop);
	}
	
	private void createProprtyComposite(final Composite parent, PropertyElem prop) {

		if (prop.type == 1) {
			// widget種別がcomboboxの場合
			Combo valueCombo = createCombo(parent);
			valueCombo.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					prop.selectedValue.clear();
					prop.selectedValue.add(valueCombo.getText());
				}
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
				}
			});
			valueCombo.add("");
			for (String s : prop.elemList) {
				valueCombo.add(s);
			}
			
		} else if(prop.type == 2) {
			// widget種別がCheckBoxの場合
			Group valueCheckBoxGroup = createGroup(parent);
			SelectionListener sl = createCheckBoxSelectionListner(prop);
			for (String s : prop.elemList) {
				Button vb = createButton(valueCheckBoxGroup, SWT.CHECK);
				vb.setText(s);
				vb.addSelectionListener(sl);
			}
			
		} else if(prop.type == 3) {
			// widget種別がradioの場合
			Group valueRadioGroup = createGroup(parent);
			SelectionListener sl = createRadioSelectionListner(prop);
			for (String s : prop.elemList) {
				Button vb = createButton(valueRadioGroup, SWT.RADIO);
				vb.setText(s);
				vb.addSelectionListener(sl);
			}
		}
	}
	
	private GridData createGridData() {
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		return gd;
	}
	
	private Group createGroup(final Composite parent) {
		Group group = new Group(parent, SWT.NONE);
		
		GridLayout gl = new GridLayout(3, false);
		gl.marginHeight = 0;
		gl.marginBottom = 5;
		group.setLayout(gl);
		
		group.setLayoutData(createGridData());
		
		return group;
	}
	private Button createButton(final Composite parent, int style) {
		Button button = new Button(parent, style);
		
		button.setLayoutData(createGridData());
		
		return button;
	}
	private Combo createCombo(final Composite parent) {
		Combo result = new Combo(parent, SWT.READ_ONLY);
		
		result.setLayoutData(createGridData());
		
		return result;
	}
	private Label createLabel(Composite parent, String label) {
		Label l = new Label(parent, SWT.NONE);
		l.setText(label);
		return l;
	}

	private SelectionListener createRadioSelectionListner(PropertyElem prop) {
		return new SelectionListener() {
			PropertyElem wd = prop;

			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent e) {
				Button b = (Button) e.widget;
				if (b.getSelection()) {
					String value = b.getText();
					wd.selectedValue.clear();
					wd.selectedValue.add(value);
				}
			}
		};
	}
	
	private SelectionListener createCheckBoxSelectionListner(PropertyElem prop) {
		return new SelectionListener() {
			PropertyElem wd = prop;

			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent e) {
				Button b = (Button) e.widget;
				String value = b.getText();
				if (b.getSelection()) {
					if( wd.selectedValue.contains(value)==false) {
						wd.selectedValue.add(value);
					}
				} else {
					wd.selectedValue.remove(value);
				}
			}
		};
	}
	
	private boolean isOffline() {
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
		List<SerializerInfo> serTypes = ConnectorUtil.getAllowDataTypes(outport, inport);
		boolean isAllowAny = ConnectorUtil.isAllowAnyDataType(outport, inport);
		int selected = 0;
		String outSer = connectorProfile.getOutportSerializerType();
		String inSer = connectorProfile.getInportSerializerType();
		serializerTypeCombo.removeAll();
		for(SerializerInfo each : serTypes) {
			serializerTypeCombo.add(each.toString());
			if(each.outPortSerializer.equals(outSer) && each.inPortSerializer.equals(inSer)) {
				selected = serializerTypeCombo.getItemCount() - 1;
			}
		}
		serializerTypeCombo.select(selected);
		connectorProfile.setDataType(serTypes.get(selected).dataType);
		if( serTypes.get(selected).useSerializer ) {
			connectorProfile.setInportSerializerType(serTypes.get(selected).inPortSerializer);
			connectorProfile.setOutportSerializerType(serTypes.get(selected).outPortSerializer);
		}
		serializerTypeCombo.setData(serTypes);

		SystemEditorPreferenceManager preference = SystemEditorPreferenceManager
				.getInstance();
		//
		List<String> types;
		if (!isOffline()) {
			types = ConnectorUtil.getAllowInterfaceTypes(outport, inport);
			isAllowAny = ConnectorUtil.isAllowAnyInterfaceType(outport, inport);
		} else {
			types = Arrays.asList(preference.getInterfaceTypes());
			isAllowAny = false;
		}
		String value = loadCombo(interfaceTypeCombo, types, connectorProfile
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
		String defaultVal = connectorProfile.getSubscriptionType();
		if(defaultVal==null || defaultVal.length()==0) {
			defaultVal = "flush";
		}
		value = loadCombo(subscriptionTypeCombo, types, defaultVal, isAllowAny);
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
		this.setHelpAvailable(false);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void okPressed() {
		for(PropertyElem each : propertyList) {
			String selected = each.getSelectedValue();
			if(selected != null && 0 < selected.length()) {
				connectorProfile.setProperty(each.name, each.getSelectedValue());
			}
		}
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

		if (ob != null && ob.enable && 0 < ob.lengthText.getText().length()) {
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
			if(0 < ib.lengthText.getText().length()) {
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
			}
			//
			isDouble = false;
			if(0 < ob.readTimeoutText.getText().length()) {
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
		}

		if (ib != null && ib.enable && 0 < ib.lengthText.getText().length()) {
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
			if(0 < ib.writeTimeoutText.getText().length()) {
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
			}
			//
			isDouble = false;
			if(0 < ib.readTimeoutText.getText().length()) {
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
