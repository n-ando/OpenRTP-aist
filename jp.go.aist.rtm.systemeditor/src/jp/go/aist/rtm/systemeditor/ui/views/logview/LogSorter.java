package jp.go.aist.rtm.systemeditor.ui.views.logview;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import OpenRTM.LogLevel;

public class LogSorter extends ViewerComparator {
	public static final int ORDER_ASC = 1;
	public static final int NONE = 0;
	public static final int ORDER_DESC = -1;

	private TableColumn col = null;
	private int colIndex = 0;
	ICustomCompare custom;
	TableViewer viewer;
	Table table;
	private int dir = 0;
	
	
	public LogSorter(TableViewer viewer) {
		this.viewer = viewer;
		this.table = viewer.getTable();
	}
	
	public LogSorter setCustomCompare(ICustomCompare custom) {
		this.custom = custom;
		return this;
	}
	
	@Override
	public int compare(Viewer viewer, Object o1, Object o2) {
		if (dir == NONE || this.col == null) {
			return 0;
		}
		if (custom == null) {
			return dir * compareNormal(o1, o2);
		} else {
			return dir * custom.doCompare(this.col, this.colIndex, o1, o2);
		}
	}
	
	public void setColumn(TableColumn clickedColumn) {
		if (col == clickedColumn) {
			dir = dir * -1;
		} else {
			this.col = clickedColumn;
			this.dir = ORDER_ASC;
		}
		TableColumn[] cols = table.getColumns();
		int colLen = cols.length;;
		for (int i = 0; i < colLen; i++) {
			if (cols[i] == this.col) {
				colIndex = i;
				break;
			}
		}
		table.setSortColumn(clickedColumn);
		switch (dir) {
		case ORDER_ASC:
			table.setSortDirection(SWT.UP);
			break;
		case ORDER_DESC:
			table.setSortDirection(SWT.DOWN);
			break;
		}
		viewer.refresh();
	}

	protected int compareNormal(Object e1, Object e2) {
		ITableLabelProvider labelProvider = (ITableLabelProvider) viewer.getLabelProvider();
		String t1 = labelProvider.getColumnText(e1, colIndex);
		String t2 = labelProvider.getColumnText(e2, colIndex);
		if(colIndex==1) {
			return convertLogLevel(t1).compareTo(convertLogLevel(t2));
		} else {
			return t1.compareTo(t2);
		}
	}
	
	private Integer convertLogLevel(String source) {
		switch(source) {
		case "SILENT":
			return LogLevel._SILENT;
		case "ERROR":
			return LogLevel._ERROR;
		case "WARN":
			return LogLevel._WARN;
		case "INFO":
			return LogLevel._INFO;
		case "NORMAL":
			return LogLevel._NORMAL;
		case "DEBUG":
			return LogLevel._DEBUG;
		case "TRACE":
			return LogLevel._TRACE;
		case "VERBOSE":
			return LogLevel._VERBOSE;
		case "PARANOID":
			return LogLevel._PARANOID;
		default:
			return LogLevel._SILENT;
		}
	}
	
	public static interface ICustomCompare {
		public int doCompare(TableColumn col, int index, Object o1, Object o2);
	}
}
