package isms.api.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import isms.api.BaseApi;
import isms.common.Constants;
import isms.common.ObjectMapperUnchecked;
import isms.models.SensorRecord;
import isms.services.Consumer;
import isms.services.ConsumerSupplier;

@ServerEndpoint(Constants.WS_PREFIX + Constants.API_ENDPOINT_MONITOR)
public class MonitorWsApi extends BaseApi {

	private ObjectMapperUnchecked mapper;
	private Consumer consumer;

	public MonitorWsApi() {
		this.mapper = new ObjectMapperUnchecked();
		this.consumer = new ConsumerSupplier(consumerProperties()).get();
	}

	private static Properties consumerProperties() {
		Properties props = new Properties();
		props.put(ConsumerConfig.GROUP_ID_CONFIG, Constants.GROUP_ID_MONITOR);

		return props;
	}

	@OnOpen
	public void start(Session session) throws InterruptedException, IOException {
		Basic remote = session.getBasicRemote();
		consumer.subscribe(Arrays.asList(Constants.SENSOR_RECORDS_TOPIC));

		while (session.isOpen()) {
			ConsumerRecords<String, SensorRecord> records = consumer.poll(100);
			final int count = records.count();

			if (count > 0) {
				List<SensorRecord> sensorRecords = new ArrayList<>(count);

				for (ConsumerRecord<String, SensorRecord> record : records) {
					sensorRecords.add(record.value());
				}

				String out = mapper.writeValueAsString(sensorRecords);
				remote.sendText(out);
			}
		}
	}

	@OnClose
	public void end() {
		if (consumer != null) {
			consumer.close();
		}
	}

}