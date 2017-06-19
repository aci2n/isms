package isms.api;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import isms.common.Constants;
import isms.dao.AlertDao;
import isms.models.Alert;
import isms.services.AlertDelegate;

@Path(Constants.API_ENDPOINT_ALERTS)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AlertsApi extends BaseApi {

	private static final Map<UUID, AlertDelegate> listeners = new ConcurrentHashMap<>();

	private AlertDao dao;

	public AlertsApi() {
		super();
		this.dao = dao(new AlertDao());
	}

	public static void addListener(UUID key, AlertDelegate delegate) {
		listeners.put(key, delegate);
	}

	public static void removeListener(UUID key) {
		listeners.remove(key);
	}

	private static void doListeners(Alert alert) {
		for (UUID key : listeners.keySet()) {
			try {
				listeners.get(key).apply(alert);
			} catch (Exception ex) {
				ex.printStackTrace();
				removeListener(key);
			}
		}
	}

	@POST
	public void save(Alert alert) {
		dao.save(alert);
		doListeners(alert);
	}

	@POST
	@Path("/{id}")
	public void markRead(@PathParam("id") long id) {
		dao.markRead(id);
	}

}
