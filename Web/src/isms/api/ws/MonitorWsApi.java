package isms.api.ws;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import isms.api.BaseApi;
import isms.common.Constants;
import isms.models.SensorType;

@ServerEndpoint(Constants.WS_PREFIX + Constants.API_ENDPOINT_MONITOR + "/{type}")
public class MonitorWsApi extends BaseApi {

	private static final ExecutorService executor = Executors.newFixedThreadPool(4);

	@OnOpen
	public void start(@PathParam("type") String type, Session session) {
		executor.submit(new MonitorConsumerTask(user(), SensorType.valueOf(type), session));
	}

	@OnClose
	public void end() {
		System.out.println("Closing websocket at thread " + Thread.currentThread().getId() + ".");
	}

	@OnError
	public void error(Throwable t) throws Throwable {
		Throwable root = t;
		for (int i = 0; root.getCause() != null && i < 20; i++) {
			root = root.getCause();
		}
		// Ignore if IOException, assume client ended connection.
		if (!(root instanceof IOException)) throw t;
	}

}