package isms.sensor.simulator;

import java.time.Instant;
import java.util.Random;

import isms.models.SensorRecord;
import isms.models.SensorType;
import isms.sensor.Client;
import isms.sensor.Config;

public class SensorTest {

	public static void main(String[] args) throws Exception {
		Client client = new Client();
		Random random = new Random();
		SensorType[] types = SensorType.values();
		int typesCount = types.length;
		String id = Config.get("sensorId");
		String ownerId = Config.get("ownerId");

		for (int i = 0; i < 100000; i++) {
			SensorRecord record = new SensorRecord(id, ownerId, types[i % typesCount], Instant.now().getEpochSecond(),
					random.nextDouble() * 100 - 50);
			int status = client.send(record);
			System.out.println(status);
		}
	}

}
