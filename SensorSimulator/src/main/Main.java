package main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import com.google.api.client.http.HttpResponse;

import client.Client;
import records.SensorRecord;
import records.TemperatureRecord;

public class Main {

	public static void main(String[] args) throws Exception {
		Client client = new Client();
		List<Future<HttpResponse>> futures = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			SensorRecord record = new TemperatureRecord(TestUtils.randomFloat());
			futures.add(client.send(record));
		}
		
		for (Future<HttpResponse> future : futures) {
			HttpResponse response = future.get();
			System.out.println(response.parseAsString());
		}
	}

}
