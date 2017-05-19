package isms.api;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import isms.models.SensorRecord;

@WebServlet("/api/record")
public class RecordsApi extends ApiServlet {

	private static final long serialVersionUID = 1L;

	private Producer producer;

	public RecordsApi() {
		super();
		producer = new ProducerSupplier().get();
	}

	protected Object post(String in) {
		SensorRecord record = mapper.readValue(in, SensorRecord.class);

		if (record != null) {
			producer.send(record);
		} else {
			status = HttpServletResponse.SC_BAD_REQUEST;
		}

		return null;
	}

}
