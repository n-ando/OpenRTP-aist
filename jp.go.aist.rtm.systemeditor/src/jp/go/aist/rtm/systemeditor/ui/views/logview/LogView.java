package jp.go.aist.rtm.systemeditor.ui.views.logview;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.systemeditor.RTSystemEditorPlugin;
import jp.go.aist.rtm.systemeditor.ui.util.LoggerHandler;
import jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper.ConfigurationSetConfigurationWrapper;
import jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper.NamedValueConfigurationWrapper;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind;
import jp.go.aist.rtm.toolscommon.model.component.util.RTCLogStore;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;
import sun.rmi.log.LogHandler;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

public class LogView extends ViewPart {

	static final OpenRTM.LogLevel[] LEVEL_LIST = new OpenRTM.LogLevel[] {
			OpenRTM.LogLevel.ERROR, //
			OpenRTM.LogLevel.WARN, //
			OpenRTM.LogLevel.INFO, //
			OpenRTM.LogLevel.NORMAL, //
			OpenRTM.LogLevel.DEBUG, //
			OpenRTM.LogLevel.TRACE, //
			OpenRTM.LogLevel.VERBOSE, // 
			OpenRTM.LogLevel.PARANOID //
	};

	static final int PROPERTY_DISP = 0;
	static final int PROPERTY_RTC = 1;

	static final int PROPERTY_TIME = 0;
	static final int PROPERTY_LEVEL = 1;
	static final int PROPERTY_COMP = 2;
	static final int PROPERTY_LOGGER = 3;
	static final int PROPERTY_BODY = 4;

	TableViewer rtclistTableViewer;
	Table rtclistTable;
	TableViewer rtclogTableViewer;
	Table rtclogTable;

	SystemDiagram targetDiagram;

	RTCStore rtcStore;
	List<RTCLog> logList;

	LogViewerFilter filter;

	SimpleDateFormat df;
	
	private LoggerHandler handler;
	private Text txtLog;

	public LogView() {
		this.df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	public LogView getLogView() {
		return this;
	}

	@Override
	public void createPartControl(Composite parent) {
		GridLayout gl;
		GridData gd;
		
		gl = new GridLayout();
		gl.marginWidth = 0;
		gl.marginHeight = 0;
		gl.numColumns = 5;
		parent.setLayout(gl);

		Label portNo = new Label(parent, SWT.NONE);
		gd = new GridData();
		gd.horizontalSpan = 0;
		gd.verticalSpan = 0;
		portNo.setLayoutData(gd);
		portNo.setText("PortNo:");

		Text txtPort = new Text(parent, SWT.BORDER);
		gd = new GridData();
		gd.horizontalSpan = 0;
		gd.verticalSpan = 0;
		gd.widthHint = 100;
		txtPort.setLayoutData(gd);
		txtPort.setText("24224");
		
		Button btnStart = new Button(parent, SWT.NONE);
		gd = new GridData();
		gd.horizontalSpan = 0;
		gd.verticalSpan = 0;
		gd.widthHint = 50;
		btnStart.setLayoutData(gd);
		btnStart.setText("Start");
		btnStart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String strPort = txtPort.getText();
				int portNo = Integer.parseInt(strPort);
				handler = new LoggerHandler();
				handler.startServer(portNo, txtLog);
			}
		});

		Button btnStop = new Button(parent, SWT.NONE);
		gd = new GridData();
		gd.horizontalSpan = 0;
		gd.verticalSpan = 0;
		gd.widthHint = 50;
		btnStop.setLayoutData(gd);
		btnStop.setText("Stop");
		btnStop.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(handler==null) return;
				handler.stopServer();
			}
		});
		
		Label lblDummy = new Label(parent, SWT.NONE);
		gd = new GridData();
		gd.grabExcessHorizontalSpace = true;
		lblDummy.setLayoutData(gd);
		/////

		txtLog = new Text(parent, SWT.MULTI|SWT.BORDER|SWT.H_SCROLL|SWT.V_SCROLL);
		gd = new GridData();
		gd.horizontalSpan = 5;
		gd.verticalSpan = 0;
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.grabExcessVerticalSpace = true;
		txtLog.setLayoutData(gd);
		
