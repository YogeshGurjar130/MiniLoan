package com.example.loan.pojo;

import com.example.loan.entities.User;

public class UserPojo {

	private String name;
	private String password;
	private String email;
	private String role;

	public UserPojo() {
	}
	
	public UserPojo(User user) {
		this.name = user.getName();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.role = user.getRole();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
