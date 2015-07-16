package com.sciencedirect.interview;

import static java.lang.String.format;

public class CalculatorMain {

	public static void main(String args[]) {
		if (args.length != 2) {
			System.out
					.println("This program should receive two parameters in the hh:mm:ss format");
			return;
		}
		String firstTime = args[0];
		String secondTime = args[1];

		Calculable<String> calculator = TimeCalculator
				.getNewInstance(TimeValidator.getInstance());
		String result = calculator.sum(firstTime, secondTime);

		System.out
				.println(format("%s + %s = %s", firstTime, secondTime, result));
	}
}
