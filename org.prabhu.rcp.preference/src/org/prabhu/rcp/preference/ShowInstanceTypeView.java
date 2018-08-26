package org.prabhu.rcp.preference;


import org.eclipse.core.runtime.preferences.InstanceScope;
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
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

public class ShowInstanceTypeView extends ViewPart {
	private Label show;

	public void createPartControl(Composite parent) {
		
		Composite composite = new Composite(parent ,SWT.NO);
		composite.setLayout(new GridLayout(2, false));
		
		Label userid= new Label(composite, SWT.NONE);
		userid.setText("Company");
		Text txID= new Text(composite, SWT.NONE);
		
		
		Label pass= new Label(composite, SWT.NONE);
		pass.setText("Location");
		Text txPass= new Text(composite, SWT.None);
		
		
		Button buttonPut = new Button(composite, SWT.PUSH);
		buttonPut.setText("Save values in node");
		buttonPut.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Preferences preferences = InstanceScope.INSTANCE
					    .getNode("org.prabhu.rcp.preference.page");

					Preferences sub1 = preferences.node("node1");
					Preferences sub2 = preferences.node("node2");
					sub1.put("company", txID.getText());
					sub1.put("location", txPass.getText());
					try {
					    preferences.flush();
					    } catch (BackingStoreException ex) {
					        ex.printStackTrace();
					    }
				txID.setText("");
				txPass.setText("");
			}
		});
		
		
		Button buttonGet = new Button(composite, SWT.PUSH);
		buttonGet.setText("Get node values");
		buttonGet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Preferences preferences = InstanceScope.INSTANCE
					    .getNode("org.prabhu.rcp.preference.page");
					Preferences sub1 = preferences.node("node1");
					Preferences sub2 = preferences.node("node2");
					System.out.println(sub1.get("company", "default"));
					System.out.println(sub1.get("location", "default"));
					show.setText(sub1.get("company", "default")+sub1.get("location", "default"));
				
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