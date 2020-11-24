package jp.go.aist.rtm.nameserviceview.util;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.Dialog;

import jp.go.aist.rtm.nameserviceview.ui.dialog.PasswordDialog;
import jp.go.aist.rtm.nameserviceview.ui.views.nameserviceview.NameServiceView;

public class NameServiceProcessHandler {
	public static String SCRIPT_WINDOWS = System.getenv("RTM_ROOT") + "bin" + Path.SEPARATOR + "rtm-naming.bat";
	public static String SCRIPT_WINDOWS_STOP = System.getenv("RTM_ROOT") + "bin" + Path.SEPARATOR + "kill-rtm-naming.bat";

	public static String SCRIPT_UNIX = "/usr/bin/rtm-naming";
	private String[] UNIX_CANDIDATE_LIST = {"/usr/bin/rtm-naming",
											"/usr/local/bin/rtm-naming",
											System.getenv("RTM_ROOT") + "bin" + Path.SEPARATOR + "rtm-naming"};
	
	public void initialize() {
		// find rtm-naming
		for(String each :  UNIX_CANDIDATE_LIST) {
			File targetFile = new File(each);
			if(targetFile.exists() == true) {
				SCRIPT_UNIX = each;
				break;
			}
		}
	}
	
	public String stopNameService(NameServiceView view, boolean isWindows) {
		ProcessBuilder pb = null;
		String passWord = "";

		if(isWindows) {
			pb = new ProcessBuilder(SCRIPT_WINDOWS_STOP);

		} else {
			PasswordDialog  passwdDialog = new PasswordDialog(view.getSite().getShell());
			if(passwdDialog.open()!=Dialog.OK) return null;

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
		
		return passWord;
	}
	
	public void startNameService(boolean isWindows, String passWord) {
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
	}

}
