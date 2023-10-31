package com.shopping.esoshop.controller.admin;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
            @RequestParam("to") Date to ) {
                try {
                    if(from.before(to)){
                       return ResponseEntity.ok().body(daoServicel.getRevenues(from, to));
                    }
                } catch (Exception e) {
                  System.out.println("Date input false fomat");
                  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
                }
        System.out.println("Date from must befor or equal date to");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
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

    @PostMapping("admin/user/ban")
    public ResponseEntity<Boolean> setStatusAccount(HttpSession session,
        @RequestParam("email") String email,
        @RequestParam("status")Integer status){
            try {
                if(status==1 || status==0){
                    boolean resutl = daoServicel.setStatusAccount(email, status);
                    if(resutl){
                       session.setAttribute("customer", null);
                    }
                    System.out.println("update success");
                   return ResponseEntity.ok().body(resutl);
                }else{
                     System.out.println("input status must 0||1");
                    return ResponseEntity.ok().body(false);
                }
            } catch (Exception e) {
                 System.out.println("update false");
                 return ResponseEntity.ok().body(false);
            }
    }

}
