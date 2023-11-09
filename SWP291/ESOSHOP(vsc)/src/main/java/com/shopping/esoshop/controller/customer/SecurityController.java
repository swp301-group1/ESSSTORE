package com.shopping.esoshop.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.shopping.esoshop.model.Account;
import com.shopping.esoshop.model.Customer;
import com.shopping.esoshop.model.Mess;
// import com.shopping.esoshop.model.User;
import com.shopping.esoshop.service.IDaoService;
import com.shopping.esoshop.service.MailService;
import com.shopping.esoshop.utils.OtpGenerator;

import jakarta.servlet.http.HttpSession;

@RestController
public class SecurityController {


	public final static String Account = "account";
	public final static String Customer = "customer";

	@Autowired
	private MailService mailService;

	@Autowired
	private IDaoService dao;

	@Autowired
	private OtpGenerator otpGenerator;
	// for register

	@GetMapping("register/checkaccount/{email}")
	public ResponseEntity<Boolean> registerCheckAccount(
			@PathVariable("email") String email) {
		Account account = dao.getAccount(email);
		if (account == null) {
			return ResponseEntity.ok().body(true);
		}
		return ResponseEntity.ok().body(false);
	}

	@PostMapping("/register")
	public ResponseEntity<Boolean> doRegister(HttpSession session, Model model,
			@RequestParam(name = "email", defaultValue = "", required = false) String email,
			@RequestParam(name = "name", defaultValue = "", required = false) String name,
			@RequestParam(name = "phone", defaultValue = "", required = false) String phone,
			@RequestParam(name = "address", defaultValue = "", required = false) String address,
			@RequestParam(name = "password", defaultValue = "", required = false) String password) {
		if (email.isEmpty() || name.isEmpty() || phone.isEmpty() || address.isEmpty() || password.isEmpty()) {
			return ResponseEntity.ok().body(false);
		} else {
			Account account = new Account(email, password, 1, 1);
			Customer customer = new Customer(name, address, phone, email);
			dao.createAccount(account, customer);

			return ResponseEntity.ok().body(true);
		}
	}

	@PostMapping("register/veryfyemail")
	public ResponseEntity<String> veryfyemail(
			@RequestParam(name = "email", required = false, defaultValue = "") String email) {
		String otp = otpGenerator.createCapcha();
	    boolean mail = 	mailService.sendEmail(email, "Login by OTP", "This is ypur OTP: " + otp);
		if(mail){
			return ResponseEntity.ok().body(otp);
		}
		else{
	     	return  ResponseEntity.ok().body("null");
		}
	}



	@PostMapping("login/checkaccount")
	public ResponseEntity<Mess> sendOTP(
			@RequestParam(name = "email", required = false, defaultValue = "") String email) {
		Account account = dao.getAccount(email);
		Mess mess = new Mess();
		if (account != null) {
			String otp = otpGenerator.createCapcha();
			boolean mail = mailService.sendEmail(email, "Login by OTP", "This is ypur OTP: " + otp);
			if(mail){
				mess.setEmail(email);
			    mess.setOTP(otp);
			}else{
				mess.getMess().add("Email not found!");
			}
		} else {
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
			mess.setEmail("null");
		}
		return ResponseEntity.ok().body(mess);
	}

	public void loginSession(HttpSession session, String email) {
		session.setAttribute(Account, dao.getAccount(email));
		session.setAttribute(Customer, dao.getCustomerByEmail(email));
	}
}
