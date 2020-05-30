package com.masivian.cleancode.controller;

import java.util.Map;

import com.masivian.cleancode.model.Roulette;

public interface RouletteController {
	Long createRoulette();
	String startRoulette(Long id);
	Map<Long, Roulette> createdRoulettes();
	String getNumberOfBetsByRoulette(Long id);
	String stopRoulette(Long roulette_id);
}

