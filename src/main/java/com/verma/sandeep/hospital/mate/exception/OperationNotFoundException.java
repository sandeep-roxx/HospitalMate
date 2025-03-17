package com.verma.sandeep.hospital.mate.exception;

public class OperationNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public OperationNotFoundException() {
		super();
	}
	
	public OperationNotFoundException(String message) {
		super(message);
	}

}
