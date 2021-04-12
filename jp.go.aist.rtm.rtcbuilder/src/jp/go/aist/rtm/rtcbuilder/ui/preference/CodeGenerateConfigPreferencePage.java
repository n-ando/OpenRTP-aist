package jp.go.aist.rtm.rtcbuilder.ui.preference;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;
import jp.go.aist.rtm.rtcbuilder.nl.Messages;

public class CodeGenerateConfigPreferencePage extends AbstarctFieldEditorPreferencePage implements
							IWorkbenchPreferencePage {
	public CodeGenerateConfigPreferencePage(){
		setPreferenceStore(RtcBuilderPlugin.getDefault().getPreferenceStore());
	}
	
	@Override
	public void init(IWorkbench workbench) {
		IPreferenceStore store = RtcBuilderPlugin.getDefault().getPreferenceStore();
		storeConfigurationSetInitialSetting(store);
	}

	@Override
	protected void createFieldEditors() {
		Composite composite = new Composite(getFieldEditorParent(), SWT.NULL);
		composite.setLayout(new GridLayout());
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.grabExcessHorizontalSpace = true;
		composite.setLayoutData(gd);
		createConfigurationSetParts(composite);
	}

	private void createConfigurationSetParts(Composite composite) {
		Composite configGroup = createGroup(composite,
				Messages.getString("IPreferenceMessageConstants.CODE_GEN_TITLE_CONFIG"));
		DigitAlphabetStringFieldEditor configurationNameEditor = 
			new DigitAlphabetStringFieldEditor(ComponentPreferenceManager.Generate_Configuration_Name,
					Messages.getString("IMC.CONFIGURATION_TBLLBL_NAME"), configGroup);
		addField(configurationNameEditor);
		StringFieldEditor configurationTypeEditor = 
			new StringFieldEditor(ComponentPreferenceManager.Generate_Configuration_Type,
					Messages.getString("IMC.CONFIGURATION_TBLLBL_TYPE"), configGroup);
		addField(configurationTypeEditor);
		StringFieldEditor configurationVarNameEditor = 
			new StringFieldEditor(ComponentPreferenceManager.Generate_Configuration_VarName,
					Messages.getString("IMC.CONFIGURATION_LBL_VARNAME"), configGroup);
		addField(configurationVarNameEditor);
		DigitAlphabetStringFieldEditor configurationDefaultEditor = 
			new DigitAlphabetStringFieldEditor(ComponentPreferenceManager.Generate_Configuration_Default,
					Messages.getString("IMC.CONFIGURATION_TBLLBL_DEFAULTVAL"), configGroup);
		addField(configurationDefaultEditor);
		StringFieldEditor configurationConstraintEditor = 
			new StringFieldEditor(ComponentPreferenceManager.Generate_Configuration_Constraint,
					Messages.getString("IMC.CONFIGURATION_LBL_CONSTRAINT"), configGroup);
		addField(configurationConstraintEditor);
		StringFieldEditor configurationUnitEditor = 
			new StringFieldEditor(ComponentPreferenceManager.Generate_Configuration_Unit,
					Messages.getString("IMC.CONFIGURATION_LBL_UNIT"), configGroup);
		addField(configurationUnitEditor);
		//
		StringFieldEditor configurationPrefixEditor = 
			new StringFieldEditor(ComponentPreferenceManager.Generate_Configuration_Prefix,
					IPreferenceMessageConstants.PORT_LBL_PREFIX, configGroup);
		addField(configurationPrefixEditor);
		StringFieldEditor configurationSuffixEditor = 
			new StringFieldEditor(ComponentPreferenceManager.Generate_Configuration_Suffix,
					IPreferenceMessageConstants.PORT_LBL_SUFFIX, configGroup);
		addField(configurationSuffixEditor);
	}

	private void storeConfigurationSetInitialSetting(IPreferenceStore store) {
		store.setDefault(ComponentPreferenceManager.Generate_Configuration_Name, ComponentPreferenceManager.DEFAULT_CONFIGURATION_NAME);
		store.setDefault(ComponentPreferenceManager.Generate_Configuration_Type, ComponentPreferenceManager.DEFAULT_CONFIGURATION_TYPE);
		store.setDefault(ComponentPreferenceManager.Generate_Configuration_VarName, ComponentPreferenceManager.DEFAULT_CONFIGURATION_VARNAME);
		store.setDefault(ComponentPreferenceManager.Generate_Configuration_Default, ComponentPreferenceManager.DEFAULT_CONFIGURATION_DEFAULT);
		store.setDefault(ComponentPreferenceManager.Generate_Configuration_Constraint, ComponentPreferenceManager.DEFAULT_CONFIGURATION_CONSTRAINT);
		store.setDefault(ComponentPreferenceManager.Generate_Configuration_Unit, ComponentPreferenceManager.DEFAULT_CONFIGURATION_UNIT);
		store.setDefault(ComponentPreferenceManager.Generate_Configuration_Prefix, ComponentPreferenceManager.DEFAULT_CONFIGURATION_PREFIX);
		store.setDefault(ComponentPreferenceManager.Generate_Configuration_Suffix, ComponentPreferenceManager.DEFAULT_CONFIGURATION_SUFFIX);
	}
}
