package isms.common;

public class Constants {

	public static final String BOOTSTRAP_SERVERS = "localhost:9092";
	public static final String API_PREFIX = "/api/";
	public static final String API_ENDPOINT = "http://localhost:8080" + API_PREFIX;
	public static final String API_ENDPOINT_RECORDS = "records";
	public static final String API_ENDPOINT_MONITOR = "monitor";
	public static final String API_ENDPOINT_ALERTS = "alert";
	public static final String WS_PREFIX = API_PREFIX + "ws/";
	public static final String SENSOR_RECORDS_TOPIC = "sensor_records-v0.1";
	public static final String SENSOR_AGGREGATOR_APPLICATION_ID = "sensor_aggregator-v0.1";
	public static final String SENSOR_AGGREGATIONS_STORE = "sensor_aggregations_store-v0.1";
	public static final String GROUP_ID_MONITOR = "monitor";

}
