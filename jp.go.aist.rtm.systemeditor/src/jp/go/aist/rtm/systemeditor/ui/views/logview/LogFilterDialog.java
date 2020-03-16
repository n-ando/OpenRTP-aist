package jp.go.aist.rtm.systemeditor.ui.views.logview;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.views.logview.FilteringParam.FilteringKind;

public class LogFilterDialog extends Dialog {
	private FilteringParam rootParam = null;

	public FilteringParam getRootParam() {
		return rootParam;
	}
	public void setRootParam(FilteringParam rootParam) {
		this.rootParam = rootParam;
	}

	public LogFilterDialog(Shell parentShell) {
		super(parentShell);
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		GridLayout gridLayout = new GridLayout(1, false);

		Composite mainComposite = (Composite) super.createDialogArea(parent);
		mainComposite.setLayout(gridLayout);
		mainComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Tree conditionTree = new Tree(mainComposite, SWT.BORDER);
	    GridData gd = new GridData();
		gd.verticalAlignment = SWT.FILL;
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.grabExcessHorizontalSpace = true;
		conditionTree.setLayoutData(gd);
		
		TreeItem root = new TreeItem(conditionTree, SWT.NULL);
		if(rootParam==null) {
			rootParam = new FilteringParam(FilteringKind.ROOT, null);
		    root.setData(rootParam);
		    root.setText(rootParam.toString());
		    
			TreeItem andElem = new TreeItem(root, SWT.NULL);
			FilteringParam andParam = new FilteringParam(FilteringKind.AND, rootParam);
		    andElem.setData(andParam);
		    andElem.setText(andParam.toString());
		    rootParam.getChildParams().add(andParam);
		} else {
		    root.setData(rootParam);
		    root.setText(rootParam.toString());
		    buildTree(rootParam, root);
		}
	    
	    root.setExpanded(true);
		buildMenu(conditionTree);		
		
		return mainComposite;
	}
	
	private void buildTree(FilteringParam targetParam, TreeItem targetItem) {
		List<FilteringParam> elems = targetParam.getChildParams();
		for(FilteringParam eachElem : elems) {
			TreeItem treeElem = new TreeItem(targetItem, SWT.NULL);
		    treeElem.setData(eachElem);
		    treeElem.setText(eachElem.toString());
		    targetItem.setExpanded(true);
		    
			if(0<eachElem.getChildParams().size()) {
				buildTree(eachElem, treeElem);
			}
		}
	}

	private void buildMenu(Tree conditionTree) {
		final Menu menu = new Menu(conditionTree);
		conditionTree.setMenu(menu);
	    menu.addMenuListener(new MenuAdapter() {
	        public void menuShown(MenuEvent e) {
	            for (MenuItem item :  menu.getItems()) {
	                item.dispose();
	            }
	            //
	            TreeItem targetItem = conditionTree.getSelection()[0];
	            FilteringParam targetParam = (FilteringParam)targetItem.getData();
	            if(targetParam.getKind()!=FilteringKind.ROOT) {
	            	buildDeleteMenu(menu, targetItem, targetParam);
	            }
	            //
	            if(targetParam.getKind()!=FilteringKind.ROOT && targetParam.getKind()!=FilteringKind.AND && targetParam.getKind()!=FilteringKind.OR) {
		            buildEditMenu(menu, targetItem, targetParam);
	            }
	            if(targetParam.getKind()==FilteringKind.ROOT) {
	            	if(targetParam.getChildParams().size()==0) {
			            buildAndMenu(menu, targetItem, targetParam);
			            buildOrMenu(menu, targetItem, targetParam);
	            	}
	            }
	            if(targetParam.getKind()==FilteringKind.AND || targetParam.getKind()==FilteringKind.OR) {
	        		new MenuItem(menu, SWT.SEPARATOR);
		            buildAndMenu(menu, targetItem, targetParam);
		            buildOrMenu(menu, targetItem, targetParam);
	        		new MenuItem(menu, SWT.SEPARATOR);
	        		
		            boolean existLevel = false;
		            for(FilteringParam child : targetParam.getChildParams()) {
		            	if(child.getKind()==FilteringKind.LEVEL) {
		            		existLevel = true;
		            		break;
		            	}
		            }
		            if(existLevel==false) {
		            	buildLevelMenu(menu, targetItem, targetParam);
		            }
	            	buildTimeMenu(menu, targetItem, targetParam);
	            	buildStringMenu(menu, targetItem, targetParam, FilteringKind.MANAGER);
	            	buildStringMenu(menu, targetItem, targetParam, FilteringKind.IDENTIFIER);
	            	buildStringMenu(menu, targetItem, targetParam, FilteringKind.MESSAGE);
	            }
	        }
	    });
	}

	private void buildAndMenu(final Menu menu, TreeItem targetItem, FilteringParam targetParam) {
		MenuItem andItem = new MenuItem(menu, SWT.PUSH);
        andItem.setText(Messages.getString("LogView.menuAnd"));
        andItem.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem andElem = new TreeItem(targetItem, SWT.NULL);
			    FilteringParam andParam = new FilteringParam(FilteringKind.AND, targetParam);
			    andElem.setData(andParam);
			    andElem.setText(andParam.toString());
			    targetParam.getChildParams().add(andParam);
			    targetItem.setExpanded(true);
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}

