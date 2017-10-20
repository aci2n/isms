package drivers;

import com.fazecast.jSerialComm.SerialPort;

public class ArduinoDriver extends Driver {

	private static final String DEFAULT_PORT = "/dev/ttyACM0";

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
			comPort.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING, 2000, 0);
			comPort.setBaudRate(9600);
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
		byte[] buffer = new byte[8];
		
		while (true) {
			int bytesAvailable;

			while ((bytesAvailable = comPort.bytesAvailable()) == 0) {
				Thread.sleep(20);
			}
			
			comPort.readBytes(buffer, bytesAvailable);

			for (int i = 0; i < bytesAvailable; i++) {
				trigger(temperature(buffer[i] & 0xFF));
			}
		}
	}

	private double temperature(int reading) {
		return (reading * 5 * 100) / 1024.0;
	}
}
