package org.prabhu.rcp.viewer.table.edit;

import org.eclipse.jface.viewers.TableViewer;
import org.prabhu.rcp.viewer.table.model.Employee;

public class LastNameEditingSupport extends FirstNameEditingSupport {

	private final TableViewer viewer;

	public LastNameEditingSupport(TableViewer viewer) {
		super(viewer);
		this.viewer = viewer;
	}

	@Override
	protected Object getValue(Object element) {
		return ((Employee) element).getLastName();
	}

	@Override
	protected void setValue(Object element, Object value) {
		((Employee) element).setLastName(String.valueOf(value));
		viewer.update(element, null);
	}
}
