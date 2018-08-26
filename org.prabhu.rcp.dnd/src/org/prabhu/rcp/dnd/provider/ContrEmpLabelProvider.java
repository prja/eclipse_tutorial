package org.prabhu.rcp.dnd.provider;

import org.eclipse.jface.viewers.LabelProvider;


public class ContrEmpLabelProvider extends LabelProvider {
	@Override
	public String getText(Object element) {
		String s = (String) element; 
		return s;
	}

}
