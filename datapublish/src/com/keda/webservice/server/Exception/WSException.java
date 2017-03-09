package com.keda.webservice.server.Exception;

public class WSException extends Exception {

	private static final long serialVersionUID = 1L;

	public WSException() {
	}

	public WSException(String message) {
		super(message);
	}

	public WSException(String message, Throwable cause) {
		super(message, cause);
	}

	public WSException(Throwable cause) {
		super(cause);
	}
	
}
