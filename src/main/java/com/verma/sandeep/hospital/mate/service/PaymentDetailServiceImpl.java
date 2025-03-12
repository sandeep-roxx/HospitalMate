package com.verma.sandeep.hospital.mate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verma.sandeep.hospital.mate.entity.PaymentDetail;
import com.verma.sandeep.hospital.mate.repository.PaymentDeatilRepository;

@Service
public class PaymentDetailServiceImpl implements IPaymentService {
	
	@Autowired
	private PaymentDeatilRepository pymtRepo;

	@Override
	public void savePaymentDetail(PaymentDetail pymt) {
		pymtRepo.save(pymt);

	}

}
