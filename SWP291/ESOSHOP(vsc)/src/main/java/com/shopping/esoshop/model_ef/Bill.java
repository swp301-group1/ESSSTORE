package com.shopping.esoshop.model_ef;

import java.util.List;

public class Bill extends Order{
    private List<OrderDelail> orderdetails;
    private Account account ;
    private double totalAmount;
    public List<OrderDelail> getOrderdetails() {
        return orderdetails;
    }
    public void setOrderdetails(List<OrderDelail> orderdetails) {
        this.orderdetails = orderdetails;
        totalAmount = 0;
        for (OrderDelail odl : orderdetails) {
            totalAmount+= odl.getTotalPrice();
        }
    }
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    

}
