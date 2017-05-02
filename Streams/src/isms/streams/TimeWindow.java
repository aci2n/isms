package isms.streams;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;

public enum TimeWindow {
	ONE_SECOND(ChronoField.SECOND_OF_DAY, 1),
	TEN_SECONDS(ChronoField.SECOND_OF_DAY, 10),
	ONE_MINUTE(ChronoField.MINUTE_OF_DAY, 1),
	THIRTY_MINUTES(ChronoField.MINUTE_OF_DAY, 30),
	ONE_HOUR(ChronoField.HOUR_OF_DAY, 1),
	TWELVE_HOURS(ChronoField.HOUR_OF_DAY, 12),
	ONE_DAY(ChronoField.DAY_OF_YEAR, 1),
	ONE_WEEK(ChronoField.ALIGNED_WEEK_OF_YEAR, 1),
	ONE_MONTH(ChronoField.MONTH_OF_YEAR, 1),
	SIX_MONTHS(ChronoField.MONTH_OF_YEAR, 6),
	ONE_YEAR(ChronoField.YEAR, 1);

	private static final ZoneOffset ZONE_OFFSET = ZoneOffset.UTC;

	private ChronoField field;
	private long window;

	private TimeWindow(ChronoField field, long window) {
		this.field = field;
		this.window = window;
	}

	public long allocate(long timestamp) {
		return LocalDateTime.ofEpochSecond(timestamp, 0, ZONE_OFFSET).with(this::adjust).with(this::truncate)
				.toEpochSecond(ZONE_OFFSET);
	}

	private Temporal adjust(Temporal temporal) {
		long value = temporal.get(field);
		return temporal.with(field, value - value % window);
	}

	/**
	 * LocalDateTime truncation that works with units larger than a single day.
	 * Just need to keep in mind that any ChronoField used in a TimeWindow
	 * instance has to be included in this method's switch statement.
	 * 
	 * @param temporal
	 * @return LocalDateTime instance truncated to this TimeWindow's field
	 */
	private Temporal truncate(Temporal temporal) {
		LocalDateTime date = LocalDateTime.from(temporal);

		switch (field) {
		case YEAR:
			date = date.withMonth(1);
		case MONTH_OF_YEAR:
			date = date.withDayOfMonth(1);
		case ALIGNED_WEEK_OF_YEAR:
		case DAY_OF_YEAR:
			date = date.withHour(0);
		case HOUR_OF_DAY:
			date = date.withSecond(0);
		case SECOND_OF_DAY:
			date = date.withNano(0);
		default:
			break;
		}

		return date;
	}

	public ChronoField getField() {
		return field;
	}

	public long getWindow() {
		return window;
	}
}