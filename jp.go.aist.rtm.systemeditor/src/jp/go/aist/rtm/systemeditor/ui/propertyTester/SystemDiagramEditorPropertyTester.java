package jp.go.aist.rtm.systemeditor.ui.propertyTester;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.State;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.RegistryToggleState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;
import jp.go.aist.rtm.toolscommon.manager.ToolsCommonPreferenceManager;

/**
 * expressions.definitionsで利用するためのプロパティテスタ (SystemDiagramEditor)
 */
public class SystemDiagramEditorPropertyTester extends PropertyTester {

	private static final Logger LOGGER = LoggerFactory.getLogger(SystemDiagramEditorPropertyTester.class);

	private static final String PROPERTY_CHECK_CORBA_STATUS_OBSERVER_GLOBAL = "checkCorbaStatusObserverGlobal";

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		LOGGER.trace("SystemDiagramEditorPropertyTester: property=<{}> args=<{}> receiver=<{}>", property, args,
				receiver);
		if (receiver instanceof AbstractSystemDiagramEditor) {
			if (PROPERTY_CHECK_CORBA_STATUS_OBSERVER_GLOBAL.equals(property)) {
				return checkCorbaStatusObserverGlobal((AbstractSystemDiagramEditor) receiver, args);
			}
		}
		return false;
	}

	/** 常時true、Preferenceの StatusObserverデフォルト設定をコマンドへ格納 */
	private boolean checkCorbaStatusObserverGlobal(AbstractSystemDiagramEditor part, Object[] args) {
		String commandId = (String) args[0];
		ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
		Command command = commandService.getCommand(commandId);
		if (command == null) {
			return true;
		}
		State state = command.getState(RegistryToggleState.STATE_ID);
		if (state == null) {
			return true;
		}
		state.setValue(ToolsCommonPreferenceManager.getInstance().isSTATUS_OBSERVER_ATTACH_ENABLE());
		LOGGER.trace("checkCorbaStatusObserverGlobal: default of status observer attachment. attach={}",
				state.getValue());
		return true;
	}

}
