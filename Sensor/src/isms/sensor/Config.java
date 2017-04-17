package isms.sensor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import isms.utils.Utils;

public class Config {
	private static Properties properties;

	static {
		properties = new Properties();
		InputStream in = ClassLoader.class.getResourceAsStream("/app.properties");

		if (in != null) {
			try {
				properties.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String get(String key) {
		return properties.getProperty(key);
	}

	public static int getInt(String key) {
		return Utils.tryParseInt(get(key));
	}

	public static long getLong(String key) {
		return Utils.tryParseLong(get(key));
	}

	public static Object set(String key, String value) {
		return properties.setProperty(key, value);
	}
}
