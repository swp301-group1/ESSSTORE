package com.shopping.esoshop.model;

import java.sql.Date;
import java.sql.Time;

public class Cart {
	private String cartId;
	
	private int customerId;
	
	private Product product;
	
	private int quantity;

	private Date date;
	
	private Time time;
	
	private Color color;

	public Cart(String cartId, int customerId, Product product, int quantity, Date date, Time time, Color color) {
		super();
		this.cartId= cartId;
		this.customerId = customerId;
		this.product = product;
		this.quantity = quantity;
		this.date = date;
		this.time = time;
		this.color = color;
	}

	public Cart() {
        
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	
	
}
