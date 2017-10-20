package drivers;

public abstract class Driver {

	private DriverListener listener;

	protected Driver() {}

	public void notify(DriverEvent event) {
		if (listener != null) {
			listener.apply(event);
		}
	}

	public void setListener(DriverListener listener) {
		this.listener = listener;
	}

	public abstract void start();
}
