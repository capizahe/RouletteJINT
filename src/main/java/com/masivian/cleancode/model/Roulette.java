package com.masivian.cleancode.model;

import java.io.Serializable;

public class Roulette implements Serializable{

	private static final long serialVersionUID = 1L;
	private long id;
	private boolean status;
	private final double max_bet = 10000;

	public Roulette(long id) {
		this.id = id;
		this.status = true;
	}

	public Roulette() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public double getMaxBet() {
		return max_bet;
	}
}
