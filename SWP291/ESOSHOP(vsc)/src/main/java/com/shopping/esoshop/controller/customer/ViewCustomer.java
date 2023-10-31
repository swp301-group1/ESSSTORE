package com.shopping.esoshop.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shopping.esoshop.model.Account;
import com.shopping.esoshop.model.Customer;
import com.shopping.esoshop.service.IDaoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class ViewCustomer {

	@Autowired
	private IDaoService daoService;
	
	@GetMapping(value = {"home"})
	public String getHome(HttpSession session) {
		return "index";
	}

	@GetMapping("/register")
	public String getRegister() {
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

	@GetMapping("/cart")
	public String getCart(Model model, HttpSession session) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer != null) {
			model.addAttribute("carts", daoService.getCartOfCustomer(customer.getId()));
			return "cart";
		}
		return "loginpage";
	}

	@GetMapping("/order_history")
	public String getOrderStory(Model model, HttpSession session) {
		Account account = (Account) session.getAttribute("account");
		if (account != null) {
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
		Customer customer = (Customer)session.getAttribute("customer");
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
