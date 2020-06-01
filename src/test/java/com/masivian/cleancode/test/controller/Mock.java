package com.masivian.cleancode.test.controller;

import java.util.HashMap;
import java.util.Map;

import com.masivian.cleancode.model.Bet;
import com.masivian.cleancode.model.Roulette;

public class Mock {

	public Roulette initRoulette() {
		Roulette roulette = new Roulette(123456789);
		roulette.setStatus(true);
		return roulette;
	}
	
	public Bet createNumberBet(){
		Bet bet = new Bet();
		bet.setRouletteId(123456789L);
		bet.setActualBet(20000D);
		bet.setNumber(10);
		return bet;
	}
	
	public Bet createColorBet() {
		Bet bet = new Bet();
		bet.setRouletteId(123456789L);
		bet.setActualBet(20000D);
		bet.setColor("RED");
		return bet;
	}
	
	public Map<Long, Roulette> getAllRoulettes(){
		Map<Long,Roulette> roulettes = new HashMap<Long, Roulette>();
		roulettes.put(123456L, initRoulette());
		roulettes.put(123458L, initRoulette());
		roulettes.put(123457L, initRoulette());
		return roulettes;

	}
}
