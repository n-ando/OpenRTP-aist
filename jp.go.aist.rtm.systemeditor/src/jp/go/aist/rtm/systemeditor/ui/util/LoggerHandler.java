package jp.go.aist.rtm.systemeditor.ui.util;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.ui.PlatformUI;
import org.msgpack.value.Value;

import influent.EventEntry;
import influent.forward.ForwardCallback;
import influent.forward.ForwardServer;
import jp.go.aist.rtm.systemeditor.ui.views.logview.LogParam;

public class LoggerHandler {
	private ForwardServer logServer;
	private List<LogParam> logList;
	private TableViewer logTable;
	
	ForwardCallback callback = ForwardCallback.ofSyncConsumer(
	  stream -> {
//		  String tag = stream.getTag().getName();
		  for (EventEntry entry : stream.getEntries()) {
			  String strRaw = entry.getTime().toString() + " " + entry.getRecord().toJson();
//		      long time = entry.getTime().toEpochMilli();
//		      String json = entry.getRecord().toJson();
//			  System.out.println();
//			  System.out.println("time:" + time);
//			  System.out.println("json:" + json);
			  
		      Value[] val = entry.getRecord().getKeyValueArray();
		      for(Value each : val) {
		    	  if(each.toString().equals("log")) continue;
//				  System.out.println("logContents:" + each.toString());
			      PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
						public void run() {
							logList.add(new LogParam(each.toString(), strRaw));
							logTable.refresh();
						}
					});
		      }
		    }				  
	  },
	  Executors.newFixedThreadPool(1)
	);
	
	public void startServer(int portNo, List<LogParam> logList, TableViewer logTable) {
		this.logTable = logTable;
		this.logList = logList;
		logServer = new ForwardServer
				  .Builder(callback)
				  .localAddress(portNo)
				  .build();
		logServer.start();
	}
	
	public void stopServer() {
		CompletableFuture<Void> stopping = logServer.shutdown();
		try {
			stopping.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		logServer = null;
	}
}
