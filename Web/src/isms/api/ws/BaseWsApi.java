package isms.api.ws;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.websocket.OnClose;
import javax.websocket.OnError;

import isms.api.BaseApi;

public abstract class BaseWsApi extends BaseApi {

	protected static final ExecutorService executor = Executors.newFixedThreadPool(4);

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
