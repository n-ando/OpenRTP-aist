package jp.go.aist.rtm.rtcbuilder.ui.editors;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
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
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import jp.go.aist.rtm.rtcbuilder.IRTCBMessageConstants;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.fsm.EventParam;
import jp.go.aist.rtm.rtcbuilder.fsm.ScXMLHandler;
import jp.go.aist.rtm.rtcbuilder.fsm.StateParam;
import jp.go.aist.rtm.rtcbuilder.fsm.TransitionParam;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.SCXMLGraphEditor;
import jp.go.aist.rtm.rtcbuilder.fsm.editor.SCXMLNotifier;
import jp.go.aist.rtm.rtcbuilder.generator.param.EventPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.PropertyParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.nl.Messages;
import jp.go.aist.rtm.rtcbuilder.ui.preference.IPreferenceMessageConstants;
import jp.go.aist.rtm.rtcbuilder.util.StringUtil;

/**
 * FSMページ
 */
public class FSMEditorFormPage extends AbstractEditorFormPage {

	private Button fsmBtn;
	private Button staticBtn;
	private Button dynamicBtn;
	private Button importBtn;
	private Button newBtn;
	private Button editBtn;
	
	private Text portNameText;
	private Text variableNameText;
	private TableViewer eventTableViewer;
	
	private Text eventNameDetailText;
	private Text sourceText;
	private Text targetText;
	private Text dataTypeText;
	private Text descriptionText;
	private Text typeText;
	private Text numberText;
	private Text semanticsText;
	private Text unitText;
	private Text operationText;
	
	private SCXMLGraphEditor scxmlEditor;
	private SCXMLReceiver observer;
	
	private EventParam selectParam;
	private String[] defaultTypeList;
	//
	/**
	 * コンストラクタ
	 * 
	 * @param editor
	 *            親のエディタ
	 */
	public FSMEditorFormPage(RtcBuilderEditor editor) {
		super(editor, "id", "FSM");
		defaultTypeList = super.extractDataTypes();
	}

	/**
	 * {@inheritDoc}
	 */
	protected void createFormContent(IManagedForm managedForm) {
		ScrolledForm form = super.createBase(managedForm, "Finite State Machine(FSM)");
		FormToolkit toolkit = managedForm.getToolkit();
		//
		createFSMSection(toolkit, form);
		createHintSection(toolkit, form);

		load();
	}

