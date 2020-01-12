package com.mindtree.shoppingcart.service;

import java.util.UUID;

import com.mindtree.shoppingcart.dto.ProductDTO;
import com.mindtree.shoppingcart.entities.CartItem;
import com.mindtree.shoppingcart.entities.Product;
import com.mindtree.shoppingcart.entities.ShoppingCart;
import com.mindtree.shoppingcart.entities.User;
import com.mindtree.shoppingcart.exceptions.CartItemNotFoundException;
import com.mindtree.shoppingcart.exceptions.EmptyCartException;
import com.mindtree.shoppingcart.exceptions.InvalidQuantityException;
import com.mindtree.shoppingcart.exceptions.ProductNotFoundException;
import com.mindtree.shoppingcart.exceptions.UnableToAddProductException;

public interface ShoppingCartService {
	
	/**
	 * to view all items present in the cart.
	 * @param integer 
	 * 
	 * @return List<Product> , null if value not found.
	 * @throws EmptyCartException , if cart is empty
	 */
	public ShoppingCart viewCart(UUID shoppingCartId)throws EmptyCartException;
	/**
	 * adds  a product to the cart.
	 * 
	 * @param product , may be null;
	 * @return product added to cart.
	 * @throws UnableToAddProductException 
	 * @throws InvalidQuantityException 
	 */
	public CartItem addItem(ProductDTO request,UUID cartId) throws ProductNotFoundException, UnableToAddProductException, InvalidQuantityException;
	
	/**
	 * updates the product in the cart.
	 * 
	 * @param productId, never null
	 * @param product, never null
	 * @return 
	 * @throws ProductNotFoundException 
	 * @throws CartItemNotFoundException 
	 */
	public CartItem updateItem(ShoppingCart userCart ,ProductDTO productDTO) throws ProductNotFoundException, CartItemNotFoundException;
	
	/**
	 * removes the product from the cart.
	 * 
	 * @param user
	 * @param itemId
	 * @return
	 * @throws ProductNotFoundException
	 * @throws CartItemNotFoundException 
	 */
	public CartItem  removeItem(ShoppingCart userCart,UUID itemId) throws ProductNotFoundException, CartItemNotFoundException;
	/**
	 * removes all items from the cart.
	 * @param shoppingCart 
	 * @return 
	 * @throws EmptyCartException 
	 */
	public void removeAll(ShoppingCart shoppingCart) throws EmptyCartException;
	
}
