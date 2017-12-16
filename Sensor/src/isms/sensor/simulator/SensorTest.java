package isms.sensor.simulator;

import java.time.Instant;
import java.util.Random;

import isms.common.Constants;
import isms.common.UnirestWrapper;
import isms.models.SensorLocation;
import isms.models.SensorRecord;
import isms.models.SensorType;
import isms.sensor.Config;

public class SensorTest {

	public static void main(String[] args) throws Exception {
		Random random = new Random();
		SensorType[] types = SensorType.values();
		int typesCount = types.length;
		String id = Config.get("sensorId");
		String ownerId = Config.get("ownerId");

		for (int i = 0; i < 100000; i++) {
			long timestamp = Instant.now().getEpochSecond();
			SensorRecord record = new SensorRecord(id, ownerId, types[Math.abs(random.nextInt() % typesCount)],
					timestamp, random.nextDouble() * 100 - 50, new SensorLocation("silo_1", 1, 1, 1));
			UnirestWrapper.post(Constants.API_ENDPOINT_RECORDS).body(record).asBinaryAsync();
			System.out.printf("[%d: %d]%s", i, timestamp, System.lineSeparator());
			Thread.sleep(500);
		}
	}

}
