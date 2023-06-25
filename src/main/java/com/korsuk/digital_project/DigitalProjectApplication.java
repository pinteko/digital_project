package com.korsuk.digital_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
public class DigitalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalProjectApplication.class, args);
	}

}
