package com.masivian.cleancode.repository.utils;

public class Validations {
	
	public static boolean validateString(String word) {
		return (!word.isEmpty() && !word.equals("") && word != null);
	}

}
