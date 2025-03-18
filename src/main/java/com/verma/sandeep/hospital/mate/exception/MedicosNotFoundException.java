package com.verma.sandeep.hospital.mate.exception;

public class MedicosNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public MedicosNotFoundException() {
		super();
	}
	
	public MedicosNotFoundException(String message) {
		super(message);
	}

}
