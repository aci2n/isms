package isms.streams;

import org.apache.kafka.streams.kstream.KGroupedStream;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.apache.kafka.streams.kstream.Windowed;
import org.apache.kafka.streams.processor.TopologyBuilder;

import isms.common.Constants;
import isms.common.ObjectMapperUnchecked;
import isms.models.SensorAggregationKey;
import isms.models.SensorMetric;
import isms.models.SensorRecord;
import isms.serdes.SensorAggregationKeySerde;
import isms.serdes.SensorMetricSerde;
import isms.serdes.SensorRecordSerde;

public class AggregatorTopologySupplier extends TopologySupplier {

	private long windowSize;

	public AggregatorTopologySupplier(long windowSize) {
		this.windowSize = windowSize;
	}

	public TopologyBuilder topology() {
		SensorAggregationKeySerde aggregationKeySerde = new SensorAggregationKeySerde();
		SensorMetricSerde metricSerde = new SensorMetricSerde();
		SensorRecordSerde recordSerde = new SensorRecordSerde();

		KStreamBuilder builder = new KStreamBuilder();
		KStream<String, SensorRecord> input = builder.stream(Constants.SENSOR_RECORDS_TOPIC);

		KGroupedStream<SensorAggregationKey, SensorRecord> grouped = input.groupBy(
				(key, value) -> new SensorAggregationKey(value.getOwnerId(), value.getType(), windowSize),
				aggregationKeySerde, recordSerde);

		KTable<Windowed<SensorAggregationKey>, SensorMetric> aggregation = grouped.aggregate(SensorMetric::new,
				(key, value, metric) -> metric.update(value), TimeWindows.of(windowSize), metricSerde,
				Constants.SENSOR_AGGREGATIONS_STORE);

		final ObjectMapperUnchecked mapper = new ObjectMapperUnchecked();
		aggregation.foreach((windowedKey, value) -> System.out.printf("[Size: %d - Window: %d] - %s -> %s%s",
				windowSize, windowedKey.window().start(), mapper.writeValueAsString(windowedKey.key()),
				mapper.writeValueAsString(value), System.lineSeparator()));

		return builder;
	}
}
