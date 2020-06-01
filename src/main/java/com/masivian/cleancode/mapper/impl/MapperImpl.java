package com.masivian.cleancode.mapper.impl;

import java.io.IOException;

import javax.annotation.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.masivian.cleancode.mapper.Mapper;
import com.masivian.cleancode.model.Bet;
import com.masivian.cleancode.model.Roulette;

@Resource(name = "mapper")
public class MapperImpl implements Mapper {
	
	private ObjectMapper objMapper = new ObjectMapper();
	
	/**
	 * Recieves a json string and converts it into a bet object.
	 * @param payload
	 * @return Bet
	 */
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

	/**
	 * Recieves a json string and converts it intoa roulette object.
	 * @param payload 
	 * @return Roulette
	 */
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
