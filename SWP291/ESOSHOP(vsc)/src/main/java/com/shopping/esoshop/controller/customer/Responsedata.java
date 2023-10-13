package com.shopping.esoshop.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shopping.esoshop.model.*;
import com.shopping.esoshop.service.DaoService;

import jakarta.servlet.http.HttpSession;

@RestController
public class Responsedata {
     @Autowired 
    private DaoService daoService;
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

}
