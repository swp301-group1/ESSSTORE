package com.shopping.esoshop.controller.admin;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shopping.esoshop.model.*;
import com.shopping.esoshop.service.IDaoService;

import jakarta.servlet.http.HttpSession;

@RestController
public class AdminController {
    @Autowired
    private IDaoService daoServicel;

    @GetMapping("admin/dashboard/top-sale-product")
    public ResponseEntity<List<TopSaleProduct>> getTopSaleProduct() {
        return ResponseEntity.ok().body(daoServicel.getTopSaleProducts());
    }

    @GetMapping("admin/dashboard/top-feedback-product")
    public ResponseEntity<List<TopFeedbackProduct>> getTopFeedbackProduct() {
        return ResponseEntity.ok().body(daoServicel.topFeedbackProducts());
    }

    @PostMapping("admin/dashboard/revenue")
    public ResponseEntity<List<Revenue>> getRevenue(
            @RequestParam("from") Date from,
            @RequestParam("to") Date to) {
        return ResponseEntity.ok().body(daoServicel.getRevenues(from, to));
    }

    @GetMapping("admin/user/customer/{email}")
    public ResponseEntity<Customer> getInforCustomer(
            @PathVariable("email") String email) {
        return ResponseEntity.ok().body((Customer) daoServicel.getCustomerByEmail(email));
    }

    @GetMapping("admin/user/staff/{email}")
    public ResponseEntity<Staff> getInforStaff(
            @PathVariable("email") String email) {
        return ResponseEntity.ok().body((Staff) daoServicel.getInforStaff(email));
    }

    @GetMapping("admin/user/account/{role}")
    public ResponseEntity<List<Account>> getAllAccountByRole(
            @PathVariable("role") Integer role) {
        return ResponseEntity.ok().body(daoServicel.getAllAccount(role));
    }

    @GetMapping("admin/user/ban/{email}/{status}")
    public ResponseEntity<Boolean> setStatusAccount(HttpSession session,
        @PathVariable("email") String email,
        @PathVariable("status")Integer status){
            try {
                boolean resutl = daoServicel.setStatusAccount(email, status);
                if(resutl){
                    session.setAttribute("customer", null);
                }
                return ResponseEntity.ok().body(resutl);
            } catch (Exception e) {
                 return ResponseEntity.ok().body(false);
            }
    }

}
