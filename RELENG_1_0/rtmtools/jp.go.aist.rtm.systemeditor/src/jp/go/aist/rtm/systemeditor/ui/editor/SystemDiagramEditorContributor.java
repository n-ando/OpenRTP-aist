package jp.go.aist.rtm.systemeditor.ui.editor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ComponentEditPart;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

/**
 * �V�X�e���_�C�A�O������ActionBarContributor�N���X
 */
public class SystemDiagramEditorContributor extends ActionBarContributor {

	private StatusLineDrawer statusLineDrawer;

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void buildActions() {
		 addRetargetAction(new DeleteRetargetAction());
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void declareGlobalActionKeys() {
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void contributeToToolBar(IToolBarManager toolBarManager) {
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void contributeToStatusLine(
			final IStatusLineManager statusLineManager) {
		statusLineDrawer = new StatusLineDrawer(getPage(), statusLineManager);

		super.contributeToStatusLine(statusLineManager);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void dispose() {
		if (statusLineDrawer != null) {
			statusLineDrawer.dispose();
		}

		super.dispose();
	}

	/**
	 * �X�e�[�^�X�s��\�����郊�X�i�N���X
	 * <p>
	 * ComponentEditPart�ɑ΂��Ďd�|����
	 */
	private final class StatusLineDrawer implements ISelectionListener,
			PropertyChangeListener {
		private final IStatusLineManager manager;

		private ComponentEditPart componentEditPart;
		
		private IWorkbenchPage page;

		private StatusLineDrawer(IWorkbenchPage page, IStatusLineManager manager) {
			super();
			this.manager = manager;
			this.page = page;

			page.addSelectionListener(this);
		}

		/**
		 * {@inheritDoc}
		 */
		public void selectionChanged(IWorkbenchPart part, ISelection selection) {
			ComponentEditPart tempComponenEditPart = null;
			if (selection instanceof IStructuredSelection) {
				IStructuredSelection structuredSelection = (IStructuredSelection) selection;

				Object firstElement = structuredSelection.getFirstElement();
				if (firstElement != null
						&& firstElement instanceof ComponentEditPart) {
					tempComponenEditPart = (ComponentEditPart) firstElement;
				}
			}

			if (componentEditPart != null) {
				componentEditPart.removePropertyChangeListener(this);
			}

			componentEditPart = tempComponenEditPart;

			if (componentEditPart != null) {
				componentEditPart.addPropertyChangeListener(this);
			}
			
			drawMessage();
		}

		private void drawMessage() {
			page.getWorkbenchWindow().getShell().getDisplay().asyncExec(
					new Runnable() {
						public void run() {
							String message = ""; //$NON-NLS-1$
							if (componentEditPart != null) {
								IFigure figure = componentEditPart.getFigure();

								Rectangle bounds = figure.getBounds();
								message = Messages.getString("SystemDiagramEditorContributor.1") + bounds.x + "," + bounds.y //$NON-NLS-1$ //$NON-NLS-2$
										+ Messages.getString("SystemDiagramEditorContributor.3") + bounds.width + "," //$NON-NLS-1$ //$NON-NLS-2$
										+ bounds.height + ")"; //$NON-NLS-1$
							}

							manager.setMessage(message);
						}
					});
		}
		/**
		 * {@inheritDoc}
		 */
		public void propertyChange(PropertyChangeEvent evt) {
			drawMessage();
		}

		public void dispose() {
			if (componentEditPart != null) {
				componentEditPart.removePropertyChangeListener(this);
			}

			page.removeSelectionListener(this);
		}
	}

}