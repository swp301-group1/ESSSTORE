package com.shopping.esoshop.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shopping.esoshop.model.Account;
import com.shopping.esoshop.model.Customer;
import com.shopping.esoshop.service.IDaoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ViewCustomer {
	@Autowired
	private IDaoService daoService;
	
	@GetMapping(value = { "/", "home" })
	public String getHome(HttpSession session) {
		return "index";
	}

	@GetMapping("/register")
	public String getRegister() {
		return "register";
	}

	@GetMapping("/login")
	public String getLogin(HttpSession session) {
		Account account = (Account) session.getAttribute("account");
		if (account != null) {
			return "redirect:/home";
		}
		return "login";
	}

	@GetMapping("/cart")
	public String getCart(Model model, HttpSession session) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer != null) {
			model.addAttribute("carts", daoService.getCartOfCustomer(customer.getId()));
			return "cart";
		}
		return "login";
	}

	@GetMapping("/order_history")
	public String getOrderStory(Model model, HttpSession session) {
		Account account = (Account) session.getAttribute("account");
		if (account != null) {
			return "order_history";
		}
		model.addAttribute("url", "order_history");
		return "redirect:/login";
	}

	@GetMapping("/logout")
	public String logOut(HttpSession session) {
		session.setAttribute("account", null);
		session.setAttribute("customer", null);
		return "redirect:/home";
	}

	@GetMapping("/user_profile")
	public String userprofile(HttpSession session) {
		Customer customer = (Customer)session.getAttribute("customer");
		if(customer!=null){
			return "user_profile";
		}
		return "redirect:/login";
	}

}
