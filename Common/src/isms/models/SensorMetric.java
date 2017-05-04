package isms.models;

public class SensorMetric {

	private long count;
	private double average;
	private double min;
	private double max;

	public SensorMetric() {
		min = Long.MAX_VALUE;
		max = Long.MIN_VALUE;
	}

	public SensorMetric update(SensorRecord record) {
		double data = record.getData();
		average = (average * count + data) / ++count;
		min = Math.min(min, data);
		max = Math.max(max, data);

		return this;
	}

	public long getCount() {
		return count;
	}

	public double getAverage() {
		return average;
	}

	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}

}
