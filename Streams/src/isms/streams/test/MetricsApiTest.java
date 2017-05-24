package isms.streams.test;

import isms.common.Client;
import isms.models.SensorAggregationKey;
import isms.models.SensorMetric;
import isms.models.SensorType;
import isms.models.WindowedMetric;

public class MetricsApiTest {

	public static void main(String[] args) throws Exception {
		Client client = new Client("windowed-metric");
		SensorAggregationKey key = new SensorAggregationKey("avalon", SensorType.HUMIDITY);
		SensorMetric metric = new SensorMetric(12f, 15f, 57f, 1l);
		WindowedMetric windowedMetric = new WindowedMetric(key, metric, 10l, 100l);
		client.post(windowedMetric);
	}
}
