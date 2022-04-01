package com.jyeory.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SeparateDbConnectionApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SeparateDbConnectionApplication.class, args);
	}

}
