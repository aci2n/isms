package isms.models;

public enum ThresholdType {
	WARNING(10),
	DANGER(20),
	CRITICAL(30);

	private int priority;

	ThresholdType(int priority) {
		this.priority = priority;
	}

	public int getPriority() {
		return priority;
	}
}
