package com.verma.sandeep.hospital.mate.exception;

public class PaymentDetailNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PaymentDetailNotFoundException() {
	    super();
	}
	
	public PaymentDetailNotFoundException(String message) {
		super(message);
	}
	
	

}
