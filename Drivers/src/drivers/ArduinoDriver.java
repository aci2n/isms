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

	public void start() {
		SerialPort comPort = SerialPort.getCommPort(port);

		comPort.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING, 2000, 0);
		comPort.setBaudRate(9600);
		comPort.openPort();

		try {
			poll(comPort);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			comPort.closePort();
		}
	}

	private void poll(SerialPort comPort) throws Exception {
		String partialValue = "";
		boolean skippedFirst = false;

		while (true) {
			int bytesAvailable;
			while ((bytesAvailable = comPort.bytesAvailable()) == 0) {
				Thread.sleep(20);
			}

			// skip first reading since it's usually inaccurate
			if (!skippedFirst) {
				skippedFirst = true;
				continue;
			}

			byte[] readBuffer = new byte[bytesAvailable];
			comPort.readBytes(readBuffer, bytesAvailable);

			String reading = new String(readBuffer, StandardCharsets.US_ASCII);
			String[] tokens = reading.split(DELIMITER, -1); // -1 to include
															// empty strings

			if (tokens.length > 0) {
				int last = tokens.length - 1;

				tokens[0] = partialValue + tokens[0];
				partialValue = tokens[last];

				for (int i = 0; i < last; i++) {
					Double value = numberParser.parseDouble(tokens[i]);

					if (value != null) {
						notify(new DriverEvent(value));
						System.out.println(value);
					}
				}
			}
		}
	}

	public static void main(String args[]) {
		new ArduinoDriver().start();
	}
}
