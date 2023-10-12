package com.shopping.esoshop.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.esoshop.model.Account;
import com.shopping.esoshop.model.Customer;
import com.shopping.esoshop.model.Mess;
// import com.shopping.esoshop.model.User;
import com.shopping.esoshop.service.DaoService;
import com.shopping.esoshop.service.MailService;
import com.shopping.esoshop.utils.Capcha;

import jakarta.servlet.http.HttpSession;

@RestController
public class SecurityController {

	public final static String Account = "account";
	public final static String Customer = "customer";

	private String otp = "";
	private Account newAccount = null;
	private Customer newCustomer = null;

	@Autowired
	private MailService mailService;

	@Autowired
	private DaoService dao;

	// for register

	@RequestMapping("/register_verify")
	public String doRegister(HttpSession session, Model model,
			@RequestParam(name = "email", defaultValue = "", required = false) String email,
			@RequestParam(name = "name", defaultValue = "", required = false) String name,
			@RequestParam(name = "phone", defaultValue = "", required = false) String phone,
			@RequestParam(name = "address", defaultValue = "", required = false) String address,
			@RequestParam(name = "password", defaultValue = "", required = false) String password) {
		Account account = dao.getAccount(email);
		if (account == null) {
			try {
				otp = Capcha.createCapcha();
				newAccount = new Account(email, password, 1, 1);
				newCustomer = new Customer(name, address, phone, email);
				session.setAttribute("newCustomer", newCustomer);
				// send otp
				mailService.sendEmail(newAccount.getEmail(), "Verify account", "Your OTP: " + otp);
				return "register_verify";
			} catch (Exception e) {
				e.printStackTrace();
				return "register";
			}
		} else {
			model.addAttribute("mess", email + " was registed");
			return "register";
		}
	}

	@PostMapping("/register_verify_OTP")
	public String verifyAccountRegister(HttpSession session,
			@RequestParam(name = "OTP", required = false, defaultValue = "") String OTP) {
		if (OTP.equals(otp)) {
			// Account newAccount = (Account) session.getAttribute("newAccount");
			// Customer newCustomer = (Customer) session.getAttribute("newCustomer");
			dao.createAccount(newAccount, newCustomer);
			// after register login for customer
			session.setAttribute(Account, newAccount);
			session.setAttribute(Customer, newCustomer);
			return "redirect:/index";
		} else {
			return "register_verify";
		}
	}

	// for login

	@PostMapping("dologin")
	public ResponseEntity<Mess> loginByPassword(Model model, HttpSession session,
			@RequestParam(name = "email", required = false, defaultValue = "") String email,
			@RequestParam(name = "password", required = false, defaultValue = "") String password) {
		Account account = dao.checkLogin(email, password, 1);
		Mess mess = new Mess();
		if (account != null) {
			session.setAttribute(Account, account);
			session.setAttribute(Customer, dao.getCustomerByEmail(account.getEmail()));
			mess.setLoginsucces(true);
			mess.setEmail(account.getEmail());
		} else {
			mess.setLoginsucces(false);
		}
		return ResponseEntity.ok().body(mess);
	}

	@PostMapping("login/checkaccount")
	public ResponseEntity<Mess> sendOTP(
			@RequestParam(name = "email", required = false, defaultValue = "") String email) {
		Account account = dao.getAccount(email);
		Mess mess = new Mess();
		if (account != null) {
			String otp = Capcha.createCapcha();
			mailService.sendEmail(email, "Login by OTP", "This is ypur OTP: " + otp);
			mess.setEmail(email);
			mess.setOTP(otp);
		}else{
			mess.setEmail("null");
		}
		return ResponseEntity.ok().body(mess);
	}

	@PostMapping("dologin/otp")
	public ResponseEntity<Mess> loginByOTP(HttpSession session,
			@RequestParam(name = "email", required = false, defaultValue = "") String email) {
		Account account = dao.getAccount(email);
		Mess mess = new Mess();
		if (account != null) {
			session.setAttribute(Account, account);
			session.setAttribute(Customer, dao.getCustomerByEmail(account.getEmail()));
			mess.setLoginsucces(true);
			mess.setEmail(account.getEmail());
		} else {
			mess.setEmail("null");;
		}
		return ResponseEntity.ok().body(mess);
	}

	public void loginSession(HttpSession session, String email) {
		session.setAttribute(Account, dao.getAccount(email));
		session.setAttribute(Customer, dao.getCustomerByEmail(email));
	}
}
