package com.prabhu.rcp.dialog.types;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class MyFirstDialog extends Dialog {

	public MyFirstDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		createFirstName(container);
		createLastName(container);

		return container;
	}

	private void createFirstName(Composite container) {
		Label lbtFirstName = new Label(container, SWT.NONE);
		lbtFirstName.setText("First Name");

		GridData dataFirstName = new GridData();
		dataFirstName.grabExcessHorizontalSpace = true;
		dataFirstName.horizontalAlignment = GridData.FILL;

		Text txtFirstName = new Text(container, SWT.BORDER);
		txtFirstName.setLayoutData(dataFirstName);
	}
	
	private void createLastName(Composite container) {
		Label lbtLastName = new Label(container, SWT.NONE);
		lbtLastName.setText("Last Name");
		
		GridData dataLastName = new GridData();
		dataLastName.grabExcessHorizontalSpace = true;
		dataLastName.horizontalAlignment = GridData.FILL;
		Text lastNameText = new Text(container, SWT.BORDER);
		lastNameText.setLayoutData(dataLastName);
	}

	// overriding this methods allows you to set the
	// title of the custom dialog
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Selection dialog");
	}

	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

}
