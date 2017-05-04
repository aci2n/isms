package isms.serialization;

import java.util.Map;

import isms.common.ObjectMapperUnchecked;

public abstract class WrapperJsonSerde<T> {

	protected Class<T> clazz;
	protected ObjectMapperUnchecked mapper;

	protected WrapperJsonSerde(Class<T> clazz) {
		this.clazz = clazz;
		this.mapper = new ObjectMapperUnchecked();
	}

	public void configure(Map<String, ?> configs, boolean isKey) {}

	public void close() {}
}
