package com.masivian.cleancode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class RouletteProyectApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(RouletteProyectApplication.class, args);
	}

}
