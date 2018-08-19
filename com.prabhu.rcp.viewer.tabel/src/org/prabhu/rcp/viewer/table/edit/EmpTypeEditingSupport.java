package org.prabhu.rcp.viewer.table.edit;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.prabhu.rcp.viewer.table.model.Employee;

public class EmpTypeEditingSupport extends EditingSupport {

	private final TableViewer viewer;

	public EmpTypeEditingSupport(TableViewer viewer) {
		super(viewer);
		this.viewer = viewer;
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		return new CheckboxCellEditor(null, SWT.CHECK | SWT.READ_ONLY);

	}

	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	@Override
	protected Object getValue(Object element) {
		Employee emp = (Employee) element;
		return emp.isPermanent();

	}

	@Override
	protected void setValue(Object element, Object value) {
		Employee pers = (Employee) element;
		pers.setPermanent((Boolean) value);
		viewer.update(element, null);
	}
}
