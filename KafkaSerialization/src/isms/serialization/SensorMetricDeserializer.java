package isms.serialization;

import isms.common.SensorMetric;

public class SensorMetricDeserializer extends JsonDeserializer<SensorMetric> {

	public SensorMetricDeserializer() {
		super(SensorMetric.class);
	}
}
