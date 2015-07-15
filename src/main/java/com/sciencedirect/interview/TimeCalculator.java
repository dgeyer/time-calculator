package com.sciencedirect.interview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeCalculator implements Calculable<String> {

	private static final String COLON = ":";
	
	private final Validable<String> timeValidator;
	
	private TimeCalculator(Validable<String> validator){this.timeValidator=validator;};
	
	public static Calculable<String> getInstance(Validable<String> validator){
		return new TimeCalculator(validator);
	}

	public String sum(String a, String b) {
		
		timeValidator.validate(a, b);
		
		String[] splitA = a.split(COLON);
		String[] splitB = b.split(COLON);
		StringBuilder sum = new StringBuilder();
	
		String[] resultArray = sum(splitA, splitB);
		sum.append(resultArray[0]).append(COLON).append(resultArray[1]).append(COLON).append(resultArray[2]);
		return sum.toString();
	}

	private String[] sum(String[] splitA, String[] splitB) {
		String[] result = new String[3];
		int take = 0;
		for (int i = splitA.length - 1; i >= 0; i--) {
			int sum = Integer.valueOf(splitA[i]) + Integer.valueOf(splitB[i]) + take;
			if (i > 0 && sum >= 60) {
				sum = sum - 60;
				take = 1;
			}else {
				take = 0;
			}
			result[i] = i > 0 ? String.format("%02d", sum) : String
					.format("%02d", sum);
		}
		return result;
	}

}
