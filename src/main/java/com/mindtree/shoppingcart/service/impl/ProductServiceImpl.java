package com.mindtree.shoppingcart.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mindtree.shoppingcart.constants.ShoppingCartConstants;
import com.mindtree.shoppingcart.dao.ProductsRepository;
import com.mindtree.shoppingcart.entities.Product;
import com.mindtree.shoppingcart.exceptions.CategoryNotFoundException;
import com.mindtree.shoppingcart.exceptions.ProductNotFoundException;
import com.mindtree.shoppingcart.service.ProductService;

@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductsRepository productsRepo;

	public List<Product> getAllProducts() {
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
		if (products == null || products.isEmpty()) {
			throw new ProductNotFoundException(ShoppingCartConstants.PRODUCT_NOT_FOUND + name);
		}
		return products;
	}

	@Override
	public List<Product> fetchProductByCategory(String category)
			throws ProductNotFoundException, CategoryNotFoundException {
		if (!(category.equalsIgnoreCase("BOOK") || category.equalsIgnoreCase("Apparal"))) {
			throw new CategoryNotFoundException(ShoppingCartConstants.NO_CATEGORY_FOUND_WITH_NAME + category);
		}
		List<Product> products = null;
		if (category.equalsIgnoreCase("BOOK")) {
			products = productsRepo.findByBookCategory();
		} else if (category.equalsIgnoreCase("Apparal")) {
			products = productsRepo.findByApparalCategory();

		}
		if (products == null || products.isEmpty()) {
			throw new ProductNotFoundException(ShoppingCartConstants.NO_ITEMS_IN_THIS_CATEGORY);
		}
		return products;
	}
}
