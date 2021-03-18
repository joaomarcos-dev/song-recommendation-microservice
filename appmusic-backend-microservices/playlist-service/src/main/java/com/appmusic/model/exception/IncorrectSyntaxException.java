package com.appmusic.model.exception;

public class IncorrectSyntaxException extends RuntimeException {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default empty UserAlreadyLoggedException constructor
	 */
	public IncorrectSyntaxException() {
	}

	public IncorrectSyntaxException(String message) {
		super(message);
	}
}
