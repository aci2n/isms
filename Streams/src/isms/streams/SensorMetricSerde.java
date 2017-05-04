package isms.streams;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import isms.common.SensorMetric;
import isms.serialization.SensorMetricDeserializer;
import isms.serialization.SensorMetricSerializer;

public class SensorMetricSerde extends BaseSerde<SensorMetric> {

	@Override
	public Serializer<SensorMetric> serializer() {
		return new SensorMetricSerializer();
	}

	@Override
	public Deserializer<SensorMetric> deserializer() {
		return new SensorMetricDeserializer();
	}

}
