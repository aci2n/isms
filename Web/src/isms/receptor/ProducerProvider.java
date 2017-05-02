package isms.receptor;

/**
 * Provides a singleton Producer instance in a thread-safe way. See
 * "Initialization-on-demand holder" idiom. Kafka documentation recommends
 * keeping a single KafkaProducer instance, since it's thread-safe.
 * 
 * @author Alvaro Calace
 */
public class ProducerProvider {

	private static final class ProducerHolder {

		private static final Producer instance = new Producer();
	}

	public Producer get() {
		return ProducerHolder.instance;
	}
}
