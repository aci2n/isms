package isms.records;

public class SensorRecordSerde {

	public static String serialize(SensorRecord record) {
		if (record == null) {
			throw new IllegalArgumentException("Cannot serialize null record.");
		}

		return String.join("|", record.getType().toString(), Long.toString(record.getOwnerId()), Long.toString(record.getTime()),
				Double.toString(record.getData()));
	}

	public static SensorRecord deserialize(String serialized) {
		String[] tokens = serialized.split("\\|");

		if (tokens.length < 4) {
			throw new IllegalArgumentException("Incorrent amount of tokens.");
		}

		SensorType type = SensorType.valueOf(tokens[0]);
		if (type == null) {
			throw new IllegalArgumentException("Could not determine sensor type.");
		}

		Long ownerId = tryParseLong(tokens[1]);
		if (ownerId == null) {
			throw new IllegalArgumentException("Could not parse owner ID.");
		}

		Long time = tryParseLong(tokens[2]);
		if (time == null) {
			throw new IllegalArgumentException("Could not parse time.");
		}

		Double data = tryParseDouble(tokens[3]);
		if (data == null) {
			throw new IllegalArgumentException("Could not parse data.");
		}

		return new SensorRecord(type, ownerId, time, data);
	}

	private static Long tryParseLong(String s) {
		Long value = null;

		try {
			value = Long.parseLong(s);
		} catch (NumberFormatException e) {
		}

		return value;
	}

	private static Double tryParseDouble(String s) {
		Double value = null;

		try {
			value = Double.parseDouble(s);
		} catch (NumberFormatException e) {
		}

		return value;
	}

	public static boolean isValid(String serialized) {
		boolean valid = false;

		try {
			deserialize(serialized);
			valid = true;
		} catch (IllegalArgumentException e) {
		}

		return valid;
	}
}
