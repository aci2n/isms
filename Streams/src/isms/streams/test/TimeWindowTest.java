package isms.streams.test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import isms.streams.TimeWindow;

public class TimeWindowTest {

	public static void main(String[] args) {
		testWindow(7, ChronoUnit.DAYS, TimeWindow.ONE_WEEK);
		testWindow(2, ChronoUnit.DAYS, TimeWindow.ONE_WEEK);
		testWindow(13, ChronoUnit.DAYS, TimeWindow.ONE_WEEK);
		testWindow(2, ChronoUnit.DAYS, TimeWindow.ONE_SECOND);
		testWindow(32, ChronoUnit.DAYS, TimeWindow.ONE_MONTH);
		testWindow(10, ChronoUnit.DAYS, TimeWindow.ONE_MONTH);
		testWindow(5 * 365, ChronoUnit.DAYS, TimeWindow.ONE_YEAR);
	}

	public static void testWindow(long value, TemporalUnit unit, TimeWindow window) {
		long seconds = window.getField().getBaseUnit().getDuration().getSeconds();
		Instant now = Instant.now();
		Instant then = Instant.now().plus(value, unit);

		System.out.println("Now: " + now + " - Then: " + then);

		long nowWindow = window.allocate(now.getEpochSecond()) / seconds;
		long thenWindow = window.allocate(then.getEpochSecond()) / seconds;

		System.out.println(window + " - Now: " + nowWindow + " - " + "Then: " + thenWindow + " - Offset: "
				+ (thenWindow - nowWindow));
	}
}
