package isms.streams.test;

import java.time.Duration;

import isms.streams.AggregatorTopologySupplier;
import isms.streams.Streams;

public class StreamsTest {

	public static void main(String[] args) {
		Duration[] windowDurations = {
				Duration.ofSeconds(1),
				Duration.ofMinutes(1),
				Duration.ofHours(1),
				Duration.ofDays(1),
				Duration.ofDays(30),
				Duration.ofDays(365) };

		for (Duration duration : windowDurations) {
			Streams streams = new Streams(new AggregatorTopologySupplier(duration.getSeconds()));
			streams.start();
		}
	}
}
