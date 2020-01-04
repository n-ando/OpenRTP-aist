package jp.go.aist.rtm.systemeditor.ui.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.msgpack.value.Value;

import influent.EventEntry;
import influent.forward.ForwardCallback;
import influent.forward.ForwardServer;

public class LoggerHandler {
	private ForwardServer logServer;
	private Text logOut;
	
	ForwardCallback callback = ForwardCallback.ofSyncConsumer(
	  stream -> {
		  String tag = stream.getTag().getName();
		  for (EventEntry entry : stream.getEntries()) {
		      long time = entry.getTime().toEpochMilli();
		      String json = entry.getRecord().toJson();
			  System.out.println();
			  System.out.println("time:" + time);
			  System.out.println("json:" + json);
			  
		      Value[] val = entry.getRecord().getKeyValueArray();
		      for(Value each : val) {
		    	  if(each.toString().equals("log")) continue;
				  System.out.println("logContents:" + each.toString());
			      PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
						public void run() {
						  String exist = logOut.getText();
						  logOut.setText(exist + System.lineSeparator() + each.toString());
						}
					});
		      }
		    }				  
	  },
	  Executors.newFixedThreadPool(1)
	);
	
	public void startServer(int portNo, final Text target) {
		this.logOut = target;
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
