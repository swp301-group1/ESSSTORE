package com.shopping.esoshop.controller.admin;


import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shopping.esoshop.model.*;
import com.shopping.esoshop.service.DaoService;
@RestController
public class DashboardController {
    @Autowired
    private DaoService daoServicel;
    @GetMapping("admin/dashboard/top-sale-product")
    public ResponseEntity<List<TopSaleProduct>> getTopSaleProduct(){
       return ResponseEntity.ok().body(daoServicel.getTopSaleProducts());
    }    

   @GetMapping("admin/dashboard/top-feedback-product")
   public ResponseEntity<List<TopFeedbackProduct>> getTopFeedbackProduct(){
       return ResponseEntity.ok().body(daoServicel.topFeedbackProducts());
   }  

   @PostMapping("admin/dashboard/revenue")
   public ResponseEntity<List<Revenue>> getRevenue(
    @RequestParam("from")Date from,
    @RequestParam("to") Date to){
       return ResponseEntity.ok().body(daoServicel.getRevenues(from,to));
   }  
   
}
