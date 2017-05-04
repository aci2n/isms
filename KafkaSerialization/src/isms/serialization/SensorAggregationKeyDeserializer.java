package isms.serialization;

import isms.models.SensorAggregationKey;

public class SensorAggregationKeyDeserializer extends JsonDeserializer<SensorAggregationKey> {

	public SensorAggregationKeyDeserializer() {
		super(SensorAggregationKey.class);
	}
}
