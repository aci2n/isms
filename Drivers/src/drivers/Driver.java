package drivers;

import isms.common.Delegate;

public abstract class Driver {

	private Delegate<DriverEvent> delegate;

	protected void trigger(DriverEvent event) {
		if (delegate != null) {
			delegate.apply(event);
		}
	}

	protected void trigger(double data) {
		trigger(new DriverEvent(data));
	}

	public void setDelegate(Delegate<DriverEvent> delegate) {
		this.delegate = delegate;
	}

	public abstract void poll();
}
