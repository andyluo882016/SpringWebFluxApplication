package com.data.center.ProductOrderCenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class ProductOrderCenterApplication {
	
	//@LoadBalanced
	@Bean
	public RestTemplate getTempalte() {
		
		return new RestTemplate();
	}
	@LoadBalanced	
	@Bean
	WebClient webClient() {
		return WebClient.create("http://localhost:8881/order");
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductOrderCenterApplication.class, args);
	}

}
