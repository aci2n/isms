package records;

public class TemperatureRecord extends SensorRecord {

	private float temperature;

	public TemperatureRecord(String ownerId, long time, float temperature) {
		super(ownerId, time, SensorType.TEMPERATURE);
		this.temperature = temperature;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

}
