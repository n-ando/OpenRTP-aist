package jp.go.aist.rtm.nameserviceview.ui.action;

import java.io.File;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

import jp.go.aist.rtm.nameserviceview.model.manager.NameServerManager;
import jp.go.aist.rtm.nameserviceview.ui.views.nameserviceview.NameServiceView;
import jp.go.aist.rtm.nameserviceview.util.NameServiceProcessHandler;

public class StopNameServiceAction implements IViewActionDelegate {
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
	}

	public void selectionChanged(IAction action, ISelection selection) {
		String target = "";
		String targetOS = System.getProperty("os.name").toLowerCase();
		if(targetOS.toLowerCase().startsWith("windows")) {
			target = NameServiceProcessHandler.SCRIPT_WINDOWS_STOP; 
		} else {
			target = NameServiceProcessHandler.SCRIPT_UNIX;
			if(target == null || target.length() == 0) {
				action.setEnabled(false);
				return;
			}
		}
		File targetFile = new File(target);
		if(targetFile.exists()==false) {
			action.setEnabled(false);
		}
	}
}
