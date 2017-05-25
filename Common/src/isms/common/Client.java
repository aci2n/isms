package isms.common;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client {

	private ObjectMapperUnchecked mapper;
	private String url;
	private IOStream io;

	public Client(String path) {
		this.mapper = new ObjectMapperUnchecked();
		this.url = Constants.API_ENDPOINT + path;
		this.io = new IOStream();
	}

	private HttpURLConnection open() throws IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
		connection.setDoOutput(true);
		return connection;
	}

	public void post(Object value) throws IOException {
		HttpURLConnection connection = open();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		io.write(connection.getOutputStream(), mapper.writeValueAsBytes(value));
	}

	public <T> T get(Class<T> clazz) throws IOException {
		HttpURLConnection connection = open();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "application/json");
		String in = io.read(connection.getInputStream());
		T value = mapper.readValue(in, clazz);

		return value;
	}

}
