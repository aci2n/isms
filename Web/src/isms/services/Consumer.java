package isms.services;

import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import isms.models.SensorRecord;
import isms.serialization.SensorRecordDeserializer;

public class Consumer extends KafkaConsumer<String, SensorRecord> {

	public Consumer(Properties properties, Deserializer<String> keyDeserializer,
			Deserializer<SensorRecord> valueDeserializer) {
		super(properties, keyDeserializer, valueDeserializer);
	}

	public Consumer(Properties properties) {
		this(properties, new StringDeserializer(), new SensorRecordDeserializer());
	}

}