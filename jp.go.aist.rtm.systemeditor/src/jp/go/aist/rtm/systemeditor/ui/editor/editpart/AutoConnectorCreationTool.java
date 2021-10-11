package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import static jp.go.aist.rtm.systemeditor.ui.util.RTMixin.to_cid;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.gef.tools.ConnectionDragCreationTool;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.ui.PlatformUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.go.aist.rtm.systemeditor.RTSystemEditorPlugin;
import jp.go.aist.rtm.toolscommon.model.component.impl.PortConnectorImpl;

/**
 * コネクタを作成するツール
 */
public class AutoConnectorCreationTool extends ConnectionDragCreationTool {

	private static final Logger LOGGER = LoggerFactory.getLogger(AutoConnectorCreationTool.class);

	private static AutoConnectorCreationTool instance = null;

	/** connection toolのインスタンスを初期化、取得 (singleton) */
	public static AutoConnectorCreationTool getInstance() {
		if (instance == null) {
			instance = new AutoConnectorCreationTool();
			instance.setFactory(new SimpleFactory(PortConnectorImpl.class));
		}
		return instance;
	};
	
	public AutoConnectorCreationTool() {
		String targetOS = System.getProperty("os.name").toLowerCase();
		String targetVersion = System.getProperty("os.version").toLowerCase();
		Cursor CURSOR_PLUG = null;
		Cursor CURSOR_PLUG_NOT= null;
		if(targetOS.toLowerCase().startsWith("linux")) {
			CURSOR_PLUG = createCursor("icons/plug.bmp", "icons/plugmask.png");
			CURSOR_PLUG_NOT= createCursor("icons/plugnot.bmp", "icons/plugmasknot.png");
		} else {
			CURSOR_PLUG = createCursor("icons/plugmask.png", "icons/plug.bmp");
			CURSOR_PLUG_NOT= createCursor("icons/plugmasknot.png", "icons/plugnot.bmp");
		}
		
		setDefaultCursor(CURSOR_PLUG);
		setDisabledCursor(CURSOR_PLUG_NOT);
	}
		
	private static Cursor createCursor(String sourceName, String maskName) {
		ImageDescriptor src = RTSystemEditorPlugin.getImageDescriptor(sourceName);
		ImageDescriptor mask = RTSystemEditorPlugin.getImageDescriptor(maskName);
		
		return new Cursor(null, src.getImageData(), mask.getImageData(), 0, 0);
	}

	/**
	 * ポートの Figureへコネクションツールを割り当てます。
	 * 
	 * @param part
	 * @param figure
	 */
	public static void fetchTool(final EditPart part, IFigure figure) {

		final EditPartViewer viewer = part.getViewer();
		final EditDomain domain = viewer.getEditDomain();

		figure.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent me) {
			}

			@Override
			public void mouseEntered(MouseEvent me) {
				if (part instanceof PortEditPart) {
					PortConnectorEditPart connPart = findSelectedPortConnectorEditPart((PortEditPart) part);
					if (connPart != null) {
						return;
					}
				}
				domain.setActiveTool(getInstance());
			}

			@Override
			public void mouseExited(MouseEvent me) {
				domain.setActiveTool(domain.getDefaultTool());
			}

			@Override
			public void mouseHover(MouseEvent me) {
			}

