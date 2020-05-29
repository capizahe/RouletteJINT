package com.masivian.test.repository.utils;

public enum Operations {
	ROULETTE("ROULETTE"),
	BET("BET");
	
	private String text;
	
	private Operations(String text) {
		this.text = text;
	}
	
	public String getOperation() {
		return this.text;
	}
	
}
