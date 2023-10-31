package com.shopping.esoshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class EsoshopApplication  {


	public static void main(String[] args) {
		SpringApplication.run(EsoshopApplication.class, args);
	}
}
