package org.prabhu.rcp.preference;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;


public class RTInitializer extends AbstractPreferenceInitializer {

	public RTInitializer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault("CustomString", "Not everyone can become great artist , but ....");

	}

}
