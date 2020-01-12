package com.mindtree.shoppingcart.service.impl;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.shoppingcart.constants.ShoppingCartConstants;
import com.mindtree.shoppingcart.dao.CartItemRepository;
import com.mindtree.shoppingcart.dao.ShoppingCartRepository;
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
import com.mindtree.shoppingcart.service.ShoppingCartService;
import com.mindtree.shoppingcart.util.ShoppingCartUtil;

import lombok.Setter;

/**
 * @author M1044441
 *
 */
@Component
public class ShoppingCartServiceImpl implements ShoppingCartService {

	/**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);
	/**
	 * CartItemRepository.
	 */
	@Autowired
	@Setter
	private CartItemRepository cartItemRepo;

	/**
	 * ShoppingCartRepository.
	 */
	@Autowired
	@Setter
	private ShoppingCartRepository shoppingCartRepo;

	/**
	 * ProductServiceImpl.
	 */
	@Autowired
	@Setter
	private ProductServiceImpl productServiceImpl;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mindtree.shoppingcart.service.ShoppingCartService#viewCart(java.util.
	 * UUID) Returns Shopping cart with all the items and cart total of the user
	 */
	@Override
	public ShoppingCart viewCart(UUID cartId) throws EmptyCartException {
		ShoppingCart cart = null;
		Optional<ShoppingCart> optional = shoppingCartRepo.findById(cartId);
		if (optional.isPresent() && !optional.get().getItems().isEmpty()) {
			cart = optional.get();
		} else {
			throw new EmptyCartException("No Products AVailable in Cart");
		}
		return cart;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mindtree.shoppingcart.service.ShoppingCartService#addItem(com.mindtree.
	 * shoppingcart.dto.ProductDTO, java.util.UUID) Adds a new Item to the User
	 * cart.
	 */
	@Override
	@Transactional
	public CartItem addItem(ProductDTO request, UUID cartId)
			throws UnableToAddProductException, ProductNotFoundException, InvalidQuantityException {
		CartItem cartItem = null;
		Integer quantity = null;
		try {
			for (Product product : productServiceImpl.getAllProducts()) {
				if (product.getProductId() != null && product.getProductId().equals(request.getProductId())) {
					ShoppingCart cart = shoppingCartRepo.findById(cartId).get();
					cartItem = ShoppingCartUtil.getCartItemForExistingProduct(product.getProductId(), cart);
					if (cartItem != null) {
						cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
						cart.setTotal(cart.getTotal() + (request.getQuantity() * product.getPrice()));
					} else {
						cartItem = ShoppingCartUtil.buildCartItem(product, request);
						if (cartItem.getQuantity() == 0) {
							throw new InvalidQuantityException("Quantity should be atlease one");
						} else {
							quantity = cartItem.getQuantity();
						}
						cartItem.setQuantity(quantity);
						cart.setTotal(cart.getTotal() + (quantity * product.getPrice()));
					}
					cartItem.setShoppingCart(cart);
					cartItemRepo.saveAndFlush(cartItem);
					break;
				}
			}
			if (cartItem == null) {
				throw new ProductNotFoundException(
						ShoppingCartConstants.PRODUCT_NOT_FOUND_WITH_ID + request.getProductId());
			}
		} catch (NoSuchElementException e) {
			logger.error(e.getMessage(), e);
			throw new UnableToAddProductException("Unable To Add Product", e);
		}
		return cartItem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mindtree.shoppingcart.service.ShoppingCartService#updateItem(com.mindtree
	 * .shoppingcart.entities.ShoppingCart,
	 * com.mindtree.shoppingcart.dto.ProductDTO) Updates the Existing Cart with the
	 * quantity specified. Removes the Product if quantity is zero.
	 */
	@Override
	@Transactional
	public CartItem updateItem(ShoppingCart userCart, ProductDTO productDto)
			throws ProductNotFoundException, CartItemNotFoundException {
		logger.info("UPDATE METHOD");
		CartItem updatedCartItem = null;
		CartItem item = ShoppingCartUtil.getCartItemForExistingProduct(productDto.getProductId(), userCart);
		if (item == null) {
			throw new ProductNotFoundException(
					ShoppingCartConstants.PRODUCT_NOT_FOUND_WITH_ID + productDto.getProductId());
		}
		Double updatedCartTotal = getupdatedCartTotal(userCart, productDto, item);
		userCart.setTotal(updatedCartTotal);
		if (productDto.getQuantity().equals(ShoppingCartConstants.ZERO)) {
			userCart.getItems().remove(item);
		} else {
			item.setQuantity(productDto.getQuantity());
		}
		shoppingCartRepo.saveAndFlush(userCart);
		return updatedCartItem;
	}

	@Override
	@Transactional
	public CartItem removeItem(ShoppingCart userCart, UUID cartItemId)
			throws ProductNotFoundException, CartItemNotFoundException {
		logger.info("DELETE METHOD"+cartItemId);
		CartItem cartItem = null;
		Optional<CartItem> optional = cartItemRepo.findById(cartItemId);
		if (optional.isPresent()) {
			cartItem = optional.get();
		} else {
			throw new CartItemNotFoundException(ShoppingCartConstants.ITEM_NOT_FOUND_IN_CART_WITH_ID + cartItemId);
		}
		Double total = getupdatedCartTotal(userCart, new ProductDTO(cartItem.getProduct().getProductId(), 0), cartItem);
		userCart.setTotal(total);
		userCart.getItems().remove(cartItem);
		shoppingCartRepo.saveAndFlush(userCart);
		return cartItem;
	}

	@Override
	@Transactional
	public void removeAll(ShoppingCart cart) throws EmptyCartException {
		if(cart.getItems()!=null && !cart.getItems().isEmpty()) {			
			cart.getItems().clear();
			cart.setTotal(0);
			shoppingCartRepo.saveAndFlush(cart);
		}else {
			throw new EmptyCartException("No items Present in Cart");
		}
	}

	/**
	 * updates the cart total
	 * 
	 * @param userCart
	 * @param productDto
	 * @param item
	 * @return
	 */
	private Double getupdatedCartTotal(ShoppingCart userCart, ProductDTO productDto, CartItem item) {
		Double total = userCart.getTotal();
		Integer productQuantity = item.getQuantity();
		Double productPrice = item.getProduct().getPrice();
		Integer updatedQuantity = productDto.getQuantity();
		if (updatedQuantity.equals(ShoppingCartConstants.ZERO)) {
			total = total - (productQuantity * productPrice);
		} else {
			total = (total - (productQuantity * productPrice)) + (updatedQuantity * productPrice);
		}
		return total;
	}

}
