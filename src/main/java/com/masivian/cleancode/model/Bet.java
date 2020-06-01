package com.masivian.cleancode.model;

import java.io.Serializable;
import java.util.Random;

public class Bet implements Serializable{
	
	private static final long serialVersionUID = -3670324679753342201L;
	private Long id;
	private Integer number;
	private String color;
	private Double actual_bet;
	private Long roulette_id;
	private Long userid;
	
	public Bet(Integer number, String color, Double actual_bet, Long roulette_id) {
		this.id = new Random().nextLong();
		this.number = number;
		this.color = color;
		this.actual_bet = actual_bet;
		this.roulette_id = roulette_id;
	}

	public Bet() {
		this.id = Math.abs(new Random().nextLong());
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getId() {
		return id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getActualBet() {
		return actual_bet;
	}

	public void setActualBet(Double actual_bet) {
		this.actual_bet = actual_bet;
	}

	public Long getRouletteId() {
		return roulette_id;
	}

	public void setRouletteId(Long roulette_id) {
		this.roulette_id = roulette_id;
	}
	
	
}
