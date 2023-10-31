package com.shopping.esoshop.controller.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        DefaultOidcUser user = (DefaultOidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "Hello "+ user.getIdToken()+"----" + user.getEmail() + "</br>" + user.getPicture() + "</br>" + user.getFullName()+"--"+user.getPhoneNumber();
    }
}
