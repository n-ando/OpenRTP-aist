package jp.go.aist.rtm.nameserviceview.ui.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.nameserviceview.ui.views.nameserviceview.NameServiceView;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

public class StartManagerAction implements IViewActionDelegate {
	private NameServiceView view;

	private static String SCRIPT_WINDOWS = System.getenv("RTM_ROOT") + "bin" + Path.SEPARATOR + "rtcd-cxx-daemon.bat";
	
	private static String SCRIPT_LINUX = "/usr/bin/rtcd2";
	private String[] UNIX_CANDIDATE_LIST = {"/usr/bin/rtcd2",
											"/usr/local/bin/rtcd2",
											System.getenv("RTM_ROOT") + "bin" + Path.SEPARATOR + "rtcd2",
									"/usr/bin/rtcd",
									"/usr/local/bin/rtcd",
									System.getenv("RTM_ROOT") + "bin" + Path.SEPARATOR + "rtcd"};


	public void init(IViewPart view) {
		this.view = (NameServiceView) view;
		// find rtcd
		for(String each :  UNIX_CANDIDATE_LIST) {
			File targetFile = new File(each);
			if(targetFile.exists() == true) {
				SCRIPT_LINUX = each;
				break;
			}
		}
	}

	public void run(IAction action) {
		view.getViewer().expandAll();
		List<TreeItem> items = new ArrayList<TreeItem>();
		getAllItems(view.getViewer().getTree(), items);
		RTCManager targetManager = null;
		for(TreeItem item : items) {
			Object adapter = AdapterUtil.getAdapter(item.getData(),
					RTCManager.class);
			if (adapter != null) {
				targetManager = (RTCManager) adapter;
				break;
			}
		}
		if(targetManager!=null) {
			targetManager.shutdownR();
		}
		/////
		boolean isWindows = false;
		String targetOS = System.getProperty("os.name").toLowerCase();
		if(targetOS.toLowerCase().startsWith("windows")) {
			isWindows = true;
		}

		if(isWindows) {
			try {
				ProcessBuilder pb = new ProcessBuilder(SCRIPT_WINDOWS);
				File dir = new File(System.getenv("RTM_ROOT") + "bin");
				pb.directory(dir);
				pb.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				ProcessBuilder pb = new ProcessBuilder(SCRIPT_LINUX, "-d");
				pb.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		String target = "";
		String targetOS = System.getProperty("os.name").toLowerCase();
		if(targetOS.toLowerCase().startsWith("windows")) {
			target = SCRIPT_WINDOWS;
		} else {
			target = SCRIPT_LINUX;
		}
		File targetFile = new File(target);
		if(targetFile.exists()==false) {
			action.setEnabled(false);
		}
	}

	private void getAllItems(Tree tree, List<TreeItem> allItems) {
	    for(TreeItem item : tree.getItems()) {
	    	item.setExpanded(true);
	        getAllItems(item, allItems);
	    }
	}

	private void getAllItems(TreeItem currentItem, List<TreeItem> allItems)	{
	    TreeItem[] children = currentItem.getItems();

	    for(int index = 0; index < children.length; index++) {
	        allItems.add(children[index]);
	        getAllItems(children[index], allItems);
	    }
	}
}
