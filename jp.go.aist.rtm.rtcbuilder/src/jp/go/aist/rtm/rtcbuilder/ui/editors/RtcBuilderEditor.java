package jp.go.aist.rtm.rtcbuilder.ui.editors;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeFactory;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;
import jp.go.aist.rtm.rtcbuilder.extension.AddFormPageExtension;
import jp.go.aist.rtm.rtcbuilder.extension.EditorExtension;
import jp.go.aist.rtm.rtcbuilder.generator.ProfileHandler;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ParamUtil;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;
import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;
import jp.go.aist.rtm.rtcbuilder.model.component.BuildView;
import jp.go.aist.rtm.rtcbuilder.model.component.Component;
import jp.go.aist.rtm.rtcbuilder.model.component.ComponentFactory;
import jp.go.aist.rtm.rtcbuilder.model.component.DataInPort;
import jp.go.aist.rtm.rtcbuilder.model.component.DataOutPort;
import jp.go.aist.rtm.rtcbuilder.model.component.InterfaceDirection;
import jp.go.aist.rtm.rtcbuilder.model.component.PortDirection;
import jp.go.aist.rtm.rtcbuilder.model.component.ServiceInterface;
import jp.go.aist.rtm.rtcbuilder.model.component.ServicePort;
import jp.go.aist.rtm.rtcbuilder.ui.preference.ComponentPreferenceManager;
import jp.go.aist.rtm.rtcbuilder.ui.preference.DocumentPreferenceManager;
import jp.go.aist.rtm.rtcbuilder.util.FileUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionFilter;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.FileEditorInput;
import org.openrtp.namespaces.rtc.version02.RtcProfile;

import com.sun.org.apache.xerces.internal.jaxp.datatype.DatatypeFactoryImpl;

/**
 * RtcBuilder�G�f�B�^
 */
public class RtcBuilderEditor extends FormEditor implements IActionFilter {
	public static final String RTC_BUILDER_EDITOR_ID = RtcBuilderEditor.class
			.getName();

	public static String RTCBUILDER_NEW_EDITOR_PATH = "RtcBuilder";
	//
	public static String ECLPSE_VERSION_33 = "3.3";
	//
	private int BASIC_FORM_INDEX = 0;
	private int ACTIVITY_FORM_INDEX = 1;
	private int DATAPORT_FORM_INDEX = 2;
	private int SERVICEPORT_FORM_INDEX = 3;
	private int CONFIGURATION_FORM_INDEX = 4;
	private int DOCUMENTATION_FORM_INDEX = 5;
	private int LANGUAGE_FORM_INDEX = 6;
	private int RTCXML_FORM_INDEX = 7;

	private void addPageIndex(int target) {
		if (target <= BASIC_FORM_INDEX)BASIC_FORM_INDEX = BASIC_FORM_INDEX + 1;
		if (target <= ACTIVITY_FORM_INDEX)ACTIVITY_FORM_INDEX = ACTIVITY_FORM_INDEX + 1;
		if (target <= DATAPORT_FORM_INDEX)DATAPORT_FORM_INDEX = DATAPORT_FORM_INDEX + 1;
		if (target <= SERVICEPORT_FORM_INDEX)SERVICEPORT_FORM_INDEX = SERVICEPORT_FORM_INDEX + 1;
		if (target <= CONFIGURATION_FORM_INDEX)CONFIGURATION_FORM_INDEX = CONFIGURATION_FORM_INDEX + 1;
		if (target <= DOCUMENTATION_FORM_INDEX)DOCUMENTATION_FORM_INDEX = DOCUMENTATION_FORM_INDEX + 1;
		if (target <= LANGUAGE_FORM_INDEX)LANGUAGE_FORM_INDEX = LANGUAGE_FORM_INDEX + 1;
		if (target <= RTCXML_FORM_INDEX)RTCXML_FORM_INDEX = RTCXML_FORM_INDEX + 1;
	}
	private void shiftPageIndex() {
		for (Integer i : customFormPages.keySet()) {
			addPageIndex(i.intValue());
		}
	}


