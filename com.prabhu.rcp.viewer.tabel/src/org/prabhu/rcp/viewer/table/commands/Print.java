package org.prabhu.rcp.viewer.table.commands;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.prabhu.rcp.viewer.table.model.ModelProvider;
import org.prabhu.rcp.viewer.table.model.Employee;

public class Print extends AbstractHandler {
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		List<Employee> empList = ModelProvider.INSTANCE.getPersons();
		for (Employee p : empList) {
			System.out.println(p);
		}
		return null;
	}
}
