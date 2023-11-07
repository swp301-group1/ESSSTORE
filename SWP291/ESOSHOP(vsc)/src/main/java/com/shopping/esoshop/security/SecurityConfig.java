package com.shopping.esoshop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http ) throws Exception {

        http.authorizeRequests(
                authorizeRequests -> authorizeRequests
                        .requestMatchers("/login/google").authenticated()
                        .anyRequest().permitAll())
                .oauth2Login(oauth2Customize -> oauth2Customize
                        .loginProcessingUrl("/login")
                        .loginPage("/oauth2/authorization/google")
                        .successHandler(new AuthenticationSuccessHandler() {
                            @Override
                            public void onAuthenticationSuccess(HttpServletRequest request,
                                    HttpServletResponse response, Authentication authentication)
                                    throws IOException, ServletException {
                                        authentication.setAuthenticated(true);
                                        request.authenticate(response);
                                response.sendRedirect("/login/google/callback");
                            }
                        }))
                .logout(logout -> logout
                        .logoutUrl("/api/user/logout")
                        .logoutSuccessHandler(logoutSuccessHandler()))
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    private LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    private class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
        @Override
        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                Authentication authentication)
                throws IOException, ServletException {
            HttpSession session = request.getSession();
            session.removeAttribute("account");
        }
    }
}