			@Override
			public void mouseMoved(MouseEvent me) {
			}
		});

		figure.addMouseListener(new MouseListener.Stub() {
			@Override
			public void mousePressed(MouseEvent me) {
				// right click
				if (me.button == 3) {
					domain.setActiveTool(domain.getDefaultTool());
				}
			}
		});
	}

	/**
	 * 対象のポートに対して、接続可能ポートを表示します。
	 * 
	 * @param diagram
	 * @param port
	 */
	public static void showAllConnectablePort(final SystemDiagramEditPart diagram, PortEditPart port) {
		LOGGER.trace("showAllConnectablePort: diagram=<{}> port=<{}>", to_cid(diagram), to_cid(port));
		if (diagram != null) {
			diagram.setConnectingPortEditPart(port);
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				@Override
				public void run() {
					diagram.refreshViewAllConnectablePort();
				}
			});
		}
	}

	/**
	 * 接続可能ポートの表示を解除します。
	 * 
	 * @param diagram
	 */
	public static void hideAllConnectablePort(final SystemDiagramEditPart diagram) {
		LOGGER.trace("hideAllConnectablePort: diagram=<{}>", to_cid(diagram));
		if (diagram != null) {
			diagram.setConnectingPortEditPart(null);
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				@Override
				public void run() {
					diagram.refreshViewAllConnectablePort();
				}
			});
		}
	}

	/**
	 * Viewerからシステムダイアグラムの EditPartを検索します。
	 * 
	 * @param viewer
	 * @return
	 */
	public static SystemDiagramEditPart findSystemDiagramEditPart(EditPartViewer viewer) {
		if (viewer == null) {
			return null;
		}
		List<?> list = viewer.getRootEditPart().getChildren();
		if (!list.isEmpty() && list.get(0) instanceof SystemDiagramEditPart) {
			SystemDiagramEditPart sd = (SystemDiagramEditPart) list.get(0);
			return sd;
		}
		return null;
	}

	/**
	 * ポート接続の EditPartからシステムダイアグラムの EditPartを検索します。
	 * 
	 * @param conn
	 * @return
	 */
	public static SystemDiagramEditPart findSystemDiagramEditPart(PortConnectorEditPart conn) {
		if (conn == null) {
			return null;
		}
		List<?> list = conn.getParent().getChildren();
		if (!list.isEmpty() && list.get(0) instanceof SystemDiagramEditPart) {
			SystemDiagramEditPart sd = (SystemDiagramEditPart) list.get(0);
			return sd;
		}
		return null;
	}

	/**
	 * ポートにつながる選択中のポートコネクタを検索します。
	 * 
	 * @param port
	 * @return
	 */
	public static PortConnectorEditPart findSelectedPortConnectorEditPart(PortEditPart port) {
		PortConnectorEditPart connPart = null;
		for (Object o : port.getViewer().getSelectedEditParts()) {
			if (o instanceof PortConnectorEditPart) {
				connPart = (PortConnectorEditPart) o;
				break;
			}
		}
		if (connPart != null) {
			if (port == connPart.getSource() || port == connPart.getTarget()) {
				return connPart;
			}
		}
		return null;
	}

	@Override
	public void activate() {
		super.activate();
	}

	@Override
	public void deactivate() {
		super.deactivate();
	}

	@Override
	protected void handleFinished() {
		LOGGER.trace("handleFinished: currViewer={} currCommand={} target={}", to_cid(getCurrentViewer()),
				to_cid(getCurrentCommand()), to_cid(getTargetEditPart()));
		getDomain().setActiveTool(getDomain().getDefaultTool());

		super.handleFinished();
	}

	@Override
	protected boolean handleDragStarted() {
		LOGGER.trace("handleDragStarted: currViewer={} currCommand={} target={}", to_cid(getCurrentViewer()),
				to_cid(getCurrentCommand()), to_cid(getTargetEditPart()));
		// Port接続モード開始
		SystemDiagramEditPart sd = findSystemDiagramEditPart(getCurrentViewer());
		PortEditPart port = (PortEditPart) getTargetEditPart();
		showAllConnectablePort(sd, port);

		return super.handleDragStarted();
	}

	@Override
	protected boolean handleCreateConnection() {
		LOGGER.trace("handleCreateConnection: currViewer={} currCommand={} target={}", to_cid(getCurrentViewer()),
				to_cid(getCurrentCommand()), to_cid(getTargetEditPart()));
		// Port接続モード終了
		SystemDiagramEditPart sd = findSystemDiagramEditPart(getCurrentViewer());
		hideAllConnectablePort(sd);

		return super.handleCreateConnection();
	}

	@Override
	protected boolean handleDrag() {
		return super.handleDrag();
	}

	@Override
	protected boolean handleMove() {
		return super.handleMove();
	}

	@Override
	protected boolean handleDragInProgress() {
		return super.handleDragInProgress();
	}

	@Override
	protected boolean handleKeyDown(KeyEvent e) {
		return super.handleKeyDown(e);
	}

	@Override
	protected boolean handleKeyUp(KeyEvent e) {
		return super.handleKeyUp(e);
	}

	@Override
	protected void handleKeyTraversed(TraverseEvent event) {
		super.handleKeyTraversed(event);
	}

	@Override
	protected boolean handleEnteredEditPart() {
		return super.handleEnteredEditPart();
	}

	@Override
	protected boolean handleExitingEditPart() {
		return super.handleExitingEditPart();
	}

	@Override
	protected boolean handleFocusGained() {
		return super.handleFocusGained();
	}

	@Override
	protected boolean handleFocusLost() {
		return super.handleFocusLost();
	}

	@Override
	protected boolean handleHover() {
		return super.handleHover();
	}

	@Override
	protected boolean handleHoverStop() {
		return super.handleHoverStop();
	}

	@Override
	protected boolean handleViewerEntered() {
		return super.handleViewerEntered();
	}

	@Override
	protected boolean handleViewerExited() {
		return super.handleViewerExited();
	}

	@Override
	protected void handleSourceDeactivated() {
		super.handleSourceDeactivated();
	}

	public boolean isStartedState() {
		return super.isInState(STATE_CONNECTION_STARTED);
	}

}
