package isms.streams;

import java.util.Properties;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.processor.TopologyBuilder;

public abstract class IsmsStreams extends KafkaStreams {
	public IsmsStreams(TopologyBuilder builder, Properties props) {
		super(builder, mergeWithDefault(props));

		setUncaughtExceptionHandler(this::uncaughtExceptionHandler);
		Runtime.getRuntime().addShutdownHook(new Thread(super::close));
	}

	public IsmsStreams(TopologyBuilder builder) {
		this(builder, null);
	}

	private static Properties mergeWithDefault(Properties properties) {
		return properties;
	}

	private void uncaughtExceptionHandler(Thread thread, Throwable throwable) {
		throwable.printStackTrace();
	}
}
