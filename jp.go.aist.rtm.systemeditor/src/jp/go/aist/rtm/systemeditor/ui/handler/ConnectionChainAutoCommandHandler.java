package jp.go.aist.rtm.systemeditor.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.go.aist.rtm.systemeditor.ui.editor.SystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.util.ConnectionChainHelper;

public class ConnectionChainAutoCommandHandler extends AbstractHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionChainAutoCommandHandler.class);

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Command command = event.getCommand();
		boolean oldValue = HandlerUtil.toggleCommandState(command);
		boolean doCheck = !oldValue;
		LOGGER.debug("execute: command=<{}> doAttach=<{}>", command.getId(), doCheck);
		
		IEditorPart editor = HandlerUtil.getActiveEditor(event);
		if (editor == null || !(editor instanceof SystemDiagramEditor)) {
			return null;
		}
		ConnectionChainHelper.autoCheckConnectionChain((SystemDiagramEditor)editor, doCheck);

		return null;
	}

}
