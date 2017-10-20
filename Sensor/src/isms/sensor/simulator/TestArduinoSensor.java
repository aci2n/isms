package isms.sensor.simulator;

import drivers.ArduinoDriver;
import isms.models.SensorType;
import isms.sensor.RecordLogger;
import isms.sensor.Sensor;

public class TestArduinoSensor {

	public static void main(String[] args) {
		Sensor sensor = new Sensor(SensorType.TEMPERATURE, new ArduinoDriver(), new RecordLogger());

		sensor.start();
	}

}
