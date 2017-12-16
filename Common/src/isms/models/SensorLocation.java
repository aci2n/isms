package isms.models;

public class SensorLocation {

	private String locationId;
	private double x;
	private double y;
	private double z;

	public SensorLocation(String locationId, double x, double y, double z) {
		super();
		this.locationId = locationId;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	@Override
	public boolean equals(Object o) {
		SensorLocation other = (SensorLocation) o;
		return locationId == other.getLocationId() && x == other.getX() && y == other.getY() && z == other.getZ();
	}

}