	private boolean isDirty;
	private String title;

	private GeneratorParam generatorParam;
	private BuildView buildview;

	private BasicEditorFormPage basicFormPage;
	private DataPortEditorFormPage dataPortFormPage;
	private ServicePortEditorFormPage servicePortFormPage;
	private ConfigurationEditorFormPage configurationFormPage;
	private LanguageEditorFormPage languageFormPage;
	private RtcXmlEditorFormPage rtcXmlFormPage;
	private DocumentEditorFormPage documentFormPage;
	private ActivityEditorFormPage activityFormPage;
	
	private Map<Integer, AbstractCustomFormPage> customFormPages;
	
	//
	private List<GenerateManager> managerList = null;

	private IPageChangedListener pageChangedListener = new IPageChangedListener(){
		public void pageChanged(PageChangedEvent event) {
			if( event!=null ){
				if( event.getSelectedPage() instanceof AbstractEditorFormPage ){
					((AbstractEditorFormPage)event.getSelectedPage()).pageSelected();
				}
			}
		}
	};
	
	public RtcBuilderEditor() {
	}

	private IEditorInput load(IEditorInput input, IEditorSite site)
			throws PartInitException {
		boolean newOpenEditor = input instanceof NullEditorInput;// �V�K�G�f�B�^

		IEditorInput result = input;
		if (newOpenEditor) {
			//�V�K�G�f�B�^�I�[�v������
			createGeneratorParam();
		}

		if (newOpenEditor) {

		} else if (result instanceof FileEditorInput) {
			FileEditorInput fileEditorInput = ((FileEditorInput) result);
			try {
				ProfileHandler handler = new ProfileHandler();
				generatorParam = handler.restorefromXMLFile(fileEditorInput.getPath().toOSString());
				if( buildview==null ) buildview = ComponentFactory.eINSTANCE.createBuildView();
				updateEMFModuleName(this.getRtcParam().getName());
				updateEMFDataInPorts(this.getRtcParam().getInports());
				updateEMFDataOutPorts(this.getRtcParam().getOutports());
				updateEMFServiceOutPorts(this.getRtcParam().getServicePorts());
			} catch (Exception e) {
				createGeneratorParam();
			}
		}

		if (newOpenEditor) {
			title = "RtcBuilder";
		} else if (result instanceof FileEditorInput) {
			String[] target = ((FileEditorInput) result).getPath().segments();
			if( target.length>1 ) {
				title = target[target.length-2];
			} else {
				title = ((FileEditorInput) result).getPath().lastSegment();
			}
		}

		isDirty = false;
		firePropertyChange(IEditorPart.PROP_TITLE);

		if( basicFormPage != null )	 basicFormPage.load();
		allPagesReLoad();
		this.setInput(result);

		return result;
	}

