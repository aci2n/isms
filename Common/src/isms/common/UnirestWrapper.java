package isms.common;

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

	public static GetRequest get(Path path) {
		return Unirest.get(path.getFullPath());
	}

	public static HttpRequestWithBody post(Path path) {
		return Unirest.post(path.getFullPath());
	}

}
