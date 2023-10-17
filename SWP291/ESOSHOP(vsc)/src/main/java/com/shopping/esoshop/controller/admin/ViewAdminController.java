package com.shopping.esoshop.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.shopping.esoshop.model.Account;

import jakarta.servlet.http.HttpSession;

@Controller
public class ViewAdminController {

    @GetMapping("/admin/login")
    public String login(HttpSession session) {
        Account admin = (Account)session.getAttribute("admin");
        if(admin==null){
            return "admin/login";
        }
        return "/admin/dashboard";
    }

    @GetMapping("/admin/dashboard")
    public String showDashbroad(HttpSession session ) {
        Account admin = (Account)session.getAttribute("admin");
        if(admin!=null){
            return "admin/dashboard";
        }
          return "/admin/login";
    }

    @GetMapping("/admin/revenue")
    public String showRevenue(HttpSession session) {
        Account admin = (Account)session.getAttribute("admin");
        if(admin==null){
            return "redirect:/admin/login";
        }
        return "/admin/revenue";
    }

    @GetMapping("/admin/listcustomer")
    public String showListCustomer(HttpSession session) {
        Account admin = (Account)session.getAttribute("admin");
        if(admin==null){
            return "redirect:/admin/login";
        }
        return "/admin/list_customer";
    }

    @GetMapping("/admin/liststaff")
    public String showListStaff(HttpSession session) {
        Account admin = (Account)session.getAttribute("admin");
        if(admin==null){
            return "redirect:/admin/login";
        }
        return "/admin/list_staff";
    }
}
