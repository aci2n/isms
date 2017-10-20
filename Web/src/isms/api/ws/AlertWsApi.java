package isms.api.ws;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import isms.api.AlertsApi;
import isms.common.Constants;
import isms.common.Event;
import isms.common.ObjectMapperUnchecked;
import isms.dao.AlertDao;
import isms.models.Alert;

@ServerEndpoint(Constants.WS_PREFIX + Constants.API_ENDPOINT_ALERTS)
public class AlertWsApi extends BaseWsApi {

	private AlertDao dao;
	private ObjectMapperUnchecked mapper;
	private Basic remote;
	private Event<Alert>.Unsubscriber unsubscriber;

	public AlertWsApi() {
		super();
		this.mapper = new ObjectMapperUnchecked();
		this.dao = dao(new AlertDao());
	}

	@OnOpen
	public void start(Session session) throws IOException {
		remote = session.getBasicRemote();
		List<Alert> unread = dao.getUnread(user());
		String out = mapper.writeValueAsString(unread);
		remote.sendText(out);
		unsubscriber = AlertsApi.subscribe(this::onAlert);
	}

	public void onAlert(Alert alert) {
		String out = mapper.writeValueAsString(Collections.singletonList(alert));
		try {
			remote.sendText(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@OnClose
	@Override
	public void end() {
		if (unsubscriber != null) {
			unsubscriber.apply();
		}
	}
}