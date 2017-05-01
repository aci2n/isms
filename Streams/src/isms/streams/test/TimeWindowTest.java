package isms.streams.test;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import isms.streams.TimeWindow;

public class TimeWindowTest {
	public static void main(String[] args) {
		testWindow(7, ChronoUnit.DAYS, TimeWindow.SEVEN_DAYS);
		testWindow(13, ChronoUnit.DAYS, TimeWindow.SEVEN_DAYS);
		testWindow(2, ChronoUnit.DAYS, TimeWindow.ONE_SECOND);
		testWindow(2, ChronoUnit.MONTHS, TimeWindow.ONE_MONTH);
		testWindow(5, ChronoUnit.YEARS, TimeWindow.ONE_YEAR);
	}

	public static boolean testWindow(long value, TemporalUnit unit, TimeWindow window) {
		Duration duration = Duration.of(value, unit);

		Instant now = Instant.now();
		Instant then = Instant.now().plus(duration);

		long nowWindow = window.allocate(now.getEpochSecond());
		long thenWindow = window.allocate(then.getEpochSecond());

		long expectedDiff = duration.getSeconds();
		long diff = thenWindow - nowWindow;

		System.out.println(window + " - Expected: " + expectedDiff + " - " + "Got: " + diff);

		return expectedDiff == diff;
	}
}
