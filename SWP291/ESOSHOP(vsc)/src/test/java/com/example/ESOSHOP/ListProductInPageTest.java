package com.example.ESOSHOP;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shopping.esoshop.EsoshopApplication;
import com.shopping.esoshop.model.Product;
import com.shopping.esoshop.service.IDaoService;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = EsoshopApplication.class)
public class ListProductInPageTest {

    
    @Autowired
    private IDaoService daoService;

    @Test
    void UTCID01() {
        int pageIndex = 0;
        int pageSize = 1;
        List <Product> expectedProducts =  daoService.getAllProductinPage(pageIndex, pageSize);
        assertTrue(expectedProducts==null,() -> "List is null");
    }
    
    @Test
    void UTCID02() {
        int pageIndex = 1;
        int pageSize = 1;
        List <Product> expectedProducts =  daoService.getAllProductinPage(pageIndex, pageSize);
        assertTrue(!expectedProducts.isEmpty(),() -> "List not empty");
    }
    
    @Test
    void UTCID03() {
        int pageIndex =8 ;
        int pageSize = 1;
        List <Product> expectedProducts =  daoService.getAllProductinPage(pageIndex, pageSize);
         assertTrue(!expectedProducts.isEmpty(),() -> "List not empty");
    }
    @Test
    void UTCID04() {
        int pageIndex = 1 ;
        int pageSize = 3;
        List <Product> expectedProducts =  daoService.getAllProductinPage(pageIndex, pageSize);
         assertTrue(!expectedProducts.isEmpty(),() -> "List not empty");
    }

    @Test
    void UTCID05() {
        int pageIndex =2;
        int pageSize = 3;
        List <Product> expectedProducts =  daoService.getAllProductinPage(pageIndex, pageSize);
         assertTrue(!expectedProducts.isEmpty(),() -> "List not empty");
    }
    @Test
    void UTCID06() {
        int pageIndex =3;
        int pageSize = 3;
        List <Product> expectedProducts =  daoService.getAllProductinPage(pageIndex, pageSize);
         assertTrue(!expectedProducts.isEmpty(),() -> "List not empty");
    }
   
    @Test
    void UTCID07() {
        int pageIndex =1;
        int pageSize = 10;
        List <Product> expectedProducts =  daoService.getAllProductinPage(pageIndex, pageSize);
         assertTrue(!expectedProducts.isEmpty(),() -> "List not empty");
    }
    @Test
    void UTCID08() {
        int pageIndex =1;
        int pageSize = 8;
        List <Product> expectedProducts =  daoService.getAllProductinPage(pageIndex, pageSize);
         assertTrue(!expectedProducts.isEmpty(),() -> "List not empty");
    }
    @Test
    void UTCID09() {
        int pageIndex =1;
        int pageSize = 10;
        List <Product> expectedProducts =  daoService.getAllProductinPage(pageIndex, pageSize);
         assertTrue(!expectedProducts.isEmpty(),() -> "List not empty");
    }
   
}


