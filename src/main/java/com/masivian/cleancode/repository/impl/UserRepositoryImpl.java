package com.masivian.cleancode.repository.impl;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.masivian.cleancode.model.User;
import com.masivian.cleancode.repository.UserRepository;
import com.masivian.cleancode.repository.utils.Operations;

@Repository
public class UserRepositoryImpl implements UserRepository{

	private RedisTemplate<String, User> redisTemplate;
	private HashOperations<String, Long, User> hashOperations;
	
	public UserRepositoryImpl(RedisTemplate<String, User> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	@PostConstruct
	public void init(){
		this.hashOperations = redisTemplate.opsForHash();	
	}
	
	@Override
	public Long createUser(User user) {
		this.hashOperations.put(Operations.USER.getOperation(), user.getId(), user);
		return user.getId();
	}

	@Override
	public User getUserById(Long id) {
		return this.hashOperations.entries(Operations.USER.getOperation()).get(id);
	}

	@Override
	public Double getUserMoneyById(Long id) {
		return this.hashOperations.get(Operations.USER.getOperation(), id).getMoney();
	}

	@Override
	public void updateUser(User user) {
		User update = this.getUserById(user.getId());
		update.setMoney(user.getMoney());
		this.hashOperations.put(Operations.USER.getOperation(), update.getId(), update);
		
	}

	@Override
	public Map<Long, User> getAllUsers() {
		return this.hashOperations.entries(Operations.USER.getOperation());
	}

}
