package jp.go.aist.rtm.systemeditor.ui.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.views.configurationview.ConfigurationView;
import jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper.ComponentConfigurationWrapper;
import jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper.ConfigurationCondition;
import jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper.ConfigurationSetConfigurationWrapper;
import jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper.ConfigurationWidget;
import jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper.NamedValueConfigurationWrapper;

/**
 * RTコンポーネントのコンフィグデータ編集ダイアログ
 *
 */
public class ConfigurationDialog extends TitleAreaDialog {

	private static final int NAME_WIDTH = 150;

	private static final String NORMAL_COLOR = "NORMAL_COLOR"; // @jve:decl-index=0: //$NON-NLS-1$
	private static final String MODIFY_COLOR = "MODIFY_COLOR"; // @jve:decl-index=0: //$NON-NLS-1$

	private static final String CANT_MODIFY_COLOR = "CANT_MODIFY_COLOR"; // @jve:decl-index=0: //$NON-NLS-1$

	private static final String ERROR_COLOR = "ERROR_COLOR"; //$NON-NLS-1$

	private static ColorRegistry colorRegistry = null;

	/** 編集用にコピーしたComponentConfiguration */
	ComponentConfigurationWrapper copiedConfig;

	/** 現在タブで開いているConfigurationSet */
	ConfigurationSetConfigurationWrapper selectedConfigSet;

	private TabFolder tabFolder;

	private boolean isValueModified;

	private boolean isApply;

	private ConfigurationView view;
	
	private boolean firstApply;

	private TabItem currentTabItem;

	private List<String> tabbedIdList;

	Text errorText;

