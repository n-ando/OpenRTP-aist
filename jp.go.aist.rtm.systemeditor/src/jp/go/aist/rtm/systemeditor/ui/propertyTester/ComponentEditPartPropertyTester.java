package jp.go.aist.rtm.systemeditor.ui.propertyTester;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.State;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.RegistryToggleState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ComponentEditPart;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;

/**
 * expressions.definitionsで利用するためのプロパティテスタ (ComponentEditPart)
 */
public class ComponentEditPartPropertyTester extends PropertyTester {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComponentEditPartPropertyTester.class);

	private static final String PROPERTY_HAS_CORBA_COMPONENT = "hasCorbaComponent";

	private static final String PROPERTY_CHECK_CORBA_STATUS_OBSERVER = "checkCorbaStatusObserver";

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		LOGGER.trace("ComponentEditPartPropertyTester: property=<{}> args=<{}> receiver=<{}>", property, args,
				receiver);
		if (receiver instanceof ComponentEditPart) {
			if (PROPERTY_HAS_CORBA_COMPONENT.equals(property)) {
				return hasCorbaComponent((ComponentEditPart) receiver);
			} else if (PROPERTY_CHECK_CORBA_STATUS_OBSERVER.equals(property)) {
				return checkCorbaStatusObserver((ComponentEditPart) receiver, args);
			}
		}
		return false;
	}

	/** Componentのモデルが CorbaComponentの場合にtrue */
	private boolean hasCorbaComponent(ComponentEditPart part) {
		LOGGER.trace("hasCorbaComponent: part=<{}>", part);
		return (part.getModel() instanceof CorbaComponent);
	}

	/** 常時true、対象の CorbaComponentへの StatusObserver設定有無をコマンドへ格納 */
	private boolean checkCorbaStatusObserver(ComponentEditPart part, Object[] args) {
		LOGGER.trace("checkCorbaStatusObserver: part=<{}>, args=<{}>", part, args);
		if (args == null || args.length < 1 || !(args[0] instanceof String)) {
			LOGGER.trace("checkCorbaStatusObserver: argument is not string");
			return true;
		}
		if (!(part.getModel() instanceof CorbaComponent)) {
			LOGGER.trace("checkCorbaStatusObserver: component is not corba");
			return true;
		}

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
		CorbaComponent corbaComp = (CorbaComponent) part.getModel();
		if (!corbaComp.supportedCorbaObserver()) {
			state.setValue(Boolean.FALSE);
			LOGGER.trace("checkCorbaStatusObserver: compnent does not support status observer");
			return true;
		}
		state.setValue(corbaComp.getStatusObserver() != null);
		LOGGER.trace("checkCorbaStatusObserver: status observer is attached currently. attached={}", state.getValue());
		return true;
	}

}
