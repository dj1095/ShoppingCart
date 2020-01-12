package com.mindtree.shoppingcart.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.mindtree.shoppingcart.entities.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem ,UUID> {
	List<CartItem> fetchByCartItemId(@Param("cartItemId")Integer cartItemId);
}
