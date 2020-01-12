package com.mindtree.shoppingcart.service;

import com.mindtree.shoppingcart.entities.User;
import com.mindtree.shoppingcart.exceptions.InvalidUserException;
import com.mindtree.shoppingcart.exceptions.UserNotFoundException;

/**
 * @author M1044441
 *	user service to perform operation on User
 */
public interface UserService {

	public User addUser(User user) throws InvalidUserException;
	
	//public List<User> findUserByName(String name)throws UserNotFoundException;
	
	public User findById(Integer id) throws UserNotFoundException;
	
}
