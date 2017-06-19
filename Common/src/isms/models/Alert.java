package isms.models;

public class Alert extends Model {

	private Long id;
	private String sensorId;
	private String ownerId;
	private SensorType type;
	private SensorThreshold threshold;
	private double data;
	private long timestamp;
	private boolean isRead;

	public Alert() {}

	public Alert(Long id, String sensorId, String ownerId, SensorType type, SensorThreshold threshold, double data,
			long timestamp, boolean isRead) {
		this.id = id;
		this.sensorId = sensorId;
		this.ownerId = ownerId;
		this.type = type;
		this.threshold = threshold;
		this.data = data;
		this.timestamp = timestamp;
		this.isRead = isRead;
	}

	public Alert(String sensorId, String ownerId, SensorType type, SensorThreshold threshold, double data,
			long timestamp, boolean isRead) {
		this(null, sensorId, ownerId, type, threshold, data, timestamp, isRead);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public SensorThreshold getThreshold() {
		return threshold;
	}

	public void setThreshold(SensorThreshold threshold) {
		this.threshold = threshold;
	}

	public double getData() {
		return data;
	}

	public void setData(double data) {
		this.data = data;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

}
