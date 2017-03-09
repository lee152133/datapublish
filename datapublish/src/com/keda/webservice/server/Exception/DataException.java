package com.keda.webservice.server.Exception;

public class DataException extends WSException {

	private static final long serialVersionUID = 1L;

	public DataException() {
	}

	public DataException(String message) {
		super(message);
	}

	public DataException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataException(Throwable cause) {
		super(cause);
	}
	
}
