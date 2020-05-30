package com.masivian.cleancode.controller.impl;

import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.masivian.cleancode.controller.BetController;
import com.masivian.cleancode.mapper.Mapper;
import com.masivian.cleancode.mapper.impl.MapperImpl;
import com.masivian.cleancode.model.Bet;
import com.masivian.cleancode.repository.BetRepository;
import com.masivian.cleancode.repository.RouletteRepository;

@Controller
@RequestMapping("/bet")
public class BetControllerImpl implements BetController{

	private BetRepository betRepository;
	private RouletteRepository rouletteRepository;
	private Mapper mapper;

	public BetControllerImpl(BetRepository betRepository,RouletteRepository rouletteRepository) {
		this.betRepository = betRepository;
		this.rouletteRepository = rouletteRepository;
		this.mapper = new MapperImpl();
	}

	/**
	 *This method creates a bet for an specific roulette
	 *@param userid user id 
	 *@param bet bet to create
	 *@return bet id
	 */
	@Override
	@PostMapping(path="/create", produces = "Application/json")
	public @ResponseBody Long createBet(@RequestHeader(name = "USER-ID") String userid, @RequestBody String bet) {
		if(userid != null) {
			Bet new_bet  = this.mapper.convertToBet(bet);
			if(rouletteRepository.isAvaliableRoulette(new_bet.getRouletteId())) {
				return this.betRepository.createBet(new_bet);
			}
		}
		return null;	
	}

	/**
	 * This method closes the bets for an specific roulettes
	 * @return list of bets
	 * @param id of the roulette
	 */
	@Override
	@GetMapping(path="/all/{id}",produces = "Application/json")
	public @ResponseBody Map<Long,Bet> closedBets(@PathParam("id")Long roulette_id) {
		return this.betRepository.closeBet();
	}
}
