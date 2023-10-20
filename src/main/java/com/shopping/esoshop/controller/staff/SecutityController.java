package com.shopping.esoshop.controller.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.esoshop.model.Account;
import com.shopping.esoshop.model.Staff;
import com.shopping.esoshop.service.DaoService;

import jakarta.servlet.http.HttpSession;

@RestController
public class SecutityController{
    @Autowired
    private DaoService daoService;   
     
    @PostMapping("/staff/checklogin")
    public ResponseEntity<String> Login(HttpSession session,
        @RequestParam(value = "email")String email,
        @RequestParam(value ="password")String password){
            Account account = daoService.checkLogin(email,password,2);
            session.setAttribute("staff", daoService.getStaffByEmail(account.getEmail()));
        return ResponseEntity.ok().body("login thanh cong");
    }
    @GetMapping("staff/getstaff")
    public ResponseEntity<Staff>getStaff(HttpSession session){
        Staff staff= (Staff)session.getAttribute("staff");
        return ResponseEntity.ok().body(staff);
    }
}