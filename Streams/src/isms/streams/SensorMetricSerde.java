package isms.streams;

import isms.common.SensorMetric;
import isms.serialization.SensorMetricDeserializer;
import isms.serialization.SensorMetricSerializer;

public class SensorMetricSerde extends WrapperSerde<SensorMetric> {

	public SensorMetricSerde() {
		super(new SensorMetricSerializer(), new SensorMetricDeserializer());
	}

}
