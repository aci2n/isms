package isms.serialization;

import isms.models.WindowedSensorAggregationKey;

public class WindowedSensorAggregationKeySerializer extends JsonSerializer<WindowedSensorAggregationKey> {

	public WindowedSensorAggregationKeySerializer() {
		super(WindowedSensorAggregationKey.class);
	}
}
