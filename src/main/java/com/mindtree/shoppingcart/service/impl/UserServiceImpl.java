package com.mindtree.shoppingcart.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mindtree.shoppingcart.constants.ShoppingCartConstants;
import com.mindtree.shoppingcart.dao.UserRepository;
import com.mindtree.shoppingcart.entities.ShoppingCart;
import com.mindtree.shoppingcart.entities.User;
import com.mindtree.shoppingcart.exceptions.InvalidUserException;
import com.mindtree.shoppingcart.exceptions.UserNotFoundException;
import com.mindtree.shoppingcart.service.UserService;

import lombok.Setter;

@Component
public class UserServiceImpl implements UserService{

	@Autowired
	@Setter
	private UserRepository userRepo;
	
	@Override
	public User addUser(User user) throws InvalidUserException {
		if(user!=null) {
			if(user.getCart()==null) {
				user.setCart(new ShoppingCart());
			}
			userRepo.save(user);
		}else {
			throw new InvalidUserException("Invalid User");
		}
		return user;
	}
	
	/*@Override
	public List<User> findUserByName(String userName) throws UserNotFoundException {
		List<User> users = null;
		if(userName!=null) {			
			users = userRepo.fetchByName(userName);
		}
		if (users == null || users.isEmpty()) {
			throw new UserNotFoundException("User Not Found With the Name :" + userName);
		}
		return users;
	}*/

	@Override
	public User findById(Integer id) throws UserNotFoundException {
		User user = null;
		Optional<User> userOptional = userRepo.findById(id);
		if (userOptional.isPresent()) {
			user = userOptional.get();
		} else {
			throw new UserNotFoundException(ShoppingCartConstants.USER_NOT_FOUND_WITH_ID + id);
		}
		return user;
	}
	
	

}
