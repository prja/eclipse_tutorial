package org.prabhu.rcp.viewer.table.commands;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.prabhu.rcp.viewer.table.model.Employee;
import org.prabhu.rcp.viewer.table.model.ModelProvider;

import com.prabhu.rcp.viewer.tabel.View;

public class DeleteEmployee extends AbstractHandler {
	@SuppressWarnings("unchecked")
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage page = window.getActivePage();
		View view = (View) page.findView(View.ID);
		ISelection selection = view.getSite().getSelectionProvider()
				.getSelection();

		if (selection != null && selection instanceof IStructuredSelection) {
			List<Employee> emp = ModelProvider.INSTANCE.getPersons();
			IStructuredSelection sel = (IStructuredSelection) selection;

			for (Iterator<Employee> iterator = sel.iterator(); iterator.hasNext();) {
				Employee person = iterator.next();
				emp.remove(person);
			}
			view.refresh();
		}
		return null;
	}
}
