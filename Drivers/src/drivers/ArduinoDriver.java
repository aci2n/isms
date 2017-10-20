package drivers;

import com.fazecast.jSerialComm.SerialPort;

public class ArduinoDriver extends Driver {

	private static final String DEFAULT_PORT = "/dev/ttyACM0";
	private static final int SIZEOF_MESSAGE = 1;

	private String port;

	public ArduinoDriver(String port) {
		this.port = port;
	}

	public ArduinoDriver() {
		this(DEFAULT_PORT);
	}

	public void poll() {
		SerialPort comPort = null;

		try {
			comPort = SerialPort.getCommPort(port);
			comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 0, 0);
			if (!comPort.openPort()) throw new Exception("Could not open serial port at: " + port);
			poll(comPort);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (comPort != null && comPort.isOpen()) {
				comPort.closePort();
			}
		}
	}

	private void poll(SerialPort comPort) throws Exception {
		byte[] buffer = new byte[SIZEOF_MESSAGE];

		while (true) {
			comPort.readBytes(buffer, SIZEOF_MESSAGE);
			trigger(translate(buffer));
		}
	}

	private double translate(byte[] buffer) {
		return ((buffer[0] & 0xFF) * 5 * 100) / 1024.0;
	}
}
