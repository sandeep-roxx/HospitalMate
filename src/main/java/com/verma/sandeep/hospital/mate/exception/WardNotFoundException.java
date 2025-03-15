package com.verma.sandeep.hospital.mate.exception;

public class WardNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public WardNotFoundException() {
		super();
	}
	
	public WardNotFoundException(String message) {
		super( message);
	}

}
