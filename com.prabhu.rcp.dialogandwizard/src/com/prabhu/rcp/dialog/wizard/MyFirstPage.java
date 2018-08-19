package com.prabhu.rcp.dialog.wizard;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class MyFirstPage extends WizardPage {
	private Text text1;
	private Composite container;
	private int number = 0;
	MyPageModel model;
	public MyFirstPage(MyPageModel model) {
		super("First Page");
		setTitle("First Page");
		setDescription("Fake Wizard. First page");
		this.model=model;
	}

	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		Label label1 = new Label(container, SWT.NONE);
		label1.setText("Enter your UserID");

		text1 = new Text(container, SWT.BORDER | SWT.SINGLE);
		text1.setText("");
		text1.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!text1.getText().isEmpty()) {
					setPageComplete(true);

				}
			}

		});
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		text1.setLayoutData(gd);
		
		
		
		Label label2 = new Label(container, SWT.NONE);
		label2.setText("Enter your Password");

		Text text2 = new Text(container, SWT.BORDER | SWT.SINGLE);
		text2.setText("");
		text2.setLayoutData(gd);
		// Required to avoid an error in the system
		setControl(container);
		setPageComplete(false);

	}

	public String getText1() {
		return text1.getText();
	}

	public IWizardPage getNextPage() {
		model.setPgae1("Value from Page1");
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				// ... do any work that updates the screen ...
			}
		});

		return super.getNextPage();
	}
}
