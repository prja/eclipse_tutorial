package org.prabhu.rcp.dnd.provider;

import org.eclipse.jface.viewers.BaseLabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class PermEmpLabelProvider extends BaseLabelProvider implements
		ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		String todo = (String) element;
		/*switch (columnIndex) {
		case 0:
			return todo.getEmpName();
		
		}*/
		return todo;
	}

}
