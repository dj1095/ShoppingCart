package com.mindtree.shoppingcart.entities;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
@Entity
public class Book extends Product{
	
	
	public Book(String genre, String author, String publications) {
		super();
		this.genre = genre;
		this.author = author;
		this.publications = publications;
	}
	/**
	 * genre.
	 */
	private String genre;
	/**
	 * author.
	 */
	private String author;
	/**
	 * publications.
	 */
	private String publications;
	
	

}
