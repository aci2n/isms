package isms.sensor.simulator;

import java.time.Instant;

import isms.records.SensorRecord;
import isms.records.SensorType;
import isms.sensor.Client;
import isms.sensor.Config;

public class Test {

	public static void main(String[] args) throws Exception {
		Client client = new Client();

		for (int i = 0; i < 3; i++) {
			SensorRecord record = new SensorRecord(SensorType.TEMPERATURE, Config.OWNER_ID, Instant.now().getEpochSecond(), Utils.randomFloat());
			int status = client.send(record);
			System.out.println(status);
		}
	}

}
