package records;

public class TemperatureRecord extends SensorRecord {

	private Float temperature;

	public TemperatureRecord(Float temperature) {
		super();
		this.temperature = temperature;
	}

	public Float getTemperature() {
		return temperature;
	}

	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}

}
