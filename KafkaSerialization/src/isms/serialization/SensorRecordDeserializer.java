package isms.serialization;

import isms.models.SensorRecord;

public class SensorRecordDeserializer extends JsonDeserializer<SensorRecord> {

	public SensorRecordDeserializer() {
		super(SensorRecord.class);
	}
}
