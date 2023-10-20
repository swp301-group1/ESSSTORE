package com.shopping.esoshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.shopping.esoshop.service.DaoService;

@SpringBootApplication
@EnableScheduling
public class EsoshopApplication  {

    @Autowired
	DaoService daoService;
	public static void main(String[] args) {
		SpringApplication.run(EsoshopApplication.class, args);
	}
}
