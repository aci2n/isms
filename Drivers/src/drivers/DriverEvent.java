package drivers;

import java.util.HashMap;
import java.util.Map;

public class DriverEvent {

	private double data;
	private Map<String, String> metadata;

	public DriverEvent(double data) {
		this.data = data;
		metadata = new HashMap<>();
	}

	public double getData() {
		return data;
	}

	public void setData(double data) {
		this.data = data;
	}

	public String addMetadata(String key, String value) {
		return metadata.put(key, value);
	}
}
