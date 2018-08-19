package com.prabhu.rcp.dialog.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class MySecondPage extends WizardPage {
	private Text text1;
	private Composite container;
	MyPageModel model;
	public MySecondPage(MyPageModel model) {
		super("Second Page");
		setTitle("Second Page");
		setDescription("Now this is the second page");
		setControl(text1);
		this.model=model;
	}

	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		Label label1 = new Label(container, SWT.NONE);
		label1.setText("Enter your secrete code");

		text1 = new Text(container, SWT.BORDER | SWT.SINGLE);
		text1.setText("");
		
		
		
		Label label2 = new Label(container, SWT.NONE);
		label2.setText("Enter your secrete identity");

		Text text2 = new Text(container, SWT.BORDER | SWT.SINGLE);
		text2.setText("");
		
		
		
		text1.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				model.setPage2("Input from Page 2");
				if (!text1.getText().isEmpty()) {
					setPageComplete(true);
				}
			}

		});
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		text1.setLayoutData(gd);
		text2.setLayoutData(gd);
		Label labelCheck = new Label(container, SWT.NONE);
		labelCheck.setText("Check this if youwish to unlock Bank code");
		Button check = new Button(container, SWT.CHECK);
		check.setSelection(true);
		// Required to avoid an error in the system
		setControl(container);
		setPageComplete(false);
	}

	public String getText1() {
		return text1.getText();
	}

}
