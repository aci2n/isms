package isms.common;

import org.apache.http.HttpHeaders;

import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

public final class UnirestWrapper {

	static {
		Unirest.setObjectMapper(new ObjectMapper() {

			private ObjectMapperUnchecked mapper = new ObjectMapperUnchecked();

			@Override
			public String writeValue(Object value) {
				return mapper.writeValueAsString(value);
			}

			@Override
			public <T> T readValue(String src, Class<T> clazz) {
				return mapper.readValue(src, clazz);
			}
		});
	}

	private static String fullpath(String path) {
		return Constants.API_ENDPOINT + path;
	}

	public static GetRequest get(String path) {
		return Unirest.get(fullpath(path));
	}

	public static HttpRequestWithBody post(String path) {
		return Unirest.post(fullpath(path)).header(HttpHeaders.CONTENT_TYPE, "application/json");
	}

}
