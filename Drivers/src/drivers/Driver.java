package drivers;

import isms.common.Delegate;
import isms.common.Event;

public abstract class Driver {

	private Event<DriverReading> event;

	protected Driver() {
		this.event = new Event<>();
	}

	protected void trigger(DriverReading reading) {
		event.trigger(reading);
	}

	protected void trigger(double data) {
		trigger(new DriverReading(data));
	}

	public void subscribe(Delegate<DriverReading> delegate) {
		event.subscribe(delegate);
	}

	public abstract void poll();
}
