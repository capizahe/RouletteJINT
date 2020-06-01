package com.masivian.cleancode.test.controller.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.masivian.cleancode.controller.impl.BetControllerImpl;
import com.masivian.cleancode.mapper.Mapper;
import com.masivian.cleancode.model.Bet;
import com.masivian.cleancode.repository.BetRepository;
import com.masivian.cleancode.repository.RouletteRepository;
import com.masivian.cleancode.repository.UserRepository;
import com.masivian.cleancode.repository.utils.Validations;
import com.masivian.cleancode.test.controller.Mock;

public class BetControllerTest {
	
	@org.mockito.Mock
	private BetRepository betRepository;
	
	@org.mockito.Mock
	private UserRepository userRepository;
	
	@org.mockito.Mock
	private Mapper mapper;
	
	@org.mockito.Mock
	private RouletteRepository rouletteRepository;
	
	@org.mockito.Mock
	private Validations validations;
	
	
	@InjectMocks
	private BetControllerImpl betController;
	
	Mock mock;
	
	@Before
	public void init() {
		this.mock = new com.masivian.cleancode.test.controller.Mock();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllBets() {
		Map<Long, Bet> betsMock = this.mock.getAllBets();
		Mockito.when(this.betRepository.getAllBets()).thenReturn(betsMock);
		assertEquals(betsMock.size(), this.betController.getAllBets().size());
	}

	@Test
	public void getAllRouletteBets() {
		Map<Long, Bet> betsMock = this.mock.getAllBets();
		Mockito.when(this.rouletteRepository.isAvaliableRoulette(123456L)).thenReturn(true);
		Mockito.when(this.betRepository.getAllBetsByRoulette(123456L)).thenReturn(betsMock);
		assertEquals(betsMock.size(), this.betController.getAllRouletteBets(123456L).size());
	}

}
