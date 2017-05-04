package isms.streams;

import java.util.Properties;

import org.apache.kafka.streams.processor.TopologyBuilder;

public abstract class TopologySupplier {

	public abstract TopologyBuilder topology();

	public Properties properties() {
		return null;
	}
}
