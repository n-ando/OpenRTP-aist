package jp.go.aist.rtm.rtcbuilder.ui.figure;

import org.eclipse.draw2d.XYLayout;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.PlatformUI;

import jp.go.aist.rtm.rtcbuilder.model.component.DataOutPort;
import jp.go.aist.rtm.rtcbuilder.util.RTCUtil;

public class OutPortBaseFigure extends PortFigureBase {
	private OutPortFigure outPortFig;

	public OutPortBaseFigure(DataOutPort outPort, int direction) {
		outPortFig = new OutPortFigure(outPort, direction,
				new Color(PlatformUI.getWorkbench().getDisplay(), RTCUtil.defaultRGBMap.get(RTCUtil.COLOR_DATAPORT)));
		setLayoutManager(new XYLayout());
		add(outPortFig);
	}
	
	public OutPortFigure getInnerFigure() {
		return this.outPortFig;
	}

	public void setInnerFigure(OutPortFigure figure) {
		this.outPortFig = figure;
	}

}
