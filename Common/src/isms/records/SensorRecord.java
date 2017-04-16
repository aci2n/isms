package isms.records;

public class SensorRecord {

	private SensorType type;
	private long ownerId;
	private long time;
	private double data;

	public SensorRecord(SensorType type, long ownerId, long time, double data) {
		super();
		this.type = type;
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

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
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

}
