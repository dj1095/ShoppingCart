package com.mindtree.shoppingcart.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQuery(name="Product.fetchByProductName" ,query="from Product where productName=:productName")
@NamedQuery(name="Product.findByBookCategory",query="SELECT p FROM Product p WHERE TYPE(p) = Book")
@NamedQuery(name="Product.findByApparalCategory" , query="SELECT p FROM Product p WHERE TYPE(p) = Apparal")
public class Product {
	/**
	 * productId.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_id")
	private Integer productId;
	/**
	 * productName.
	 */
	@Column(name="product_name")
	private String productName;
	/**
	 * price.
	 */
	@Column(name="price")
	private Double price;
	
	
}
