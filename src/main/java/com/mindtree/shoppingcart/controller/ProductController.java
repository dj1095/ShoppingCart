package com.mindtree.shoppingcart.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.shoppingcart.entities.Product;
import com.mindtree.shoppingcart.exceptions.CategoryNotFoundException;
import com.mindtree.shoppingcart.exceptions.ProductNotFoundException;
import com.mindtree.shoppingcart.service.ProductService;

@RestController
public class ProductController {

	/**
	 * productsrepo.
	 */
	@Autowired
	private ProductService productService;

	@GetMapping(path = "/product/id/{productId}")
	public Product searchProductById(
			@Valid @PathVariable 
			@Positive(message="Positive Integer Required") 
			Integer productId) throws ProductNotFoundException {
		return productService.fetchProductById(productId);
	}
	
	@GetMapping(path="/product/name/{productName}")
	public List<Product> searchProductByName(@Valid
			@PathVariable 
			@Size(min=1,message="Minimum One Character Required") 
			String productName) throws ProductNotFoundException {
				return productService.fetchProductByName(productName);
	}
	
	@GetMapping(path = "/product/{category}")
	public List<Product> searchProductByCategory(@Valid 
						@PathVariable String category)
			throws ProductNotFoundException, CategoryNotFoundException {
		return productService.fetchProductByCategory(category);
	}

}
