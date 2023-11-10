package com.shopping.esoshop.controller.customer;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.esoshop.model.*;
import com.shopping.esoshop.service.IDaoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {
	
	@Autowired
	private IDaoService daoService;

	// order product
	@RequestMapping("/order")
	public String order(Model model,HttpSession session,
			@RequestParam(name = "product_order",defaultValue = "0", required = false)String cartId,
			@RequestParam(name = "product_quantity",defaultValue = "",required = false)String quantity) {
			Customer customer = (Customer)session.getAttribute("customer");
		if(cartId.equals("0")) {
			return "redirect:/cart";
		}
		else {
			try {
				String[] cartIDs = cartId.split(",");
				String[] quantitys = quantity.split(",");
				List<OrderDelail> orderDelails = new ArrayList<OrderDelail>();
				for (int i=0;i< cartIDs.length;i++) {
					// get information cart
					Cart c = daoService.getCartByCartIdOfCustomer(customer.getId(), cartIDs[i]);
					OrderDelail od = new OrderDelail();
					od.setOrderDetailId(od.createId(customer.getId()));
					od.setProduct(c.getProduct());
					od.setQuantity(Integer.parseInt(quantitys[i]));
					od.setColor(c.getColorId());
					orderDelails.add(od);
					System.out.println(od);
				}
				String orderId = daoService.orderProduct(customer.getId(), orderDelails, cartIDs);
				System.out.println(orderId);
				return "redirect:/bill"+orderId+"";
			} catch (Exception e) {
			}
		}
		return "redirect:/cart";
	}
	public String viewOrders(Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        List<Order> orders = daoService.getOrdersByCustomer(customer.getId());
        model.addAttribute("orders", orders);
        return "orderListPage"; // Create a corresponding view page
    }
	public String viewOrderDetails(Model model, HttpSession session, @RequestParam("orderId") String orderId) {
        Customer customer = (Customer) session.getAttribute("customer");
        Order order = daoService.getOrderByIdAndCustomer(orderId, customer.getId());
        model.addAttribute("order", order);
        return "orderDetailsPage"; // Create a corresponding view page
    }
	public String cancelOrder(HttpSession session, @RequestParam("orderId") String orderId) {
        Customer customer = (Customer) session.getAttribute("customer");
        boolean canceled = daoService.cancelOrder(customer.getId(), orderId);
        if (canceled) {
            return "redirect:/orders";
        } else {
            // Handle cancellation failure
            return "errorPage"; // Create an error page
        }
    }
	public String orderDetails(Model model, @PathVariable("orderId") String orderId) {
        Order order = daoService.getOrderById(orderId);
        model.addAttribute("order", order);
        return "orderDetailsPage"; // Create a corresponding view page
    }

    // ... (existing code)
}
public String editOrder(Model model, HttpSession session, @RequestParam("orderId") String orderId) {
        Customer customer = (Customer) session.getAttribute("customer");
        Order order = daoService.getOrderByIdAndCustomer(orderId, customer.getId());
        model.addAttribute("order", order);
        return "editOrderPage"; // Create a corresponding view page for editing orders
    }
	public String updateOrder(HttpSession session, @ModelAttribute("order") Order updatedOrder) {
        Customer customer = (Customer) session.getAttribute("customer");
        boolean updated = daoService.updateOrder(customer.getId(), updatedOrder);
        if (updated) {
            return "redirect:/orders";
        } else {
            // Handle update failure
            return "errorPage"; // Create an error page
        }
		public ResponseEntity<byte[]> downloadInvoice(@RequestParam("orderId") String orderId) {
        // Retrieve the invoice data and file name
        byte[] invoiceData = daoService.getInvoiceData(orderId);
        String fileName = "invoice_" + orderId + ".pdf";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + fileName);

        return ResponseEntity
            .ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(invoiceData);
    
    }
	public String trackOrder(Model model, @RequestParam("orderId") String orderId) {
        Order order = daoService.getOrderById(orderId);
        model.addAttribute("order", order);
        return "trackOrderPage"; // Create a corresponding view page for order tracking
    }
	public String confirmDelivery(HttpSession session, @RequestParam("orderId") String orderId) {
        Customer customer = (Customer) session.getAttribute("customer");
        boolean confirmed = daoService.confirmDelivery(customer.getId(), orderId);
        if (confirmed) {
            return "redirect:/orders";
        } else {
            // Handle confirmation failure
            return "errorPage"; // Create an error page
        }
    }

    // ... (existing code)

   public String viewOrderHistory(Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        List<Order> orderHistory = daoService.getOrderHistory(customer.getId());
        model.addAttribute("orderHistory", orderHistory);
        return "orderHistoryPage"; // Create a corresponding view page for order history
    }
}
