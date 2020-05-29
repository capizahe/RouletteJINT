package com.masivian.test.controller.impl;

import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.masivian.test.controller.BetController;
import com.masivian.test.model.Bet;
import com.masivian.test.repository.BetRepository;

@Controller
@RequestMapping("/bet")
public class BetControllerImpl implements BetController{
		
	private BetRepository betRepository;
		
	public BetControllerImpl(BetRepository betRepository) {
		this.betRepository = betRepository;
	}
	
	/**
	 *This method creates a bet for an specific roulette
	 */
	@Override
	@PostMapping(path="/create", produces = "Application/json")
	public @ResponseBody Long createBet(@RequestHeader(name = "USER-ID") String userid, @RequestBody String bet) {	
		return this.betRepository.createBet(bet);	
	}

	/**
	 * This method closes the bets for an specific roulettes
	 */
	@Override
	@GetMapping(path="/all/{id}",produces = "Application/json")
	public @ResponseBody Map<Long,Bet> closedBets(@PathParam("id")Long roulette_id) {
		
		return this.betRepository.getAllBets();
	}
}
