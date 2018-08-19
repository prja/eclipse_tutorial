package org.prabhu.rcp.viewer.table.model;

import java.util.ArrayList;
import java.util.List;

public enum ModelProvider {
	INSTANCE;

	private List<Employee> employee;

	private ModelProvider() {
		employee = new ArrayList<Employee>();
		// Image here some fancy database access to read the persons and to
		// put them into the model
		employee.add(new Employee("Prabhu", "Narayan", "IT", true,10000));
		employee.add(new Employee("Rahul", "Kumar", "NonIT", false,20000));
		employee.add(new Employee("Priyanka", "Sharma", "IT", false,30000));
		employee.add(new Employee("Preeti", "Sing", "NonIT", true,40000));
		employee.add(new Employee("Jyoti", "Verma", "IT", true,500000));
	}

	public List<Employee> getPersons() {
		return employee;
	}

}
