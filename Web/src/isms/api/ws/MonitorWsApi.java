package isms.api.ws;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import isms.common.Constants;
import isms.models.SensorType;

@ServerEndpoint(Constants.WS_PREFIX + Constants.API_ENDPOINT_MONITOR + "/{type}")
public class MonitorWsApi extends BaseWsApi {

	@OnOpen
	public void start(@PathParam("type") String type, Session session) {
		executor.submit(new MonitorConsumerTask(user(), SensorType.valueOf(type), session));
	}

}