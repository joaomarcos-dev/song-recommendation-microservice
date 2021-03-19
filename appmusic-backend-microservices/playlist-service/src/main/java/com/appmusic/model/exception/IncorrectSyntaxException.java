package com.appmusic.model.exception;

public class IncorrectSyntaxException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IncorrectSyntaxException() {
	}

	public IncorrectSyntaxException(String message) {
		super(message);
	}
}
