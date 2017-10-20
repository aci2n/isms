package isms.sensor;

import java.time.Instant;

import drivers.Driver;
import drivers.DriverEvent;
import isms.common.Constants;
import isms.common.UnirestWrapper;
import isms.models.SensorRecord;
import isms.models.SensorType;

public abstract class Sensor {

	private String id;
	private String ownerId;
	private SensorType type;
	private Driver driver;

	protected Sensor(SensorType type, Driver driver) {
		this.id = Config.get("sensorId");
		this.ownerId = Config.get("ownerId");
		this.type = type;
		this.driver = driver;

		driver.setListener(this::onDriverEvent);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public SensorType getType() {
		return type;
	}

	public void setType(SensorType type) {
		this.type = type;
	}

	public long timestamp() {
		return Instant.now().getEpochSecond();
	}

	private void onDriverEvent(DriverEvent event) {
		SensorRecord record = new SensorRecord(getId(), getOwnerId(), getType(), timestamp(), event.getData());
		UnirestWrapper.post(Constants.API_ENDPOINT_RECORDS).body(record).asBinaryAsync();
	}

	public void start() {
		driver.start();
	}

}
