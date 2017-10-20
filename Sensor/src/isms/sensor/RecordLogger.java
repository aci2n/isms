package isms.sensor;

import isms.common.Delegate;
import isms.common.ObjectMapperUnchecked;
import isms.models.SensorRecord;

public class RecordLogger implements Delegate<SensorRecord> {

	private ObjectMapperUnchecked mapper;

	public RecordLogger() {
		this.mapper = new ObjectMapperUnchecked();
	}

	public void apply(SensorRecord record) {
		System.out.println(mapper.writeValueAsString(record));
	}

}
