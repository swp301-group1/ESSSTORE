package com.example.ESOSHOP;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.shopping.esoshop.service2.IDaoService;
import com.shopping.esoshop.EsoshopApplication;

@SpringBootTest(classes = EsoshopApplication.class)
class EsoshopApplicationTests {

	@Autowired
	IDaoService daoService;

	@Test
	void contextLoads() {
		assertNotNull(daoService); // Check if the service is injected correctly
	}
}
