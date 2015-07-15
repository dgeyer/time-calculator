package com.sciencedirect.interview;

import static java.lang.String.format;

public class CalculatorMain {

	public static void main(String args[]) {
		if (args.length != 2) {
			System.out.println("This program should receive two parameters");
			return;
		}
		String a = args[0];
		String b = args[1];

		Calculable<String> calculator = TimeCalculator
				.getInstance(TimeValidator.getInstance());
		String result = calculator.sum(a, b);

		System.out.println(format("%s + %s = %s", a, b, result));
	}
}
