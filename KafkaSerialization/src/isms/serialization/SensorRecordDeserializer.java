package isms.serialization;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import isms.records.SensorRecord;

public class SensorRecordDeserializer implements Deserializer<SensorRecord> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {}

	@Override
	public SensorRecord deserialize(String topic, byte[] data) {
		return SensorRecord.deserialize(new String(data));
	}

	@Override
	public void close() {}
}
