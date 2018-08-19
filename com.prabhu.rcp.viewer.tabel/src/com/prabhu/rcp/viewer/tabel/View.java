package com.prabhu.rcp.viewer.tabel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.prabhu.rcp.viewer.table.edit.FirstNameEditingSupport;
import org.prabhu.rcp.viewer.table.edit.DepartmentEditingSupport;
import org.prabhu.rcp.viewer.table.edit.LastNameEditingSupport;
import org.prabhu.rcp.viewer.table.edit.EmpTypeEditingSupport;
import org.prabhu.rcp.viewer.table.filter.EmployeeFilter;
import org.prabhu.rcp.viewer.table.model.Employee;
import org.prabhu.rcp.viewer.table.model.ModelProvider;
import org.prabhu.rcp.viewer.table.sorter.MyViewerComparator;
import org.prabhu.rcp.viewer.table.util.SearchUtil;

public class View extends ViewPart {
	public static final String ID = "com.prabhu.rcp.viewer.tabel.view";

	private MyViewerComparator comparator;

	private TableViewer viewer;
	private EmployeeFilter filter;
	// Assuming your have these two icons
	// in your icons folder
	private static final Image CHECKED = getImage("checked.gif");
	private static final Image UNCHECKED = getImage("unchecked.gif");
	private Text searchText;
	private static Color colorYellow = Display.getCurrent().getSystemColor(
			SWT.COLOR_YELLOW);
	private Menu headerMenu;

	public void createPartControl(Composite parent) {
		GridLayout layout = new GridLayout(2, false);
		parent.setLayout(layout);
		Label searchLabel = new Label(parent, SWT.NONE);
		searchLabel.setText("Search: ");
		searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_FILL));
		createViewer(parent);
		// Set the sorter for the table
		comparator = new MyViewerComparator();
		viewer.setComparator(comparator);

		// New to support the search
		searchText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				filter.setSearchText(searchText.getText());
				viewer.refresh();
			}
		});
		filter = new EmployeeFilter();
		viewer.addFilter(filter);
		
		// to enable context or popup menu
		MenuManager menuManager = new MenuManager();
        Menu menu = menuManager.createContextMenu(viewer.getTable());
        // set the menu on the SWT widget
        viewer.getTable().setMenu(menu);
        // register the menu with the framework
        getSite().registerContextMenu(menuManager, viewer);
	}

	private void createViewer(Composite parent) {

		// Define the TableViewer
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

		// Create the columns
		createColumns(parent);

		// Make lines and make header visible
		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		// Set the ContentProvider
		viewer.setContentProvider(ArrayContentProvider.getInstance());

		// Get the content for the Viewer,
		// setInput will call getElements in the ContentProvider
		viewer.setInput(ModelProvider.INSTANCE.getPersons());

		// Make the selection available to other Views
		getSite().setSelectionProvider(viewer);

		// Layout the viewer
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		viewer.getControl().setLayoutData(gridData);
	}

	// Used to update the viewer from outsite
	public void refresh() {
		viewer.refresh();
	}

	// This will create the columns for the table
	private void createColumns(final Composite parent) {
		headerMenu = new Menu(viewer.getTable());
		viewer.getTable().setMenu(headerMenu);

		String[] titles = { "First name", "Last name", "Department", "Employement Type","Sallary" };
		int[] bounds = { 100, 100, 100, 100,100 };

		// First column is for the first name
		TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				String search = searchText.getText();
				Employee emp = (Employee) cell.getElement();
				String cellText = emp.getFirstName();
				cell.setText(cellText);
				if (search != null && search.length() > 0) {
					int intRangesCorrectSize[] = SearchUtil
							.getSearchTermOccurrences(search, cellText);
					List<StyleRange> styleRange = new ArrayList<StyleRange>();
					for (int i = 0; i < intRangesCorrectSize.length / 2; i++) {
						int start = intRangesCorrectSize[i];
						int length = intRangesCorrectSize[++i];
						StyleRange myStyledRange = new StyleRange(start,
								length, null, colorYellow);

						styleRange.add(myStyledRange);
					}
					cell.setStyleRanges(styleRange
							.toArray(new StyleRange[styleRange.size()]));
				} else {
					cell.setStyleRanges(null);
				}

				super.update(cell);

			}
		});

		col.setEditingSupport(new FirstNameEditingSupport(viewer));

		// Second column is for the last name
		col = createTableViewerColumn(titles[1], bounds[1], 1);
		col.setLabelProvider(new CellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				cell.setText(((Employee) cell.getElement()).getLastName());
			}

			@Override
			public String getToolTipText(Object element) {
				return "Tooltip (" + element + ")";
			}

			@Override
			public Point getToolTipShift(Object object) {
				return new Point(5, 5);
			}

			@Override
			public int getToolTipDisplayDelayTime(Object object) {
				return 100;
			}

			@Override
			public int getToolTipTimeDisplayed(Object object) {
				return 5000;
			}

		});
		col.setEditingSupport(new LastNameEditingSupport(viewer));

		
		col = createTableViewerColumn(titles[2], bounds[2], 2);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Employee p = (Employee) element;
				return p.getDepartment();
			}
		});
		col.setEditingSupport(new DepartmentEditingSupport(viewer));

		// // Now the status married
		col = createTableViewerColumn(titles[3], bounds[3], 3);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				if (((Employee) element).isPermanent()) {
					return "Permanent";
				}
				return "Contract";
			}

			@Override
			public Image getImage(Object element) {
				if (((Employee) element).isPermanent()) {
					return CHECKED;
				}
				return UNCHECKED;
			}
		});
		col.setEditingSupport(new EmpTypeEditingSupport(viewer));

				col = createTableViewerColumn(titles[4], bounds[4], 4);
				col.setLabelProvider(new ColumnLabelProvider() {
					@Override
					public String getText(Object element) {
						
						return ((Employee) element).getSallary()+"";
					}

					
				});

	}

	

	private TableViewerColumn createTableViewerColumn(String title, int bound,
			final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer,
				SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		column.addSelectionListener(getSelectionAdapter(column, colNumber));
		
		return viewerColumn;
	}

	private SelectionAdapter getSelectionAdapter(final TableColumn column,
			final int index) {
		SelectionAdapter selectionAdapter = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comparator.setColumn(index);
				int dir = comparator.getDirection();
				viewer.getTable().setSortDirection(dir);
				viewer.getTable().setSortColumn(column);
				viewer.refresh();
			}
		};
		return selectionAdapter;
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	private static Image getImage(String file) {
		Bundle bundle = FrameworkUtil.getBundle(View.class);
		URL url = FileLocator.find(bundle, new Path("icons/" + file), null);
		ImageDescriptor image = ImageDescriptor.createFromURL(url);
		return image.createImage();

	}
}
