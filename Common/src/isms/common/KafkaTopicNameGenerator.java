package isms.common;

public class KafkaTopicNameGenerator {
	public String get(String ownerId) {
		return Constants.TOPIC_PREFIX + ownerId;
	}
}
