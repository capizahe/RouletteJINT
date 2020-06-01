package com.masivian.cleancode.mapper;

import com.masivian.cleancode.model.Bet;
import com.masivian.cleancode.model.Roulette;
import com.masivian.cleancode.model.User;

public interface Mapper {
	
	Bet convertToBet(String payload);
	Roulette convertToRoulette(String payload);
	User convertToUser(String payload);

}
