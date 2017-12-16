package isms.sensor;

import java.time.Instant;

import drivers.Driver;
import drivers.DriverEvent;
import isms.common.Delegate;
import isms.models.SensorLocation;
import isms.models.SensorRecord;
import isms.models.SensorType;

public class Sensor {

	private String id;
	private String ownerId;
	private SensorType type;
	private Driver driver;
	private Delegate<SensorRecord> delegate;
	private SensorLocation location;

	public Sensor(SensorType type, Driver driver, Delegate<SensorRecord> delegate, SensorLocation location) {
		this.type = type;
		this.id = Config.get("sensorId");
		this.ownerId = Config.get("ownerId");
		this.driver = driver;
		this.delegate = delegate;
		this.location = location;

		driver.setDelegate(this::onDriverEvent);
	}

	public long timestamp() {
		return Instant.now().getEpochSecond();
	}

	protected void onDriverEvent(DriverEvent event) {
		delegate.apply(new SensorRecord(id, ownerId, type, timestamp(), event.getData(), location));
	}

	public void start() {
		driver.poll();
	}

}
