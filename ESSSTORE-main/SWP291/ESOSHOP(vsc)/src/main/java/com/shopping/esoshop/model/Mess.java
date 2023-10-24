package com.shopping.esoshop.model;

import java.util.ArrayList;
import java.util.List;

public class Mess {
    private List<String> mess;
    private String email;
    private String OTP;
    private boolean loginsucces;
    public Mess() {
        this.mess = new ArrayList<>();
    }
    public Mess(List<String> mess, String email, String oTP) {
        this.mess = mess;
        this.email = email;
        OTP = oTP;
    }
    public List<String> getMess() {
        return mess;
    }
    public void setMess(List<String> mess) {
        this.mess = mess;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getOTP() {
        return OTP;
    }
    public void setOTP(String oTP) {
        OTP = oTP;
    }
    
    @Override
    public String toString() {
        return "Messages [mess=" + mess + ", email=" + email + ", OTP=" + OTP + "]";
    }
    public boolean isLoginsucces() {
        return loginsucces;
    }
    public void setLoginsucces(boolean loginsucces) {
        this.loginsucces = loginsucces;
    }
    
}
