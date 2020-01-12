package com.mindtree.shoppingcart.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.mindtree.shoppingcart.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> fetchByName(@Param("name") String name);

}
