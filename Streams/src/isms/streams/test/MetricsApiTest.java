package isms.streams.test;

import com.mashape.unirest.http.HttpResponse;

import isms.common.Constants;
import isms.common.UnirestWrapper;
import isms.models.SensorAggregationKey;
import isms.models.SensorMetric;
import isms.models.SensorType;
import isms.models.WindowedMetric;

public class MetricsApiTest {

	public static void main(String[] args) throws Exception {
		SensorAggregationKey key = new SensorAggregationKey("avalon", SensorType.HUMIDITY);
		SensorMetric metric = new SensorMetric(12f, 15f, 57f, 1l);
		WindowedMetric windowedMetric = new WindowedMetric(key, metric, 10l, 100l);
		HttpResponse<String> response = UnirestWrapper.post(Constants.API_ENDPOINT_MONITOR)
				.body(windowedMetric).asString();
		System.out.println("sent to windowed metrics API: " + response.getStatus() + " " + response.getBody());
	}
}
