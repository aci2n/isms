package isms.common;

public class SafeNumberParser {

	private interface NumberParser<T> {

		public T parse(String s) throws NumberFormatException;
	}

	public Integer parseInt(String s) {
		return parseNumber(s, Integer::parseInt);
	}

	public Long parseLong(String s) {
		return parseNumber(s, Long::parseLong);
	}

	public Double parseDouble(String s) {
		return parseNumber(s, Double::parseDouble);
	}

	private <T> T parseNumber(String s, NumberParser<T> parser) {
		T value = null;

		try {
			value = parser.parse(s);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return value;
	}
}
