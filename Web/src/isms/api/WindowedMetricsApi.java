package isms.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import isms.common.Constants;
import isms.dao.WindowedMetricDao;
import isms.models.WindowedMetric;

@Path(Constants.API_ENDPOINT_WINDOWED_METRICS)
@Consumes(MediaType.APPLICATION_JSON)
public class WindowedMetricsApi extends BaseApi {

	private WindowedMetricDao dao;

	public WindowedMetricsApi() {
		super();
		this.dao = dao(new WindowedMetricDao());
	}

	@POST
	public void save(WindowedMetric metric) {
		dao.save(metric);
	}
}