	private void buildOrMenu(final Menu menu, TreeItem targetItem, FilteringParam targetParam) {
		MenuItem item = new MenuItem(menu, SWT.PUSH);
		item.setText(Messages.getString("LogView.menuOr"));
		item.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem elem = new TreeItem(targetItem, SWT.NULL);
			    FilteringParam param = new FilteringParam(FilteringKind.OR, targetParam);
			    elem.setData(param);
			    elem.setText(param.toString());
			    targetParam.getChildParams().add(param);
			    targetItem.setExpanded(true);
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}
	
	private void buildDeleteMenu(final Menu menu, TreeItem targetItem, FilteringParam targetParam) {
		MenuItem deleteItem = new MenuItem(menu, SWT.PUSH);
		deleteItem.setText(Messages.getString("LogView.menuDelete"));
		deleteItem.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				targetItem.dispose();
				FilteringParam parentParam = targetParam.getParentParam();
				parentParam.getChildParams().remove(targetParam);
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}
	
	private void buildEditMenu(final Menu menu, TreeItem targetItem, FilteringParam targetParam) {
		MenuItem menuItem = new MenuItem(menu, SWT.PUSH);
		menuItem.setText(Messages.getString("LogView.menuEdit"));
		menuItem.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(targetParam.kind==FilteringKind.LEVEL) {
					LogLevelDialog dialog = new LogLevelDialog(getShell());
					dialog.setTargetParam(targetParam);
					int open = dialog.open();
					if (open != IDialogConstants.OK_ID) {
						return;
					}
					targetItem.setText(targetParam.toString());
				    targetItem.setExpanded(true);
				    
				} else if(targetParam.kind==FilteringKind.TIME) {
					TimeRangeDialog dialog = new TimeRangeDialog(getShell());
					dialog.setTargetParam(targetParam);
					int open = dialog.open();
					if (open != IDialogConstants.OK_ID) {
						return;
					}
					targetItem.setText(targetParam.toString());
				    targetItem.setExpanded(true);
				    
				} else {
				    StringDialog dialog = new StringDialog(getShell());
					dialog.setTargetParam(targetParam);
					dialog.setKind(targetParam.kind);
					int open = dialog.open();
					if (open != IDialogConstants.OK_ID) {
						return;
					}
					targetItem.setText(targetParam.toString());
				    targetItem.setExpanded(true);
					
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}
	
	private void buildLevelMenu(final Menu menu, TreeItem targetItem, FilteringParam targetParam) {
		MenuItem item = new MenuItem(menu, SWT.PUSH);
		item.setText(Messages.getString("LogView.menuLevel"));
		item.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			    FilteringParam param = new FilteringParam(FilteringKind.LEVEL, targetParam);
				LogLevelDialog dialog = new LogLevelDialog(getShell());
				dialog.setTargetParam(param);
				int open = dialog.open();
				if (open != IDialogConstants.OK_ID) {
					return;
				}
				TreeItem elem = new TreeItem(targetItem, SWT.NULL);
			    elem.setData(param);
			    elem.setText(param.toString());
			    targetParam.getChildParams().add(param);
			    targetItem.setExpanded(true);
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}
	
	private void buildTimeMenu(final Menu menu, TreeItem targetItem, FilteringParam targetParam) {
		MenuItem item = new MenuItem(menu, SWT.PUSH);
		item.setText(Messages.getString("LogView.menuTime"));
		item.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			    FilteringParam param = new FilteringParam(FilteringKind.TIME, targetParam);
				TimeRangeDialog dialog = new TimeRangeDialog(getShell());
				dialog.setTargetParam(param);
				int open = dialog.open();
				if (open != IDialogConstants.OK_ID) {
					return;
				}
				TreeItem elem = new TreeItem(targetItem, SWT.NULL);
			    elem.setData(param);
			    elem.setText(param.toString());
			    targetParam.getChildParams().add(param);
			    targetItem.setExpanded(true);
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}
	
	private void buildStringMenu(final Menu menu, TreeItem targetItem, FilteringParam targetParam, FilteringKind type) {
		MenuItem item = new MenuItem(menu, SWT.PUSH);
		switch(type) {
		case MANAGER:
			item.setText(Messages.getString("LogView.menuManager"));
			break;
		case IDENTIFIER:
			item.setText(Messages.getString("LogView.menuIdentifier"));
			break;
		case MESSAGE:
			item.setText(Messages.getString("LogView.menuMessage"));
			break;
		default:
			return;
		}
		item.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			    FilteringParam param = new FilteringParam(type, targetParam);
			    StringDialog dialog = new StringDialog(getShell());
				dialog.setTargetParam(param);
				dialog.setKind(type);
				int open = dialog.open();
				if (open != IDialogConstants.OK_ID) {
					return;
				}
				TreeItem elem = new TreeItem(targetItem, SWT.NULL);
			    elem.setData(param);
			    elem.setText(param.toString());
			    targetParam.getChildParams().add(param);
			    targetItem.setExpanded(true);
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}
	
	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Log Filtering Condition"); //$NON-NLS-1$
	}
	
	protected Point getInitialSize() {
		return new Point(500, 500);
	}
	
	@Override
	protected void okPressed() {
		super.okPressed();
	}
}
