package simulator;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import records.RecordSerde;
import records.SensorRecord;

public class Client {

	public int send(SensorRecord record) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL(Config.ENDPOINT).openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Accept", "text/plain");
		
		DataOutputStream out = new DataOutputStream(connection.getOutputStream());
		out.writeBytes(new RecordSerde().serialize(record));
		out.flush();
		out.close();
		
		return connection.getResponseCode();
	} 
	
	@SuppressWarnings("unused")
	private String readInput(HttpURLConnection connection) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line = null;
		
		while ((line = in.readLine()) != null) {
			sb.append(line);
		}
		in.close();
		
		return sb.toString();
	}

}
