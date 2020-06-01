package com.masivian.cleancode.repository;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.masivian.cleancode.model.User;

@Repository
public interface UserRepository {
	Long createUser(User user);
	User getUserById(Long id);
	Double getUserMoneyById(Long id);
	void updateUser(User user);
	Map<Long,User> getAllUsers();
}
