package com.masivian.cleancode.repository.utils;

public enum Operations {
	ROULETTE("ROULETTE"),
	BET("BET"),
	USER("USER");
	
	
	private String text;
	
	private Operations(String text) {
		this.text = text;
	}
	
	public String getOperation() {
		return this.text;
	}
	
}
