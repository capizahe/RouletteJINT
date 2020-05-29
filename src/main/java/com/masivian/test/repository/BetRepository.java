package com.masivian.test.repository;

import java.util.Map;

import com.masivian.test.model.Bet;

public interface BetRepository {
	
	Long createBet(String new_bet);
	Bet getBet(long id);
	Map<Long,Bet> getAllBets();
	
}
