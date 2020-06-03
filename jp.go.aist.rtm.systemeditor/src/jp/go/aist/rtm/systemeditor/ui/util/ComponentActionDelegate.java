package jp.go.aist.rtm.systemeditor.ui.util;

import static jp.go.aist.rtm.systemeditor.ui.util.RTMixin.LOG_R;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IWorkbenchPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;

/**
 * コマンドの実行を代理するクラス
 */
public class ComponentActionDelegate {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComponentActionDelegate.class);

	static final String MSG_BEGIN_TASK = Messages.getString("ComponentActionDelegate.task.begin");
	static final String MSG_SUB_TASK1 = Messages.getString("ComponentActionDelegate.task.1");
	static final String MSG_SUB_TASK2 = Messages.getString("ComponentActionDelegate.task.2");

	IWorkbenchPart targetPart;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.targetPart = targetPart;
	}

	/**
	 * コマンド実行の基本クラス
	 */
	public static abstract class Command {

		public abstract int run() throws Exception;

		public abstract void done();

		public static Command of(Callable<Integer> run, Callable<Integer> done) {
			return new Command() {
				@Override
				public int run() throws Exception {
					return run.call();
				}

				@Override
				public void done() {
					try {
						done.call();
					} catch (Exception e) {
						LOGGER.error("Fail to done command.", e);
					}
				}
			};
		}

	};

	/**
	 * 進捗ダイアログを開いて、コマンドを実行します。
	 */
	public void run(final Command... commands) {
		run(Arrays.asList(commands));
	}

	/**
	 * 進捗ダイアログを開いて、コマンドを実行します。
	 */
	public void run(final List<Command> commands) {
		ProgressMonitorDialog dialog = new ProgressMonitorDialog(targetPart.getSite().getShell());

		List<Integer> returnCodes = new ArrayList<>();

		IRunnableWithProgress runable = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {

				monitor.beginTask(MSG_BEGIN_TASK, 100);
				monitor.worked(20);
				monitor.subTask(MSG_SUB_TASK1);

				for (Command command : commands) {
					TimeoutWrapper wrapper = TimeoutWrapper.asDefault();
					returnCodes.add(wrapper.start(new Callable<Integer>() {
						@Override
						public Integer call() throws Exception {
							return command.run();
						}
					}));
				}

				monitor.subTask(MSG_SUB_TASK2);
				monitor.done();
			}
		};

		try {
			dialog.run(false, false, runable);
		} catch (Exception e) {
			LOGGER.error("Fail in dialog", e);
		}

		if (returnCodes.contains(null)) {
			LOGGER.error("Fail to run command.");
			return;
		}

		for (int i = 0; i < returnCodes.size(); i++) {
			if (CorbaComponent.RETURNCODE_OK == returnCodes.get(i)) {
				if (i < commands.size()) {
					commands.get(i).done();
				}
			}
		}
	}

	// 以下、コマンドファクトリ

	/** コマンド作成ファクトリ (コンポーネント開始) */
	public static List<Command> commandOf_START(Logger log, List<CorbaComponent> components, Callable<Integer> done) {
		List<Command> ret = new ArrayList<>();
		for (CorbaComponent comp : components) {
			Command cmd = Command.of( //
					() -> {
						return LOG_R(log, "start()", comp, () -> {
							Integer returnCode = comp.startR();
							if(returnCode==CorbaComponent.RETURNCODE_PRECONDITION_NOT_MET) {
								int state = comp.getExecutionContextState();
								if(state == ExecutionContext.STATE_RUNNING) {
									returnCode = null;
								}
							}
							return returnCode;
						});
					}, //
					() -> {
						comp.synchronizeManually();
						int r = 1;
						if (done != null) {
							r = done.call();
						}
						return r;
					});
			ret.add(cmd);
		}
		return ret;
	}

	public static List<Command> commandOf_START(Logger log, List<CorbaComponent> components) {
		return commandOf_START(log, components, null);
	}

	public static List<Command> commandOf_START(Logger log, CorbaComponent... components) {
		return commandOf_START(log, Arrays.asList(components));
	}

	/** コマンド作成ファクトリ (コンポーネント停止) */
	public static List<Command> commandOf_STOP(Logger log, List<CorbaComponent> components, Callable<Integer> done) {
		List<Command> ret = new ArrayList<>();
		for (CorbaComponent comp : components) {
			Command cmd = Command.of( //
					() -> {
						return LOG_R(log, "stop()", comp, () -> {
							Integer returnCode = comp.stopR();
							if(returnCode==CorbaComponent.RETURNCODE_PRECONDITION_NOT_MET) {
								int state = comp.getExecutionContextState();
								if(state == ExecutionContext.STATE_STOPPED) {
									returnCode = null;
								}
							}
							return returnCode;
						});
					}, //
					() -> {
						comp.synchronizeManually();
						int r = 1;
						if (done != null) {
							r = done.call();
						}
						return r;
					});
			ret.add(cmd);
		}
		return ret;
	}

	public static List<Command> commandOf_STOP(Logger log, List<CorbaComponent> components) {
		return commandOf_STOP(log, components, null);
	}

	public static List<Command> commandOf_STOP(Logger log, CorbaComponent... components) {
		return commandOf_STOP(log, Arrays.asList(components));
	}

	/** コマンド作成ファクトリ (コンポーネント活性) */
	public static List<Command> commandOf_ACTIVATE(Logger log, List<CorbaComponent> components,
			Callable<Integer> done) {
		List<Command> ret = new ArrayList<>();
		for (CorbaComponent comp : components) {
			Command cmd = Command.of( //
					() -> {
						return LOG_R(log, "activate()", comp, () -> {
							Integer returnCode = comp.activateR();
							if(returnCode==CorbaComponent.RETURNCODE_PRECONDITION_NOT_MET) {
								int state = comp.getComponentState();
								if(state == ExecutionContext.RTC_ACTIVE) {
									returnCode = null;
								}
							}
							return returnCode;
						});
					}, //
					() -> {
						comp.synchronizeManually();
						int r = 1;
						if (done != null) {
							r = done.call();
						}
						return r;
					});
			ret.add(cmd);
		}
		return ret;
	}

	public static List<Command> commandOf_ACTIVATE(Logger log, List<CorbaComponent> components) {
		return commandOf_ACTIVATE(log, components, null);
	}

	public static List<Command> commandOf_ACTIVATE(Logger log, CorbaComponent... components) {
		return commandOf_ACTIVATE(log, Arrays.asList(components));
	}

	/** コマンド作成ファクトリ (コンポーネント非活性) */
	public static List<Command> commandOf_DEACTIVATE(Logger log, List<CorbaComponent> components,
			Callable<Integer> done) {
		List<Command> ret = new ArrayList<>();
		for (CorbaComponent comp : components) {
			Command cmd = Command.of( //
					() -> {
						return LOG_R(log, "deactivate()", comp, () -> {
							Integer returnCode = comp.deactivateR();
							if(returnCode==CorbaComponent.RETURNCODE_PRECONDITION_NOT_MET) {
								int state = comp.getComponentState();
								if(state == ExecutionContext.RTC_INACTIVE) {
									returnCode = null;
								}
							}
							return returnCode;
						});
					}, //
					() -> {
						comp.synchronizeManually();
						int r = 1;
						if (done != null) {
							r = done.call();
						}
						return r;
					});
			ret.add(cmd);
		}
		return ret;
	}

	public static List<Command> commandOf_DEACTIVATE(Logger log, List<CorbaComponent> components) {
		return commandOf_DEACTIVATE(log, components, null);
	}

	public static List<Command> commandOf_DEACTIVATE(Logger log, CorbaComponent... components) {
		return commandOf_DEACTIVATE(log, Arrays.asList(components));
	}

	/** コマンド作成ファクトリ (コンポーネントリセット) */
	public static List<Command> commandOf_RESET(Logger log, List<CorbaComponent> components, Callable<Integer> done) {
		List<Command> ret = new ArrayList<>();
		for (CorbaComponent comp : components) {
			Command cmd = Command.of( //
					() -> {
						return LOG_R(log, "reset()", comp, () -> {
							return comp.resetR();
						});
					}, //
					() -> {
						comp.synchronizeManually();
						int r = 1;
						if (done != null) {
							r = done.call();
						}
						return r;
					});
			ret.add(cmd);
		}
		return ret;
	}

	public static List<Command> commandOf_RESET(Logger log, List<CorbaComponent> components) {
		return commandOf_RESET(log, components, null);
	}

	public static List<Command> commandOf_RESET(Logger log, CorbaComponent... components) {
		return commandOf_RESET(log, Arrays.asList(components));
	}

	/** コマンド作成ファクトリ (コンポーネント終了) */
	public static List<Command> commandOf_EXIT(Logger log, List<CorbaComponent> components, Callable<Integer> done) {
		List<Command> ret = new ArrayList<>();
		for (CorbaComponent comp : components) {
			Command cmd = Command.of( //
					() -> {
						return LOG_R(log, "exit()", comp, () -> {
							return comp.exitR();
						});
					}, //
					() -> {
						comp.synchronizeManually();
						int r = 1;
						if (done != null) {
							r = done.call();
						}
						return r;
					});
			ret.add(cmd);
		}
		return ret;
	}

	public static List<Command> commandOf_EXIT(Logger log, List<CorbaComponent> components) {
		return commandOf_EXIT(log, components, null);
	}

	public static List<Command> commandOf_EXIT(Logger log, CorbaComponent... components) {
		return commandOf_EXIT(log, Arrays.asList(components));
	}

	/** コマンド作成ファクトリ (EC開始) */
	public static List<Command> commandOf_START(Logger log, CorbaExecutionContext ec, Callable<Integer> done) {
		List<Command> ret = new ArrayList<>();
		Command cmd = Command.of( //
				() -> {
					return LOG_R(log, "start()", ec, () -> {
						return ec.startR();
					});
				}, //
				() -> {
					ec.getSynchronizationSupport().synchronizeLocal();
					int r = 1;
					if (done != null) {
						r = done.call();
					}
					return r;
				});
		ret.add(cmd);
		return ret;
	}

	public static List<Command> commandOf_START(Logger log, CorbaExecutionContext ec) {
		return commandOf_START(log, ec, null);
	}

	/** コマンド作成ファクトリ (EC停止) */
	public static List<Command> commandOf_STOP(Logger log, CorbaExecutionContext ec, Callable<Integer> done) {
		List<Command> ret = new ArrayList<>();
		Command cmd = Command.of( //
				() -> {
					return LOG_R(log, "stop()", ec, () -> {
						return ec.stopR();
					});
				}, //
				() -> {
					ec.getSynchronizationSupport().synchronizeLocal();
					int r = 1;
					if (done != null) {
						r = done.call();
					}
					return r;
				});
		ret.add(cmd);
		return ret;
	}

	public static List<Command> commandOf_STOP(Logger log, CorbaExecutionContext ec) {
		return commandOf_STOP(log, ec, null);
	}

	/** コマンド作成ファクトリ (EC指定、コンポーネント活性) */
	public static List<Command> commandOf_ACTIVATE(Logger log, CorbaExecutionContext ec,
			List<CorbaComponent> components, Callable<Integer> done) {
		List<Command> ret = new ArrayList<>();
		for (CorbaComponent comp : components) {
			Command cmd = Command.of( //
					() -> {
						return LOG_R(log, "activate()", ec, () -> {
							return ec.activateR(comp);
						});
					}, //
					() -> {
						comp.synchronizeManually();
						int r = 1;
						if (done != null) {
							r = done.call();
						}
						return r;
					});
			ret.add(cmd);
		}
		return ret;
	}

	public static List<Command> commandOf_ACTIVATE(Logger log, CorbaExecutionContext ec,
			List<CorbaComponent> components) {
		return commandOf_ACTIVATE(log, ec, components, null);
	}

	public static List<Command> commandOf_ACTIVATE(Logger log, CorbaExecutionContext ec, CorbaComponent... components) {
		return commandOf_ACTIVATE(log, ec, Arrays.asList(components));
	}

	/** コマンド作成ファクトリ (EC指定、コンポーネント非活性) */
	public static List<Command> commandOf_DEACTIVATE(Logger log, CorbaExecutionContext ec,
			List<CorbaComponent> components, Callable<Integer> done) {
		List<Command> ret = new ArrayList<>();
		for (CorbaComponent comp : components) {
			Command cmd = Command.of( //
					() -> {
						return LOG_R(log, "deactivate()", ec, () -> {
							return ec.deactivateR(comp);
						});
					}, //
					() -> {
						comp.synchronizeManually();
						int r = 1;
						if (done != null) {
							r = done.call();
						}
						return r;
					});
			ret.add(cmd);
		}
		return ret;
	}

	public static List<Command> commandOf_DEACTIVATE(Logger log, CorbaExecutionContext ec,
			List<CorbaComponent> components) {
		return commandOf_DEACTIVATE(log, ec, components, null);
	}

	public static List<Command> commandOf_DEACTIVATE(Logger log, CorbaExecutionContext ec,
			CorbaComponent... components) {
		return commandOf_DEACTIVATE(log, ec, Arrays.asList(components));
	}

	/** コマンド作成ファクトリ (EC指定、コンポーネントリセット) */
	public static List<Command> commandOf_RESET(Logger log, CorbaExecutionContext ec, List<CorbaComponent> components,
			Callable<Integer> done) {
		List<Command> ret = new ArrayList<>();
		for (CorbaComponent comp : components) {
			Command cmd = Command.of( //
					() -> {
						return LOG_R(log, "reset()", ec, () -> {
							return ec.resetR(comp);
						});
					}, //
					() -> {
						comp.synchronizeManually();
						int r = 1;
						if (done != null) {
							r = done.call();
						}
						return r;
					});
			ret.add(cmd);
		}
		return ret;
	}

	public static List<Command> commandOf_RESET(Logger log, CorbaExecutionContext ec, List<CorbaComponent> components) {
		return commandOf_RESET(log, ec, components, null);
	}

	public static List<Command> commandOf_RESET(Logger log, CorbaExecutionContext ec, CorbaComponent... components) {
		return commandOf_RESET(log, ec, Arrays.asList(components));
	}

	/** コマンド作成ファクトリ (ECへのコンポーネント割当) */
	public static List<Command> commandOf_EC_ATTACH(Logger log, CorbaExecutionContext ec,
			List<CorbaComponent> components, Callable<Integer> done) {
		List<Command> ret = new ArrayList<>();
		for (CorbaComponent comp : components) {
			Command cmd = Command.of( //
					() -> {
						return LOG_R(log, "addComponent()", ec, () -> {
							return (ec.addComponentR(comp)) ? 0 : 1;
						});
					}, //
					() -> {
						comp.synchronizeManually();
						int r = 1;
						if (done != null) {
							r = done.call();
						}
						return r;
					});
			ret.add(cmd);
		}
		return ret;
	}

	public static List<Command> commandOf_EC_ATTACH(Logger log, CorbaExecutionContext ec,
			List<CorbaComponent> components) {
		return commandOf_EC_ATTACH(log, ec, components, null);
	}

	public static List<Command> commandOf_EC_ATTACH(Logger log, CorbaExecutionContext ec,
			CorbaComponent... components) {
		return commandOf_EC_ATTACH(log, ec, Arrays.asList(components));
	}

	/** コマンド作成ファクトリ (ECへのコンポーネント割当解除) */
	public static List<Command> commandOf_EC_DETACH(Logger log, CorbaExecutionContext ec,
			List<CorbaComponent> components, Callable<Integer> done) {
		List<Command> ret = new ArrayList<>();
		for (CorbaComponent comp : components) {
			Command cmd = Command.of( //
					() -> {
						return LOG_R(log, "removeComponent()", ec, () -> {
							return (ec.removeComponentR(comp)) ? 0 : 1;
						});
					}, //
					() -> {
						comp.synchronizeManually();
						int r = 1;
						if (done != null) {
							r = done.call();
						}
						return r;
					});
			ret.add(cmd);
		}
		return ret;
	}

	public static List<Command> commandOf_EC_DETACH(Logger log, CorbaExecutionContext ec,
			List<CorbaComponent> components) {
		return commandOf_EC_DETACH(log, ec, components, null);
	}

	public static List<Command> commandOf_EC_DETACH(Logger log, CorbaExecutionContext ec,
			CorbaComponent... components) {
		return commandOf_EC_DETACH(log, ec, Arrays.asList(components));
	}

	/** コマンド作成ファクトリ (マネージャ終了) */
	public static List<Command> commandOf_Manager_SHUTDOWN(Logger log, List<RTCManager> managers,
			Callable<Integer> done) {
		List<Command> ret = new ArrayList<>();
		for (RTCManager manager : managers) {
			Command cmd = Command.of( //
					() -> {
						return LOG_R(LOGGER, "shutdown()", manager, () -> {
							return manager.shutdownR();
						});
					}, //
					() -> {
						int r = 1;
						if (done != null) {
							r = done.call();
						}
						return r;
					});
			ret.add(cmd);
		}
		return ret;
	}

	public static List<Command> commandOf_Manager_SHUTDOWN(Logger log, List<RTCManager> managers) {
		return commandOf_Manager_SHUTDOWN(log, managers, null);
	}

	public static List<Command> commandOf_Manager_SHUTDOWN(Logger log, RTCManager... managers) {
		return commandOf_Manager_SHUTDOWN(log, Arrays.asList(managers));
	}

	/** コマンド作成ファクトリ (コンポーネント終了) */
	public static List<Command> commandOf_Manager_RESTART(Logger log, List<RTCManager> managers,
			Callable<Integer> done) {
		List<Command> ret = new ArrayList<>();
		for (RTCManager manager : managers) {
			Command cmd = Command.of( //
					() -> {
						return LOG_R(LOGGER, "restart()", manager, () -> {
							return manager.restartR();
						});
					}, //
					() -> {
						int r = 1;
						if (done != null) {
							r = done.call();
						}
						return r;
					});
			ret.add(cmd);
		}
		return ret;
	}

	public static List<Command> commandOf_Manager_RESTART(Logger log, List<RTCManager> managers) {
		return commandOf_Manager_RESTART(log, managers, null);
	}

	public static List<Command> commandOf_Manager_RESTART(Logger log, RTCManager... managers) {
		return commandOf_Manager_RESTART(log, Arrays.asList(managers));
	}

}
