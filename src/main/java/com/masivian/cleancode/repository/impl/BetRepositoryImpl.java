package com.masivian.cleancode.repository.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

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
	
	/**
	 * This method stores bet 
	 */
	@Override
	public Long createBet(Bet bet) {
		this.hashOperations.put(Operations.BET.getOperation(), bet.getId(), bet);
		return bet.getId();
		
	}

	/**
	 * This method returns a bet by its id
	 * @param Bet id
	 * @return Returns an specific bet
	 */
	@Override
	public Bet getBet(Long id) {
		return this.hashOperations.get(Operations.BET.getOperation(),id);
		
	}

	/**
	 * This method returns all the bets
	 * @return List of bets
	 */
	@Override
	public Map<Long, Bet> getAllBets() {
		return this.hashOperations.entries(Operations.BET.getOperation());
		
	}

	/**
	 * This method returns all the bets from a specific roulette
	 * @param Roulette id
	 * @return List of bets by roulette
	 */
	@Override
	public Map<Long, Bet> getAllBetsByRoulette(Long id) {
		Map<Long,Bet> bets = new HashMap<Long, Bet>();
		this.getAllBets().forEach((key,bet) -> {
			if(bet.getRouletteId().equals(id)){
				bets.put(key, bet);
			}
		});
		return bets;
	}

	

	
	

}
