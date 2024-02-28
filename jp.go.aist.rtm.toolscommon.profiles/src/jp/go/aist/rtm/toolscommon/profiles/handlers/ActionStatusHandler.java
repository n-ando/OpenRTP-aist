package jp.go.aist.rtm.toolscommon.profiles.handlers;

import org.openrtp.namespaces.rtc.version03.ActionStatusDoc;


public class ActionStatusHandler extends CommonDocHandler {

	public ActionStatusHandler(Class<?> type) {
		super(type);
	}

	@Override
	public Object createPrototype() {
		ActionStatusDoc target = (ActionStatusDoc)super.createPrototype();
		target.setImplemented("");
		return target;
	}
}
