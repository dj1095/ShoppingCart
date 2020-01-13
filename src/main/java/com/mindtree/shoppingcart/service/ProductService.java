package com.mindtree.shoppingcart.service;

import java.util.List;

import com.mindtree.shoppingcart.entities.Product;
import com.mindtree.shoppingcart.exceptions.CategoryNotFoundException;
import com.mindtree.shoppingcart.exceptions.ProductNotFoundException;

public interface ProductService {
	
	public List<Product> getAllProducts() throws ProductNotFoundException;
	
	public Product fetchProductById(Integer productId) throws ProductNotFoundException;

	public List<Product> fetchProductByName(String name) throws ProductNotFoundException;
	
	public List<Product> fetchProductByCategory(String category) throws ProductNotFoundException, CategoryNotFoundException;
	
}
