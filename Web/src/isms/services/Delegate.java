package isms.services;

public interface Delegate<T> {

	public void apply(T alert);
}
