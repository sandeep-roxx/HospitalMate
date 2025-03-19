package com.verma.sandeep.hospital.mate.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verma.sandeep.hospital.mate.entity.PaymentDetail;
import com.verma.sandeep.hospital.mate.exception.PaymentDetailNotFoundException;
import com.verma.sandeep.hospital.mate.service.impl.PaymentDetailService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/payment-detail")
public class PaymentDetailController {
	
	@Autowired
	private PaymentDetailService pymtDetail;
	
	//Logged-in patient can download invoice
	@GetMapping("/generate-invoice")
    public ResponseEntity<String>  generateInvoice(Principal principal, 
    		HttpServletResponse response)
	{
		ResponseEntity<String> res=null;
		
		try {
			PaymentDetail paymentDetail=pymtDetail.getPaymentDetailByPatientEmail(principal.getName());
			pymtDetail.generateInvoice(response, paymentDetail);
			
			
		}catch (PaymentDetailNotFoundException pnfe) {
			pnfe.printStackTrace();
			res=new ResponseEntity<String>("Payment detail not found",HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (Exception e) {
			e.printStackTrace();
			res=new ResponseEntity<String>("Somthing went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return res;
	
    }
	
	//Admin can view
	@GetMapping("/all-invoice")
	 public ResponseEntity< List<PaymentDetail>>  findAllPaymentDetails(){
		 List<PaymentDetail> list=pymtDetail.getAllPaymentDetails();
		 return new ResponseEntity<List<PaymentDetail>>(list,HttpStatus.OK);
		 
	 }
	
	

}
