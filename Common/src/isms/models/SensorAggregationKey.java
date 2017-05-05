package isms.models;

public class SensorAggregationKey extends Model {

	private String ownerId;
	private SensorType type;

	public SensorAggregationKey() {}

	public SensorAggregationKey(String ownerId, SensorType type) {
		this.ownerId = ownerId;
		this.type = type;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public SensorType getType() {
		return type;
	}

	@Override
	protected boolean strictEquals(Object o) {
		SensorAggregationKey other = (SensorAggregationKey) o;
		return ownerId.equals(other.getOwnerId()) && type.equals(other.getType());
	}

}
