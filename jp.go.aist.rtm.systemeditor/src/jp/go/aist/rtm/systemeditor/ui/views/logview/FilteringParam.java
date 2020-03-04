package jp.go.aist.rtm.systemeditor.ui.views.logview;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.systemeditor.nl.Messages;

public class FilteringParam {
	public enum FilteringKind {
		ROOT,
		AND,
		OR,
		LEVEL,
		TIME,
		MANAGER,
		IDENTIFIER,
		MESSAGE
	}
	protected FilteringKind kind;
	private FilteringParam parentParam;
	private List<FilteringParam> childParams = new ArrayList<FilteringParam>();
	
	private List<String> levelList = new ArrayList<String>();
	private boolean isFrom;
	private String fromDate;
	private String fromTime;
	private boolean isTo;
	private String toDate;
	private String toTime;
	private String managerCond;
	private String identifierCond;
	private String messageCond;
	
	public FilteringParam(FilteringKind kind, FilteringParam parent) {
		this.kind = kind;
		this.parentParam = parent;
	}
	
	public FilteringKind getKind() {
		return kind;
	}

	public FilteringParam getParentParam() {
		return parentParam;
	}
	public void setParentParam(FilteringParam parentParam) {
		this.parentParam = parentParam;
	}

	public void addChild(FilteringParam child) {
		this.childParams.add(child);
	}

	public List<FilteringParam> getChildParams() {
		return childParams;
	}
	/////
	public List<String> getLevelList() {
		return levelList;
	}

	public boolean isFrom() {
		return isFrom;
	}
	public void setFrom(boolean isFrom) {
		this.isFrom = isFrom;
	}

	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getFromTime() {
		return fromTime;
	}
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public boolean isTo() {
		return isTo;
	}
	public void setTo(boolean isTo) {
		this.isTo = isTo;
	}

	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	//////////
	public String getManagerCond() {
		return managerCond;
	}
	public void setManagerCond(String managerCond) {
		this.managerCond = managerCond;
	}

	public String getIdentifierCond() {
		return identifierCond;
	}
	public void setIdentifierCond(String identifierCond) {
		this.identifierCond = identifierCond;
	}

	public String getMessageCond() {
		return messageCond;
	}
	public void setMessageCond(String messageCond) {
		this.messageCond = messageCond;
	}
	

	@Override
	public String toString() {
		switch(this.kind) {
		case ROOT:
			return "Filtering Condition";
		case AND:
			return "And";
		case OR:
			return "Or";
		case LEVEL:
		{
			StringBuilder builder = new StringBuilder();
			builder.append(Messages.getString("LogView.columnLevel"));
			builder.append("=");
			for(int index=0; index<levelList.size(); index++) {
				if(0<index) builder.append(", ");
				builder.append(levelList.get(index));
			}
			return builder.toString();
		}
		case TIME: {
			StringBuilder builder = new StringBuilder();
			builder.append(Messages.getString("LogView.columnTime"));
			builder.append("=");
			if(isFrom) {
				builder.append("From:").append(fromDate).append(" ").append(fromTime);
			}
			if(isTo) {
				builder.append(" - ");
				builder.append("To:").append(toDate).append(" ").append(toTime);
			}
			return builder.toString();
		}
		case MANAGER: {
			StringBuilder builder = new StringBuilder();
			builder.append(Messages.getString("LogView.columnManager"));
			builder.append("=");
			builder.append(managerCond);
			return builder.toString();
		}
		case IDENTIFIER: {
			StringBuilder builder = new StringBuilder();
			builder.append(Messages.getString("LogView.columnID"));
			builder.append("=");
			builder.append(identifierCond);
			return builder.toString();
		}
		case MESSAGE: {
			StringBuilder builder = new StringBuilder();
			builder.append(Messages.getString("LogView.columnMessage"));
			builder.append("=");
			builder.append(messageCond);
			return builder.toString();
		}
		default:
			return super.toString();
		}
	}
}
