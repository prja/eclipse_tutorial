package com.prabhu.rcp.databinding.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;



public class Employee implements PropertyChangeListener {
	private String firstName;
	private String lastName;
	private boolean permanent;
	private String department;
	private Integer sallary;
	private Address address;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	public Employee() {
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
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		address.addPropertyChangeListener("country", this);
		propertyChangeSupport.firePropertyChange("address", this.address,
				this.address = address);
	}



	@Override
	public void propertyChange(PropertyChangeEvent event) {
		propertyChangeSupport.firePropertyChange("address", null, address);
	}

}