package isms.models;

import isms.common.ObjectMapperUnchecked;

public abstract class Model {

	private static class ObjectMapperHolder {

		private static final ObjectMapperUnchecked mapper = new ObjectMapperUnchecked();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (this == o) return true;
		if (!getClass().equals(o.getClass())) return false;

		return strictEquals(o);
	}

	protected boolean strictEquals(Object o) {
		return true;
	}

	@Override
	public String toString() {
		return ObjectMapperHolder.mapper.writeValueAsString(this);
	}

}
