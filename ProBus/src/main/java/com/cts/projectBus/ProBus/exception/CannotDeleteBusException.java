package com.cts.projectBus.ProBus.exception;

public class CannotDeleteBusException extends RuntimeException {
	public CannotDeleteBusException(String message, Throwable cause) {
		super(message,cause);
	}
	public CannotDeleteBusException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
