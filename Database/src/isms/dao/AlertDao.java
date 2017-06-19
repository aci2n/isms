package isms.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import isms.models.Alert;
import isms.models.SensorThreshold;
import isms.models.SensorType;
import isms.models.ThresholdType;

public class AlertDao extends BaseDao {

	private static final String ALL_COLS = "owner_id, sensor_id, sensor_type, threshold_type, lower_bound, upper_bound, sensor_data, sensor_timestamp, is_read";

	public void save(Alert alert) {
		String sql = "insert into Alerts (" + ALL_COLS + ") " + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		run(sql, stmt -> {
			SensorThreshold threshold = alert.getThreshold();
			stmt.setString(1, alert.getOwnerId());
			stmt.setString(2, alert.getSensorId());
			stmt.setString(3, alert.getType().toString());
			stmt.setString(4, threshold.getType().toString());
			stmt.setDouble(5, threshold.getLower());
			stmt.setDouble(6, threshold.getUpper());
			stmt.setDouble(7, alert.getData());
			stmt.setLong(8, alert.getTimestamp());
			stmt.setBoolean(9, alert.isRead());
			stmt.execute();
		});
	}

	public List<Alert> getUnread(String ownerId) {
		List<Alert> unread = new ArrayList<>();
		String sql = "select alert_id, " + ALL_COLS + "from Alerts where owner_id = ? and is_read = 0";

		run(sql, stmt -> {
			stmt.setString(1, ownerId);
			if (stmt.execute()) {
				ResultSet r = stmt.getResultSet();
				while (r.next()) {
					long id = r.getLong(1);
					String oId = r.getString(2);
					String sensorId = r.getString(3);
					SensorType type = SensorType.valueOf(r.getString(4));
					ThresholdType thresholdType = ThresholdType.valueOf(r.getString(5));
					double lower = r.getDouble(6);
					double upper = r.getDouble(7);
					double data = r.getDouble(8);
					long timestamp = r.getLong(9);
					boolean isRead = r.getBoolean(10);

					SensorThreshold threshold = new SensorThreshold(thresholdType, lower, upper);
					Alert alert = new Alert(id, sensorId, oId, type, threshold, data, timestamp, isRead);
					unread.add(alert);
				}
				r.close();
			}
			stmt.execute();
		});

		return unread;
	}

	public void markRead(long id) {
		String sql = "update Alerts set is_read = 1 where alert_id = ?";
		run(sql, stmt -> {
			stmt.setLong(1, id);
			stmt.execute();
		});
	}

}
