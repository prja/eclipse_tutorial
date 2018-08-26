package org.prabhu.rcp.dnd.model;

import java.util.ArrayList;
import java.util.List;

public enum ContrEmpModelProviderTree {
	INSTANCE;
	List<String> list = new ArrayList<String>();
	
	private ContrEmpModelProviderTree() {
		list.add("Suchi");
		list.add("Preeti");
		list.add("Anu");
		list.add("Adi");
	}
	
	public List<String> getModel(){
		return list;
	}
}
