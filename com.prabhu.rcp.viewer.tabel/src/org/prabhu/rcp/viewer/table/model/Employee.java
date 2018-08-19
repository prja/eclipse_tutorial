package org.prabhu.rcp.viewer.table.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Employee {
	private String firstName;
	private String lastName;
	private boolean permanent;
	private String department;
	private Integer sallary;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	public Employee() {
	}

	public Employee(String firstName, String lastName, String department,
			boolean permanent,int sallary) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.permanent = permanent;
		this.sallary=sallary;
	}

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getDepartment() {
		return department;
	}

	public String getLastName() {
		return lastName;
	}

	public boolean isPermanent() {
		return permanent;
	}

	public void setFirstName(String firstName) {
		propertyChangeSupport.firePropertyChange("firstName", this.firstName,
				this.firstName = firstName);
	}

	public void setDepartment(String gender) {
		propertyChangeSupport.firePropertyChange("department", this.department,
				this.department = gender);
	}

	public void setLastName(String lastName) {
		propertyChangeSupport.firePropertyChange("lastName", this.lastName,
				this.lastName = lastName);
	}

	public void setPermanent(boolean permanent) {
		propertyChangeSupport.firePropertyChange("permanent", this.permanent,
				this.permanent = permanent);
	}

	public Integer getSallary() {
		return sallary;
	}

	public void setSallary(Integer sallary) {
		propertyChangeSupport.firePropertyChange("sallary", this.sallary,
				this.sallary = sallary);
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

}