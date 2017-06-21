package isms.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import isms.common.Constants;
import isms.dao.AlertDao;
import isms.models.Alert;
import isms.services.Delegate;
import isms.services.Event;

@Path(Constants.API_ENDPOINT_ALERTS)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AlertsApi extends BaseApi {

	private static final Event<Alert> onAlert = new Event<>();

	public static Event<Alert>.Unsubscriber subscribe(Delegate<Alert> delegate) {
		return onAlert.subscribe(delegate);
	}

	private AlertDao dao;

	public AlertsApi() {
		super();
		this.dao = dao(new AlertDao());
	}

	@POST
	public void save(Alert alert) {
		alert.setId(dao.save(alert));
		onAlert.trigger(alert);
	}

	@POST
	@Path("/{id}")
	public void markRead(@PathParam("id") long id) {
		dao.markRead(id);
	}

}
