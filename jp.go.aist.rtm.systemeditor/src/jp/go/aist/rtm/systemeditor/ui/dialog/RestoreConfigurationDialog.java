package jp.go.aist.rtm.systemeditor.ui.dialog;

import java.util.Collections;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import jp.go.aist.rtm.systemeditor.RTSystemEditorPlugin;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper.ComponentConfigurationWrapper;
import jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper.ConfigurationSetConfigurationWrapper;
import jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper.NamedValueConfigurationWrapper;
import jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper.Secretable;
import jp.go.aist.rtm.toolscommon.model.component.Component;

public class RestoreConfigurationDialog extends Dialog {
	static final int APPLY_ID = 998;
	
	private static final String MODIFY_COLOR = "MODIFY_COLOR";
	private static final String CANT_MODIFY_COLOR = "CANT_MODIFY_COLOR";
	private static final String WHITE_COLOR = "WHITE_COLOR";

	private static ColorRegistry colorRegistry = null;
	
	private static final String PROPERTY_ACTIVE_CONFIGSET = "PROPERTY_ACTIVE_CONFIGSET";
	private static final String PROPERTY_CONFIG_SET = "PROPERTY_CONFIG_SET";
	private static final String PROPERTY_KEY = "PROPERTY_KEY";
	private static final String PROPERTY_VALUE = "PROPERTY_VALUE";
	
	private static final String LABEL_BUTTON_DETAIL = Messages.getString("ConfigurationView.48");
	private static final String LABEL_TOOLTIP_DETAIL = Messages.getString("ConfigurationView.47");

	private static final String LABEL_BUTTON_NV_SORT = Messages.getString("ConfigurationView.49");
	private static final String LABEL_TOOLTIP_NV_SORT = Messages.getString("ConfigurationView.50");
	private static final String LABEL_BUTTON_NV_DETAIL = Messages.getString("ConfigurationView.48");
	private static final String LABEL_TOOLTIP_NV_DETAIL = Messages.getString("ConfigurationView.47");

	private static final String MSG_WARNING = Messages.getString("ConfigurationView.39");
	private static final String MSG_NAME_ALREADY_EXIST = Messages.getString("ConfigurationView.40");
	private static final String MSG_KEY_ALREADY_EXIST = Messages.getString("ConfigurationView.43");

	private Component targetComponent;
	private ComponentConfigurationWrapper copiedComponent;

	private Table leftTable;
	private TableViewer leftTableViewer;
	private DetailTableViewerFilter leftTableViewerFilter;

	private Table rightTable;
	private TableViewer rightTableViewer;
	private DetailTableViewerFilter rightTableViewerFilter;

	private Button detailConfigurationSetCheckButton;
	private Button sortCheckButton;
	private Button detailNamedValueCheckButton;

