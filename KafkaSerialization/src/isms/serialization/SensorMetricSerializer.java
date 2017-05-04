package isms.serialization;

import isms.common.SensorMetric;

public class SensorMetricSerializer extends JsonSerializer<SensorMetric> {

	public SensorMetricSerializer() {
		super(SensorMetric.class);
	}
}
