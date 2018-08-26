package org.prabhu.rcp.preference;


import org.eclipse.equinox.security.storage.ISecurePreferences;
import org.eclipse.equinox.security.storage.SecurePreferencesFactory;
import org.eclipse.equinox.security.storage.StorageException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class SecurePrefView extends ViewPart {
	private Label show;

	public void createPartControl(Composite parent) {
		
		Composite composite = new Composite(parent ,SWT.NO);
		composite.setLayout(new GridLayout(2, false));
		
		Label userid= new Label(composite, SWT.NONE);
		userid.setText("User ID");
		Text txID= new Text(composite, SWT.NONE);
		
		
		Label pass= new Label(composite, SWT.NONE);
		pass.setText("User Password");
		Text txPass= new Text(composite, SWT.PASSWORD);
		
		
		Button buttonPut = new Button(composite, SWT.PUSH);
		buttonPut.setText("Save values");
		buttonPut.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ISecurePreferences preferences = SecurePreferencesFactory
						.getDefault();
				ISecurePreferences node = preferences.node("login");
				try {
					
					node.put("userId", txID.getText(), true);
					node.put("password", txPass.getText(), true);
					
				} catch (StorageException e1) {
					e1.printStackTrace();
				}
				txID.setText("");
				txPass.setText("");
			}
		});
		
		
		Button buttonGet = new Button(composite, SWT.PUSH);
		buttonGet.setText("Get values");
		buttonGet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ISecurePreferences preferences = SecurePreferencesFactory
						.getDefault();
				if (preferences.nodeExists("login")) {
					ISecurePreferences node = preferences.node("login");
					try {
						String user = node.get("userId", "n/a");
						String password = node.get("password", "n/a");
						System.out.println(user);
						System.out.println(password);
						String sr= user +":"+password;
						System.out.println(sr);
						show.setText(sr);
					} catch (StorageException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		
		 show= new Label(composite, SWT.NONE);
		 show.setText("Nothing to display");
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
	}
}