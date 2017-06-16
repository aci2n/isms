package isms.services;

import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;

import isms.common.Constants;
import isms.models.SensorRecord;
import isms.serialization.SensorRecordSerializer;

public class Producer extends KafkaProducer<String, SensorRecord> {

	public Producer(Properties properties, Serializer<String> keySerializer, Serializer<SensorRecord> valueSerializer) {
		super(properties, keySerializer, valueSerializer);
	}

	public Producer(Properties properties) {
		this(properties, new StringSerializer(), new SensorRecordSerializer());
	}

	public Future<RecordMetadata> send(SensorRecord record) {
		return super.send(new ProducerRecord<>(Constants.SENSOR_RECORDS_TOPIC, null, record.getTime(),
				record.getOwnerId(), record));
	}
}