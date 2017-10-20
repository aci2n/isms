package isms.sensor;

import drivers.ArduinoDriver;
import isms.models.SensorType;

public class ArduinoTemperatureSensor extends Sensor {

	public ArduinoTemperatureSensor() {
		super(SensorType.TEMPERATURE, new ArduinoDriver());
	}

}
