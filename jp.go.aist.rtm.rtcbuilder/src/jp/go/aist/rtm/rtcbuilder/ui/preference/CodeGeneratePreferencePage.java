package jp.go.aist.rtm.rtcbuilder.ui.preference;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;
import jp.go.aist.rtm.rtcbuilder.nl.Messages;

public class CodeGeneratePreferencePage extends AbstarctFieldEditorPreferencePage implements
							IWorkbenchPreferencePage {
	//
	static private String[][] componentTypeItems = {
						{"STATIC", "STATIC"},
						{"UNIQUE", "UNIQUE"},
						{"COMMUTATIVE","COMMUTATIVE"}};
	static private String[][] activityTypeItems = {
						{"PERIODIC", "PERIODIC"},
						{"SPORADIC", "SPORADIC"},
						{"EVENTDRIVEN","EVENTDRIVEN"}};
	static private String[][] componentKindItems = {
						{"DataFlowComponent", "DataFlowComponent"}, 
						{"FiniteStateMachineComponent","FiniteStateMachineComponent"},
						{"DataFlowFiniteStateMachineComponent","DataFlowFiniteStateMachineComponent"},
						{"FiniteStateMachineMultiModeComponent","FiniteStateMachineMultiModeComponent"},
						{"DataFlowMultiModeComponent","DataFlowMultiModeComponent"},
						{"DataFlowFiniteStateMachineMultiModeComponent", "DataFlowFiniteStateMachineMultiModeComponent"}};
	//
	static private String[][] executionContextTypeItems = {
						{"PeriodicExecutionContext", "PeriodicExecutionContext"},
						{"ExtTrigExecutionContext","ExtTrigExecutionContext"}};
	
	public CodeGeneratePreferencePage(){
		setPreferenceStore(RtcBuilderPlugin.getDefault().getPreferenceStore());
	}
	
	@Override
	public void init(IWorkbench workbench) {
		IPreferenceStore store = RtcBuilderPlugin.getDefault().getPreferenceStore();
		storeComponentInitialSetting(store);
	}

	@Override
	protected void createFieldEditors() {
		Composite composite = new Composite(getFieldEditorParent(), SWT.NULL);
		composite.setLayout(new GridLayout());
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.grabExcessHorizontalSpace = true;
		composite.setLayoutData(gd);
		createComponentPart(composite);
	}

	private void createComponentPart(Composite composite) {
		//Basic Page
		Composite basicGroup = createGroup(composite, 
				Messages.getString("IPreferenceMessageConstants.CODE_GEN_TITLE_BASIC"));
		DigitAlphabetStringFieldEditor moduleNameEditor = 
			new DigitAlphabetStringFieldEditor(ComponentPreferenceManager.Generate_Basic_Name,
					Messages.getString("IMC.BASIC_LBL_MODULENAME"), basicGroup);
		addField(moduleNameEditor);
		StringFieldEditor moduleDescriptionEditor = 
			new StringFieldEditor(ComponentPreferenceManager.Generate_Basic_Description,
					Messages.getString("IMC.BASIC_LBL_DESCRIPTION"), basicGroup);
		addField(moduleDescriptionEditor);
		StringFieldEditor moduleVersionEditor = 
			new StringFieldEditor(ComponentPreferenceManager.Generate_Basic_Version,
					Messages.getString("IMC.BASIC_LBL_VERSION"), basicGroup);
		addField(moduleVersionEditor);
		StringFieldEditor moduleVendorEditor = 
			new StringFieldEditor(ComponentPreferenceManager.Generate_Basic_Vendor_Name,
					Messages.getString("IMC.BASIC_LBL_VENDOR"), basicGroup);
		addField(moduleVendorEditor);
		StringFieldEditor moduleCategoryEditor = 
			new StringFieldEditor(ComponentPreferenceManager.Generate_Basic_Category,
					Messages.getString("IMC.BASIC_LBL_CATEGORY"), basicGroup);
		addField(moduleCategoryEditor);
		//
		ComboFieldEditor componentTypeComboEditor = new ComboFieldEditor(ComponentPreferenceManager.Generate_Basic_ComponentType,
				Messages.getString("IMC.BASIC_LBL_COMPONENT_TYPE"), componentTypeItems, basicGroup);
		addField(componentTypeComboEditor);
		ComboFieldEditor activityTypeComboEditor = new ComboFieldEditor(ComponentPreferenceManager.Generate_Basic_ActivityType,
				Messages.getString("IMC.BASIC_LBL_ACTIVITY_TYPE"), activityTypeItems, basicGroup);
		addField(activityTypeComboEditor);
		//
		ComboFieldEditor componentKindCombo = new ComboFieldEditor(ComponentPreferenceManager.Generate_Basic_ComponentKind,
				Messages.getString("IMC.BASIC_LBL_COMPONENT_KIND"), componentKindItems, basicGroup);
		addField(componentKindCombo);
		//
		IntegerFieldEditor moduleMaxInstanceTextEditor = new IntegerFieldEditor(ComponentPreferenceManager.Generate_Basic_Max_Instance,
				Messages.getString("IMC.BASIC_LBL_MAX_INSTANCES"), basicGroup);
		addField(moduleMaxInstanceTextEditor);
		//
		ComboFieldEditor executionTypeCombo = new ComboFieldEditor(ComponentPreferenceManager.Generate_Basic_ExecutionType,
				Messages.getString("IMC.BASIC_LBL_EXECUTION_TYPE"), executionContextTypeItems, basicGroup);
		addField(executionTypeCombo);
		//
		DoubleStringFieldEditor moduleExecutionRateTextEditor = 
			new DoubleStringFieldEditor(ComponentPreferenceManager.Generate_Basic_Execution_Rate,
					Messages.getString("IMC.BASIC_LBL_EXECUTION_RATE"), basicGroup);
		addField(moduleExecutionRateTextEditor);
		//
		StringFieldEditor commonPrefixEditor = 
			new StringFieldEditor(ComponentPreferenceManager.Generate_Basic_Prefix,
					IPreferenceMessageConstants.PORT_LBL_PREFIX, basicGroup);
		addField(commonPrefixEditor);
		StringFieldEditor commonSuffixEditor = 
			new StringFieldEditor(ComponentPreferenceManager.Generate_Basic_Suffix,
					IPreferenceMessageConstants.PORT_LBL_SUFFIX, basicGroup);
		addField(commonSuffixEditor);
	}
	
	private void storeComponentInitialSetting(IPreferenceStore store) {
		store.setDefault(ComponentPreferenceManager.Generate_Basic_Name, ComponentPreferenceManager.DEFAULT_COMPONENT_NAME);
		store.setDefault(ComponentPreferenceManager.Generate_Basic_Description, ComponentPreferenceManager.DEFAULT_DESCRIPTION);
		store.setDefault(ComponentPreferenceManager.Generate_Basic_Version, ComponentPreferenceManager.DEFAULT_VERSION);
		store.setDefault(ComponentPreferenceManager.Generate_Basic_Vendor_Name, ComponentPreferenceManager.DEFAULT_VENDER);
		store.setDefault(ComponentPreferenceManager.Generate_Basic_Category, ComponentPreferenceManager.DEFAULT_CATEGORY);
		store.setDefault(ComponentPreferenceManager.Generate_Basic_ComponentType, ComponentPreferenceManager.DEFAULT_COMPONENT_TYPE);
		store.setDefault(ComponentPreferenceManager.Generate_Basic_ActivityType, ComponentPreferenceManager.DEFAULT_ACTIVITY_TYPE);
		store.setDefault(ComponentPreferenceManager.Generate_Basic_ComponentKind, ComponentPreferenceManager.DEFAULT_COMPONENT_KIND);
		store.setDefault(ComponentPreferenceManager.Generate_Basic_ExecutionType, ComponentPreferenceManager.DEFAULT_EXECUTION_TYPE);
		store.setDefault(ComponentPreferenceManager.Generate_Basic_Max_Instance, ComponentPreferenceManager.DEFAULT_MAXINST);
		store.setDefault(ComponentPreferenceManager.Generate_Basic_Execution_Rate, ComponentPreferenceManager.DEFAULT_EXECUTION_RATE);
		store.setDefault(ComponentPreferenceManager.Generate_Basic_Prefix, ComponentPreferenceManager.DEFAULT_PREFIX);
		store.setDefault(ComponentPreferenceManager.Generate_Basic_Suffix, ComponentPreferenceManager.DEFAULT_SUFFIX);
	}
}
