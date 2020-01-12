package com.mindtree.shoppingcart.dto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ProductDTO {
	

	public ProductDTO(@Positive Integer productId, @PositiveOrZero Integer quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}
	/**
	 * productId.
	 */
	@Positive
	private Integer productId;
	/**
	 * quantity.
	 */
	@PositiveOrZero
	private Integer quantity;
	
}
