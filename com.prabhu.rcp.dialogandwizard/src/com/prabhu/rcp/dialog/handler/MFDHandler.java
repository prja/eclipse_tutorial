package com.prabhu.rcp.dialog.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import com.prabhu.rcp.dialog.types.MyFirstDialog;

public class MFDHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		
		System.out.println("moin");
		Shell shell = HandlerUtil.getActiveWorkbenchWindow(event).getShell();

		// File standard dialog 
		MyFirstDialog myFirstDialog = new MyFirstDialog(shell);
		myFirstDialog.open();
		return null;
	}

}
