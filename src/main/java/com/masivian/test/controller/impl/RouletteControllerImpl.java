package com.masivian.test.controller.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.masivian.test.controller.RouletteController;
import com.masivian.test.model.Roulette;
import com.masivian.test.repository.BetRepository;
import com.masivian.test.repository.RouletteRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/roulette")
public class RouletteControllerImpl implements RouletteController{

	private RouletteRepository rouletteRepository;
	
	private BetRepository betRepository;

	public RouletteControllerImpl(RouletteRepository rouletteRepository,BetRepository betRepository) {
		this.rouletteRepository = rouletteRepository;
		this.betRepository = betRepository;
	}

	private int numberOfBets = 0;
	
	/**
	 * This method creates a rulette and returns its id.
	 */
	@Override
	@GetMapping("/init")
	public Long createRoulette() {
		return this.rouletteRepository.createRoulette();
	}

	/**
	 *This method starts a specific roulette. 
	 */
	@Override
	@GetMapping("/start")
	public String startRoulette(Long roulette_id) {		
		return this.rouletteRepository.isAvaliableRoulette(roulette_id)?"OPERACION EXITOSA":"OPERACION DENEGADA";
	}
	
	/**
	 * This method stops a specific roulette 
	 */
	@Override
	@GetMapping("/stop/{id}")
	public String stopRoulette(@PathVariable("id")Long roulette_id) {		
		if(this.rouletteRepository.isAvaliableRoulette(roulette_id)) {
		   if(this.rouletteRepository.updateStatus(roulette_id, false))
			   return "OPERACION EXITOSA";
		}
		return "YA SE DETUVO"; 
	}	

	/**
	 * This method returns all the created roulettes
	 */
	@Override
	@GetMapping("/all")
	public Map<Long,Roulette> createdRoulettes() {
		return this.rouletteRepository.getAllRoulettes();
	}
	
	/**
	 * This method counts the number of bet in this roulette game
	 */
	@Override
	@GetMapping("/count-bets/{id}")
	@HystrixCommand(fallbackMethod = "getFallbackRouletteBetCount")
	public @ResponseBody String getNumberOfBetsByRoulette(@PathVariable("id")Long id) {
		this.numberOfBets = 0;
		this.betRepository.getAllBets().forEach((key,bet) ->{
			if(bet.getRouletteId() == id) {
				numberOfBets++;
			}
		});
		return "The number of bets for Roulette "+id+" is "+numberOfBets;
	}
	
	public @ResponseBody String getFallbackRouletteBetCount(@PathVariable("id")Long id) {
		return "Couldn't count the number of bets :c";
	}
	
	

}
