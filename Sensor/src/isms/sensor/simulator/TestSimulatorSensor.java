package isms.sensor.simulator;

import drivers.Driver;
import drivers.NormalSimulatorDriver;
import isms.models.SensorType;
import isms.sensor.APIPublisher;
import isms.sensor.Sensor;

public class TestSimulatorSensor {

	public static void main(String[] args) {
		int mean = (int) Math.random() * 40;
		int std = 2;
		int sleep = 1000;
		Driver driver = new NormalSimulatorDriver(mean, std, sleep);
		Sensor sensor = new Sensor(SensorType.HUMIDITY, driver, new APIPublisher());

		sensor.start();
	}

}
