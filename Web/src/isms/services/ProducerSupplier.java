package isms.services;

import isms.common.Supplier;

public class ProducerSupplier implements Supplier<Producer> {

	private static final Producer instance = new Producer();

	public Producer get() {
		return instance;
	}
}
