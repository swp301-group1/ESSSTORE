package com.shopping.esoshop.model;

import java.time.LocalDateTime;

public class OrderDelail {
	private String orderDetailId;
	private String orderId;
	private Product product;
	private int quantity;
	private Color color;
	private double price;
	private Double totalPrice;

	public String createId(int customerId){
		String id  = "odl"+customerId+LocalDateTime.now().toString();
	    id=id.replaceAll("[:,.,-]", "");
		return  id;
	}
	public OrderDelail(String orderDetailId, String orderId, Product product, int quantity, Color color) {
		super();
		this.orderDetailId = orderDetailId;
		this.orderId = orderId;
		this.product = product;
		this.quantity = quantity;
		this.color = color;
	}

	public OrderDelail() {
		super();
	}

	public String getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	// public int getColor() {
	// 	return color;
	// }

	// public void setColor(int color) {
	// 	this.color = color;
	// 	for (Color c : this.product.getColor()) {
	// 		if (c.getColorId() == color) {
	// 			this.nameColor = c.getColorname();
	// 			this.image = c.getImage();
	// 		}
	// 	}
	// }

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
		// this.totalPrice = price * quantity;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}


}
