package isms.sensor.simulator;

import drivers.ArduinoDriver;
import isms.models.SensorLocation;
import isms.models.SensorType;
import isms.sensor.APIPublisher;
import isms.sensor.Sensor;

public class TestArduinoSensor {

	public static void main(String[] args) {
		SensorLocation location = new SensorLocation("silo_1", 2);
		Sensor sensor = new Sensor(SensorType.TEMPERATURE, new ArduinoDriver(), new APIPublisher(), location);

		sensor.start();
	}

}