	private void createGeneratorParam(){
		generatorParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(generatorParam);
		rtcParam.setSchemaVersion(IRtcBuilderConstants.SCHEMA_VERSION);
		//
		rtcParam.setName(ComponentPreferenceManager.getInstance().getBasic_ComponentName());
		rtcParam.setDescription(ComponentPreferenceManager.getInstance().getBasic_Description());
		rtcParam.setCategory(ComponentPreferenceManager.getInstance().getBasic_Category());
		rtcParam.setVersion(ComponentPreferenceManager.getInstance().getBasic_Version());
		rtcParam.setVender(ComponentPreferenceManager.getInstance().getBasic_VendorName());
		rtcParam.setComponentType(ComponentPreferenceManager.getInstance().getBasic_ComponentType());
		rtcParam.setActivityType(ComponentPreferenceManager.getInstance().getBasic_ActivityType());
		rtcParam.setComponentKind(ComponentPreferenceManager.getInstance().getBasic_ComponentKind());
		rtcParam.setMaxInstance(ComponentPreferenceManager.getInstance().getBasic_MaxInstances());
		rtcParam.setExecutionType(ComponentPreferenceManager.getInstance().getBasic_ExecutionType());
		rtcParam.setExecutionRate(ComponentPreferenceManager.getInstance().getBasic_ExecutionRate());
		//
		ArrayList<String> docs = DocumentPreferenceManager.getInstance().getDocumentValue();
		rtcParam.setActionImplemented(IRtcBuilderConstants.ACTIVITY_INITIALIZE, docs.get(IRtcBuilderConstants.ACTIVITY_INITIALIZE));
		rtcParam.setActionImplemented(IRtcBuilderConstants.ACTIVITY_FINALIZE, docs.get(IRtcBuilderConstants.ACTIVITY_FINALIZE));
		rtcParam.setActionImplemented(IRtcBuilderConstants.ACTIVITY_STARTUP, docs.get(IRtcBuilderConstants.ACTIVITY_STARTUP));
		rtcParam.setActionImplemented(IRtcBuilderConstants.ACTIVITY_SHUTDOWN, docs.get(IRtcBuilderConstants.ACTIVITY_SHUTDOWN));
		rtcParam.setActionImplemented(IRtcBuilderConstants.ACTIVITY_ACTIVATED, docs.get(IRtcBuilderConstants.ACTIVITY_ACTIVATED));
		rtcParam.setActionImplemented(IRtcBuilderConstants.ACTIVITY_DEACTIVATED, docs.get(IRtcBuilderConstants.ACTIVITY_DEACTIVATED));
		rtcParam.setActionImplemented(IRtcBuilderConstants.ACTIVITY_EXECUTE, docs.get(IRtcBuilderConstants.ACTIVITY_EXECUTE));
		rtcParam.setActionImplemented(IRtcBuilderConstants.ACTIVITY_ABORTING, docs.get(IRtcBuilderConstants.ACTIVITY_ABORTING));
		rtcParam.setActionImplemented(IRtcBuilderConstants.ACTIVITY_ERROR, docs.get(IRtcBuilderConstants.ACTIVITY_ERROR));
		rtcParam.setActionImplemented(IRtcBuilderConstants.ACTIVITY_RESET, docs.get(IRtcBuilderConstants.ACTIVITY_RESET));
		rtcParam.setActionImplemented(IRtcBuilderConstants.ACTIVITY_STATE_UPDATE, docs.get(IRtcBuilderConstants.ACTIVITY_STATE_UPDATE));
		rtcParam.setActionImplemented(IRtcBuilderConstants.ACTIVITY_RATE_CHANGED, docs.get(IRtcBuilderConstants.ACTIVITY_RATE_CHANGED));
		//
		rtcParam.setDocLicense(DocumentPreferenceManager.getInstance().getLicenseValue());
		rtcParam.setDocCreator(DocumentPreferenceManager.getInstance().getCreatorValue());
		//
		rtcParam.resetUpdated();
		generatorParam.getRtcParams().add(rtcParam);
		buildview = ComponentFactory.eINSTANCE.createBuildView();
	}
	
	@Override
	/**
	 * {@inheritDoc}
	 */
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		IEditorInput newInput = load(input, site);
		super.init(site, newInput);
		managerList = RtcBuilderPlugin.getDefault().getLoader().getManagerList();
		// �y�[�W�؂�ւ����̃C�x���g���Ǘ�
		addPageChangedListener(pageChangedListener);
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager();
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				RtcBuilderEditor.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(getContainer());
		getContainer().setMenu(menu);
		((IEditorSite) getSite()).registerContextMenu(menuMgr,
				new ISelectionProvider() {

					public void addSelectionChangedListener(
							ISelectionChangedListener listener) {
					}

					public ISelection getSelection() {
						return new StructuredSelection(RtcBuilderEditor.this);
					}

					public void removeSelectionChangedListener(
							ISelectionChangedListener listener) {
					}

					public void setSelection(ISelection selection) {
					}

				}, false);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(new Separator());
		// drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	@Override
	protected void createPages() {
		super.createPages();
		hookContextMenu();
	}

	/**
	 * {@inheritDoc}
	 */
	protected FormToolkit createToolkit(Display display) {
		return new FormToolkit(getSite().getShell().getDisplay());
	}

