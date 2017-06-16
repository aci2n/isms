package isms.services;

import java.util.Properties;

public class ProducerSupplier extends ClientSupplier<Producer> {

	private static class Holder {

		private static Producer instance;
	}

	public ProducerSupplier(Properties props) {
		super(props);
	}
	
	public ProducerSupplier() {
		this(new Properties());
	}

	protected Producer getWithProperties(Properties properties) {
		if (Holder.instance == null) {
			Holder.instance = new Producer(properties);
		}

		return Holder.instance;
	}
}
