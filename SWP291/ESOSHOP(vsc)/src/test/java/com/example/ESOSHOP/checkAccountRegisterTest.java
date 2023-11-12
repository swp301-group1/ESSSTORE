package com.example.ESOSHOP;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.shopping.esoshop.EsoshopApplication;
import com.shopping.esoshop.model.Account;
import com.shopping.esoshop.security.SecurityController;
import com.shopping.esoshop.service.IDaoService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = EsoshopApplication.class)
public class checkAccountRegisterTest {

    @InjectMocks
    private SecurityController securityController;

    @Mock
    private IDaoService daoService;

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
        // Input data for the test
        String email = "ngolinh09032002@gmail.com";
        String password = "fwe12@ewe";
        Account mockAccount = mockAccount();
        when(daoService.findAccountByEmail(email)).thenReturn(mockAccount);
        ResponseEntity<Boolean> response = securityController.checkAccountRegister(email, password);
        assertFalse("Email have register",response.getBody());
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
    }
    
    @Test
    void UTCID02 () {
        String email = "test@gmail.com";
        String password = "123456sf@";
        Account mockAccount = null;
        when(daoService.findAccountByEmail(email)).thenReturn(mockAccount);
        ResponseEntity<Boolean> response = securityController.checkAccountRegister(email, password);
        assertTrue(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void UTCID03 () {
        String email = "test@gmail.com";
        String password = "sdfgshfgjsd";
        Account mockAccount = null;
        when(daoService.findAccountByEmail(email)).thenReturn(mockAccount);
        ResponseEntity<Boolean> response = securityController.checkAccountRegister(email, password);
        assertFalse("Password false",response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

  
    @Test
    void UTCID04 () {
        String email = "test@gmail.com";
        String password = "1236178231";
        Account mockAccount = null;
        when(daoService.findAccountByEmail(email)).thenReturn(mockAccount);
        ResponseEntity<Boolean> response = securityController.checkAccountRegister(email, password);
        assertFalse(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    
    @Test 
    void UTCID05 () {
        String email = "test@gmail.com";
        String password = "qhw123122";
        Account mockAccount = null;
        when(daoService.findAccountByEmail(email)).thenReturn(mockAccount);
        ResponseEntity<Boolean> response = securityController.checkAccountRegister(email, password);
        assertFalse(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    @Test 
    void UTCID06 () {
        String email = "test@gmail.com";
        String password = "qhw1@2";
        Account mockAccount = null;
        when(daoService.findAccountByEmail(email)).thenReturn(mockAccount);
        ResponseEntity<Boolean> response = securityController.checkAccountRegister(email, password);
        assertFalse(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    
 
  
}


