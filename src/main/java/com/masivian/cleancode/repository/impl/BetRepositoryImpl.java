package com.masivian.cleancode.repository.impl;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.masivian.cleancode.mapper.Mapper;
import com.masivian.cleancode.mapper.impl.MapperImpl;
import com.masivian.cleancode.model.Bet;
import com.masivian.cleancode.repository.BetRepository;
import com.masivian.cleancode.repository.utils.Operations;

@Repository
public class BetRepositoryImpl implements BetRepository{

	
	private RedisTemplate<String, Bet> reddisTemplate;
	private HashOperations<String, Long, Bet> hashOperations;
	
	public BetRepositoryImpl(RedisTemplate<String, Bet> redisTemplate) {
		this.reddisTemplate = redisTemplate;
	}
	
	@PostConstruct
	public void init() {
		this.hashOperations = reddisTemplate.opsForHash();
	}
	
	@Override
	public Long createBet(Bet bet) {		
		this.hashOperations.put(Operations.BET.getOperation(), bet.getId(), bet);
		return bet.getId();
	}

	@Override
	public Bet getBet(long id) {
		return this.hashOperations.get(Operations.BET.getOperation(),id);
	}

	@Override
	public Map<Long, Bet> getAllBets() {
		return this.hashOperations.entries(Operations.BET.getOperation());
	}
	
	

}
