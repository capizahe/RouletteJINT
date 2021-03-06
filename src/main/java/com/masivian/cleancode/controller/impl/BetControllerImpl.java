package com.masivian.cleancode.controller.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.masivian.cleancode.controller.BetController;
import com.masivian.cleancode.mapper.Mapper;
import com.masivian.cleancode.mapper.impl.MapperImpl;
import com.masivian.cleancode.model.Bet;
import com.masivian.cleancode.model.Roulette;
import com.masivian.cleancode.model.User;
import com.masivian.cleancode.repository.BetRepository;
import com.masivian.cleancode.repository.RouletteRepository;
import com.masivian.cleancode.repository.UserRepository;
import com.masivian.cleancode.repository.utils.Validations;

@RestController
@RequestMapping("/bet")
public class BetControllerImpl implements BetController{

	private BetRepository betRepository;
	private RouletteRepository rouletteRepository;
	private UserRepository userRepository;
	private Mapper mapper;

	public BetControllerImpl(BetRepository betRepository,RouletteRepository rouletteRepository,UserRepository userRepository) {
		this.betRepository = betRepository;
		this.rouletteRepository = rouletteRepository;
		this.userRepository = userRepository;
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
	public @ResponseBody Long createBet(@RequestHeader(value = "USER-ID") String userid, @RequestBody String bet) {
		if(Validations.validateString(userid)) {
			User user = this.userRepository.getUserById(Long.parseLong(userid));
			if(user.getId().equals(Long.parseLong(userid))) {
				Bet new_bet  = this.mapper.convertToBet(bet);
				new_bet.setUserid(user.getId());
				if(rouletteRepository.isAvaliableRoulette(new_bet.getRouletteId())) {
					//Validates if it has enough money
					if(new_bet.getActualBet() < user.getMoney()) { 
						user.setMoney(user.getMoney() - new_bet.getActualBet());
						this.userRepository.updateUser(user);
						return this.betRepository.createBet(new_bet);
					}
				}
			}
		}

		return null;	
	}

	/**
	 * This method retrieves the list of bet
	 * @return list of bets
	 */
	@Override
	@GetMapping(path="/allbets", produces = "Application/json")
	public @ResponseBody Map<Long,Bet> getAllBets(){
		return this.betRepository.getAllBets();
	}

	/**
	 * This method closes the bets for an specific roulettes
	 * @return list of bets
	 * @param id of the roulette
	 */
	@Override
	@GetMapping(path="/close/results/{id}", produces = "Application/json")
	public @ResponseBody Map<Long,Bet> closedBets(@PathVariable("id")Long roulette_id) {
		Map<Long,Bet> winners = new HashMap<Long, Bet>();
		if(this.rouletteRepository.isAvaliableRoulette(roulette_id)) {
			this.rouletteRepository.updateStatus(roulette_id,false); //Closes the roulette
			Roulette final_resoults = this.rouletteRepository.getRoulette(roulette_id);
			this.getAllRouletteBets(roulette_id).forEach((key,bet) -> {
				if(bet.getColor().equals(final_resoults.getFinalColor())) {
					winners.put(key, bet);
				}else if(bet.getNumber().equals(final_resoults.getFinalResult())) {
					winners.put(key, bet);
				}
			});
		}
		return winners;

	}

	/**
	 * This method retrieves the list of bets by roulette
	 * @param roulette_id of the roulette
	 * @return list of bets 
	 */
	@Override
	@GetMapping(path="/all/{id}",produces = "Application/json")	
	public @ResponseBody Map<Long, Bet> getAllRouletteBets(@PathVariable("id")Long roulette_id) {
		if(this.rouletteRepository.isAvaliableRoulette(roulette_id)) 
			return this.betRepository.getAllBetsByRoulette(roulette_id);
		return null;

	}	

}
