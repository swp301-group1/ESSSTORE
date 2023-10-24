package com.shopping.esoshop.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shopping.esoshop.model.Page;
import com.shopping.esoshop.model.Product;
import com.shopping.esoshop.service.*;

// import jakarta.servlet.http.HttpSession;

@RestController
public class HomeController {
    
	private int sizepage =20;
	
	@Autowired
	private DaoService daoService;
	
	@GetMapping("/searchproductbyname/{name}")
	public ResponseEntity<List<Product>> searchProductByName(
		@PathVariable("name")String name) {
		return ResponseEntity.ok().body(daoService.searchByName(name));
	}
	@GetMapping("/getproduct/page/{n}")
	public ResponseEntity<List<Product>> getProductInPage(@PathVariable("n")Integer n){
		return ResponseEntity.ok().body(daoService.getAllProductinPage(n, sizepage));
	}
	
	@GetMapping("/page/{n}")
	public ResponseEntity<Page> getPages(@PathVariable("n")Integer n) {
		return ResponseEntity.ok().body(daoService.getPage(n, sizepage));
	}
	

}
