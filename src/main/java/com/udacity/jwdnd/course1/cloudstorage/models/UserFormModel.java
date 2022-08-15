package com.udacity.jwdnd.course1.cloudstorage.models;

public class UserFormModel {

	private String firstName;
	private String lastName;
	private String userName;
	private String password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public User toUserModel() {
		User u = new User(userName, null, password, firstName, lastName);
		return u;
	}

	@Override
	public String toString() {
		return "UserFormModel [firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", password=" + password + "]";
	}

	
}
