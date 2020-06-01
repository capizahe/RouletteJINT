package com.masivian.cleancode.repository;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.masivian.cleancode.model.Bet;

@Repository
public interface BetRepository {
	
	Long createBet(Bet bet);
	Bet getBet(long id);
	Map<Long,Bet> getAllBets();
	
}
