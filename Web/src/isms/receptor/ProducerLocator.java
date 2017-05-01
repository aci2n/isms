package isms.receptor;

public class ProducerLocator {
	private static IsmsProducer instance;

	public IsmsProducer get() {
		if (instance == null) {
			instance = new IsmsProducer();
		}

		return instance;
	}
}
