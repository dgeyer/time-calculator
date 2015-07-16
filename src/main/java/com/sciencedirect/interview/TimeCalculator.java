package com.sciencedirect.interview;

public class TimeCalculator implements Calculable<String> {

	private static final String COLON = ":";

	private Validable<String> timeValidator;

	private TimeCalculator(Validable<String> timeValidator) {
		this.timeValidator = timeValidator;
	}

	public static TimeCalculator getNewInstance(Validable<String> timeValidator) {
		return new TimeCalculator(timeValidator);
	}

	public String sum(String firstTime, String secondTime) {

		timeValidator.validate(firstTime, secondTime);

		String[] splitFirstTimeString = firstTime.split(COLON);
		String[] splitSecondTimeString = secondTime.split(COLON);
		StringBuilder sum = new StringBuilder();

		String[] resultArray = sum(splitFirstTimeString, splitSecondTimeString);
		sum.append(resultArray[0]).append(COLON).append(resultArray[1])
				.append(COLON).append(resultArray[2]);
		return sum.toString();
	}

	private String[] sum(String[] splitA, String[] splitB) {
		String[] result = new String[3];
		int take = 0;
		for (int i = splitA.length - 1; i >= 0; i--) {
			int sum = Integer.valueOf(splitA[i]) + Integer.valueOf(splitB[i])
					+ take;
			if (i > 0 && sum >= 60) {
				sum = sum - 60;
				take = 1;
			} else {
				take = 0;
			}
			result[i] = i > 0 ? String.format("%02d", sum) : String.format(
					"%02d", sum);
		}
		return result;
	}

}
