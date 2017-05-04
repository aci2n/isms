package isms.streams;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import isms.records.SensorRecord;
import isms.serialization.SensorRecordDeserializer;
import isms.serialization.SensorRecordSerializer;

public class SensorRecordSerde extends BaseSerde<SensorRecord> {

	@Override
	public Serializer<SensorRecord> serializer() {
		return new SensorRecordSerializer();
	}

	@Override
	public Deserializer<SensorRecord> deserializer() {
		return new SensorRecordDeserializer();
	}

}