	/**
	 * {@inheritDoc}
	 */
	protected void addPages() {
		try {
			basicFormPage = new BasicEditorFormPage(this);
			addPage(BASIC_FORM_INDEX, basicFormPage);
			activityFormPage = new ActivityEditorFormPage(this);
			addPage(ACTIVITY_FORM_INDEX, activityFormPage);
			dataPortFormPage = new DataPortEditorFormPage(this);
			addPage(DATAPORT_FORM_INDEX, dataPortFormPage);
			servicePortFormPage = new ServicePortEditorFormPage(this);
			addPage(SERVICEPORT_FORM_INDEX, servicePortFormPage);
			configurationFormPage = new ConfigurationEditorFormPage(this);
			addPage(CONFIGURATION_FORM_INDEX, configurationFormPage);
			documentFormPage = new DocumentEditorFormPage(this);
			addPage(DOCUMENTATION_FORM_INDEX, documentFormPage);
			languageFormPage = new LanguageEditorFormPage(this);
			addPage(LANGUAGE_FORM_INDEX, languageFormPage);
			rtcXmlFormPage = new RtcXmlEditorFormPage(this);
			addPage(RTCXML_FORM_INDEX, rtcXmlFormPage);
			//
			setCustomEditors();
			customPagesOperation("add");
			shiftPageIndex();

		} catch (PartInitException e) {
			throw new RuntimeException(e); // system error
		}
	}

	private void customPagesOperation(String command) {
		if(customFormPages == null) return;
		
		for(Integer i : customFormPages.keySet()) {
			AbstractCustomFormPage customPage = customFormPages.get(i);
			if (customPage != null) {
				if(command.equals("add")) {
					try {
						addPage (i.intValue(),customPage);
						addPageIndex(i.intValue());
					} catch (PartInitException e) {
						e.printStackTrace();
					}
				} else if (command.equals("load")) {
					customPage.load();
				} else if (command.equals("update")) {
					customPage.update();
				}
			}
		}
	}

	protected void allPagesReLoad(){
		if( dataPortFormPage != null ) dataPortFormPage.load();
		if( servicePortFormPage != null ) servicePortFormPage.load();
		if( configurationFormPage != null ) configurationFormPage.load();
		if( languageFormPage != null ) languageFormPage.load();
		if( rtcXmlFormPage != null ) rtcXmlFormPage.load();
		if( documentFormPage != null ) documentFormPage.load();
		if( activityFormPage != null ) activityFormPage.load();
		//
		customPagesOperation("load");
	}
	
	protected void allUpdates(){
		basicFormPage.update();
		dataPortFormPage.updateForOutput();
		servicePortFormPage.update();
		configurationFormPage.updateForOutput();
		languageFormPage.update();
		documentFormPage.update();
		activityFormPage.update();
		//
		customPagesOperation("update");
	}

	protected void addDefaultComboValue(){
		basicFormPage.addDefaultComboValue();
	}

