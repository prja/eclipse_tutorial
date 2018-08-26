package org.prabhu.rcp.dnd;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.prabhu.rcp.dnd.listner.MyDropListener;
import org.prabhu.rcp.dnd.model.ContrEmpModelProviderTree;
import org.prabhu.rcp.dnd.provider.ContrEmpContentProvider;
import org.prabhu.rcp.dnd.provider.ContrEmpLabelProvider;

public class ContractEmpView extends ViewPart {

	@Override
	public void createPartControl(Composite parent) {
	
		
		TableViewer viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		int operations = DND.DROP_COPY| DND.DROP_MOVE;
		Transfer[] transferTypes = new Transfer[]{TextTransfer.getInstance()};
		
		viewer.setContentProvider(new ContrEmpContentProvider());
		viewer.setLabelProvider(new ContrEmpLabelProvider());
		viewer.setInput(ContrEmpModelProviderTree.INSTANCE.getModel());
		viewer.addDropSupport(operations, transferTypes , new MyDropListener(viewer));
		
				
	}
	
	@Override
	public void setFocus() {
	}


}
