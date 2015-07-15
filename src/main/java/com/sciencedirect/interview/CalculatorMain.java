package com.sciencedirect.interview;

import static java.lang.String.format;

public class CalculatorMain {

    public static void main(String args[]) {
        String a = args[0];
        String b = args[1];

    	Calculable<String> calculator = TimeCalculator.getInstance();
        String result = calculator.sum(a, b);

        System.out.println(format("%s + %s = %s", a, b, result));
    }
}
