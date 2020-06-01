package com.masivian.cleancode.model;

import java.io.Serializable;

public class Roulette implements Serializable{

	private static final long serialVersionUID = 1L;
	private long id;
	private boolean status;
	private final double max_bet = 10000;
	private int final_result;
	private String final_color;

	public Roulette(long id) {
		this.id = id;
		this.status = true;
	}
	
	public Roulette() {

	}

	public int getFinalResult() {
		return final_result;
	}

	public void setFinalResult(int finalResult) {
		this.final_result = finalResult;
	}

	public String getFinalColor() {
		return final_color;
	}

	public void setFinalColor(String finalColor) {
		this.final_color = finalColor;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((final_color == null) ? 0 : final_color.hashCode());
		result = prime * result + final_result;
		result = prime * result + (int) (id ^ (id >>> 32));
		long temp;
		temp = Double.doubleToLongBits(max_bet);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (status ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Roulette other = (Roulette) obj;
		if (final_color == null) {
			if (other.final_color != null)
				return false;
		} else if (!final_color.equals(other.final_color))
			return false;
		if (final_result != other.final_result)
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(max_bet) != Double.doubleToLongBits(other.max_bet))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	
	
	
}
