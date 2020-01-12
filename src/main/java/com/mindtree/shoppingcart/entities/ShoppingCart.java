package com.mindtree.shoppingcart.entities;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@NamedQuery(name="CartItem.deleteByShoppingCartId",query="from CartItem where shoppingCartId=:shoppingCartId")
public class ShoppingCart {
	/**
	 * constructs an instance of ShoppingCart with params
	 * @param shoppingCartId , may be null.
	 * @param total , never null.
	 * @param items , never null.
	 */
	public ShoppingCart(UUID shoppingCartId, double total, Set<CartItem> items) {
		super();
		this.shoppingCartId = shoppingCartId;
		this.total = total;
		this.items = items;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="shopping_cart_id")
	private UUID shoppingCartId;
	
	

	@Column(name="total")
	private double total;
	
	@OneToMany(orphanRemoval=true,mappedBy="shoppingCart",cascade= {CascadeType.REMOVE,
				CascadeType.PERSIST,CascadeType.MERGE})
	private Set<CartItem> items;

}
