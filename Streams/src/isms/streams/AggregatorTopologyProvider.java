package isms.streams;

import org.apache.kafka.streams.processor.TopologyBuilder;

public class AggregatorTopologyProvider extends IsmsTopologyProvider {
	private static final long[] TIME_WINDOWS = { 1, 60, 3600, 3600 * 12, 3600 * 24, 3600 * 30, 3600 * 365 };

	public TopologyBuilder topology() {
		return null;
	}
}
