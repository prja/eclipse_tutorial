package org.prabhu.rcp.viewer.table.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.prabhu.rcp.viewer.table.dialogs.AddEmployeeDialog;
import org.prabhu.rcp.viewer.table.model.ModelProvider;

import com.prabhu.rcp.viewer.tabel.View;

public class AddEmployee extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		ModelProvider employee = ModelProvider.INSTANCE;
		AddEmployeeDialog dialog = new AddEmployeeDialog(window.getShell());
		dialog.open();
		if (dialog.getEmp() != null) {
			employee.getPersons().add(dialog.getEmp());
			// Updating the display in the view
			IWorkbenchPage page = window.getActivePage();
			View view = (View) page.findView(View.ID);
			view.refresh();
		}
		return null;
	}
}
