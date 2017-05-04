package isms.serdes;

import isms.models.SensorAggregationKey;
import isms.serialization.SensorAggregationKeyDeserializer;
import isms.serialization.SensorAggregationKeySerializer;

public class SensorAggregationKeySerde extends WrapperSerde<SensorAggregationKey> {

	public SensorAggregationKeySerde() {
		super(new SensorAggregationKeySerializer(), new SensorAggregationKeyDeserializer());
	}

}
