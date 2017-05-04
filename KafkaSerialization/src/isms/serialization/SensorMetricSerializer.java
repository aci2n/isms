package isms.serialization;

import isms.models.SensorMetric;

public class SensorMetricSerializer extends JsonSerializer<SensorMetric> {

	public SensorMetricSerializer() {
		super(SensorMetric.class);
	}
}
