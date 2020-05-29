package com.masivian.test.mapper;

import com.masivian.test.model.Bet;
import com.masivian.test.model.Roulette;

public interface Mapper {
	
	Bet convertToBet(String payload);
	Roulette convertToRoulette(String payload);

}
