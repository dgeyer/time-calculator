package com.sciencedirect.interview;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TimeValidatorTest {

	Validable<String> timeValidator = TimeValidator.getInstance();
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void ifTimeParameterAreValidShouldNotThrowException() {

		timeValidator.validate("22:35:27", "05:15:56");
		timeValidator.validate("79:35:27", "00:00:00");
		timeValidator.validate("00:00:00", "79:35:27");
		timeValidator.validate("00:00:00", "120:59:59");

	}

	@Test
	public void ifParamIsNullShouldThrowException() {
		exception.expect(IllegalArgumentException.class);
		timeValidator.validate(null, "22:35:27");

		exception.expect(IllegalArgumentException.class);
		timeValidator.validate("22:35:27", null);

		exception.expect(IllegalArgumentException.class);
		timeValidator.validate(null, null);
	}

	@Test
	public void ifMinExceedRangeShouldThrowException() {
		exception.expect(IllegalArgumentException.class);
		timeValidator.validate("22:35:27", "23:60:53");

		exception.expect(IllegalArgumentException.class);
		timeValidator.validate("23:60:53", "22:35:27");

		exception.expect(IllegalArgumentException.class);
		timeValidator.validate("23:60:53", "23:60:53");
	}

	@Test
	public void ifSecExceedRangeShouldThrowException() {
		exception.expect(IllegalArgumentException.class);
		timeValidator.validate("22:35:27", "23:55:60");

		exception.expect(IllegalArgumentException.class);
		timeValidator.validate("23:55:60", "22:35:27");

		exception.expect(IllegalArgumentException.class);
		timeValidator.validate("23:55:60", "23:55:60");
	}

	@Test
	public void ifParamsDoNotHaveRightFormatShouldThrowException() {

		exception.expect(IllegalArgumentException.class);
		timeValidator.validate("22:35:27", "23,60,53");

		exception.expect(IllegalArgumentException.class);
		timeValidator.validate("23,60,53", "22:35:27");

		exception.expect(IllegalArgumentException.class);
		timeValidator.validate("23,60,53", "23,60,53");

		exception.expect(IllegalArgumentException.class);
		timeValidator.validate("22:35:27", "AAAA");

		exception.expect(IllegalArgumentException.class);
		timeValidator.validate("AAAA", "22:35:27");

		exception.expect(IllegalArgumentException.class);
		timeValidator.validate("AAAA", "AAAA");

		exception.expect(IllegalArgumentException.class);
		timeValidator.validate("22:35:27", "AA:05:09");

		exception.expect(IllegalArgumentException.class);
		timeValidator.validate("AA:05:09", "22:35:27");

		exception.expect(IllegalArgumentException.class);
		timeValidator.validate("AA:05:09", "AA:05:09");
	}

	@Test
	public void ifParamsDoNotHaveColonShouldThrowException() {
		exception.expect(IllegalArgumentException.class);
		timeValidator.validate("22:35:27", "234553");

		exception.expect(IllegalArgumentException.class);
		timeValidator.validate("234553", "22:35:27");

		exception.expect(IllegalArgumentException.class);
		timeValidator.validate("234553", "234553");
	}

}
