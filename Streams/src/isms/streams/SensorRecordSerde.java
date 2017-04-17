package isms.streams;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import isms.records.SensorRecord;
import isms.serialization.SensorRecordDeserializer;
import isms.serialization.SensorRecordSerializer;

public class SensorRecordSerde implements Serde<SensorRecord> {
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {}

	@Override
	public void close() {}

	@Override
	public Serializer<SensorRecord> serializer() {
		return new SensorRecordSerializer();
	}

	@Override
	public Deserializer<SensorRecord> deserializer() {
		return new SensorRecordDeserializer();
	}

}
