package jp.go.aist.rtm.nameserviceview.ui.action;

import java.io.File;
import java.io.IOException;

import jp.go.aist.rtm.nameserviceview.model.manager.NameServerContext;
import jp.go.aist.rtm.nameserviceview.model.manager.NameServerManager;
import jp.go.aist.rtm.nameserviceview.nl.Messages;
import jp.go.aist.rtm.nameserviceview.ui.dialog.PasswordDialog;
import jp.go.aist.rtm.nameserviceview.ui.views.nameserviceview.NameServiceView;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

public class StartNameServiceAction implements IViewActionDelegate {
	private NameServiceView view;

	private static String SCRIPT_WINDOWS = System.getenv("RTM_ROOT") + "bin" + Path.SEPARATOR + "rtm-naming.bat";
	private static String SCRIPT_WINDOWS_STOP = System.getenv("RTM_ROOT") + "bin" + Path.SEPARATOR + "kill-rtm-naming.bat";

	private static String SCRIPT_UNIX = "/usr/bin/rtm-naming";
	private String[] UNIX_CANDIDATE_LIST = {"/usr/bin/rtm-naming",
											"/usr/local/bin/rtm-naming",
											System.getenv("RTM_ROOT") + "bin" + Path.SEPARATOR + "rtm-naming"};

	public void init(IViewPart view) {
		this.view = (NameServiceView) view;
		// find rtm-naming
		for(String each :  UNIX_CANDIDATE_LIST) {
			File targetFile = new File(each);
			if(targetFile.exists() == true) {
				SCRIPT_UNIX = each;
				break;
			}
		}
	}

	public void run(IAction action) {
		String targetOS = System.getProperty("os.name").toLowerCase();
		boolean isWindows = false;
		if(targetOS.toLowerCase().startsWith("windows")) {
			isWindows = true;
		}
		String passWord = "";
		//Stop NameService
		{
			ProcessBuilder pb = null;
			if(isWindows) {
				pb = new ProcessBuilder(SCRIPT_WINDOWS_STOP);

			} else {
				PasswordDialog  passwdDialog = new PasswordDialog(view.getSite().getShell());
				if(passwdDialog.open()!=Dialog.OK) return;

				passWord = passwdDialog.getPassWord();
				pb = new ProcessBuilder(SCRIPT_UNIX, "-k", "-f", "-w " + passWord);
			}
			try {
				pb.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			NameServerManager.eInstance.refreshAll();
		}
		//Start NameServer
		{
			ProcessBuilder pb = null;
			if(isWindows) {
				pb = new ProcessBuilder(SCRIPT_WINDOWS);

			} else {
				pb = new ProcessBuilder(SCRIPT_UNIX, "-f", "-w " + passWord);
			}
			try {
				pb.start();
			} catch (IOException e) {
				e.printStackTrace();
			}

			/////
			//　ネームサーバーの登録を複数回試行
			boolean isStarted = false;
			for(int index=0; index<10; index++) {
				NameServerContext server = NameServerManager.eInstance.addNameServer("localhost");
				if(server!=null) {
					isStarted = true;
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(isStarted) {
				NameServerManager.eInstance.refreshAll();
			} else {
				MessageDialog.openWarning(view.getSite().getShell(), "Warning",
						Messages.getString("StartNameServiceAction.1")); //$NON-NLS-1$
			}
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		String targetOS = System.getProperty("os.name").toLowerCase();
		if(targetOS.toLowerCase().startsWith("windows")) {
			File targetFile = new File(SCRIPT_WINDOWS);
			if(targetFile.exists()==false) {
				action.setEnabled(false);
			}
			targetFile = new File(SCRIPT_WINDOWS_STOP);
			if(targetFile.exists()==false) {
				action.setEnabled(false);
			}

		} else {
			File targetFile = new File(SCRIPT_UNIX);
			if(targetFile.exists()==false) {
				action.setEnabled(false);
			}
		}
	}
}
