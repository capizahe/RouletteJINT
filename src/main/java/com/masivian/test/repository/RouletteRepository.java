package com.masivian.test.repository;

import java.util.Map;
import com.masivian.test.model.Roulette;

public interface RouletteRepository {
	
	long createRoulette();
	boolean isAvaliableRoulette(Long id);
	Map<Long, Roulette> getAllRoulettes();
	boolean updateStatus(Long id, boolean status);
}