	public RestoreConfigurationDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(getShellStyle() | SWT.CENTER | SWT.RESIZE);
	}
	@Override
	protected Control createDialogArea(Composite parent) {
		if (colorRegistry == null) {
			colorRegistry = new ColorRegistry();
			colorRegistry.put(MODIFY_COLOR, new RGB(255, 192, 192));
			colorRegistry.put(CANT_MODIFY_COLOR, new RGB(198, 198, 198));
			colorRegistry.put(WHITE_COLOR, new RGB(255, 255, 255));
		}
		
		GridLayout gl;
		GridData gd;

		gl = new GridLayout();
		gl.numColumns = 1;
		parent.setLayout(gl);
		
		SashForm sashForm = new SashForm(parent, SWT.HORIZONTAL);
		
		gd = new GridData();
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.verticalAlignment = SWT.FILL;
		sashForm.setLayoutData(gd);
		
		createLeftControl(sashForm);

		createRightControl(sashForm);

		sashForm.setWeights(new int[] { 30, 70 });
		
		return sashForm;
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Configuration Setting"); //$NON-NLS-1$
		shell.setSize(1000, 400);
	}

	private void createLeftControl(SashForm sashForm) {
		GridLayout gl;
		GridData gd;

		final Composite composite = new Composite(sashForm, SWT.FILL);
		gl = new GridLayout();
		gl.marginWidth = 0;
		gl.marginHeight = 0;
		gl.numColumns = 1;
		composite.setLayout(gl);

		Composite componentNameComposite = new Composite(composite, SWT.NONE);
		gl = new GridLayout();
		gl.numColumns = 2;
		gl.marginHeight = 2;
		componentNameComposite.setLayout(gl);
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 0;
		gd.verticalSpan = 0;
		componentNameComposite.setLayoutData(gd);

		leftTableViewer = new TableViewer(composite, SWT.FULL_SELECTION
				| SWT.SINGLE | SWT.BORDER);
		leftTableViewer.setColumnProperties(new String[] {
				PROPERTY_ACTIVE_CONFIGSET, PROPERTY_CONFIG_SET });
		leftTableViewer.setContentProvider(new ArrayContentProvider());
		leftTableViewer.setLabelProvider(new ConfigSetLabelProvider());
		leftTableViewer.setCellModifier(new LeftTableCellModifier(
				leftTableViewer));

		leftTableViewer.setCellEditors(new CellEditor[] {
				new CheckboxCellEditor(leftTableViewer.getTable()),
				new TextCellEditor(leftTableViewer.getTable()) });
		this.leftTableViewerFilter = new DetailTableViewerFilter();
		this.leftTableViewer.addFilter(this.leftTableViewerFilter);

		leftTable = leftTableViewer.getTable();
		leftTable.setLinesVisible(true);
		leftTable.setHeaderVisible(true);
		gd = new GridData();
		gd.verticalAlignment = SWT.FILL;
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 0;
		gd.verticalSpan = 0;
		leftTable.setLayoutData(gd);

		gl = new GridLayout(1, false);
		gl.numColumns = 1;
		leftTable.setLayout(gl);

		final TableColumn activeCol = new TableColumn(leftTable, SWT.RIGHT);
		activeCol.setText("active");
		activeCol.setWidth(50);

		final TableColumn configCol = new TableColumn(leftTable, SWT.LEFT);
		configCol.setText("config");
		activeCol.setWidth(50);

		ControlAdapter controlAdapter = new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				composite.getParent().forceFocus();
				int width = composite.getClientArea().width - 2
						* leftTable.getBorderWidth() - activeCol.getWidth();
				configCol.setWidth(Math.max(10, width));
			}
		};

		activeCol.addControlListener(controlAdapter);
		composite.addControlListener(controlAdapter);

		Composite buttonCompsite = new Composite(composite, SWT.BOTTOM);
		gl = new GridLayout();
		gl.numColumns = 4;
		buttonCompsite.setLayout(gl);
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		buttonCompsite.setLayoutData(gd);

		this.detailConfigurationSetCheckButton = new Button(buttonCompsite, SWT.BOTTOM | SWT.CHECK);
		this.detailConfigurationSetCheckButton.setEnabled(false);
		this.detailConfigurationSetCheckButton.setToolTipText(LABEL_TOOLTIP_DETAIL);
		gd = new GridData();
		gd.horizontalAlignment = SWT.END;
		this.detailConfigurationSetCheckButton.setLayoutData(gd);
		this.detailConfigurationSetCheckButton.setText(LABEL_BUTTON_DETAIL);
		this.detailConfigurationSetCheckButton.setSelection(false);
		this.detailConfigurationSetCheckButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				refreshLeftData();
			}
		});
	}

	private void createRightControl(SashForm sashForm) {
		GridLayout gl;
		GridData gd;

		final Composite composite = new Composite(sashForm, SWT.FILL);
		gl = new GridLayout();
		gl.marginWidth = 0;
		gl.marginHeight = 0;
		composite.setLayout(gl);

		Composite configurationNameComposite = new Composite(composite,
				SWT.NONE);
		gl = new GridLayout();
		gl.numColumns = 2;
		gl.marginHeight = 2;
		configurationNameComposite.setLayout(gl);
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 0;
		gd.verticalSpan = 0;
		configurationNameComposite.setLayoutData(gd);

		rightTableViewer = new TableViewer(composite, SWT.FULL_SELECTION
				| SWT.SINGLE | SWT.BORDER);
		rightTableViewer.setColumnProperties(new String[] { PROPERTY_KEY,
				PROPERTY_VALUE });
		rightTableViewer.setContentProvider(new ArrayContentProvider());
		rightTableViewer.setLabelProvider(new MapEntryLabelProvider());
		rightTableViewer.setCellModifier(new RightTableCellModifier(
				rightTableViewer));
		rightTableViewer.setCellEditors(new CellEditor[] {
				new TextCellEditor(rightTableViewer.getTable()),
				new TextCellEditor(rightTableViewer.getTable()) });
		this.rightTableViewerFilter = new DetailTableViewerFilter();
		this.rightTableViewer.addFilter(this.rightTableViewerFilter);

		rightTable = rightTableViewer.getTable();
		rightTable.setLinesVisible(true);
		leftTable.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				refreshRightData();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				refreshRightData();
			}
		});

		gd = new GridData();
		gd.verticalAlignment = SWT.FILL;
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 0;
		gd.verticalSpan = 0;
		rightTable.setLayoutData(gd);

		rightTable.setHeaderVisible(true);

		final TableColumn keyCol = new TableColumn(rightTable, SWT.LEFT);
		keyCol.setText("name");
		keyCol.setWidth(150);

		final TableColumn valueCol = new TableColumn(rightTable, SWT.LEFT);
		valueCol.setText("value");
		valueCol.setWidth(300);

		ControlAdapter controlAdapter = new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				composite.getParent().forceFocus();
				int width = composite.getClientArea().width - 2
						* rightTable.getBorderWidth() - keyCol.getWidth();
				valueCol.setWidth(Math.max(10, width));
			}
		};

		keyCol.addControlListener(controlAdapter);
		composite.addControlListener(controlAdapter);

		Composite buttonCompsite = new Composite(composite, SWT.BOTTOM);
		gl = new GridLayout();
		gl.numColumns = 4;
		buttonCompsite.setLayout(gl);
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		buttonCompsite.setLayoutData(gd);

		this.sortCheckButton = new Button(buttonCompsite, SWT.BOTTOM | SWT.CHECK);
		this.sortCheckButton.setEnabled(false);
		this.sortCheckButton.setToolTipText(LABEL_TOOLTIP_NV_SORT);
		gd = new GridData();
		gd.horizontalAlignment = SWT.END;
		this.sortCheckButton.setLayoutData(gd);
		this.sortCheckButton.setText(LABEL_BUTTON_NV_SORT);
		this.sortCheckButton.setSelection(false);
		this.sortCheckButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				buildData();
				refreshRightData();
			}
		});

		this.detailNamedValueCheckButton = new Button(buttonCompsite, SWT.BOTTOM | SWT.CHECK);
		this.detailNamedValueCheckButton.setEnabled(false);
		this.detailNamedValueCheckButton.setToolTipText(LABEL_TOOLTIP_NV_DETAIL);
		gd = new GridData();
		gd.horizontalAlignment = SWT.END;
		this.detailNamedValueCheckButton.setLayoutData(gd);
		this.detailNamedValueCheckButton.setText(LABEL_BUTTON_NV_DETAIL);
		this.detailNamedValueCheckButton.setSelection(false);
		this.detailNamedValueCheckButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				refreshRightData();
			}
		});
	}

	private ConfigurationSetConfigurationWrapper getSelectedConfigurationSet() {
		if (this.leftTableViewer.getSelection() instanceof StructuredSelection) {
			ConfigurationSetConfigurationWrapper ret = (ConfigurationSetConfigurationWrapper) ((StructuredSelection) this.leftTableViewer
					.getSelection()).getFirstElement();
			return ret;
		}
		return null;
	}
	
	private String createDefaultNamedValueKey(String preString) {
		ConfigurationSetConfigurationWrapper currentConfugurationSet = getSelectedConfigurationSet();
		int number = 1;
		for (;; number++) {
			boolean isExist = false;
			for (NamedValueConfigurationWrapper current : currentConfugurationSet.getNamedValueList()) {
				if ((preString + "_" + number).equals(current.getKey())) {
					isExist = true;
					break;
				}
			}
			if (!isExist) {
				break;
			}
		}
		return (preString + "_" + number);
	}

	private String createDefaultConfigurationSetName(String preString) {
		int number = 1;
		for (;; number++) {
			boolean isExist = false;
			for (ConfigurationSetConfigurationWrapper current : copiedComponent.getConfigurationSetList()) {
				if ((preString + "_" + number).equals(current.getId())) {
					isExist = true;
					break;
				}
			}
			if (!isExist) {
				break;
			}
		}
		return preString + "_" + number;
	}
	
	@Override
	protected Control createButtonBar(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout(4, false);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		composite.setLayout(gl);
		composite.setLayoutData(gd);
		
		Label lblDummy = new Label(composite, SWT.NONE);
		gd = new GridData();
		gd.grabExcessHorizontalSpace = true;
		lblDummy.setLayoutData(gd);
		
		Button btnClose = createButton(composite, APPLY_ID, "OK", false);
		btnClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				notifyModified();
//				restoreComponent();
				close();
			}
		});
		Button btnCancel = createButton(composite, CANCEL, "Cancel", true);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				close();
			}
		});
		return composite;
	}
	
	private void buildData() {
		copiedComponent = null;
		if (targetComponent != null) {
			copiedComponent = createConfigurationWrapper(targetComponent);
		}
		refreshData();
	}
	
	private void refreshData() {
		refreshLeftData();

		if (this.copiedComponent != null) {
			this.leftTable.setSelection(
					this.copiedComponent.getConfigurationSetList().indexOf(this.copiedComponent.getActiveConfigSet()));
		}

		refreshRightData();
	}

	private void refreshLeftData() {
		this.leftTableViewer.setInput(Collections.EMPTY_LIST);
		this.detailConfigurationSetCheckButton.setEnabled(false);

		if (this.copiedComponent != null) {
			this.detailConfigurationSetCheckButton.setEnabled(true);

			this.leftTableViewerFilter.setDetail(this.detailConfigurationSetCheckButton.getSelection());
			this.leftTableViewer.setInput(this.copiedComponent.getConfigurationSetList());

			if (this.leftTable.getItemCount() > 0) {
				this.leftTable.select(0);
			}
		}
	}

	private void refreshRightData() {
		this.rightTableViewer.setInput(Collections.EMPTY_LIST);
		this.sortCheckButton.setEnabled(false);
		this.detailNamedValueCheckButton.setEnabled(false);

		if (this.copiedComponent != null) {
			ConfigurationSetConfigurationWrapper currentConfugurationSet = getSelectedConfigurationSet();
			if (currentConfugurationSet != null) {
				this.rightTableViewerFilter.setDetail(this.detailNamedValueCheckButton.getSelection());
				this.rightTableViewer.setInput(currentConfugurationSet.getNamedValueList());

				this.sortCheckButton.setEnabled(true);
				this.detailNamedValueCheckButton.setEnabled(true);
			}
		}
	}
	
	/**
	 * アクティブなコンフィグレーションを切り替えたか
	 */
	private boolean isActiveConfigurationSetChanged() {
		if (copiedComponent.getActiveConfigSet() == null
				|| copiedComponent.getActiveConfigSet().getConfigurationSet() == null) {
			return targetComponent.getActiveConfigurationSet() != null;
		}
		if (targetComponent.getActiveConfigurationSet() == null) {
			return true;
		}
		if (!copiedComponent.getActiveConfigSet().getConfigurationSet().getId()
				.equals(targetComponent.getActiveConfigurationSet().getId())) {
			return true;
		}
		return false;
	}

	/**
	 * @return ConfiguratoinSetの詳細表示が有効な場合はtrue
	 */
	public boolean isCheckedDetailConfigurationSet() {
		return this.detailConfigurationSetCheckButton.getSelection();
	}

	/**
	 * @return NamedValueの詳細表示が有効な場合はtrue
	 */
	public boolean isCheckedDetailNamedValue() {
		return this.detailNamedValueCheckButton.getSelection();
	}

	public ComponentConfigurationWrapper createConfigurationWrapper(
			Component target) {
		return ComponentConfigurationWrapper.create(target, sortCheckButton.getSelection());
	}

	/**
	 * 左テーブルのCellModifierクラス
	 */
	public class LeftTableCellModifier implements ICellModifier {
		private TableViewer viewer;

		public LeftTableCellModifier(TableViewer viewer) {
			this.viewer = viewer;
		}

		@Override
		public boolean canModify(Object element, String property) {
			ConfigurationSetConfigurationWrapper configurationSet = null;
			if (element instanceof ConfigurationSetConfigurationWrapper) {
				configurationSet = (ConfigurationSetConfigurationWrapper) element;
			}
			if (PROPERTY_ACTIVE_CONFIGSET.equals(property)) {
				if (configurationSet.isSecret()) {
					return false;
				}
			}
			return true;
		}

		@Override
		public Object getValue(Object element, String property) {
			Object result = null;
			if (PROPERTY_ACTIVE_CONFIGSET.equals(property)) {
				result = Boolean.TRUE;
			} else if (PROPERTY_CONFIG_SET.equals(property)) {
				result = ((ConfigurationSetConfigurationWrapper) element).getId();
			}
			return result;
		}

		@Override
		public void modify(Object element, String property, Object value) {
			ConfigurationSetConfigurationWrapper configurationSet = null;
			if (element instanceof Item) {
				configurationSet = ((ConfigurationSetConfigurationWrapper) ((Item) element).getData());
			}
			if (configurationSet == null) {
				return;
			}

			if (PROPERTY_ACTIVE_CONFIGSET.equals(property)) {
				copiedComponent.setActiveConfigSet(configurationSet);
				viewer.refresh();
			} else if (PROPERTY_CONFIG_SET.equals(property)) {
				boolean isDuplicate = false;
				for (ConfigurationSetConfigurationWrapper current : copiedComponent.getConfigurationSetList()) {
					if (configurationSet != current && ((String) value).equals(current.getId())) {
						isDuplicate = true;
						break;
					}
				}
				String newConfigurationSetName = (String) value;
				if (isDuplicate) {
					MessageDialog.openWarning(viewer.getControl().getShell(), MSG_WARNING, MSG_NAME_ALREADY_EXIST);
					newConfigurationSetName = createDefaultConfigurationSetName((String) value);
				}
				configurationSet.setId(newConfigurationSetName);
				viewer.update(configurationSet, null);
			}
		}

	}

	/**
	 * 右テーブルのCellModifierクラス
	 */
	public class RightTableCellModifier implements ICellModifier {
		private TableViewer viewer;

		public RightTableCellModifier(TableViewer viewer) {
			this.viewer = viewer;
		}

		@Override
		public boolean canModify(Object element, String property) {

			if (PROPERTY_KEY.equals(property)) {
				return true;
			} else if (PROPERTY_VALUE.equals(property)) {
				NamedValueConfigurationWrapper item = (NamedValueConfigurationWrapper) element;
				return item.canModify();
			}

			return false;
		}

		@Override
		public Object getValue(Object element, String property) {
			NamedValueConfigurationWrapper item = (NamedValueConfigurationWrapper) element;

			if (PROPERTY_KEY.equals(property)) {
				return item.getKey();
			} else if (PROPERTY_VALUE.equals(property)) {
				return item.getValueAsString();
			}

			return null;
		}

		@Override
		public void modify(Object element, String property, Object value) {
			if (element instanceof TableItem == false) {
				return;
			}
			NamedValueConfigurationWrapper item = ((NamedValueConfigurationWrapper) ((TableItem) element).getData());
			ConfigurationSetConfigurationWrapper currentConfugurationSet = getSelectedConfigurationSet();
			if (item == null || currentConfugurationSet == null) {
				return;
			}

			if (PROPERTY_KEY.equals(property)) {
				boolean isDuplicate = false;
				for (NamedValueConfigurationWrapper current : currentConfugurationSet.getNamedValueList()) {
					if (item != current && ((String) value).equals(current.getKey())) {
						isDuplicate = true;
						break;
					}
				}
				String newKey = (String) value;
				if (isDuplicate) {
					MessageDialog.openWarning(viewer.getControl().getShell(), MSG_WARNING, MSG_KEY_ALREADY_EXIST);
					newKey = createDefaultNamedValueKey((String) value);
				}
				item.setKey(newKey);
			} else if (PROPERTY_VALUE.equals(property)) {
				item.setValue((String) value);
			}

			viewer.update(item, null);
		}
	}

	/**
	 * LabelProviderクラス
	 */
	public class ConfigSetLabelProvider extends LabelProvider implements
			ITableLabelProvider, ITableColorProvider {

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			Image result = null;
			if (columnIndex == 0) {
				ConfigurationSetConfigurationWrapper item = (ConfigurationSetConfigurationWrapper) element;
				if (item == copiedComponent.getActiveConfigSet()) {
					result = RTSystemEditorPlugin
							.getCachedImage("icons/Radiobutton_Checked.png");
				} else {
					result = RTSystemEditorPlugin
							.getCachedImage("icons/Radiobutton_Unchecked.png");
				}
			}

			return result;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			ConfigurationSetConfigurationWrapper item = (ConfigurationSetConfigurationWrapper) element;

			String result = null;
			if (columnIndex == 1) {
				result = getModiedLabelString(item.isNameModified())
						+ item.getId();
			}

			return result;
		}

		@Override
		public Color getBackground(Object element, int columnIndex) {
			ConfigurationSetConfigurationWrapper configurationSetConfigurationWrapper = (ConfigurationSetConfigurationWrapper) element;

			boolean isModify = false;
			if (columnIndex == 0) {
				if (isActiveConfigurationSetChanged()) {
					if (targetComponent.getActiveConfigurationSet() == configurationSetConfigurationWrapper
							.getConfigurationSet()
							|| copiedComponent.getActiveConfigSet() == configurationSetConfigurationWrapper) {
						isModify = true;
					}
				}
			} else if (columnIndex == 1) {
				isModify = configurationSetConfigurationWrapper
						.isNameModified();
			}

			Color color = null;
			if (isModify) {
				color = colorRegistry.get(MODIFY_COLOR);
			}

			return color;
		}

		@Override
		public Color getForeground(Object element, int columnIndex) {
			return null;
		}
	}

	/**
	 * LabelProviderクラス
	 */
	public class MapEntryLabelProvider extends LabelProvider implements
			ITableLabelProvider, ITableColorProvider {

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			NamedValueConfigurationWrapper item = (NamedValueConfigurationWrapper) element;

			if (columnIndex == 0) {
				return getModiedLabelString(item.isKeyModified())
						+ item.getKey();
			} else if (columnIndex == 1) {
				return item.getValueAsString();
			}

			return null;
		}

		@Override
		public Color getForeground(Object element, int columnIndex) {
			return null;
		}

		@Override
		public Color getBackground(Object element, int columnIndex) {
			NamedValueConfigurationWrapper namedValueConfigurationWrapper = (NamedValueConfigurationWrapper) element;
			if (columnIndex == 0
					&& namedValueConfigurationWrapper.isKeyModified()
					|| columnIndex == 1
					&& namedValueConfigurationWrapper.isValueModified()) {
				return colorRegistry.get(MODIFY_COLOR);
			}

			if (columnIndex == 1 && !namedValueConfigurationWrapper.canModify()) {
				return colorRegistry.get(CANT_MODIFY_COLOR);
			}

			return null;
		}
	}

	/**
	 * ConfigurationSet、およびNamedValueの一覧テーブルの表示フィルタ<br>
	 * 「__」で始まる設定値は、詳細モードのときのみ表示します。
	 */
	private static class DetailTableViewerFilter extends ViewerFilter {

		private boolean isDetail;

		public DetailTableViewerFilter() {
			this.isDetail = false;
		}

		/**
		 * 詳細モードを設定します。
		 *
		 * @param isDetail
		 *            詳細モードの場合はtrue
		 */
		public void setDetail(boolean isDetail) {
			this.isDetail = isDetail;
		}

		/**
		 * 詳細モード、かつNameValueのキーが「__」で始まっていない場合はtrue
		 */
		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (!(element instanceof Secretable)) {
				return false;
			}
			Secretable sc = (Secretable) element;
			return (this.isDetail || !sc.isSecret());
		}

	}

	private String getModiedLabelString(boolean bool) {
		String result = "";
		if (bool) {
			result = "*";
		}
		return result;
	}

	/** 編集用のコンフィグを返す */
	public ComponentConfigurationWrapper getComponentConfig() {
		return copiedComponent;
	}
}

