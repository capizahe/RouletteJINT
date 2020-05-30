package com.masivian.cleancode.controller;

import java.util.Map;

import com.masivian.cleancode.model.Bet;

public interface BetController {
	
	Long createBet(String userid,String bet);
	Map<Long, Bet> closedBets(Long id);

}
