package com.mindtree.shoppingcart.controller;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.shoppingcart.dto.ProductDTO;
import com.mindtree.shoppingcart.entities.CartItem;
import com.mindtree.shoppingcart.entities.ShoppingCart;
import com.mindtree.shoppingcart.entities.User;
import com.mindtree.shoppingcart.exceptions.CartItemNotFoundException;
import com.mindtree.shoppingcart.exceptions.EmptyCartException;
import com.mindtree.shoppingcart.exceptions.InvalidQuantityException;
import com.mindtree.shoppingcart.exceptions.InvalidUserException;
import com.mindtree.shoppingcart.exceptions.ProductNotFoundException;
import com.mindtree.shoppingcart.exceptions.UnableToAddProductException;
import com.mindtree.shoppingcart.exceptions.UserNotFoundException;
import com.mindtree.shoppingcart.service.ShoppingCartService;
import com.mindtree.shoppingcart.service.UserService;
import com.mindtree.shoppingcart.util.ShoppingCartUtil;

import lombok.Setter;

@RestController
public class ShoppingCartController {

	/**
	 * logger
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(ShoppingCartController.class);

	/**
	 * cartService.
	 */
	@Autowired
	@Setter
	private ShoppingCartService cartService;
	/**
	 * userService.
	 */
	@Autowired
	@Setter
	private UserService userService;

	/**
	 * creates a new user.
	 * 
	 * @param user , may be null
	 */
	@PostMapping("/addUser")
	public User addUser(@RequestBody User user) {
		User addedUser = null;
		try {
			addedUser = userService.addUser(user);
		} catch (InvalidUserException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return addedUser;
	}

	/**
	 * gives all products in the cart for a particular user.
	 * 
	 * @param userId , may be null.
	 * 
	 * @return list of Products ,null if value not found.
	 * @throws EmptyCartException    , if cart is Empty
	 * @throws UserNotFoundException , if user Not Found.
	 */
	@GetMapping(path = "user/{userId}/cart/allproducts")
	public ShoppingCart getAllProducts(@PathVariable Integer userId) throws EmptyCartException, UserNotFoundException {
		ShoppingCart cartItems = null;
		User user = userService.findById(userId);
		if (user != null && user.getCart() != null) {
			cartItems = cartService.viewCart(user.getCart().getShoppingCartId());
		}
		return cartItems;

	}

	/**
	 * adds a product to shopping cart.
	 * 
	 * @param name      , may be null.
	 * @param productId , may be null
	 * @throws UnableToAddProductException
	 * @throws UserNotFoundException
	 * @throws InvalidQuantityException
	 * @throws ProductNotFoundException    , if productId not found
	 */
	@PostMapping(path = "user/{userId}/addItem")
	public CartItem addItem(@Valid @PathVariable @Positive Integer userId, @Valid @RequestBody ProductDTO request)
			throws UnableToAddProductException, UserNotFoundException, InvalidQuantityException,
			ProductNotFoundException {
		User user = userService.findById(userId);
		CartItem item = cartService.addItem(request, user.getCart().getShoppingCartId());
		return item;
	}

	/**
	 * updates an existing product in the cart.
	 * @param userId , a non null value
	 * @param product , a non null value
	 * @return ,updated item
	 * @throws UserNotFoundException
	 * @throws ProductNotFoundException
	 * @throws CartItemNotFoundException
	 */
	@PutMapping(path = "user/{userId}/updateItem")
	public CartItem updateItem(@Valid @PathVariable @Positive Integer userId, @Valid @RequestBody ProductDTO product)
			throws UserNotFoundException, ProductNotFoundException, CartItemNotFoundException {
		User user = userService.findById(userId);
		CartItem updatedItem = cartService.updateItem(user.getCart(), product);
		return updatedItem;
	}

	/**
	 * deletes a selected item from the cart.
	 * @param userId
	 * @param cartItemId
	 * @return
	 * @throws UserNotFoundException
	 * @throws ProductNotFoundException
	 * @throws CartItemNotFoundException
	 */
	@DeleteMapping(path = "user/{userId}/deleteItem/{cartItemId}")
	public CartItem deleteItem(@Valid @PathVariable @Positive Integer userId, @PathVariable String cartItemId)
			throws UserNotFoundException, ProductNotFoundException, CartItemNotFoundException {
		UUID itemId = ShoppingCartUtil.getUUID(cartItemId);
		User user = userService.findById(userId);
		CartItem removedItem = cartService.removeItem(user.getCart(), itemId);
		return removedItem;

	}
	
	/**
	 * deletes all items in the cart.
	 * @param userId
	 * @throws UserNotFoundException
	 * @throws EmptyCartException
	 */
	@DeleteMapping(path = "user/{userId}/deleteAll")
	public void deleteAll(@Valid @PathVariable @Positive Integer userId)
			throws UserNotFoundException, EmptyCartException {
		User user = userService.findById(userId);
		 cartService.removeAll(user.getCart());
	}



}
