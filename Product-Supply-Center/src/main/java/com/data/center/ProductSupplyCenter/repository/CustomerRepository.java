package com.data.center.ProductSupplyCenter.repository;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.data.center.ProductSupplyCenter.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	//public Customer findcustomerById(Integer id);
}