//		GridLayout gl;
//		GridData gd;
//
//		gl = new GridLayout();
//		gl.numColumns = 3;
//		parent.setLayout(gl);
//
//		SashForm sashForm = new SashForm(parent, SWT.HORIZONTAL);
//		gd = new GridData();
//		gd.horizontalAlignment = SWT.FILL;
//		gd.verticalAlignment = SWT.FILL;
//		gd.grabExcessHorizontalSpace = true;
//		gd.grabExcessVerticalSpace = true;
//		gd.horizontalSpan = 2;
//		sashForm.setLayoutData(gd);
//
//		createRTCListPart(sashForm);
//
//		createRTCLogPart(sashForm);
//
//		sashForm.setWeights(new int[] { 20, 80 });
//
//		setSiteSelection();
	}

	Composite createRTCListPart(SashForm sash) {
		GridLayout gl;
		GridData gd;

		Composite composite = new Composite(sash, SWT.FILL);
		gl = new GridLayout();
		gl.marginWidth = 0;
		gl.marginHeight = 0;
		gl.numColumns = 1;
		composite.setLayout(gl);

		rtclistTableViewer = new TableViewer(composite, SWT.FULL_SELECTION
				| SWT.SINGLE | SWT.BORDER);
		rtclistTableViewer.setContentProvider(new ArrayContentProvider());

		rtclistTable = rtclistTableViewer.getTable();
		rtclistTable.setLinesVisible(true);
		rtclistTable.setHeaderVisible(true);
		gd = new GridData();
		gd.verticalAlignment = SWT.FILL;
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.grabExcessHorizontalSpace = true;
		rtclistTable.setLayoutData(gd);

		gl = new GridLayout(1, false);
		gl.numColumns = 1;
		rtclistTable.setLayout(gl);

		TableViewerColumn col;
		col = createColumn(rtclistTableViewer, "", 30);
		col.setEditingSupport(new RTCEditingSupport(rtclistTableViewer,
				PROPERTY_DISP));

		createColumn(rtclistTableViewer, "component", 90);

		rtclistTableViewer.setLabelProvider(new RTCLabelProvider());

		Combo levelCombo = new Combo(composite, SWT.READ_ONLY);
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		levelCombo.setLayoutData(gd);

		for (OpenRTM.LogLevel lv : LEVEL_LIST) {
			levelCombo.add(RTCLogStore.toLevelName(lv));
		}
		levelCombo.select(0);
		levelCombo.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				Combo c = (Combo) e.widget;
				if (c.getSelectionIndex() < 0) {
					return;
				}
				OpenRTM.LogLevel lv = LEVEL_LIST[c.getSelectionIndex()];
				filter.level = lv;
			}
		});

		return composite;
	}

	Composite createRTCLogPart(SashForm sash) {
		GridLayout gl;
		GridData gd;

		Composite composite = new Composite(sash, SWT.FILL);
		gl = new GridLayout();
		gl.numColumns = 2;
		gl.marginWidth = 0;
		gl.marginHeight = 0;
		composite.setLayout(gl);

		rtclogTableViewer = new TableViewer(composite, SWT.FULL_SELECTION
				| SWT.SINGLE | SWT.BORDER);
		rtclogTableViewer.setContentProvider(new ArrayContentProvider());

		filter = new LogViewerFilter();
		rtclogTableViewer.setFilters(new ViewerFilter[] { filter });
		filter.rtcNames.add("ConsoleIn0");

		rtclogTable = rtclogTableViewer.getTable();
		rtclogTable.setLinesVisible(true);

		gd = new GridData();
		gd.verticalAlignment = SWT.FILL;
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 2;
		rtclogTable.setLayoutData(gd);
		rtclogTable.setHeaderVisible(true);

		createColumn(rtclogTableViewer, "time", 80);
		createColumn(rtclogTableViewer, "level", 60);
		createColumn(rtclogTableViewer, "component", 90);
		createColumn(rtclogTableViewer, "logger", 60);
		createColumn(rtclogTableViewer, "message", 120);

		rtclogTableViewer.setLabelProvider(new LogLabelProvider());

		return composite;
	}

	TableViewerColumn createColumn(TableViewer viewer, String title, int width) {
		TableViewerColumn col;
		col = new TableViewerColumn(viewer, SWT.NONE);
		col.getColumn().setText(title);
		col.getColumn().setWidth(width);
		return col;
	}

	/** 内部モデル(RTC一覧)を構築 */
	void buildData() {
		if (targetDiagram == null) {
			return;
		}
		rtcStore = RTCStore.get(targetDiagram);

		refreshData();
	}

	/** 内部モデル(ログ一覧)を構築 [非同期] */
	void buildLogData() {
		if (rtcStore == null) {
			return;
		}
		if (logList == null) {
			logList = new ArrayList<RTCLog>();
		}
		logList.clear();
		//
		List<String> id_list = new ArrayList<String>();
		for (RTCStore.RTC rtc : rtcStore.rtcs) {
			if (rtc.comp.getLogObserver() == null) {
				continue;
			}
			id_list.add(rtc.comp.getLogObserver().getServiceProfile().id);
		}
		List<RTCLogStore.Record> records = RTCLogStore.eINSTANCE.find(id_list,
				10000);
		for (RTCLogStore.Record r : records) {
			RTCLog rlog = new RTCLog(r);
			logList.add(rlog);
		}
	}

	/** 内部モデル(RTC一覧)から表示 */
	void refreshData() {
		rtclistTableViewer.setInput(Collections.EMPTY_LIST);
		if (rtcStore != null) {
			rtclistTableViewer.setInput(rtcStore.rtcs);
			if (rtclistTable.getItemCount() <= 0) {
				return;
			}
		}
	}

	/** 内部モデル(ログ一覧)から表示 [非同期] */
	void refreshLogData() {
		if (rtcStore == null) {
			return;
		}
		filter.rtcNames.clear();
		for (RTCStore.RTC rtc : rtcStore.rtcs) {
			if (rtc.display) {
				filter.rtcNames.add(rtc.comp.getInstanceNameL());
			}
		}
		try {
			rtclogTableViewer.getControl().getDisplay().asyncExec(
					new Runnable() {
						@Override
						public void run() {
							if (rtclogTableViewer.getControl().isDisposed()) {
								return;
							}
							if (rtclogTableViewer.getInput() == null) {
								rtclogTableViewer.setInput(logList);
							}
							rtclogTableViewer.refresh();
						}
					});
		} catch (Exception e) {
		}
	}

	@Override
	public void dispose() {
		if (refresher != null) {
			refresher.end();
		}
		super.dispose();
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}

	/** RTC一覧を表すクラス */
	static class RTCStore {
		static Map<SystemDiagram, RTCStore> store = new HashMap<SystemDiagram, RTCStore>();

		public static RTCStore get(SystemDiagram diagram) {
			RTCStore result = store.get(diagram);
			if (result == null) {
				result = new RTCStore();
				store.put(diagram, result);
			}
			result.reset(diagram);
			return result;
		}

		List<RTC> rtcs = new ArrayList<RTC>();

		void reset(SystemDiagram diagram) {
			List<String> pathes = new ArrayList<String>();
			for (RTC rtc : rtcs) {
				if (rtc.display) {
					pathes.add(rtc.comp.getPathId());
				}
			}
			rtcs.clear();
			for (Component comp : diagram.getRegisteredComponents()) {
				if (!(comp instanceof CorbaComponent)) {
					continue;
				}
				CorbaComponent corbaComp = (CorbaComponent) comp;
				if (corbaComp.getLogObserver() == null) {
					continue;
				}
				RTC rtc = new RTC(corbaComp);
				if (pathes.contains(comp.getPathId())) {
					rtc.display = true;
				}
				rtcs.add(rtc);
			}
		}

		static class RTC {
			boolean display;
			CorbaComponent comp;

			RTC(CorbaComponent comp) {
				this.comp = comp;
				this.display = false;
			}
		}
	}

	/** RTCログを表すクラス */
	static class RTCLog {
		RTC.Time time;
		OpenRTM.LogLevel level;
		String levelName;
		String rtcName;
		String logger;
		String message;

		RTCLog(RTCLogStore.Record r) {
			this.time = r.getTime();
			this.level = r.getLevel();
			this.levelName = r.getLevelName();
			this.rtcName = r.getRtcName();
			this.logger = r.getLoggerName();
			this.message = r.getMessage();
		}
	}

	/** RTC一覧表示のLabelProvider */
	public class RTCLabelProvider extends LabelProvider implements
			ITableLabelProvider {
		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			Image result = null;
			RTCStore.RTC entry = (RTCStore.RTC) element;
			if (columnIndex == 0) {
				if (entry.display) {
					result = RTSystemEditorPlugin
							.getCachedImage("icons/checkbox_checked.png");
				} else {
					result = RTSystemEditorPlugin
							.getCachedImage("icons/checkbox_unchecked.png");
				}
			}
			return result;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			RTCStore.RTC entry = (RTCStore.RTC) element;
			if (columnIndex == 1) {
				return entry.comp.getInstanceNameL();
			}
			return null;
		}
	}

	/** RTC一覧表示のEditingSupport */
	public class RTCEditingSupport extends EditingSupport {
		CellEditor editor;
		int column;

		public RTCEditingSupport(ColumnViewer viewer, int column) {
			super(viewer);
			// Create the correct editor based on the column index
			switch (column) {
			case PROPERTY_DISP:
				editor = new CheckboxCellEditor(((TableViewer) viewer)
						.getTable());
				break;
			default:
				break;
			}
			this.column = column;
		}

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			return editor;
		}

		@Override
		protected Object getValue(Object element) {
			if (!(element instanceof RTCStore.RTC))
				return null;
			RTCStore.RTC entry = (RTCStore.RTC) element;
			switch (this.column) {
			case PROPERTY_DISP:
				return entry.display;
			default:
				break;
			}
			return null;
		}

		@Override
		protected void setValue(Object element, Object value) {
			if (element instanceof RTCStore.RTC == false)
				return;
			RTCStore.RTC entry = (RTCStore.RTC) element;
			switch (this.column) {
			case PROPERTY_DISP:
				entry.display = ((Boolean) value).booleanValue();
				break;
			default:
				break;
			}
			getViewer().update(element, null);
		}
	}

	/** ログ一覧表示のViewerFilter */
	public static class LogViewerFilter extends ViewerFilter {
		List<String> rtcNames = new ArrayList<String>();
		OpenRTM.LogLevel level = OpenRTM.LogLevel.ERROR;

		@Override
		public boolean select(Viewer viewer, Object parentElement,
				Object element) {
			RTCLog entry = (RTCLog) element;
			if (!rtcNames.contains(entry.rtcName)) {
				return false;
			}
			if (level.value() < entry.level.value()) {
				return false;
			}
			return true;
		}
	}

	/** ログ一覧表示のLabelProvider */
	public class LogLabelProvider extends LabelProvider implements
			ITableLabelProvider, ITableColorProvider {
		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			RTCLog entry = (RTCLog) element;
			switch (columnIndex) {
			case PROPERTY_TIME:
				long milisec = (long) entry.time.sec * 1000;
				Date date = new Date(milisec);
				return String.format("%s.%09d", df.format(date),
						entry.time.nsec);
			case PROPERTY_LEVEL:
				return entry.levelName;
			case PROPERTY_COMP:
				return entry.rtcName;
			case PROPERTY_LOGGER:
				return entry.logger;
			case PROPERTY_BODY:
				return entry.message;
			default:
				break;
			}
			return null;
		}

		@Override
		public Color getBackground(Object element, int columnIndex) {
			return null;
		}

		@Override
		public Color getForeground(Object element, int columnIndex) {
			return null;
		}
	}

	static class Refresher extends Thread {
		LogView view;
		boolean running;

		public Refresher(LogView view) {
			this.view = view;
		}

		@Override
		public void run() {
			running = true;
			while (running) {
				try {
					view.buildLogData();
					view.refreshLogData();
					//
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					running = false;
				}
			}
		}

		public void end() {
			running = false;
		}
	}

	Refresher refresher;

	ISelectionListener selectionListener = new ISelectionListener() {
		@Override
		public void selectionChanged(IWorkbenchPart part, ISelection selection) {
			targetDiagram = null;
			if (selection instanceof IStructuredSelection) {
				IStructuredSelection ss = (IStructuredSelection) selection;
				Object firstElement = ss.getFirstElement();
				Object adapter = AdapterUtil.getAdapter(firstElement,
						SystemDiagram.class);
				if (adapter != null) {
					SystemDiagram sd = (SystemDiagram) adapter;
					if (SystemDiagramKind.ONLINE_LITERAL.equals(sd.getKind())) {
						targetDiagram = sd;
					}
				}
			}
			buildData();
			//
			if (targetDiagram == null) {
				if (refresher != null) {
					refresher.end();
				}
				return;
			}
			if (refresher == null || !refresher.isAlive()) {
				refresher = new Refresher(getLogView());
				refresher.setDaemon(true);
				refresher.start();
			}
		}
	};

	void setSiteSelection() {
		if (getSite() == null) {
			return;
		}
		getSite().getWorkbenchWindow().getSelectionService()
				.addSelectionListener(selectionListener);

		// SelectionProviderを登録(プロパティ・ビュー連携)
		getSite().setSelectionProvider(new ISelectionProvider() {
			public void addSelectionChangedListener(
					ISelectionChangedListener listener) {
			}

			public ISelection getSelection() {
				StructuredSelection result = null;
				if (targetDiagram != null) {
					result = new StructuredSelection(targetDiagram);
				}
				return result;
			}

			public void removeSelectionChangedListener(
					ISelectionChangedListener listener) {
			}

			public void setSelection(ISelection selection) {
			}
		});
	}

}
