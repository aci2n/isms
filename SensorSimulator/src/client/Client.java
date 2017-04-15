package client;

import java.io.IOException;
import java.util.concurrent.Future;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import config.Config;
import records.SensorRecord;

public class Client {

	public static HttpRequestFactory requestFactory;
	public static JsonFactory jsonFactory;

	static {
		jsonFactory = new JacksonFactory();
		
		requestFactory = new NetHttpTransport().createRequestFactory(request -> {
			request.setConnectTimeout(10);
		});
	}

	public Future<HttpResponse> send(SensorRecord record) throws IOException {
		GenericUrl url = new GenericUrl(Config.endpoint);
		HttpContent content = new JsonHttpContent(jsonFactory, record);
		HttpRequest request = requestFactory.buildPostRequest(url, content);
		
		return request.executeAsync();
	}

}
