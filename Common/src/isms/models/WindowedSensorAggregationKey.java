package isms.models;

public class WindowedSensorAggregationKey extends Model {

	private long start;
	private long size;
	private SensorAggregationKey key;

	public WindowedSensorAggregationKey() {}

	public WindowedSensorAggregationKey(long start, long size, SensorAggregationKey key) {
		this.start = start;
		this.size = size;
		this.key = key;
	}

	public long getStart() {
		return start;
	}

	public long getSize() {
		return size;
	}

	public SensorAggregationKey getKey() {
		return key;
	}

	@Override
	protected boolean strictEquals(Object o) {
		WindowedSensorAggregationKey other = (WindowedSensorAggregationKey) o;
		return start == other.getStart() && size == other.getSize() && key.equals(other.getKey());
	}

}
