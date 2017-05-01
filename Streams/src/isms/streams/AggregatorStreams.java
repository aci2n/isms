package isms.streams;

import java.util.Properties;

import org.apache.kafka.streams.processor.TopologyBuilder;

public class AggregatorStreams extends IsmsStreams {
	public AggregatorStreams() {
		super(topology(), properties());
	}
	
	private static TopologyBuilder topology() {
		return null;
	}
	
	private static Properties properties() {
		return null;
	}
}
