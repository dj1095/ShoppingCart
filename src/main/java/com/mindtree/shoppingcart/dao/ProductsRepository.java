package com.mindtree.shoppingcart.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.mindtree.shoppingcart.entities.Product;

public interface ProductsRepository extends JpaRepository<Product, Integer> {
	List<Product> fetchByProductName(@Param("productName") String productName);
 }
