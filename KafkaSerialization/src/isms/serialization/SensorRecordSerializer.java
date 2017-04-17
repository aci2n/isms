package isms.serialization;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import isms.records.SensorRecord;
import isms.utils.SerializationUtils;

public class SensorRecordSerializer implements Serializer<SensorRecord> {
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {}

	@Override
	public byte[] serialize(String topic, SensorRecord data) {
		byte[] bytes = null;
		String serialized = SerializationUtils.serialize(data);

		if (serialized != null) {
			bytes = serialized.getBytes();
		}

		return bytes;
	}

	@Override
	public void close() {}
}
