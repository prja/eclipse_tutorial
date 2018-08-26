package org.prabhu.rcp.dnd.provider;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class PermEmpContentProvider implements IStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		List<String> list = (List<String>) inputElement;
		return list.toArray();
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

}
