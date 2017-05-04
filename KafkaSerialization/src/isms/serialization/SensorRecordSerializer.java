package isms.serialization;

import isms.models.SensorRecord;

public class SensorRecordSerializer extends JsonSerializer<SensorRecord> {

	public SensorRecordSerializer() {
		super(SensorRecord.class);
	}
}
