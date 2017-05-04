package isms.serialization;

import org.apache.kafka.common.serialization.Deserializer;

import isms.common.GsonHelpers;

public abstract class JsonDeserializer<T> extends BaseJsonSerde<T> implements Deserializer<T> {

	protected JsonDeserializer(Class<T> clazz) {
		super(clazz);
	}

	@Override
	public T deserialize(String topic, byte[] data) {
		String json = new String(data);
		return new GsonHelpers().fromJson(json, clazz);
	}

}
