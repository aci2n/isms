package isms.models;

public class WindowedMetric {

	private SensorAggregationKey key;
	private SensorMetric metric;
	private long windowSize;
	private long windowStart;

	public WindowedMetric(SensorAggregationKey key, SensorMetric metric, long windowSize, long windowStart) {
		this.key = key;
		this.metric = metric;
		this.windowSize = windowSize;
		this.windowStart = windowStart;
	}

	public SensorAggregationKey getKey() {
		return key;
	}

	public void setKey(SensorAggregationKey key) {
		this.key = key;
	}

	public SensorMetric getMetric() {
		return metric;
	}

	public void setMetric(SensorMetric metric) {
		this.metric = metric;
	}

	public long getWindowSize() {
		return windowSize;
	}

	public void setWindowSize(long windowSize) {
		this.windowSize = windowSize;
	}

	public long getWindowStart() {
		return windowStart;
	}

	public void setWindowStart(long windowStart) {
		this.windowStart = windowStart;
	}

}
