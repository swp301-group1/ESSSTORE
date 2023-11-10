package com.shopping.esoshop.controller.customer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shopping.esoshop.model_ef.*;
import com.shopping.esoshop.service2.IDaoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class ViewCustomer {

	@Autowired
	private IDaoService daoService;
	
	@GetMapping(value = {"/home"})
	public String getHome(Model model) {
		return "index";
	}

	@GetMapping("/register")
	public String getRegister(Model model, Authentication authentication) {
			 if(authentication.isAuthenticated()){
			 DefaultOidcUser user = (DefaultOidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 Account newAccount = new Account();
			 newAccount.setEmail(user.getEmail());
			 newAccount.setName(user.getFullName());
			 newAccount.setPicture(user.getPicture());
			 model.addAttribute("account", newAccount);
		}
		return "register";
	}
	@GetMapping("/register2")

	public String getRegister2() {
		return "register2";
	}

	@GetMapping("/loginpage")
	public String getLogin(HttpSession session) {
		Account account = (Account) session.getAttribute("account");
		if (account != null) {
			return "redirect:/home";
		}
		return "loginpage";
	}
	@GetMapping("/detail{id}")
	public String getProduct(Model model,HttpSession session,
			@PathVariable("id")String id) {
		// product
		model.addAttribute("product", daoService.getProductbyId(id));
		// save session url to back add to cart more
		String urlback = "/detail"+id+"";
		if((String)session.getAttribute("urlback")==null){
			urlback="redirect:/home";
		}
		session.setAttribute("urlback", urlback);
		return "detail";
	}
	@GetMapping("/cart")
	public String getCart(Model model, HttpSession session) {
		Account customer = (Account) session.getAttribute("account");
		if (customer != null) {
			model.addAttribute("carts", daoService.getCartOfCustomer(customer.getAid()));
		}else model.addAttribute("carts", null);
		// else{
		// 	List<Cart> carts = (List<Cart>)session.getAttribute("carts");
		// 	if(carts!=null){
		// 		model.addAttribute("carts",carts );
		// 	}
		// 	else{
		// 		model.addAttribute("carts",null );
		// 	}
			
		// }
		return "cart";
	}

	@GetMapping("/order_history{orderid}")
	public String getOrderStory(Model model, HttpSession session,
	    @PathVariable("orderid")String orderid) {
		Account account = (Account) session.getAttribute("account");
		if (account != null) {
			model.addAttribute("orderid", orderid);
			return "order_history";
		}
		model.addAttribute("url", "order_history");
		return "redirect:/loginpage";
	}


	@GetMapping("/logout")
	public String logOut(HttpSession session,HttpServletRequest request, HttpServletResponse response) {
		session.setAttribute("account", null);
		session.setAttribute("customer", null);
	    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.setInvalidateHttpSession(true);
        logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());
		SecurityContextHolder.createEmptyContext();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            // Thực hiện đăng xuất bằng cách xóa thông tin đăng nhập
			new SecurityContextLogoutHandler().logout(request, response, authentication);
            SecurityContextHolder.clearContext();
        }
		return "redirect:/home";
	}

	@GetMapping("/user_profile")
	public String userprofile(HttpSession session) {
		Account customer = (Account) session.getAttribute("account");
		if(customer!=null){
			return "user_profile";
		}
		return "redirect:/loginpage";
	}
    @GetMapping(value = "/bill{orderId}")
    public String showBill(Model model, HttpSession session,
        @PathVariable(value = "orderId" )String orderId) {
        model.addAttribute("orderId", orderId);
        return "bill";
    }
	@GetMapping("/logingoogle")
	public String getLoginGoogle(HttpSession session) {
		return "logingoogle";
	}

}
