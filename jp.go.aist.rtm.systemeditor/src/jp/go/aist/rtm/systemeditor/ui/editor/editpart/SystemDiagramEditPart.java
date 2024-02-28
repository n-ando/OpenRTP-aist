package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.ui.PlatformUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.go.aist.rtm.systemeditor.ui.editor.editpolicy.GraphicalConnectorCreateManager;
import jp.go.aist.rtm.systemeditor.ui.editor.editpolicy.SystemXYLayoutEditPolicy;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

/**
 * システムダイアグラムのEditPartクラス
 */
public class SystemDiagramEditPart extends AbstractEditPart {

	private static final Logger LOGGER = LoggerFactory.getLogger(SystemDiagramEditPart.class);

	private GraphicalConnectorCreateManager connectingPortManager;
	private PortEditPart connectingPortEditPart;

	/**
	 * コンストラクタ
	 *
	 * @param actionRegistry
	 *            ActionRegistry
	 */
	public SystemDiagramEditPart(ActionRegistry actionRegistry) {
		super(actionRegistry);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected IFigure createFigure() {
		FreeformLayer result = new FreeformLayer();
		result.setLayoutManager(new FreeformLayout());
		return result;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE,
				new SystemXYLayoutEditPolicy());
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public List getModelChildren() {
		return getModel().getComponents();
	}

	/**
	 * {@inheritDoc}
	 */
	public void notifyChanged(Notification notification) {
		refreshSystemDiagram();
	}

	public void refreshSystemDiagram() {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			public void run() {
				if (!isActive()) {
					return;
				}
				LOGGER.debug("refreshSystemDiagram: START");
				refreshChildren();
				LOGGER.debug("refreshSystemDiagram: refreshChildren.");
				for (Object o : getChildren()) {
					EditPart child = (EditPart) o;
					LOGGER.debug("refreshSystemDiagram: - child=<{}>", child);
					for (Object o2 : child.getChildren()) {
						LOGGER.debug("refreshSystemDiagram:   - refresh part==<{}>", o2);
						((EditPart) o2).refresh();
					}
				}
				LOGGER.debug("refreshSystemDiagram: END");
			}
		});
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public SystemDiagram getModel() {
		return (SystemDiagram) super.getModel();
	}

	/**
	 * 接続先選択中となっているポートを設定します。<br>
	 * 選択状態を解除する場合はnullを指定します。
	 *
	 * @param connectingPortEditPart
	 *            接続先選択中のポート
	 */
	public void setConnectingPortEditPart(PortEditPart connectingPortEditPart) {
		if (connectingPortEditPart == null) {
			this.connectingPortEditPart = null;
			this.connectingPortManager = null;
			return;
		}
		this.connectingPortEditPart = connectingPortEditPart;
		this.connectingPortManager = new GraphicalConnectorCreateManager(getViewer().getControl().getShell());
		this.connectingPortManager.setFirst(this.connectingPortEditPart.getModel());
	}

	/**
	 * 接続先選択中のポートを取得します。
	 *
	 * @return 接続先選択中のポート
	 */
	public PortEditPart getConnectingPortEditPart() {
		return this.connectingPortEditPart;
	}

	/**
	 * 現在、接続先選択中のポートと接続可能か判定します。
	 *
	 * @param part
	 *            対象ポート
	 * @return 接続先選択中のポートと接続可能な場合はtrue
	 */
	public boolean isConnectablePortEditPart(PortEditPart part) {
		if (this.connectingPortManager != null && part != null) {
			this.connectingPortManager.setSecond(part.getModel());
			return this.connectingPortManager.validate();
		}
		return false;
	}

	/**
	 * ダイアグラム内の全ポートの接続可否を判定し、ポートの描画を更新します。
	 */
	public void refreshViewAllConnectablePort() {
		for (Object o : getChildren()) {
			if (!(o instanceof ComponentEditPart)) {
				continue;
			}
			ComponentEditPart comp = (ComponentEditPart) o;
			@SuppressWarnings("unchecked")
			List<?> children = new ArrayList<>(comp.getChildren());
			for (Object o2 : children) {
				if (!(o2 instanceof PortEditPart)) {
					continue;
				}
				PortEditPart port = (PortEditPart) o2;
				if (isConnectablePortEditPart(port)) {
					PortHelper.refreshViewPortAsConnectable(port, true);
				} else {
					PortHelper.refreshViewPortAsConnectable(port, false);
				}
			}
		}
	}

}
