package com.shopping.esoshop.model_ef;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

public class Order {
	private String orderId;
	private int aid;
	private Account customer;
	private Date orderDate;
	private Time orderTime;
	private int status;
	private String address;
	
	public Order() {
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public Account getCustomer() {
		return customer;
	}
	public void setCustomer(Account customer) {
		this.customer = customer;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Time getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Time orderTime) {
		this.orderTime = orderTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String createId(int aid){
		String id  = "od"+aid+LocalDateTime.now().toString();
	    id=id.replaceAll("[:,.,-]", "");
		return  id;
	}
	
}
