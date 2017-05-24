package isms.sensor.simulator;

import java.time.Instant;
import java.util.Random;

import isms.common.Client;
import isms.models.SensorRecord;
import isms.models.SensorType;
import isms.sensor.Config;

public class SensorTest {

	public static void main(String[] args) throws Exception {
		Client client = new Client("record");
		Random random = new Random();
		SensorType[] types = SensorType.values();
		int typesCount = types.length;
		String id = Config.get("sensorId");
		String ownerId = Config.get("ownerId");

		for (int i = 0; i < 100000; i++) {
			long timestamp = Instant.now().getEpochSecond();
			SensorRecord record = new SensorRecord(id, ownerId, types[Math.abs(random.nextInt() % typesCount)],
					timestamp, random.nextDouble() * 100 - 50);
			client.post(record);
			System.out.printf("[%d: %d]%s", i, timestamp, System.lineSeparator());

			if (i % 100 == 0) {
				System.out.println("Sleeping...");
				Thread.sleep(1000);
			}
		}
	}

}