	public String validateParam() {
		String result = null;
		
		for( int intIdx=0; intIdx<this.pages.size(); intIdx++ ) {
			AbstractEditorFormPage page = (AbstractEditorFormPage)this.pages.get(intIdx);
			result = page.validateParam();
			if( result != null ){
				this.setActivePage(intIdx);
				return result;
			}
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	public void doSave(IProgressMonitor monitor) {
		boolean isRtcXml = this.getCurrentPage() == RTCXML_FORM_INDEX;
		boolean newOpenEditor = getEditorInput() instanceof NullEditorInput;// �V�K�G�f�B�^
		this.allUpdates();
		
		if (newOpenEditor) {
			doSaveAs();
			return;
		}

		if( this.getCurrentPage() == RTCXML_FORM_INDEX ) {
			try {
				ProfileHandler handler = new ProfileHandler();
				if( !handler.validateXml(this.getRtcParam().getRtcXml()) ) return;
			} catch (Exception e) {
				String errMessage = null;
				if( e.getCause()==null ) {
					errMessage = e.getMessage();
				} else {
					errMessage = e.getCause().toString();
				}
				MessageDialog.openError(getSite().getShell(), "XML Save Error", errMessage);
				return;
			}
		}else{
			// RTC.xml�y�[�W�ł͂Ȃ��Ƃ�
			try {
				ProfileHandler handler = new ProfileHandler();
				if( !handler.validateXml(handler.convert2XML(this.getGeneratorParam())) ) return;
			} catch (JAXBException ex) {
				boolean result = MessageDialog.openQuestion(
						getSite().getShell(),
						ex.getMessage(),
    					IMessageConstants.PROFILE_VALIDATE_ERROR_MESSAGE + System.getProperty("line.separator") + ex.getCause().toString()
    				); 
    			if( !result ) return;// �u�������v�̂Ƃ��͕ۑ����Ȃ�
			} catch (Exception e) {
				MessageDialog.openError(getSite().getShell(), "XML Save Error", e.getMessage());
				return;
			}
		}

		IFile file = ((IFileEditorInput) getEditorInput()).getFile();

		try {
			save(file, monitor, isRtcXml);
		} catch (CoreException e) {
			ErrorDialog.openError(getSite().getShell(), "Error During Save",
					"The current model could not be saved.", e.getStatus());
		} catch (Exception e) {
			MessageDialog.openError(getSite().getShell(), "Error During Save",
					"The current model could not be saved.");
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void doSaveAs() {
		final boolean isRtcXml = this.getCurrentPage() == RTCXML_FORM_INDEX;
		boolean newOpenEditor = getEditorInput() instanceof NullEditorInput;// �V�K�G�f�B�^

		if( this.getCurrentPage() == RTCXML_FORM_INDEX ) {
			try {
				ProfileHandler handler = new ProfileHandler();
				if( !handler.validateXml(this.getRtcParam().getRtcXml()) ) return;
			} catch (Exception e) {
				String errMessage = null;
				if( e.getCause()==null ) {
					errMessage = e.getMessage();
				} else {
					errMessage = e.getCause().toString();
				}
				MessageDialog.openError(getSite().getShell(), "XML Save Error", errMessage);
				return;
			}
		}else{
			// RTC.xml�y�[�W�ł͂Ȃ��Ƃ�
			try {
				ProfileHandler handler = new ProfileHandler();
				if( !handler.validateXml(handler.convert2XML(this.getGeneratorParam())) ) return;
			} catch (JAXBException ex) {
				boolean result = MessageDialog.openQuestion(
						getSite().getShell(),
						ex.getMessage(),
    					IMessageConstants.PROFILE_VALIDATE_ERROR_MESSAGE + System.getProperty("line.separator") + ex.getCause().toString()
    				); 
    			if( !result ) return;// �u�������v�̂Ƃ��͕ۑ����Ȃ�
			} catch (Exception e) {
				MessageDialog.openError(getSite().getShell(), "XML Save Error", e.getMessage());
				return;
			}
		}
		
		IPath oldFile = null;
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		if (newOpenEditor) {
			oldFile = new Path(root.getLocation().toOSString());
			// void
		} else {
			oldFile = ((FileEditorInput) getEditorInput()).getFile().getProject().getLocation();
		}

//		final IPath newPath = getFilePathByDialog(oldFile, SWT.SAVE);
		final IPath newPath = FileUtil.getDirectoryPathByDialog(oldFile);

		if (newPath == null)
			return;

		if (newPath.toFile().exists() == false) {
			try {
				newPath.toFile().createNewFile();
			} catch (IOException e) {
				MessageDialog.openError(getSite().getShell(), "Error",
						IMessageConstants.CREATE_FILE_ERROR + newPath.toOSString());
				return;
			}
		}

		final IFile newFile = root.getFileForLocation(newPath);

		ProgressMonitorDialog progressMonitorDialog = new ProgressMonitorDialog(
				getSite().getShell());

		try {
			progressMonitorDialog.run(false, false,
					new IRunnableWithProgress() {
						public void run(IProgressMonitor monitor)
								throws InvocationTargetException,
								InterruptedException {
							try {
								if (newPath.toFile().exists() == false) {
									try {
										newPath.toFile().createNewFile();
									} catch (IOException e) {
										throw new RuntimeException(e); // SystemError
									}
								}

								save(newFile, monitor, isRtcXml);
								// getMultiPageCommandStackListener().markSaveLocations();
							} catch (CoreException e) {
							} catch (Exception e) {
							}
						}
					});
		} catch (Exception e) {
			throw new RuntimeException(e); // SystemError
		}

	}

	/**
	 * {@inheritDoc}
	 * @throws Exception 
	 */
	private void save(IFile file, IProgressMonitor progressMonitor, boolean blnRtcXml)
			throws Exception {

		if (null == progressMonitor)
			progressMonitor = new NullProgressMonitor();

		progressMonitor.beginTask("Saving ", 2);
		String xmlFile = "";
		if( blnRtcXml ) {
	        xmlFile = this.getRtcParam().getRtcXml();
		} else {
			DatatypeFactory dateFactory = new DatatypeFactoryImpl();
			String dateTime = dateFactory.newXMLGregorianCalendar(new GregorianCalendar()).toString();
			for(RtcParam rtcParam : generatorParam.getRtcParams() ) {
				rtcParam.setUpdateDate(dateTime);
			}
			ProfileHandler handler = new ProfileHandler();
			xmlFile = handler.convert2XML(generatorParam);
		}
		progressMonitor.worked(15);
		//
		
		IProject projectHandle = file.getProject();
		try {
			IFile rtcxml = projectHandle.getFile(IRtcBuilderConstants.DEFAULT_RTC_XML);
			if( rtcxml.exists()) rtcxml.delete(true, null);
			rtcxml.create(new ByteArrayInputStream(xmlFile.getBytes("UTF-8")), true, null);
			//
			setInput(new FileEditorInput(rtcxml));
			this.getRtcParam().setRtcXml(xmlFile);
			//
			// isDirty = false;
			// firePropertyChange(IEditorPart.PROP_DIRTY);
			getRtcParam().resetUpdated();
			updateDirty();
		} catch (UnsupportedEncodingException e) {
			IStatus status = new Status(IStatus.ERROR, RtcBuilderPlugin
						.getDefault().getClass().getName(), 0,
						"Error writing file.", e);
			progressMonitor.done();
			throw new CoreException(status);
		} catch (NullPointerException ex) {
			MessageDialog.openError(getSite().getShell(), "Error",
					"Error writing file.");
			progressMonitor.done();
			throw new CoreException(null);
		}
		//
		if( blnRtcXml ) {
			updateProfiles(xmlFile);
		}
		//
		if( rtcXmlFormPage != null ) rtcXmlFormPage.load();
		addDefaultComboValue();
		title = projectHandle.getName();
		firePropertyChange(IEditorPart.PROP_TITLE);
		progressMonitor.done();
	}

	protected void updateProfiles(String xmlFile) throws Exception {
		// RTC.xml�̓��e�𑼂̃y�[�W�ɔ��f
		ProfileHandler handler = new ProfileHandler();
		RtcProfile module = handler.restorefromXML(xmlFile);
		ParamUtil putil = new ParamUtil();
		getGeneratorParam().getRtcParams().set(0,
				putil.convertFromModule(module, generatorParam, managerList));
		getRtcParam().setRtcXml(xmlFile);
		//
		if (basicFormPage != null) basicFormPage.load();
		if (dataPortFormPage != null) dataPortFormPage.load();
		if (servicePortFormPage != null) servicePortFormPage.load();
		if (configurationFormPage != null) configurationFormPage.load();
		if (languageFormPage != null) languageFormPage.load();
		if (documentFormPage != null) documentFormPage.load();
		if (activityFormPage != null) activityFormPage.load();
		//
		customPagesOperation("load");
		//
		getRtcParam().resetUpdated();
		updateDirty();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isSaveAsAllowed() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isDirty() {
		return isDirty;
	}

	private void setDirty(boolean isDirty) {
		this.isDirty = isDirty;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void firePropertyChange(int propertyId) {
		super.firePropertyChange(propertyId);
	}

	/**
	 * �G�f�B�^���_�[�e�B�ɂ���B
	 */
	public void updateDirty() {
		setDirty(getRtcParam().isUpdated());
		firePropertyChange(IEditorPart.PROP_DIRTY);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * GeneratorParam���擾����
	 */
	public GeneratorParam getGeneratorParam() {
		return generatorParam;
	}
	public void setGeneratorParam(GeneratorParam genparam) {
		this.generatorParam = genparam;
	}

	/**
	 * RtcParam���擾����
	 */
	public RtcParam getRtcParam() {
		return generatorParam.getRtcParams().get(0);
	}

	/**
	 * EMF model���擾����
	 */
	public BuildView getEMFmodel() {
		return buildview;
	}
	
	public void open() {
		boolean save = false;
		if (isDirty()) {
			save = MessageDialog.openQuestion(getSite().getShell(), "",
					"�t�@�C�����ۑ�����Ă��܂���B�ۑ����܂����H");
		}

		if (save) {
			doSave(null);
		}

		final IPath newPath = FileUtil.getFilePathByDialog(null, SWT.OPEN);
		if( newPath==null ) return;
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		final IFile newFile = root.getFileForLocation(newPath);
		if (newFile != null) {
			try {
				load(new FileEditorInput(newFile), getEditorSite());
			} catch (PartInitException e) {
				e.printStackTrace(); // system error
				MessageDialog.openError(getSite().getShell(), "", e.getMessage());
			}
		} else {
			MessageDialog.openError(getSite().getShell(), "File Open Error", "Project���̃t�@�C����I�����Ă��������B");
		}
	}
	
	public void updateEMFModuleName(String name) {
		((Component)buildview.getComponents().get(0)).setComponent_Name(name);
	}

	public void updateEMFDataInPorts(List<DataPortParam> dataInPorts) {
		((Component)buildview.getComponents().get(0)).clearDataInports();
		for(int intIdx=0; intIdx<dataInPorts.size();intIdx++ ) {
			DataInPort dataInport= ComponentFactory.eINSTANCE.createDataInPort();
			dataInport.setInPort_Name(dataInPorts.get(intIdx).getName());
			dataInport.setIndex(intIdx);
			dataInport.setDirection(PortDirection.get(dataInPorts.get(intIdx).getPositionByIndex()));
			((Component)buildview.getComponents().get(0)).addDataInport(dataInport);
		}
	}

	public void updateEMFDataOutPorts(List<DataPortParam> dataOutPorts) {
		((Component)buildview.getComponents().get(0)).clearDataOutports();
		for(int intIdx=0; intIdx<dataOutPorts.size();intIdx++ ) {
			DataOutPort dataOutport= ComponentFactory.eINSTANCE.createDataOutPort();
			dataOutport.setOutPort_Name(dataOutPorts.get(intIdx).getName());
			dataOutport.setIndex(intIdx);
			dataOutport.setDirection(PortDirection.get(dataOutPorts.get(intIdx).getPositionByIndex()));
			((Component)buildview.getComponents().get(0)).addDataOutport(dataOutport);
		}
	}

	public void updateEMFServiceOutPorts(List<ServicePortParam> servicePorts) {
		((Component)buildview.getComponents().get(0)).clearServiceports();
		for(int intIdx=0; intIdx<servicePorts.size();intIdx++ ) {
			ServicePortParam srvParam = servicePorts.get(intIdx);
			ServicePort servicePort= ComponentFactory.eINSTANCE.createServicePort();
			servicePort.setServicePort_Name(srvParam.getName());
			servicePort.setIndex(intIdx);
			servicePort.setDirection(PortDirection.get(srvParam.getPositionByIndex()));
			for(int idxIf=0;idxIf<srvParam.getServicePortInterfaces().size();idxIf++) {
				ServicePortInterfaceParam srvIfParam = srvParam.getServicePortInterfaces().get(idxIf);
				ServiceInterface serviceIF = ComponentFactory.eINSTANCE.createServiceInterface();
				serviceIF.setServiceInterface_Name(srvIfParam.getName());
				serviceIF.setDirection(InterfaceDirection.get(srvIfParam.getDirection()));
				serviceIF.setParentDirection(servicePort.getDirection());
				serviceIF.setIndex(idxIf);
				servicePort.addServiceInterface(serviceIF);
			}
			((Component)buildview.getComponents().get(0)).addServiceport(servicePort);
		}
	}

	public boolean testAttribute(Object target, String name, String value) {
		boolean result = false;
		if ("dirty".equals(name)) {
			if (isDirty()) {
				result = "true".equalsIgnoreCase(value);
			} else {
				result = "false".equalsIgnoreCase(value);
			}
		}

		return result;
	}

	private String getEclipseVersion() {
		 return System.getProperty("osgi.framework.version");
	 }
	
	public void setCustomEditors() {
		this.customFormPages = new HashMap<Integer, AbstractCustomFormPage>();
		List list = RtcBuilderPlugin.getDefault().getAddFormPageExtensionLoader().getList();
		if (list != null) {
			for(Iterator it = list.iterator() ; it.hasNext();) {
				AddFormPageExtension extension = (AddFormPageExtension) it.next();
					this.customFormPages.putAll(extension.getCustomPages(this));
			}
		}
		for(AbstractCustomFormPage customPage : customFormPages.values()) {
			customPage.setDefaultEnableInfo();
		}
	}
		
	public void setEnabledInfoByLang(String langName){
		if( "C++".equals(langName) ){
			setEnabledInfo(null);
		}else{
			List extensionList = RtcBuilderPlugin.getDefault().getEditorExtensionLoader().getList();
			boolean existManager = false;
			if( extensionList != null ) {
				for( Iterator iter = extensionList.iterator(); iter.hasNext(); ) {
					EditorExtension manager = (EditorExtension) iter.next();
					if( manager.getManagerKey().equals(langName) ){
						// manager����UI�̊�����ԏ����擾���A
						// �eUI�Ɋ������𔽉f
						setEnabledInfo(manager.getInapplicables());
						existManager = true;
						break;
					}
				}
			}
			if(extensionList == null || !existManager) {
				//���ׂĕ\������
				setEnabledInfo(new ArrayList<String>());
			}
		}
		
		//�g�����Ă���FormPage
		for(AbstractCustomFormPage customPage : customFormPages.values()) {
			customPage.setEnableInfo(langName);
		}
	}
	
	private void setEnabledInfo(List<String> infos){
		if( infos!=null && infos.contains(EditorExtension.RTC_PROFILE_PARAMETERS_INAPPLICABLE) ){
			configurationFormPage.setConfigurationParameterSectionCompositeEnabled(false);
		}else{
			configurationFormPage.setConfigurationParameterSectionCompositeEnabled(true);
		}
		if( infos!=null && infos.contains(EditorExtension.RTC_PROFILE_SERVICE_PORTS_INAPPLICABLE) ){
			servicePortFormPage.setServicePortFormPageEnabled(false);
		}else{
			servicePortFormPage.setServicePortFormPageEnabled(true);
		}

		if( infos!=null && infos.contains(EditorExtension.RTC_PROFILE_DATA_PORTS_INAPPLICABLE) ){
			dataPortFormPage.setDataPortFormPageEnabled(false);
		}else{
			dataPortFormPage.setDataPortFormPageEnabled(true);
		}
		if( infos!=null && infos.contains(EditorExtension.GENERATE_BUTTON_SECTION_INAPPLICABLE) ){
			basicFormPage.setEnableGenerateSection(false);
		}else{
			basicFormPage.setEnableGenerateSection(true);
		}
	}
	
	public void setEnabledInfoByLangFromRtcParam(){
		setEnabledInfoByLang(getRtcParam().getLanguage());
		
		//�g�����Ă���FormPage
		for(AbstractCustomFormPage customPage : customFormPages.values()) {
			customPage.setEnableInfo(getRtcParam().getLanguage());
		}
	}
}