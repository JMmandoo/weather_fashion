package com.min.fashion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class FashionApplication {

	public static void main(String[] args) {

		SpringApplication.run(FashionApplication.class, args);
	}

}
