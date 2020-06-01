package com.masivian.cleancode.controller.impl;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.masivian.cleancode.controller.RouletteController;
import com.masivian.cleancode.model.Roulette;
import com.masivian.cleancode.repository.BetRepository;
import com.masivian.cleancode.repository.RouletteRepository;

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
	 * This method creates a roulette.
	 * @return roulette id
	 */
	@Override
	@GetMapping("/init")
	public @ResponseBody Long createRoulette() {
		return this.rouletteRepository.createRoulette();
	}

	/**
	 *This method starts a specific roulette.
	 *@param roulette id
	 *@return roulette status  
	 */
	@Override
	@GetMapping("/start")
	public @ResponseBody String startRoulette(Long roulette_id) {		
		return this.rouletteRepository.isAvaliableRoulette(roulette_id)?"OPERACION EXITOSA":"OPERACION DENEGADA";
	}
	
	/**
	 * This method stops a specific roulette 
	 * @param roulette id
	 * @return stop roulete status
	 */
	@Override
	@GetMapping("/stop/{id}")
	public @ResponseBody String stopRoulette(@PathVariable("id")Long roulette_id) {		
		if(this.rouletteRepository.isAvaliableRoulette(roulette_id)) {
		   if(this.rouletteRepository.updateStatus(roulette_id, false))
			   return "OPERACION EXITOSA";
		}
		return "YA SE DETUVO"; 
	}	

	/**
	 * This method returns all the created roulettes
	 * @return List of roulettes
	 */
	@Override
	@GetMapping("/all")
	public @ResponseBody Map<Long,Roulette> createdRoulettes() {
		return this.rouletteRepository.getAllRoulettes();
	}
	
	/**
	 * This method counts the number of bet in this roulette game
	 * @param id of roulette to check
	 * @return the number of bet of a corresponding roulette
	 */
	@Override
	@GetMapping("/count-bets/{id}")
	public @ResponseBody String getNumberOfBetsByRoulette(@PathVariable("id")Long id) {
		this.numberOfBets = 0;
		this.betRepository.getAllBets().forEach((key,bet) ->{
			if(bet.getRouletteId() == id) {
				numberOfBets++;
			}
		});
		return "The number of bets for Roulette "+id+" is "+numberOfBets;
	}
	
	/**
	 * @param id of the roulette
	 * @return default message if error
	 */
	public @ResponseBody String getFallbackRouletteBetCount(@PathVariable("id")Long id) {
		return "Couldn't count the number of bets :c";
	}
	
	

}
