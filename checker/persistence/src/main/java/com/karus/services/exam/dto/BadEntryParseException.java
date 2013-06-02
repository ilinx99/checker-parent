package com.karus.services.exam.dto;

public class BadEntryParseException extends Exception{
	private static final long serialVersionUID = 1L;

	public BadEntryParseException() {
		super();
	}

	public BadEntryParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadEntryParseException(String message) {
		super(message);
	}

	public BadEntryParseException(Throwable cause) {
		super(cause);
	}
}
