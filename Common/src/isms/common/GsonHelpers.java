package isms.common;

import com.google.gson.Gson;

public class GsonHelpers {

	private static final Gson gson = new Gson();

	public String toJson(Object src) {
		return gson.toJson(src);
	}

	public <T> String toJson(Object src, Class<T> clazz) {
		return gson.toJson(src, clazz);
	}

	public <T> T fromJson(String json, Class<T> clazz) {
		return gson.fromJson(json, clazz);
	}
}
