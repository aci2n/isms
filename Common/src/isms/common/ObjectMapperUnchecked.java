package isms.common;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * I don't like having to try/catch IOExceptions all around the place,
 * especially since we don't do any IO during JSON mapping.
 * 
 * This is just a wrapper for the ObjectMapper where these exceptions are
 * translated to unchecked ones (AssertionError in particular).
 * 
 * @author Alvaro Calace
 */
public class ObjectMapperUnchecked extends ObjectMapper {

	private static final long serialVersionUID = 1L;

	private interface ValueWriter<T> {

		public T write(Object value) throws JsonProcessingException;
	}

	private interface ValueReader<T, R> {

		public T read(R src, Class<T> clazz) throws IOException;
	}

	@Override
	public String writeValueAsString(Object value) {
		return writeValueAs(value, super::writeValueAsString);
	}

	@Override
	public byte[] writeValueAsBytes(Object value) {
		return writeValueAs(value, super::writeValueAsBytes);
	}

	private <T> T writeValueAs(Object value, ValueWriter<T> writer) {
		T result = null;

		try {
			result = writer.write(value);
		} catch (JsonProcessingException e) {
			throw new AssertionError(String.format("Could not write value: %s.", value), e);
		}

		return result;
	}

	@Override
	public <T> T readValue(String src, Class<T> clazz) {
		return readValueAs(src, clazz, super::readValue);
	}

	@Override
	public <T> T readValue(byte[] src, Class<T> clazz) {
		return readValueAs(src, clazz, super::readValue);
	}

	private <T, R> T readValueAs(R src, Class<T> clazz, ValueReader<T, R> reader) {
		T value = null;

		try {
			value = reader.read(src, clazz);
		} catch (IOException e) {
			throw new AssertionError(String.format("Could not read value of type: %s from src: %s.", clazz, src), e);
		}

		return value;
	}

}
