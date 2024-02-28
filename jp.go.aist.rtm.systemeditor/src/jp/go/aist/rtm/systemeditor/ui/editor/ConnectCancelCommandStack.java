package jp.go.aist.rtm.systemeditor.ui.editor;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.Stack;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackEventListener;
import org.eclipse.gef.commands.CommandStackListener;

import jp.go.aist.rtm.systemeditor.ui.editor.command.CreateConnectorCommand;
import jp.go.aist.rtm.systemeditor.ui.editor.command.ReconnectConnectorCommand;

/**
 * コネクタ作成のキャンセルを認識するコマンドスタック
 *
 */
public class ConnectCancelCommandStack extends CommandStack {
	private Stack undoable = new Stack();

	private int saveLocation = 0;

	private Stack redoable = new Stack();

	/**
	 * @return <code>true</code> if {@link #undo()} can be called
	 */
	public boolean canUndo() {
		if (undoable.size() == 0)
			return false;
		return ((Command)undoable.lastElement()).canUndo();
	}
	/**
	 * Executes the specified Command if possible. Prior to executing the command, a
	 * CommandStackEvent for {@link #PRE_EXECUTE} will be fired to event listeners.
	 * Similarly, after attempting to execute the command, an event for {@link #POST_EXECUTE}
	 * will be fired.  If the execution of the command completely normally,  stack listeners
	 * will receive {@link CommandStackListener#commandStackChanged(EventObject) stackChanged}
	 * notification.
	 * <P>
	 * If the command is <code>null</code> or cannot be executed, nothing happens.
	 * @param command the Command to execute
	 * @see CommandStackEventListener
	 */
	@SuppressWarnings("deprecation")
	public void execute(Command command) {
		if (command == null || !command.canExecute())
			return;
		flushRedo();
		notifyListeners(command, PRE_EXECUTE);
		try {
			command.execute();
			if (getUndoLimit() > 0) {
				while (undoable.size() >= getUndoLimit()) {
					((Command)undoable.remove(0)).dispose();
					if (saveLocation > -1)
						saveLocation--;
				}
			}
			if (saveLocation > undoable.size())
				saveLocation = -1; //The save point was somewhere in the redo stack
			// コネクタ接続のキャンセルはUndoのスタックに入れない
			pushUndoable(command);
			notifyListeners();
		} finally {
			notifyListeners(command, POST_EXECUTE);
		}
	}
	// コネクタ接続のキャンセルはUndoのスタックに入れない
	@SuppressWarnings("unchecked")
	private void pushUndoable(Command command) {
		if (command instanceof CreateConnectorCommand) {
			CreateConnectorCommand connectCommand = (CreateConnectorCommand) command;
			if (!connectCommand.getResult()) return;
		}
		if (command instanceof ReconnectConnectorCommand) {
			ReconnectConnectorCommand connectCommand = (ReconnectConnectorCommand) command;
			if (!connectCommand.getResult()) return;
		}

		undoable.push(command);
	}

	private void flushRedo() {
		while (!redoable.isEmpty())
			((Command)redoable.pop()).dispose();
	}

	/**
	 * @return <code>true</code> if it is appropriate to call {@link #redo()}.
	 */
	public boolean canRedo() {
		return !redoable.isEmpty();
	}

	/**
	 * @return an array containing all commands in the order they were executed
	 */
	@SuppressWarnings("unchecked")
	public Object[] getCommands() {
		List commands = new ArrayList(undoable);
		for (int i = redoable.size() - 1; i >= 0; i--) {
			commands.add(redoable.get(i));
		}
		return commands.toArray();
	}

	/**
	 * Peeks at the top of the <i>redo</i> stack. This is useful for describing to the User
	 * what will be redone. The returned <code>Command</code> has a label describing it.
	 * @return the top of the <i>redo</i> stack, which may be <code>null</code>
	 */
	public Command getRedoCommand() {
		return redoable.isEmpty() ? null : (Command)redoable.peek();
	}

	/**
	 * Peeks at the top of the <i>undo</i> stack. This is useful for describing to the User
	 * what will be undone. The returned <code>Command</code> has a label describing it.
	 * @return the top of the <i>undo</i> stack, which may be <code>null</code>
	 */
	public Command getUndoCommand() {
		return undoable.isEmpty() ? null : (Command)undoable.peek();
	}

	/**
	 * Calls redo on the Command at the top of the <i>redo</i> stack, and pushes that Command
	 * onto the <i>undo</i> stack. This method should only be called when {@link #canUndo()}
	 * returns <code>true</code>.
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void redo() {
		//Assert.isTrue(canRedo())
		if (!canRedo())
			return;
		Command command = (Command)redoable.pop();
		notifyListeners(command, PRE_REDO);
		try {
			command.redo();
			undoable.push(command);
			notifyListeners();
		} finally {
			notifyListeners(command, POST_REDO);
		}
	}

	/**
	 * Undoes the most recently executed (or redone) Command. The Command is popped from the
	 * undo stack to and pushed onto the redo stack. This method should only be called when
	 * {@link #canUndo()} returns <code>true</code>.
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void undo() {
		//Assert.isTrue(canUndo());
		Command command = (Command)undoable.pop();
		notifyListeners(command, PRE_UNDO);
		try {
			command.undo();
			redoable.push(command);
			notifyListeners();
		} finally {
			notifyListeners(command, POST_UNDO);
		}
	}

	/**
	 * Flushes the entire stack and resets the save location to zero. This method might be
	 * called when performing "revert to saved".
	 */
	@SuppressWarnings("deprecation")
	public void flush() {
		flushRedo();
		flushUndo();
		saveLocation = 0;
		notifyListeners();
	}

	private void flushUndo() {
		while (!undoable.isEmpty())
			((Command)undoable.pop()).dispose();
	}

	/**
	 * Returns true if the stack is dirty. The stack is dirty whenever the last executed or
	 * redone command is different than the command that was at the top of the undo stack when
	 * {@link #markSaveLocation()} was last called.
	 * @return <code>true</code> if the stack is dirty
	 */
	public boolean isDirty() {
		return undoable.size() != saveLocation;
	}

	/**
	 * Marks the last executed or redone Command as the point at which the changes were saved.
	 * Calculation of {@link #isDirty()} will be based on this checkpoint.
	 */
	@SuppressWarnings("deprecation")
	public void markSaveLocation() {
		saveLocation = undoable.size();
		notifyListeners();
	}


}
