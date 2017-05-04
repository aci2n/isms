package isms.streams;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public abstract class BaseSerde<T> implements Serde<T> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {}

	@Override
	public void close() {}

	@Override
	public abstract Serializer<T> serializer();

	@Override
	public abstract Deserializer<T> deserializer();
}
