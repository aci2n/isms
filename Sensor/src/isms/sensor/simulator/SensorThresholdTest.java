package isms.sensor.simulator;

import isms.models.SensorThreshold;
import isms.models.SensorType;

public class SensorThresholdTest {

	public static void main(String[] args) {
		SensorType type = SensorType.TEMPERATURE;

		System.out.println(name(type.control(0)));
		System.out.println(name(type.control(42)));
		System.out.println(name(type.control(46)));
		System.out.println(name(type.control(60)));

		System.out.println();

		System.out.println(name(type.control(-10)));
		System.out.println(name(type.control(-12)));
		System.out.println(name(type.control(-16)));
		System.out.println(name(type.control(-25)));
	}

	private static String name(SensorThreshold threshold) {
		return threshold == null ? "NONE" : threshold.getType().toString();
	}
}
