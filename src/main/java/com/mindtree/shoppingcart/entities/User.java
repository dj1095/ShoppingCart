package com.mindtree.shoppingcart.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="User")
@Getter @Setter @NoArgsConstructor @ToString
@NamedQuery(name="User.fetchByName",query=" from User where name=:name")
public class User {
	
	/**
	 * construct an instance of User
	 * 
	 * @param userId
	 * @param name
	 * @param cart
	 */
	public User(Integer userId, String name, ShoppingCart cart) {
		super();
		this.userId = userId;
		this.name = name;
		this.cart = cart;
	}

	/**
	 * userId.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer userId ;
	
	/**
	 * name.
	 */
	private String name;
	
	/**
	 * cart.
	 */
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_car_Id")
	private ShoppingCart cart;

}
