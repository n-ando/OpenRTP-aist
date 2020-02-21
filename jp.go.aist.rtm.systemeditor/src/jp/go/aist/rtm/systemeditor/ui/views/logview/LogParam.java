package jp.go.aist.rtm.systemeditor.ui.views.logview;

public class LogParam {
	private String message;
	private String raw_message;
	
	public String getMessage() {
		return message;
	}

	public String getRaw_message() {
		return raw_message;
	}

	public LogParam(String message, String rawMessage) {
		this.message = message;
		this.raw_message = rawMessage;
	}
}
