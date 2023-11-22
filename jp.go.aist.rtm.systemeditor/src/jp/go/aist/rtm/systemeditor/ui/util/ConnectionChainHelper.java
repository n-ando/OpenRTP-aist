package jp.go.aist.rtm.systemeditor.ui.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import RTC.RTObject;
import RTC.RTObjectHelper;
import jp.go.aist.rtm.nameserviceview.model.manager.NameServerManager;
import jp.go.aist.rtm.nameserviceview.model.manager.impl.NameServerManagerImpl;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingContextNode;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingObjectNode;
import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.ui.editor.SystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.editor.command.CreateCommand;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.ServicePort;
import jp.go.aist.rtm.toolscommon.model.core.Rectangle;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;

public class ConnectionChainHelper {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ConnectionChainHelper.class);
	private int startX = 0;
	private int startY = 0;
	private int countX = 0;
	private int countY = 0;
	
	private static SystemDiagramEditor targetEditor;
	private static boolean compCheck = false;
	
	private enum PortType {
		DataInPort,
		DataOutPort,
		ServicePort
	}

//	class CheckThread extends Thread {
//	    public void run() {
//			ConnectionChainHelper handler = new ConnectionChainHelper();
//			while(true) {
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				if(compCheck==false) {
//					break;
//				}
//				handler.checkConnectionChain(targetEditor);
////				targetEditor.refresh();
//			}
//	    }
//	}
	
	public static void autoCheckConnectionChain(SystemDiagramEditor editor, boolean doCheck) {
		if (editor.getSystemDiagram() == null) {
			return;
		}
		targetEditor = editor;
		if(doCheck) {
			compCheck = true;
			try {
				new Thread(new Runnable() {
					public void run() {
						ConnectionChainHelper handler = new ConnectionChainHelper();
						while(true) {
							if(compCheck==false) {
								break;
							}
							handler.checkConnectionChain(targetEditor);
//							targetEditor.refresh();
							try {
								SystemEditorPreferenceManager manager = SystemEditorPreferenceManager.getInstance();
								int interval = manager.getCheckInterval();
								Thread.sleep(1000* interval);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
				}}).start();
			} catch (Exception e) {
			}
		} else {
			compCheck = false;
		}
	}
	
	public void checkConnectionChain(SystemDiagramEditor editor) {
		if (editor.getSystemDiagram() == null) {
			return;
		}
		List<Component> compList = editor.getSystemDiagram().getRegisteredComponents();
		Collections.sort(compList, new ComponentComparator());
		for (Component comp : compList) {
			if (!(comp instanceof CorbaComponent)) {
				continue;
			}
			startX = comp.getConstraint().getX();
			startY = comp.getConstraint().getY();
			countX = 0;
			countY = 0;
			traceComponent((CorbaComponent)comp, editor);
		}
	}
	
	private class ComponentComparator implements Comparator<Component> {
		@Override
		public int compare(Component comp1, Component comp2) {
			if(comp1.getConstraint().getY() < comp1.getConstraint().getY()
					&& comp1.getConstraint().getX() < comp1.getConstraint().getX()) {
				return -1;
			}
			return 1;
		}
	}
	
	private void traceComponent(CorbaComponent comp, SystemDiagramEditor editor) {
		for(Port port : comp.getInports()) {
			tracePort(port, editor, PortType.DataInPort);
		}
		for(Port port : comp.getOutports()) {
			tracePort(port, editor, PortType.DataOutPort);
		}
		for(ServicePort port : comp.getServiceports()) {
			tracePort(port, editor, PortType.ServicePort);
		}
	}
	
	private void tracePort(Port port, SystemDiagramEditor editor, PortType porttype) {
		EList<ConnectorProfile> conns = port.getConnectorProfiles();
		if(conns.isEmpty()) return;
		
		String sourcePort = port.getOriginalPortString();
		
		for(ConnectorProfile con : conns) {
			String sourceP = con.getSourceString();
			String targetP = con.getTargetString();
			String targetPort = "";
			if(sourcePort.equals(sourceP)) {
				targetPort = targetP;
			} else if(sourcePort.equals(targetP)) {
				targetPort = sourceP;
			}
			
			// Search connected Component
			NameServerManager ns = NameServerManagerImpl.getInstance();
			EList<?> nscomps = ns.getNodes();
			CorbaComponent targetComp = searchComponent(nscomps, targetPort);
			if(targetComp == null) continue;
			
			List<Component> compList = editor.getSystemDiagram().getRegisteredComponents();
			if(compList.contains(targetComp)) {
				continue;
			}
			Rectangle rect = searchPosition(editor, porttype);
			targetComp.setConstraint(rect);
			
			//コンポーネントの作成，描画
			CreateCommand command = new CreateCommand();
			command.setParent(editor.getSystemDiagram());
			command.setTarget(targetComp);
			command.execute();
			
			traceComponent((CorbaComponent)targetComp, editor);
		}
	}
	private Rectangle searchPosition(SystemDiagramEditor editor, PortType porttype) {
		if(porttype == PortType.ServicePort) {
			countY++;
		} else {
			if(porttype == PortType.DataInPort) {
				countX--;
			} else {
				countX++;
			}
		}
		if(startX + countX * 200 < 0) {
			countX++;
			countY++;
		}
		int posX = startX + countX * 200;
		int posY = startY + countY * 150;
		
		for(int index=0; index<10; index++) {
			if(checkCompPos(editor, posX, posY)) break;
			countY++;
			posX = startX + countX * 200;
			posY = startY + countY * 150;
		}
		
		Rectangle rect = new Rectangle();
		rect.setHeight(-1);
		rect.setWidth(-1);
		rect.setX(posX);
		rect.setY(posY);
		
		return rect;
	}
	
	private boolean checkCompPos(SystemDiagramEditor editor, int posX, int posY) {
		List<Component> compList = editor.getSystemDiagram().getRegisteredComponents();
		for(Component each : compList) {
			int eX = each.getConstraint().getX();
			int eY = each.getConstraint().getY();
			if(eX <= posX && posX <= eX + 180 && eY <= posY && posY <= eY + 80) {
//			if(eX <= posX && posX <= eX + 200 && eY <= posY && posY <= eY + 100) {
//			if(posX <= eX && posX <= eX + 150 && posY <= eY && posY <= eY + 100) {
				return false;
			}
		}
		return true;
	}
	
	private CorbaComponent searchComponent(EList target, String targetPort) {
		for(int index=0;index<target.size();index++) {
			if( target.get(index) instanceof NamingObjectNode ) {
				NamingObjectNode obj = ((NamingObjectNode)target.get(index));
				try {
					if( obj.getCorbaObject()._is_a(RTObjectHelper.id()) ) {
						RTObject rtobj = RTObjectHelper.narrow(obj.getCorbaObject());
						for(RTC.PortService port : rtobj.get_ports()) {
							if(port.toString().equals(targetPort)) {
								CorbaComponent component = (CorbaComponent)(jp.go.aist.rtm.toolscommon.model.component.Component) AdapterUtil.getAdapter(obj,jp.go.aist.rtm.toolscommon.model.component.Component.class);
								obj.getSynchronizationSupport().getSynchronizationManager().assignSynchonizationSupport(component);					
								component.synchronizeManually();
								component.setIor(obj.getCorbaObject().toString());
								return component;
							}
						}
					}
				} catch (Exception e) {
					LOGGER.error("Fail to search component", e);
				}
			} else {
				EList nscomps = ((NamingContextNode)target.get(index)).getNodes();
				return searchComponent(nscomps, targetPort);
			}
		}
		return null;
	}
}
