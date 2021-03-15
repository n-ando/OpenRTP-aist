package jp.go.aist.rtm.systemeditor.ui.views.actionorderview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import jp.go.aist.rtm.systemeditor.ui.util.ComponentComparator;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;

public class ActionOrderView extends ViewPart {
	private static final int BUTTON_WIDTH = 40;

	private TableViewer orderTableViewer;
	private List<TableViewer> actionOrderlistTableViewer;

	private SystemDiagram targetDiagram;
	private ActionOrder actionOrder = new ActionOrder();
	private int selectedTable = -1;

	public enum ActionName {
		ACTION_START_UP,
		ACTION_SHUT_DOWN,
		ACTION_ACTIVATION,
		ACTION_DEACTIVATION,
		ACTION_RESETTING,
		ACTION_INITIALIZE,
		ACTION_FINALIZE
	}

	public ActionOrderView() {
	}

	public ActionOrderView getActionOrderView() {
		return this;
	}

	@Override
	public void createPartControl(Composite parent) {
		GridLayout gl;
		GridData gd;

		gl = new GridLayout();
		gl.numColumns = 3;
		parent.setLayout(gl);

		SashForm sashForm = new SashForm(parent, SWT.HORIZONTAL);
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.verticalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.grabExcessVerticalSpace = true;
		gd.horizontalSpan = 2;
		sashForm.setLayoutData(gd);

		createActionOrderListPart(sashForm);

		setSiteSelection();
	}

