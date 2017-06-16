package isms.services;

import java.util.Properties;

public class ConsumerSupplier extends ClientSupplier<Consumer> {

	public ConsumerSupplier(Properties props) {
		super(props);
	}

	protected Consumer getWithProperties(Properties properties) {
		return new Consumer(properties);
	}
}
