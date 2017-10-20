package drivers;

import java.util.HashMap;
import java.util.Map;

public class DriverReading {

	private double data;
	private Map<String, String> metadata;

	DriverReading(double data) {
		this.data = data;
	}

	public double getData() {
		return data;
	}

	public DriverReading addMetadata(String key, String value) {
		if (metadata == null) {
			metadata = new HashMap<>();
		}

		metadata.put(key, value);

		return this;
	}

	public String getMetadata(String key) {
		String value = null;

		if (metadata != null) {
			value = metadata.get(key);
		}

		return value;
	}
}
