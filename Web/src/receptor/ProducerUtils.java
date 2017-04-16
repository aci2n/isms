package receptor;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProducerUtils {

	private static KafkaProducer<String, String> instance;

	static {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		StringSerializer serializer = new StringSerializer();
		instance = new KafkaProducer<String, String>(props, serializer, serializer);
	}

	public static KafkaProducer<String, String> instance() {
		return instance;
	}

	public static void send(String data) {
		ProducerRecord<String, String> record = new ProducerRecord<String, String>("sensor-records", data);
		instance.send(record);
	}
	
}
