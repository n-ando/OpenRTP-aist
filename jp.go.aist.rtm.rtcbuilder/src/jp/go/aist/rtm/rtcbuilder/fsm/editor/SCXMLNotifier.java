package jp.go.aist.rtm.rtcbuilder.fsm.editor;

import java.util.List;

import jp.go.aist.rtm.rtcbuilder.fsm.EventParam;

public interface SCXMLNotifier {
	void notifyContents(String contents, List<EventParam> eventList);

}
