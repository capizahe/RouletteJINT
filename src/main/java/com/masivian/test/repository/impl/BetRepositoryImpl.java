package com.masivian.test.repository.impl;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import com.masivian.test.mapper.Mapper;
import com.masivian.test.mapper.impl.MapperImpl;
import com.masivian.test.model.Bet;
import com.masivian.test.repository.BetRepository;
import com.masivian.test.repository.utils.Operations;

@Repository
public class BetRepositoryImpl implements BetRepository{

	private Mapper mapper;
	
	private RedisTemplate<String, Bet> reddisTemplate;
	private HashOperations<String, Long, Bet> hashOperations;
	
	public BetRepositoryImpl(RedisTemplate<String, Bet> redisTemplate) {
		this.reddisTemplate = redisTemplate;
		this.mapper = new MapperImpl();
	}
	
	@PostConstruct
	public void init() {
		this.hashOperations = reddisTemplate.opsForHash();
	}
	
	@Override
	public Long createBet(@RequestBody String payload) {
		Bet bet  = this.mapper.convertToBet(payload);
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
