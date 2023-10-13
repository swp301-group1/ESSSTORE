package com.shopping.esoshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class ViewAdminController {

    @GetMapping("/admin/dashboard")
    public String showDashbroad(HttpSession session) {
        return "/admin/dashboard";
    }

    @GetMapping("/admin/revenue")
    public String showRevenue(HttpSession session) {
        return "/admin/revenue";
    }

    @GetMapping("/admin/listcustomer")
    public String showListCustomer(HttpSession session) {
        return "/admin/list_customer";
    }

    @GetMapping("/admin/liststaff")
    public String showListStaff(HttpSession session) {
        return "/admin/list_staff";
    }
}
