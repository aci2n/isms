package isms.streams;

import org.apache.kafka.streams.KeyValue;
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
import isms.models.WindowedSensorAggregationKey;
import isms.serdes.SensorAggregationKeySerde;
import isms.serdes.SensorMetricSerde;
import isms.serdes.SensorRecordSerde;
import isms.serdes.WindowedSensorAggregationKeySerde;

public class AggregatorTopologySupplier extends TopologySupplier {

	private long windowSize;

	public AggregatorTopologySupplier(long windowSize) {
		this.windowSize = windowSize;
	}

	public TopologyBuilder topology() {
		SensorAggregationKeySerde aggregationKeySerde = new SensorAggregationKeySerde();
		SensorMetricSerde metricSerde = new SensorMetricSerde();
		SensorRecordSerde recordSerde = new SensorRecordSerde();
		WindowedSensorAggregationKeySerde windowedKeySerde = new WindowedSensorAggregationKeySerde();

		KStreamBuilder builder = new KStreamBuilder();
		KStream<String, SensorRecord> input = builder.stream(Constants.SENSOR_RECORDS_TOPIC);

		KGroupedStream<SensorAggregationKey, SensorRecord> grouped = input.groupBy(
				(key, value) -> new SensorAggregationKey(value.getOwnerId(), value.getType()), aggregationKeySerde,
				recordSerde);

		KTable<Windowed<SensorAggregationKey>, SensorMetric> aggregation = grouped.aggregate(SensorMetric::new,
				(key, value, metric) -> metric.update(value), TimeWindows.of(windowSize), metricSerde,
				Constants.SENSOR_AGGREGATIONS_STORE);

		final ObjectMapperUnchecked mapper = new ObjectMapperUnchecked();
		aggregation.foreach((windowedKey, value) -> System.out.printf("[Size: %d - Window: %d] - %s -> %s%s",
				windowSize, windowedKey.window().start(), mapper.writeValueAsString(windowedKey.key()),
				mapper.writeValueAsString(value), System.lineSeparator()));

		KStream<WindowedSensorAggregationKey, SensorMetric> output = aggregation.toStream()
				.map((windowedKey, metric) -> new KeyValue<>(
						new WindowedSensorAggregationKey(windowedKey.window().start(), windowSize, windowedKey.key()),
						metric));

		output.to(windowedKeySerde, metricSerde, Constants.SENSOR_AGGREGATIONS_TOPIC_PREFIX + windowSize);

		return builder;
	}
}
