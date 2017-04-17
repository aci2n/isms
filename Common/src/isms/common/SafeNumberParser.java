package isms.common;

public class SafeNumberParser {
	public Integer parseInt(String s) {
		Integer value = null;

		try {
			value = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return value;
	}

	public Long parseLong(String s) {
		Long value = null;

		try {
			value = Long.parseLong(s);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return value;
	}

	public Double parseDouble(String s) {
		Double value = null;

		try {
			value = Double.parseDouble(s);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return value;
	}
}
