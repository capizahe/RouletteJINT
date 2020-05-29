package com.masivian.test.mapper.impl;

import java.io.IOException;

import javax.annotation.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.masivian.test.mapper.Mapper;
import com.masivian.test.model.Bet;
import com.masivian.test.model.Roulette;

@Resource(name = "mapper")
public class MapperImpl implements Mapper {
	
	private ObjectMapper objMapper = new ObjectMapper();
	
	@Override
	public Bet convertToBet(String payload) {
		Bet bet = null;
		try {
			bet = objMapper.readValue(payload, Bet.class);
		} catch (IOException e) {
			System.err.println(e);
		}	
		return bet;
	}

	@Override
	public Roulette convertToRoulette(String payload) {
		Roulette roulette = null;
		try {
			roulette = objMapper.readValue(payload, Roulette.class);
		} catch (IOException e) {
			System.err.println(e);
		}	
		return roulette;
	}

}
