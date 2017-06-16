package isms.views;

public class WindowSizeOption {

	private String label;
	private long size;

	public WindowSizeOption(String label, long size) {
		this.label = label;
		this.size = size;
	}

	public WindowSizeOption(long size) {
		this(Long.toString(size), size);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

}
