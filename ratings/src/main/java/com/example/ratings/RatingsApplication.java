package com.example.ratings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.example.ratings"})
public class RatingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingsApplication.class, args);
	}

}
