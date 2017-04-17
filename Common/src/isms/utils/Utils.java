package isms.utils;

public class Utils {
	public static Integer tryParseInt(String s) {
		Integer value = null;

		try {
			value = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return value;
	}

	public static Long tryParseLong(String s) {
		Long value = null;

		try {
			value = Long.parseLong(s);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return value;
	}

	public static Double tryParseDouble(String s) {
		Double value = null;

		try {
			value = Double.parseDouble(s);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return value;
	}

	public static String kafkaTopic(String ownerId) {
		return Constants.TOPIC_PREFIX + ownerId;
	}
}
