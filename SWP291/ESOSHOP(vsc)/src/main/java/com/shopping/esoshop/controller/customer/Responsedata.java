package com.shopping.esoshop.controller.customer;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.shopping.esoshop.model.*;
import com.shopping.esoshop.service.IDaoService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
public class Responsedata {
	@Autowired
	private IDaoService daoService;

	@GetMapping("/listcart")
	public ResponseEntity<List<Cart>> getCart(HttpSession session) {
		Customer customer = (Customer) session.getAttribute("customer");
		List<Cart> carts = (List<Cart>) session.getAttribute("carts");
		if (customer != null) {
			if (carts == null) {
				carts = daoService.getCartOfCustomer(customer.getId());
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
			@RequestParam(name = "product_color") Integer color) {
		Customer customer = (Customer) session.getAttribute("customer");
			Cart c = new Cart();
			c.setCustomerId(customer.getId());
			c.setProduct(daoService.getProductbyId(id));
			c.setQuantity(quantity);
			c.setColorId(color);
		if (customer != null) {
			int n = daoService.addToCart(c);
			session.setAttribute("added", c.getCustomerId() + "-" + c.getProduct().getId() + "-" + c.getColorId());
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
		Customer customer = (Customer) session.getAttribute("customer");
		return ResponseEntity.ok().body(daoService.getBillOfCustomer(customer, orderID));
	}

	@GetMapping("/cancelorder{orderId}")
	public ResponseEntity<String> cancelOrder(
			@PathVariable("orderId") String orderId) {
		return ResponseEntity.ok().body(daoService.deleteOrder(orderId));
	}

	@GetMapping("/getbills")
	public ResponseEntity<List<Bill>> getAllBills(HttpSession session) {
		Customer customer = (Customer) session.getAttribute("customer");
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
		Customer customer = (Customer) session.getAttribute("customer");
		return ResponseEntity.ok().body(daoService.getCartOfCustomer(customer.getId()));
	}

	@GetMapping("/customer/totalofcart")
	public ResponseEntity<Integer> getTotalOfCart(HttpSession session) {
		Customer customer = (Customer) session.getAttribute("customer");
		if(customer!=null)return ResponseEntity.ok().body(daoService.getTotalProductOfcart(customer.getId()));
		else{
		    List<Cart> carts = (List<Cart>)session.getAttribute("carts");
			if(carts!=null){
				return ResponseEntity.ok().body(carts.size());
			}
			return ResponseEntity.ok().body(0);
		}
	}

	@GetMapping("/customer/infor/{from}")
	public ResponseEntity<Customer> getInforCustomer(HttpSession session, @PathVariable("from") String from) {
		DefaultOidcUser user = (DefaultOidcUser) SecurityContextHolder.getContext().getAuthentication()
		.getPrincipal();
		Customer customer = new Customer();
		switch (from) {
			case "database":
			    customer = (Customer) session.getAttribute("customer");
				if(user!=null)customer.setPicture(user.getPicture());
				break;
			case "google":
				if (user != null) {
					customer.setEmail(user.getEmail());
					customer.setName(user.getFullName());
					customer.setAddress("");
					customer.setPhone(user.getPhoneNumber());
					customer.setPicture(user.getPicture());
					return ResponseEntity.ok().body(customer);
				}
				break;
			default:
				break;
		}
		return ResponseEntity.ok().body(customer);
	}

	@GetMapping(value = "/pay/{orderId}")
	public ResponseEntity<String> pay(Model model, HttpSession session,
			@PathVariable("orderId") String orderId) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer != null) {
			daoService.payBill(daoService.getBillOfCustomer(customer, orderId));
			return ResponseEntity.ok().body(orderId);
		}
		return ResponseEntity.ok().body(orderId);
	}
}
