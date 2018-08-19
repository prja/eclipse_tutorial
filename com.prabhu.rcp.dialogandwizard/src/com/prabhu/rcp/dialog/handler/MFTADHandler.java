package com.prabhu.rcp.dialog.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import com.prabhu.rcp.dialog.types.MyFirstDialog;
import com.prabhu.rcp.dialog.types.MyFirstTitleAreaDialog;

public class MFTADHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.out.println("moin");
		Shell shell = HandlerUtil.getActiveWorkbenchWindow(event).getShell();

		// File standard dialog 
		MyFirstTitleAreaDialog myFirstTitleAreaDialog = new MyFirstTitleAreaDialog(shell);
		myFirstTitleAreaDialog.open();
		return null;
	}

}
