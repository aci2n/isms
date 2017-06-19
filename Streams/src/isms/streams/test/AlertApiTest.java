package isms.streams.test;

import java.time.Instant;
import java.util.UUID;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

import isms.common.Constants;
import isms.common.UnirestWrapper;
import isms.models.Alert;
import isms.models.SensorThreshold;
import isms.models.SensorType;
import isms.models.ThresholdType;

public class AlertApiTest {

	public static void main(String[] args) throws UnirestException {
		Alert alert = new Alert(UUID.randomUUID().toString(), "avalon", SensorType.HUMIDITY,
				new SensorThreshold(ThresholdType.CRITICAL, -20, 20), 30, Instant.now().getEpochSecond(), false);
		HttpResponse<String> result = UnirestWrapper.post(Constants.API_ENDPOINT_ALERTS).body(alert).asString();
		System.out.println(result.getBody());
	}
}
