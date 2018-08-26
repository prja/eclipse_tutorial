package org.prabhu.rcp.dnd.model;

import java.util.ArrayList;
import java.util.List;

public enum PermEmpModeltProvider {
	INSTANCE;
	
	public List<String> getModel(){
		List<String> list = new ArrayList<String>();
		list.add("Prabhu");
		list.add("Hari");
		list.add("Reshu");
		list.add("John");
		return list;
	}
}
