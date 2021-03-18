package com.appmusic.model.exception;

public class CityNotFoundException extends RuntimeException {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default empty UserAlreadyLoggedException constructor
	 */
	public CityNotFoundException() {
	}

	public CityNotFoundException(String message) {
		super(message);
	}
}
