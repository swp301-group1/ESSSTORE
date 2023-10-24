package com.shopping.esoshop.model;

public class Revenue extends Product {
    private int soled;
    private double total;
    public Revenue() {
    }
    public int getSoled() {
        return soled;
    }
    public void setSoled(int soled) {
        this.soled = soled;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    
}
