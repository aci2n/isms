package isms.services;

import java.time.Instant;

import org.apache.kafka.clients.producer.RecordMetadata;

import isms.models.SensorLocation;
import isms.models.SensorRecord;
import isms.models.SensorType;

public class MeasureThroughput {

	private static SensorRecord record() {
		return new SensorRecord("sensor_id", "avalon", SensorType.HUMIDITY, now(), 1234d,
				new SensorLocation("location_id", 1));
	}

	private static long now() {
		return Instant.now().getEpochSecond();
	}

	public static void main(String[] args) throws Exception {
		final Producer producer = new ProducerSupplier().get();
		final long duration = 60 * 10;
		final long start = now();
		long sent = 0;

		for (; now() - start < duration; sent++) {
			RecordMetadata response = producer.sendSync(record());
			System.out.println("Sent record " + sent + " at " + now() + " (timestamp: " + response.timestamp() + ")");
		}

		System.out.println("Sent " + sent + " in " + (now() - start) + " seconds");
	}

}
