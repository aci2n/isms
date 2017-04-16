package records;

public enum SensorType {

	TEMPERATURE(TemperatureRecord.class);

	private Class<? extends SensorRecord> recordClass;

	SensorType(Class<? extends SensorRecord> recordClass) {
		this.recordClass = recordClass;
	}

	public Class<? extends SensorRecord> getRecordClass() {
		return recordClass;
	}

	public static Class<? extends SensorRecord> getRecordClass(String s) {
		SensorType type = SensorType.valueOf(s);
		return type == null ? null : type.getRecordClass();
	}

}
