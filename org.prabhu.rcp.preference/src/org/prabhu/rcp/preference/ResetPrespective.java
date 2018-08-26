package org.prabhu.rcp.preference;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PlatformUI;

public class ResetPrespective extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		
		
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().resetPerspective();
		return null;
	}

}
