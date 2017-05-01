package isms.streams;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes.StringSerde;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.processor.TopologyBuilder;

import isms.common.Constants;

public class IsmsStreams extends KafkaStreams {
	private IsmsStreams(TopologyBuilder builder, Properties properties) {
		super(builder, properties(properties));

		setUncaughtExceptionHandler(this::uncaughtExceptionHandler);
		Runtime.getRuntime().addShutdownHook(new Thread(super::close));
	}

	public IsmsStreams(IsmsTopologyProvider provider) {
		this(provider.topology(), provider.properties());
	}

	private static Properties properties(Properties properties) {
		if (properties == null) {
			properties = new Properties();
		}
		properties.putIfAbsent(StreamsConfig.APPLICATION_ID_CONFIG, Constants.SENSOR_AGGREGATOR_APPLICATION_ID);
		properties.putIfAbsent(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.BOOTSTRAP_SERVERS);
		properties.putIfAbsent(StreamsConfig.KEY_SERDE_CLASS_CONFIG, StringSerde.class);
		properties.putIfAbsent(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, SensorRecordSerde.class);

		return properties;
	}

	private void uncaughtExceptionHandler(Thread thread, Throwable throwable) {
		throwable.printStackTrace();
	}
}
