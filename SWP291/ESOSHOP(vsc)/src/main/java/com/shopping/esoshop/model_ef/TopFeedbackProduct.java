package com.shopping.esoshop.model_ef;

public class TopFeedbackProduct extends Product{
    private int totalFeedback;
    private double avgRating;
    public TopFeedbackProduct(){

    }
    public int getTotalFeedback() {
        return totalFeedback;
    }
    public void setTotalFeedback(int totalFeedback) {
        this.totalFeedback = totalFeedback;
    }
    public double getAvgRating() {
        return avgRating;
    }
    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }
    
}
