package isms.streams;

import isms.records.SensorType;

public class SensorAggregationKey {

	String ownerId;
	SensorType type;

	public SensorAggregationKey(String ownerId, SensorType type) {
		this.ownerId = ownerId;
		this.type = type;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public SensorType getType() {
		return type;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (this == o) return true;
		if (!(o instanceof SensorAggregationKey)) return false;
		SensorAggregationKey other = (SensorAggregationKey) o;

		return this.ownerId.equals(other.getOwnerId()) && this.type.equals(other.getType());
	}

}
