package com.diegomarques.testepratico.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootApplicationTestePratico {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplicationTestePratico.class, args);
	}
}
