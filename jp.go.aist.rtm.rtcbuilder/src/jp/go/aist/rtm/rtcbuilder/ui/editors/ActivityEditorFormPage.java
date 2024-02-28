package jp.go.aist.rtm.rtcbuilder.ui.editors;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.util.StringUtil;
import jp.go.aist.rtm.rtcbuilder.util.RTCUtil;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

/**
 * アクティビティページ
 */
public class ActivityEditorFormPage extends AbstractEditorFormPage {

	private static final String ACTIVITY_INITIALIZE = "onInitialize";
	private static final String ACTIVITY_FINALIZE = "onFinalize";
	private static final String ACTIVITY_STARTUP = "onStartup";
	private static final String ACTIVITY_SHUTDOWN = "onShutdown";
	private static final String ACTIVITY_ACTIVATED = "onActivated";
	private static final String ACTIVITY_DEACTIVATED = "onDeactivated";
	private static final String ACTIVITY_ABORTING = "onAborting";
	private static final String ACTIVITY_ERROR = "onError";
	private static final String ACTIVITY_RESET = "onReset";
	private static final String ACTIVITY_EXECUTE = "onExecute";
	private static final String ACTIVITY_STATE_UPDATE = "onStateUpdate";
	private static final String ACTIVITY_RATE_CHANGED = "onRateChanged";

	private List<Label> implChk;
	private Text actionNameText;
	private Button onBtn;
	private Button offBtn;
	private Text activityText;
	private Text preConditionText;
	private Text postConditionText;
	//
	private int preSelection;

	/**
	 * コンストラクタ
	 *
	 * @param editor
	 *            親のエディタ
	 */
	public ActivityEditorFormPage(RtcBuilderEditor editor) {
		super(editor, "id", IMessageConstants.ACTIVITY_SECTION);
		//
		preSelection = -1;
		implChk = new ArrayList<Label>();
	}

	/**
	 * {@inheritDoc}
	 */
	protected void createFormContent(IManagedForm managedForm) {
		ScrolledForm form = super.createBase(managedForm, IMessageConstants.ACTIVITY_SECTION);
		FormToolkit toolkit = managedForm.getToolkit();
		createActivitySection(managedForm.getToolkit(), form);
		createHintSection(toolkit, form);
		createDocumentSection(managedForm.getToolkit(), form);

		// 言語・環境ページより先にこのページが表示された場合、ここで言語を判断する
		editor.setEnabledInfoByLang();

		load();
	}

	private void createActivitySection(FormToolkit toolkit, ScrolledForm form) {
		Composite composite = createSectionBaseWithLabel(toolkit, form,
				IMessageConstants.ACTIVITY_ACTIVITY_TITLE, IMessageConstants.ACTIVITY_ACTIVITY_EXPL, 3);
		//
		createInitFinalSection(toolkit, composite);
		createStartShutSection(toolkit, composite);
		createAliveSection(toolkit, composite);
		createDataFlowSection(toolkit, composite);
	}

	private void createDataFlowSection(FormToolkit toolkit, Composite parent) {
		createSectionTitle(toolkit, parent, IMessageConstants.ACTIVITY_LBL_DATAFLOW);
		createActionSelection(parent,ACTIVITY_EXECUTE);
		createActionSelection(parent, ACTIVITY_STATE_UPDATE);
		createActionSelection(parent, ACTIVITY_RATE_CHANGED);
	}

	private void createAliveSection(FormToolkit toolkit, Composite parent) {
		createSectionTitle(toolkit, parent, IMessageConstants.ACTIVITY_LBL_ALIVE);
		createActionSelection(parent, ACTIVITY_ACTIVATED);
		createActionSelection(parent, ACTIVITY_DEACTIVATED);
		createActionSelection(parent, ACTIVITY_ABORTING);
		createActionSelection(parent, ACTIVITY_ERROR);
		createActionSelection(parent, ACTIVITY_RESET);
	}

	private void createStartShutSection(FormToolkit toolkit, Composite parent) {
		createSectionTitle(toolkit, parent, IMessageConstants.ACTIVITY_LBL_START_END);
		createActionSelection(parent, ACTIVITY_STARTUP);
		createActionSelection(parent, ACTIVITY_SHUTDOWN);
	}

