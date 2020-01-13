package com.mindtree.shoppingcart.entities;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@Entity(name="Apparal")
public class Apparal extends Product {
	
	public Apparal(String type, String brand, String design) {
		super();
		this.type = type;
		this.brand = brand;
		this.design = design;
	}
	/**
	 * type
	 */
	private String type;
	/**
	 * brand.
	 */
	private String brand;
	/**
	 * design.
	 */
	private String design;

	
}
