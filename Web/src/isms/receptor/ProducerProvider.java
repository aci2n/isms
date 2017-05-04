package isms.receptor;

public class ProducerProvider {

	private static final Producer instance = new Producer();

	public Producer get() {
		return instance;
	}
}
