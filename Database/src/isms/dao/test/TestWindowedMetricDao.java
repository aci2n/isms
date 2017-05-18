package isms.dao.test;

import isms.dao.WindowedMetricDao;
import isms.models.SensorAggregationKey;
import isms.models.SensorMetric;
import isms.models.SensorType;

public class TestWindowedMetricDao {

	public static void main(String[] args) {
		WindowedMetricDao dao = new WindowedMetricDao();
		SensorAggregationKey key = new SensorAggregationKey("avalon", SensorType.DUST);
		SensorMetric metric = new SensorMetric();
		dao.save(key, metric, 10, 1234);
	}

}
