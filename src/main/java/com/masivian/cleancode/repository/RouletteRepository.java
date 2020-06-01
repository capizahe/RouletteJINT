package com.masivian.cleancode.repository;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.masivian.cleancode.model.Roulette;

@Repository
public interface RouletteRepository {
	
	long createRoulette();
	boolean isAvaliableRoulette(Long id);
	Map<Long, Roulette> getAllRoulettes();
	boolean updateStatus(Long id, boolean status);
}
