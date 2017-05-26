package isms.common;

public enum Path {

	RECORDS("record"),
	WINDOWED_METRICS("windowed-metric");

	private static final String BASE = Constants.API_ENDPOINT;
	private String path;

	Path(String path) {
		this.path = path;
	}

	public String getPath() {
		return this.path;
	}

	public String getFullPath() {
		return BASE + this.getPath();
	}

}
