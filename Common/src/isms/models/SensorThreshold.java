package isms.models;

public class SensorThreshold implements Comparable<SensorThreshold> {

	private ThresholdType type;
	private double lower;
	private double upper;

	public SensorThreshold(ThresholdType type, double lower, double upper) {
		if (lower >= upper) throw new IllegalArgumentException("Upper bound must be higher than the lower bound.");
		this.type = type;
		this.lower = lower;
		this.upper = upper;
	}

	public boolean contains(double value) {
		return value >= lower && value <= upper;
	}

	public ThresholdType getType() {
		return type;
	}

	public void setType(ThresholdType type) {
		this.type = type;
	}

	public double getLower() {
		return lower;
	}

	public void setLower(double lower) {
		this.lower = lower;
	}

	public double getUpper() {
		return upper;
	}

	public void setUpper(double upper) {
		this.upper = upper;
	}

	public int compareTo(SensorThreshold other) {
		if (other == null) return -1;
		return getType().getPriority() - other.getType().getPriority();
	}

}
