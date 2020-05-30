package com.masivian.cleancode.repository.impl;

import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.masivian.cleancode.model.Roulette;
import com.masivian.cleancode.repository.RouletteRepository;
import com.masivian.cleancode.repository.utils.Operations;

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

	/**
	 * @return id of the created roulettes
	 */
	@Override
	public long createRoulette() {
		Roulette roulette = new Roulette(Math.abs(new Random().nextLong()));
		roulette.setStatus(true);
		hashOperations.put(Operations.ROULETTE.getOperation(), roulette.getId(), roulette);
		return roulette.getId();
	}

	/**
	 * @param id of the roulette
	 */
	@Override
	public boolean isAvaliableRoulette(Long id) {
		Roulette roulette = hashOperations.get(Operations.ROULETTE.getOperation(), id);
		return roulette.isStatus();
		
	}
	
	/**
	 * @return list of roulettes
	 */
	@Override
	public Map<Long, Roulette> getAllRoulettes() {
		return hashOperations.entries(Operations.ROULETTE.getOperation());
		
	}

	/**
	 * @param id of the roulette
	 * @param desired status for the roulette
	 */
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
