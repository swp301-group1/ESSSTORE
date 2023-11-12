package com.example.ESOSHOP;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.shopping.esoshop.EsoshopApplication;
import com.shopping.esoshop.controller.admin.AdminController;
import com.shopping.esoshop.model.Revenue;
import com.shopping.esoshop.service.IDaoService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = EsoshopApplication.class)
public class RevenusTest {

    @InjectMocks
    AdminController controller = new AdminController();

    @Mock
    private IDaoService daoService;

    @Test
    void UTCID01() {
        // from after to retutn null
        Date from = Date.valueOf("2023-03-09");
        Date to = Date.valueOf("2023-01-08");
        List<Revenue> mockRevenus = new ArrayList<>();
        when(daoService.getRevenues(from, to)).thenReturn(mockRevenus);
        ResponseEntity<List<Revenue>> response = controller.getRevenue(from, to);
        assertNull(response.getBody());
    }

    @Test
    void UTCID02() {
        // from before to retutn not null
        Date from = Date.valueOf("2023-03-09");
        Date to = Date.valueOf("2023-10-02");
        List<Revenue> mockRevenus = new ArrayList<>();
        when(daoService.getRevenues(from, to)).thenReturn(mockRevenus);
        ResponseEntity<List<Revenue>> response = controller.getRevenue(from, to);
        assertNotNull(response.getBody());
    }

    @Test
    void UTCID03() {
        // input date null
        Date from = null;
        Date to = Date.valueOf("2023-10-02");
        List<Revenue> mockRevenus = new ArrayList<>();
        when(daoService.getRevenues(from, to)).thenReturn(mockRevenus);
        ResponseEntity<List<Revenue>> response = controller.getRevenue(from, to);
        assertNull(response.getBody());
    }

    @Test
    void UTCID04() {
        ResponseEntity<List<Revenue>> response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        try {
            Date from = Date.valueOf("2023-03-99");
            Date to = Date.valueOf("2023-10-02");
            List<Revenue> mockRevenues = new ArrayList<>();
            when(daoService.getRevenues(from, to)).thenReturn(mockRevenues);
            response = controller.getRevenue(from, to);
            assertNull(response.getBody());
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
            verify(daoService).getRevenues(from, to);
        } catch (IllegalArgumentException e) {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        }
    }

    @Test
    void UTCID05() {
        // from equal to
        Date from = Date.valueOf("2023-10-04");
        Date to = Date.valueOf("2023-10-04");
        List<Revenue> mockRevenues = new ArrayList<>();
        when(daoService.getRevenues(from, to)).thenReturn(mockRevenues);
        ResponseEntity<List<Revenue>> response = controller.getRevenue(from, to);
        assertNull(response.getBody());
    }
}

