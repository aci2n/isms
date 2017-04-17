package isms.utils;

import isms.records.SensorRecord;
import isms.records.SensorType;

public class SerializationUtils {
	private static final String DELIM = "|";

	private static String join(String... strings) {
		return String.join(DELIM, strings);
	}

	public static String serialize(SensorRecord record) {
		String serialized = null;

		if (record != null) {
			serialized = join(record.getType().toString(), record.getSensorId(), record.getOwnerId(), Long.toString(record.getTime()), Double.toString(record.getData()));
		}

		return serialized;
	}

	public static SensorRecord deserialize(String serialized) {
		if (serialized == null) return null;

		String[] tokens = serialized.split("\\" + DELIM);
		if (tokens.length < 4) return null;

		SensorType type = SensorType.valueOf(tokens[0]);
		if (type == null) return null;

		String sensorId = tokens[1];
		String ownerId = tokens[2];
		Long time = Utils.tryParseLong(tokens[3]);
		if (time == null) return null;

		Double data = Utils.tryParseDouble(tokens[4]);
		if (data == null) return null;

		return new SensorRecord(type, sensorId, ownerId, time, data);
	}

	public static boolean isValid(String serialized) {
		return deserialize(serialized) != null;
	}
}
