package isms.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Event<T> {

	private Integer currentId;
	private Map<Integer, Delegate<T>> delegates;

	public class Unsubscriber {

		private Integer id;

		public Unsubscriber(Integer id) {
			this.id = id;
		}

		public void apply() {
			unsubscribe(id);
		}
	}

	public Event() {
		this.currentId = 0;
		this.delegates = new HashMap<>();
	}

	public synchronized Unsubscriber subscribe(Delegate<T> delegate) {
		final Integer id = currentId++;
		delegates.put(id, delegate);

		return new Unsubscriber(id);
	}

	public synchronized void unsubscribe(Integer... ids) {
		for (Integer id : ids) {
			delegates.remove(id);
		}
	}

	public synchronized void trigger(T t) {
		final List<Integer> failures = new ArrayList<>();

		for (Integer id : delegates.keySet()) {
			try {
				delegates.get(id).apply(t);
			} catch (Exception e) {
				failures.add(id);
				e.printStackTrace();
			}
		}

		if (!failures.isEmpty()) {
			unsubscribe(failures.toArray(new Integer[failures.size()]));
		}
	}
}
