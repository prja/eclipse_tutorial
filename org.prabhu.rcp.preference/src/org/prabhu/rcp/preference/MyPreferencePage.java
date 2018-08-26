package org.prabhu.rcp.preference;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;



public class MyPreferencePage extends FieldEditorPreferencePage implements
IWorkbenchPreferencePage {

	private StringFieldEditor stringField1;

	public MyPreferencePage() {
		super(GRID);
	}

	public void createFieldEditors() {
		
		stringField1 = new StringFieldEditor("CustomString",
				"A &text preference:", getFieldEditorParent());
		addField(stringField1);
		
	}



	// checkState allow you to perform validations
	@Override
	protected void checkState() {
		super.checkState();
		if (stringField1.getStringValue() != null
				&& stringField1.getStringValue().length() > 0) {
			setErrorMessage(null);
			setValid(true);
		} else {
			setErrorMessage("First text field must be maintained");
			setValid(false);
		}
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription(" Dummy Preference Page");
	}
}
