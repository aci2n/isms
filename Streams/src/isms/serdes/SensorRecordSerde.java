package isms.serdes;

import isms.models.SensorRecord;
import isms.serialization.SensorRecordDeserializer;
import isms.serialization.SensorRecordSerializer;

public class SensorRecordSerde extends WrapperSerde<SensorRecord> {

	public SensorRecordSerde() {
		super(new SensorRecordSerializer(), new SensorRecordDeserializer());
	}

}
