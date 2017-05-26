package isms.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import isms.common.Constants;
import isms.models.SensorRecord;
import isms.services.Producer;
import isms.services.ProducerSupplier;

@Path(Constants.API_ENDPOINT_RECORDS)
@Consumes(MediaType.APPLICATION_JSON)
public class RecordsApi extends BaseApi {

	private Producer producer;

	public RecordsApi() {
		super();
		producer = new ProducerSupplier().get();
	}

	@POST
	public void send(SensorRecord record) {
		producer.send(record);
	}

}
