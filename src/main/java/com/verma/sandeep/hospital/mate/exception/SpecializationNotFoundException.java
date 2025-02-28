package com.verma.sandeep.hospital.mate.exception;

public class SpecializationNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public SpecializationNotFoundException() {
		
	}
	
	public SpecializationNotFoundException(String errMsg) {
		super(errMsg);
	}

}
