package com.shopping.esoshop.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shopping.esoshop.model.Account;

import jakarta.servlet.http.HttpSession;

@Controller
public class ViewAdminController {

    @GetMapping(value = {"/admin","/admin/login"})
    public String login(HttpSession session) {
        return "/admin/login";
    }

    @GetMapping("/admin/dashboard")
    public String showDashbroad(HttpSession session ,Model model) {
        return checkAccout("/admin/dashboard",session,model);
    }

    @GetMapping("/admin/revenue")
    public String showRevenue(HttpSession session,Model model) {
       return checkAccout("/admin/revenue",session,model);
    }

    @GetMapping("/admin/list_customer")
    public String showListCustomer(HttpSession session,Model model) {
        return checkAccout("/admin/list_customer",session,model);
    }

    @GetMapping("/admin/list_staff")
    public String showListStaff(HttpSession session,Model model) {
        return checkAccout("/admin/list_staff",session,model);
    }

    private String checkAccout(String url,HttpSession session,Model model){
        Account admin = (Account)session.getAttribute("account");
        if(admin!=null){
            if( admin.getRole()>=3) {
                return url;
            }
            else {
                return "redirect:/admin/login";
            }
        }
        else return "redirect:/admin/login";
    }
}
