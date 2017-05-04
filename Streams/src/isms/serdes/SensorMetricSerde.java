package isms.serdes;

import isms.models.SensorMetric;
import isms.serialization.SensorMetricDeserializer;
import isms.serialization.SensorMetricSerializer;

public class SensorMetricSerde extends WrapperSerde<SensorMetric> {

	public SensorMetricSerde() {
		super(new SensorMetricSerializer(), new SensorMetricDeserializer());
	}

}
