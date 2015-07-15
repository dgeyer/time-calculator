package com.sciencedirect.interview;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

public class TimeCalculatorTest {

	Calculable<String> timeCalculator = TimeCalculator.getInstance(TimeValidator.getInstance());

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void shouldReturnTwoTwoTwo() {
		String result = timeCalculator.sum("01:01:01", "01:01:01");
		Assert.assertEquals("02:02:02", result);
	}

	@Test
	public void shouldReturnOneOneOne() {
		String result = timeCalculator.sum("23:59:59", "01:01:01");
		Assert.assertEquals("25:01:00", result);
	}

	@Test
	public void ifSecondsAreMoreThanSixtyBy5ShouldAddMinutes() {
		String result = timeCalculator.sum("01:01:55", "01:01:10");
		Assert.assertEquals("02:03:05", result);
	}

	@Test
	public void ifSecondsAreMoreThanSixtyBy20ShouldAddMinutes() {
		String result = timeCalculator.sum("01:01:40", "01:01:40");
		Assert.assertEquals("02:03:20", result);
	}

	@Test
	public void ifMinutesAreMoreThanSixtyBy5ShouldAddHours() {
		String result = timeCalculator.sum("01:55:01", "01:10:01");
		Assert.assertEquals("03:05:02", result);
	}

	@Test
	public void ifMinutesAreMoreThanSixtyBy20ShouldAddHours() {
		String result = timeCalculator.sum("01:40:01", "01:40:01");
		Assert.assertEquals("03:20:02", result);
	}

	@Test
	public void ifSecondsMinutesAreMoreThanSixtyBy5ShouldAddHoursAndMinutes() {
		String result = timeCalculator.sum("01:55:55", "01:10:10");
		Assert.assertEquals("03:06:05", result);
	}

	@Test
	public void ifHoursAreMoreThan24ShouldBeginFrom0() {
		String result = timeCalculator.sum("23:01:01", "01:01:01");
		Assert.assertEquals("24:02:02", result);
	}

	@Test
	public void ifAddingaDayShouldReturnTheSame() {
		String result = timeCalculator.sum("23:01:01", "24:00:00");
		Assert.assertEquals("47:01:01", result);
	}

	@Test
	public void ifSecondsMinutesAreMoreThan60AndHourMoreThan24By5ShouldAddAll() {
		String result = timeCalculator.sum("23:45:53", "22:35:27");
		Assert.assertEquals("46:21:20", result);
	}

	@Test
	public void shouldCallValidate() {
		TimeValidator timeValidator = Mockito.mock(TimeValidator.class);
		timeCalculator = TimeCalculator.getInstance(timeValidator);
		String a = "01:01:01";
		String b = "01:01:01";
		timeCalculator.sum(a,b);
		Mockito.verify(timeValidator, Mockito.times(1)).validate(a,b);

	}
}
