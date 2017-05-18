package isms.models;

public class SensorAggregationKey extends Model {

	private String ownerId;
	private SensorType type;
	private long windowSize;

	public SensorAggregationKey() {}

	public SensorAggregationKey(String ownerId, SensorType type, long windowSize) {
		this.ownerId = ownerId;
		this.type = type;
		this.windowSize = windowSize;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public SensorType getType() {
		return type;
	}

	public long getWindowSize() {
		return windowSize;
	}

	@Override
	protected boolean strictEquals(Object o) {
		SensorAggregationKey other = (SensorAggregationKey) o;
		return ownerId.equals(other.getOwnerId()) && type.equals(other.getType());
	}

}
