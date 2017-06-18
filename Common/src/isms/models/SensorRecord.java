package isms.models;

public class SensorRecord extends Model {

	private String sensorId;
	private String ownerId;
	private SensorType type;
	private long time;
	private double data;

	public SensorRecord() {}

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

	public SensorThreshold controlThreshold() {
		return type.control(data);
	}

	@Override
	protected boolean strictEquals(Object o) {
		SensorRecord other = (SensorRecord) o;
		return sensorId.equals(other.getSensorId()) && ownerId.equals(other.getOwnerId())
				&& type.equals(other.getType()) && time == other.getTime() && data == other.getData();
	}

}
