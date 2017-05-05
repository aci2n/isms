package isms.serdes;

import isms.models.WindowedSensorAggregationKey;
import isms.serialization.WindowedSensorAggregationKeyDeserializer;
import isms.serialization.WindowedSensorAggregationKeySerializer;

public class WindowedSensorAggregationKeySerde extends WrapperSerde<WindowedSensorAggregationKey> {

	public WindowedSensorAggregationKeySerde() {
		super(new WindowedSensorAggregationKeySerializer(), new WindowedSensorAggregationKeyDeserializer());
	}

}
