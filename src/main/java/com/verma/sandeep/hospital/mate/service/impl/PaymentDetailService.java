package com.verma.sandeep.hospital.mate.service.impl;


import java.util.List;

import com.verma.sandeep.hospital.mate.entity.PaymentDetail;

import jakarta.servlet.http.HttpServletResponse;


public interface PaymentDetailService {
	
	public void savePaymentDetail(PaymentDetail pymt);
	public List<PaymentDetail> getAllPaymentDetails();
	public PaymentDetail getPaymentDetailByPatientEmail(String email);
	public void generateInvoice(HttpServletResponse response,PaymentDetail paymentDetail) throws Exception;
	
	

}
