package com.masivian.test.model;

import java.io.Serializable;
import java.util.Random;

public class Bet implements Serializable{
	
	private static final long serialVersionUID = -3670324679753342201L;
	private long id;
	private int number;
	private String color;
	private double actual_bet;
	private long roulette_id;
	
	public Bet(int number, String color, double actual_bet, long roulette_id) {
		super();
		this.id = new Random().nextLong();
		this.number = number;
		this.color = color;
		this.actual_bet = actual_bet;
		this.roulette_id = roulette_id;
	}

	public Bet() {
		
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getActualBet() {
		return actual_bet;
	}

	public void setActualBet(double bet) {
		this.actual_bet = bet;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public long getId() {
		return id;
	}

	public long getRouletteId() {
		return roulette_id;
	}

	public void setRouletteId(long roulette_id) {
		this.roulette_id = roulette_id;
	}
}
