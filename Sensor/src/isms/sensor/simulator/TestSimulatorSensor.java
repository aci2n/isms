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

	private static void startSensor(SensorType type, String locationId, int section) {
		int mean = (int) Math.random() * 40;
		int std = 2;
		int sleep = 1000;
		Driver driver = new NormalSimulatorDriver(mean, std, sleep);
		SensorLocation location = new SensorLocation(locationId, section);
		Sensor sensor = new Sensor(type, driver, new APIPublisher(), location);
		sensor.start();
	}

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(10);

		service.submit(() -> {
			startSensor(SensorType.HUMIDITY, "silo_1", 1);
		});

		service.submit(() -> {
			startSensor(SensorType.HUMIDITY, "silo_1", 2);
		});
		
		service.submit(() -> {
			startSensor(SensorType.HUMIDITY, "silo_1", 3);
		});
		
		service.submit(() -> {
			startSensor(SensorType.HUMIDITY, "silo_1", 4);
		});
		
		service.submit(() -> {
			startSensor(SensorType.HUMIDITY, "silo_2", 1);
		});
		
		service.submit(() -> {
			startSensor(SensorType.HUMIDITY, "silo_2", 2);
		});
		
		service.submit(() -> {
			startSensor(SensorType.HUMIDITY, "silo_2", 3);
		});
		
		service.submit(() -> {
			startSensor(SensorType.HUMIDITY, "silo_2", 4);
		});
		
		service.submit(() -> {
			startSensor(SensorType.VOLTAGE, "silo_1", 1);
		});
		
		service.submit(() -> {
			startSensor(SensorType.VOLTAGE, "silo_2", 1);
		});
	}

}
