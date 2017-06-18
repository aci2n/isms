package isms.dao;

import isms.models.Alert;
import isms.models.SensorThreshold;

public class AlertDao extends BaseDao {

	private static final String ALL_COLS = "owner_id, sensor_id, sensor_type, threshold_type, lower_bound, upper_bound, sensor_data, sensor_timestamp, is_read";

	public void save(Alert alert) {
		String sql = "insert into Alerts (" + ALL_COLS + ") " + "values (?, ?, ?, ?, ?, ?, ?, ?)";
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

	public void markRead(long id) {
		String sql = "update Alerts set is_read = 1 where alert_id = ?";
		run(sql, stmt -> {
			stmt.setLong(1, id);
			stmt.execute();
		});
	}

}
