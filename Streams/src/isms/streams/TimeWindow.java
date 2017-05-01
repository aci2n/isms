package isms.streams;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;

public enum TimeWindow {
	ONE_SECOND(ChronoField.SECOND_OF_DAY, 1),
	TEN_SECONDS(ChronoField.SECOND_OF_DAY, 10),
	ONE_MINUTE(ChronoField.MINUTE_OF_DAY, 1),
	THIRTY_MINUTES(ChronoField.MINUTE_OF_DAY, 30),
	ONE_HOUR(ChronoField.HOUR_OF_DAY, 1),
	TWELVE_HOURS(ChronoField.HOUR_OF_DAY, 12),
	ONE_DAY(ChronoField.DAY_OF_YEAR, 1),
	SEVEN_DAYS(ChronoField.DAY_OF_YEAR, 7),
	ONE_MONTH(ChronoField.MONTH_OF_YEAR, 1),
	SIX_MONTHS(ChronoField.MONTH_OF_YEAR, 1),
	ONE_YEAR(ChronoField.YEAR, 1);

	private static final ZoneOffset ZONE_OFFSET = ZoneOffset.UTC;

	private TemporalField field;
	private long window;

	private TimeWindow(TemporalField field, long window) {
		this.field = field;
		this.window = window;
	}

	public long allocate(long timestamp) {
		LocalDateTime date = LocalDateTime.ofEpochSecond(timestamp, 0, ZONE_OFFSET);
		TemporalUnit unit = field.getBaseUnit();
		long value = date.get(field);
		long allocation = date.minus(value % window, unit).truncatedTo(unit).toEpochSecond(ZONE_OFFSET);

		return allocation;
	}
}