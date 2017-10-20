package isms.common;

public interface Delegate<T> {

	public void apply(T alert);
}
