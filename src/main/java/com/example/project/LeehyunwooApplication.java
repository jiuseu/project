package com.example.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LeehyunwooApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeehyunwooApplication.class, args);
	}

}
