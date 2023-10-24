package com.shopping.esoshop.model;


public class Account {

	private String email;
	
	private String password;
	
	private int role;

	private int status;
	
	public Account(String email, int role) {
		super();
		this.email = email;
		this.role = role;
		this.password=null;
	}
	public Account(String email, String password,int role) {
		super();
		this.email = email;
		this.role = role;
		this.password=password;
	}
		public Account(String email, String password,int role,int status) {
		super();
		this.email = email;
		this.role = role;
		this.password=password;
		this.status = status;
	}
	public Account() {
		super();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Account [email=" + email + ", password=" + password + ", role=" + role + ", status=" + status + "]";
	}
	
	

	
}
