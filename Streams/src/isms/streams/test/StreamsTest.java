package isms.streams.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;

import isms.common.Constants;
import isms.records.SensorRecord;
import isms.streams.SensorRecordSerde;

public class StreamsTest {
	public static void main(String[] args) {
		Map<String, Object> props = new HashMap<>();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "my-stream-processing-application");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, SensorRecordSerde.class);
		StreamsConfig config = new StreamsConfig(props);

		KStreamBuilder builder = new KStreamBuilder();
		KStream<String, SensorRecord> stream = builder.stream(Constants.SENSOR_RECORDS_TOPIC);
		stream.foreach((key, record) -> {
			System.out.println(record.getSensorId());
		});

		KafkaStreams streams = new KafkaStreams(builder, config);
		streams.start();
	}
}
