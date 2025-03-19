package com.verma.sandeep.hospital.mate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verma.sandeep.hospital.mate.Invoice.InvoicePdfGenerator;
import com.verma.sandeep.hospital.mate.entity.PaymentDetail;
import com.verma.sandeep.hospital.mate.exception.PaymentDetailNotFoundException;
import com.verma.sandeep.hospital.mate.repository.PaymentDeatilRepository;

import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class PaymentDetailServiceImpl implements PaymentDetailService {
	
	
	@Autowired
	private PaymentDeatilRepository pymtRepo;
	
	@Autowired
	private InvoicePdfGenerator invoiceGenerator;

	@Override
	public void savePaymentDetail(PaymentDetail pymt) {
		pymtRepo.save(pymt);

	}

	@Override
	public List<PaymentDetail> getAllPaymentDetails() {
		return pymtRepo.findAll();
	}

	@Override
	public PaymentDetail getPaymentDetailByPatientEmail(String email) {
		return  pymtRepo.findByEmail(email)
				                           .orElseThrow(()->new PaymentDetailNotFoundException("Payment detail not found"));
	}

	@Override
	public void generateInvoice(HttpServletResponse response,PaymentDetail paymentDetail) throws Exception {
		invoiceGenerator.generateInvoice(response, paymentDetail);
	}

}
