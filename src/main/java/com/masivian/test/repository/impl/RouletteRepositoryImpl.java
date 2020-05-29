package com.masivian.test.repository.impl;

import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.masivian.test.model.Roulette;
import com.masivian.test.repository.RouletteRepository;
import com.masivian.test.repository.utils.Operations;

@Repository
public class RouletteRepositoryImpl implements RouletteRepository{
	
	private RedisTemplate<String , Roulette> redisTemplate;
	private HashOperations<String, Long, Roulette> hashOperations;
	
	public RouletteRepositoryImpl(RedisTemplate<String,Roulette> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	@PostConstruct
	private void init() {
		this.hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public long createRoulette() {
		Roulette roulette = new Roulette(Math.abs(new Random().nextLong()));
		roulette.setStatus(true);
		hashOperations.put(Operations.ROULETTE.getOperation(), roulette.getId(), roulette);
		return roulette.getId();
	}

	@Override
	public boolean isAvaliableRoulette(Long id) {
		Roulette roulette = hashOperations.get(Operations.ROULETTE.getOperation(), id);
		return roulette.isStatus();
		
	}
	
	@Override
	public Map<Long, Roulette> getAllRoulettes() {
		return hashOperations.entries(Operations.ROULETTE.getOperation());
		
	}

	@Override
	public boolean updateStatus(Long id, boolean status) {
		Roulette roulette = hashOperations.get(Operations.ROULETTE.getOperation(), id); 
		if(roulette != null) {
			roulette.setStatus(status);
			hashOperations.put(Operations.ROULETTE.getOperation(), roulette.getId(), roulette);
			return true;
		}
		return false;
	}

	
}
