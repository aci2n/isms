package test;

import com.fazecast.jSerialComm.SerialPort;

@SuppressWarnings("all")
public class SerialTest {

	public static void main(String args[]) {
		SerialPort port = SerialPort.getCommPort("/dev/ttyACM0");
		byte[] buffer = new byte[100];

		port.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING, 2000, 0);
		port.setBaudRate(9600);
		port.openPort();

		try {
			while (true) {
				while (port.bytesAvailable() == 0) {
					Thread.sleep(20);
				}

				byte[] readBuffer = new byte[port.bytesAvailable()];
				int numRead = port.readBytes(readBuffer, readBuffer.length);
				//System.out.println("Read " + numRead + " bytes.");
				System.out.println(new String(readBuffer));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
