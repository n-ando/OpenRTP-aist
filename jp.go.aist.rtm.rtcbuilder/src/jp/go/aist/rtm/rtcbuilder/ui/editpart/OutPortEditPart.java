package jp.go.aist.rtm.rtcbuilder.ui.editpart;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;

import jp.go.aist.rtm.rtcbuilder.model.component.DataOutPort;
import jp.go.aist.rtm.rtcbuilder.ui.figure.ComponentFigure;
import jp.go.aist.rtm.rtcbuilder.ui.figure.OutPortBaseFigure;
import jp.go.aist.rtm.rtcbuilder.ui.figure.OutPortFigure;

/**
 * OutPortのEditPartクラス
 */
public class OutPortEditPart extends PortEditPartBase {

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected IFigure createFigure() {
		ComponentFigure parentFigure = (ComponentFigure)((ComponentEditPart)this.getParent()).getFigure();
		int direction = this.getModel().getDirection().getValue();
		
		OutPortBaseFigure result = new OutPortBaseFigure(getModel(), direction);
		
		int index = this.getModel().getIndex();
		result.setSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
		result.setLocation(new Point(0,0));

		OutPortFigure innerFigure = (OutPortFigure)modifyPosition(parentFigure, direction, index, result.getInnerFigure());
		result.setInnerFigure(innerFigure);

		return result;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public DataOutPort getModel() {
		return (DataOutPort) super.getModel();
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void refreshVisuals() {
		OutPortBaseFigure outport = (OutPortBaseFigure)getFigure();
		originalChildren = outport.getParent().getChildren();
		super.refreshVisuals();
	}

}
