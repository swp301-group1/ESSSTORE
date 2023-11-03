package com.shopping.esoshop.controller.customer;

import org.checkerframework.common.returnsreceiver.qual.This;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	@GetMapping("/cart")
public String viewCart(Model model, HttpSession session) {
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer != null) {
        List<CartItem> cartItems = daoService.getCartItemsForCustomer(customer.getId());
        model.addAttribute("cartItems", cartItems);
        return "view_cart"; // Create a view for displaying cart contents
    }
    return "redirect:/login";
}
@PostMapping("/update_cart")
	public String updateCart(Model model, HttpSession session, @RequestParam("cart_id") String cartId,
			@RequestParam("quantity") int quantity) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer != null) {
			// Update the quantity of the item in the cart
			int n = daoService.updateCart(cartId, quantity);
			// Update success
			if (n > 0) {
				return "redirect:/cart";
			}
		}
		return "redirect:/login";
	}
	@PostMapping("/clear_cart")
public String clearCart(Model model, HttpSession session) {
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer != null) {
        // Clear the user's shopping cart
        daoService.clearCart(customer.getId());
        return "redirect:/cart";
    }
    return "redirect:/login";
}
@PostMapping("/save_for_later/{cart_id}")
public String saveForLater(Model model, HttpSession session, @PathVariable("cart_id") String cartId) {
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer != null) {
        // Move the selected item to a "Saved for Later" list
        daoService.moveCartItemToSavedList(customer.getId(), cartId);
        return "redirect:/cart";
    }
    return "redirect:/login";
}
@GetMapping("/saved_items")
public String viewSavedItems(Model model, HttpSession session) {
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer != null) {
        // Retrieve the items saved for later by the customer
        List<CartItem> savedItems = daoService.getSavedItemsForCustomer(customer.getId());
        // Add the saved items to the model for the view
        model.addAttribute("savedItems", savedItems);
        return "saved_items"; // Create a view for displaying saved items
    }
    return "redirect:/login";
}

}






