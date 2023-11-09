package com.example.ESOSHOP;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.shopping.esoshop.model_ef.Account;
import com.shopping.esoshop.service2.IDaoService;

@SpringBootTest
public class LoginTest {
    
    @Autowired
    private IDaoService daoService;

    @Test
    void UTCID01(){
        String email = "ngolinh09032002@gmail.com";
        Account expectedAccount = new Account(1, email, "0974841508", "09032002", 3, 1, "admin", "123fpr", null);
        
        // Mock IDaoService và thiết lập behavior
        when(daoService.findAccountByEmail(email)).thenReturn(expectedAccount);
        
        Account accountservice = daoService.findAccountByEmail(email);
        assertNotNull(accountservice);
        assertEquals(expectedAccount, accountservice);
    }

    @Test
    void UTCID02(){
        String email = "ngolinh09032002@gmail.com";
        
        // Mock IDaoService và thiết lập behavior
        when(daoService.findAccountByEmail(email)).thenReturn(null);
        
        Account accountservice = daoService.findAccountByEmail(email);
        assertNull(accountservice);
    }
}
