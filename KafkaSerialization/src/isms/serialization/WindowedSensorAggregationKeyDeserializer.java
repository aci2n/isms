package isms.serialization;

import isms.models.WindowedSensorAggregationKey;

public class WindowedSensorAggregationKeyDeserializer extends JsonDeserializer<WindowedSensorAggregationKey> {

	public WindowedSensorAggregationKeyDeserializer() {
		super(WindowedSensorAggregationKey.class);
	}
}
