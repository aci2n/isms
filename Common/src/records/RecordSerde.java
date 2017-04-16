package records;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class RecordSerde {

	public String serialize(SensorRecord record) {
		return new Gson().toJson(record);
	}

	public SensorRecord deserialize(String data) {
		SensorRecord record = null;

		try {
			JsonElement element = new JsonParser().parse(data);
			JsonElement typeElement = element.getAsJsonObject().get("type");

			if (typeElement != null) {
				String type = typeElement.getAsString();
				Class<? extends SensorRecord> recordClass = SensorType.getRecordClass(type);

				if (recordClass != null) {
					record = new Gson().fromJson(element, recordClass);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return record;
	}
}
