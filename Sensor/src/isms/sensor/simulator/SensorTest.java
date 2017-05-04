package isms.sensor.simulator;

import java.time.Instant;

import isms.models.SensorRecord;
import isms.models.SensorType;
import isms.sensor.Client;
import isms.sensor.Config;

public class SensorTest {

	public static void main(String[] args) throws Exception {
		Client client = new Client();

		for (int i = 0; i < 100; i++) {
			SensorRecord record = new SensorRecord(Config.get("sensorId"), Config.get("ownerId"),
					SensorType.TEMPERATURE, Instant.now().getEpochSecond(), 100.10f);
			int status = client.send(record);
			System.out.println(status);
		}
	}

}
