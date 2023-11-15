package com.shopping.esoshop.controller.admin;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import com.shopping.esoshop.model.*;
import com.shopping.esoshop.service.IDaoService;
import com.shopping.esoshop.service.MailService;
import com.shopping.esoshop.utils.OtpGenerator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.charset.StandardCharsets;
import jakarta.servlet.http.HttpSession;

@RestController
public class AdminController {

    @Autowired
    private IDaoService daoServicel;
    @Autowired
    private OtpGenerator generator;
    @Autowired 
    private MailService mailService;

    @GetMapping("admin/dashboard/top-sale-product")
    public ResponseEntity<List<TopSaleProduct>> getTopSaleProduct() {
        return ResponseEntity.ok().body(daoServicel.getTopSaleProducts());
    }

    @GetMapping("admin/dashboard/top-feedback-product")
    public ResponseEntity<List<TopFeedbackProduct>> getTopFeedbackProduct() {
        return ResponseEntity.ok().body(daoServicel.topFeedbackProducts());
    }

    @PostMapping("/admin/dashboard/revenue")
    public ResponseEntity<List<Revenue>> getRevenue(
            @RequestParam("from") Date from,
            @RequestParam("to") Date to) {
        try {
            if (from.before(to)) {
                return ResponseEntity.ok().body(daoServicel.getRevenues(from, to));
            }
        } catch (Exception e) {
            System.out.println("Date input false fomat");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        System.out.println("Date from must befor or equal date to");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @GetMapping("/api/admin/account/{aid}")
    public ResponseEntity<Account> getInforCustomer(
            @PathVariable("aid") Integer aid) {
        return ResponseEntity.ok().body((Account) daoServicel.findAccount(aid));
    }

    @GetMapping("/api/admin/accounts/{role}")
    public ResponseEntity<List<Account>> getAllAccountByRole(
            @PathVariable("role") Integer role) {
        return ResponseEntity.ok().body(daoServicel.getAllAccount(role));
    }

    @PostMapping("/api/admin/user/ban_unban")
    public ResponseEntity<Boolean> setStatusAccount(HttpSession session,
            @RequestParam("aid") Integer aid,
            @RequestParam("status") Integer status) {
        try {
            if (status == 1 || status == 0) {
                boolean resutl = daoServicel.setStatusAccount(aid, status);
                return ResponseEntity.ok().body(resutl);
            } else {
                System.out.println("input status must 0||1");
                return ResponseEntity.ok().body(false);
            }
        } catch (Exception e) {
            System.out.println("update false");
            return ResponseEntity.ok().body(false);
        }
    }

    @GetMapping("/api/checkrole/{email}")
    public ResponseEntity<Account> checkRole(@PathVariable("email") String email) {
        return ResponseEntity.ok().body(daoServicel.findAccountByEmail(email));
    }

    @PostMapping("/api/admin/account/update")
    public ResponseEntity<Boolean> updateStaff(@ModelAttribute Account staff) {
        staff.setRole(2);
        staff.setStatus(1);
        if(isPasswordValid(staff.getPassword())&& !staff.getName().trim().isEmpty() && !staff.getAddress().trim().isEmpty()){
            return ResponseEntity.ok().body(daoServicel.updateAccount(staff));
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
    }

    public boolean isPasswordValid(String password) {
        if (password.length() < 8 || password.length() > 50) {
            return false;
        }
        if (!containsDigit(password)) {
            return false;
        }
        if (!containsSpecialCharacter(password)) {
            return false;
        }
        return true;
    }

    private static boolean containsDigit(String password) {
        return password.matches(".*\\d.*");
    }

    private static boolean containsSpecialCharacter(String password) {
        Pattern specialCharPattern = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]");
        Matcher matcher = specialCharPattern.matcher(password);
        return matcher.find();
    }

    @PostMapping("/api/admin/account/add")
    public ResponseEntity<Boolean> addStaff(@ModelAttribute Account account) {
        if(account.getPassword().isEmpty()){
            String pass = generator.generatePassword();
            account.setPassword(pass);
        }
        mailService.sendEmail(account.getEmail(),"Password to login" , account.getPassword());
        return ResponseEntity.ok().body(daoServicel.createAccount(account));
    }

    @PostMapping("/api/admin/account/delete")
    public ResponseEntity<Boolean> deleteAccount(
            @RequestParam("aid") Integer aid) {
        return ResponseEntity.ok().body(daoServicel.deleteAccount(aid));
    }

    public static String encodeURL(String url) {
        return UriUtils.encode(url, StandardCharsets.UTF_8);
    }

    public static String decodeURL(String encodedUrl) {
        return UriUtils.decode(encodedUrl, StandardCharsets.UTF_8);
    }

    @GetMapping("/api/code/url/{c}")
    public String getCodeUrl(@PathVariable("c") Integer c) {

        String originalURL = "/staff/login";
        String encodedURL = encodeURL(originalURL);
        System.out.println("Encoded URL: " + encodedURL);
        String decodedURL = decodeURL(encodedURL);
        System.out.println("Decoded URL: " + decodedURL);
        if(c==1)return encodedURL;
        else return decodedURL;
    }
    @PostMapping("/api/mail/send")
    public ResponseEntity<Boolean> deleteAccount(
        @RequestParam("email")String email ) {
        return ResponseEntity.ok().body(mailService.sendEmail(email, "Password", "123456"));
    }

}
