package drivers;

import java.nio.charset.StandardCharsets;

import com.fazecast.jSerialComm.SerialPort;

import isms.common.SafeNumberParser;

public class ArduinoDriver extends Driver {

	private static final String DELIMITER = ",";
	private static final String DEFAULT_PORT = "/dev/ttyACM0";

	private String port;
	private SafeNumberParser numberParser;

	public ArduinoDriver(String port) {
		this.port = port;
		this.numberParser = new SafeNumberParser();
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
		String partial = "";
		boolean first = true;

		while (true) {
			int bytes;
			while ((bytes = comPort.bytesAvailable()) == 0) {
				Thread.sleep(20);
			}
			if (bytes < 0) throw new Exception("Unexpected number of available bytes: " + bytes);

			byte[] buffer = new byte[bytes];
			comPort.readBytes(buffer, bytes);

			// skip first reading since it's usually inaccurate
			if (first) {
				first = false;
				continue;
			}

			String reading = new String(buffer, StandardCharsets.US_ASCII);
			String[] tokens = reading.split(DELIMITER, -1);

			if (tokens.length == 0) {
				continue;
			}

			int last = tokens.length - 1;

			tokens[0] = partial + tokens[0];
			partial = tokens[last];

			for (int i = 0; i < last; i++) {
				Double value = numberParser.parseDouble(tokens[i]);

				if (value != null) {
					trigger(value);
				}
			}
		}
	}
}
