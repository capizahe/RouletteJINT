package com.masivian.cleancode.repository;

import java.util.Map;

import com.masivian.cleancode.model.Bet;

public interface BetRepository {
	
	Long createBet(Bet bet);
	Bet getBet(long id);
	Map<Long,Bet> getAllBets();
	
}
