package com.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SalesApplication {

	public static void main(String[] args) {

		SpringApplication.run(SalesApplication.class, args);
		System.out.println("Hello folks");
		System.out.println("whats up!");
	}

}
