package com.mindtree.shoppingcart.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mindtree.shoppingcart.dao.ProductsRepository;
import com.mindtree.shoppingcart.entities.Apparal;
import com.mindtree.shoppingcart.entities.Book;
import com.mindtree.shoppingcart.entities.Product;

import lombok.Setter;

@Component
public class ProductsRepoCommandLineRunner implements CommandLineRunner {

	@Autowired
	@Setter
	private ProductsRepository productsRepo;

	@Override
	public void run(String... args) throws Exception {
		Product book = new Book("fiction","Anand","RK");
		book.setPrice(1500.00);
		book.setProductId(1);
		book.setProductName("assura");
		productsRepo.save(book);
		
		Product apparal = new Apparal("jeans","Buffalo","shaded");
		apparal.setPrice(1500.00);
		apparal.setProductId(2);
		apparal.setProductName("trouser");
		productsRepo.save(apparal);

	}

}
