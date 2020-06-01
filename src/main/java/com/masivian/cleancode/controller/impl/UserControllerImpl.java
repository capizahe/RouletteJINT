package com.masivian.cleancode.controller.impl;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.masivian.cleancode.controller.UserController;
import com.masivian.cleancode.mapper.Mapper;
import com.masivian.cleancode.mapper.impl.MapperImpl;
import com.masivian.cleancode.model.User;
import com.masivian.cleancode.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

	private UserRepository userRepository;
	private Mapper mapper;
	
	public UserControllerImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
		this.mapper = new MapperImpl();
	}
	
	/**
	 * This method creates a new user.
	 * @param User json string format.
	 * @return Id of the user.
	 */
	@Override
	@PostMapping("/create")
	public @ResponseBody Long createUser(@RequestBody String user) {
		if(user != null) {	
			User new_user = this.mapper.convertToUser(user);
			return this.userRepository.createUser(new_user);
		}
		return null;
		
	}

	/**
	 * This method returns an specific user by its id
	 * @param Id of the user
	 * @return User
	 */
	@Override
	@GetMapping("/{id}")
	public @ResponseBody User getUserById(@PathVariable("id") Long id) {
		return (id != null)?this.userRepository.getUserById(id):null;
		
	}

	/**
	 * This method returns the actual money of the user.
	 * @param Id of the user.
	 * @return Money of the user.
	 */
	@Override
	@GetMapping("/money/{id}")
	public @ResponseBody Double getUserMoneyById(@PathVariable("id") Long id) {
		return (id != null)?this.userRepository.getUserMoneyById(id):null;
		
	}

	/**
	 * This method updates the actual money of a new user
	 * @param User json string format.
	 * @return
	 */
	@Override
	@PatchMapping("/update")
	public void updateUser(@RequestBody String user) {
		User update_user = this.mapper.convertToUser(user);
		if(this.getUserById(update_user.getId()).getId().equals(update_user.getId()))
			this.userRepository.updateUser(update_user);
	}

	/**
	 * This method returns all the users registered
	 * @return list of users
	 */
	@Override
	@GetMapping("/all")
	public Map<Long, User> getAllUsers() {
		return this.userRepository.getAllUsers();
	}
}
