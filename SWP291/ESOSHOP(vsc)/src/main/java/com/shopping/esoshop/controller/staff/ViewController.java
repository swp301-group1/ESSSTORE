package com.shopping.esoshop.controller.staff;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shopping.esoshop.model.*;

import jakarta.servlet.http.HttpSession;



@Controller
public class ViewController {
    

    @GetMapping("/staff/orders")
    public String getViewManages(HttpSession session,Model model){
        return checkAccout(session,"/staff/ordercustomer2",model);
    }

    @GetMapping(value={"/staff/product_management"})
    public String getMethodName(HttpSession session,Model model) {
        return checkAccout(session,"/staff/product_management",model);
    }
    ///staff/login
    @GetMapping(value = {"/staff","staff/login"})
    public String login(HttpSession session){
        Account staff = (Account)session.getAttribute("account");
        if(staff!=null){
            return "redirect:/staff/orders";
        }
        return "/staff/login";
    }

    String checkAccout(HttpSession session,String url,Model model){
        Account staff = (Account)session.getAttribute("account");
        if(staff!=null){
            if( staff.getRole()>=2) {
                return url;
            }
            else {
                model.addAttribute("mess", "The account does not have access");
                return "redirect:/staff/login";
            }
        }
        else return "redirect:/staff/login";
    }
}
