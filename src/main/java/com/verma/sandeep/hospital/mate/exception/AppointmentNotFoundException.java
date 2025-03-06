package com.verma.sandeep.hospital.mate.exception;

public class AppointmentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public AppointmentNotFoundException() {
		super();
	}
	
	public AppointmentNotFoundException(String message) {
		super(message);
	}

}
