package isms.api;

import javax.servlet.annotation.WebServlet;

import isms.dao.WindowedMetricDao;
import isms.models.WindowedMetric;

@WebServlet("/api/windowed-metric")
public class WindowedMetricsApi extends ApiServlet {

	private static final long serialVersionUID = 1L;

	private WindowedMetricDao dao;

	public WindowedMetricsApi() {
		super();
		this.dao = inject(new WindowedMetricDao());
	}

	protected Object post(String in) {
		WindowedMetric metric = mapper.readValue(in, WindowedMetric.class);

		if (metric != null) {
			dao.save(metric);
		} else {
			status = 400;
		}

		return null;
	}
}
