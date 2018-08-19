package org.prabhu.rcp.viewer.table.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.prabhu.rcp.viewer.table.model.Employee;

import com.prabhu.rcp.viewer.tabel.View;

public class CopyClipboard extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IWorkbenchWindow window = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
		IViewPart view = page.findView(View.ID);
		Clipboard cb = new Clipboard(Display.getDefault());
		ISelection selection = view.getSite().getSelectionProvider()
				.getSelection();
		List<Employee> empList = new ArrayList<Employee>();
		if (selection != null && selection instanceof IStructuredSelection) {
			IStructuredSelection sel = (IStructuredSelection) selection;
			for (Iterator<Employee> iterator = sel.iterator(); iterator.hasNext();) {
				Employee person = iterator.next();
				empList.add(person);
			}
		}
		StringBuilder sb = new StringBuilder();
		for (Employee employee : empList) {
			sb.append(personToString(employee));
		}
		TextTransfer textTransfer = TextTransfer.getInstance();
		cb.setContents(new Object[] { sb.toString() },
				new Transfer[] { textTransfer });

		return null;
	}

	private String personToString(Employee emp) {
		return emp.getFirstName() + "\t" + emp.getLastName() + "\t"
				+ emp.getDepartment() + "\t" + emp.isPermanent()
				+ System.getProperty("line.separator");
	}

}
