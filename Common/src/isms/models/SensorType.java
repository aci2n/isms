package isms.models;

import java.util.Set;
import java.util.TreeSet;

public enum SensorType {
	TEMPERATURE(new SensorThreshold(ThresholdType.DANGER, -15, 45), new SensorThreshold(ThresholdType.WARNING, -10, 40),
			new SensorThreshold(ThresholdType.CRITICAL, -20, 50)),
	HUMIDITY(new SensorThreshold(ThresholdType.DANGER, -15, 45), new SensorThreshold(ThresholdType.WARNING, -10, 40),
			new SensorThreshold(ThresholdType.CRITICAL, -20, 50)),
	DUST(new SensorThreshold(ThresholdType.DANGER, -15, 45), new SensorThreshold(ThresholdType.WARNING, -10, 40),
			new SensorThreshold(ThresholdType.CRITICAL, -20, 50)),
	VOLTAGE(new SensorThreshold(ThresholdType.DANGER, -15, 45), new SensorThreshold(ThresholdType.WARNING, -10, 40),
			new SensorThreshold(ThresholdType.CRITICAL, -20, 50));

	private Set<SensorThreshold> thresholds;

	SensorType(SensorThreshold... thresholds) {
		this.thresholds = new TreeSet<>();

		for (SensorThreshold threshold : thresholds) {
			this.thresholds.add(threshold);
		}
	}

	public SensorThreshold control(double value) {
		SensorThreshold result = null;

		for (SensorThreshold threshold : thresholds) {
			if (!threshold.contains(value)) {
				result = threshold;
			} else {
				break;
			}
		}

		return result;
	}
}