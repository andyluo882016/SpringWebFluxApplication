package com.data.center.ProductSupplyCenter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.center.ProductSupplyCenter.domain.*;
import com.data.center.ProductSupplyCenter.repository.CustomerRepository;
import com.data.center.ProductSupplyCenter.repository.ProductsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

//--
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class SupplyOrderController {

	private static final Logger logger = LoggerFactory.getLogger(SupplyOrderController.class);
	
	private CustomerRepository custRepository;
	
	private ProductsRepository prodRepository;

	public SupplyOrderController(CustomerRepository custRepository, ProductsRepository prodRepository) {
		super();
		this.custRepository = custRepository;
		this.prodRepository = prodRepository;
	}
	
	@GetMapping(path="/getAlls")
	public ResponseEntity<List<Product>> getAllProducts(HttpServletRequest request, @RequestHeader(value="Accept") String acceptType) {
		logger.info("****  get All Product List ********");
		HttpHeaders headers = new HttpHeaders();
		headers.add("find-product-alls", "allproducts");
		
		logger.info("The URI -->"+request.getRequestURI());
		  
		  logger.info("Accept : --> " + acceptType);
		  logger.info("The URI -->"+request.getRequestURI());
		  List<Product> list= prodRepository.findAll();
		  
		  return new ResponseEntity<List<Product>>(list, headers, HttpStatus.OK);
	}
	
	@GetMapping(path="/getProduct/{id}")
	public ResponseEntity<Product> getProduct(@Valid @PathVariable("id") final String id, HttpServletRequest request, @RequestHeader(value="Accept") String acceptType) {
		logger.info("****  get one Product by ID ********");
		HttpHeaders headers = new HttpHeaders();
		headers.add("find-product-byId", "productID");
		Optional<Product> opt=prodRepository.findById(id);
		
		logger.info("The URI -->"+request.getRequestURI());
		  
		logger.info("Accept : --> " + acceptType);
		logger.info("The URI -->"+request.getRequestURI());
		  
		  
		 return new ResponseEntity<Product>(opt.get(), headers, HttpStatus.OK);
	  }

	@PutMapping(path="/updateProduct/{id}")
	public ResponseEntity<Void> updatedOne(@Valid @RequestBody Product product, @PathVariable("id") final String id, HttpServletRequest request, @RequestHeader(value="Accept") String acceptType){
		logger.info("****  Update a Product by ID ********");
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("update-product-byId", "productUpdated");
		//boolean flag=false;
		logger.info("The URI -->"+request.getRequestURI());
		  
		logger.info("Accept : --> " + acceptType);
		logger.info("The URI -->"+request.getRequestURI());
		if (prodRepository.findById(id).isPresent()) {
		prodRepository.save(product);
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
		}else {
			//flag=true;
			return new ResponseEntity<Void>(headers, HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping(path="/saveProduct")
	public ResponseEntity<Void> saveProduct(@Valid @RequestBody Product product, HttpServletRequest request, @RequestHeader(value="Accept") String acceptType) {
		logger.info("****  Save a Product  ********");
		HttpHeaders headers = new HttpHeaders();
		headers.add("save-product", "productSave");
		//boolean flag=false;
		logger.info("The URI -->"+request.getRequestURI());
		  
		logger.info("Accept : --> " + acceptType);
		logger.info("The URI -->"+request.getRequestURI());    
		prodRepository.save(product);
		   return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}
		
	@GetMapping(path="/allCustomers")	
	 public ResponseEntity<List<Customer>> getAllCustomers(HttpServletRequest request, @RequestHeader(value="Accept") String acceptType){
		 
		 logger.info("****  get All Customer List ********");
			HttpHeaders headers = new HttpHeaders();
			headers.add("find-customer-alls", "allcustomer");
			
			logger.info("The URI -->"+request.getRequestURI());
			  
			  logger.info("Accept : --> " + acceptType);
			  logger.info("The URI -->"+request.getRequestURI());
			  List<Customer> list= custRepository.findAll();
			  
			  return new ResponseEntity<List<Customer>>(list, headers, HttpStatus.OK);
	 
		
	}
	@GetMapping(path="/getcustomer/{id}")
	public ResponseEntity<Customer> getOneCustomer(@Valid @PathVariable("id") final Integer id, HttpServletRequest request, @RequestHeader(value="Accept") String acceptType){
		logger.info("****  get one Customer List ********");
		HttpHeaders headers = new HttpHeaders();
		headers.add("find-customer-alls", "allcustomer");
		
		logger.info("The URI -->"+request.getRequestURI());
		  
		  logger.info("Accept : --> " + acceptType);
		  logger.info("The URI -->"+request.getRequestURI());
		  List<Customer> list= custRepository.findAll();
		  Integer ddi= new Integer(id);
		  //Integer id, String name, String cardNumber, String pcid
		  Customer customer=new Customer();
		  for(Customer c : list) {
			  System.out.println(c.toString());
			  if (c.getId().equals(id)) {
				Customer  cust=new Customer(c.getId(), c.getName(),c.getCardNumber(),c.getPcid());
				System.out.println(cust.toString());
				 customer=cust;
			  }
		  }
		 System.out.println("+++  "+ddi);
		
		  System.out.println("\n+++++++++  -->>>*********\n"+customer.toString());
		  //String myid=String.valueOf(id);
		  
		 //Customer customer =list.get(1);
		  
		  return new ResponseEntity<Customer>(custRepository.findById(id).get(),headers, HttpStatus.OK);
	}
	
}
