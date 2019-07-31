package com.data.center.ProductSupplyCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.data.center.ProductSupplyCenter.domain.Product;

@Repository
public interface ProductsRepository extends JpaRepository<Product, String>{

}
