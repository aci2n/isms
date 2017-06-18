package isms.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import isms.common.Constants;
import isms.dao.AlertDao;
import isms.models.Alert;

@Path(Constants.API_ENDPOINT_ALERTS)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AlertsApi extends BaseApi {

	private AlertDao dao;

	public AlertsApi() {
		super();
		this.dao = dao(new AlertDao());
	}

	@POST
	public void save(Alert alert) {
		dao.save(alert);
	}

	// @POST
	// @Path("/{id}")
	// public void markRead(@PathParam("id") long id) {
	// dao.markRead(id);
	// }

}
