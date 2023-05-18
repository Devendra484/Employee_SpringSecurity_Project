package com.springboot;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication

public class EmployeeApplication {

	public static void main(String[] args) {
		System.out.println("App is working");
		SpringApplication.run(EmployeeApplication.class, args);
	}

}
