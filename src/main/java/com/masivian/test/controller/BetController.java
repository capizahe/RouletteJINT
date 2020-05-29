package com.masivian.test.controller;

import java.util.Map;

import com.masivian.test.model.Bet;

public interface BetController {
	
	Long createBet(String userid,String bet);
	Map<Long, Bet> closedBets(Long id);

}
