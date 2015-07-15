package com.sciencedirect.interview;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeValidator implements Validable<String> {

	private static final String TIME_PATTERN = "^[0-9]{2,}:[0-5][0-9]:[0-5][0-9]$";

	private final Pattern timePattern = Pattern.compile(TIME_PATTERN);

	private TimeValidator() {
	}

	public static Validable<String> getInstance() {
		return new TimeValidator();
	}

	@Override
	public void validate(String a, String b) {
		Long start = System.currentTimeMillis();
		StringBuilder error = new StringBuilder();

		if (a != null && b != null) {
			error.append(match(a, b));
		} else {
			error.append(buildNullError(a, b));
		}
		if (error.length() > 0) {
			throw new IllegalArgumentException(error.toString());
		}
		System.out.println(" validate input was "
				+ (System.currentTimeMillis() - start));
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

	private String match(String a, String b) {
		StringBuilder error = new StringBuilder();
		Matcher matcherA = timePattern.matcher(a);
		Matcher matcherB = timePattern.matcher(b);
		if (!matcherA.matches()) {
			error.append("First argument does not matches the pattern hh:mm:ss ");
		}
		if (!matcherB.matches()) {
			error.append("Second argument does not matches the pattern hh:mm:ss");
		}
		return error.toString();
	}

}
