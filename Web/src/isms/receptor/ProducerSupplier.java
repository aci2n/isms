package isms.receptor;

public class ProducerSupplier {

	private static final Producer instance = new Producer();

	public Producer get() {
		return instance;
	}
}
