package isms.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import isms.common.Constants;
import isms.dao.WindowedMetricDao;
import isms.models.SensorType;
import isms.models.WindowedMetric;
import isms.views.WindowSizeOption;

@Path(Constants.API_ENDPOINT_MONITOR)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MonitorApi extends BaseApi {

	private WindowedMetricDao dao;

	public MonitorApi() {
		super();
		this.dao = dao(new WindowedMetricDao());
	}

	@POST
	public void save(WindowedMetric metric) {
		dao.save(metric);
	}

	@GET
	@Path("/{windowSize}/{type}")
	public List<WindowedMetric> getByWindowSizeAndType(@PathParam("windowSize") long windowSize,
			@PathParam("type") String type) {
		return dao.getByOwnerAndSizeAndType(user(), windowSize, type);
	}

	@GET
	@Path("/sizes")
	public WindowSizeOption[] getAvailableSizes() {
		WindowSizeOption[] options = { new WindowSizeOption("Realtime", 0), new WindowSizeOption(10) };
		return options;
	}

	@GET
	@Path("/types")
	public SensorType[] getTypes() {
		return SensorType.values();
	}
}
