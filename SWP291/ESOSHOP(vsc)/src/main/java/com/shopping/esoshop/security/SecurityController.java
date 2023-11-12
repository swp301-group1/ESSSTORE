package com.shopping.esoshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.shopping.esoshop.model.*;
import com.shopping.esoshop.service.*;
import com.shopping.esoshop.utils.OtpGenerator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
public class SecurityController {
	
	@Autowired
    TwilioSmsService twilioSmsService;

    @Autowired 
    OtpGenerator otpgenerator;

	@Autowired
	private IDaoService daoService;

	@PostMapping("/api/user/checkaccount")
	public ResponseEntity<Boolean> registerCheckAccount(
		@RequestParam(name = "email", defaultValue = "", required = false) String email,
		@RequestParam(name = "phone", defaultValue = "", required = false) String phone,
		@RequestParam(name = "method", required = false, defaultValue = "") Integer method) {
			Account account = null ;
			switch (method) {
				case 1:
					account= daoService.findAccountByEmail(email);
					break;
				case 2:
					account= daoService.findAccountByPhone(phone);
					break;
				default:
					break;
			}
			if(account!=null) return ResponseEntity.badRequest().body(false);
		return ResponseEntity.ok().body(true);
	}

	@PostMapping("/api/user/resiger")
	public ResponseEntity<Boolean> doRegister(HttpSession session, Model model,
			@RequestParam(name = "email", defaultValue = "", required = false) String email,
			@RequestParam(name = "phone", defaultValue = "", required = false) String phone,
			@RequestParam(name = "name", defaultValue = "", required = false) String name,
			@RequestParam(name = "address", defaultValue = "", required = false) String address,
			@RequestParam(name = "password", defaultValue = "", required = false) String password,
			@RequestParam(name = "role", required = false, defaultValue = "1") Integer role) {
				Account account = null;
		if (daoService.checkAccount(email, phone) != null) {
			switch (role) {
				case 1:
				    account  = new Account(0, email, phone, password, role, 1, name, address, password);
					break;
				case 2:
				    account  = new Account(0, email, phone, password, role, 1, name, address, password);
					break;
				default:
					break;
			}
			Boolean result = daoService.createAccount(account);
			if(result!=null){
				return ResponseEntity.ok().body(true);
			}return ResponseEntity.ok().body(false);
		}
		return ResponseEntity.ok().body(false);
	}

	// for login

	@PostMapping("/api/user/login")
	public ResponseEntity<Boolean> loginByPassword(Model model, HttpSession session,
			@RequestParam(name = "email_phone", required = false, defaultValue = "") String email_phone,
			@RequestParam(name = "password", required = false, defaultValue = "") String password,
			@RequestParam(name = "role", required = false, defaultValue = "") Integer role) {
		Account acount = new Account();
		acount = daoService.findAccountByEmail(email_phone);
		if(acount==null) acount = daoService.findAccountByPhone(email_phone);
		if (acount != null) {
			if(acount.getRole()>=role && acount.getStatus()==1){
				if(acount.getPassword().equals(password)){
				session.setAttribute("account", acount);
				return ResponseEntity.ok().body(true);
			    }
			}else return ResponseEntity.ok().body(false);
		}
		return ResponseEntity.ok().body(false);
	}


	@GetMapping("/api/user/logout")
	public ResponseEntity<Boolean> loginout(HttpSession session,HttpServletRequest request, HttpServletResponse response){
		session.setAttribute("account", null);
			    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.setInvalidateHttpSession(true);
        logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());
		SecurityContextHolder.createEmptyContext();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
            SecurityContextHolder.clearContext();
        }
		return ResponseEntity.ok().body(true);
	}

    @PostMapping("/api/send-otp")
    public ResponseEntity<String> sendOTP(
        @RequestParam("pcode")String pcode,
        @RequestParam("phone")String phone){
        String p = pcode.trim()+phone.trim();
        String sms = otpgenerator.createCapcha();
        boolean resutl = twilioSmsService.sendSms("+"+p, "Your sms "+sms);
        if(resutl){
            return ResponseEntity.status(HttpStatus.OK).body(sms);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Can not send sms to "+p);
   }


    @PostMapping("/api/checkaccount")
    public ResponseEntity<Boolean> checkAccountRegister(
        @RequestParam("email")String email,
        @RequestParam("password")String password){
			Account account = daoService.findAccountByEmail(email);
			if(account!=null) return ResponseEntity.status(HttpStatus.FOUND).body(false);
			else if(account==null){
				if(isPasswordValid(password)){
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(true);
				}
				else if(!isPasswordValid(password)){
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
				}
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(true);
   }

    @GetMapping("/info")
    public String getinfor(OAuth2AuthenticationToken token){
		return token.getName()+token.getClass();
    }

	public  boolean isPasswordValid(String password) {
        if (password.length() < 8 || password.length() > 50) {
            return false;
        }
        if (!containsDigit(password)) {
            return false;
        }
        if (!containsSpecialCharacter(password)) {
            return false;
        }
        else return true;
    }

    private static boolean containsDigit(String password) {
        return password.matches(".*\\d.*");
    }

    private static boolean containsSpecialCharacter(String password) {
        Pattern specialCharPattern = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]");
        Matcher matcher = specialCharPattern.matcher(password);
        return matcher.find();
    }
}
