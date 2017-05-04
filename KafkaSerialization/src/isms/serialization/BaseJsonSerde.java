package isms.serialization;

import java.util.Map;

public abstract class BaseJsonSerde<T> {

	protected Class<T> clazz;

	protected BaseJsonSerde(Class<T> clazz) {
		this.clazz = clazz;
	}

	public void configure(Map<String, ?> configs, boolean isKey) {}

	public void close() {}
}
