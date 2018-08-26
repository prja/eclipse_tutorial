package org.prabhu.rcp.dnd;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.prabhu.rcp.dnd.listner.MyDragListener;
import org.prabhu.rcp.dnd.model.PermEmpModeltProvider;
import org.prabhu.rcp.dnd.provider.PermEmpContentProvider;
import org.prabhu.rcp.dnd.provider.PermEmpLabelProvider;

public class PermanentEmpView extends ViewPart {


	@Override
	public void createPartControl(Composite parent) {
		TableViewer viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		int operations = DND.DROP_COPY| DND.DROP_MOVE;
		Transfer[] transferTypes = new Transfer[]{TextTransfer.getInstance()};
		viewer.addDragSupport(operations, transferTypes , new MyDragListener(viewer));
		viewer.setContentProvider(new PermEmpContentProvider());
		viewer.setLabelProvider(new PermEmpLabelProvider());
		viewer.setInput(PermEmpModeltProvider.INSTANCE.getModel());
	}

	@Override
	public void setFocus() {

	}

}
