package com.shopping.esoshop.model_ef;

public class Page {
	private int pageActive;
	private int totalPage;
	
	public Page() {
	}
	public Page(int pageActive, int totalPage) {
		this.pageActive = pageActive;
		this.totalPage = totalPage;
	}
	public int getPageActive() {
		return pageActive;
	}
	public void setPageActive(int pageActive) {
		this.pageActive = pageActive;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	@Override
	public String toString() {
		return "Page [pageActive=" + pageActive + ", totalPage=" + totalPage + "]";
	}

}
