package isms.sensor;

import java.time.Instant;

import drivers.Driver;
import drivers.DriverReading;
import isms.common.Delegate;
import isms.common.Event;
import isms.models.SensorRecord;
import isms.models.SensorType;

public class Sensor {

	private String id;
	private String ownerId;
	private SensorType type;
	private Driver driver;
	private Event<SensorRecord> event;

	public Sensor(SensorType type) {
		this.type = type;
		this.id = Config.get("sensorId");
		this.ownerId = Config.get("ownerId");
		this.event = new Event<>();
	}

	public Sensor(SensorType type, Driver driver) {
		this(type);
		setDriver(driver);
	}

	public Sensor(SensorType type, Driver driver, Delegate<SensorRecord> delegate) {
		this(type, driver);
		subscribe(delegate);
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

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		if (driver != null) {
			driver.subscribe(this::onDriverEvent);
		}

		this.driver = driver;
	}

	protected void onDriverEvent(DriverReading data) {
		event.trigger(new SensorRecord(getId(), getOwnerId(), getType(), timestamp(), data.getData()));
	}

	public void subscribe(Delegate<SensorRecord> delegate) {
		event.subscribe(delegate);
	}

	public void start() {
		driver.poll();
	}

}
