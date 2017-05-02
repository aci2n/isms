package isms.receptor;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import isms.records.SensorRecord;

@WebServlet("/api/records")
public class RecordsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BufferedReader reader = request.getReader();
		String line = null;
		StringBuilder sb = new StringBuilder();

		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}

		SensorRecord record = SensorRecord.deserialize(sb.toString());

		if (record != null) {
			new ProducerProvider().get().send(record);
			response.setStatus(200);
		} else {
			response.setStatus(400);
		}
	}
}
