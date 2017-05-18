package isms.streams.test;

import java.time.Duration;

import isms.streams.AggregatorTopologySupplier;
import isms.streams.Streams;

public class StreamsTest {

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Duration[] windowDurations = {
				Duration.ofSeconds(1),
				Duration.ofMinutes(1),
				Duration.ofHours(1),
				Duration.ofDays(1),
				Duration.ofDays(30),
				Duration.ofDays(365) };

		// for (Duration duration : windowDurations) {
		// long size = duration.getSeconds();
		// System.out.println("Launching stream with window size: " + size);
		// Streams streams = new Streams(new AggregatorTopologySupplier(size));
		// streams.start();
		// }

		System.out.println("Launching stream with window size: " + 10);
		Streams streams = new Streams(new AggregatorTopologySupplier(10));
		streams.start();
	}
}