	private void createHintSection(FormToolkit toolkit, ScrolledForm form) {
		Composite composite = createHintSectionBase(toolkit, form, 3);
		createHintLabel(Messages.getString("IMC.FSM_HINT_PROC"), toolkit, composite);
		createHintLabel("  " + Messages.getString("IMC.FSM_HINT_PROC_1"), toolkit, composite);
		createHintLabel("  " + Messages.getString("IMC.FSM_HINT_PROC_2"), toolkit, composite);
		createHintLabel("    " + Messages.getString("IMC.FSM_HINT_PROC_2_1"), toolkit, composite);
		createHintLabel("    " + Messages.getString("IMC.FSM_HINT_PROC_2_2"), toolkit, composite);
		createHintLabel("    " + Messages.getString("IMC.FSM_HINT_PROC_2_3"), toolkit, composite);
		createHintLabel("  " + Messages.getString("IMC.FSM_HINT_PROC_3"), toolkit, composite);
		createHintLabel("    " + Messages.getString("IMC.FSM_HINT_PROC_3_1"), toolkit, composite);
		createHintLabel("    " + Messages.getString("IMC.FSM_HINT_PROC_3_2"), toolkit, composite);
		createHintLabel("    " + Messages.getString("IMC.FSM_HINT_PROC_3_3"), toolkit, composite);
		createHintLabel("    " + Messages.getString("IMC.FSM_HINT_PROC_3_4"), toolkit, composite);
		createHintSpace(toolkit, composite);
		createHintLabel("FSM:", IMessageConstants.FSM_HINT_DESC, toolkit, composite);
		createHintLabel("Static:", IMessageConstants.FSM_STATIC_HINT_DESC, toolkit, composite);
		createHintLabel("Dynamic:", IMessageConstants.FSM_DYNAMIC_HINT_DESC, toolkit, composite);
		createHintSpace(toolkit, composite);
		createHintLabel("SCXML:", Messages.getString("IMC.FSM_SCXML_HINT_DESC"), toolkit, composite);
		createHintLabel(Messages.getString("IMC.FSM_SCXML_NEW")
				+ ":", IMessageConstants.FSM_SCXML_NEW_DESC, toolkit, composite);
		createHintLabel(Messages.getString("IMC.FSM_SCXML_EDIT")
				+ ":", IMessageConstants.FSM_SCXML_EDIT_DESC, toolkit, composite);
		createHintLabel(Messages.getString("IMC.FSM_SCXML_IMPORT")
				+ ":", IMessageConstants.FSM_SCXML_IMPORT_DESC, toolkit, composite);
		createHintSpace(toolkit, composite);
		createHintLabel(Messages.getString("IMC.FSM_HINT_EVENT_PORT"), toolkit, composite);
		createHintLabel(Messages.getString("IMC.FSM_HINT_PORT_NAME_TITLE"), IMessageConstants.FSM_PORT_NAME_DESC, toolkit, composite);
		createHintLabel(Messages.getString("IMC.FSM_HINT_VAR_NAME_TITLE"), Messages.getString("IMC.FSM_HINT_VAR_NAME_DESC"), toolkit, composite);
		createHintSpace(toolkit, composite);
		createHintLabel(Messages.getString("IMC.FSM_HINT_EVENT_LIST"), toolkit, composite);
		createHintLabel(Messages.getString("IMC.FSM_HINT_EVENT_NAME_TITLE"), Messages.getString("IMC.FSM_HINT_EVENT_NAME_DESC"), toolkit, composite);
		createHintLabel(Messages.getString("IMC.FSM_HINT_SOURCE_TITLE"), IMessageConstants.FSM_SORCE_DESC, toolkit, composite);
		createHintLabel(Messages.getString("IMC.FSM_HINT_TARGET_TITLE"), IMessageConstants.FSM_TARGET_DESC, toolkit, composite);
		createHintLabel(Messages.getString("IMC.FSM_HINT_DATA_TYPE_TITLE"), Messages.getString("IMC.FSM_HINT_DATA_TYPE_DESC"), toolkit, composite);
		createHintSpace(toolkit, composite);
		createHintLabel(Messages.getString("IMC.HINT_DOCUMENT_TITLE"), toolkit, composite);
		createHintLabel(Messages.getString("IMC.EVENT_DOC_DESCRIPTION"), Messages.getString("IMC.FSM_HINT_DOC_DESC_DESC"), toolkit, composite);
		createHintLabel(Messages.getString("IMC.EVENT_DOC_TYPE"), Messages.getString("IMC.FSM_HINT_DOC_TYPE_DESC"), toolkit, composite);
		createHintLabel(Messages.getString("IMC.DATAPORT_LBL_DATANUM"), Messages.getString("IMC.FSM_HINT_DOC_NUM_DESC"), toolkit, composite);
		createHintLabel(Messages.getString("IMC.DATAPORT_LBL_SEMANTICS"), IMessageConstants.FSM_DOC_DETAIL_DESC, toolkit, composite);
		createHintLabel(Messages.getString("IMC.DATAPORT_LBL_UNIT"), Messages.getString("IMC.FSM_HINT_DOC_UNIT_DESC"), toolkit, composite);
		createHintLabel(Messages.getString("IMC.DATAPORT_LBL_OPERAT"), Messages.getString("IMC.FSM_HINT_DOC_OPE_DESC"), toolkit, composite);
	}
	
