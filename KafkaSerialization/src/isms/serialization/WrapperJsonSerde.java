package isms.serialization;

import java.util.Map;

public abstract class WrapperJsonSerde<T> {

	protected Class<T> clazz;

	protected WrapperJsonSerde(Class<T> clazz) {
		this.clazz = clazz;
	}

	public void configure(Map<String, ?> configs, boolean isKey) {}

	public void close() {}
}
