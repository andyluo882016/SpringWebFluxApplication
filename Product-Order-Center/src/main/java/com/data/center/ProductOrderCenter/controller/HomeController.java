package com.data.center.ProductOrderCenter.controller;

import java.time.Duration;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
//
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.convert.ReadingConverter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import com.data.center.ProductOrderCenter.domain.Customer;
import com.data.center.ProductOrderCenter.domain.Invoice;
import com.data.center.ProductOrderCenter.domain.Product;

import reactor.core.publisher.Flux;
//
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/checkout")
public class HomeController {
   
	
	
	@Autowired
	private WebClient client;
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping(path="/customer/{id}")
	public Mono<Customer> showCustomer(@PathVariable("id") final Integer id,HttpServletRequest request, @RequestHeader(value="Accept") String acceptType){
		   //String url="http://localhost:8881/order/getcustomer/"+id;
		   
		  // Customer item=restTemplate.getForObject(url, Customer.class, id);
		   //System.out.println("++++++++++++++++++++++++++"+item.getCardNumber());
		
		   return client
				  .get()
				  .uri("/getcustomer/"+id)
				  .accept(MediaType.APPLICATION_JSON)
				  .retrieve()
				  .bodyToMono(Customer.class);
				  
	}
	
	@GetMapping(path="/showallCustomers")
	public Flux<Customer> showAlls() {
		
		return client
				.get()
				.uri("/allCustomers")
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
		    	.bodyToFlux(Customer.class);
	}
	@GetMapping(path="/showProducts")
	public Flux<Product> showAllProducts(){
		
		return client
			   .get()
			   .uri("/getAlls")
			   .accept(MediaType.APPLICATION_JSON)
			   .retrieve()
			   .bodyToFlux(Product.class);
			   
	}
	
	@GetMapping(path="/showProduct/{id}")
	public Mono<Product> showOneProduct(@Valid @PathVariable("id") final String id){
		
		return client
				.get()
				.uri("/getProduct/"+id)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(Product.class);
	}
	@PutMapping(path="/updateProduct/{id}")
	public Mono<Void> updatedProduct(@Valid @RequestBody Product product, @PathVariable("id") final String id){
		
		return client
			   .put()
			   .uri("/updateProduct/"+id)
			   .accept(MediaType.APPLICATION_JSON)
			   .body(Mono.just(product), Product.class)
			   .retrieve()
			   .bodyToMono(Void.class);
	}
	@PostMapping(path="/saveOneProduct")
	public Mono<Void> saveProduct(@Valid @RequestBody Product product) {
		
		     return client
	               .post()
	                .uri("/saveProduct")
	                .accept(MediaType.APPLICATION_JSON)
	 			   .body(Mono.just(product), Product.class)
	 			   .retrieve()
	 			   .bodyToMono(Void.class);
       } 
	
	@GetMapping(path="/{id}/{cid}/OrderEvents", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Invoice> showInvoice(@Valid @PathVariable("id") final String id, @PathVariable("cid") Integer cid){
		String url="http://localhost:8881/order/getcustomer/"+cid;
		
		return     client
				   .get()
				   .uri("/getProduct/"+id)
				   .accept(MediaType.APPLICATION_JSON)
				   .retrieve()
				   .bodyToFlux(Product.class)
				   .flatMap(product -> { 
					   Customer customer=restTemplate.getForObject(url, Customer.class, cid);
					   Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
					   
					   Flux<Invoice> buyProduct= Flux.fromStream(
								 Stream.generate(() -> new Invoice(product,customer, new Date()))
							
								 );
						 
						 return  Flux.zip(interval, buyProduct)
								   .map(Tuple2::getT2);
					   
					   
				   });
				   
	}
}
