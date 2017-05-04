package isms.serialization;

import isms.records.SensorRecord;

public class SensorRecordDeserializer extends JsonDeserializer<SensorRecord> {

	public SensorRecordDeserializer() {
		super(SensorRecord.class);
	}
}
