package isms.receptor;

import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;

import isms.common.Constants;
import isms.records.SensorRecord;
import isms.serialization.SensorRecordSerializer;

public class Producer extends KafkaProducer<String, SensorRecord> {

	public Producer(Properties properties, Serializer<String> keySerializer, Serializer<SensorRecord> valueSerializer) {
		super(properties, keySerializer, valueSerializer);
	}

	public Producer() {
		this(properties(), new StringSerializer(), new SensorRecordSerializer());
	}

	private static Properties properties() {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.BOOTSTRAP_SERVERS);

		return props;
	}

	public Future<RecordMetadata> send(SensorRecord record) {
		return super.send(new ProducerRecord<>(Constants.SENSOR_RECORDS_TOPIC, null, record.getTime(),
				record.getOwnerId(), record));
	}
}