package isms.streams;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.processor.TopologyBuilder;

import isms.common.Constants;
import isms.records.SensorRecord;

public class AggregatorTopologyProvider extends TopologyProvider {

	public TopologyBuilder topology() {
		KStreamBuilder builder = new KStreamBuilder();
		KStream<String, SensorRecord> input = builder.stream(Constants.SENSOR_RECORDS_TOPIC);
		input.to("");
		
		return builder;
	}
}
