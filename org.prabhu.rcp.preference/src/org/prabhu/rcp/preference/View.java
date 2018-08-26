package org.prabhu.rcp.preference;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;


public class View extends ViewPart {

	private Label label;

	public void createPartControl(Composite parent) {
		IPreferenceStore preferenceStore = Activator.getDefault()
				.getPreferenceStore();
		String string = preferenceStore.getString("CustomString");

		label = new Label(parent, SWT.NONE);
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false,
				false));
		label.setText(string);
		// Add change listener to the preference store so that we are notified
		// in case of changes
		Activator.getDefault().getPreferenceStore()
				.addPropertyChangeListener(new IPropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent event) {
						if (event.getProperty() == "CustomString") {
							label.setText(event.getNewValue().toString());
						}
					}
				});
	}

	public void setFocus() {
	}
}