	private void createFSMSection(FormToolkit toolkit, ScrolledForm form) {
		Section sctOverView = toolkit.createSection(form.getBody(),
				Section.TITLE_BAR | Section.EXPANDED | Section.TWISTIE);
		sctOverView.setText("FSM Component Profile");
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.BEGINNING;
		sctOverView.setLayoutData(gridData);
		//
		Composite composite = toolkit.createComposite(sctOverView, SWT.NULL);
		composite.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		toolkit.paintBordersFor(composite);
		GridLayout gl = new GridLayout(4, false);
		composite.setLayout(gl);
		GridData gd = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(gd);
		sctOverView.setClient(composite);
		//
		fsmBtn = createRadioCheckButton(toolkit, composite, "FSM", SWT.CHECK);
		gd = new GridData();
		gd.verticalAlignment = SWT.CENTER;
		fsmBtn.setLayoutData(gd);
		fsmBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				staticBtn.setEnabled(fsmBtn.getSelection());
				dynamicBtn.setEnabled(fsmBtn.getSelection());
				newBtn.setEnabled(fsmBtn.getSelection());
				editBtn.setEnabled(fsmBtn.getSelection() && editor.getRtcParam().getFsmParam()!=null);
				importBtn.setEnabled(fsmBtn.getSelection());
				if(fsmBtn.getSelection()) {
					editor.getRtcParam().addFSMPort();
				} else {
					editor.getRtcParam().deleteFSMPort();
				}
				//
				portNameText.setEnabled(fsmBtn.getSelection());
				variableNameText.setEnabled(fsmBtn.getSelection());
				eventTableViewer.getTable().setEnabled(fsmBtn.getSelection());
				eventNameDetailText.setEnabled(fsmBtn.getSelection());
				sourceText.setEnabled(fsmBtn.getSelection());
				targetText.setEnabled(fsmBtn.getSelection());
				dataTypeText.setEnabled(fsmBtn.getSelection());
				descriptionText.setEnabled(fsmBtn.getSelection());
				typeText.setEnabled(fsmBtn.getSelection());
				numberText.setEnabled(fsmBtn.getSelection());
				semanticsText.setEnabled(fsmBtn.getSelection());
				unitText.setEnabled(fsmBtn.getSelection());
				operationText.setEnabled(fsmBtn.getSelection());
				
				editor.updateEMFDataPorts(
						editor.getRtcParam().getInports(), editor.getRtcParam().getOutports(),
						editor.getRtcParam().getEventports(), editor.getRtcParam().getServicePorts());
				update();
			}
		});
		
		Group compGroup = new Group(composite, SWT.NONE);
		compGroup.setLayout(new GridLayout(3, false));
		gd = new GridData();
		gd.horizontalSpan = 3;
		compGroup.setLayoutData(gd);
		
		staticBtn = createRadioCheckButton(toolkit, compGroup, IRtcBuilderConstants.FSMTYTPE_STATIC, SWT.RADIO);
		dynamicBtn = createRadioCheckButton(toolkit, compGroup, IRtcBuilderConstants.FSMTYTPE_DYNAMIC, SWT.RADIO);
		staticBtn.setSelection(true);
		dynamicBtn.setSelection(false);
		
		toolkit.createLabel(composite, "SCXML");
		
		newBtn = toolkit.createButton(composite, Messages.getString("IMC.FSM_SCXML_NEW"), SWT.PUSH);
		gd = new GridData();
		gd.widthHint = 100;
		gd.horizontalAlignment = GridData.BEGINNING;
		newBtn.setLayoutData(gd);
		newBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					if(scxmlEditor==null) {
						String cmpName = editor.getRtcParam().getName() + "FSM.scxml";
						if(observer==null) {
							observer = new SCXMLReceiver(editor.getRtcParam());
						}
						IWorkspace workspace = ResourcesPlugin.getWorkspace();
						IWorkspaceRoot root = workspace.getRoot();
						IProject project = root.getProject(editor.getRtcParam().getOutputProject());
						IFile fsmFile  = project.getFile(cmpName);
						if(fsmFile.exists()) {
							boolean confirm = MessageDialog.openConfirm(getSite().getShell(), "FSM Editor",
									Messages.getString("IMC.FSM_OVERWRITE"));
							if (!confirm) return;
						}
						List<EventParam> eventList = editor.getRtcParam().getEventports().get(0).getEvents();
						scxmlEditor = SCXMLGraphEditor.openEditor(null, observer, defaultTypeList, eventList, false);
					} else {
						JFrame frame = (JFrame) SwingUtilities.windowForComponent(scxmlEditor);
						frame.setAlwaysOnTop(true);
						frame.setAlwaysOnTop(false);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		editBtn = toolkit.createButton(composite, Messages.getString("IMC.FSM_SCXML_EDIT"), SWT.PUSH);
		gd = new GridData();
		gd.widthHint = 100;
		gd.horizontalAlignment = GridData.END;
		editBtn.setLayoutData(gd);
		editBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					if(scxmlEditor==null) {
						IWorkspace workspace = ResourcesPlugin.getWorkspace();
						IWorkspaceRoot root = workspace.getRoot();
						IProject project = root.getProject(editor.getRtcParam().getOutputProject());
						if(observer==null) {
							observer = new SCXMLReceiver(editor.getRtcParam());
						}
						//
						String dummyName = ".Dummy.scxml";
						IFile dummyFile  = project.getFile(dummyName);
						if(dummyFile.exists()==false) {
							try {
								dummyFile.create(null, true, null);
							} catch (CoreException ex) {
								ex.printStackTrace();
							}
						}
						String strPath = dummyFile.getLocation().toOSString();
						String xmlSplit[] = editor.getRtcParam().getFsmContents().split(System.lineSeparator());
						try {
							BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(strPath), "UTF-8"));
							for (String s : xmlSplit) {
								if(s.length()==0) continue;
								writer.write(s);
								writer.newLine();
							}
							writer.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						try {
							project.refreshLocal(IResource.DEPTH_INFINITE, null);
						} catch (CoreException e1) {
							throw new RuntimeException(IRTCBMessageConstants.ERROR_GENERATE_FAILED);
						}
						
						List<EventParam> eventList = editor.getRtcParam().getEventports().get(0).getEvents();
						scxmlEditor = SCXMLGraphEditor.openEditor(dummyFile.getLocation().toOSString(), observer, defaultTypeList, eventList, false);
					} else {
						JFrame frame = (JFrame) SwingUtilities.windowForComponent(scxmlEditor);
						frame.setAlwaysOnTop(true);
						frame.setAlwaysOnTop(false);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		editBtn.setEnabled(editor.getRtcParam().getFsmParam()!=null);
		/////
		importBtn = toolkit.createButton(composite, Messages.getString("IMC.FSM_SCXML_IMPORT"), SWT.PUSH);
		gd = new GridData();
		gd.widthHint = 100;
		gd.horizontalAlignment = GridData.BEGINNING;
		importBtn.setLayoutData(gd);
		importBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String cmpName = editor.getRtcParam().getName() + "FSM.scxml";
				IWorkspace workspace = ResourcesPlugin.getWorkspace();
				IWorkspaceRoot root = workspace.getRoot();
				IProject project = root.getProject(editor.getRtcParam().getOutputProject());
				IFile fsmFile  = project.getFile(cmpName);
				if(fsmFile.exists()) {
					boolean confirm = MessageDialog.openConfirm(getSite().getShell(), "FSM Editor",
							Messages.getString("IMC.FSM_OVERWRITE"));
					if (!confirm) return;
				}
				/////
				FileDialog dialog = new FileDialog(getEditorSite().getShell(), SWT.OPEN);
				dialog.setFilterNames(new String[]{"SCXML File", "XML File"});
				dialog.setFilterExtensions(new String[]{"*.scxml", "*.xml"});
				String newFile = dialog.open();
				if (newFile == null) return;
				/////
				String strPath = fsmFile.getLocation().toOSString();
				try {
					fsmFile.delete(true, null);
    				Path inputPath = FileSystems.getDefault().getPath(newFile);
    				Path outputPath = FileSystems.getDefault().getPath(strPath);			        				
					Files.copy(inputPath, outputPath);
					project.refreshLocal(IResource.DEPTH_INFINITE, null);
					//インポートしたファイルの読み込み
					ScXMLHandler scHandler = new ScXMLHandler();
					StringBuffer buffer = new StringBuffer();
					StateParam rootState = scHandler.parseSCXML(strPath, buffer);
					if(rootState!=null) {
						editor.getRtcParam().setFsmParam(rootState);
						editor.getRtcParam().setFsmContents(buffer.toString());
						editor.getRtcParam().parseEvent();
						if(eventTableViewer.getInput()==null) {
							eventTableViewer.setInput(editor.getRtcParam().getEventports().get(0).getEvents());
						}
						eventTableViewer.refresh();
					}
					editBtn.setEnabled(editor.getRtcParam().getFsmParam()!=null);
					
					MessageDialog.openInformation(getSite().getShell(), Messages.getString("IMC.FSM_SCXML_IMPORT"), Messages.getString("IMC.FSM_IMPORT_OK"));
				} catch (IOException e1) {
					MessageDialog.openWarning(getSite().getShell(), Messages.getString("IMC.FSM_SCXML_IMPORT"), Messages.getString("IMC.FSM_IMPORT_NG"));
					e1.printStackTrace();
				} catch (CoreException e1) {
				}
			}
		});
		staticBtn.setEnabled(false);
		dynamicBtn.setEnabled(false);
		newBtn.setEnabled(false);
		editBtn.setEnabled(false);
		importBtn.setEnabled(false);
		/////
		Group portGroup = new Group(composite, SWT.NONE);
		portGroup.setText(IPreferenceMessageConstants.PORT_TITLE_EVENT_PORT);
		portGroup.setLayout(new GridLayout(2, false));
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 4;
		portGroup.setLayoutData(gd);
		
		portNameText = createLabelAndText(toolkit, portGroup,
				Messages.getString("IMC.DATAPORT_LBL_PORTNAME"), SWT.BORDER);
		variableNameText = createLabelAndText(toolkit, portGroup,
				Messages.getString("IMC.CONFIGURATION_LBL_VARNAME"), SWT.BORDER);
		/////
		Group eventGroup = new Group(composite, SWT.NONE);
		eventGroup.setText(Messages.getString("IMC.EVENT_LIST"));
		eventGroup.setLayout(new GridLayout(2, false));
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 4;
		eventGroup.setLayoutData(gd);
		
		eventTableViewer = createTableViewer(toolkit, eventGroup, 70);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		gd.heightHint = 70;
		gd.grabExcessHorizontalSpace = true;
		eventTableViewer.getTable().setLayoutData(gd);
		super.createColumn(eventTableViewer, Messages.getString("IMC.EVENT_NAME"), 100);
		super.createColumn(eventTableViewer, Messages.getString("IMC.EVENT_SOURCE"), 100);
		super.createColumn(eventTableViewer, Messages.getString("IMC.EVENT_TARGET"), 100);
		super.createColumn(eventTableViewer, Messages.getString("IMC.DATAPORT_TBLLBL_DATATYPE"), 100);
		eventTableViewer.setLabelProvider(new EventParamLabelProvider());
		//
		eventTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection selection = (StructuredSelection)event.getSelection();
				selectParam = (EventParam)selection.getFirstElement();
				if( selectParam != null ) {
					StringBuffer portName = new StringBuffer(selectParam.getName());
					eventNameDetailText.setText(portName.toString());
					sourceText.setText(selectParam.getSource());
					targetText.setText(selectParam.getTarget());
					if(selectParam.getDataType()!=null && 0<selectParam.getDataType().length()) {
						dataTypeText.setText(selectParam.getDataType());
					} else {
						dataTypeText.setText("");
					}
					descriptionText.setText(StringUtil.getDisplayDocText(selectParam.getDoc_description()));
					typeText.setText(StringUtil.getDisplayDocText(selectParam.getDoc_type()));
					numberText.setText(StringUtil.getDisplayDocText(selectParam.getDoc_num()));
					semanticsText.setText(StringUtil.getDisplayDocText(selectParam.getDoc_semantics()));
					unitText.setText(StringUtil.getDisplayDocText(selectParam.getDoc_unit()));
					operationText.setText(StringUtil.getDisplayDocText(selectParam.getDoc_operation()));
				}
			}
		});
		
		eventNameDetailText = createLabelAndText(toolkit, eventGroup,
				Messages.getString("IMC.EVENT_NAME"), SWT.BORDER);
		eventNameDetailText.setEditable(false);
		eventNameDetailText.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		sourceText = createLabelAndText(toolkit, eventGroup,
				Messages.getString("IMC.EVENT_SOURCE"), SWT.BORDER);
		sourceText.setEditable(false);
		sourceText.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		targetText = createLabelAndText(toolkit, eventGroup,
				Messages.getString("IMC.EVENT_TARGET"), SWT.BORDER);
		targetText.setEditable(false);
		targetText.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		dataTypeText = createLabelAndText(toolkit, eventGroup,
				Messages.getString("IMC.DATAPORT_TBLLBL_DATATYPE"), SWT.BORDER);
		dataTypeText.setEditable(false);
		dataTypeText.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		/////
		Group documentGroup = new Group(eventGroup, SWT.SHADOW_ETCHED_IN);
		documentGroup.setLayout(new GridLayout(2, false));
		documentGroup.setText(Messages.getString("IMC.HINT_DOCUMENT_TITLE"));
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 4;
		documentGroup.setLayoutData(gd);
		//
		descriptionText = createLabelAndText(toolkit, documentGroup,
				Messages.getString("IMC.EVENT_DOC_DESCRIPTION"), SWT.MULTI | SWT.V_SCROLL | SWT.WRAP | SWT.BORDER);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = 50;
		descriptionText.setLayoutData(gridData);
		descriptionText.setEditable(false);
		descriptionText.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		typeText = createLabelAndText(toolkit, documentGroup,
				Messages.getString("IMC.EVENT_DOC_TYPE"), SWT.BORDER);
		typeText.setEditable(false);
		typeText.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		numberText = createLabelAndText(toolkit, documentGroup,
				Messages.getString("IMC.DATAPORT_LBL_DATANUM"), SWT.BORDER);
		numberText.setEditable(false);
		numberText.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		semanticsText = createLabelAndText(toolkit, documentGroup,
				Messages.getString("IMC.DATAPORT_LBL_SEMANTICS"), SWT.MULTI | SWT.V_SCROLL | SWT.WRAP | SWT.BORDER);
		semanticsText.setLayoutData(gridData);
		semanticsText.setEditable(false);
		semanticsText.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		unitText = createLabelAndText(toolkit, documentGroup,
				Messages.getString("IMC.DATAPORT_LBL_UNIT"), SWT.BORDER);
		unitText.setEditable(false);
		unitText.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));

		operationText = createLabelAndText(toolkit, documentGroup,
				Messages.getString("IMC.DATAPORT_LBL_OPERAT"), SWT.MULTI | SWT.V_SCROLL | SWT.WRAP | SWT.BORDER);
		operationText.setLayoutData(gridData);
		operationText.setEditable(false);
		operationText.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
	}

	public void update() {
		RtcParam rtcParam = editor.getRtcParam();
		if( fsmBtn != null ) {
			rtcParam.setProperty(IRtcBuilderConstants.PROP_TYPE_FSM, Boolean.valueOf(fsmBtn.getSelection()).toString());
			//
			String fsmCompType = "";
			if(dynamicBtn.getSelection()) {
				fsmCompType = IRtcBuilderConstants.FSMTYTPE_DYNAMIC;
			} else if(staticBtn.getSelection()) {
				fsmCompType = IRtcBuilderConstants.FSMTYTPE_STATIC;
			}
			rtcParam.setProperty(IRtcBuilderConstants.PROP_TYPE_FSMTYTPE, fsmCompType);
			//
			if(0<rtcParam.getEventports().size()) {
				EventPortParam eport = rtcParam.getEventports().get(0);
				eport.setName(portNameText.getText());
				eport.setVarname(variableNameText.getText());
				editor.updateEMFDataPorts(
						editor.getRtcParam().getInports(), editor.getRtcParam().getOutports(),
						editor.getRtcParam().getEventports(), editor.getRtcParam().getServicePorts());
			}
		}

		editor.updateDirty();
	}

	/**
	 * データをロードする
	 */
	public void load() {
		if(fsmBtn==null) return;
		RtcParam rtcParam = editor.getRtcParam();

		PropertyParam target = rtcParam.getProperty(IRtcBuilderConstants.PROP_TYPE_FSM);
		if(target==null) {
			fsmBtn.setSelection(false);
		} else {
			fsmBtn.setSelection(Boolean.valueOf(target.getValue()));
		}
		staticBtn.setEnabled(fsmBtn.getSelection());
		dynamicBtn.setEnabled(fsmBtn.getSelection());
		newBtn.setEnabled(fsmBtn.getSelection());
		editBtn.setEnabled(fsmBtn.getSelection() && editor.getRtcParam().getFsmParam()!=null);
		importBtn.setEnabled(fsmBtn.getSelection());
		//
		portNameText.setEnabled(fsmBtn.getSelection());
		variableNameText.setEnabled(fsmBtn.getSelection());
		eventTableViewer.getTable().setEnabled(fsmBtn.getSelection());
		eventNameDetailText.setEnabled(fsmBtn.getSelection());
		sourceText.setEnabled(fsmBtn.getSelection());
		targetText.setEnabled(fsmBtn.getSelection());
		dataTypeText.setEnabled(fsmBtn.getSelection());
		descriptionText.setEnabled(fsmBtn.getSelection());
		typeText.setEnabled(fsmBtn.getSelection());
		numberText.setEnabled(fsmBtn.getSelection());
		semanticsText.setEnabled(fsmBtn.getSelection());
		unitText.setEnabled(fsmBtn.getSelection());
		operationText.setEnabled(fsmBtn.getSelection());
		//
		PropertyParam fsmType = rtcParam.getProperty(IRtcBuilderConstants.PROP_TYPE_FSMTYTPE);
		if(fsmType!=null) {
			String fsmCompType = fsmType.getValue();
			if(fsmCompType.equals(IRtcBuilderConstants.FSMTYTPE_DYNAMIC) ) {
				dynamicBtn.setSelection(true);
				staticBtn.setSelection(false);
			} else if(fsmCompType.equals(IRtcBuilderConstants.FSMTYTPE_STATIC)) {
				dynamicBtn.setSelection(false);
				staticBtn.setSelection(true);
			}
		}
		//
		String targetFile = editor.getRtcParam().getName() + "FSM.scxml";
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject project = root.getProject(editor.getRtcParam().getOutputProject());
		IFile fsmFile  = project.getFile(targetFile);
		if(fsmFile.exists()) {
			targetFile = fsmFile.getLocation().toOSString();
			ScXMLHandler handler = new ScXMLHandler();
			StringBuffer buffer = new StringBuffer();
			StateParam rootState = handler.parseSCXML(targetFile, buffer);
			if(rootState!=null) {
				rtcParam.setFsmParam(rootState);
				rtcParam.setFsmContents(buffer.toString());
			}
		}
		//
		if(0<rtcParam.getEventports().size()) {
			EventPortParam eport = rtcParam.getEventports().get(0);
			portNameText.setText(eport.getName());
			variableNameText.setText(eport.getVarname());
			eventTableViewer.setInput(eport.getEvents());
		}
		clearText();
	}

	public String validateParam() {
		String result = null;
		RtcParam rtcParam = editor.getRtcParam();
		if(rtcParam==null) return result;
		PropertyParam fsm = rtcParam.getProperty(IRtcBuilderConstants.PROP_TYPE_FSM);
		if(fsm==null) return result;
		
		if(Boolean.valueOf(fsm.getValue())) {
			PropertyParam fsmType = rtcParam.getProperty(IRtcBuilderConstants.PROP_TYPE_FSMTYTPE);
			if(fsmType==null) {
				result = Messages.getString("IMC.FSM_NOT_SELECTED");
			} else {
				String strType = fsmType.getValue();
				if(!(strType.equals(IRtcBuilderConstants.FSMTYTPE_STATIC) || strType.equals(IRtcBuilderConstants.FSMTYTPE_DYNAMIC))) {
					result = Messages.getString("IMC.FSM_TYPE_INVALID");
				}
			}
			
			StateParam fsmParam = rtcParam.getFsmParam();
			if(fsmParam==null) {
				result = Messages.getString("IMC.FSM_NO_SM");
			} else {
				List<String> stateList = new ArrayList<String>();
				stateList.add(fsmParam.getName());
				for(StateParam param : fsmParam.getAllStateList() ) {
					if(stateList.contains(param.getName())) {
						result = Messages.getString("IMC.STATE_DUPL1") + param.getName() + Messages.getString("IMC.STATE_DUPL2");
						break;
					} else {
						stateList.add(param.getName());
					}
				}
				//
				if(fsmParam.existInitialState()==false) {
					result = Messages.getString("IMC.FSM_NOT_INITIAL");
				}
			}
		}
		return result;
	}
	
	private void clearText() {
		eventNameDetailText.setText("");
		dataTypeText.setText("");
		descriptionText.setText("");
		typeText.setText("");
		numberText.setText("");
		semanticsText.setText("");
		unitText.setText("");
		operationText.setText("");
	}
	
	class SCXMLReceiver implements SCXMLNotifier {
		private RtcParam rtcParam;
		
		public SCXMLReceiver(RtcParam param) {
			this.rtcParam = param;
		}
		
		@Override
		public void notifyContents(String contents, List<EventParam> eventList) {
    		editor.updateDirty();
			ScXMLHandler handler = new ScXMLHandler();
			StateParam rootState = handler.parseSCXMLStr(contents);
			if(rootState!=null) {
				rtcParam.setFsmParam(rootState);
				rtcParam.setFsmContents(contents);
				
				List<EventParam> newEventList = new ArrayList<EventParam>();
				List<TransitionParam> transList = rootState.getAllTransList();
				for(TransitionParam trans : transList) {
					StateParam source = rootState.getStateParam(trans.getSource());
					StateParam target = rootState.getStateParam(trans.getTarget());
					if(source.isInitial() || target.isInitial()) continue;
					
					EventParam newParam = new EventParam();
					newParam.setName(trans.getEvent());
					newParam.setCondition(trans.getCondition());
					newParam.setSource(trans.getSource());
					newParam.setTarget(trans.getTarget());
					for(EventParam event : eventList) {
						if(newParam.checkSame(event)) {
							newParam.replaceContents(event);
							break;
						}
					}
					newEventList.add(newParam);
				}
				rtcParam.getEventports().get(0).getEvents().clear();
				rtcParam.getEventports().get(0).getEvents().addAll(newEventList);
				editor.updateDirty();
				PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
					public void run() {
						editBtn.setEnabled(true);
						if(eventTableViewer.getInput()==null) {
							eventTableViewer.setInput(rtcParam.getEventports().get(0).getEvents());
						}
						eventTableViewer.refresh();
					}
				});
			}
			scxmlEditor = null;
		}
	}
	
	private class EventParamLabelProvider extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			if (element instanceof EventParam == false) return null;
			EventParam param = (EventParam) element;
			String result = null;
			if (columnIndex == 0) {
				result = param.getName();
			} else if (columnIndex == 1) {
				result = param.getSource();
			} else if (columnIndex == 2) {
				result = param.getTarget();
			} else if (columnIndex == 3) {
				result = param.getDataType();
			}

			return result;
		}
	}
}
