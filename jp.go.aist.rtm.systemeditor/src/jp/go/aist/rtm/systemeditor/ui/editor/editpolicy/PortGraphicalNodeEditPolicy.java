package jp.go.aist.rtm.systemeditor.ui.editor.editpolicy;

import jp.go.aist.rtm.systemeditor.ui.editor.command.CreateConnectorCommand;
import jp.go.aist.rtm.systemeditor.ui.editor.command.ReconnectConnectorCommand;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

/**
 * コネクタの作成や付け替えに関するEditPolicyクラス
 */
public class PortGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
		CreateConnectorCommand command = (CreateConnectorCommand) request.getStartCommand();
		if (getHost().getModel() instanceof Port == false || command.getManager().getFirst() == getHost().getModel()) {
			return null;
		}
		command.getManager().setSecond((Port) getHost().getModel());
		return command;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		if (getHost().getModel() instanceof Port == false) {
			return null;
		}
		GraphicalConnectorCreateManager manager = new GraphicalConnectorCreateManager(
				(getHost().getViewer().getControl().getShell()));
		manager.setFirst((Port) getHost().getModel());

		CreateConnectorCommand command = new CreateConnectorCommand(manager);
		command.setViewer(getHost().getViewer());
		request.setStartCommand(command);
		return command;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		if (getHost().getModel() instanceof Port == false
				|| ((PortConnector) request.getConnectionEditPart().getModel()).getSource() == getHost().getModel()) {
			return null;
		}
		GraphicalConnectorCreateManager manager = new GraphicalConnectorCreateManager(getHost().getViewer().getControl()
				.getShell());
		ReconnectConnectorCommand command = new ReconnectConnectorCommand((PortConnector) request.getConnectionEditPart()
				.getModel(), manager);
		command.setNewTarget((Port) getHost().getModel());
		return command;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		if (getHost().getModel() instanceof Port == false
				|| ((PortConnector) request.getConnectionEditPart().getModel()).getTarget() == getHost().getModel()) {
			return null;
		}
		GraphicalConnectorCreateManager manager = new GraphicalConnectorCreateManager(getHost().getViewer().getControl()
				.getShell());
		ReconnectConnectorCommand command = new ReconnectConnectorCommand((PortConnector) request.getConnectionEditPart()
				.getModel(), manager);
		command.setNewSource((Port) getHost().getModel());
		return command;
	}

}
