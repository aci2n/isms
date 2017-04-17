package isms.records;

public class SensorRecord {
	private SensorType type;
	private String sensorId;
	private String ownerId;
	private long time;
	private double data;

	public SensorRecord(SensorType type, String sensorId, String ownerId, long time, double data) {
		super();
		this.type = type;
		this.sensorId = sensorId;
		this.ownerId = ownerId;
		this.time = time;
		this.data = data;
	}

	public SensorType getType() {
		return type;
	}

	public void setType(SensorType type) {
		this.type = type;
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
	
	public String key() {
		return sensorId + "." + type;
	}
}