	public ConfigurationDialog(ConfigurationView view) {
		super(view.getSite().getShell());
		setShellStyle(getShellStyle() | SWT.RESIZE);
		if (colorRegistry == null) {
			colorRegistry = new ColorRegistry();
			colorRegistry.put(NORMAL_COLOR, new RGB(255, 255, 255));
			colorRegistry.put(MODIFY_COLOR, new RGB(255, 192, 192));
			colorRegistry.put(CANT_MODIFY_COLOR, new RGB(198, 198, 198));
			colorRegistry.put(ERROR_COLOR, new RGB(255, 0, 0));
		}
		this.isValueModified = false;
		this.isApply = true;
		this.copiedConfig = view.getComponentConfig();
		this.view = view;
		this.firstApply = true;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite mainComposite = (Composite) super.createDialogArea(parent);

		GridLayout gl;
		GridData gd;
		gl = new GridLayout();
		mainComposite.setLayout(gl);
		mainComposite.setFont(parent.getFont());

		createTabFolder(mainComposite);

		// 制約エラー表示領域を追加 2009.12.09
		gd = new GridData();
		errorText = new Text(mainComposite, SWT.MULTI | SWT.V_SCROLL);
		errorText.setEditable(false);
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.heightHint = 50;
		errorText.setLayoutData(gd);
		//Check Constraint
		checkConstraint(false);

		gd = new GridData();
		gd.horizontalAlignment = GridData.END;
		gd.grabExcessHorizontalSpace = true;

		Button applyCheckBox = new Button(mainComposite, SWT.CHECK);
		applyCheckBox.setLayoutData(gd);
		applyCheckBox.setText("Apply"); //$NON-NLS-1$
		applyCheckBox.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent e) {
				Button source = (Button) e.getSource();
				isApply = source.getSelection();
				if (!isApply) return;
				if (firstApply) firstApply = false;
				if (firstApply) {
					isApply = false;
					source.setSelection(false);
					return;
				}
				
				if(checkConstraint(false)==false) return;
				if (saveData()) {
					view.applyConfiguration(false);
					refreshTabItem();
				}
			}
		});
		applyCheckBox.setSelection(isApply);
		
		return mainComposite;
	}

	private boolean checkConstraint(boolean onlyUpdated) {
		List<String> validateErrors = new ArrayList<String>();
		for (NamedValueConfigurationWrapper nv : selectedConfigSet.getNamedValueList()) {
			List<String> result = nv.checkConstraints(selectedConfigSet.getId(), onlyUpdated);
			validateErrors.addAll(result);
		}
		if (validateErrors.size() > 0) {
			String msg = "";
			for (String s : validateErrors) {
				msg += "- " + s + "\n";
			}
			errorText.setText(Messages.getString("ConfigurationDialog.21") + msg);
			return false;
		} else {
			errorText.setText("");
			return true;
		}
	}
	
	protected void refreshTabItem() {
		if (currentTabItem == null) return;
		if (selectedConfigSet == null) return;
		
		// コントロールを全部作りかえない	2009.12.04
//		currentTabItem.setControl(createConfigSetComposite(selectedConfigSet));
		
		// ウィジェットを編集中の状態に戻す 2009.12.04
		for (NamedValueConfigurationWrapper nv : selectedConfigSet.getNamedValueList()) {
			nv.loadWidgetValue();
		}
		
		// 修正済の背景色のコントロールを元に戻す 2009.12.04
		resetBackground(currentTabItem.getControl());
	}

	// 再帰的にControlの背景色を修正済から元に戻す 2009.12.04
	private void resetBackground(Control content) {
		if (content instanceof Composite){
			for (Control child : ((Composite)content).getChildren()) {
				resetBackground(child);
			}
		}
		if (content.getBackground().equals(colorRegistry.get(MODIFY_COLOR))){
			content.setBackground(colorRegistry.get(NORMAL_COLOR));
		}
	}

	private void createTabFolder(Composite mainComposite) {
		if (this.copiedConfig == null) {
			return;
		}

		GridData gd;
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.grabExcessVerticalSpace = true;

		tabFolder = new TabFolder(mainComposite, SWT.NONE);
		tabFolder.setLayoutData(gd);
		this.tabbedIdList = new ArrayList<>();

		for (ConfigurationSetConfigurationWrapper cs : this.copiedConfig.getConfigurationSetList()) {
			if (this.copiedConfig.getActiveConfigSet() != null
					&& this.copiedConfig.getActiveConfigSet().getId().equals(cs.getId())) {
				// Active ConfigSetは先頭タブへ追加
				TabItem item = new TabItem(tabFolder, SWT.NONE, 0);
				item.setText(cs.getId());
				this.tabbedIdList.add(0, cs.getId());
				continue;
			}
			if (!cs.isSecret()) {
				// 隠しConfigSetをタブへ追加
				TabItem item = new TabItem(tabFolder, SWT.NONE);
				item.setText(cs.getId());
				this.tabbedIdList.add(cs.getId());
			}
		}

		if (tabFolder.getItemCount() > 0) {
			// 先頭タブのActive ConfigSetを選択
			this.tabFolder.setSelection(0);
			this.selectConfigSet(0);
		}

		tabFolder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				selectConfigSet();
			}
		});
	}

	/** ConfigurationSet単位のCompositeを作成 */
	private Control createConfigSetComposite(
			ConfigurationSetConfigurationWrapper configSet) {
		GridLayout gl;
		gl = new GridLayout(2, false);

		GridData gd;
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;

		// スクロール設定
		ScrolledComposite scroll = new ScrolledComposite(tabFolder, SWT.V_SCROLL);
		scroll.setLayout(new FillLayout());
		scroll.setExpandHorizontal(true);
		scroll.setExpandVertical(true);

		Composite configSetComposite = new Composite(scroll, SWT.NONE);
		configSetComposite.setLayout(gl);
		configSetComposite.setLayoutData(gd);

		scroll.setContent(configSetComposite);

		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;

		Label nameLabel = new Label(configSetComposite, SWT.NONE);
		nameLabel.setText("ConfigurationSet :"); //$NON-NLS-1$
		Label nameText = new Label(configSetComposite, SWT.SINGLE);
		nameText.setLayoutData(gd);
		nameText.setText(configSet.getId());

		for (NamedValueConfigurationWrapper nv : configSet.getNamedValueList()) {
			if (!nv.isSecret()) {
				createNamedValueComposite(configSetComposite, nv);
			}
		}
		if (this.view.isCheckedDetailNamedValue()) {
			// NameValueの詳細表示が有効な場合は、隠しNameValueも追加
			for (NamedValueConfigurationWrapper nv : configSet.getNamedValueList()) {
				if (nv.isSecret()) {
					createNamedValueComposite(configSetComposite, nv);
				}
			}
		}
		
		// スクロールの初期サイズ
		scroll.setMinHeight(configSetComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y + 50);

		return scroll;
	}

	/** NamedValue単位のCompositeを作成 */
	private void createNamedValueComposite(Composite parent,
			final NamedValueConfigurationWrapper namedValue) {
		GridLayout gl;
		gl = new GridLayout(2, false);
		gl.marginTop = 2;
		gl.marginBottom = 4;

		GridData gd;
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.horizontalSpan = 2;
		gd.grabExcessHorizontalSpace = true;

		Group namedValueGroup = new Group(parent, SWT.NONE);
		namedValueGroup.setLayout(gl);
		namedValueGroup.setLayoutData(gd);

		gd = new GridData();
		gd.widthHint = NAME_WIDTH;

		Label keyLabel = new Label(namedValueGroup, SWT.WRAP | SWT.CENTER);
		keyLabel.setText(namedValue.getKey());
		keyLabel.setLayoutData(gd);

		gl = new GridLayout(1, false);
		gl.marginHeight = 0;
		gl.marginWidth = 0;

		Composite valueComposite = new Composite(namedValueGroup, SWT.NONE);
		valueComposite.setLayout(gl);
		valueComposite.setLayoutData(createGridData());

		namedValue.loadWidgetValue();
		if (namedValue.widgetKeySet().size() > 0) {
			// ハッシュの場合
			for (String key : new TreeSet<String>(namedValue.widgetKeySet())) {
				ConfigurationWidget widget = namedValue.widget(key);
				createValueComposite(valueComposite, key, widget);
			}
		} else {
			// 配列、単体
			for (int i = 0; i < namedValue.widgetSize(); i++) {
				ConfigurationWidget widget = namedValue.widget(i);
				createValueComposite(valueComposite, null, widget);
			}
		}
	}

	private GridData createGridData() {
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		return gd;
	}
	
	private Composite createComposite(final Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		
		GridLayout gl = new GridLayout(1, false);
		gl.marginHeight = 0;
		gl.marginWidth = 0;
		composite.setLayout(gl);
		
		composite.setLayoutData(createGridData());
		
		return composite;
	}
	
	private Label createLabel(final Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		
		label.setLayoutData(createGridData());
		
		return label;
	}
	
	private Text createText(final Composite parent) {
		Text text = new Text(parent, SWT.SINGLE
				| SWT.BORDER);
		
		text.setLayoutData(createGridData());
		
		return text;
	}

	private Slider createSlider(final Composite parent) {
		Slider slider = new Slider(parent, SWT.BORDER);
		
		slider.setLayoutData(createGridData());
		
		return slider;
	}

	private Spinner createSpinner(final Composite parent) {
		Spinner spinner = new Spinner(parent, SWT.BORDER);
		
		spinner.setLayoutData(createGridData());
		
		return spinner;
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

	private Button createButton(final Composite parent) {
		Button button = new Button(parent, SWT.RADIO);
		
		button.setLayoutData(createGridData());
		
		return button;
	}

	/** NamedValue単位のCompositeを作成 */
	private void createValueComposite(final Composite parent, final String key,
			final ConfigurationWidget widget) {

		if (widget != null && widget.isSlider()) {
			// widget種別がsliderの場合
			Composite valueComposite = createComposite(parent);

			createKeyLabel(key, valueComposite);

			final Text valueSliderText = createText(valueComposite);
			final Slider valueSlider = createSlider(valueComposite);

			valueSlider.setMinimum(0);
			valueSlider.setMaximum(widget.getSliderMaxStep() + 10);
			valueSlider.setIncrement(1);
			
			// slider、textに初期値設定(リスナ登録前)
			try {
				// 値を制約範囲内のステップに換算
				int step = widget.getCondition().getStepByValue(
						widget.getValue(), widget);
				valueSlider.setSelection(step);
			} catch (NumberFormatException e) {
				valueSlider.setSelection(0);
			}
			valueSliderText.setText(widget.getValue());
			if (widget.isValueModified()) {
				valueSliderText.setBackground(colorRegistry.get(MODIFY_COLOR));
			}
			String value = valueSliderText.getText();
			ConfigurationCondition condition = widget.getCondition();
			if (!condition.validate(value)) {
				valueSliderText.setToolTipText(Messages.getString("ConfigurationDialog.6") + condition + Messages.getString("ConfigurationDialog.7")); //$NON-NLS-1$ //$NON-NLS-2$
				valueSliderText.setBackground(colorRegistry.get(ERROR_COLOR));
			}

			valueSliderText.addModifyListener(createSliderModifyListner(widget, valueSliderText, valueSlider));
			valueSlider.addSelectionListener(createSliderSelectionListner(widget, valueSliderText, valueSlider));

		} else if (widget != null && widget.isSpinner()) {
			// widget種別がspinnerの場合
			Composite valueComposite = createComposite(parent);

			createKeyLabel(key, valueComposite);

			final Spinner valueSpinner = createSpinner(valueComposite);

			if (widget.getCondition().getDigits() > 0) {
				// 小数の場合は桁数を設定
				valueSpinner.setDigits(widget.getCondition().getDigits());
			}
			valueSpinner.setMaximum(widget.getCondition().getMaxByInteger()
					.intValue());
			valueSpinner.setMinimum(widget.getCondition().getMinByInteger()
					.intValue());
			valueSpinner.setIncrement(widget.getSpinIncrement());

			// spinnerに初期値設定
			try {
				Double d = Double.valueOf(widget.getValue());
				Integer i = widget.getCondition().getIntegerByDigits(
						d.doubleValue());
				valueSpinner.setSelection(i.intValue());
			} catch (NumberFormatException e) {
				valueSpinner.setSelection(0);
			}
			if (widget.isValueModified()) {
				valueSpinner.setBackground(colorRegistry.get(MODIFY_COLOR));
			}

			valueSpinner.addModifyListener(createSpinnerModifyListner(widget, valueSpinner));

		} else if (widget != null && widget.isRadio()) {
			// widget種別がradioの場合
			Group valueRadioGroup = createGroup(parent);
			if (key != null) {
				// ハッシュキーのある場合
				valueRadioGroup.setText(key);
			}

			SelectionListener sl = createButtonSelectionListner(widget);

			// 列挙型制約条件から選択リスト作成
			for (String s : widget.getCondition().getEnumList()) {
				Button vb = createButton(valueRadioGroup);
				vb.setText(s);
				vb.addSelectionListener(sl);
				// 初期値設定
				if (vb.getText().equals(widget.getValue())) {
					vb.setSelection(true);
				}
			}

		} else if (widget != null && widget.isCheckbox()) {
			// widget種別がcheckboxの場合
			Checkbox.create(parent, key, this, widget);

		} else if (widget != null && widget.isOrderedList()) {
			// widget種別がordered_listの場合
			OrderedList.create(parent, this, widget);

		} else {
			createKeyLabel(key, parent);

			final Text valueText = createText(parent);
			valueText.setTextLimit(255);
			valueText.setEnabled(true);
			
			// textに初期値設定
			valueText.setText(widget.getValue());
			if (widget.isValueModified()) {
				valueText.setBackground(colorRegistry.get(MODIFY_COLOR));
			}
			String value = valueText.getText();
			ConfigurationCondition condition = widget.getCondition();
			if (!condition.validate(value)) {
				valueText.setToolTipText(Messages.getString("ConfigurationDialog.6") + condition + Messages.getString("ConfigurationDialog.7")); //$NON-NLS-1$ //$NON-NLS-2$
				valueText.setBackground(colorRegistry.get(ERROR_COLOR));
			}

			valueText.addModifyListener(createTextModifyListner(widget, valueText));
			valueText.addFocusListener(createFocusListner(widget, valueText));
		}
	}

	/**
	 * チェックボックスのコントロール
	 */
	public static class Checkbox {

		List<Button> checkButtons;

		String keyLabel;
		ConfigurationDialog dialog;
		ConfigurationWidget widget;

		public static Checkbox create(Composite parent, String keyLabel,
				ConfigurationDialog dialog, ConfigurationWidget widget) {
			Checkbox cb = new Checkbox(keyLabel, dialog, widget);
			cb.createComposite(parent);
			//
			cb.refreshCheck();
			return cb;
		}

		Checkbox(String keyLabel, ConfigurationDialog dialog,
				ConfigurationWidget widget) {
			this.keyLabel = keyLabel;
			this.dialog = dialog;
			this.widget = widget;
		}

		void createComposite(Composite parent) {
			GridLayout gl;
			GridData gd;

			Group group = new Group(parent, SWT.NONE);
			gl = new GridLayout(3, false);
			gl.marginHeight = 1;
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.grabExcessHorizontalSpace = true;
			group.setLayout(gl);
			group.setLayoutData(gd);

			if (keyLabel != null) {
				// ハッシュキーのある場合
				group.setText(keyLabel);
			}

			// 列挙型制約条件から選択リスト作成
			List<String> enumList = widget.getCondition().getEnumList();
			checkButtons = new ArrayList<Button>();
			for (String s : enumList) {
				Button vb = new Button(group, SWT.CHECK);
				gd = new GridData();
				gd.horizontalAlignment = GridData.FILL;
				gd.grabExcessHorizontalSpace = true;
				vb.setLayoutData(gd);
				vb.setText(s);
				vb.addSelectionListener(new SelectionListener() {
					ConfigurationWidget wd = widget;

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
					}

					@Override
					public void widgetSelected(SelectionEvent e) {
						List<String> values = new ArrayList<String>();
						for (Button b : checkButtons) {
							if (b.getSelection()) {
								values.add(b.getText());
							}
						}
						wd.setValueByArray(values.toArray(new String[0]));
						doModify();
					}
				});
				checkButtons.add(vb);
			}
		}

		void doModify() {
			if (dialog != null) {
				dialog.doModify(null);
			}
		}

		public void refreshCheck() {
			for (Button vb : checkButtons) {
				for (String v : widget.getValueAsArray()) {
					if (vb.getText().equals(v)) {
						vb.setSelection(true);
					}
				}
			}
		}
	}

	/**
	 * 順序付きリストのコントロール
	 */
	public static class OrderedList {

		TableViewer enumViewer;
		TableViewer valueViewer;
		Button addButton;
		Button deleteButton;
		Button upButton;
		Button downButton;

		String selectedEnum;
		int selectedValueIndex = -1;

		ConfigurationDialog dialog;
		ConfigurationWidget widget;
		List<String> valueList;

		public static OrderedList create(Composite parent,
				ConfigurationDialog dialog, ConfigurationWidget widget) {
			OrderedList ol = new OrderedList(dialog, widget);
			ol.createComposite(parent);
			//
			ol.refreshEnumList();
			ol.refreshValueList();
			ol.refreshButton();
			return ol;
		}

		OrderedList(ConfigurationDialog dialog, ConfigurationWidget widget) {
			this.dialog = dialog;
			this.widget = widget;
			this.valueList = new ArrayList<String>();
		}

		void createComposite(Composite parent) {
			GridLayout gl;
			GridData gd;

			Composite composite = new Composite(parent, SWT.NONE);
			gl = new GridLayout(4, false);
			gd = new GridData();
			gd.verticalAlignment = SWT.FILL;
			gd.horizontalAlignment = SWT.FILL;
			gd.grabExcessVerticalSpace = true;
			gd.grabExcessHorizontalSpace = true;

			composite.setLayout(gl);
			composite.setLayoutData(gd);

			enumViewer = new TableViewer(composite, SWT.BORDER);
			gl = new GridLayout(1, false);
			gd = new GridData();
			gd.verticalAlignment = SWT.FILL;
			gd.horizontalAlignment = SWT.FILL;
			gd.grabExcessVerticalSpace = true;
			gd.grabExcessHorizontalSpace = true;
			enumViewer.getTable().setLayout(gl);
			enumViewer.getTable().setLayoutData(gd);
			enumViewer.setContentProvider(new ArrayContentProvider());
			enumViewer.setLabelProvider(new LabelProvider());
			enumViewer
					.addSelectionChangedListener(new ISelectionChangedListener() {
						@Override
						public void selectionChanged(SelectionChangedEvent event) {
							selectedEnum = null;
							selectedValueIndex = -1;
							StructuredSelection s = (StructuredSelection) event
									.getSelection();
							selectedEnum = (String) s.getFirstElement();
							refreshButton();
						}
					});

			Composite bc1 = new Composite(composite, SWT.NONE);
			gl = new GridLayout(1, false);
			gd = new GridData();
			gd.horizontalAlignment = GridData.CENTER;
			gd.verticalAlignment = GridData.CENTER;
			gd.grabExcessVerticalSpace = false;
			gd.grabExcessHorizontalSpace = false;
			bc1.setLayout(gl);
			bc1.setLayoutData(gd);

			addButton = new Button(bc1, SWT.ARROW | SWT.RIGHT);
			gd = new GridData();
			gd.widthHint = 30;
			addButton.setLayoutData(gd);
			addButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if (selectedEnum == null || widget == null) {
						return;
					}
					valueList.add(selectedEnum);
					widget.setValueByArray(valueList.toArray(new String[0]));
					doModify();
					refreshValueList();
				}
			});

			deleteButton = new Button(bc1, SWT.ARROW | SWT.LEFT);
			gd = new GridData();
			gd.widthHint = 30;
			deleteButton.setLayoutData(gd);
			deleteButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if (selectedValueIndex == -1 || widget == null) {
						return;
					}
					valueList.remove(selectedValueIndex);
					widget.setValueByArray(valueList.toArray(new String[0]));
					doModify();
					refreshValueList();
				}
			});

			valueViewer = new TableViewer(composite, SWT.BORDER);
			gl = new GridLayout(1, false);
			gd = new GridData();
			gd.verticalAlignment = SWT.FILL;
			gd.horizontalAlignment = SWT.FILL;
			gd.grabExcessVerticalSpace = true;
			gd.grabExcessHorizontalSpace = true;
			valueViewer.getTable().setLayout(gl);
			valueViewer.getTable().setLayoutData(gd);
			valueViewer.setContentProvider(new ArrayContentProvider());
			valueViewer.setLabelProvider(new LabelProvider());
			valueViewer
					.addSelectionChangedListener(new ISelectionChangedListener() {
						@Override
						public void selectionChanged(SelectionChangedEvent event) {
							selectedEnum = null;
							selectedValueIndex = valueViewer.getTable()
									.getSelectionIndex();
							refreshButton();
						}
					});

			Composite bc2 = new Composite(composite, SWT.NONE);
			gl = new GridLayout(1, false);
			gd = new GridData();
			gd.horizontalAlignment = GridData.CENTER;
			gd.verticalAlignment = GridData.CENTER;
			gd.grabExcessVerticalSpace = false;
			gd.grabExcessHorizontalSpace = false;
			bc2.setLayout(gl);
			bc2.setLayoutData(gd);

			upButton = new Button(bc2, SWT.ARROW | SWT.UP);
			gd = new GridData();
			gd.widthHint = 30;
			upButton.setLayoutData(gd);
			upButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					int index = selectedValueIndex;
					String s = valueList.remove(index);
					valueList.add(index - 1, s);
					selectedValueIndex = index - 1;
					//
					widget.setValueByArray(valueList.toArray(new String[0]));
					doModify();
					refreshValueList();
					refreshButton();
				}
			});

			downButton = new Button(bc2, SWT.ARROW | SWT.DOWN);
			gd = new GridData();
			gd.widthHint = 30;
			downButton.setLayoutData(gd);
			downButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					int index = selectedValueIndex;
					String s = valueList.remove(index);
					valueList.add(index + 1, s);
					selectedValueIndex = index + 1;
					//
					widget.setValueByArray(valueList.toArray(new String[0]));
					doModify();
					refreshValueList();
					refreshButton();
				}
			});
		}

		void doModify() {
			if (dialog != null) {
				dialog.doModify(null);
			}
		}

		public void refreshEnumList() {
			if (widget == null) {
				return;
			}
			enumViewer.setInput(widget.getCondition().getEnumList());
		}

		public void refreshValueList() {
			valueList.clear();
			valueViewer.getTable().setBackground(
					colorRegistry.get(NORMAL_COLOR));
			if (widget != null) {
				for (String v : widget.getValueAsArray()) {
					if (widget.getCondition().getEnumList().contains(v)) {
						valueList.add(v);
					}
				}
				if (widget.isValueModified()) {
					valueViewer.getTable().setBackground(
							colorRegistry.get(MODIFY_COLOR));
				}
			}
			valueViewer.setInput(valueList);
			valueViewer.refresh();
			if (selectedValueIndex != -1) {
				valueViewer.getTable().setSelection(selectedValueIndex);
			}
		}

		public void refreshButton() {
			addButton.setEnabled(false);
			deleteButton.setEnabled(false);
			upButton.setEnabled(false);
			downButton.setEnabled(false);
			if (selectedEnum != null) {
				addButton.setEnabled(true);
			}
			if (selectedValueIndex != -1) {
				deleteButton.setEnabled(true);
			}
			if (selectedValueIndex > 0 && selectedValueIndex < valueList.size()) {
				upButton.setEnabled(true);
			}
			if (selectedValueIndex >= 0
					&& selectedValueIndex < valueList.size() - 1) {
				downButton.setEnabled(true);
			}
		}
	}

	private FocusListener createFocusListner(final ConfigurationWidget widget, final Text valueText) {
		return new FocusListener(){
			ConfigurationWidget wd = widget;
			
			public void focusGained(FocusEvent e) {
			}

			public void focusLost(FocusEvent e) {
				String value = valueText.getText();
				ConfigurationCondition condition = wd.getCondition();
				if (condition.validate(value)) {
					doModify(valueText);
				}
			}};
	}

	private ModifyListener createTextModifyListner(
			final ConfigurationWidget widget, final Text valueText) {
		return new ModifyListener() {
			ConfigurationWidget wd = widget;

			public void modifyText(ModifyEvent e) {
				String value = valueText.getText();
				wd.setValue(value);
				ConfigurationCondition condition = wd.getCondition();
				if (!condition.validate(value)) {
					valueText.setToolTipText(Messages.getString("ConfigurationDialog.6") + condition + Messages.getString("ConfigurationDialog.7")); //$NON-NLS-1$ //$NON-NLS-2$
					valueText.setBackground(colorRegistry.get(ERROR_COLOR));
				} else {
					valueText.setToolTipText(null);
					if (wd.isValueModified()) {
						valueText.setBackground(colorRegistry.get(MODIFY_COLOR));
						isValueModified = true;
					} else {
						valueText.setBackground(colorRegistry.get(NORMAL_COLOR));
					}
				}
				checkConstraint(false);
			}
		};
	}

	/** Applyが押されていたら即時更新する */
	void doModify(Control control) {
		if (control != null)
			control.setBackground(colorRegistry.get(MODIFY_COLOR));
		isValueModified = true;
		
		if (!isApply) return;
		if (!saveData()) return;
		
		view.applyConfiguration(false);
		refreshTabItem();
	}
	
	private SelectionListener createButtonSelectionListner(
			final ConfigurationWidget widget) {
		return new SelectionListener() {
			ConfigurationWidget wd = widget;

			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent e) {
				Button b = (Button) e.widget;
				if (b.getSelection()) {
					String value = b.getText();
					wd.setValue(value);
					doModify(null);
					checkConstraint(false);
				}
			}
		};
	}

	private ModifyListener createSpinnerModifyListner(
			final ConfigurationWidget widget, final Spinner valueSpinner) {
		return new ModifyListener() {
			ConfigurationWidget wd = widget;

			public void modifyText(ModifyEvent e) {
				String value = valueSpinner.getText();
				ConfigurationCondition condition = wd.getCondition();
				if (!condition.validate(value)) {
					valueSpinner.setToolTipText(Messages.getString("ConfigurationDialog.6") + condition + Messages.getString("ConfigurationDialog.7")); //$NON-NLS-1$ //$NON-NLS-2$
					// 最小/最大値を超える値を丸める
//					wd.setValue(condition.adjustMinMaxValue(value));
                    wd.setValue(value);
					valueSpinner.setBackground(colorRegistry.get(ERROR_COLOR));
				} else {
					valueSpinner.setToolTipText(null);
					wd.setValue(value);
					if (wd.isValueModified()) {
						doModify(valueSpinner);
					} else {
						valueSpinner.setBackground(colorRegistry
								.get(NORMAL_COLOR));
					}
				}
				checkConstraint(false);
			}
		};
	}

	private SelectionAdapter createSliderSelectionListner(
			final ConfigurationWidget widget, final Text valueSliderText,
			final Slider valueSlider) {
		return new SelectionAdapter() {
			ConfigurationWidget wd = widget;

			public void widgetSelected(SelectionEvent e) {
				// ステップから制約範囲内の値に換算
				int step = valueSlider.getSelection();
				String value = wd.getCondition().getValueByStep(step, wd,
						valueSliderText.getText(), wd.getSliderStepStr());
				if (wd.getCondition().validate(value)) {
					wd.setValue(value);
				}
				valueSliderText.setText(value);
			}
		};
	}

	private ModifyListener createSliderModifyListner(
			final ConfigurationWidget widget, final Text valueSliderText,
			final Slider valueSlider) {
		return new ModifyListener() {
			ConfigurationWidget wd = widget;

			public void modifyText(ModifyEvent e) {
				String value = valueSliderText.getText();
				ConfigurationCondition condition = wd.getCondition();
				try {
					// 値を制約範囲内のステップに換算
					int step = condition.getStepByValue(value, widget);
					valueSlider.setSelection(step);
				} catch (NumberFormatException ne) {
					valueSlider.setSelection(0);
				}
				if (!condition.validate(value)) {
					valueSliderText.setToolTipText(Messages.getString("ConfigurationDialog.6") + condition + Messages.getString("ConfigurationDialog.7")); //$NON-NLS-1$ //$NON-NLS-2$
					// 最小/最大値を超える値を丸める
//                    wd.setValue(condition.adjustMinMaxValue(value));
                    wd.setValue(value);
					valueSliderText.setBackground(colorRegistry.get(ERROR_COLOR));
				} else {
					valueSliderText.setToolTipText(null);
					wd.setValue(value);
					if (wd.isValueModified()) {
						doModify(valueSliderText);
					} else {
						valueSliderText.setBackground(colorRegistry.get(NORMAL_COLOR));
					}
				}
				checkConstraint(false);
			}
		};
	}

	private void createKeyLabel(final String key, Composite parent) {
		if (key != null) {
			// ハッシュキーのある場合
			final Label valueSliderLabel = createLabel(parent);
			valueSliderLabel.setText(key + ":"); //$NON-NLS-1$
		}
	}

	private void selectConfigSet(int index) {
		if (index < 0 || index >= tabFolder.getItemCount()) {
			return;
		}
		currentTabItem = tabFolder.getItem(index);
		String currentId = this.tabbedIdList.get(index);
		// 選択タブに対応するConfigurationSetを検索
		selectedConfigSet = null;
		for (ConfigurationSetConfigurationWrapper cs : copiedConfig.getConfigurationSetList()) {
			if (cs.getId().equals(currentId)) {
				selectedConfigSet = cs;
				break;
			}
		}
		if (selectedConfigSet != null) {
			if (currentTabItem.getControl() == null) {
				currentTabItem.setControl(createConfigSetComposite(selectedConfigSet));
			}
			//check Constraint
			if(errorText!=null) {
				checkConstraint(false);
			}
		}
	}

	/**
	 * 編集結果をモデル情報へ保存する
	 * 
	 * @return 保存エラーの場合はfalse
	 */
	private boolean saveData() {
		if (!this.isValueModified) {
			// 値に変更がない場合
			return true;
		}
		// 設定値保存
		List<ConfigurationSetConfigurationWrapper> origSetList = view.getComponentConfig()
				.getConfigurationSetList();
		List<ConfigurationSetConfigurationWrapper> copySetList = this.copiedConfig
				.getConfigurationSetList();
		doSave(origSetList, copySetList);
		isValueModified = false;
		return true;
	}

	private void doSave(List<ConfigurationSetConfigurationWrapper> origSetList,
			List<ConfigurationSetConfigurationWrapper> copySetList) {
		for (int i = 0; i < copySetList.size(); i++) {
			ConfigurationSetConfigurationWrapper origSet = origSetList.get(i);
			ConfigurationSetConfigurationWrapper copySet = copySetList.get(i);
			List<NamedValueConfigurationWrapper> origNvList = origSet
					.getNamedValueList();
			List<NamedValueConfigurationWrapper> copyNvList = copySet
					.getNamedValueList();
			for (int j = 0; j < copyNvList.size(); j++) {
				NamedValueConfigurationWrapper copyNv = copyNvList.get(j);
				if (!copyNv.isLoadedWidgetValue()) {
					// 編集中でなければスキップ
					continue;
				}
				// valueの変更があり、かつ文字列の場合のみ保存
				boolean modified = false;
				if (copyNv.widgetKeySet().size() > 0) {
					// ハッシュの場合
					for (String key : copyNv.widgetKeySet()) {
						if (copyNv.widget(key).isValueModified()) {
							modified = true;
							break;
						}
					}
				} else {
					// 配列、単体の場合
					for (int k = 0; k < copyNv.widgetSize(); k++) {
						if (copyNv.widget(k).isValueModified()) {
							modified = true;
							break;
						}
					}
				}
				if (!modified) {
					continue;
				}
				copyNv.saveWidgetValue();

				for (NamedValueConfigurationWrapper origNv : origNvList) {
					// ダイアログのオープン中にビューの ConfigurationDataリストに
					// ずれが生じると、異なる NameValueに変更を反映してしまうため、
					// キーで変更対象の NameValueの検索を行う。
					if (copyNv.getKey().equals(origNv.getKey())) {
						origNv.setValue(copyNv.getValue());
						break;
					}
				}
			}
		}
	}
    private List<String> checkConstraints() {
        List<String> validateErrors = new ArrayList<String>();
        for (ConfigurationSetConfigurationWrapper cs : this.copiedConfig
                .getConfigurationSetList()) {
            for (NamedValueConfigurationWrapper nv : cs.getNamedValueList()) {
				if(nv.isLoadedWidgetValue() == false) {
					nv.loadWidgetValue();
				}
				List<String> result = nv.checkConstraints(cs.getId(), true);
				validateErrors.addAll(result);
            }
        }
        return validateErrors;
    }
    
    @Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Configuration"); //$NON-NLS-1$
	}

	@Override
	protected void okPressed() {
        List<String> validateErrors = checkConstraints();
		if(0 < validateErrors.size()) {
			StringBuilder builder = new StringBuilder();
			builder.append(Messages.getString("ConfigurationView.condition_error.1")).append(System.getProperty("line.separator"));
			builder.append(Messages.getString("ConfigurationView.condition_error.2")).append(System.getProperty("line.separator"));
			builder.append(System.getProperty("line.separator"));
			for(String each : validateErrors) {
				builder.append(each).append(System.getProperty("line.separator"));
			}
			boolean ret = MessageDialog.openConfirm(getShell(),
													Messages.getString("Common.dialog.confirm_title"),
													builder.toString());
			if(ret == false) return;
		}
		
		this.isValueModified = true;
		
		if (saveData()) {
			view.applyConfiguration(false);
			super.okPressed();
		}
	}

	/** コンフィグ値を再描画する */
	private void selectConfigSet() {
		int index = tabFolder.getSelectionIndex();
		selectConfigSet((index == -1) ? 0 : index);
	}

}
