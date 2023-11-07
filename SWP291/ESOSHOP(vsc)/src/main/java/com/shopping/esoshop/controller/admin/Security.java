package com.shopping.esoshop.controller.admin;

import java.io.File;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.esoshop.model_ef.Account;

import jakarta.servlet.http.HttpSession;

@RestController
public class Security {
     @PostMapping("admin/checklogin")
    public ResponseEntity<Boolean>getAdmin(HttpSession session,
        @RequestParam("email")String email,
        @RequestParam("password")String passowrd){
        String filePath =  System.getProperty("user.dir")+"\\admin.json";
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File(filePath);
        try {
        Account admin = objectMapper.readValue(jsonFile, Account.class);
        if(email.equals(admin.getEmail()) &&passowrd.equals(admin.getPassword())){
            session.setAttribute("admin", admin);
            return ResponseEntity.ok().body(true);
        }
      } catch (Exception e) {
        // TODO: handle exception
      }
        return ResponseEntity.ok().body(false);
    }
}
