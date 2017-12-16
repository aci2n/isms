package isms.models;

public class SensorLocation {

	private String locationId;
	private int section;

	public SensorLocation() {}

	public SensorLocation(String locationId, int section) {
		super();
		this.locationId = locationId;
		this.section = section;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
	}

	@Override
	public boolean equals(Object o) {
		SensorLocation other = (SensorLocation) o;
		return locationId == other.getLocationId() && section == other.getSection();
	}

}
