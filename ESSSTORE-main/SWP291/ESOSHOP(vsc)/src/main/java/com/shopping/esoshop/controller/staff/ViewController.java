package com.shopping.esoshop.controller.staff;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.shopping.esoshop.model.Staff;

import jakarta.servlet.http.HttpSession;



@Controller
public class ViewController {
    

    @GetMapping("/staff/orders")
    public String getViewManages(HttpSession session){
        return checkSessionStaff(session,"staff/ordercustomer2");
    }

    @GetMapping(value={"/staff/product_management"})
    public String getMethodName(HttpSession session) {
        return checkSessionStaff(session,"/staff/product_management");
    }

    @GetMapping(value={"/staff/ordercustomer"})
    public String getViewOrder(HttpSession session) {
        return checkSessionStaff(session,"/staff/ordercustomer");
    }

    @GetMapping(value = {"/staff","staff/login"})
    public String login(HttpSession session){
        Staff staff = (Staff)session.getAttribute("staff");
        if(staff!=null){
            return "redirect:/staff/productmanages";
        }
        return "/staff/login";
    }

    String checkSessionStaff(HttpSession session,String url){
        Staff staff = (Staff)session.getAttribute("staff");
        if(staff==null){
            return "redirect:/staff/login";
        }
        return url;
    }
}
