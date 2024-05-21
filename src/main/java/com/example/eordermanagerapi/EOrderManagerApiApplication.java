package com.example.eordermanagerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EOrderManagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EOrderManagerApiApplication.class, args);
	}

}
