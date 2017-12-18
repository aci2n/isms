package isms.sensor.simulator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import drivers.Driver;
import drivers.NormalSimulatorDriver;
import isms.models.SensorLocation;
import isms.models.SensorType;
import isms.sensor.APIPublisher;
import isms.sensor.Sensor;

public class TestSimulatorSensor {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(10);

		service.submit(() -> {
			int mean = (int) Math.random() * 40;
			int std = 2;
			int sleep = 1000;
			Driver driver = new NormalSimulatorDriver(mean, std, sleep);
			SensorLocation location = new SensorLocation("silo_2", 3);
			Sensor sensor = new Sensor(SensorType.HUMIDITY, driver, new APIPublisher(), location);

			sensor.start();
		});

		service.submit(() -> {
			int mean = (int) Math.random() * 40;
			int std = 2;
			int sleep = 1000;
			Driver driver = new NormalSimulatorDriver(mean, std, sleep);
			SensorLocation location = new SensorLocation("silo_1", 1);
			Sensor sensor = new Sensor(SensorType.HUMIDITY, driver, new APIPublisher(), location);

			sensor.start();
		});
		
		service.submit(() -> {
			int mean = (int) Math.random() * 40;
			int std = 2;
			int sleep = 1000;
			Driver driver = new NormalSimulatorDriver(mean, std, sleep);
			SensorLocation location = new SensorLocation("silo_1", 2);
			Sensor sensor = new Sensor(SensorType.HUMIDITY, driver, new APIPublisher(), location);

			sensor.start();
		});
	}

}