	Composite createActionOrderListPart(SashForm sash) {
		GridLayout gl;
		GridData gd;

		Composite composite = new Composite(sash, SWT.FILL);
		gl = new GridLayout();
		gl.horizontalSpacing = 0;
		gl.marginWidth = 0;
		gl.marginHeight = 0;
		gl.numColumns = 9;
		composite.setLayout(gl);
		/////
		orderTableViewer = new TableViewer(composite, SWT.FULL_SELECTION | SWT.HIDE_SELECTION
				| SWT.SINGLE | SWT.BORDER);
		orderTableViewer.setContentProvider(new ArrayContentProvider());

		Table orderTable = orderTableViewer.getTable();
		orderTable.setLinesVisible(true);
		orderTable.setHeaderVisible(true);
		gd = new GridData();
		gd.verticalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		orderTable.setLayoutData(gd);
		orderTable.getHorizontalBar().setVisible(false);

		gl = new GridLayout(1, false);
		gl.numColumns = 1;
		orderTable.setLayout(gl);

		TableViewerColumn noColumn = createColumn(orderTableViewer, "No.", 40);
		noColumn.getColumn().setResizable(false);

		orderTableViewer.setLabelProvider(new OrderLabelProvider());
		orderTableViewer.getTable().addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				selectedTable = -1;
				super.focusGained(e);
			}
		});
		//
		actionOrderlistTableViewer = new ArrayList<TableViewer>();
		for(ActionName target : ActionName.values()) {
			TableViewer tableViewer = createActionTable(composite, target);
			actionOrderlistTableViewer.add(tableViewer);
		}
		actionOrder.update();
	    //
		Composite buttonCompsite = new Composite(composite, SWT.BOTTOM);
		gl = new GridLayout();
		gl.numColumns = 1;
		buttonCompsite.setLayout(gl);
		gd = new GridData();
		gd.verticalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		buttonCompsite.setLayoutData(gd);

		Label dummy01 = new Label(buttonCompsite, SWT.NONE);
		gd = new GridData();
		gd.grabExcessVerticalSpace = true;
		dummy01.setLayoutData(gd);

		Button upButton = new Button(buttonCompsite, SWT.NONE);
		upButton.setText("▲");
		upButton.setEnabled(true);
		gd = new GridData();
		gd.widthHint = BUTTON_WIDTH;
		gd.grabExcessHorizontalSpace = true;
		gd.verticalAlignment = SWT.BEGINNING;
		upButton.setLayoutData(gd);
		upButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(selectedTable<0) return;
				int rowIndex = actionOrderlistTableViewer.get(selectedTable).getTable().getSelectionIndex();
				actionOrder.upElement(selectedTable, rowIndex);
			}
		});

		Label dummy02 = new Label(buttonCompsite, SWT.NONE);
		gd = new GridData();
		gd.grabExcessVerticalSpace = true;
		dummy02.setLayoutData(gd);

		Button downButton = new Button(buttonCompsite, SWT.NONE);
		downButton.setText("▼");
		downButton.setEnabled(true);
		gd = new GridData();
		gd.widthHint = BUTTON_WIDTH;
		gd.grabExcessHorizontalSpace = true;
		gd.verticalAlignment = SWT.END;
		downButton.setLayoutData(gd);
		downButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(selectedTable<0) return;
				int rowIndex = actionOrderlistTableViewer.get(selectedTable).getTable().getSelectionIndex();
				actionOrder.downElement(selectedTable, rowIndex);
			}
		});

		Label dummy03 = new Label(buttonCompsite, SWT.NONE);
		gd = new GridData();
		gd.grabExcessVerticalSpace = true;
		dummy03.setLayoutData(gd);

		return composite;
	}

	private TableViewer createActionTable(Composite composite, final ActionName target) {
		GridLayout gl;
		GridData gd;
		TableViewer actionTableViewer = new TableViewer(composite, SWT.FULL_SELECTION
				| SWT.SINGLE | SWT.BORDER);
		actionTableViewer.setContentProvider(new ArrayContentProvider());

		final Table actionTable = actionTableViewer.getTable();
		actionTable.setLinesVisible(true);
		actionTable.setHeaderVisible(true);
		gd = new GridData();
		gd.verticalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		actionTable.setLayoutData(gd);

		gl = new GridLayout(1, false);
		gl.numColumns = 1;
		actionTable.setLayout(gl);

		switch(target) {
		case ACTION_START_UP:
			createColumn(actionTableViewer, "Startup", 120);
			break;
		case ACTION_SHUT_DOWN:
			createColumn(actionTableViewer, "Shutdown", 120);
			break;
		case ACTION_ACTIVATION:
			createColumn(actionTableViewer, "Activation", 120);
			break;
		case ACTION_DEACTIVATION:
			createColumn(actionTableViewer, "Deactivation", 120);
			break;
		case ACTION_RESETTING:
			createColumn(actionTableViewer, "Resetting", 120);
			break;
		case ACTION_INITIALIZE:
			createColumn(actionTableViewer, "Initialize", 120);
			break;
		case ACTION_FINALIZE:
			createColumn(actionTableViewer, "Finalize", 120);
			break;
		}

		actionTableViewer.setLabelProvider(new ActionOrderLabelProvider());

		actionTableViewer.getTable().addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				selectedTable = target.ordinal();
				super.focusGained(e);
			}
		});
		/////
		Transfer[] types = new Transfer[] { TextTransfer.getInstance() };
	    DragSource source = new DragSource(actionTable, DND.DROP_MOVE);
	    source.setTransfer(types);

	    source.addDragListener(new DragSourceAdapter() {
	      public void dragSetData(DragSourceEvent event) {
			int rowIndex = actionTable.getSelectionIndex();
			event.data = actionOrder.getElement(selectedTable, rowIndex);
	      }
	    });

	    DropTarget dragTarget = new DropTarget(actionTable, DND.DROP_MOVE);
	    dragTarget.setTransfer(types);
	    dragTarget.addDropListener(new DropTargetAdapter() {
	      public void dragEnter(DropTargetEvent event) {
	          if (TextTransfer.getInstance().isSupportedType(event.dataTypes[0])) {
	        	  if(selectedTable!=target.ordinal()) {
	    	    	  event.detail = DND.DROP_NONE;
	        	  } else {
	        		  event.currentDataType = event.dataTypes[0];
	        	  }
	          }
	      }

	      public void dragOver(DropTargetEvent event) {
	         event.feedback = DND.FEEDBACK_SCROLL | DND.FEEDBACK_INSERT_BEFORE;
	      }
	      public void drop(DropTargetEvent event) {
	        if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
	          // Get the dropped data
	          String data = (String) event.data;
	          Widget targetItem = event.item;
	          if(targetItem==null) {
	        	  actionOrder.moveBackElement(selectedTable, data);
	          } else {
	        	  String targetData = ((TableItem)targetItem).getText();
	        	  actionOrder.swapElement(selectedTable, data, targetData);
	          }
	        }
	      }
	    });
		return actionTableViewer;
	}

	TableViewerColumn createColumn(TableViewer viewer, String title, int width) {
		TableViewerColumn col;
		col = new TableViewerColumn(viewer, SWT.NONE);
		col.getColumn().setText(title);
		col.getColumn().setWidth(width);
		col.getColumn().setResizable(false);
		return col;
	}

	/** 内部モデル(RTC一覧)から表示 */
	void refreshData() {
		if(targetDiagram==null) return;
		List<Component> targetComps = new ArrayList<Component>();
		for (Component comp : targetDiagram.getRegisteredComponents()) {
			if (!(comp instanceof CorbaComponent)) {
				continue;
			}
			targetComps.add(comp);
		}
		actionOrder.updateElement(targetComps);
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void setFocus() {
	}

	private class ActionOrder {
		private List<Component> startupOrder = new ArrayList<Component>();
		private List<Component> shutdownOrder = new ArrayList<Component>();
		private List<Component> activationOrder = new ArrayList<Component>();
		private List<Component> deactivationOrder = new ArrayList<Component>();
		private List<Component> resettingOrder = new ArrayList<Component>();
		private List<Component> initializeOrder = new ArrayList<Component>();
		private List<Component> finalizeOrder = new ArrayList<Component>();

		public void updateElement(List<Component> targetComps) {
			List<Component> removeComps = new ArrayList<Component>();
			for(Component target : startupOrder) {
				boolean isHit = false;
				for(Component source : targetComps) {
					if(target.getPathId().equals(source.getPathId()) && target.getInstanceNameL().equals(source.getInstanceNameL())) {
						isHit = true;
						break;
					}
				}
				if(isHit==false) {
					removeComps.add(target);
				}
			}
			if(0<removeComps.size()) {
				for(Component target : removeComps) {
					startupOrder.remove(target);
					shutdownOrder.remove(target);
					activationOrder.remove(target);
					deactivationOrder.remove(target);
					resettingOrder.remove(target);
					initializeOrder.remove(target);
					finalizeOrder.remove(target);
				}
			}
			//
			for(Component source : targetComps) {
				boolean isHit = false;
				for(Component target : startupOrder) {
					if(target.getPathId().equals(source.getPathId()) && target.getInstanceNameL().equals(source.getInstanceNameL())) {
						isHit = true;
						break;
					}
				}
				if(isHit==false) {
					startupOrder.add(source);
					shutdownOrder.add(source);
					activationOrder.add(source);
					deactivationOrder.add(source);
					resettingOrder.add(source);
					initializeOrder.add(source);
					finalizeOrder.add(source);
				}
			}
			//
			Collections.sort(startupOrder, new ComponentComparator(ActionName.ACTION_START_UP));
			Collections.sort(shutdownOrder, new ComponentComparator(ActionName.ACTION_SHUT_DOWN));
			Collections.sort(activationOrder, new ComponentComparator(ActionName.ACTION_ACTIVATION));
			Collections.sort(deactivationOrder, new ComponentComparator(ActionName.ACTION_DEACTIVATION));
			Collections.sort(resettingOrder, new ComponentComparator(ActionName.ACTION_RESETTING));
			Collections.sort(initializeOrder, new ComponentComparator(ActionName.ACTION_INITIALIZE));
			Collections.sort(finalizeOrder, new ComponentComparator(ActionName.ACTION_FINALIZE));
			//
			updateOrder();
			orderTableViewer.refresh();
			for(int index=0; index<7; index++) {
				actionOrderlistTableViewer.get(index).refresh();
			}
		}

		public String getElement(int colIndex, int rowIndex) {
			switch(colIndex) {
			case 0:
				return startupOrder.get(rowIndex).getInstanceNameL();
			case 1:
				return shutdownOrder.get(rowIndex).getInstanceNameL();
			case 2:
				return activationOrder.get(rowIndex).getInstanceNameL();
			case 3:
				return deactivationOrder.get(rowIndex).getInstanceNameL();
			case 4:
				return resettingOrder.get(rowIndex).getInstanceNameL();
			case 5:
				return initializeOrder.get(rowIndex).getInstanceNameL();
			default:
				return finalizeOrder.get(rowIndex).getInstanceNameL();
			}
		}

		public void swapElement(int tableIndex, String source, String target) {
			if(tableIndex<0 || source==null || source.length()<=0 || target==null || target.length()<=0) return;
			List<Component> targetData;
			targetData = getTargetData(tableIndex);
			int srcIndex = getIndex(source, targetData);
			int trgIndex = getIndex(target, targetData);
			Collections.swap(targetData, srcIndex, trgIndex);
			//
			updateOrder();
			actionOrderlistTableViewer.get(tableIndex).refresh();
			actionOrderlistTableViewer.get(tableIndex).getTable().setSelection(trgIndex);
		}

		public void moveBackElement(int tableIndex, String source) {
			if(tableIndex<0 || source==null || source.length()<=0) return;
			List<Component> targetData;
			targetData = getTargetData(tableIndex);
			int srcIndex = getIndex(source, targetData);
			Component elem = targetData.remove(srcIndex);
			targetData.add(elem);
			//
			updateOrder();
			actionOrderlistTableViewer.get(tableIndex).refresh();
			actionOrderlistTableViewer.get(tableIndex).getTable().setSelection(targetData.size()-1);
		}

		public void upElement(int tableIndex, int rowIndex) {
			if(tableIndex<0 || rowIndex<=0) return;
			List<Component> targetData;
			targetData = getTargetData(tableIndex);
			Component selected = targetData.remove(rowIndex);
			targetData.add(rowIndex-1, selected);
			//
			updateOrder();
			actionOrderlistTableViewer.get(tableIndex).refresh();
			actionOrderlistTableViewer.get(tableIndex).getTable().setSelection(rowIndex-1);
		}

		public void downElement(int tableIndex, int rowIndex) {
			if(tableIndex<0 || startupOrder.size()-1<=rowIndex) return;
			List<Component> targetData;
			targetData = getTargetData(tableIndex);
			Component selected = targetData.remove(rowIndex);
			targetData.add(rowIndex+1, selected);
			//
			updateOrder();
			actionOrderlistTableViewer.get(tableIndex).refresh();
			actionOrderlistTableViewer.get(tableIndex).getTable().setSelection(rowIndex+1);
		}

		public void update() {
			orderTableViewer.setInput(startupOrder);
			actionOrderlistTableViewer.get(ActionName.ACTION_START_UP.ordinal()).setInput(startupOrder);
			actionOrderlistTableViewer.get(ActionName.ACTION_SHUT_DOWN.ordinal()).setInput(shutdownOrder);
			actionOrderlistTableViewer.get(ActionName.ACTION_ACTIVATION.ordinal()).setInput(activationOrder);
			actionOrderlistTableViewer.get(ActionName.ACTION_DEACTIVATION.ordinal()).setInput(deactivationOrder);
			actionOrderlistTableViewer.get(ActionName.ACTION_RESETTING.ordinal()).setInput(resettingOrder);
			actionOrderlistTableViewer.get(ActionName.ACTION_INITIALIZE.ordinal()).setInput(initializeOrder);
			actionOrderlistTableViewer.get(ActionName.ACTION_FINALIZE.ordinal()).setInput(finalizeOrder);
		}

		private List<Component> getTargetData(int tableIndex) {
			List<Component> targetData;
			switch(tableIndex) {
				case 0: targetData = startupOrder; break;
				case 1: targetData = shutdownOrder; break;
				case 2: targetData = activationOrder; break;
				case 3: targetData = deactivationOrder;	break;
				case 4:targetData = resettingOrder;	break;
				case 5: targetData = initializeOrder; break;
				default: targetData = finalizeOrder; break;
			}
			return targetData;
		}

		private int getIndex(String source, List<Component> targetData) {
			for(int index=0; index<targetData.size(); index++) {
				Component target = targetData.get(index);
				if(source.equals(target.getInstanceNameL())) return index;
			}
			return -1;
		}

		private void updateOrder() {
			for(int index=0; index<startupOrder.size(); index++) {
				Component startup = startupOrder.get(index);
				startup.setStartUp(Integer.valueOf(index+1).toString());
				//
				Component shutdown = shutdownOrder.get(index);
				shutdown.setShutDown(Integer.valueOf(index+1).toString());
				//
				Component activation = activationOrder.get(index);
				activation.setActivation(Integer.valueOf(index+1).toString());
				//
				Component deactivation = deactivationOrder.get(index);
				deactivation.setDeActivation(Integer.valueOf(index+1).toString());
				//
				Component resetting = resettingOrder.get(index);
				resetting.setResetting(Integer.valueOf(index+1).toString());
				//
				Component initialize = initializeOrder.get(index);
				initialize.setInitialize(Integer.valueOf(index+1).toString());
				//
				Component finalize = finalizeOrder.get(index);
				finalize.setFinalize(Integer.valueOf(index+1).toString());
			}
		}
	}

	public class OrderLabelProvider extends LabelProvider implements ITableLabelProvider {
		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			Component entry = (Component) element;
			return Integer.valueOf(actionOrder.startupOrder.indexOf(entry) + 1).toString();
		}
	}
	/** RTC一覧表示のLabelProvider */
	public class ActionOrderLabelProvider extends LabelProvider implements
			ITableLabelProvider {
		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			return ((Component)element).getInstanceNameL();
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
			refreshData();
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
