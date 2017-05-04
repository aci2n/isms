package isms.streams;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public abstract class WrapperSerde<T> implements Serde<T> {

	private Serializer<T> serializer;
	private Deserializer<T> deserializer;

	protected WrapperSerde(Serializer<T> serializer, Deserializer<T> deserializer) {
		this.serializer = serializer;
		this.deserializer = deserializer;
	}

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {}

	@Override
	public void close() {}

	@Override
	public Serializer<T> serializer() {
		return serializer;
	}

	@Override
	public Deserializer<T> deserializer() {
		return deserializer;
	}
}
