package records;

public abstract class SensorRecord {

	private String ownerId;
	private long time;
	private SensorType type;

	public SensorRecord(String ownerId, long time, SensorType type) {
		super();
		this.ownerId = ownerId;
		this.time = time;
		this.type = type;
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

	public SensorType getType() {
		return type;
	}

	public void setType(SensorType type) {
		this.type = type;
	}

}
