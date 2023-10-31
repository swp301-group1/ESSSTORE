package com.shopping.esoshop.controller.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.SecurityFilterChain;
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
                authorizeRequests -> authorizeRequests.requestMatchers("/google").authenticated().anyRequest().permitAll()
        ).oauth2Login(oauth2Customize -> oauth2Customize
                        .loginProcessingUrl("/login")
                        .loginPage("/oauth2/authorization/google")
                        .successHandler(new AuthenticationSuccessHandler() {
                            @Override
                            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                                DefaultOidcUser user = (DefaultOidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                                request.authenticate(response);
                                if (user != null) {
                                    Account acount = daoService.checkcheckRole(user.getEmail());
                                    if(acount.getRole()==3){
                                        session.setAttribute("admin", acount);
                                        response.sendRedirect("/admin/dashboard");
                                    }
                                    else if ( acount.getRole()==1) {
                                        if( acount.getStatus() == 1){
                                            Customer customer = daoService.getCustomerByEmail(user.getEmail());
                                            customer.setPicture(user.getPicture());
                                            session.setAttribute("customer", customer);
                                            session.setAttribute("account", acount);
                                            response.sendRedirect("/home");
                                        }else{
                                            response.sendRedirect("/home");
                                        }
                                    } 
                                    else if(acount.getRole()==0){
                                        response.sendRedirect("/register2");
                                    }
                                    else if(acount.getRole()==2){
                                        session.setAttribute("staff", daoService.getStaffByEmail(acount.getEmail()));
                                        response.sendRedirect("/staff/orders");
                                    }
                                }
                                else response.sendRedirect("/home");
                            }
                        })
        )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler(logoutSuccessHandler()))
                .csrf(csrf -> csrf.disable());
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
            response.sendRedirect("/home");
        }
    }
}