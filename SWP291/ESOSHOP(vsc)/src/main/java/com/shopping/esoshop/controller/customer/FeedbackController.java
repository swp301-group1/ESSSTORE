package com.shopping.esoshop.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shopping.esoshop.model.Customer;
import com.shopping.esoshop.model.Feedback;
import com.shopping.esoshop.service.DaoService;

import jakarta.servlet.http.HttpSession;

@RestController
public class FeedbackController {
    
    @Autowired
    private DaoService daoService;

    @PostMapping("/customer/feedback")
	public ResponseEntity<Integer> doFeedBack(HttpSession session,
	@RequestParam("productId")String productId,
	@RequestParam("star")Integer star,
	@RequestParam("comment") String comment) {
		Customer customer= (Customer)session.getAttribute("customer");
		Feedback feedback = new Feedback();
		feedback.setProduct(daoService.getProductbyId(productId));
		if(comment.trim().isEmpty()){
			return ResponseEntity.ok().body(0);
		}
		feedback.setCommen(comment);
		feedback.setCustomer(customer);
		feedback.setStar(star);
		daoService.insertFeedBack(feedback);
		return ResponseEntity.ok().body(1);
	}
}
