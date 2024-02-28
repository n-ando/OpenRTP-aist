package jp.go.aist.rtm.rtcbuilder.ui.editpart;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;

import jp.go.aist.rtm.rtcbuilder.model.component.DataInPort;
import jp.go.aist.rtm.rtcbuilder.ui.figure.ComponentFigure;
import jp.go.aist.rtm.rtcbuilder.ui.figure.InPortBaseFigure;
import jp.go.aist.rtm.rtcbuilder.ui.figure.InPortFigure;

/**
 * InPortのEditPartクラス
 */
public class InPortEditPart extends PortEditPartBase {

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected IFigure createFigure() {
		ComponentFigure parentFigure = (ComponentFigure)((ComponentEditPart)this.getParent()).getFigure();
		int direction = this.getModel().getDirection().getValue();
		
		InPortBaseFigure result = new InPortBaseFigure(getModel(), direction);

		int index = this.getModel().getIndex();
		result.setSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
		result.setLocation(new Point(0,0));

		InPortFigure innerFigure = (InPortFigure)modifyPosition(parentFigure, direction, index, result.getInnerFigure());
		result.setInnerFigure(innerFigure);

		return result;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public DataInPort getModel() {
		return (DataInPort) super.getModel();
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void refreshVisuals() {
		InPortBaseFigure inport = (InPortBaseFigure)getFigure();
		originalChildren = inport.getParent().getChildren();
		super.refreshVisuals();
	}
}
