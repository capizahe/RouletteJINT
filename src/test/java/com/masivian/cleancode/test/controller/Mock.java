package com.masivian.cleancode.test.controller;

import java.util.HashMap;
import java.util.Map;

import com.masivian.cleancode.model.Bet;
import com.masivian.cleancode.model.Roulette;
import com.masivian.cleancode.model.User;

public class Mock {
	
	public Mock() {
		
	}

	public Roulette initRoulette() {
		Roulette roulette = new Roulette(123456789);
		roulette.setStatus(true);
		return roulette;
	}
	
	public String getPayloadBet() {
		return "{\r\n" + 
				"	\"color\":\"RED\",\r\n" + 
				"	\"actualBet\":2500,\r\n" + 
				"	\"rouletteId\":7291557986181969165\r\n" + 
				"}";	
	}
	
	public Bet createNumberBet(){
		Bet bet = new Bet();
		bet.setRouletteId(7291557986181969165L);
		bet.setActualBet(2000D);
		bet.setNumber(10);
		bet.setUserid(123456L);
		return bet;
	}
	
	
	
	public Bet createColorBet() {
		Bet bet = new Bet();
		bet.setRouletteId(7291557986181969165L);
		bet.setActualBet(2000D);
		bet.setColor("RED");
		bet.setUserid(123456L);
		return bet;
	}
	
	public Map<Long, Bet> getAllBets(){
		Map<Long,Bet> bets = new HashMap<Long, Bet>();
		bets.put(123456L,createColorBet());
		bets.put(159123L, createNumberBet());
		return bets;
	}
	public Map<Long, Roulette> getAllRoulettes(){
		Map<Long,Roulette> roulettes = new HashMap<Long, Roulette>();
		roulettes.put(123456L, initRoulette());
		roulettes.put(123458L, initRoulette());
		roulettes.put(123457L, initRoulette());
		return roulettes;

	}
	
	public  User getUser() {
		User user = new User();
		user.setId(123456L);
		user.setMoney(250000D);
		return user;
	}
}
