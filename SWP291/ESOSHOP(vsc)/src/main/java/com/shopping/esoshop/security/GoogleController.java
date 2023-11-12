package com.shopping.esoshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.shopping.esoshop.model.Account;
import com.shopping.esoshop.service.DaoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GoogleController {

    @Autowired
    DaoService daoService;


    // @GetMapping("/register/google")
    // public String confirmRegister(Model model){
    //     model.addAttribute("", model)
    //     return "redirect:/register2";
    // }

    @GetMapping("/login/google/{role}")
    public String googleLogin(Authentication authentication, Model model, HttpSession session,
            @PathVariable("role") Integer role) {
        if (authentication != null && authentication.isAuthenticated()) {
            DefaultOidcUser user = (DefaultOidcUser) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            Account account = daoService.findAccountByEmail(user.getEmail());
            if (account != null) {
                System.out.println(role + "---" + account.getEmail() + "--" + account.getRole());
                if (account.getRole() == role && account.getStatus() == 1) {
                    session.setAttribute("account", account);
                } else {
                    model.addAttribute("role", "The account does not have access");
                    session.setAttribute("account", null);
                }
                switch (role) {
                    case 1:
                        return "redirect:/home";
                    case 2:
                        return "redirect:/staff/login";
                    case 3:
                        return "redirect:/admin/dashboard";
                }
            }else{
                if(role==1){
                   return "redirect:/register";
                }if(role==2){
                    return "redirect:/staff/login";
                }
            }
        }
        return "redirect:/oauth2/authorization/google";
    }

    @GetMapping("/login/google/callback")
    public String googleLoginCallback(HttpSession session, OAuth2AuthenticationToken token) {
        String pathRequest = "";
        DefaultOidcUser user = (DefaultOidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (token.isAuthenticated()) {
            Account account = daoService.findAccountByEmail(user.getEmail());
            if (account == null) {
                pathRequest = "redirect:/login/google/1";
            } else {
                pathRequest = "redirect:/login/google/" + account.getRole();
            }
        }
        return pathRequest;
    }
}