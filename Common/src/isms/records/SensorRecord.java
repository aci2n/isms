package isms.records;

import isms.common.SafeNumberParser;

public class SensorRecord {

	private String sensorId;
	private String ownerId;
	private SensorType type;
	private long time;
	private double data;

	public SensorRecord(String sensorId, String ownerId, SensorType type, long time, double data) {
		super();
		this.sensorId = sensorId;
		this.ownerId = ownerId;
		this.type = type;
		this.time = time;
		this.data = data;
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public SensorType getType() {
		return type;
	}

	public void setType(SensorType type) {
		this.type = type;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public double getData() {
		return data;
	}

	public void setData(double data) {
		this.data = data;
	}

	public String serialize() {
		return String.join("|", getSensorId(), getOwnerId(), getType().toString(), Long.toString(getTime()),
				Double.toString(getData()));
	}

	public static SensorRecord deserialize(String serialized) {
		if (serialized == null) return null;
		String[] tokens = serialized.split("\\|");
		if (tokens.length < 4) return null;
		String sensorId = tokens[0];
		String ownerId = tokens[1];
		SensorType type = SensorType.valueOf(tokens[2]);
		if (type == null) return null;
		SafeNumberParser parser = new SafeNumberParser();
		Long time = parser.parseLong(tokens[3]);
		if (time == null) return null;
		Double data = parser.parseDouble(tokens[4]);
		if (data == null) return null;

		return new SensorRecord(sensorId, ownerId, type, time, data);
	}
}
