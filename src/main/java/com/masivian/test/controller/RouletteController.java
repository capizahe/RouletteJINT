package com.masivian.test.controller;

import java.util.Map;

import com.masivian.test.model.Roulette;

public interface RouletteController {
	Long createRoulette();
	String startRoulette(Long id);
	Map<Long, Roulette> createdRoulettes();
	String getNumberOfBetsByRoulette(Long id);
	String stopRoulette(Long roulette_id);
}

