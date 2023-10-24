package com.shopping.esoshop.controller.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.esoshop.model.Account;
import com.shopping.esoshop.service.IDaoService;

import jakarta.servlet.http.HttpSession;

@RestController
public class SecutityController{
    @Autowired
    private IDaoService daoService;   
     
    @PostMapping("/staff/checklogin")
    public ResponseEntity<String> Login(HttpSession session,
        @RequestParam(value = "email")String email,
        @RequestParam(value ="password")String password){
            Account account = daoService.checkLogin(email,password,2);
            session.setAttribute("staff", daoService.getStaffByEmail(account.getEmail()));
        return ResponseEntity.ok().body("login thanh cong");
    }
    @GetMapping("/staff/logout")
    public ResponseEntity<Boolean>logOut(HttpSession session){
        session.setAttribute("staff", null);
        return ResponseEntity.ok().body(true);
    }

}