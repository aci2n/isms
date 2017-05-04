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

public class AggregatorTopologyProvider extends TopologyProvider {

	public TopologyBuilder topology() {
		KStreamBuilder builder = new KStreamBuilder();
		KStream<String, SensorRecord> input = builder.stream(Constants.SENSOR_RECORDS_TOPIC);

		KGroupedStream<SensorAggregationKey, SensorRecord> grouped = input.groupBy(
				(key, value) -> new SensorAggregationKey(value.getOwnerId(), value.getType()),
				new SensorAggregationKeySerde(), new SensorRecordSerde());

		KTable<Windowed<SensorAggregationKey>, SensorMetric> aggregation = grouped.aggregate(SensorMetric::new,
				(key, value, metric) -> metric.update(value), TimeWindows.of(1000), new SensorMetricSerde(),
				Constants.SENSOR_AGGREGATIONS_STORE);

		final ObjectMapperUnchecked mapper = new ObjectMapperUnchecked();
		aggregation.foreach((windowedKey, value) -> System.out.printf("%s -> %s\r\n",
				mapper.writeValueAsString(windowedKey.key()), mapper.writeValueAsString(value)));

		return builder;
	}
}
