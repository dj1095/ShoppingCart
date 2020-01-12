package com.mindtree.shoppingcart.util;

import java.util.UUID;

import com.mindtree.shoppingcart.constants.ShoppingCartConstants;
import com.mindtree.shoppingcart.dto.ProductDTO;
import com.mindtree.shoppingcart.entities.CartItem;
import com.mindtree.shoppingcart.entities.Product;
import com.mindtree.shoppingcart.entities.ShoppingCart;
import com.mindtree.shoppingcart.exceptions.CartItemNotFoundException;

public class ShoppingCartUtil {

	/**
	 * constructs CartItem from a Product.
	 * 
	 * @param product , never null
	 * @param request , never null
	 * @return CartItem , a non null value.
	 */
	public static CartItem buildCartItem(Product product, ProductDTO request) {
		return CartItem.builder().product(product).quantity(request.getQuantity()).build();
	}

	/**
	 * checks if item already present in the cart.
	 * 
	 * @param cart     , never null.
	 * @param cartItem , never null.
	 * @return true if cart already contains item.
	 */
	public static boolean isCartContainsItem(ShoppingCart cart, CartItem cartItem) {
		if (cart.getItems() != null && !cart.getItems().isEmpty() && cartItem != null) {
			return cart.getItems().contains(cartItem);
		}
		return false;
	}

	/**
	 * checks if the given productId is present in the cart else null;
	 * 
	 * @param product , never null
	 * @param cart    , never null
	 * @return null if value not found.
	 */
	public static CartItem getCartItemForExistingProduct(Integer productID, ShoppingCart cart) {
		for (CartItem item : cart.getItems()) {
			if (productID!=null && productID.equals(item.getProduct().getProductId())) {
				return item;
			}
		}
		return null;
	}
	/**
	 * @param cartItemId , never null
	 * @return an UUID for the valid String.
	 * @throws CartItemNotFoundException
	 */
	public static UUID getUUID(String cartItemId) throws CartItemNotFoundException {
		String uuid = cartItemId.replaceAll(                                            
			    "(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})",                            
			    "$1-$2-$3-$4-$5"); 
		UUID itemUUID = null;
		try {
			itemUUID = UUID.fromString(uuid);
		} catch (IllegalArgumentException e) {
			throw new CartItemNotFoundException(
					ShoppingCartConstants.ITEM_NOT_FOUND_IN_CART_WITH_ID+cartItemId);
		}
		return itemUUID;
	}
	
	
}
