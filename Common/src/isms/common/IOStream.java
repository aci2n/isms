package isms.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class IOStream {

	public void write(OutputStream out, byte[] data) throws IOException {
		try (DataOutputStream o = new DataOutputStream(out)) {
			o.write(data);
			o.flush();
		}
	}

	public String read(InputStream in) throws IOException {
		StringBuilder sb = new StringBuilder();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		}

		return sb.toString();
	}
}