	private void createInitFinalSection(FormToolkit toolkit, Composite parent) {
		createSectionTitle(toolkit, parent, IMessageConstants.ACTIVITY_LBL_INIT_FINAL);
		createActionSelection(parent, ACTIVITY_INITIALIZE);
		createActionSelection(parent, ACTIVITY_FINALIZE);
	}

	private void createSectionTitle(FormToolkit toolkit, Composite parent, String title) {
		Composite base = toolkit.createComposite(parent, SWT.NULL);
		GridLayout gl = new GridLayout(1, false);
		base.setLayout(gl);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		base.setLayoutData(gd);
		base.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		//
		Label titleLabel = toolkit.createLabel(base, title);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalAlignment = GridData.CENTER;
		titleLabel.setLayoutData(gd);
		titleLabel.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
	}

	private void createActionSelection(Composite composite, String actionName) {
		Label impl = new Label(composite, SWT.CHECK);
		impl.setText(actionName);
		impl.addMouseListener(new MouseListener() {
			@Override
			public void mouseDown(MouseEvent e) {
				RtcParam rtcParam = editor.getRtcParam();
				if (preSelection >= 0) {
					rtcParam.setActionImplemented(preSelection, onBtn.getSelection());
					rtcParam.setDocActionOverView(preSelection, StringUtil.getDocText(activityText.getText()));
					rtcParam.setDocActionPreCondition(preSelection, StringUtil.getDocText(preConditionText.getText()));
					rtcParam.setDocActionPostCondition(preSelection, StringUtil.getDocText(postConditionText.getText()));
				}
				int index = implChk.indexOf(e.getSource());
				//onInitializeは常に有効
				if(index==IRtcBuilderConstants.ACTIVITY_INITIALIZE) {
					onBtn.setSelection(true);
					offBtn.setSelection(false);
					onBtn.setEnabled(false);
					offBtn.setEnabled(false);
				} else {
					if( rtcParam.getActionImplemented(index) ) {
						onBtn.setSelection(true);
						offBtn.setSelection(false);
					} else {
						onBtn.setSelection(false);
						offBtn.setSelection(true);
					}
					onBtn.setEnabled(true);
					offBtn.setEnabled(true);
				}
				actionNameText.setText(IRtcBuilderConstants.ACTION_TYPE_ITEMS[index]);
				activityText.setText(StringUtil.getDisplayDocText(rtcParam.getDocActionOverView(index)));
				preConditionText.setText(StringUtil.getDisplayDocText(rtcParam.getDocActionPreCondition(index)));
				postConditionText.setText(StringUtil.getDisplayDocText(rtcParam.getDocActionPostCondition(index)));
				preSelection = index;
				//
				for(Label target : implChk) {
					target.setForeground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_BLACK));
				}
				implChk.get(index).setForeground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_RED));
				update();
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				RtcParam rtcParam = editor.getRtcParam();
				int index = implChk.indexOf(e.getSource());
				if(index==IRtcBuilderConstants.ACTIVITY_INITIALIZE) {
					onBtn.setSelection(true);
					offBtn.setSelection(false);
					onBtn.setEnabled(false);
					offBtn.setEnabled(false);
				} else {
					rtcParam.setActionImplemented(index, !rtcParam.getActionImplemented(index));
					if( rtcParam.getActionImplemented(index) ) {
						onBtn.setSelection(true);
						offBtn.setSelection(false);
						implChk.get(index).setBackground(new Color(PlatformUI.getWorkbench().getDisplay(), RTCUtil.defaultRGBMap.get(RTCUtil.COLOR_COMPONENT)));
					} else {
						onBtn.setSelection(false);
						offBtn.setSelection(true);
						implChk.get(index).setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WHITE));
					}
					onBtn.setEnabled(true);
					offBtn.setEnabled(true);
				}
				update();
			}

			@Override
			public void mouseUp(MouseEvent e) {
			}
		});
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		impl.setLayoutData(gridData);
		impl.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WHITE));
		implChk.add(impl);
	}

	private void createHintSection(FormToolkit toolkit, ScrolledForm form) {
		Composite composite = createHintSectionBase(toolkit, form, 12);
		//
		createHintLabel(ACTIVITY_INITIALIZE, IMessageConstants.ACTIVITY_HINT_ONINITIALIZE_DESC, toolkit, composite);
		createHintLabel(ACTIVITY_FINALIZE, IMessageConstants.ACTIVITY_HINT_ONFINALIZE_DESC, toolkit, composite);
		createHintLabel(ACTIVITY_STARTUP, IMessageConstants.ACTIVITY_HINT_ONSTARTUP_DESC, toolkit, composite);
		createHintLabel(ACTIVITY_SHUTDOWN, IMessageConstants.ACTIVITY_HINT_ONSHUTDOWN_DESC, toolkit, composite);
		createHintLabel(ACTIVITY_ACTIVATED, IMessageConstants.ACTIVITY_HINT_ONACTIVATED_DESC, toolkit, composite);
		createHintLabel(ACTIVITY_DEACTIVATED, IMessageConstants.ACTIVITY_HINT_ONDEACTIVATED_DESC, toolkit, composite);
		createHintLabel(ACTIVITY_ABORTING, IMessageConstants.ACTIVITY_HINT_ONABORTING_DESC, toolkit, composite);
		createHintLabel(ACTIVITY_ERROR, IMessageConstants.ACTIVITY_HINT_ONERROR_DESC, toolkit, composite);
		createHintLabel(ACTIVITY_RESET, IMessageConstants.ACTIVITY_HINT_ONRESET_DESC, toolkit, composite);
		createHintLabel(ACTIVITY_EXECUTE, IMessageConstants.ACTIVITY_HINT_ONEXECUTE_DESC, toolkit, composite);
		createHintLabel(ACTIVITY_STATE_UPDATE, IMessageConstants.ACTIVITY_HINT_ONSTATEUPDATE_DESC, toolkit, composite);
		createHintLabel(ACTIVITY_RATE_CHANGED, IMessageConstants.ACTIVITY_HINT_ONRATECHANGED_DESC, toolkit, composite);
		//
		createHintSpace(toolkit, composite);
		//
		createHintLabel(IMessageConstants.ACTIVITY_HINT_DESCRIPTION_TITLE, IMessageConstants.ACTIVITY_HINT_DESCRIPTION_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.ACTIVITY_HINT_PRECONDITION_TITLE, IMessageConstants.ACTIVITY_HINT_PRECONDITION_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.ACTIVITY_HINT_POSTCONDITION_TITLE, IMessageConstants.ACTIVITY_HINT_POSTCONDITION_DESC, toolkit, composite);
	}

	private void createDocumentSection(FormToolkit toolkit, ScrolledForm form) {
		Composite composite = createSectionBaseWithLabel(toolkit, form,
				"Documentation", IMessageConstants.ACTIVITY_DOCUMENT_EXPL, 3);

		actionNameText = createLabelAndText(toolkit, composite,
				IMessageConstants.ACTIVITY_LBL_ACTIVITYNAME, SWT.BORDER);
		actionNameText.setEditable(false);
		actionNameText.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = 50;
		//
		Group compGroup = new Group(composite, SWT.NONE);
		compGroup.setLayout(new GridLayout(2, false));
		GridData gd = new GridData();
		compGroup.setLayoutData(gd);
		onBtn = createRadioCheckButton(toolkit, compGroup, "ON", SWT.RADIO);
		onBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(preSelection>=0) {
					implChk.get(preSelection).setBackground(new Color(PlatformUI.getWorkbench().getDisplay(), RTCUtil.defaultRGBMap.get(RTCUtil.COLOR_COMPONENT)));
				}
			}
		});
		offBtn = createRadioCheckButton(toolkit, compGroup, "OFF", SWT.RADIO);
		offBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(preSelection>=0) {
					implChk.get(preSelection).setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WHITE));
				}
			}
		});
		//
		activityText = createLabelAndText(toolkit, composite,
				IMessageConstants.ACTIVITY_LBL_DESCRIPTION, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
		gridData.horizontalSpan = 2;
		activityText.setLayoutData(gridData);
		preConditionText = createLabelAndText(toolkit, composite,
				IMessageConstants.ACTIVITY_LBL_PRECONDITION, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
		preConditionText.setLayoutData(gridData);
		postConditionText = createLabelAndText(toolkit, composite,
				IMessageConstants.ACTIVITY_LBL_POSTCONDITION, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
		postConditionText.setLayoutData(gridData);
	}

	public void update() {
		RtcParam rtcParam = editor.getRtcParam();

		if (preSelection >= 0) {
			rtcParam.setActionImplemented(preSelection, onBtn.getSelection());
			rtcParam.setDocActionOverView(preSelection, StringUtil.getDocText(activityText.getText()));
			rtcParam.setDocActionPreCondition(preSelection, StringUtil.getDocText(preConditionText.getText()));
			rtcParam.setDocActionPostCondition(preSelection, StringUtil.getDocText(postConditionText.getText()));
		}

		editor.updateDirty();
	}

	/**
	 * データをロードする
	 */
	public void load() {
		RtcParam rtcParam = editor.getRtcParam();

		if( activityText != null ) {
			for( int intidx=IRtcBuilderConstants.ACTIVITY_INITIALIZE; intidx<IRtcBuilderConstants.ACTIVITY_DUMMY; intidx++) {
				if( rtcParam.getActionImplemented(intidx) ) {
					implChk.get(intidx).setBackground(new Color(PlatformUI.getWorkbench().getDisplay(), RTCUtil.defaultRGBMap.get(RTCUtil.COLOR_COMPONENT)));
				} else {
					implChk.get(intidx).setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WHITE));
				}
			}
		}
	}

	public String validateParam() {
		//入力パラメータチェックなし
		return null;
	}

	/**
	 * Activityフォーム内の要素の有効/無効を設定します。
	 * <ul>
	 * <li>activity.action.onInitialize : Activityセクションの on_initialize</li>
	 * <li>activity.action.onFinalize : Activityセクションの on_finalize</li>
	 * <li>activity.action.onStartup : Activityセクションの on_startup</li>
	 * <li>activity.action.onShutdown : Activityセクションの on_shutdown</li>
	 * <li>activity.action.onActivated : Activityセクションの on_activated</li>
	 * <li>activity.action.onDeactivated : Activityセクションの on_deactivated</li>
	 * <li>activity.action.onAborting : Activityセクションの on_aborting</li>
	 * <li>activity.action.onError : Activityセクションの on_error</li>
	 * <li>activity.action.onReset : Activityセクションの on_reset</li>
	 * <li>activity.action.onExecute : Activityセクションの on_execute</li>
	 * <li>activity.action.onStateUpdate : Activityセクションの on_state_update</li>
	 * <li>activity.action.onRateChanged : Activityセクションの on_rate_changed</li>
	 * <li>activity.action.onAction : Activityセクションの on_action</li>
	 * <li>activity.action.onModeChanged : Activityセクションの on_mode_changed</li>
	 * </ul>
	 */
	public void setEnabledInfo(WidgetInfo widgetInfo, boolean enabled) {
		if (widgetInfo.matchSection("action")) {
			if (implChk != null && !implChk.isEmpty()) {
				int index = -1;
				if (widgetInfo.matchWidget("onInitialize"))  index = IRtcBuilderConstants.ACTIVITY_INITIALIZE;
				if (widgetInfo.matchWidget("onFinalize"))    index = IRtcBuilderConstants.ACTIVITY_FINALIZE;
				if (widgetInfo.matchWidget("onStartup"))     index = IRtcBuilderConstants.ACTIVITY_STARTUP;
				if (widgetInfo.matchWidget("onShutdown"))    index = IRtcBuilderConstants.ACTIVITY_SHUTDOWN;
				if (widgetInfo.matchWidget("onActivated"))   index = IRtcBuilderConstants.ACTIVITY_ACTIVATED;
				if (widgetInfo.matchWidget("onDeactivated")) index = IRtcBuilderConstants.ACTIVITY_DEACTIVATED;
				if (widgetInfo.matchWidget("onAborting"))    index = IRtcBuilderConstants.ACTIVITY_ABORTING;
				if (widgetInfo.matchWidget("onError"))       index = IRtcBuilderConstants.ACTIVITY_ERROR;
				if (widgetInfo.matchWidget("onReset"))       index = IRtcBuilderConstants.ACTIVITY_RESET;
				if (widgetInfo.matchWidget("onExecute"))     index = IRtcBuilderConstants.ACTIVITY_EXECUTE;
				if (widgetInfo.matchWidget("onStateUpdate")) index = IRtcBuilderConstants.ACTIVITY_STATE_UPDATE;
				if (widgetInfo.matchWidget("onRateChanged")) index = IRtcBuilderConstants.ACTIVITY_RATE_CHANGED;
				if(index>=IRtcBuilderConstants.ACTIVITY_INITIALIZE && index<=IRtcBuilderConstants.ACTIVITY_RATE_CHANGED) {
					setControlEnabled(implChk.get(index), enabled);
				}
			}
		}
	}

}
