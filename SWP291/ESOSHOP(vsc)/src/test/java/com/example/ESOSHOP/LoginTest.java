package com.example.ESOSHOP;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.shopping.esoshop.EsoshopApplication;
import com.shopping.esoshop.model.Account;
import com.shopping.esoshop.security.SecurityController;
import com.shopping.esoshop.service.IDaoService;

import jakarta.servlet.http.HttpSession;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = EsoshopApplication.class)
public class LoginTest {

    @InjectMocks
    private SecurityController securityController;

    @Mock
    private IDaoService daoService;
    
    @Mock
    HttpSession session = mock(HttpSession.class);

    private Account mockAccount() {
        Account account = new Account();
        account.setAid(1);
        account.setEmail("ngolinh09032002@gmail.com");
        account.setPassword("123456");
        account.setPhonenumber("0974841508");
        account.setRole(3);
        account.setStatus(1);
        account.setAddress("sdffwef");
        account.setName("admin");
        account.setPicture(null);
        return account;
    }
    
    @Test
    void UTCID01() {
        String email = "ngolinh09032002@gmail.com";
        String password = "123456";
        Account mockAccount = mockAccount();
        when(daoService.findAccountByEmail(email)).thenReturn(mockAccount);
        ResponseEntity<Boolean> response = securityController.loginByPassword(null, session, email, password, 3);
        assertTrue(response.getBody(), () -> "Login success");
    }
    
    
    
    @Test
    void UTCID02 () {
        String email = "ngolinh09032002@gmail.com";
        String password = "123";
        Account mockAccount = mockAccount();
        when(daoService.findAccountByEmail(email)).thenReturn(mockAccount);
        ResponseEntity<Boolean> response = securityController.loginByPassword(null, session, email, password, 3);
        assertFalse("Login false",response.getBody());
    }

    @Test
    void UTCID03 () {
        String email = "ngolinh09032002@gmail.com";
        String password = "";
        Account mockAccount = mockAccount();
        when(daoService.findAccountByEmail(email)).thenReturn(mockAccount);
        ResponseEntity<Boolean> response = securityController.loginByPassword(null, session, email, password, 3);
        assertFalse("Login false",response.getBody());
    }

  
    @Test
    void UTCID04 () {
        String email = "ngolinhgmail.com";
        String password = "123456";
        Account mockAccount = mockAccount();
        when(daoService.findAccountByEmail(email)).thenReturn(mockAccount);
        ResponseEntity<Boolean> response = securityController.loginByPassword(null, session, email, password, 3);
        assertFalse("Login false",!response.getBody());
    }
    
    @Test 
    void UTCID05 () {
        String email = "";
        String password = "123456";
        Account mockAccount = mockAccount();
        when(daoService.findAccountByEmail(email)).thenReturn(mockAccount);
        ResponseEntity<Boolean> response = securityController.loginByPassword(null, session, email, password, 3);
        assertFalse("Login false",!response.getBody());
    }
}


