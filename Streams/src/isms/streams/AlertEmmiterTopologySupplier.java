package isms.streams;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.processor.TopologyBuilder;

import isms.common.Constants;
import isms.common.UnirestWrapper;
import isms.models.SensorRecord;
import isms.models.SensorThreshold;

public class AlertEmmiterTopologySupplier extends TopologySupplier {

	public TopologyBuilder get() {
		KStreamBuilder builder = new KStreamBuilder();
		KStream<String, SensorRecord> input = builder.stream(Constants.SENSOR_RECORDS_TOPIC);

		input.foreach((ownerId, record) -> {
			SensorThreshold threshold = record.controlThreshold();
			if (threshold != null) {
				UnirestWrapper.post(Constants.API_ENDPOINT_ALERT).body(record).asBinaryAsync();
			}
		});

		return builder;
	}
}
