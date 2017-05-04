package isms.common;

import isms.records.SensorRecord;

public class SensorMetric {

	private long count;
	private double average;
	private double min;
	private double max;

	public SensorMetric() {}

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
