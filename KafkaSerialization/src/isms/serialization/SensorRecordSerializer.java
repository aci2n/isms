package isms.serialization;

import isms.records.SensorRecord;

public class SensorRecordSerializer extends JsonSerializer<SensorRecord> {

	public SensorRecordSerializer() {
		super(SensorRecord.class);
	}
}
