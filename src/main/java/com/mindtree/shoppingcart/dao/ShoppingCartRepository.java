package com.mindtree.shoppingcart.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.mindtree.shoppingcart.entities.CartItem;
import com.mindtree.shoppingcart.entities.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, UUID> {

	int deleteByShoppingCartId(@Param("shoppingCartId")UUID shoppingCartId);

}
