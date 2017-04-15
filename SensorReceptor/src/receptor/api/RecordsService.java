package receptor.api;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import records.RecordSerdes;
import records.SensorRecord;

@Path("/records")
public class RecordsService {

	@POST
	public String receiveRecord(String data) {
		SensorRecord record = new RecordSerdes().deserialize(data);
		return "hi";
	}
	
}
