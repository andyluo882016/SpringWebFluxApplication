package com.data.center.MedicalSupportCenter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import java.util.UUID;
import java.util.stream.Stream;
import com.data.center.MedicalSupportCenter.repository.MedicationRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import com.data.center.MedicalSupportCenter.domain.*;

@SpringBootApplication
@EnableEurekaClient
public class MedicalSupportCenterApplication {
	
	
	@Bean
	CommandLineRunner demo(MedicationRepository MDR ) {
	
		return args -> {
		  MDR.deleteAll()
		  .subscribe(null, null, () -> {
			
			  Stream.of(new Medication(UUID.randomUUID().toString(), "Diphenhydramine", "table", 897.21 ),
					    new Medication(UUID.randomUUID().toString(), "Coricidin", "Liquid", 227.90 ),
					    new Medication(UUID.randomUUID().toString(), "Acetaminophen", "caplet", 871.60 ),
					    new Medication(UUID.randomUUID().toString(), "Antibacterial", "Bandages", 19.76 ),
					    new Medication(UUID.randomUUID().toString(), "Sodium Chloride", "Solution", 22119.76 )
					  )
			          .forEach(medication -> {
			        	  MDR.save(medication)
			        	  .subscribe(medicate ->System.out.println(medicate.toString()));
			          });
		  });
		
		
		};
	}
	

	public static void main(String[] args) {
		SpringApplication.run(MedicalSupportCenterApplication.class, args);
	}

}
