package com.mindtree.shoppingcart.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mindtree.shoppingcart.constants.ShoppingCartConstants;
import com.mindtree.shoppingcart.dao.ProductsRepository;
import com.mindtree.shoppingcart.entities.Product;
import com.mindtree.shoppingcart.exceptions.ProductNotFoundException;
import com.mindtree.shoppingcart.service.ProductService;

@Component
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductsRepository productsRepo;
	
	public List<Product> getAllProducts(){
		return productsRepo.findAll();
	}

	@Override
	public Product fetchProductById(Integer productId) throws ProductNotFoundException {
		Optional<Product> optional = productsRepo.findById(productId);
		if (!optional.isPresent()) {
			throw new ProductNotFoundException(ShoppingCartConstants.PRODUCT_NOT_FOUND_WITH_ID + productId);
		}
		return optional.get();
	}

	@Override
	public List<Product> fetchProductByName(String name) throws ProductNotFoundException {
		List<Product> products = productsRepo.fetchByProductName(name.toLowerCase());
		if(products==null || products.isEmpty()) {
			throw new ProductNotFoundException(ShoppingCartConstants.PRODUCT_NOT_FOUND + name);
		}
		return products;
	}

	@Override
	public Product fetchProductByCategory(String category) throws ProductNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
