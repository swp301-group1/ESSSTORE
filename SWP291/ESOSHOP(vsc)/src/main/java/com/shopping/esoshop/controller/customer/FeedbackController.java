package com.shopping.esoshop.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shopping.esoshop.model.*;
import com.shopping.esoshop.service.IDaoService;

import jakarta.servlet.http.HttpSession;

@RestController
public class FeedbackController {
    
    @Autowired
    private IDaoService daoService;

    @PostMapping("/customer/feedback")
	public ResponseEntity<Integer> doFeedBack(HttpSession session,
	@RequestParam("productId")String productId,
	@RequestParam("star")Integer star,
	@RequestParam("comment") String comment) {
		Account customer= (Account)session.getAttribute("account");
		Feedback feedback = new Feedback();
		feedback.setProduct(daoService.getProductbyId(productId));
		if(comment.trim().isEmpty()){
			return ResponseEntity.ok().body(0);
		}
		feedback.setCommen(comment);
		feedback.setAid(customer.getAid());
		feedback.setAccount(customer);
		feedback.setStar(star);
		daoService.insertFeedBack(feedback);
		return ResponseEntity.ok().body(1);
	}
}
