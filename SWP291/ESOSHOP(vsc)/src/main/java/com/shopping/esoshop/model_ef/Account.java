package com.shopping.esoshop.model_ef;


public class Account {

	private int aid;
	private String email;
	private String phonenumber;
	private String password;
	private int role;
	private int status;
	private String name;
	private String address;
	private String picture;

	public Account() {
	}
	
	public Account(int aid, String email, String phonenumber, String password, int role, int status, String name,
			String address, String picture) {
		this.aid = aid;
		this.email = email;
		this.phonenumber = phonenumber;
		this.password = password;
		this.role = role;
		this.status = status;
		this.name = name;
		this.address = address;
		this.picture = picture;
	}

	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}

	
}
