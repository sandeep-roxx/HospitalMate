package com.verma.sandeep.hospital.mate.exception;

public class PatientsNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PatientsNotFoundException() {
		super();
	}
	
	public PatientsNotFoundException(String message) {
		super(message);
	}

}
