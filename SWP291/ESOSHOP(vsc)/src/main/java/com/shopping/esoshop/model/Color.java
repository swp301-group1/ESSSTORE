package com.shopping.esoshop.model;

public class Color {
	//private String[] colors = {"","Black","While","Yellow"};
	private String productId;
	//private int colorId;
	private String colorId;
	private String image;
	private String colorname;

	public Color(){}
	public Color(String productId, String colorId, String colorname, String image) {
		this.productId = productId;
		this.colorId = colorId;
		this.image = image;
		this.colorname = colorname;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getColorId() {
		return colorId;
	}
	public void setColorId(String colorId) {
		this.colorId = colorId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getColorname() {
		return colorname;
	}
	public void setColorname(String colorname) {
		this.colorname = colorname;
	}
	
	
	
}
