package org.prabhu.rcp.viewer.table.sorter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.prabhu.rcp.viewer.table.model.Employee;

public class MyViewerComparator extends ViewerComparator {
	private int propertyIndex;
	private static final int DESCENDING = 1;
	private int direction = DESCENDING;

	public MyViewerComparator() {
		this.propertyIndex = 0;
		direction = DESCENDING;
	}

	public int getDirection() {
		return direction == 1 ? SWT.DOWN : SWT.UP;
	}

	public void setColumn(int column) {
		if (column == this.propertyIndex) {
			// Same column as last sort; toggle the direction
			direction = 1 - direction;
		} else {
			// New column; do an ascending sort
			this.propertyIndex = column;
			direction = DESCENDING;
		}
	}

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		Employee p1 = (Employee) e1;
		Employee p2 = (Employee) e2;
		int rc = 0;
		switch (propertyIndex) {
		case 0:
			rc = p1.getFirstName().compareTo(p2.getFirstName());
			break;
		case 1:
			rc = p1.getLastName().compareTo(p2.getLastName());
			break;
		case 2:
			rc = p1.getDepartment().compareTo(p2.getDepartment());
			break;
		case 3:
			if (p1.isPermanent() == p2.isPermanent()) {
				rc = 0;
			} else
				rc = (p1.isPermanent() ? 1 : -1);
			break;
		default:
			rc = 0;
		}
		// If descending order, flip the direction
		if (direction == DESCENDING) {
			rc = -rc;
		}
		return rc;
	}

}
