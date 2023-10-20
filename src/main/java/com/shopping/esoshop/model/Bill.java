package com.shopping.esoshop.model;

import java.sql.Date;
import java.util.List;

public class Bill extends Order{
    private List<OrderDelail> orderdetails;
    private Staff staff;
    private double totalAmount;

    public Bill(String orderId,Customer customer, List<OrderDelail> orderdetails, Date orderDate, Staff staff, int status,
            String address, double totalAmount) {
        super(orderId, customer, orderDate, staff, status, address);
        this.orderdetails = orderdetails;
        this.totalAmount = totalAmount;
    }

    public Bill() {
    }

    public List<OrderDelail> getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(List<OrderDelail> orderdetails) {
        this.orderdetails = orderdetails;
        sumPrice();
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }


    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    private void sumPrice() {
        for (OrderDelail orderDelail : this.orderdetails) {
            double price = orderDelail.getProduct().getPrice() * orderDelail.getQuantity();
            this.totalAmount += price;
        }
    }

    @Override
    public String toString() {
        return "Bill ["+super.toString()+"orderdetails=" + orderdetails + ", staff=" + staff + ", totalAmount=" + totalAmount + "]";
    }

}
