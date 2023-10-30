package com.shopping.esoshop.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Controller;

import com.shopping.esoshop.model.Account;
import com.shopping.esoshop.model.Customer;
import com.shopping.esoshop.service.IDaoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@EnableWebSecurity
@Configuration
@Controller
public class MySecurityConfig  {

    @Autowired
    IDaoService daoService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,HttpSession session) throws  Exception{
        http.authorizeRequests(
                (authorizeRequests)->authorizeRequests.requestMatchers("/google").authenticated()
        ).oauth2Login(oauth2Customize->oauth2Customize
                .loginProcessingUrl("/login")
                .loginPage("/oauth2/authorization/google")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        DefaultOidcUser user= (DefaultOidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                        request.authenticate(response);
                        if(user!=null){
                            Account acount = daoService.getAccount(user.getEmail());
                            if(acount!=null && acount.getStatus()==1){
                                Customer customer = daoService.getCustomerByEmail(user.getEmail());
                                 customer.setPicture(user.getPicture());
                                 session.setAttribute("customer", customer);
                                 session.setAttribute("account", acount);
                                 response.sendRedirect("/home");
                            }else if(acount!=null && acount.getStatus()==0){
                                response.sendRedirect("/home");
                            }
                            else{
                               response.sendRedirect("/register2");
                            }
                        }
                        else response.sendRedirect("/home");
                    }
                })
        )
        .logout(logout -> logout
        .logoutUrl("/logout")
        .logoutSuccessHandler(logoutSuccessHandler() ))
        .csrf().disable();
        return http.build();
    }

    private LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    private class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
        @Override
        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            // Perform your logout success actions here
            HttpSession session = request.getSession();
            session.removeAttribute("account");
            session.removeAttribute("customer");
            response.sendRedirect("/home"); // Redirect to the home page after logout
        }
    }
}