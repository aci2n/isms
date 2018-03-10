package isms.common;

public class Constants {

	/*
	 * I'm not even gonna pretend it's OK to have this hardcoded - I just want off this ride.
	 * 
	 * Remote machine 1: vps173930.vps.ovh.ca
	 *  - Kafka I, SQL, Aggregations
	 *  
	 * Remote machine 2: vps173931.vps.ovh.ca
	 *  - Kafka II, Zookeeper, Tomcat, Alerts
	 */
	
	public static final String BOOTSTRAP_SERVERS = "vps173930.vps.ovh.ca:9092,vps173931.vps.ovh.ca:9092";
	public static final String API_PREFIX = "/api/";
	public static final String API_ENDPOINT = "http://vps173931.vps.ovh.ca:8080" + API_PREFIX;
	public static final String API_ENDPOINT_RECORDS = "records";
	public static final String API_ENDPOINT_MONITOR = "monitor";
	public static final String API_ENDPOINT_ALERTS = "alert";
	public static final String WS_PREFIX = API_PREFIX + "ws/";
	public static final String SENSOR_RECORDS_TOPIC = "sensor_records-v0.1";
	public static final String SENSOR_AGGREGATOR_APPLICATION_ID = "sensor_aggregator-v0.1";
	public static final String SENSOR_AGGREGATIONS_STORE = "sensor_aggregations_store-v0.1";
	public static final String GROUP_ID_MONITOR = "monitor";

}
