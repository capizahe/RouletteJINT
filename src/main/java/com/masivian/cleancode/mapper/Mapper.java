package com.masivian.cleancode.mapper;

import com.masivian.cleancode.model.Bet;
import com.masivian.cleancode.model.Roulette;

public interface Mapper {
	
	Bet convertToBet(String payload);
	Roulette convertToRoulette(String payload);

}
