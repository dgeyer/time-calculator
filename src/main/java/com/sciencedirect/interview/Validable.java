package com.sciencedirect.interview;

public interface Validable<T> {
	
	public void validate(T firstTimeToAdd, T secondTimeToAdd);

}
