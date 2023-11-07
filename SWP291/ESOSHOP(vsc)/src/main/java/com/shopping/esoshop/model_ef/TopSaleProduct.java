package com.shopping.esoshop.model_ef;

public class TopSaleProduct extends Product {
    private double income;
    private int totalOrder;

    public TopSaleProduct(double income, int totalOrder) {
        this.income = income;
        this.totalOrder = totalOrder;
    }

    public TopSaleProduct() {
        
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public int getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(int totalOrder) {
        this.totalOrder = totalOrder;
    }

    @Override
    public String toString() {
        return "TopSaleProduct [ProductId="+super.getId()+"ProductName="+super.getName()+", income=" + income + ", totalOrder=" + totalOrder + "]";
    }

}
