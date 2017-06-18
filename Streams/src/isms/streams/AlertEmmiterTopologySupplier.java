package isms.streams;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.processor.TopologyBuilder;

import isms.common.Constants;
import isms.common.UnirestWrapper;
import isms.models.Alert;
import isms.models.SensorRecord;
import isms.models.SensorThreshold;

public class AlertEmmiterTopologySupplier extends TopologySupplier {

	public TopologyBuilder get() {
		KStreamBuilder builder = new KStreamBuilder();
		KStream<String, SensorRecord> input = builder.stream(Constants.SENSOR_RECORDS_TOPIC);

		input.foreach((ownerId, record) -> {
			SensorThreshold threshold = record.checkThreshold();
			if (threshold != null) {
				Alert alert = new Alert(record.getSensorId(), record.getOwnerId(), record.getType(), threshold,
						record.getData(), record.getTime(), false);
				// TODO: Handle failures here to avoid losing data.
				UnirestWrapper.post(Constants.API_ENDPOINT_ALERTS).body(alert).asBinaryAsync();
			}
		});

		return builder;
	}
}
