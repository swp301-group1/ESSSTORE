package com.shopping.esoshop.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.shopping.esoshop.model.Customer;
import com.shopping.esoshop.service.IDaoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
	
	@Autowired
	private IDaoService daoService;

	// delete product form cart
	@GetMapping("/detete_cart{cart_id}")
	public String deleteProductOfCart(Model model,HttpSession session,
			@PathVariable("cart_id") String cartId) {
		Customer customer = (Customer)session.getAttribute("customer");
		if(customer!=null) {
			// delete product of cart
		   int n = daoService.deletCart(cartId);
		   // delete succes
		   if(n>0) {
			   return "redirect:/cart";
		   }
		}
		return "redirect:/login";
	}
	
}
