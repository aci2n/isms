package isms.receptor;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import isms.records.SensorRecord;
import isms.serialization.SensorRecordSerializer;
import isms.utils.Constants;
import isms.utils.Utils;

public class ProducerUtils {
	private static KafkaProducer<String, SensorRecord> instance;

	static {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.BOOTSTRAP_SERVERS);

		instance = new KafkaProducer<String, SensorRecord>(props, new StringSerializer(), new SensorRecordSerializer());
	}

	public static KafkaProducer<String, SensorRecord> instance() {
		return instance;
	}

	public static void send(SensorRecord record) {
		String topic = Utils.kafkaTopic(record.getOwnerId());
		String key = record.key();
		long timestamp = record.getTime();
		ProducerRecord<String, SensorRecord> message = new ProducerRecord<String, SensorRecord>(topic, 0, timestamp, key, record);

		instance.send(message);
	}

}
