package isms.serialization;

import isms.models.SensorMetric;

public class SensorMetricDeserializer extends JsonDeserializer<SensorMetric> {

	public SensorMetricDeserializer() {
		super(SensorMetric.class);
	}
}
