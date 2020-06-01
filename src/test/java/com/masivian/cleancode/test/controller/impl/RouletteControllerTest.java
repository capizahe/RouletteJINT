package com.masivian.cleancode.test.controller.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.masivian.cleancode.controller.impl.RouletteControllerImpl;
import com.masivian.cleancode.model.Roulette;
import com.masivian.cleancode.repository.RouletteRepository;
import com.masivian.cleancode.test.controller.Mock;

public class RouletteControllerTest {
	
	
	@org.mockito.Mock
	RouletteRepository rouletteRepository;
	
	@InjectMocks
	private RouletteControllerImpl rouletteControler;
	
	Mock mock;	
	
	@Before
	public void init() {
		mock = new Mock();
        MockitoAnnotations.initMocks(this);

	}
	
	@Test
	public void createRoulette() {
		Mockito.when(rouletteRepository.createRoulette()).thenReturn(123456L);
		assertEquals(123456L,rouletteControler.createRoulette());
	}

	@Test
	public void startRoulette() {
		Mockito.when(rouletteRepository.isAvaliableRoulette(123456L)).thenReturn(true);
		assertEquals("OPERACION EXITOSA",rouletteControler.startRoulette(123456L));
	}

	@Test
	public void createdRoulettes() {
		Mockito.when(rouletteRepository.getAllRoulettes()).thenReturn(mock.getAllRoulettes());
		assertEquals(mock.getAllRoulettes(), rouletteControler.createdRoulettes());
	}

	@Test
	public void stopRoulette() {
		Mockito.when(rouletteRepository.isAvaliableRoulette(123456L)).thenReturn(true);
		Mockito.when(rouletteRepository.updateStatus(123456L, false)).thenReturn(true);
		assertEquals("OPERACION EXITOSA", rouletteControler.startRoulette(123456L));
	}
	
	
	

}
