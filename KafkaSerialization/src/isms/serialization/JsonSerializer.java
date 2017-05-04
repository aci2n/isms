package isms.serialization;

import org.apache.kafka.common.serialization.Serializer;

public abstract class JsonSerializer<T> extends WrapperJsonSerde<T> implements Serializer<T> {

	protected JsonSerializer(Class<T> clazz) {
		super(clazz);
	}

	@Override
	public byte[] serialize(String topic, T src) {
		return mapper.writeValueAsBytes(src);
	}

}
