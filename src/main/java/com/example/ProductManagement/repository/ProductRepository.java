package com.example.ProductManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProductManagement.model.Products;


public interface ProductRepository extends JpaRepository<Products,Integer>{
	
	Products findByUsernameAndPassword(String username,String password);

}
