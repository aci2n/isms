package isms.streams;

import org.apache.kafka.streams.kstream.KGroupedStream;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Windowed;
import org.apache.kafka.streams.processor.TopologyBuilder;

import isms.common.Constants;
import isms.common.SensorMetric;
import isms.records.SensorRecord;

public class AggregatorTopologyProvider extends TopologyProvider {

	public TopologyBuilder topology() {
		KStreamBuilder builder = new KStreamBuilder();
		KStream<String, SensorRecord> input = builder.stream(Constants.SENSOR_RECORDS_TOPIC);

		for (ChronoWindows window : ChronoWindows.DEFAULT_WINDOWS) {
			KGroupedStream<SensorAggregationKey, SensorRecord> grouped = input
					.groupBy((key, value) -> new SensorAggregationKey(value.getOwnerId(), value.getType()));

			KTable<Windowed<SensorAggregationKey>, SensorMetric> aggregation = grouped.aggregate(SensorMetric::new,
					(key, value, metric) -> metric.update(value), window, new SensorMetricSerde(),
					Constants.SENSOR_AGGREGATIONS_STORE);

			aggregation.print();
		}

		return builder;
	}
}
