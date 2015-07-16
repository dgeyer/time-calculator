package com.sciencedirect.interview;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeValidator implements Validable<String> {

	private static final String TIME_PATTERN = "^[0-9]{2,}:[0-5][0-9]:[0-5][0-9]$";

	private final Pattern timePattern = Pattern.compile(TIME_PATTERN);

	private TimeValidator() {
	}

	private static volatile TimeValidator instance = new TimeValidator();

	public static TimeValidator getInstance() {
		if (instance == null) {
			synchronized (TimeCalculator.class) {
				if (instance == null)
					instance = new TimeValidator();
			}
		}
		return instance;
	}

	@Override
	public void validate(String firstTime, String secondTime) {
		StringBuilder error = new StringBuilder();

		if (firstTime != null && secondTime != null) {
			error.append(match(firstTime, secondTime));
		} else {
			error.append(buildNullError(firstTime, secondTime));
		}
		if (error.length() > 0) {
			throw new IllegalArgumentException(error.toString());
		}
	}

	private String buildNullError(String a, String b) {
		StringBuilder error = new StringBuilder();
		if (a == null) {
			error.append("First param is null ");
		}
		if (b == null) {
			error.append("Second param is null");
		}
		return error.toString();
	}

	private String match(String firstTime, String secondTime) {
		StringBuilder error = new StringBuilder();
		Matcher matcherFirstTime = timePattern.matcher(firstTime);
		Matcher matcherSecondTime = timePattern.matcher(secondTime);
		if (!matcherFirstTime.matches()) {
			error.append("First argument does not matches the pattern hh:mm:ss ");
		}
		if (!matcherSecondTime.matches()) {
			error.append("Second argument does not matches the pattern hh:mm:ss");
		}
		return error.toString();
	}

}
