package com.shopping.esoshop.controller.customer;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.shopping.esoshop.model_ef.*;
import com.shopping.esoshop.service2.IDaoService;
import com.shopping.esoshop.service2.MailService;

import jakarta.servlet.http.HttpSession;

@RestController
public class Responsedata {
	@Autowired
	private IDaoService daoService;
	@Autowired
	MailService mailService;
	@GetMapping("/listcart")
	public ResponseEntity<List<Cart>> getCart(HttpSession session) {
		Account customer = (Account) session.getAttribute("account");
		List<Cart> carts = (List<Cart>) session.getAttribute("carts");
		if (customer != null) {
			if (carts == null) {
				carts = daoService.getCartOfCustomer(customer.getAid());
			}
			return ResponseEntity.ok().body(carts);
		} else {
			if (carts == null) {
				carts = new ArrayList();
				session.setAttribute("carts", carts);
			}
			return ResponseEntity.ok().body((List<Cart>)session.getAttribute("carts"));
		}
	}
	

	@GetMapping("/listproducts")
	public ResponseEntity<List<Product>> getListProducts() {
		return ResponseEntity.ok().body(daoService.getAllProduct());
	}

	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getListCategory() {
		return ResponseEntity.ok().body(daoService.getAllCategory());
	}

	@PostMapping("/addtocart")
	public ResponseEntity<String> addTocart(Model model, HttpSession session,
			@RequestParam(name = "product_id") String id,
			@RequestParam(name = "product_quanity", defaultValue = "1") Integer quantity,
			@RequestParam(name = "product_color") String color) {
		Account customer = (Account) session.getAttribute("account");
			Cart c = new Cart();
			c.setAid(customer.getAid());
			c.setAccount(customer);
			c.setProduct(daoService.getProductbyId(id));
			c.setQuantity(quantity);
			c.setColor(daoService.getColor(id, color));
		if (customer != null) {
			int n = daoService.addToCart(c);
			//session.setAttribute("added", c.getCustomerId() + "-" + c.getProduct().getId() + "-" + c.getColorId());
			if (n > 0) {
				return ResponseEntity.ok().body("Add succes");
			}
		}
		return ResponseEntity.ok().body("Add false");
	}

	@GetMapping("/getlistorderdetails{orderId}")
	public ResponseEntity<List<OrderDelail>> getlistorderdetail(
			@PathVariable("orderId") String orderId) {
		return ResponseEntity.ok().body(daoService.getListOrderdetail(orderId));
	}

	@GetMapping("/getbill{orderId}")
	public ResponseEntity<Bill> getBills(HttpSession session,
			@PathVariable("orderId") String orderID) {
		Account customer = (Account) session.getAttribute("account");
		return ResponseEntity.ok().body(daoService.getBillOfCustomer(customer, orderID));
	}

	@GetMapping("/cancelorder{orderId}")
	public ResponseEntity<String> cancelOrder(
			@PathVariable("orderId") String orderId) {
		return ResponseEntity.ok().body(daoService.deleteOrder(orderId));
	}

	@GetMapping("/getbills")
	public ResponseEntity<List<Bill>> getAllBills(HttpSession session) {
		Account customer = (Account) session.getAttribute("account");
		return ResponseEntity.ok().body(daoService.getAllBillsOfCustomer(customer));
	}

	@GetMapping("/getproductbycategoty/{category}")
	public ResponseEntity<List<Product>> getProductsByCategory(
			@PathVariable("category") Integer category) {
		return ResponseEntity.ok().body(daoService.getAllProductByCategory(category));
	}

	@GetMapping("product/feedback/{productId}")
	public ResponseEntity<List<Feedback>> getFeedbackOfProduct(
			@PathVariable("productId") String productId) {
		return ResponseEntity.ok().body(daoService.getFeedBack(productId));
	}

	@GetMapping("product/reportrating/{productId}")
	public ResponseEntity<ReportRating> getReportRateting(
			@PathVariable("productId") String productId) {
		return ResponseEntity.ok().body(daoService.getReportRating(productId));
	}

	@GetMapping("/customer/cart")
	public ResponseEntity<List<Cart>> getCart(Model model, HttpSession session) {
	    Account customer = (Account) session.getAttribute("account");
		return ResponseEntity.ok().body(daoService.getCartOfCustomer(customer.getAid()));
	}

	@GetMapping("/customer/totalofcart")
	public ResponseEntity<Integer> getTotalOfCart(HttpSession session) {
		Account customer = (Account) session.getAttribute("account");
		if(customer!=null)return ResponseEntity.ok().body(daoService.getTotalProductOfcart(customer.getAid()));
		else{
		    List<Cart> carts = (List<Cart>)session.getAttribute("carts");
			if(carts!=null){
				return ResponseEntity.ok().body(carts.size());
			}
			return ResponseEntity.ok().body(0);
		}
	}

	@GetMapping("/user/infor")
	public ResponseEntity<Account> getInforCustomer(HttpSession session) {
		Account user = (Account) session.getAttribute("account");
        if (user != null) {
				return ResponseEntity.ok().body(daoService.findAccount(user.getAid()));
		}
		return ResponseEntity.ok().body(null);
	}

	@GetMapping(value = "/pay/{orderId}")
	public ResponseEntity<Boolean> pay(Model model, HttpSession session,
			@PathVariable("orderId") String orderId) {
		//Account customer = (Account) session.getAttribute("account");
		Account customer = daoService.findAccount(15);
		if (customer != null ) {
			Bill bill = daoService.getBillOfCustomer(customer, orderId);
			boolean pay = daoService.payBill(orderId);
			if(pay){
				Boolean resoutl = mailService.sendEmailBill(bill);
			}
			return ResponseEntity.ok().body(pay);
		}
		return ResponseEntity.ok().body(false);
	}

	@GetMapping(value = "/api/send/bill/{orderid}")
	public ResponseEntity<Boolean> sendBillEmail(HttpSession session,
	@PathVariable("orderid")String orderid){
		Account account = (Account) session.getAttribute("account");
		boolean resoutl =mailService.sendEmailBill(daoService.getBillOfCustomer(account, orderid));
		return ResponseEntity.ok().body(resoutl);
	}

	@PostMapping(value = "/api/customer/edit/profile")
	public ResponseEntity<Boolean> updateProfile(HttpSession session,
		@RequestParam("name")String name,
		@RequestParam("phone")String phone,
		@RequestParam("address") String address,
		@RequestParam("password") String password){
			Account customer = (Account) session.getAttribute("account");
			Account customerUpdate = new Account(customer.getAid(),customer.getEmail()
			,phone,password,customer.getRole(),customer.getStatus(),name,address,customer.getPicture());
		return ResponseEntity.ok().body(daoService.updateAccount(customerUpdate));
	}
	@PostMapping("/api/customer/account/add")
    public ResponseEntity<Boolean> addStaff(HttpSession session,
        @RequestParam("email")String email,
        @RequestParam(value = "phone" ,defaultValue = "")String phone,
        @RequestParam("password")String password,
        @RequestParam("name")String name,
        @RequestParam("address")String address){
            Account account = new Account(0,email,phone,password,1,1,name,address,"null");
			boolean resutl = daoService.createAccount(account);
			if(resutl){
				account = daoService.findAccountByEmail(email);
				if(account.getAid()==0) account = daoService.findAccountByPhone(phone);
				if(account.getAid()!=0){
					session.setAttribute("account", account);
					return ResponseEntity.ok().body(true);
				}
			}
			return ResponseEntity.ok().body(false);
    }
}
