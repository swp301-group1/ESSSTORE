package com.shopping.esoshop.model;

public class Staff extends User{

	public Staff(){
		super();
	}
	public Staff(int id,String name,String address,String phone,String email){
		super(id, name, address, address, email);
	}
    
	@Override
	public String toString() {
		return "Staff [getId()=" + getId() + ", getName()=" + getName() + ", getAddress()=" + getAddress()
				+ ", getPhone()=" + getPhone() + ", getEmail()=" + getEmail() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
