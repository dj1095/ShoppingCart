package com.mindtree.shoppingcart.util;

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
		Product book1 = new Book("fiction","Anand","RK");
		book1.setPrice(1500.00);
		book1.setProductId(1);
		book1.setProductName("assura");
		productsRepo.save(book1);
		Product book2 = new Book("Educational","Napolean Hill","Antony");
		book2.setPrice(500.00);
		book2.setProductId(2);
		book2.setProductName("Mastery");
		productsRepo.save(book2);
		
		Product apparal = new Apparal("jeans","Buffalo","shaded");
		apparal.setPrice(1500.00);
		apparal.setProductId(2);
		apparal.setProductName("trouser");
		productsRepo.save(apparal);
		
		Product appara2 = new Apparal("Tee","Buffalo","V-Neck");
		appara2.setPrice(2000.00);
		appara2.setProductId(2);
		appara2.setProductName("Shirt");
		productsRepo.save(appara2);

	}

}
