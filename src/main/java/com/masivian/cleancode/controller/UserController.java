package com.masivian.cleancode.controller;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.masivian.cleancode.model.User;

@Repository
public interface UserController {
	Long createUser(String user);
	User getUserById(Long id);
	Double getUserMoneyById(Long id);
	void updateUser(String user);	
	Map<Long,User> getAllUsers();
}
