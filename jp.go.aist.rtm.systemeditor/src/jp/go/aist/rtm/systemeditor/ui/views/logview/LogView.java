package jp.go.aist.rtm.systemeditor.ui.views.logview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import jp.go.aist.rtm.systemeditor.ui.util.LoggerHandler;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;

public class LogView extends ViewPart {
	private LoggerHandler handler;

	private TableViewer rtclogTableViewer;
	private Table rtclogTable;
	private Button btnRaw;
	private Text txtSearch;

	private SystemDiagram targetDiagram;

	private LogViewerFilter filter;
	private LogLabelProvider provider;
	private List<LogParam> logList = new ArrayList<LogParam>();

	public LogView() {
	}

	public LogView getLogView() {
		return this;
	}

	@Override
	public void createPartControl(Composite parent) {
		GridLayout gl = new GridLayout();
		gl.marginWidth = 0;
		gl.marginHeight = 0;
		gl.numColumns = 1;
		parent.setLayout(gl);
		
		createControlPart(parent);
		createRTCLogPart(parent);
		setSiteSelection();
	}

	private void createControlPart(Composite parent) {
		GridLayout gl;
		GridData gd;
		
		Composite composite = new Composite(parent, SWT.FILL);
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 1;
		composite.setLayoutData(gd);
		
		gl = new GridLayout();
		gl.marginWidth = 0;
		gl.marginHeight = 0;
		gl.numColumns = 6;
		composite.setLayout(gl);
		
		Label portNo = new Label(composite, SWT.NONE);
		portNo.setText("PortNo:");

		Text txtPort = new Text(composite, SWT.BORDER);
		gd = new GridData();
		gd.widthHint = 100;
		txtPort.setLayoutData(gd);
		txtPort.setText("24224");
		
		Button btnStart = new Button(composite, SWT.TOGGLE);
		gd = new GridData();
		gd.widthHint = 50;
		btnStart.setLayoutData(gd);
		btnStart.setText("Start");
		btnStart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnStart.getSelection()) {
					String strPort = txtPort.getText();
					int portNo = Integer.parseInt(strPort);
					handler = new LoggerHandler();
					handler.startServer(portNo, logList, rtclogTableViewer);
					btnStart.setText("Stop");
				} else {
					if(handler!=null) {
						try {
							handler.stopServer();
						} catch (Exception ex) {
						}
					}
					btnStart.setText("Start");
				}
			}
		});

		btnRaw = new Button(composite, SWT.CHECK);
		btnRaw.setText("Raw Data");
		btnRaw.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				provider.setRaw(btnRaw.getSelection());
				filter.setRaw(btnRaw.getSelection());
				rtclogTableViewer.refresh();
			}
		});
		
		txtSearch = new Text(composite, SWT.BORDER);
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace  = true;
		txtSearch.setLayoutData(gd);
		
		Button btnSearch = new Button(composite, SWT.NONE);
		gd = new GridData();
		gd.widthHint = 50;
		btnSearch.setLayoutData(gd);
		btnSearch.setText("Search");
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String strSearch = txtSearch.getText();
				if(strSearch.trim().length()==0) {
					filter.words = new ArrayList<String>();
				} else {
					String[] words = strSearch.split(" ");
					filter.words = Arrays.asList(words);
				}
				rtclogTableViewer.refresh();
			}
		});
	}

	Composite createRTCLogPart(Composite parent) {
		GridLayout gl;
		GridData gd;

		Composite composite = new Composite(parent, SWT.FILL);
		gd = new GridData();
		gd.verticalAlignment = SWT.FILL;
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 5;
		composite.setLayoutData(gd);
		
		gl = new GridLayout();
		gl.numColumns = 1;
		gl.marginWidth = 0;
		gl.marginHeight = 0;
		composite.setLayout(gl);

		rtclogTableViewer = new TableViewer(composite, SWT.FULL_SELECTION
				| SWT.SINGLE | SWT.BORDER);
		rtclogTableViewer.setContentProvider(new ArrayContentProvider());
		rtclogTableViewer.setInput(logList);

		filter = new LogViewerFilter();
		rtclogTableViewer.setFilters(new ViewerFilter[] { filter });

		rtclogTable = rtclogTableViewer.getTable();
		rtclogTable.setLinesVisible(true);

		gd = new GridData();
		gd.verticalAlignment = SWT.FILL;
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.grabExcessHorizontalSpace = true;
		rtclogTable.setLayoutData(gd);
		rtclogTable.setHeaderVisible(true);

		createColumn(rtclogTableViewer, "message", 700);
		
		provider = new LogLabelProvider();
		rtclogTableViewer.setLabelProvider(provider);

		return composite;
	}

	TableViewerColumn createColumn(TableViewer viewer, String title, int width) {
		TableViewerColumn col;
		col = new TableViewerColumn(viewer, SWT.NONE);
		col.getColumn().setText(title);
		col.getColumn().setWidth(width);
		return col;
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void setFocus() {
	}

	/** ログ一覧表示のViewerFilter */
	public class LogViewerFilter extends ViewerFilter {
		List<String> words = new ArrayList<String>();
		private boolean isRaw;
		
		public void setRaw(boolean isRaw) {
			this.isRaw = isRaw;
		}

		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if(words.size()==0) return true;
			
			LogParam entry = (LogParam) element;
			String target = entry.getMessage();
			if(isRaw) target = entry.getRaw_message();
			for(String word : words) {
				if(target.contains(word)==false) return false;
			}
			return true;
		}
	}

	/** ログ一覧表示のLabelProvider */
	public class LogLabelProvider extends LabelProvider implements
			ITableLabelProvider, ITableColorProvider {
		private boolean isRaw;
		
		public void setRaw(boolean isRaw) {
			this.isRaw = isRaw;
		}

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			LogParam entry = (LogParam) element;
			if(isRaw) {
				return entry.getRaw_message();
			} else {
				return entry.getMessage();
			}
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
