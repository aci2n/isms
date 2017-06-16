package isms.services;

import java.util.Properties;

import org.apache.kafka.clients.producer.ProducerConfig;

import isms.common.Constants;
import isms.common.Supplier;

public abstract class ClientSupplier<T> implements Supplier<T> {

	private Properties properties;

	protected ClientSupplier(Properties props) {
		this.properties = props;
		this.properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.BOOTSTRAP_SERVERS);
	}

	public T get() {
		return getWithProperties(properties);
	}

	protected abstract T getWithProperties(Properties properties);
}
