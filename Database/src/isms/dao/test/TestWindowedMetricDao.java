package isms.dao.test;

import java.util.List;

import isms.dao.WindowedMetricDao;
import isms.models.SensorAggregationKey;
import isms.models.SensorMetric;
import isms.models.SensorType;
import isms.models.WindowedMetric;

public class TestWindowedMetricDao {

	public static void main(String[] args) {
		WindowedMetricDao dao = new WindowedMetricDao();
		SensorAggregationKey key = new SensorAggregationKey("avalon", SensorType.DUST);
		SensorMetric metric = new SensorMetric();
		dao.save(new WindowedMetric(key, metric, 10, 1234));

		List<WindowedMetric> metrics = dao.getByOwnerAndSizeAndType("avalon", 10, SensorType.DUST.toString());
		System.out.println(metrics.size());
	}

}
