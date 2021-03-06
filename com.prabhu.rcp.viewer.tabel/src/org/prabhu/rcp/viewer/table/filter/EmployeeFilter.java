package org.prabhu.rcp.viewer.table.filter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.prabhu.rcp.viewer.table.model.Employee;

public class EmployeeFilter extends ViewerFilter {

	private String searchString;

	public void setSearchText(String s) {
		// Search must be a substring of the existing value
		this.searchString = ".*" + s + ".*";
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (searchString == null || searchString.length() == 0) {
			return true;
		}
		Employee p = (Employee) element;
		if (p.getFirstName().matches(searchString)) {
			return true;
		}
		if (p.getLastName().matches(searchString)) {
			return true;
		}

		return false;
	}
}
