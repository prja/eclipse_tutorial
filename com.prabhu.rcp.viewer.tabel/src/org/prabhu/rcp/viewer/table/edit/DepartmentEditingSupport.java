package org.prabhu.rcp.viewer.table.edit;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.prabhu.rcp.viewer.table.model.Employee;

public class DepartmentEditingSupport extends EditingSupport {

	private final TableViewer viewer;

	public DepartmentEditingSupport(TableViewer viewer) {
		super(viewer);
		this.viewer = viewer;
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		String[] gender = new String[2];
		gender[0] = "IT";
		gender[1] = "NonIT";

		return new ComboBoxCellEditor(viewer.getTable(), gender);
	}

	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	@Override
	protected Object getValue(Object element) {
		Employee emp = (Employee) element;
		if (emp.getDepartment().equals("IT")) {
			return 0;
		}
		return 1;

	}

	@Override
	protected void setValue(Object element, Object value) {
		Employee pers = (Employee) element;
		if (((Integer) value) == 0) {
			pers.setDepartment("IT");
		} else {
			pers.setDepartment("NonIT");
		}
		viewer.update(element, null);
	}
}
