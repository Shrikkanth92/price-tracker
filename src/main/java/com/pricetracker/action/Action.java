package com.pricetracker.action;

public interface Action<T> {

	public T invoke();
}
