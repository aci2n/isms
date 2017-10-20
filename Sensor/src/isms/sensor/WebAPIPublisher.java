package isms.sensor;

import isms.common.Constants;
import isms.common.Delegate;
import isms.common.UnirestWrapper;
import isms.models.SensorRecord;

public class WebAPIPublisher implements Delegate<SensorRecord> {

	public void apply(SensorRecord record) {
		UnirestWrapper.post(Constants.API_ENDPOINT_RECORDS).body(record).asBinaryAsync();
	}

}
