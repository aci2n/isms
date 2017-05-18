package isms.dao;

import isms.models.SensorAggregationKey;
import isms.models.SensorMetric;

public class WindowedMetricDao extends BaseDao {

	public void save(SensorAggregationKey key, SensorMetric metric, long size, long start) {
		String sql = "insert into WindowedMetrics (owner_id, sensor_type, window_size, window_start, sensor_avg, sensor_min, sensor_max) "
				+ "values (?, ?, ?, ?, ?, ?, ?) on duplicate key update sensor_avg = ?, sensor_min = ?, sensor_max = ?";
		run(sql, stmt -> {
			stmt.setString(1, key.getOwnerId());
			stmt.setString(2, key.getType().toString());
			stmt.setLong(3, size);
			stmt.setLong(4, start);
			stmt.setDouble(5, metric.getAverage());
			stmt.setDouble(6, metric.getMin());
			stmt.setDouble(7, metric.getMax());
			stmt.setDouble(8, metric.getAverage());
			stmt.setDouble(9, metric.getMin());
			stmt.setDouble(10, metric.getMax());
			stmt.execute();
			return null;
		});
	}

}
