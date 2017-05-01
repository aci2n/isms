package isms.serialization;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import isms.records.SensorRecord;

public class SensorRecordSerializer implements Serializer<SensorRecord> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {}

	@Override
	public byte[] serialize(String topic, SensorRecord data) {
		return data.serialize().getBytes();
	}

	@Override
	public void close() {}
}
