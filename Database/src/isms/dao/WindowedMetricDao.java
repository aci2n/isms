package isms.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import isms.models.SensorAggregationKey;
import isms.models.SensorMetric;
import isms.models.SensorType;
import isms.models.WindowedMetric;

public class WindowedMetricDao extends BaseDao {

	private static final String ALL_COLS = "owner_id, sensor_type, window_size, window_start, sensor_avg, sensor_min, sensor_max, sensor_count";

	public void save(WindowedMetric windowedMetric) {
		String sql = "insert into WindowedMetrics (" + ALL_COLS + ") "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?) on duplicate key "
				+ "update sensor_avg = ?, sensor_min = ?, sensor_max = ?, sensor_count = ?";
		run(sql, stmt -> {
			SensorAggregationKey key = windowedMetric.getKey();
			SensorMetric metric = windowedMetric.getMetric();
			stmt.setString(1, key.getOwnerId());
			stmt.setString(2, key.getType().toString());
			stmt.setLong(3, windowedMetric.getWindowSize());
			stmt.setLong(4, windowedMetric.getWindowStart());
			stmt.setDouble(5, metric.getAverage());
			stmt.setDouble(6, metric.getMin());
			stmt.setDouble(7, metric.getMax());
			stmt.setLong(8, metric.getCount());
			stmt.setDouble(9, metric.getAverage());
			stmt.setDouble(10, metric.getMin());
			stmt.setDouble(11, metric.getMax());
			stmt.setLong(12, metric.getCount());
			stmt.execute();
		});
	}

	public List<WindowedMetric> getByOwnerAndSizeAndType(String ownerId, long size, String type) {
		String sql = "select " + ALL_COLS + " from WindowedMetrics where owner_id = ? and window_size = ? and sensor_type = ? limit 10";
		final List<WindowedMetric> windowedMetrics = new ArrayList<>();
		run(sql, stmt -> {
			stmt.setString(1, ownerId);
			stmt.setLong(2, size);
			stmt.setString(3, type);
			if (stmt.execute()) {
				ResultSet r = stmt.getResultSet();
				while (r.next()) {
					String id = r.getString(1);
					SensorType t = SensorType.valueOf(r.getString(2));
					long sz = r.getLong(3);
					long start = r.getLong(4);
					double avg = r.getDouble(5);
					double min = r.getDouble(6);
					double max = r.getDouble(7);
					long count = r.getLong(8);
					SensorAggregationKey key = new SensorAggregationKey(id, t);
					SensorMetric metric = new SensorMetric(avg, min, max, count);
					WindowedMetric windowedMetric = new WindowedMetric(key, metric, sz, start);
					windowedMetrics.add(windowedMetric);
				}
				r.close();
			}
		});

		return windowedMetrics;
	}

}
