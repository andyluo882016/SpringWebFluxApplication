package com.data.center.ProductSupplyCenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductSupplyCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductSupplyCenterApplication.class, args);
	}

}
