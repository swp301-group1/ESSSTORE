package com.shopping.esoshop.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.shopping.esoshop.model.*;
import com.shopping.esoshop.service.IDaoService;

import jakarta.servlet.http.HttpSession;

@RestController
public class Responsedata {
     @Autowired 
    private IDaoService daoService;
	@GetMapping("/listcart")
	public ResponseEntity<List<Cart>> getCart(HttpSession session) {
		Customer customer  = (Customer)session.getAttribute("customer");
		if(customer!=null){
			return ResponseEntity.ok().body(daoService.getCartOfCustomer(customer.getId()));
		}
		return ResponseEntity.ok().body(daoService.getCartOfCustomer(0));
	}

    @GetMapping("/listproducts")
	public ResponseEntity<List<Product>> getListProducts() {
		return ResponseEntity.ok().body(daoService.getAllProduct());
	}

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getListCategory(){
        return ResponseEntity.ok().body(daoService.getAllCategory());
    }

    // @GetMapping("/listproducts")
	// 	public RequestEntity<List<Product>> getListProductss() {
	// 	return new RequestEntity<List<Product>>(daoService.getAllProduct(), null, null, null, getClass());
	// }

	// @GetMapping("/detete_cart{cart_id}")
	// public ResponseEntity<String> deleteProductOfCart(HttpSession session,
	// 		@PathVariable("cart_id") String cartId) {
	// 	Customer customer = (Customer)session.getAttribute("customer");
	// 	if(customer!=null) {
	// 		// delete product of cart
	// 	   int n = daoService.deletCart(cartId);
	// 	}
	// 	return ResponseEntity.ok().body("Delete succses");
	// }

	@PostMapping("/addtocart")
	public ResponseEntity<String> addTocart(Model model,HttpSession session,
			@RequestParam(name = "product_id") String id,
			@RequestParam(name = "product_quanity",defaultValue = "1")Integer quantity,
			@RequestParam(name = "product_color") Integer color){
		Customer customer = (Customer)session.getAttribute("customer");
		if(customer!=null) {
			Cart c = new Cart();
			c.setCustomerId(customer.getId());
			c.setProduct(daoService.getProductbyId(id));
			c.setQuantity(quantity);
			c.setColorId(color);
		    int n =daoService.addToCart(c);
		    session.setAttribute("added",c.getCustomerId()+"-"+c.getProduct().getId()+"-"+c.getColorId());
			if(n>0){
				return ResponseEntity.ok().body("Add succes");
			}
			return ResponseEntity.ok().body("You added to cart");
		}
		return ResponseEntity.ok().body("Please login!");
	}
	@GetMapping("/getlistorderdetails{orderId}")
	public ResponseEntity<List<OrderDelail>> getlistorderdetail(
		@PathVariable("orderId")String orderId){
		return ResponseEntity.ok().body(daoService.getListOrderdetail(orderId));
	}

	@GetMapping("/getbill{orderId}")
	public ResponseEntity<Bill> getBills(HttpSession session,
	@PathVariable("orderId")String orderID) {
		Customer customer= (Customer)session.getAttribute("customer");
		return ResponseEntity.ok().body(daoService.getBillOfCustomer(customer, orderID));
	}
	@GetMapping("/cancelorder{orderId}")
	public ResponseEntity<String> cancelOrder(
		@PathVariable("orderId")String orderId){
			return ResponseEntity.ok().body(daoService.deleteOrder(orderId));
	}
	@GetMapping("/getbills")
	public ResponseEntity<List<Bill>> getAllBills(HttpSession session) {
		Customer customer= (Customer)session.getAttribute("customer");
		return ResponseEntity.ok().body(daoService.getAllBillsOfCustomer(customer));
	}
	@GetMapping("/getproductbycategoty/{category}")
	public ResponseEntity<List<Product>> getProductsByCategory(
		@PathVariable("category")Integer category){
			return ResponseEntity.ok().body(daoService.getAllProductByCategory(category));
	}
	@GetMapping("product/feedback/{productId}")
	public ResponseEntity<List<Feedback>> getFeedbackOfProduct(
		@PathVariable("productId")String productId){
		return ResponseEntity.ok().body(daoService.getFeedBack(productId));
	}
	@GetMapping("product/reportrating/{productId}")
	public ResponseEntity<ReportRating> getReportRateting(
		@PathVariable("productId")String productId){
		return ResponseEntity.ok().body(daoService.getReportRating(productId));
	}

	@GetMapping("/customer/cart")
	public ResponseEntity<List<Cart>> getCart(Model model,HttpSession session) {
		Customer customer  = (Customer)session.getAttribute("customer");
		return ResponseEntity.ok().body(daoService.getCartOfCustomer(customer.getId()));
	}

	@GetMapping("/customer/totalofcart")
	public ResponseEntity<Integer> getTotalOfCart(HttpSession session){
		Customer customer  = (Customer)session.getAttribute("customer");
		return ResponseEntity.ok().body(daoService.getTotalProductOfcart(customer.getId()));
	}
}
