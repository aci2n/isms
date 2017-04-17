package isms.receptor;

public class ProducerLocator {
	private static Producer instance;

	public Producer get() {
		if (instance == null) {
			instance = new Producer();
		}

		return instance;
	}
}
