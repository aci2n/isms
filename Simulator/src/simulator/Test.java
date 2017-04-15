package simulator;

import java.time.Instant;

import records.SensorRecord;
import records.TemperatureRecord;

public class Test {

	public static void main(String[] args) throws Exception {
		Client client = new Client();

		for (int i = 0; i < 3; i++) {
			SensorRecord record = new TemperatureRecord(Config.OWNER_ID, Instant.now().getEpochSecond(), Utils.randomFloat());
			int status = client.send(record);
			System.out.println(status); 
		}
	}

}
