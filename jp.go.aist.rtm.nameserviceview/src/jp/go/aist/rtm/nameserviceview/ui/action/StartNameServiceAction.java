package jp.go.aist.rtm.nameserviceview.ui.action;

import java.io.File;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

import jp.go.aist.rtm.nameserviceview.model.manager.NameServerContext;
import jp.go.aist.rtm.nameserviceview.model.manager.NameServerManager;
import jp.go.aist.rtm.nameserviceview.nl.Messages;
import jp.go.aist.rtm.nameserviceview.ui.views.nameserviceview.NameServiceView;
import jp.go.aist.rtm.nameserviceview.util.NameServiceProcessHandler;

public class StartNameServiceAction implements IViewActionDelegate {
	private NameServiceView view;
	private NameServiceProcessHandler handler = new NameServiceProcessHandler();

	public void init(IViewPart view) {
		this.view = (NameServiceView) view;
		handler.initialize();
	}

	public void run(IAction action) {
		String targetOS = System.getProperty("os.name").toLowerCase();
		boolean isWindows = false;
		if(targetOS.toLowerCase().startsWith("windows")) {
			isWindows = true;
		}
		//Stop NameService
		String passWord = handler.stopNameService(view, isWindows);
		if(passWord == null) return;
		NameServerManager.eInstance.refreshAll();
		//Start NameServer
		{
			handler.startNameService(isWindows, passWord);
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
			File targetFile = new File(NameServiceProcessHandler.SCRIPT_WINDOWS);
			if(targetFile.exists()==false) {
				action.setEnabled(false);
			}
			targetFile = new File(NameServiceProcessHandler.SCRIPT_WINDOWS_STOP);
			if(targetFile.exists()==false) {
				action.setEnabled(false);
			}

		} else {
			File targetFile = new File(NameServiceProcessHandler.SCRIPT_UNIX);
			if(targetFile.exists()==false) {
				action.setEnabled(false);
			}
		}
	}
}
