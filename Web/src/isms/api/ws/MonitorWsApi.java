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
		int count = 0;
		Throwable root = t;
		while (root.getCause() != null && count++ < 20) {
			root = root.getCause();
		}
		if (root instanceof IOException) {
			// ignore, assume client ended connection
		} else {
			throw t;
		}
	}

}