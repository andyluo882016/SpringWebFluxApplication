package com.data.center.MedicalOrderCenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient
public class MedicalOrderCenterApplication {
	
	@Bean
	WebClient webClient() {
		return WebClient.create("http://localhost:8808/order");
	}

	public static void main(String[] args) {
		SpringApplication.run(MedicalOrderCenterApplication.class, args);
	}

}
