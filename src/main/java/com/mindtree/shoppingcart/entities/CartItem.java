package com.mindtree.shoppingcart.entities;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@Entity
@NamedQuery(name = "CartItem.fetchByCartItemId", query = "from CartItem where cartItemId=:cartItemId")
public class CartItem {

	/**
	 * constructs an instance of CartItem
	 * 
	 * @param cartItemId
	 * @param product
	 * @param quantity
	 * @param shoppingCart
	 */
	public CartItem(UUID cartItemId, Product product, Integer quantity, ShoppingCart shoppingCart) {
		super();
		this.cartItemId = cartItemId;
		this.product = product;
		this.quantity = quantity;
		this.shoppingCart = shoppingCart;
	}

	/**
	 * cartItemId.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID cartItemId;
	/**
	 * product.
	 */
	@OneToOne
	@JoinColumn(name = "product_Id")
	private Product product;
	/**
	 * quantity.
	 */
	@Column(name = "quantity")
	private Integer quantity;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "shopping_cart_id")
	@JsonIgnore
	private ShoppingCart shoppingCart;

}
