package com.shopping.esoshop.model;

import java.sql.Date;
import java.sql.Time;

public class Feedback {
	private int feedbackId;

	private Product product;
	
	private int aid ;
	
	private Account account;
	
	private String commen;
	
	private int star;

	private Date date;
	
	private Time time;

	private int status;

	public Feedback() {
	}

	public int getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getCommen() {
		return commen;
	}

	public void setCommen(String commen) {
		this.commen = commen;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
}
