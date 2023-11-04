package com.shopping.esoshop.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shopping.esoshop.model.Customer;
import com.shopping.esoshop.service.IDaoService;
import com.twilio.rest.api.v2010.account.call.Payment;

import jakarta.servlet.http.HttpSession;

@Controller
public class PayController {

    @Autowired 
    private IDaoService daoService;

    @GetMapping(value = "/bill{orderId}")
    public String showBill(Model model, HttpSession session,
        @PathVariable(value = "orderId" )String orderId) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
            model.addAttribute("bill", daoService.getBillOfCustomer(customer,orderId));
        } else {
            return "redirect:/login";
        }
        return "bill";
    }
    
    @GetMapping(value = "/pay{orderId}")
    public String pay(Model model,HttpSession session,
        @PathVariable(value = "orderId")String orderId){
        Customer customer = (Customer) session.getAttribute("customer");
            if(customer!=null){
                daoService.payBill(daoService.getBillOfCustomer(customer, orderId));
            }
        return "redirect:/bill"+orderId;
    }

   ////////////////////////////////////////////////
   ///////////////////////////////////////////////////
   ////////////////////////////////////////////////////
   ////////////////////////////////////////////////////
   @GetMapping(value = "/confirmPayment{orderId}")
public String confirmPayment(Model model, HttpSession session, @PathVariable(value = "orderId") String orderId) {
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer != null) {
        // Thực hiện xác nhận thanh toán ở đây (ví dụ: kiểm tra trạng thái thanh toán).
        // Nếu thanh toán thành công, bạn có thể cập nhật hóa đơn hoặc thông báo thành công.
        // Nếu thanh toán không thành công, bạn có thể thông báo lỗi hoặc thực hiện xử lý khác.
    } else {
        return "redirect:/login";
    }
    return "paymentConfirmation";
}
 @GetMapping(value = "/paymentHistory")
public String viewPaymentHistory(Model model, HttpSession session) {
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer != null) {
        // Lấy lịch sử thanh toán của khách hàng từ daoService.
        List<Payment> paymentHistory = daoService.getPaymentHistory(customer);
        model.addAttribute("paymentHistory", paymentHistory);
    } else {
        return "redirect:/login";
    }
    return "paymentHistory";
}

}
