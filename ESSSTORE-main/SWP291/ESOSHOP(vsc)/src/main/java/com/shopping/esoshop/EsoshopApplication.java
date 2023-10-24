package com.shopping.esoshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.shopping.esoshop.service.IDaoService;

@SpringBootApplication
@EnableScheduling
public class EsoshopApplication implements CommandLineRunner  {

    @Autowired
	IDaoService daoService;
	public static void main(String[] args) {
		SpringApplication.run(EsoshopApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println(daoService.getAllProduct());
	}
}
