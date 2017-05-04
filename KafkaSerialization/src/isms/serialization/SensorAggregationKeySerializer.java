package isms.serialization;

import isms.models.SensorAggregationKey;

public class SensorAggregationKeySerializer extends JsonSerializer<SensorAggregationKey> {

	public SensorAggregationKeySerializer() {
		super(SensorAggregationKey.class);
	}
}
