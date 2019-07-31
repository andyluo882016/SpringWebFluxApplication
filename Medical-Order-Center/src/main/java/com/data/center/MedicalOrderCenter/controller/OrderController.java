package com.data.center.MedicalOrderCenter.controller;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

import javax.validation.Valid;

//import org.hibernate.validator.internal.util.logging.Log_.logger;
//import org.hibernate.validator.internal.util.logging.Log_.logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import com.data.center.MedicalOrderCenter.domain.*;

//import com.data.center.MedicalSupportCenter.domain.MedicalEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
@RequestMapping("/buy")
public class OrderController {

	Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	   @Autowired
	  private WebClient client;
	   
	   @GetMapping(path="/Drug")
		public Flux<Medication> showProduct(){//@PathVariable("id") final String id){
			 
				return 	client
			    	.get()
			    	.uri("/getAllMedication")
			    	.retrieve()
			    	.bodyToFlux(Medication.class);
			    	
			
		}
	   @GetMapping(path="/show/{id}")
	   public Mono<Medication> getOne(@PathVariable("id") final String id) {
		   /*
		    *
             return client
				   .get()
				   .uri("/getMedication/"+id)
				   .retrieve()
				   .bodyToMono(Medication.class);
		    */
		   
		   return client
				   .get()
				   .uri("/getMedication/{id}", id).accept(MediaType.APPLICATION_JSON)
				   .exchange()
				   .flatMap(response -> response.bodyToMono(Medication.class));
	   }
	   
	   
	   @GetMapping(path="/showEvent/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	   public Flux<MedicalEvents> showEvents(@PathVariable("id") final String id){
		   
		   return client
				   .get()//{id}/medication/event
				   .uri("/getMedication/"+id)
				   .accept(MediaType.APPLICATION_JSON)
				   .retrieve()
				   .bodyToFlux(Medication.class)
				   .flatMap(medica -> { 
					  
					   Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
						 
						 Flux<MedicalEvents> buyProduct= Flux.fromStream(
								 Stream.generate(() -> new MedicalEvents(medica, new Date()))
							
								 );
						 
						 return  Flux.zip(interval, buyProduct)
								   .map(Tuple2::getT2);
						 
					});
					   
					   
	   }
	   
	   @PutMapping(path="/updateMe")
	   public Mono<Void> showChange(@Valid @RequestBody Medication med){
		   //final Medication record = new Medication ("WM876980", "Sodium Chloride", "soution", 88990.00);
		  // Mono<Medication> mdr =Mono.just(record);
		   return client
				  .put()
				  .uri("/update")
				  .contentType(MediaType.APPLICATION_JSON)
				  .body(Mono.just(med), Medication.class)
				  .retrieve()
				  .bodyToMono(Void.class);
	   }
		
	  
}
