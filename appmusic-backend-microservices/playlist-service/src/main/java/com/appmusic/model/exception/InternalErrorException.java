package com.appmusic.model.exception;

public class InternalErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InternalErrorException() {
	}

	public InternalErrorException(String message) {
		super(message);
	}
}
