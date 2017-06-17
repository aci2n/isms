package isms.api.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.websocket.Session;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import isms.common.Constants;
import isms.common.ObjectMapperUnchecked;
import isms.models.SensorRecord;
import isms.models.SensorType;
import isms.services.Consumer;
import isms.services.ConsumerSupplier;

public class MonitorConsumerTask implements Runnable {

	private Consumer consumer;
	private String ownerId;
	private SensorType type;
	private Session session;
	private ObjectMapperUnchecked mapper;

	public MonitorConsumerTask(String ownerId, SensorType type, Session session) {
		this.consumer = new ConsumerSupplier(properties()).get();
		this.ownerId = ownerId;
		this.type = type;
		this.session = session;
		this.mapper = new ObjectMapperUnchecked();
	}

	@Override
	public void run() {
		consumer.subscribe(Arrays.asList(Constants.SENSOR_RECORDS_TOPIC));

		while (session.isOpen()) {
			ConsumerRecords<String, SensorRecord> kafkaRecords = consumer.poll(1000);
			if (kafkaRecords.count() > 0) {
				List<SensorRecord> records = filter(kafkaRecords);
				if (!sendMessage(records)) break;
			}
		}

		consumer.close();
	}

	private Properties properties() {
		Properties props = new Properties();
		props.put(ConsumerConfig.GROUP_ID_CONFIG, Constants.GROUP_ID_MONITOR + "_" + UUID.randomUUID().toString());
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

		return props;
	}

	private List<SensorRecord> filter(ConsumerRecords<String, SensorRecord> kafkaRecords) {
		List<SensorRecord> records = new ArrayList<>();

		for (ConsumerRecord<String, SensorRecord> kafkaRecord : kafkaRecords) {
			SensorRecord record = kafkaRecord.value();
			if (record.getOwnerId().equals(ownerId) && record.getType().equals(type)) {
				records.add(record);
			}
		}

		return records;
	}

	private boolean sendMessage(List<SensorRecord> records) {
		boolean success = true;

		if (!records.isEmpty()) {
			String out = mapper.writeValueAsString(records);

			try {
				session.getBasicRemote().sendText(out);
			} catch (IOException e) {
				success = false;
			}
		}

		return success;
	}

}
