package isms.streams;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.kafka.streams.kstream.Windows;
import org.apache.kafka.streams.kstream.internals.TimeWindow;

public class ChronoWindows extends Windows<TimeWindow> {

	private static final ZoneOffset ZONE_OFFSET = ZoneOffset.UTC;
	public static final List<ChronoField> ALLOWED_FIELDS = Arrays.asList(ChronoField.SECOND_OF_MINUTE,
			ChronoField.MINUTE_OF_HOUR, ChronoField.DAY_OF_MONTH, ChronoField.MONTH_OF_YEAR, ChronoField.YEAR);
	public static final ChronoWindows[] DEFAULT_WINDOWS = {
			of(ChronoField.SECOND_OF_MINUTE, 1),
			of(ChronoField.SECOND_OF_MINUTE, 10),
			of(ChronoField.MINUTE_OF_HOUR, 1),
			of(ChronoField.MINUTE_OF_HOUR, 30),
			of(ChronoField.HOUR_OF_DAY, 1),
			of(ChronoField.HOUR_OF_DAY, 12),
			of(ChronoField.DAY_OF_MONTH, 1),
			of(ChronoField.DAY_OF_MONTH, 7),
			of(ChronoField.MONTH_OF_YEAR, 1),
			of(ChronoField.MONTH_OF_YEAR, 6),
			of(ChronoField.YEAR, 1) };

	private ChronoField field;
	private long size;

	private ChronoWindows(ChronoField field, long size) {
		this.field = field;
		this.size = size;
	}

	public static ChronoWindows of(ChronoField field, long size) {
		if (!ALLOWED_FIELDS.contains(field)) throw new IllegalArgumentException("Invalid ChronoField.");
		if (size < 1) throw new IllegalArgumentException("Size must be equal to or greater than 1.");

		return new ChronoWindows(field, size);
	}

	@Override
	public Map<Long, TimeWindow> windowsFor(long timestamp) {
		final long start = startTime(timestamp);
		final long end = startTime(timestamp + size());

		return Collections.singletonMap(timestamp, new TimeWindow(start, end));

	}

	private long startTime(long timestamp) {
		return LocalDateTime.ofEpochSecond(timestamp, 0, ZONE_OFFSET).with(this::adjust).with(this::truncate)
				.toEpochSecond(ZONE_OFFSET);
	}

	private Temporal adjust(Temporal temporal) {
		final long value = temporal.get(field);
		return temporal.with(field, value - value % size);
	}

	private Temporal truncate(Temporal temporal) {
		LocalDateTime date = LocalDateTime.from(temporal);

		switch (field) {
		case YEAR:
			date = date.withMonth(1);
		case MONTH_OF_YEAR:
			date = date.withDayOfMonth(1);
		case DAY_OF_MONTH:
			date = date.withHour(0);
		case HOUR_OF_DAY:
			date = date.withMinute(0);
		case MINUTE_OF_HOUR:
			date = date.withSecond(0);
		case SECOND_OF_MINUTE:
			date = date.withNano(0);
		default:
			break;
		}

		return date;
	}

	@Override
	public long size() {
		return field.getBaseUnit().getDuration().toMillis() * size;
	}

}
