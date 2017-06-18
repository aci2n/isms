package isms.streams;

import java.util.Properties;

import org.apache.kafka.streams.processor.TopologyBuilder;

import isms.common.Supplier;

public abstract class TopologySupplier implements Supplier<TopologyBuilder> {

	public abstract TopologyBuilder get();

	public Properties properties() {
		return new Properties();
	}
}
