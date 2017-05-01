package isms.receptor;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;

import isms.common.Constants;
import isms.records.SensorRecord;
import isms.serialization.SensorRecordSerializer;

public class IsmsProducer extends KafkaProducer<String, SensorRecord> {
	public IsmsProducer(Properties properties, Serializer<String> keySerializer, Serializer<SensorRecord> valueSerializer) {
		super(properties, keySerializer, valueSerializer);
	}

	public IsmsProducer() {
		this(properties(), new StringSerializer(), new SensorRecordSerializer());
	}

	private static Properties properties() {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.BOOTSTRAP_SERVERS);

		return props;
	}

	public void send(SensorRecord record) {
		super.send(new ProducerRecord<>(Constants.TOPIC_NAME, null, record.getTime(), record.getOwnerId(), record));
	}
}