package api;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import records.RecordSerdes;
import records.SensorRecord;

@WebServlet("/api/records")
public class RecordsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader reader = request.getReader();
		String line = null;
		StringBuilder sb = new StringBuilder();
		
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		
		String data = sb.toString();
		RecordSerdes serdes = new RecordSerdes();
		SensorRecord record = serdes.deserialize(data);
		
		if (record == null) {
			response.setStatus(400);
		} else {
			response.setStatus(200);
		}
	}

}
